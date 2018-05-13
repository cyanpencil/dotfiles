// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMapEntrySet, ImmutableList, RegularImmutableAsList, RegularImmutableMap, 
//            ImmutableMap, UnmodifiableIterator

class <init> extends ImmutableMapEntrySet
{

            ImmutableMap map()
            {
/* 191*/        return RegularImmutableMap.this;
            }

            public UnmodifiableIterator iterator()
            {
/* 196*/        return asList().iterator();
            }

            ImmutableList createAsList()
            {
/* 201*/        return new RegularImmutableAsList(this, RegularImmutableMap.access$100(RegularImmutableMap.this));
            }

            public volatile Iterator iterator()
            {
/* 188*/        return iterator();
            }

            final RegularImmutableMap this$0;

            private ()
            {
/* 189*/        this$0 = RegularImmutableMap.this;
/* 189*/        super();
            }

            this._cls0(this._cls0 _pcls0)
            {
/* 189*/        this();
            }
}
