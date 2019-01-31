// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadLocalRandom.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ThreadLocalRandom

static class  extends ThreadLocal
{

            protected final ThreadLocalRandom initialValue()
            {
/* 102*/        return new ThreadLocalRandom();
            }

            protected final volatile Object initialValue()
            {
/* 100*/        return initialValue();
            }

            ()
            {
            }
}
