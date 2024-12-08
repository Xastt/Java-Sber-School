package ru.xast.sbertasks.task7.org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encryptor {

    private final File dir;
    private final String key;

    public Encryptor(File dir, String key) {
        this.dir = dir;
        this.key = key;
    }

    public Path encryptAndSaveClass(String className) throws IOException {
        if(!className.endsWith(".java")) {
            throw new IllegalArgumentException("The class name must end with .java");
        }

        Path filePath = dir.toPath().resolve(className);
        byte[] bytes = Files.readAllBytes(filePath);

        encrypt(bytes);

        int dotIndex = className.lastIndexOf(".");

        Path encryptedFilePath = dir.toPath().resolve(className.substring(0, dotIndex));
        Files.write(encryptedFilePath, bytes);
        return encryptedFilePath;
    }

    private void encrypt(byte[] bytes) {
        byte code = (byte) key.length();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] -= code;
        }
    }
}
