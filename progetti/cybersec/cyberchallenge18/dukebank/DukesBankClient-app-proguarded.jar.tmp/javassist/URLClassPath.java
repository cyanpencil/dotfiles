// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   URLClassPath.java

package javassist;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

// Referenced classes of package javassist:
//            ClassPath, ClassPoolTail

public class URLClassPath
    implements ClassPath
{

            public URLClassPath(String s, int i, String s1, String s2)
            {
/*  62*/        hostname = s;
/*  63*/        port = i;
/*  64*/        directory = s1;
/*  65*/        packageName = s2;
            }

            public String toString()
            {
/*  69*/        return (new StringBuilder()).append(hostname).append(":").append(port).append(directory).toString();
            }

            public InputStream openClassfile(String s)
            {
/*  79*/        if((s = openClassfile0(s)) != null)
/*  81*/            return s.getInputStream();
/*  83*/        break MISSING_BLOCK_LABEL_19;
/*  83*/        JVM INSTR pop ;
/*  84*/        return null;
            }

            private URLConnection openClassfile0(String s)
                throws IOException
            {
/*  88*/        if(packageName == null || s.startsWith(packageName))
                {
/*  89*/            s = (new StringBuilder()).append(directory).append(s.replace('.', '/')).append(".class").toString();
/*  91*/            return fetchClass0(hostname, port, s);
                } else
                {
/*  94*/            return null;
                }
            }

            public URL find(String s)
            {
                InputStream inputstream;
/* 104*/        if((inputstream = (s = openClassfile0(s)).getInputStream()) == null)
/* 107*/            break MISSING_BLOCK_LABEL_28;
/* 107*/        inputstream.close();
/* 108*/        return s.getURL();
/* 111*/        JVM INSTR pop ;
/* 112*/        return null;
            }

            public void close()
            {
            }

            public static byte[] fetchClass(String s, int i, String s1, String s2)
                throws IOException
            {
/* 135*/        i = (s = fetchClass0(s, i, (new StringBuilder()).append(s1).append(s2.replace('.', '/')).append(".class").toString())).getContentLength();
/* 138*/        s1 = s.getInputStream();
/* 140*/        if(i <= 0)
                {
/* 141*/            s = ClassPoolTail.readStream(s1);
                } else
                {
/* 143*/            s = new byte[i];
/* 144*/            int j = 0;
                    int k;
/* 146*/            do
/* 146*/                if((k = s1.read(s, j, i - j)) < 0)
/* 148*/                    throw new IOException((new StringBuilder("the stream was closed: ")).append(s2).toString());
/* 152*/            while((j += k) < i);
                }
/* 156*/        s1.close();
/* 157*/        break MISSING_BLOCK_LABEL_132;
/* 156*/        s;
/* 156*/        s1.close();
/* 156*/        throw s;
/* 159*/        return s;
            }

            private static URLConnection fetchClass0(String s, int i, String s1)
                throws IOException
            {
/* 168*/        try
                {
/* 168*/            s = new URL("http", s, i, s1);
                }
/* 170*/        catch(MalformedURLException _ex)
                {
/* 172*/            throw new IOException("invalid URL?");
                }
/* 175*/        (s = s.openConnection()).connect();
/* 177*/        return s;
            }

            protected String hostname;
            protected int port;
            protected String directory;
            protected String packageName;
}
