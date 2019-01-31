// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Equivalence.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

public abstract class Equivalence
{
    static final class Identity extends Equivalence
        implements Serializable
    {

                protected final boolean doEquivalent(Object obj, Object obj1)
                {
/* 345*/            return false;
                }

                protected final int doHash(Object obj)
                {
/* 349*/            return System.identityHashCode(obj);
                }

                static final Identity INSTANCE = new Identity();


                Identity()
                {
                }
    }

    static final class Equals extends Equivalence
        implements Serializable
    {

                protected final boolean doEquivalent(Object obj, Object obj1)
                {
/* 327*/            return obj.equals(obj1);
                }

                protected final int doHash(Object obj)
                {
/* 330*/            return obj.hashCode();
                }

                static final Equals INSTANCE = new Equals();


                Equals()
                {
                }
    }


            protected Equivalence()
            {
            }

            public final boolean equivalent(Object obj, Object obj1)
            {
/*  65*/        if(obj == obj1)
/*  66*/            return true;
/*  68*/        if(obj == null || obj1 == null)
/*  69*/            return false;
/*  71*/        else
/*  71*/            return doEquivalent(obj, obj1);
            }

            protected abstract boolean doEquivalent(Object obj, Object obj1);

            public final int hash(Object obj)
            {
/* 101*/        if(obj == null)
/* 102*/            return 0;
/* 104*/        else
/* 104*/            return doHash(obj);
            }

            protected abstract int doHash(Object obj);

            public static Equivalence equals()
            {
/* 306*/        return Equals.INSTANCE;
            }

            public static Equivalence identity()
            {
/* 318*/        return Identity.INSTANCE;
            }
}
