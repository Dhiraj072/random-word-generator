package com.github.dhiraj072.randomwordgenerator.datamuse;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;

/**
 * This class allows you to build a request as per the specifications at
 * <a href="https://www.datamuse.com/api/">DataMuse API docs</a>. You can specify a wide variety
 * of constraints on meaning, spelling, sound, and vocabulary in your request, in any combination.
 *
 * NOTE: Support for quite a lot of the constraints in DataMuse API is yet to be added.
 */
public class DataMuseRequest implements WordsRequest {

    private static final String DATAMUSE_API_URL = "https://api.datamuse.com/words";
    private BoundRequestBuilder request;

    public DataMuseRequest() {

        request = Dsl.asyncHttpClient().prepareGet(DATAMUSE_API_URL);
        this.maxResults(500); // default to 500 for all our requests
    }

    /**
     *  A hint to the system about the theme of the words you want.
     *  Results will be skewed toward these topics. At most 5 words can be specified.
     *  If list contains more than 5 words, first 5 will be taken.
     *
     *  For example, using "Car" as a topic will result in words like "automobile", "driver", etc.
     *
     * @param topics string arguments for your topics
     * @return request with topics param set
     */
    public DataMuseRequest topics(String... topics) {

        StringBuilder topicsParam = new StringBuilder();
        int count = 0;
        for (String topic : topics) {

            topicsParam.append(topic);
            count++;
            if (count == 5)
                break;
            topicsParam.append(",");
        }
        request.addQueryParam("topics", topicsParam.toString());
        return this;
    }

    /**
     * Maximum number of results to return, not to exceed 1000. (default: 100)
     * Private because we don't want this to be visible to the users of
     * {@link RandomWordGenerator#getDataMuseWords(WordsRequest)}, at least for now.
     *
     * @return request with max param set
     */
    private DataMuseRequest maxResults(int maxResults) {

        request.addQueryParam("max", Integer.toString(Math.min(maxResults, 1000)));
        return this;
    }

    @Override
    public BoundRequestBuilder build() {

        return request;
    }
}
