// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstrainedTo.java

package javax.ws.rs;

import java.lang.annotation.Annotation;

// Referenced classes of package javax.ws.rs:
//            RuntimeType

public interface ConstrainedTo
    extends Annotation
{

    public abstract RuntimeType value();
}
