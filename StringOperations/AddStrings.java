package Leetcode2023.StringOperations;

class AddStrings {
    StringBuilder resultantSumDigit = new StringBuilder();

    public String addStrings(String num1, String num2) {
        // Mental map -> We convert a string
        // "Eleven" to 11 basis the fact that we go through the string and we know how to
        // map it to its corresponding numeric combinations.

        // Can have a mapping: "1" -> 1 (string to integer)
        // Can do type casting for each of the charcters
        // Can do ascii stuff for each of the characters
        // Integer to parstInt works.. but we need some character manipulation

        // Starting index of the numbers
        int idx1 = num1.length()-1;
        int idx2 = num2.length()-1;

        int carry = 0;

        while(idx1 >=0 && idx2>=0) {
            // Get the digits
            int firstDigit = num1.charAt(idx1) - '0';
            int secondDigit = num2.charAt(idx2) - '0';

            // Find the sum, resultantSum digit and carry
            int digitsSum = firstDigit + secondDigit + carry;
            buildResultantSum(digitsSum);
            carry = digitsSum/10;

            // Moving to the left by 1 step
            idx1--;
            idx2--;
        }

        // Eating the left over numbers.
        while(idx1>=0) {
            int firstDigit = num1.charAt(idx1) - '0';
            int digitsSum = firstDigit + carry;
            buildResultantSum(digitsSum);
            carry = digitsSum/10;
            idx1--;
        }

        while(idx2>=0) {
            int secondDigit = num2.charAt(idx2) - '0';
            int digitsSum = secondDigit + carry;
            buildResultantSum(digitsSum);
            carry = digitsSum/10;
            idx2--;    
        }

        if(carry!=0) {
            resultantSumDigit.append(carry);
        }

        return resultantSumDigit.reverse().toString();

    }

    private void buildResultantSum(int digitsSum) {
        resultantSumDigit.append(String.valueOf(digitsSum%10));
    }


    // THIS DOES NOT WORK BECAUSE THE NUMBERS CAN BE VERY LONG
    // AND WE WOULD HAVE OVERFLOWS.

    // WE NEED TO TAKE A STEP BACK AND DO IT LIKE GRADE SCHOOL MATHS
    // 456 + 984 = 1440

    //  456 <-i
    //21984 <-j

    // We start with the right most digits (keep a pointer at both) 
    // Do a basic integer sum -> 6+4 = 10. Now one comes at the result position
    // and one comes as the carry.
    // Next is 5+8+carry = 5+8+1=14. Now 1 goes carry and 4 comes in string
    // How do I evaluate carry and what goes in string??
    
    // Simple actually: 14%10 = 4 (Stringiy)
    // 14/10 = 1 (Carry)

    // Sounds good once you have 



    // private long getIntegerVersionOfString(String s) {
    //         long integerVersionNum = 0;
    //         long multiplier = 1;
    //         for(int i = s.length()-1; i>=0; i--) {
    //         long characterToNumber = s.charAt(i) - '0';
    //         long faceValueOfNumber = characterToNumber*multiplier;
    //         integerVersionNum += faceValueOfNumber;
    //         multiplier*= 10;
    //     }

    //     return integerVersionNum;
    // }
}
