// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanDescriptor.java

package com.owlike.genson.reflect;

import java.util.Comparator;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanDescriptor, BeanProperty

static class 
    implements Comparator
{

            public final int compare(BeanProperty beanproperty, BeanProperty beanproperty1)
            {
/*  54*/        return beanproperty.name.compareToIgnoreCase(beanproperty1.name);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  52*/        return compare((BeanProperty)obj, (BeanProperty)obj1);
            }

            ()
            {
            }
}
