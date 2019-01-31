// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByFunctionOrdering.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering

final class ByFunctionOrdering extends Ordering
    implements Serializable
{

            ByFunctionOrdering(Function function1, Ordering ordering1)
            {
/*  41*/        function = (Function)Preconditions.checkNotNull(function1);
/*  42*/        ordering = (Ordering)Preconditions.checkNotNull(ordering1);
            }

            public final int compare(Object obj, Object obj1)
            {
/*  46*/        return ordering.compare(function.apply(obj), function.apply(obj1));
            }

            public final boolean equals(Object obj)
            {
/*  50*/        if(obj == this)
/*  51*/            return true;
/*  53*/        if(obj instanceof ByFunctionOrdering)
                {
/*  54*/            obj = (ByFunctionOrdering)obj;
/*  55*/            return function.equals(((ByFunctionOrdering) (obj)).function) && ordering.equals(((ByFunctionOrdering) (obj)).ordering);
                } else
                {
/*  58*/            return false;
                }
            }

            public final int hashCode()
            {
/*  62*/        return Objects.hashCode(new Object[] {
/*  62*/            function, ordering
                });
            }

            public final String toString()
            {
/*  66*/        String s = String.valueOf(String.valueOf(ordering));
/*  66*/        String s1 = String.valueOf(String.valueOf(function));
/*  66*/        return (new StringBuilder(13 + s.length() + s1.length())).append(s).append(".onResultOf(").append(s1).append(")").toString();
            }

            final Function function;
            final Ordering ordering;
}
