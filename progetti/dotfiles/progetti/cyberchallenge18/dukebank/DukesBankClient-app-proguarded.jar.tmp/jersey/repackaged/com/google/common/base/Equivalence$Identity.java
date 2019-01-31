// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Equivalence.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Equivalence

static final class  extends Equivalence
    implements Serializable
{

            protected final boolean doEquivalent(Object obj, Object obj1)
            {
/* 345*/        return false;
            }

            protected final int doHash(Object obj)
            {
/* 349*/        return System.identityHashCode(obj);
            }

            static final  INSTANCE = new <init>();


            ()
            {
            }
}
