// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtBehavior.java

package javassist;

import java.util.List;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.LineNumberAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.LocalVariableTypeAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.expr.ExprEditor;

// Referenced classes of package javassist:
//            CtMember, CannotCompileException, ClassMap, ClassPool, 
//            CodeConverter, CtClass, CtClassType, CtField, 
//            CtPrimitiveType, Modifier, NotFoundException

public abstract class CtBehavior extends CtMember
{

            protected CtBehavior(CtClass ctclass, MethodInfo methodinfo)
            {
/*  39*/        super(ctclass);
/*  40*/        methodInfo = methodinfo;
            }

            void copy(CtBehavior ctbehavior, boolean flag, ClassMap classmap)
                throws CannotCompileException
            {
/*  49*/        CtClass ctclass = declaringClass;
/*  50*/        MethodInfo methodinfo = ctbehavior.methodInfo;
/*  51*/        ctbehavior = ctbehavior.getDeclaringClass();
/*  52*/        ConstPool constpool = ctclass.getClassFile2().getConstPool();
/*  54*/        (classmap = new ClassMap(classmap)).put(ctbehavior.getName(), ctclass.getName());
/*  57*/        try
                {
/*  57*/            boolean flag1 = false;
/*  58*/            ctbehavior = ctbehavior.getSuperclass();
/*  59*/            ctclass = ctclass.getSuperclass();
/*  60*/            String s = null;
/*  61*/            if(ctbehavior != null && ctclass != null)
                    {
/*  62*/                ctbehavior = ctbehavior.getName();
/*  63*/                s = ctclass.getName();
/*  64*/                if(!ctbehavior.equals(s))
/*  65*/                    if(ctbehavior.equals("java.lang.Object"))
/*  66*/                        flag1 = true;
/*  68*/                    else
/*  68*/                        classmap.putIfNone(ctbehavior, s);
                    }
/*  72*/            methodInfo = new MethodInfo(constpool, methodinfo.getName(), methodinfo, classmap);
/*  73*/            if(flag && flag1)
/*  74*/                methodInfo.setSuperclass(s);
/*  81*/            return;
                }
/*  76*/        catch(NotFoundException notfoundexception)
                {
/*  77*/            throw new CannotCompileException(notfoundexception);
                }
/*  79*/        catch(BadBytecode badbytecode)
                {
/*  80*/            throw new CannotCompileException(badbytecode);
                }
            }

            protected void extendToString(StringBuffer stringbuffer)
            {
/*  85*/        stringbuffer.append(' ');
/*  86*/        stringbuffer.append(getName());
/*  87*/        stringbuffer.append(' ');
/*  88*/        stringbuffer.append(methodInfo.getDescriptor());
            }

            public abstract String getLongName();

            public MethodInfo getMethodInfo()
            {
/* 111*/        declaringClass.checkModify();
/* 112*/        return methodInfo;
            }

            public MethodInfo getMethodInfo2()
            {
/* 134*/        return methodInfo;
            }

            public int getModifiers()
            {
/* 144*/        return AccessFlag.toModifier(methodInfo.getAccessFlags());
            }

            public void setModifiers(int i)
            {
/* 157*/        declaringClass.checkModify();
/* 158*/        methodInfo.setAccessFlags(AccessFlag.of(i));
            }

            public boolean hasAnnotation(Class class1)
            {
                Object obj;
/* 170*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((MethodInfo) (obj = getMethodInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 173*/        obj = (AnnotationsAttribute)((MethodInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 175*/        return CtClassType.hasAnnotationType(class1, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public Object getAnnotation(Class class1)
                throws ClassNotFoundException
            {
                Object obj;
/* 192*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((MethodInfo) (obj = getMethodInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 195*/        obj = (AnnotationsAttribute)((MethodInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 197*/        return CtClassType.getAnnotationType(class1, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public Object[] getAnnotations()
                throws ClassNotFoundException
            {
/* 210*/        return getAnnotations(false);
            }

            public Object[] getAvailableAnnotations()
            {
/* 224*/        return getAnnotations(true);
                ClassNotFoundException classnotfoundexception;
/* 226*/        classnotfoundexception;
/* 227*/        throw new RuntimeException("Unexpected exception", classnotfoundexception);
            }

            private Object[] getAnnotations(boolean flag)
                throws ClassNotFoundException
            {
                Object obj;
/* 234*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((MethodInfo) (obj = getMethodInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 237*/        obj = (AnnotationsAttribute)((MethodInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 239*/        return CtClassType.toAnnotationType(flag, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public Object[][] getParameterAnnotations()
                throws ClassNotFoundException
            {
/* 256*/        return getParameterAnnotations(false);
            }

            public Object[][] getAvailableParameterAnnotations()
            {
/* 274*/        return getParameterAnnotations(true);
                ClassNotFoundException classnotfoundexception;
/* 276*/        classnotfoundexception;
/* 277*/        throw new RuntimeException("Unexpected exception", classnotfoundexception);
            }

            Object[][] getParameterAnnotations(boolean flag)
                throws ClassNotFoundException
            {
                MethodInfo methodinfo;
/* 284*/        ParameterAnnotationsAttribute parameterannotationsattribute = (ParameterAnnotationsAttribute)(methodinfo = getMethodInfo2()).getAttribute("RuntimeInvisibleParameterAnnotations");
/* 287*/        ParameterAnnotationsAttribute parameterannotationsattribute1 = (ParameterAnnotationsAttribute)methodinfo.getAttribute("RuntimeVisibleParameterAnnotations");
/* 289*/        return CtClassType.toAnnotationType(flag, getDeclaringClass().getClassPool(), parameterannotationsattribute, parameterannotationsattribute1, methodinfo);
            }

            public CtClass[] getParameterTypes()
                throws NotFoundException
            {
/* 298*/        return Descriptor.getParameterTypes(methodInfo.getDescriptor(), declaringClass.getClassPool());
            }

            CtClass getReturnType0()
                throws NotFoundException
            {
/* 306*/        return Descriptor.getReturnType(methodInfo.getDescriptor(), declaringClass.getClassPool());
            }

            public String getSignature()
            {
/* 328*/        return methodInfo.getDescriptor();
            }

            public String getGenericSignature()
            {
                SignatureAttribute signatureattribute;
/* 339*/        if((signatureattribute = (SignatureAttribute)methodInfo.getAttribute("Signature")) == null)
/* 341*/            return null;
/* 341*/        else
/* 341*/            return signatureattribute.getSignature();
            }

            public void setGenericSignature(String s)
            {
/* 355*/        declaringClass.checkModify();
/* 356*/        methodInfo.addAttribute(new SignatureAttribute(methodInfo.getConstPool(), s));
            }

            public CtClass[] getExceptionTypes()
                throws NotFoundException
            {
                String as[];
/* 366*/        if((as = methodInfo.getExceptionsAttribute()) == null)
/* 368*/            as = null;
/* 370*/        else
/* 370*/            as = as.getExceptions();
/* 372*/        return declaringClass.getClassPool().get(as);
            }

            public void setExceptionTypes(CtClass actclass[])
                throws NotFoundException
            {
/* 379*/        declaringClass.checkModify();
/* 380*/        if(actclass == null || actclass.length == 0)
                {
/* 381*/            methodInfo.removeExceptionsAttribute();
/* 382*/            return;
                }
/* 385*/        String as[] = new String[actclass.length];
/* 386*/        for(int i = 0; i < actclass.length; i++)
/* 387*/            as[i] = actclass[i].getName();

                ExceptionsAttribute exceptionsattribute;
/* 389*/        if((exceptionsattribute = methodInfo.getExceptionsAttribute()) == null)
                {
/* 391*/            exceptionsattribute = new ExceptionsAttribute(methodInfo.getConstPool());
/* 392*/            methodInfo.setExceptionsAttribute(exceptionsattribute);
                }
/* 395*/        exceptionsattribute.setExceptions(as);
            }

            public abstract boolean isEmpty();

            public void setBody(String s)
                throws CannotCompileException
            {
/* 412*/        setBody(s, null, null);
            }

            public void setBody(String s, String s1, String s2)
                throws CannotCompileException
            {
                CtClass ctclass;
/* 431*/        (ctclass = declaringClass).checkModify();
/* 434*/        try
                {
/* 434*/            Javac javac = new Javac(ctclass);
/* 435*/            if(s2 != null)
/* 436*/                javac.recordProceed(s1, s2);
/* 438*/            s = javac.compileBody(this, s);
/* 439*/            methodInfo.setCodeAttribute(s.toCodeAttribute());
/* 440*/            methodInfo.setAccessFlags(methodInfo.getAccessFlags() & 0xfffffbff);
/* 442*/            methodInfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
/* 443*/            declaringClass.rebuildClassFile();
/* 449*/            return;
                }
/* 445*/        catch(CompileError compileerror)
                {
/* 446*/            throw new CannotCompileException(compileerror);
                }
/* 447*/        catch(BadBytecode badbytecode)
                {
/* 448*/            throw new CannotCompileException(badbytecode);
                }
            }

            static void setBody0(CtClass ctclass, MethodInfo methodinfo, CtClass ctclass1, MethodInfo methodinfo1, ClassMap classmap)
                throws CannotCompileException
            {
/* 457*/        ctclass1.checkModify();
/* 459*/        (classmap = new ClassMap(classmap)).put(ctclass.getName(), ctclass1.getName());
/* 462*/        try
                {
/* 462*/            if((ctclass = methodinfo.getCodeAttribute()) != null)
                    {
/* 464*/                methodinfo = methodinfo1.getConstPool();
/* 465*/                ctclass = (CodeAttribute)ctclass.copy(methodinfo, classmap);
/* 466*/                methodinfo1.setCodeAttribute(ctclass);
                    }
                }
                // Misplaced declaration of an exception variable
/* 470*/        catch(CtClass ctclass)
                {
/* 473*/            throw new CannotCompileException(ctclass);
                }
/* 476*/        methodinfo1.setAccessFlags(methodinfo1.getAccessFlags() & 0xfffffbff);
/* 478*/        ctclass1.rebuildClassFile();
            }

            public byte[] getAttribute(String s)
            {
/* 493*/        if((s = methodInfo.getAttribute(s)) == null)
/* 495*/            return null;
/* 497*/        else
/* 497*/            return s.get();
            }

            public void setAttribute(String s, byte abyte0[])
            {
/* 511*/        declaringClass.checkModify();
/* 512*/        methodInfo.addAttribute(new AttributeInfo(methodInfo.getConstPool(), s, abyte0));
            }

            public void useCflow(String s)
                throws CannotCompileException
            {
                CtClass ctclass;
/* 534*/        (ctclass = declaringClass).checkModify();
/* 536*/        Object obj = ctclass.getClassPool();
/* 538*/        int i = 0;
/* 540*/        do
                {
/* 540*/            String s1 = (new StringBuilder("_cflow$")).append(i++).toString();
/* 542*/            try
                    {
/* 542*/                ctclass.getDeclaredField(s1);
                    }
/* 544*/            catch(NotFoundException _ex)
                    {
/* 549*/                ((ClassPool) (obj)).recordCflow(s, declaringClass.getName(), s1);
/* 551*/                try
                        {
/* 551*/                    s = ((ClassPool) (obj)).get("javassist.runtime.Cflow");
/* 552*/                    ((CtField) (obj = new CtField(s, s1, ctclass))).setModifiers(9);
/* 554*/                    ctclass.addField(((CtField) (obj)), CtField.Initializer.byNew(s));
/* 555*/                    insertBefore((new StringBuilder()).append(s1).append(".enter();").toString(), false);
/* 556*/                    s = (new StringBuilder()).append(s1).append(".exit();").toString();
/* 557*/                    insertAfter(s, true);
/* 561*/                    return;
                        }
                        // Misplaced declaration of an exception variable
/* 559*/                catch(String s)
                        {
/* 560*/                    throw new CannotCompileException(s);
                        }
                    }
                } while(true);
            }

            public void addLocalVariable(String s, CtClass ctclass)
                throws CannotCompileException
            {
/* 582*/        declaringClass.checkModify();
/* 583*/        ConstPool constpool = methodInfo.getConstPool();
                CodeAttribute codeattribute;
/* 584*/        if((codeattribute = methodInfo.getCodeAttribute()) == null)
/* 586*/            throw new CannotCompileException("no method body");
                LocalVariableAttribute localvariableattribute;
/* 588*/        if((localvariableattribute = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable")) == null)
                {
/* 591*/            localvariableattribute = new LocalVariableAttribute(constpool);
/* 592*/            codeattribute.getAttributes().add(localvariableattribute);
                }
/* 595*/        int i = codeattribute.getMaxLocals();
/* 596*/        ctclass = Descriptor.of(ctclass);
/* 597*/        localvariableattribute.addEntry(0, codeattribute.getCodeLength(), constpool.addUtf8Info(s), constpool.addUtf8Info(ctclass), i);
/* 599*/        codeattribute.setMaxLocals(i + Descriptor.dataSize(ctclass));
            }

            public void insertParameter(CtClass ctclass)
                throws CannotCompileException
            {
/* 608*/        declaringClass.checkModify();
/* 609*/        String s = methodInfo.getDescriptor();
/* 610*/        String s1 = Descriptor.insertParameter(ctclass, s);
/* 612*/        try
                {
/* 612*/            addParameter2(Modifier.isStatic(getModifiers()) ? 0 : 1, ctclass, s);
                }
                // Misplaced declaration of an exception variable
/* 614*/        catch(CtClass ctclass)
                {
/* 615*/            throw new CannotCompileException(ctclass);
                }
/* 618*/        methodInfo.setDescriptor(s1);
            }

            public void addParameter(CtClass ctclass)
                throws CannotCompileException
            {
/* 627*/        declaringClass.checkModify();
/* 628*/        String s = methodInfo.getDescriptor();
/* 629*/        String s1 = Descriptor.appendParameter(ctclass, s);
/* 630*/        int i = Modifier.isStatic(getModifiers()) ? 0 : 1;
/* 632*/        try
                {
/* 632*/            addParameter2(i + Descriptor.paramSize(s), ctclass, s);
                }
                // Misplaced declaration of an exception variable
/* 634*/        catch(CtClass ctclass)
                {
/* 635*/            throw new CannotCompileException(ctclass);
                }
/* 638*/        methodInfo.setDescriptor(s1);
            }

            private void addParameter2(int i, CtClass ctclass, String s)
                throws BadBytecode
            {
/* 644*/        if((s = methodInfo.getCodeAttribute()) != null)
                {
/* 646*/            int j = 1;
/* 647*/            char c = 'L';
/* 648*/            int k = 0;
/* 649*/            if(ctclass.isPrimitive())
                    {
/* 650*/                j = (ctclass = (CtPrimitiveType)ctclass).getDataSize();
/* 652*/                c = ctclass.getDescriptor();
                    } else
                    {
/* 655*/                k = methodInfo.getConstPool().addClassInfo(ctclass);
                    }
/* 657*/            s.insertLocalVar(i, j);
/* 658*/            if((ctclass = (LocalVariableAttribute)s.getAttribute("LocalVariableTable")) != null)
/* 661*/                ctclass.shiftIndex(i, j);
/* 663*/            if((ctclass = (LocalVariableTypeAttribute)s.getAttribute("LocalVariableTypeTable")) != null)
/* 666*/                ctclass.shiftIndex(i, j);
/* 668*/            if((ctclass = (StackMapTable)s.getAttribute("StackMapTable")) != null)
/* 670*/                ctclass.insertLocal(i, StackMapTable.typeTagOf(c), k);
/* 672*/            if((ctclass = (StackMap)s.getAttribute("StackMap")) != null)
/* 674*/                ctclass.insertLocal(i, StackMapTable.typeTagOf(c), k);
                }
            }

            public void instrument(CodeConverter codeconverter)
                throws CannotCompileException
            {
/* 686*/        declaringClass.checkModify();
/* 687*/        ConstPool constpool = methodInfo.getConstPool();
/* 688*/        codeconverter.doit(getDeclaringClass(), methodInfo, constpool);
            }

            public void instrument(ExprEditor expreditor)
                throws CannotCompileException
            {
/* 709*/        if(declaringClass.isFrozen())
/* 710*/            declaringClass.checkModify();
/* 712*/        if(expreditor.doit(declaringClass, methodInfo))
/* 713*/            declaringClass.checkModify();
            }

            public void insertBefore(String s)
                throws CannotCompileException
            {
/* 734*/        insertBefore(s, true);
            }

            private void insertBefore(String s, boolean flag)
                throws CannotCompileException
            {
                CtClass ctclass;
/* 740*/        (ctclass = declaringClass).checkModify();
                CodeAttribute codeattribute;
/* 742*/        if((codeattribute = methodInfo.getCodeAttribute()) == null)
/* 744*/            throw new CannotCompileException("no method body");
/* 746*/        CodeIterator codeiterator = codeattribute.iterator();
/* 747*/        Javac javac = new Javac(ctclass);
/* 749*/        try
                {
/* 749*/            int k = javac.recordParams(getParameterTypes(), Modifier.isStatic(getModifiers()));
/* 751*/            javac.recordParamNames(codeattribute, k);
/* 752*/            javac.recordLocalVariables(codeattribute, 0);
/* 753*/            javac.recordType(getReturnType0());
/* 754*/            javac.compileStmnt(s);
/* 755*/            int j = (s = javac.getBytecode()).getMaxStack();
/* 757*/            k = s.getMaxLocals();
/* 759*/            if(j > codeattribute.getMaxStack())
/* 760*/                codeattribute.setMaxStack(j);
/* 762*/            if(k > codeattribute.getMaxLocals())
/* 763*/                codeattribute.setMaxLocals(k);
/* 765*/            int i = codeiterator.insertEx(s.get());
/* 766*/            codeiterator.insert(s.getExceptionTable(), i);
/* 767*/            if(flag)
/* 768*/                methodInfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
/* 778*/            return;
                }
/* 770*/        catch(NotFoundException notfoundexception)
                {
/* 771*/            throw new CannotCompileException(notfoundexception);
                }
/* 773*/        catch(CompileError compileerror)
                {
/* 774*/            throw new CannotCompileException(compileerror);
                }
/* 776*/        catch(BadBytecode badbytecode)
                {
/* 777*/            throw new CannotCompileException(badbytecode);
                }
            }

            public void insertAfter(String s)
                throws CannotCompileException
            {
/* 792*/        insertAfter(s, false);
            }

            public void insertAfter(String s, boolean flag)
                throws CannotCompileException
            {
                CtClass ctclass;
/* 810*/        (ctclass = declaringClass).checkModify();
/* 812*/        ConstPool constpool = methodInfo.getConstPool();
                CodeAttribute codeattribute;
/* 813*/        if((codeattribute = methodInfo.getCodeAttribute()) == null)
/* 815*/            throw new CannotCompileException("no method body");
/* 817*/        CodeIterator codeiterator = codeattribute.iterator();
/* 818*/        int i = codeattribute.getMaxLocals();
                Bytecode bytecode;
/* 819*/        (bytecode = new Bytecode(constpool, 0, i + 1)).setStackDepth(codeattribute.getMaxStack() + 1);
/* 821*/        Javac javac = new Javac(bytecode, ctclass);
/* 823*/        try
                {
/* 823*/            int j = javac.recordParams(getParameterTypes(), Modifier.isStatic(getModifiers()));
/* 825*/            javac.recordParamNames(codeattribute, j);
/* 826*/            CtClass ctclass1 = getReturnType0();
/* 827*/            int k = javac.recordReturnType(ctclass1, true);
/* 828*/            javac.recordLocalVariables(codeattribute, 0);
/* 831*/            int l = insertAfterHandler(flag, bytecode, ctclass1, k, javac, s);
/* 833*/            int i1 = codeiterator.getCodeLength();
/* 834*/            if(flag)
/* 835*/                codeattribute.getExceptionTable().add(getStartPosOfBody(codeattribute), i1, i1, 0);
/* 837*/            flag = false;
/* 838*/            int k1 = 0;
/* 839*/            boolean flag1 = true;
/* 840*/            do
                    {
                        int l1;
/* 840*/                if(!codeiterator.hasNext() || (l1 = codeiterator.next()) >= i1)
/* 845*/                    break;
                        int i2;
/* 845*/                if((i2 = codeiterator.byteAt(l1)) == 176 || i2 == 172 || i2 == 174 || i2 == 173 || i2 == 175 || i2 == 177)
                        {
/* 849*/                    if(flag1)
                            {
/* 851*/                        flag = insertAfterAdvice(bytecode, javac, s, constpool, ctclass1, k);
/* 852*/                        i1 = codeiterator.append(bytecode.get());
/* 853*/                        codeiterator.append(bytecode.getExceptionTable(), i1);
/* 854*/                        l = (k1 = codeiterator.getCodeLength() - flag) - i1;
/* 856*/                        flag1 = false;
                            }
/* 858*/                    insertGoto(codeiterator, k1, l1);
/* 859*/                    i1 = (k1 = codeiterator.getCodeLength() - flag) - l;
                        }
                    } while(true);
/* 864*/            if(flag1)
                    {
/* 865*/                int j1 = codeiterator.append(bytecode.get());
/* 866*/                codeiterator.append(bytecode.getExceptionTable(), j1);
                    }
/* 869*/            codeattribute.setMaxStack(bytecode.getMaxStack());
/* 870*/            codeattribute.setMaxLocals(bytecode.getMaxLocals());
/* 871*/            methodInfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
/* 881*/            return;
                }
/* 873*/        catch(NotFoundException notfoundexception)
                {
/* 874*/            throw new CannotCompileException(notfoundexception);
                }
/* 876*/        catch(CompileError compileerror)
                {
/* 877*/            throw new CannotCompileException(compileerror);
                }
/* 879*/        catch(BadBytecode badbytecode)
                {
/* 880*/            throw new CannotCompileException(badbytecode);
                }
            }

            private int insertAfterAdvice(Bytecode bytecode, Javac javac, String s, ConstPool constpool, CtClass ctclass, int i)
                throws CompileError
            {
/* 888*/        constpool = bytecode.currentPc();
/* 889*/        if(ctclass == CtClass.voidType)
                {
/* 890*/            bytecode.addOpcode(1);
/* 891*/            bytecode.addAstore(i);
/* 892*/            javac.compileStmnt(s);
/* 893*/            bytecode.addOpcode(177);
/* 894*/            if(bytecode.getMaxLocals() <= 0)
/* 895*/                bytecode.setMaxLocals(1);
                } else
                {
/* 898*/            bytecode.addStore(i, ctclass);
/* 899*/            javac.compileStmnt(s);
/* 900*/            bytecode.addLoad(i, ctclass);
/* 901*/            if(ctclass.isPrimitive())
/* 902*/                bytecode.addOpcode(((CtPrimitiveType)ctclass).getReturnOp());
/* 904*/            else
/* 904*/                bytecode.addOpcode(176);
                }
/* 907*/        return bytecode.currentPc() - constpool;
            }

            private void insertGoto(CodeIterator codeiterator, int i, int j)
                throws BadBytecode
            {
/* 916*/        codeiterator.setMark(i);
/* 918*/        codeiterator.writeByte(0, j);
/* 919*/        i = (i + 2) - j <= 32767 ? 0 : 1;
/* 920*/        j = codeiterator.insertGapAt(j, i == 0 ? 2 : 4, false).position;
/* 921*/        int k = codeiterator.getMark() - j;
/* 922*/        if(i != 0)
                {
/* 923*/            codeiterator.writeByte(200, j);
/* 924*/            codeiterator.write32bit(k, j + 1);
/* 924*/            return;
                }
/* 926*/        if(k <= 32767)
                {
/* 927*/            codeiterator.writeByte(167, j);
/* 928*/            codeiterator.write16bit(k, j + 1);
/* 928*/            return;
                } else
                {
/* 931*/            j = codeiterator.insertGapAt(j, 2, false).position;
/* 932*/            codeiterator.writeByte(200, j);
/* 933*/            codeiterator.write32bit(codeiterator.getMark() - j, j + 1);
/* 935*/            return;
                }
            }

            private int insertAfterHandler(boolean flag, Bytecode bytecode, CtClass ctclass, int i, Javac javac, String s)
                throws CompileError
            {
/* 944*/        if(!flag)
/* 945*/            return 0;
/* 947*/        flag = bytecode.getMaxLocals();
/* 948*/        bytecode.incMaxLocals(1);
/* 949*/        int j = bytecode.currentPc();
/* 950*/        bytecode.addAstore(flag);
/* 951*/        if(ctclass.isPrimitive())
                {
/* 952*/            if((ctclass = ((CtPrimitiveType)ctclass).getDescriptor()) == 'D')
                    {
/* 954*/                bytecode.addDconst(0.0D);
/* 955*/                bytecode.addDstore(i);
                    } else
/* 957*/            if(ctclass == 70)
                    {
/* 958*/                bytecode.addFconst(0.0F);
/* 959*/                bytecode.addFstore(i);
                    } else
/* 961*/            if(ctclass == 74)
                    {
/* 962*/                bytecode.addLconst(0L);
/* 963*/                bytecode.addLstore(i);
                    } else
/* 965*/            if(ctclass == 86)
                    {
/* 966*/                bytecode.addOpcode(1);
/* 967*/                bytecode.addAstore(i);
                    } else
                    {
/* 970*/                bytecode.addIconst(0);
/* 971*/                bytecode.addIstore(i);
                    }
                } else
                {
/* 975*/            bytecode.addOpcode(1);
/* 976*/            bytecode.addAstore(i);
                }
/* 979*/        javac.compileStmnt(s);
/* 980*/        bytecode.addAload(flag);
/* 981*/        bytecode.addOpcode(191);
/* 982*/        return bytecode.currentPc() - j;
            }

            public void addCatch(String s, CtClass ctclass)
                throws CannotCompileException
            {
/*1048*/        addCatch(s, ctclass, "$e");
            }

            public void addCatch(String s, CtClass ctclass, String s1)
                throws CannotCompileException
            {
                CtClass ctclass1;
/*1067*/        (ctclass1 = declaringClass).checkModify();
/*1069*/        ConstPool constpool = methodInfo.getConstPool();
                CodeAttribute codeattribute;
/*1070*/        CodeIterator codeiterator = (codeattribute = methodInfo.getCodeAttribute()).iterator();
                Bytecode bytecode;
/*1072*/        (bytecode = new Bytecode(constpool, codeattribute.getMaxStack(), codeattribute.getMaxLocals())).setStackDepth(1);
/*1074*/        Javac javac = new Javac(bytecode, ctclass1);
/*1076*/        try
                {
/*1076*/            javac.recordParams(getParameterTypes(), Modifier.isStatic(getModifiers()));
/*1078*/            s1 = javac.recordVariable(ctclass, s1);
/*1079*/            bytecode.addAstore(s1);
/*1080*/            javac.compileStmnt(s);
/*1082*/            s = bytecode.getMaxStack();
/*1083*/            s1 = bytecode.getMaxLocals();
/*1085*/            if(s > codeattribute.getMaxStack())
/*1086*/                codeattribute.setMaxStack(s);
/*1088*/            if(s1 > codeattribute.getMaxLocals())
/*1089*/                codeattribute.setMaxLocals(s1);
/*1091*/            s = codeiterator.getCodeLength();
/*1092*/            s1 = codeiterator.append(bytecode.get());
/*1093*/            codeattribute.getExceptionTable().add(getStartPosOfBody(codeattribute), s, s, constpool.addClassInfo(ctclass));
/*1095*/            codeiterator.append(bytecode.getExceptionTable(), s1);
/*1096*/            methodInfo.rebuildStackMapIf6(ctclass1.getClassPool(), ctclass1.getClassFile2());
/*1105*/            return;
                }
                // Misplaced declaration of an exception variable
/*1098*/        catch(String s1)
                {
/*1099*/            throw new CannotCompileException(s1);
                }
                // Misplaced declaration of an exception variable
/*1101*/        catch(String s1)
                {
/*1102*/            throw new CannotCompileException(s1);
                }
                // Misplaced declaration of an exception variable
/*1103*/        catch(String s1)
                {
/*1104*/            throw new CannotCompileException(s1);
                }
            }

            int getStartPosOfBody(CodeAttribute codeattribute)
                throws CannotCompileException
            {
/*1111*/        return 0;
            }

            public int insertAt(int i, String s)
                throws CannotCompileException
            {
/*1134*/        return insertAt(i, true, s);
            }

            public int insertAt(int i, boolean flag, String s)
                throws CannotCompileException
            {
                CodeAttribute codeattribute;
                int j;
                CodeIterator codeiterator;
                Javac javac;
/*1162*/        if((codeattribute = methodInfo.getCodeAttribute()) == null)
/*1164*/            throw new CannotCompileException("no method body");
                Object obj;
/*1166*/        if((obj = (LineNumberAttribute)codeattribute.getAttribute("LineNumberTable")) == null)
/*1169*/            throw new CannotCompileException("no line number info");
/*1171*/        i = ((javassist.bytecode.LineNumberAttribute.Pc) (obj = ((LineNumberAttribute) (obj)).toNearPc(i))).line;
/*1173*/        j = ((javassist.bytecode.LineNumberAttribute.Pc) (obj)).index;
/*1174*/        if(!flag)
/*1175*/            return i;
/*1177*/        (flag = declaringClass).checkModify();
/*1179*/        codeiterator = codeattribute.iterator();
/*1180*/        javac = new Javac(flag);
/*1182*/        javac.recordLocalVariables(codeattribute, j);
/*1183*/        javac.recordParams(getParameterTypes(), Modifier.isStatic(getModifiers()));
/*1185*/        javac.setMaxLocals(codeattribute.getMaxLocals());
/*1186*/        javac.compileStmnt(s);
/*1187*/        int k = (s = javac.getBytecode()).getMaxLocals();
/*1189*/        int l = s.getMaxStack();
/*1190*/        codeattribute.setMaxLocals(k);
/*1195*/        if(l > codeattribute.getMaxStack())
/*1196*/            codeattribute.setMaxStack(l);
/*1198*/        j = codeiterator.insertAt(j, s.get());
/*1199*/        codeiterator.insert(s.getExceptionTable(), j);
/*1200*/        methodInfo.rebuildStackMapIf6(flag.getClassPool(), flag.getClassFile2());
/*1201*/        return i;
/*1203*/        s;
/*1204*/        throw new CannotCompileException(s);
/*1206*/        s;
/*1207*/        throw new CannotCompileException(s);
/*1209*/        s;
/*1210*/        throw new CannotCompileException(s);
            }

            protected MethodInfo methodInfo;
}
