package streams;

import java.util.*;
import java.util.stream.Collectors;

public class examples {
    public static void main(String[] args) {

        /***
         * First Non-Repeated Character: Given a string, find the first character that does not repeat anywhere in the string.
         * If no such character exists, return null or an empty result.
         *
         * input: "swiss"
         * output: "w"
         */

        String input = "swiss";

        char ans = input.chars() //IntStream of ASCII values
                .mapToObj(c->(char) c) //Converts ASCII → actual characters:
                .filter(c->input.indexOf(c)==input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
        System.out.println(ans);


        /***
         * 2. First Repeated Character: Given a string, find the first character that appears more than once based on the order of appearance.
         *
         * input: "programming"
         * output: "r"
         */

        Set<Character> seen =  new HashSet<>();
        String input2 = "programming";
        char ans2 = input2.chars()
                .mapToObj(c->(char)c)
                .filter(c->!seen.add(c)) //seen.add(c) returns: true → if element was NOT present
                .findFirst().orElse(null);

        System.out.println(ans2);

        /***
         * 3. Character Frequency: Given a string, return the frequency of each character present in the string.
         * input: "banana"
         * output: {b=1, a=3, n=2}
         */

        String input3="banana";
         Map<Character, Long> ans3 = input3.chars()
                             .mapToObj(c->(char) c)
                             .collect(Collectors.groupingBy(c->c, Collectors.counting()));

        System.out.println(ans3);

        /***
         * 4. Anagram Check: Given two strings, check whether they are anagrams of each other. Ignore case and spaces.
         *
         * input: "listen", "silent"
         * output: true
         */

        String s1="listen";
        String s2="silent";

        boolean isAnagram = Arrays.equals(s1.replaceAll("\\s","").chars().sorted().toArray(), s2.replaceAll("\\s", "").chars().sorted().toArray());

        System.out.println(isAnagram);

        /***
         *
         * 5. Reverse Each Word in a Sentence: Given a sentence, reverse each word individually while preserving the word order.
         */
        String input4 = "Java streams are powerful";
        String result = Arrays.stream(input4.split(" "))
                .map(word-> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println(result);


        /***
         * 6. Longest Word in a String: Given a sentence, find the longest word.
         *  If multiple words have the same maximum length, return the first one.
         *
         * input: "Streams make Java expressive"
         * output: "expressive"
         */

        String input5 = "Streams make Java expressive";
        String ans4 = Arrays.stream(input5.split(" "))
                .max(Comparator.comparing(String::length))
                .orElse("");

        System.out.println(ans4);

        /***
         * 7. Remove Duplicate Characters: Given a string, remove duplicate characters while preserving the original order.
         *
         * input: "programming"
         * output: "progamin"
         */

        String input7 = "programming";

        String ans5 = input7.chars()
                .mapToObj(c->(char)c)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(ans5);

        /***
         * 8. Count Vowels and Consonants: Given a string, count the number of vowels and consonants. Ignore spaces and non-alphabetic characters.
         *
         * input: "Hello World"
         * output: Vowels: 3, Consonants: 7
         */

        String input6 = "Hello World";
        Map<Boolean, Long> counts = input6.toLowerCase().chars()
                .mapToObj(c->(char)c)
                .filter(Character::isLetter)
                .collect(Collectors.partitioningBy(
                        c->"aeiou".indexOf(c) != -1,
                        Collectors.counting()
                ));

        System.out.println(counts.get(true));
        System.out.println(counts.get(false));

        /***
         * Flatten List of Lists
         */
        List<List<Integer>> list = List.of(
                List.of(1,2),
                List.of(3,4)
        );

        List<Integer> flat = list.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println(flat);
    }

    /***
     * create a map where Key = length of the string
     * Value = list of strings having that length
     */

    List<String> wordsList = List.of( "apple", "banana", "cherry", "date", "fig", "grapefruit", "kiwi" );

    Map<Integer, List<String>> result =
            wordsList.stream()
                    .collect(Collectors.groupingBy(
                            String::length,
                            Collectors.toList()
                    ));
}