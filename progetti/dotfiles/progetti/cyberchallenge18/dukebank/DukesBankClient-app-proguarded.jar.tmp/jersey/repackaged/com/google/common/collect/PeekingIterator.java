// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PeekingIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

public interface PeekingIterator
    extends Iterator
{

    public abstract Object peek();

    public abstract Object next();
}
