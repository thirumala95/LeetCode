import java.util.*;

class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> ans = new ArrayList<>();
        solve(0, s, new ArrayList<>(), ans);
        return ans;
    }

    void solve(int start, String s, List<String> temp, List<List<String>> ans){

        if(start == s.length()){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i=start;i<s.length();i++){

            if(isPal(s,start,i)){

                temp.add(s.substring(start,i+1));
                solve(i+1,s,temp,ans);
                temp.remove(temp.size()-1);
            }
        }
    }

    boolean isPal(String s,int l,int r){

        while(l<r){
            if(s.charAt(l)!=s.charAt(r))
                return false;
            l++; r--;
        }
        return true;
    }
}