// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComparatorOrdering.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering

final class ComparatorOrdering extends Ordering
    implements Serializable
{

            ComparatorOrdering(Comparator comparator1)
            {
/*  34*/        comparator = (Comparator)Preconditions.checkNotNull(comparator1);
            }

            public final int compare(Object obj, Object obj1)
            {
/*  38*/        return comparator.compare(obj, obj1);
            }

            public final boolean equals(Object obj)
            {
/*  42*/        if(obj == this)
/*  43*/            return true;
/*  45*/        if(obj instanceof ComparatorOrdering)
                {
/*  46*/            obj = (ComparatorOrdering)obj;
/*  47*/            return comparator.equals(((ComparatorOrdering) (obj)).comparator);
                } else
                {
/*  49*/            return false;
                }
            }

            public final int hashCode()
            {
/*  53*/        return comparator.hashCode();
            }

            public final String toString()
            {
/*  57*/        return comparator.toString();
            }

            final Comparator comparator;
}
