package Leetcode2023.DynamicProgramming;

/**
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 * 
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =
 * 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you
 * must buy before you sell.
 * 
 * 
 * Thinking about it in the brute force way.
 * I go one by one throughout the array length O(n-1) - considering the current idx to be the purchase day.
 * 
 * I keep a second pointer... where I keep moving to the right and seeing what is the profit i am making
 * by doing sellPrice - costPrice.
 * 
 * Why Greedy won't work?
 * What extra work I am doing that can be cached -> calculating the largestInAnArray
 * 
 * From every iteration I will get a maximum - which i will keep comparing against a global maximum
 * 
 * Input: [12, 4, 6, 9, 3, 5, 4]
 *        [9,  9, 9, 5, 5, 4, 0]
 *        [-3, 5, 3, 4, 2, -1, -4]
 * 
 *        [0, 0, 2, ] 
 * Re-word the problem.
 * I take one candidate and from the array in front of me I need the largest number [that's where I can sell]
 * 
 * or I take one candidate and from the array behind me I need the smallest number [that's where i can buy]
 * 
 * Mark negatives as 0
 * Global Problem: What's the best profit i can make by selling on day i and buying on day j (where j<=i)
 * 
 * // Base case:
 * profit(prices, i) = 0 when i=0 (i.e. if i want to sell on the same day I get NADA)
 * 
 * // Recursive case:
 * Extend by a day and yield a profit delta on top of the profit that I could have gotten on the day before this
 * profit(prices, i) = max(0, (profit on prev day i.e. i-1 day) + (prices[i]-prices[i-1])) when i>0;
 * 
 * What's my subproblem and how can i relate the sub problems? What are my base cases.
 * 
 * Well my subproblem would be to know what is the best profit I can get by selling on day i.
 * 
 * My decision space at every point would be: Should I extend my selling day by one day or not.
 * 
 * 
 */
public class BuyAndSellStock {
    public int maxProfit(int[] prices) {
        // Days I have to buy/sell
        int days = prices.length;
        // I have no days to sell/buy or I will need to sell on the same day when I buy.
        if(days==0 || days==1) return 0;

        // This will be the result
        int maximumProfit = 0;
        // At all times I want to know what was the best opportunity to buy the stock
        // in my past, initializing it to starting price of stock.
        int leastCostPrice = prices[0];

        // I will consider every day as the day when I can sell
        for(int sellingDay=1; sellingDay<days; sellingDay++) {

            // The price at which i can sell today
            int sellingPrice = prices[sellingDay];
            
            // Case where we choose sellingPrice here:
            // If i would have bought it yesterday i would have to buy it for 4.
            // but today its value is just 1, so I will buy and sell today only
            // even if I make a profit of 0, its better than going in loss.

            // Case where we choose leastCostPrice here:
            // If i would have bought it yesterday, it was just for 2.
            // today its value is 6, no way i am buying and selling the same day
            // I had a golden opportunity to buy it yesterday!
            leastCostPrice =  Math.min(sellingPrice, leastCostPrice);

            // Did I hit the maximum answer up-untill now?
            maximumProfit =  Math.max(maximumProfit, sellingPrice - leastCostPrice);
        }

        return maximumProfit;
    }

}
