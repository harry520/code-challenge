package com.example.luckydayandroidtriviagamechallenge;

import java.util.List;

public class OpenTriviaDBResponse {

    private int response_code;
    private List<Result> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "OpenTriviaDBResponse{" +
                "response_code=" + response_code +
                ", results=" + results +
                '}';
    }

}
