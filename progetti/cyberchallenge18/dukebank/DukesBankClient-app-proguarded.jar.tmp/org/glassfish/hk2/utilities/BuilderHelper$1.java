// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BuilderHelper.java

package org.glassfish.hk2.utilities;

import java.util.Iterator;
import java.util.Set;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

// Referenced classes of package org.glassfish.hk2.utilities:
//            BuilderHelper

static class val.fName
    implements IndexedFilter
{

            public final boolean matches(Descriptor descriptor)
            {
/* 204*/        if(val$qualifiers.isEmpty())
/* 204*/            return true;
/* 206*/        else
/* 206*/            return descriptor.getQualifiers().containsAll(val$qualifiers);
            }

            public final String getAdvertisedContract()
            {
/* 211*/        return val$fContract;
            }

            public final String getName()
            {
/* 216*/        return val$fName;
            }

            public final String toString()
            {
/* 221*/        String s = val$fContract != null ? val$fContract : "";
/* 222*/        String s1 = val$fName != null ? (new StringBuilder(";name=")).append(val$fName).toString() : "";
/* 224*/        StringBuffer stringbuffer = new StringBuffer();
                String s2;
/* 225*/        for(Iterator iterator = val$qualifiers.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder(";qualifier=")).append(s2).toString()))
/* 225*/            s2 = (String)iterator.next();

/* 229*/        return (new StringBuilder("TokenizedFilter(")).append(s).append(s1).append(stringbuffer.toString()).append(")").toString();
            }

            final Set val$qualifiers;
            final String val$fContract;
            final String val$fName;

            (Set set, String s, String s1)
            {
/* 200*/        val$qualifiers = set;
/* 200*/        val$fContract = s;
/* 200*/        val$fName = s1;
/* 200*/        super();
            }
}
