// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public static class name extends TypeData
{

            public String getName()
            {
/* 681*/        return name;
            }

            public name isBasicType()
            {
/* 684*/        return null;
            }

            public boolean is2WordType()
            {
/* 686*/        return false;
            }

            public int getTypeTag()
            {
/* 688*/        return 7;
            }

            public int getTypeData(ConstPool constpool)
            {
/* 691*/        return constpool.addClassInfo(getName());
            }

            public boolean eq(TypeData typedata)
            {
/* 694*/        return name.equals(typedata.getName());
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
            }

            private String name;

            public (String s)
            {
/* 677*/        name = s;
            }
}
