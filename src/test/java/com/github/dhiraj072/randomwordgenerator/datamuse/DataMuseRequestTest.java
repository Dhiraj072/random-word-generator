package com.github.dhiraj072.randomwordgenerator.datamuse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.asynchttpclient.BoundRequestBuilder;
import org.junit.jupiter.api.Test;

class DataMuseRequestTest {

    @Test
    void testSetsTopicsCorrectly() {

        BoundRequestBuilder request =
            new DataMuseRequest().topics("topic1", "topic2").build();
        assertEquals("topic1%2Ctopic2%2C", request.build().getQueryParams().get(1).getValue());
    }

    @Test
    void testTakesFirstFiveTopicsOnly() {

        BoundRequestBuilder request =
            new DataMuseRequest().topics("topic1", "topic2", "topic3", "topic4", "topic5", "topic6").build();
        assertEquals("topic1%2Ctopic2%2Ctopic3%2Ctopic4%2Ctopic5",
            request.build().getQueryParams().get(1).getValue());
    }
}