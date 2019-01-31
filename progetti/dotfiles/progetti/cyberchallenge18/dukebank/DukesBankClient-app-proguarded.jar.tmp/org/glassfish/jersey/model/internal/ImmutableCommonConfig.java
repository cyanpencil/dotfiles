// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableCommonConfig.java

package org.glassfish.jersey.model.internal;

import java.util.Map;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            CommonConfig

public class ImmutableCommonConfig extends CommonConfig
{

            public ImmutableCommonConfig(CommonConfig commonconfig, String s)
            {
/*  65*/        super(commonconfig);
/*  67*/        errorMessage = s;
            }

            public ImmutableCommonConfig(CommonConfig commonconfig)
            {
/*  76*/        this(commonconfig, LocalizationMessages.CONFIGURATION_NOT_MODIFIABLE());
            }

            public ImmutableCommonConfig property(String s, Object obj)
            {
/*  81*/        throw new IllegalStateException(errorMessage);
            }

            public ImmutableCommonConfig setProperties(Map map)
            {
/*  86*/        throw new IllegalStateException(errorMessage);
            }

            public ImmutableCommonConfig register(Class class1)
            {
/*  91*/        throw new IllegalStateException(errorMessage);
            }

            public ImmutableCommonConfig register(Class class1, int i)
            {
/*  96*/        throw new IllegalStateException(errorMessage);
            }

            public transient ImmutableCommonConfig register(Class class1, Class aclass[])
            {
/* 101*/        throw new IllegalStateException(errorMessage);
            }

            public CommonConfig register(Class class1, Map map)
            {
/* 106*/        throw new IllegalStateException(errorMessage);
            }

            public ImmutableCommonConfig register(Object obj)
            {
/* 111*/        throw new IllegalStateException(errorMessage);
            }

            public ImmutableCommonConfig register(Object obj, int i)
            {
/* 116*/        throw new IllegalStateException(errorMessage);
            }

            public transient ImmutableCommonConfig register(Object obj, Class aclass[])
            {
/* 121*/        throw new IllegalStateException(errorMessage);
            }

            public CommonConfig register(Object obj, Map map)
            {
/* 126*/        throw new IllegalStateException(errorMessage);
            }

            public CommonConfig loadFrom(Configuration configuration)
            {
/* 131*/        throw new IllegalStateException(errorMessage);
            }

            public volatile CommonConfig register(Object obj, Class aclass[])
            {
/*  53*/        return register(obj, aclass);
            }

            public volatile CommonConfig register(Object obj, int i)
            {
/*  53*/        return register(obj, i);
            }

            public volatile CommonConfig register(Object obj)
            {
/*  53*/        return register(obj);
            }

            public volatile CommonConfig register(Class class1, Class aclass[])
            {
/*  53*/        return register(class1, aclass);
            }

            public volatile CommonConfig register(Class class1, int i)
            {
/*  53*/        return register(class1, i);
            }

            public volatile CommonConfig register(Class class1)
            {
/*  53*/        return register(class1);
            }

            public volatile CommonConfig property(String s, Object obj)
            {
/*  53*/        return property(s, obj);
            }

            public volatile CommonConfig setProperties(Map map)
            {
/*  53*/        return setProperties(map);
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  53*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  53*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  53*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  53*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  53*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  53*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  53*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  53*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  53*/        return property(s, obj);
            }

            private final String errorMessage;
}
