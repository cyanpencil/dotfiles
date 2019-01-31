// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;

import java.io.ByteArrayOutputStream;

// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMapTable, ConstPool

public static class output
{

            public byte[] toByteArray()
            {
/* 561*/        byte abyte0[] = output.toByteArray();
/* 562*/        ByteArray.write16bit(numOfEntries, abyte0, 0);
/* 563*/        return abyte0;
            }

            public StackMapTable toStackMapTable(ConstPool constpool)
            {
/* 574*/        return new StackMapTable(constpool, toByteArray());
            }

            public void sameFrame(int i)
            {
/* 581*/        numOfEntries++;
/* 582*/        if(i < 64)
                {
/* 583*/            output.write(i);
/* 583*/            return;
                } else
                {
/* 585*/            output.write(251);
/* 586*/            write16(i);
/* 588*/            return;
                }
            }

            public void sameLocals(int i, int j, int k)
            {
/* 602*/        numOfEntries++;
/* 603*/        if(i < 64)
                {
/* 604*/            output.write(i + 64);
                } else
                {
/* 606*/            output.write(247);
/* 607*/            write16(i);
                }
/* 610*/        writeTypeInfo(j, k);
            }

            public void chopFrame(int i, int j)
            {
/* 619*/        numOfEntries++;
/* 620*/        output.write(251 - j);
/* 621*/        write16(i);
            }

            public void appendFrame(int i, int ai[], int ai1[])
            {
/* 638*/        numOfEntries++;
/* 639*/        int j = ai.length;
/* 640*/        output.write(j + 251);
/* 641*/        write16(i);
/* 642*/        for(i = 0; i < j; i++)
/* 643*/            writeTypeInfo(ai[i], ai1[i]);

            }

            public void fullFrame(int i, int ai[], int ai1[], int ai2[], int ai3[])
            {
/* 667*/        numOfEntries++;
/* 668*/        output.write(255);
/* 669*/        write16(i);
/* 670*/        i = ai.length;
/* 671*/        write16(i);
/* 672*/        for(int j = 0; j < i; j++)
/* 673*/            writeTypeInfo(ai[j], ai1[j]);

/* 675*/        i = ai2.length;
/* 676*/        write16(i);
/* 677*/        for(int k = 0; k < i; k++)
/* 678*/            writeTypeInfo(ai2[k], ai3[k]);

            }

            private void writeTypeInfo(int i, int j)
            {
/* 682*/        output.write(i);
/* 683*/        if(i == 7 || i == 8)
/* 684*/            write16(j);
            }

            private void write16(int i)
            {
/* 688*/        output.write(i >>> 8 & 0xff);
/* 689*/        output.write(i & 0xff);
            }

            ByteArrayOutputStream output;
            int numOfEntries;

            public (int i)
            {
/* 551*/        output = new ByteArrayOutputStream(i);
/* 552*/        numOfEntries = 0;
/* 553*/        output.write(0);
/* 554*/        output.write(0);
            }
}
