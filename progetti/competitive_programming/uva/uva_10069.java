import java.io.*;
import java.math.*;
import java.util.Scanner;

class Main {

    public static BigInteger dp[][];
    public static StringBuffer a;
    public static StringBuffer b;
    public static int len_a;
    public static int len_b;

    public static BigInteger calc(int idx_X, int idx_Z) {
        if (dp[idx_X][idx_Z].intValue() != -1) return dp[idx_X][idx_Z];
        if (idx_Z >= len_b) { return BigInteger.valueOf(1); }
        if (idx_X >= len_a) { return BigInteger.valueOf(0); }


        BigInteger sol = BigInteger.valueOf(0);
        sol = sol.add(calc(idx_X + 1, idx_Z));
        if (a.charAt(idx_X) == b.charAt(idx_Z)) sol = sol.add(calc(idx_X + 1, idx_Z + 1));

        return dp[idx_X][idx_Z] = sol;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n -- > 0) {
            a = new StringBuffer(sc.next());
            b = new StringBuffer(sc.next());
            len_a = a.length();
            len_b = b.length();
            dp = new BigInteger [10000][100];
            for (int i = 0; i <= len_a; i++)
                for (int j = 0; j <= len_b; j++) 
                    dp[i][j] = BigInteger.valueOf(-1);

            System.out.println(calc(0,0));
        }
        sc.close();
    }
}
