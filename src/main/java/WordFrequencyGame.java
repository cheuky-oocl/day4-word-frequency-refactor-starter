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
        List<String> inputList = new ArrayList<>();
        for (String s : words) {
            inputList.add(s);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<String>> groups1 = new HashMap<>();
        for (String input1 : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!groups1.containsKey(input1)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input1);
                groups1.put(input1, arr);
            } else {
                groups1.get(input1).add(input1);
            }
        }
        Map<String, List<String>> groups = groups1;

        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            frequencies.add(input);
        }
        return frequencies;
    }

}
