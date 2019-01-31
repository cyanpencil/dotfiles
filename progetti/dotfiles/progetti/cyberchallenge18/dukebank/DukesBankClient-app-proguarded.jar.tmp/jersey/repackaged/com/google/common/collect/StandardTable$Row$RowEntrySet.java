// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps, StandardTable, ForwardingMapEntry

final class <init> extends <init>
{

            final Map map()
            {
/* 339*/        return this._cls1.this;
            }

            public final int size()
            {
                Map map1;
/* 344*/        if((map1 = p()) == null)
/* 345*/            return 0;
/* 345*/        else
/* 345*/            return map1.size();
            }

            public final Iterator iterator()
            {
                final Iterator iterator;
/* 350*/        if((iterator = p()) == null)
                {
/* 352*/            return Iterators.emptyModifiableIterator();
                } else
                {
/* 354*/            iterator = iterator.entrySet().iterator();
/* 355*/            return new Iterator() {

                        public boolean hasNext()
                        {
/* 357*/                    return iterator.hasNext();
                        }

                        public java.util.Map.Entry next()
                        {
/* 360*/                    final java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
/* 361*/                    return new ForwardingMapEntry() {

                                protected java.util.Map.Entry _mthdelegate()
                                {
/* 363*/                            return entry;
                                }

                                public Object setValue(Object obj)
                                {
/* 366*/                            return super.setValue(Preconditions.checkNotNull(obj));
                                }

                                public boolean equals(Object obj)
                                {
/* 371*/                            return standardEquals(obj);
                                }

                                protected volatile Object _mthdelegate()
                                {
/* 361*/                            return _mthdelegate();
                                }

                                final java.util.Map.Entry val$entry;
                                final _cls1 this$3;

                            
                            {
/* 361*/                        this$3 = _cls1.this;
/* 361*/                        entry = entry1;
/* 361*/                        super();
                            }
                    };
                        }

                        public void remove()
                        {
/* 378*/                    iterator.remove();
/* 379*/                    maintainEmptyInvariant();
                        }

                        public volatile Object next()
                        {
/* 355*/                    return next();
                        }

                        final Iterator val$iterator;
                        final StandardTable.Row.RowEntrySet this$2;

                    
                    {
/* 355*/                this$2 = StandardTable.Row.RowEntrySet.this;
/* 355*/                iterator = iterator1;
/* 355*/                super();
                    }
            };
                }
            }

            final _cls1.next this$1;

            private _cls1.val.iterator()
            {
/* 336*/        this$1 = this._cls1.this;
/* 336*/        super();
            }

            this._cls1(this._cls1 _pcls1_1)
            {
/* 336*/        this();
            }
}
