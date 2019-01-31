// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap

class this._cls0 extends AbstractCollection
{

            public Iterator iterator()
            {
/* 183*/        return valueIterator();
            }

            public int size()
            {
/* 187*/        return AbstractMultimap.this.size();
            }

            public boolean contains(Object obj)
            {
/* 191*/        return containsValue(obj);
            }

            public void clear()
            {
/* 195*/        AbstractMultimap.this.clear();
            }

            final AbstractMultimap this$0;

            I()
            {
/* 181*/        this$0 = AbstractMultimap.this;
/* 181*/        super();
            }
}
