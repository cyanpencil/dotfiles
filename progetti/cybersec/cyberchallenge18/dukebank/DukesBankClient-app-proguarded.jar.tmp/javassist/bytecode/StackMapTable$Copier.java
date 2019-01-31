// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;

import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstPool, StackMapTable

static class classnames extends opy
{

            protected int copyData(int i, int j)
            {
/* 426*/        if(i == 7)
/* 427*/            return srcPool.copy(j, destPool, classnames);
/* 429*/        else
/* 429*/            return j;
            }

            protected int[] copyData(int ai[], int ai1[])
            {
/* 433*/        int ai2[] = new int[ai1.length];
/* 434*/        for(int i = 0; i < ai1.length; i++)
/* 435*/            if(ai[i] == 7)
/* 436*/                ai2[i] = srcPool.copy(ai1[i], destPool, classnames);
/* 438*/            else
/* 438*/                ai2[i] = ai1[i];

/* 440*/        return ai2;
            }

            private ConstPool srcPool;
            private ConstPool destPool;
            private Map classnames;

            public opy(ConstPool constpool, byte abyte0[], ConstPool constpool1, Map map)
            {
/* 419*/        super(abyte0);
/* 420*/        srcPool = constpool;
/* 421*/        destPool = constpool1;
/* 422*/        classnames = map;
            }
}
