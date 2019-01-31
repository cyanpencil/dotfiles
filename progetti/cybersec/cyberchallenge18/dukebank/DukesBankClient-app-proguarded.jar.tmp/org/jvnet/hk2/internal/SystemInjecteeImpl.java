// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SystemInjecteeImpl.java

package org.jvnet.hk2.internal;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.Set;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.Pretty;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

public class SystemInjecteeImpl
    implements Injectee
{

            SystemInjecteeImpl(Type type, Set set, int i, AnnotatedElement annotatedelement, boolean flag, boolean flag1, Unqualified unqualified1, 
                    ActiveDescriptor activedescriptor)
            {
/*  86*/        requiredType = type;
/*  87*/        position = i;
/*  88*/        parent = annotatedelement;
/*  89*/        qualifiers = Collections.unmodifiableSet(set);
/*  90*/        isOptional = flag;
/*  91*/        isSelf = flag1;
/*  92*/        unqualified = unqualified1;
/*  93*/        injecteeDescriptor = activedescriptor;
/*  95*/        if(annotatedelement instanceof Field)
                {
/*  96*/            pClass = ((Field)annotatedelement).getDeclaringClass();
/*  97*/            parentIdentifier = ((Field)annotatedelement).getName();
/*  97*/            return;
                }
/*  99*/        if(annotatedelement instanceof Constructor)
                {
/* 100*/            pClass = ((Constructor)annotatedelement).getDeclaringClass();
/* 101*/            parentIdentifier = pClass;
/* 101*/            return;
                } else
                {
/* 104*/            pClass = ((Method)annotatedelement).getDeclaringClass();
/* 105*/            parentIdentifier = ReflectionHelper.createMethodWrapper((Method)annotatedelement);
/* 107*/            return;
                }
            }

            public Type getRequiredType()
            {
/* 114*/        return requiredType;
            }

            public Set getRequiredQualifiers()
            {
/* 122*/        return qualifiers;
            }

            public int getPosition()
            {
/* 130*/        return position;
            }

            public Class getInjecteeClass()
            {
/* 138*/        return pClass;
            }

            public AnnotatedElement getParent()
            {
/* 146*/        return parent;
            }

            public boolean isOptional()
            {
/* 154*/        return isOptional;
            }

            public boolean isSelf()
            {
/* 162*/        return isSelf;
            }

            public Unqualified getUnqualified()
            {
/* 167*/        return unqualified;
            }

            public ActiveDescriptor getInjecteeDescriptor()
            {
/* 172*/        return injecteeDescriptor;
            }

            void resetInjecteeDescriptor(ActiveDescriptor activedescriptor)
            {
/* 176*/        injecteeDescriptor = activedescriptor;
            }

            public int hashCode()
            {
/* 181*/        return position ^ parentIdentifier.hashCode() ^ pClass.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 186*/        if(obj == null)
/* 186*/            return false;
/* 187*/        if(!(obj instanceof SystemInjecteeImpl))
/* 187*/            return false;
/* 189*/        obj = (SystemInjecteeImpl)obj;
/* 191*/        if(position != ((SystemInjecteeImpl) (obj)).getPosition())
/* 191*/            return false;
/* 192*/        if(!pClass.equals(((SystemInjecteeImpl) (obj)).getInjecteeClass()))
/* 192*/            return false;
/* 194*/        else
/* 194*/            return parentIdentifier.equals(((SystemInjecteeImpl) (obj)).parentIdentifier);
            }

            public String toString()
            {
/* 199*/        return (new StringBuilder("SystemInjecteeImpl(requiredType=")).append(Pretty.type(requiredType)).append(",parent=").append(Pretty.clazz(pClass)).append(",qualifiers=").append(Pretty.collection(qualifiers)).append(",position=").append(position).append(",optional=").append(isOptional).append(",self=").append(isSelf).append(",unqualified=").append(unqualified).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final Type requiredType;
            private final Set qualifiers;
            private final int position;
            private final Class pClass;
            private final AnnotatedElement parent;
            private final boolean isOptional;
            private final boolean isSelf;
            private final Unqualified unqualified;
            private ActiveDescriptor injecteeDescriptor;
            private final Object parentIdentifier;
}
