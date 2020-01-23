package com.github.dhiraj072.randomwordgenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomWordGenerator {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(RandomWordGenerator.class);

  private static final String BASE_URL = "https://api.datamuse.com/words";
  private static final String MAX_RESULTS = "1000";
  private static List<DataMuseWord> randomWords;
  private static final ObjectMapper MAPPER = new ObjectMapper();;

  static {

        initializeNewRandomWords();
  }

  private static void initializeNewRandomWords() {

    LOGGER.debug("Initializing with new random word");
    try {

      Response response = dataMuseWordsRequest().execute().get();
      LOGGER.debug("Got list of words {}", response.getResponseBody());
      List<DataMuseWord> words = MAPPER.readValue(response.getResponseBody(),
          new TypeReference<List<DataMuseWord>>() {});
      if (words.isEmpty()) {

        LOGGER.warn("Datamuse API returned nothing, setting random word from local word list");
        setRandomWordLocally();
        return;
      }
      setRandomWords(words);
    } catch (ExecutionException | InterruptedException | IOException e) {

      LOGGER.warn("Error initializing, setting random word from local word list");
      LOGGER.trace("Exception initializing {}", e);
      setRandomWordLocally();
    }
  }

  public static String getRandomWord() {

    new Thread(RandomWordGenerator::initializeNewRandomWords).start();
    Random random = new Random();
    return randomWords.get(random.nextInt(randomWords.size())).getWord();
  }

  private static void setRandomWordLocally() {

    randomWords = Collections.singletonList(new DataMuseWord(Topics.getRandomTopic()));
  }

  private static void setRandomWords(List<DataMuseWord> words) {

    randomWords = words;
  }

  private static BoundRequestBuilder dataMuseWordsRequest() {

    return Dsl.asyncHttpClient().prepareGet(BASE_URL)
        .addQueryParam("topics", Topics.getRandomTopic())
        .addQueryParam("max", MAX_RESULTS);
  }
}
