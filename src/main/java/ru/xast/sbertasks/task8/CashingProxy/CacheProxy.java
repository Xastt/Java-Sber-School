package ru.xast.sbertasks.task8.CashingProxy;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.zip.*;

/**
 * Using the CacheProxy class, you can create a proxy for any service that will
 * automatically cache the results of method calls marked with the @Cache annotation.
 * @see Cache
 * @see CacheSettings
 * @author Khasrovyan Artyom
 */
@SuppressWarnings("unchecked")
public class CacheProxy {

    private final String rootPath;
    private final CacheSettings cacheSettings;
    private final ConcurrentHashMap<String, Object> inMemoryCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, ReadWriteLock> locks = new ConcurrentHashMap<>();

    public CacheProxy(String rootPath, CacheSettings cacheSettings) {
        this.rootPath = rootPath;
        this.cacheSettings = cacheSettings;
    }

    /**
     * @param service
     * @return proxy object
     * @param <T>
     */
    public <T> T cache(final T service){
        return (T) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new CacheInvocationHandler(service, rootPath, cacheSettings)
        );
    }

    /**
     * class which processing all operations
     * @see CacheSettings
     * @author Khasrovyan Artyom
     */
    private static class CacheInvocationHandler implements InvocationHandler {

        private final Object service;
        private final String rootPath;
        private final CacheSettings cacheSettings;
        private final ConcurrentHashMap<String, Object> inMemoryCache;
        private final ConcurrentMap<String, ReadWriteLock> locks;

        public CacheInvocationHandler(Object service, String rootPath, CacheSettings cacheSettings, ConcurrentHashMap<String, Object> inMemoryCache, ConcurrentMap<String, ReadWriteLock> locks) {
            this.service = service;
            this.rootPath = rootPath;
            this.cacheSettings = cacheSettings;
            this.inMemoryCache = inMemoryCache;
            this.locks = locks;
        }

        /**
         *Checks if the @Cache annotation is present in the method.
         * If the annotation is present, generates a cache key using the generateCacheKey method.
         * Depending on the type of cache (in memory or file), calls the appropriate method:
         * - IN_MEMORY: calls the method directly (until an in-memory cache is implemented).
         * - FILE: checks if the cached file exists. If the file exists, deserialises it;
         *  if not, calls the original method, serialises the result and saves it to a file.
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Cache cache = method.getAnnotation(Cache.class);
            if(cache != null){
                String cacheKey = generateCacheKey(method, args);
                switch (cache.cacheType()){
                    case IN_MEMORY:
                        return fetchFromInMemoryCache(cacheKey,method,args);
                    case FILE:
                        return fetchFromFileCache(cacheKey, method, args, cache);
                    default:
                        throw new UnsupportedOperationException("Unsupported cache type: " + cache.cacheType());

                }
            }
            return method.invoke(service, args);
        }

        /**
         * generate unique cache key using it name and args
         * @param method
         * @param args
         * @return key
         */
        private String generateCacheKey(Method method, Object[] args) {
            StringBuilder key = new StringBuilder(method.getName());
            for (Object arg : args) {
                key.append("_").append(arg != null ? arg.toString().replaceAll("[<>:\"/\\|?*]", "_"):null);
            }
            return key.toString();
        }

        /**
         * @param cacheKey
         * @param method
         * @param args
         * @return data from cache
         * @throws Throwable
         */
        private Object fetchFromInMemoryCache(String cacheKey, Method method, Object[] args, ReadWriteLock lock) throws Throwable {
            lock.readLock().lock();
            try {
                if (inMemoryCache.containsKey(cacheKey)) {
                    return inMemoryCache.get(cacheKey);
                }
            } finally {
                lock.readLock().unlock();
            }

            lock.writeLock().lock();
            try {
                if (!inMemoryCache.containsKey(cacheKey)) {
                    Object result = method.invoke(service, args);
                    inMemoryCache.put(cacheKey, result);
                    return result;
                } else {
                    return inMemoryCache.get(cacheKey);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        /**
         * control caching in file
         * @param cacheKey
         * @param method
         * @param args
         * @param cache
         * @throws Throwable
         */
        private Object fetchFromFileCache(String cacheKey, Method method, Object[] args, Cache cache, ReadWriteLock lock) throws Throwable {
            String filePath = createFilePath(cache, cacheKey);
            lock.readLock().lock();
            try {
                if (Files.exists(Paths.get(filePath))) {
                    return deserialize(filePath);
                }
            } finally {
                lock.readLock().unlock();
            }

            lock.writeLock().lock();
            try {
                if (!Files.exists(Paths.get(filePath))) {
                    Object result = method.invoke(service, args);
                    serialize(filePath, result);
                    return result;
                } else {
                    return deserialize(filePath);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }


        /**
         * generate file path for data cache
         * @param cache
         * @param cacheKey
         * @return
         */
        private String createFilePath(Cache cache, String cacheKey) {
            return rootPath + File.separator + (cache.fileName().isEmpty() ? "" : cache.fileName() + "_") + cacheKey + ".ser";
        }


        /**
         * serialize object and save it in file
         * @param filePath
         * @param obj
         * @throws IOException
         */
        private void serialize(String filePath, Object obj) throws IOException {
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
                out.writeObject(obj);
                if (cacheSettings.isZip()) {
                    zipFile(filePath);
                }
            }
        }

        /**
         * deserialize object from file
         * @param filePath
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private Object deserialize(String filePath) throws IOException, ClassNotFoundException {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
                return in.readObject();
            }
        }

        /**
         * method for compress file to the zip format
         * @param filePath
         * @throws IOException
         */
        private void zipFile(String filePath) throws IOException {
            try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(Paths.get(filePath + ".zip")))) {
                File fileToZip = new File(filePath);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    zos.putNextEntry(new ZipEntry(fileToZip.getName()));
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }
                }
            }
        }
    }
}
