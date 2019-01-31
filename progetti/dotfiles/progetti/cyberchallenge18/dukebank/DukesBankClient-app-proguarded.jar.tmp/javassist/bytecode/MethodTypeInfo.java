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

class MethodTypeInfo extends ConstInfo
{

            public MethodTypeInfo(int i, int j)
            {
/*1881*/        super(j);
/*1882*/        descriptor = i;
            }

            public MethodTypeInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1886*/        super(i);
/*1887*/        descriptor = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1890*/        return descriptor;
            }

            public boolean equals(Object obj)
            {
/*1893*/        if(obj instanceof MethodTypeInfo)
/*1894*/            return ((MethodTypeInfo)obj).descriptor == descriptor;
/*1896*/        else
/*1896*/            return false;
            }

            public int getTag()
            {
/*1899*/        return 16;
            }

            public void renameClass(ConstPool constpool, String s, String s1, HashMap hashmap)
            {
                String s2;
/*1902*/        s = Descriptor.rename(s2 = constpool.getUtf8Info(descriptor), s, s1);
/*1904*/        if(s2 != s)
                {
/*1905*/            if(hashmap == null)
                    {
/*1906*/                descriptor = constpool.addUtf8Info(s);
/*1906*/                return;
                    }
/*1908*/            hashmap.remove(this);
/*1909*/            descriptor = constpool.addUtf8Info(s);
/*1910*/            hashmap.put(this, this);
                }
            }

            public void renameClass(ConstPool constpool, Map map, HashMap hashmap)
            {
                String s;
/*1915*/        map = Descriptor.rename(s = constpool.getUtf8Info(descriptor), map);
/*1917*/        if(s != map)
                {
/*1918*/            if(hashmap == null)
                    {
/*1919*/                descriptor = constpool.addUtf8Info(map);
/*1919*/                return;
                    }
/*1921*/            hashmap.remove(this);
/*1922*/            descriptor = constpool.addUtf8Info(map);
/*1923*/            hashmap.put(this, this);
                }
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1928*/        constpool = Descriptor.rename(constpool = constpool.getUtf8Info(descriptor), map);
/*1930*/        return constpool1.addMethodTypeInfo(constpool1.addUtf8Info(constpool));
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1934*/        dataoutputstream.writeByte(16);
/*1935*/        dataoutputstream.writeShort(descriptor);
            }

            public void print(PrintWriter printwriter)
            {
/*1939*/        printwriter.print("MethodType #");
/*1940*/        printwriter.println(descriptor);
            }

            static final int tag = 16;
            int descriptor;
}
