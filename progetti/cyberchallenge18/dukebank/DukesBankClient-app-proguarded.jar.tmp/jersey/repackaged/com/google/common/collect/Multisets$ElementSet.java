// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multiset, Multisets, Sets, TransformedIterator

static abstract class init> extends Set
{

            abstract Multiset multiset();

            public void clear()
            {
/* 925*/        multiset().clear();
            }

            public boolean contains(Object obj)
            {
/* 929*/        return multiset().contains(obj);
            }

            public boolean containsAll(Collection collection)
            {
/* 933*/        return multiset().containsAll(collection);
            }

            public boolean isEmpty()
            {
/* 937*/        return multiset().isEmpty();
            }

            public Iterator iterator()
            {
/* 941*/        return new TransformedIterator(multiset().entrySet().iterator()) {

                    Object transform(Multiset.Entry entry)
                    {
/* 944*/                return entry.getElement();
                    }

                    volatile Object transform(Object obj)
                    {
/* 941*/                return transform((Multiset.Entry)obj);
                    }

                    final Multisets.ElementSet this$0;

                    
                    {
/* 941*/                this$0 = Multisets.ElementSet.this;
/* 941*/                super(iterator1);
                    }
        };
            }

            public boolean remove(Object obj)
            {
                int i;
/* 951*/        if((i = multiset().count(obj)) > 0)
                {
/* 953*/            multiset().remove(obj, i);
/* 954*/            return true;
                } else
                {
/* 956*/            return false;
                }
            }

            public int size()
            {
/* 960*/        return multiset().entrySet().size();
            }

            init>()
            {
            }
}
