// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, UnmodifiableIterator

static abstract class _cls1 extends Enum
    implements Function
{

            public static final VALUE KEY;
            public static final VALUE VALUE;
            private static final VALUE $VALUES[];

            static 
            {
/*  86*/        KEY = new Maps.EntryFunction("KEY", 0) {

                    public final Object apply(java.util.Map.Entry entry)
                    {
/*  90*/                return entry.getKey();
                    }

                    public final volatile Object apply(Object obj)
                    {
/*  86*/                return apply((java.util.Map.Entry)obj);
                    }

        };
/*  93*/        VALUE = new Maps.EntryFunction("VALUE", 1) {

                    public final Object apply(java.util.Map.Entry entry)
                    {
/*  97*/                return entry.getValue();
                    }

                    public final volatile Object apply(Object obj)
                    {
/*  93*/                return apply((java.util.Map.Entry)obj);
                    }

        };
/*  85*/        $VALUES = (new .VALUES[] {
/*  85*/            KEY, VALUE
                });
            }

            private _cls1(String s, int i)
            {
/*  85*/        super(s, i);
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/collect/Maps$1

/* anonymous class */
    static class Maps._cls1 extends UnmodifiableIterator
    {

                public final boolean hasNext()
                {
/* 125*/            return entryIterator.hasNext();
                }

                public final Object next()
                {
/* 130*/            return ((java.util.Map.Entry)entryIterator.next()).getValue();
                }

                final UnmodifiableIterator val$entryIterator;

                    
                    {
/* 122*/                entryIterator = unmodifiableiterator;
/* 122*/                super();
                    }
    }

}
