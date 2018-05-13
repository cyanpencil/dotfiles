// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            VariantSelector

static interface 
{

    public abstract Object getDimension( );

    public abstract int getQualitySource( , Object obj);

    public abstract boolean isCompatible(Object obj, Object obj1);

    public abstract String getVaryHeaderValue();
}
