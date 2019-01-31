// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;


// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

static class assLoaderProvider
    implements assLoaderProvider
{

            public final ClassLoader get(ProxyFactory proxyfactory)
            {
/* 608*/        return proxyfactory.getClassLoader0();
            }

            assLoaderProvider()
            {
            }
}
