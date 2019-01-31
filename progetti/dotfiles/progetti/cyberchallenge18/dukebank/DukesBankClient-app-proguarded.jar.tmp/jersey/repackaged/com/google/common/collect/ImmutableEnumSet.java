// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEnumSet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, Iterables, Iterators, UnmodifiableIterator

final class ImmutableEnumSet extends ImmutableSet
{
    static class EnumSerializedForm
        implements Serializable
    {

                final EnumSet _flddelegate;

                EnumSerializedForm(EnumSet enumset)
                {
/* 111*/            _flddelegate = enumset;
                }
    }


            static ImmutableSet asImmutable(EnumSet enumset)
            {
/*  35*/        switch(enumset.size())
                {
/*  37*/        case 0: // '\0'
/*  37*/            return ImmutableSet.of();

/*  39*/        case 1: // '\001'
/*  39*/            return ImmutableSet.of(Iterables.getOnlyElement(enumset));
                }
/*  41*/        return new ImmutableEnumSet(enumset);
            }

            private ImmutableEnumSet(EnumSet enumset)
            {
/*  56*/        _flddelegate = enumset;
            }

            final boolean isPartialView()
            {
/*  60*/        return false;
            }

            public final UnmodifiableIterator iterator()
            {
/*  64*/        return Iterators.unmodifiableIterator(_flddelegate.iterator());
            }

            public final int size()
            {
/*  69*/        return _flddelegate.size();
            }

            public final boolean contains(Object obj)
            {
/*  73*/        return _flddelegate.contains(obj);
            }

            public final boolean containsAll(Collection collection)
            {
/*  77*/        return _flddelegate.containsAll(collection);
            }

            public final boolean isEmpty()
            {
/*  81*/        return _flddelegate.isEmpty();
            }

            public final boolean equals(Object obj)
            {
/*  85*/        return obj == this || _flddelegate.equals(obj);
            }

            public final int hashCode()
            {
                int i;
/*  91*/        if((i = hashCode) == 0)
/*  92*/            return hashCode = _flddelegate.hashCode();
/*  92*/        else
/*  92*/            return i;
            }

            public final String toString()
            {
/*  96*/        return _flddelegate.toString();
            }

            final Object writeReplace()
            {
/* 101*/        return new EnumSerializedForm(_flddelegate);
            }

            public final volatile Iterator iterator()
            {
/*  31*/        return iterator();
            }

            private final transient EnumSet _flddelegate;
            private transient int hashCode;
}
