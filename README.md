# Random word generator

[![Build Status](https://travis-ci.com/Dhiraj072/random-word-generator.svg?branch=master)](https://travis-ci.com/Dhiraj072/random-word-generator)
[![Coverage Status](https://coveralls.io/repos/github/Dhiraj072/random-word-generator/badge.svg?branch=master)](https://coveralls.io/github/Dhiraj072/random-word-generator?branch=master)

A java library to generate random words.

## Usage
Import from Maven Central

Using Maven
```xml
<dependency>
  <groupId>com.github.dhiraj072</groupId>
  <artifactId>random-word-generator</artifactId>
  <version>1.0.0</version>
</dependency>
```
Using Gradle
```gradlew
compile 'com.github.dhiraj072:random-word-generator:1.0.0'
```
Get a random word
```java
// Import the class
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

// Get a random word with a simple static method call
String randomWord = RandomWordGenerator.getRandomWord();
```
Get a random word skewed towards various parameters supported by ```DataMuseRequest```
```java
import com.github.dhiraj072.randomwordgenerator.datamuse.WordsRequest;
import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequest;

// Get a random word skewed towards topics "Car" and "Road"
WordsRequest customRequest = new DataMuseRequest().topics("Car", "Road");
String randomWord = RandomWordGenerator.getRandomWord(customRequest);
```

## Built With

-   [Gradle](https://gradle.org/) - Build tool
-   [DataMuse API](https://www.datamuse.com/api/) - For getting list of words
-   [JUnit](https://junit.org/) - Testing Framework

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
