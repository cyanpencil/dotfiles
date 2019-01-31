// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractTable

class this._cls0 extends AbstractCollection
{

            public Iterator iterator()
            {
/* 183*/        return valuesIterator();
            }

            public boolean contains(Object obj)
            {
/* 188*/        return containsValue(obj);
            }

            public void clear()
            {
/* 193*/        AbstractTable.this.clear();
            }

            public int size()
            {
/* 198*/        return AbstractTable.this.size();
            }

            final AbstractTable this$0;

            ()
            {
/* 180*/        this$0 = AbstractTable.this;
/* 180*/        super();
            }
}
