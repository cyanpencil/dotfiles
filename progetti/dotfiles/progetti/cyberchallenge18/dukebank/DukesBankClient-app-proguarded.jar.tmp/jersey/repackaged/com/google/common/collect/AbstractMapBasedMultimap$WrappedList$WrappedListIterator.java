// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.List;
import java.util.ListIterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class r extends r
    implements ListIterator
{

            private ListIterator getDelegateListIterator()
            {
/* 859*/        return (ListIterator)getDelegateIterator();
            }

            public boolean hasPrevious()
            {
/* 864*/        return getDelegateListIterator().hasPrevious();
            }

            public Object previous()
            {
/* 869*/        return getDelegateListIterator().previous();
            }

            public int nextIndex()
            {
/* 874*/        return getDelegateListIterator().nextIndex();
            }

            public int previousIndex()
            {
/* 879*/        return getDelegateListIterator().previousIndex();
            }

            public void set(Object obj)
            {
/* 884*/        getDelegateListIterator().set(obj);
            }

            public void add(Object obj)
            {
/* 889*/        boolean flag = getDelegateListIterator.this.getDelegateListIterator();
/* 890*/        getDelegateListIterator().add(obj);
/* 891*/        int _tmp = AbstractMapBasedMultimap.access$208(this._cls1.this.getDelegateListIterator);
/* 892*/        if(flag)
/* 893*/            _mth1();
            }

            final this._cls1 this$1;

            r()
            {
/* 852*/        this$1 = this._cls1.this;
/* 852*/        super(r.this);
            }

            public r(int i)
            {
/* 854*/        this$1 = this._cls1.this;
/* 855*/        super(r.this, r.this.r().listIterator(i));
            }
}
