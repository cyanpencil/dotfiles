// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, StandardTable

class <init> extends <init>
{

            public Iterator iterator()
            {
/* 584*/        return createColumnKeyIterator();
            }

            public int size()
            {
/* 588*/        return Iterators.size(iterator());
            }

            public boolean remove(Object obj)
            {
/* 592*/        if(obj == null)
/* 593*/            return false;
/* 595*/        boolean flag = false;
/* 596*/        Iterator iterator1 = backingMap.values().iterator();
/* 597*/        do
                {
/* 597*/            if(!iterator1.hasNext())
/* 598*/                break;
                    Map map;
/* 598*/            if((map = (Map)iterator1.next()).keySet().remove(obj))
                    {
/* 600*/                flag = true;
/* 601*/                if(map.isEmpty())
/* 602*/                    iterator1.remove();
                    }
                } while(true);
/* 606*/        return flag;
            }

            public boolean removeAll(Collection collection)
            {
/* 610*/        Preconditions.checkNotNull(collection);
/* 611*/        boolean flag = false;
/* 612*/        Iterator iterator1 = backingMap.values().iterator();
/* 613*/        do
                {
/* 613*/            if(!iterator1.hasNext())
/* 614*/                break;
                    Map map;
/* 614*/            if(Iterators.removeAll((map = (Map)iterator1.next()).keySet().iterator(), collection))
                    {
/* 618*/                flag = true;
/* 619*/                if(map.isEmpty())
/* 620*/                    iterator1.remove();
                    }
                } while(true);
/* 624*/        return flag;
            }

            public boolean retainAll(Collection collection)
            {
/* 628*/        Preconditions.checkNotNull(collection);
/* 629*/        boolean flag = false;
/* 630*/        Iterator iterator1 = backingMap.values().iterator();
/* 631*/        do
                {
/* 631*/            if(!iterator1.hasNext())
/* 632*/                break;
                    Map map;
/* 632*/            if((map = (Map)iterator1.next()).keySet().retainAll(collection))
                    {
/* 634*/                flag = true;
/* 635*/                if(map.isEmpty())
/* 636*/                    iterator1.remove();
                    }
                } while(true);
/* 640*/        return flag;
            }

            public boolean contains(Object obj)
            {
/* 644*/        return containsColumn(obj);
            }

            final StandardTable this$0;

            private ()
            {
/* 582*/        this$0 = StandardTable.this;
/* 582*/        super(StandardTable.this, null);
            }

            t>(t> t>)
            {
/* 582*/        this();
            }
}
