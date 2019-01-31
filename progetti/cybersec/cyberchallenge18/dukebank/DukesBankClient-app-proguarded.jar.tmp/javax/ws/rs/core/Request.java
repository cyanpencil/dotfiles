// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Request.java

package javax.ws.rs.core;

import java.util.Date;
import java.util.List;

// Referenced classes of package javax.ws.rs.core:
//            Response, Variant, EntityTag

public interface Request
{

    public abstract String getMethod();

    public abstract Variant selectVariant(List list);

    public abstract Response.ResponseBuilder evaluatePreconditions(EntityTag entitytag);

    public abstract Response.ResponseBuilder evaluatePreconditions(Date date);

    public abstract Response.ResponseBuilder evaluatePreconditions(Date date, EntityTag entitytag);

    public abstract Response.ResponseBuilder evaluatePreconditions();
}
