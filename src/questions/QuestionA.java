package questions;

import java.util.*;

public class QuestionA {

  // might be more
  private static final Character[] ENDS = new Character[]{' ', '.', ','};

  public static String firstWord(final Stream input) {
    // ordered map
    LinkedHashMap<String, Integer> words = new LinkedHashMap<>();

    StringBuilder wordBuilder = new StringBuilder();


    // read all words and count
    while(input.hasNext()) {
      char c = input.getNext();

      if (Arrays.stream(ENDS).anyMatch(e -> e.equals(c))) {
        addWord(words, wordBuilder.toString());

        wordBuilder = new StringBuilder();

      } else {
        wordBuilder.append(c);
      }
    }

    // last word
    addWord(words, wordBuilder.toString());

    for (Map.Entry<String, Integer> entry : words.entrySet()) {
      if (entry.getValue().equals(1)) {
        // return first unique word
        return entry.getKey();
      }
    }

    throw new RuntimeException("No unique words found in input stream");

  }

  private static void addWord(Map<String, Integer> words, String word) {
    if (word.length() > 0) {
      String lowerCaseWord = word.toLowerCase();

      Integer count = words.getOrDefault(lowerCaseWord, 0);
      words.put(lowerCaseWord, count + 1);
    }
  }

}
