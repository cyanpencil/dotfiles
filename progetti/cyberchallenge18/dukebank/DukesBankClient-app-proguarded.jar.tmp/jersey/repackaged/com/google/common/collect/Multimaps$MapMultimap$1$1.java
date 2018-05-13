// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Multimaps, Sets

class this._cls1
    implements Iterator
{

            public boolean hasNext()
            {
/* 964*/        return i == 0 && _fld0.i.containsKey(key);
            }

            public Object next()
            {
/* 969*/        if(!hasNext())
                {
/* 970*/            throw new NoSuchElementException();
                } else
                {
/* 972*/            i++;
/* 973*/            return _fld0.i.get(key);
                }
            }

            public void remove()
            {
/* 978*/        CollectPreconditions.checkRemove(i == 1);
/* 979*/        i = -1;
/* 980*/        _fld0.i.remove(key);
            }

            int i;
            final l.key this$1;

            init>()
            {
/* 959*/        this$1 = this._cls1.this;
/* 959*/        super();
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/collect/Multimaps$MapMultimap$1

/* anonymous class */
    class Multimaps.MapMultimap._cls1 extends Sets.ImprovedAbstractSet
    {

                public Iterator iterator()
                {
/* 959*/            return new Multimaps.MapMultimap._cls1._cls1();
                }

                public int size()
                {
/* 986*/            return !map.containsKey(key) ? 0 : 1;
                }

                final Object val$key;
                final Multimaps.MapMultimap this$0;

                    
                    {
/* 957*/                this$0 = final_mapmultimap;
/* 957*/                key = Object.this;
/* 957*/                super();
                    }
    }

}
