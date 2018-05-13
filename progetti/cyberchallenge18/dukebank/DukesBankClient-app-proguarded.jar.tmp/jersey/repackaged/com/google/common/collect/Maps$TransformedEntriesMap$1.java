// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps

class this._cls0 extends this._cls0
{

            Map map()
            {
/*1925*/        return this._cls0.this;
            }

            public Iterator iterator()
            {
/*1929*/        return Iterators.transform(omMap.entrySet().iterator(), Maps.asEntryToEntryFunction(ansformer));
            }

            final ansformer this$0;

            ()
            {
/*1923*/        this$0 = this._cls0.this;
/*1923*/        super();
            }
}
