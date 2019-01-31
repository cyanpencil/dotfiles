// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Converter.java

package com.owlike.genson;

import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson:
//            Deserializer, Serializer, Context

public interface Converter
    extends Deserializer, Serializer
{

    public abstract void serialize(Object obj, ObjectWriter objectwriter, Context context)
        throws Exception;

    public abstract Object deserialize(ObjectReader objectreader, Context context)
        throws Exception;
}
