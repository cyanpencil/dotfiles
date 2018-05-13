// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractNavigableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractNavigableMap, Maps

final class this._cls0 extends this._cls0
{

            final NavigableMap forward()
            {
/* 189*/        return AbstractNavigableMap.this;
            }

            final Iterator entryIterator()
            {
/* 194*/        return descendingEntryIterator();
            }

            final AbstractNavigableMap this$0;

            private ()
            {
/* 186*/        this$0 = AbstractNavigableMap.this;
/* 186*/        super();
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/collect/AbstractNavigableMap$1

/* anonymous class */
    class AbstractNavigableMap._cls1 extends Maps.EntrySet
    {

                Map map()
                {
/* 166*/            return AbstractNavigableMap.this;
                }

                public Iterator iterator()
                {
/* 171*/            return entryIterator();
                }

                final AbstractNavigableMap this$0;

                    
                    {
/* 163*/                this$0 = AbstractNavigableMap.this;
/* 163*/                super();
                    }
    }

}
