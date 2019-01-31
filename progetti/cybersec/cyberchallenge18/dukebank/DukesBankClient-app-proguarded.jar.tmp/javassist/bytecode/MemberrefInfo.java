// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

abstract class MemberrefInfo extends ConstInfo
{

            public MemberrefInfo(int i, int j, int k)
            {
/*1493*/        super(k);
/*1494*/        classIndex = i;
/*1495*/        nameAndTypeIndex = j;
            }

            public MemberrefInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1499*/        super(i);
/*1500*/        classIndex = datainputstream.readUnsignedShort();
/*1501*/        nameAndTypeIndex = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1504*/        return classIndex << 16 ^ nameAndTypeIndex;
            }

            public boolean equals(Object obj)
            {
/*1507*/        if(obj instanceof MemberrefInfo)
/*1508*/            return ((MemberrefInfo) (obj = (MemberrefInfo)obj)).classIndex == classIndex && ((MemberrefInfo) (obj)).nameAndTypeIndex == nameAndTypeIndex && obj.getClass() == getClass();
/*1513*/        else
/*1513*/            return false;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1517*/        int i = constpool.getItem(classIndex).copy(constpool, constpool1, map);
/*1518*/        constpool = constpool.getItem(nameAndTypeIndex).copy(constpool, constpool1, map);
/*1519*/        return copy2(constpool1, i, constpool);
            }

            protected abstract int copy2(ConstPool constpool, int i, int j);

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1525*/        dataoutputstream.writeByte(getTag());
/*1526*/        dataoutputstream.writeShort(classIndex);
/*1527*/        dataoutputstream.writeShort(nameAndTypeIndex);
            }

            public void print(PrintWriter printwriter)
            {
/*1531*/        printwriter.print((new StringBuilder()).append(getTagName()).append(" #").toString());
/*1532*/        printwriter.print(classIndex);
/*1533*/        printwriter.print(", name&type #");
/*1534*/        printwriter.println(nameAndTypeIndex);
            }

            public abstract String getTagName();

            int classIndex;
            int nameAndTypeIndex;
}
