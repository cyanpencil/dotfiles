// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeUtil.java

package com.owlike.genson.reflect;

import com.owlike.genson.Operations;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class TypeUtil
{
    static final class TypeAndRootClassKey
    {

                public final int hashCode()
                {
/* 585*/            return _hash;
                }

                public final boolean equals(Object obj)
                {
/* 590*/            if(this == obj)
/* 591*/                return true;
/* 592*/            if(obj == null)
/* 593*/                return false;
/* 594*/            if(!(obj instanceof TypeAndRootClassKey))
/* 595*/                return false;
/* 596*/            obj = (TypeAndRootClassKey)obj;
/* 597*/            return rootType.equals(((TypeAndRootClassKey) (obj)).rootType) && type.equals(((TypeAndRootClassKey) (obj)).type);
                }

                private final Type type;
                private final Type rootType;
                private int _hash;

                public TypeAndRootClassKey(Type type1, Type type2)
                {
/* 575*/            if(type1 == null || type2 == null)
                    {
/* 576*/                throw new IllegalArgumentException("type and rootType must be not null!");
                    } else
                    {
/* 577*/                type = type1;
/* 578*/                rootType = type2;
/* 579*/                _hash = 31 + type2.hashCode();
/* 580*/                _hash = 31 + type1.hashCode();
/* 581*/                return;
                    }
                }
    }

    static final class ExpandedParameterizedType extends ExpandedType
        implements ParameterizedType
    {

                public final Type[] getActualTypeArguments()
                {
/* 526*/            return typeArgs;
                }

                public final Type getOwnerType()
                {
/* 530*/            return ((ParameterizedType)originalType).getOwnerType();
                }

                public final Type getRawType()
                {
/* 534*/            return ((ParameterizedType)originalType).getRawType();
                }

                public final int hashCode()
                {
/* 539*/            return _hash;
                }

                public final boolean equals(Object obj)
                {
/* 544*/            if(this == obj)
/* 545*/                return true;
/* 546*/            if(!super.equals(obj))
/* 547*/                return false;
/* 548*/            if(getClass() != obj.getClass())
/* 549*/                return false;
/* 550*/            obj = (ExpandedParameterizedType)obj;
/* 551*/            return Arrays.equals(typeArgs, ((ExpandedParameterizedType) (obj)).typeArgs);
                }

                private final Type typeArgs[];
                private final int _hash;

                public ExpandedParameterizedType(ParameterizedType parameterizedtype, Class class1, Type atype[])
                {
/* 515*/            super(parameterizedtype, class1);
/* 516*/            if(atype == null)
                    {
/* 517*/                throw new IllegalArgumentException("Null arg not allowed!");
                    } else
                    {
/* 518*/                typeArgs = atype;
/* 521*/                parameterizedtype = super.hashCode();
/* 522*/                _hash = parameterizedtype * 31 + Arrays.hashCode(atype);
/* 523*/                return;
                    }
                }
    }

    static final class ExpandedGenericArrayType extends ExpandedType
        implements GenericArrayType
    {

                public final Type getGenericComponentType()
                {
/* 480*/            return componentType;
                }

                public final int hashCode()
                {
/* 485*/            return _hash;
                }

                public final boolean equals(Object obj)
                {
/* 490*/            if(this == obj)
/* 491*/                return true;
/* 492*/            if(!super.equals(obj))
/* 493*/                return false;
/* 494*/            if(getClass() != obj.getClass())
/* 495*/                return false;
/* 496*/            obj = (ExpandedGenericArrayType)obj;
/* 497*/            if(componentType == null)
                    {
/* 498*/                if(((ExpandedGenericArrayType) (obj)).componentType != null)
/* 499*/                    return false;
                    } else
/* 500*/            if(!componentType.equals(((ExpandedGenericArrayType) (obj)).componentType))
/* 501*/                return false;
/* 502*/            return true;
                }

                private final Type componentType;
                private final int _hash;

                public ExpandedGenericArrayType(GenericArrayType genericarraytype, Type type, Class class1)
                {
/* 469*/            super(genericarraytype, class1);
/* 470*/            if(type == null)
                    {
/* 471*/                throw new IllegalArgumentException("Null arg not allowed!");
                    } else
                    {
/* 472*/                componentType = type;
/* 475*/                genericarraytype = super.hashCode();
/* 476*/                _hash = genericarraytype * 31 + (type != null ? type.hashCode() : 0);
/* 477*/                return;
                    }
                }
    }

    static abstract class ExpandedType
    {

                public Type getOriginalType()
                {
/* 428*/            return originalType;
                }

                public Class getRootClass()
                {
/* 433*/            return rootClass;
                }

                public int hashCode()
                {
/* 438*/            return _hash;
                }

                public boolean equals(Object obj)
                {
/* 443*/            if(this == obj)
/* 444*/                return true;
/* 445*/            if(obj == null)
/* 446*/                return false;
/* 447*/            if(getClass() != obj.getClass())
/* 448*/                return false;
/* 450*/            obj = (ExpandedType)obj;
/* 451*/            if(originalType == null)
                    {
/* 452*/                if(((ExpandedType) (obj)).originalType != null)
/* 453*/                    return false;
                    } else
/* 454*/            if(!originalType.equals(((ExpandedType) (obj)).originalType))
/* 455*/                return false;
/* 456*/            return true;
                }

                protected final Type originalType;
                protected final Class rootClass;
                private final int _hash;

                private ExpandedType(Type type, Class class1)
                {
/* 416*/            if(type == null || class1 == null)
                    {
/* 417*/                throw new IllegalArgumentException("Null arg not allowed!");
                    } else
                    {
/* 418*/                originalType = type;
/* 419*/                rootClass = class1;
/* 423*/                _hash = 31 + (type != null ? type.hashCode() : 0);
/* 424*/                return;
                    }
                }

    }


            public TypeUtil()
            {
            }

            public static final Class wrap(Class class1)
            {
                Class class2;
/*  49*/        if((class2 = (Class)_wrappedPrimitives.get(class1)) == null)
/*  50*/            return class1;
/*  50*/        else
/*  50*/            return class2;
            }

            public static final Type expandType(Type type, Type type1)
            {
                Object obj;
/*  64*/        if((type instanceof ExpandedType) || (type instanceof Class))
/*  65*/            return type;
/*  67*/        if((obj = (Map)_circularExpandedType.get()) == null)
                {
/*  69*/            obj = new HashMap();
/*  70*/            _circularExpandedType.set(obj);
                }
/*  74*/        if(((Map) (obj)).containsKey(type))
/*  75*/            return (Type)((Map) (obj)).get(type);
                TypeAndRootClassKey typeandrootclasskey;
                Object obj1;
/*  78*/        ((Map) (obj)).put(type, getRawClass(type));
/*  79*/        typeandrootclasskey = new TypeAndRootClassKey(type, type1);
/*  80*/        if((obj1 = (Type)_cache.get(typeandrootclasskey)) != null)
/*  83*/            break MISSING_BLOCK_LABEL_487;
/*  83*/        if(!(type instanceof ParameterizedType)) goto _L2; else goto _L1
_L1:
                Object obj2;
                Type atype2[];
                Type atype[];
                int i;
/*  84*/        atype2 = new Type[i = (atype = ((ParameterizedType) (obj2 = (ParameterizedType)type)).getActualTypeArguments()).length];
/*  88*/        for(int j = 0; j < i; j++)
/*  89*/            atype2[j] = expandType(atype[j], type1);

/*  91*/        new ExpandedParameterizedType(((ParameterizedType) (obj2)), getRawClass(type1), atype2);
                  goto _L3
_L2:
/*  92*/        if(!(type instanceof TypeVariable)) goto _L5; else goto _L4
_L4:
/*  94*/        obj2 = (TypeVariable)type;
/*  95*/        if(type1 instanceof ParameterizedType)
                {
                    ParameterizedType parameterizedtype;
/*  96*/            Type atype1[] = (parameterizedtype = (ParameterizedType)type1).getActualTypeArguments();
/*  98*/            String s = ((TypeVariable) (obj2)).getName();
/*  99*/            int k = 0;
                    TypeVariable atypevariable[];
/* 100*/            int l = (atypevariable = genericDeclarationToClass(((TypeVariable) (obj2)).getGenericDeclaration()).getTypeParameters()).length;
/* 100*/            int i1 = 0;
/* 100*/            do
                    {
/* 100*/                if(i1 >= l)
/* 100*/                    break;
/* 100*/                TypeVariable typevariable = atypevariable[i1];
/* 102*/                if(s.equals(typevariable.getName()))
                        {
/* 103*/                    obj1 = atype1[k];
/* 104*/                    break;
                        }
/* 106*/                k++;
/* 100*/                i1++;
                    } while(true);
                } else
                {
/* 109*/            obj1 = resolveTypeVariable(((TypeVariable) (obj2)), getRawClass(type1));
                }
/* 111*/        if(type == obj1)
/* 112*/            obj1 = expandType(((TypeVariable) (obj2)).getBounds()[0], type1);
                  goto _L6
_L5:
/* 113*/        if(!(type instanceof GenericArrayType)) goto _L8; else goto _L7
_L7:
                Object obj3;
/* 114*/        obj3 = expandType(((GenericArrayType) (obj2 = (GenericArrayType)type)).getGenericComponentType(), type1);
/* 116*/        if(((GenericArrayType) (obj2)).getGenericComponentType() == obj3)
/* 117*/            obj3 = java/lang/Object;
/* 118*/        new ExpandedGenericArrayType(((GenericArrayType) (obj2)), ((Type) (obj3)), getRawClass(type1));
                  goto _L3
_L8:
/* 119*/        if(!(type instanceof WildcardType)) goto _L6; else goto _L9
_L9:
/* 120*/        ((WildcardType) (obj2 = (WildcardType)type)).getUpperBounds().length <= 0 ? java/lang/Object : expandType(((WildcardType) (obj2)).getUpperBounds()[0], type1);
_L3:
/* 126*/        obj1;
_L6:
/* 130*/        if(obj1 == null)
/* 131*/            throw new IllegalArgumentException((new StringBuilder("Type ")).append(type).append(" not supported for expansion!").toString());
/* 133*/        _cache.put(typeandrootclasskey, obj1);
/* 136*/        obj2 = obj1;
/* 138*/        ((Map) (obj)).remove(type);
/* 138*/        return ((Type) (obj2));
/* 138*/        type1;
/* 138*/        ((Map) (obj)).remove(type);
/* 138*/        throw type1;
            }

            public static final Type lookupGenericType(Class class1, Class class2)
            {
/* 158*/        do
                {
/* 158*/            if(class1 == null || class2 == null || !class1.isAssignableFrom(class2))
/* 159*/                return null;
/* 160*/            if(class1.equals(class2))
/* 161*/                return class2;
/* 163*/            if(class1.isInterface())
                    {
/* 165*/                Class aclass[] = class2.getInterfaces();
/* 167*/                for(int i = 0; i < aclass.length; i++)
                        {
/* 169*/                    if(class1.equals(aclass[i]))
/* 170*/                        return class2.getGenericInterfaces()[i];
                            Type type;
/* 172*/                    if((type = lookupGenericType(class1, aclass[i])) != null)
/* 174*/                        return type;
                        }

                    }
/* 180*/            Class class3 = class2.getSuperclass();
/* 181*/            if(class1.equals(class3))
/* 182*/                return class2.getGenericSuperclass();
/* 183*/            class2 = class2.getSuperclass();
/* 183*/            class1 = class1;
                } while(true);
            }

            public static final Class getRawClass(Type type)
            {
/* 187*/        do
                {
/* 187*/            if(type instanceof Class)
/* 188*/                return (Class)type;
/* 189*/            if(type instanceof ParameterizedType)
/* 190*/                return (Class)(type = (ParameterizedType)type).getRawType();
/* 192*/            if(type instanceof GenericArrayType)
/* 193*/                return Array.newInstance(getRawClass(type = ((GenericArrayType)type).getGenericComponentType()), 0).getClass();
/* 196*/            type = expand(type, null);
                } while(true);
            }

            public static final Type getCollectionType(Type type)
            {
/* 205*/        if(type instanceof GenericArrayType)
/* 206*/            return ((GenericArrayType)type).getGenericComponentType();
/* 207*/        if(type instanceof Class)
                {
/* 208*/            if((type = (Class)type).isArray())
/* 210*/                return type.getComponentType();
/* 211*/            if(java/util/Collection.isAssignableFrom(type))
/* 212*/                return java/lang/Object;
                } else
/* 214*/        if((type instanceof ParameterizedType) && java/util/Collection.isAssignableFrom(getRawClass(type)))
/* 215*/            return typeOf(0, type);
/* 218*/        throw new IllegalArgumentException("Could not extract parametrized type, are you sure it is a Collection or an Array?");
            }

            static final Type expand(Type type, Class class1)
            {
                WildcardType wildcardtype;
/* 225*/        if(type instanceof TypeVariable)
                {
/* 228*/            TypeVariable typevariable = (TypeVariable)type;
/* 229*/            if(class1 == null)
/* 230*/                class1 = genericDeclarationToClass(typevariable.getGenericDeclaration());
/* 231*/            class1 = resolveTypeVariable(typevariable, class1);
/* 232*/            if(type.equals(class1))
/* 233*/                class1 = typevariable.getBounds()[0];
                } else
/* 234*/        if(type instanceof WildcardType)
/* 235*/            class1 = ((Class) ((wildcardtype = (WildcardType)type).getUpperBounds().length <= 0 ? java/lang/Object : ((Class) (expand(wildcardtype.getUpperBounds()[0], class1)))));
/* 239*/        else
/* 239*/            return type;
/* 241*/        if(class1 == null || type.equals(class1))
/* 241*/            return java/lang/Object;
/* 241*/        else
/* 241*/            return class1;
            }

            public static final Type resolveTypeVariable(TypeVariable typevariable, Class class1)
            {
/* 252*/        return resolveTypeVariable(typevariable, genericDeclarationToClass(typevariable.getGenericDeclaration()), class1);
            }

            private static final Type resolveTypeVariable(TypeVariable typevariable, Class class1, Class class2)
            {
/* 258*/        if(class2 == null)
/* 259*/            return null;
/* 261*/        Class class3 = null;
/* 262*/        Object obj = null;
/* 263*/        Object obj1 = null;
/* 265*/        if(!class1.equals(class2))
                {
/* 266*/            if(class1.isInterface())
                    {
/* 268*/                Class aclass[] = class2.getInterfaces();
/* 269*/                for(int i = 0; i < aclass.length && obj == null; i++)
                        {
/* 270*/                    class3 = aclass[i];
/* 271*/                    obj = resolveTypeVariable(typevariable, class1, class3);
/* 272*/                    obj1 = class2.getGenericInterfaces()[i];
                        }

                    }
/* 276*/            if(obj == null)
                    {
/* 277*/                class3 = class2.getSuperclass();
/* 278*/                obj = resolveTypeVariable(typevariable, class1, class3);
/* 279*/                obj1 = class2.getGenericSuperclass();
                    }
                } else
                {
/* 282*/            obj = typevariable;
/* 283*/            obj1 = class3 = class2;
                }
/* 287*/        if(obj != null && (obj instanceof TypeVariable))
                {
/* 290*/            typevariable = (TypeVariable)obj;
/* 291*/            TypeVariable atypevariable[] = class3.getTypeParameters();
                    int j;
/* 292*/            for(j = 0; j < atypevariable.length && !typevariable.equals(atypevariable[j]); j++);
/* 297*/            if(j < atypevariable.length && (obj1 instanceof ParameterizedType))
                    {
/* 300*/                typevariable = (typevariable = (ParameterizedType)obj1).getActualTypeArguments();
/* 302*/                if(j < typevariable.length)
/* 302*/                    return typevariable[j];
/* 302*/                else
/* 302*/                    return null;
                    }
                }
/* 311*/        return ((Type) (obj));
            }

            public static final boolean match(Type type, Type type1, boolean flag)
            {
/* 319*/        if(type == null || type1 == null)
/* 320*/            return type == null && type1 == null;
/* 321*/        Class class1 = getRawClass(type);
/* 322*/        Class class2 = getRawClass(type1);
/* 323*/        boolean flag1 = flag ? class2.equals(class1) : class2.isAssignableFrom(class1);
/* 325*/        if(java/lang/Object.equals(class2) && !flag)
/* 326*/            return flag1;
/* 328*/        if(class1.isArray() && !class2.isArray())
/* 329*/            return flag1;
/* 331*/        type = getTypes(type);
/* 332*/        type1 = getTypes(type1);
/* 334*/        flag1 = flag1 && (type.length == type1.length || type.length == 0);
/* 336*/        for(int i = 0; i < type.length && flag1; i++)
/* 337*/            flag1 = match(type[i], type1[i], flag);

/* 339*/        return flag1;
            }

            public static final Type typeOf(int i, Type type)
            {
                Object obj;
                Object aobj[];
/* 348*/        if(type instanceof Class)
                {
/* 349*/            if((aobj = ((Class) (obj = (Class)type)).getTypeParameters()).length > i)
/* 352*/                return expandType(aobj[i], type);
                } else
/* 353*/        if((type instanceof ParameterizedType) && (aobj = ((ParameterizedType) (aobj = (ParameterizedType)type)).getActualTypeArguments()).length > i)
/* 357*/            return aobj[i];
/* 359*/        throw new UnsupportedOperationException((new StringBuilder("Couldn't find parameter at ")).append(i).append(" from type ").append(type).append(" , you should first locate the parameterized type, expand it and then use typeOf.").toString());
            }

            private static Class genericDeclarationToClass(GenericDeclaration genericdeclaration)
            {
/* 364*/        if(genericdeclaration instanceof Class)
/* 365*/            return (Class)genericdeclaration;
/* 366*/        if(genericdeclaration instanceof Method)
/* 367*/            return ((Method)genericdeclaration).getDeclaringClass();
/* 368*/        if(genericdeclaration instanceof Constructor)
/* 369*/            return ((Constructor)genericdeclaration).getDeclaringClass();
/* 370*/        else
/* 370*/            throw new UnsupportedOperationException();
            }

            private static final Type[] getTypes(Type type)
            {
/* 374*/        if(type instanceof Class)
                {
                    Class class1;
/* 375*/            if((class1 = (Class)type).isArray())
/* 377*/                return (new Type[] {
/* 377*/                    class1.getComponentType()
                        });
/* 379*/            Type atype[] = new Type[(type = ((Class)type).getTypeParameters()).length];
/* 381*/            int i = 0;
/* 382*/            int j = (type = type).length;
/* 382*/            for(int k = 0; k < j; k++)
                    {
/* 382*/                TypeVariable typevariable1 = type[k];
/* 383*/                atype[i++] = typevariable1.getBounds()[0];
                    }

/* 385*/            return atype;
                }
/* 387*/        if(type instanceof ParameterizedType)
/* 388*/            return ((ParameterizedType)type).getActualTypeArguments();
/* 389*/        if(type instanceof GenericArrayType)
/* 390*/            return (new Type[] {
/* 390*/                ((GenericArrayType)type).getGenericComponentType()
                    });
/* 391*/        if(type instanceof WildcardType)
/* 392*/            return (Type[])Operations.union([Ljava/lang/reflect/Type;, new Type[][] {
/* 392*/                ((WildcardType)type).getUpperBounds(), ((WildcardType)type).getLowerBounds()
                    });
/* 394*/        if(type instanceof TypeVariable)
                {
                    TypeVariable typevariable;
/* 396*/            type = resolveTypeVariable(typevariable = (TypeVariable)type, (Class)typevariable.getGenericDeclaration());
/* 398*/            if(typevariable.equals(type))
/* 398*/                return typevariable.getBounds();
/* 398*/            else
/* 398*/                return (new Type[] {
/* 398*/                    type
                        });
                } else
                {
/* 400*/            return new Type[0];
                }
            }

            private static final Map _wrappedPrimitives;
            private static final Map _cache = new ConcurrentHashMap(32);
            private static final ThreadLocal _circularExpandedType = new ThreadLocal();

            static 
            {
/*  31*/        (_wrappedPrimitives = new HashMap()).put(Integer.TYPE, java/lang/Integer);
/*  35*/        _wrappedPrimitives.put(Double.TYPE, java/lang/Double);
/*  36*/        _wrappedPrimitives.put(Long.TYPE, java/lang/Long);
/*  37*/        _wrappedPrimitives.put(Float.TYPE, java/lang/Float);
/*  38*/        _wrappedPrimitives.put(Short.TYPE, java/lang/Short);
/*  39*/        _wrappedPrimitives.put(Boolean.TYPE, java/lang/Boolean);
/*  40*/        _wrappedPrimitives.put(Character.TYPE, java/lang/Character);
/*  41*/        _wrappedPrimitives.put(Byte.TYPE, java/lang/Byte);
/*  42*/        _wrappedPrimitives.put(Void.TYPE, java/lang/Void);
            }
}
