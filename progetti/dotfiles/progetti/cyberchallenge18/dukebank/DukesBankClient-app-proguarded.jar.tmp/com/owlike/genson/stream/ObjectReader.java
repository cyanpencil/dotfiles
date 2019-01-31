// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ObjectReader.java

package com.owlike.genson.stream;

import java.io.Closeable;

// Referenced classes of package com.owlike.genson.stream:
//            ValueType, JsonType

public interface ObjectReader
    extends Closeable
{

    public abstract ObjectReader beginObject();

    public abstract ObjectReader endObject();

    public abstract ObjectReader beginArray();

    public abstract ObjectReader endArray();

    public abstract ObjectReader nextObjectMetadata();

    public abstract ValueType next();

    public abstract boolean hasNext();

    public abstract ObjectReader skipValue();

    public abstract ValueType getValueType();

    public abstract String metadata(String s);

    public abstract String name();

    public abstract String valueAsString();

    public abstract int valueAsInt();

    public abstract long valueAsLong();

    public abstract double valueAsDouble();

    public abstract short valueAsShort();

    public abstract float valueAsFloat();

    public abstract boolean valueAsBoolean();

    public abstract byte[] valueAsByteArray();

    public abstract JsonType enclosingType();

    public abstract int column();

    public abstract int row();
}
