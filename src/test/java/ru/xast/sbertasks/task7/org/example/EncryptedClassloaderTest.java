package ru.xast.sbertasks.task7.org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

class EncryptedClassloaderTest {

    private static EncryptedClassloader classloader;

    @BeforeAll
    public static void setUp() {
        classloader = new EncryptedClassloader("secret",
                new File("src/test/java/ru/xast/sbertasks/task7/org/example/data"),
                ClassLoader.getSystemClassLoader());
    }

    @Test
    void findClass() {
    }
}