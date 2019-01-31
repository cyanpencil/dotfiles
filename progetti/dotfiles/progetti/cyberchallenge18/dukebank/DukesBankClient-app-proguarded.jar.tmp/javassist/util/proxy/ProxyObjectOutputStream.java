// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyObjectOutputStream.java

package javassist.util.proxy;

import java.io.*;

// Referenced classes of package javassist.util.proxy:
//            Proxy, ProxyFactory, ProxyObject

public class ProxyObjectOutputStream extends ObjectOutputStream
{

            public ProxyObjectOutputStream(OutputStream outputstream)
                throws IOException
            {
/*  44*/        super(outputstream);
            }

            protected void writeClassDescriptor(ObjectStreamClass objectstreamclass)
                throws IOException
            {
                Class class1;
/*  48*/        if(ProxyFactory.isProxyClass(class1 = objectstreamclass.forClass()))
                {
/*  50*/            writeBoolean(true);
/*  51*/            objectstreamclass = class1.getSuperclass();
/*  52*/            Class aclass[] = class1.getInterfaces();
/*  53*/            byte abyte0[] = ProxyFactory.getFilterSignature(class1);
/*  54*/            objectstreamclass = objectstreamclass.getName();
/*  55*/            writeObject(objectstreamclass);
/*  57*/            writeInt(aclass.length - 1);
/*  58*/            for(int i = 0; i < aclass.length; i++)
/*  59*/                if((objectstreamclass = aclass[i]) != javassist/util/proxy/ProxyObject && objectstreamclass != javassist/util/proxy/Proxy)
                        {
/*  61*/                    objectstreamclass = aclass[i].getName();
/*  62*/                    writeObject(objectstreamclass);
                        }

/*  65*/            writeInt(abyte0.length);
/*  66*/            write(abyte0);
/*  67*/            return;
                } else
                {
/*  68*/            writeBoolean(false);
/*  69*/            super.writeClassDescriptor(objectstreamclass);
/*  71*/            return;
                }
            }
}
