// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionTable.java

package javassist.bytecode;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstPool, ExceptionTableEntry

public class ExceptionTable
    implements Cloneable
{

            public ExceptionTable(ConstPool constpool)
            {
/*  52*/        constPool = constpool;
/*  53*/        entries = new ArrayList();
            }

            ExceptionTable(ConstPool constpool, DataInputStream datainputstream)
                throws IOException
            {
/*  57*/        constPool = constpool;
/*  58*/        constpool = datainputstream.readUnsignedShort();
/*  59*/        ArrayList arraylist = new ArrayList(constpool);
/*  60*/        for(int i = 0; i < constpool; i++)
                {
/*  61*/            int j = datainputstream.readUnsignedShort();
/*  62*/            int k = datainputstream.readUnsignedShort();
/*  63*/            int l = datainputstream.readUnsignedShort();
/*  64*/            int i1 = datainputstream.readUnsignedShort();
/*  65*/            arraylist.add(new ExceptionTableEntry(j, k, l, i1));
                }

/*  68*/        entries = arraylist;
            }

            public Object clone()
                throws CloneNotSupportedException
            {
                ExceptionTable exceptiontable;
/*  77*/        (exceptiontable = (ExceptionTable)super.clone()).entries = new ArrayList(entries);
/*  79*/        return exceptiontable;
            }

            public int size()
            {
/*  87*/        return entries.size();
            }

            public int startPc(int i)
            {
/*  96*/        return ((ExceptionTableEntry) (i = (ExceptionTableEntry)entries.get(i))).startPc;
            }

            public void setStartPc(int i, int j)
            {
/* 107*/        (i = (ExceptionTableEntry)entries.get(i)).startPc = j;
            }

            public int endPc(int i)
            {
/* 117*/        return ((ExceptionTableEntry) (i = (ExceptionTableEntry)entries.get(i))).endPc;
            }

            public void setEndPc(int i, int j)
            {
/* 128*/        (i = (ExceptionTableEntry)entries.get(i)).endPc = j;
            }

            public int handlerPc(int i)
            {
/* 138*/        return ((ExceptionTableEntry) (i = (ExceptionTableEntry)entries.get(i))).handlerPc;
            }

            public void setHandlerPc(int i, int j)
            {
/* 149*/        (i = (ExceptionTableEntry)entries.get(i)).handlerPc = j;
            }

            public int catchType(int i)
            {
/* 161*/        return ((ExceptionTableEntry) (i = (ExceptionTableEntry)entries.get(i))).catchType;
            }

            public void setCatchType(int i, int j)
            {
/* 172*/        (i = (ExceptionTableEntry)entries.get(i)).catchType = j;
            }

            public void add(int i, ExceptionTable exceptiontable, int j)
            {
/* 184*/        for(int k = exceptiontable.size(); --k >= 0;)
                {
/* 186*/            ExceptionTableEntry exceptiontableentry = (ExceptionTableEntry)exceptiontable.entries.get(k);
/* 188*/            add(i, exceptiontableentry.startPc + j, exceptiontableentry.endPc + j, exceptiontableentry.handlerPc + j, exceptiontableentry.catchType);
                }

            }

            public void add(int i, int j, int k, int l, int i1)
            {
/* 203*/        if(j < k)
/* 204*/            entries.add(i, new ExceptionTableEntry(j, k, l, i1));
            }

            public void add(int i, int j, int k, int l)
            {
/* 217*/        if(i < j)
/* 218*/            entries.add(new ExceptionTableEntry(i, j, k, l));
            }

            public void remove(int i)
            {
/* 227*/        entries.remove(i);
            }

            public ExceptionTable copy(ConstPool constpool, Map map)
            {
/* 240*/        ExceptionTable exceptiontable = new ExceptionTable(constpool);
/* 241*/        ConstPool constpool1 = constPool;
/* 242*/        int i = size();
/* 243*/        for(int j = 0; j < i; j++)
                {
/* 244*/            ExceptionTableEntry exceptiontableentry = (ExceptionTableEntry)entries.get(j);
/* 245*/            int k = constpool1.copy(exceptiontableentry.catchType, constpool, map);
/* 246*/            exceptiontable.add(exceptiontableentry.startPc, exceptiontableentry.endPc, exceptiontableentry.handlerPc, k);
                }

/* 249*/        return exceptiontable;
            }

            void shiftPc(int i, int j, boolean flag)
            {
/* 253*/        int k = size();
/* 254*/        for(int l = 0; l < k; l++)
                {
                    ExceptionTableEntry exceptiontableentry;
/* 255*/            (exceptiontableentry = (ExceptionTableEntry)entries.get(l)).startPc = shiftPc(exceptiontableentry.startPc, i, j, flag);
/* 257*/            exceptiontableentry.endPc = shiftPc(exceptiontableentry.endPc, i, j, flag);
/* 258*/            exceptiontableentry.handlerPc = shiftPc(exceptiontableentry.handlerPc, i, j, flag);
                }

            }

            private static int shiftPc(int i, int j, int k, boolean flag)
            {
/* 264*/        if(i > j || flag && i == j)
/* 265*/            i += k;
/* 267*/        return i;
            }

            void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 271*/        int i = size();
/* 272*/        dataoutputstream.writeShort(i);
/* 273*/        for(int j = 0; j < i; j++)
                {
/* 274*/            ExceptionTableEntry exceptiontableentry = (ExceptionTableEntry)entries.get(j);
/* 275*/            dataoutputstream.writeShort(exceptiontableentry.startPc);
/* 276*/            dataoutputstream.writeShort(exceptiontableentry.endPc);
/* 277*/            dataoutputstream.writeShort(exceptiontableentry.handlerPc);
/* 278*/            dataoutputstream.writeShort(exceptiontableentry.catchType);
                }

            }

            private ConstPool constPool;
            private ArrayList entries;
}
