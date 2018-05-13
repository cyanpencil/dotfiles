// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractTable, Collections2, Maps, Table

class this._cls0 extends AbstractSet
{

            public boolean contains(Object obj)
            {
/* 123*/        if(obj instanceof this._cls0)
                {
/* 124*/            obj = (this._cls0)obj;
                    Map map;
/* 125*/            return (map = (Map)Maps.safeGet(rowMap(), ((this._cls0) (obj))._mth0())) != null && Collections2.safeContains(map.entrySet(), Maps.immutableEntry(((ins) (obj)).y(), ((y) (obj)).y()));
                } else
                {
/* 129*/            return false;
                }
            }

            public boolean remove(Object obj)
            {
/* 134*/        if(obj instanceof y)
                {
/* 135*/            obj = (y)obj;
                    Map map;
/* 136*/            return (map = (Map)Maps.safeGet(rowMap(), ((y) (obj)).y())) != null && Collections2.safeRemove(map.entrySet(), Maps.immutableEntry(((e) (obj)).y(), ((y) (obj)).y()));
                } else
                {
/* 140*/            return false;
                }
            }

            public void clear()
            {
/* 145*/        AbstractTable.this.clear();
            }

            public Iterator iterator()
            {
/* 150*/        return cellIterator();
            }

            public int size()
            {
/* 155*/        return AbstractTable.this.size();
            }

            final AbstractTable this$0;

            ()
            {
/* 120*/        this$0 = AbstractTable.this;
/* 120*/        super();
            }
}
