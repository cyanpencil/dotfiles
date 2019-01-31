// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DynamicFeature.java

package javax.ws.rs.container;

import javax.ws.rs.core.FeatureContext;

// Referenced classes of package javax.ws.rs.container:
//            ResourceInfo

public interface DynamicFeature
{

    public abstract void configure(ResourceInfo resourceinfo, FeatureContext featurecontext);
}
