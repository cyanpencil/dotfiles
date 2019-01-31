// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, EmptyImmutableSet, Hashing, ImmutableEnumSet, 
//            ImmutableSortedSet, ObjectArrays, RegularImmutableSet, Sets, 
//            SingletonImmutableSet, UnmodifiableIterator

public abstract class ImmutableSet extends ImmutableCollection
    implements Set
{
    static class SerializedForm
        implements Serializable
    {

                final Object elements[];

                SerializedForm(Object aobj[])
                {
/* 420*/            elements = aobj;
                }
    }


            public static ImmutableSet of()
            {
/*  84*/        return EmptyImmutableSet.INSTANCE;
            }

            public static ImmutableSet of(Object obj)
            {
/*  94*/        return new SingletonImmutableSet(obj);
            }

            private static transient ImmutableSet construct(int i, Object aobj[])
            {
/* 179*/        do
                {
                    Object obj;
/* 179*/            switch(i)
                    {
/* 181*/            case 0: // '\0'
/* 181*/                return of();

/* 184*/            case 1: // '\001'
/* 184*/                return of(obj = aobj[0]);
                    }
                    int j;
/* 189*/            Object aobj1[] = new Object[j = chooseTableSize(i)];
/* 191*/            int k = j - 1;
/* 192*/            int l = 0;
/* 193*/            int i1 = 0;
/* 194*/label0:
/* 194*/            for(int j1 = 0; j1 < i; j1++)
                    {
                        Object obj2;
                        int k1;
/* 195*/                int l1 = Hashing.smear(k1 = (obj2 = ObjectArrays.checkElementNotNull(aobj[j1], j1)).hashCode());
/* 198*/                do
                        {
/* 198*/                    int i2 = l1 & k;
                            Object obj3;
/* 199*/                    if((obj3 = aobj1[i2]) == null)
                            {
/* 202*/                        aobj[i1++] = obj2;
/* 203*/                        aobj1[i2] = obj2;
/* 204*/                        l += k1;
/* 205*/                        continue label0;
                            }
/* 206*/                    if(obj3.equals(obj2))
/* 197*/                        continue label0;
/* 197*/                    l1++;
                        } while(true);
                    }

/* 211*/            Arrays.fill(aobj, i1, i, null);
/* 212*/            if(i1 == 1)
                    {
/* 215*/                Object obj1 = aobj[0];
/* 216*/                return new SingletonImmutableSet(obj1, l);
                    }
/* 217*/            if(j != chooseTableSize(i1))
                    {
/* 220*/                i = i1;
                    } else
                    {
/* 222*/                Object aobj2[] = i1 >= aobj.length ? aobj : ObjectArrays.arraysCopyOf(aobj, i1);
/* 225*/                return new RegularImmutableSet(aobj2, l, aobj1, k);
                    }
                } while(true);
            }

            static int chooseTableSize(int i)
            {
/* 249*/        if(i < 0x2ccccccc)
                {
                    int j;
/* 251*/            for(j = Integer.highestOneBit(i - 1) << 1; (double)j * 0.69999999999999996D < (double)i; j <<= 1);
/* 255*/            return j;
                } else
                {
/* 259*/            Preconditions.checkArgument(i < 0x40000000, "collection too large");
/* 260*/            return 0x40000000;
                }
            }

            public static ImmutableSet copyOf(Collection collection)
            {
                ImmutableSet immutableset;
/* 364*/        if((collection instanceof ImmutableSet) && !(collection instanceof ImmutableSortedSet))
                {
/* 367*/            if(!(immutableset = (ImmutableSet)collection).isPartialView())
/* 369*/                return immutableset;
                } else
/* 371*/        if(collection instanceof EnumSet)
/* 372*/            return copyOfEnumSet((EnumSet)collection);
                Object aobj[];
/* 374*/        return construct((aobj = collection.toArray()).length, aobj);
            }

            private static ImmutableSet copyOfEnumSet(EnumSet enumset)
            {
/* 380*/        return ImmutableEnumSet.asImmutable(EnumSet.copyOf(enumset));
            }

            ImmutableSet()
            {
            }

            boolean isHashCodeFast()
            {
/* 387*/        return false;
            }

            public boolean equals(Object obj)
            {
/* 391*/        if(obj == this)
/* 392*/            return true;
/* 393*/        if((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet)obj).isHashCodeFast() && hashCode() != obj.hashCode())
/* 397*/            return false;
/* 399*/        else
/* 399*/            return Sets.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 403*/        return Sets.hashCodeImpl(this);
            }

            public abstract UnmodifiableIterator iterator();

            Object writeReplace()
            {
/* 429*/        return new SerializedForm(toArray());
            }

            public volatile Iterator iterator()
            {
/*  72*/        return iterator();
            }
}
