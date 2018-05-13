// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap, Multimaps, Multiset, Multisets

class this._cls0 extends this._cls0
{

            Multiset multiset()
            {
/*1552*/        return this._cls0.this;
            }

            public Iterator iterator()
            {
/*1556*/        return ();
            }

            public int size()
            {
/*1560*/        return nts();
            }

            public boolean isEmpty()
            {
/*1564*/        return nts.isEmpty();
            }

            public boolean contains(Object obj)
            {
/*1568*/        if(obj instanceof nts)
                {
/*1569*/            obj = (nts)obj;
                    Collection collection;
/*1570*/            return (collection = (Collection)nts.asMap().get(((nts) (obj)).nts())) != null && collection.size() == ((nts) (obj)).nts();
                } else
                {
/*1573*/            return false;
                }
            }

            public boolean remove(Object obj)
            {
/*1577*/        if(obj instanceof nts)
                {
/*1578*/            obj = (nts)obj;
                    Collection collection;
/*1579*/            if((collection = (Collection)nts.asMap().get(((nts) (obj)).nts())) != null && collection.size() == ((nts) (obj)).nts())
                    {
/*1581*/                collection.clear();
/*1582*/                return true;
                    }
                }
/*1585*/        return false;
            }

            final nts this$0;

            ()
            {
/*1550*/        this$0 = this._cls0.this;
/*1550*/        super();
            }
}
