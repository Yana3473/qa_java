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
    private Predator predatorMock;
    private Lion lionMale;
    private Lion lionFemale;

    @BeforeEach
    void setUp() throws Exception {
        lionMale = new Lion("Самец", predatorMock);
        lionFemale = new Lion("Самка", predatorMock);
    }

    @Test
    void testDoesHaveMane() {
        assertTrue(lionMale.doesHaveMane());
        assertFalse(lionFemale.doesHaveMane());
    }

    @Test
    void testGetKittens() {
        when(predatorMock.getKittens()).thenReturn(1);
        assertEquals(1, lionMale.getKittens());
        assertEquals(1, lionFemale.getKittens());
        verify(predatorMock, times(2)).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        List<String> food = List.of("Животные");
        when(predatorMock.eatMeat()).thenReturn(food);
        List<String> result = lionMale.getFood();
        assertEquals(food, result);
        verify(predatorMock, times(1)).eatMeat();
    }

    @Test
    void testInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Оно", predatorMock);
        });
        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }
}
