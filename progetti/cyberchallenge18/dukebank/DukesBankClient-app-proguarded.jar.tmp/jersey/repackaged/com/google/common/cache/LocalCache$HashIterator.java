// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Ticker;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

abstract class advance
    implements Iterator
{

            final void advance()
            {
/*4268*/        nextExternal = null;
/*4270*/        if(nextInChain())
/*4271*/            return;
/*4274*/        if(nextInTable())
/*4275*/            return;
/*4278*/        while(nextSegmentIndex >= 0) 
                {
/*4279*/            currentSegment = segments[nextSegmentIndex--];
/*4280*/            if(currentSegment. != 0)
                    {
/*4281*/                currentTable = currentSegment.;
/*4282*/                nextTableIndex = currentTable.length() - 1;
/*4283*/                if(nextInTable())
/*4284*/                    return;
                    }
                }
            }

            boolean nextInChain()
            {
/*4294*/        if(nextEntry != null)
/*4295*/            for(nextEntry = nextEntry.getNext(); nextEntry != null; nextEntry = nextEntry.getNext())
/*4296*/                if(advanceTo(nextEntry))
/*4297*/                    return true;

/*4301*/        return false;
            }

            boolean nextInTable()
            {
/*4308*/        while(nextTableIndex >= 0) 
/*4309*/            if((nextEntry = (y)currentTable.get(nextTableIndex--)) != null && (advanceTo(nextEntry) || nextInChain()))
/*4311*/                return true;
/*4315*/        return false;
            }

            boolean advanceTo(y y)
            {
/*4324*/        long l = ticker.read();
/*4325*/        Object obj = y.getKey();
/*4326*/        if((y = ((y) (getLiveValue(y, l)))) == null)
/*4328*/            break MISSING_BLOCK_LABEL_60;
/*4328*/        nextExternal = new ntry(LocalCache.this, obj, y);
/*4335*/        currentSegment.eadCleanup();
/*4335*/        return true;
/*4335*/        currentSegment.eadCleanup();
/*4335*/        return false;
/*4335*/        y;
/*4335*/        currentSegment.eadCleanup();
/*4335*/        throw y;
            }

            public boolean hasNext()
            {
/*4341*/        return nextExternal != null;
            }

            ntry nextEntry()
            {
/*4345*/        if(nextExternal == null)
                {
/*4346*/            throw new NoSuchElementException();
                } else
                {
/*4348*/            lastReturned = nextExternal;
/*4349*/            advance();
/*4350*/            return lastReturned;
                }
            }

            public void remove()
            {
/*4355*/        Preconditions.checkState(lastReturned != null);
/*4356*/        LocalCache.this.remove(lastReturned.getKey());
/*4357*/        lastReturned = null;
            }

            int nextSegmentIndex;
            int nextTableIndex;
            lastReturned currentSegment;
            AtomicReferenceArray currentTable;
            y nextEntry;
            ntry nextExternal;
            ntry lastReturned;
            final LocalCache this$0;

            ntry()
            {
/*4258*/        this$0 = LocalCache.this;
/*4258*/        super();
/*4259*/        nextSegmentIndex = segments.length - 1;
/*4260*/        nextTableIndex = -1;
/*4261*/        advance();
            }
}
