// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, ImmutableList

public static final class rayBasedBuilder extends rayBasedBuilder
{

            public final rayBasedBuilder add(Object obj)
            {
/* 652*/        super.add(obj);
/* 653*/        return this;
            }

            public final rayBasedBuilder.add addAll(Iterator iterator)
            {
/* 691*/        super.addAll(iterator);
/* 692*/        return this;
            }

            public final ImmutableList build()
            {
/* 700*/        return ImmutableList.asImmutableList(contents, size);
            }

            public final volatile rayBasedBuilder add(Object obj)
            {
/* 630*/        return add(obj);
            }

            public final volatile ilder addAll(Iterator iterator)
            {
/* 630*/        return addAll(iterator);
            }

            public final volatile ilder add(Object obj)
            {
/* 630*/        return add(obj);
            }

            public ilder()
            {
/* 636*/        this(4);
            }

            <init>(int i)
            {
/* 641*/        super(i);
            }
}
