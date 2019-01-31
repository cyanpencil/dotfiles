// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            AnnotationVisitor, TypePath, Attribute

public abstract class FieldVisitor
{

    public FieldVisitor(int i)
    {
        this(i, null);
    }

    public FieldVisitor(int i, FieldVisitor fieldvisitor)
    {
        if(i != 0x40000 && i != 0x50000)
        {
            throw new IllegalArgumentException();
        } else
        {
            api = i;
            fv = fieldvisitor;
            return;
        }
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        if(fv != null)
            return fv.visitAnnotation(s, flag);
        else
            return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typepath, String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(fv != null)
            return fv.visitTypeAnnotation(i, typepath, s, flag);
        else
            return null;
    }

    public void visitAttribute(Attribute attribute)
    {
        if(fv != null)
            fv.visitAttribute(attribute);
    }

    public void visitEnd()
    {
        if(fv != null)
            fv.visitEnd();
    }

    protected final int api;
    protected FieldVisitor fv;
}
