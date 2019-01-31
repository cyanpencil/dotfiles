// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

final class this._cls0 extends AbstractCollection
{

            public final Iterator iterator()
            {
/*3810*/        return new erator(MapMakerInternalMap.this);
            }

            public final int size()
            {
/*3815*/        return MapMakerInternalMap.this.size();
            }

            public final boolean isEmpty()
            {
/*3820*/        return MapMakerInternalMap.this.isEmpty();
            }

            public final boolean contains(Object obj)
            {
/*3825*/        return containsValue(obj);
            }

            public final void clear()
            {
/*3830*/        MapMakerInternalMap.this.clear();
            }

            final MapMakerInternalMap this$0;

            erator()
            {
/*3806*/        this$0 = MapMakerInternalMap.this;
/*3806*/        super();
            }
}
