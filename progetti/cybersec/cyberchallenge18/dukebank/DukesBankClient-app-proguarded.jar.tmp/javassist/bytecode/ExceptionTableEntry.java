// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionTable.java

package javassist.bytecode;


class ExceptionTableEntry
{

            ExceptionTableEntry(int i, int j, int k, int l)
            {
/*  32*/        startPc = i;
/*  33*/        endPc = j;
/*  34*/        handlerPc = k;
/*  35*/        catchType = l;
            }

            int startPc;
            int endPc;
            int handlerPc;
            int catchType;
}
