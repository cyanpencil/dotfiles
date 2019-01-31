// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;
import javassist.CannotCompileException;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, BadBytecode, ConstPool, ByteArray

public class StackMapTable extends AttributeInfo
{
    static class NewRemover extends SimpleCopy
    {

                public void sameLocals(int i, int j, int k, int l)
                {
/*1017*/            if(k == 8 && l == posOfNew)
                    {
/*1018*/                super.sameFrame(i, j);
/*1018*/                return;
                    } else
                    {
/*1020*/                super.sameLocals(i, j, k, l);
/*1021*/                return;
                    }
                }

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                {
/*1025*/            int k = ai2.length - 1;
/*1026*/            int l = 0;
/*1026*/            do
                    {
/*1026*/                if(l >= k)
/*1027*/                    break;
/*1027*/                if(ai2[l] == 8 && ai3[l] == posOfNew && ai2[l + 1] == 8 && ai3[l + 1] == posOfNew)
                        {
/*1029*/                    int ai4[] = new int[++k - 2];
/*1031*/                    int ai5[] = new int[k - 2];
/*1032*/                    int i1 = 0;
/*1033*/                    for(int j1 = 0; j1 < k; j1++)
/*1034*/                        if(j1 == l)
                                {
/*1035*/                            j1++;
                                } else
                                {
/*1037*/                            ai4[i1] = ai2[j1];
/*1038*/                            ai5[i1++] = ai3[j1];
                                }

/*1041*/                    ai2 = ai4;
/*1042*/                    ai3 = ai5;
/*1043*/                    break;
                        }
/*1026*/                l++;
                    } while(true);
/*1046*/            super.fullFrame(i, j, ai, ai1, ai2, ai3);
                }

                int posOfNew;

                public NewRemover(byte abyte0[], int i)
                {
/*1012*/            super(abyte0);
/*1013*/            posOfNew = i;
                }
    }

    static class SwitchShifter extends Shifter
    {

                void update(int i, int j, int k, int l)
                {
/* 935*/            int i1 = position;
/* 936*/            position = i1 + j + (i1 != 0 ? 1 : 0);
/* 938*/            if(where == position)
/* 939*/                i1 = j - gap;
/* 940*/            else
/* 940*/            if(where == i1)
/* 941*/                i1 = j + gap;
/* 943*/            else
/* 943*/                return;
/* 945*/            if(j < 64)
/* 946*/                if(i1 < 64)
                        {
/* 947*/                    info[i] = (byte)(i1 + k);
/* 947*/                    return;
                        } else
                        {
/* 949*/                    (j = insertGap(info, i, 2))[i] = (byte)l;
/* 951*/                    ByteArray.write16bit(i1, j, i + 1);
/* 952*/                    updatedInfo = j;
/* 953*/                    return;
                        }
/* 955*/            if(i1 < 64)
                    {
/* 956*/                (j = deleteGap(info, i, 2))[i] = (byte)(i1 + k);
/* 958*/                updatedInfo = j;
/* 959*/                return;
                    } else
                    {
/* 961*/                ByteArray.write16bit(i1, info, i + 1);
/* 962*/                return;
                    }
                }

                static byte[] deleteGap(byte abyte0[], int i, int j)
                {
/* 965*/            i += j;
                    int k;
/* 966*/            byte abyte1[] = new byte[(k = abyte0.length) - j];
/* 968*/            for(int l = 0; l < k; l++)
/* 969*/                abyte1[l - (l >= i ? j : 0)] = abyte0[l];

/* 971*/            return abyte1;
                }

                void update(int i, int j)
                {
/* 975*/            int k = position;
/* 976*/            position = k + j + (k != 0 ? 1 : 0);
/* 978*/            if(where == position)
/* 979*/                j -= gap;
/* 980*/            else
/* 980*/            if(where == k)
/* 981*/                j += gap;
/* 983*/            else
/* 983*/                return;
/* 985*/            ByteArray.write16bit(j, info, i + 1);
                }

                SwitchShifter(StackMapTable stackmaptable, int i, int j)
                {
/* 931*/            super(stackmaptable, i, j, false);
                }
    }

    static class Shifter extends Walker
    {

                public void doit()
                    throws BadBytecode
                {
/* 845*/            parse();
/* 846*/            if(updatedInfo != null)
/* 847*/                stackMap.set(updatedInfo);
                }

                public void sameFrame(int i, int j)
                {
/* 851*/            update(i, j, 0, 251);
                }

                public void sameLocals(int i, int j, int k, int l)
                {
/* 855*/            update(i, j, 64, 247);
                }

                void update(int i, int j, int k, int l)
                {
/* 859*/            int i1 = position;
/* 860*/            position = i1 + j + (i1 != 0 ? 1 : 0);
/* 862*/            if(exclusive)
/* 863*/                i1 = i1 >= where || where > position ? 0 : 1;
/* 865*/            else
/* 865*/                i1 = i1 > where || where >= position ? 0 : 1;
/* 867*/            if(i1 != 0)
                    {
/* 868*/                int j1 = j + gap;
/* 869*/                position += gap;
/* 870*/                if(j1 < 64)
                        {
/* 871*/                    info[i] = (byte)(j1 + k);
/* 871*/                    return;
                        }
/* 872*/                if(j < 64)
                        {
/* 873*/                    (j = insertGap(info, i, 2))[i] = (byte)l;
/* 875*/                    ByteArray.write16bit(j1, j, i + 1);
/* 876*/                    updatedInfo = j;
/* 877*/                    return;
                        }
/* 879*/                ByteArray.write16bit(j1, info, i + 1);
                    }
                }

                static byte[] insertGap(byte abyte0[], int i, int j)
                {
                    int k;
/* 884*/            byte abyte1[] = new byte[(k = abyte0.length) + j];
/* 886*/            for(int l = 0; l < k; l++)
/* 887*/                abyte1[l + (l >= i ? j : 0)] = abyte0[l];

/* 889*/            return abyte1;
                }

                public void chopFrame(int i, int j, int k)
                {
/* 893*/            update(i, j);
                }

                public void appendFrame(int i, int j, int ai[], int ai1[])
                {
/* 897*/            update(i, j);
                }

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                {
/* 902*/            update(i, j);
                }

                void update(int i, int j)
                {
/* 906*/            int k = position;
/* 907*/            position = k + j + (k != 0 ? 1 : 0);
/* 909*/            if(exclusive)
/* 910*/                k = k >= where || where > position ? 0 : 1;
/* 912*/            else
/* 912*/                k = k > where || where >= position ? 0 : 1;
/* 914*/            if(k != 0)
                    {
/* 915*/                ByteArray.write16bit(j += gap, info, i + 1);
/* 917*/                position += gap;
                    }
                }

                private StackMapTable stackMap;
                int where;
                int gap;
                int position;
                byte updatedInfo[];
                boolean exclusive;

                public Shifter(StackMapTable stackmaptable, int i, int j, boolean flag)
                {
/* 835*/            super(stackmaptable);
/* 836*/            stackMap = stackmaptable;
/* 837*/            where = i;
/* 838*/            gap = j;
/* 839*/            position = 0;
/* 840*/            updatedInfo = null;
/* 841*/            exclusive = flag;
                }
    }

    static class OffsetShifter extends Walker
    {

                public void objectOrUninitialized(int i, int j, int k)
                {
/* 821*/            if(i == 8 && where <= j)
/* 823*/                ByteArray.write16bit(j + gap, info, k);
                }

                int where;
                int gap;

                public OffsetShifter(StackMapTable stackmaptable, int i, int j)
                {
/* 815*/            super(stackmaptable);
/* 816*/            where = i;
/* 817*/            gap = j;
                }
    }

    static class Printer extends Walker
    {

                public static void print(StackMapTable stackmaptable, PrintWriter printwriter)
                {
/* 718*/            try
                    {
/* 718*/                (new Printer(stackmaptable.get(), printwriter)).parse();
/* 722*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 720*/            catch(StackMapTable stackmaptable)
                    {
/* 721*/                printwriter.println(stackmaptable.getMessage());
                    }
                }

                public void sameFrame(int i, int j)
                {
/* 732*/            offset += j + 1;
/* 733*/            writer.println((new StringBuilder()).append(offset).append(" same frame: ").append(j).toString());
                }

                public void sameLocals(int i, int j, int k, int l)
                {
/* 737*/            offset += j + 1;
/* 738*/            writer.println((new StringBuilder()).append(offset).append(" same locals: ").append(j).toString());
/* 739*/            printTypeInfo(k, l);
                }

                public void chopFrame(int i, int j, int k)
                {
/* 743*/            offset += j + 1;
/* 744*/            writer.println((new StringBuilder()).append(offset).append(" chop frame: ").append(j).append(",    ").append(k).append(" last locals").toString());
                }

                public void appendFrame(int i, int j, int ai[], int ai1[])
                {
/* 748*/            offset += j + 1;
/* 749*/            writer.println((new StringBuilder()).append(offset).append(" append frame: ").append(j).toString());
/* 750*/            for(i = 0; i < ai.length; i++)
/* 751*/                printTypeInfo(ai[i], ai1[i]);

                }

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                {
/* 756*/            offset += j + 1;
/* 757*/            writer.println((new StringBuilder()).append(offset).append(" full frame: ").append(j).toString());
/* 758*/            writer.println("[locals]");
/* 759*/            for(i = 0; i < ai.length; i++)
/* 760*/                printTypeInfo(ai[i], ai1[i]);

/* 762*/            writer.println("[stack]");
/* 763*/            for(i = 0; i < ai2.length; i++)
/* 764*/                printTypeInfo(ai2[i], ai3[i]);

                }

                private void printTypeInfo(int i, int j)
                {
/* 768*/            String s = null;
/* 769*/            switch(i)
                    {
/* 771*/            case 0: // '\0'
/* 771*/                s = "top";
                        break;

/* 774*/            case 1: // '\001'
/* 774*/                s = "integer";
                        break;

/* 777*/            case 2: // '\002'
/* 777*/                s = "float";
                        break;

/* 780*/            case 3: // '\003'
/* 780*/                s = "double";
                        break;

/* 783*/            case 4: // '\004'
/* 783*/                s = "long";
                        break;

/* 786*/            case 5: // '\005'
/* 786*/                s = "null";
                        break;

/* 789*/            case 6: // '\006'
/* 789*/                s = "this";
                        break;

/* 792*/            case 7: // '\007'
/* 792*/                s = (new StringBuilder("object (cpool_index ")).append(j).append(")").toString();
                        break;

/* 795*/            case 8: // '\b'
/* 795*/                s = (new StringBuilder("uninitialized (offset ")).append(j).append(")").toString();
                        break;
                    }
/* 799*/            writer.print("    ");
/* 800*/            writer.println(s);
                }

                private PrintWriter writer;
                private int offset;

                Printer(byte abyte0[], PrintWriter printwriter)
                {
/* 726*/            super(abyte0);
/* 727*/            writer = printwriter;
/* 728*/            offset = -1;
                }
    }

    public static class Writer
    {

                public byte[] toByteArray()
                {
/* 561*/            byte abyte0[] = output.toByteArray();
/* 562*/            ByteArray.write16bit(numOfEntries, abyte0, 0);
/* 563*/            return abyte0;
                }

                public StackMapTable toStackMapTable(ConstPool constpool)
                {
/* 574*/            return new StackMapTable(constpool, toByteArray());
                }

                public void sameFrame(int i)
                {
/* 581*/            numOfEntries++;
/* 582*/            if(i < 64)
                    {
/* 583*/                output.write(i);
/* 583*/                return;
                    } else
                    {
/* 585*/                output.write(251);
/* 586*/                write16(i);
/* 588*/                return;
                    }
                }

                public void sameLocals(int i, int j, int k)
                {
/* 602*/            numOfEntries++;
/* 603*/            if(i < 64)
                    {
/* 604*/                output.write(i + 64);
                    } else
                    {
/* 606*/                output.write(247);
/* 607*/                write16(i);
                    }
/* 610*/            writeTypeInfo(j, k);
                }

                public void chopFrame(int i, int j)
                {
/* 619*/            numOfEntries++;
/* 620*/            output.write(251 - j);
/* 621*/            write16(i);
                }

                public void appendFrame(int i, int ai[], int ai1[])
                {
/* 638*/            numOfEntries++;
/* 639*/            int j = ai.length;
/* 640*/            output.write(j + 251);
/* 641*/            write16(i);
/* 642*/            for(i = 0; i < j; i++)
/* 643*/                writeTypeInfo(ai[i], ai1[i]);

                }

                public void fullFrame(int i, int ai[], int ai1[], int ai2[], int ai3[])
                {
/* 667*/            numOfEntries++;
/* 668*/            output.write(255);
/* 669*/            write16(i);
/* 670*/            i = ai.length;
/* 671*/            write16(i);
/* 672*/            for(int j = 0; j < i; j++)
/* 673*/                writeTypeInfo(ai[j], ai1[j]);

/* 675*/            i = ai2.length;
/* 676*/            write16(i);
/* 677*/            for(int k = 0; k < i; k++)
/* 678*/                writeTypeInfo(ai2[k], ai3[k]);

                }

                private void writeTypeInfo(int i, int j)
                {
/* 682*/            output.write(i);
/* 683*/            if(i == 7 || i == 8)
/* 684*/                write16(j);
                }

                private void write16(int i)
                {
/* 688*/            output.write(i >>> 8 & 0xff);
/* 689*/            output.write(i & 0xff);
                }

                ByteArrayOutputStream output;
                int numOfEntries;

                public Writer(int i)
                {
/* 551*/            output = new ByteArrayOutputStream(i);
/* 552*/            numOfEntries = 0;
/* 553*/            output.write(0);
/* 554*/            output.write(0);
                }
    }

    static class InsertLocal extends SimpleCopy
    {

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                {
                    int k;
/* 509*/            if((k = ai.length) < varIndex)
                    {
/* 511*/                super.fullFrame(i, j, ai, ai1, ai2, ai3);
/* 512*/                return;
                    }
/* 515*/            byte byte0 = ((byte)(varTag != 4 && varTag != 3 ? 1 : 2));
/* 516*/            int ai4[] = new int[k + byte0];
/* 517*/            int ai5[] = new int[k + byte0];
/* 518*/            int l = varIndex;
/* 519*/            int i1 = 0;
/* 520*/            for(int j1 = 0; j1 < k; j1++)
                    {
/* 521*/                if(i1 == l)
/* 522*/                    i1 += byte0;
/* 524*/                ai4[i1] = ai[j1];
/* 525*/                ai5[i1++] = ai1[j1];
                    }

/* 528*/            ai4[l] = varTag;
/* 529*/            ai5[l] = varData;
/* 530*/            if(byte0 > 1)
                    {
/* 531*/                ai4[l + 1] = 0;
/* 532*/                ai5[l + 1] = 0;
                    }
/* 535*/            super.fullFrame(i, j, ai4, ai5, ai2, ai3);
                }

                private int varIndex;
                private int varTag;
                private int varData;

                public InsertLocal(byte abyte0[], int i, int j, int k)
                {
/* 501*/            super(abyte0);
/* 502*/            varIndex = i;
/* 503*/            varTag = j;
/* 504*/            varData = k;
                }
    }

    static class Copier extends SimpleCopy
    {

                protected int copyData(int i, int j)
                {
/* 426*/            if(i == 7)
/* 427*/                return srcPool.copy(j, destPool, classnames);
/* 429*/            else
/* 429*/                return j;
                }

                protected int[] copyData(int ai[], int ai1[])
                {
/* 433*/            int ai2[] = new int[ai1.length];
/* 434*/            for(int i = 0; i < ai1.length; i++)
/* 435*/                if(ai[i] == 7)
/* 436*/                    ai2[i] = srcPool.copy(ai1[i], destPool, classnames);
/* 438*/                else
/* 438*/                    ai2[i] = ai1[i];

/* 440*/            return ai2;
                }

                private ConstPool srcPool;
                private ConstPool destPool;
                private Map classnames;

                public Copier(ConstPool constpool, byte abyte0[], ConstPool constpool1, Map map)
                {
/* 419*/            super(abyte0);
/* 420*/            srcPool = constpool;
/* 421*/            destPool = constpool1;
/* 422*/            classnames = map;
                }
    }

    static class SimpleCopy extends Walker
    {

                public byte[] doit()
                    throws BadBytecode
                {
/* 379*/            parse();
/* 380*/            return writer.toByteArray();
                }

                public void sameFrame(int i, int j)
                {
/* 384*/            writer.sameFrame(j);
                }

                public void sameLocals(int i, int j, int k, int l)
                {
/* 388*/            writer.sameLocals(j, k, copyData(k, l));
                }

                public void chopFrame(int i, int j, int k)
                {
/* 392*/            writer.chopFrame(j, k);
                }

                public void appendFrame(int i, int j, int ai[], int ai1[])
                {
/* 396*/            writer.appendFrame(j, ai, copyData(ai, ai1));
                }

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                {
/* 401*/            writer.fullFrame(j, ai, copyData(ai, ai1), ai2, copyData(ai2, ai3));
                }

                protected int copyData(int i, int j)
                {
/* 406*/            return j;
                }

                protected int[] copyData(int ai[], int ai1[])
                {
/* 410*/            return ai1;
                }

                private Writer writer;

                public SimpleCopy(byte abyte0[])
                {
/* 374*/            super(abyte0);
/* 375*/            writer = new Writer(abyte0.length);
                }
    }

    public static class Walker
    {

                public final int size()
                {
/* 172*/            return numOfEntries;
                }

                public void parse()
                    throws BadBytecode
                {
/* 178*/            int i = numOfEntries;
/* 179*/            int j = 2;
/* 180*/            for(int k = 0; k < i; k++)
/* 181*/                j = stackMapFrames(j, k);

                }

                int stackMapFrames(int i, int j)
                    throws BadBytecode
                {
/* 194*/            if((j = info[i] & 0xff) < 64)
                    {
/* 196*/                sameFrame(i, j);
/* 197*/                i++;
                    } else
/* 199*/            if(j < 128)
                    {
/* 200*/                i = sameLocals(i, j);
                    } else
                    {
/* 201*/                if(j < 247)
/* 202*/                    throw new BadBytecode("bad frame_type in StackMapTable");
/* 203*/                if(j == 247)
/* 204*/                    i = sameLocals(i, j);
/* 205*/                else
/* 205*/                if(j < 251)
                        {
/* 206*/                    int k = ByteArray.readU16bit(info, i + 1);
/* 207*/                    chopFrame(i, k, 251 - j);
/* 208*/                    i += 3;
                        } else
/* 210*/                if(j == 251)
                        {
/* 211*/                    int l = ByteArray.readU16bit(info, i + 1);
/* 212*/                    sameFrame(i, l);
/* 213*/                    i += 3;
                        } else
/* 215*/                if(j < 255)
/* 216*/                    i = appendFrame(i, j);
/* 218*/                else
/* 218*/                    i = fullFrame(i);
                    }
/* 220*/            return i;
                }

                public void sameFrame(int i, int j)
                    throws BadBytecode
                {
                }

                private int sameLocals(int i, int j)
                    throws BadBytecode
                {
/* 234*/            int k = i;
/* 236*/            if(j < 128)
                    {
/* 237*/                j -= 64;
                    } else
                    {
/* 239*/                j = ByteArray.readU16bit(info, i + 1);
/* 240*/                i += 2;
                    }
/* 243*/            int l = info[i + 1] & 0xff;
/* 244*/            int i1 = 0;
/* 245*/            if(l == 7 || l == 8)
                    {
/* 246*/                i1 = ByteArray.readU16bit(info, i + 2);
/* 247*/                objectOrUninitialized(l, i1, i + 2);
/* 248*/                i += 2;
                    }
/* 251*/            sameLocals(k, j, l, i1);
/* 252*/            return i + 2;
                }

                public void sameLocals(int i, int j, int k, int l)
                    throws BadBytecode
                {
                }

                public void chopFrame(int i, int j, int k)
                    throws BadBytecode
                {
                }

                private int appendFrame(int i, int j)
                    throws BadBytecode
                {
/* 280*/            j -= 251;
/* 281*/            int k = ByteArray.readU16bit(info, i + 1);
/* 282*/            int ai[] = new int[j];
/* 283*/            int ai1[] = new int[j];
/* 284*/            int l = i + 3;
/* 285*/            for(int i1 = 0; i1 < j; i1++)
                    {
/* 286*/                int j1 = info[l] & 0xff;
/* 287*/                ai[i1] = j1;
/* 288*/                if(j1 == 7 || j1 == 8)
                        {
/* 289*/                    ai1[i1] = ByteArray.readU16bit(info, l + 1);
/* 290*/                    objectOrUninitialized(j1, ai1[i1], l + 1);
/* 291*/                    l += 3;
                        } else
                        {
/* 294*/                    ai1[i1] = 0;
/* 295*/                    l++;
                        }
                    }

/* 299*/            appendFrame(i, k, ai, ai1);
/* 300*/            return l;
                }

                public void appendFrame(int i, int j, int ai[], int ai1[])
                    throws BadBytecode
                {
                }

                private int fullFrame(int i)
                    throws BadBytecode
                {
/* 316*/            int j = ByteArray.readU16bit(info, i + 1);
                    int k;
/* 317*/            int ai[] = new int[k = ByteArray.readU16bit(info, i + 3)];
/* 319*/            int ai1[] = new int[k];
/* 320*/            k = verifyTypeInfo(i + 5, k, ai, ai1);
                    int l;
/* 321*/            int ai2[] = new int[l = ByteArray.readU16bit(info, k)];
/* 323*/            int ai3[] = new int[l];
/* 324*/            k = verifyTypeInfo(k + 2, l, ai2, ai3);
/* 325*/            fullFrame(i, j, ai, ai1, ai2, ai3);
/* 326*/            return k;
                }

                public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                    throws BadBytecode
                {
                }

                private int verifyTypeInfo(int i, int j, int ai[], int ai1[])
                {
/* 346*/            for(int k = 0; k < j; k++)
                    {
/* 347*/                int l = info[i++] & 0xff;
/* 348*/                ai[k] = l;
/* 349*/                if(l == 7 || l == 8)
                        {
/* 350*/                    ai1[k] = ByteArray.readU16bit(info, i);
/* 351*/                    objectOrUninitialized(l, ai1[k], i);
/* 352*/                    i += 2;
                        }
                    }

/* 356*/            return i;
                }

                public void objectOrUninitialized(int i, int j, int k)
                {
                }

                byte info[];
                int numOfEntries;

                public Walker(StackMapTable stackmaptable)
                {
/* 153*/            this(stackmaptable.get());
                }

                public Walker(byte abyte0[])
                {
/* 165*/            info = abyte0;
/* 166*/            numOfEntries = ByteArray.readU16bit(abyte0, 0);
                }
    }

    public static class RuntimeCopyException extends RuntimeException
    {

                public RuntimeCopyException(String s)
                {
/*  86*/            super(s);
                }
    }


            StackMapTable(ConstPool constpool, byte abyte0[])
            {
/*  47*/        super(constpool, "StackMapTable", abyte0);
            }

            StackMapTable(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  53*/        super(constpool, i, datainputstream);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
                throws RuntimeCopyException
            {
/*  69*/        return new StackMapTable(constpool, (new Copier(constPool, info, constpool, map)).doit());
/*  72*/        JVM INSTR pop ;
/*  73*/        throw new RuntimeCopyException("bad bytecode. fatal?");
            }

            void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*  91*/        super.write(dataoutputstream);
            }

            public void insertLocal(int i, int j, int k)
                throws BadBytecode
            {
/* 461*/        i = (new InsertLocal(get(), i, j, k)).doit();
/* 462*/        set(i);
            }

            public static int typeTagOf(char c)
            {
/* 475*/        switch(c)
                {
/* 477*/        case 68: // 'D'
/* 477*/            return 3;

/* 479*/        case 70: // 'F'
/* 479*/            return 2;

/* 481*/        case 74: // 'J'
/* 481*/            return 4;

/* 484*/        case 76: // 'L'
/* 484*/        case 91: // '['
/* 484*/            return 7;
                }
/* 487*/        return 1;
            }

            public void println(PrintWriter printwriter)
            {
/* 697*/        Printer.print(this, printwriter);
            }

            public void println(PrintStream printstream)
            {
/* 706*/        Printer.print(this, new PrintWriter(printstream, true));
            }

            void shiftPc(int i, int j, boolean flag)
                throws BadBytecode
            {
/* 807*/        (new OffsetShifter(this, i, j)).parse();
/* 808*/        (new Shifter(this, i, j, flag)).doit();
            }

            void shiftForSwitch(int i, int j)
                throws BadBytecode
            {
/* 926*/        (new SwitchShifter(this, i, j)).doit();
            }

            public void removeNew(int i)
                throws CannotCompileException
            {
/*1000*/        try
                {
/*1000*/            i = (new NewRemover(get(), i)).doit();
/*1001*/            set(i);
/*1005*/            return;
                }
                // Misplaced declaration of an exception variable
/*1003*/        catch(int i)
                {
/*1004*/            throw new CannotCompileException("bad stack map table", i);
                }
            }

            public static final String tag = "StackMapTable";
            public static final int TOP = 0;
            public static final int INTEGER = 1;
            public static final int FLOAT = 2;
            public static final int DOUBLE = 3;
            public static final int LONG = 4;
            public static final int NULL = 5;
            public static final int THIS = 6;
            public static final int OBJECT = 7;
            public static final int UNINIT = 8;
}
