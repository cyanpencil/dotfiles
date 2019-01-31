// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicBlock.java

package javassist.bytecode.stackmap;


// Referenced classes of package javassist.bytecode.stackmap:
//            BasicBlock

static class catcher
    implements Comparable
{

            public int compareTo(Object obj)
            {
/* 122*/        if(obj instanceof catcher)
                {
/* 123*/            obj = ((catcher)obj).position;
/* 124*/            return position - obj;
                } else
                {
/* 127*/            return -1;
                }
            }

            void setJump(BasicBlock abasicblock[], int i, boolean flag)
            {
/* 131*/        jump = abasicblock;
/* 132*/        size = i;
/* 133*/        alwaysJmp = flag;
            }

            int position;
            BasicBlock block;
            BasicBlock jump[];
            boolean alwaysJmp;
            int size;
             catcher;

            (int i)
            {
/* 113*/        position = i;
/* 114*/        block = null;
/* 115*/        jump = null;
/* 116*/        alwaysJmp = false;
/* 117*/        size = 0;
/* 118*/        catcher = null;
            }
}
