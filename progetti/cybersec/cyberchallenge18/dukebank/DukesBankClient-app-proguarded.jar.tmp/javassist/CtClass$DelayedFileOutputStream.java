// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtClass.java

package javassist;

import java.io.*;

// Referenced classes of package javassist:
//            CtClass

static class filename extends OutputStream
{

            private void init()
                throws IOException
            {
/*1492*/        if(file == null)
/*1493*/            file = new FileOutputStream(filename);
            }

            public void write(int i)
                throws IOException
            {
/*1497*/        init();
/*1498*/        file.write(i);
            }

            public void write(byte abyte0[])
                throws IOException
            {
/*1502*/        init();
/*1503*/        file.write(abyte0);
            }

            public void write(byte abyte0[], int i, int j)
                throws IOException
            {
/*1507*/        init();
/*1508*/        file.write(abyte0, i, j);
            }

            public void flush()
                throws IOException
            {
/*1513*/        init();
/*1514*/        file.flush();
            }

            public void close()
                throws IOException
            {
/*1518*/        init();
/*1519*/        file.close();
            }

            private FileOutputStream file;
            private String filename;

            (String s)
            {
/*1487*/        file = null;
/*1488*/        filename = s;
            }
}
