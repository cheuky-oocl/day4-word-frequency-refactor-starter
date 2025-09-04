import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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
        for (WordCount w : frequencies) {
            String s = w.word + " " + w.count;
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<WordCount> countFrequencies(String[] words) {
        Map<String, Integer> groups = groupSameWords(words);
        List<WordCount> frequencies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groups.entrySet()) {
            frequencies.add(new WordCount(entry.getKey(), entry.getValue()));
        }
        return frequencies;
    }

    private static Map<String, Integer> groupSameWords(String[] words) {
        Map<String, Integer> groups = new HashMap<>();
        for (String word: words) {
            groups.merge(word, 1, Integer::sum);
        }
        return groups;
    }
}
