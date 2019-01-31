// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultiset, Multiset, Multisets

class this._cls0 extends this._cls0
{

            Multiset multiset()
            {
/* 176*/        return AbstractMultiset.this;
            }

            public Iterator iterator()
            {
/* 180*/        return entryIterator();
            }

            public int size()
            {
/* 184*/        return distinctElements();
            }

            final AbstractMultiset this$0;

            ()
            {
/* 174*/        this$0 = AbstractMultiset.this;
/* 174*/        super();
            }
}
