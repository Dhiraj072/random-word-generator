package com.github.dhiraj072.randomwordgenerator.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataMuseRandomWordGeneratorTest {

  private RandomWordGenerator generator;

  @BeforeEach
  void setUp() {

    generator = new DataMuseRandomWordGenerator();
  }

  @Test
  void testGetsRandomWordCorrectly() {

    assertNotNull(generator.getRandomWord());
  }
}