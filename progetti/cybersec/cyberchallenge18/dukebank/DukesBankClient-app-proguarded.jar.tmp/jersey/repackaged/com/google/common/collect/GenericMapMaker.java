// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericMapMaker.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.MoreObjects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMaker

/**
 * @deprecated Class GenericMapMaker is deprecated
 */

abstract class GenericMapMaker
{
    static final class NullListener extends Enum
        implements MapMaker.RemovalListener
    {

                public final void onRemoval(MapMaker.RemovalNotification removalnotification)
                {
                }

                public static final NullListener INSTANCE;
                private static final NullListener $VALUES[];

                static 
                {
/*  53*/            INSTANCE = new NullListener("INSTANCE", 0);
/*  51*/            $VALUES = (new NullListener[] {
/*  51*/                INSTANCE
                    });
                }

                private NullListener(String s, int i)
                {
/*  52*/            super(s, i);
                }
    }


            GenericMapMaker()
            {
            }

            MapMaker.RemovalListener getRemovalListener()
            {
/* 131*/        return (MapMaker.RemovalListener)MoreObjects.firstNonNull(removalListener, NullListener.INSTANCE);
            }

            MapMaker.RemovalListener removalListener;
}
