// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            StandardTable

class this._cls2
    implements Function
{

            public Map apply(Object obj)
            {
/* 796*/        return column(obj);
            }

            public volatile Object apply(Object obj)
            {
/* 793*/        return apply(obj);
            }

            final apply this$2;

            ()
            {
/* 793*/        this$2 = this._cls2.this;
/* 793*/        super();
            }
}
