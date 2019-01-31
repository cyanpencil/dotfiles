// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerializedProxy.java

package javassist.util.proxy;

import java.io.*;
import java.security.*;

// Referenced classes of package javassist.util.proxy:
//            MethodHandler, Proxy, ProxyFactory, ProxyObject

class SerializedProxy
    implements Serializable
{

            SerializedProxy(Class class1, byte abyte0[], MethodHandler methodhandler)
            {
/*  39*/        filterSignature = abyte0;
/*  40*/        handler = methodhandler;
/*  41*/        superClass = class1.getSuperclass().getName();
/*  42*/        abyte0 = (class1 = class1.getInterfaces()).length;
/*  44*/        interfaces = new String[abyte0 - 1];
/*  45*/        methodhandler = javassist/util/proxy/ProxyObject.getName();
/*  46*/        String s = javassist/util/proxy/Proxy.getName();
/*  47*/        for(int i = 0; i < abyte0; i++)
                {
                    String s1;
/*  48*/            if(!(s1 = class1[i].getName()).equals(methodhandler) && !s1.equals(s))
/*  50*/                interfaces[i] = s1;
                }

            }

            protected Class loadClass(final String className)
                throws ClassNotFoundException
            {
/*  63*/        return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public Object run()
                        throws Exception
                    {
/*  65*/                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
/*  66*/                return Class.forName(className, true, classloader);
                    }

                    final String val$className;
                    final SerializedProxy this$0;

                    
                    {
/*  63*/                this$0 = SerializedProxy.this;
/*  63*/                className = s;
/*  63*/                super();
                    }
        });
                PrivilegedActionException privilegedactionexception;
/*  70*/        privilegedactionexception;
/*  71*/        throw new RuntimeException((new StringBuilder("cannot load the class: ")).append(className).toString(), privilegedactionexception.getException());
            }

            Object readResolve()
                throws ObjectStreamException
            {
                Proxy proxy;
                int i;
/*  77*/        Class aclass[] = new Class[i = interfaces.length];
/*  79*/        for(int j = 0; j < i; j++)
/*  80*/            aclass[j] = loadClass(interfaces[j]);

                ProxyFactory proxyfactory;
/*  82*/        (proxyfactory = new ProxyFactory()).setSuperclass(loadClass(superClass));
/*  84*/        proxyfactory.setInterfaces(aclass);
/*  85*/        (proxy = (Proxy)proxyfactory.createClass(filterSignature).newInstance()).setHandler(handler);
/*  87*/        return proxy;
                Object obj;
/*  89*/        obj;
/*  90*/        throw new InvalidClassException(((ClassNotFoundException) (obj)).getMessage());
/*  92*/        obj;
/*  93*/        throw new InvalidObjectException(((InstantiationException) (obj)).getMessage());
/*  95*/        obj;
/*  96*/        throw new InvalidClassException(((IllegalAccessException) (obj)).getMessage());
            }

            private String superClass;
            private String interfaces[];
            private byte filterSignature[];
            private MethodHandler handler;
}
