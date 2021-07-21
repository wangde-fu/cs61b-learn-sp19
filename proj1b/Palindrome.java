public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordInDeque = new ArrayDeque<>();
        //将字符串转为字符串数组
        char[] chars = word.toCharArray();
        for(char theChar : chars){
            wordInDeque.addLast(theChar);
        }
        return wordInDeque;
    }
    public static void main(String[] args){
        Palindrome palindrome = new Palindrome();
        String test = "abcdefghijklmnopq";
        Deque<Character> temp = palindrome.wordToDeque(test);
    }
}
