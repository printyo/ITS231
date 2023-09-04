package Lab1;

public class Recursion {

    static int subsum(int n) {
        // Ex1. Complete the content of this method
        if (n == 0) {
            return 0;
        } else {
            double x = Math.pow((-1), (n + 1)) * n;
            return (int) x + subsum(n - 1);
        }

    }

    static int sumDigit(int n) {
    //Ex2. Complete the content of this method
        if (n == 0) {
            return 0;
        } else {
            int x = n % 10;
            int y = n/10;
            return x + sumDigit(y);
        }
    }

    public static void main(String[] args) {

        // Test Uncomment these lines below to test your subsum code
        /// *
        System.out.println("Calculating subsum(10):");
        System.out.println("Your answer is " + subsum(10));
        System.out.println("The correct answer is -5");
        System.out.println("-----------------------");
        // */

        // Uncomment these lines below to test your sumDigit code
        ///*
          System.out.println("sumDigit(123456789)");
          System.out.println("Your answer is "+ sumDigit(123456789));
          System.out.println("The correct answer is 45");
         //*/
    }

}