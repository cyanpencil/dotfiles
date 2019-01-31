// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeTag.java

package javassist.bytecode.stackmap;


// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public interface TypeTag
{

            public static final String TOP_TYPE = "*top*";
            public static final TypeData TOP = new TypeData.BasicType("*top*", 0);
            public static final TypeData INTEGER = new TypeData.BasicType("int", 1);
            public static final TypeData FLOAT = new TypeData.BasicType("float", 2);
            public static final TypeData DOUBLE = new TypeData.BasicType("double", 3);
            public static final TypeData LONG = new TypeData.BasicType("long", 4);

}
