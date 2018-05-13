// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClassAnalyzer.java

package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.binding.*;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public final class JerseyClassAnalyzer
    implements ClassAnalyzer
{
    public static final class Binder extends AbstractBinder
    {

                protected final void configure()
                {
/*  92*/            bind(org/glassfish/jersey/internal/inject/JerseyClassAnalyzer).analyzeWith("default").named("JerseyClassAnalyzer").to(org/glassfish/hk2/api/ClassAnalyzer).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            JerseyClassAnalyzer(ClassAnalyzer classanalyzer, IterableProvider iterableprovider)
            {
/* 112*/        defaultAnalyzer = classanalyzer;
/* 114*/        classanalyzer = new HashSet();
/* 115*/        iterableprovider = iterableprovider.iterator();
/* 115*/        do
                {
/* 115*/            if(!iterableprovider.hasNext())
/* 115*/                break;
                    Object obj;
/* 115*/            if(((InjectionResolver) (obj = (InjectionResolver)iterableprovider.next())).isConstructorParameterIndicator())
                    {
/* 117*/                obj = ReflectionHelper.erasure(((java.lang.reflect.Type) (obj = ReflectionHelper.getParameterizedTypeArguments(((org.glassfish.jersey.internal.util.ReflectionHelper.DeclaringClassInterfacePair) (obj = ReflectionHelper.getClass(obj.getClass(), org/glassfish/hk2/api/InjectionResolver))))[0])));
/* 121*/                if(java/lang/annotation/Annotation.isAssignableFrom(((Class) (obj))))
/* 122*/                    classanalyzer.add(((Class) (obj)).asSubclass(java/lang/annotation/Annotation));
                    }
                } while(true);
/* 127*/        resolverAnnotations = classanalyzer.size() <= 0 ? Collections.emptySet() : Collections.unmodifiableSet(classanalyzer);
            }

            public final Constructor getConstructor(final Class clazz)
                throws MultiException, NoSuchMethodException
            {
/* 134*/        if(clazz.isLocalClass())
/* 135*/            throw new NoSuchMethodException(LocalizationMessages.INJECTION_ERROR_LOCAL_CLASS_NOT_SUPPORTED(clazz.getName()));
/* 137*/        if(clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()))
/* 138*/            throw new NoSuchMethodException(LocalizationMessages.INJECTION_ERROR_NONSTATIC_MEMBER_CLASS_NOT_SUPPORTED(clazz.getName()));
                Constructor constructor;
                Class aclass[];
/* 144*/        if((aclass = (constructor = defaultAnalyzer.getConstructor(clazz)).getParameterTypes()).length != 0)
/* 148*/            return constructor;
                Inject inject;
/* 152*/        if((inject = (Inject)constructor.getAnnotation(javax/inject/Inject)) != null)
/* 154*/            return constructor;
/* 165*/        break MISSING_BLOCK_LABEL_133;
/* 158*/        JVM INSTR pop ;
/* 165*/        break MISSING_BLOCK_LABEL_133;
/* 160*/        JVM INSTR dup ;
                MultiException multiexception;
/* 161*/        multiexception;
/* 161*/        getErrors();
/* 161*/        size();
/* 161*/        1;
/* 161*/        JVM INSTR icmpeq 133;
                   goto _L1 _L2
_L1:
/* 161*/        break MISSING_BLOCK_LABEL_115;
_L2:
/* 161*/        break MISSING_BLOCK_LABEL_133;
/* 161*/        if(!(multiexception.getErrors().get(0) instanceof IllegalArgumentException))
/* 162*/            throw multiexception;
/* 168*/        Constructor aconstructor[] = (Constructor[])AccessController.doPrivileged(new PrivilegedAction() {

                    public Constructor[] run()
                    {
/* 171*/                return clazz.getDeclaredConstructors();
                    }

                    public volatile Object run()
                    {
/* 168*/                return run();
                    }

                    final Class val$clazz;
                    final JerseyClassAnalyzer this$0;

                    
                    {
/* 168*/                this$0 = JerseyClassAnalyzer.this;
/* 168*/                clazz = class1;
/* 168*/                super();
                    }
        });
/* 174*/        Constructor constructor1 = null;
/* 175*/        int i = 0;
/* 176*/        int j = -1;
/* 178*/        int k = (aconstructor = aconstructor).length;
/* 178*/        for(int l = 0; l < k; l++)
                {
                    Constructor constructor2;
                    Class aclass1[];
/* 178*/            if((aclass1 = (constructor2 = aconstructor[l]).getParameterTypes()).length < j || !isCompatible(constructor2))
/* 181*/                continue;
/* 181*/            if(aclass1.length > j)
                    {
/* 182*/                j = aclass1.length;
/* 183*/                i = 0;
                    }
/* 186*/            constructor1 = constructor2;
/* 187*/            i++;
                }

/* 191*/        if(i == 0)
/* 192*/            throw new NoSuchMethodException(LocalizationMessages.INJECTION_ERROR_SUITABLE_CONSTRUCTOR_NOT_FOUND(clazz.getName()));
/* 195*/        if(i > 1)
/* 197*/            Errors.warning(clazz, LocalizationMessages.MULTIPLE_MATCHING_CONSTRUCTORS_FOUND(Integer.valueOf(i), Integer.valueOf(j), clazz.getName(), constructor1.toGenericString()));
/* 201*/        return constructor1;
            }

            private boolean isCompatible(Constructor constructor)
            {
/* 206*/        if(constructor.getAnnotation(javax/inject/Inject) != null)
/* 208*/            return true;
                int i;
/* 211*/        if((i = constructor.getParameterTypes().length) != 0 && resolverAnnotations.isEmpty())
/* 214*/            return false;
/* 217*/        if(!Modifier.isPublic(constructor.getModifiers()))
/* 219*/            return i == 0 && (constructor.getDeclaringClass().getModifiers() & 7) == constructor.getModifiers();
/* 224*/        i = (constructor = constructor.getParameterAnnotations()).length;
/* 224*/        for(int j = 0; j < i; j++)
                {
/* 224*/            Object obj = constructor[j];
/* 225*/            boolean flag = false;
/* 226*/            int k = (obj = obj).length;
/* 226*/            int l = 0;
/* 226*/            do
                    {
/* 226*/                if(l >= k)
/* 226*/                    break;
/* 226*/                Annotation annotation = obj[l];
/* 227*/                if(resolverAnnotations.contains(annotation.annotationType()))
                        {
/* 228*/                    flag = true;
/* 229*/                    break;
                        }
/* 226*/                l++;
                    } while(true);
/* 232*/            if(!flag)
/* 233*/                return false;
                }

/* 237*/        return true;
            }

            public final Set getInitializerMethods(Class class1)
                throws MultiException
            {
/* 242*/        return defaultAnalyzer.getInitializerMethods(class1);
            }

            public final Set getFields(Class class1)
                throws MultiException
            {
/* 247*/        return defaultAnalyzer.getFields(class1);
            }

            public final Method getPostConstructMethod(Class class1)
                throws MultiException
            {
/* 252*/        return defaultAnalyzer.getPostConstructMethod(class1);
            }

            public final Method getPreDestroyMethod(Class class1)
                throws MultiException
            {
/* 257*/        return defaultAnalyzer.getPreDestroyMethod(class1);
            }

            public static final String NAME = "JerseyClassAnalyzer";
            private final ClassAnalyzer defaultAnalyzer;
            private final Set resolverAnnotations;
}
