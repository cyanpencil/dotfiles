// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Enumeration;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators

static class val.iterator
    implements Enumeration
{

            public final boolean hasMoreElements()
            {
/*1125*/        return val$iterator.hasNext();
            }

            public final Object nextElement()
            {
/*1129*/        return val$iterator.next();
            }

            final Iterator val$iterator;

            (Iterator iterator1)
            {
/*1122*/        val$iterator = iterator1;
/*1122*/        super();
            }
}
