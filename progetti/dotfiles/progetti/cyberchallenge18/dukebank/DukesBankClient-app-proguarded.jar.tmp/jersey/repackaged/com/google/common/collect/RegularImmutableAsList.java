// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableAsList.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableAsList, ImmutableList, ImmutableCollection, UnmodifiableListIterator

class RegularImmutableAsList extends ImmutableAsList
{

            RegularImmutableAsList(ImmutableCollection immutablecollection, ImmutableList immutablelist)
            {
/*  35*/        _flddelegate = immutablecollection;
/*  36*/        delegateList = immutablelist;
            }

            RegularImmutableAsList(ImmutableCollection immutablecollection, Object aobj[])
            {
/*  40*/        this(immutablecollection, ImmutableList.asImmutableList(aobj));
            }

            ImmutableCollection delegateCollection()
            {
/*  45*/        return _flddelegate;
            }

            public UnmodifiableListIterator listIterator(int i)
            {
/*  55*/        return delegateList.listIterator(i);
            }

            int copyIntoArray(Object aobj[], int i)
            {
/*  61*/        return delegateList.copyIntoArray(aobj, i);
            }

            public Object get(int i)
            {
/*  66*/        return delegateList.get(i);
            }

            public volatile ListIterator listIterator(int i)
            {
/*  28*/        return listIterator(i);
            }

            private final ImmutableCollection _flddelegate;
            private final ImmutableList delegateList;
}
