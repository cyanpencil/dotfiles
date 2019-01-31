// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.List;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, ImmutableSet, Sets

static class  extends ImmutableList
{

            public final int size()
            {
/*1138*/        return val$axes.size();
            }

            public final List get(int i)
            {
/*1143*/        return ((ImmutableSet)val$axes.get(i)).asList();
            }

            final boolean isPartialView()
            {
/*1148*/        return true;
            }

            public final volatile Object get(int i)
            {
/*1134*/        return get(i);
            }

            final ImmutableList val$axes;

            (ImmutableList immutablelist)
            {
/*1134*/        val$axes = immutablelist;
/*1134*/        super();
            }
}
