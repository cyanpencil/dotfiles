// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathSegment.java

package javax.ws.rs.core;


// Referenced classes of package javax.ws.rs.core:
//            MultivaluedMap

public interface PathSegment
{

    public abstract String getPath();

    public abstract MultivaluedMap getMatrixParameters();
}
