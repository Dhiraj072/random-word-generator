package com.github.dhiraj072.randomwordgenerator.datamuse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Param;
import org.junit.jupiter.api.Test;

class DataMuseRequestTest {

    @Test
    void testSetsTopicsCorrectly() {

        BoundRequestBuilder request =
            new DataMuseRequest().topics("topic1", "topic2").build();
        assertEquals("topic1%2Ctopic2%2C", getBoundRequestParam(request, "topics"));
    }

    @Test
    void testTakesFirstFiveTopicsOnly() {

        BoundRequestBuilder request =
            new DataMuseRequest().topics("topic1", "topic2", "topic3", "topic4", "topic5", "topic6").build();
        assertEquals("topic1%2Ctopic2%2Ctopic3%2Ctopic4%2Ctopic5",
            getBoundRequestParam(request, "topics"));
    }

    @Test
    void testSetsMeansLikeParamCorrectly() {

        BoundRequestBuilder request = new DataMuseRequest().meansLike("ringing in the ears").build();
        assertEquals("ringing%20in%20the%20ears", getBoundRequestParam(request,"ml"));
    }

    @Test
    void testSetsSoundsLikeParamCorrectly() {

        BoundRequestBuilder request = new DataMuseRequest().soundsLike("ringing in the ears").build();
        assertEquals("ringing%20in%20the%20ears", getBoundRequestParam(request,"sl"));
    }

    private static String getBoundRequestParam(BoundRequestBuilder request, String paramName) {

        Optional<Param> p = request.build().getQueryParams().stream().
            filter(q -> q.getName().equals(paramName)).findFirst();
        assertTrue(p.isPresent(), paramName + " not present in the request");
        return p.get().getValue();
    }
}