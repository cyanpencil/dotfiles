// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeAttribute.java

package javassist.bytecode;

import java.io.*;
import java.util.*;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, BadBytecode, CodeAnalyzer, CodeIterator, 
//            ConstPool, ExceptionTable, Opcode, StackMap, 
//            StackMapTable

public class CodeAttribute extends AttributeInfo
    implements Opcode
{
    static class LdcEntry
    {

                static byte[] doit(byte abyte0[], LdcEntry ldcentry, ExceptionTable exceptiontable, CodeAttribute codeattribute)
                    throws BadBytecode
                {
/* 444*/            if(ldcentry != null)
/* 445*/                abyte0 = CodeIterator.changeLdcToLdcW(abyte0, exceptiontable, codeattribute, ldcentry);
/* 460*/            return abyte0;
                }

                LdcEntry next;
                int where;
                int index;

                LdcEntry()
                {
                }
    }

    public static class RuntimeCopyException extends RuntimeException
    {

                public RuntimeCopyException(String s)
                {
/* 153*/            super(s);
                }
    }


            public CodeAttribute(ConstPool constpool, int i, int j, byte abyte0[], ExceptionTable exceptiontable)
            {
/*  62*/        super(constpool, "Code");
/*  63*/        maxStack = i;
/*  64*/        maxLocals = j;
/*  65*/        info = abyte0;
/*  66*/        exceptions = exceptiontable;
/*  67*/        attributes = new ArrayList();
            }

            private CodeAttribute(ConstPool constpool, CodeAttribute codeattribute, Map map)
                throws BadBytecode
            {
/*  82*/        super(constpool, "Code");
/*  84*/        maxStack = codeattribute.getMaxStack();
/*  85*/        maxLocals = codeattribute.getMaxLocals();
/*  86*/        exceptions = codeattribute.getExceptionTable().copy(constpool, map);
/*  87*/        attributes = new ArrayList();
                List list;
/*  88*/        int i = (list = codeattribute.getAttributes()).size();
/*  90*/        for(int j = 0; j < i; j++)
                {
/*  91*/            AttributeInfo attributeinfo = (AttributeInfo)list.get(j);
/*  92*/            attributes.add(attributeinfo.copy(constpool, map));
                }

/*  95*/        info = codeattribute.copyCode(constpool, map, exceptions, this);
            }

            CodeAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/* 101*/        super(constpool, i, null);
/* 102*/        datainputstream.readInt();
/* 104*/        maxStack = datainputstream.readUnsignedShort();
/* 105*/        maxLocals = datainputstream.readUnsignedShort();
/* 107*/        i = datainputstream.readInt();
/* 108*/        info = new byte[i];
/* 109*/        datainputstream.readFully(info);
/* 111*/        exceptions = new ExceptionTable(constpool, datainputstream);
/* 113*/        attributes = new ArrayList();
/* 114*/        i = datainputstream.readUnsignedShort();
/* 115*/        for(int j = 0; j < i; j++)
/* 116*/            attributes.add(AttributeInfo.read(constpool, datainputstream));

            }

            public AttributeInfo copy(ConstPool constpool, Map map)
                throws RuntimeCopyException
            {
/* 137*/        return new CodeAttribute(constpool, this, map);
/* 139*/        JVM INSTR pop ;
/* 140*/        throw new RuntimeCopyException("bad bytecode. fatal?");
            }

            public int length()
            {
/* 163*/        return 18 + info.length + (exceptions.size() << 3) + AttributeInfo.getLength(attributes);
            }

            void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 168*/        dataoutputstream.writeShort(name);
/* 169*/        dataoutputstream.writeInt(length() - 6);
/* 170*/        dataoutputstream.writeShort(maxStack);
/* 171*/        dataoutputstream.writeShort(maxLocals);
/* 172*/        dataoutputstream.writeInt(info.length);
/* 173*/        dataoutputstream.write(info);
/* 174*/        exceptions.write(dataoutputstream);
/* 175*/        dataoutputstream.writeShort(attributes.size());
/* 176*/        AttributeInfo.writeAll(attributes, dataoutputstream);
            }

            public byte[] get()
            {
/* 185*/        throw new UnsupportedOperationException("CodeAttribute.get()");
            }

            public void set(byte abyte0[])
            {
/* 194*/        throw new UnsupportedOperationException("CodeAttribute.set()");
            }

            void renameClass(String s, String s1)
            {
/* 198*/        AttributeInfo.renameClass(attributes, s, s1);
            }

            void renameClass(Map map)
            {
/* 202*/        AttributeInfo.renameClass(attributes, map);
            }

            void getRefClasses(Map map)
            {
/* 206*/        AttributeInfo.getRefClasses(attributes, map);
            }

            public String getDeclaringClass()
            {
                ConstPool constpool;
/* 214*/        return (constpool = getConstPool()).getClassName();
            }

            public int getMaxStack()
            {
/* 222*/        return maxStack;
            }

            public void setMaxStack(int i)
            {
/* 229*/        maxStack = i;
            }

            public int computeMaxStack()
                throws BadBytecode
            {
/* 240*/        maxStack = (new CodeAnalyzer(this)).computeMaxStack();
/* 241*/        return maxStack;
            }

            public int getMaxLocals()
            {
/* 248*/        return maxLocals;
            }

            public void setMaxLocals(int i)
            {
/* 255*/        maxLocals = i;
            }

            public int getCodeLength()
            {
/* 262*/        return info.length;
            }

            public byte[] getCode()
            {
/* 269*/        return info;
            }

            void setCode(byte abyte0[])
            {
/* 275*/        super.set(abyte0);
            }

            public CodeIterator iterator()
            {
/* 281*/        return new CodeIterator(this);
            }

            public ExceptionTable getExceptionTable()
            {
/* 287*/        return exceptions;
            }

            public List getAttributes()
            {
/* 297*/        return attributes;
            }

            public AttributeInfo getAttribute(String s)
            {
/* 307*/        return AttributeInfo.lookup(attributes, s);
            }

            public void setAttribute(StackMapTable stackmaptable)
            {
/* 319*/        AttributeInfo.remove(attributes, "StackMapTable");
/* 320*/        if(stackmaptable != null)
/* 321*/            attributes.add(stackmaptable);
            }

            public void setAttribute(StackMap stackmap)
            {
/* 334*/        AttributeInfo.remove(attributes, "StackMap");
/* 335*/        if(stackmap != null)
/* 336*/            attributes.add(stackmap);
            }

            private byte[] copyCode(ConstPool constpool, Map map, ExceptionTable exceptiontable, CodeAttribute codeattribute)
                throws BadBytecode
            {
                int i;
/* 346*/        byte abyte0[] = new byte[i = getCodeLength()];
/* 348*/        codeattribute.info = abyte0;
/* 349*/        constpool = copyCode(info, 0, i, getConstPool(), abyte0, constpool, map);
/* 351*/        return LdcEntry.doit(abyte0, constpool, exceptiontable, codeattribute);
            }

            private static LdcEntry copyCode(byte abyte0[], int i, int j, ConstPool constpool, byte abyte1[], ConstPool constpool1, Map map)
                throws BadBytecode
            {
/* 360*/        LdcEntry ldcentry = null;
/* 362*/        for(int l = i; l < j; l = i)
                {
/* 363*/            i = CodeIterator.nextOpcode(abyte0, l);
/* 364*/            byte byte0 = abyte0[l];
/* 365*/            abyte1[l] = byte0;
/* 366*/            switch(byte0 & 0xff)
                    {
/* 380*/            case 19: // '\023'
/* 380*/            case 20: // '\024'
/* 380*/            case 178: 
/* 380*/            case 179: 
/* 380*/            case 180: 
/* 380*/            case 181: 
/* 380*/            case 182: 
/* 380*/            case 183: 
/* 380*/            case 184: 
/* 380*/            case 187: 
/* 380*/            case 189: 
/* 380*/            case 192: 
/* 380*/            case 193: 
/* 380*/                copyConstPoolInfo(l + 1, abyte0, constpool, abyte1, constpool1, map);
/* 382*/                break;

/* 384*/            case 18: // '\022'
/* 384*/                int k = abyte0[l + 1] & 0xff;
/* 385*/                if((k = constpool.copy(k, constpool1, map)) < 256)
                        {
/* 387*/                    abyte1[l + 1] = (byte)k;
                        } else
                        {
/* 389*/                    abyte1[l] = 0;
/* 390*/                    abyte1[l + 1] = 0;
                            LdcEntry ldcentry1;
/* 391*/                    (ldcentry1 = new LdcEntry()).where = l;
/* 393*/                    ldcentry1.index = k;
/* 394*/                    ldcentry1.next = ldcentry;
/* 395*/                    ldcentry = ldcentry1;
                        }
/* 397*/                break;

/* 399*/            case 185: 
/* 399*/                copyConstPoolInfo(l + 1, abyte0, constpool, abyte1, constpool1, map);
/* 401*/                abyte1[l + 3] = abyte0[l + 3];
/* 402*/                abyte1[l + 4] = abyte0[l + 4];
/* 403*/                break;

/* 405*/            case 186: 
/* 405*/                copyConstPoolInfo(l + 1, abyte0, constpool, abyte1, constpool1, map);
/* 407*/                abyte1[l + 3] = 0;
/* 408*/                abyte1[l + 4] = 0;
/* 409*/                break;

/* 411*/            case 197: 
/* 411*/                copyConstPoolInfo(l + 1, abyte0, constpool, abyte1, constpool1, map);
/* 413*/                abyte1[l + 3] = abyte0[l + 3];
/* 414*/                break;

/* 416*/            default:
/* 416*/                while(++l < i) 
/* 417*/                    abyte1[l] = abyte0[l];
                        break;
                    }
                }

/* 423*/        return ldcentry;
            }

            private static void copyConstPoolInfo(int i, byte abyte0[], ConstPool constpool, byte abyte1[], ConstPool constpool1, Map map)
            {
/* 429*/        abyte0 = (abyte0[i] & 0xff) << 8 | abyte0[i + 1] & 0xff;
/* 430*/        abyte0 = constpool.copy(abyte0, constpool1, map);
/* 431*/        abyte1[i] = (byte)(abyte0 >> 8);
/* 432*/        abyte1[i + 1] = (byte)abyte0;
            }

            public void insertLocalVar(int i, int j)
                throws BadBytecode
            {
/* 481*/        for(CodeIterator codeiterator = iterator(); codeiterator.hasNext(); shiftIndex(codeiterator, i, j));
/* 485*/        setMaxLocals(getMaxLocals() + j);
            }

            private static void shiftIndex(CodeIterator codeiterator, int i, int j)
                throws BadBytecode
            {
/* 496*/        int k = codeiterator.next();
                int l;
/* 497*/        if((l = codeiterator.byteAt(k)) < 21)
/* 499*/            return;
/* 500*/        if(l < 79)
                {
/* 501*/            if(l < 26)
                    {
/* 503*/                shiftIndex8(codeiterator, k, l, i, j);
/* 503*/                return;
                    }
/* 505*/            if(l < 46)
                    {
/* 507*/                shiftIndex0(codeiterator, k, l, i, j, 26, 21);
/* 507*/                return;
                    }
/* 509*/            if(l < 54)
/* 510*/                return;
/* 511*/            if(l < 59)
                    {
/* 513*/                shiftIndex8(codeiterator, k, l, i, j);
/* 513*/                return;
                    } else
                    {
/* 517*/                shiftIndex0(codeiterator, k, l, i, j, 59, 54);
/* 517*/                return;
                    }
                }
/* 520*/        if(l == 132)
                {
/* 521*/            if((l = codeiterator.byteAt(k + 1)) < i)
/* 523*/                return;
/* 525*/            if((l += j) < 256)
                    {
/* 527*/                codeiterator.writeByte(l, k + 1);
                    } else
                    {
/* 529*/                i = (byte)codeiterator.byteAt(k + 2);
/* 530*/                j = codeiterator.insertExGap(3);
/* 531*/                codeiterator.writeByte(196, j - 3);
/* 532*/                codeiterator.writeByte(132, j - 2);
/* 533*/                codeiterator.write16bit(l, j - 1);
/* 534*/                codeiterator.write16bit(i, j + 1);
/* 536*/                return;
                    }
                } else
                {
/* 537*/            if(l == 169)
                    {
/* 538*/                shiftIndex8(codeiterator, k, l, i, j);
/* 538*/                return;
                    }
/* 539*/            if(l == 196)
                    {
                        int i1;
/* 540*/                if((i1 = codeiterator.u16bitAt(k + 2)) < i)
/* 542*/                    return;
/* 544*/                i1 += j;
/* 545*/                codeiterator.write16bit(i1, k + 2);
                    }
                }
            }

            private static void shiftIndex8(CodeIterator codeiterator, int i, int j, int k, int l)
                throws BadBytecode
            {
                int i1;
/* 553*/        if((i1 = codeiterator.byteAt(i + 1)) < k)
/* 555*/            return;
/* 557*/        if((i1 += l) < 256)
                {
/* 559*/            codeiterator.writeByte(i1, i + 1);
/* 559*/            return;
                } else
                {
/* 561*/            i = codeiterator.insertExGap(2);
/* 562*/            codeiterator.writeByte(196, i - 2);
/* 563*/            codeiterator.writeByte(j, i - 1);
/* 564*/            codeiterator.write16bit(i1, i);
/* 566*/            return;
                }
            }

            private static void shiftIndex0(CodeIterator codeiterator, int i, int j, int k, int l, int i1, int j1)
                throws BadBytecode
            {
                int k1;
/* 573*/        if((k1 = (j - i1) % 4) < k)
/* 575*/            return;
/* 577*/        if((k1 += l) < 4)
                {
/* 579*/            codeiterator.writeByte(j + l, i);
/* 579*/            return;
                }
/* 581*/        j = (j - i1) / 4 + j1;
/* 582*/        if(k1 < 256)
                {
/* 583*/            i = codeiterator.insertExGap(1);
/* 584*/            codeiterator.writeByte(j, i - 1);
/* 585*/            codeiterator.writeByte(k1, i);
/* 586*/            return;
                } else
                {
/* 588*/            i = codeiterator.insertExGap(3);
/* 589*/            codeiterator.writeByte(196, i - 1);
/* 590*/            codeiterator.writeByte(j, i);
/* 591*/            codeiterator.write16bit(k1, i + 1);
/* 594*/            return;
                }
            }

            public static final String tag = "Code";
            private int maxStack;
            private int maxLocals;
            private ExceptionTable exceptions;
            private ArrayList attributes;
}
