// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;
import javassist.CannotCompileException;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, BadBytecode, ByteArray, ConstPool

public class StackMap extends AttributeInfo
{
    public static class Writer
    {

                public byte[] toByteArray()
                {
/* 547*/            return output.toByteArray();
                }

                public StackMap toStackMap(ConstPool constpool)
                {
/* 554*/            return new StackMap(constpool, output.toByteArray());
                }

                public void writeVerifyTypeInfo(int i, int j)
                {
/* 563*/            output.write(i);
/* 564*/            if(i == 7 || i == 8)
/* 565*/                write16bit(j);
                }

                public void write16bit(int i)
                {
/* 572*/            output.write(i >>> 8 & 0xff);
/* 573*/            output.write(i & 0xff);
                }

                private ByteArrayOutputStream output;

                public Writer()
                {
/* 540*/            output = new ByteArrayOutputStream();
                }
    }

    static class Printer extends Walker
    {

                public void print()
                {
/* 517*/            int i = ByteArray.readU16bit(info, 0);
/* 518*/            writer.println((new StringBuilder()).append(i).append(" entries").toString());
/* 519*/            visit();
                }

                public int locals(int i, int j, int k)
                {
/* 523*/            writer.println((new StringBuilder("  * offset ")).append(j).toString());
/* 524*/            return super.locals(i, j, k);
                }

                private PrintWriter writer;

                public Printer(StackMap stackmap, PrintWriter printwriter)
                {
/* 512*/            super(stackmap);
/* 513*/            writer = printwriter;
                }
    }

    static class NewRemover extends SimpleCopy
    {

                public int stack(int i, int j, int k)
                {
/* 455*/            return stackTypeInfoArray(i, j, k);
                }

                private int stackTypeInfoArray(int i, int j, int k)
                {
/* 459*/            j = i;
/* 460*/            int l = 0;
/* 461*/            for(int i1 = 0; i1 < k; i1++)
                    {
                        byte byte0;
/* 462*/                if((byte0 = info[j]) == 7)
                        {
/* 464*/                    j += 3;
/* 464*/                    continue;
                        }
/* 465*/                if(byte0 == 8)
                        {
                            int k1;
/* 466*/                    if((k1 = ByteArray.readU16bit(info, j + 1)) == posOfNew)
/* 468*/                        l++;
/* 470*/                    j += 3;
                        } else
                        {
/* 473*/                    j++;
                        }
                    }

/* 476*/            writer.write16bit(k - l);
/* 477*/            for(int j1 = 0; j1 < k; j1++)
                    {
                        int l1;
/* 478*/                if((l1 = info[i]) == 7)
                        {
/* 480*/                    l1 = ByteArray.readU16bit(info, i + 1);
/* 481*/                    objectVariable(i, l1);
/* 482*/                    i += 3;
/* 483*/                    continue;
                        }
/* 484*/                if(l1 == 8)
                        {
/* 485*/                    if((l1 = ByteArray.readU16bit(info, i + 1)) != posOfNew)
/* 487*/                        uninitialized(i, l1);
/* 489*/                    i += 3;
                        } else
                        {
/* 492*/                    typeInfo(i, l1);
/* 493*/                    i++;
                        }
                    }

/* 497*/            return i;
                }

                int posOfNew;

                NewRemover(StackMap stackmap, int i)
                {
/* 450*/            super(stackmap);
/* 451*/            posOfNew = i;
                }
    }

    static class SwitchShifter extends Walker
    {

                public int locals(int i, int j, int k)
                {
/* 423*/            if(where == i + j)
/* 424*/                ByteArray.write16bit(j - gap, info, i - 4);
/* 425*/            else
/* 425*/            if(where == i)
/* 426*/                ByteArray.write16bit(j + gap, info, i - 4);
/* 428*/            return super.locals(i, j, k);
                }

                private int where;
                private int gap;

                public SwitchShifter(StackMap stackmap, int i, int j)
                {
/* 417*/            super(stackmap);
/* 418*/            where = i;
/* 419*/            gap = j;
                }
    }

    static class Shifter extends Walker
    {

                public int locals(int i, int j, int k)
                {
/* 394*/            if(exclusive ? where <= j : where < j)
/* 395*/                ByteArray.write16bit(j + gap, info, i - 4);
/* 397*/            return super.locals(i, j, k);
                }

                public void uninitialized(int i, int j)
                {
/* 401*/            if(where <= j)
/* 402*/                ByteArray.write16bit(j + gap, info, i + 1);
                }

                private int where;
                private int gap;
                private boolean exclusive;

                public Shifter(StackMap stackmap, int i, int j, boolean flag)
                {
/* 387*/            super(stackmap);
/* 388*/            where = i;
/* 389*/            gap = j;
/* 390*/            exclusive = flag;
                }
    }

    static class InsertLocal extends SimpleCopy
    {

                public int typeInfoArray(int i, int j, int k, boolean flag)
                {
/* 349*/            if(!flag || k < varIndex)
/* 350*/                return super.typeInfoArray(i, j, k, flag);
/* 352*/            writer.write16bit(k + 1);
/* 353*/            for(j = 0; j < k; j++)
                    {
/* 354*/                if(j == varIndex)
/* 355*/                    writeVarTypeInfo();
/* 357*/                i = typeInfoArray2(j, i);
                    }

/* 360*/            if(k == varIndex)
/* 361*/                writeVarTypeInfo();
/* 363*/            return i;
                }

                private void writeVarTypeInfo()
                {
/* 367*/            if(varTag == 7)
                    {
/* 368*/                writer.writeVerifyTypeInfo(7, varData);
/* 368*/                return;
                    }
/* 369*/            if(varTag == 8)
                    {
/* 370*/                writer.writeVerifyTypeInfo(8, varData);
/* 370*/                return;
                    } else
                    {
/* 372*/                writer.writeVerifyTypeInfo(varTag, 0);
/* 373*/                return;
                    }
                }

                private int varIndex;
                private int varTag;
                private int varData;

                InsertLocal(StackMap stackmap, int i, int j, int k)
                {
/* 342*/            super(stackmap);
/* 343*/            varIndex = i;
/* 344*/            varTag = j;
/* 345*/            varData = k;
                }
    }

    static class SimpleCopy extends Walker
    {

                byte[] doit()
                {
/* 304*/            visit();
/* 305*/            return writer.toByteArray();
                }

                public void visit()
                {
/* 309*/            int i = ByteArray.readU16bit(info, 0);
/* 310*/            writer.write16bit(i);
/* 311*/            super.visit();
                }

                public int locals(int i, int j, int k)
                {
/* 315*/            writer.write16bit(j);
/* 316*/            return super.locals(i, j, k);
                }

                public int typeInfoArray(int i, int j, int k, boolean flag)
                {
/* 320*/            writer.write16bit(k);
/* 321*/            return super.typeInfoArray(i, j, k, flag);
                }

                public void typeInfo(int i, byte byte0)
                {
/* 325*/            writer.writeVerifyTypeInfo(byte0, 0);
                }

                public void objectVariable(int i, int j)
                {
/* 329*/            writer.writeVerifyTypeInfo(7, j);
                }

                public void uninitialized(int i, int j)
                {
/* 333*/            writer.writeVerifyTypeInfo(8, j);
                }

                Writer writer;

                SimpleCopy(StackMap stackmap)
                {
/* 299*/            super(stackmap);
/* 300*/            writer = new Writer();
                }
    }

    static class Copier extends Walker
    {

                public void visit()
                {
                    int i;
/* 238*/            ByteArray.write16bit(i = ByteArray.readU16bit(info, 0), dest, 0);
/* 240*/            super.visit();
                }

                public int locals(int i, int j, int k)
                {
/* 244*/            ByteArray.write16bit(j, dest, i - 4);
/* 245*/            return super.locals(i, j, k);
                }

                public int typeInfoArray(int i, int j, int k, boolean flag)
                {
/* 249*/            ByteArray.write16bit(k, dest, i - 2);
/* 250*/            return super.typeInfoArray(i, j, k, flag);
                }

                public void typeInfo(int i, byte byte0)
                {
/* 254*/            dest[i] = byte0;
                }

                public void objectVariable(int i, int j)
                {
/* 258*/            dest[i] = 7;
/* 259*/            ByteArray.write16bit(j = srcCp.copy(j, destCp, classnames), dest, i + 1);
                }

                public void uninitialized(int i, int j)
                {
/* 264*/            dest[i] = 8;
/* 265*/            ByteArray.write16bit(j, dest, i + 1);
                }

                public StackMap getStackMap()
                {
/* 269*/            return new StackMap(destCp, dest);
                }

                byte dest[];
                ConstPool srcCp;
                ConstPool destCp;
                Map classnames;

                Copier(StackMap stackmap, ConstPool constpool, Map map)
                {
/* 230*/            super(stackmap);
/* 231*/            srcCp = stackmap.getConstPool();
/* 232*/            dest = new byte[info.length];
/* 233*/            destCp = constpool;
/* 234*/            classnames = map;
                }
    }

    public static class Walker
    {

                public void visit()
                {
/* 142*/            int i = ByteArray.readU16bit(info, 0);
/* 143*/            int j = 2;
/* 144*/            for(int k = 0; k < i; k++)
                    {
/* 145*/                int l = ByteArray.readU16bit(info, j);
/* 146*/                int i1 = ByteArray.readU16bit(info, j + 2);
/* 147*/                j = locals(j + 4, l, i1);
/* 148*/                i1 = ByteArray.readU16bit(info, j);
/* 149*/                j = stack(j + 2, l, i1);
                    }

                }

                public int locals(int i, int j, int k)
                {
/* 158*/            return typeInfoArray(i, j, k, true);
                }

                public int stack(int i, int j, int k)
                {
/* 166*/            return typeInfoArray(i, j, k, false);
                }

                public int typeInfoArray(int i, int j, int k, boolean flag)
                {
/* 178*/            for(j = 0; j < k; j++)
/* 179*/                i = typeInfoArray2(j, i);

/* 181*/            return i;
                }

                int typeInfoArray2(int i, int j)
                {
/* 185*/            if((i = info[j]) == 7)
                    {
/* 187*/                i = ByteArray.readU16bit(info, j + 1);
/* 188*/                objectVariable(j, i);
/* 189*/                j += 3;
                    } else
/* 191*/            if(i == 8)
                    {
/* 192*/                i = ByteArray.readU16bit(info, j + 1);
/* 193*/                uninitialized(j, i);
/* 194*/                j += 3;
                    } else
                    {
/* 197*/                typeInfo(j, i);
/* 198*/                j++;
                    }
/* 201*/            return j;
                }

                public void typeInfo(int i, byte byte0)
                {
                }

                public void objectVariable(int i, int j)
                {
                }

                public void uninitialized(int i, int j)
                {
                }

                byte info[];

                public Walker(StackMap stackmap)
                {
/* 135*/            info = stackmap.get();
                }
    }


            StackMap(ConstPool constpool, byte abyte0[])
            {
/*  55*/        super(constpool, "StackMap", abyte0);
            }

            StackMap(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  61*/        super(constpool, i, datainputstream);
            }

            public int numOfEntries()
            {
/*  68*/        return ByteArray.readU16bit(info, 0);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 120*/        (constpool = new Copier(this, constpool, map)).visit();
/* 122*/        return constpool.getStackMap();
            }

            public void insertLocal(int i, int j, int k)
                throws BadBytecode
            {
/* 291*/        i = (new InsertLocal(this, i, j, k)).doit();
/* 292*/        set(i);
            }

            void shiftPc(int i, int j, boolean flag)
                throws BadBytecode
            {
/* 379*/        (new Shifter(this, i, j, flag)).visit();
            }

            void shiftForSwitch(int i, int j)
                throws BadBytecode
            {
/* 410*/        (new SwitchShifter(this, i, j)).visit();
            }

            public void removeNew(int i)
                throws CannotCompileException
            {
/* 442*/        i = (new NewRemover(this, i)).doit();
/* 443*/        set(i);
            }

            public void print(PrintWriter printwriter)
            {
/* 505*/        (new Printer(this, printwriter)).print();
            }

            public static final String tag = "StackMap";
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
