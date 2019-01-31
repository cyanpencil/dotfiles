// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UnmodifiableIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

public abstract class UnmodifiableIterator
    implements Iterator
{

            protected UnmodifiableIterator()
            {
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public final void remove()
            {
/*  43*/        throw new UnsupportedOperationException();
            }
}
