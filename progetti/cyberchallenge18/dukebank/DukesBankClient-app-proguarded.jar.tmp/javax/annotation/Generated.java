// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Generated.java

package javax.annotation;

import java.lang.annotation.Annotation;

public interface Generated
    extends Annotation
{

    public abstract String[] value();

    public abstract String date();

    public abstract String comments();
}
