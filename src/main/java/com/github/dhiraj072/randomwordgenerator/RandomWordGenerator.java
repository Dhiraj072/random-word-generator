package com.github.dhiraj072.randomwordgenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequest;
import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseWord;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Get random words using <a href="https://www.datamuse.com/api/">DataMuse API</a>
 */
public class RandomWordGenerator {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(RandomWordGenerator.class);

  private static List<DataMuseWord> randomWords;
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static Random random = new Random();

  static {

        initializeNewRandomWords();
  }

  /**
   * Makes a HTTP request to {@link DataMuseRequest#DATAMUSE_API_URL} with a random topic
   * from {@link Topics} to get a list of words, and updates {@link #randomWords}
   * with the result
   */
  private static void initializeNewRandomWords() {

    LOGGER.debug("Initializing with new random word");
    try {

      DataMuseRequest standardRequest = new DataMuseRequest()
          .topics(Topics.getRandomTopic())
          .maxResults("1000");
      setRandomWords(getDataMuseWords(standardRequest));
    } catch (DataMuseException e) {

      LOGGER.error("Error getting word from DataMuse, setting a random word locally", e);
      setRandomWordLocally();
    }
  }

  private static List<DataMuseWord> getDataMuseWords(DataMuseRequest request) throws DataMuseException {

    try {

      Response response = request.build().execute().get();
      LOGGER.debug("Got list of words {}", response.getResponseBody());
      List<DataMuseWord> words = MAPPER.readValue(response.getResponseBody(),
          new TypeReference<List<DataMuseWord>>() {});
      if (words.isEmpty()) {

        throw new DataMuseException("DataMuse API did not return any words for request " + request.toString());
      }
      return words;
    } catch (ExecutionException | IOException e) {

      throw new DataMuseException(e.getMessage(), e);
    } catch (InterruptedException e) {

      Thread.currentThread().interrupt();
      throw new IllegalThreadStateException("Thread interrupted: " + e.toString());
    }
  }

  /**
   * Get a random word. This has the side-effect of calling the
   * {@link #initializeNewRandomWords()} to get the next set of
   * random words from DataMuse API, see {@link DataMuseRequest#DATAMUSE_API_URL}
   * @return a random word
   */
  public static String getRandomWord() {

    new Thread(RandomWordGenerator::initializeNewRandomWords).start();
    return randomWords.get(random.nextInt(randomWords.size())).getWord();
  }

  /**
   * Get a random word that is skewed towards the constraints provided in {@link DataMuseRequest} param.
   *
   * For example, you can get the random word skewed toward certain topics you want (e.g. Car, Human) by
   * setting {@link DataMuseRequest#topics(String...)} in your request parameter.
   *
   * @param request DataMuse request with custom params specified as per the random word result required
   * @return a random word from the result of the incoming request
   */
  public static String getRandomWord(DataMuseRequest request) throws DataMuseException {

    List<DataMuseWord> wordsReturned = getDataMuseWords(request);
    return wordsReturned.get(random.nextInt(wordsReturned.size())).getWord();
  }

  private static void setRandomWordLocally() {

    randomWords = Collections.singletonList(new DataMuseWord(Topics.getRandomTopic()));
  }

  private static void setRandomWords(List<DataMuseWord> words) {

    randomWords = words;
  }
}
