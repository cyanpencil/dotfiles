// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProceedHandler.java

package javassist.compiler;

import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.compiler:
//            CompileError, JvstCodeGen, JvstTypeChecker

public interface ProceedHandler
{

    public abstract void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
        throws CompileError;

    public abstract void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
        throws CompileError;
}
