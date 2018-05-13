// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static interface I
{

    public abstract I getValueReference();

    public abstract void setValueReference(I i);

    public abstract I getNext();

    public abstract int getHash();

    public abstract Object getKey();

    public abstract long getAccessTime();

    public abstract void setAccessTime(long l);

    public abstract I getNextInAccessQueue();

    public abstract void setNextInAccessQueue(I i);

    public abstract I getPreviousInAccessQueue();

    public abstract void setPreviousInAccessQueue(I i);

    public abstract long getWriteTime();

    public abstract void setWriteTime(long l);

    public abstract I getNextInWriteQueue();

    public abstract void setNextInWriteQueue(I i);

    public abstract I getPreviousInWriteQueue();

    public abstract void setPreviousInWriteQueue(I i);
}
