// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap

interface FilteredMultimap
    extends Multimap
{

    public abstract Multimap unfiltered();

    public abstract Predicate entryPredicate();
}
