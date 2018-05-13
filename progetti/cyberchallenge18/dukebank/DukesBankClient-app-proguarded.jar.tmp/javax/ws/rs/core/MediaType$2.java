// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaType.java

package javax.ws.rs.core;

import java.util.Comparator;

// Referenced classes of package javax.ws.rs.core:
//            MediaType

class this._cls0
    implements Comparator
{

            public int compare(String s, String s1)
            {
/* 258*/        return s.compareToIgnoreCase(s1);
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 254*/        return compare((String)obj, (String)obj1);
            }

            final MediaType this$0;

            ()
            {
/* 254*/        this$0 = MediaType.this;
/* 254*/        super();
            }
}
