// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeChecker.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.*;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

public class TypeChecker
{

            public TypeChecker()
            {
            }

            public static boolean isRawTypeSafe(Type type, Type type1)
            {
                Class class1;
/*  68*/        if((class1 = ReflectionHelper.getRawClass(type)) == null)
/*  70*/            return false;
/*  72*/        class1 = ReflectionHelper.translatePrimitiveType(class1);
                Class class2;
/*  74*/        if((class2 = ReflectionHelper.getRawClass(type1)) == null)
/*  76*/            return false;
/*  78*/        class2 = ReflectionHelper.translatePrimitiveType(class2);
/*  80*/        if(!class1.isAssignableFrom(class2))
/*  81*/            return false;
/*  84*/        if((type instanceof Class) || (type instanceof GenericArrayType))
/*  87*/            return true;
/*  90*/        if(!(type instanceof ParameterizedType))
/*  91*/            throw new IllegalArgumentException((new StringBuilder("requiredType ")).append(type).append(" is of unknown type").toString());
/*  96*/        type = (type = (ParameterizedType)type).getActualTypeArguments();
/* 100*/        if(type1 instanceof Class)
/* 101*/            type1 = ((Class)type1).getTypeParameters();
/* 103*/        else
/* 103*/        if(type1 instanceof ParameterizedType)
/* 104*/            type1 = ((ParameterizedType)type1).getActualTypeArguments();
/* 107*/        else
/* 107*/            throw new IllegalArgumentException((new StringBuilder("Uknown beanType ")).append(type1).toString());
/* 110*/        if(type.length != type1.length)
/* 112*/            return false;
/* 115*/        for(int i = 0; i < type.length; i++)
                {
/* 116*/            Object obj = type[i];
/* 117*/            Object obj1 = type1[i];
/* 119*/            if(isActualType(((Type) (obj))) && isActualType(((Type) (obj1))))
                    {
/* 120*/                if(!isRawTypeSafe(((Type) (obj)), ((Type) (obj1))))
/* 120*/                    return false;
/* 122*/                continue;
                    }
/* 122*/            if(isArrayType(((Type) (obj))) && isArrayType(((Type) (obj1))))
                    {
/* 123*/                obj = getArrayType(((Type) (obj)));
/* 124*/                obj1 = getArrayType(((Type) (obj1)));
/* 126*/                if(!isRawTypeSafe(((Type) (obj)), ((Type) (obj1))))
/* 126*/                    return false;
/* 127*/                continue;
                    }
/* 128*/            if(isWildcard(((Type) (obj))) && isActualType(((Type) (obj1))))
                    {
/* 129*/                obj = getWildcard(((Type) (obj)));
/* 130*/                obj1 = ReflectionHelper.getRawClass(((Type) (obj1)));
/* 132*/                if(!isWildcardActualSafe(((WildcardType) (obj)), ((Class) (obj1))))
/* 132*/                    return false;
/* 133*/                continue;
                    }
/* 134*/            if(isWildcard(((Type) (obj))) && isTypeVariable(((Type) (obj1))))
                    {
/* 135*/                obj = getWildcard(((Type) (obj)));
/* 136*/                obj1 = getTypeVariable(((Type) (obj1)));
/* 138*/                if(!isWildcardTypeVariableSafe(((WildcardType) (obj)), ((TypeVariable) (obj1))))
/* 138*/                    return false;
/* 139*/                continue;
                    }
/* 140*/            if(isActualType(((Type) (obj))) && isTypeVariable(((Type) (obj1))))
                    {
/* 141*/                obj = ReflectionHelper.getRawClass(((Type) (obj)));
/* 142*/                obj1 = getTypeVariable(((Type) (obj1)));
/* 144*/                if(!isActualTypeVariableSafe(((Class) (obj)), ((TypeVariable) (obj1))))
/* 144*/                    return false;
/* 145*/                continue;
                    }
/* 146*/            if(isTypeVariable(((Type) (obj))) && isTypeVariable(((Type) (obj1))))
                    {
/* 147*/                obj = getTypeVariable(((Type) (obj)));
/* 148*/                obj1 = getTypeVariable(((Type) (obj1)));
/* 150*/                if(!isTypeVariableTypeVariableSafe(((TypeVariable) (obj)), ((TypeVariable) (obj1))))
/* 150*/                    return false;
                    } else
                    {
/* 154*/                return false;
                    }
                }

/* 158*/        return true;
            }

            private static boolean isTypeVariableTypeVariableSafe(TypeVariable typevariable, TypeVariable typevariable1)
            {
/* 162*/        if((typevariable = getBound(typevariable.getBounds())) == null)
/* 165*/            return false;
/* 168*/        if((typevariable1 = getBound(typevariable1.getBounds())) == null)
/* 171*/            return false;
/* 174*/        return typevariable1.isAssignableFrom(typevariable);
            }

            private static boolean isActualTypeVariableSafe(Class class1, TypeVariable typevariable)
            {
/* 182*/        if((typevariable = getBound(typevariable.getBounds())) == null)
/* 185*/            return false;
/* 188*/        return class1.isAssignableFrom(typevariable);
            }

            private static boolean isWildcardTypeVariableSafe(WildcardType wildcardtype, TypeVariable typevariable)
            {
/* 196*/        if((typevariable = getBound(typevariable.getBounds())) == null)
/* 199*/            return false;
                Class class1;
/* 202*/        if((class1 = getBound(wildcardtype.getUpperBounds())) == null)
/* 205*/            return false;
/* 208*/        if(!class1.isAssignableFrom(typevariable))
/* 209*/            return false;
/* 212*/        if((wildcardtype = getBound(wildcardtype.getLowerBounds())) == null)
/* 214*/            return true;
/* 217*/        return typevariable.isAssignableFrom(wildcardtype);
            }

            private static Class getBound(Type atype[])
            {
/* 225*/        if(atype == null)
/* 225*/            return null;
/* 226*/        if(atype.length <= 0)
/* 226*/            return null;
/* 227*/        if(atype.length > 1)
/* 228*/            throw new AssertionError("Do not understand multiple bounds");
/* 231*/        else
/* 231*/            return ReflectionHelper.getRawClass(atype[0]);
            }

            private static boolean isWildcardActualSafe(WildcardType wildcardtype, Class class1)
            {
                Class class2;
/* 235*/        if((class2 = getBound(wildcardtype.getUpperBounds())) == null)
/* 238*/            return false;
/* 241*/        if(!class2.isAssignableFrom(class1))
/* 242*/            return false;
/* 245*/        if((wildcardtype = getBound(wildcardtype.getLowerBounds())) == null)
/* 247*/            return true;
/* 250*/        return class1.isAssignableFrom(wildcardtype);
            }

            private static WildcardType getWildcard(Type type)
            {
/* 258*/        if(type == null)
/* 258*/            return null;
/* 260*/        if(type instanceof WildcardType)
/* 261*/            return (WildcardType)type;
/* 264*/        else
/* 264*/            return null;
            }

            private static TypeVariable getTypeVariable(Type type)
            {
/* 268*/        if(type == null)
/* 268*/            return null;
/* 270*/        if(type instanceof TypeVariable)
/* 271*/            return (TypeVariable)type;
/* 274*/        else
/* 274*/            return null;
            }

            private static boolean isWildcard(Type type)
            {
/* 278*/        if(type == null)
/* 278*/            return false;
/* 280*/        else
/* 280*/            return type instanceof WildcardType;
            }

            private static boolean isTypeVariable(Type type)
            {
/* 284*/        if(type == null)
/* 284*/            return false;
/* 286*/        else
/* 286*/            return type instanceof TypeVariable;
            }

            private static boolean isActualType(Type type)
            {
/* 296*/        if(type == null)
/* 296*/            return false;
/* 298*/        return (type instanceof Class) || (type instanceof ParameterizedType);
            }

            private static boolean isArrayType(Type type)
            {
/* 310*/        if(type == null)
/* 310*/            return false;
/* 312*/        if(type instanceof Class)
/* 313*/            return (type = (Class)type).isArray();
/* 317*/        else
/* 317*/            return type instanceof GenericArrayType;
            }

            private static Type getArrayType(Type type)
            {
/* 328*/        if(type == null)
/* 328*/            return null;
/* 330*/        if(type instanceof Class)
/* 331*/            return (type = (Class)type).getComponentType();
/* 335*/        if(type instanceof GenericArrayType)
/* 336*/            return (type = (GenericArrayType)type).getGenericComponentType();
/* 340*/        else
/* 340*/            return null;
            }
}
