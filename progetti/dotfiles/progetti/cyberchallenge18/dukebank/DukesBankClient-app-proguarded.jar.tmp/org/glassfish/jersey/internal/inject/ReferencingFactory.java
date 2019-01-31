// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReferencingFactory.java

package org.glassfish.jersey.internal.inject;

import javax.inject.Provider;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;

public abstract class ReferencingFactory
    implements Factory
{
    static class InitializedReferenceFactory
        implements Factory
    {

                public Ref provide()
                {
/*  80*/            return Refs.of(initialValue);
                }

                public void dispose(Ref ref)
                {
                }

                public volatile void dispose(Object obj)
                {
/*  70*/            dispose((Ref)obj);
                }

                public volatile Object provide()
                {
/*  70*/            return provide();
                }

                private final Object initialValue;

                public InitializedReferenceFactory(Object obj)
                {
/*  75*/            initialValue = obj;
                }
    }

    static class EmptyReferenceFactory
        implements Factory
    {

                public Ref provide()
                {
/*  61*/            return Refs.emptyRef();
                }

                public void dispose(Ref ref)
                {
                }

                public volatile void dispose(Object obj)
                {
/*  57*/            dispose((Ref)obj);
                }

                public volatile Object provide()
                {
/*  57*/            return provide();
                }

                private EmptyReferenceFactory()
                {
                }

    }


            public ReferencingFactory(Provider provider)
            {
/*  97*/        referenceFactory = provider;
            }

            public Object provide()
            {
/* 102*/        return ((Ref)referenceFactory.get()).get();
            }

            public void dispose(Object obj)
            {
            }

            public static Factory referenceFactory()
            {
/* 117*/        return new EmptyReferenceFactory();
            }

            public static Factory referenceFactory(Object obj)
            {
/* 130*/        if(obj == null)
/* 131*/            return new EmptyReferenceFactory();
/* 134*/        else
/* 134*/            return new InitializedReferenceFactory(obj);
            }

            private final Provider referenceFactory;
}
