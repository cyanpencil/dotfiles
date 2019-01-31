// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

static abstract class all
{

            all node(BasicBlock basicblock)
            {
/* 320*/        return all[((all)basicblock).ndex];
            }

            abstract BasicBlock[] exits(ndex ndex);

            abstract BasicBlock[] entrances(ndex ndex);

            ndex all[];

            ( a[])
            {
/* 319*/        all = a;
            }
}
