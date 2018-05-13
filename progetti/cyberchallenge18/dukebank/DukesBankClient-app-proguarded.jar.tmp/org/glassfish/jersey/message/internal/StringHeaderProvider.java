// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringHeaderProvider.java

package org.glassfish.jersey.message.internal;

import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Utils

public class StringHeaderProvider
    implements HeaderDelegateProvider
{

            public StringHeaderProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  61*/        return class1 == java/lang/String;
            }

            public String toString(String s)
            {
/*  67*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.STRING_IS_NULL());
/*  69*/        return s;
            }

            public String fromString(String s)
            {
/*  75*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.STRING_IS_NULL());
/*  77*/        return s;
            }

            public volatile String toString(Object obj)
            {
/*  56*/        return toString((String)obj);
            }

            public volatile Object fromString(String s)
            {
/*  56*/        return fromString(s);
            }
}
