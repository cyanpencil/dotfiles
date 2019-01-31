// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SoftAnnotatedElementAnnotationInfo.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.AnnotatedElement;

// Referenced classes of package org.jvnet.hk2.internal:
//            AnnotatedElementAnnotationInfo, Utilities

class SoftAnnotatedElementAnnotationInfo
{

            SoftAnnotatedElementAnnotationInfo(Annotation aannotation[], boolean flag, Annotation aannotation1[][], boolean flag1)
            {
/*  58*/        elementAnnotationsReference = new SoftReference(aannotation);
/*  59*/        hasParams = flag;
/*  60*/        paramAnnotationsReference = new SoftReference(aannotation1);
/*  61*/        isConstructor = flag1;
            }

            AnnotatedElementAnnotationInfo harden(AnnotatedElement annotatedelement)
            {
/*  65*/        Annotation aannotation[] = (Annotation[])elementAnnotationsReference.get();
/*  66*/        Annotation aannotation1[][] = (Annotation[][])paramAnnotationsReference.get();
/*  68*/        if(!Utilities.USE_SOFT_REFERENCE || aannotation == null || aannotation1 == null)
/*  69*/            return Utilities.computeAEAI(annotatedelement);
/*  72*/        else
/*  72*/            return new AnnotatedElementAnnotationInfo(aannotation, hasParams, aannotation1, isConstructor);
            }

            private final SoftReference elementAnnotationsReference;
            private final SoftReference paramAnnotationsReference;
            private final boolean hasParams;
            private final boolean isConstructor;
}
