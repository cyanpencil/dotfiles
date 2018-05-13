// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, StackMapTable

static class t> extends t>
{

            public byte[] doit()
                throws BadBytecode
            {
/* 379*/        parse();
/* 380*/        return writer.teArray();
            }

            public void sameFrame(int i, int j)
            {
/* 384*/        writer.Frame(j);
            }

            public void sameLocals(int i, int j, int k, int l)
            {
/* 388*/        writer.Locals(j, k, copyData(k, l));
            }

            public void chopFrame(int i, int j, int k)
            {
/* 392*/        writer.Frame(j, k);
            }

            public void appendFrame(int i, int j, int ai[], int ai1[])
            {
/* 396*/        writer.ndFrame(j, ai, copyData(ai, ai1));
            }

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
            {
/* 401*/        writer.Frame(j, ai, copyData(ai, ai1), ai2, copyData(ai2, ai3));
            }

            protected int copyData(int i, int j)
            {
/* 406*/        return j;
            }

            protected int[] copyData(int ai[], int ai1[])
            {
/* 410*/        return ai1;
            }

            private copyData writer;

            public (byte abyte0[])
            {
/* 374*/        super(abyte0);
/* 375*/        writer = new t>(abyte0.length);
            }
}
