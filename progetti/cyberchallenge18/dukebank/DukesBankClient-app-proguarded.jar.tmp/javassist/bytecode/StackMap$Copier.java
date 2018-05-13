// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;

import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ByteArray, ConstPool, StackMap

static class classnames extends classnames
{

            public void visit()
            {
                int i;
/* 238*/        ByteArray.write16bit(i = ByteArray.readU16bit(info, 0), dest, 0);
/* 240*/        super.visit();
            }

            public int locals(int i, int j, int k)
            {
/* 244*/        ByteArray.write16bit(j, dest, i - 4);
/* 245*/        return super.locals(i, j, k);
            }

            public int typeInfoArray(int i, int j, int k, boolean flag)
            {
/* 249*/        ByteArray.write16bit(k, dest, i - 2);
/* 250*/        return super.typeInfoArray(i, j, k, flag);
            }

            public void typeInfo(int i, byte byte0)
            {
/* 254*/        dest[i] = byte0;
            }

            public void objectVariable(int i, int j)
            {
/* 258*/        dest[i] = 7;
/* 259*/        ByteArray.write16bit(j = srcCp.copy(j, destCp, classnames), dest, i + 1);
            }

            public void uninitialized(int i, int j)
            {
/* 264*/        dest[i] = 8;
/* 265*/        ByteArray.write16bit(j, dest, i + 1);
            }

            public StackMap getStackMap()
            {
/* 269*/        return new StackMap(destCp, dest);
            }

            byte dest[];
            ConstPool srcCp;
            ConstPool destCp;
            Map classnames;

            (StackMap stackmap, ConstPool constpool, Map map)
            {
/* 230*/        super(stackmap);
/* 231*/        srcCp = stackmap.getConstPool();
/* 232*/        dest = new byte[info.length];
/* 233*/        destCp = constpool;
/* 234*/        classnames = map;
            }
}
