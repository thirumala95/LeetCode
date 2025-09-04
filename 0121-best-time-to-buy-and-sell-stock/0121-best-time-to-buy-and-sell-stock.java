class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice=prices[0];
        int profit =0;
        int n=prices.length;
        for(int i=0;i<n;i++){
            if(buyPrice >prices[i]){
                buyPrice=prices[i];
            }
            profit =Math.max(profit,prices[i]-buyPrice);
        }
        return profit;
        
    }
}