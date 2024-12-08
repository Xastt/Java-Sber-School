package ru.xast.sbertasks.task7.org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        String encryptionKey = "secretKey";
        File dir = new File("./");
        Encryptor encryptor = new Encryptor(dir, encryptionKey);

        try {
            String sourceFilePath = "src/main/java/ru/xast/sbertasks/task7/org/example/instance";
            encryptor.encryptAndSaveClass(sourceFilePath + "/Sample.java");
            File encryptionDir = new File("src/main/java/ru/xast/sbertasks/task7/org/example/instance");
            EncryptedClassloader classLoader = new EncryptedClassloader(encryptionKey, new File(dir.toPath().toString() + "/" + sourceFilePath), null);
            Class<?> loadedClass = classLoader.loadClass("Sample");

            Object instance = loadedClass.getDeclaredConstructor().newInstance();
            loadedClass.getMethod("printMessage").invoke(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException | InvocationTargetException | SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


