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

class ClassInfo extends ConstInfo
{

            public ClassInfo(int i, int j)
            {
/*1321*/        super(j);
/*1322*/        name = i;
            }

            public ClassInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1326*/        super(i);
/*1327*/        name = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1330*/        return name;
            }

            public boolean equals(Object obj)
            {
/*1333*/        return (obj instanceof ClassInfo) && ((ClassInfo)obj).name == name;
            }

            public int getTag()
            {
/*1336*/        return 7;
            }

            public String getClassName(ConstPool constpool)
            {
/*1339*/        return constpool.getUtf8Info(name);
            }

            public void renameClass(ConstPool constpool, String s, String s1, HashMap hashmap)
            {
/*1343*/        String s2 = constpool.getUtf8Info(name);
/*1344*/        String s3 = null;
/*1345*/        if(s2.equals(s))
/*1346*/            s3 = s1;
/*1347*/        else
/*1347*/        if(s2.charAt(0) == '[')
                {
/*1348*/            s = Descriptor.rename(s2, s, s1);
/*1349*/            if(s2 != s)
/*1350*/                s3 = s;
                }
/*1353*/        if(s3 != null)
                {
/*1354*/            if(hashmap == null)
                    {
/*1355*/                name = constpool.addUtf8Info(s3);
/*1355*/                return;
                    }
/*1357*/            hashmap.remove(this);
/*1358*/            name = constpool.addUtf8Info(s3);
/*1359*/            hashmap.put(this, this);
                }
            }

            public void renameClass(ConstPool constpool, Map map, HashMap hashmap)
            {
/*1364*/        String s = constpool.getUtf8Info(name);
/*1365*/        Map map1 = null;
/*1366*/        if(s.charAt(0) == '[')
                {
/*1367*/            map = Descriptor.rename(s, map);
/*1368*/            if(s != map)
/*1369*/                map1 = map;
                } else
/*1372*/        if((map = (String)map.get(s)) != null && !map.equals(s))
/*1374*/            map1 = map;
/*1377*/        if(map1 != null)
                {
/*1378*/            if(hashmap == null)
                    {
/*1379*/                name = constpool.addUtf8Info(map1);
/*1379*/                return;
                    }
/*1381*/            hashmap.remove(this);
/*1382*/            name = constpool.addUtf8Info(map1);
/*1383*/            hashmap.put(this, this);
                }
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1389*/        constpool = constpool.getUtf8Info(name);
/*1390*/        if(map != null && (map = (String)map.get(constpool)) != null)
/*1393*/            constpool = map;
/*1396*/        return constpool1.addClassInfo(constpool);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1400*/        dataoutputstream.writeByte(7);
/*1401*/        dataoutputstream.writeShort(name);
            }

            public void print(PrintWriter printwriter)
            {
/*1405*/        printwriter.print("Class #");
/*1406*/        printwriter.println(name);
            }

            static final int tag = 7;
            int name;
}
