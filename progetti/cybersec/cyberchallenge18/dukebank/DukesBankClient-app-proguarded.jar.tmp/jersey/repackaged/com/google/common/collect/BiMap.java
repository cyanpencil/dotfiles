// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BiMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

public interface BiMap
    extends Map
{

    public abstract Set values();

    public abstract BiMap inverse();
}
