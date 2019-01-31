// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomX509TrustManager.java

package c;

import java.io.PrintStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

public final class c
    implements X509TrustManager
{

            public c()
            {
            }

            public final void checkClientTrusted(X509Certificate ax509certificate[], String s)
                throws CertificateException
            {
            }

            public final void checkServerTrusted(X509Certificate ax509certificate[], String s)
                throws CertificateException
            {
                X509Certificate x509certificate;
/*  29*/        x509certificate = ax509certificate[0];
/*  29*/        s = "B2F9C16EA2C2FB47FEA843837A08BAE04A036C3DC4A34E0BC90F0A9EA48F1214B09F0CFFA35292A879834F3D1A438543AFDE96F65E5AAFBC24C83B6AEA9C89C3";
/*  29*/        ax509certificate = this;
/*1039*/        MessageDigest messagedigest = MessageDigest.getInstance("SHA-512");
/*1041*/        byte abyte0[] = x509certificate.getPublicKey().getEncoded();
/*1042*/        abyte0 = messagedigest.digest(abyte0);
/*1044*/        s = DatatypeConverter.parseHexBinary(s);
/*1046*/        if(!Arrays.equals(abyte0, s))
                {
/*1047*/            System.out.println("Certificate Pinning mismatch.");
/*1048*/            ax509certificate.getClass();
/*1048*/            System.out.println((new StringBuilder("Pinned Digest [Hex]: ")).append("B2F9C16EA2C2FB47FEA843837A08BAE04A036C3DC4A34E0BC90F0A9EA48F1214B09F0CFFA35292A879834F3D1A438543AFDE96F65E5AAFBC24C83B6AEA9C89C3").toString());
/*1049*/            System.out.println((new StringBuilder("Server Digest [Hex]: ")).append(DatatypeConverter.printHexBinary(abyte0)).toString());
/*1050*/            throw new CertificateException("Pin doesn't match.");
                } else
                {
/*1056*/            return;
                }
/*1052*/        JVM INSTR pop ;
/*1053*/        System.out.println("NoSuchAlgorithm.");
/*1056*/        return;
/*1054*/        JVM INSTR dup ;
                CertificateException certificateexception;
/*1055*/        certificateexception;
/*1055*/        throw ;
            }

            public final X509Certificate[] getAcceptedIssuers()
            {
/*  34*/        return null;
            }
}
