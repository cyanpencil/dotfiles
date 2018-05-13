// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedClassPool.java

package javassist.scopedpool;

import java.lang.ref.WeakReference;
import java.security.ProtectionDomain;
import java.util.*;
import javassist.*;

// Referenced classes of package javassist.scopedpool:
//            ScopedClassPoolRepository, SoftValueHashMap

public class ScopedClassPool extends ClassPool
{

            /**
             * @deprecated Method ScopedClassPool is deprecated
             */

            protected ScopedClassPool(ClassLoader classloader, ClassPool classpool, ScopedClassPoolRepository scopedclasspoolrepository)
            {
/*  66*/        this(classloader, classpool, scopedclasspoolrepository, false);
            }

            protected ScopedClassPool(ClassLoader classloader, ClassPool classpool, ScopedClassPoolRepository scopedclasspoolrepository, boolean flag)
            {
/*  83*/        super(classpool);
/*  44*/        softcache = new SoftValueHashMap();
/*  46*/        isBootstrapCl = true;
/*  84*/        repository = scopedclasspoolrepository;
/*  85*/        classLoader = new WeakReference(classloader);
/*  86*/        if(classloader != null)
                {
/*  87*/            classPath = new LoaderClassPath(classloader);
/*  88*/            insertClassPath(classPath);
                }
/*  90*/        childFirstLookup = true;
/*  91*/        if(!flag && classloader == null)
/*  93*/            isBootstrapCl = true;
            }

            public ClassLoader getClassLoader()
            {
                ClassLoader classloader;
/* 103*/        if((classloader = getClassLoader0()) == null && !isBootstrapCl)
/* 106*/            throw new IllegalStateException("ClassLoader has been garbage collected");
/* 109*/        else
/* 109*/            return classloader;
            }

            protected ClassLoader getClassLoader0()
            {
/* 113*/        return (ClassLoader)classLoader.get();
            }

            public void close()
            {
/* 120*/        removeClassPath(classPath);
/* 121*/        classPath.close();
/* 122*/        classes.clear();
/* 123*/        softcache.clear();
            }

            public synchronized void flushClass(String s)
            {
/* 133*/        classes.remove(s);
/* 134*/        softcache.remove(s);
            }

            public synchronized void soften(CtClass ctclass)
            {
/* 144*/        if(repository.isPrune())
/* 145*/            ctclass.prune();
/* 146*/        classes.remove(ctclass.getName());
/* 147*/        softcache.put(ctclass.getName(), ctclass);
            }

            public boolean isUnloadedClassLoader()
            {
/* 156*/        return false;
            }

            protected CtClass getCached(String s)
            {
                CtClass ctclass;
/* 167*/        if((ctclass = getCachedLocally(s)) != null)
/* 169*/            break MISSING_BLOCK_LABEL_227;
/* 169*/        int i = 0;
                ClassLoader classloader;
/* 171*/        if((classloader = getClassLoader0()) != null)
                {
                    String s1;
/* 173*/            if((i = s.lastIndexOf('$')) < 0)
/* 176*/                s1 = (new StringBuilder()).append(s.replaceAll("[\\.]", "/")).append(".class").toString();
/* 180*/            else
/* 180*/                s1 = (new StringBuilder()).append(s.substring(0, i).replaceAll("[\\.]", "/")).append(s.substring(i)).append(".class").toString();
/* 185*/            i = classloader.getResource(s1) == null ? 0 : 1;
                }
/* 188*/        if(i != 0)
/* 189*/            break MISSING_BLOCK_LABEL_227;
                Object obj;
/* 189*/        Map map = ((Map) (obj = repository.getRegisteredCLs()));
/* 190*/        JVM INSTR monitorenter ;
                ScopedClassPool scopedclasspool;
/* 191*/label0:
/* 191*/        do
                {
/* 191*/            for(obj = ((Map) (obj)).values().iterator(); ((Iterator) (obj)).hasNext(); repository.unregisterClassLoader(scopedclasspool.getClassLoader()))
/* 193*/                if(!(scopedclasspool = (ScopedClassPool)((Iterator) (obj)).next()).isUnloadedClassLoader())
/* 195*/                    continue label0;

/* 197*/            break MISSING_BLOCK_LABEL_215;
                } while((ctclass = scopedclasspool.getCachedLocally(s)) == null);
/* 202*/        return ctclass;
/* 205*/        map;
/* 205*/        JVM INSTR monitorexit ;
/* 205*/        break MISSING_BLOCK_LABEL_227;
/* 205*/        s;
/* 205*/        throw s;
/* 209*/        return ctclass;
            }

            protected void cacheCtClass(String s, CtClass ctclass, boolean flag)
            {
/* 223*/        if(flag)
                {
/* 224*/            super.cacheCtClass(s, ctclass, flag);
/* 224*/            return;
                }
/* 227*/        if(repository.isPrune())
/* 228*/            ctclass.prune();
/* 229*/        softcache.put(s, ctclass);
            }

            public void lockInCache(CtClass ctclass)
            {
/* 240*/        super.cacheCtClass(ctclass.getName(), ctclass, false);
            }

            protected CtClass getCachedLocally(String s)
            {
                Object obj;
/* 251*/        if((obj = (CtClass)classes.get(s)) != null)
/* 253*/            return ((CtClass) (obj));
/* 254*/        obj = softcache;
/* 254*/        JVM INSTR monitorenter ;
/* 255*/        return (CtClass)softcache.get(s);
/* 256*/        s;
/* 256*/        throw s;
            }

            public synchronized CtClass getLocally(String s)
                throws NotFoundException
            {
/* 270*/        softcache.remove(s);
                CtClass ctclass;
/* 271*/        if((ctclass = (CtClass)classes.get(s)) == null)
                {
/* 273*/            if((ctclass = createCtClass(s, true)) == null)
/* 275*/                throw new NotFoundException(s);
/* 276*/            super.cacheCtClass(s, ctclass, false);
                }
/* 279*/        return ctclass;
            }

            public Class toClass(CtClass ctclass, ClassLoader classloader, ProtectionDomain protectiondomain)
                throws CannotCompileException
            {
/* 306*/        lockInCache(ctclass);
/* 307*/        return super.toClass(ctclass, getClassLoader0(), protectiondomain);
            }

            protected ScopedClassPoolRepository repository;
            protected WeakReference classLoader;
            protected LoaderClassPath classPath;
            protected SoftValueHashMap softcache;
            boolean isBootstrapCl;

            static 
            {
/*  49*/        ClassPool.doPruning = false;
/*  50*/        ClassPool.releaseUnmodifiedClassFile = false;
            }
}
