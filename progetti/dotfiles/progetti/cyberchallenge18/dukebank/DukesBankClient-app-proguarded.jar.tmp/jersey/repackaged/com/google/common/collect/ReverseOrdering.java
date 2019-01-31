// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReverseOrdering.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering

final class ReverseOrdering extends Ordering
    implements Serializable
{

            ReverseOrdering(Ordering ordering)
            {
/*  34*/        forwardOrder = (Ordering)Preconditions.checkNotNull(ordering);
            }

            public final int compare(Object obj, Object obj1)
            {
/*  38*/        return forwardOrder.compare(obj1, obj);
            }

            public final Ordering reverse()
            {
/*  43*/        return forwardOrder;
            }

            public final int hashCode()
            {
/*  81*/        return -forwardOrder.hashCode();
            }

            public final boolean equals(Object obj)
            {
/*  85*/        if(obj == this)
/*  86*/            return true;
/*  88*/        if(obj instanceof ReverseOrdering)
                {
/*  89*/            obj = (ReverseOrdering)obj;
/*  90*/            return forwardOrder.equals(((ReverseOrdering) (obj)).forwardOrder);
                } else
                {
/*  92*/            return false;
                }
            }

            public final String toString()
            {
/*  96*/        String s = String.valueOf(String.valueOf(forwardOrder));
/*  96*/        return (new StringBuilder(10 + s.length())).append(s).append(".reverse()").toString();
            }

            final Ordering forwardOrder;
}
