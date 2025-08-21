package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FelineParameterizedTest {

    private final Feline feline = new Feline();

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5})
    void testGetKittensParameterized(int kittensCount) {
        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }
}