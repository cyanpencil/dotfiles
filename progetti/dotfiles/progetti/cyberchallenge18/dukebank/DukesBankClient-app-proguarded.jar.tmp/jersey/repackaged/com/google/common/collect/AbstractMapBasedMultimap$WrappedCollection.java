// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class getDelegate extends AbstractCollection
{
    class WrappedIterator
        implements Iterator
    {

                void validateIterator()
                {
/* 471*/            refreshIfEmpty();
/* 472*/            if(_flddelegate != originalDelegate)
/* 473*/                throw new ConcurrentModificationException();
/* 475*/            else
/* 475*/                return;
                }

                public boolean hasNext()
                {
/* 479*/            validateIterator();
/* 480*/            return delegateIterator.hasNext();
                }

                public Object next()
                {
/* 485*/            validateIterator();
/* 486*/            return delegateIterator.next();
                }

                public void remove()
                {
/* 491*/            delegateIterator.remove();
/* 492*/            int _tmp = AbstractMapBasedMultimap.access$210(this$0);
/* 493*/            removeIfEmpty();
                }

                Iterator getDelegateIterator()
                {
/* 497*/            validateIterator();
/* 498*/            return delegateIterator;
                }

                final Iterator delegateIterator;
                final Collection originalDelegate;
                final AbstractMapBasedMultimap.WrappedCollection this$1;

                WrappedIterator()
                {
/* 458*/            this$1 = AbstractMapBasedMultimap.WrappedCollection.this;
/* 458*/            super();
/* 456*/            originalDelegate = _flddelegate;
/* 459*/            delegateIterator = AbstractMapBasedMultimap.access$100(this$0, _flddelegate);
                }

                WrappedIterator(Iterator iterator1)
                {
/* 462*/            this$1 = AbstractMapBasedMultimap.WrappedCollection.this;
/* 462*/            super();
/* 456*/            originalDelegate = _flddelegate;
/* 463*/            delegateIterator = iterator1;
                }
    }


            void refreshIfEmpty()
            {
                Collection collection;
/* 377*/        if(ancestor != null)
                {
/* 378*/            ancestor.refreshIfEmpty();
/* 379*/            if(ancestor.getDelegate() != ancestorDelegate)
/* 380*/                throw new ConcurrentModificationException();
                } else
/* 382*/        if(_flddelegate.isEmpty() && (collection = (Collection)AbstractMapBasedMultimap.access$000(AbstractMapBasedMultimap.this).get(key)) != null)
/* 385*/            _flddelegate = collection;
            }

            void removeIfEmpty()
            {
/* 395*/        if(ancestor != null)
                {
/* 396*/            ancestor.removeIfEmpty();
/* 396*/            return;
                }
/* 397*/        if(_flddelegate.isEmpty())
/* 398*/            AbstractMapBasedMultimap.access$000(AbstractMapBasedMultimap.this).remove(key);
            }

            Object getKey()
            {
/* 403*/        return key;
            }

            void addToMap()
            {
/* 414*/        if(ancestor != null)
                {
/* 415*/            ancestor.addToMap();
/* 415*/            return;
                } else
                {
/* 417*/            AbstractMapBasedMultimap.access$000(AbstractMapBasedMultimap.this).put(key, _flddelegate);
/* 419*/            return;
                }
            }

            public int size()
            {
/* 422*/        refreshIfEmpty();
/* 423*/        return _flddelegate.size();
            }

            public boolean equals(Object obj)
            {
/* 427*/        if(obj == this)
                {
/* 428*/            return true;
                } else
                {
/* 430*/            refreshIfEmpty();
/* 431*/            return _flddelegate.equals(obj);
                }
            }

            public int hashCode()
            {
/* 435*/        refreshIfEmpty();
/* 436*/        return _flddelegate.hashCode();
            }

            public String toString()
            {
/* 440*/        refreshIfEmpty();
/* 441*/        return _flddelegate.toString();
            }

            Collection getDelegate()
            {
/* 445*/        return _flddelegate;
            }

            public Iterator iterator()
            {
/* 449*/        refreshIfEmpty();
/* 450*/        return new WrappedIterator();
            }

            public boolean add(Object obj)
            {
/* 503*/        refreshIfEmpty();
/* 504*/        boolean flag = _flddelegate.isEmpty();
/* 505*/        if((obj = _flddelegate.add(obj)) != 0)
                {
/* 507*/            int _tmp = AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
/* 508*/            if(flag)
/* 509*/                addToMap();
                }
/* 512*/        return ((boolean) (obj));
            }

            addToMap getAncestor()
            {
/* 516*/        return ancestor;
            }

            public boolean addAll(Collection collection)
            {
/* 522*/        if(collection.isEmpty())
/* 523*/            return false;
/* 525*/        int i = size();
/* 526*/        if((collection = _flddelegate.addAll(collection)) != 0)
                {
/* 528*/            int j = _flddelegate.size();
/* 529*/            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, j - i);
/* 530*/            if(i == 0)
/* 531*/                addToMap();
                }
/* 534*/        return collection;
            }

            public boolean contains(Object obj)
            {
/* 538*/        refreshIfEmpty();
/* 539*/        return _flddelegate.contains(obj);
            }

            public boolean containsAll(Collection collection)
            {
/* 543*/        refreshIfEmpty();
/* 544*/        return _flddelegate.containsAll(collection);
            }

            public void clear()
            {
                int i;
/* 548*/        if((i = size()) == 0)
                {
/* 550*/            return;
                } else
                {
/* 552*/            _flddelegate.clear();
/* 553*/            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, i);
/* 554*/            removeIfEmpty();
/* 555*/            return;
                }
            }

            public boolean remove(Object obj)
            {
/* 558*/        refreshIfEmpty();
/* 559*/        if((obj = _flddelegate.remove(obj)) != 0)
                {
/* 561*/            int _tmp = AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
/* 562*/            removeIfEmpty();
                }
/* 564*/        return ((boolean) (obj));
            }

            public boolean removeAll(Collection collection)
            {
/* 568*/        if(collection.isEmpty())
/* 569*/            return false;
/* 571*/        int i = size();
/* 572*/        if((collection = _flddelegate.removeAll(collection)) != 0)
                {
/* 574*/            int j = _flddelegate.size();
/* 575*/            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, j - i);
/* 576*/            removeIfEmpty();
                }
/* 578*/        return collection;
            }

            public boolean retainAll(Collection collection)
            {
/* 582*/        Preconditions.checkNotNull(collection);
/* 583*/        int i = size();
/* 584*/        if((collection = _flddelegate.retainAll(collection)) != 0)
                {
/* 586*/            int j = _flddelegate.size();
/* 587*/            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, j - i);
/* 588*/            removeIfEmpty();
                }
/* 590*/        return collection;
            }

            final Object key;
            Collection _flddelegate;
            final removeIfEmpty ancestor;
            final Collection ancestorDelegate;
            final AbstractMapBasedMultimap this$0;

            WrappedIterator.delegateIterator(Object obj, Collection collection, WrappedIterator.delegateIterator delegateiterator)
            {
/* 361*/        this$0 = AbstractMapBasedMultimap.this;
/* 361*/        super();
/* 362*/        key = obj;
/* 363*/        _flddelegate = collection;
/* 364*/        ancestor = delegateiterator;
/* 365*/        ancestorDelegate = delegateiterator != null ? delegateiterator.getDelegate() : null;
            }
}
