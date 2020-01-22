package com.github.dhiraj072.randomwordgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataMuseWordTest {

  private ObjectMapper mapper;

  @BeforeEach
  void setUp() {

    mapper = new ObjectMapper();
  }

  @Test
  void  testConstructsCorrectly() {

    DataMuseWord word = new DataMuseWord("test");
    assertEquals(1, word.getScore());
    assertEquals("test", word.getWord());
    assertNotNull(word.getTags());
  }

  @Test
  void testJsonWordIsDeserializedCorrectly() throws IOException {

    String dataMuseWordJson = "{\"word\":\"terpsichore\",\"score\":91967,\"tags\":[\"syn\",\"n\",\"prop\"]}";
    DataMuseWord word = mapper.readValue(dataMuseWordJson, DataMuseWord.class);
    assertEquals("terpsichore", word.getWord());
    assertEquals(91967, word.getScore());
    assertEquals(Arrays.asList("syn", "n", "prop"), word.getTags());
  }

  @Test
  void testJsonWordListIsDeserializedCorrectly() throws IOException {

    String dataMuseWordList = "[{\"word\":\"terpsichore\",\"score\":91967,\"tags\":[\"syn\",\"n\",\"prop\"]},{\"word\":\"trip the light fantastic\",\"score\":78293,\"tags\":[\"syn\",\"v\"]}]";
    List<DataMuseWord> words = mapper.readValue(dataMuseWordList, new TypeReference<List<DataMuseWord>>() {});
    assertEquals(2, words.size());
  }
}