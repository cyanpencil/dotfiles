// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMultimap, Maps

class r extends r
{

            Object output(Object obj, Object obj1)
            {
/* 636*/        return obj1;
            }

            final ImmutableMultimap this$0;

            r()
            {
/* 633*/        this$0 = ImmutableMultimap.this;
/* 633*/        super(ImmutableMultimap.this);
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/collect/ImmutableMultimap$1

/* anonymous class */
    class ImmutableMultimap._cls1 extends ImmutableMultimap.Itr
    {

                java.util.Map.Entry output(Object obj, Object obj1)
                {
/* 560*/            return Maps.immutableEntry(obj, obj1);
                }

                volatile Object output(Object obj, Object obj1)
                {
/* 557*/            return output(obj, obj1);
                }

                final ImmutableMultimap this$0;

                    
                    {
/* 557*/                this$0 = ImmutableMultimap.this;
/* 557*/                super(ImmutableMultimap.this);
                    }
    }

}
