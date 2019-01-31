// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.collect.AbstractSequentialIterator;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

class ator extends AbstractSequentialIterator
{

            protected y computeNext(y y)
            {
/*3713*/        if((y = y.getNextInWriteQueue()) == ad)
/*3714*/            return null;
/*3714*/        else
/*3714*/            return y;
            }

            protected volatile Object computeNext(Object obj)
            {
/*3710*/        return computeNext((y)obj);
            }

            final y this$0;

            y(y y1)
            {
/*3710*/        this$0 = this._cls0.this;
/*3710*/        super(y1);
            }
}
