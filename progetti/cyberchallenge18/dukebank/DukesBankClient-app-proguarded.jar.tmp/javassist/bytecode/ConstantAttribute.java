// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstantAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class ConstantAttribute extends AttributeInfo
{

            ConstantAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            public ConstantAttribute(ConstPool constpool, int i)
            {
/*  46*/        super(constpool, "ConstantValue");
/*  47*/        (constpool = new byte[2])[0] = (byte)(i >>> 8);
/*  49*/        constpool[1] = (byte)i;
/*  50*/        set(constpool);
            }

            public int getConstantValue()
            {
/*  57*/        return ByteArray.readU16bit(get(), 0);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/*  69*/        map = getConstPool().copy(getConstantValue(), constpool, map);
/*  71*/        return new ConstantAttribute(constpool, map);
            }

            public static final String tag = "ConstantValue";
}
