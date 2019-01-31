// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsAttribute.java

package javassist.bytecode;

import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AnnotationsAttribute, ByteArray, ConstPool, Descriptor

static class classnames extends classnames
{

            int annotation(int i, int j, int k)
                throws Exception
            {
/* 429*/        renameType(i - 4, j);
/* 430*/        return super.nnotation(i, j, k);
            }

            void enumMemberValue(int i, int j, int k)
                throws Exception
            {
/* 436*/        renameType(i + 1, j);
/* 437*/        super.numMemberValue(i, j, k);
            }

            void classMemberValue(int i, int j)
                throws Exception
            {
/* 441*/        renameType(i + 1, j);
/* 442*/        super.lassMemberValue(i, j);
            }

            private void renameType(int i, int j)
            {
/* 446*/        String s = Descriptor.rename(j = cpool.getUtf8Info(j), classnames);
/* 448*/        if(!j.equals(s))
/* 449*/            ByteArray.write16bit(j = cpool.addUtf8Info(s), info, i);
            }

            ConstPool cpool;
            Map classnames;

            (byte abyte0[], ConstPool constpool, Map map)
            {
/* 423*/        super(abyte0);
/* 424*/        cpool = constpool;
/* 425*/        classnames = map;
            }
}
