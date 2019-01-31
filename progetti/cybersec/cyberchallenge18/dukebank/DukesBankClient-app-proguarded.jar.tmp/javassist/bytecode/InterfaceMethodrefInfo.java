// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;

// Referenced classes of package javassist.bytecode:
//            MemberrefInfo, ConstPool

class InterfaceMethodrefInfo extends MemberrefInfo
{

            public InterfaceMethodrefInfo(int i, int j, int k)
            {
/*1584*/        super(i, j, k);
            }

            public InterfaceMethodrefInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1588*/        super(datainputstream, i);
            }

            public int getTag()
            {
/*1591*/        return 11;
            }

            public String getTagName()
            {
/*1593*/        return "Interface";
            }

            protected int copy2(ConstPool constpool, int i, int j)
            {
/*1596*/        return constpool.addInterfaceMethodrefInfo(i, j);
            }

            static final int tag = 11;
}
