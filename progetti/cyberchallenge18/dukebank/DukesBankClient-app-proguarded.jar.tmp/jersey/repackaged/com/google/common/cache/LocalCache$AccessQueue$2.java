// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.collect.AbstractSequentialIterator;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

class tor extends AbstractSequentialIterator
{

            protected  computeNext( )
            {
/*3850*/        if(( = .getNextInAccessQueue()) == ad)
/*3851*/            return null;
/*3851*/        else
/*3851*/            return ;
            }

            protected volatile Object computeNext(Object obj)
            {
/*3847*/        return computeNext(()obj);
            }

            final  this$0;

            ( 1)
            {
/*3847*/        this$0 = this._cls0.this;
/*3847*/        super(1);
            }
}
