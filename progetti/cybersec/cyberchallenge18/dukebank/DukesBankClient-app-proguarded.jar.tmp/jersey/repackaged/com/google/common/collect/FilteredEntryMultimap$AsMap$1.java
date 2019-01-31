// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, Maps

class this._cls1 extends this._cls1
{

            public boolean removeAll(Collection collection)
            {
/* 222*/        return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
            }

            public boolean retainAll(Collection collection)
            {
/* 227*/        return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
            }

            public boolean remove(Object obj)
            {
/* 232*/        return move(obj) != null;
            }

            final move this$1;

            (Map map)
            {
/* 219*/        this$1 = this._cls1.this;
/* 219*/        super(map);
            }
}
