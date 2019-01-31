// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UnmodifiableListIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator

public abstract class UnmodifiableListIterator extends UnmodifiableIterator
    implements ListIterator
{

            protected UnmodifiableListIterator()
            {
            }

            /**
             * @deprecated Method add is deprecated
             */

            public final void add(Object obj)
            {
/*  43*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method set is deprecated
             */

            public final void set(Object obj)
            {
/*  53*/        throw new UnsupportedOperationException();
            }
}
