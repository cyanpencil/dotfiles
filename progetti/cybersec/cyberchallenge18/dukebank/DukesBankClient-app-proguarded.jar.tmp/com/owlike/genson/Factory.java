// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Factory.java

package com.owlike.genson;

import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson:
//            Genson

public interface Factory
{

    public abstract Object create(Type type, Genson genson);
}
