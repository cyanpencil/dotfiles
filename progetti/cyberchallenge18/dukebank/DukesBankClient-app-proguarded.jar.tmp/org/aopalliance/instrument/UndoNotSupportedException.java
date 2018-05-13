// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UndoNotSupportedException.java

package org.aopalliance.instrument;


// Referenced classes of package org.aopalliance.instrument:
//            Instrumentation

public class UndoNotSupportedException extends Exception
{

            public UndoNotSupportedException(Instrumentation instrumentation)
            {
/*  19*/        super((new StringBuilder("Undo not supported for instrumentation: ")).append(instrumentation).toString());
            }
}
