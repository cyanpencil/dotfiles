// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReverseNaturalOrdering.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering

final class ReverseNaturalOrdering extends Ordering
    implements Serializable
{

            public final int compare(Comparable comparable, Comparable comparable1)
            {
/*  34*/        Preconditions.checkNotNull(comparable);
/*  35*/        if(comparable == comparable1)
/*  36*/            return 0;
/*  39*/        else
/*  39*/            return comparable1.compareTo(comparable);
            }

            public final Ordering reverse()
            {
/*  43*/        return Ordering.natural();
            }

            public final String toString()
            {
/*  86*/        return "Ordering.natural().reverse()";
            }

            private ReverseNaturalOrdering()
            {
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  27*/        return compare((Comparable)obj, (Comparable)obj1);
            }

            static final ReverseNaturalOrdering INSTANCE = new ReverseNaturalOrdering();

}
