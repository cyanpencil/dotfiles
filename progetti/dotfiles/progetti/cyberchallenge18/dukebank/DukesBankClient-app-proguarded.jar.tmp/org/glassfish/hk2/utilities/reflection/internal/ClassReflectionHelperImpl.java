// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperImpl.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import org.glassfish.hk2.utilities.cache.*;
import org.glassfish.hk2.utilities.reflection.ClassReflectionHelper;
import org.glassfish.hk2.utilities.reflection.MethodWrapper;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperUtilities, MethodWrapperImpl

public class ClassReflectionHelperImpl
    implements ClassReflectionHelper
{
    static final class LifecycleKey
    {

                public final int hashCode()
                {
/* 236*/            return hash;
                }

                public final boolean equals(Object obj)
                {
/* 240*/            if(obj == null)
/* 240*/                return false;
/* 241*/            if(!(obj instanceof LifecycleKey))
/* 241*/                return false;
/* 242*/            else
/* 242*/                return clazz.equals(((LifecycleKey)obj).clazz);
                }

                private final Class clazz;
                private final Class matchingClass;
                private final int hash;



                private LifecycleKey(Class class1, Class class2)
                {
/* 230*/            clazz = class1;
/* 231*/            matchingClass = class2;
/* 232*/            hash = class1.hashCode();
                }

    }


            public ClassReflectionHelperImpl()
            {
            }

            public Set getAllMethods(Class class1)
            {
/* 108*/        return (Set)methodCache.compute(class1).getValue();
            }

            public Set getAllFields(Class class1)
            {
/* 113*/        return (Set)fieldCache.compute(class1).getValue();
            }

            public Method findPostConstruct(Class class1, Class class2)
                throws IllegalArgumentException
            {
/* 119*/        return (Method)postConstructCache.compute(new LifecycleKey(class1, class2)).getValue();
            }

            public Method findPreDestroy(Class class1, Class class2)
                throws IllegalArgumentException
            {
/* 125*/        return (Method)preDestroyCache.compute(new LifecycleKey(class1, class2)).getValue();
            }

            public void clean(Class class1)
            {
/* 130*/        for(; class1 != null && !java/lang/Object.equals(class1); class1 = class1.getSuperclass())
                {
/* 131*/            postConstructCache.remove(new LifecycleKey(class1, null));
/* 132*/            preDestroyCache.remove(new LifecycleKey(class1, null));
/* 133*/            methodCache.remove(class1);
/* 134*/            fieldCache.remove(class1);
                }

            }

            public MethodWrapper createMethodWrapper(Method method)
            {
/* 145*/        return new MethodWrapperImpl(method);
            }

            public void dispose()
            {
/* 150*/        postConstructCache.clear();
/* 151*/        preDestroyCache.clear();
/* 152*/        methodCache.clear();
/* 153*/        fieldCache.clear();
            }

            public int size()
            {
/* 161*/        return postConstructCache.size() + preDestroyCache.size() + methodCache.size() + fieldCache.size();
            }

            private Method getPostConstructMethod(Class class1, Class class2)
            {
/* 168*/        if(class1 == null || java/lang/Object.equals(class1))
/* 168*/            return null;
/* 170*/        if(class2.isAssignableFrom(class1))
                {
/* 175*/            try
                    {
/* 175*/                class1 = class1.getMethod("postConstruct", new Class[0]);
                    }
/* 177*/            catch(NoSuchMethodException _ex)
                    {
/* 178*/                class1 = null;
                    }
/* 181*/            return class1;
                }
/* 184*/        for(class1 = getAllMethods(class1).iterator(); class1.hasNext();)
/* 184*/            if(ClassReflectionHelperUtilities.isPostConstruct(class2 = (class2 = (MethodWrapper)class1.next()).getMethod()))
/* 186*/                return class2;

/* 189*/        return null;
            }

            private Method getPreDestroyMethod(Class class1, Class class2)
            {
/* 193*/        if(class1 == null || java/lang/Object.equals(class1))
/* 193*/            return null;
/* 195*/        if(class2.isAssignableFrom(class1))
                {
/* 200*/            try
                    {
/* 200*/                class1 = class1.getMethod("preDestroy", new Class[0]);
                    }
/* 202*/            catch(NoSuchMethodException _ex)
                    {
/* 203*/                class1 = null;
                    }
/* 206*/            return class1;
                }
/* 209*/        for(class1 = getAllMethods(class1).iterator(); class1.hasNext();)
/* 209*/            if(ClassReflectionHelperUtilities.isPreDestroy(class2 = (class2 = (MethodWrapper)class1.next()).getMethod()))
/* 211*/                return class2;

/* 214*/        return null;
            }

            public String toString()
            {
/* 221*/        return (new StringBuilder("ClassReflectionHelperImpl(")).append(System.identityHashCode(this)).append(")").toString();
            }

            private final int MAX_CACHE_SIZE = 20000;
            private final LRUHybridCache postConstructCache = new LRUHybridCache(20000, new Computable() {

                public HybridCacheEntry compute(LifecycleKey lifecyclekey)
                {
/*  64*/            return postConstructCache.createCacheEntry(lifecyclekey, getPostConstructMethod(lifecyclekey.clazz, lifecyclekey.matchingClass), false);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  60*/            return compute((LifecycleKey)obj);
                }

                final ClassReflectionHelperImpl this$0;

                    
                    {
/*  60*/                this$0 = ClassReflectionHelperImpl.this;
/*  60*/                super();
                    }
    });
            private final LRUHybridCache preDestroyCache = new LRUHybridCache(20000, new Computable() {

                public HybridCacheEntry compute(LifecycleKey lifecyclekey)
                {
/*  74*/            return preDestroyCache.createCacheEntry(lifecyclekey, getPreDestroyMethod(lifecyclekey.clazz, lifecyclekey.matchingClass), false);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  70*/            return compute((LifecycleKey)obj);
                }

                final ClassReflectionHelperImpl this$0;

                    
                    {
/*  70*/                this$0 = ClassReflectionHelperImpl.this;
/*  70*/                super();
                    }
    });
            private final LRUHybridCache methodCache = new LRUHybridCache(20000, new Computable() {

                public HybridCacheEntry compute(Class class1)
                {
/*  84*/            return methodCache.createCacheEntry(class1, ClassReflectionHelperUtilities.getAllMethodWrappers(class1), false);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  80*/            return compute((Class)obj);
                }

                final ClassReflectionHelperImpl this$0;

                    
                    {
/*  80*/                this$0 = ClassReflectionHelperImpl.this;
/*  80*/                super();
                    }
    });
            private final LRUHybridCache fieldCache = new LRUHybridCache(20000, new Computable() {

                public HybridCacheEntry compute(Class class1)
                {
/*  95*/            return fieldCache.createCacheEntry(class1, ClassReflectionHelperUtilities.getAllFieldWrappers(class1), false);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  91*/            return compute((Class)obj);
                }

                final ClassReflectionHelperImpl this$0;

                    
                    {
/*  91*/                this$0 = ClassReflectionHelperImpl.this;
/*  91*/                super();
                    }
    });






}
