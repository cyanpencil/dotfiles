// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SaxHelper.java

package org.glassfish.jersey.internal.util;

import java.security.AccessController;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

public final class SaxHelper
{

            private SaxHelper()
            {
            }

            public static boolean isXdkParserFactory(SAXParserFactory saxparserfactory)
            {
/*  64*/        return isXdkFactory(saxparserfactory, "oracle.xml.jaxp.JXSAXParserFactory");
            }

            public static boolean isXdkDocumentBuilderFactory(DocumentBuilderFactory documentbuilderfactory)
            {
/*  74*/        return isXdkFactory(documentbuilderfactory, "oracle.xml.jaxp.JXDocumentBuilderFactory");
            }

            private static boolean isXdkFactory(Object obj, String s)
            {
/*  78*/        if((s = (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA(s, null))) == null)
/*  80*/            return false;
/*  82*/        else
/*  82*/            return s.isAssignableFrom(obj.getClass());
            }
}
