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

            public final Class onClass(Class class1, Void void1)
            {
/* 268*/        return class1;
            }

            public final Class onParameterizdType(ParameterizedType parameterizedtype, Void void1)
            {
/* 273*/        return (Class)visit(parameterizedtype.getRawType(), null);
            }

            public final Class onGenericArray(GenericArrayType genericarraytype, Void void1)
            {
/* 277*/        return Array.newInstance((Class)visit(genericarraytype.getGenericComponentType(), null), 0).getClass();
            }

            public final Class onVariable(TypeVariable typevariable, Void void1)
            {
/* 283*/        return (Class)visit(typevariable.getBounds()[0], null);
            }

            public final Class onWildcard(WildcardType wildcardtype, Void void1)
            {
/* 287*/        return (Class)visit(wildcardtype.getUpperBounds()[0], null);
            }

            public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
            {
/* 266*/        return onWildcard(wildcardtype, (Void)obj);
            }

            public final volatile Object onVariable(TypeVariable typevariable, Object obj)
            {
/* 266*/        return onVariable(typevariable, (Void)obj);
            }

            public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
            {
/* 266*/        return onGenericArray(genericarraytype, (Void)obj);
            }

            public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
            {
/* 266*/        return onParameterizdType(parameterizedtype, (Void)obj);
            }

            public final volatile Object onClass(Class class1, Object obj)
            {
/* 266*/        return onClass(class1, (Void)obj);
            }

            dType()
            {
            }
}
