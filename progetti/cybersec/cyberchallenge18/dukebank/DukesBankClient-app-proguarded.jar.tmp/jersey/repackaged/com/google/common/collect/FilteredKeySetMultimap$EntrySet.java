// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeySetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredKeyMultimap, FilteredKeySetMultimap, Sets

class t> extends t>
    implements Set
{

            public int hashCode()
            {
/*  73*/        return Sets.hashCodeImpl(this);
            }

            public boolean equals(Object obj)
            {
/*  78*/        return Sets.equalsImpl(this, obj);
            }

            final FilteredKeySetMultimap this$0;

            Q()
            {
/*  70*/        this$0 = FilteredKeySetMultimap.this;
/*  70*/        super(FilteredKeySetMultimap.this);
            }
}
