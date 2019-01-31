// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, ByteArray, StackMapTable

static class exclusive extends exclusive
{

            public void doit()
                throws BadBytecode
            {
/* 845*/        parse();
/* 846*/        if(updatedInfo != null)
/* 847*/            stackMap.set(updatedInfo);
            }

            public void sameFrame(int i, int j)
            {
/* 851*/        update(i, j, 0, 251);
            }

            public void sameLocals(int i, int j, int k, int l)
            {
/* 855*/        update(i, j, 64, 247);
            }

            void update(int i, int j, int k, int l)
            {
/* 859*/        int i1 = position;
/* 860*/        position = i1 + j + (i1 != 0 ? 1 : 0);
/* 862*/        if(exclusive)
/* 863*/            i1 = i1 >= where || where > position ? 0 : 1;
/* 865*/        else
/* 865*/            i1 = i1 > where || where >= position ? 0 : 1;
/* 867*/        if(i1 != 0)
                {
/* 868*/            int j1 = j + gap;
/* 869*/            position += gap;
/* 870*/            if(j1 < 64)
                    {
/* 871*/                info[i] = (byte)(j1 + k);
/* 871*/                return;
                    }
/* 872*/            if(j < 64)
                    {
/* 873*/                (j = insertGap(info, i, 2))[i] = (byte)l;
/* 875*/                ByteArray.write16bit(j1, j, i + 1);
/* 876*/                updatedInfo = j;
/* 877*/                return;
                    }
/* 879*/            ByteArray.write16bit(j1, info, i + 1);
                }
            }

            static byte[] insertGap(byte abyte0[], int i, int j)
            {
                int k;
/* 884*/        byte abyte1[] = new byte[(k = abyte0.length) + j];
/* 886*/        for(int l = 0; l < k; l++)
/* 887*/            abyte1[l + (l >= i ? j : 0)] = abyte0[l];

/* 889*/        return abyte1;
            }

            public void chopFrame(int i, int j, int k)
            {
/* 893*/        update(i, j);
            }

            public void appendFrame(int i, int j, int ai[], int ai1[])
            {
/* 897*/        update(i, j);
            }

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
            {
/* 902*/        update(i, j);
            }

            void update(int i, int j)
            {
/* 906*/        int k = position;
/* 907*/        position = k + j + (k != 0 ? 1 : 0);
/* 909*/        if(exclusive)
/* 910*/            k = k >= where || where > position ? 0 : 1;
/* 912*/        else
/* 912*/            k = k > where || where >= position ? 0 : 1;
/* 914*/        if(k != 0)
                {
/* 915*/            ByteArray.write16bit(j += gap, info, i + 1);
/* 917*/            position += gap;
                }
            }

            private StackMapTable stackMap;
            int where;
            int gap;
            int position;
            byte updatedInfo[];
            boolean exclusive;

            public (StackMapTable stackmaptable, int i, int j, boolean flag)
            {
/* 835*/        super(stackmaptable);
/* 836*/        stackMap = stackmaptable;
/* 837*/        where = i;
/* 838*/        gap = j;
/* 839*/        position = 0;
/* 840*/        updatedInfo = null;
/* 841*/        exclusive = flag;
            }
}
