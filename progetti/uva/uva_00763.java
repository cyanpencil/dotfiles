import java.io.*;
import java.math.*;
import java.util.Scanner;

class Main {

    public static BigInteger fib[];

    public static String convertToBin(BigInteger a) {
        String res = "";
        for (int i = 119; i > 0; i--) {
            if (a.compareTo(fib[i]) >= 0) {
                res += "1";
                a = a.subtract(fib[i]);
            }
            else if (res.length() > 0) {
                res += "0";
            }
        }
        if (res.equals("")) return "0";
        return res;
    }

    public static BigInteger convertToDec(StringBuffer a) {
        BigInteger res = BigInteger.valueOf(0);
        if (a.equals("0")) return res;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(a.length() - (i + 1)) == '1') {
                res = res.add(fib[i + 1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("ciao");
        fib = new BigInteger[120];
        fib[0] = BigInteger.valueOf(1);
        fib[1] = BigInteger.valueOf(1);
        fib[2] = BigInteger.valueOf(2);
        for (int i = 3; i < 120; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }

        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        boolean notfirst = false;
        while (sc.hasNext()) {
            StringBuffer a = new StringBuffer(sc.next());
            StringBuffer b = new StringBuffer(sc.next());

            if (notfirst) System.out.print("\n");
            notfirst = true;
            //BigInteger uno = convertToDec(a);
            //BigInteger due = convertToDec(b);
            //BigInteger c = convertToDec(a).add(convertToDec(b));
            //System.out.println(uno + " - " + due + " - " + c);

            System.out.println(convertToBin(convertToDec(a).add(convertToDec(b))));
        }
        sc.close();
    }
}
