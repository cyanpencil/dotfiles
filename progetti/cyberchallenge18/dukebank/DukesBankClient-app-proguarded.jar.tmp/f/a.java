// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LicenseUtil.java

package f;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class a
{

            public a()
            {
            }

            public static boolean a(String s, String s1, String s2, String s3, String s4, String s5)
            {
/*  19*/        s4 = s4;
/*  19*/        s3 = s3;
/*  19*/        s2 = s2;
/*  19*/        s1 = s1;
/*  19*/        s = s;
/*1024*/        String s6 = "Duk3M31fY0uC4n";
/*1026*/        s1 = a(s = (new StringBuilder()).append(s).append(s1).append(s2).append(s3).append(s4).toString(), "MD2", 1337, s6);
/*1029*/        s2 = a(s, "MD5", 13337, s6);
/*1030*/        s3 = a(s, "SHA", 137, s6);
/*1031*/        s4 = a(s, "SHA-224", 0x208d9, s6);
/*1032*/        String s7 = a(s, "SHA-256", 1337, s6);
/*1033*/        String s8 = a(s, "SHA-384", 17, s6);
/*1034*/        s = a(s, "SHA-512", 1337, s6);
/*1035*/        s = (new StringBuilder()).append(s1).append(s2).append(s3).append(s4).append(s7).append(s8).append(s).toString();
/*1037*/        s = s = (new StringBuilder()).append(s.charAt(313)).append(s.charAt(334)).append(s.charAt(277)).append(s.charAt(430)).append(s.charAt(4)).append("-").append(s.charAt(107)).append(s.charAt(442)).append(s.charAt(262)).append(s.charAt(407)).append(s.charAt(6)).append("-").append(s.charAt(402)).append(s.charAt(356)).append(s.charAt(290)).append(s.charAt(296)).append(s.charAt(416)).append("-").append(s.charAt(22)).append(s.charAt(379)).append(s.charAt(7)).append(s.charAt(71)).append(s.charAt(148)).append("-").append(s.charAt(383)).append(s.charAt(311)).append(s.charAt(195)).append(s.charAt(83)).append(s.charAt(72)).append("-").append(s.charAt(225)).append(s.charAt(336)).append(s.charAt(171)).append(s.charAt(104)).append(s.charAt(350)).toString();
		System.out.println(s);;
/*  20*/        return s5.equals(s);
            }

            private static String a(String s, String s1, int i, String s2)
            {
/*  78*/        return a((new StringBuilder()).append(s).append(s2).toString(), s1, i);
            }

            private static String a(String s, String s1, int i)
            {
/*  82*/        s = s;
/*  83*/        for(int j = 0; j < i; j++)
/*  84*/            s = a(s, s1);

/*  86*/        return s;
            }

            private static String a(String s, String s1)
            {
                String s2;
/*  91*/        s2 = "";
		try {
  //92        s = s.getBytes();
		byte [] bites = s.getBytes();
		MessageDigest bates = MessageDigest.getInstance(s1);
/*  93*/        bates.update(bites);
/*  95*/        bites = bates.digest();
		int k = 0;
/*  96*/        for(k = 0; k < bites.length; k++)
                {
                    int i;
/*  97*/            if(Integer.toHexString(i = bites[k] & 0xff).length() == 1)
/*  99*/                s2 = (new StringBuilder()).append(s2).append("0").toString();
/* 101*/            s2 = (new StringBuilder()).append(s2).append(Integer.toHexString(i)).toString();
                }

		} catch (Exception e) {}
/* 103*/        return s2;
                //NoSuchAlgorithmException nosuchalgorithmexception;
//[> 104<]        nosuchalgorithmexception;
//[> 105<]        Logger.getLogger(f/a.getName()).log(Level.SEVERE, null, nosuchalgorithmexception);
 //107        return null;
            }
}
