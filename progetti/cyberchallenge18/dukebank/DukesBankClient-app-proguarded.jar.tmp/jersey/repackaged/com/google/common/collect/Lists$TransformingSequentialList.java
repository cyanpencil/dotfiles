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

static class function extends AbstractSequentialList
    implements Serializable
{

            public void clear()
            {
/* 569*/        fromList.clear();
            }

            public int size()
            {
/* 572*/        return fromList.size();
            }

            public ListIterator listIterator(int i)
            {
/* 575*/        return new TransformedListIterator(fromList.listIterator(i)) {

                    Object transform(Object obj)
                    {
/* 578*/                return function.apply(obj);
                    }

                    final Lists.TransformingSequentialList this$0;

                    
                    {
/* 575*/                this$0 = Lists.TransformingSequentialList.this;
/* 575*/                super(listiterator);
                    }
        };
            }

            final List fromList;
            final Function function;

            _cls1.this._cls0(List list, Function function1)
            {
/* 560*/        fromList = (List)Preconditions.checkNotNull(list);
/* 561*/        function = (Function)Preconditions.checkNotNull(function1);
            }
}
