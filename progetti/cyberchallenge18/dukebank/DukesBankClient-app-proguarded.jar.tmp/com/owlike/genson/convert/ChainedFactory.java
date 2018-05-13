// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChainedFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import java.lang.reflect.Type;

public abstract class ChainedFactory
    implements Factory
{

            protected ChainedFactory()
            {
            }

            protected ChainedFactory(Factory factory)
            {
/*  82*/        next = factory;
            }

            public Converter create(Type type, Genson genson)
            {
/*  86*/        Converter converter = null;
/*  87*/        if(next != null)
/*  88*/            converter = (Converter)next.create(type, genson);
/*  90*/        if((type = create(type, genson, converter)) == null)
/*  91*/            return converter;
/*  91*/        else
/*  91*/            return type;
            }

            protected abstract Converter create(Type type, Genson genson, Converter converter);

            public final Factory withNext(Factory factory)
            {
/* 116*/        if(next != null)
                {
/* 117*/            throw new IllegalStateException((new StringBuilder("next factory has already been set for ")).append(getClass()).append(" you can not override it!").toString());
                } else
                {
/* 119*/            next = factory;
/* 120*/            return factory;
                }
            }

            public final Factory append(Factory factory)
            {
                ChainedFactory chainedfactory;
/* 124*/        for(chainedfactory = this; chainedfactory.next() != null; chainedfactory = (ChainedFactory)chainedfactory.next())
/* 126*/            if(!(chainedfactory.next() instanceof ChainedFactory))
/* 127*/                throw new UnsupportedOperationException("Last element in the chain is not a ChainedFactory");

/* 132*/        return chainedfactory.withNext(factory);
            }

            public final Factory next()
            {
/* 139*/        return next;
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  75*/        return create(type, genson);
            }

            private Factory next;
}
