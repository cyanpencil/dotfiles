// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMapTable

static class  extends 
{

            void update(int i, int j, int k, int l)
            {
/* 935*/        int i1 = position;
/* 936*/        position = i1 + j + (i1 != 0 ? 1 : 0);
/* 938*/        if(where == position)
/* 939*/            i1 = j - gap;
/* 940*/        else
/* 940*/        if(where == i1)
/* 941*/            i1 = j + gap;
/* 943*/        else
/* 943*/            return;
/* 945*/        if(j < 64)
/* 946*/            if(i1 < 64)
                    {
/* 947*/                info[i] = (byte)(i1 + k);
/* 947*/                return;
                    } else
                    {
/* 949*/                (j = insertGap(info, i, 2))[i] = (byte)l;
/* 951*/                ByteArray.write16bit(i1, j, i + 1);
/* 952*/                updatedInfo = j;
/* 953*/                return;
                    }
/* 955*/        if(i1 < 64)
                {
/* 956*/            (j = deleteGap(info, i, 2))[i] = (byte)(i1 + k);
/* 958*/            updatedInfo = j;
/* 959*/            return;
                } else
                {
/* 961*/            ByteArray.write16bit(i1, info, i + 1);
/* 962*/            return;
                }
            }

            static byte[] deleteGap(byte abyte0[], int i, int j)
            {
/* 965*/        i += j;
                int k;
/* 966*/        byte abyte1[] = new byte[(k = abyte0.length) - j];
/* 968*/        for(int l = 0; l < k; l++)
/* 969*/            abyte1[l - (l >= i ? j : 0)] = abyte0[l];

/* 971*/        return abyte1;
            }

            void update(int i, int j)
            {
/* 975*/        int k = position;
/* 976*/        position = k + j + (k != 0 ? 1 : 0);
/* 978*/        if(where == position)
/* 979*/            j -= gap;
/* 980*/        else
/* 980*/        if(where == k)
/* 981*/            j += gap;
/* 983*/        else
/* 983*/            return;
/* 985*/        ByteArray.write16bit(j, info, i + 1);
            }

            (StackMapTable stackmaptable, int i, int j)
            {
/* 931*/        super(stackmaptable, i, j, false);
            }
}
