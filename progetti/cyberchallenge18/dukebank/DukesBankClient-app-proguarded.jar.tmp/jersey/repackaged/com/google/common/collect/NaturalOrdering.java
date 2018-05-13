// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NaturalOrdering.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering, ReverseNaturalOrdering

final class NaturalOrdering extends Ordering
    implements Serializable
{

            public final int compare(Comparable comparable, Comparable comparable1)
            {
/*  33*/        Preconditions.checkNotNull(comparable);
/*  34*/        Preconditions.checkNotNull(comparable1);
/*  35*/        return comparable.compareTo(comparable1);
            }

            public final Ordering reverse()
            {
/*  39*/        return ReverseNaturalOrdering.INSTANCE;
            }

            public final String toString()
            {
/*  48*/        return "Ordering.natural()";
            }

            private NaturalOrdering()
            {
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  26*/        return compare((Comparable)obj, (Comparable)obj1);
            }

            static final NaturalOrdering INSTANCE = new NaturalOrdering();

}
