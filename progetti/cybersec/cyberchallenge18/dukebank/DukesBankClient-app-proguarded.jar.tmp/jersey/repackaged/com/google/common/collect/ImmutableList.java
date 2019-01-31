// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.io.*;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, Lists, ObjectArrays, RegularImmutableList, 
//            SingletonImmutableList, UnmodifiableIterator, UnmodifiableListIterator, AbstractIndexedListIterator

public abstract class ImmutableList extends ImmutableCollection
    implements List, RandomAccess
{
    public static final class Builder extends ImmutableCollection.ArrayBasedBuilder
    {

                public final Builder add(Object obj)
                {
/* 652*/            super.add(obj);
/* 653*/            return this;
                }

                public final Builder addAll(Iterator iterator1)
                {
/* 691*/            super.addAll(iterator1);
/* 692*/            return this;
                }

                public final ImmutableList build()
                {
/* 700*/            return ImmutableList.asImmutableList(contents, size);
                }

                public final volatile ImmutableCollection.ArrayBasedBuilder add(Object obj)
                {
/* 630*/            return add(obj);
                }

                public final volatile ImmutableCollection.Builder addAll(Iterator iterator1)
                {
/* 630*/            return addAll(iterator1);
                }

                public final volatile ImmutableCollection.Builder add(Object obj)
                {
/* 630*/            return add(obj);
                }

                public Builder()
                {
/* 636*/            this(4);
                }

                Builder(int i)
                {
/* 641*/            super(i);
                }
    }

    static class SerializedForm
        implements Serializable
    {

                final Object elements[];

                SerializedForm(Object aobj[])
                {
/* 589*/            elements = aobj;
                }
    }

    static class ReverseImmutableList extends ImmutableList
    {

                private int reverseIndex(int i)
                {
/* 521*/            return size() - 1 - i;
                }

                private int reversePosition(int i)
                {
/* 525*/            return size() - i;
                }

                public ImmutableList reverse()
                {
/* 529*/            return forwardList;
                }

                public boolean contains(Object obj)
                {
/* 533*/            return forwardList.contains(obj);
                }

                public int indexOf(Object obj)
                {
/* 537*/            if((obj = forwardList.lastIndexOf(obj)) >= 0)
/* 538*/                return reverseIndex(((int) (obj)));
/* 538*/            else
/* 538*/                return -1;
                }

                public int lastIndexOf(Object obj)
                {
/* 542*/            if((obj = forwardList.indexOf(obj)) >= 0)
/* 543*/                return reverseIndex(((int) (obj)));
/* 543*/            else
/* 543*/                return -1;
                }

                public ImmutableList subList(int i, int j)
                {
/* 547*/            Preconditions.checkPositionIndexes(i, j, size());
/* 548*/            return forwardList.subList(reversePosition(j), reversePosition(i)).reverse();
                }

                public Object get(int i)
                {
/* 553*/            Preconditions.checkElementIndex(i, size());
/* 554*/            return forwardList.get(reverseIndex(i));
                }

                public int size()
                {
/* 558*/            return forwardList.size();
                }

                boolean isPartialView()
                {
/* 562*/            return forwardList.isPartialView();
                }

                public volatile List subList(int i, int j)
                {
/* 513*/            return subList(i, j);
                }

                public volatile ListIterator listIterator(int i)
                {
/* 513*/            return listIterator(i);
                }

                public volatile ListIterator listIterator()
                {
/* 513*/            return listIterator();
                }

                public volatile Iterator iterator()
                {
/* 513*/            return iterator();
                }

                private final transient ImmutableList forwardList;

                ReverseImmutableList(ImmutableList immutablelist)
                {
/* 517*/            forwardList = immutablelist;
                }
    }

    class SubList extends ImmutableList
    {

                public int size()
                {
/* 413*/            return length;
                }

                public Object get(int i)
                {
/* 418*/            Preconditions.checkElementIndex(i, length);
/* 419*/            return ImmutableList.this.get(i + offset);
                }

                public ImmutableList subList(int i, int j)
                {
/* 424*/            Preconditions.checkPositionIndexes(i, j, length);
/* 425*/            return ImmutableList.this.subList(i + offset, j + offset);
                }

                boolean isPartialView()
                {
/* 430*/            return true;
                }

                public volatile List subList(int i, int j)
                {
/* 402*/            return subList(i, j);
                }

                public volatile ListIterator listIterator(int i)
                {
/* 402*/            return listIterator(i);
                }

                public volatile ListIterator listIterator()
                {
/* 402*/            return listIterator();
                }

                public volatile Iterator iterator()
                {
/* 402*/            return iterator();
                }

                final transient int offset;
                final transient int length;
                final ImmutableList this$0;

                SubList(int i, int j)
                {
/* 406*/            this$0 = ImmutableList.this;
/* 406*/            super();
/* 407*/            offset = i;
/* 408*/            length = j;
                }
    }


            public static ImmutableList of()
            {
/*  77*/        return EMPTY;
            }

            public static ImmutableList of(Object obj)
            {
/*  89*/        return new SingletonImmutableList(obj);
            }

            public static ImmutableList of(Object obj, Object obj1)
            {
/*  98*/        return construct(new Object[] {
/*  98*/            obj, obj1
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2)
            {
/* 107*/        return construct(new Object[] {
/* 107*/            obj, obj1, obj2
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3)
            {
/* 116*/        return construct(new Object[] {
/* 116*/            obj, obj1, obj2, obj3
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
            {
/* 125*/        return construct(new Object[] {
/* 125*/            obj, obj1, obj2, obj3, obj4
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
            {
/* 134*/        return construct(new Object[] {
/* 134*/            obj, obj1, obj2, obj3, obj4, obj5
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
            {
/* 144*/        return construct(new Object[] {
/* 144*/            obj, obj1, obj2, obj3, obj4, obj5, obj6
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7)
            {
/* 154*/        return construct(new Object[] {
/* 154*/            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8)
            {
/* 164*/        return construct(new Object[] {
/* 164*/            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8, Object obj9)
            {
/* 174*/        return construct(new Object[] {
/* 174*/            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9
                });
            }

            public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8, Object obj9, Object obj10)
            {
/* 184*/        return construct(new Object[] {
/* 184*/            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, 
/* 184*/            obj10
                });
            }

            public static transient ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8, Object obj9, Object obj10, Object obj11, Object aobj[])
            {
                Object aobj1[];
/* 199*/        (aobj1 = new Object[12 + aobj.length])[0] = obj;
/* 201*/        aobj1[1] = obj1;
/* 202*/        aobj1[2] = obj2;
/* 203*/        aobj1[3] = obj3;
/* 204*/        aobj1[4] = obj4;
/* 205*/        aobj1[5] = obj5;
/* 206*/        aobj1[6] = obj6;
/* 207*/        aobj1[7] = obj7;
/* 208*/        aobj1[8] = obj8;
/* 209*/        aobj1[9] = obj9;
/* 210*/        aobj1[10] = obj10;
/* 211*/        aobj1[11] = obj11;
/* 212*/        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 12, aobj.length);
/* 213*/        return construct(aobj1);
            }

            public static ImmutableList copyOf(Iterable iterable)
            {
/* 225*/        Preconditions.checkNotNull(iterable);
/* 226*/        if(iterable instanceof Collection)
/* 226*/            return copyOf((Collection)iterable);
/* 226*/        else
/* 226*/            return copyOf(iterable.iterator());
            }

            public static ImmutableList copyOf(Collection collection)
            {
/* 251*/        if(collection instanceof ImmutableCollection)
                {
/* 253*/            if((collection = ((ImmutableCollection)collection).asList()).isPartialView())
/* 254*/                return asImmutableList(collection.toArray());
/* 254*/            else
/* 254*/                return collection;
                } else
                {
/* 258*/            return construct(collection.toArray());
                }
            }

            public static ImmutableList copyOf(Iterator iterator1)
            {
/* 268*/        if(!iterator1.hasNext())
/* 269*/            return of();
/* 271*/        Object obj = iterator1.next();
/* 272*/        if(!iterator1.hasNext())
/* 273*/            return of(obj);
/* 275*/        else
/* 275*/            return (new Builder()).add(obj).addAll(iterator1).build();
            }

            public static ImmutableList copyOf(Object aobj[])
            {
/* 289*/        switch(aobj.length)
                {
/* 291*/        case 0: // '\0'
/* 291*/            return of();

/* 293*/        case 1: // '\001'
/* 293*/            return new SingletonImmutableList(aobj[0]);
                }
/* 295*/        return new RegularImmutableList(ObjectArrays.checkElementsNotNull((Object[])((Object []) (aobj)).clone()));
            }

            private static transient ImmutableList construct(Object aobj[])
            {
/* 303*/        return asImmutableList(ObjectArrays.checkElementsNotNull(aobj));
            }

            static ImmutableList asImmutableList(Object aobj[])
            {
/* 312*/        return asImmutableList(aobj, aobj.length);
            }

            static ImmutableList asImmutableList(Object aobj[], int i)
            {
/* 320*/        switch(i)
                {
/* 322*/        case 0: // '\0'
/* 322*/            return of();

/* 325*/        case 1: // '\001'
/* 325*/            return ((ImmutableList) (aobj = new SingletonImmutableList(aobj[0])));
                }
/* 328*/        if(i < aobj.length)
/* 329*/            aobj = ObjectArrays.arraysCopyOf(aobj, i);
/* 331*/        return new RegularImmutableList(aobj);
            }

            ImmutableList()
            {
            }

            public UnmodifiableIterator iterator()
            {
/* 340*/        return listIterator();
            }

            public UnmodifiableListIterator listIterator()
            {
/* 344*/        return listIterator(0);
            }

            public UnmodifiableListIterator listIterator(int i)
            {
/* 348*/        return new AbstractIndexedListIterator(size(), i) {

                    protected Object get(int j)
                    {
/* 351*/                return ImmutableList.this.get(j);
                    }

                    final ImmutableList this$0;

                    
                    {
/* 348*/                this$0 = ImmutableList.this;
/* 348*/                super(i, j);
                    }
        };
            }

            public int indexOf(Object obj)
            {
/* 358*/        if(obj == null)
/* 358*/            return -1;
/* 358*/        else
/* 358*/            return Lists.indexOfImpl(this, obj);
            }

            public int lastIndexOf(Object obj)
            {
/* 363*/        if(obj == null)
/* 363*/            return -1;
/* 363*/        else
/* 363*/            return Lists.lastIndexOfImpl(this, obj);
            }

            public boolean contains(Object obj)
            {
/* 368*/        return indexOf(obj) >= 0;
            }

            public ImmutableList subList(int i, int j)
            {
/* 381*/        Preconditions.checkPositionIndexes(i, j, size());
                int k;
/* 382*/        switch(k = j - i)
                {
/* 385*/        case 0: // '\0'
/* 385*/            return of();

/* 387*/        case 1: // '\001'
/* 387*/            return of(get(i));
                }
/* 389*/        return subListUnchecked(i, j);
            }

            ImmutableList subListUnchecked(int i, int j)
            {
/* 399*/        return new SubList(i, j - i);
            }

            /**
             * @deprecated Method addAll is deprecated
             */

            public final boolean addAll(int i, Collection collection)
            {
/* 443*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method set is deprecated
             */

            public final Object set(int i, Object obj)
            {
/* 455*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method add is deprecated
             */

            public final void add(int i, Object obj)
            {
/* 467*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public final Object remove(int i)
            {
/* 479*/        throw new UnsupportedOperationException();
            }

            public final ImmutableList asList()
            {
/* 488*/        return this;
            }

            int copyIntoArray(Object aobj[], int i)
            {
/* 494*/        int j = size();
/* 495*/        for(int k = 0; k < j; k++)
/* 496*/            aobj[i + k] = get(k);

/* 498*/        return i + j;
            }

            public ImmutableList reverse()
            {
/* 510*/        return new ReverseImmutableList(this);
            }

            public boolean equals(Object obj)
            {
/* 567*/        return Lists.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 571*/        int i = 1;
/* 572*/        int j = size();
/* 573*/        for(int k = 0; k < j; k++)
/* 574*/            i = ~~(i = i * 31 + get(k).hashCode());

/* 579*/        return i;
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws InvalidObjectException
            {
/* 599*/        throw new InvalidObjectException("Use SerializedForm");
            }

            Object writeReplace()
            {
/* 603*/        return new SerializedForm(toArray());
            }

            public static Builder builder()
            {
/* 611*/        return new Builder();
            }

            public volatile Iterator iterator()
            {
/*  61*/        return iterator();
            }

            public volatile List subList(int i, int j)
            {
/*  61*/        return subList(i, j);
            }

            public volatile ListIterator listIterator(int i)
            {
/*  61*/        return listIterator(i);
            }

            public volatile ListIterator listIterator()
            {
/*  61*/        return listIterator();
            }

            private static final ImmutableList EMPTY;

            static 
            {
/*  66*/        EMPTY = new RegularImmutableList(ObjectArrays.EMPTY_ARRAY);
            }
}
