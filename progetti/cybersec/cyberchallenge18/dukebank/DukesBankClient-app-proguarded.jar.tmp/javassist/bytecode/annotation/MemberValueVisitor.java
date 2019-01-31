// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberValueVisitor.java

package javassist.bytecode.annotation;


// Referenced classes of package javassist.bytecode.annotation:
//            AnnotationMemberValue, ArrayMemberValue, BooleanMemberValue, ByteMemberValue, 
//            CharMemberValue, DoubleMemberValue, EnumMemberValue, FloatMemberValue, 
//            IntegerMemberValue, LongMemberValue, ShortMemberValue, StringMemberValue, 
//            ClassMemberValue

public interface MemberValueVisitor
{

    public abstract void visitAnnotationMemberValue(AnnotationMemberValue annotationmembervalue);

    public abstract void visitArrayMemberValue(ArrayMemberValue arraymembervalue);

    public abstract void visitBooleanMemberValue(BooleanMemberValue booleanmembervalue);

    public abstract void visitByteMemberValue(ByteMemberValue bytemembervalue);

    public abstract void visitCharMemberValue(CharMemberValue charmembervalue);

    public abstract void visitDoubleMemberValue(DoubleMemberValue doublemembervalue);

    public abstract void visitEnumMemberValue(EnumMemberValue enummembervalue);

    public abstract void visitFloatMemberValue(FloatMemberValue floatmembervalue);

    public abstract void visitIntegerMemberValue(IntegerMemberValue integermembervalue);

    public abstract void visitLongMemberValue(LongMemberValue longmembervalue);

    public abstract void visitShortMemberValue(ShortMemberValue shortmembervalue);

    public abstract void visitStringMemberValue(StringMemberValue stringmembervalue);

    public abstract void visitClassMemberValue(ClassMemberValue classmembervalue);
}
