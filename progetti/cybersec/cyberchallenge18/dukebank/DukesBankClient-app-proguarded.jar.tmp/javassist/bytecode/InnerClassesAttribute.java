// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InnerClassesAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class InnerClassesAttribute extends AttributeInfo
{

            InnerClassesAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            private InnerClassesAttribute(ConstPool constpool, byte abyte0[])
            {
/*  39*/        super(constpool, "InnerClasses", abyte0);
            }

            public InnerClassesAttribute(ConstPool constpool)
            {
/*  48*/        super(constpool, "InnerClasses", new byte[2]);
/*  49*/        ByteArray.write16bit(0, get(), 0);
            }

            public int tableLength()
            {
/*  55*/        return ByteArray.readU16bit(get(), 0);
            }

            public int innerClassIndex(int i)
            {
/*  61*/        return ByteArray.readU16bit(get(), (i << 3) + 2);
            }

            public String innerClass(int i)
            {
/*  71*/        if((i = innerClassIndex(i)) == 0)
/*  73*/            return null;
/*  75*/        else
/*  75*/            return constPool.getClassInfo(i);
            }

            public void setInnerClassIndex(int i, int j)
            {
/*  83*/        ByteArray.write16bit(j, get(), (i << 3) + 2);
            }

            public int outerClassIndex(int i)
            {
/*  90*/        return ByteArray.readU16bit(get(), (i << 3) + 4);
            }

            public String outerClass(int i)
            {
/* 100*/        if((i = outerClassIndex(i)) == 0)
/* 102*/            return null;
/* 104*/        else
/* 104*/            return constPool.getClassInfo(i);
            }

            public void setOuterClassIndex(int i, int j)
            {
/* 112*/        ByteArray.write16bit(j, get(), (i << 3) + 4);
            }

            public int innerNameIndex(int i)
            {
/* 119*/        return ByteArray.readU16bit(get(), (i << 3) + 6);
            }

            public String innerName(int i)
            {
/* 129*/        if((i = innerNameIndex(i)) == 0)
/* 131*/            return null;
/* 133*/        else
/* 133*/            return constPool.getUtf8Info(i);
            }

            public void setInnerNameIndex(int i, int j)
            {
/* 141*/        ByteArray.write16bit(j, get(), (i << 3) + 6);
            }

            public int accessFlags(int i)
            {
/* 148*/        return ByteArray.readU16bit(get(), (i << 3) + 8);
            }

            public void setAccessFlags(int i, int j)
            {
/* 156*/        ByteArray.write16bit(j, get(), (i << 3) + 8);
            }

            public void append(String s, String s1, String s2, int i)
            {
/* 168*/        s = constPool.addClassInfo(s);
/* 169*/        s1 = constPool.addClassInfo(s1);
/* 170*/        s2 = constPool.addUtf8Info(s2);
/* 171*/        append(s, s1, s2, i);
            }

            public void append(int i, int j, int k, int l)
            {
                byte abyte0[];
                int i1;
/* 183*/        byte abyte1[] = new byte[(i1 = (abyte0 = get()).length) + 8];
/* 186*/        for(int j1 = 2; j1 < i1; j1++)
/* 187*/            abyte1[j1] = abyte0[j1];

                int k1;
/* 189*/        ByteArray.write16bit((k1 = ByteArray.readU16bit(abyte0, 0)) + 1, abyte1, 0);
/* 192*/        ByteArray.write16bit(i, abyte1, i1);
/* 193*/        ByteArray.write16bit(j, abyte1, i1 + 2);
/* 194*/        ByteArray.write16bit(k, abyte1, i1 + 4);
/* 195*/        ByteArray.write16bit(l, abyte1, i1 + 6);
/* 197*/        set(abyte1);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
                byte abyte0[];
/* 209*/        byte abyte1[] = new byte[(abyte0 = get()).length];
/* 211*/        ConstPool constpool1 = getConstPool();
/* 212*/        InnerClassesAttribute innerclassesattribute = new InnerClassesAttribute(constpool, abyte1);
                int i;
/* 213*/        ByteArray.write16bit(i = ByteArray.readU16bit(abyte0, 0), abyte1, 0);
/* 215*/        int j = 2;
/* 216*/        for(int k = 0; k < i; k++)
                {
/* 217*/            int l = ByteArray.readU16bit(abyte0, j);
/* 218*/            int i1 = ByteArray.readU16bit(abyte0, j + 2);
/* 219*/            int j1 = ByteArray.readU16bit(abyte0, j + 4);
/* 220*/            int k1 = ByteArray.readU16bit(abyte0, j + 6);
/* 222*/            if(l != 0)
/* 223*/                l = constpool1.copy(l, constpool, map);
/* 225*/            ByteArray.write16bit(l, abyte1, j);
/* 227*/            if(i1 != 0)
/* 228*/                i1 = constpool1.copy(i1, constpool, map);
/* 230*/            ByteArray.write16bit(i1, abyte1, j + 2);
/* 232*/            if(j1 != 0)
/* 233*/                j1 = constpool1.copy(j1, constpool, map);
/* 235*/            ByteArray.write16bit(j1, abyte1, j + 4);
/* 236*/            ByteArray.write16bit(k1, abyte1, j + 6);
/* 237*/            j += 8;
                }

/* 240*/        return innerclassesattribute;
            }

            public static final String tag = "InnerClasses";
}
