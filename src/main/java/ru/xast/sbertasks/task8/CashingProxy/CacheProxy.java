package ru.xast.sbertasks.task8.CashingProxy;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.zip.*;

@SuppressWarnings("unchecked")
public class CacheProxy {

    private final String rootPath;
    private final CacheSettings cacheSettings;

    public CacheProxy(String rootPath, CacheSettings cacheSettings) {
        this.rootPath = rootPath;
        this.cacheSettings = cacheSettings;
    }

    public <T> T cache(final T service){
        return (T) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new CacheInvocationHandler(service, rootPath, cacheSettings)
        );
    }

    private static class CacheInvocationHandler implements InvocationHandler {

        private final Object service;
        private final String rootPath;
        private final CacheSettings cacheSettings;

        public CacheInvocationHandler(Object service, String rootPath, CacheSettings cacheSettings) {
            this.service = service;
            this.rootPath = rootPath;
            this.cacheSettings = cacheSettings;
        }

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

        private String generateCacheKey(Method method, Object[] args) {
            StringBuilder key = new StringBuilder(method.getName());
            for (Object arg : args) {
                key.append("_").append(arg != null ? arg.toString().replaceAll("[<>:\"/\\|?*]", "_"):null);
            }
            return key.toString();
        }

        private Object fetchFromInMemoryCache(String cacheKey, Method method, Object[] args) throws Throwable {
            return method.invoke(service, args);
        }

        private Object fetchFromFileCache(String cacheKey, Method method, Object[] args, Cache cache) throws Throwable {
            String filePath = createFilePath(cache, cacheKey);
            if (Files.exists(Paths.get(filePath))) {
                return deserialize(filePath);
            } else {
                Object result = method.invoke(service, args);
                serialize(filePath, result);
                return result;
            }
        }


        private String createFilePath(Cache cache, String cacheKey) {
            return rootPath + File.separator + (cache.fileName().isEmpty() ? "" : cache.fileName() + "_") + cacheKey + ".ser";
        }


        private void serialize(String filePath, Object obj) throws IOException {
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
                out.writeObject(obj);
                if (cacheSettings.isZip()) {
                    zipFile(filePath);
                }
            }
        }

        private Object deserialize(String filePath) throws IOException, ClassNotFoundException {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
                return in.readObject();
            }
        }

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
