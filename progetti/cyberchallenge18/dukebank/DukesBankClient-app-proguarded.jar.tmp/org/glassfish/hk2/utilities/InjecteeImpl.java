// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InjecteeImpl.java

package org.glassfish.hk2.utilities;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.Set;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.Pretty;

public class InjecteeImpl
    implements Injectee
{

            public InjecteeImpl()
            {
/*  68*/        position = -1;
/*  71*/        isOptional = false;
/*  72*/        isSelf = false;
/*  73*/        unqualified = null;
            }

            public InjecteeImpl(Type type)
            {
/*  68*/        position = -1;
/*  71*/        isOptional = false;
/*  72*/        isSelf = false;
/*  73*/        unqualified = null;
/*  88*/        requiredType = type;
            }

            public InjecteeImpl(Injectee injectee)
            {
/*  68*/        position = -1;
/*  71*/        isOptional = false;
/*  72*/        isSelf = false;
/*  73*/        unqualified = null;
/*  96*/        requiredType = injectee.getRequiredType();
/*  97*/        position = injectee.getPosition();
/*  98*/        parent = injectee.getParent();
/*  99*/        qualifiers = Collections.unmodifiableSet(injectee.getRequiredQualifiers());
/* 100*/        isOptional = injectee.isOptional();
/* 101*/        isSelf = injectee.isSelf();
/* 102*/        injecteeDescriptor = injectee.getInjecteeDescriptor();
/* 105*/        if(parent == null)
                {
/* 106*/            pClass = null;
/* 106*/            return;
                }
/* 108*/        if(parent instanceof Field)
                {
/* 109*/            pClass = ((Field)parent).getDeclaringClass();
/* 109*/            return;
                }
/* 111*/        if(parent instanceof Constructor)
                {
/* 112*/            pClass = ((Constructor)parent).getDeclaringClass();
/* 112*/            return;
                }
/* 114*/        if(parent instanceof Method)
/* 115*/            pClass = ((Method)parent).getDeclaringClass();
            }

            public Type getRequiredType()
            {
/* 124*/        return requiredType;
            }

            public void setRequiredType(Type type)
            {
/* 132*/        requiredType = type;
            }

            public Set getRequiredQualifiers()
            {
/* 140*/        if(qualifiers == null)
/* 140*/            return Collections.emptySet();
/* 141*/        else
/* 141*/            return qualifiers;
            }

            public void setRequiredQualifiers(Set set)
            {
/* 149*/        qualifiers = Collections.unmodifiableSet(set);
            }

            public int getPosition()
            {
/* 158*/        return position;
            }

            public void setPosition(int i)
            {
/* 168*/        position = i;
            }

            public Class getInjecteeClass()
            {
/* 176*/        return pClass;
            }

            public AnnotatedElement getParent()
            {
/* 184*/        return parent;
            }

            public void setParent(AnnotatedElement annotatedelement)
            {
/* 194*/        parent = annotatedelement;
/* 196*/        if(annotatedelement instanceof Field)
                {
/* 197*/            pClass = ((Field)annotatedelement).getDeclaringClass();
/* 197*/            return;
                }
/* 199*/        if(annotatedelement instanceof Constructor)
                {
/* 200*/            pClass = ((Constructor)annotatedelement).getDeclaringClass();
/* 200*/            return;
                }
/* 202*/        if(annotatedelement instanceof Method)
/* 203*/            pClass = ((Method)annotatedelement).getDeclaringClass();
            }

            public boolean isOptional()
            {
/* 212*/        return isOptional;
            }

            public void setOptional(boolean flag)
            {
/* 221*/        isOptional = flag;
            }

            public boolean isSelf()
            {
/* 229*/        return isSelf;
            }

            public void setSelf(boolean flag)
            {
/* 238*/        isSelf = flag;
            }

            public Unqualified getUnqualified()
            {
/* 246*/        return unqualified;
            }

            public void setUnqualified(Unqualified unqualified1)
            {
/* 255*/        unqualified = unqualified1;
            }

            public ActiveDescriptor getInjecteeDescriptor()
            {
/* 260*/        return injecteeDescriptor;
            }

            public void setInjecteeDescriptor(ActiveDescriptor activedescriptor)
            {
/* 269*/        injecteeDescriptor = activedescriptor;
            }

            public String toString()
            {
/* 273*/        return (new StringBuilder("InjecteeImpl(requiredType=")).append(Pretty.type(requiredType)).append(",parent=").append(Pretty.clazz(pClass)).append(",qualifiers=").append(Pretty.collection(qualifiers)).append(",position=").append(position).append(",optional=").append(isOptional).append(",self=").append(isSelf).append(",unqualified=").append(unqualified).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private Type requiredType;
            private Set qualifiers;
            private int position;
            private Class pClass;
            private AnnotatedElement parent;
            private boolean isOptional;
            private boolean isSelf;
            private Unqualified unqualified;
            private ActiveDescriptor injecteeDescriptor;
}
