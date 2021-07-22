public class PalindromeFinderMost {
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        int minLength = 4;
        int maxNunberWithN=0;
        int maxN=0;
        int longest =0;
        String longestWord = " ";
        for (int N=1;N<26;N++) {
            //必须重新读取In
            In in = new In("../library-sp19/data/words.txt");
            CharacterComparator cc = new OffByN(N);

            int i = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                    if (word.length()>longest){
                        longest=word.length();
                        longestWord=word;
                    }
                    i++;
                }
            }
            System.out.println("There are " + i + " palindrome(s) with N="+N);
            if (i>maxNunberWithN){
                maxNunberWithN=i;
                maxN=N;
            }
        }
        System.out.println("N="+maxN+" has the most palindromes in English");
        System.out.println("The longest off-by-N palindrome for any N is "+longestWord);
    }
}
