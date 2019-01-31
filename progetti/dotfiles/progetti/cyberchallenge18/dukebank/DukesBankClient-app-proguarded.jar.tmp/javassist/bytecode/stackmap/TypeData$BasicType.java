// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData, TypeTag

public static class typeTag extends TypeData
{

            public int getTypeTag()
            {
/* 110*/        return typeTag;
            }

            public int getTypeData(ConstPool constpool)
            {
/* 111*/        return 0;
            }

            public TypeData join()
            {
/* 114*/        if(this == TypeTag.TOP)
/* 115*/            return this;
/* 117*/        else
/* 117*/            return super.join();
            }

            public typeTag isBasicType()
            {
/* 120*/        return this;
            }

            public boolean is2WordType()
            {
/* 123*/        return typeTag == 4 || typeTag == 3;
            }

            public boolean eq(TypeData typedata)
            {
/* 127*/        return this == typedata;
            }

            public String getName()
            {
/* 130*/        return name;
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
/* 134*/        throw new BadBytecode((new StringBuilder("conflict: ")).append(name).append(" and ").append(s).toString());
            }

            public String toString()
            {
/* 137*/        return name;
            }

            private String name;
            private int typeTag;

            public (String s, int i)
            {
/* 106*/        name = s;
/* 107*/        typeTag = i;
            }
}
