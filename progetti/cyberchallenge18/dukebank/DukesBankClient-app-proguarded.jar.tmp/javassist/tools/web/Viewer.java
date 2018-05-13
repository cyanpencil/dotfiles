// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Viewer.java

package javassist.tools.web;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

public class Viewer extends ClassLoader
{

            public static void main(String args[])
                throws Throwable
            {
/*  59*/        if(args.length >= 3)
                {
/*  60*/            Viewer viewer = new Viewer(args[0], Integer.parseInt(args[1]));
/*  61*/            String args1[] = new String[args.length - 3];
/*  62*/            System.arraycopy(args, 3, args1, 0, args.length - 3);
/*  63*/            viewer.run(args[2], args1);
/*  64*/            return;
                } else
                {
/*  66*/            System.err.println("Usage: java javassist.tools.web.Viewer <host> <port> class [args ...]");
/*  68*/            return;
                }
            }

            public Viewer(String s, int i)
            {
/*  77*/        server = s;
/*  78*/        port = i;
            }

            public String getServer()
            {
/*  84*/        return server;
            }

            public int getPort()
            {
/*  89*/        return port;
            }

            public void run(String s, String as[])
                throws Throwable
            {
/* 100*/        s = loadClass(s);
/* 102*/        s.getDeclaredMethod("main", new Class[] {
/* 102*/            [Ljava/lang/String;
                }).invoke(null, new Object[] {
/* 102*/            as
                });
/* 107*/        return;
/* 105*/        JVM INSTR dup ;
/* 106*/        s;
/* 106*/        getTargetException();
/* 106*/        throw ;
            }

            protected synchronized Class loadClass(String s, boolean flag)
                throws ClassNotFoundException
            {
                Class class1;
/* 116*/        if((class1 = findLoadedClass(s)) == null)
/* 118*/            class1 = findClass(s);
/* 120*/        if(class1 == null)
/* 121*/            throw new ClassNotFoundException(s);
/* 123*/        if(flag)
/* 124*/            resolveClass(class1);
/* 126*/        return class1;
            }

            protected Class findClass(String s)
                throws ClassNotFoundException
            {
/* 140*/        Class class1 = null;
/* 141*/        if(s.startsWith("java.") || s.startsWith("javax.") || s.equals("javassist.tools.web.Viewer"))
/* 143*/            class1 = findSystemClass(s);
                byte abyte0[];
/* 145*/        if(class1 == null)
/* 147*/            try
                    {
/* 147*/                if((abyte0 = fetchClass(s)) != null)
/* 149*/                    class1 = defineClass(s, abyte0, 0, abyte0.length);
                    }
/* 151*/            catch(Exception _ex) { }
/* 154*/        return class1;
            }

            protected byte[] fetchClass(String s)
                throws Exception
            {
                Object obj;
/* 164*/        ((URLConnection) (obj = ((URL) (obj = new URL("http", server, port, (new StringBuilder("/")).append(s.replace('.', '/')).append(".class").toString()))).openConnection())).connect();
/* 168*/        int i = ((URLConnection) (obj)).getContentLength();
/* 169*/        InputStream inputstream = ((URLConnection) (obj)).getInputStream();
                byte abyte0[];
/* 170*/        if(i <= 0)
                {
/* 171*/            abyte0 = readStream(inputstream);
                } else
                {
/* 173*/            abyte0 = new byte[i];
/* 174*/            int j = 0;
                    int k;
/* 176*/            do
/* 176*/                if((k = inputstream.read(abyte0, j, i - j)) < 0)
                        {
/* 178*/                    inputstream.close();
/* 179*/                    throw new IOException((new StringBuilder("the stream was closed: ")).append(s).toString());
                        }
/* 183*/            while((j += k) < i);
                }
/* 186*/        inputstream.close();
/* 187*/        return abyte0;
            }

            private byte[] readStream(InputStream inputstream)
                throws IOException
            {
/* 191*/        byte abyte0[] = new byte[4096];
/* 192*/        int i = 0;
/* 193*/        int j = 0;
/* 195*/        do
                {
/* 195*/            i += j;
/* 196*/            if(abyte0.length - i <= 0)
                    {
/* 197*/                j = new byte[abyte0.length << 1];
/* 198*/                System.arraycopy(abyte0, 0, j, 0, i);
/* 199*/                abyte0 = j;
                    }
                } while((j = inputstream.read(abyte0, i, abyte0.length - i)) >= 0);
/* 205*/        byte abyte1[] = new byte[i];
/* 206*/        System.arraycopy(abyte0, 0, abyte1, 0, i);
/* 207*/        return abyte1;
            }

            private String server;
            private int port;
}
