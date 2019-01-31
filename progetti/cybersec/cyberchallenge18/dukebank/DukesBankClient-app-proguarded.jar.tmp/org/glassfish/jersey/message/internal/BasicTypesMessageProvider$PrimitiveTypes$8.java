// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicTypesMessageProvider.java

package org.glassfish.jersey.message.internal;

import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            BasicTypesMessageProvider, MessageBodyProcessingException

static class nit> extends nit>
{

            public final Object convert(String s)
            {
/* 126*/        if(s.length() != 1)
/* 127*/            throw new MessageBodyProcessingException(LocalizationMessages.ERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS());
/* 130*/        else
/* 130*/            return Character.valueOf(s.charAt(0));
            }

            (String s, int i, Class class1, Class class2)
            {
/* 123*/        super(s, i, class1, class2);
            }
}
