// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableAsList.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, ImmutableCollection

abstract class ImmutableAsList extends ImmutableList
{
    static class SerializedForm
        implements Serializable
    {

                final ImmutableCollection collection;

                SerializedForm(ImmutableCollection immutablecollection)
                {
/*  66*/            collection = immutablecollection;
                }
    }


            ImmutableAsList()
            {
            }

            abstract ImmutableCollection delegateCollection();

            public boolean contains(Object obj)
            {
/*  41*/        return delegateCollection().contains(obj);
            }

            public int size()
            {
/*  46*/        return delegateCollection().size();
            }

            public boolean isEmpty()
            {
/*  51*/        return delegateCollection().isEmpty();
            }

            boolean isPartialView()
            {
/*  56*/        return delegateCollection().isPartialView();
            }

            Object writeReplace()
            {
/*  82*/        return new SerializedForm(delegateCollection());
            }
}
