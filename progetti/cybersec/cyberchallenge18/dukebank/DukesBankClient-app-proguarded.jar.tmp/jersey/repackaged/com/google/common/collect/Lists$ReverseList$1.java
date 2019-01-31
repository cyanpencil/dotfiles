// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Lists

class val.forwardIterator
    implements ListIterator
{

            public void add(Object obj)
            {
/* 867*/        val$forwardIterator.add(obj);
/* 868*/        val$forwardIterator.previous();
/* 869*/        canRemoveOrSet = false;
            }

            public boolean hasNext()
            {
/* 873*/        return val$forwardIterator.hasPrevious();
            }

            public boolean hasPrevious()
            {
/* 877*/        return val$forwardIterator.hasNext();
            }

            public Object next()
            {
/* 881*/        if(!hasNext())
                {
/* 882*/            throw new NoSuchElementException();
                } else
                {
/* 884*/            canRemoveOrSet = true;
/* 885*/            return val$forwardIterator.previous();
                }
            }

            public int nextIndex()
            {
/* 889*/        return cess._mth000(this._cls0.this, val$forwardIterator.nextIndex());
            }

            public Object previous()
            {
/* 893*/        if(!hasPrevious())
                {
/* 894*/            throw new NoSuchElementException();
                } else
                {
/* 896*/            canRemoveOrSet = true;
/* 897*/            return val$forwardIterator.next();
                }
            }

            public int previousIndex()
            {
/* 901*/        return nextIndex() - 1;
            }

            public void remove()
            {
/* 905*/        CollectPreconditions.checkRemove(canRemoveOrSet);
/* 906*/        val$forwardIterator.remove();
/* 907*/        canRemoveOrSet = false;
            }

            public void set(Object obj)
            {
/* 911*/        Preconditions.checkState(canRemoveOrSet);
/* 912*/        val$forwardIterator.set(obj);
            }

            boolean canRemoveOrSet;
            final ListIterator val$forwardIterator;
            final val.forwardIterator this$0;

            ()
            {
/* 862*/        this$0 = final_;
/* 862*/        val$forwardIterator = ListIterator.this;
/* 862*/        super();
            }
}
