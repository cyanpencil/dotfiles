// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized, ForwardingIterator

static class init> extends init>
{

            public Iterator iterator()
            {
/*1210*/        final Iterator iterator = super.terator();
/*1211*/        return new ForwardingIterator() {

                    protected Iterator _mthdelegate()
                    {
/*1213*/                return iterator;
                    }

                    public Collection next()
                    {
/*1216*/                return Synchronized.access$400((Collection)super.next(), mutex);
                    }

                    public volatile Object next()
                    {
/*1211*/                return next();
                    }

                    protected volatile Object _mthdelegate()
                    {
/*1211*/                return _mthdelegate();
                    }

                    final Iterator val$iterator;
                    final Synchronized.SynchronizedAsMapValues this$0;

                    
                    {
/*1211*/                this$0 = Synchronized.SynchronizedAsMapValues.this;
/*1211*/                iterator = iterator1;
/*1211*/                super();
                    }
        };
            }

            _cls1.val.iterator(Collection collection, Object obj)
            {
/*1205*/        super(collection, obj, null);
            }
}
