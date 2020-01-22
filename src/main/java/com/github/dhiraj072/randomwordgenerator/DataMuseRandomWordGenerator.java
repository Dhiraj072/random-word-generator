package com.github.dhiraj072.randomwordgenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataMuseRandomWordGenerator implements RandomWordGenerator {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(
          DataMuseRandomWordGenerator.class);

  private static final String BASE_URL = "https://api.datamuse.com/words";
  private static final String MAX_RESULTS = "100";
  private DataMuseWord randomWord;
  private AsyncHttpClient httpClient;
  private ObjectMapper objectMapper;

  public DataMuseRandomWordGenerator() {

    try {

      httpClient = Dsl.asyncHttpClient();
      objectMapper = new ObjectMapper();
      Future<Response> responseFuture = initializeNewRandomWord();
      responseFuture.get();
    } catch (ExecutionException | InterruptedException e) {

      LOGGER.warn("Unable to initialize, using a random topic instead of word: {}", e);
      setRandomWord(new DataMuseWord(Topics.getRandomTopic()));
    }

  }

  private ListenableFuture<Response> initializeNewRandomWord() {

    LOGGER.debug("Initializing with new random word");
    BoundRequestBuilder randomWordRequest = httpClient.prepareGet(BASE_URL)
        .addQueryParam("topics", Topics.getRandomTopic())
        .addQueryParam("max", MAX_RESULTS);
    return randomWordRequest.execute(new AsyncCompletionHandler<Response>() {

      @Override
      public Response onCompleted(Response response) throws IOException {

        LOGGER.debug("Got list of words {}", response.getResponseBody());
        List<DataMuseWord> words = objectMapper.readValue(response.getResponseBody(), new TypeReference<List<DataMuseWord>>() {});
        if (words.isEmpty()) {

          LOGGER.warn("Response body is empty. Not setting random word!");
          return response;
        }
        Random random = new Random();
        setRandomWord(words.get(random.nextInt(words.size())));
        return response;
      }
    });
  }

  @Override
  public String getRandomWord() {

    initializeNewRandomWord();
    return randomWord.getWord();
  }

  public void setRandomWord(DataMuseWord randomWord) {

    this.randomWord = randomWord;
  }
}
