// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractNavigableMap, Collections2, Maps

static final class function extends AbstractNavigableMap
{

            public final NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 908*/        return Maps.asMap(set.subSet(obj, flag, obj1, flag1), function);
            }

            public final NavigableMap headMap(Object obj, boolean flag)
            {
/* 913*/        return Maps.asMap(set.headSet(obj, flag), function);
            }

            public final NavigableMap tailMap(Object obj, boolean flag)
            {
/* 918*/        return Maps.asMap(set.tailSet(obj, flag), function);
            }

            public final Comparator comparator()
            {
/* 923*/        return set.comparator();
            }

            public final Object get(Object obj)
            {
/* 929*/        if(Collections2.safeContains(set, obj))
                {
/* 931*/            obj = obj;
/* 932*/            return function.apply(obj);
                } else
                {
/* 934*/            return null;
                }
            }

            public final void clear()
            {
/* 940*/        set.clear();
            }

            final Iterator entryIterator()
            {
/* 945*/        return Maps.asMapEntryIterator(set, function);
            }

            final Iterator descendingEntryIterator()
            {
/* 950*/        return descendingMap().entrySet().iterator();
            }

            public final NavigableSet navigableKeySet()
            {
/* 955*/        return Maps.access$400(set);
            }

            public final int size()
            {
/* 960*/        return set.size();
            }

            public final NavigableMap descendingMap()
            {
/* 965*/        return Maps.asMap(set.descendingSet(), function);
            }

            private final NavigableSet set;
            private final Function function;

            I(NavigableSet navigableset, Function function1)
            {
/* 901*/        set = (NavigableSet)Preconditions.checkNotNull(navigableset);
/* 902*/        function = (Function)Preconditions.checkNotNull(function1);
            }
}
