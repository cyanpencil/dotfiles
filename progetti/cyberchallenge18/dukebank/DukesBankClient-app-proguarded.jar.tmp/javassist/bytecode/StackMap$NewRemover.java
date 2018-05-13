// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

static class posOfNew extends posOfNew
{

            public int stack(int i, int j, int k)
            {
/* 455*/        return stackTypeInfoArray(i, j, k);
            }

            private int stackTypeInfoArray(int i, int j, int k)
            {
/* 459*/        j = i;
/* 460*/        int l = 0;
/* 461*/        for(int i1 = 0; i1 < k; i1++)
                {
                    byte byte0;
/* 462*/            if((byte0 = info[j]) == 7)
                    {
/* 464*/                j += 3;
/* 464*/                continue;
                    }
/* 465*/            if(byte0 == 8)
                    {
                        int k1;
/* 466*/                if((k1 = ByteArray.readU16bit(info, j + 1)) == posOfNew)
/* 468*/                    l++;
/* 470*/                j += 3;
                    } else
                    {
/* 473*/                j++;
                    }
                }

/* 476*/        writer.e16bit(k - l);
/* 477*/        for(int j1 = 0; j1 < k; j1++)
                {
                    int l1;
/* 478*/            if((l1 = info[i]) == 7)
                    {
/* 480*/                l1 = ByteArray.readU16bit(info, i + 1);
/* 481*/                objectVariable(i, l1);
/* 482*/                i += 3;
/* 483*/                continue;
                    }
/* 484*/            if(l1 == 8)
                    {
/* 485*/                if((l1 = ByteArray.readU16bit(info, i + 1)) != posOfNew)
/* 487*/                    uninitialized(i, l1);
/* 489*/                i += 3;
                    } else
                    {
/* 492*/                typeInfo(i, l1);
/* 493*/                i++;
                    }
                }

/* 497*/        return i;
            }

            int posOfNew;

            (StackMap stackmap, int i)
            {
/* 450*/        super(stackmap);
/* 451*/        posOfNew = i;
            }
}
