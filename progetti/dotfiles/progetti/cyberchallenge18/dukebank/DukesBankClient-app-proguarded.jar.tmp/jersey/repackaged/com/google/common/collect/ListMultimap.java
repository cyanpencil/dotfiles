// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ListMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.List;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap

public interface ListMultimap
    extends Multimap
{

    public abstract List get(Object obj);

    public abstract List removeAll(Object obj);

    public abstract List replaceValues(Object obj, Iterable iterable);

    public abstract Map asMap();
}
