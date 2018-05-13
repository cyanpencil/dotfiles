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

public static class type extends type
{

            public int getTypeTag()
            {
/* 638*/        return type.getTypeTag();
            }

            public int getTypeData(ConstPool constpool)
            {
/* 639*/        return type.getTypeData(constpool);
            }

            public type isBasicType()
            {
/* 640*/        return type.isBasicType();
            }

            public boolean is2WordType()
            {
/* 641*/        return type.is2WordType();
            }

            public boolean isUninit()
            {
/* 642*/        return type.isUninit();
            }

            public boolean eq(TypeData typedata)
            {
/* 643*/        return type.eq(typedata);
            }

            public String getName()
            {
/* 644*/        return type.getName();
            }

            protected type toTypeVar()
            {
/* 646*/        return null;
            }

            public TypeData join()
            {
/* 647*/        return type.join();
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
/* 650*/        type.setType(s, classpool);
            }

            public void merge(TypeData typedata)
            {
/* 654*/        if(!typedata.eq(type))
/* 655*/            type = TypeTag.TOP;
            }

            public void constructorCalled(int i)
            {
/* 659*/        type.constructorCalled(i);
            }

            public int offset()
            {
/* 663*/        if(type instanceof type)
/* 664*/            return ((type)type).set;
/* 666*/        else
/* 666*/            throw new RuntimeException("not available");
            }

            protected TypeData type;

            public ( )
            {
/* 637*/        type = ;
            }
}
