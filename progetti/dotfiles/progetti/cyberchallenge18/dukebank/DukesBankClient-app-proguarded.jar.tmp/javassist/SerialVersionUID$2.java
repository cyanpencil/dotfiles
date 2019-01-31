// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerialVersionUID.java

package javassist;

import java.util.Comparator;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist:
//            CtConstructor, SerialVersionUID

static class 
    implements Comparator
{

            public final int compare(Object obj, Object obj1)
            {
/* 139*/        obj = (CtConstructor)obj;
/* 140*/        obj1 = (CtConstructor)obj1;
/* 141*/        return ((CtConstructor) (obj)).getMethodInfo2().getDescriptor().compareTo(((CtConstructor) (obj1)).getMethodInfo2().getDescriptor());
            }

            ()
            {
            }
}
