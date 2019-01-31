// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Translator.java

package javassist;


// Referenced classes of package javassist:
//            CannotCompileException, NotFoundException, ClassPool

public interface Translator
{

    public abstract void start(ClassPool classpool)
        throws NotFoundException, CannotCompileException;

    public abstract void onLoad(ClassPool classpool, String s)
        throws NotFoundException, CannotCompileException;
}
