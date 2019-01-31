// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool, Descriptor

class NameAndTypeInfo extends ConstInfo
{

            public NameAndTypeInfo(int i, int j, int k)
            {
/*1416*/        super(k);
/*1417*/        memberName = i;
/*1418*/        typeDescriptor = j;
            }

            public NameAndTypeInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1422*/        super(i);
/*1423*/        memberName = datainputstream.readUnsignedShort();
/*1424*/        typeDescriptor = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1427*/        return memberName << 16 ^ typeDescriptor;
            }

            public boolean equals(Object obj)
            {
/*1430*/        if(obj instanceof NameAndTypeInfo)
/*1431*/            return ((NameAndTypeInfo) (obj = (NameAndTypeInfo)obj)).memberName == memberName && ((NameAndTypeInfo) (obj)).typeDescriptor == typeDescriptor;
/*1435*/        else
/*1435*/            return false;
            }

            public int getTag()
            {
/*1438*/        return 12;
            }

            public void renameClass(ConstPool constpool, String s, String s1, HashMap hashmap)
            {
                String s2;
/*1441*/        s = Descriptor.rename(s2 = constpool.getUtf8Info(typeDescriptor), s, s1);
/*1443*/        if(s2 != s)
                {
/*1444*/            if(hashmap == null)
                    {
/*1445*/                typeDescriptor = constpool.addUtf8Info(s);
/*1445*/                return;
                    }
/*1447*/            hashmap.remove(this);
/*1448*/            typeDescriptor = constpool.addUtf8Info(s);
/*1449*/            hashmap.put(this, this);
                }
            }

            public void renameClass(ConstPool constpool, Map map, HashMap hashmap)
            {
                String s;
/*1454*/        map = Descriptor.rename(s = constpool.getUtf8Info(typeDescriptor), map);
/*1456*/        if(s != map)
                {
/*1457*/            if(hashmap == null)
                    {
/*1458*/                typeDescriptor = constpool.addUtf8Info(map);
/*1458*/                return;
                    }
/*1460*/            hashmap.remove(this);
/*1461*/            typeDescriptor = constpool.addUtf8Info(map);
/*1462*/            hashmap.put(this, this);
                }
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1467*/        String s = constpool.getUtf8Info(memberName);
/*1468*/        constpool = Descriptor.rename(constpool = constpool.getUtf8Info(typeDescriptor), map);
/*1470*/        return constpool1.addNameAndTypeInfo(constpool1.addUtf8Info(s), constpool1.addUtf8Info(constpool));
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1475*/        dataoutputstream.writeByte(12);
/*1476*/        dataoutputstream.writeShort(memberName);
/*1477*/        dataoutputstream.writeShort(typeDescriptor);
            }

            public void print(PrintWriter printwriter)
            {
/*1481*/        printwriter.print("NameAndType #");
/*1482*/        printwriter.print(memberName);
/*1483*/        printwriter.print(", type #");
/*1484*/        printwriter.println(typeDescriptor);
            }

            static final int tag = 12;
            int memberName;
            int typeDescriptor;
}
