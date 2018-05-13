// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.util.List;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, Lists

static final class string extends ImmutableList
{

            public final int indexOf(Object obj)
            {
/* 712*/        if(obj instanceof Character)
/* 712*/            return string.indexOf(((Character)obj).charValue());
/* 712*/        else
/* 712*/            return -1;
            }

            public final int lastIndexOf(Object obj)
            {
/* 717*/        if(obj instanceof Character)
/* 717*/            return string.lastIndexOf(((Character)obj).charValue());
/* 717*/        else
/* 717*/            return -1;
            }

            public final ImmutableList subList(int i, int j)
            {
/* 723*/        Preconditions.checkPositionIndexes(i, j, size());
/* 724*/        return Lists.charactersOf(string.substring(i, j));
            }

            final boolean isPartialView()
            {
/* 728*/        return false;
            }

            public final Character get(int i)
            {
/* 732*/        Preconditions.checkElementIndex(i, size());
/* 733*/        return Character.valueOf(string.charAt(i));
            }

            public final int size()
            {
/* 737*/        return string.length();
            }

            public final volatile List subList(int i, int j)
            {
/* 701*/        return subList(i, j);
            }

            public final volatile Object get(int i)
            {
/* 701*/        return get(i);
            }

            private final String string;

            (String s)
            {
/* 708*/        string = s;
            }
}
