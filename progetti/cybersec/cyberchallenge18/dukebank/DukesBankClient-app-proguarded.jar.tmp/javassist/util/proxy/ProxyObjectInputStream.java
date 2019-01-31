// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyObjectInputStream.java

package javassist.util.proxy;

import java.io.*;

// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

public class ProxyObjectInputStream extends ObjectInputStream
{

            public ProxyObjectInputStream(InputStream inputstream)
                throws IOException
            {
/*  46*/        super(inputstream);
/*  47*/        loader = Thread.currentThread().getContextClassLoader();
/*  48*/        if(loader == null)
/*  49*/            loader = ClassLoader.getSystemClassLoader();
            }

            public void setClassLoader(ClassLoader classloader)
            {
/*  59*/        if(classloader != null)
                {
/*  60*/            loader = classloader;
/*  60*/            return;
                } else
                {
/*  62*/            ClassLoader.getSystemClassLoader();
/*  64*/            return;
                }
            }

            protected ObjectStreamClass readClassDescriptor()
                throws IOException, ClassNotFoundException
            {
                boolean flag;
/*  67*/        if(flag = readBoolean())
                {
/*  69*/            Object obj = (String)readObject();
/*  70*/            Class class1 = loader.loadClass(((String) (obj)));
                    int i;
/*  71*/            Class aclass[] = new Class[i = readInt()];
/*  73*/            for(int j = 0; j < i; j++)
                    {
/*  74*/                obj = (String)readObject();
/*  75*/                aclass[j] = loader.loadClass(((String) (obj)));
                    }

/*  77*/            byte abyte0[] = new byte[i = readInt()];
/*  79*/            read(abyte0);
/*  80*/            ((ProxyFactory) (obj = new ProxyFactory())).setUseCache(true);
/*  84*/            ((ProxyFactory) (obj)).setUseWriteReplace(false);
/*  85*/            ((ProxyFactory) (obj)).setSuperclass(class1);
/*  86*/            ((ProxyFactory) (obj)).setInterfaces(aclass);
/*  87*/            return ObjectStreamClass.lookup(((Class) (obj = ((ProxyFactory) (obj)).createClass(abyte0))));
                } else
                {
/*  90*/            return super.readClassDescriptor();
                }
            }

            private ClassLoader loader;
}
