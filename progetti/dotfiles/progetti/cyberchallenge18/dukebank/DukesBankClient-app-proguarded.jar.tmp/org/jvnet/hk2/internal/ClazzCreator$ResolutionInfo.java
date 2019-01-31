// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClazzCreator.java

package org.jvnet.hk2.internal;

import java.lang.reflect.AnnotatedElement;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package org.jvnet.hk2.internal:
//            ClazzCreator

static class <init>
{

            public String toString()
            {
/* 450*/        return (new StringBuilder("ResolutionInfo(")).append(baseElement).append(",").append(injectees).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final AnnotatedElement baseElement;
            private final List injectees;



            private (AnnotatedElement annotatedelement, List list)
            {
/* 441*/        injectees = new LinkedList();
/* 444*/        baseElement = annotatedelement;
/* 445*/        injectees.addAll(list);
            }

            injectees(AnnotatedElement annotatedelement, List list, injectees injectees1)
            {
/* 439*/        this(annotatedelement, list);
            }
}
