// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

static class t> extends t>
{

            byte[] doit()
            {
/* 304*/        visit();
/* 305*/        return writer.teArray();
            }

            public void visit()
            {
/* 309*/        int i = ByteArray.readU16bit(info, 0);
/* 310*/        writer.e16bit(i);
/* 311*/        super.t();
            }

            public int locals(int i, int j, int k)
            {
/* 315*/        writer.e16bit(j);
/* 316*/        return super.ls(i, j, k);
            }

            public int typeInfoArray(int i, int j, int k, boolean flag)
            {
/* 320*/        writer.e16bit(k);
/* 321*/        return super.InfoArray(i, j, k, flag);
            }

            public void typeInfo(int i, byte byte0)
            {
/* 325*/        writer.eVerifyTypeInfo(byte0, 0);
            }

            public void objectVariable(int i, int j)
            {
/* 329*/        writer.eVerifyTypeInfo(7, j);
            }

            public void uninitialized(int i, int j)
            {
/* 333*/        writer.eVerifyTypeInfo(8, j);
            }

            eVerifyTypeInfo writer;

            (StackMap stackmap)
            {
/* 299*/        super(stackmap);
/* 300*/        writer = new t>();
            }
}
