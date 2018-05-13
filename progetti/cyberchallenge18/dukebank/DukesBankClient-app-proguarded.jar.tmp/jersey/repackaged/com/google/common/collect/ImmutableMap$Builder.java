// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, ImmutableMap, ImmutableMapEntry, ObjectArrays, 
//            RegularImmutableMap

public static class size
{

            private void ensureCapacity(int i)
            {
/* 194*/        if(i > entries.length)
/* 195*/            entries = (minalEntry[])ObjectArrays.arraysCopyOf(entries, uilder.expandedCapacity(entries.length, i));
            }

            public entries put(Object obj, Object obj1)
            {
/* 205*/        ensureCapacity(size + 1);
/* 206*/        obj = ImmutableMap.entryOf(obj, obj1);
/* 208*/        entries[size++] = ((minalEntry) (obj));
/* 209*/        return this;
            }

            public ImmutableMap build()
            {
/* 248*/        switch(size)
                {
/* 250*/        case 0: // '\0'
/* 250*/            return ImmutableMap.of();

/* 252*/        case 1: // '\001'
/* 252*/            return ImmutableMap.of(entries[0].getKey(), entries[0].getValue());
                }
/* 254*/        return new RegularImmutableMap(size, entries);
            }

            minalEntry entries[];
            int size;

            public minalEntry()
            {
/* 184*/        this(4);
            }

            <init>(int i)
            {
/* 189*/        entries = new minalEntry[i];
/* 190*/        size = 0;
            }
}
