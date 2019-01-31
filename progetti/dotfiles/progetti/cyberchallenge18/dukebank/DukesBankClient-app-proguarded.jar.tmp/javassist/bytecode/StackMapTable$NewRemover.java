// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            StackMapTable

static class posOfNew extends posOfNew
{

            public void sameLocals(int i, int j, int k, int l)
            {
/*1017*/        if(k == 8 && l == posOfNew)
                {
/*1018*/            super.sameFrame(i, j);
/*1018*/            return;
                } else
                {
/*1020*/            super.sameLocals(i, j, k, l);
/*1021*/            return;
                }
            }

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
            {
/*1025*/        int k = ai2.length - 1;
/*1026*/        int l = 0;
/*1026*/        do
                {
/*1026*/            if(l >= k)
/*1027*/                break;
/*1027*/            if(ai2[l] == 8 && ai3[l] == posOfNew && ai2[l + 1] == 8 && ai3[l + 1] == posOfNew)
                    {
/*1029*/                int ai4[] = new int[++k - 2];
/*1031*/                int ai5[] = new int[k - 2];
/*1032*/                int i1 = 0;
/*1033*/                for(int j1 = 0; j1 < k; j1++)
/*1034*/                    if(j1 == l)
                            {
/*1035*/                        j1++;
                            } else
                            {
/*1037*/                        ai4[i1] = ai2[j1];
/*1038*/                        ai5[i1++] = ai3[j1];
                            }

/*1041*/                ai2 = ai4;
/*1042*/                ai3 = ai5;
/*1043*/                break;
                    }
/*1026*/            l++;
                } while(true);
/*1046*/        super.fullFrame(i, j, ai, ai1, ai2, ai3);
            }

            int posOfNew;

            public (byte abyte0[], int i)
            {
/*1012*/        super(abyte0);
/*1013*/        posOfNew = i;
            }
}
