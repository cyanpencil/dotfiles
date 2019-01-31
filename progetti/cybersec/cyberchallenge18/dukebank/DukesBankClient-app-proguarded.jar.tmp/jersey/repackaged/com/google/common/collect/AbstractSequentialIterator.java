// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractSequentialIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator

public abstract class AbstractSequentialIterator extends UnmodifiableIterator
{

            protected AbstractSequentialIterator(Object obj)
            {
/*  53*/        nextOrNull = obj;
            }

            protected abstract Object computeNext(Object obj);

            public final boolean hasNext()
            {
/*  66*/        return nextOrNull != null;
            }

            public final Object next()
            {
/*  71*/        if(!hasNext())
/*  72*/            throw new NoSuchElementException();
/*  75*/        Object obj = nextOrNull;
/*  77*/        nextOrNull = computeNext(nextOrNull);
/*  77*/        return obj;
                Exception exception;
/*  77*/        exception;
/*  77*/        nextOrNull = computeNext(nextOrNull);
/*  77*/        throw exception;
            }

            private Object nextOrNull;
}
