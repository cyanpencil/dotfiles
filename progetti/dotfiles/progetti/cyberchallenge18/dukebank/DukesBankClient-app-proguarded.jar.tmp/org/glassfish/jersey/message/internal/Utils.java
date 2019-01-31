// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utils.java

package org.glassfish.jersey.message.internal;

import java.io.File;
import java.io.IOException;

public final class Utils
{

            static void throwIllegalArgumentExceptionIfNull(Object obj, String s)
            {
/*  60*/        if(obj == null)
/*  61*/            throw new IllegalArgumentException(s);
/*  63*/        else
/*  63*/            return;
            }

            public static File createTempFile()
                throws IOException
            {
                File file;
/*  72*/        (file = File.createTempFile("rep", "tmp")).deleteOnExit();
/*  75*/        return file;
            }

            private Utils()
            {
/*  82*/        throw new AssertionError("No instances allowed.");
            }
}
