package ru.xast.sbertasks.task7.org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassloader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassloader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.dir = dir;
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = this.dir.getAbsolutePath() + File.separator + name.replace('.', File.separatorChar) + ".class";
        File classFile = new File(filePath);
        if (!classFile.exists()) {
            throw new ClassNotFoundException("Class " + name + " not found");
        }
        try{
            byte[] encryptedBytes = Files.readAllBytes(classFile.toPath());
            byte[] decryptedBytes = decrypt(encryptedBytes);
            return defineClass(name, decryptedBytes, 0, decryptedBytes.length);
        }catch (IOException e){
            throw new ClassNotFoundException("Failed to load class " + name, e);
        }
    }

    private byte[]  decrypt(byte[] encryptedBytes) {
        byte[] decryptedBytes = new byte[encryptedBytes.length];
        int keyLength = key.length();
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte)(encryptedBytes[i]-key.charAt(i % keyLength));
        }
        return decryptedBytes;
    }
}
