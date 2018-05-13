// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

final class  extends 
{

            public final Object next()
            {
/*3706*/        return nextEntry().getKey();
            }

            final MapMakerInternalMap this$0;

            Entry()
            {
/*3702*/        this$0 = MapMakerInternalMap.this;
/*3702*/        super(MapMakerInternalMap.this);
            }
}
