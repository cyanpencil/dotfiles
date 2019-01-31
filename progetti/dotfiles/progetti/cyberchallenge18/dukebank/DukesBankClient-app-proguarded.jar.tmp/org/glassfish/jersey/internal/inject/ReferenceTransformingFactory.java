// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReferenceTransformingFactory.java

package org.glassfish.jersey.internal.inject;

import javax.inject.Provider;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.util.collection.Ref;

public abstract class ReferenceTransformingFactory
    implements Factory
{
    public static interface Transformer
    {

        public abstract Object transform(Object obj);
    }


            protected ReferenceTransformingFactory(Provider provider, Transformer transformer1)
            {
/*  86*/        refProvider = provider;
/*  87*/        transformer = transformer1;
            }

            public Object provide()
            {
/*  92*/        return transformer.transform(((Ref)refProvider.get()).get());
            }

            public void dispose(Object obj)
            {
            }

            private final Provider refProvider;
            private final Transformer transformer;
}
