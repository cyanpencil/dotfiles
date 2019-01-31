// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;

// Referenced classes of package javassist.bytecode:
//            MemberrefInfo, ConstPool

class MethodrefInfo extends MemberrefInfo
{

            public MethodrefInfo(int i, int j, int k)
            {
/*1564*/        super(i, j, k);
            }

            public MethodrefInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1568*/        super(datainputstream, i);
            }

            public int getTag()
            {
/*1571*/        return 10;
            }

            public String getTagName()
            {
/*1573*/        return "Method";
            }

            protected int copy2(ConstPool constpool, int i, int j)
            {
/*1576*/        return constpool.addMethodrefInfo(i, j);
            }

            static final int tag = 10;
}
