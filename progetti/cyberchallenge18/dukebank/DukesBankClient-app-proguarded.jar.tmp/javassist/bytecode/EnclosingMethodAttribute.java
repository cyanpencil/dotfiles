// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EnclosingMethodAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class EnclosingMethodAttribute extends AttributeInfo
{

            EnclosingMethodAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            public EnclosingMethodAttribute(ConstPool constpool, String s, String s1, String s2)
            {
/*  48*/        super(constpool, "EnclosingMethod");
/*  49*/        s = constpool.addClassInfo(s);
/*  50*/        constpool = constpool.addNameAndTypeInfo(s1, s2);
/*  51*/        (s1 = new byte[4])[0] = (byte)(s >>> 8);
/*  53*/        s1[1] = (byte)s;
/*  54*/        s1[2] = (byte)(constpool >>> 8);
/*  55*/        s1[3] = (byte)constpool;
/*  56*/        set(s1);
            }

            public EnclosingMethodAttribute(ConstPool constpool, String s)
            {
/*  67*/        super(constpool, "EnclosingMethod");
/*  68*/        constpool = constpool.addClassInfo(s);
/*  70*/        (s = new byte[4])[0] = (byte)(constpool >>> 8);
/*  72*/        s[1] = (byte)constpool;
/*  73*/        s[2] = 0;
/*  74*/        s[3] = 0;
/*  75*/        set(s);
            }

            public int classIndex()
            {
/*  82*/        return ByteArray.readU16bit(get(), 0);
            }

            public int methodIndex()
            {
/*  89*/        return ByteArray.readU16bit(get(), 2);
            }

            public String className()
            {
/*  96*/        return getConstPool().getClassInfo(classIndex());
            }

            public String methodName()
            {
/* 103*/        ConstPool constpool = getConstPool();
/* 104*/        int i = methodIndex();
/* 105*/        i = constpool.getNameAndTypeName(i);
/* 106*/        return constpool.getUtf8Info(i);
            }

            public String methodDescriptor()
            {
/* 113*/        ConstPool constpool = getConstPool();
/* 114*/        int i = methodIndex();
/* 115*/        i = constpool.getNameAndTypeDescriptor(i);
/* 116*/        return constpool.getUtf8Info(i);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 128*/        if(methodIndex() == 0)
/* 129*/            return new EnclosingMethodAttribute(constpool, className());
/* 131*/        else
/* 131*/            return new EnclosingMethodAttribute(constpool, className(), methodName(), methodDescriptor());
            }

            public static final String tag = "EnclosingMethod";
}
