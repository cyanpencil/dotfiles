// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;

import java.io.ByteArrayOutputStream;

// Referenced classes of package javassist.bytecode:
//            StackMap, ConstPool

public static class >
{

            public byte[] toByteArray()
            {
/* 547*/        return output.toByteArray();
            }

            public StackMap toStackMap(ConstPool constpool)
            {
/* 554*/        return new StackMap(constpool, output.toByteArray());
            }

            public void writeVerifyTypeInfo(int i, int j)
            {
/* 563*/        output.write(i);
/* 564*/        if(i == 7 || i == 8)
/* 565*/            write16bit(j);
            }

            public void write16bit(int i)
            {
/* 572*/        output.write(i >>> 8 & 0xff);
/* 573*/        output.write(i & 0xff);
            }

            private ByteArrayOutputStream output;

            public ()
            {
/* 540*/        output = new ByteArrayOutputStream();
            }
}
