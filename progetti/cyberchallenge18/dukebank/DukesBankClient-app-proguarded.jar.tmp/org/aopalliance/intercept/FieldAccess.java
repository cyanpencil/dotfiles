// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldAccess.java

package org.aopalliance.intercept;

import java.lang.reflect.Field;

// Referenced classes of package org.aopalliance.intercept:
//            Joinpoint

public interface FieldAccess
    extends Joinpoint
{

    public abstract Field getField();

    public abstract Object getValueToSet();

    public abstract int getAccessType();

    public static final int READ = 0;
    public static final int WRITE = 1;
}
