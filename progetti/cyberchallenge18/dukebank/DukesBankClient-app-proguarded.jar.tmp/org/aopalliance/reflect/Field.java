// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Field.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            Member, CodeLocator

public interface Field
    extends Member
{

    public abstract CodeLocator getReadLocator();

    public abstract CodeLocator getReadLocator(int i);

    public abstract CodeLocator getWriteLocator();

    public abstract CodeLocator getWriteLocator(int i);
}
