// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathTemplate.java

package org.glassfish.jersey.uri;

import org.glassfish.jersey.uri.internal.UriTemplateParser;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriTemplate, UriComponent

public final class PathTemplate extends UriTemplate
{
    static final class PathTemplateParser extends UriTemplateParser
    {

                protected final String encodeLiteralCharacters(String s)
                {
/*  70*/            return UriComponent.contextualEncode(s, UriComponent.Type.PATH);
                }

                public PathTemplateParser(String s)
                {
/*  64*/            super(s);
                }
    }


            public PathTemplate(String s)
            {
/*  84*/        super(new PathTemplateParser(prefixWithSlash(s)));
            }

            private static String prefixWithSlash(String s)
            {
/*  95*/        if(!s.isEmpty() && s.charAt(0) == '/')
/*  95*/            return s;
/*  95*/        else
/*  95*/            return (new StringBuilder("/")).append(s).toString();
            }
}
