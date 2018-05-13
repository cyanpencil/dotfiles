// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AccessorMaker.java

package javassist.compiler;

import java.util.HashMap;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.compiler:
//            CompileError

public class AccessorMaker
{

            public AccessorMaker(CtClass ctclass)
            {
/*  35*/        clazz = ctclass;
/*  36*/        uniqueNumber = 1;
/*  37*/        accessors = new HashMap();
            }

            public String getConstructor(CtClass ctclass, String s, MethodInfo methodinfo)
                throws CompileError
            {
/*  43*/        ctclass = (new StringBuilder("<init>:")).append(s).toString();
                String s1;
/*  44*/        if((s1 = (String)accessors.get(ctclass)) != null)
/*  46*/            return s1;
/*  48*/        s1 = Descriptor.appendParameter("javassist.runtime.Inner", s);
/*  49*/        ClassFile classfile = clazz.getClassFile();
/*  51*/        try
                {
/*  51*/            Object obj = classfile.getConstPool();
/*  52*/            ClassPool classpool = clazz.getClassPool();
                    MethodInfo methodinfo1;
/*  53*/            (methodinfo1 = new MethodInfo(((ConstPool) (obj)), "<init>", s1)).setAccessFlags(0);
/*  56*/            methodinfo1.addAttribute(new SyntheticAttribute(((ConstPool) (obj))));
/*  57*/            if((methodinfo = methodinfo.getExceptionsAttribute()) != null)
/*  59*/                methodinfo1.addAttribute(methodinfo.copy(((ConstPool) (obj)), null));
/*  61*/            methodinfo = Descriptor.getParameterTypes(s, classpool);
/*  62*/            ((Bytecode) (obj = new Bytecode(((ConstPool) (obj))))).addAload(0);
/*  64*/            int i = 1;
/*  65*/            for(int j = 0; j < methodinfo.length; j++)
/*  66*/                i += ((Bytecode) (obj)).addLoad(i, methodinfo[j]);

/*  67*/            ((Bytecode) (obj)).setMaxLocals(i + 1);
/*  68*/            ((Bytecode) (obj)).addInvokespecial(clazz, "<init>", s);
/*  70*/            ((Bytecode) (obj)).addReturn(null);
/*  71*/            methodinfo1.setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/*  72*/            classfile.addMethod(methodinfo1);
                }
/*  74*/        catch(CannotCompileException cannotcompileexception)
                {
/*  75*/            throw new CompileError(cannotcompileexception);
                }
/*  77*/        catch(NotFoundException notfoundexception)
                {
/*  78*/            throw new CompileError(notfoundexception);
                }
/*  81*/        accessors.put(ctclass, s1);
/*  82*/        return s1;
            }

            public String getMethodAccessor(String s, String s1, String s2, MethodInfo methodinfo)
                throws CompileError
            {
/* 102*/        String s3 = (new StringBuilder()).append(s).append(":").append(s1).toString();
                String s4;
/* 103*/        if((s4 = (String)accessors.get(s3)) != null)
/* 105*/            return s4;
/* 107*/        ClassFile classfile = clazz.getClassFile();
/* 108*/        s4 = findAccessorName(classfile);
/* 110*/        try
                {
/* 110*/            Object obj = classfile.getConstPool();
/* 111*/            ClassPool classpool = clazz.getClassPool();
                    MethodInfo methodinfo1;
/* 112*/            (methodinfo1 = new MethodInfo(((ConstPool) (obj)), s4, s2)).setAccessFlags(8);
/* 115*/            methodinfo1.addAttribute(new SyntheticAttribute(((ConstPool) (obj))));
/* 116*/            if((methodinfo = methodinfo.getExceptionsAttribute()) != null)
/* 118*/                methodinfo1.addAttribute(methodinfo.copy(((ConstPool) (obj)), null));
/* 120*/            methodinfo = Descriptor.getParameterTypes(s2, classpool);
/* 121*/            int i = 0;
/* 122*/            obj = new Bytecode(((ConstPool) (obj)));
/* 123*/            for(int j = 0; j < methodinfo.length; j++)
/* 124*/                i += ((Bytecode) (obj)).addLoad(i, methodinfo[j]);

/* 126*/            ((Bytecode) (obj)).setMaxLocals(i);
/* 127*/            if(s1 == s2)
/* 128*/                ((Bytecode) (obj)).addInvokestatic(clazz, s, s1);
/* 130*/            else
/* 130*/                ((Bytecode) (obj)).addInvokevirtual(clazz, s, s1);
/* 132*/            ((Bytecode) (obj)).addReturn(Descriptor.getReturnType(s1, classpool));
/* 133*/            methodinfo1.setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/* 134*/            classfile.addMethod(methodinfo1);
                }
/* 136*/        catch(CannotCompileException cannotcompileexception)
                {
/* 137*/            throw new CompileError(cannotcompileexception);
                }
/* 139*/        catch(NotFoundException notfoundexception)
                {
/* 140*/            throw new CompileError(notfoundexception);
                }
/* 143*/        accessors.put(s3, s4);
/* 144*/        return s4;
            }

            public MethodInfo getFieldGetter(FieldInfo fieldinfo, boolean flag)
                throws CompileError
            {
                String s;
                String s1;
                Object obj;
                Object obj1;
/* 153*/        s = fieldinfo.getName();
/* 154*/        s1 = (new StringBuilder()).append(s).append(":getter").toString();
/* 155*/        if((obj = accessors.get(s1)) != null)
/* 157*/            return (MethodInfo)obj;
/* 159*/        obj = clazz.getClassFile();
/* 160*/        obj1 = findAccessorName(((ClassFile) (obj)));
/* 162*/        Object obj2 = ((ClassFile) (obj)).getConstPool();
/* 163*/        ClassPool classpool = clazz.getClassPool();
/* 164*/        fieldinfo = fieldinfo.getDescriptor();
                String s2;
/* 166*/        if(flag)
/* 167*/            s2 = (new StringBuilder("()")).append(fieldinfo).toString();
/* 169*/        else
/* 169*/            s2 = (new StringBuilder("(")).append(Descriptor.of(clazz)).append(")").append(fieldinfo).toString();
/* 171*/        ((MethodInfo) (obj1 = new MethodInfo(((ConstPool) (obj2)), ((String) (obj1)), s2))).setAccessFlags(8);
/* 173*/        ((MethodInfo) (obj1)).addAttribute(new SyntheticAttribute(((ConstPool) (obj2))));
/* 174*/        obj2 = new Bytecode(((ConstPool) (obj2)));
/* 175*/        if(flag)
                {
/* 176*/            ((Bytecode) (obj2)).addGetstatic(Bytecode.THIS, s, fieldinfo);
                } else
                {
/* 179*/            ((Bytecode) (obj2)).addAload(0);
/* 180*/            ((Bytecode) (obj2)).addGetfield(Bytecode.THIS, s, fieldinfo);
/* 181*/            ((Bytecode) (obj2)).setMaxLocals(1);
                }
/* 184*/        ((Bytecode) (obj2)).addReturn(Descriptor.toCtClass(fieldinfo, classpool));
/* 185*/        ((MethodInfo) (obj1)).setCodeAttribute(((Bytecode) (obj2)).toCodeAttribute());
/* 186*/        ((ClassFile) (obj)).addMethod(((MethodInfo) (obj1)));
/* 187*/        accessors.put(s1, obj1);
/* 188*/        return ((MethodInfo) (obj1));
                Object obj3;
/* 190*/        obj3;
/* 191*/        throw new CompileError(((CannotCompileException) (obj3)));
/* 193*/        obj3;
/* 194*/        throw new CompileError(((NotFoundException) (obj3)));
            }

            public MethodInfo getFieldSetter(FieldInfo fieldinfo, boolean flag)
                throws CompileError
            {
                String s;
                String s1;
                Object obj;
                Object obj1;
/* 204*/        s = fieldinfo.getName();
/* 205*/        s1 = (new StringBuilder()).append(s).append(":setter").toString();
/* 206*/        if((obj = accessors.get(s1)) != null)
/* 208*/            return (MethodInfo)obj;
/* 210*/        obj = clazz.getClassFile();
/* 211*/        obj1 = findAccessorName(((ClassFile) (obj)));
/* 213*/        Object obj2 = ((ClassFile) (obj)).getConstPool();
/* 214*/        ClassPool classpool = clazz.getClassPool();
/* 215*/        fieldinfo = fieldinfo.getDescriptor();
                String s2;
/* 217*/        if(flag)
/* 218*/            s2 = (new StringBuilder("(")).append(fieldinfo).append(")V").toString();
/* 220*/        else
/* 220*/            s2 = (new StringBuilder("(")).append(Descriptor.of(clazz)).append(fieldinfo).append(")V").toString();
/* 222*/        ((MethodInfo) (obj1 = new MethodInfo(((ConstPool) (obj2)), ((String) (obj1)), s2))).setAccessFlags(8);
/* 224*/        ((MethodInfo) (obj1)).addAttribute(new SyntheticAttribute(((ConstPool) (obj2))));
/* 225*/        obj2 = new Bytecode(((ConstPool) (obj2)));
/* 227*/        if(flag)
                {
/* 228*/            flag = ((Bytecode) (obj2)).addLoad(0, Descriptor.toCtClass(fieldinfo, classpool));
/* 229*/            ((Bytecode) (obj2)).addPutstatic(Bytecode.THIS, s, fieldinfo);
                } else
                {
/* 232*/            ((Bytecode) (obj2)).addAload(0);
/* 233*/            flag = ((Bytecode) (obj2)).addLoad(1, Descriptor.toCtClass(fieldinfo, classpool)) + 1;
/* 235*/            ((Bytecode) (obj2)).addPutfield(Bytecode.THIS, s, fieldinfo);
                }
/* 238*/        ((Bytecode) (obj2)).addReturn(null);
/* 239*/        ((Bytecode) (obj2)).setMaxLocals(flag);
/* 240*/        ((MethodInfo) (obj1)).setCodeAttribute(((Bytecode) (obj2)).toCodeAttribute());
/* 241*/        ((ClassFile) (obj)).addMethod(((MethodInfo) (obj1)));
/* 242*/        accessors.put(s1, obj1);
/* 243*/        return ((MethodInfo) (obj1));
                Object obj3;
/* 245*/        obj3;
/* 246*/        throw new CompileError(((CannotCompileException) (obj3)));
/* 248*/        obj3;
/* 249*/        throw new CompileError(((NotFoundException) (obj3)));
            }

            private String findAccessorName(ClassFile classfile)
            {
                String s;
/* 256*/        do
/* 256*/            s = (new StringBuilder("access$")).append(uniqueNumber++).toString();
/* 257*/        while(classfile.getMethod(s) != null);
/* 258*/        return s;
            }

            private CtClass clazz;
            private int uniqueNumber;
            private HashMap accessors;
            static final String lastParamType = "javassist.runtime.Inner";
}
