// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

final class  extends 
{

            public final Object next()
            {
/*4365*/        return nextEntry().getKey();
            }

            final LocalCache this$0;

            Entry()
            {
/*4361*/        this$0 = LocalCache.this;
/*4361*/        super(LocalCache.this);
            }
}
