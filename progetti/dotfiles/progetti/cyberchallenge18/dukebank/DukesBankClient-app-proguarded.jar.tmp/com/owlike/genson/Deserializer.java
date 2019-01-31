// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Deserializer.java

package com.owlike.genson;

import com.owlike.genson.stream.ObjectReader;

// Referenced classes of package com.owlike.genson:
//            Context

public interface Deserializer
{

    public abstract Object deserialize(ObjectReader objectreader, Context context)
        throws Exception;
}
