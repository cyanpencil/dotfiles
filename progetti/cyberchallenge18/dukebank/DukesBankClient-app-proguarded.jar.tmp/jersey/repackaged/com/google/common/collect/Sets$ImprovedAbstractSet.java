// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractSet;
import java.util.Collection;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Sets

static abstract class  extends AbstractSet
{

            public boolean removeAll(Collection collection)
            {
/*  74*/        return Sets.removeAllImpl(this, collection);
            }

            public boolean retainAll(Collection collection)
            {
/*  79*/        return super.retainAll((Collection)Preconditions.checkNotNull(collection));
            }

            ()
            {
            }
}
