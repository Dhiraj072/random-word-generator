package com.github.dhiraj072.randomwordgenerator.datamuse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.asynchttpclient.BoundRequestBuilder;
import org.junit.jupiter.api.Test;

class DataMuseRequestTest {

    @Test
    public void testSetsTopicsCorrectly() {

        BoundRequestBuilder request =
            new DataMuseRequest().topics("topic1", "topic2").build();
        assertEquals("topic1%2Ctopic2%2C", request.build().getQueryParams().get(0).getValue());
    }
}