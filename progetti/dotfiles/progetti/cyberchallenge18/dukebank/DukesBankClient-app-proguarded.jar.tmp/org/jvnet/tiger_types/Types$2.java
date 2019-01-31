// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Types.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;

// Referenced classes of package org.jvnet.tiger_types:
//            TypeVisitor, GenericArrayTypeImpl, ParameterizedTypeImpl, Types, 
//            WildcardTypeImpl

static class dType extends TypeVisitor
{

            public final Type onClass(Class class1, nderArg nderarg)
            {
/* 150*/        return class1;
            }

            public final Type onParameterizdType(ParameterizedType parameterizedtype, nderArg nderarg)
            {
/* 154*/        Type atype[] = parameterizedtype.getActualTypeArguments();
/* 156*/        boolean flag = false;
/* 157*/        for(int i = 0; i < atype.length; i++)
                {
/* 158*/            Type type1 = atype[i];
/* 159*/            atype[i] = (Type)visit(type1, nderarg);
/* 160*/            flag |= type1 != atype[i];
                }

                Type type;
/* 163*/        if((type = parameterizedtype.getOwnerType()) != null)
/* 165*/            type = (Type)visit(type, nderarg);
/* 166*/        if(!(flag |= parameterizedtype.getOwnerType() != type))
/* 168*/            return parameterizedtype;
/* 170*/        else
/* 170*/            return new ParameterizedTypeImpl((Class)parameterizedtype.getRawType(), atype, type);
            }

            public final Type onGenericArray(GenericArrayType genericarraytype, nderArg nderarg)
            {
/* 174*/        if((nderarg = (Type)visit(genericarraytype.getGenericComponentType(), nderarg)) == genericarraytype.getGenericComponentType())
/* 175*/            return genericarraytype;
/* 177*/        else
/* 177*/            return new GenericArrayTypeImpl(nderarg);
            }

            public final Type onVariable(TypeVariable typevariable, nderArg nderarg)
            {
/* 181*/        return nderarg.replace(typevariable);
            }

            public final Type onWildcard(WildcardType wildcardtype, nderArg nderarg)
            {
/* 188*/        Type atype[] = wildcardtype.getLowerBounds();
/* 189*/        Type atype1[] = wildcardtype.getUpperBounds();
/* 190*/        boolean flag = false;
/* 192*/        for(int i = 0; i < atype.length; i++)
                {
/* 193*/            Type type = atype[i];
/* 194*/            atype[i] = (Type)visit(type, nderarg);
/* 195*/            flag |= type != atype[i];
                }

/* 198*/        for(int j = 0; j < atype1.length; j++)
                {
/* 199*/            Type type1 = atype1[j];
/* 200*/            atype1[j] = (Type)visit(type1, nderarg);
/* 201*/            flag |= type1 != atype1[j];
                }

/* 204*/        if(!flag)
/* 204*/            return wildcardtype;
/* 206*/        else
/* 206*/            return new WildcardTypeImpl(atype, atype1);
            }

            public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
            {
/* 148*/        return onWildcard(wildcardtype, (nderArg)obj);
            }

            public final volatile Object onVariable(TypeVariable typevariable, Object obj)
            {
/* 148*/        return onVariable(typevariable, (nderArg)obj);
            }

            public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
            {
/* 148*/        return onGenericArray(genericarraytype, (nderArg)obj);
            }

            public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
            {
/* 148*/        return onParameterizdType(parameterizedtype, (nderArg)obj);
            }

            public final volatile Object onClass(Class class1, Object obj)
            {
/* 148*/        return onClass(class1, (nderArg)obj);
            }

            dType()
            {
            }
}
