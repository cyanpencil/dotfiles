// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CannotCompileException.java

package javassist;

import javassist.compiler.CompileError;

// Referenced classes of package javassist:
//            NotFoundException

public class CannotCompileException extends Exception
{

            public Throwable getCause()
            {
/*  32*/        if(myCause == this)
/*  32*/            return null;
/*  32*/        else
/*  32*/            return myCause;
            }

            public synchronized Throwable initCause(Throwable throwable)
            {
/*  40*/        myCause = throwable;
/*  41*/        return this;
            }

            public String getReason()
            {
/*  50*/        if(message != null)
/*  51*/            return message;
/*  53*/        else
/*  53*/            return toString();
            }

            public CannotCompileException(String s)
            {
/*  62*/        super(s);
/*  63*/        message = s;
/*  64*/        initCause(null);
            }

            public CannotCompileException(Throwable throwable)
            {
/*  74*/        super((new StringBuilder("by ")).append(throwable.toString()).toString());
/*  75*/        message = null;
/*  76*/        initCause(throwable);
            }

            public CannotCompileException(String s, Throwable throwable)
            {
/*  87*/        this(s);
/*  88*/        initCause(throwable);
            }

            public CannotCompileException(NotFoundException notfoundexception)
            {
/*  96*/        this((new StringBuilder("cannot find ")).append(notfoundexception.getMessage()).toString(), ((Throwable) (notfoundexception)));
            }

            public CannotCompileException(CompileError compileerror)
            {
/* 103*/        this((new StringBuilder("[source error] ")).append(compileerror.getMessage()).toString(), ((Throwable) (compileerror)));
            }

            public CannotCompileException(ClassNotFoundException classnotfoundexception, String s)
            {
/* 111*/        this((new StringBuilder("cannot find ")).append(s).toString(), ((Throwable) (classnotfoundexception)));
            }

            public CannotCompileException(ClassFormatError classformaterror, String s)
            {
/* 118*/        this((new StringBuilder("invalid class format: ")).append(s).toString(), ((Throwable) (classformaterror)));
            }

            private Throwable myCause;
            private String message;
}
