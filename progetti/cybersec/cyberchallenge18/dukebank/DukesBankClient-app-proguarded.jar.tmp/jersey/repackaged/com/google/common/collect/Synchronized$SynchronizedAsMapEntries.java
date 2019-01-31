// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Iterators, Maps, ObjectArrays, 
//            Sets, Synchronized, ForwardingIterator, ForwardingMapEntry

static class _cls1.val.iterator extends _cls1.val.iterator
{

            public Iterator iterator()
            {
/* 848*/        final Iterator iterator = super.iterator();
/* 849*/        return new ForwardingIterator() {

                    protected Iterator _mthdelegate()
                    {
/* 851*/                return iterator;
                    }

                    public java.util.Map.Entry next()
                    {
/* 855*/                final java.util.Map.Entry entry = (java.util.Map.Entry)super.next();
/* 856*/                return new ForwardingMapEntry() {

                            protected java.util.Map.Entry _mthdelegate()
                            {
/* 858*/                        return entry;
                            }

                            public Collection getValue()
                            {
/* 861*/                        return Synchronized.access$400((Collection)entry.getValue(), mutex);
                            }

                            public volatile Object getValue()
                            {
/* 856*/                        return getValue();
                            }

                            protected volatile Object _mthdelegate()
                            {
/* 856*/                        return _mthdelegate();
                            }

                            final java.util.Map.Entry val$entry;
                            final _cls1 this$1;

                            
                            {
/* 856*/                        this$1 = _cls1.this;
/* 856*/                        entry = entry1;
/* 856*/                        super();
                            }
                };
                    }

                    public volatile Object next()
                    {
/* 849*/                return next();
                    }

                    protected volatile Object _mthdelegate()
                    {
/* 849*/                return _mthdelegate();
                    }

                    final Iterator val$iterator;
                    final Synchronized.SynchronizedAsMapEntries this$0;

                    
                    {
/* 849*/                this$0 = Synchronized.SynchronizedAsMapEntries.this;
/* 849*/                iterator = iterator1;
/* 849*/                super();
                    }
        };
            }

            public Object[] toArray()
            {
/* 871*/        Object obj = mutex;
/* 871*/        JVM INSTR monitorenter ;
/* 872*/        return ObjectArrays.toArrayImpl(_mthdelegate());
                Exception exception;
/* 873*/        exception;
/* 873*/        throw exception;
            }

            public Object[] toArray(Object aobj[])
            {
/* 876*/        Object obj = mutex;
/* 876*/        JVM INSTR monitorenter ;
/* 877*/        return ObjectArrays.toArrayImpl(_mthdelegate(), aobj);
/* 878*/        aobj;
/* 878*/        throw aobj;
            }

            public boolean contains(Object obj)
            {
/* 881*/        Object obj1 = mutex;
/* 881*/        JVM INSTR monitorenter ;
/* 882*/        return Maps.containsEntryImpl(_mthdelegate(), obj);
/* 883*/        obj;
/* 883*/        throw obj;
            }

            public boolean containsAll(Collection collection)
            {
/* 886*/        Object obj = mutex;
/* 886*/        JVM INSTR monitorenter ;
/* 887*/        return Collections2.containsAllImpl(_mthdelegate(), collection);
/* 888*/        collection;
/* 888*/        throw collection;
            }

            public boolean equals(Object obj)
            {
/* 891*/        if(obj == this)
/* 892*/            return true;
/* 894*/        Object obj1 = mutex;
/* 894*/        JVM INSTR monitorenter ;
/* 895*/        return Sets.equalsImpl(_mthdelegate(), obj);
/* 896*/        obj;
/* 896*/        throw obj;
            }

            public boolean remove(Object obj)
            {
/* 899*/        Object obj1 = mutex;
/* 899*/        JVM INSTR monitorenter ;
/* 900*/        return Maps.removeEntryImpl(_mthdelegate(), obj);
/* 901*/        obj;
/* 901*/        throw obj;
            }

            public boolean removeAll(Collection collection)
            {
/* 904*/        Object obj = mutex;
/* 904*/        JVM INSTR monitorenter ;
/* 905*/        return Iterators.removeAll(_mthdelegate().iterator(), collection);
/* 906*/        collection;
/* 906*/        throw collection;
            }

            public boolean retainAll(Collection collection)
            {
/* 909*/        Object obj = mutex;
/* 909*/        JVM INSTR monitorenter ;
/* 910*/        return Iterators.retainAll(_mthdelegate().iterator(), collection);
/* 911*/        collection;
/* 911*/        throw collection;
            }

            _cls1.val.iterator(Set set, Object obj)
            {
/* 843*/        super(set, obj);
            }
}
