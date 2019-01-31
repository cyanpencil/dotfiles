// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Predicate, Predicates

static abstract class <init> extends Enum
    implements Predicate
{

            Predicate withNarrowedType()
            {
/* 315*/        return this;
            }

            public static final NOT_NULL ALWAYS_TRUE;
            public static final NOT_NULL ALWAYS_FALSE;
            public static final NOT_NULL IS_NULL;
            public static final NOT_NULL NOT_NULL;
            private static final NOT_NULL $VALUES[];

            static 
            {
/* 277*/        ALWAYS_TRUE = new Predicates.ObjectPredicate("ALWAYS_TRUE", 0) {

                    public final boolean apply(Object obj)
                    {
/* 279*/                return true;
                    }

                    public final String toString()
                    {
/* 282*/                return "Predicates.alwaysTrue()";
                    }

        };
/* 286*/        ALWAYS_FALSE = new Predicates.ObjectPredicate("ALWAYS_FALSE", 1) {

                    public final boolean apply(Object obj)
                    {
/* 288*/                return false;
                    }

                    public final String toString()
                    {
/* 291*/                return "Predicates.alwaysFalse()";
                    }

        };
/* 295*/        IS_NULL = new Predicates.ObjectPredicate("IS_NULL", 2) {

                    public final boolean apply(Object obj)
                    {
/* 297*/                return obj == null;
                    }

                    public final String toString()
                    {
/* 300*/                return "Predicates.isNull()";
                    }

        };
/* 304*/        NOT_NULL = new Predicates.ObjectPredicate("NOT_NULL", 3) {

                    public final boolean apply(Object obj)
                    {
/* 306*/                return obj != null;
                    }

                    public final String toString()
                    {
/* 309*/                return "Predicates.notNull()";
                    }

        };
/* 275*/        $VALUES = (new .VALUES[] {
/* 275*/            ALWAYS_TRUE, ALWAYS_FALSE, IS_NULL, NOT_NULL
                });
            }

            private _cls1(String s, int i)
            {
/* 275*/        super(s, i);
            }

            _cls1(String s, int i, _cls1 _pcls1)
            {
/* 275*/        this(s, i);
            }
}
