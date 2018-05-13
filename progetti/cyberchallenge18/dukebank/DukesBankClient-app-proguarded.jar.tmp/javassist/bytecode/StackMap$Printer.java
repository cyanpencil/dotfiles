// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;

import java.io.PrintWriter;

// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

static class writer extends writer
{

            public void print()
            {
/* 517*/        int i = ByteArray.readU16bit(info, 0);
/* 518*/        writer.println((new StringBuilder()).append(i).append(" entries").toString());
/* 519*/        visit();
            }

            public int locals(int i, int j, int k)
            {
/* 523*/        writer.println((new StringBuilder("  * offset ")).append(j).toString());
/* 524*/        return super.ocals(i, j, k);
            }

            private PrintWriter writer;

            public (StackMap stackmap, PrintWriter printwriter)
            {
/* 512*/        super(stackmap);
/* 513*/        writer = printwriter;
            }
}
