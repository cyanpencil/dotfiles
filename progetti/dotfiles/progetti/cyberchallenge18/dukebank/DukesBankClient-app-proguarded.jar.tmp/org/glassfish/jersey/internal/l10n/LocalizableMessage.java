// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalizableMessage.java

package org.glassfish.jersey.internal.l10n;


// Referenced classes of package org.glassfish.jersey.internal.l10n:
//            Localizable

public final class LocalizableMessage
    implements Localizable
{

            public transient LocalizableMessage(String s, String s1, Object aobj[])
            {
/*  52*/        _bundlename = s;
/*  53*/        _key = s1;
/*  54*/        if(aobj == null)
/*  55*/            aobj = new Object[0];
/*  57*/        _args = aobj;
            }

            public final String getKey()
            {
/*  62*/        return _key;
            }

            public final Object[] getArguments()
            {
/*  67*/        return (Object[])((Object []) (_args)).clone();
            }

            public final String getResourceBundleName()
            {
/*  72*/        return _bundlename;
            }

            private final String _bundlename;
            private final String _key;
            private final Object _args[];
}
