// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Sets

class ection extends ection
    implements Set
{

            public boolean removeAll(Collection collection)
            {
/* 608*/        if(collection.isEmpty())
/* 609*/            return false;
/* 611*/        int i = size();
/* 616*/        if((collection = Sets.removeAllImpl((Set)_flddelegate, collection)) != 0)
                {
/* 618*/            int j = _flddelegate.size();
/* 619*/            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, j - i);
/* 620*/            removeIfEmpty();
                }
/* 622*/        return collection;
            }

            final AbstractMapBasedMultimap this$0;

            ection(Object obj, Set set)
            {
/* 602*/        this$0 = AbstractMapBasedMultimap.this;
/* 603*/        super(AbstractMapBasedMultimap.this, obj, set, null);
            }
}
