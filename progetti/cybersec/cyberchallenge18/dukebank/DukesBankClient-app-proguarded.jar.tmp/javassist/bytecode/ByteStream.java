// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteStream.java

package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

final class ByteStream extends OutputStream
{

            public ByteStream()
            {
/*  26*/        this(32);
            }

            public ByteStream(int i)
            {
/*  29*/        buf = new byte[i];
/*  30*/        count = 0;
            }

            public final int getPos()
            {
/*  33*/        return count;
            }

            public final int size()
            {
/*  34*/        return count;
            }

            public final void writeBlank(int i)
            {
/*  37*/        enlarge(i);
/*  38*/        count += i;
            }

            public final void write(byte abyte0[])
            {
/*  42*/        write(abyte0, 0, abyte0.length);
            }

            public final void write(byte abyte0[], int i, int j)
            {
/*  46*/        enlarge(j);
/*  47*/        System.arraycopy(abyte0, i, buf, count, j);
/*  48*/        count += j;
            }

            public final void write(int i)
            {
/*  52*/        enlarge(1);
/*  53*/        int j = count;
/*  54*/        buf[j] = (byte)i;
/*  55*/        count = j + 1;
            }

            public final void writeShort(int i)
            {
/*  59*/        enlarge(2);
/*  60*/        int j = count;
/*  61*/        buf[j] = (byte)(i >>> 8);
/*  62*/        buf[j + 1] = (byte)i;
/*  63*/        count = j + 2;
            }

            public final void writeInt(int i)
            {
/*  67*/        enlarge(4);
/*  68*/        int j = count;
/*  69*/        buf[j] = i >> 24;
/*  70*/        buf[j + 1] = (byte)(i >>> 16);
/*  71*/        buf[j + 2] = (byte)(i >>> 8);
/*  72*/        buf[j + 3] = (byte)i;
/*  73*/        count = j + 4;
            }

            public final void writeLong(long l)
            {
/*  77*/        enlarge(8);
/*  78*/        int i = count;
/*  79*/        buf[i] = (byte)(int)(l >>> 56);
/*  80*/        buf[i + 1] = (byte)(int)(l >>> 48);
/*  81*/        buf[i + 2] = (byte)(int)(l >>> 40);
/*  82*/        buf[i + 3] = (byte)(int)(l >>> 32);
/*  83*/        buf[i + 4] = (byte)(int)(l >>> 24);
/*  84*/        buf[i + 5] = (byte)(int)(l >>> 16);
/*  85*/        buf[i + 6] = (byte)(int)(l >>> 8);
/*  86*/        buf[i + 7] = (byte)(int)l;
/*  87*/        count = i + 8;
            }

            public final void writeFloat(float f)
            {
/*  91*/        writeInt(Float.floatToIntBits(f));
            }

            public final void writeDouble(double d)
            {
/*  95*/        writeLong(Double.doubleToLongBits(d));
            }

            public final void writeUTF(String s)
            {
/*  99*/        int i = s.length();
/* 100*/        int j = count;
/* 101*/        enlarge(i + 2);
                byte abyte0[];
/* 103*/        (abyte0 = buf)[j++] = (byte)(i >>> 8);
/* 105*/        abyte0[j++] = (byte)i;
/* 106*/        for(int k = 0; k < i; k++)
                {
/* 107*/            char c = s.charAt(k);
/* 108*/            if(c > 0 && c <= '\177')
                    {
/* 109*/                abyte0[j++] = (byte)c;
                    } else
                    {
/* 111*/                writeUTF2(s, i, k);
/* 112*/                return;
                    }
                }

/* 116*/        count = j;
            }

            private void writeUTF2(String s, int i, int j)
            {
/* 120*/        int k = i;
/* 121*/        for(int l = j; l < i; l++)
                {
                    char c1;
/* 122*/            if((c1 = s.charAt(l)) > '\u07FF')
                    {
/* 124*/                k += 2;
/* 124*/                continue;
                    }
/* 125*/            if(c1 == 0 || c1 > '\177')
/* 126*/                k++;
                }

/* 129*/        if(k > 65535)
/* 130*/            throw new RuntimeException((new StringBuilder("encoded string too long: ")).append(i).append(k).append(" bytes").toString());
/* 133*/        enlarge(k + 2);
/* 134*/        int i1 = count;
                byte abyte0[];
/* 135*/        (abyte0 = buf)[i1] = (byte)(k >>> 8);
/* 137*/        abyte0[i1 + 1] = (byte)k;
/* 138*/        i1 += j + 2;
/* 139*/        for(j = j; j < i; j++)
                {
/* 140*/            char c = s.charAt(j);
/* 141*/            if(c > 0 && c <= '\177')
                    {
/* 142*/                abyte0[i1++] = (byte)c;
/* 142*/                continue;
                    }
/* 143*/            if(c > '\u07FF')
                    {
/* 144*/                abyte0[i1] = (byte)(0xe0 | c >> 12 & 0xf);
/* 145*/                abyte0[i1 + 1] = (byte)(0x80 | c >> 6 & 0x3f);
/* 146*/                abyte0[i1 + 2] = (byte)(0x80 | c & 0x3f);
/* 147*/                i1 += 3;
                    } else
                    {
/* 150*/                abyte0[i1] = (byte)(0xc0 | c >> 6 & 0x1f);
/* 151*/                abyte0[i1 + 1] = (byte)(0x80 | c & 0x3f);
/* 152*/                i1 += 2;
                    }
                }

/* 156*/        count = i1;
            }

            public final void write(int i, int j)
            {
/* 160*/        buf[i] = (byte)j;
            }

            public final void writeShort(int i, int j)
            {
/* 164*/        buf[i] = (byte)(j >>> 8);
/* 165*/        buf[i + 1] = (byte)j;
            }

            public final void writeInt(int i, int j)
            {
/* 169*/        buf[i] = j >> 24;
/* 170*/        buf[i + 1] = (byte)(j >>> 16);
/* 171*/        buf[i + 2] = (byte)(j >>> 8);
/* 172*/        buf[i + 3] = (byte)j;
            }

            public final byte[] toByteArray()
            {
/* 176*/        byte abyte0[] = new byte[count];
/* 177*/        System.arraycopy(buf, 0, abyte0, 0, count);
/* 178*/        return abyte0;
            }

            public final void writeTo(OutputStream outputstream)
                throws IOException
            {
/* 182*/        outputstream.write(buf, 0, count);
            }

            public final void enlarge(int i)
            {
/* 186*/        if((i = count + i) > buf.length)
                {
                    int j;
/* 188*/            i = new byte[(j = buf.length << 1) <= i ? i : j];
/* 190*/            System.arraycopy(buf, 0, i, 0, count);
/* 191*/            buf = i;
                }
            }

            private byte buf[];
            private int count;
}
