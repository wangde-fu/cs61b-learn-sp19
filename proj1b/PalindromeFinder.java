/* This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByN(4);

        int i=0;
        while (!in.isEmpty()) {
            String word = in.readString();
            //if (word.length() >= minLength && palindrome.isPalindrome(word)) {
             if (word.length() >= minLength && palindrome.isPalindrome(word,cc)) {
                System.out.println(word);
                i++;
            }
        }
        System.out.print("There are "+i+" palindrome(s)");
    }
}
//Uncomment this class once you've written isPalindrome.
