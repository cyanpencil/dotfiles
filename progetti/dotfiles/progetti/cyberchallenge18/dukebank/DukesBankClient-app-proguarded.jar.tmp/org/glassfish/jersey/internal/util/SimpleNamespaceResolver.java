// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SimpleNamespaceResolver.java

package org.glassfish.jersey.internal.util;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;

public class SimpleNamespaceResolver
    implements NamespaceContext
{

            public SimpleNamespaceResolver(String s, String s1)
            {
/*  66*/        prefix = s;
/*  67*/        nsURI = s1;
            }

            public String getNamespaceURI(String s)
            {
/*  72*/        if(s.equals(prefix))
/*  73*/            return nsURI;
/*  75*/        else
/*  75*/            return "";
            }

            public String getPrefix(String s)
            {
/*  81*/        if(s.equals(nsURI))
/*  82*/            return prefix;
/*  84*/        else
/*  84*/            return null;
            }

            public Iterator getPrefixes(String s)
            {
/*  90*/        return null;
            }

            private final String prefix;
            private final String nsURI;
}
