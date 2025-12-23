import java.util.*;

class Solution {
    public String frequencySort(String s) {

        int[] freq = new int[256];
        for (char c : s.toCharArray()) freq[c]++;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 256; i++)
            if (freq[i] > 0) list.add(new int[]{freq[i], i});

        list.sort((a, b) -> b[0] - a[0]); // descending

        StringBuilder sb = new StringBuilder();
        for (int[] p : list)
            sb.append(String.valueOf((char) p[1]).repeat(p[0]));

        return sb.toString();
    }
}
