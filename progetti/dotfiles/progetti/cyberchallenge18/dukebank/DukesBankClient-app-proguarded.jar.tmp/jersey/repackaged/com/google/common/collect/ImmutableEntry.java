// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEntry.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapEntry

class ImmutableEntry extends AbstractMapEntry
    implements Serializable
{

            ImmutableEntry(Object obj, Object obj1)
            {
/*  35*/        key = obj;
/*  36*/        value = obj1;
            }

            public final Object getKey()
            {
/*  40*/        return key;
            }

            public final Object getValue()
            {
/*  44*/        return value;
            }

            public final Object setValue(Object obj)
            {
/*  48*/        throw new UnsupportedOperationException();
            }

            final Object key;
            final Object value;
}
