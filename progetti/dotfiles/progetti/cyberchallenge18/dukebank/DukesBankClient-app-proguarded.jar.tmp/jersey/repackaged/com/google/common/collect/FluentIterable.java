// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FluentIterable.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterables

public abstract class FluentIterable
    implements Iterable
{

            protected FluentIterable()
            {
            }

            public String toString()
            {
/* 130*/        return Iterables.toString(iterable);
            }

            private final Iterable iterable = this;
}
