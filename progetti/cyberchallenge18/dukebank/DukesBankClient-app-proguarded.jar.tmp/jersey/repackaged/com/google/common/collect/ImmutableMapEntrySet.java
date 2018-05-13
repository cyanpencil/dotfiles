// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMapEntrySet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, ImmutableMap

abstract class ImmutableMapEntrySet extends ImmutableSet
{
    static class EntrySetSerializedForm
        implements Serializable
    {

                final ImmutableMap map;

                EntrySetSerializedForm(ImmutableMap immutablemap)
                {
/*  69*/            map = immutablemap;
                }
    }


            ImmutableMapEntrySet()
            {
            }

            abstract ImmutableMap map();

            public int size()
            {
/*  41*/        return map().size();
            }

            public boolean contains(Object obj)
            {
/*  46*/        if(obj instanceof java.util.Map.Entry)
                {
/*  47*/            obj = (java.util.Map.Entry)obj;
                    Object obj1;
/*  48*/            return (obj1 = map().get(((java.util.Map.Entry) (obj)).getKey())) != null && obj1.equals(((java.util.Map.Entry) (obj)).getValue());
                } else
                {
/*  51*/            return false;
                }
            }

            boolean isPartialView()
            {
/*  56*/        return map().isPartialView();
            }

            Object writeReplace()
            {
/*  62*/        return new EntrySetSerializedForm(map());
            }
}
