// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, StandardTable

class this._cls1 extends this._cls1
{

            public boolean remove(Object obj)
            {
/* 548*/        return obj != null && romColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
            }

            public boolean removeAll(Collection collection)
            {
/* 552*/        return romColumnIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
            }

            public boolean retainAll(Collection collection)
            {
/* 556*/        return romColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
            }

            final  this$1;

            ()
            {
/* 543*/        this$1 = this._cls1.this;
/* 544*/        super(this._cls1.this);
            }
}
