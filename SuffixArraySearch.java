import java.util.*;

public class SuffixArraySearch {
    static String text;
    static int[] suffixArray;

    static void buildSuffixArray() {
        Integer[] sa = new Integer[text.length()];

        for (int i = 0; i < text.length(); i++) {
            sa[i] = i;
        }

        Arrays.sort(sa, (a, b) -> text.substring(a).compareTo(text.substring(b)));

        suffixArray = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            suffixArray[i] = sa[i];
        }
    }

    static List<Integer> searchPattern(String pattern) {
        List<Integer> result = new ArrayList<>();

        for (int index : suffixArray) {
            if (text.startsWith(pattern, index)) {
                result.add(index);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        text = "banana$";
        String pattern = "an";

        buildSuffixArray();

        System.out.println("Suffix Array:");

        for (int i = 0; i < suffixArray.length; i++) {
            System.out.println(
                "i=" + i +
                " suffix=" + text.substring(suffixArray[i]) +
                " sa[i]=" + suffixArray[i]
            );
        }

        List<Integer> positions = searchPattern(pattern);

        System.out.println("\nPattern: " + pattern);
        System.out.println("Pattern found at positions: " + positions);
    }
}