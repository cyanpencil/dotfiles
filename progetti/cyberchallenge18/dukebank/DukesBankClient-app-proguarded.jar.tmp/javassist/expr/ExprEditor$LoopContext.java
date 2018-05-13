// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExprEditor.java

package javassist.expr;


// Referenced classes of package javassist.expr:
//            ExprEditor

static final class newList
{

            final void updateMax(int i, int j)
            {
/* 168*/        if(maxLocals < i)
/* 169*/            maxLocals = i;
/* 171*/        if(maxStack < j)
/* 172*/            maxStack = j;
            }

            maxStack newList;
            int maxLocals;
            int maxStack;

            (int i)
            {
/* 162*/        maxLocals = i;
/* 163*/        maxStack = 0;
/* 164*/        newList = null;
            }
}
