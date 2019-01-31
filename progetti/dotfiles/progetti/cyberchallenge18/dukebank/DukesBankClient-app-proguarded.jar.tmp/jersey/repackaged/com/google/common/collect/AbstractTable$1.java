// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedIterator, AbstractTable, Table

class tor extends TransformedIterator
{

            Object transform(tor tor)
            {
/* 175*/        return tor.lue();
            }

            volatile Object transform(Object obj)
            {
/* 172*/        return transform((transform)obj);
            }

            final AbstractTable this$0;

            tor(Iterator iterator)
            {
/* 172*/        this$0 = AbstractTable.this;
/* 172*/        super(iterator);
            }
}
