// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilder.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilder, RemovalListener, RemovalNotification

static final class I extends Enum
    implements RemovalListener
{

            public final void onRemoval(RemovalNotification removalnotification)
            {
            }

            public static final INSTANCE INSTANCE;
            private static final INSTANCE $VALUES[];

            static 
            {
/* 191*/        INSTANCE = new <init>("INSTANCE", 0);
/* 190*/        $VALUES = (new .VALUES[] {
/* 190*/            INSTANCE
                });
            }

            private I(String s, int i)
            {
/* 190*/        super(s, i);
            }
}
