// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

class this._cls0 extends this._cls0
    implements SortedSet
{

            public Comparator comparator()
            {
/*2805*/        return _mth0().comparator();
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/*2810*/        return (SortedSet)_mth0(obj, obj1).keySet();
            }

            public SortedSet headSet(Object obj)
            {
/*2815*/        return (SortedSet)_mth0(obj).keySet();
            }

            public SortedSet tailSet(Object obj)
            {
/*2820*/        return (SortedSet)_mth0(obj).keySet();
            }

            public Object first()
            {
/*2825*/        return _mth0();
            }

            public Object last()
            {
/*2830*/        return _mth0();
            }

            final this._cls0 this$0;

            ()
            {
/*2802*/        this$0 = this._cls0.this;
/*2802*/        super(this._cls0.this);
            }
}
