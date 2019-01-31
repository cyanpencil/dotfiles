// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerialVersionUID.java

package javassist;

import java.util.Comparator;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist:
//            CtMethod, SerialVersionUID

static class 
    implements Comparator
{

            public final int compare(Object obj, Object obj1)
            {
/* 160*/        obj = (CtMethod)obj;
/* 161*/        obj1 = (CtMethod)obj1;
                int i;
/* 162*/        if((i = ((CtMethod) (obj)).getName().compareTo(((CtMethod) (obj1)).getName())) == 0)
/* 164*/            i = ((CtMethod) (obj)).getMethodInfo2().getDescriptor().compareTo(((CtMethod) (obj1)).getMethodInfo2().getDescriptor());
/* 167*/        return i;
            }

            ()
            {
            }
}
