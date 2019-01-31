// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HighLevelEncoder.java

package com.google.zxing.aztec.encoder;

import java.util.Comparator;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            HighLevelEncoder, State

class this._cls0
    implements Comparator
{

            public int compare(State state, State state1)
            {
/* 195*/        return state.getBitCount() - state1.getBitCount();
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 192*/        return compare((State)obj, (State)obj1);
            }

            final HighLevelEncoder this$0;

            ()
            {
/* 192*/        this$0 = HighLevelEncoder.this;
/* 192*/        super();
            }
}
