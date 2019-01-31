// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableSetMultimap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSetMultimap, ImmutableMap

class EmptyImmutableSetMultimap extends ImmutableSetMultimap
{

            private EmptyImmutableSetMultimap()
            {
/*  32*/        super(ImmutableMap.of(), 0, null);
            }

            static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();

}
