// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;

import java.io.PrintWriter;

// Referenced classes of package javassist.bytecode:
//            BadBytecode, StackMapTable

static class offset extends offset
{

            public static void print(StackMapTable stackmaptable, PrintWriter printwriter)
            {
/* 718*/        try
                {
/* 718*/            (new <init>(stackmaptable.get(), printwriter)).parse();
/* 722*/            return;
                }
                // Misplaced declaration of an exception variable
/* 720*/        catch(StackMapTable stackmaptable)
                {
/* 721*/            printwriter.println(stackmaptable.getMessage());
                }
            }

            public void sameFrame(int i, int j)
            {
/* 732*/        offset += j + 1;
/* 733*/        writer.println((new StringBuilder()).append(offset).append(" same frame: ").append(j).toString());
            }

            public void sameLocals(int i, int j, int k, int l)
            {
/* 737*/        offset += j + 1;
/* 738*/        writer.println((new StringBuilder()).append(offset).append(" same locals: ").append(j).toString());
/* 739*/        printTypeInfo(k, l);
            }

            public void chopFrame(int i, int j, int k)
            {
/* 743*/        offset += j + 1;
/* 744*/        writer.println((new StringBuilder()).append(offset).append(" chop frame: ").append(j).append(",    ").append(k).append(" last locals").toString());
            }

            public void appendFrame(int i, int j, int ai[], int ai1[])
            {
/* 748*/        offset += j + 1;
/* 749*/        writer.println((new StringBuilder()).append(offset).append(" append frame: ").append(j).toString());
/* 750*/        for(i = 0; i < ai.length; i++)
/* 751*/            printTypeInfo(ai[i], ai1[i]);

            }

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
            {
/* 756*/        offset += j + 1;
/* 757*/        writer.println((new StringBuilder()).append(offset).append(" full frame: ").append(j).toString());
/* 758*/        writer.println("[locals]");
/* 759*/        for(i = 0; i < ai.length; i++)
/* 760*/            printTypeInfo(ai[i], ai1[i]);

/* 762*/        writer.println("[stack]");
/* 763*/        for(i = 0; i < ai2.length; i++)
/* 764*/            printTypeInfo(ai2[i], ai3[i]);

            }

            private void printTypeInfo(int i, int j)
            {
/* 768*/        String s = null;
/* 769*/        switch(i)
                {
/* 771*/        case 0: // '\0'
/* 771*/            s = "top";
                    break;

/* 774*/        case 1: // '\001'
/* 774*/            s = "integer";
                    break;

/* 777*/        case 2: // '\002'
/* 777*/            s = "float";
                    break;

/* 780*/        case 3: // '\003'
/* 780*/            s = "double";
                    break;

/* 783*/        case 4: // '\004'
/* 783*/            s = "long";
                    break;

/* 786*/        case 5: // '\005'
/* 786*/            s = "null";
                    break;

/* 789*/        case 6: // '\006'
/* 789*/            s = "this";
                    break;

/* 792*/        case 7: // '\007'
/* 792*/            s = (new StringBuilder("object (cpool_index ")).append(j).append(")").toString();
                    break;

/* 795*/        case 8: // '\b'
/* 795*/            s = (new StringBuilder("uninitialized (offset ")).append(j).append(")").toString();
                    break;
                }
/* 799*/        writer.print("    ");
/* 800*/        writer.println(s);
            }

            private PrintWriter writer;
            private int offset;

            (byte abyte0[], PrintWriter printwriter)
            {
/* 726*/        super(abyte0);
/* 727*/        writer = printwriter;
/* 728*/        offset = -1;
            }
}
