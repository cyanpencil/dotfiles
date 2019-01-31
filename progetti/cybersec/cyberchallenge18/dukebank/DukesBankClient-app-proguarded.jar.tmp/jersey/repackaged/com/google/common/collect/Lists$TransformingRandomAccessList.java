// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Lists, TransformedListIterator

static class function extends AbstractList
    implements Serializable, RandomAccess
{

            public void clear()
            {
/* 605*/        fromList.clear();
            }

            public Object get(int i)
            {
/* 608*/        return function.apply(fromList.get(i));
            }

            public Iterator iterator()
            {
/* 611*/        return listIterator();
            }

            public ListIterator listIterator(int i)
            {
/* 614*/        return new TransformedListIterator(fromList.listIterator(i)) {

                    Object transform(Object obj)
                    {
/* 617*/                return function.apply(obj);
                    }

                    final Lists.TransformingRandomAccessList this$0;

                    
                    {
/* 614*/                this$0 = Lists.TransformingRandomAccessList.this;
/* 614*/                super(listiterator);
                    }
        };
            }

            public boolean isEmpty()
            {
/* 622*/        return fromList.isEmpty();
            }

            public Object remove(int i)
            {
/* 625*/        return function.apply(fromList.remove(i));
            }

            public int size()
            {
/* 628*/        return fromList.size();
            }

            final List fromList;
            final Function function;

            _cls1.this._cls0(List list, Function function1)
            {
/* 601*/        fromList = (List)Preconditions.checkNotNull(list);
/* 602*/        function = (Function)Preconditions.checkNotNull(function1);
            }
}
