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
public class CatTest {

    @Mock
    private Predator predatorMock;
    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(predatorMock);
    }

    @Test
    void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFood() throws Exception {
        List<String> food = List.of("Животные", "Птицы");
        when(predatorMock.eatMeat()).thenReturn(food);

        List<String> result = cat.getFood();
        assertEquals(food, result);
        verify(predatorMock, times(1)).eatMeat();
    }
}
