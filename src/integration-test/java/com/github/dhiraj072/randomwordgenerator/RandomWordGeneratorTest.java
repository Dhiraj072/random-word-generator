package com.github.dhiraj072.randomwordgenerator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequestBuilder;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Integration tests with <a href="https://api.datamuse.com/words">DataMuse API endpoint</a>
 */
class RandomWordGeneratorTest {

  @Test
  void testGetsRandomWordCorrectly() {

    String word = RandomWordGenerator.getRandomWord();
    assertNotNull(word);
  }

  @Test
  void testGetsRandomWordByTopicCorrectly() throws DataMuseException {

    DataMuseRequestBuilder request = new DataMuseRequestBuilder().topics("Car").maxResults("1");
    String actualWord = RandomWordGenerator.getRandomWord(request);
    // Expected list of words we should get as per a manual request for https://api.datamuse.com/words?topics=Car
    // executed on DataMuse API on 4 July 2020. There is a little chance 'might' change in future which will
    // break this test, and should be updated accordingly
    List<String> expectedWords = Arrays.asList("automobile","motorcar","auto","railcar",
        "machine","gondola","cable car","elevator car","railroad car","railway car",
        "vehicle","truck","sedan","van","suv","vehicles","driver","cruiser","cab","tire",
        "motor","limo","passenger","taxi","bus","wheel");
    assertTrue(expectedWords.contains(actualWord));
  }
}