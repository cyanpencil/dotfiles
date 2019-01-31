// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, Annotation, AnnotationImpl, AnnotationsWriter, 
//            MemberValueVisitor

public class AnnotationMemberValue extends MemberValue
{

            public AnnotationMemberValue(ConstPool constpool)
            {
/*  36*/        this(null, constpool);
            }

            public AnnotationMemberValue(Annotation annotation, ConstPool constpool)
            {
/*  44*/        super('@', constpool);
/*  45*/        value = annotation;
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
                throws ClassNotFoundException
            {
/*  51*/        return AnnotationImpl.make(classloader, getType(classloader), classpool, value);
            }

            Class getType(ClassLoader classloader)
                throws ClassNotFoundException
            {
/*  55*/        if(value == null)
/*  56*/            throw new ClassNotFoundException("no type specified");
/*  58*/        else
/*  58*/            return loadClass(classloader, value.getTypeName());
            }

            public Annotation getValue()
            {
/*  65*/        return value;
            }

            public void setValue(Annotation annotation)
            {
/*  72*/        value = annotation;
            }

            public String toString()
            {
/*  79*/        return value.toString();
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  86*/        annotationswriter.annotationValue();
/*  87*/        value.write(annotationswriter);
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/*  94*/        membervaluevisitor.visitAnnotationMemberValue(this);
            }

            Annotation value;
}
