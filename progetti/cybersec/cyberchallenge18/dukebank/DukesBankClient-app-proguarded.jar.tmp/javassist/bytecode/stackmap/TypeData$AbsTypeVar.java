// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public static abstract class  extends TypeData
{

            public abstract void merge(TypeData typedata);

            public int getTypeTag()
            {
/* 144*/        return 7;
            }

            public int getTypeData(ConstPool constpool)
            {
/* 147*/        return constpool.addClassInfo(getName());
            }

            public boolean eq(TypeData typedata)
            {
/* 150*/        return getName().equals(typedata.getName());
            }

            public ()
            {
            }
}
