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

            public boolean contains(Object obj)
            {
/* 525*/        return StandardTable.this.contains(obj, ey);
            }

            public boolean remove(Object obj)
            {
/* 529*/        return StandardTable.this.remove(obj, ey) != null;
            }

            public boolean retainAll(Collection collection)
            {
/* 533*/        return romColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
            }

            final romColumnIf this$1;

            ()
            {
/* 520*/        this$1 = this._cls1.this;
/* 521*/        super(this._cls1.this);
            }
}
