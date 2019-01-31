// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseBeanDescriptorProvider.java

package com.owlike.genson.reflect;

import java.util.Comparator;
import java.util.Map;

// Referenced classes of package com.owlike.genson.reflect:
//            BaseBeanDescriptorProvider, BeanCreator

static class 
    implements Comparator
{

            public final int compare(BeanCreator beancreator, BeanCreator beancreator1)
            {
/*  37*/        return beancreator.parameters.size() - beancreator1.parameters.size();
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  35*/        return compare((BeanCreator)obj, (BeanCreator)obj1);
            }

            ()
            {
            }
}
