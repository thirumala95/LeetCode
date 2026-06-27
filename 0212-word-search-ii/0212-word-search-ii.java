import java.util.ArrayList;
import java.util.List;

class Solution {
    // Trie Node structure
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Stores the complete word at the leaf node
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        // Step 1: Build the Trie
        TrieNode root = buildTrie(words);
        
        // Step 2: Backtrack from every cell on the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        char ch = board[r][c];
        
        // Base case: If the character is visited ('#') or doesn't exist in the Trie branch
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }

        node = node.children[ch - 'a'];
        
        // If we found a valid word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Crucial Optimization: Avoid adding duplicate words
        }

        // Mark the current cell as visited
        board[r][c] = '#';

        // Explore all 4 adjacent directions
        if (r > 0) dfs(board, r - 1, c, node, result);
        if (r < board.length - 1) dfs(board, r + 1, c, node, result);
        if (c > 0) dfs(board, r, c - 1, node, result);
        if (c < board[0].length - 1) dfs(board, r, c + 1, node, result);

        // Backtrack: Restore the original character
        board[r][c] = ch;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.word = word; // Store the word at the end node
        }
        return root;
    }
}