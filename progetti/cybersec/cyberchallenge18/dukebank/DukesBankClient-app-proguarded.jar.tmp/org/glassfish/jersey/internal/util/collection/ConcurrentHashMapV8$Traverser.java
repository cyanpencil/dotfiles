// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static class next
{

            final next advance()
            {
                Object obj;
/*2753*/        if((obj = next) != null)
/*2754*/            obj = ((next) (obj)).next;
/*2760*/        do
                {
/*2760*/            if(obj != null)
/*2761*/                return next = ((next) (obj));
                    int i;
                    int j;
/*2762*/            if(baseIndex >= baseLimit || (obj = tab) == null || (j = obj.length) <= (i = index) || i < 0)
/*2764*/                return next = null;
/*2765*/            if((obj = ConcurrentHashMapV8.tabAt(((next []) (obj)), index)) != null && ((index) (obj)).index < 0)
                    {
/*2766*/                if(obj instanceof Node)
                        {
/*2767*/                    tab = ((Node)obj).nextTable;
/*2768*/                    obj = null;
/*2769*/                    continue;
                        }
/*2770*/                if(obj instanceof Node.nextTable)
/*2771*/                    obj = ((Node.nextTable)obj).rst;
/*2773*/                else
/*2773*/                    obj = null;
                    }
/*2775*/            if((index += baseSize) >= j)
/*2776*/                index = ++baseIndex;
                } while(true);
            }

            baseIndex tab[];
            baseIndex next;
            int index;
            int baseIndex;
            int baseLimit;
            final int baseSize;

            Node(Node anode[], int i, int j, int k)
            {
/*2741*/        tab = anode;
/*2742*/        baseSize = i;
/*2743*/        baseIndex = index = j;
/*2744*/        baseLimit = k;
/*2745*/        next = null;
            }
}
