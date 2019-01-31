// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiArrayType.java

package javassist.bytecode.analysis;

import javassist.*;

// Referenced classes of package javassist.bytecode.analysis:
//            Type, MultiType

public class MultiArrayType extends Type
{

            public MultiArrayType(MultiType multitype, int i)
            {
/*  32*/        super(null);
/*  33*/        component = multitype;
/*  34*/        dims = i;
            }

            public CtClass getCtClass()
            {
                Object obj;
                ClassPool classpool;
/*  38*/        if((obj = component.getCtClass()) == null)
/*  40*/            return null;
/*  42*/        if((classpool = ((CtClass) (obj)).getClassPool()) == null)
/*  44*/            classpool = ClassPool.getDefault();
/*  46*/        obj = arrayName(((CtClass) (obj)).getName(), dims);
/*  49*/        return classpool.get(((String) (obj)));
                NotFoundException notfoundexception;
/*  50*/        notfoundexception;
/*  51*/        throw new RuntimeException(notfoundexception);
            }

            boolean popChanged()
            {
/*  56*/        return component.popChanged();
            }

            public int getDimensions()
            {
/*  60*/        return dims;
            }

            public Type getComponent()
            {
/*  64*/        if(dims == 1)
/*  64*/            return component;
/*  64*/        else
/*  64*/            return new MultiArrayType(component, dims - 1);
            }

            public int getSize()
            {
/*  68*/        return 1;
            }

            public boolean isArray()
            {
/*  72*/        return true;
            }

            public boolean isAssignableFrom(Type type)
            {
/*  76*/        throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isReference()
            {
/*  80*/        return true;
            }

            public boolean isAssignableTo(Type type)
            {
/*  84*/        if(eq(type.getCtClass(), Type.OBJECT.getCtClass()))
/*  85*/            return true;
/*  87*/        if(eq(type.getCtClass(), Type.CLONEABLE.getCtClass()))
/*  88*/            return true;
/*  90*/        if(eq(type.getCtClass(), Type.SERIALIZABLE.getCtClass()))
/*  91*/            return true;
/*  93*/        if(!type.isArray())
/*  94*/            return false;
/*  96*/        Type type1 = getRootComponent(type);
/*  97*/        if((type = type.getDimensions()) > dims)
/* 100*/            return false;
/* 102*/        if(type < dims)
                {
/* 103*/            if(eq(type1.getCtClass(), Type.OBJECT.getCtClass()))
/* 104*/                return true;
/* 106*/            if(eq(type1.getCtClass(), Type.CLONEABLE.getCtClass()))
/* 107*/                return true;
/* 109*/            return eq(type1.getCtClass(), Type.SERIALIZABLE.getCtClass());
                } else
                {
/* 115*/            return component.isAssignableTo(type1);
                }
            }

            public boolean equals(Object obj)
            {
/* 119*/        if(!(obj instanceof MultiArrayType))
/* 120*/            return false;
/* 121*/        obj = (MultiArrayType)obj;
/* 123*/        return component.equals(((MultiArrayType) (obj)).component) && dims == ((MultiArrayType) (obj)).dims;
            }

            public String toString()
            {
/* 128*/        return arrayName(component.toString(), dims);
            }

            private MultiType component;
            private int dims;
}
