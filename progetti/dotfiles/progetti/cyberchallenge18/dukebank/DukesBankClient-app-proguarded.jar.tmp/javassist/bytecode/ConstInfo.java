// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstPool

abstract class ConstInfo
{

            public ConstInfo(int i)
            {
/*1277*/        index = i;
            }

            public abstract int getTag();

            public String getClassName(ConstPool constpool)
            {
/*1281*/        return null;
            }

            public void renameClass(ConstPool constpool, String s, String s1, HashMap hashmap)
            {
            }

            public void renameClass(ConstPool constpool, Map map, HashMap hashmap)
            {
            }

            public abstract int copy(ConstPool constpool, ConstPool constpool1, Map map);

            public abstract void write(DataOutputStream dataoutputstream)
                throws IOException;

            public abstract void print(PrintWriter printwriter);

            public String toString()
            {
/*1291*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/*1292*/        PrintWriter printwriter = new PrintWriter(bytearrayoutputstream);
/*1293*/        print(printwriter);
/*1294*/        return bytearrayoutputstream.toString();
            }

            int index;
}
