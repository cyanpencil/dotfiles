// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableCollection.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, ObjectArrays, RegularImmutableAsList, UnmodifiableIterator, 
//            CollectPreconditions

public abstract class ImmutableCollection extends AbstractCollection
    implements Serializable
{
    static abstract class ArrayBasedBuilder extends Builder
    {

                private void ensureCapacity(int i)
                {
/* 331*/            if(contents.length < i)
/* 332*/                contents = ObjectArrays.arraysCopyOf(contents, expandedCapacity(contents.length, i));
                }

                public ArrayBasedBuilder add(Object obj)
                {
/* 339*/            Preconditions.checkNotNull(obj);
/* 340*/            ensureCapacity(size + 1);
/* 341*/            contents[size++] = obj;
/* 342*/            return this;
                }

                public volatile Builder add(Object obj)
                {
/* 316*/            return add(obj);
                }

                Object contents[];
                int size;

                ArrayBasedBuilder(int i)
                {
/* 321*/            CollectPreconditions.checkNonnegative(i, "initialCapacity");
/* 322*/            contents = new Object[i];
/* 323*/            size = 0;
                }
    }

    public static abstract class Builder
    {

                static int expandedCapacity(int i, int j)
                {
/* 219*/            if(j < 0)
/* 220*/                throw new AssertionError("cannot store more than MAX_VALUE elements");
/* 223*/            if((i = i + (i >> 1) + 1) < j)
/* 225*/                i = Integer.highestOneBit(j - 1) << 1;
/* 227*/            if(i < 0)
/* 228*/                i = 0x7fffffff;
/* 231*/            return i;
                }

                public abstract Builder add(Object obj);

                public Builder addAll(Iterator iterator1)
                {
/* 300*/            for(; iterator1.hasNext(); add(iterator1.next()));
/* 303*/            return this;
                }

                Builder()
                {
                }
    }


            ImmutableCollection()
            {
            }

            public abstract UnmodifiableIterator iterator();

            public final Object[] toArray()
            {
                int i;
/*  60*/        if((i = size()) == 0)
                {
/*  62*/            return ObjectArrays.EMPTY_ARRAY;
                } else
                {
/*  64*/            Object aobj[] = new Object[i];
/*  65*/            copyIntoArray(aobj, 0);
/*  66*/            return aobj;
                }
            }

            public final Object[] toArray(Object aobj[])
            {
/*  71*/        Preconditions.checkNotNull(((Object) (aobj)));
/*  72*/        int i = size();
/*  73*/        if(aobj.length < i)
/*  74*/            aobj = ObjectArrays.newArray(aobj, i);
/*  75*/        else
/*  75*/        if(aobj.length > i)
/*  76*/            aobj[i] = null;
/*  78*/        copyIntoArray(aobj, 0);
/*  79*/        return aobj;
            }

            public boolean contains(Object obj)
            {
/*  84*/        return obj != null && super.contains(obj);
            }

            /**
             * @deprecated Method add is deprecated
             */

            public final boolean add(Object obj)
            {
/*  96*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public final boolean remove(Object obj)
            {
/* 108*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method addAll is deprecated
             */

            public final boolean addAll(Collection collection)
            {
/* 120*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method removeAll is deprecated
             */

            public final boolean removeAll(Collection collection)
            {
/* 132*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method retainAll is deprecated
             */

            public final boolean retainAll(Collection collection)
            {
/* 144*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method clear is deprecated
             */

            public final void clear()
            {
/* 156*/        throw new UnsupportedOperationException();
            }

            public ImmutableList asList()
            {
                ImmutableList immutablelist;
/* 171*/        if((immutablelist = asList) == null)
/* 172*/            return asList = createAsList();
/* 172*/        else
/* 172*/            return immutablelist;
            }

            ImmutableList createAsList()
            {
/* 176*/        switch(size())
                {
/* 178*/        case 0: // '\0'
/* 178*/            return ImmutableList.of();

/* 180*/        case 1: // '\001'
/* 180*/            return ImmutableList.of(iterator().next());
                }
/* 182*/        return new RegularImmutableAsList(this, toArray());
            }

            abstract boolean isPartialView();

            int copyIntoArray(Object aobj[], int i)
            {
/* 199*/        for(Iterator iterator1 = iterator(); iterator1.hasNext();)
                {
/* 199*/            Object obj = iterator1.next();
/* 200*/            aobj[i++] = obj;
                }

/* 202*/        return i;
            }

            Object writeReplace()
            {
/* 207*/        return new ImmutableList.SerializedForm(toArray());
            }

            public volatile Iterator iterator()
            {
/*  45*/        return iterator();
            }

            private transient ImmutableList asList;
}
