class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Step 1: Find the maximum cost to size our frequency array
        int maxCost = 0;
        for (int cost : costs) {
            if (cost > maxCost) {
                maxCost = cost;
            }
        }
        
        // Step 2: Create a frequency array for counting sort
        int[] freq = new int[maxCost + 1];
        for (int cost : costs) {
            freq[cost]++;
        }
        
        int iceCreamCount = 0;
        
        // Step 3: Greedily buy ice cream bars from cheapest to most expensive
        for (int price = 1; price <= maxCost; price++) {
            if (freq[price] == 0) {
                continue;
            }
            
            // If we can't even afford one bar at this price, we're done
            if (coins < price) {
                break;
            }
            
            // Calculate how many bars at this price we want vs. how many we can afford
            int countToBuy = Math.min(freq[price], coins / price);
            
            // Update our total count and deduct the coins spent
            iceCreamCount += countToBuy;
            coins -= countToBuy * price;
        }
        
        return iceCreamCount;
    }
}