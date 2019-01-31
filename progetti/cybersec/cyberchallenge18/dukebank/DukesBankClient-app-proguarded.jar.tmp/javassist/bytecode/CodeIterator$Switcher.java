// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, ByteArray, CodeIterator

static abstract class pointers extends pointers
{

            void shift(int i, int j, boolean flag)
            {
/*1484*/        int k = pos;
/*1485*/        defaultByte = shiftOffset(k, defaultByte, i, j, flag);
/*1486*/        int l = offsets.length;
/*1487*/        for(int i1 = 0; i1 < l; i1++)
/*1488*/            offsets[i1] = shiftOffset(k, offsets[i1], i, j, flag);

/*1490*/        super.ift(i, j, flag);
            }

            int gapChanged()
            {
                int i;
/*1494*/        if((i = 3 - (pos & 3)) > gap)
                {
/*1496*/            int j = i - gap;
/*1497*/            gap = i;
/*1498*/            return j;
                } else
                {
/*1501*/            return 0;
                }
            }

            int deltaSize()
            {
/*1505*/        return gap - (3 - (orgPos & 3));
            }

            int write(int i, byte abyte0[], int j, byte abyte1[])
                throws BadBytecode
            {
/*1509*/        int k = 3 - (pos & 3);
/*1510*/        int l = gap - k;
/*1511*/        int i1 = 5 + (3 - (orgPos & 3)) + tableSize();
/*1512*/        if(l > 0)
/*1513*/            adjustOffsets(i1, l);
/*1515*/        for(abyte1[j++] = abyte0[i]; k-- > 0; abyte1[j++] = 0);
/*1519*/        ByteArray.write32bit(defaultByte, abyte1, j);
/*1520*/        i = write2(j + 4, abyte1);
/*1521*/        j += i + 4;
/*1522*/        while(l-- > 0) 
/*1523*/            abyte1[j++] = 0;
/*1525*/        return 5 + (3 - (orgPos & 3)) + i;
            }

            abstract int write2(int i, byte abyte0[]);

            abstract int tableSize();

            void adjustOffsets(int i, int j)
                throws BadBytecode
            {
/*1541*/        pointers.shiftForSwitch(pos + i, j);
/*1542*/        if(defaultByte == i)
/*1543*/            defaultByte -= j;
/*1545*/        for(int k = 0; k < offsets.length; k++)
/*1546*/            if(offsets[k] == i)
/*1547*/                offsets[k] -= j;

            }

            int gap;
            int defaultByte;
            int offsets[];
            offsets pointers;

            (int i, int j, int ai[],  )
            {
/*1476*/        super(i);
/*1477*/        gap = 3 - (i & 3);
/*1478*/        defaultByte = j;
/*1479*/        offsets = ai;
/*1480*/        pointers = ;
            }
}
