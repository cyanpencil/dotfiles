// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LinkedTransferQueue.java

package org.glassfish.jersey.internal.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            LinkedTransferQueue

final class 
    implements Iterator
{

            private void advance( )
            {
                 1;
/* 855*/        if((1 = lastRet) != null && !1.isMatched())
/* 856*/            lastPred = 1;
/* 857*/        else
/* 857*/        if((1 = lastPred) == null || 1.isMatched())
                {
/* 858*/            lastPred = null;
                } else
                {
                     2;
                     4;
/* 861*/            for(; (2 = 1.next) != null && 2 != 1 && 2.isMatched() && (4 = 2.next) != null && 4 != 2; 1.casNext(2, 4));
                }
/* 867*/        lastRet = ;
/* 869*/         3 = ;
/* 870*/        do
                {
                     5;
/* 870*/            if((5 = 3 != null ? 3.next : head) == null)
/* 873*/                break;
/* 873*/            if(5 == 3)
                    {
/* 874*/                3 = null;
/* 875*/                continue;
                    }
/* 877*/             = (() (5.item));
/* 878*/            if(5.isData)
                    {
/* 879*/                if( != null &&  != 5)
                        {
/* 880*/                    nextItem = LinkedTransferQueue.cast();
/* 881*/                    nextNode = 5;
/* 882*/                    return;
                        }
                    } else
/* 884*/            if( == null)
/* 887*/                break;
/* 887*/            if(3 == null)
                    {
/* 888*/                3 = 5;
/* 888*/                continue;
                    }
/* 889*/            if(( = 5.next) == null)
/* 891*/                break;
/* 891*/            if(5 == )
/* 892*/                3 = null;
/* 894*/            else
/* 894*/                3.casNext(5, );
                } while(true);
/* 896*/        nextNode = null;
/* 897*/        nextItem = null;
            }

            public final boolean hasNext()
            {
/* 905*/        return nextNode != null;
            }

            public final Object next()
            {
                 ;
/* 909*/        if(( = nextNode) == null)
                {
/* 910*/            throw new NoSuchElementException();
                } else
                {
/* 911*/            Object obj = nextItem;
/* 912*/            advance();
/* 913*/            return obj;
                }
            }

            public final void remove()
            {
                 ;
/* 917*/        if(( = lastRet) == null)
/* 919*/            throw new IllegalStateException();
/* 920*/        lastRet = null;
/* 921*/        if(.tryMatchData())
/* 922*/            unsplice(lastPred, );
            }

            private  nextNode;
            private Object nextItem;
            private  lastRet;
            private  lastPred;
            final LinkedTransferQueue this$0;

            ()
            {
/* 900*/        this$0 = LinkedTransferQueue.this;
/* 900*/        super();
/* 901*/        advance(null);
            }
}
