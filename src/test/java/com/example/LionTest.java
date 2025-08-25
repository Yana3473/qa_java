package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    private Feline felineMock;
    private Lion lionMale;
    private Lion lionFemale;

    @BeforeEach
    void setUp() throws Exception {
        lionMale = new Lion("Самец", felineMock);
        lionFemale = new Lion("Самка", felineMock);
    }

    @Test
    void testDoesHaveManeTrue() {
        assertTrue(lionMale.doesHaveMane());
    }

    @Test
    void testDoesHaveManeFalse() {
        assertFalse(lionFemale.doesHaveMane());
    }

    @Test
    void testGetKittens() {
        when(felineMock.getKittens()).thenReturn(1);
        assertEquals(1, lionMale.getKittens());
        assertEquals(1, lionFemale.getKittens());
        verify(felineMock, times(2)).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        List<String> food = List.of("Животные");
        when(felineMock.eatMeat()).thenReturn(food);
        List<String> result = lionMale.getFood();
        assertEquals(food, result);
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void testInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Оно", felineMock);
        });
        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }
}
