// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataStructures.java

package org.glassfish.jersey.internal.util.collection;

import java.security.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.JdkVersion;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

public final class DataStructures
{

            public DataStructures()
            {
            }

            private static int ceilingNextPowerOfTwo(int i)
            {
/*  99*/        return 1 << 32 - Integer.numberOfLeadingZeros(i - 1);
            }

            private static Class getAndVerify(String s)
                throws Throwable
            {
/* 104*/        return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction(s) {

                    public final Class run()
                        throws Exception
                    {
/* 107*/                return org/glassfish/jersey/internal/util/collection/DataStructures.getClassLoader().loadClass(cn).newInstance().getClass();
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/* 104*/                return run();
                    }

                    final String val$cn;

                    
                    {
/* 104*/                cn = s;
/* 104*/                super();
                    }
        });
/* 110*/        JVM INSTR dup ;
/* 111*/        s;
/* 111*/        getCause();
/* 111*/        throw ;
            }

            public static BlockingQueue createLinkedTransferQueue()
            {
/* 130*/        return (BlockingQueue)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public final BlockingQueue run()
                        throws Exception
                    {
/* 133*/                return (BlockingQueue)DataStructures.LTQ_CLASS.newInstance();
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/* 130*/                return run();
                    }

        });
/* 136*/        JVM INSTR dup ;
                Object obj;
/* 137*/        obj;
/* 137*/        getCause();
/* 137*/        JVM INSTR dup ;
/* 138*/        obj;
/* 138*/        JVM INSTR instanceof #11  <Class RuntimeException>;
/* 138*/        JVM INSTR ifeq 32;
                   goto _L1 _L2
_L1:
/* 139*/        break MISSING_BLOCK_LABEL_27;
_L2:
/* 139*/        break MISSING_BLOCK_LABEL_32;
/* 139*/        throw (RuntimeException)obj;
/* 141*/        throw new RuntimeException(((Throwable) (obj)));
            }

            public static ConcurrentMap createConcurrentMap()
            {
/* 161*/        return (ConcurrentMap)(JdkVersion.getJdkVersion().isUnsafeSupported() ? new ConcurrentHashMapV8() : new ConcurrentHashMap());
            }

            public static ConcurrentMap createConcurrentMap(Map map)
            {
/* 181*/        return (ConcurrentMap)(JdkVersion.getJdkVersion().isUnsafeSupported() ? new ConcurrentHashMapV8(map) : new ConcurrentHashMap(map));
            }

            public static ConcurrentMap createConcurrentMap(int i)
            {
/* 205*/        return (ConcurrentMap)(JdkVersion.getJdkVersion().isUnsafeSupported() ? new ConcurrentHashMapV8(i) : new ConcurrentHashMap(i));
            }

            public static ConcurrentMap createConcurrentMap(int i, float f, int j)
            {
/* 238*/        return (ConcurrentMap)(JdkVersion.getJdkVersion().isUnsafeSupported() ? new ConcurrentHashMapV8(i, f, j) : new ConcurrentHashMap(i, f, j));
            }

            private static final Class LTQ_CLASS;
            public static final int DEFAULT_CONCURENCY_LEVEL = ceilingNextPowerOfTwo(Runtime.getRuntime().availableProcessors());

            static 
            {
/*  68*/        String s = null;
                Object obj;
/*  72*/        try
                {
/*  72*/            obj = JdkVersion.getJdkVersion();
                    JdkVersion jdkversion;
/*  73*/            obj = getAndVerify(s = (jdkversion = JdkVersion.parseVersion("1.7.0")).compareTo(((JdkVersion) (obj))) > 0 ? "org.glassfish.jersey.internal.util.collection.LinkedTransferQueue" : "java.util.concurrent.LinkedTransferQueue");
/*  80*/            Logger.getLogger(org/glassfish/jersey/internal/util/collection/DataStructures.getName()).log(Level.FINE, "USING LTQ class:{0}", obj);
                }
                // Misplaced declaration of an exception variable
/*  81*/        catch(Object obj)
                {
/*  82*/            Logger.getLogger(org/glassfish/jersey/internal/util/collection/DataStructures.getName()).log(Level.FINE, (new StringBuilder("failed loading data structure class:")).append(s).append(" fallback to embedded one").toString(), ((Throwable) (obj)));
/*  86*/            obj = java/util/concurrent/LinkedBlockingQueue;
                }
/*  89*/        LTQ_CLASS = ((Class) (obj));
            }

}
