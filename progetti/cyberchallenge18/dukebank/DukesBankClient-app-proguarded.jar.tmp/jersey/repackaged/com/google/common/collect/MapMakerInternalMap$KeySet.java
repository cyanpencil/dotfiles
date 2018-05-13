// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

final class this._cls0 extends AbstractSet
{

            public final Iterator iterator()
            {
/*3777*/        return new ator(MapMakerInternalMap.this);
            }

            public final int size()
            {
/*3782*/        return MapMakerInternalMap.this.size();
            }

            public final boolean isEmpty()
            {
/*3787*/        return MapMakerInternalMap.this.isEmpty();
            }

            public final boolean contains(Object obj)
            {
/*3792*/        return containsKey(obj);
            }

            public final boolean remove(Object obj)
            {
/*3797*/        return MapMakerInternalMap.this.remove(obj) != null;
            }

            public final void clear()
            {
/*3802*/        MapMakerInternalMap.this.clear();
            }

            final MapMakerInternalMap this$0;

            ator()
            {
/*3773*/        this$0 = MapMakerInternalMap.this;
/*3773*/        super();
            }
}
