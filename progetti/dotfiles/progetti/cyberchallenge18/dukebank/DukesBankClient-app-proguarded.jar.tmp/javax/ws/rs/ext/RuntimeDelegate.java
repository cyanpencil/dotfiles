// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeDelegate.java

package javax.ws.rs.ext;

import java.lang.reflect.ReflectPermission;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.ext:
//            FactoryFinder

public abstract class RuntimeDelegate
{
    public static interface HeaderDelegate
    {

        public abstract Object fromString(String s);

        public abstract String toString(Object obj);
    }


            protected RuntimeDelegate()
            {
            }

            public static RuntimeDelegate getInstance()
            {
/* 115*/        if((obj = cachedDelegate) == null)
/* 117*/            synchronized(RD_LOCK)
                    {
/* 118*/                if((obj = cachedDelegate) == null)
/* 120*/                    cachedDelegate = ((RuntimeDelegate) (obj = findDelegate()));
                    }
/* 124*/        return ((RuntimeDelegate) (obj));
            }

            private static RuntimeDelegate findDelegate()
            {
                Object obj;
/* 135*/        if(!((obj = FactoryFinder.find("javax.ws.rs.ext.RuntimeDelegate", "org.glassfish.jersey.internal.RuntimeDelegateImpl")) instanceof RuntimeDelegate))
                {
/* 139*/            /*<invalid signature>*/java.lang.Object local = javax/ws/rs/ext/RuntimeDelegate;
/* 140*/            String s = (new StringBuilder()).append(local.getName().replace('.', '/')).append(".class").toString();
                    Object obj1;
/* 141*/            if((obj1 = local.getClassLoader()) == null)
/* 143*/                obj1 = ClassLoader.getSystemClassLoader();
/* 145*/            obj1 = ((ClassLoader) (obj1)).getResource(s);
/* 146*/            throw new LinkageError((new StringBuilder("ClassCastException: attempting to cast")).append(obj.getClass().getClassLoader().getResource(s)).append(" to ").append(obj1).toString());
                }
/* 150*/        return (RuntimeDelegate)obj;
                Exception exception;
/* 151*/        exception;
/* 152*/        throw new RuntimeException(exception);
            }

            public static void setInstance(RuntimeDelegate runtimedelegate)
            {
                SecurityManager securitymanager;
/* 166*/        if((securitymanager = System.getSecurityManager()) != null)
/* 168*/            securitymanager.checkPermission(suppressAccessChecksPermission);
/* 170*/        synchronized(RD_LOCK)
                {
/* 171*/            cachedDelegate = runtimedelegate;
                }
            }

            public abstract UriBuilder createUriBuilder();

            public abstract javax.ws.rs.core.Response.ResponseBuilder createResponseBuilder();

            public abstract javax.ws.rs.core.Variant.VariantListBuilder createVariantListBuilder();

            public abstract Object createEndpoint(Application application, Class class1)
                throws IllegalArgumentException, UnsupportedOperationException;

            public abstract HeaderDelegate createHeaderDelegate(Class class1)
                throws IllegalArgumentException;

            public abstract javax.ws.rs.core.Link.Builder createLinkBuilder();

            public static final String JAXRS_RUNTIME_DELEGATE_PROPERTY = "javax.ws.rs.ext.RuntimeDelegate";
            private static final String JAXRS_DEFAULT_RUNTIME_DELEGATE = "org.glassfish.jersey.internal.RuntimeDelegateImpl";
            private static final Object RD_LOCK = new Object();
            private static ReflectPermission suppressAccessChecksPermission = new ReflectPermission("suppressAccessChecks");
            private static volatile RuntimeDelegate cachedDelegate;

}
