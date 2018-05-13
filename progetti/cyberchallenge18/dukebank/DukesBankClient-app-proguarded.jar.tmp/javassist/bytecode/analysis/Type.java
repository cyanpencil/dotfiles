// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Type.java

package javassist.bytecode.analysis;

import java.util.*;
import javassist.*;

// Referenced classes of package javassist.bytecode.analysis:
//            MultiArrayType, MultiType

public class Type
{

            public static Type get(CtClass ctclass)
            {
                Type type;
/* 127*/        if((type = (Type)prims.get(ctclass)) != null)
/* 128*/            return type;
/* 128*/        else
/* 128*/            return new Type(ctclass);
            }

            private static Type lookupType(String s)
            {
/* 133*/        return new Type(ClassPool.getDefault().get(s));
/* 134*/        s;
/* 135*/        throw new RuntimeException(s);
            }

            Type(CtClass ctclass)
            {
/* 140*/        this(ctclass, false);
            }

            private Type(CtClass ctclass, boolean flag)
            {
/* 144*/        clazz = ctclass;
/* 145*/        special = flag;
            }

            boolean popChanged()
            {
/* 150*/        return false;
            }

            public int getSize()
            {
/* 160*/        return clazz != CtClass.doubleType && clazz != CtClass.longType && this != TOP ? 1 : 2;
            }

            public CtClass getCtClass()
            {
/* 169*/        return clazz;
            }

            public boolean isReference()
            {
/* 178*/        return !special && (clazz == null || !clazz.isPrimitive());
            }

            public boolean isSpecial()
            {
/* 188*/        return special;
            }

            public boolean isArray()
            {
/* 197*/        return clazz != null && clazz.isArray();
            }

            public int getDimensions()
            {
/* 207*/        if(!isArray())
/* 207*/            return 0;
                String s;
/* 209*/        int i = (s = clazz.getName()).length() - 1;
                int j;
/* 211*/        for(j = 0; s.charAt(i) == ']'; j++)
/* 213*/            i -= 2;

/* 217*/        return j;
            }

            public Type getComponent()
            {
/* 227*/        if(clazz == null || !clazz.isArray())
/* 228*/            return null;
                CtClass ctclass;
/* 232*/        try
                {
/* 232*/            ctclass = clazz.getComponentType();
                }
/* 233*/        catch(NotFoundException notfoundexception)
                {
/* 234*/            throw new RuntimeException(notfoundexception);
                }
                Type type;
/* 237*/        if((type = (Type)prims.get(ctclass)) != null)
/* 238*/            return type;
/* 238*/        else
/* 238*/            return new Type(ctclass);
            }

            public boolean isAssignableFrom(Type type)
            {
/* 250*/        if(this == type)
/* 251*/            return true;
/* 253*/        if(type == UNINIT && isReference() || this == UNINIT && type.isReference())
/* 254*/            return true;
/* 256*/        if(type instanceof MultiType)
/* 257*/            return ((MultiType)type).isAssignableTo(this);
/* 259*/        if(type instanceof MultiArrayType)
/* 260*/            return ((MultiArrayType)type).isAssignableTo(this);
/* 264*/        if(clazz == null || clazz.isPrimitive())
/* 265*/            return false;
/* 268*/        return type.clazz.subtypeOf(clazz);
/* 269*/        type;
/* 270*/        throw new RuntimeException(type);
            }

            public Type merge(Type type)
            {
/* 286*/        if(type == this)
/* 287*/            return this;
/* 288*/        if(type == null)
/* 289*/            return this;
/* 290*/        if(type == UNINIT)
/* 291*/            return this;
/* 292*/        if(this == UNINIT)
/* 293*/            return type;
/* 296*/        if(!type.isReference() || !isReference())
/* 297*/            return BOGUS;
/* 300*/        if(type instanceof MultiType)
/* 301*/            return type.merge(this);
/* 303*/        if(type.isArray() && isArray())
/* 304*/            return mergeArray(type);
/* 307*/        return mergeClasses(type);
/* 308*/        type;
/* 309*/        throw new RuntimeException(type);
            }

            Type getRootComponent(Type type)
            {
/* 314*/        for(; type.isArray(); type = type.getComponent());
/* 317*/        return type;
            }

            private Type createArray(Type type, int i)
            {
/* 321*/        if(type instanceof MultiType)
/* 322*/            return new MultiArrayType((MultiType)type, i);
/* 324*/        i = arrayName(type.clazz.getName(), i);
/* 328*/        try
                {
/* 328*/            type = get(getClassPool(type).get(i));
                }
                // Misplaced declaration of an exception variable
/* 329*/        catch(Type type)
                {
/* 330*/            throw new RuntimeException(type);
                }
/* 333*/        return type;
            }

            String arrayName(String s, int i)
            {
                int j;
/* 339*/        char ac[] = new char[i = (j = s.length()) + (i << 1)];
/* 342*/        s.getChars(0, j, ac, 0);
/* 343*/        while(j < i) 
                {
/* 344*/            ac[j++] = '[';
/* 345*/            ac[j++] = ']';
                }
/* 347*/        return s = new String(ac);
            }

            private ClassPool getClassPool(Type type)
            {
/* 352*/        if((type = type.clazz.getClassPool()) != null)
/* 353*/            return type;
/* 353*/        else
/* 353*/            return ClassPool.getDefault();
            }

            private Type mergeArray(Type type)
            {
/* 357*/        Type type1 = getRootComponent(type);
/* 358*/        Type type2 = getRootComponent(this);
/* 359*/        type = type.getDimensions();
/* 360*/        int i = getDimensions();
/* 363*/        if(type == i)
/* 364*/            if((type1 = type2.merge(type1)) == BOGUS)
/* 369*/                return OBJECT;
/* 371*/            else
/* 371*/                return createArray(type1, i);
/* 377*/        if(type < i)
                {
/* 378*/            type1 = type1;
/* 379*/            type = type;
                } else
                {
/* 381*/            type1 = type2;
/* 382*/            type = i;
                }
/* 386*/        if(eq(CLONEABLE.clazz, type1.clazz) || eq(SERIALIZABLE.clazz, type1.clazz))
/* 387*/            return createArray(type1, type);
/* 389*/        else
/* 389*/            return createArray(OBJECT, type);
            }

            private static CtClass findCommonSuperClass(CtClass ctclass, CtClass ctclass1)
                throws NotFoundException
            {
/* 393*/        ctclass = ctclass;
/* 394*/        CtClass ctclass2 = ctclass1 = ctclass1;
/* 396*/        CtClass ctclass3 = ctclass;
/* 401*/        do
                {
/* 401*/            if(eq(ctclass, ctclass1) && ctclass.getSuperclass() != null)
/* 402*/                return ctclass;
/* 404*/            CtClass ctclass4 = ctclass.getSuperclass();
                    CtClass ctclass5;
/* 405*/            if((ctclass5 = ctclass1.getSuperclass()) == null)
                    {
/* 409*/                ctclass1 = ctclass2;
/* 410*/                break;
                    }
/* 413*/            if(ctclass4 == null)
                    {
/* 415*/                ctclass = ctclass3;
/* 416*/                ctclass3 = ctclass2;
/* 417*/                ctclass2 = ctclass;
/* 419*/                ctclass = ctclass1;
/* 420*/                ctclass1 = ctclass2;
/* 421*/                break;
                    }
/* 424*/            ctclass = ctclass4;
/* 425*/            ctclass1 = ctclass5;
                } while(true);
/* 430*/        while((ctclass = ctclass.getSuperclass()) != null) 
/* 434*/            ctclass3 = ctclass3.getSuperclass();
/* 437*/        for(ctclass = ctclass3; !eq(ctclass, ctclass1); ctclass1 = ctclass1.getSuperclass())
/* 442*/            ctclass = ctclass.getSuperclass();

/* 446*/        return ctclass;
            }

            private Type mergeClasses(Type type)
                throws NotFoundException
            {
                CtClass ctclass;
/* 450*/        if((ctclass = findCommonSuperClass(clazz, type.clazz)).getSuperclass() == null)
                {
/* 454*/            if((type = findCommonInterfaces(type)).size() == 1)
/* 456*/                return new Type((CtClass)type.values().iterator().next());
/* 457*/            if(type.size() > 1)
/* 458*/                return new MultiType(type);
/* 461*/            else
/* 461*/                return new Type(ctclass);
                }
/* 465*/        if((type = findExclusiveDeclaredInterfaces(type, ctclass)).size() > 0)
/* 467*/            return new MultiType(type, new Type(ctclass));
/* 470*/        else
/* 470*/            return new Type(ctclass);
            }

            private Map findCommonInterfaces(Type type)
            {
/* 474*/        type = getAllInterfaces(type.clazz, null);
/* 475*/        Map map = getAllInterfaces(clazz, null);
/* 477*/        return findCommonInterfaces(((Map) (type)), map);
            }

            private Map findExclusiveDeclaredInterfaces(Type type, CtClass ctclass)
            {
/* 481*/        type = getDeclaredInterfaces(type.clazz, null);
/* 482*/        Map map = getDeclaredInterfaces(clazz, null);
                Object obj;
/* 483*/        for(ctclass = (ctclass = getAllInterfaces(ctclass, null)).keySet().iterator(); ctclass.hasNext(); map.remove(obj))
                {
/* 487*/            obj = ctclass.next();
/* 488*/            type.remove(obj);
                }

/* 492*/        return findCommonInterfaces(type, map);
            }

            Map findCommonInterfaces(Map map, Map map1)
            {
/* 497*/        Iterator iterator = map1.keySet().iterator();
/* 498*/        do
                {
/* 498*/            if(!iterator.hasNext())
/* 499*/                break;
/* 499*/            if(!map.containsKey(iterator.next()))
/* 500*/                iterator.remove();
                } while(true);
/* 506*/        for(Iterator iterator1 = (new ArrayList(map1.values())).iterator(); iterator1.hasNext();)
                {
/* 508*/            map = (CtClass)iterator1.next();
/* 511*/            try
                    {
/* 511*/                map = map.getInterfaces();
                    }
/* 512*/            catch(NotFoundException notfoundexception)
                    {
/* 513*/                throw new RuntimeException(notfoundexception);
                    }
/* 516*/            int i = 0;
/* 516*/            while(i < map.length) 
                    {
/* 517*/                map1.remove(map[i].getName());
/* 516*/                i++;
                    }
                }

/* 520*/        return map1;
            }

            Map getAllInterfaces(CtClass ctclass, Map map)
            {
/* 524*/        if(map == null)
/* 525*/            map = new HashMap();
/* 527*/        if(ctclass.isInterface())
/* 528*/            map.put(ctclass.getName(), ctclass);
/* 531*/        do
/* 531*/            try
                    {
/* 531*/                CtClass actclass[] = ctclass.getInterfaces();
/* 532*/                for(int i = 0; i < actclass.length; i++)
                        {
/* 533*/                    CtClass ctclass1 = actclass[i];
/* 534*/                    map.put(ctclass1.getName(), ctclass1);
/* 535*/                    getAllInterfaces(ctclass1, map);
                        }

/* 538*/                ctclass = ctclass.getSuperclass();
                    }
/* 539*/            catch(NotFoundException notfoundexception)
                    {
/* 540*/                throw new RuntimeException(notfoundexception);
                    }
/* 542*/        while(ctclass != null);
/* 544*/        return map;
            }

            Map getDeclaredInterfaces(CtClass ctclass, Map map)
            {
/* 548*/        if(map == null)
/* 549*/            map = new HashMap();
/* 551*/        if(ctclass.isInterface())
/* 552*/            map.put(ctclass.getName(), ctclass);
/* 556*/        try
                {
/* 556*/            ctclass = ctclass.getInterfaces();
                }
/* 557*/        catch(NotFoundException notfoundexception)
                {
/* 558*/            throw new RuntimeException(notfoundexception);
                }
/* 561*/        for(int i = 0; i < ctclass.length; i++)
                {
/* 562*/            CtClass ctclass1 = ctclass[i];
/* 563*/            map.put(ctclass1.getName(), ctclass1);
/* 564*/            getDeclaredInterfaces(ctclass1, map);
                }

/* 567*/        return map;
            }

            public boolean equals(Object obj)
            {
/* 571*/        if(!(obj instanceof Type))
/* 572*/            return false;
/* 574*/        return obj.getClass() == getClass() && eq(clazz, ((Type)obj).clazz);
            }

            static boolean eq(CtClass ctclass, CtClass ctclass1)
            {
/* 578*/        return ctclass == ctclass1 || ctclass != null && ctclass1 != null && ctclass.getName().equals(ctclass1.getName());
            }

            public String toString()
            {
/* 582*/        if(this == BOGUS)
/* 583*/            return "BOGUS";
/* 584*/        if(this == UNINIT)
/* 585*/            return "UNINIT";
/* 586*/        if(this == RETURN_ADDRESS)
/* 587*/            return "RETURN ADDRESS";
/* 588*/        if(this == TOP)
/* 589*/            return "TOP";
/* 591*/        if(clazz == null)
/* 591*/            return "null";
/* 591*/        else
/* 591*/            return clazz.getName();
            }

            private final CtClass clazz;
            private final boolean special;
            private static final Map prims;
            public static final Type DOUBLE;
            public static final Type BOOLEAN;
            public static final Type LONG;
            public static final Type CHAR;
            public static final Type BYTE;
            public static final Type SHORT;
            public static final Type INTEGER;
            public static final Type FLOAT;
            public static final Type VOID;
            public static final Type UNINIT = new Type(null);
            public static final Type RETURN_ADDRESS = new Type(null, true);
            public static final Type TOP = new Type(null, true);
            public static final Type BOGUS = new Type(null, true);
            public static final Type OBJECT = lookupType("java.lang.Object");
            public static final Type SERIALIZABLE = lookupType("java.io.Serializable");
            public static final Type CLONEABLE = lookupType("java.lang.Cloneable");
            public static final Type THROWABLE = lookupType("java.lang.Throwable");

            static 
            {
/*  47*/        prims = new IdentityHashMap();
/*  49*/        DOUBLE = new Type(CtClass.doubleType);
/*  51*/        BOOLEAN = new Type(CtClass.booleanType);
/*  53*/        LONG = new Type(CtClass.longType);
/*  55*/        CHAR = new Type(CtClass.charType);
/*  57*/        BYTE = new Type(CtClass.byteType);
/*  59*/        SHORT = new Type(CtClass.shortType);
/*  61*/        INTEGER = new Type(CtClass.intType);
/*  63*/        FLOAT = new Type(CtClass.floatType);
/*  65*/        VOID = new Type(CtClass.voidType);
/* 106*/        prims.put(CtClass.doubleType, DOUBLE);
/* 107*/        prims.put(CtClass.longType, LONG);
/* 108*/        prims.put(CtClass.charType, CHAR);
/* 109*/        prims.put(CtClass.shortType, SHORT);
/* 110*/        prims.put(CtClass.intType, INTEGER);
/* 111*/        prims.put(CtClass.floatType, FLOAT);
/* 112*/        prims.put(CtClass.byteType, BYTE);
/* 113*/        prims.put(CtClass.booleanType, BOOLEAN);
/* 114*/        prims.put(CtClass.voidType, VOID);
            }
}
