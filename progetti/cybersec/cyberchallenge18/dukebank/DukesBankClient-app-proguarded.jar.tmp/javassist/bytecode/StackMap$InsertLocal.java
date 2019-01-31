// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            StackMap

static class varData extends varData
{

            public int typeInfoArray(int i, int j, int k, boolean flag)
            {
/* 349*/        if(!flag || k < varIndex)
/* 350*/            return super.ypeInfoArray(i, j, k, flag);
/* 352*/        writer._mth16bit(k + 1);
/* 353*/        for(j = 0; j < k; j++)
                {
/* 354*/            if(j == varIndex)
/* 355*/                writeVarTypeInfo();
/* 357*/            i = typeInfoArray2(j, i);
                }

/* 360*/        if(k == varIndex)
/* 361*/            writeVarTypeInfo();
/* 363*/        return i;
            }

            private void writeVarTypeInfo()
            {
/* 367*/        if(varTag == 7)
                {
/* 368*/            writer.VerifyTypeInfo(7, varData);
/* 368*/            return;
                }
/* 369*/        if(varTag == 8)
                {
/* 370*/            writer.VerifyTypeInfo(8, varData);
/* 370*/            return;
                } else
                {
/* 372*/            writer.VerifyTypeInfo(varTag, 0);
/* 373*/            return;
                }
            }

            private int varIndex;
            private int varTag;
            private int varData;

            (StackMap stackmap, int i, int j, int k)
            {
/* 342*/        super(stackmap);
/* 343*/        varIndex = i;
/* 344*/        varTag = j;
/* 345*/        varData = k;
            }
}
