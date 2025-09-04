import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        try {
            List<Input> frequencies = countFrequencies(words);
            frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
            return composeOutput(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : frequencies) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);

        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            frequencies.add(input);
        }
        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word: words) {
            if (!groups.containsKey(word)) {
                ArrayList arr = new ArrayList<>();
                arr.add(word);
                groups.put(word, arr);
            } else {
                groups.get(word).add(word);
            }
        }
        return groups;
    }
}
