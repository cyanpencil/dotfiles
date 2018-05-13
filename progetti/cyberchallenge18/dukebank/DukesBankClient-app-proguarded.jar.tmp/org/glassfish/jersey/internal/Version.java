// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Version.java

package org.glassfish.jersey.internal;

import java.io.InputStream;
import java.util.Properties;

public final class Version
{

            private Version()
            {
/*  61*/        throw new AssertionError("Instantiation not allowed.");
            }

            private static void _initiateProperties()
            {
                InputStream inputstream;
/*  65*/        if((inputstream = getIntputStream()) == null)
/*  68*/            break MISSING_BLOCK_LABEL_82;
                Properties properties;
/*  68*/        (properties = new Properties()).load(inputstream);
/*  70*/        String s = properties.getProperty("Build-Timestamp");
/*  71*/        version = properties.getProperty("Build-Version");
/*  73*/        buildId = String.format("Jersey: %s %s", new Object[] {
/*  73*/            version, s
                });
/*  77*/        close(inputstream);
/*  78*/        return;
/*  74*/        JVM INSTR pop ;
/*  75*/        buildId = "Jersey";
/*  77*/        close(inputstream);
/*  78*/        return;
                Exception exception;
/*  77*/        exception;
/*  77*/        close(inputstream);
/*  77*/        throw exception;
            }

            private static void close(InputStream inputstream)
            {
/*  84*/        try
                {
/*  84*/            inputstream.close();
/*  87*/            return;
                }
/*  85*/        catch(Exception _ex)
                {
/*  88*/            return;
                }
            }

            private static InputStream getIntputStream()
            {
/*  92*/        return org/glassfish/jersey/internal/Version.getResourceAsStream("build.properties");
/*  93*/        JVM INSTR pop ;
/*  94*/        return null;
            }

            public static String getBuildId()
            {
/* 104*/        return buildId;
            }

            public static String getVersion()
            {
/* 113*/        return version;
            }

            private static String buildId;
            private static String version = null;

            static 
            {
/*  57*/        _initiateProperties();
            }
}
