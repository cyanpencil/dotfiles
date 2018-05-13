// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Types.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;

// Referenced classes of package org.jvnet.tiger_types:
//            ParameterizedTypeImpl, TypeVisitor, GenericArrayTypeImpl, WildcardTypeImpl

public class Types
{
    static class BinderArg
    {

                Type replace(TypeVariable typevariable)
                {
/* 142*/            for(int i = 0; i < params.length; i++)
/* 143*/                if(params[i].equals(typevariable))
/* 144*/                    return args[i];

/* 145*/            return typevariable;
                }

                final TypeVariable params[];
                final Type args[];
                static final boolean $assertionsDisabled = !org/jvnet/tiger_types/Types.desiredAssertionStatus();


                BinderArg(TypeVariable atypevariable[], Type atype[])
                {
/* 132*/            params = atypevariable;
/* 133*/            args = atype;
/* 134*/            if(!$assertionsDisabled && atypevariable.length != atype.length)
/* 134*/                throw new AssertionError();
/* 135*/            else
/* 135*/                return;
                }

                public BinderArg(GenericDeclaration genericdeclaration, Type atype[])
                {
/* 138*/            this(genericdeclaration.getTypeParameters(), atype);
                }
    }


            public Types()
            {
            }

            public static Type getBaseClass(Type type, Class class1)
            {
/* 236*/        return (Type)baseClassFinder.visit(type, class1);
            }

            public static String getTypeName(Type type)
            {
/* 246*/        if(type instanceof Class)
                {
/* 247*/            if((type = (Class)type).isArray())
/* 249*/                return (new StringBuilder()).append(getTypeName(((Type) (type.getComponentType())))).append("[]").toString();
/* 250*/            else
/* 250*/                return type.getName();
                } else
                {
/* 252*/            return type.toString();
                }
            }

            public static boolean isSubClassOf(Type type, Type type1)
            {
/* 259*/        return erasure(type1).isAssignableFrom(erasure(type));
            }

            public static Class erasure(Type type)
            {
/* 302*/        return (Class)eraser.visit(type, null);
            }

            public static transient ParameterizedType createParameterizedType(Class class1, Type atype[])
            {
/* 309*/        return new ParameterizedTypeImpl(class1, atype, null);
            }

            public static boolean isArray(Type type)
            {
/* 316*/        if(type instanceof Class)
/* 317*/            return (type = (Class)type).isArray();
/* 320*/        return type instanceof GenericArrayType;
            }

            public static boolean isArrayButNotByteArray(Type type)
            {
/* 329*/        if(type instanceof Class)
/* 330*/            return (type = (Class)type).isArray() && type != [B;
/* 333*/        if(type instanceof GenericArrayType)
/* 334*/            return (type = ((GenericArrayType)type).getGenericComponentType()) != Byte.TYPE;
/* 337*/        else
/* 337*/            return false;
            }

            public static Type getComponentType(Type type)
            {
/* 347*/        if(type instanceof Class)
/* 348*/            return (type = (Class)type).getComponentType();
/* 351*/        if(type instanceof GenericArrayType)
/* 352*/            return ((GenericArrayType)type).getGenericComponentType();
/* 354*/        else
/* 354*/            throw new IllegalArgumentException();
            }

            public static Type getTypeArgument(Type type, int i)
            {
/* 366*/        if((type = getTypeArgument(type, i, null)) == null)
/* 368*/            throw new IllegalArgumentException();
/* 369*/        else
/* 369*/            return type;
            }

            public static Type getTypeArgument(Type type, int i, Type type1)
            {
/* 387*/        if(type instanceof ParameterizedType)
/* 388*/            return fix((type = (ParameterizedType)type).getActualTypeArguments()[i]);
/* 391*/        else
/* 391*/            return type1;
            }

            public static boolean isPrimitive(Type type)
            {
/* 398*/        if(type instanceof Class)
/* 399*/            return (type = (Class)type).isPrimitive();
/* 402*/        else
/* 402*/            return false;
            }

            public static boolean isOverriding(Method method, Class class1)
            {
                String s;
/* 416*/        s = method.getName();
/* 417*/        method = method.getParameterTypes();
_L3:
/* 419*/        if(class1 == null) goto _L2; else goto _L1
_L1:
/* 421*/        if(class1.getDeclaredMethod(s, method) != null)
/* 422*/            return true;
/* 425*/        continue; /* Loop/switch isn't completed */
/* 423*/        JVM INSTR pop ;
/* 427*/        class1 = class1.getSuperclass();
                  goto _L3
_L2:
/* 430*/        return false;
            }

            private static Type fix(Type type)
            {
/* 440*/        if(!(type instanceof GenericArrayType))
/* 441*/            return type;
                GenericArrayType genericarraytype;
/* 443*/        if((genericarraytype = (GenericArrayType)type).getGenericComponentType() instanceof Class)
/* 445*/            return Array.newInstance(type = (Class)genericarraytype.getGenericComponentType(), 0).getClass();
/* 449*/        else
/* 449*/            return type;
            }

            private static final TypeVisitor baseClassFinder = new TypeVisitor() {

                public final Type onClass(Class class1, Class class2)
                {
/*  61*/            if(class2 == class1)
/*  62*/                return class2;
                    Type type;
/*  66*/            if((type = class1.getGenericSuperclass()) != null && (type = (Type)visit(type, class2)) != null)
/*  69*/                return type;
/*  72*/            int i = (class1 = class1.getGenericInterfaces()).length;
/*  72*/            for(int j = 0; j < i; j++)
                    {
/*  72*/                Type type1 = class1[j];
/*  73*/                if((type1 = (Type)visit(type1, class2)) != null)
/*  74*/                    return type1;
                    }

/*  77*/            return null;
                }

                public final Type onParameterizdType(ParameterizedType parameterizedtype, Class class1)
                {
                    Class class2;
/*  81*/            if((class2 = (Class)parameterizedtype.getRawType()) == class1)
/*  84*/                return parameterizedtype;
                    Type type;
/*  87*/            if((type = class2.getGenericSuperclass()) != null)
/*  89*/                type = (Type)visit(bind(type, class2, parameterizedtype), class1);
/*  90*/            if(type != null)
/*  91*/                return type;
                    Type atype[];
/*  92*/            int i = (atype = class2.getGenericInterfaces()).length;
/*  92*/            for(int j = 0; j < i; j++)
                    {
/*  92*/                Type type1 = atype[j];
/*  93*/                if((type1 = (Type)visit(bind(type1, class2, parameterizedtype), class1)) != null)
/*  94*/                    return type1;
                    }

/*  96*/            return null;
                }

                public final Type onGenericArray(GenericArrayType genericarraytype, Class class1)
                {
/* 102*/            return null;
                }

                public final Type onVariable(TypeVariable typevariable, Class class1)
                {
/* 106*/            return (Type)visit(typevariable.getBounds()[0], class1);
                }

                public final Type onWildcard(WildcardType wildcardtype, Class class1)
                {
/* 111*/            return null;
                }

                private Type bind(Type type, GenericDeclaration genericdeclaration, ParameterizedType parameterizedtype)
                {
/* 123*/            return (Type)Types.binder.visit(type, new BinderArg(genericdeclaration, parameterizedtype.getActualTypeArguments()));
                }

                public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
                {
/*  58*/            return onWildcard(wildcardtype, (Class)obj);
                }

                public final volatile Object onVariable(TypeVariable typevariable, Object obj)
                {
/*  58*/            return onVariable(typevariable, (Class)obj);
                }

                public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
                {
/*  58*/            return onGenericArray(genericarraytype, (Class)obj);
                }

                public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
                {
/*  58*/            return onParameterizdType(parameterizedtype, (Class)obj);
                }

                public final volatile Object onClass(Class class1, Object obj)
                {
/*  58*/            return onClass(class1, (Class)obj);
                }

    };
            private static final TypeVisitor binder = new TypeVisitor() {

                public final Type onClass(Class class1, BinderArg binderarg)
                {
/* 150*/            return class1;
                }

                public final Type onParameterizdType(ParameterizedType parameterizedtype, BinderArg binderarg)
                {
/* 154*/            Type atype[] = parameterizedtype.getActualTypeArguments();
/* 156*/            boolean flag = false;
/* 157*/            for(int i = 0; i < atype.length; i++)
                    {
/* 158*/                Type type1 = atype[i];
/* 159*/                atype[i] = (Type)visit(type1, binderarg);
/* 160*/                flag |= type1 != atype[i];
                    }

                    Type type;
/* 163*/            if((type = parameterizedtype.getOwnerType()) != null)
/* 165*/                type = (Type)visit(type, binderarg);
/* 166*/            if(!(flag |= parameterizedtype.getOwnerType() != type))
/* 168*/                return parameterizedtype;
/* 170*/            else
/* 170*/                return new ParameterizedTypeImpl((Class)parameterizedtype.getRawType(), atype, type);
                }

                public final Type onGenericArray(GenericArrayType genericarraytype, BinderArg binderarg)
                {
/* 174*/            if((binderarg = (Type)visit(genericarraytype.getGenericComponentType(), binderarg)) == genericarraytype.getGenericComponentType())
/* 175*/                return genericarraytype;
/* 177*/            else
/* 177*/                return new GenericArrayTypeImpl(binderarg);
                }

                public final Type onVariable(TypeVariable typevariable, BinderArg binderarg)
                {
/* 181*/            return binderarg.replace(typevariable);
                }

                public final Type onWildcard(WildcardType wildcardtype, BinderArg binderarg)
                {
/* 188*/            Type atype[] = wildcardtype.getLowerBounds();
/* 189*/            Type atype1[] = wildcardtype.getUpperBounds();
/* 190*/            boolean flag = false;
/* 192*/            for(int i = 0; i < atype.length; i++)
                    {
/* 193*/                Type type = atype[i];
/* 194*/                atype[i] = (Type)visit(type, binderarg);
/* 195*/                flag |= type != atype[i];
                    }

/* 198*/            for(int j = 0; j < atype1.length; j++)
                    {
/* 199*/                Type type1 = atype1[j];
/* 200*/                atype1[j] = (Type)visit(type1, binderarg);
/* 201*/                flag |= type1 != atype1[j];
                    }

/* 204*/            if(!flag)
/* 204*/                return wildcardtype;
/* 206*/            else
/* 206*/                return new WildcardTypeImpl(atype, atype1);
                }

                public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
                {
/* 148*/            return onWildcard(wildcardtype, (BinderArg)obj);
                }

                public final volatile Object onVariable(TypeVariable typevariable, Object obj)
                {
/* 148*/            return onVariable(typevariable, (BinderArg)obj);
                }

                public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
                {
/* 148*/            return onGenericArray(genericarraytype, (BinderArg)obj);
                }

                public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
                {
/* 148*/            return onParameterizdType(parameterizedtype, (BinderArg)obj);
                }

                public final volatile Object onClass(Class class1, Object obj)
                {
/* 148*/            return onClass(class1, (BinderArg)obj);
                }

    };
            private static final TypeVisitor eraser = new TypeVisitor() {

                public final Class onClass(Class class1, Void void1)
                {
/* 268*/            return class1;
                }

                public final Class onParameterizdType(ParameterizedType parameterizedtype, Void void1)
                {
/* 273*/            return (Class)visit(parameterizedtype.getRawType(), null);
                }

                public final Class onGenericArray(GenericArrayType genericarraytype, Void void1)
                {
/* 277*/            return Array.newInstance((Class)visit(genericarraytype.getGenericComponentType(), null), 0).getClass();
                }

                public final Class onVariable(TypeVariable typevariable, Void void1)
                {
/* 283*/            return (Class)visit(typevariable.getBounds()[0], null);
                }

                public final Class onWildcard(WildcardType wildcardtype, Void void1)
                {
/* 287*/            return (Class)visit(wildcardtype.getUpperBounds()[0], null);
                }

                public final volatile Object onWildcard(WildcardType wildcardtype, Object obj)
                {
/* 266*/            return onWildcard(wildcardtype, (Void)obj);
                }

                public final volatile Object onVariable(TypeVariable typevariable, Object obj)
                {
/* 266*/            return onVariable(typevariable, (Void)obj);
                }

                public final volatile Object onGenericArray(GenericArrayType genericarraytype, Object obj)
                {
/* 266*/            return onGenericArray(genericarraytype, (Void)obj);
                }

                public final volatile Object onParameterizdType(ParameterizedType parameterizedtype, Object obj)
                {
/* 266*/            return onParameterizdType(parameterizedtype, (Void)obj);
                }

                public final volatile Object onClass(Class class1, Object obj)
                {
/* 266*/            return onClass(class1, (Void)obj);
                }

    };


}
