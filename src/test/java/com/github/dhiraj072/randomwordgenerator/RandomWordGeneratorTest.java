package com.github.dhiraj072.randomwordgenerator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.dhiraj072.randomwordgenerator.datamuse.WordsRequest;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RandomWordGeneratorTest {

    private class BadDataMuseRequest implements WordsRequest {

        BoundRequestBuilder request;

        public BadDataMuseRequest(BoundRequestBuilder request) {

            this.request = request;
        }

        @Override
        public BoundRequestBuilder build() {

            return request;
        }
    }

    @Test
    void testExecutionExceptionFromDataMuseIsHandledCorrectly() {

        BoundRequestBuilder request = Mockito.mock(BoundRequestBuilder.class);
        ListenableFuture<Response> executionFailureResponse = new CompletedFailure<>(new ExecutionException(null));
        Mockito.when(request.execute()).thenReturn(executionFailureResponse);
        WordsRequest requestToThrowExecutionFailure = new BadDataMuseRequest(request);
        assertThrows(DataMuseException.class, () -> RandomWordGenerator.getRandomWord(requestToThrowExecutionFailure));
    }

    @Test
    void testIOExceptionFromDataMuseIsHandledCorrectly() throws ExecutionException, InterruptedException {

        BoundRequestBuilder request = Mockito.mock(BoundRequestBuilder.class);
        ListenableFuture<Response> executionFailureResponse = Mockito.mock(ListenableFuture.class);
        Mockito.when(request.execute()).thenReturn(executionFailureResponse);
        Mockito.when(executionFailureResponse.get()).thenThrow(IOException.class);
        WordsRequest requestToThrowExecutionFailure = new BadDataMuseRequest(request);
        assertThrows(DataMuseException.class, () -> RandomWordGenerator.getRandomWord(requestToThrowExecutionFailure));
    }

    @Test
    void testInterruptedExceptionFromDataMuseIsHandledCorrectly() throws ExecutionException, InterruptedException {

        BoundRequestBuilder request = Mockito.mock(BoundRequestBuilder.class);
        ListenableFuture<Response> executionFailureResponse = Mockito.mock(ListenableFuture.class);
        Mockito.when(request.execute()).thenReturn(executionFailureResponse);
        Mockito.when(executionFailureResponse.get()).thenThrow(InterruptedException.class);
        WordsRequest requestToThrowExecutionFailure = new BadDataMuseRequest(request);
        assertThrows(IllegalThreadStateException.class, () -> RandomWordGenerator.getRandomWord(requestToThrowExecutionFailure));
    }
}