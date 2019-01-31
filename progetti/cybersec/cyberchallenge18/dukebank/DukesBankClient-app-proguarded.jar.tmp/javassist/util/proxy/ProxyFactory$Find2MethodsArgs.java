// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;


// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

static class origIndex
{

            String methodName;
            String delegatorName;
            String descriptor;
            int origIndex;

            (String s, String s1, String s2, int i)
            {
/*1300*/        methodName = s;
/*1301*/        delegatorName = s1;
/*1302*/        descriptor = s2;
/*1303*/        origIndex = i;
            }
}
