// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathTemplate.java

package org.glassfish.jersey.uri;

import org.glassfish.jersey.uri.internal.UriTemplateParser;

// Referenced classes of package org.glassfish.jersey.uri:
//            PathTemplate, UriComponent

static final class > extends UriTemplateParser
{

            protected final String encodeLiteralCharacters(String s)
            {
/*  70*/        return UriComponent.contextualEncode(s, >);
            }

            public (String s)
            {
/*  64*/        super(s);
            }
}
