// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Types.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;

// Referenced classes of package org.jvnet.tiger_types:
//            TypeVisitor, Types

static class dType extends TypeVisitor
{

            public final Type onClass(Class class1, Class class2)
            {
/*  61*/        if(class2 == class1)
/*  62*/            return class2;
                Type type;
/*  66*/        if((type = class1.getGenericSuperclass()) != null && (type = (Type)visit(type, class2)) != null)
/*  69*/            return type;
/*  72*/        int i = (class1 = class1.getGenericInterfaces()).length;
/*  72*/        for(int j = 0; j < i; j++)
                {
/*  72*/            Type type1 = class1[j];
/*  73*/            if((type1 = (Type)visit(type1, class2)) != null)
/*  74*/                return type1;
                }

/*  77*/        return null;
            }

            public final Type onParameterizdType(ParameterizedType parameterizedtype, Class class1)
            {
                Class class2;
/*  81*/        if((class2 = (Class)parameterizedtype.getRawType()) == class1)
/*  84*/            return parameterizedtype;
                Type type;
/*  87*/        if((type = class2.getGenericSuperclass()) != null)
/*  89*/            type = (Type)visit(bind(type, class2, parameterizedtype), class1);
/*  90*/        if(type != null)
/*  91*/            return type;
                Type atype[];
/*  92*/        int i = (atype = class2.getGenericInterfaces()).length;
/*  92*/        for(int j = 0; j < i; j++)
                {
/*  92*/            Type type1 = atype[j];
/*  93*/            if((type1 = (Type)visit(bind(type1, class2, parameterizedtype), class1)) != null)
/*  94*/                return type1;
                }

/*  96*/        return null;
            }

            public final Type onGenericArray(GenericArrayType genericarraytype, Class class1)
            {
/* 102*/        return null;
            }

            public final Type onVariable(TypeVariable typevariable, Class class1)
            {
/* 106*/        return (Type)visit(typevariable.getBounds()[0], class1);
            }

            public final Type onWildcard(WildcardType wildcardtype, Class class1)
            {
/* 111*/        return null;
            }

            private Type bind(Type type, GenericDeclaration genericdeclaration, ParameterizedType parameterizedtype)
            {
/* 123*/        return (Type)Types.access$000().visit(type, new nderArg(genericdeclaration, parameterizedtype.getActualTypeArguments()));
            }

            public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
            {
/*  58*/        return onWildcard(wildcardtype, (Class)obj);
            }

            public final volatile Object onVariable(TypeVariable typevariable, Object obj)
            {
/*  58*/        return onVariable(typevariable, (Class)obj);
            }

            public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
            {
/*  58*/        return onGenericArray(genericarraytype, (Class)obj);
            }

            public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
            {
/*  58*/        return onParameterizdType(parameterizedtype, (Class)obj);
            }

            public final volatile Object onClass(Class class1, Object obj)
            {
/*  58*/        return onClass(class1, (Class)obj);
            }

            dType()
            {
            }
}
