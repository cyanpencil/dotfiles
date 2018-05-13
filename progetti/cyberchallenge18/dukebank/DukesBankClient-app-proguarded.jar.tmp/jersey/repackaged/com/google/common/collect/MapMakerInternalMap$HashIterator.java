// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, MapMakerInternalMap

abstract class advance
    implements Iterator
{

            final void advance()
            {
/*3610*/        nextExternal = null;
/*3612*/        if(nextInChain())
/*3613*/            return;
/*3616*/        if(nextInTable())
/*3617*/            return;
/*3620*/        while(nextSegmentIndex >= 0) 
                {
/*3621*/            currentSegment = segments[nextSegmentIndex--];
/*3622*/            if(currentSegment. != 0)
                    {
/*3623*/                currentTable = currentSegment.;
/*3624*/                nextTableIndex = currentTable.length() - 1;
/*3625*/                if(nextInTable())
/*3626*/                    return;
                    }
                }
            }

            boolean nextInChain()
            {
/*3636*/        if(nextEntry != null)
/*3637*/            for(nextEntry = nextEntry.getNext(); nextEntry != null; nextEntry = nextEntry.getNext())
/*3638*/                if(advanceTo(nextEntry))
/*3639*/                    return true;

/*3643*/        return false;
            }

            boolean nextInTable()
            {
/*3650*/        while(nextTableIndex >= 0) 
/*3651*/            if((nextEntry = (y)currentTable.get(nextTableIndex--)) != null && (advanceTo(nextEntry) || nextInChain()))
/*3653*/                return true;
/*3657*/        return false;
            }

            boolean advanceTo(y y)
            {
/*3666*/        Object obj = y.getKey();
/*3667*/        if((y = ((y) (getLiveValue(y)))) == null)
/*3669*/            break MISSING_BLOCK_LABEL_46;
/*3669*/        nextExternal = new ntry(MapMakerInternalMap.this, obj, y);
/*3676*/        currentSegment.eadCleanup();
/*3676*/        return true;
/*3676*/        currentSegment.eadCleanup();
/*3676*/        return false;
/*3676*/        y;
/*3676*/        currentSegment.eadCleanup();
/*3676*/        throw y;
            }

            public boolean hasNext()
            {
/*3682*/        return nextExternal != null;
            }

            ntry nextEntry()
            {
/*3686*/        if(nextExternal == null)
                {
/*3687*/            throw new NoSuchElementException();
                } else
                {
/*3689*/            lastReturned = nextExternal;
/*3690*/            advance();
/*3691*/            return lastReturned;
                }
            }

            public void remove()
            {
/*3696*/        CollectPreconditions.checkRemove(lastReturned != null);
/*3697*/        MapMakerInternalMap.this.remove(lastReturned.getKey());
/*3698*/        lastReturned = null;
            }

            int nextSegmentIndex;
            int nextTableIndex;
            lastReturned currentSegment;
            AtomicReferenceArray currentTable;
            y nextEntry;
            ntry nextExternal;
            ntry lastReturned;
            final MapMakerInternalMap this$0;

            ntry()
            {
/*3600*/        this$0 = MapMakerInternalMap.this;
/*3600*/        super();
/*3601*/        nextSegmentIndex = segments.length - 1;
/*3602*/        nextTableIndex = -1;
/*3603*/        advance();
            }
}
