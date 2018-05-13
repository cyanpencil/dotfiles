// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberResolver.java

package javassist.compiler;

import javassist.CtClass;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist.compiler:
//            MemberResolver

public static class notmatch
{

            public boolean isStatic()
            {
                int i;
/*  58*/        return ((i = info.getAccessFlags()) & 8) != 0;
            }

            public CtClass declaring;
            public MethodInfo info;
            public int notmatch;

            public (CtClass ctclass, MethodInfo methodinfo, int i)
            {
/*  49*/        declaring = ctclass;
/*  50*/        info = methodinfo;
/*  51*/        notmatch = i;
            }
}
