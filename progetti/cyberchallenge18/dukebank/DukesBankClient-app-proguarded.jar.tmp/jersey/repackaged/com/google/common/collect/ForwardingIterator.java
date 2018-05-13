// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingObject

public abstract class ForwardingIterator extends ForwardingObject
    implements Iterator
{

            protected ForwardingIterator()
            {
            }

            protected abstract Iterator _mthdelegate();

            public boolean hasNext()
            {
/*  43*/        return _mthdelegate().hasNext();
            }

            public Object next()
            {
/*  48*/        return _mthdelegate().next();
            }

            public void remove()
            {
/*  53*/        _mthdelegate().remove();
            }

            protected volatile Object _mthdelegate()
            {
/*  32*/        return _mthdelegate();
            }
}
