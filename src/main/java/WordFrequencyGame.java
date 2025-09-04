import java.util.*;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public record WordCount(String word, int count) {}

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        try {
            List<WordCount> frequencies = countFrequencies(words);
            frequencies.sort((word1, word2) -> word2.count - word1.count);
            return composeOutput(frequencies);
        } catch (Exception calculateError) {
            return "Calculate Error";
        }
    }

    private static String composeOutput(List<WordCount> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        frequencies.forEach(w -> joiner.add(w.word + " " + w.count));
        return joiner.toString();
    }

    private List<WordCount> countFrequencies(String[] words) {
        Map<String, Integer> groups = groupSameWords(words);
        List<WordCount> frequencies = new ArrayList<>();
        groups.forEach((word, count) -> frequencies.add(new WordCount(word, count)));
        return frequencies;
    }

    private static Map<String, Integer> groupSameWords(String[] words) {
        Map<String, Integer> groups = new HashMap<>();
        Arrays.stream(words).forEach(word -> groups.merge(word, 1, Integer::sum));
        return groups;
    }
}
