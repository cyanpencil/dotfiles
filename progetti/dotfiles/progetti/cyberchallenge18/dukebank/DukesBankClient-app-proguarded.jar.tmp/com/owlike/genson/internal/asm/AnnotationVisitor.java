// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


public abstract class AnnotationVisitor
{

    public AnnotationVisitor(int i)
    {
        this(i, null);
    }

    public AnnotationVisitor(int i, AnnotationVisitor annotationvisitor)
    {
        if(i != 0x40000 && i != 0x50000)
        {
            throw new IllegalArgumentException();
        } else
        {
            api = i;
            av = annotationvisitor;
            return;
        }
    }

    public void visit(String s, Object obj)
    {
        if(av != null)
            av.visit(s, obj);
    }

    public void visitEnum(String s, String s1, String s2)
    {
        if(av != null)
            av.visitEnum(s, s1, s2);
    }

    public AnnotationVisitor visitAnnotation(String s, String s1)
    {
        if(av != null)
            return av.visitAnnotation(s, s1);
        else
            return null;
    }

    public AnnotationVisitor visitArray(String s)
    {
        if(av != null)
            return av.visitArray(s);
        else
            return null;
    }

    public void visitEnd()
    {
        if(av != null)
            av.visitEnd();
    }

    protected final int api;
    protected AnnotationVisitor av;
}
