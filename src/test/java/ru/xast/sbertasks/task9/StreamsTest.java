package ru.xast.sbertasks.task9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamsTest {
    @Test
    void testAllOperations(){
        List<String> names = Arrays.asList("Alice", "Jack", "Bob", "Den");

        Map<Integer, String> nameMap = Streams.of(names)
                .filter(name -> name.length() > 3)
                .transform(String::toUpperCase)
                .toMap(String::length, name -> name);

        assertEquals(2, nameMap.size());
        assertEquals("ALICE", nameMap.get(5));
        assertEquals("JACK", nameMap.get(4));
    }

    @Test
    void testAllOperationsWithPerson(){
        List<Person> users = Arrays.asList(
                new Person("Alice", 20),
                new Person("Jack",21),
                new Person( "Bob",22),
                new Person("Den", 23));

        Map<String, Person> sortedUsers = Streams.of(users)
                .filter(n -> n.getAge() > 20)
                .transform(n -> new Person(n.getName(), n.getAge()+10))
                .toMap(Person::getName, n->n);

        System.out.println(sortedUsers);
    }

}