package com.github.dhiraj072.randomwordgenerator;

import java.util.Collections;
import java.util.List;

public class DataMuseWord {

  private String word;
  private int score;
  private List<String> tags;

  public DataMuseWord() {};

  public DataMuseWord(String word) {

    this.word = word;
    this.score = 1;
    this.tags = Collections.emptyList();
  }

  public String getWord() {

    return word;
  }

  public void setWord(String word) {

    this.word = word;
  }

  public int getScore() {

    return score;
  }

  public void setScore(int score) {

    this.score = score;
  }

  public List<String> getTags() {

    return tags;
  }

  public void setTags(List<String> tags) {

    this.tags = tags;
  }
}
