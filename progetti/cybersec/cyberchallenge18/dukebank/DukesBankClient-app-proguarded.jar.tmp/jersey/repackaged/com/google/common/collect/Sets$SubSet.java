// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, Sets, UnmodifiableIterator, ImmutableList, 
//            ImmutableSet

static final class mask extends AbstractSet
{

            public final Iterator iterator()
            {
/*1243*/        return new UnmodifiableIterator() {

                    public boolean hasNext()
                    {
/*1249*/                return remainingSetBits != 0;
                    }

                    public Object next()
                    {
                        int i;
/*1254*/                if((i = Integer.numberOfTrailingZeros(remainingSetBits)) == 32)
                        {
/*1256*/                    throw new NoSuchElementException();
                        } else
                        {
/*1258*/                    remainingSetBits &= ~(1 << i);
/*1259*/                    return elements.get(i);
                        }
                    }

                    final ImmutableList elements;
                    int remainingSetBits;
                    final Sets.SubSet this$0;

                    
                    {
/*1243*/                this$0 = Sets.SubSet.this;
/*1243*/                super();
/*1244*/                elements = inputSet.keySet().asList();
/*1245*/                remainingSetBits = mask;
                    }
        };
            }

            public final int size()
            {
/*1266*/        return Integer.bitCount(mask);
            }

            public final boolean contains(Object obj)
            {
/*1271*/        return (obj = (Integer)inputSet.get(obj)) != null && (mask & 1 << ((Integer) (obj)).intValue()) != 0;
            }

            private final ImmutableMap inputSet;
            private final int mask;



            (ImmutableMap immutablemap, int i)
            {
/*1237*/        inputSet = immutablemap;
/*1238*/        mask = i;
            }
}
