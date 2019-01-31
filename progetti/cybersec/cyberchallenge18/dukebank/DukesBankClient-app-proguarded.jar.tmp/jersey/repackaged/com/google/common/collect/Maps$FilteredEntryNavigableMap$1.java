// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps

class this._cls0 extends this._cls0
{

            public boolean removeAll(Collection collection)
            {
/*2912*/        return Iterators.removeIf(cess._mth700(this._cls0.this).entrySet().iterator(), Predicates.and(cess._mth600(this._cls0.this), Maps.keyPredicateOnEntries(Predicates.in(collection))));
            }

            public boolean retainAll(Collection collection)
            {
/*2918*/        return Iterators.removeIf(cess._mth700(this._cls0.this).entrySet().iterator(), Predicates.and(cess._mth600(this._cls0.this), Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection)))));
            }

            final this._cls0 this$0;

            (NavigableMap navigablemap)
            {
/*2909*/        this$0 = this._cls0.this;
/*2909*/        super(navigablemap);
            }
}
