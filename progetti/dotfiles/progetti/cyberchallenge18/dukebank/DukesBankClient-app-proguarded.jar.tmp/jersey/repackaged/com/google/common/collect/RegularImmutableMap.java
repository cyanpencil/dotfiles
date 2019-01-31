// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, CollectPreconditions, Hashing, ImmutableMapEntry, 
//            ImmutableSet, ImmutableMapEntrySet, ImmutableList, RegularImmutableAsList, 
//            UnmodifiableIterator

final class RegularImmutableMap extends ImmutableMap
{
    class EntrySet extends ImmutableMapEntrySet
    {

                ImmutableMap map()
                {
/* 191*/            return RegularImmutableMap.this;
                }

                public UnmodifiableIterator iterator()
                {
/* 196*/            return asList().iterator();
                }

                ImmutableList createAsList()
                {
/* 201*/            return new RegularImmutableAsList(this, entries);
                }

                public volatile Iterator iterator()
                {
/* 188*/            return iterator();
                }

                final RegularImmutableMap this$0;

                private EntrySet()
                {
/* 189*/            this$0 = RegularImmutableMap.this;
/* 189*/            super();
                }

    }

    static final class NonTerminalMapEntry extends ImmutableMapEntry
    {

                final ImmutableMapEntry getNextInKeyBucket()
                {
/* 123*/            return nextInKeyBucket;
                }

                private final ImmutableMapEntry nextInKeyBucket;

                NonTerminalMapEntry(Object obj, Object obj1, ImmutableMapEntry immutablemapentry)
                {
/* 112*/            super(obj, obj1);
/* 113*/            nextInKeyBucket = immutablemapentry;
                }

                NonTerminalMapEntry(ImmutableMapEntry immutablemapentry, ImmutableMapEntry immutablemapentry1)
                {
/* 117*/            super(immutablemapentry);
/* 118*/            nextInKeyBucket = immutablemapentry1;
                }
    }


            transient RegularImmutableMap(ImmutableMapEntry.TerminalEntry aterminalentry[])
            {
/*  44*/        this(aterminalentry.length, aterminalentry);
            }

            RegularImmutableMap(int i, ImmutableMapEntry.TerminalEntry aterminalentry[])
            {
/*  54*/        entries = createEntryArray(i);
/*  55*/        int j = Hashing.closedTableSize(i, 1.2D);
/*  56*/        table = createEntryArray(j);
/*  57*/        mask = j - 1;
/*  58*/        for(int k = 0; k < i; k++)
                {
                    Object obj;
                    Object obj1;
/*  60*/            int l = Hashing.smear((obj1 = ((ImmutableMapEntry.TerminalEntry) (obj = aterminalentry[k])).getKey()).hashCode()) & mask;
                    ImmutableMapEntry immutablemapentry;
/*  63*/            obj = (immutablemapentry = table[l]) != null ? ((Object) (new NonTerminalMapEntry(((ImmutableMapEntry) (obj)), immutablemapentry))) : obj;
/*  68*/            table[l] = ((ImmutableMapEntry) (obj));
/*  69*/            entries[k] = ((ImmutableMapEntry) (obj));
/*  70*/            checkNoConflictInBucket(obj1, ((ImmutableMapEntry) (obj)), immutablemapentry);
                }

            }

            RegularImmutableMap(java.util.Map.Entry aentry[])
            {
/*  78*/        int i = aentry.length;
/*  79*/        entries = createEntryArray(i);
/*  80*/        int j = Hashing.closedTableSize(i, 1.2D);
/*  81*/        table = createEntryArray(j);
/*  82*/        mask = j - 1;
/*  83*/        for(int k = 0; k < i; k++)
                {
                    Object obj;
/*  85*/            Object obj1 = ((java.util.Map.Entry) (obj = aentry[k])).getKey();
/*  87*/            obj = ((java.util.Map.Entry) (obj)).getValue();
/*  88*/            CollectPreconditions.checkEntryNotNull(obj1, obj);
/*  89*/            int l = Hashing.smear(obj1.hashCode()) & mask;
                    ImmutableMapEntry immutablemapentry;
/*  90*/            obj = (immutablemapentry = table[l]) != null ? ((Object) (new NonTerminalMapEntry(obj1, obj, immutablemapentry))) : ((Object) (new ImmutableMapEntry.TerminalEntry(obj1, obj)));
/*  95*/            table[l] = ((ImmutableMapEntry) (obj));
/*  96*/            entries[k] = ((ImmutableMapEntry) (obj));
/*  97*/            checkNoConflictInBucket(obj1, ((ImmutableMapEntry) (obj)), immutablemapentry);
                }

            }

            private void checkNoConflictInBucket(Object obj, ImmutableMapEntry immutablemapentry, ImmutableMapEntry immutablemapentry1)
            {
/* 103*/        for(; immutablemapentry1 != null; immutablemapentry1 = immutablemapentry1.getNextInKeyBucket())
/* 104*/            checkNoConflict(!obj.equals(immutablemapentry1.getKey()), "key", immutablemapentry, immutablemapentry1);

            }

            private ImmutableMapEntry[] createEntryArray(int i)
            {
/* 148*/        return new ImmutableMapEntry[i];
            }

            public final Object get(Object obj)
            {
/* 152*/        if(obj == null)
/* 153*/            return null;
/* 155*/        int i = Hashing.smear(obj.hashCode()) & mask;
/* 156*/        for(ImmutableMapEntry immutablemapentry = table[i]; immutablemapentry != null; immutablemapentry = immutablemapentry.getNextInKeyBucket())
                {
/* 159*/            Object obj1 = immutablemapentry.getKey();
/* 167*/            if(obj.equals(obj1))
/* 168*/                return immutablemapentry.getValue();
                }

/* 171*/        return null;
            }

            public final int size()
            {
/* 176*/        return entries.length;
            }

            final boolean isPartialView()
            {
/* 180*/        return false;
            }

            final ImmutableSet createEntrySet()
            {
/* 185*/        return new EntrySet();
            }

            private final transient ImmutableMapEntry entries[];
            private final transient ImmutableMapEntry table[];
            private final transient int mask;

}
