// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static abstract class _cls1
{

            public abstract Object adapt(String s);

            public abstract String adapt(Object obj);

            public static final _cls1 runtimeAdapter = new DefaultConverters.KeyAdapter() {

                public final Object adapt(String s)
                {
/* 728*/            return s;
                }

                public final String adapt(Object obj)
                {
/* 733*/            return obj.toString();
                }

    };
            public static final _cls1 strAdapter = new DefaultConverters.KeyAdapter() {

                public final String adapt(String s)
                {
/* 740*/            return s;
                }

                public final volatile String adapt(Object obj)
                {
/* 737*/            return adapt((String)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 737*/            return adapt(s);
                }

    };
            public static final _cls2.adapt shortAdapter = new DefaultConverters.KeyAdapter() {

                public final Short adapt(String s)
                {
/* 747*/            return Short.valueOf(Short.parseShort(s));
                }

                public final String adapt(Short short1)
                {
/* 752*/            return short1.toString();
                }

                public final volatile String adapt(Object obj)
                {
/* 744*/            return adapt((Short)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 744*/            return adapt(s);
                }

    };
            public static final _cls3.adapt intAdapter = new DefaultConverters.KeyAdapter() {

                public final Integer adapt(String s)
                {
/* 759*/            return Integer.valueOf(Integer.parseInt(s));
                }

                public final String adapt(Integer integer)
                {
/* 764*/            return integer.toString();
                }

                public final volatile String adapt(Object obj)
                {
/* 756*/            return adapt((Integer)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 756*/            return adapt(s);
                }

    };
            public static final _cls4.adapt longAdapter = new DefaultConverters.KeyAdapter() {

                public final Long adapt(String s)
                {
/* 771*/            return Long.valueOf(Long.parseLong(s));
                }

                public final String adapt(Long long1)
                {
/* 776*/            return long1.toString();
                }

                public final volatile String adapt(Object obj)
                {
/* 768*/            return adapt((Long)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 768*/            return adapt(s);
                }

    };
            public static final _cls5.adapt floatAdapter = new DefaultConverters.KeyAdapter() {

                public final Float adapt(String s)
                {
/* 783*/            return Float.valueOf(Float.parseFloat(s));
                }

                public final String adapt(Float float1)
                {
/* 788*/            return float1.toString();
                }

                public final volatile String adapt(Object obj)
                {
/* 780*/            return adapt((Float)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 780*/            return adapt(s);
                }

    };
            public static final _cls6.adapt doubleAdapter = new DefaultConverters.KeyAdapter() {

                public final Double adapt(String s)
                {
/* 795*/            return Double.valueOf(Double.parseDouble(s));
                }

                public final String adapt(Double double1)
                {
/* 800*/            return double1.toString();
                }

                public final volatile String adapt(Object obj)
                {
/* 792*/            return adapt((Double)obj);
                }

                public final volatile Object adapt(String s)
                {
/* 792*/            return adapt(s);
                }

    };


            public _cls1()
            {
            }
}
