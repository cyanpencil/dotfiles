// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterables, Lists, Maps

class this._cls0 extends this._cls0
{

            public boolean remove(Object obj)
            {
/*2736*/        if(sKey(obj))
                {
/*2737*/            red.remove(obj);
/*2738*/            return true;
                } else
                {
/*2740*/            return false;
                }
            }

            private boolean removeIf(Predicate predicate)
            {
/*2744*/        return Iterables.removeIf(red.entrySet(), Predicates.and(te, Maps.keyPredicateOnEntries(predicate)));
            }

            public boolean removeAll(Collection collection)
            {
/*2750*/        return removeIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection collection)
            {
/*2755*/        return removeIf(Predicates.not(Predicates.in(collection)));
            }

            public Object[] toArray()
            {
/*2760*/        return Lists.newArrayList(iterator()).toArray();
            }

            public Object[] toArray(Object aobj[])
            {
/*2764*/        return Lists.newArrayList(iterator()).toArray(aobj);
            }

            final iterator this$0;

            ()
            {
/*2731*/        this$0 = this._cls0.this;
/*2732*/        super(this._cls0.this);
            }
}
