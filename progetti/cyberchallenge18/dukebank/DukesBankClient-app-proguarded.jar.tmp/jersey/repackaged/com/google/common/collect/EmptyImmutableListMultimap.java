// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableListMultimap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableListMultimap, ImmutableMap

class EmptyImmutableListMultimap extends ImmutableListMultimap
{

            private EmptyImmutableListMultimap()
            {
/*  32*/        super(ImmutableMap.of(), 0);
            }

            static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();

}
