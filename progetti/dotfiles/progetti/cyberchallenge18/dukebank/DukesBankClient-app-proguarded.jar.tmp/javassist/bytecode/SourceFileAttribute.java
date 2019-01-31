// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceFileAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class SourceFileAttribute extends AttributeInfo
{

            SourceFileAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            public SourceFileAttribute(ConstPool constpool, String s)
            {
/*  45*/        super(constpool, "SourceFile");
/*  46*/        constpool = constpool.addUtf8Info(s);
/*  47*/        (s = new byte[2])[0] = (byte)(constpool >>> 8);
/*  49*/        s[1] = (byte)constpool;
/*  50*/        set(s);
            }

            public String getFileName()
            {
/*  57*/        return getConstPool().getUtf8Info(ByteArray.readU16bit(get(), 0));
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/*  69*/        return new SourceFileAttribute(constpool, getFileName());
            }

            public static final String tag = "SourceFile";
}
