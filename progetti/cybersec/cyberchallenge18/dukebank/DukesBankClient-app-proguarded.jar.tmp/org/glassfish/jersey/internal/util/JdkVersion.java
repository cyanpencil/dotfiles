// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JdkVersion.java

package org.glassfish.jersey.internal.util;


public class JdkVersion
    implements Comparable
{

            private JdkVersion(int i, int j, int k, int l)
            {
/*  84*/        major = i;
/*  85*/        minor = j;
/*  86*/        maintenance = k;
/*  87*/        update = l;
            }

            public static JdkVersion parseVersion(String s)
            {
                int i;
/*  94*/        if((i = s.indexOf('-')) != -1)
/*  96*/            s = s.substring(0, i);
/*  98*/        if((s = s.split("\\.|_")).length == 3)
/* 100*/            return new JdkVersion(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), 0);
/* 105*/        return new JdkVersion(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
/* 110*/        JVM INSTR pop ;
/* 111*/        return UNKNOWN_VERSION;
            }

            public static JdkVersion getJdkVersion()
            {
/* 116*/        return JDK_VERSION;
            }

            public int getMajor()
            {
/* 121*/        return major;
            }

            public int getMinor()
            {
/* 126*/        return minor;
            }

            public int getMaintenance()
            {
/* 131*/        return maintenance;
            }

            public int getUpdate()
            {
/* 136*/        return update;
            }

            public boolean isUnsafeSupported()
            {
/* 146*/        return IS_UNSAFE_SUPPORTED;
            }

            public String toString()
            {
/* 151*/        return (new StringBuilder("JdkVersion{major=")).append(major).append(", minor=").append(minor).append(", maintenance=").append(maintenance).append(", update=").append(update).append('}').toString();
            }

            public int compareTo(String s)
            {
/* 158*/        return compareTo(parseVersion(s));
            }

            public int compareTo(JdkVersion jdkversion)
            {
/* 163*/        if(major < jdkversion.major)
/* 164*/            return -1;
/* 166*/        if(major > jdkversion.major)
/* 167*/            return 1;
/* 169*/        if(minor < jdkversion.minor)
/* 170*/            return -1;
/* 172*/        if(minor > jdkversion.minor)
/* 173*/            return 1;
/* 175*/        if(maintenance < jdkversion.maintenance)
/* 176*/            return -1;
/* 178*/        if(maintenance > jdkversion.maintenance)
/* 179*/            return 1;
/* 181*/        if(update < jdkversion.update)
/* 182*/            return -1;
/* 184*/        return update <= jdkversion.update ? 0 : 1;
            }

            public volatile int compareTo(Object obj)
            {
/*  49*/        return compareTo((JdkVersion)obj);
            }

            private static final boolean IS_UNSAFE_SUPPORTED;
            private static final JdkVersion UNKNOWN_VERSION = new JdkVersion(-1, -1, -1, -1);
            private static final JdkVersion JDK_VERSION = parseVersion(System.getProperty("java.version"));
            private final int major;
            private final int minor;
            private final int maintenance;
            private final int update;

            static 
            {
                boolean flag;
/*  58*/        try
                {
/*  58*/            flag = (flag = Class.forName("sun.misc.Unsafe") != null) & (System.getProperty("com.google.appengine.runtime.environment") == null);
                }
/*  63*/        catch(Throwable _ex)
                {
/*  67*/            flag = false;
                }
/*  70*/        IS_UNSAFE_SUPPORTED = flag;
            }
}
