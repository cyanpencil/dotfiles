// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorLinkedHashMap.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorLinkedHashMap

class erator extends erator
{

            public Object next()
            {
/* 347*/        return nextEntry().();
            }

            final KeyComparatorLinkedHashMap this$0;

            private erator()
            {
/* 343*/        this$0 = KeyComparatorLinkedHashMap.this;
/* 343*/        super(KeyComparatorLinkedHashMap.this);
            }

}
