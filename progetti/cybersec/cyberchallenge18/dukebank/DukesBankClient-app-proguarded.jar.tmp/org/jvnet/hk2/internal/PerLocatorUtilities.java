// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerLocatorUtilities.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.WeakHashMap;
import javax.inject.Inject;
import org.glassfish.hk2.api.InjectionPointIndicator;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.utilities.general.Hk2ThreadLocal;
import org.glassfish.hk2.utilities.reflection.Pretty;
import org.jvnet.hk2.annotations.Service;

// Referenced classes of package org.jvnet.hk2.internal:
//            AnnotatedElementAnnotationInfo, ProxyUtilities, ServiceLocatorImpl, SoftAnnotatedElementAnnotationInfo, 
//            SystemInjecteeImpl, Utilities

public class PerLocatorUtilities
{

            PerLocatorUtilities(ServiceLocatorImpl servicelocatorimpl)
            {
/*  95*/        parent = servicelocatorimpl;
            }

            boolean hasInjectAnnotation(AnnotatedElement annotatedelement)
            {
                WeakHashMap weakhashmap;
                Boolean boolean1;
/* 108*/        if((boolean1 = (Boolean)(weakhashmap = (WeakHashMap)hasInjectCache.get()).get(annotatedelement)) != null)
/* 110*/            return boolean1.booleanValue();
                Annotation aannotation[];
/* 112*/        int i = (aannotation = annotatedelement.getAnnotations()).length;
/* 112*/        for(int k = 0; k < i; k++)
                {
                    Annotation annotation;
/* 112*/            if((annotation = aannotation[k]).annotationType().getAnnotation(org/glassfish/hk2/api/InjectionPointIndicator) != null)
                    {
/* 114*/                weakhashmap.put(annotatedelement, Boolean.valueOf(true));
/* 115*/                return true;
                    }
/* 118*/            if(parent.isInjectAnnotation(annotation))
                    {
/* 119*/                weakhashmap.put(annotatedelement, Boolean.valueOf(true));
/* 120*/                return true;
                    }
                }

                boolean flag;
                Annotation aannotation1[][];
/* 126*/        if(annotatedelement instanceof Method)
                {
/* 127*/            Method method = (Method)annotatedelement;
/* 129*/            flag = false;
/* 130*/            aannotation1 = method.getParameterAnnotations();
                } else
/* 131*/        if(annotatedelement instanceof Constructor)
                {
/* 132*/            Constructor constructor = (Constructor)annotatedelement;
/* 134*/            flag = true;
/* 135*/            aannotation1 = constructor.getParameterAnnotations();
                } else
                {
/* 137*/            weakhashmap.put(annotatedelement, Boolean.valueOf(false));
/* 138*/            return false;
                }
                Annotation aannotation2[][];
/* 141*/        int l = (aannotation2 = aannotation1).length;
/* 141*/        for(int j = 0; j < l; j++)
                {
                    Annotation aannotation3[];
/* 141*/            int i1 = (aannotation3 = aannotation3 = aannotation2[j]).length;
/* 142*/            for(int j1 = 0; j1 < i1; j1++)
                    {
                        Annotation annotation1;
/* 142*/                if((annotation1 = aannotation3[j1]).annotationType().getAnnotation(org/glassfish/hk2/api/InjectionPointIndicator) != null)
                        {
/* 144*/                    weakhashmap.put(annotatedelement, Boolean.valueOf(true));
/* 145*/                    return true;
                        }
/* 148*/                if(parent.isInjectAnnotation(annotation1, flag))
                        {
/* 149*/                    weakhashmap.put(annotatedelement, Boolean.valueOf(true));
/* 150*/                    return true;
                        }
                    }

                }

/* 155*/        weakhashmap.put(annotatedelement, Boolean.valueOf(false));
/* 156*/        return false;
            }

            public String getAutoAnalyzerName(Class class1)
            {
                Object obj;
/* 166*/        if((obj = (String)((WeakHashMap)threadLocalAutoAnalyzerNameCache.get()).get(class1)) != null)
/* 167*/            return ((String) (obj));
/* 169*/        if((obj = (Service)class1.getAnnotation(org/jvnet/hk2/annotations/Service)) == null)
                {
/* 170*/            return null;
                } else
                {
/* 172*/            obj = ((Service) (obj)).analyzer();
/* 173*/            ((WeakHashMap)threadLocalAutoAnalyzerNameCache.get()).put(class1, obj);
/* 175*/            return ((String) (obj));
                }
            }

            public InjectionResolver getInjectionResolver(ServiceLocatorImpl servicelocatorimpl, SystemInjecteeImpl systeminjecteeimpl)
                throws IllegalStateException
            {
/* 188*/        return getInjectionResolver(servicelocatorimpl, systeminjecteeimpl.getParent(), systeminjecteeimpl.getPosition());
            }

            InjectionResolver getInjectionResolver(ServiceLocatorImpl servicelocatorimpl, AnnotatedElement annotatedelement)
                throws IllegalStateException
            {
/* 203*/        if((annotatedelement instanceof Method) || (annotatedelement instanceof Constructor))
/* 204*/            throw new IllegalArgumentException((new StringBuilder("Annotated element '")).append(annotatedelement).append("' can be neither a Method nor a Constructor.").toString());
/* 206*/        else
/* 206*/            return getInjectionResolver(servicelocatorimpl, annotatedelement, -1);
            }

            private InjectionResolver getInjectionResolver(ServiceLocatorImpl servicelocatorimpl, AnnotatedElement annotatedelement, int i)
                throws IllegalStateException
            {
/* 211*/        boolean flag = (annotatedelement instanceof Method) || (annotatedelement instanceof Constructor);
/* 212*/        i = ((int) ((i = getInjectAnnotation(servicelocatorimpl, annotatedelement, flag, i)) != null ? ((int) (i.annotationType())) : javax/inject/Inject));
/* 219*/        if((servicelocatorimpl = servicelocatorimpl.getInjectionResolver(i)) == null)
/* 222*/            throw new IllegalStateException((new StringBuilder("There is no installed injection resolver for ")).append(Pretty.clazz(i)).append(" for type ").append(annotatedelement).toString());
/* 226*/        else
/* 226*/            return servicelocatorimpl;
            }

            private Annotation getInjectAnnotation(ServiceLocatorImpl servicelocatorimpl, AnnotatedElement annotatedelement, boolean flag, int i)
            {
/* 245*/        annotatedelement = computeElementAnnotationInfo(annotatedelement);
/* 247*/        if(flag && ((AnnotatedElementAnnotationInfo) (annotatedelement)).hasParams)
                {
/* 250*/            i = (flag = ((AnnotatedElementAnnotationInfo) (annotatedelement)).paramAnnotations[i]).length;
/* 250*/            for(int j = 0; j < i; j++)
                    {
/* 250*/                Annotation annotation = flag[j];
/* 251*/                if(servicelocatorimpl.isInjectAnnotation(annotation, ((AnnotatedElementAnnotationInfo) (annotatedelement)).isConstructor))
/* 252*/                    return annotation;
                    }

                }
/* 258*/        i = (flag = ((AnnotatedElementAnnotationInfo) (annotatedelement)).elementAnnotations).length;
/* 258*/        for(int k = 0; k < i; k++)
                {
/* 258*/            Annotation annotation1 = flag[k];
/* 259*/            if(servicelocatorimpl.isInjectAnnotation(annotation1))
/* 260*/                return annotation1;
                }

/* 264*/        return null;
            }

            private AnnotatedElementAnnotationInfo computeElementAnnotationInfo(AnnotatedElement annotatedelement)
            {
                AnnotatedElementAnnotationInfo annotatedelementannotationinfo;
                SoftAnnotatedElementAnnotationInfo softannotatedelementannotationinfo;
/* 269*/        if((softannotatedelementannotationinfo = (SoftAnnotatedElementAnnotationInfo)((WeakHashMap)threadLocalAnnotationCache.get()).get(annotatedelement)) != null)
                {
/* 271*/            annotatedelementannotationinfo = softannotatedelementannotationinfo.harden(annotatedelement);
                } else
                {
/* 274*/            SoftAnnotatedElementAnnotationInfo softannotatedelementannotationinfo1 = (annotatedelementannotationinfo = Utilities.computeAEAI(annotatedelement)).soften();
/* 276*/            ((WeakHashMap)threadLocalAnnotationCache.get()).put(annotatedelement, softannotatedelementannotationinfo1);
                }
/* 278*/        return annotatedelementannotationinfo;
            }

            public synchronized void releaseCaches()
            {
/* 282*/        hasInjectCache.removeAll();
/* 283*/        if(proxyUtilities != null)
/* 284*/            proxyUtilities.releaseCache();
            }

            public void shutdown()
            {
/* 289*/        releaseCaches();
/* 291*/        threadLocalAutoAnalyzerNameCache.removeAll();
/* 292*/        threadLocalAnnotationCache.removeAll();
            }

            public ProxyUtilities getProxyUtilities()
            {
/* 296*/        if(proxyUtilities != null)
/* 296*/            return proxyUtilities;
/* 298*/        PerLocatorUtilities perlocatorutilities = this;
/* 298*/        JVM INSTR monitorenter ;
/* 299*/        if(proxyUtilities != null)
/* 299*/            return proxyUtilities;
/* 301*/        proxyUtilities = new ProxyUtilities();
/* 303*/        proxyUtilities;
/* 303*/        perlocatorutilities;
/* 303*/        JVM INSTR monitorexit ;
/* 303*/        return;
                Exception exception;
/* 304*/        exception;
/* 304*/        throw exception;
            }

            private final Hk2ThreadLocal threadLocalAutoAnalyzerNameCache = new Hk2ThreadLocal() {

                protected WeakHashMap initialValue()
                {
/*  69*/            return new WeakHashMap();
                }

                protected volatile Object initialValue()
                {
/*  66*/            return initialValue();
                }

                final PerLocatorUtilities this$0;

                    
                    {
/*  66*/                this$0 = PerLocatorUtilities.this;
/*  66*/                super();
                    }
    };
            private final Hk2ThreadLocal threadLocalAnnotationCache = new Hk2ThreadLocal() {

                protected WeakHashMap initialValue()
                {
/*  79*/            return new WeakHashMap();
                }

                protected volatile Object initialValue()
                {
/*  76*/            return initialValue();
                }

                final PerLocatorUtilities this$0;

                    
                    {
/*  76*/                this$0 = PerLocatorUtilities.this;
/*  76*/                super();
                    }
    };
            private final Hk2ThreadLocal hasInjectCache = new Hk2ThreadLocal() {

                protected WeakHashMap initialValue()
                {
/*  87*/            return new WeakHashMap();
                }

                protected volatile Object initialValue()
                {
/*  84*/            return initialValue();
                }

                final PerLocatorUtilities this$0;

                    
                    {
/*  84*/                this$0 = PerLocatorUtilities.this;
/*  84*/                super();
                    }
    };
            private volatile ProxyUtilities proxyUtilities;
            private final ServiceLocatorImpl parent;
}
