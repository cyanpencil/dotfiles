// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalVariableTypeAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            LocalVariableAttribute, ByteArray, SignatureAttribute, ConstPool

public class LocalVariableTypeAttribute extends LocalVariableAttribute
{

            public LocalVariableTypeAttribute(ConstPool constpool)
            {
/*  38*/        super(constpool, "LocalVariableTypeTable", new byte[2]);
/*  39*/        ByteArray.write16bit(0, info, 0);
            }

            LocalVariableTypeAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  45*/        super(constpool, i, datainputstream);
            }

            private LocalVariableTypeAttribute(ConstPool constpool, byte abyte0[])
            {
/*  49*/        super(constpool, "LocalVariableTypeTable", abyte0);
            }

            String renameEntry(String s, String s1, String s2)
            {
/*  53*/        return SignatureAttribute.renameClass(s, s1, s2);
            }

            String renameEntry(String s, Map map)
            {
/*  57*/        return SignatureAttribute.renameClass(s, map);
            }

            LocalVariableAttribute makeThisAttr(ConstPool constpool, byte abyte0[])
            {
/*  61*/        return new LocalVariableTypeAttribute(constpool, abyte0);
            }

            public static final String tag = "LocalVariableTypeTable";
}
