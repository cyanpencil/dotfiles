// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, StandardTable, Lists, Sets

class <init> extends 
{
    class ColumnMapValues extends Maps.Values
    {

                public boolean remove(Object obj)
                {
                    java.util.Map.Entry entry;
/* 858*/            for(Iterator iterator = entrySet().iterator(); iterator.hasNext();)
/* 858*/                if(((Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).equals(obj))
                        {
/* 860*/                    StandardTable.access$1000(this$0, entry.getKey());
/* 861*/                    return true;
                        }

/* 864*/            return false;
                }

                public boolean removeAll(Collection collection)
                {
/* 868*/            Preconditions.checkNotNull(collection);
/* 869*/            boolean flag = false;
/* 870*/            Iterator iterator = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 870*/            do
                    {
/* 870*/                if(!iterator.hasNext())
/* 870*/                    break;
/* 870*/                Object obj = iterator.next();
/* 871*/                if(collection.contains(column(obj)))
                        {
/* 872*/                    StandardTable.access$1000(this$0, obj);
/* 873*/                    flag = true;
                        }
                    } while(true);
/* 876*/            return flag;
                }

                public boolean retainAll(Collection collection)
                {
/* 880*/            Preconditions.checkNotNull(collection);
/* 881*/            boolean flag = false;
/* 882*/            Iterator iterator = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 882*/            do
                    {
/* 882*/                if(!iterator.hasNext())
/* 882*/                    break;
/* 882*/                Object obj = iterator.next();
/* 883*/                if(!collection.contains(column(obj)))
                        {
/* 884*/                    StandardTable.access$1000(this$0, obj);
/* 885*/                    flag = true;
                        }
                    } while(true);
/* 888*/            return flag;
                }

                final StandardTable.ColumnMap this$1;

                ColumnMapValues()
                {
/* 853*/            this$1 = StandardTable.ColumnMap.this;
/* 854*/            super(StandardTable.ColumnMap.this);
                }
    }

    class ColumnMapEntrySet extends StandardTable.TableSet
    {

                public Iterator iterator()
                {
/* 793*/            return Maps.asMapEntryIterator(columnKeySet(), new Function() {

                        public Map apply(Object obj)
                        {
/* 796*/                    return column(obj);
                        }

                        public volatile Object apply(Object obj)
                        {
/* 793*/                    return apply(obj);
                        }

                        final ColumnMapEntrySet this$2;

                        
                        {
/* 793*/                    this$2 = ColumnMapEntrySet.this;
/* 793*/                    super();
                        }
            });
                }

                public int size()
                {
/* 802*/            return columnKeySet().size();
                }

                public boolean contains(Object obj)
                {
/* 806*/            if(obj instanceof java.util.Map.Entry)
                    {
/* 807*/                obj = (java.util.Map.Entry)obj;
/* 808*/                if(containsColumn(((java.util.Map.Entry) (obj)).getKey()))
                        {
/* 812*/                    Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
/* 813*/                    return get(obj1).equals(((java.util.Map.Entry) (obj)).getValue());
                        }
                    }
/* 816*/            return false;
                }

                public boolean remove(Object obj)
                {
/* 820*/            if(contains(obj))
                    {
/* 821*/                obj = (java.util.Map.Entry)obj;
/* 822*/                StandardTable.access$1000(this$0, ((java.util.Map.Entry) (obj)).getKey());
/* 823*/                return true;
                    } else
                    {
/* 825*/                return false;
                    }
                }

                public boolean removeAll(Collection collection)
                {
/* 835*/            Preconditions.checkNotNull(collection);
/* 836*/            return Sets.removeAllImpl(this, collection.iterator());
                }

                public boolean retainAll(Collection collection)
                {
/* 840*/            Preconditions.checkNotNull(collection);
/* 841*/            boolean flag = false;
/* 842*/            Iterator iterator1 = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 842*/            do
                    {
/* 842*/                if(!iterator1.hasNext())
/* 842*/                    break;
/* 842*/                Object obj = iterator1.next();
/* 843*/                if(!collection.contains(Maps.immutableEntry(obj, column(obj))))
                        {
/* 844*/                    StandardTable.access$1000(this$0, obj);
/* 845*/                    flag = true;
                        }
                    } while(true);
/* 848*/            return flag;
                }

                final StandardTable.ColumnMap this$1;

                ColumnMapEntrySet()
                {
/* 791*/            this$1 = StandardTable.ColumnMap.this;
/* 791*/            super(this$0, null);
                }
    }


            public Map get(Object obj)
            {
/* 768*/        if(containsColumn(obj))
/* 768*/            return column(obj);
/* 768*/        else
/* 768*/            return null;
            }

            public boolean containsKey(Object obj)
            {
/* 772*/        return containsColumn(obj);
            }

            public Map remove(Object obj)
            {
/* 776*/        if(containsColumn(obj))
/* 776*/            return StandardTable.access$1000(StandardTable.this, obj);
/* 776*/        else
/* 776*/            return null;
            }

            public Set createEntrySet()
            {
/* 780*/        return new ColumnMapEntrySet();
            }

            public Set keySet()
            {
/* 784*/        return columnKeySet();
            }

            Collection createValues()
            {
/* 788*/        return new ColumnMapValues();
            }

            public volatile Object remove(Object obj)
            {
/* 763*/        return remove(obj);
            }

            public volatile Object get(Object obj)
            {
/* 763*/        return get(obj);
            }

            final StandardTable this$0;

            private this._cls0()
            {
/* 763*/        this$0 = StandardTable.this;
/* 763*/        super();
            }

            ( )
            {
/* 763*/        this();
            }
}
