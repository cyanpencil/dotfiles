// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class delegateIterator
    implements Iterator
{

            void validateIterator()
            {
/* 471*/        delegateIterator.this.delegateIterator();
/* 472*/        if(this._cls1.this.delegateIterator != originalDelegate)
/* 473*/            throw new ConcurrentModificationException();
/* 475*/        else
/* 475*/            return;
            }

            public boolean hasNext()
            {
/* 479*/        validateIterator();
/* 480*/        return delegateIterator.hasNext();
            }

            public Object next()
            {
/* 485*/        validateIterator();
/* 486*/        return delegateIterator.next();
            }

            public void remove()
            {
/* 491*/        delegateIterator.remove();
/* 492*/        int _tmp = AbstractMapBasedMultimap.access$210(this._cls1.this.delegateIterator);
/* 493*/        _mth1();
            }

            Iterator getDelegateIterator()
            {
/* 497*/        validateIterator();
/* 498*/        return delegateIterator;
            }

            final Iterator delegateIterator;
            final Collection originalDelegate;
            final delegateIterator this$1;

            ()
            {
/* 458*/        this$1 = this._cls1.this;
/* 458*/        super();
/* 456*/        originalDelegate = this._cls1.this.originalDelegate;
/* 459*/        delegateIterator = AbstractMapBasedMultimap.access$100(delegateIterator.this.delegateIterator, delegateIterator.this.delegateIterator);
            }

            delegateIterator(Iterator iterator)
            {
/* 462*/        this$1 = this._cls1.this;
/* 462*/        super();
/* 456*/        originalDelegate = this._cls1.this.originalDelegate;
/* 463*/        delegateIterator = iterator;
            }
}
