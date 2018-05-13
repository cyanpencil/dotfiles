// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerialVersionUID.java

package javassist;

import java.util.Comparator;

// Referenced classes of package javassist:
//            CtField, SerialVersionUID

static class 
    implements Comparator
{

            public final int compare(Object obj, Object obj1)
            {
/* 111*/        obj = (CtField)obj;
/* 112*/        obj1 = (CtField)obj1;
/* 113*/        return ((CtField) (obj)).getName().compareTo(((CtField) (obj1)).getName());
            }

            ()
            {
            }
}
