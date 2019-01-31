// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotatedElementAnnotationInfo.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;

// Referenced classes of package org.jvnet.hk2.internal:
//            SoftAnnotatedElementAnnotationInfo

class AnnotatedElementAnnotationInfo
{

            AnnotatedElementAnnotationInfo(Annotation aannotation[], boolean flag, Annotation aannotation1[][], boolean flag1)
            {
/*  56*/        elementAnnotations = aannotation;
/*  57*/        hasParams = flag;
/*  58*/        paramAnnotations = aannotation1;
/*  59*/        isConstructor = flag1;
            }

            SoftAnnotatedElementAnnotationInfo soften()
            {
/*  63*/        return new SoftAnnotatedElementAnnotationInfo(elementAnnotations, hasParams, paramAnnotations, isConstructor);
            }

            final Annotation elementAnnotations[];
            final Annotation paramAnnotations[][];
            final boolean hasParams;
            final boolean isConstructor;
}
