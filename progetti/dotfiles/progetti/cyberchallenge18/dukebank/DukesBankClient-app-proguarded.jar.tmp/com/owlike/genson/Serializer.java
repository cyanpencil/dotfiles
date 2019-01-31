// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Serializer.java

package com.owlike.genson;

import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson:
//            Context

public interface Serializer
{

    public abstract void serialize(Object obj, ObjectWriter objectwriter, Context context)
        throws Exception;
}
