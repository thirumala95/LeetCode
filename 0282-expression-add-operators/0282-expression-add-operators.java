import java.util.*;

class Solution {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();
        backtrack(result, num, target, 0, 0, 0, "");
        return result;
    }

    void backtrack(List<String> result, String num, int target,
                   int index, long value, long prev, String expr) {

        if(index == num.length()){
            if(value == target){
                result.add(expr);
            }
            return;
        }

        for(int i=index; i<num.length(); i++){

            if(i!=index && num.charAt(index)=='0') break;

            long cur = Long.parseLong(num.substring(index,i+1));

            if(index==0){
                backtrack(result,num,target,i+1,cur,cur,expr+cur);
            }
            else{

                backtrack(result,num,target,i+1,value+cur,cur,expr+"+"+cur);

                backtrack(result,num,target,i+1,value-cur,-cur,expr+"-"+cur);

                backtrack(result,num,target,i+1,value-prev+prev*cur,prev*cur,expr+"*"+cur);
            }
        }
    }
}