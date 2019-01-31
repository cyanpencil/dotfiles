// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashFunction.java

package jersey.repackaged.com.google.common.hash;


// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            Hasher, HashCode

public interface HashFunction
{

    public abstract Hasher newHasher();

    public abstract HashCode hashLong(long l);
}
