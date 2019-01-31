// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Quality.java

package org.glassfish.jersey.message.internal;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Qualified

public final class Quality
{

            private Quality()
            {
/*  87*/        throw new AssertionError("Instantiation not allowed.");
            }

            static Map enhanceWithQualityParameter(Map map, String s, int i)
            {
/* 122*/        if(i == 1000 && (map == null || map.isEmpty() || !map.containsKey(s)))
/* 125*/            return map;
/* 130*/        if(map == null || map.isEmpty())
/* 131*/            return Collections.singletonMap(s, qualityValueToString(i));
/* 136*/        map.put(s, qualityValueToString(i));
/* 137*/        return map;
/* 138*/        JVM INSTR pop ;
/* 140*/        (map = new HashMap(map)).put(s, qualityValueToString(i));
/* 142*/        return map;
            }

            private static int compare(int i, int j)
            {
/* 162*/        if(i < j)
/* 162*/            return -1;
/* 162*/        return i != j ? 1 : 0;
            }

            private static String qualityValueToString(float f)
            {
                int i;
/* 166*/        for(f = new StringBuilder(String.format(Locale.US, "%3.3f", new Object[] {
/* 166*/    Float.valueOf(f / 1000F)
})); (i = f.length() - 1) > 2 && f.charAt(i) == '0'; f.deleteCharAt(i));
/* 172*/        return f.toString();
            }

            public static final Comparator QUALIFIED_COMPARATOR = new Comparator() {

                public final int compare(Qualified qualified, Qualified qualified1)
                {
/*  65*/            return Quality.compare(qualified1.getQuality(), qualified.getQuality());
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  60*/            return compare((Qualified)obj, (Qualified)obj1);
                }

    };
            public static final Comparator QUALITY_VALUE_COMPARATOR = new Comparator() {

                public final int compare(Integer integer, Integer integer1)
                {
/*  79*/            return Quality.compare(integer1.intValue(), integer.intValue());
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  74*/            return compare((Integer)obj, (Integer)obj1);
                }

    };
            public static final String QUALITY_PARAMETER_NAME = "q";
            public static final String QUALITY_SOURCE_PARAMETER_NAME = "qs";
            public static final int MINIMUM = 0;
            public static final int MAXIMUM = 1000;
            public static final int DEFAULT = 1000;


}
