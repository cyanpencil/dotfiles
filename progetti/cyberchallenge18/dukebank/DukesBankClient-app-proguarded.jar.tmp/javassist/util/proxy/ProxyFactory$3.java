// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;

import java.util.Comparator;
import java.util.Map;

// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

static class 
    implements Comparator
{

            public final int compare(Object obj, Object obj1)
            {
/* 817*/        obj = (java.util.yFactory._cls3)obj;
/* 818*/        obj1 = (java.util.yFactory._cls3)obj1;
/* 819*/        obj = (String)((java.util.yFactory._cls3) (obj)).y();
/* 820*/        obj1 = (String)((java.util.y) (obj1)).y();
/* 821*/        return ((String) (obj)).compareTo(((String) (obj1)));
            }

            ()
            {
            }
}
