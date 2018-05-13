// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.LinkedHashMap;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, ImmutableMultimap, Lists

static class  extends AbstractMapBasedMultimap
{

            Collection createCollection()
            {
/* 131*/        return Lists.newArrayList();
            }

            ()
            {
/* 128*/        super(new LinkedHashMap());
            }
}
