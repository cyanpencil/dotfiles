// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Instrumentor.java

package org.aopalliance.instrument;

import org.aopalliance.reflect.*;

// Referenced classes of package org.aopalliance.instrument:
//            InstrumentationError, UndoNotSupportedException, Instrumentation

public interface Instrumentor
{

    public abstract ClassLocator createClass(String s)
        throws InstrumentationError;

    public abstract Instrumentation addInterface(ClassLocator classlocator, String s)
        throws InstrumentationError;

    public abstract Instrumentation setSuperClass(ClassLocator classlocator, String s)
        throws InstrumentationError;

    public abstract Instrumentation addClass(ClassLocator classlocator, String s)
        throws InstrumentationError;

    public abstract Instrumentation addMethod(ClassLocator classlocator, String s, String as[], String as1[], Code code)
        throws InstrumentationError;

    public abstract Instrumentation addField(ClassLocator classlocator, String s, String s1, Code code)
        throws InstrumentationError;

    public abstract Instrumentation addBeforeCode(CodeLocator codelocator, Code code, Instrumentation instrumentation, Instrumentation instrumentation1)
        throws InstrumentationError;

    public abstract Instrumentation addAfterCode(CodeLocator codelocator, Code code, Instrumentation instrumentation, Instrumentation instrumentation1)
        throws InstrumentationError;

    public abstract Instrumentation addAroundCode(CodeLocator codelocator, Code code, String s, Instrumentation instrumentation, Instrumentation instrumentation1)
        throws InstrumentationError;

    public abstract void undo(Instrumentation instrumentation)
        throws UndoNotSupportedException;
}
