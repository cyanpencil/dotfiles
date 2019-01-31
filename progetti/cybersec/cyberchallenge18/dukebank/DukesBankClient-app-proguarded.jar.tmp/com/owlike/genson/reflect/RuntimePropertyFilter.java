// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimePropertyFilter.java

package com.owlike.genson.reflect;

import com.owlike.genson.Context;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanProperty

public interface RuntimePropertyFilter
{

            public abstract boolean shouldInclude(BeanProperty beanproperty, Context context);

            public static final RuntimePropertyFilter noFilter = new RuntimePropertyFilter() {

                public final boolean shouldInclude(BeanProperty beanproperty, Context context)
                {
/*   9*/            return true;
                }

    };

}
