// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;

import java.lang.ref.WeakReference;

// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

static class isUseWriteReplace
{

            byte signature[];
            WeakReference proxyClass;
            boolean isUseWriteReplace;

            (byte abyte0[], Class class1, boolean flag)
            {
/* 329*/        signature = abyte0;
/* 330*/        proxyClass = new WeakReference(class1);
/* 331*/        isUseWriteReplace = flag;
            }
}
