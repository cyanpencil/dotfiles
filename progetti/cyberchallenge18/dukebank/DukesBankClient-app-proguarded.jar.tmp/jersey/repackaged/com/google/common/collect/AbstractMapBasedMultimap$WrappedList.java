// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class ction extends ction
    implements List
{
    class WrappedListIterator extends AbstractMapBasedMultimap.WrappedCollection.WrappedIterator
        implements ListIterator
    {

                private ListIterator getDelegateListIterator()
                {
/* 859*/            return (ListIterator)getDelegateIterator();
                }

                public boolean hasPrevious()
                {
/* 864*/            return getDelegateListIterator().hasPrevious();
                }

                public Object previous()
                {
/* 869*/            return getDelegateListIterator().previous();
                }

                public int nextIndex()
                {
/* 874*/            return getDelegateListIterator().nextIndex();
                }

                public int previousIndex()
                {
/* 879*/            return getDelegateListIterator().previousIndex();
                }

                public void set(Object obj)
                {
/* 884*/            getDelegateListIterator().set(obj);
                }

                public void add(Object obj)
                {
/* 889*/            boolean flag = isEmpty();
/* 890*/            getDelegateListIterator().add(obj);
/* 891*/            int _tmp = AbstractMapBasedMultimap.access$208(this$0);
/* 892*/            if(flag)
/* 893*/                addToMap();
                }

                final AbstractMapBasedMultimap.WrappedList this$1;

                WrappedListIterator()
                {
/* 852*/            this$1 = AbstractMapBasedMultimap.WrappedList.this;
/* 852*/            super(AbstractMapBasedMultimap.WrappedList.this);
                }

                public WrappedListIterator(int i)
                {
/* 854*/            this$1 = AbstractMapBasedMultimap.WrappedList.this;
/* 855*/            super(AbstractMapBasedMultimap.WrappedList.this, getListDelegate().listIterator(i));
                }
    }


            List getListDelegate()
            {
/* 765*/        return (List)getDelegate();
            }

            public boolean addAll(int i, Collection collection)
            {
/* 770*/        if(collection.isEmpty())
/* 771*/            return false;
/* 773*/        int j = size();
/* 774*/        if((i = getListDelegate().addAll(i, collection)) != 0)
                {
/* 776*/            collection = getDelegate().size();
/* 777*/            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, collection - j);
/* 778*/            if(j == 0)
/* 779*/                addToMap();
                }
/* 782*/        return i;
            }

            public Object get(int i)
            {
/* 787*/        refreshIfEmpty();
/* 788*/        return getListDelegate().get(i);
            }

            public Object set(int i, Object obj)
            {
/* 793*/        refreshIfEmpty();
/* 794*/        return getListDelegate().set(i, obj);
            }

            public void add(int i, Object obj)
            {
/* 799*/        refreshIfEmpty();
/* 800*/        boolean flag = getDelegate().isEmpty();
/* 801*/        getListDelegate().add(i, obj);
/* 802*/        int _tmp = AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
/* 803*/        if(flag)
/* 804*/            addToMap();
            }

            public Object remove(int i)
            {
/* 810*/        refreshIfEmpty();
/* 811*/        i = ((int) (getListDelegate().remove(i)));
/* 812*/        int _tmp = AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
/* 813*/        removeIfEmpty();
/* 814*/        return i;
            }

            public int indexOf(Object obj)
            {
/* 819*/        refreshIfEmpty();
/* 820*/        return getListDelegate().indexOf(obj);
            }

            public int lastIndexOf(Object obj)
            {
/* 825*/        refreshIfEmpty();
/* 826*/        return getListDelegate().lastIndexOf(obj);
            }

            public ListIterator listIterator()
            {
/* 831*/        refreshIfEmpty();
/* 832*/        return new WrappedListIterator();
            }

            public ListIterator listIterator(int i)
            {
/* 837*/        refreshIfEmpty();
/* 838*/        return new WrappedListIterator(i);
            }

            public List subList(int i, int j)
            {
/* 843*/        refreshIfEmpty();
/* 844*/        return AbstractMapBasedMultimap.access$300(AbstractMapBasedMultimap.this, getKey(), getListDelegate().subList(i, j), ((ction) (getAncestor() != null ? getAncestor() : ((ction) (this)))));
            }

            final AbstractMapBasedMultimap this$0;

            ction(Object obj, List list, ction ction)
            {
/* 760*/        this$0 = AbstractMapBasedMultimap.this;
/* 761*/        super(AbstractMapBasedMultimap.this, obj, list, ction);
            }
}
