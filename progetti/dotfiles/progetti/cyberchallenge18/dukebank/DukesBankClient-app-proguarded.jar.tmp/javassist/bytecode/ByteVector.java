// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Bytecode.java

package javassist.bytecode;


class ByteVector
    implements Cloneable
{

            public ByteVector()
            {
/*  27*/        buffer = new byte[64];
/*  28*/        size = 0;
            }

            public Object clone()
                throws CloneNotSupportedException
            {
                ByteVector bytevector;
/*  32*/        (bytevector = (ByteVector)super.clone()).buffer = (byte[])buffer.clone();
/*  34*/        return bytevector;
            }

            public final int getSize()
            {
/*  37*/        return size;
            }

            public final byte[] copy()
            {
/*  40*/        byte abyte0[] = new byte[size];
/*  41*/        System.arraycopy(buffer, 0, abyte0, 0, size);
/*  42*/        return abyte0;
            }

            public int read(int i)
            {
/*  46*/        if(i < 0 || size <= i)
/*  47*/            throw new ArrayIndexOutOfBoundsException(i);
/*  49*/        else
/*  49*/            return buffer[i];
            }

            public void write(int i, int j)
            {
/*  53*/        if(i < 0 || size <= i)
                {
/*  54*/            throw new ArrayIndexOutOfBoundsException(i);
                } else
                {
/*  56*/            buffer[i] = (byte)j;
/*  57*/            return;
                }
            }

            public void add(int i)
            {
/*  60*/        addGap(1);
/*  61*/        buffer[size - 1] = (byte)i;
            }

            public void add(int i, int j)
            {
/*  65*/        addGap(2);
/*  66*/        buffer[size - 2] = (byte)i;
/*  67*/        buffer[size - 1] = (byte)j;
            }

            public void add(int i, int j, int k, int l)
            {
/*  71*/        addGap(4);
/*  72*/        buffer[size - 4] = (byte)i;
/*  73*/        buffer[size - 3] = (byte)j;
/*  74*/        buffer[size - 2] = (byte)k;
/*  75*/        buffer[size - 1] = (byte)l;
            }

            public void addGap(int i)
            {
/*  79*/        if(size + i > buffer.length)
                {
                    int j;
/*  80*/            if((j = size << 1) < size + i)
/*  82*/                j = size + i;
/*  84*/            byte abyte0[] = new byte[j];
/*  85*/            System.arraycopy(buffer, 0, abyte0, 0, size);
/*  86*/            buffer = abyte0;
                }
/*  89*/        size += i;
            }

            private byte buffer[];
            private int size;
}
