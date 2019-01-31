// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Primitives.java

package jersey.repackaged.com.google.common.primitives;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

public final class Primitives
{

            private Primitives()
            {
            }

            private static void add(Map map, Map map1, Class class1, Class class2)
            {
/*  64*/        map.put(class1, class2);
/*  65*/        map1.put(class2, class1);
            }

            public static Set allPrimitiveTypes()
            {
/*  76*/        return PRIMITIVE_TO_WRAPPER_TYPE.keySet();
            }

            public static Set allWrapperTypes()
            {
/*  86*/        return WRAPPER_TO_PRIMITIVE_TYPE.keySet();
            }

            public static boolean isWrapperType(Class class1)
            {
/*  96*/        return WRAPPER_TO_PRIMITIVE_TYPE.containsKey(Preconditions.checkNotNull(class1));
            }

            public static Class wrap(Class class1)
            {
/* 109*/        Preconditions.checkNotNull(class1);
                Class class2;
/* 113*/        if((class2 = (Class)PRIMITIVE_TO_WRAPPER_TYPE.get(class1)) == null)
/* 114*/            return class1;
/* 114*/        else
/* 114*/            return class2;
            }

            public static Class unwrap(Class class1)
            {
/* 127*/        Preconditions.checkNotNull(class1);
                Class class2;
/* 131*/        if((class2 = (Class)WRAPPER_TO_PRIMITIVE_TYPE.get(class1)) == null)
/* 132*/            return class1;
/* 132*/        else
/* 132*/            return class2;
            }

            private static final Map PRIMITIVE_TO_WRAPPER_TYPE;
            private static final Map WRAPPER_TO_PRIMITIVE_TYPE;

            static 
            {
/*  45*/        HashMap hashmap = new HashMap(16);
/*  46*/        HashMap hashmap1 = new HashMap(16);
/*  48*/        add(hashmap, hashmap1, Boolean.TYPE, java/lang/Boolean);
/*  49*/        add(hashmap, hashmap1, Byte.TYPE, java/lang/Byte);
/*  50*/        add(hashmap, hashmap1, Character.TYPE, java/lang/Character);
/*  51*/        add(hashmap, hashmap1, Double.TYPE, java/lang/Double);
/*  52*/        add(hashmap, hashmap1, Float.TYPE, java/lang/Float);
/*  53*/        add(hashmap, hashmap1, Integer.TYPE, java/lang/Integer);
/*  54*/        add(hashmap, hashmap1, Long.TYPE, java/lang/Long);
/*  55*/        add(hashmap, hashmap1, Short.TYPE, java/lang/Short);
/*  56*/        add(hashmap, hashmap1, Void.TYPE, java/lang/Void);
/*  58*/        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(hashmap);
/*  59*/        WRAPPER_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(hashmap1);
            }
}
