// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalVariableAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool, Descriptor

public class LocalVariableAttribute extends AttributeInfo
{

            public LocalVariableAttribute(ConstPool constpool)
            {
/*  41*/        super(constpool, "LocalVariableTable", new byte[2]);
/*  42*/        ByteArray.write16bit(0, info, 0);
            }

            /**
             * @deprecated Method LocalVariableAttribute is deprecated
             */

            public LocalVariableAttribute(ConstPool constpool, String s)
            {
/*  57*/        super(constpool, s, new byte[2]);
/*  58*/        ByteArray.write16bit(0, info, 0);
            }

            LocalVariableAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  64*/        super(constpool, i, datainputstream);
            }

            LocalVariableAttribute(ConstPool constpool, String s, byte abyte0[])
            {
/*  68*/        super(constpool, s, abyte0);
            }

            public void addEntry(int i, int j, int k, int l, int i1)
            {
                int j1;
/*  82*/        byte abyte0[] = new byte[(j1 = info.length) + 10];
/*  84*/        ByteArray.write16bit(tableLength() + 1, abyte0, 0);
/*  85*/        for(int k1 = 2; k1 < j1; k1++)
/*  86*/            abyte0[k1] = info[k1];

/*  88*/        ByteArray.write16bit(i, abyte0, j1);
/*  89*/        ByteArray.write16bit(j, abyte0, j1 + 2);
/*  90*/        ByteArray.write16bit(k, abyte0, j1 + 4);
/*  91*/        ByteArray.write16bit(l, abyte0, j1 + 6);
/*  92*/        ByteArray.write16bit(i1, abyte0, j1 + 8);
/*  93*/        info = abyte0;
            }

            void renameClass(String s, String s1)
            {
/*  97*/        ConstPool constpool = getConstPool();
/*  98*/        int i = tableLength();
/*  99*/        for(int j = 0; j < i; j++)
                {
/* 100*/            int k = j * 10 + 2;
                    int l;
/* 101*/            if((l = ByteArray.readU16bit(info, k + 6)) != 0)
                    {
/* 103*/                String s2 = constpool.getUtf8Info(l);
/* 104*/                s2 = renameEntry(s2, s, s1);
/* 105*/                ByteArray.write16bit(constpool.addUtf8Info(s2), info, k + 6);
                    }
                }

            }

            String renameEntry(String s, String s1, String s2)
            {
/* 111*/        return Descriptor.rename(s, s1, s2);
            }

            void renameClass(Map map)
            {
/* 115*/        ConstPool constpool = getConstPool();
/* 116*/        int i = tableLength();
/* 117*/        for(int j = 0; j < i; j++)
                {
/* 118*/            int k = j * 10 + 2;
                    int l;
/* 119*/            if((l = ByteArray.readU16bit(info, k + 6)) != 0)
                    {
/* 121*/                String s = constpool.getUtf8Info(l);
/* 122*/                s = renameEntry(s, map);
/* 123*/                ByteArray.write16bit(constpool.addUtf8Info(s), info, k + 6);
                    }
                }

            }

            String renameEntry(String s, Map map)
            {
/* 129*/        return Descriptor.rename(s, map);
            }

            public void shiftIndex(int i, int j)
            {
/* 140*/        int k = info.length;
/* 141*/        for(int l = 2; l < k; l += 10)
                {
                    int i1;
/* 142*/            if((i1 = ByteArray.readU16bit(info, l + 8)) >= i)
/* 144*/                ByteArray.write16bit(i1 + j, info, l + 8);
                }

            }

            public int tableLength()
            {
/* 153*/        return ByteArray.readU16bit(info, 0);
            }

            public int startPc(int i)
            {
/* 164*/        return ByteArray.readU16bit(info, i * 10 + 2);
            }

            public int codeLength(int i)
            {
/* 175*/        return ByteArray.readU16bit(info, i * 10 + 4);
            }

            void shiftPc(int i, int j, boolean flag)
            {
/* 182*/        int k = tableLength();
/* 183*/        for(int l = 0; l < k; l++)
                {
/* 184*/            int i1 = l * 10 + 2;
/* 185*/            int j1 = ByteArray.readU16bit(info, i1);
/* 186*/            int k1 = ByteArray.readU16bit(info, i1 + 2);
/* 190*/            if(j1 > i || flag && j1 == i && j1 != 0)
                    {
/* 191*/                ByteArray.write16bit(j1 + j, info, i1);
/* 191*/                continue;
                    }
/* 192*/            if(j1 + k1 > i || flag && j1 + k1 == i)
/* 193*/                ByteArray.write16bit(k1 + j, info, i1 + 2);
                }

            }

            public int nameIndex(int i)
            {
/* 204*/        return ByteArray.readU16bit(info, i * 10 + 6);
            }

            public String variableName(int i)
            {
/* 214*/        return getConstPool().getUtf8Info(nameIndex(i));
            }

            public int descriptorIndex(int i)
            {
/* 230*/        return ByteArray.readU16bit(info, i * 10 + 8);
            }

            public int signatureIndex(int i)
            {
/* 244*/        return descriptorIndex(i);
            }

            public String descriptor(int i)
            {
/* 258*/        return getConstPool().getUtf8Info(descriptorIndex(i));
            }

            public String signature(int i)
            {
/* 275*/        return descriptor(i);
            }

            public int index(int i)
            {
/* 285*/        return ByteArray.readU16bit(info, i * 10 + 10);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
                byte abyte0[];
/* 295*/        byte abyte1[] = new byte[(abyte0 = get()).length];
/* 297*/        ConstPool constpool1 = getConstPool();
/* 298*/        LocalVariableAttribute localvariableattribute = makeThisAttr(constpool, abyte1);
                int i;
/* 299*/        ByteArray.write16bit(i = ByteArray.readU16bit(abyte0, 0), abyte1, 0);
/* 301*/        int j = 2;
/* 302*/        for(int k = 0; k < i; k++)
                {
/* 303*/            int l = ByteArray.readU16bit(abyte0, j);
/* 304*/            int i1 = ByteArray.readU16bit(abyte0, j + 2);
/* 305*/            int j1 = ByteArray.readU16bit(abyte0, j + 4);
/* 306*/            int k1 = ByteArray.readU16bit(abyte0, j + 6);
/* 307*/            int l1 = ByteArray.readU16bit(abyte0, j + 8);
/* 309*/            ByteArray.write16bit(l, abyte1, j);
/* 310*/            ByteArray.write16bit(i1, abyte1, j + 2);
/* 311*/            if(j1 != 0)
/* 312*/                j1 = constpool1.copy(j1, constpool, null);
/* 314*/            ByteArray.write16bit(j1, abyte1, j + 4);
/* 316*/            if(k1 != 0)
                    {
                        String s;
/* 317*/                s = Descriptor.rename(s = constpool1.getUtf8Info(k1), map);
/* 319*/                k1 = constpool.addUtf8Info(s);
                    }
/* 322*/            ByteArray.write16bit(k1, abyte1, j + 6);
/* 323*/            ByteArray.write16bit(l1, abyte1, j + 8);
/* 324*/            j += 10;
                }

/* 327*/        return localvariableattribute;
            }

            LocalVariableAttribute makeThisAttr(ConstPool constpool, byte abyte0[])
            {
/* 332*/        return new LocalVariableAttribute(constpool, "LocalVariableTable", abyte0);
            }

            public static final String tag = "LocalVariableTable";
            public static final String typeTag = "LocalVariableTypeTable";
}
