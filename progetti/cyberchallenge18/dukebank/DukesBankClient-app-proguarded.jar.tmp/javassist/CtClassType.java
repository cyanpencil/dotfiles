// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtClassType.java

package javassist;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.*;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ConstantAttribute;
import javassist.bytecode.Descriptor;
import javassist.bytecode.EnclosingMethodAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.InnerClassesAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.compiler.AccessorMaker;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.expr.ExprEditor;

// Referenced classes of package javassist:
//            CtClass, CannotCompileException, ClassMap, ClassPool, 
//            CodeConverter, CtBehavior, CtConstructor, CtField, 
//            CtMember, CtMethod, FieldInitLink, Modifier, 
//            NotFoundException

class CtClassType extends CtClass
{

            CtClassType(String s, ClassPool classpool)
            {
/*  81*/        super(s);
/*  76*/        doPruning = ClassPool.doPruning;
/*  82*/        classPool = classpool;
/*  83*/        wasChanged = wasFrozen = wasPruned = gcConstPool = false;
/*  84*/        classfile = null;
/*  85*/        rawClassfile = null;
/*  86*/        memberCache = null;
/*  87*/        accessors = null;
/*  88*/        fieldInitializers = null;
/*  89*/        hiddenMethods = null;
/*  90*/        uniqueNumberSeed = 0;
/*  91*/        getCount = 0;
            }

            CtClassType(InputStream inputstream, ClassPool classpool)
                throws IOException
            {
/*  95*/        this(((String) (null)), classpool);
/*  96*/        classfile = new ClassFile(new DataInputStream(inputstream));
/*  97*/        qualifiedName = classfile.getName();
            }

            protected void extendToString(StringBuffer stringbuffer)
            {
/* 101*/        if(wasChanged)
/* 102*/            stringbuffer.append("changed ");
/* 104*/        if(wasFrozen)
/* 105*/            stringbuffer.append("frozen ");
/* 107*/        if(wasPruned)
/* 108*/            stringbuffer.append("pruned ");
/* 110*/        stringbuffer.append(Modifier.toString(getModifiers()));
/* 111*/        stringbuffer.append(" class ");
/* 112*/        stringbuffer.append(getName());
                Object obj;
                String s;
/* 115*/        try
                {
/* 115*/            if((obj = getSuperclass()) != null && !(s = ((CtClass) (obj)).getName()).equals("java.lang.Object"))
/* 119*/                stringbuffer.append((new StringBuilder(" extends ")).append(((CtClass) (obj)).getName()).toString());
                }
/* 122*/        catch(NotFoundException _ex)
                {
/* 123*/            stringbuffer.append(" extends ??");
                }
/* 127*/        try
                {
                    CtClass actclass[];
/* 127*/            if((actclass = getInterfaces()).length > 0)
/* 129*/                stringbuffer.append(" implements ");
/* 131*/            for(int i = 0; i < actclass.length; i++)
                    {
/* 132*/                stringbuffer.append(actclass[i].getName());
/* 133*/                stringbuffer.append(", ");
                    }

                }
/* 136*/        catch(NotFoundException _ex)
                {
/* 137*/            stringbuffer.append(" extends ??");
                }
/* 140*/        actclass = getMembers();
/* 141*/        exToString(stringbuffer, " fields=", actclass.fieldHead(), actclass.lastField());
/* 143*/        exToString(stringbuffer, " constructors=", actclass.consHead(), actclass.lastCons());
/* 145*/        exToString(stringbuffer, " methods=", actclass.methodHead(), actclass.lastMethod());
            }

            private void exToString(StringBuffer stringbuffer, String s, CtMember ctmember, CtMember ctmember1)
            {
/* 151*/        stringbuffer.append(s);
/* 152*/        while(ctmember != ctmember1) 
                {
/* 153*/            ctmember = ctmember.next();
/* 154*/            stringbuffer.append(ctmember);
/* 155*/            stringbuffer.append(", ");
                }
            }

            public AccessorMaker getAccessorMaker()
            {
/* 160*/        if(accessors == null)
/* 161*/            accessors = new AccessorMaker(this);
/* 163*/        return accessors;
            }

            public ClassFile getClassFile2()
            {
                ClassFile classfile1;
/* 167*/        if((classfile1 = classfile) != null)
/* 169*/            return classfile1;
/* 171*/        classPool.compress();
/* 172*/        if(rawClassfile == null)
/* 174*/            break MISSING_BLOCK_LABEL_83;
/* 174*/        classfile = new ClassFile(new DataInputStream(new ByteArrayInputStream(rawClassfile)));
/* 176*/        rawClassfile = null;
/* 177*/        getCount = 2;
/* 178*/        return classfile;
                IOException ioexception;
/* 180*/        ioexception;
/* 181*/        throw new RuntimeException(ioexception.toString(), ioexception);
                Object obj;
                Exception exception;
/* 185*/        obj = null;
                ClassFile classfile2;
/* 187*/        try
                {
/* 187*/            if((obj = classPool.openClassfile(getName())) == null)
/* 189*/                throw new NotFoundException(getName());
/* 191*/            obj = new BufferedInputStream(((InputStream) (obj)));
/* 192*/            if(!(classfile2 = new ClassFile(new DataInputStream(((InputStream) (obj))))).getName().equals(qualifiedName))
/* 194*/                throw new RuntimeException((new StringBuilder("cannot find ")).append(qualifiedName).append(": ").append(classfile2.getName()).append(" found in ").append(qualifiedName.replace('.', '/')).append(".class").toString());
/* 198*/            classfile = classfile2;
/* 199*/            classfile2 = classfile2;
                }
/* 201*/        catch(NotFoundException notfoundexception)
                {
/* 202*/            throw new RuntimeException(notfoundexception.toString(), notfoundexception);
                }
/* 204*/        catch(IOException ioexception1)
                {
/* 205*/            throw new RuntimeException(ioexception1.toString(), ioexception1);
                }
/* 208*/        finally
                {
/* 208*/            if(obj == null) goto _L0; else goto _L0
                }
/* 210*/        try
                {
/* 210*/            ((InputStream) (obj)).close();
                }
/* 212*/        catch(IOException _ex) { }
/* 212*/        return classfile2;
/* 210*/        try
                {
/* 210*/            ((InputStream) (obj)).close();
                }
/* 212*/        catch(IOException _ex) { }
/* 212*/        throw exception;
            }

            final void incGetCounter()
            {
/* 221*/        getCount++;
            }

            void compress()
            {
/* 229*/        if(getCount < 2)
/* 230*/            if(!isModified() && ClassPool.releaseUnmodifiedClassFile)
/* 231*/                removeClassFile();
/* 232*/            else
/* 232*/            if(isFrozen() && !wasPruned)
/* 233*/                saveClassFile();
/* 235*/        getCount = 0;
            }

            private synchronized void saveClassFile()
            {
/* 245*/        if(classfile == null || hasMemberCache() != null)
/* 246*/            return;
/* 248*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 249*/        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
/* 251*/        try
                {
/* 251*/            classfile.write(dataoutputstream);
/* 252*/            bytearrayoutputstream.close();
/* 253*/            rawClassfile = bytearrayoutputstream.toByteArray();
/* 254*/            classfile = null;
/* 256*/            return;
                }
/* 256*/        catch(IOException _ex)
                {
/* 257*/            return;
                }
            }

            private synchronized void removeClassFile()
            {
/* 260*/        if(classfile != null && !isModified() && hasMemberCache() == null)
/* 261*/            classfile = null;
            }

            public ClassPool getClassPool()
            {
/* 264*/        return classPool;
            }

            void setClassPool(ClassPool classpool)
            {
/* 266*/        classPool = classpool;
            }

            public URL getURL()
                throws NotFoundException
            {
                URL url;
/* 269*/        if((url = classPool.find(getName())) == null)
/* 271*/            throw new NotFoundException(getName());
/* 273*/        else
/* 273*/            return url;
            }

            public boolean isModified()
            {
/* 276*/        return wasChanged;
            }

            public boolean isFrozen()
            {
/* 278*/        return wasFrozen;
            }

            public void freeze()
            {
/* 280*/        wasFrozen = true;
            }

            void checkModify()
                throws RuntimeException
            {
/* 283*/        if(isFrozen())
                {
/* 284*/            String s = (new StringBuilder()).append(getName()).append(" class is frozen").toString();
/* 285*/            if(wasPruned)
/* 286*/                s = (new StringBuilder()).append(s).append(" and pruned").toString();
/* 288*/            throw new RuntimeException(s);
                } else
                {
/* 291*/            wasChanged = true;
/* 292*/            return;
                }
            }

            public void defrost()
            {
/* 295*/        checkPruned("defrost");
/* 296*/        wasFrozen = false;
            }

            public boolean subtypeOf(CtClass ctclass)
                throws NotFoundException
            {
/* 301*/        String s = ctclass.getName();
/* 302*/        if(this == ctclass || getName().equals(s))
/* 303*/            return true;
                ClassFile classfile1;
                String s1;
/* 305*/        if((s1 = (classfile1 = getClassFile2()).getSuperclass()) != null && s1.equals(s))
/* 308*/            return true;
                String as[];
/* 310*/        int k = (as = classfile1.getInterfaces()).length;
/* 312*/        for(int i = 0; i < k; i++)
/* 313*/            if(as[i].equals(s))
/* 314*/                return true;

/* 316*/        if(s1 != null && classPool.get(s1).subtypeOf(ctclass))
/* 317*/            return true;
/* 319*/        for(int j = 0; j < k; j++)
/* 320*/            if(classPool.get(as[j]).subtypeOf(ctclass))
/* 321*/                return true;

/* 323*/        return false;
            }

            public void setName(String s)
                throws RuntimeException
            {
/* 327*/        String s1 = getName();
/* 328*/        if(s.equals(s1))
                {
/* 329*/            return;
                } else
                {
/* 332*/            classPool.checkNotFrozen(s);
/* 333*/            ClassFile classfile1 = getClassFile2();
/* 334*/            super.setName(s);
/* 335*/            classfile1.setName(s);
/* 336*/            nameReplaced();
/* 337*/            classPool.classNameChanged(s1, this);
/* 338*/            return;
                }
            }

            public String getGenericSignature()
            {
                SignatureAttribute signatureattribute;
/* 341*/        if((signatureattribute = (SignatureAttribute)getClassFile2().getAttribute("Signature")) == null)
/* 343*/            return null;
/* 343*/        else
/* 343*/            return signatureattribute.getSignature();
            }

            public void setGenericSignature(String s)
            {
/* 347*/        ClassFile classfile1 = getClassFile();
/* 348*/        s = new SignatureAttribute(classfile1.getConstPool(), s);
/* 349*/        classfile1.addAttribute(s);
            }

            public void replaceClassName(ClassMap classmap)
                throws RuntimeException
            {
/* 355*/        String s = getName();
                String s1;
/* 356*/        if((s1 = (String)classmap.get(Descriptor.toJvmName(s))) != null)
                {
/* 359*/            s1 = Descriptor.toJavaName(s1);
/* 361*/            classPool.checkNotFrozen(s1);
                }
/* 364*/        super.replaceClassName(classmap);
                ClassFile classfile1;
/* 365*/        (classfile1 = getClassFile2()).renameClass(classmap);
/* 367*/        nameReplaced();
/* 369*/        if(s1 != null)
                {
/* 370*/            super.setName(s1);
/* 371*/            classPool.classNameChanged(s, this);
                }
            }

            public void replaceClassName(String s, String s1)
                throws RuntimeException
            {
                String s2;
/* 378*/        if((s2 = getName()).equals(s))
                {
/* 380*/            setName(s1);
/* 380*/            return;
                } else
                {
/* 382*/            super.replaceClassName(s, s1);
/* 383*/            getClassFile2().renameClass(s, s1);
/* 384*/            nameReplaced();
/* 386*/            return;
                }
            }

            public boolean isInterface()
            {
/* 389*/        return Modifier.isInterface(getModifiers());
            }

            public boolean isAnnotation()
            {
/* 393*/        return Modifier.isAnnotation(getModifiers());
            }

            public boolean isEnum()
            {
/* 397*/        return Modifier.isEnum(getModifiers());
            }

            public int getModifiers()
            {
                ClassFile classfile1;
                int j;
/* 401*/        j = AccessFlag.clear(j = (classfile1 = getClassFile2()).getAccessFlags(), 32);
                int i;
/* 404*/        if((i = classfile1.getInnerAccessFlags()) != -1 && (i & 8) != 0)
/* 406*/            j |= 8;
/* 408*/        return AccessFlag.toModifier(j);
            }

            public CtClass[] getNestedClasses()
                throws NotFoundException
            {
                Object obj;
                InnerClassesAttribute innerclassesattribute;
/* 412*/        if((innerclassesattribute = (InnerClassesAttribute)((ClassFile) (obj = getClassFile2())).getAttribute("InnerClasses")) == null)
/* 416*/            return new CtClass[0];
/* 418*/        obj = (new StringBuilder()).append(((ClassFile) (obj)).getName()).append("$").toString();
/* 419*/        int i = innerclassesattribute.tableLength();
/* 420*/        ArrayList arraylist = new ArrayList(i);
/* 421*/        for(int j = 0; j < i; j++)
                {
                    String s;
/* 422*/            if((s = innerclassesattribute.innerClass(j)) != null && s.startsWith(((String) (obj))) && s.lastIndexOf('$') < ((String) (obj)).length())
/* 427*/                arraylist.add(classPool.get(s));
                }

/* 431*/        return (CtClass[])arraylist.toArray(new CtClass[arraylist.size()]);
            }

            public void setModifiers(int i)
            {
/* 435*/        ClassFile classfile1 = getClassFile2();
                int j;
/* 436*/        if(Modifier.isStatic(i))
/* 437*/            if((j = classfile1.getInnerAccessFlags()) != -1 && (j & 8) != 0)
/* 439*/                i &= -9;
/* 441*/            else
/* 441*/                throw new RuntimeException((new StringBuilder("cannot change ")).append(getName()).append(" into a static class").toString());
/* 444*/        checkModify();
/* 445*/        classfile1.setAccessFlags(AccessFlag.of(i));
            }

            public boolean hasAnnotation(Class class1)
            {
                Object obj;
/* 449*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((ClassFile) (obj = getClassFile2())).getAttribute("RuntimeInvisibleAnnotations");
/* 452*/        obj = (AnnotationsAttribute)((ClassFile) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 454*/        return hasAnnotationType(class1, getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            static boolean hasAnnotationType(Class class1, ClassPool classpool, AnnotationsAttribute annotationsattribute, AnnotationsAttribute annotationsattribute1)
            {
/* 462*/        if(annotationsattribute == null)
/* 463*/            classpool = null;
/* 465*/        else
/* 465*/            classpool = annotationsattribute.getAnnotations();
/* 467*/        if(annotationsattribute1 == null)
/* 468*/            annotationsattribute = null;
/* 470*/        else
/* 470*/            annotationsattribute = annotationsattribute1.getAnnotations();
/* 472*/        class1 = class1.getName();
/* 473*/        if(classpool != null)
/* 474*/            for(annotationsattribute1 = 0; annotationsattribute1 < classpool.length; annotationsattribute1++)
/* 475*/                if(classpool[annotationsattribute1].getTypeName().equals(class1))
/* 476*/                    return true;

/* 478*/        if(annotationsattribute != null)
/* 479*/            for(annotationsattribute1 = 0; annotationsattribute1 < annotationsattribute.length; annotationsattribute1++)
/* 480*/                if(annotationsattribute[annotationsattribute1].getTypeName().equals(class1))
/* 481*/                    return true;

/* 483*/        return false;
            }

            public Object getAnnotation(Class class1)
                throws ClassNotFoundException
            {
                Object obj;
/* 487*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((ClassFile) (obj = getClassFile2())).getAttribute("RuntimeInvisibleAnnotations");
/* 490*/        obj = (AnnotationsAttribute)((ClassFile) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 492*/        return getAnnotationType(class1, getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            static Object getAnnotationType(Class class1, ClassPool classpool, AnnotationsAttribute annotationsattribute, AnnotationsAttribute annotationsattribute1)
                throws ClassNotFoundException
            {
/* 501*/        if(annotationsattribute == null)
/* 502*/            annotationsattribute = null;
/* 504*/        else
/* 504*/            annotationsattribute = annotationsattribute.getAnnotations();
/* 506*/        if(annotationsattribute1 == null)
/* 507*/            annotationsattribute1 = null;
/* 509*/        else
/* 509*/            annotationsattribute1 = annotationsattribute1.getAnnotations();
/* 511*/        class1 = class1.getName();
/* 512*/        if(annotationsattribute != null)
                {
/* 513*/            for(int i = 0; i < annotationsattribute.length; i++)
/* 514*/                if(annotationsattribute[i].getTypeName().equals(class1))
/* 515*/                    return toAnnoType(annotationsattribute[i], classpool);

                }
/* 517*/        if(annotationsattribute1 != null)
                {
/* 518*/            for(int j = 0; j < annotationsattribute1.length; j++)
/* 519*/                if(annotationsattribute1[j].getTypeName().equals(class1))
/* 520*/                    return toAnnoType(annotationsattribute1[j], classpool);

                }
/* 522*/        return null;
            }

            public Object[] getAnnotations()
                throws ClassNotFoundException
            {
/* 526*/        return getAnnotations(false);
            }

            public Object[] getAvailableAnnotations()
            {
/* 531*/        return getAnnotations(true);
                ClassNotFoundException classnotfoundexception;
/* 533*/        classnotfoundexception;
/* 534*/        throw new RuntimeException("Unexpected exception ", classnotfoundexception);
            }

            private Object[] getAnnotations(boolean flag)
                throws ClassNotFoundException
            {
                Object obj;
/* 541*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((ClassFile) (obj = getClassFile2())).getAttribute("RuntimeInvisibleAnnotations");
/* 544*/        obj = (AnnotationsAttribute)((ClassFile) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 546*/        return toAnnotationType(flag, getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            static Object[] toAnnotationType(boolean flag, ClassPool classpool, AnnotationsAttribute annotationsattribute, AnnotationsAttribute annotationsattribute1)
                throws ClassNotFoundException
            {
                int i;
/* 556*/        if(annotationsattribute == null)
                {
/* 557*/            annotationsattribute = null;
/* 558*/            i = 0;
                } else
                {
/* 561*/            i = (annotationsattribute = annotationsattribute.getAnnotations()).length;
                }
                int j;
/* 565*/        if(annotationsattribute1 == null)
                {
/* 566*/            annotationsattribute1 = null;
/* 567*/            j = 0;
                } else
                {
/* 570*/            j = (annotationsattribute1 = annotationsattribute1.getAnnotations()).length;
                }
/* 574*/        if(!flag)
                {
/* 575*/            flag = ((boolean) (new Object[i + j]));
/* 576*/            for(int k = 0; k < i; k++)
/* 577*/                flag[k] = toAnnoType(annotationsattribute[k], classpool);

/* 579*/            for(int l = 0; l < j; l++)
/* 580*/                flag[l + i] = toAnnoType(annotationsattribute1[l], classpool);

/* 582*/            return flag;
                }
/* 585*/        flag = new ArrayList();
/* 586*/        for(int i1 = 0; i1 < i; i1++)
/* 588*/            try
                    {
/* 588*/                flag.add(toAnnoType(annotationsattribute[i1], classpool));
                    }
/* 590*/            catch(ClassNotFoundException _ex) { }

/* 592*/        for(int j1 = 0; j1 < j; j1++)
/* 594*/            try
                    {
/* 594*/                flag.add(toAnnoType(annotationsattribute1[j1], classpool));
                    }
/* 596*/            catch(ClassNotFoundException _ex) { }

/* 599*/        return flag.toArray();
            }

            static Object[][] toAnnotationType(boolean flag, ClassPool classpool, ParameterAnnotationsAttribute parameterannotationsattribute, ParameterAnnotationsAttribute parameterannotationsattribute1, MethodInfo methodinfo)
                throws ClassNotFoundException
            {
/* 610*/        if(parameterannotationsattribute != null)
/* 611*/            methodinfo = parameterannotationsattribute.numParameters();
/* 612*/        else
/* 612*/        if(parameterannotationsattribute1 != null)
/* 613*/            methodinfo = parameterannotationsattribute1.numParameters();
/* 615*/        else
/* 615*/            methodinfo = Descriptor.numOfParameters(methodinfo.getDescriptor());
/* 617*/        Object aobj[][] = new Object[methodinfo][];
/* 618*/        for(int i = 0; i < methodinfo; i++)
                {
                    Annotation aannotation[];
                    int j;
/* 622*/            if(parameterannotationsattribute == null)
                    {
/* 623*/                aannotation = null;
/* 624*/                j = 0;
                    } else
                    {
/* 627*/                j = (aannotation = parameterannotationsattribute.getAnnotations()[i]).length;
                    }
                    Annotation aannotation1[];
                    int k;
/* 631*/            if(parameterannotationsattribute1 == null)
                    {
/* 632*/                aannotation1 = null;
/* 633*/                k = 0;
                    } else
                    {
/* 636*/                k = (aannotation1 = parameterannotationsattribute1.getAnnotations()[i]).length;
                    }
/* 640*/            if(!flag)
                    {
/* 641*/                aobj[i] = new Object[j + k];
/* 642*/                for(int l = 0; l < j; l++)
/* 643*/                    aobj[i][l] = toAnnoType(aannotation[l], classpool);

/* 645*/                for(int i1 = 0; i1 < k; i1++)
/* 646*/                    aobj[i][i1 + j] = toAnnoType(aannotation1[i1], classpool);

/* 645*/                continue;
                    }
/* 649*/            ArrayList arraylist = new ArrayList();
/* 650*/            for(int j1 = 0; j1 < j; j1++)
/* 652*/                try
                        {
/* 652*/                    arraylist.add(toAnnoType(aannotation[j1], classpool));
                        }
/* 654*/                catch(ClassNotFoundException _ex) { }

/* 656*/            for(int k1 = 0; k1 < k; k1++)
/* 658*/                try
                        {
/* 658*/                    arraylist.add(toAnnoType(aannotation1[k1], classpool));
                        }
/* 660*/                catch(ClassNotFoundException _ex) { }

/* 663*/            aobj[i] = arraylist.toArray();
                }

/* 667*/        return aobj;
            }

            private static Object toAnnoType(Annotation annotation, ClassPool classpool)
                throws ClassNotFoundException
            {
/* 674*/        ClassLoader classloader = classpool.getClassLoader();
/* 675*/        return annotation.toAnnotationType(classloader, classpool);
/* 677*/        JVM INSTR pop ;
/* 678*/        ClassLoader classloader1 = classpool.getClass().getClassLoader();
/* 679*/        return annotation.toAnnotationType(classloader1, classpool);
            }

            public boolean subclassOf(CtClass ctclass)
            {
                Object obj;
/* 684*/        if(ctclass == null)
/* 685*/            return false;
/* 687*/        ctclass = ctclass.getName();
/* 688*/        obj = this;
_L2:
/* 690*/        if(obj == null)
/* 691*/            break; /* Loop/switch isn't completed */
/* 691*/        if(((CtClass) (obj)).getName().equals(ctclass))
/* 692*/            return true;
/* 694*/        try
                {
/* 694*/            obj = ((CtClass) (obj)).getSuperclass();
/* 694*/            continue; /* Loop/switch isn't completed */
                }
/* 697*/        catch(Exception _ex) { }
/* 698*/        break; /* Loop/switch isn't completed */
/* 698*/        if(true) goto _L2; else goto _L1
_L1:
/* 698*/        return false;
            }

            public CtClass getSuperclass()
                throws NotFoundException
            {
                String s;
/* 702*/        if((s = getClassFile2().getSuperclass()) == null)
/* 704*/            return null;
/* 706*/        else
/* 706*/            return classPool.get(s);
            }

            public void setSuperclass(CtClass ctclass)
                throws CannotCompileException
            {
/* 710*/        checkModify();
/* 711*/        if(isInterface())
                {
/* 712*/            addInterface(ctclass);
/* 712*/            return;
                } else
                {
/* 714*/            getClassFile2().setSuperclass(ctclass.getName());
/* 715*/            return;
                }
            }

            public CtClass[] getInterfaces()
                throws NotFoundException
            {
                String as[];
                int i;
/* 718*/        CtClass actclass[] = new CtClass[i = (as = getClassFile2().getInterfaces()).length];
/* 721*/        for(int j = 0; j < i; j++)
/* 722*/            actclass[j] = classPool.get(as[j]);

/* 724*/        return actclass;
            }

            public void setInterfaces(CtClass actclass[])
            {
/* 728*/        checkModify();
                String as[];
/* 730*/        if(actclass == null)
                {
/* 731*/            as = new String[0];
                } else
                {
                    int i;
/* 733*/            as = new String[i = actclass.length];
/* 735*/            for(int j = 0; j < i; j++)
/* 736*/                as[j] = actclass[j].getName();

                }
/* 739*/        getClassFile2().setInterfaces(as);
            }

            public void addInterface(CtClass ctclass)
            {
/* 743*/        checkModify();
/* 744*/        if(ctclass != null)
/* 745*/            getClassFile2().addInterface(ctclass.getName());
            }

            public CtClass getDeclaringClass()
                throws NotFoundException
            {
                ClassFile classfile1;
                InnerClassesAttribute innerclassesattribute;
/* 749*/        if((innerclassesattribute = (InnerClassesAttribute)(classfile1 = getClassFile2()).getAttribute("InnerClasses")) == null)
/* 753*/            return null;
/* 755*/        String s = getName();
/* 756*/        int i = innerclassesattribute.tableLength();
/* 757*/        for(int j = 0; j < i; j++)
                {
/* 758*/            if(!s.equals(innerclassesattribute.innerClass(j)))
/* 759*/                continue;
                    Object obj;
/* 759*/            if((obj = innerclassesattribute.outerClass(j)) != null)
/* 761*/                return classPool.get(((String) (obj)));
/* 764*/            if((obj = (EnclosingMethodAttribute)classfile1.getAttribute("EnclosingMethod")) != null)
/* 768*/                return classPool.get(((EnclosingMethodAttribute) (obj)).className());
                }

/* 772*/        return null;
            }

            public CtMethod getEnclosingMethod()
                throws NotFoundException
            {
                Object obj;
                CtClass ctclass;
/* 776*/        if((obj = (EnclosingMethodAttribute)((ClassFile) (obj = getClassFile2())).getAttribute("EnclosingMethod")) != null)
/* 781*/            return (ctclass = classPool.get(((EnclosingMethodAttribute) (obj)).className())).getMethod(((EnclosingMethodAttribute) (obj)).methodName(), ((EnclosingMethodAttribute) (obj)).methodDescriptor());
/* 785*/        else
/* 785*/            return null;
            }

            public CtClass makeNestedClass(String s, boolean flag)
            {
/* 789*/        if(!flag)
/* 790*/            throw new RuntimeException("sorry, only nested static class is supported");
/* 793*/        checkModify();
/* 794*/        flag = classPool.makeNestedClass((new StringBuilder()).append(getName()).append("$").append(s).toString());
/* 795*/        ClassFile classfile1 = getClassFile2();
/* 796*/        ClassFile classfile2 = flag.getClassFile2();
                InnerClassesAttribute innerclassesattribute;
/* 797*/        if((innerclassesattribute = (InnerClassesAttribute)classfile1.getAttribute("InnerClasses")) == null)
                {
/* 800*/            innerclassesattribute = new InnerClassesAttribute(classfile1.getConstPool());
/* 801*/            classfile1.addAttribute(innerclassesattribute);
                }
/* 804*/        innerclassesattribute.append(flag.getName(), getName(), s, classfile2.getAccessFlags() & 0xffffffdf | 8);
/* 806*/        classfile2.addAttribute(innerclassesattribute.copy(classfile2.getConstPool(), null));
/* 807*/        return flag;
            }

            private void nameReplaced()
            {
                Object obj;
/* 813*/        if((obj = hasMemberCache()) != null)
                {
/* 815*/            CtMember ctmember = ((CtMember.Cache) (obj)).methodHead();
/* 816*/            for(obj = ((CtMember.Cache) (obj)).lastMethod(); ctmember != obj;)
/* 818*/                (ctmember = ctmember.next()).nameReplaced();

                }
            }

            protected CtMember.Cache hasMemberCache()
            {
/* 828*/        if(memberCache != null)
/* 829*/            return (CtMember.Cache)memberCache.get();
/* 831*/        else
/* 831*/            return null;
            }

            protected synchronized CtMember.Cache getMembers()
            {
                CtMember.Cache cache;
/* 836*/        if(memberCache == null || (cache = (CtMember.Cache)memberCache.get()) == null)
                {
/* 838*/            cache = new CtMember.Cache(this);
/* 839*/            makeFieldCache(cache);
/* 840*/            makeBehaviorCache(cache);
/* 841*/            memberCache = new WeakReference(cache);
                }
/* 844*/        return cache;
            }

            private void makeFieldCache(CtMember.Cache cache)
            {
                List list;
/* 848*/        int i = (list = getClassFile2().getFields()).size();
/* 850*/        for(int j = 0; j < i; j++)
                {
/* 851*/            Object obj = (FieldInfo)list.get(j);
/* 852*/            obj = new CtField(((FieldInfo) (obj)), this);
/* 853*/            cache.addField(((CtMember) (obj)));
                }

            }

            private void makeBehaviorCache(CtMember.Cache cache)
            {
                List list;
/* 858*/        int i = (list = getClassFile2().getMethods()).size();
/* 860*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 861*/            if(((MethodInfo) (obj = (MethodInfo)list.get(j))).isMethod())
                    {
/* 863*/                obj = new CtMethod(((MethodInfo) (obj)), this);
/* 864*/                cache.addMethod(((CtMember) (obj)));
                    } else
                    {
/* 867*/                obj = new CtConstructor(((MethodInfo) (obj)), this);
/* 868*/                cache.addConstructor(((CtMember) (obj)));
                    }
                }

            }

            public CtField[] getFields()
            {
                ArrayList arraylist;
/* 874*/        getFields(arraylist = new ArrayList(), ((CtClass) (this)));
/* 876*/        return (CtField[])arraylist.toArray(new CtField[arraylist.size()]);
            }

            private static void getFields(ArrayList arraylist, CtClass ctclass)
            {
/* 881*/        if(ctclass == null)
/* 882*/            return;
/* 885*/        try
                {
/* 885*/            getFields(arraylist, ctclass.getSuperclass());
                }
/* 887*/        catch(NotFoundException _ex) { }
/* 890*/        try
                {
                    CtClass actclass[];
/* 890*/            int j = (actclass = ctclass.getInterfaces()).length;
/* 892*/            for(int i = 0; i < j; i++)
/* 893*/                getFields(arraylist, actclass[i]);

                }
/* 895*/        catch(NotFoundException _ex) { }
                CtMember.Cache cache;
/* 897*/        ctclass = (cache = ((CtClassType)ctclass).getMembers()).fieldHead();
/* 899*/        CtMember ctmember = cache.lastField();
/* 900*/        do
                {
/* 900*/            if(ctclass == ctmember)
/* 901*/                break;
/* 901*/            if(!Modifier.isPrivate((ctclass = ctclass.next()).getModifiers()))
/* 903*/                arraylist.add(ctclass);
                } while(true);
            }

            public CtField getField(String s, String s1)
                throws NotFoundException
            {
/* 908*/        CtField ctfield = getField2(s, s1);
/* 909*/        return checkGetField(ctfield, s, s1);
            }

            private CtField checkGetField(CtField ctfield, String s, String s1)
                throws NotFoundException
            {
/* 915*/        if(ctfield == null)
                {
/* 916*/            ctfield = (new StringBuilder("field: ")).append(s).toString();
/* 917*/            if(s1 != null)
/* 918*/                ctfield = (new StringBuilder()).append(ctfield).append(" type ").append(s1).toString();
/* 920*/            throw new NotFoundException((new StringBuilder()).append(ctfield).append(" in ").append(getName()).toString());
                } else
                {
/* 923*/            return ctfield;
                }
            }

            CtField getField2(String s, String s1)
            {
                CtField ctfield;
/* 927*/        if((ctfield = getDeclaredField2(s, s1)) != null)
/* 929*/            return ctfield;
                CtClass actclass[];
                int i;
                int j;
/* 932*/        i = (actclass = getInterfaces()).length;
/* 934*/        j = 0;
_L1:
/* 934*/        if(j >= i)
/* 935*/            break MISSING_BLOCK_LABEL_56;
                CtField ctfield1;
/* 935*/        if((ctfield1 = actclass[j].getField2(s, s1)) != null)
/* 937*/            return ctfield1;
/* 934*/        j++;
                  goto _L1
                CtClass ctclass;
/* 940*/        if((ctclass = getSuperclass()) != null)
/* 942*/            return ctclass.getField2(s, s1);
/* 944*/        break MISSING_BLOCK_LABEL_78;
/* 944*/        JVM INSTR pop ;
/* 945*/        return null;
            }

            public CtField[] getDeclaredFields()
            {
                Object obj;
/* 949*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).fieldHead();
/* 951*/        obj = ((CtMember.Cache) (obj)).lastField();
                int i;
/* 952*/        CtField actfield[] = new CtField[i = CtMember.Cache.count(ctmember, ((CtMember) (obj)))];
/* 954*/        int j = 0;
/* 955*/        while(ctmember != obj) 
                {
/* 956*/            ctmember = ctmember.next();
/* 957*/            actfield[j++] = (CtField)ctmember;
                }
/* 960*/        return actfield;
            }

            public CtField getDeclaredField(String s)
                throws NotFoundException
            {
/* 964*/        return getDeclaredField(s, null);
            }

            public CtField getDeclaredField(String s, String s1)
                throws NotFoundException
            {
/* 968*/        CtField ctfield = getDeclaredField2(s, s1);
/* 969*/        return checkGetField(ctfield, s, s1);
            }

            private CtField getDeclaredField2(String s, String s1)
            {
                Object obj;
/* 973*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).fieldHead();
/* 975*/        for(obj = ((CtMember.Cache) (obj)).lastField(); ctmember != obj;)
/* 977*/            if((ctmember = ctmember.next()).getName().equals(s) && (s1 == null || s1.equals(ctmember.getSignature())))
/* 980*/                return (CtField)ctmember;

/* 983*/        return null;
            }

            public CtBehavior[] getDeclaredBehaviors()
            {
                Object obj;
/* 987*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).consHead();
/* 989*/        CtMember ctmember1 = ((CtMember.Cache) (obj)).lastCons();
/* 990*/        int i = CtMember.Cache.count(ctmember, ctmember1);
/* 991*/        CtMember ctmember2 = ((CtMember.Cache) (obj)).methodHead();
/* 992*/        obj = ((CtMember.Cache) (obj)).lastMethod();
/* 993*/        int j = CtMember.Cache.count(ctmember2, ((CtMember) (obj)));
/* 995*/        CtBehavior actbehavior[] = new CtBehavior[i + j];
/* 996*/        j = 0;
/* 997*/        while(ctmember != ctmember1) 
                {
/* 998*/            ctmember = ctmember.next();
/* 999*/            actbehavior[j++] = (CtBehavior)ctmember;
                }
/*1002*/        while(ctmember2 != obj) 
                {
/*1003*/            ctmember2 = ctmember2.next();
/*1004*/            actbehavior[j++] = (CtBehavior)ctmember2;
                }
/*1007*/        return actbehavior;
            }

            public CtConstructor[] getConstructors()
            {
                Object obj;
/*1011*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).consHead();
/*1013*/        obj = ((CtMember.Cache) (obj)).lastCons();
/*1015*/        int i = 0;
/*1016*/        CtMember ctmember1 = ctmember;
/*1017*/        do
                {
/*1017*/            if(ctmember1 == obj)
/*1018*/                break;
/*1018*/            if(isPubCons((CtConstructor)(ctmember1 = ctmember1.next())))
/*1020*/                i++;
                } while(true);
/*1023*/        CtConstructor actconstructor[] = new CtConstructor[i];
/*1024*/        int j = 0;
/*1025*/        ctmember1 = ctmember;
/*1026*/        do
                {
/*1026*/            if(ctmember1 == obj)
/*1027*/                break;
                    CtConstructor ctconstructor;
/*1027*/            if(isPubCons(ctconstructor = (CtConstructor)(ctmember1 = ctmember1.next())))
/*1030*/                actconstructor[j++] = ctconstructor;
                } while(true);
/*1033*/        return actconstructor;
            }

            private static boolean isPubCons(CtConstructor ctconstructor)
            {
/*1037*/        return !Modifier.isPrivate(ctconstructor.getModifiers()) && ctconstructor.isConstructor();
            }

            public CtConstructor getConstructor(String s)
                throws NotFoundException
            {
                Object obj;
/*1044*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).consHead();
                CtConstructor ctconstructor;
/*1046*/        for(obj = ((CtMember.Cache) (obj)).lastCons(); ctmember != obj;)
/*1049*/            if((ctconstructor = (CtConstructor)(ctmember = ctmember.next())).getMethodInfo2().getDescriptor().equals(s) && ctconstructor.isConstructor())
/*1053*/                return ctconstructor;

/*1056*/        return super.getConstructor(s);
            }

            public CtConstructor[] getDeclaredConstructors()
            {
                Object obj;
/*1060*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).consHead();
/*1062*/        obj = ((CtMember.Cache) (obj)).lastCons();
/*1064*/        int i = 0;
/*1065*/        CtMember ctmember1 = ctmember;
/*1066*/        do
                {
/*1066*/            if(ctmember1 == obj)
/*1067*/                break;
                    CtConstructor ctconstructor1;
/*1067*/            if((ctconstructor1 = (CtConstructor)(ctmember1 = ctmember1.next())).isConstructor())
/*1070*/                i++;
                } while(true);
/*1073*/        CtConstructor actconstructor[] = new CtConstructor[i];
/*1074*/        i = 0;
/*1075*/        ctmember1 = ctmember;
/*1076*/        do
                {
/*1076*/            if(ctmember1 == obj)
/*1077*/                break;
                    CtConstructor ctconstructor;
/*1077*/            if((ctconstructor = (CtConstructor)(ctmember1 = ctmember1.next())).isConstructor())
/*1080*/                actconstructor[i++] = ctconstructor;
                } while(true);
/*1083*/        return actconstructor;
            }

            public CtConstructor getClassInitializer()
            {
                Object obj;
/*1087*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).consHead();
                CtConstructor ctconstructor;
/*1089*/        for(obj = ((CtMember.Cache) (obj)).lastCons(); ctmember != obj;)
/*1092*/            if((ctconstructor = (CtConstructor)(ctmember = ctmember.next())).isClassInitializer())
/*1095*/                return ctconstructor;

/*1098*/        return null;
            }

            public CtMethod[] getMethods()
            {
                HashMap hashmap;
/*1102*/        getMethods0(hashmap = new HashMap(), this);
/*1104*/        return (CtMethod[])hashmap.values().toArray(new CtMethod[hashmap.size()]);
            }

            private static void getMethods0(HashMap hashmap, CtClass ctclass)
            {
/*1109*/        try
                {
                    CtClass actclass[];
/*1109*/            int i = (actclass = ctclass.getInterfaces()).length;
/*1111*/            for(int j = 0; j < i; j++)
/*1112*/                getMethods0(hashmap, actclass[j]);

                }
/*1114*/        catch(NotFoundException _ex) { }
                CtClass ctclass1;
/*1117*/        try
                {
/*1117*/            if((ctclass1 = ctclass.getSuperclass()) != null)
/*1119*/                getMethods0(hashmap, ctclass1);
                }
/*1121*/        catch(NotFoundException _ex) { }
/*1123*/        if(ctclass instanceof CtClassType)
                {
                    CtMember.Cache cache;
/*1124*/            CtMember ctmember = (cache = ((CtClassType)ctclass).getMembers()).methodHead();
/*1126*/            CtMember ctmember1 = cache.lastMethod();
/*1128*/            do
                    {
/*1128*/                if(ctmember == ctmember1)
/*1129*/                    break;
/*1129*/                if(!Modifier.isPrivate((ctmember = ctmember.next()).getModifiers()))
/*1131*/                    hashmap.put(((CtMethod)ctmember).getStringRep(), ctmember);
                    } while(true);
                }
            }

            public CtMethod getMethod(String s, String s1)
                throws NotFoundException
            {
/*1139*/        if((s1 = getMethod0(this, s, s1)) != null)
/*1141*/            return s1;
/*1143*/        else
/*1143*/            throw new NotFoundException((new StringBuilder()).append(s).append("(..) is not found in ").append(getName()).toString());
            }

            private static CtMethod getMethod0(CtClass ctclass, String s, String s1)
            {
/*1149*/label0:
                {
/*1149*/            if(!(ctclass instanceof CtClassType))
/*1150*/                break label0;
                    CtMember.Cache cache;
/*1150*/            CtMember ctmember = (cache = ((CtClassType)ctclass).getMembers()).methodHead();
/*1152*/            CtMember ctmember1 = cache.lastMethod();
/*1154*/            do
/*1154*/                if(ctmember == ctmember1)
/*1155*/                    break label0;
/*1156*/            while(!(ctmember = ctmember.next()).getName().equals(s) || !((CtMethod)ctmember).getMethodInfo2().getDescriptor().equals(s1));
/*1158*/            return (CtMethod)ctmember;
                }
                CtClass ctclass1;
                CtMethod ctmethod;
/*1163*/        if((ctclass1 = ctclass.getSuperclass()) != null && (ctmethod = getMethod0(ctclass1, s, s1)) != null)
/*1167*/            return ctmethod;
/*1170*/        break MISSING_BLOCK_LABEL_104;
/*1170*/        JVM INSTR pop ;
                CtClass actclass[];
                int i;
/*1173*/        i = (actclass = ctclass.getInterfaces()).length;
/*1175*/        for(int j = 0; j < i; j++)
/*1176*/            if((ctclass = getMethod0(actclass[j], s, s1)) != null)
/*1178*/                return ctclass;

/*1175*/        break MISSING_BLOCK_LABEL_149;
/*1181*/        JVM INSTR pop ;
/*1182*/        return null;
            }

            public CtMethod[] getDeclaredMethods()
            {
                Object obj;
/*1186*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).methodHead();
/*1188*/        obj = ((CtMember.Cache) (obj)).lastMethod();
                int i;
/*1189*/        CtMethod actmethod[] = new CtMethod[i = CtMember.Cache.count(ctmember, ((CtMember) (obj)))];
/*1191*/        int j = 0;
/*1192*/        while(ctmember != obj) 
                {
/*1193*/            ctmember = ctmember.next();
/*1194*/            actmethod[j++] = (CtMethod)ctmember;
                }
/*1197*/        return actmethod;
            }

            public CtMethod getDeclaredMethod(String s)
                throws NotFoundException
            {
                Object obj;
/*1201*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).methodHead();
/*1203*/        for(obj = ((CtMember.Cache) (obj)).lastMethod(); ctmember != obj;)
/*1205*/            if((ctmember = ctmember.next()).getName().equals(s))
/*1207*/                return (CtMethod)ctmember;

/*1210*/        throw new NotFoundException((new StringBuilder()).append(s).append("(..) is not found in ").append(getName()).toString());
            }

            public CtMethod getDeclaredMethod(String s, CtClass actclass[])
                throws NotFoundException
            {
/*1217*/        actclass = Descriptor.ofParameters(actclass);
                Object obj;
/*1218*/        CtMember ctmember = ((CtMember.Cache) (obj = getMembers())).methodHead();
/*1220*/        for(obj = ((CtMember.Cache) (obj)).lastMethod(); ctmember != obj;)
/*1223*/            if((ctmember = ctmember.next()).getName().equals(s) && ((CtMethod)ctmember).getMethodInfo2().getDescriptor().startsWith(actclass))
/*1226*/                return (CtMethod)ctmember;

/*1229*/        throw new NotFoundException((new StringBuilder()).append(s).append("(..) is not found in ").append(getName()).toString());
            }

            public void addField(CtField ctfield, String s)
                throws CannotCompileException
            {
/*1236*/        addField(ctfield, CtField.Initializer.byExpr(s));
            }

            public void addField(CtField ctfield, CtField.Initializer initializer)
                throws CannotCompileException
            {
/*1242*/        checkModify();
/*1243*/        if(ctfield.getDeclaringClass() != this)
/*1244*/            throw new CannotCompileException("cannot add");
/*1246*/        if(initializer == null)
/*1247*/            initializer = ctfield.getInit();
/*1249*/        if(initializer != null)
                {
/*1250*/            initializer.check(ctfield.getSignature());
                    int i;
/*1251*/            if(Modifier.isStatic(i = ctfield.getModifiers()) && Modifier.isFinal(i))
/*1254*/                try
                        {
/*1254*/                    ConstPool constpool = getClassFile2().getConstPool();
                            int j;
/*1255*/                    if((j = initializer.getConstantValue(constpool, ctfield.getType())) != 0)
                            {
/*1257*/                        ctfield.getFieldInfo2().addAttribute(new ConstantAttribute(constpool, j));
/*1258*/                        initializer = null;
                            }
                        }
/*1261*/                catch(NotFoundException _ex) { }
                }
/*1264*/        getMembers().addField(ctfield);
/*1265*/        getClassFile2().addField(ctfield.getFieldInfo2());
/*1267*/        if(initializer != null)
                {
/*1268*/            FieldInitLink fieldinitlink = new FieldInitLink(ctfield, initializer);
                    FieldInitLink fieldinitlink1;
/*1269*/            if((fieldinitlink1 = fieldInitializers) == null)
                    {
/*1271*/                fieldInitializers = fieldinitlink;
/*1271*/                return;
                    }
/*1273*/            for(; fieldinitlink1.next != null; fieldinitlink1 = fieldinitlink1.next);
/*1276*/            fieldinitlink1.next = fieldinitlink;
                }
            }

            public void removeField(CtField ctfield)
                throws NotFoundException
            {
/*1282*/        checkModify();
/*1283*/        FieldInfo fieldinfo = ctfield.getFieldInfo2();
                ClassFile classfile1;
/*1284*/        if((classfile1 = getClassFile2()).getFields().remove(fieldinfo))
                {
/*1286*/            getMembers().remove(ctfield);
/*1287*/            gcConstPool = true;
/*1287*/            return;
                } else
                {
/*1290*/            throw new NotFoundException(ctfield.toString());
                }
            }

            public CtConstructor makeClassInitializer()
                throws CannotCompileException
            {
                CtConstructor ctconstructor;
/*1296*/        if((ctconstructor = getClassInitializer()) != null)
                {
/*1298*/            return ctconstructor;
                } else
                {
/*1300*/            checkModify();
/*1301*/            ClassFile classfile1 = getClassFile2();
/*1302*/            Bytecode bytecode = new Bytecode(classfile1.getConstPool(), 0, 0);
/*1303*/            modifyClassConstructor(classfile1, bytecode, 0, 0);
/*1304*/            return getClassInitializer();
                }
            }

            public void addConstructor(CtConstructor ctconstructor)
                throws CannotCompileException
            {
/*1310*/        checkModify();
/*1311*/        if(ctconstructor.getDeclaringClass() != this)
                {
/*1312*/            throw new CannotCompileException("cannot add");
                } else
                {
/*1314*/            getMembers().addConstructor(ctconstructor);
/*1315*/            getClassFile2().addMethod(ctconstructor.getMethodInfo2());
/*1316*/            return;
                }
            }

            public void removeConstructor(CtConstructor ctconstructor)
                throws NotFoundException
            {
/*1319*/        checkModify();
/*1320*/        MethodInfo methodinfo = ctconstructor.getMethodInfo2();
                ClassFile classfile1;
/*1321*/        if((classfile1 = getClassFile2()).getMethods().remove(methodinfo))
                {
/*1323*/            getMembers().remove(ctconstructor);
/*1324*/            gcConstPool = true;
/*1324*/            return;
                } else
                {
/*1327*/            throw new NotFoundException(ctconstructor.toString());
                }
            }

            public void addMethod(CtMethod ctmethod)
                throws CannotCompileException
            {
/*1331*/        checkModify();
/*1332*/        if(ctmethod.getDeclaringClass() != this)
/*1333*/            throw new CannotCompileException("bad declaring class");
/*1335*/        int i = ctmethod.getModifiers();
/*1336*/        if((getModifiers() & 0x200) != 0)
                {
/*1337*/            ctmethod.setModifiers(i | 1);
/*1338*/            if((i & 0x400) == 0)
/*1339*/                throw new CannotCompileException((new StringBuilder("an interface method must be abstract: ")).append(ctmethod.toString()).toString());
                }
/*1343*/        getMembers().addMethod(ctmethod);
/*1344*/        getClassFile2().addMethod(ctmethod.getMethodInfo2());
/*1345*/        if((i & 0x400) != 0)
/*1346*/            setModifiers(getModifiers() | 0x400);
            }

            public void removeMethod(CtMethod ctmethod)
                throws NotFoundException
            {
/*1350*/        checkModify();
/*1351*/        MethodInfo methodinfo = ctmethod.getMethodInfo2();
                ClassFile classfile1;
/*1352*/        if((classfile1 = getClassFile2()).getMethods().remove(methodinfo))
                {
/*1354*/            getMembers().remove(ctmethod);
/*1355*/            gcConstPool = true;
/*1355*/            return;
                } else
                {
/*1358*/            throw new NotFoundException(ctmethod.toString());
                }
            }

            public byte[] getAttribute(String s)
            {
/*1362*/        if((s = getClassFile2().getAttribute(s)) == null)
/*1364*/            return null;
/*1366*/        else
/*1366*/            return s.get();
            }

            public void setAttribute(String s, byte abyte0[])
            {
/*1370*/        checkModify();
                ClassFile classfile1;
/*1371*/        (classfile1 = getClassFile2()).addAttribute(new AttributeInfo(classfile1.getConstPool(), s, abyte0));
            }

            public void instrument(CodeConverter codeconverter)
                throws CannotCompileException
            {
/*1378*/        checkModify();
                Object obj;
/*1379*/        ConstPool constpool = ((ClassFile) (obj = getClassFile2())).getConstPool();
/*1381*/        int i = ((List) (obj = ((ClassFile) (obj)).getMethods())).size();
/*1383*/        for(int j = 0; j < i; j++)
                {
/*1384*/            MethodInfo methodinfo = (MethodInfo)((List) (obj)).get(j);
/*1385*/            codeconverter.doit(this, methodinfo, constpool);
                }

            }

            public void instrument(ExprEditor expreditor)
                throws CannotCompileException
            {
/*1392*/        checkModify();
                Object obj;
/*1393*/        int i = ((List) (obj = ((ClassFile) (obj = getClassFile2())).getMethods())).size();
/*1396*/        for(int j = 0; j < i; j++)
                {
/*1397*/            MethodInfo methodinfo = (MethodInfo)((List) (obj)).get(j);
/*1398*/            expreditor.doit(this, methodinfo);
                }

            }

            public void prune()
            {
/*1407*/        if(wasPruned)
                {
/*1408*/            return;
                } else
                {
/*1410*/            wasPruned = wasFrozen = true;
/*1411*/            getClassFile2().prune();
/*1412*/            return;
                }
            }

            public void rebuildClassFile()
            {
/*1414*/        gcConstPool = true;
            }

            public void toBytecode(DataOutputStream dataoutputstream)
                throws CannotCompileException, IOException
            {
/*1420*/        try
                {
/*1420*/            if(isModified())
                    {
/*1421*/                checkPruned("toBytecode");
/*1422*/                ClassFile classfile1 = getClassFile2();
/*1423*/                if(gcConstPool)
                        {
/*1424*/                    classfile1.compact();
/*1425*/                    gcConstPool = false;
                        }
/*1428*/                modifyClassConstructor(classfile1);
/*1429*/                modifyConstructors(classfile1);
/*1430*/                if(debugDump != null)
/*1431*/                    dumpClassFile(classfile1);
/*1433*/                classfile1.write(dataoutputstream);
/*1434*/                dataoutputstream.flush();
/*1435*/                fieldInitializers = null;
/*1436*/                if(doPruning)
                        {
/*1438*/                    classfile1.prune();
/*1439*/                    wasPruned = true;
                        }
                    } else
                    {
/*1443*/                classPool.writeClassfile(getName(), dataoutputstream);
                    }
/*1448*/            getCount = 0;
/*1449*/            wasFrozen = true;
/*1456*/            return;
                }
/*1451*/        catch(NotFoundException notfoundexception)
                {
/*1452*/            throw new CannotCompileException(notfoundexception);
                }
/*1454*/        catch(IOException ioexception)
                {
/*1455*/            throw new CannotCompileException(ioexception);
                }
            }

            private void dumpClassFile(ClassFile classfile1)
                throws IOException
            {
/*1460*/        DataOutputStream dataoutputstream = makeFileOutput(debugDump);
/*1462*/        classfile1.write(dataoutputstream);
/*1465*/        dataoutputstream.close();
/*1466*/        return;
/*1465*/        classfile1;
/*1465*/        dataoutputstream.close();
/*1465*/        throw classfile1;
            }

            private void checkPruned(String s)
            {
/*1472*/        if(wasPruned)
/*1473*/            throw new RuntimeException((new StringBuilder()).append(s).append("(): ").append(getName()).append(" was pruned.").toString());
/*1475*/        else
/*1475*/            return;
            }

            public boolean stopPruning(boolean flag)
            {
/*1478*/        boolean flag1 = !doPruning;
/*1479*/        doPruning = !flag;
/*1480*/        return flag1;
            }

            private void modifyClassConstructor(ClassFile classfile1)
                throws CannotCompileException, NotFoundException
            {
/*1486*/        if(fieldInitializers == null)
/*1487*/            return;
/*1489*/        Bytecode bytecode = new Bytecode(classfile1.getConstPool(), 0, 0);
/*1490*/        Javac javac = new Javac(bytecode, this);
/*1491*/        int i = 0;
/*1492*/        boolean flag = false;
/*1493*/        for(FieldInitLink fieldinitlink = fieldInitializers; fieldinitlink != null; fieldinitlink = fieldinitlink.next)
                {
                    CtField ctfield;
/*1494*/            if(!Modifier.isStatic((ctfield = fieldinitlink.field).getModifiers()))
/*1496*/                continue;
/*1496*/            flag = true;
/*1497*/            int j = fieldinitlink.init.compileIfStatic(ctfield.getType(), ctfield.getName(), bytecode, javac);
/*1499*/            if(i < j)
/*1500*/                i = j;
                }

/*1504*/        if(flag)
/*1505*/            modifyClassConstructor(classfile1, bytecode, i, 0);
            }

            private void modifyClassConstructor(ClassFile classfile1, Bytecode bytecode, int i, int j)
                throws CannotCompileException
            {
                MethodInfo methodinfo;
/*1512*/        if((methodinfo = classfile1.getStaticInitializer()) == null)
                {
/*1514*/            bytecode.add(177);
/*1515*/            bytecode.setMaxStack(i);
/*1516*/            bytecode.setMaxLocals(j);
/*1517*/            (methodinfo = new MethodInfo(classfile1.getConstPool(), "<clinit>", "()V")).setAccessFlags(8);
/*1519*/            methodinfo.setCodeAttribute(bytecode.toCodeAttribute());
/*1520*/            classfile1.addMethod(methodinfo);
                    CtMember.Cache cache;
/*1521*/            if((cache = hasMemberCache()) != null)
/*1523*/                cache.addConstructor(new CtConstructor(methodinfo, this));
                } else
                {
                    CodeAttribute codeattribute;
/*1526*/            if((codeattribute = methodinfo.getCodeAttribute()) == null)
/*1528*/                throw new CannotCompileException("empty <clinit>");
/*1531*/            try
                    {
                        CodeIterator codeiterator;
/*1531*/                int k = (codeiterator = codeattribute.iterator()).insertEx(bytecode.get());
/*1533*/                codeiterator.insert(bytecode.getExceptionTable(), k);
/*1534*/                if((bytecode = codeattribute.getMaxStack()) < i)
/*1536*/                    codeattribute.setMaxStack(i);
/*1538*/                if((bytecode = codeattribute.getMaxLocals()) < j)
/*1540*/                    codeattribute.setMaxLocals(j);
                    }
/*1542*/            catch(BadBytecode badbytecode1)
                    {
/*1543*/                throw new CannotCompileException(badbytecode1);
                    }
                }
/*1548*/        try
                {
/*1548*/            methodinfo.rebuildStackMapIf6(classPool, classfile1);
/*1552*/            return;
                }
/*1550*/        catch(BadBytecode badbytecode)
                {
/*1551*/            throw new CannotCompileException(badbytecode);
                }
            }

            private void modifyConstructors(ClassFile classfile1)
                throws CannotCompileException, NotFoundException
            {
/*1558*/        if(fieldInitializers == null)
/*1559*/            return;
/*1561*/        ConstPool constpool = classfile1.getConstPool();
                List list;
/*1562*/        int i = (list = classfile1.getMethods()).size();
/*1564*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
                    CodeAttribute codeattribute;
/*1565*/            if(!(methodinfo = (MethodInfo)list.get(j)).isConstructor() || (codeattribute = methodinfo.getCodeAttribute()) == null)
/*1570*/                continue;
/*1570*/            try
                    {
/*1570*/                Bytecode bytecode = new Bytecode(constpool, 0, codeattribute.getMaxLocals());
/*1572*/                CtClass actclass[] = Descriptor.getParameterTypes(methodinfo.getDescriptor(), classPool);
/*1576*/                int k = makeFieldInitializer(bytecode, actclass);
/*1577*/                insertAuxInitializer(codeattribute, bytecode, k);
/*1578*/                methodinfo.rebuildStackMapIf6(classPool, classfile1);
                    }
/*1580*/            catch(BadBytecode badbytecode)
                    {
/*1581*/                throw new CannotCompileException(badbytecode);
                    }
                }

            }

            private static void insertAuxInitializer(CodeAttribute codeattribute, Bytecode bytecode, int i)
                throws BadBytecode
            {
                CodeIterator codeiterator;
                int j;
/*1592*/        if((j = (codeiterator = codeattribute.iterator()).skipSuperConstructor()) < 0 && (j = codeiterator.skipThisConstructor()) >= 0)
/*1597*/            return;
/*1602*/        j = codeiterator.insertEx(bytecode.get());
/*1603*/        codeiterator.insert(bytecode.getExceptionTable(), j);
/*1604*/        if((bytecode = codeattribute.getMaxStack()) < i)
/*1606*/            codeattribute.setMaxStack(i);
            }

            private int makeFieldInitializer(Bytecode bytecode, CtClass actclass[])
                throws CannotCompileException, NotFoundException
            {
/*1612*/        int i = 0;
/*1613*/        Javac javac = new Javac(bytecode, this);
/*1615*/        try
                {
/*1615*/            javac.recordParams(actclass, false);
                }
/*1617*/        catch(CompileError compileerror)
                {
/*1618*/            throw new CannotCompileException(compileerror);
                }
/*1621*/        for(FieldInitLink fieldinitlink = fieldInitializers; fieldinitlink != null; fieldinitlink = fieldinitlink.next)
                {
                    CtField ctfield;
/*1622*/            if(Modifier.isStatic((ctfield = fieldinitlink.field).getModifiers()))
/*1624*/                continue;
/*1624*/            int j = fieldinitlink.init.compile(ctfield.getType(), ctfield.getName(), bytecode, actclass, javac);
/*1626*/            if(i < j)
/*1627*/                i = j;
                }

/*1631*/        return i;
            }

            Hashtable getHiddenMethods()
            {
/*1637*/        if(hiddenMethods == null)
/*1638*/            hiddenMethods = new Hashtable();
/*1640*/        return hiddenMethods;
            }

            int getUniqueNumber()
            {
/*1643*/        return uniqueNumberSeed++;
            }

            public String makeUniqueName(String s)
            {
/*1646*/        Object obj = new HashMap();
/*1647*/        makeMemberList(((HashMap) (obj)));
/*1648*/        String as[] = new String[((Set) (obj = ((HashMap) (obj)).keySet())).size()];
/*1650*/        ((Set) (obj)).toArray(as);
/*1652*/        if(notFindInArray(s, as))
/*1653*/            return s;
/*1655*/        int i = 100;
                String s1;
/*1658*/        do
/*1658*/            if(i > 999)
/*1659*/                throw new RuntimeException("too many unique name");
/*1661*/        while(!notFindInArray(s1 = (new StringBuilder()).append(s).append(i++).toString(), as));
/*1663*/        return s1;
            }

            private static boolean notFindInArray(String s, String as[])
            {
/*1667*/        int i = as.length;
/*1668*/        for(int j = 0; j < i; j++)
/*1669*/            if(as[j].startsWith(s))
/*1670*/                return false;

/*1672*/        return true;
            }

            private void makeMemberList(HashMap hashmap)
            {
                int i;
/*1676*/        if(Modifier.isAbstract(i = getModifiers()) || Modifier.isInterface(i))
/*1679*/            try
                    {
                        CtClass actclass[];
/*1679*/                int j = (actclass = getInterfaces()).length;
/*1681*/                for(int l = 0; l < j; l++)
                        {
                            CtClass ctclass;
/*1682*/                    if((ctclass = actclass[l]) != null && (ctclass instanceof CtClassType))
/*1684*/                        ((CtClassType)ctclass).makeMemberList(hashmap);
                        }

                    }
/*1687*/            catch(NotFoundException _ex) { }
                Object obj;
/*1690*/        try
                {
/*1690*/            if((obj = getSuperclass()) != null && (obj instanceof CtClassType))
/*1692*/                ((CtClassType)obj).makeMemberList(hashmap);
                }
/*1694*/        catch(NotFoundException _ex) { }
/*1696*/        int k = ((List) (obj = getClassFile2().getMethods())).size();
/*1698*/        for(int i1 = 0; i1 < k; i1++)
                {
/*1699*/            MethodInfo methodinfo = (MethodInfo)((List) (obj)).get(i1);
/*1700*/            hashmap.put(methodinfo.getName(), this);
                }

/*1703*/        k = ((List) (obj = getClassFile2().getFields())).size();
/*1705*/        for(int j1 = 0; j1 < k; j1++)
                {
/*1706*/            FieldInfo fieldinfo = (FieldInfo)((List) (obj)).get(j1);
/*1707*/            hashmap.put(fieldinfo.getName(), this);
                }

            }

            ClassPool classPool;
            boolean wasChanged;
            private boolean wasFrozen;
            boolean wasPruned;
            boolean gcConstPool;
            ClassFile classfile;
            byte rawClassfile[];
            private WeakReference memberCache;
            private AccessorMaker accessors;
            private FieldInitLink fieldInitializers;
            private Hashtable hiddenMethods;
            private int uniqueNumberSeed;
            private boolean doPruning;
            private int getCount;
            private static final int GET_THRESHOLD = 2;
}
