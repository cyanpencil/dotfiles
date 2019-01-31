// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.math.IntMath;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, ImmutableList

static final class inputList extends AbstractCollection
{

            public final int size()
            {
/* 571*/        return IntMath.factorial(inputList.size());
            }

            public final boolean isEmpty()
            {
/* 575*/        return false;
            }

            public final Iterator iterator()
            {
/* 579*/        return new nit>(inputList);
            }

            public final boolean contains(Object obj)
            {
/* 583*/        if(obj instanceof List)
                {
/* 584*/            obj = (List)obj;
/* 585*/            return Collections2.access$100(inputList, ((List) (obj)));
                } else
                {
/* 587*/            return false;
                }
            }

            public final String toString()
            {
/* 591*/        String s = String.valueOf(String.valueOf(inputList));
/* 591*/        return (new StringBuilder(14 + s.length())).append("permutations(").append(s).append(")").toString();
            }

            final ImmutableList inputList;

            (ImmutableList immutablelist)
            {
/* 567*/        inputList = immutablelist;
            }
}
