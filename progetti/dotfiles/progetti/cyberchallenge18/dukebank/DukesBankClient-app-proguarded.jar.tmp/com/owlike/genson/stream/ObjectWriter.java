// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ObjectWriter.java

package com.owlike.genson.stream;


// Referenced classes of package com.owlike.genson.stream:
//            JsonType

public interface ObjectWriter
{

    public abstract ObjectWriter beginArray();

    public abstract ObjectWriter endArray();

    public abstract ObjectWriter beginObject();

    public abstract ObjectWriter endObject();

    public abstract ObjectWriter writeName(String s);

    public abstract ObjectWriter writeEscapedName(char ac[]);

    public abstract ObjectWriter writeValue(int i);

    public abstract ObjectWriter writeValue(double d);

    public abstract ObjectWriter writeValue(long l);

    public abstract ObjectWriter writeValue(short word0);

    public abstract ObjectWriter writeValue(float f);

    public abstract ObjectWriter writeValue(boolean flag);

    public abstract ObjectWriter writeBoolean(Boolean boolean1);

    public abstract ObjectWriter writeValue(Number number);

    public abstract ObjectWriter writeNumber(Number number);

    public abstract ObjectWriter writeValue(String s);

    public abstract ObjectWriter writeString(String s);

    public abstract ObjectWriter writeValue(byte abyte0[]);

    public abstract ObjectWriter writeBytes(byte abyte0[]);

    public abstract ObjectWriter writeUnsafeValue(String s);

    public abstract ObjectWriter writeNull();

    public abstract ObjectWriter beginNextObjectMetadata();

    public abstract ObjectWriter writeMetadata(String s, String s1);

    public abstract ObjectWriter writeBoolean(String s, Boolean boolean1);

    public abstract ObjectWriter writeNumber(String s, Number number);

    public abstract ObjectWriter writeString(String s, String s1);

    public abstract ObjectWriter writeBytes(String s, byte abyte0[]);

    public abstract void flush();

    public abstract void close();

    public abstract JsonType enclosingType();
}
