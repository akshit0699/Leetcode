package Leetcode2023.DynamicProgramming;

class ChangeMakingRecursive {
    public int coinChange(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }

    return coinChangeHelper(coins, amount, new int[amount + 1]);
    }

    private int coinChangeHelper(int[] coins, int amountToMake, int[] amountsList) {
    
    // Just returning -1 as an indicatiy that this is an impossible case, move ahead
    if (amountToMake < 0) {
      return -1;
    }
  
    
    // I know  base case where if I want to make an amout of 0, I need 0 coins
    if (amountToMake == 0) {
      return 0;
    }

    // We already have an answer cached. Return it, stop creation of a redudant recurrence flow
    if (amountsList[amountToMake] != 0) {
      return amountsList[amountToMake];
    }

    /*
      No answer yet. Try each coin as the last coin in the change that
      we make for the amount
    */
    int coinsNeeded = Integer.MAX_VALUE;
    for (int currentCoinValue: coins) {

      // Hey coin change helper, please tell me how many coins will be needed at minimum
      // to form an amount of (amountToMake-currentCoinValue) 
      // --> Can be 0/-1/some pre-cached answer/or can come back to this flow to go deeper in the recurrence. 
      int coinsNeededForRemainingAmount = coinChangeHelper(coins, amountToMake - currentCoinValue, amountsList);

      /*
        If making change was possible (coinsNeededForRemainingAmount >= 0) and 
        the coinsNeededForRemainingAmount beats our present minimum, add one to
        that smallest value
        
        We accept that coin as the last coin in our change making
        sequence up to this point since it minimizes the coins we
        need
      */
      if (coinsNeededForRemainingAmount >= 0 && coinsNeededForRemainingAmount < coinsNeeded) {
        coinsNeeded = 1 + coinsNeededForRemainingAmount;
      }
    }

    /*
      If no answer is found (minimum == Integer.MAX_VALUE) then the
      sub problem answer is just arbitrarily made to be -1, otherwise
      the sub problem's answer is "minimum"
    */
    amountsList[amountToMake] = (coinsNeeded == Integer.MAX_VALUE) ? -1 : coinsNeeded;

    return amountsList[amountToMake];
  }
}
