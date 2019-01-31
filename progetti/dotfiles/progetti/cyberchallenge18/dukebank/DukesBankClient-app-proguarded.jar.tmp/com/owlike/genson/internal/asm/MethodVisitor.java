// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            AnnotationVisitor, TypePath, Attribute, Handle, 
//            Label

public abstract class MethodVisitor
{

    public MethodVisitor(int i)
    {
        this(i, null);
    }

    public MethodVisitor(int i, MethodVisitor methodvisitor)
    {
        if(i != 0x40000 && i != 0x50000)
        {
            throw new IllegalArgumentException();
        } else
        {
            api = i;
            mv = methodvisitor;
            return;
        }
    }

    public void visitParameter(String s, int i)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(mv != null)
            mv.visitParameter(s, i);
    }

    public AnnotationVisitor visitAnnotationDefault()
    {
        if(mv != null)
            return mv.visitAnnotationDefault();
        else
            return null;
    }

    public AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        if(mv != null)
            return mv.visitAnnotation(s, flag);
        else
            return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typepath, String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(mv != null)
            return mv.visitTypeAnnotation(i, typepath, s, flag);
        else
            return null;
    }

    public AnnotationVisitor visitParameterAnnotation(int i, String s, boolean flag)
    {
        if(mv != null)
            return mv.visitParameterAnnotation(i, s, flag);
        else
            return null;
    }

    public void visitAttribute(Attribute attribute)
    {
        if(mv != null)
            mv.visitAttribute(attribute);
    }

    public void visitCode()
    {
        if(mv != null)
            mv.visitCode();
    }

    public void visitFrame(int i, int j, Object aobj[], int k, Object aobj1[])
    {
        if(mv != null)
            mv.visitFrame(i, j, aobj, k, aobj1);
    }

    public void visitInsn(int i)
    {
        if(mv != null)
            mv.visitInsn(i);
    }

    public void visitIntInsn(int i, int j)
    {
        if(mv != null)
            mv.visitIntInsn(i, j);
    }

    public void visitVarInsn(int i, int j)
    {
        if(mv != null)
            mv.visitVarInsn(i, j);
    }

    public void visitTypeInsn(int i, String s)
    {
        if(mv != null)
            mv.visitTypeInsn(i, s);
    }

    public void visitFieldInsn(int i, String s, String s1, String s2)
    {
        if(mv != null)
            mv.visitFieldInsn(i, s, s1, s2);
    }

    /**
     * @deprecated Method visitMethodInsn is deprecated
     */

    public void visitMethodInsn(int i, String s, String s1, String s2)
    {
        if(api >= 0x50000)
        {
            boolean flag = i == 185;
            visitMethodInsn(i, s, s1, s2, flag);
            return;
        }
        if(mv != null)
            mv.visitMethodInsn(i, s, s1, s2);
    }

    public void visitMethodInsn(int i, String s, String s1, String s2, boolean flag)
    {
        if(api < 0x50000)
            if(flag != (i == 185))
            {
                throw new IllegalArgumentException("INVOKESPECIAL/STATIC on interfaces require ASM 5");
            } else
            {
                visitMethodInsn(i, s, s1, s2);
                return;
            }
        if(mv != null)
            mv.visitMethodInsn(i, s, s1, s2, flag);
    }

    public transient void visitInvokeDynamicInsn(String s, String s1, Handle handle, Object aobj[])
    {
        if(mv != null)
            mv.visitInvokeDynamicInsn(s, s1, handle, aobj);
    }

    public void visitJumpInsn(int i, Label label)
    {
        if(mv != null)
            mv.visitJumpInsn(i, label);
    }

    public void visitLabel(Label label)
    {
        if(mv != null)
            mv.visitLabel(label);
    }

    public void visitLdcInsn(Object obj)
    {
        if(mv != null)
            mv.visitLdcInsn(obj);
    }

    public void visitIincInsn(int i, int j)
    {
        if(mv != null)
            mv.visitIincInsn(i, j);
    }

    public transient void visitTableSwitchInsn(int i, int j, Label label, Label alabel[])
    {
        if(mv != null)
            mv.visitTableSwitchInsn(i, j, label, alabel);
    }

    public void visitLookupSwitchInsn(Label label, int ai[], Label alabel[])
    {
        if(mv != null)
            mv.visitLookupSwitchInsn(label, ai, alabel);
    }

    public void visitMultiANewArrayInsn(String s, int i)
    {
        if(mv != null)
            mv.visitMultiANewArrayInsn(s, i);
    }

    public AnnotationVisitor visitInsnAnnotation(int i, TypePath typepath, String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(mv != null)
            return mv.visitInsnAnnotation(i, typepath, s, flag);
        else
            return null;
    }

    public void visitTryCatchBlock(Label label, Label label1, Label label2, String s)
    {
        if(mv != null)
            mv.visitTryCatchBlock(label, label1, label2, s);
    }

    public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typepath, String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(mv != null)
            return mv.visitTryCatchAnnotation(i, typepath, s, flag);
        else
            return null;
    }

    public void visitLocalVariable(String s, String s1, String s2, Label label, Label label1, int i)
    {
        if(mv != null)
            mv.visitLocalVariable(s, s1, s2, label, label1, i);
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typepath, Label alabel[], Label alabel1[], int ai[], String s, boolean flag)
    {
        if(api < 0x50000)
            throw new RuntimeException();
        if(mv != null)
            return mv.visitLocalVariableAnnotation(i, typepath, alabel, alabel1, ai, s, flag);
        else
            return null;
    }

    public void visitLineNumber(int i, Label label)
    {
        if(mv != null)
            mv.visitLineNumber(i, label);
    }

    public void visitMaxs(int i, int j)
    {
        if(mv != null)
            mv.visitMaxs(i, j);
    }

    public void visitEnd()
    {
        if(mv != null)
            mv.visitEnd();
    }

    protected final int api;
    protected MethodVisitor mv;
}
