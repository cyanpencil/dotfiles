// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;

// Referenced classes of package javassist.bytecode:
//            MemberrefInfo, ConstPool

class FieldrefInfo extends MemberrefInfo
{

            public FieldrefInfo(int i, int j, int k)
            {
/*1544*/        super(i, j, k);
            }

            public FieldrefInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1548*/        super(datainputstream, i);
            }

            public int getTag()
            {
/*1551*/        return 9;
            }

            public String getTagName()
            {
/*1553*/        return "Field";
            }

            protected int copy2(ConstPool constpool, int i, int j)
            {
/*1556*/        return constpool.addFieldrefInfo(i, j);
            }

            static final int tag = 9;
}
