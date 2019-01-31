// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParamConverter.java

package javax.ws.rs.ext;

import java.lang.annotation.Annotation;

public interface ParamConverter
{
    public static interface Lazy
        extends Annotation
    {
    }


    public abstract Object fromString(String s);

    public abstract String toString(Object obj);
}
