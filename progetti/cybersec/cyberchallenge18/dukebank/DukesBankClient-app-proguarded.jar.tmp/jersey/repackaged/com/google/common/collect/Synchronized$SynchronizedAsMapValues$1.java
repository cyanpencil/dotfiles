// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingIterator, Synchronized

class val.iterator extends ForwardingIterator
{

            protected Iterator _mthdelegate()
            {
/*1213*/        return val$iterator;
            }

            public Collection next()
            {
/*1216*/        return Synchronized.access$400((Collection)super.next(), tex);
            }

            public volatile Object next()
            {
/*1211*/        return next();
            }

            protected volatile Object _mthdelegate()
            {
/*1211*/        return _mthdelegate();
            }

            final Iterator val$iterator;
            final delegate this$0;

            ()
            {
/*1211*/        this$0 = final_;
/*1211*/        val$iterator = Iterator.this;
/*1211*/        super();
            }
}
