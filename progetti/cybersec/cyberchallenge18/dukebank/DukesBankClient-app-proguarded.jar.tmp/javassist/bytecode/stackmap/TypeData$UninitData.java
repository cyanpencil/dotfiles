// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public static class initialized extends initialized
{

            public initialized copy()
            {
/* 730*/        return new <init>(offset, getName());
            }

            public int getTypeTag()
            {
/* 733*/        return 8;
            }

            public int getTypeData(ConstPool constpool)
            {
/* 737*/        return offset;
            }

            public TypeData join()
            {
/* 741*/        if(initialized)
/* 742*/            return new it>(new init>(getName()));
/* 744*/        else
/* 744*/            return new ar(copy());
            }

            public boolean isUninit()
            {
/* 747*/        return true;
            }

            public boolean eq(TypeData typedata)
            {
/* 750*/        if(typedata instanceof copy)
                {
/* 751*/            typedata = (copy)typedata;
/* 752*/            return offset == ((offset) (typedata)).offset && getName().equals(typedata.getName());
                } else
                {
/* 755*/            return false;
                }
            }

            public String toString()
            {
/* 758*/        return (new StringBuilder("uninit:")).append(getName()).append("@").append(offset).toString();
            }

            public int offset()
            {
/* 760*/        return offset;
            }

            public void constructorCalled(int i)
            {
/* 763*/        if(i == offset)
/* 764*/            initialized = true;
            }

            int offset;
            boolean initialized;

            ar(int i, String s)
            {
/* 725*/        super(s);
/* 726*/        offset = i;
/* 727*/        initialized = false;
            }
}
