// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorLinkedHashMap.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorLinkedHashMap

class ator extends ator
{

            public Object next()
            {
/* 355*/        return nextEntry().nextEntry;
            }

            final KeyComparatorLinkedHashMap this$0;

            private ator()
            {
/* 351*/        this$0 = KeyComparatorLinkedHashMap.this;
/* 351*/        super(KeyComparatorLinkedHashMap.this);
            }

}
