// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            AnnotationVisitor, TypePath, Attribute, FieldVisitor, 
//            MethodVisitor

public abstract class ClassVisitor
{

    public ClassVisitor(int i)
    {
        this(i, null);
    }

    public ClassVisitor(int i, ClassVisitor classvisitor)
    {
        if(i != 0x40000 && i != 0x50000)
        {
            throw new IllegalArgumentException();
        } else
        {
            api = i;
            cv = classvisitor;
            return;
        }
    }

    public void visit(int i, int j, String s, String s1, String s2, String as[])
    {
        if(cv != null)
            cv.visit(i, j, s, s1, s2, as);
    }

    public void visitSource(String s, String s1)
    {
        if(cv != null)
            cv.visitSource(s, s1);
    }

    public void visitOuterClass(String s, String s1, String s2)
    {
        if(cv != null)
            cv.visitOuterClass(s, s1, s2);
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        if(cv != null)
            return cv.visitAnnotation(s, flag);
        else
            return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typepath, String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(cv != null)
            return cv.visitTypeAnnotation(i, typepath, s, flag);
        else
            return null;
    }

    public void visitAttribute(Attribute attribute)
    {
        if(cv != null)
            cv.visitAttribute(attribute);
    }

    public void visitInnerClass(String s, String s1, String s2, int i)
    {
        if(cv != null)
            cv.visitInnerClass(s, s1, s2, i);
    }

    public FieldVisitor visitField(int i, String s, String s1, String s2, Object obj)
    {
        if(cv != null)
            return cv.visitField(i, s, s1, s2, obj);
        else
            return null;
    }

    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String as[])
    {
        if(cv != null)
            return cv.visitMethod(i, s, s1, s2, as);
        else
            return null;
    }

    public void visitEnd()
    {
        if(cv != null)
            cv.visitEnd();
    }

    protected final int api;
    protected ClassVisitor cv;
}
