import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert word into trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search for a word in trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    // Get all words in the trie
    public List<String> getAllWords() {
        List<String> result = new ArrayList<>();
        collectWords(root, "", result);
        return result;
    }

    // Get all words with a given prefix
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null)
                return result;  // empty list if prefix doesn't exist
            node = node.children[index];
        }

        collectWords(node, prefix, result);
        return result;
    }

    // Helper method to collect all words from a given node
    private void collectWords(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord)
            result.add(prefix);

        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = ch - 'a';
            if (node.children[index] != null)
                collectWords(node.children[index], prefix + ch, result);
        }
    }
}

public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("april");
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("banana");

        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'batman': " + trie.search("batman")); // false

        System.out.println("\nAll words in Trie:");
        for (String word : trie.getAllWords()) {
            System.out.println(word);
        }

        System.out.println("\nWords with prefix 'ap':");
        for (String word : trie.getWordsWithPrefix("ap")) {
            System.out.println(word);
        }

        System.out.println("\nWords with prefix 'ba':");
        for (String word : trie.getWordsWithPrefix("ba")) {
            System.out.println(word);
        }
    }
}
