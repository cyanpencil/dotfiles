// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Javac.java

package javassist.compiler;

import javassist.*;
import javassist.compiler.ast.ASTree;

// Referenced classes of package javassist.compiler:
//            Javac

public static class init extends CtField
{

            protected void setInit(ASTree astree)
            {
/* 121*/        init = astree;
            }

            protected ASTree getInitAST()
            {
/* 124*/        return init;
            }

            private ASTree init;

            (CtClass ctclass, String s, CtClass ctclass1)
                throws CannotCompileException
            {
/* 117*/        super(ctclass, s, ctclass1);
/* 118*/        init = null;
            }
}
