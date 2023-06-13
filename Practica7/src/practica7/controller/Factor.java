package practica7.controller;

import java.math.BigInteger;

/**
 *
 * @author usuario
 */
public class Factor {
    public Factor() {
        
    }
    
    public void factorizar(BigInteger num) {
        BigInteger veces;
        long temps = System.nanoTime();
        Primality primprob = new Primality(100);
        BigInteger zero = new BigInteger("0");
        BigInteger uno = new BigInteger("1");
        BigInteger dos = new BigInteger("2");
        BigInteger obj = num.subtract(uno);
        BigInteger test = new BigInteger("3");
        System.out.println("Voy a factorizar " + num);
        if (num.remainder(dos).compareTo(zero) == 0) {
            veces = zero.add(zero);
            while (num.remainder(dos).compareTo(zero) == 0) {
                num = num.divide(dos);
                veces = veces.add(uno);
            }
            System.out.println("   factor -------> " + dos + "  (x" + veces + ")");
        }
        obj = sqrt(obj);
        int control = 0;
        while (test.compareTo(obj) <= 0) {
            if (primprob.esPrimo(num)) {
                System.out.println("   factor -------> " + num + " (x1)");
                test = obj.add(uno);
            } else if (primprob.esPrimo(test)) {
                if (num.remainder(test).compareTo(zero) == 0) {
                    veces = zero.add(zero);
                    while (num.remainder(test).compareTo(zero) == 0) {
                        num = num.divide(test);
                        veces = veces.add(uno);
                    }
                    System.out.println("   factor -------> " + test + "  (x" + veces + ")");
                    if (num.compareTo(uno) == 0) {
                        test = obj.add(uno);
                    }
                }
            }
            test = test.add(dos);
            if (control++ == 10000) {
                System.out.println(test);
                control = 0;
            }
        }
        System.out.println("He tardado " + (System.nanoTime() - temps) + "nanosec\n");
    }

    private BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for (;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) {
                return y;
            }
            div2 = div;
            div = y;
        }
    }
}
