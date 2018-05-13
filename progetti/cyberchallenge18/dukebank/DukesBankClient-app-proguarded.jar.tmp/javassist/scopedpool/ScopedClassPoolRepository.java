// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedClassPoolRepository.java

package javassist.scopedpool;

import java.util.Map;
import javassist.ClassPool;

// Referenced classes of package javassist.scopedpool:
//            ScopedClassPoolFactory, ScopedClassPool

public interface ScopedClassPoolRepository
{

    public abstract void setClassPoolFactory(ScopedClassPoolFactory scopedclasspoolfactory);

    public abstract ScopedClassPoolFactory getClassPoolFactory();

    public abstract boolean isPrune();

    public abstract void setPrune(boolean flag);

    public abstract ScopedClassPool createScopedClassPool(ClassLoader classloader, ClassPool classpool);

    public abstract ClassPool findClassPool(ClassLoader classloader);

    public abstract ClassPool registerClassLoader(ClassLoader classloader);

    public abstract Map getRegisteredCLs();

    public abstract void clearUnregisteredClassLoaders();

    public abstract void unregisterClassLoader(ClassLoader classloader);
}
