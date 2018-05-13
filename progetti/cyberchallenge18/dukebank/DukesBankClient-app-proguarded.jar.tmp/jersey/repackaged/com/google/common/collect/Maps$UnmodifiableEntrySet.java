// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Sets

static class init> extends init>
    implements Set
{

            public boolean equals(Object obj)
            {
/*1294*/        return Sets.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/*1298*/        return Sets.hashCodeImpl(this);
            }

            (Set set)
            {
/*1288*/        super(set);
            }
}
