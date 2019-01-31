// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CartesianList.java

package jersey.repackaged.com.google.common.collect;

import java.util.List;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, CartesianList

class nit> extends ImmutableList
{

            public int size()
            {
/*  80*/        return CartesianList.access$000(CartesianList.this).size();
            }

            public Object get(int i)
            {
/*  85*/        Preconditions.checkElementIndex(i, size());
/*  86*/        int j = CartesianList.access$100(CartesianList.this, val$index, i);
/*  87*/        return ((List)CartesianList.access$000(CartesianList.this).get(i)).get(j);
            }

            boolean isPartialView()
            {
/*  92*/        return true;
            }

            final int val$index;
            final CartesianList this$0;

            A()
            {
/*  76*/        this$0 = final_cartesianlist;
/*  76*/        val$index = I.this;
/*  76*/        super();
            }
}
