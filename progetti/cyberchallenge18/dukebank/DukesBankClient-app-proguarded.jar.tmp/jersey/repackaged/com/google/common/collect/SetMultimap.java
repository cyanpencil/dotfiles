// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap

public interface SetMultimap
    extends Multimap
{

    public abstract Set get(Object obj);

    public abstract Set removeAll(Object obj);

    public abstract Set replaceValues(Object obj, Iterable iterable);

    public abstract Set entries();

    public abstract Map asMap();
}
