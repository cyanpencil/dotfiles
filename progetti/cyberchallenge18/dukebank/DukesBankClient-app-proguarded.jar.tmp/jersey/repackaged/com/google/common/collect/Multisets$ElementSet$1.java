// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedIterator, Multiset, Multisets

class it> extends TransformedIterator
{

            Object transform(it> it>)
            {
/* 944*/        return it>.nt();
            }

            volatile Object transform(Object obj)
            {
/* 941*/        return transform((transform)obj);
            }

            final transform this$0;

            (Iterator iterator)
            {
/* 941*/        this$0 = this._cls0.this;
/* 941*/        super(iterator);
            }
}
