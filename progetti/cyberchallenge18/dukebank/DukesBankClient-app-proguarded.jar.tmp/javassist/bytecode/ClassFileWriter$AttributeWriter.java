// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFileWriter.java

package javassist.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package javassist.bytecode:
//            ClassFileWriter

public static interface 
{

    public abstract int size();

    public abstract void write(DataOutputStream dataoutputstream)
        throws IOException;
}
