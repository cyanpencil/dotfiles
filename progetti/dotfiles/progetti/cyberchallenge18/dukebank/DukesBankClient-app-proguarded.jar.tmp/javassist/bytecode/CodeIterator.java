// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;

import java.util.ArrayList;

// Referenced classes of package javassist.bytecode:
//            BadBytecode, ByteArray, CodeAttribute, ConstPool, 
//            ExceptionTable, LineNumberAttribute, LocalVariableAttribute, Opcode, 
//            StackMap, StackMapTable

public class CodeIterator
    implements Opcode
{
    static class Lookup extends Switcher
    {

                int write2(int i, byte abyte0[])
                {
                    int j;
/*1585*/            ByteArray.write32bit(j = matches.length, abyte0, i);
/*1587*/            i += 4;
/*1588*/            for(int k = 0; k < j; k++)
                    {
/*1589*/                ByteArray.write32bit(matches[k], abyte0, i);
/*1590*/                ByteArray.write32bit(offsets[k], abyte0, i + 4);
/*1591*/                i += 8;
                    }

/*1594*/            return 4 + j * 8;
                }

                int tableSize()
                {
/*1597*/            return 4 + 8 * matches.length;
                }

                int matches[];

                Lookup(int i, int j, int ai[], int ai1[], Pointers pointers)
                {
/*1580*/            super(i, j, ai1, pointers);
/*1581*/            matches = ai;
                }
    }

    static class Table extends Switcher
    {

                int write2(int i, byte abyte0[])
                {
/*1561*/            ByteArray.write32bit(low, abyte0, i);
/*1562*/            ByteArray.write32bit(high, abyte0, i + 4);
/*1563*/            int j = offsets.length;
/*1564*/            i += 8;
/*1565*/            for(int k = 0; k < j; k++)
                    {
/*1566*/                ByteArray.write32bit(offsets[k], abyte0, i);
/*1567*/                i += 4;
                    }

/*1570*/            return 8 + 4 * j;
                }

                int tableSize()
                {
/*1573*/            return 8 + 4 * offsets.length;
                }

                int low;
                int high;

                Table(int i, int j, int k, int l, int ai[], Pointers pointers)
                {
/*1555*/            super(i, j, ai, pointers);
/*1556*/            low = k;
/*1557*/            high = l;
                }
    }

    static abstract class Switcher extends Branch
    {

                void shift(int i, int j, boolean flag)
                {
/*1484*/            int k = pos;
/*1485*/            defaultByte = shiftOffset(k, defaultByte, i, j, flag);
/*1486*/            int l = offsets.length;
/*1487*/            for(int i1 = 0; i1 < l; i1++)
/*1488*/                offsets[i1] = shiftOffset(k, offsets[i1], i, j, flag);

/*1490*/            super.shift(i, j, flag);
                }

                int gapChanged()
                {
                    int i;
/*1494*/            if((i = 3 - (pos & 3)) > gap)
                    {
/*1496*/                int j = i - gap;
/*1497*/                gap = i;
/*1498*/                return j;
                    } else
                    {
/*1501*/                return 0;
                    }
                }

                int deltaSize()
                {
/*1505*/            return gap - (3 - (orgPos & 3));
                }

                int write(int i, byte abyte0[], int j, byte abyte1[])
                    throws BadBytecode
                {
/*1509*/            int k = 3 - (pos & 3);
/*1510*/            int l = gap - k;
/*1511*/            int i1 = 5 + (3 - (orgPos & 3)) + tableSize();
/*1512*/            if(l > 0)
/*1513*/                adjustOffsets(i1, l);
/*1515*/            for(abyte1[j++] = abyte0[i]; k-- > 0; abyte1[j++] = 0);
/*1519*/            ByteArray.write32bit(defaultByte, abyte1, j);
/*1520*/            i = write2(j + 4, abyte1);
/*1521*/            j += i + 4;
/*1522*/            while(l-- > 0) 
/*1523*/                abyte1[j++] = 0;
/*1525*/            return 5 + (3 - (orgPos & 3)) + i;
                }

                abstract int write2(int i, byte abyte0[]);

                abstract int tableSize();

                void adjustOffsets(int i, int j)
                    throws BadBytecode
                {
/*1541*/            pointers.shiftForSwitch(pos + i, j);
/*1542*/            if(defaultByte == i)
/*1543*/                defaultByte -= j;
/*1545*/            for(int k = 0; k < offsets.length; k++)
/*1546*/                if(offsets[k] == i)
/*1547*/                    offsets[k] -= j;

                }

                int gap;
                int defaultByte;
                int offsets[];
                Pointers pointers;

                Switcher(int i, int j, int ai[], Pointers pointers1)
                {
/*1476*/            super(i);
/*1477*/            gap = 3 - (i & 3);
/*1478*/            defaultByte = j;
/*1479*/            offsets = ai;
/*1480*/            pointers = pointers1;
                }
    }

    static class Jump32 extends Branch
    {

                void shift(int i, int j, boolean flag)
                {
/*1459*/            offset = shiftOffset(pos, offset, i, j, flag);
/*1460*/            super.shift(i, j, flag);
                }

                int write(int i, byte abyte0[], int j, byte abyte1[])
                {
/*1464*/            abyte1[j] = abyte0[i];
/*1465*/            ByteArray.write32bit(offset, abyte1, j + 1);
/*1466*/            return 5;
                }

                int offset;

                Jump32(int i, int j)
                {
/*1454*/            super(i);
/*1455*/            offset = j;
                }
    }

    static class If16 extends Branch16
    {

                int deltaSize()
                {
/*1425*/            return state != 2 ? 0 : 5;
                }

                void write32(int i, byte abyte0[], int j, byte abyte1[])
                {
/*1429*/            abyte1[j] = (byte)opcode(abyte0[i] & 0xff);
/*1430*/            abyte1[j + 1] = 0;
/*1431*/            abyte1[j + 2] = 8;
/*1432*/            abyte1[j + 3] = -56;
/*1433*/            ByteArray.write32bit(offset - 3, abyte1, j + 4);
                }

                int opcode(int i)
                {
/*1437*/            if(i == 198)
/*1438*/                return 199;
/*1439*/            if(i == 199)
/*1440*/                return 198;
/*1442*/            if((i - 153 & 1) == 0)
/*1443*/                return i + 1;
/*1445*/            else
/*1445*/                return i - 1;
                }

                If16(int i, int j)
                {
/*1421*/            super(i, j);
                }
    }

    static class Jump16 extends Branch16
    {

                int deltaSize()
                {
/*1409*/            return state != 2 ? 0 : 2;
                }

                void write32(int i, byte abyte0[], int j, byte abyte1[])
                {
/*1413*/            abyte1[j] = (byte)((abyte0[i] & 0xff) != 167 ? '\311' : 200);
/*1414*/            ByteArray.write32bit(offset, abyte1, j + 1);
                }

                Jump16(int i, int j)
                {
/*1405*/            super(i, j);
                }
    }

    static abstract class Branch16 extends Branch
    {

                void shift(int i, int j, boolean flag)
                {
/*1371*/            offset = shiftOffset(pos, offset, i, j, flag);
/*1372*/            super.shift(i, j, flag);
/*1373*/            if(state == 0 && (offset < -32768 || 32767 < offset))
/*1375*/                state = 1;
                }

                boolean expanded()
                {
/*1379*/            if(state == 1)
                    {
/*1380*/                state = 2;
/*1381*/                return true;
                    } else
                    {
/*1384*/                return false;
                    }
                }

                abstract int deltaSize();

                abstract void write32(int i, byte abyte0[], int j, byte abyte1[]);

                int write(int i, byte abyte0[], int j, byte abyte1[])
                {
/*1391*/            if(state == 2)
                    {
/*1392*/                write32(i, abyte0, j, abyte1);
                    } else
                    {
/*1394*/                abyte1[j] = abyte0[i];
/*1395*/                ByteArray.write16bit(offset, abyte1, j + 1);
                    }
/*1398*/            return 3;
                }

                int offset;
                int state;
                static final int BIT16 = 0;
                static final int EXPAND = 1;
                static final int BIT32 = 2;

                Branch16(int i, int j)
                {
/*1365*/            super(i);
/*1366*/            offset = j;
/*1367*/            state = 0;
                }
    }

    static class LdcW extends Branch
    {

                boolean expanded()
                {
/*1340*/            if(state)
                    {
/*1341*/                state = false;
/*1342*/                return true;
                    } else
                    {
/*1345*/                return false;
                    }
                }

                int deltaSize()
                {
/*1348*/            return 1;
                }

                int write(int i, byte abyte0[], int j, byte abyte1[])
                {
/*1351*/            abyte1[j] = 19;
/*1352*/            ByteArray.write16bit(index, abyte1, j + 1);
/*1353*/            return 2;
                }

                int index;
                boolean state;

                LdcW(int i, int j)
                {
/*1334*/            super(i);
/*1335*/            index = j;
/*1336*/            state = true;
                }
    }

    static abstract class Branch
    {

                void shift(int i, int j, boolean flag)
                {
/*1294*/            if(i < pos || i == pos && flag)
/*1295*/                pos += j;
                }

                static int shiftOffset(int i, int j, int k, int l, boolean flag)
                {
/*1300*/label0:
                    {
/*1300*/                int i1 = i + j;
/*1301*/                if(i < k)
                        {
/*1302*/                    if(k < i1 || flag && k == i1)
/*1303*/                        j += l;
/*1303*/                    break label0;
                        }
/*1305*/                if(i == k)
                        {
/*1308*/                    if(i1 >= k || !flag)
                            {
/*1310*/                        if(k < i1 && !flag)
/*1311*/                            j += l;
/*1311*/                        break label0;
                            }
                        } else
/*1314*/                if(i1 >= k && (flag || k != i1))
/*1315*/                    break label0;
/*1315*/                j -= l;
                    }
/*1317*/            return j;
                }

                boolean expanded()
                {
/*1320*/            return false;
                }

                int gapChanged()
                {
/*1321*/            return 0;
                }

                int deltaSize()
                {
/*1322*/            return 0;
                }

                abstract int write(int i, byte abyte0[], int j, byte abyte1[])
                    throws BadBytecode;

                int pos;
                int orgPos;

                Branch(int i)
                {
/*1292*/            pos = orgPos = i;
                }
    }

    static class Pointers
    {

                void shiftPc(int i, int j, boolean flag)
                    throws BadBytecode
                {
/*1030*/            if(i < cursor || i == cursor && flag)
/*1031*/                cursor += j;
/*1033*/            if(i < mark || i == mark && flag)
/*1034*/                mark += j;
/*1036*/            if(i < mark0 || i == mark0 && flag)
/*1037*/                mark0 += j;
/*1039*/            etable.shiftPc(i, j, flag);
/*1040*/            if(line != null)
/*1041*/                line.shiftPc(i, j, flag);
/*1043*/            if(vars != null)
/*1044*/                vars.shiftPc(i, j, flag);
/*1046*/            if(types != null)
/*1047*/                types.shiftPc(i, j, flag);
/*1049*/            if(stack != null)
/*1050*/                stack.shiftPc(i, j, flag);
/*1052*/            if(stack2 != null)
/*1053*/                stack2.shiftPc(i, j, flag);
                }

                void shiftForSwitch(int i, int j)
                    throws BadBytecode
                {
/*1057*/            if(stack != null)
/*1058*/                stack.shiftForSwitch(i, j);
/*1060*/            if(stack2 != null)
/*1061*/                stack2.shiftForSwitch(i, j);
                }

                int cursor;
                int mark0;
                int mark;
                ExceptionTable etable;
                LineNumberAttribute line;
                LocalVariableAttribute vars;
                LocalVariableAttribute types;
                StackMapTable stack;
                StackMap stack2;

                Pointers(int i, int j, int k, ExceptionTable exceptiontable, CodeAttribute codeattribute)
                {
/*1018*/            cursor = i;
/*1019*/            mark = j;
/*1020*/            mark0 = k;
/*1021*/            etable = exceptiontable;
/*1022*/            line = (LineNumberAttribute)codeattribute.getAttribute("LineNumberTable");
/*1023*/            vars = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable");
/*1024*/            types = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTypeTable");
/*1025*/            stack = (StackMapTable)codeattribute.getAttribute("StackMapTable");
/*1026*/            stack2 = (StackMap)codeattribute.getAttribute("StackMap");
                }
    }

    static class AlignmentException extends Exception
    {

                AlignmentException()
                {
                }
    }

    public static class Gap
    {

                public int position;
                public int length;

                public Gap()
                {
                }
    }


            protected CodeIterator(CodeAttribute codeattribute)
            {
/*  57*/        codeAttr = codeattribute;
/*  58*/        bytecode = codeattribute.getCode();
/*  59*/        begin();
            }

            public void begin()
            {
/*  66*/        currentPos = mark = 0;
/*  67*/        endPos = getCodeLength();
            }

            public void move(int i)
            {
/*  83*/        currentPos = i;
            }

            public void setMark(int i)
            {
/*  97*/        mark = i;
            }

            public int getMark()
            {
/* 108*/        return mark;
            }

            public CodeAttribute get()
            {
/* 114*/        return codeAttr;
            }

            public int getCodeLength()
            {
/* 121*/        return bytecode.length;
            }

            public int byteAt(int i)
            {
/* 127*/        return bytecode[i] & 0xff;
            }

            public void writeByte(int i, int j)
            {
/* 133*/        bytecode[j] = (byte)i;
            }

            public int u16bitAt(int i)
            {
/* 140*/        return ByteArray.readU16bit(bytecode, i);
            }

            public int s16bitAt(int i)
            {
/* 147*/        return ByteArray.readS16bit(bytecode, i);
            }

            public void write16bit(int i, int j)
            {
/* 154*/        ByteArray.write16bit(i, bytecode, j);
            }

            public int s32bitAt(int i)
            {
/* 161*/        return ByteArray.read32bit(bytecode, i);
            }

            public void write32bit(int i, int j)
            {
/* 168*/        ByteArray.write32bit(i, bytecode, j);
            }

            public void write(byte abyte0[], int i)
            {
/* 177*/        int j = abyte0.length;
/* 178*/        for(int k = 0; k < j; k++)
/* 179*/            bytecode[i++] = abyte0[k];

            }

            public boolean hasNext()
            {
/* 185*/        return currentPos < endPos;
            }

            public int next()
                throws BadBytecode
            {
/* 198*/        int i = currentPos;
/* 199*/        currentPos = nextOpcode(bytecode, i);
/* 200*/        return i;
            }

            public int lookAhead()
            {
/* 212*/        return currentPos;
            }

            public int skipConstructor()
                throws BadBytecode
            {
/* 234*/        return skipSuperConstructor0(-1);
            }

            public int skipSuperConstructor()
                throws BadBytecode
            {
/* 256*/        return skipSuperConstructor0(0);
            }

            public int skipThisConstructor()
                throws BadBytecode
            {
/* 278*/        return skipSuperConstructor0(1);
            }

            private int skipSuperConstructor0(int i)
                throws BadBytecode
            {
/* 284*/        begin();
/* 285*/        Object obj = codeAttr.getConstPool();
/* 286*/        String s = codeAttr.getDeclaringClass();
/* 287*/label0:
/* 287*/        do
                {
                    int j;
                    int k;
                    int l;
/* 287*/label1:
/* 287*/            do
                    {
/* 287*/                for(j = 0; hasNext(); j++)
                        {
/* 289*/                    k = next();
/* 290*/                    if((l = byteAt(k)) != 187)
/* 292*/                        continue label1;
                        }

/* 292*/                break label0;
                    } while(l != 183);
/* 294*/            l = ByteArray.readU16bit(bytecode, k + 1);
/* 295*/            if(!((ConstPool) (obj)).getMethodrefName(l).equals("<init>") || --j >= 0)
/* 297*/                continue;
/* 297*/            if(i < 0)
/* 298*/                return k;
/* 300*/            if(((String) (obj = ((ConstPool) (obj)).getMethodrefClassName(l))).equals(s) == (i > 0))
/* 302*/                return k;
/* 307*/            break;
                } while(true);
/* 309*/        begin();
/* 310*/        return -1;
            }

            public int insert(byte abyte0[])
                throws BadBytecode
            {
/* 334*/        return insert0(currentPos, abyte0, false);
            }

            public void insert(int i, byte abyte0[])
                throws BadBytecode
            {
/* 359*/        insert0(i, abyte0, false);
            }

            public int insertAt(int i, byte abyte0[])
                throws BadBytecode
            {
/* 383*/        return insert0(i, abyte0, false);
            }

            public int insertEx(byte abyte0[])
                throws BadBytecode
            {
/* 407*/        return insert0(currentPos, abyte0, true);
            }

            public void insertEx(int i, byte abyte0[])
                throws BadBytecode
            {
/* 432*/        insert0(i, abyte0, true);
            }

            public int insertExAt(int i, byte abyte0[])
                throws BadBytecode
            {
/* 456*/        return insert0(i, abyte0, true);
            }

            private int insert0(int i, byte abyte0[], boolean flag)
                throws BadBytecode
            {
                int j;
/* 466*/        if((j = abyte0.length) <= 0)
/* 468*/            return i;
/* 471*/        flag = i = insertGapAt(i, j, flag).position;
/* 474*/        for(int k = 0; k < j; k++)
/* 475*/            bytecode[flag++] = abyte0[k];

/* 477*/        return i;
            }

            public int insertGap(int i)
                throws BadBytecode
            {
/* 496*/        return insertGapAt(currentPos, i, false).position;
            }

            public int insertGap(int i, int j)
                throws BadBytecode
            {
/* 516*/        return insertGapAt(i, j, false).length;
            }

            public int insertExGap(int i)
                throws BadBytecode
            {
/* 535*/        return insertGapAt(currentPos, i, true).position;
            }

            public int insertExGap(int i, int j)
                throws BadBytecode
            {
/* 555*/        return insertGapAt(i, j, true).length;
            }

            public Gap insertGapAt(int i, int j, boolean flag)
                throws BadBytecode
            {
/* 614*/        Gap gap = new Gap();
/* 615*/        if(j <= 0)
                {
/* 616*/            gap.position = i;
/* 617*/            gap.length = 0;
/* 618*/            return gap;
                }
                byte abyte0[];
/* 623*/        if(bytecode.length + j > 32767)
                {
/* 625*/            abyte0 = insertGapCore0w(bytecode, i, j, flag, get().getExceptionTable(), codeAttr, gap);
/* 627*/            i = gap.position;
/* 628*/            j = j;
                } else
                {
/* 631*/            int k = currentPos;
/* 632*/            j = (abyte0 = insertGapCore0(bytecode, i, j, flag, get().getExceptionTable(), codeAttr)).length - bytecode.length;
/* 636*/            gap.position = i;
/* 637*/            gap.length = j;
/* 638*/            if(k >= i)
/* 639*/                currentPos = k + j;
/* 641*/            if(mark > i || mark == i && flag)
/* 642*/                mark += j;
                }
/* 645*/        codeAttr.setCode(abyte0);
/* 646*/        bytecode = abyte0;
/* 647*/        endPos = getCodeLength();
/* 648*/        updateCursors(i, j);
/* 649*/        return gap;
            }

            protected void updateCursors(int i, int j)
            {
            }

            public void insert(ExceptionTable exceptiontable, int i)
            {
/* 672*/        codeAttr.getExceptionTable().add(0, exceptiontable, i);
            }

            public int append(byte abyte0[])
            {
/* 682*/        int i = getCodeLength();
                int j;
/* 683*/        if((j = abyte0.length) <= 0)
/* 685*/            return i;
/* 687*/        appendGap(j);
/* 688*/        byte abyte1[] = bytecode;
/* 689*/        for(int k = 0; k < j; k++)
/* 690*/            abyte1[k + i] = abyte0[k];

/* 692*/        return i;
            }

            public void appendGap(int i)
            {
                byte abyte0[];
                int j;
/* 701*/        byte abyte1[] = new byte[(j = (abyte0 = bytecode).length) + i];
/* 706*/        for(int k = 0; k < j; k++)
/* 707*/            abyte1[k] = abyte0[k];

/* 709*/        for(int l = j; l < j + i; l++)
/* 710*/            abyte1[l] = 0;

/* 712*/        codeAttr.setCode(abyte1);
/* 713*/        bytecode = abyte1;
/* 714*/        endPos = getCodeLength();
            }

            public void append(ExceptionTable exceptiontable, int i)
            {
                ExceptionTable exceptiontable1;
/* 726*/        (exceptiontable1 = codeAttr.getExceptionTable()).add(exceptiontable1.size(), exceptiontable, i);
            }

            static int nextOpcode(byte abyte0[], int i)
                throws BadBytecode
            {
                int j;
/* 755*/        try
                {
/* 755*/            j = abyte0[i] & 0xff;
                }
/* 757*/        catch(IndexOutOfBoundsException _ex)
                {
/* 758*/            throw new BadBytecode("invalid opcode address");
                }
                int k;
/* 762*/        if((k = opcodeLength[j]) > 0)
/* 764*/            return i + k;
/* 765*/        if(j == 196)
/* 766*/            if(abyte0[i + 1] == -124)
/* 767*/                return i + 6;
/* 769*/            else
/* 769*/                return i + 4;
/* 771*/        i = (i & -4) + 8;
/* 772*/        if(j != 171)
/* 773*/            break MISSING_BLOCK_LABEL_91;
/* 773*/        k = ByteArray.read32bit(abyte0, i);
/* 774*/        return i + (k << 3) + 4;
/* 776*/        if(j != 170)
/* 777*/            break MISSING_BLOCK_LABEL_129;
/* 777*/        k = ByteArray.read32bit(abyte0, i);
/* 778*/        abyte0 = ByteArray.read32bit(abyte0, i + 4);
/* 779*/        return i + ((abyte0 - k) + 1 << 2) + 8;
/* 785*/        JVM INSTR pop ;
/* 789*/        throw new BadBytecode(j);
            }

            static byte[] insertGapCore0(byte abyte0[], int i, int j, boolean flag, ExceptionTable exceptiontable, CodeAttribute codeattribute)
                throws BadBytecode
            {
/* 815*/        if(j <= 0)
/* 816*/            return abyte0;
/* 819*/        return insertGapCore1(abyte0, i, j, flag, exceptiontable, codeattribute);
/* 821*/        JVM INSTR pop ;
/* 823*/        return insertGapCore1(abyte0, i, j + 3 & -4, flag, exceptiontable, codeattribute);
/* 826*/        JVM INSTR pop ;
/* 827*/        throw new RuntimeException("fatal error?");
            }

            private static byte[] insertGapCore1(byte abyte0[], int i, int j, boolean flag, ExceptionTable exceptiontable, CodeAttribute codeattribute)
                throws BadBytecode, AlignmentException
            {
                int k;
/* 837*/        byte abyte1[] = new byte[(k = abyte0.length) + j];
/* 839*/        insertGap2(abyte0, i, j, k, abyte1, flag);
/* 840*/        exceptiontable.shiftPc(i, j, flag);
/* 841*/        if((abyte0 = (LineNumberAttribute)codeattribute.getAttribute("LineNumberTable")) != null)
/* 844*/            abyte0.shiftPc(i, j, flag);
/* 846*/        if((abyte0 = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable")) != null)
/* 849*/            abyte0.shiftPc(i, j, flag);
/* 851*/        if((abyte0 = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTypeTable")) != null)
/* 855*/            abyte0.shiftPc(i, j, flag);
/* 857*/        if((abyte0 = (StackMapTable)codeattribute.getAttribute("StackMapTable")) != null)
/* 859*/            abyte0.shiftPc(i, j, flag);
/* 861*/        if((abyte0 = (StackMap)codeattribute.getAttribute("StackMap")) != null)
/* 863*/            abyte0.shiftPc(i, j, flag);
/* 865*/        return abyte1;
            }

            private static void insertGap2(byte abyte0[], int i, int j, int k, byte abyte1[], boolean flag)
                throws BadBytecode, AlignmentException
            {
/* 873*/        int i1 = 0;
/* 874*/        int j1 = 0;
                int l;
/* 875*/        for(; i1 < k; i1 = l)
                {
/* 876*/            if(i1 == i)
                    {
/* 877*/                for(int k1 = j1 + j; j1 < k1;)
/* 879*/                    abyte1[j1++] = 0;

                    }
/* 882*/            l = nextOpcode(abyte0, i1);
/* 883*/            int l1 = abyte0[i1] & 0xff;
/* 885*/            if(153 <= l1 && l1 <= 168 || l1 == 198 || l1 == 199)
                    {
/* 888*/                l1 = abyte0[i1 + 1] << 8 | abyte0[i1 + 2] & 0xff;
/* 889*/                l1 = newOffset(i1, l1, i, j, flag);
/* 890*/                abyte1[j1] = abyte0[i1];
/* 891*/                ByteArray.write16bit(l1, abyte1, j1 + 1);
/* 892*/                j1 += 3;
/* 893*/                continue;
                    }
/* 894*/            if(l1 == 200 || l1 == 201)
                    {
/* 896*/                l1 = ByteArray.read32bit(abyte0, i1 + 1);
/* 897*/                l1 = newOffset(i1, l1, i, j, flag);
/* 898*/                abyte1[j1++] = abyte0[i1];
/* 899*/                ByteArray.write32bit(l1, abyte1, j1);
/* 900*/                j1 += 4;
/* 901*/                continue;
                    }
/* 902*/            if(l1 == 170)
                    {
/* 903*/                if(i1 != j1 && (j & 3) != 0)
/* 904*/                    throw new AlignmentException();
/* 906*/                l1 = (i1 & -4) + 4;
/* 913*/                j1 = copyGapBytes(abyte1, j1, abyte0, i1, l1);
                        int j2;
/* 915*/                ByteArray.write32bit(j2 = newOffset(i1, ByteArray.read32bit(abyte0, l1), i, j, flag), abyte1, j1);
/* 918*/                ByteArray.write32bit(j2 = ByteArray.read32bit(abyte0, l1 + 4), abyte1, j1 + 4);
                        int i3;
/* 920*/                ByteArray.write32bit(i3 = ByteArray.read32bit(abyte0, l1 + 8), abyte1, j1 + 8);
/* 922*/                j1 += 12;
                        int k3;
/* 923*/                for(l1 = (k3 = l1 + 12) + ((i3 - j2) + 1 << 2); k3 < l1; k3 += 4)
                        {
                            int k2;
/* 926*/                    ByteArray.write32bit(k2 = newOffset(i1, ByteArray.read32bit(abyte0, k3), i, j, flag), abyte1, j1);
/* 929*/                    j1 += 4;
                        }

/* 931*/                continue;
                    }
/* 933*/            if(l1 == 171)
                    {
/* 934*/                if(i1 != j1 && (j & 3) != 0)
/* 935*/                    throw new AlignmentException();
/* 937*/                int i2 = (i1 & -4) + 4;
/* 945*/                j1 = copyGapBytes(abyte1, j1, abyte0, i1, i2);
                        int l2;
/* 947*/                ByteArray.write32bit(l2 = newOffset(i1, ByteArray.read32bit(abyte0, i2), i, j, flag), abyte1, j1);
/* 950*/                ByteArray.write32bit(l2 = ByteArray.read32bit(abyte0, i2 + 4), abyte1, j1 + 4);
/* 952*/                j1 += 8;
                        int j3;
/* 953*/                for(i2 = (j3 = i2 + 8) + (l2 << 3); j3 < i2; j3 += 8)
                        {
/* 956*/                    ByteArray.copy32bit(abyte0, j3, abyte1, j1);
                            int l3;
/* 957*/                    ByteArray.write32bit(l3 = newOffset(i1, ByteArray.read32bit(abyte0, j3 + 4), i, j, flag), abyte1, j1 + 4);
/* 961*/                    j1 += 8;
                        }

/* 963*/                continue;
                    }
/* 966*/            while(i1 < l) 
/* 967*/                abyte1[j1++] = abyte0[i1++];
                }

            }

            private static int copyGapBytes(byte abyte0[], int i, byte abyte1[], int j, int k)
            {
/* 973*/        switch(k - j)
                {
/* 975*/        case 4: // '\004'
/* 975*/            abyte0[i++] = abyte1[j++];
                    // fall through

/* 977*/        case 3: // '\003'
/* 977*/            abyte0[i++] = abyte1[j++];
                    // fall through

/* 979*/        case 2: // '\002'
/* 979*/            abyte0[i++] = abyte1[j++];
                    // fall through

/* 981*/        case 1: // '\001'
/* 981*/            abyte0[i++] = abyte1[j];
                    // fall through

/* 985*/        default:
/* 985*/            return i;
                }
            }

            private static int newOffset(int i, int j, int k, int l, boolean flag)
            {
/* 990*/        int i1 = i + j;
/* 991*/        if(i < k)
                {
/* 992*/            if(k < i1 || flag && k == i1)
/* 993*/                j += l;
                } else
/* 995*/        if(i != k ? i1 < k || !flag && k == i1 : i1 < k)
/*1003*/            j -= l;
/*1005*/        return j;
            }

            static byte[] changeLdcToLdcW(byte abyte0[], ExceptionTable exceptiontable, CodeAttribute codeattribute, CodeAttribute.LdcEntry ldcentry)
                throws BadBytecode
            {
/*1072*/        exceptiontable = new Pointers(0, 0, 0, exceptiontable, codeattribute);
/*1073*/        codeattribute = makeJumpList(abyte0, abyte0.length, exceptiontable);
/*1074*/        for(; ldcentry != null; ldcentry = ldcentry.next)
/*1075*/            addLdcW(ldcentry, codeattribute);

/*1079*/        return abyte0 = insertGap2w(abyte0, 0, 0, false, codeattribute, exceptiontable);
            }

            private static void addLdcW(CodeAttribute.LdcEntry ldcentry, ArrayList arraylist)
            {
/*1084*/        int i = ldcentry.where;
/*1085*/        ldcentry = new LdcW(i, ldcentry.index);
/*1086*/        int j = arraylist.size();
/*1087*/        for(int k = 0; k < j; k++)
/*1088*/            if(i < ((Branch)arraylist.get(k)).orgPos)
                    {
/*1089*/                arraylist.add(k, ldcentry);
/*1090*/                return;
                    }

/*1093*/        arraylist.add(ldcentry);
            }

            private byte[] insertGapCore0w(byte abyte0[], int i, int j, boolean flag, ExceptionTable exceptiontable, CodeAttribute codeattribute, Gap gap)
                throws BadBytecode
            {
/*1113*/        if(j <= 0)
/*1114*/            return abyte0;
/*1116*/        exceptiontable = new Pointers(currentPos, mark, i, exceptiontable, codeattribute);
/*1117*/        codeattribute = makeJumpList(abyte0, abyte0.length, exceptiontable);
/*1118*/        abyte0 = insertGap2w(abyte0, i, j, flag, codeattribute, exceptiontable);
/*1119*/        currentPos = ((Pointers) (exceptiontable)).cursor;
/*1120*/        mark = ((Pointers) (exceptiontable)).mark;
/*1121*/        if((i = ((Pointers) (exceptiontable)).mark0) == currentPos && !flag)
/*1123*/            currentPos += j;
/*1125*/        if(flag)
/*1126*/            i -= j;
/*1128*/        gap.position = i;
/*1129*/        gap.length = j;
/*1130*/        return abyte0;
            }

            private static byte[] insertGap2w(byte abyte0[], int i, int j, boolean flag, ArrayList arraylist, Pointers pointers)
                throws BadBytecode
            {
/*1137*/        int k = arraylist.size();
/*1138*/        if(j > 0)
                {
/*1139*/            pointers.shiftPc(i, j, flag);
/*1140*/            for(int l = 0; l < k; l++)
/*1141*/                ((Branch)arraylist.get(l)).shift(i, j, flag);

                }
                boolean flag1;
/*1144*/        do
                {
/*1144*/            for(flag1 = true; flag1;)
                    {
/*1147*/                flag1 = false;
/*1148*/                flag = false;
/*1148*/                while(flag < k) 
                        {
                            Branch branch;
/*1149*/                    if((branch = (Branch)arraylist.get(flag)).expanded())
                            {
/*1151*/                        flag1 = true;
/*1152*/                        int k1 = branch.pos;
/*1153*/                        int i1 = branch.deltaSize();
/*1154*/                        pointers.shiftPc(k1, i1, false);
/*1155*/                        for(int i2 = 0; i2 < k; i2++)
/*1156*/                            ((Branch)arraylist.get(i2)).shift(k1, i1, false);

                            }
/*1148*/                    flag++;
                        }
                    }

/*1161*/            for(flag = false; flag < k; flag++)
                    {
                        Branch branch1;
                        int l1;
/*1162*/                if((l1 = (branch1 = (Branch)arraylist.get(flag)).gapChanged()) <= 0)
/*1165*/                    continue;
/*1165*/                flag1 = true;
/*1166*/                int j1 = branch1.pos;
/*1167*/                pointers.shiftPc(j1, l1, false);
/*1168*/                for(int j2 = 0; j2 < k; j2++)
/*1169*/                    ((Branch)arraylist.get(j2)).shift(j1, l1, false);

                    }

                } while(flag1);
/*1174*/        return makeExapndedCode(abyte0, arraylist, i, j);
            }

            private static ArrayList makeJumpList(byte abyte0[], int i, Pointers pointers)
                throws BadBytecode
            {
/*1180*/        ArrayList arraylist = new ArrayList();
                int j;
/*1182*/        for(int k = 0; k < i; k = j)
                {
/*1183*/            j = nextOpcode(abyte0, k);
/*1184*/            int l = abyte0[k] & 0xff;
/*1186*/            if(153 <= l && l <= 168 || l == 198 || l == 199)
                    {
/*1189*/                int i1 = abyte0[k + 1] << 8 | abyte0[k + 2] & 0xff;
/*1191*/                if(l == 167 || l == 168)
/*1192*/                    l = new Jump16(k, i1);
/*1194*/                else
/*1194*/                    l = new If16(k, i1);
/*1196*/                arraylist.add(l);
/*1197*/                continue;
                    }
/*1198*/            if(l == 200 || l == 201)
                    {
/*1200*/                int j1 = ByteArray.read32bit(abyte0, k + 1);
/*1201*/                arraylist.add(new Jump32(k, j1));
/*1202*/                continue;
                    }
/*1203*/            if(l == 170)
                    {
/*1204*/                int k1 = (k & -4) + 4;
/*1205*/                l = ByteArray.read32bit(abyte0, k1);
/*1206*/                int i2 = ByteArray.read32bit(abyte0, k1 + 4);
/*1207*/                int k2 = ByteArray.read32bit(abyte0, k1 + 8);
/*1208*/                k1 += 12;
                        int i3;
/*1209*/                int ai2[] = new int[i3 = (k2 - i2) + 1];
/*1211*/                for(int k3 = 0; k3 < i3; k3++)
                        {
/*1212*/                    ai2[k3] = ByteArray.read32bit(abyte0, k1);
/*1213*/                    k1 += 4;
                        }

/*1216*/                arraylist.add(new Table(k, l, i2, k2, ai2, pointers));
/*1217*/                continue;
                    }
/*1218*/            if(l != 171)
/*1219*/                continue;
/*1219*/            int l1 = (k & -4) + 4;
/*1220*/            l = ByteArray.read32bit(abyte0, l1);
/*1221*/            int j2 = ByteArray.read32bit(abyte0, l1 + 4);
/*1222*/            int l2 = l1 + 8;
/*1223*/            int ai[] = new int[j2];
/*1224*/            int ai1[] = new int[j2];
/*1225*/            for(int j3 = 0; j3 < j2; j3++)
                    {
/*1226*/                ai[j3] = ByteArray.read32bit(abyte0, l2);
/*1227*/                ai1[j3] = ByteArray.read32bit(abyte0, l2 + 4);
/*1228*/                l2 += 8;
                    }

/*1231*/            arraylist.add(new Lookup(k, l, ai, ai1, pointers));
                }

/*1235*/        return arraylist;
            }

            private static byte[] makeExapndedCode(byte abyte0[], ArrayList arraylist, int i, int j)
                throws BadBytecode
            {
/*1242*/        int k = arraylist.size();
/*1243*/        int l = abyte0.length + j;
/*1244*/        for(int i1 = 0; i1 < k; i1++)
                {
/*1245*/            Branch branch = (Branch)arraylist.get(i1);
/*1246*/            l += branch.deltaSize();
                }

/*1249*/        byte abyte1[] = new byte[l];
/*1250*/        int j1 = 0;
/*1250*/        l = 0;
/*1250*/        int k1 = 0;
/*1251*/        int l1 = abyte0.length;
                Branch branch1;
                int i2;
/*1254*/        if(k > 0)
                {
/*1255*/            i2 = (branch1 = (Branch)arraylist.get(0)).orgPos;
                } else
                {
/*1259*/            branch1 = null;
/*1260*/            i2 = l1;
                }
/*1263*/        while(j1 < l1) 
                {
/*1264*/            if(j1 == i)
                    {
/*1265*/                for(int j2 = l + j; l < j2;)
/*1267*/                    abyte1[l++] = 0;

                    }
/*1270*/            if(j1 != i2)
                    {
/*1271*/                abyte1[l++] = abyte0[j1++];
                    } else
                    {
/*1273*/                int k2 = branch1.write(j1, abyte0, l, abyte1);
/*1274*/                j1 += k2;
/*1275*/                l += k2 + branch1.deltaSize();
/*1276*/                if(++k1 < k)
                        {
/*1277*/                    i2 = (branch1 = (Branch)arraylist.get(k1)).orgPos;
                        } else
                        {
/*1281*/                    branch1 = null;
/*1282*/                    i2 = l1;
                        }
                    }
                }
/*1287*/        return abyte1;
            }

            protected CodeAttribute codeAttr;
            protected byte bytecode[];
            protected int endPos;
            protected int currentPos;
            protected int mark;
            private static final int opcodeLength[] = {
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 2, 3, 2, 3, 
/* 732*/        3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
/* 732*/        1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 
/* 732*/        3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 
/* 732*/        0, 0, 1, 1, 1, 1, 1, 1, 3, 3, 
/* 732*/        3, 3, 3, 3, 3, 5, 5, 3, 2, 3, 
/* 732*/        1, 1, 3, 3, 1, 1, 0, 4, 3, 3, 
/* 732*/        5, 5
            };

}
