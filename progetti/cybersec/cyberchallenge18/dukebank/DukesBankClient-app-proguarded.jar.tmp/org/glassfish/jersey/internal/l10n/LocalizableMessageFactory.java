// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalizableMessageFactory.java

package org.glassfish.jersey.internal.l10n;


// Referenced classes of package org.glassfish.jersey.internal.l10n:
//            LocalizableMessage, Localizable

public class LocalizableMessageFactory
{

            public LocalizableMessageFactory(String s)
            {
/*  51*/        _bundlename = s;
            }

            public transient Localizable getMessage(String s, Object aobj[])
            {
/*  55*/        return new LocalizableMessage(_bundlename, s, aobj);
            }

            private final String _bundlename;
}
