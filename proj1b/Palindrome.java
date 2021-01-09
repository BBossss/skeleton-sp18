public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    private  boolean isPalindromeHelper(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else if (d.removeFirst() != d.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(d);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }

    private  boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(d, cc);
        }
    }
}
