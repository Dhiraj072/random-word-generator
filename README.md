# Random word generator

A java library to generate random words.

## Usage
Import using Maven
```xml
<dependency>
  <groupId>com.github.dhiraj072</groupId>
  <artifactId>random-word-generator</artifactId>
  <version>1.0.0</version>
</dependency>
```
or using Gradle
```gradlew
compile 'com.github.dhiraj072:random-word-generator:1.0.0'
```
And use in your code as
```java
// Import the class
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

// Get a random word with a simple static method call
String randomWord = RandomWordGenerator.getRandomWord();
```

## Built With

-   [Gradle](https://gradle.org/) - Build tool
-   [DataMuse API](https://www.datamuse.com/api/) - For getting list of words
-   [JUnit](https://junit.org/) - Testing Framework

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
