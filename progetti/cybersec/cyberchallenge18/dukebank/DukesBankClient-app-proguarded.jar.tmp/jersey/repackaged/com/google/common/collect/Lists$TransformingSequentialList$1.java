// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedListIterator, Lists

class this._cls0 extends TransformedListIterator
{

            Object transform(Object obj)
            {
/* 578*/        return nction.apply(obj);
            }

            final nction this$0;

            (ListIterator listiterator)
            {
/* 575*/        this$0 = this._cls0.this;
/* 575*/        super(listiterator);
            }
}
