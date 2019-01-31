// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimaps

class this._cls0
    implements Function
{

            public Collection apply(Collection collection)
            {
/* 519*/        return Multimaps.access$000(collection);
            }

            public volatile Object apply(Object obj)
            {
/* 516*/        return apply((Collection)obj);
            }

            final apply this$0;

            ()
            {
/* 516*/        this$0 = this._cls0.this;
/* 516*/        super();
            }
}
