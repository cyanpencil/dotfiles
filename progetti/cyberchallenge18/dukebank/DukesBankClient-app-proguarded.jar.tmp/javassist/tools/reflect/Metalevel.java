// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Metalevel.java

package javassist.tools.reflect;


// Referenced classes of package javassist.tools.reflect:
//            ClassMetaobject, Metaobject

public interface Metalevel
{

    public abstract ClassMetaobject _getClass();

    public abstract Metaobject _getMetaobject();

    public abstract void _setMetaobject(Metaobject metaobject);
}
