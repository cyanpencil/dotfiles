// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class ConstInfoPadding extends ConstInfo
{

            public ConstInfoPadding(int i)
            {
/*1301*/        super(i);
            }

            public int getTag()
            {
/*1303*/        return 0;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1306*/        return constpool1.addConstInfoPadding();
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
            }

            public void print(PrintWriter printwriter)
            {
/*1312*/        printwriter.println("padding");
            }
}
