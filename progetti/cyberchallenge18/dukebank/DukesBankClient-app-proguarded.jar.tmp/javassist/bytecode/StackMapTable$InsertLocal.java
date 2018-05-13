// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            StackMapTable

static class varData extends varData
{

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
            {
                int k;
/* 509*/        if((k = ai.length) < varIndex)
                {
/* 511*/            super.ullFrame(i, j, ai, ai1, ai2, ai3);
/* 512*/            return;
                }
/* 515*/        byte byte0 = ((byte)(varTag != 4 && varTag != 3 ? 1 : 2));
/* 516*/        int ai4[] = new int[k + byte0];
/* 517*/        int ai5[] = new int[k + byte0];
/* 518*/        int l = varIndex;
/* 519*/        int i1 = 0;
/* 520*/        for(int j1 = 0; j1 < k; j1++)
                {
/* 521*/            if(i1 == l)
/* 522*/                i1 += byte0;
/* 524*/            ai4[i1] = ai[j1];
/* 525*/            ai5[i1++] = ai1[j1];
                }

/* 528*/        ai4[l] = varTag;
/* 529*/        ai5[l] = varData;
/* 530*/        if(byte0 > 1)
                {
/* 531*/            ai4[l + 1] = 0;
/* 532*/            ai5[l + 1] = 0;
                }
/* 535*/        super.ullFrame(i, j, ai4, ai5, ai2, ai3);
            }

            private int varIndex;
            private int varTag;
            private int varData;

            public (byte abyte0[], int i, int j, int k)
            {
/* 501*/        super(abyte0);
/* 502*/        varIndex = i;
/* 503*/        varTag = j;
/* 504*/        varData = k;
            }
}
