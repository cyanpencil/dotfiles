// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringIgnoreCaseKeyComparator.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparator

public class StringIgnoreCaseKeyComparator
    implements KeyComparator
{

            public StringIgnoreCaseKeyComparator()
            {
            }

            public int hash(String s)
            {
/*  56*/        return s.toLowerCase().hashCode();
            }

            public boolean equals(String s, String s1)
            {
/*  61*/        return s.equalsIgnoreCase(s1);
            }

            public volatile int hash(Object obj)
            {
/*  48*/        return hash((String)obj);
            }

            public volatile boolean equals(Object obj, Object obj1)
            {
/*  48*/        return equals((String)obj, (String)obj1);
            }

            private static final long serialVersionUID = 0x7e623598787d9253L;
            public static final StringIgnoreCaseKeyComparator SINGLETON = new StringIgnoreCaseKeyComparator();

}
