// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Set;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Sets

static class eredCollection extends eredCollection
    implements Set
{

            public boolean equals(Object obj)
            {
/* 771*/        return Sets.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 775*/        return Sets.hashCodeImpl(this);
            }

            eredCollection(Set set, Predicate predicate)
            {
/* 767*/        super(set, predicate);
            }
}
