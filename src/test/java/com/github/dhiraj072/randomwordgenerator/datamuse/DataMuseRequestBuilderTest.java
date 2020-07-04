package com.github.dhiraj072.randomwordgenerator.datamuse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.asynchttpclient.BoundRequestBuilder;
import org.junit.jupiter.api.Test;

class DataMuseRequestBuilderTest {

    @Test
    public void testSetsTopicsCorrectly() {

        BoundRequestBuilder request =
            new DataMuseRequestBuilder().topics("topic1", "topic2").build();
        assertEquals(request.build().getQueryParams().get(0).getValue(), "topic1%2Ctopic2%2C");
    }
}