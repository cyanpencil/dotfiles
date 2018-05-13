// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtMethod.java

package javassist;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist:
//            CtBehavior, CannotCompileException, CtClass, CtNewMethod, 
//            CtNewWrappedMethod, NotFoundException, ClassMap

public final class CtMethod extends CtBehavior
{
    static class StringConstParameter extends ConstParameter
    {

                int compile(Bytecode bytecode)
                    throws CannotCompileException
                {
/* 424*/            bytecode.addLdc(param);
/* 425*/            return 1;
                }

                String descriptor()
                {
/* 429*/            return "([Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;";
                }

                String constDescriptor()
                {
/* 433*/            return "([Ljava/lang/Object;Ljava/lang/String;)V";
                }

                String param;

                StringConstParameter(String s)
                {
/* 420*/            param = s;
                }
    }

    static class LongConstParameter extends ConstParameter
    {

                int compile(Bytecode bytecode)
                    throws CannotCompileException
                {
/* 403*/            bytecode.addLconst(param);
/* 404*/            return 2;
                }

                String descriptor()
                {
/* 408*/            return "([Ljava/lang/Object;J)Ljava/lang/Object;";
                }

                String constDescriptor()
                {
/* 412*/            return "([Ljava/lang/Object;J)V";
                }

                long param;

                LongConstParameter(long l)
                {
/* 399*/            param = l;
                }
    }

    static class IntConstParameter extends ConstParameter
    {

                int compile(Bytecode bytecode)
                    throws CannotCompileException
                {
/* 382*/            bytecode.addIconst(param);
/* 383*/            return 1;
                }

                String descriptor()
                {
/* 387*/            return "([Ljava/lang/Object;I)Ljava/lang/Object;";
                }

                String constDescriptor()
                {
/* 391*/            return "([Ljava/lang/Object;I)V";
                }

                int param;

                IntConstParameter(int i)
                {
/* 378*/            param = i;
                }
    }

    public static class ConstParameter
    {

                public static ConstParameter integer(int i)
                {
/* 316*/            return new IntConstParameter(i);
                }

                public static ConstParameter integer(long l)
                {
/* 325*/            return new LongConstParameter(l);
                }

                public static ConstParameter string(String s)
                {
/* 334*/            return new StringConstParameter(s);
                }

                int compile(Bytecode bytecode)
                    throws CannotCompileException
                {
/* 343*/            return 0;
                }

                String descriptor()
                {
/* 347*/            return defaultDescriptor();
                }

                static String defaultDescriptor()
                {
/* 354*/            return "([Ljava/lang/Object;)Ljava/lang/Object;";
                }

                String constDescriptor()
                {
/* 363*/            return defaultConstDescriptor();
                }

                static String defaultConstDescriptor()
                {
/* 370*/            return "([Ljava/lang/Object;)V";
                }

                ConstParameter()
                {
                }
    }


            CtMethod(MethodInfo methodinfo, CtClass ctclass)
            {
/*  38*/        super(ctclass, methodinfo);
/*  39*/        cachedStringRep = null;
            }

            public CtMethod(CtClass ctclass, String s, CtClass actclass[], CtClass ctclass1)
            {
/*  55*/        this(null, ctclass1);
/*  56*/        ctclass1 = ctclass1.getClassFile2().getConstPool();
/*  57*/        ctclass = Descriptor.ofMethod(ctclass, actclass);
/*  58*/        methodInfo = new MethodInfo(ctclass1, s, ctclass);
/*  59*/        setModifiers(1025);
            }

            public CtMethod(CtMethod ctmethod, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
/* 113*/        this(null, ctclass);
/* 114*/        copy(ctmethod, false, classmap);
            }

            public static CtMethod make(String s, CtClass ctclass)
                throws CannotCompileException
            {
/* 130*/        return CtNewMethod.make(s, ctclass);
            }

            public static CtMethod make(MethodInfo methodinfo, CtClass ctclass)
                throws CannotCompileException
            {
/* 145*/        if(ctclass.getClassFile2().getConstPool() != methodinfo.getConstPool())
/* 146*/            throw new CannotCompileException("bad declaring class");
/* 148*/        else
/* 148*/            return new CtMethod(methodinfo, ctclass);
            }

            public final int hashCode()
            {
/* 157*/        return getStringRep().hashCode();
            }

            final void nameReplaced()
            {
/* 165*/        cachedStringRep = null;
            }

            final String getStringRep()
            {
/* 171*/        if(cachedStringRep == null)
/* 172*/            cachedStringRep = (new StringBuilder()).append(methodInfo.getName()).append(Descriptor.getParamDescriptor(methodInfo.getDescriptor())).toString();
/* 175*/        return cachedStringRep;
            }

            public final boolean equals(Object obj)
            {
/* 183*/        return obj != null && (obj instanceof CtMethod) && ((CtMethod)obj).getStringRep().equals(getStringRep());
            }

            public final String getLongName()
            {
/* 194*/        return (new StringBuilder()).append(getDeclaringClass().getName()).append(".").append(getName()).append(Descriptor.toString(getSignature())).toString();
            }

            public final String getName()
            {
/* 202*/        return methodInfo.getName();
            }

            public final void setName(String s)
            {
/* 209*/        declaringClass.checkModify();
/* 210*/        methodInfo.setName(s);
            }

            public final CtClass getReturnType()
                throws NotFoundException
            {
/* 217*/        return getReturnType0();
            }

            public final boolean isEmpty()
            {
                Object obj;
/* 225*/        if((obj = getMethodInfo2().getCodeAttribute()) == null)
/* 227*/            return (getModifiers() & 0x400) != 0;
/* 229*/        obj = ((CodeAttribute) (obj)).iterator();
/* 231*/        if(((CodeIterator) (obj)).hasNext() && ((CodeIterator) (obj)).byteAt(((CodeIterator) (obj)).next()) == 177 && !((CodeIterator) (obj)).hasNext())
/* 231*/            return true;
/* 231*/        return false;
/* 234*/        JVM INSTR pop ;
/* 235*/        return false;
            }

            public final void setBody(CtMethod ctmethod, ClassMap classmap)
                throws CannotCompileException
            {
/* 255*/        setBody0(ctmethod.declaringClass, ctmethod.methodInfo, declaringClass, methodInfo, classmap);
            }

            public final void setWrappedBody(CtMethod ctmethod, ConstParameter constparameter)
                throws CannotCompileException
            {
/* 273*/        declaringClass.checkModify();
/* 275*/        CtClass ctclass = getDeclaringClass();
                CtClass actclass[];
                CtClass ctclass1;
/* 279*/        try
                {
/* 279*/            actclass = getParameterTypes();
/* 280*/            ctclass1 = getReturnType();
                }
                // Misplaced declaration of an exception variable
/* 282*/        catch(CtMethod ctmethod)
                {
/* 283*/            throw new CannotCompileException(ctmethod);
                }
/* 286*/        ctmethod = (ctmethod = CtNewWrappedMethod.makeBody(ctclass, ctclass.getClassFile2(), ctmethod, actclass, ctclass1, constparameter)).toCodeAttribute();
/* 292*/        methodInfo.setCodeAttribute(ctmethod);
/* 293*/        methodInfo.setAccessFlags(methodInfo.getAccessFlags() & 0xfffffbff);
            }

            protected String cachedStringRep;
}
