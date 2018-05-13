// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

public static class info
{

            public void visit()
            {
/* 142*/        int i = ByteArray.readU16bit(info, 0);
/* 143*/        int j = 2;
/* 144*/        for(int k = 0; k < i; k++)
                {
/* 145*/            int l = ByteArray.readU16bit(info, j);
/* 146*/            int i1 = ByteArray.readU16bit(info, j + 2);
/* 147*/            j = locals(j + 4, l, i1);
/* 148*/            i1 = ByteArray.readU16bit(info, j);
/* 149*/            j = stack(j + 2, l, i1);
                }

            }

            public int locals(int i, int j, int k)
            {
/* 158*/        return typeInfoArray(i, j, k, true);
            }

            public int stack(int i, int j, int k)
            {
/* 166*/        return typeInfoArray(i, j, k, false);
            }

            public int typeInfoArray(int i, int j, int k, boolean flag)
            {
/* 178*/        for(j = 0; j < k; j++)
/* 179*/            i = typeInfoArray2(j, i);

/* 181*/        return i;
            }

            int typeInfoArray2(int i, int j)
            {
/* 185*/        if((i = info[j]) == 7)
                {
/* 187*/            i = ByteArray.readU16bit(info, j + 1);
/* 188*/            objectVariable(j, i);
/* 189*/            j += 3;
                } else
/* 191*/        if(i == 8)
                {
/* 192*/            i = ByteArray.readU16bit(info, j + 1);
/* 193*/            uninitialized(j, i);
/* 194*/            j += 3;
                } else
                {
/* 197*/            typeInfo(j, i);
/* 198*/            j++;
                }
/* 201*/        return j;
            }

            public void typeInfo(int i, byte byte0)
            {
            }

            public void objectVariable(int i, int j)
            {
            }

            public void uninitialized(int i, int j)
            {
            }

            byte info[];

            public (StackMap stackmap)
            {
/* 135*/        info = stackmap.get();
            }
}
