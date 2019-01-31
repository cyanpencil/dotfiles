// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorLinkedHashMap.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorHashMap, KeyComparatorLinkedHashMap

static class  extends 
{

            private void remove()
            {
/* 253*/        before.after = after;
/* 254*/        after.before = before;
            }

            private void addBefore(before before1)
            {
/* 261*/        after = before1;
/* 262*/        before = before1.before;
/* 263*/        before.after = this;
/* 264*/        after.before = this;
            }

            void recordAccess(KeyComparatorHashMap keycomparatorhashmap)
            {
/* 275*/        if(KeyComparatorLinkedHashMap.access$000(keycomparatorhashmap = (KeyComparatorLinkedHashMap)keycomparatorhashmap))
                {
/* 277*/            keycomparatorhashmap.modCount++;
/* 278*/            remove();
/* 279*/            addBefore(KeyComparatorLinkedHashMap.access$100(keycomparatorhashmap));
                }
            }

            void recordRemoval(KeyComparatorHashMap keycomparatorhashmap)
            {
/* 285*/        remove();
            }

            public boolean equals(Object obj)
            {
/* 290*/        return super.(obj);
            }

            public int hashCode()
            {
/* 295*/        return super.de();
            }

            de before;
            de after;


            (int i, Object obj, Object obj1,  )
            {
/* 246*/        super(i, obj, obj1, );
            }
}
