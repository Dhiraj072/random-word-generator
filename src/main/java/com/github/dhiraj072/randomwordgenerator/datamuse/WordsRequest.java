package com.github.dhiraj072.randomwordgenerator.datamuse;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.asynchttpclient.BoundRequestBuilder;

/**
 * Represents a request that can be passed to a {@link RandomWordGenerator} to specify various
 * constraints to get the words from a  REST API. Full independence is given to the implementation on
 * what those constraints are. However, this enforces all implementations use {@link BoundRequestBuilder}
 * as the builder for contained HTTP request so that downstream execution is consistent.
 */
public interface WordsRequest {

    /**
     * Build and return the contained HTTP {@link BoundRequestBuilder} request
     * @return
     */
    BoundRequestBuilder build();
}
