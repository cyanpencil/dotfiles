// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Multimaps, Multimap

class <init> extends <init>
{

            Multimap multimap()
            {
/* 122*/        return AbstractMultimap.this;
            }

            public Iterator iterator()
            {
/* 127*/        return entryIterator();
            }

            final AbstractMultimap this$0;

            private ()
            {
/* 119*/        this$0 = AbstractMultimap.this;
/* 119*/        super();
            }

            this._cls0(this._cls0 _pcls0)
            {
/* 119*/        this();
            }
}
