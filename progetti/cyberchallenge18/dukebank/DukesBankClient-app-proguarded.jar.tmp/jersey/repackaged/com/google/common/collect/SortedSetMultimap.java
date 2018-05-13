// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SortedSetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            SetMultimap

public interface SortedSetMultimap
    extends SetMultimap
{

    public abstract SortedSet get(Object obj);

    public abstract SortedSet removeAll(Object obj);

    public abstract SortedSet replaceValues(Object obj, Iterable iterable);

    public abstract Map asMap();

    public abstract Comparator valueComparator();
}
