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
    /*public boolean isPalindrome(String word){
        if (word == null) { return false; }
        Deque<Character> after=isPalindrome(wordToDeque(word));
        if(after.size() ==0){return true;}
        else{
            //仅剩长度为1的字母
            Character First=after.removeFirst();
            //System.out.println(word+":"+First);
            //如果是回文，则该字母非null，否则为null
            return !(First==null);
        }
    }
    //这里写复杂的原因是我没有意识到迭代的返回值类型可以与输入不一样。根本原因还是没有学懂迭代。
    private Deque<Character> isPalindrome(Deque<Character> wordDeque){
        if (wordDeque.size()<2){return wordDeque;}
        if (wordDeque.removeFirst() != wordDeque.removeLast()){
            //此时不是回文，我使用null标记
            Deque<Character> theNull=new ArrayDeque<>();
            theNull.addFirst(null);
            return theNull;
        }
        return isPalindrome(wordDeque);
    }*/
    public boolean isPalindrome(String word){
        if (word == null) { return false; }
        return isPalindrome(wordToDeque(word));
    }
    private boolean isPalindrome(Deque wordDeque){
        if (wordDeque.size() < 2) {
            return true;
        }
        else if(wordDeque.removeFirst()!=wordDeque.removeLast()){
            return false;
        }
        else{return isPalindrome(wordDeque)}
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null) { return false; }
        //这里的cc不是让我们输入的参数，而是外面提前new的一个CharacterComparator实例
        return isPalindrome(wordToDeque(word),cc);
    }
    private boolean isPalindrome(Deque wordDeque,CharacterComparator cc){
        if (wordDeque.size() < 2) {
            return true;
        }
        else if(!cc.equalChars(wordDeque.removeFirst(),wordDeque.removeLast())){
            return false;
        }
        else{return isPalindrome(wordDeque,cc);}
    }

    /*public static void main(String[] args){
        Palindrome palindrome = new Palindrome();
        //String test = "abcdefghijklmnopq";
        //Deque<Character> temp = palindrome.wordToDeque(test);
        Boolean non=palindrome.isPalindrome("non");
        Boolean appa=palindrome.isPalindrome("appa");
        Boolean pp=palindrome.isPalindrome("pp");
        Boolean cat=palindrome.isPalindrome("cat");
        Boolean a=palindrome.isPalindrome("a");
        Boolean theNull=palindrome.isPalindrome("");
        Boolean Null=palindrome.isPalindrome(null);
    }*/
}
