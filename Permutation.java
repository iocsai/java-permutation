package permutation;

import java.math.BigInteger;
import java.util.Scanner;

public class Permutation {

    private int n;
    private int k;
    private BigInteger p;
    
    public static void main(String[] args) {
        Permutation per;
        while (true) {
            try {
                per = new Permutation(
                        input("Enter one positive whole number for N: "), 
                        input("Enter another positive whole number for K: "));
                break;
            } catch (ValueError ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.out.println(per.toString());
    }

    public Permutation(int n, int k) throws ValueError {
        if (n <= k) {
            throw new ValueError("In permutation value of N should be greater than K!");
        }
        this.n = n;
        this.k = k;
        this.p = permutate();
    }
    
    public final BigInteger permutate() {
        BigInteger nFact = new BigInteger("1");
        BigInteger n_kFact = new BigInteger("1");
        for (int i = 1; i <= this.n; i++) {
            nFact = nFact.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= (this.n - this.k); i++) {
            n_kFact = n_kFact.multiply(BigInteger.valueOf(i));
        }
        return nFact.divide(n_kFact);
    }
    
    private static int input(String message) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        while (number < 1) {
            System.out.print(message);
            try {
                String nextLine = sc.nextLine();
                number = Integer.parseInt(nextLine);
            } catch(NumberFormatException e) {
                System.err.println("You have to enter a positive whole number!");
            }
        }
        return number;
    }
 
    @Override
    public String toString() {
        return String.format("Permutation of %d and %d equals %d.", this.n, this.k, this.p);
    }

    private static class ValueError extends Exception {

        public ValueError(String message) {
            super(message);
        }
    }
}