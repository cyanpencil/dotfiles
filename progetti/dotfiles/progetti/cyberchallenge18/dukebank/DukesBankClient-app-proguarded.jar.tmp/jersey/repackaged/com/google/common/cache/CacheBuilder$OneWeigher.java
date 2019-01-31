// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilder.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilder, Weigher

static final class  extends Enum
    implements Weigher
{

            public final int weigh(Object obj, Object obj1)
            {
/* 202*/        return 1;
            }

            public static final INSTANCE INSTANCE;
            private static final INSTANCE $VALUES[];

            static 
            {
/* 198*/        INSTANCE = new <init>("INSTANCE", 0);
/* 197*/        $VALUES = (new .VALUES[] {
/* 197*/            INSTANCE
                });
            }

            private (String s, int i)
            {
/* 197*/        super(s, i);
            }
}
