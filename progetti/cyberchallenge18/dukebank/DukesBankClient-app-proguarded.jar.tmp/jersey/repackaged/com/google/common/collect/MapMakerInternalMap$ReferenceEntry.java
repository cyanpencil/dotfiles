// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static interface 
{

    public abstract  getValueReference();

    public abstract void setValueReference( );

    public abstract  getNext();

    public abstract int getHash();

    public abstract Object getKey();

    public abstract long getExpirationTime();

    public abstract void setExpirationTime(long l);

    public abstract  getNextExpirable();

    public abstract void setNextExpirable( );

    public abstract  getPreviousExpirable();

    public abstract void setPreviousExpirable( );

    public abstract  getNextEvictable();

    public abstract void setNextEvictable( );

    public abstract  getPreviousEvictable();

    public abstract void setPreviousEvictable( );
}
