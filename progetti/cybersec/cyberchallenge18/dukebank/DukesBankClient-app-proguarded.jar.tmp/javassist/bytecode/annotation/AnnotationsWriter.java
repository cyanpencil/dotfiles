// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsWriter.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.io.OutputStream;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;

public class AnnotationsWriter
{

            public AnnotationsWriter(OutputStream outputstream, ConstPool constpool)
            {
/*  71*/        output = outputstream;
/*  72*/        pool = constpool;
            }

            public ConstPool getConstPool()
            {
/*  79*/        return pool;
            }

            public void close()
                throws IOException
            {
/*  87*/        output.close();
            }

            public void numParameters(int i)
                throws IOException
            {
/*  97*/        output.write(i);
            }

            public void numAnnotations(int i)
                throws IOException
            {
/* 107*/        write16bit(i);
            }

            public void annotation(String s, int i)
                throws IOException
            {
/* 122*/        annotation(pool.addUtf8Info(s), i);
            }

            public void annotation(int i, int j)
                throws IOException
            {
/* 137*/        write16bit(i);
/* 138*/        write16bit(j);
            }

            public void memberValuePair(String s)
                throws IOException
            {
/* 151*/        memberValuePair(pool.addUtf8Info(s));
            }

            public void memberValuePair(int i)
                throws IOException
            {
/* 165*/        write16bit(i);
            }

            public void constValueIndex(boolean flag)
                throws IOException
            {
/* 175*/        constValueIndex(90, pool.addIntegerInfo(flag ? 1 : 0));
            }

            public void constValueIndex(byte byte0)
                throws IOException
            {
/* 185*/        constValueIndex(66, pool.addIntegerInfo(byte0));
            }

            public void constValueIndex(char c)
                throws IOException
            {
/* 195*/        constValueIndex(67, pool.addIntegerInfo(c));
            }

            public void constValueIndex(short word0)
                throws IOException
            {
/* 205*/        constValueIndex(83, pool.addIntegerInfo(word0));
            }

            public void constValueIndex(int i)
                throws IOException
            {
/* 215*/        constValueIndex(73, pool.addIntegerInfo(i));
            }

            public void constValueIndex(long l)
                throws IOException
            {
/* 225*/        constValueIndex(74, pool.addLongInfo(l));
            }

            public void constValueIndex(float f)
                throws IOException
            {
/* 235*/        constValueIndex(70, pool.addFloatInfo(f));
            }

            public void constValueIndex(double d)
                throws IOException
            {
/* 245*/        constValueIndex(68, pool.addDoubleInfo(d));
            }

            public void constValueIndex(String s)
                throws IOException
            {
/* 255*/        constValueIndex(115, pool.addUtf8Info(s));
            }

            public void constValueIndex(int i, int j)
                throws IOException
            {
/* 269*/        output.write(i);
/* 270*/        write16bit(j);
            }

            public void enumConstValue(String s, String s1)
                throws IOException
            {
/* 283*/        enumConstValue(pool.addUtf8Info(s), pool.addUtf8Info(s1));
            }

            public void enumConstValue(int i, int j)
                throws IOException
            {
/* 299*/        output.write(101);
/* 300*/        write16bit(i);
/* 301*/        write16bit(j);
            }

            public void classInfoIndex(String s)
                throws IOException
            {
/* 311*/        classInfoIndex(pool.addUtf8Info(s));
            }

            public void classInfoIndex(int i)
                throws IOException
            {
/* 321*/        output.write(99);
/* 322*/        write16bit(i);
            }

            public void annotationValue()
                throws IOException
            {
/* 331*/        output.write(64);
            }

            public void arrayValue(int i)
                throws IOException
            {
/* 345*/        output.write(91);
/* 346*/        write16bit(i);
            }

            private void write16bit(int i)
                throws IOException
            {
/* 350*/        byte abyte0[] = new byte[2];
/* 351*/        ByteArray.write16bit(i, abyte0, 0);
/* 352*/        output.write(abyte0);
            }

            private OutputStream output;
            private ConstPool pool;
}
