// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedClassPoolRepositoryImpl.java

package javassist.scopedpool;

import java.util.*;
import javassist.ClassPool;
import javassist.LoaderClassPath;

// Referenced classes of package javassist.scopedpool:
//            ScopedClassPool, ScopedClassPoolFactory, ScopedClassPoolFactoryImpl, ScopedClassPoolRepository

public class ScopedClassPoolRepositoryImpl
    implements ScopedClassPoolRepository
{

            public static ScopedClassPoolRepository getInstance()
            {
/*  61*/        return instance;
            }

            private ScopedClassPoolRepositoryImpl()
            {
/*  40*/        prune = true;
/*  46*/        registeredCLs = Collections.synchronizedMap(new WeakHashMap());
/*  53*/        factory = new ScopedClassPoolFactoryImpl();
/*  68*/        classpool = ClassPool.getDefault();
/*  70*/        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
/*  71*/        classpool.insertClassPath(new LoaderClassPath(classloader));
            }

            public boolean isPrune()
            {
/*  80*/        return prune;
            }

            public void setPrune(boolean flag)
            {
/*  89*/        prune = flag;
            }

            public ScopedClassPool createScopedClassPool(ClassLoader classloader, ClassPool classpool1)
            {
/* 100*/        return factory.create(classloader, classpool1, this);
            }

            public ClassPool findClassPool(ClassLoader classloader)
            {
/* 104*/        if(classloader == null)
/* 105*/            return registerClassLoader(ClassLoader.getSystemClassLoader());
/* 107*/        else
/* 107*/            return registerClassLoader(classloader);
            }

            public ClassPool registerClassLoader(ClassLoader classloader)
            {
/* 117*/        Map map = registeredCLs;
/* 117*/        JVM INSTR monitorenter ;
/* 123*/        if(registeredCLs.containsKey(classloader))
/* 124*/            return (ClassPool)registeredCLs.get(classloader);
                ScopedClassPool scopedclasspool;
/* 126*/        scopedclasspool = createScopedClassPool(classloader, classpool);
/* 127*/        registeredCLs.put(classloader, scopedclasspool);
/* 128*/        scopedclasspool;
/* 128*/        map;
/* 128*/        JVM INSTR monitorexit ;
/* 128*/        return;
/* 129*/        classloader;
/* 129*/        throw classloader;
            }

            public Map getRegisteredCLs()
            {
/* 136*/        clearUnregisteredClassLoaders();
/* 137*/        return registeredCLs;
            }

            public void clearUnregisteredClassLoaders()
            {
/* 145*/        ArrayList arraylist = null;
/* 146*/        synchronized(registeredCLs)
                {
/* 147*/            Iterator iterator = registeredCLs.values().iterator();
/* 148*/            do
                    {
/* 148*/                if(!iterator.hasNext())
/* 149*/                    break;
                        Object obj;
/* 149*/                if(((ScopedClassPool) (obj = (ScopedClassPool)iterator.next())).isUnloadedClassLoader())
                        {
/* 151*/                    iterator.remove();
/* 152*/                    if((obj = ((ScopedClassPool) (obj)).getClassLoader()) != null)
                            {
/* 154*/                        if(arraylist == null)
/* 155*/                            arraylist = new ArrayList();
/* 157*/                        arraylist.add(obj);
                            }
                        }
                    } while(true);
/* 161*/            if(arraylist != null)
                    {
/* 162*/                for(int i = 0; i < arraylist.size(); i++)
/* 163*/                    unregisterClassLoader((ClassLoader)arraylist.get(i));

                    }
                }
            }

            public void unregisterClassLoader(ClassLoader classloader)
            {
/* 170*/        synchronized(registeredCLs)
                {
/* 171*/            if((classloader = (ScopedClassPool)registeredCLs.remove(classloader)) != null)
/* 173*/                classloader.close();
                }
            }

            public void insertDelegate(ScopedClassPoolRepository scopedclasspoolrepository)
            {
            }

            public void setClassPoolFactory(ScopedClassPoolFactory scopedclasspoolfactory)
            {
/* 182*/        factory = scopedclasspoolfactory;
            }

            public ScopedClassPoolFactory getClassPoolFactory()
            {
/* 186*/        return factory;
            }

            private static final ScopedClassPoolRepositoryImpl instance = new ScopedClassPoolRepositoryImpl();
            private boolean prune;
            boolean pruneWhenCached;
            protected Map registeredCLs;
            protected ClassPool classpool;
            protected ScopedClassPoolFactory factory;

}
