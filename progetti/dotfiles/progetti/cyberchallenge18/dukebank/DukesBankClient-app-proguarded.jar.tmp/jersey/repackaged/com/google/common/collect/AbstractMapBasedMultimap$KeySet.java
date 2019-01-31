// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Iterators, Maps, CollectPreconditions

class this._cls0 extends this._cls0
{

            public Iterator iterator()
            {
/* 925*/        final Iterator entryIterator = map().entrySet().iterator();
/* 927*/        return new Iterator() {

                    public boolean hasNext()
                    {
/* 932*/                return entryIterator.hasNext();
                    }

                    public Object next()
                    {
/* 936*/                entry = (java.util.Map.Entry)entryIterator.next();
/* 937*/                return entry.getKey();
                    }

                    public void remove()
                    {
/* 941*/                CollectPreconditions.checkRemove(entry != null);
/* 942*/                Collection collection = (Collection)entry.getValue();
/* 943*/                entryIterator.remove();
/* 944*/                AbstractMapBasedMultimap.access$220(this$0, collection.size());
/* 945*/                collection.clear();
                    }

                    java.util.Map.Entry entry;
                    final Iterator val$entryIterator;
                    final AbstractMapBasedMultimap.KeySet this$1;

                    
                    {
/* 927*/                this$1 = AbstractMapBasedMultimap.KeySet.this;
/* 927*/                entryIterator = iterator1;
/* 927*/                super();
                    }
        };
            }

            public boolean remove(Object obj)
            {
/* 953*/        int i = 0;
/* 954*/        if((obj = (Collection)map().remove(obj)) != null)
                {
/* 956*/            i = ((Collection) (obj)).size();
/* 957*/            ((Collection) (obj)).clear();
/* 958*/            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, i);
                }
/* 960*/        return i > 0;
            }

            public void clear()
            {
/* 965*/        Iterators.clear(iterator());
            }

            public boolean containsAll(Collection collection)
            {
/* 969*/        return map().keySet().containsAll(collection);
            }

            public boolean equals(Object obj)
            {
/* 973*/        return this == obj || map().keySet().equals(obj);
            }

            public int hashCode()
            {
/* 977*/        return map().keySet().hashCode();
            }

            final AbstractMapBasedMultimap this$0;

            _cls1.val.entryIterator(Map map)
            {
/* 920*/        this$0 = AbstractMapBasedMultimap.this;
/* 921*/        super(map);
            }
}
