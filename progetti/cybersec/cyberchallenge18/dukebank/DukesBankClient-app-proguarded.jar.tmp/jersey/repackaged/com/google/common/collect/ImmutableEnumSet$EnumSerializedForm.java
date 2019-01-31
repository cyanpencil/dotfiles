// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEnumSet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.EnumSet;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableEnumSet

static class delegate
    implements Serializable
{

            final EnumSet _flddelegate;

            (EnumSet enumset)
            {
/* 111*/        _flddelegate = enumset;
            }
}
