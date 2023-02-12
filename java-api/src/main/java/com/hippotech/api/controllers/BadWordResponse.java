package com.hippotech.api.controllers;

public class BadWordResponse {
    private String error;
    private String word;

    public String getError() {
      return error;
    }
    public String getWord() {
        return word;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
