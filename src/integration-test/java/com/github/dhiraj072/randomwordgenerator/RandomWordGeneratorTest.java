package com.github.dhiraj072.randomwordgenerator;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RandomWordGeneratorTest {

  @Test
  void testGetsRandomWordCorrectly() {

    String word = RandomWordGenerator.getRandomWord();
    assertNotNull(word);
    assertNotEquals(word, RandomWordGenerator.getRandomWord());
  }
}