// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJaxRSFeature.java

package com.owlike.genson.ext.jaxrs;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.GensonBundle;
import com.owlike.genson.ext.jaxb.JAXBBundle;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ext.ContextResolver;

public final class GensonJaxRSFeature
    implements ContextResolver
{

            public GensonJaxRSFeature()
            {
/*  18*/        enabled = true;
/*  19*/        notSerializableTypes = new HashSet();
/*  20*/        notDeserializableTypes = new HashSet();
/*  21*/        genson = _defaultGenson;
            }

            public final GensonJaxRSFeature getContext(Class class1)
            {
/*  25*/        return this;
            }

            public final GensonJaxRSFeature disable()
            {
/*  29*/        enabled = false;
/*  30*/        return this;
            }

            public final GensonJaxRSFeature enable()
            {
/*  34*/        enabled = true;
/*  35*/        return this;
            }

            public final GensonJaxRSFeature use(Genson genson1)
            {
/*  39*/        genson = genson1;
/*  40*/        return this;
            }

            public final Genson genson()
            {
/*  44*/        return genson;
            }

            public final boolean isEnabled()
            {
/*  48*/        return enabled;
            }

            public final transient GensonJaxRSFeature disableSerializationFor(Class class1, Class aclass[])
            {
/*  52*/        notSerializableTypes.add(class1);
/*  53*/        aclass = (class1 = aclass).length;
/*  53*/        for(int i = 0; i < aclass; i++)
                {
/*  53*/            Object obj = class1[i];
/*  53*/            notSerializableTypes.add(obj);
                }

/*  54*/        return this;
            }

            public final transient GensonJaxRSFeature disableDeserializationFor(Class class1, Class aclass[])
            {
/*  58*/        notDeserializableTypes.add(class1);
/*  59*/        aclass = (class1 = aclass).length;
/*  59*/        for(int i = 0; i < aclass; i++)
                {
/*  59*/            Object obj = class1[i];
/*  59*/            notDeserializableTypes.add(obj);
                }

/*  60*/        return this;
            }

            public final boolean isSerializable(Class class1)
            {
/*  64*/        return !notSerializableTypes.contains(class1);
            }

            public final boolean isDeserializable(Class class1)
            {
/*  68*/        return !notDeserializableTypes.contains(class1);
            }

            public final volatile Object getContext(Class class1)
            {
/*  11*/        return getContext(class1);
            }

            private static final Genson _defaultGenson = (new GensonBuilder()).withBundle(new GensonBundle[] {
/*  13*/        new JAXBBundle()
            }).useConstructorWithArguments(true).create();
            private boolean enabled;
            private Set notSerializableTypes;
            private Set notDeserializableTypes;
            private Genson genson;

}
