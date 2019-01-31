// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;


// Referenced classes of package javassist.util.proxy:
//            ProxyFactory

static class counter
    implements iqueName
{

            public final String get(String s)
            {
/* 727*/        return (new StringBuilder()).append(s).append(sep).append(Integer.toHexString(counter++)).toString();
            }

            private final String sep = (new StringBuilder("_$$_jvst")).append(Integer.toHexString(hashCode() & 0xfff)).append("_").toString();
            private int counter;

            iqueName()
            {
/* 724*/        counter = 0;
            }
}
