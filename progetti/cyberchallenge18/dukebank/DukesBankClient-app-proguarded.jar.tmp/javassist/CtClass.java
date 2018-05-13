// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtClass.java

package javassist;

import java.io.*;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Collection;
import javassist.bytecode.ClassFile;
import javassist.bytecode.Descriptor;
import javassist.compiler.AccessorMaker;
import javassist.expr.ExprEditor;

// Referenced classes of package javassist:
//            CannotCompileException, ClassMap, ClassPool, CtBehavior, 
//            CtConstructor, CtField, CtMethod, CtPrimitiveType, 
//            NotFoundException, CodeConverter

public abstract class CtClass
{
    static class DelayedFileOutputStream extends OutputStream
    {

                private void init()
                    throws IOException
                {
/*1492*/            if(file == null)
/*1493*/                file = new FileOutputStream(filename);
                }

                public void write(int i)
                    throws IOException
                {
/*1497*/            init();
/*1498*/            file.write(i);
                }

                public void write(byte abyte0[])
                    throws IOException
                {
/*1502*/            init();
/*1503*/            file.write(abyte0);
                }

                public void write(byte abyte0[], int i, int j)
                    throws IOException
                {
/*1507*/            init();
/*1508*/            file.write(abyte0, i, j);
                }

                public void flush()
                    throws IOException
                {
/*1513*/            init();
/*1514*/            file.flush();
                }

                public void close()
                    throws IOException
                {
/*1518*/            init();
/*1519*/            file.close();
                }

                private FileOutputStream file;
                private String filename;

                DelayedFileOutputStream(String s)
                {
/*1487*/            file = null;
/*1488*/            filename = s;
                }
    }


            public static void main(String args[])
            {
/*  82*/        System.out.println("Javassist version 3.18.0-GA");
/*  83*/        System.out.println("Copyright (C) 1999-2013 Shigeru Chiba. All Rights Reserved.");
            }

            protected CtClass(String s)
            {
/* 195*/        qualifiedName = s;
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 202*/        (stringbuffer = new StringBuffer(getClass().getName())).append("@");
/* 204*/        stringbuffer.append(Integer.toHexString(hashCode()));
/* 205*/        stringbuffer.append("[");
/* 206*/        extendToString(stringbuffer);
/* 207*/        stringbuffer.append("]");
/* 208*/        return stringbuffer.toString();
            }

            protected void extendToString(StringBuffer stringbuffer)
            {
/* 216*/        stringbuffer.append(getName());
            }

            public ClassPool getClassPool()
            {
/* 222*/        return null;
            }

            public ClassFile getClassFile()
            {
/* 231*/        checkModify();
/* 232*/        return getClassFile2();
            }

            public ClassFile getClassFile2()
            {
/* 253*/        return null;
            }

            public AccessorMaker getAccessorMaker()
            {
/* 259*/        return null;
            }

            public URL getURL()
                throws NotFoundException
            {
/* 266*/        throw new NotFoundException(getName());
            }

            public boolean isModified()
            {
/* 272*/        return false;
            }

            public boolean isFrozen()
            {
/* 281*/        return true;
            }

            public void freeze()
            {
            }

            void checkModify()
                throws RuntimeException
            {
/* 295*/        if(isFrozen())
/* 296*/            throw new RuntimeException((new StringBuilder()).append(getName()).append(" class is frozen").toString());
/* 299*/        else
/* 299*/            return;
            }

            public void defrost()
            {
/* 317*/        throw new RuntimeException((new StringBuilder("cannot defrost ")).append(getName()).toString());
            }

            public boolean isPrimitive()
            {
/* 325*/        return false;
            }

            public boolean isArray()
            {
/* 331*/        return false;
            }

            public CtClass getComponentType()
                throws NotFoundException
            {
/* 339*/        return null;
            }

            public boolean subtypeOf(CtClass ctclass)
                throws NotFoundException
            {
/* 348*/        return this == ctclass || getName().equals(ctclass.getName());
            }

            public String getName()
            {
/* 354*/        return qualifiedName;
            }

            public final String getSimpleName()
            {
                String s;
                int i;
/* 360*/        if((i = (s = qualifiedName).lastIndexOf('.')) < 0)
/* 363*/            return s;
/* 365*/        else
/* 365*/            return s.substring(i + 1);
            }

            public final String getPackageName()
            {
                String s;
                int i;
/* 372*/        if((i = (s = qualifiedName).lastIndexOf('.')) < 0)
/* 375*/            return null;
/* 377*/        else
/* 377*/            return s.substring(0, i);
            }

            public void setName(String s)
            {
/* 386*/        checkModify();
/* 387*/        if(s != null)
/* 388*/            qualifiedName = s;
            }

            public String getGenericSignature()
            {
/* 405*/        return null;
            }

            public void setGenericSignature(String s)
            {
/* 472*/        checkModify();
            }

            public void replaceClassName(String s, String s1)
            {
/* 482*/        checkModify();
            }

            public void replaceClassName(ClassMap classmap)
            {
/* 503*/        checkModify();
            }

            public synchronized Collection getRefClasses()
            {
                ClassFile classfile;
/* 516*/        if((classfile = getClassFile2()) != null)
                {
/* 518*/            ClassMap classmap = new ClassMap() {

                        public void put(String s, String s1)
                        {
/* 520*/                    put0(s, s1);
                        }

                        public Object get(Object obj)
                        {
/* 524*/                    obj = toJavaName((String)obj);
/* 525*/                    put0(obj, obj);
/* 526*/                    return null;
                        }

                        public void fix(String s)
                        {
                        }

                        final CtClass this$0;

                    
                    {
/* 518*/                this$0 = CtClass.this;
/* 518*/                super();
                    }
            };
/* 531*/            classfile.getRefClasses(classmap);
/* 532*/            return classmap.values();
                } else
                {
/* 535*/            return null;
                }
            }

            public boolean isInterface()
            {
/* 543*/        return false;
            }

            public boolean isAnnotation()
            {
/* 553*/        return false;
            }

            public boolean isEnum()
            {
/* 563*/        return false;
            }

            public int getModifiers()
            {
/* 576*/        return 0;
            }

            public boolean hasAnnotation(Class class1)
            {
/* 587*/        return false;
            }

            public Object getAnnotation(Class class1)
                throws ClassNotFoundException
            {
/* 602*/        return null;
            }

            public Object[] getAnnotations()
                throws ClassNotFoundException
            {
/* 617*/        return new Object[0];
            }

            public Object[] getAvailableAnnotations()
            {
/* 632*/        return new Object[0];
            }

            public CtClass[] getDeclaredClasses()
                throws NotFoundException
            {
/* 644*/        return getNestedClasses();
            }

            public CtClass[] getNestedClasses()
                throws NotFoundException
            {
/* 655*/        return new CtClass[0];
            }

            public void setModifiers(int i)
            {
/* 670*/        checkModify();
            }

            public boolean subclassOf(CtClass ctclass)
            {
/* 682*/        return false;
            }

            public CtClass getSuperclass()
                throws NotFoundException
            {
/* 698*/        return null;
            }

            public void setSuperclass(CtClass ctclass)
                throws CannotCompileException
            {
/* 715*/        checkModify();
            }

            public CtClass[] getInterfaces()
                throws NotFoundException
            {
/* 724*/        return new CtClass[0];
            }

            public void setInterfaces(CtClass actclass[])
            {
/* 737*/        checkModify();
            }

            public void addInterface(CtClass ctclass)
            {
/* 746*/        checkModify();
            }

            public CtClass getDeclaringClass()
                throws NotFoundException
            {
/* 756*/        return null;
            }

            public CtMethod getEnclosingMethod()
                throws NotFoundException
            {
/* 767*/        return null;
            }

            public CtClass makeNestedClass(String s, boolean flag)
            {
/* 782*/        throw new RuntimeException((new StringBuilder()).append(getName()).append(" is not a class").toString());
            }

            public CtField[] getFields()
            {
/* 791*/        return new CtField[0];
            }

            public CtField getField(String s)
                throws NotFoundException
            {
/* 798*/        return getField(s, null);
            }

            public CtField getField(String s, String s1)
                throws NotFoundException
            {
/* 813*/        throw new NotFoundException(s);
            }

            CtField getField2(String s, String s1)
            {
/* 819*/        return null;
            }

            public CtField[] getDeclaredFields()
            {
/* 827*/        return new CtField[0];
            }

            public CtField getDeclaredField(String s)
                throws NotFoundException
            {
/* 836*/        throw new NotFoundException(s);
            }

            public CtField getDeclaredField(String s, String s1)
                throws NotFoundException
            {
/* 852*/        throw new NotFoundException(s);
            }

            public CtBehavior[] getDeclaredBehaviors()
            {
/* 859*/        return new CtBehavior[0];
            }

            public CtConstructor[] getConstructors()
            {
/* 867*/        return new CtConstructor[0];
            }

            public CtConstructor getConstructor(String s)
                throws NotFoundException
            {
/* 883*/        throw new NotFoundException("no such constructor");
            }

            public CtConstructor[] getDeclaredConstructors()
            {
/* 892*/        return new CtConstructor[0];
            }

            public CtConstructor getDeclaredConstructor(CtClass actclass[])
                throws NotFoundException
            {
/* 903*/        actclass = Descriptor.ofConstructor(actclass);
/* 904*/        return getConstructor(actclass);
            }

            public CtConstructor getClassInitializer()
            {
/* 917*/        return null;
            }

            public CtMethod[] getMethods()
            {
/* 927*/        return new CtMethod[0];
            }

            public CtMethod getMethod(String s, String s1)
                throws NotFoundException
            {
/* 945*/        throw new NotFoundException(s);
            }

            public CtMethod[] getDeclaredMethods()
            {
/* 955*/        return new CtMethod[0];
            }

            public CtMethod getDeclaredMethod(String s, CtClass actclass[])
                throws NotFoundException
            {
/* 971*/        throw new NotFoundException(s);
            }

            public CtMethod getDeclaredMethod(String s)
                throws NotFoundException
            {
/* 984*/        throw new NotFoundException(s);
            }

            public CtConstructor makeClassInitializer()
                throws CannotCompileException
            {
/* 997*/        throw new CannotCompileException("not a class");
            }

            public void addConstructor(CtConstructor ctconstructor)
                throws CannotCompileException
            {
/*1009*/        checkModify();
            }

            public void removeConstructor(CtConstructor ctconstructor)
                throws NotFoundException
            {
/*1019*/        checkModify();
            }

            public void addMethod(CtMethod ctmethod)
                throws CannotCompileException
            {
/*1026*/        checkModify();
            }

            public void removeMethod(CtMethod ctmethod)
                throws NotFoundException
            {
/*1036*/        checkModify();
            }

            public void addField(CtField ctfield)
                throws CannotCompileException
            {
/*1049*/        addField(ctfield, ((CtField.Initializer) (null)));
            }

            public void addField(CtField ctfield, String s)
                throws CannotCompileException
            {
/*1085*/        checkModify();
            }

            public void addField(CtField ctfield, CtField.Initializer initializer)
                throws CannotCompileException
            {
/*1113*/        checkModify();
            }

            public void removeField(CtField ctfield)
                throws NotFoundException
            {
/*1123*/        checkModify();
            }

            public byte[] getAttribute(String s)
            {
/*1144*/        return null;
            }

            public void setAttribute(String s, byte abyte0[])
            {
/*1170*/        checkModify();
            }

            public void instrument(CodeConverter codeconverter)
                throws CannotCompileException
            {
/*1184*/        checkModify();
            }

            public void instrument(ExprEditor expreditor)
                throws CannotCompileException
            {
/*1198*/        checkModify();
            }

            public Class toClass()
                throws CannotCompileException
            {
/*1224*/        return getClassPool().toClass(this);
            }

            public Class toClass(ClassLoader classloader, ProtectionDomain protectiondomain)
                throws CannotCompileException
            {
/*1261*/        ClassPool classpool = getClassPool();
/*1262*/        if(classloader == null)
/*1263*/            classloader = classpool.getClassLoader();
/*1265*/        return classpool.toClass(this, classloader, protectiondomain);
            }

            /**
             * @deprecated Method toClass is deprecated
             */

            public final Class toClass(ClassLoader classloader)
                throws CannotCompileException
            {
/*1280*/        return getClassPool().toClass(this, classloader);
            }

            public void detach()
            {
                ClassPool classpool;
                CtClass ctclass;
/*1296*/        if((ctclass = (classpool = getClassPool()).removeCached(getName())) != this)
/*1299*/            classpool.cacheCtClass(getName(), ctclass, false);
            }

            public boolean stopPruning(boolean flag)
            {
/*1326*/        return true;
            }

            public void prune()
            {
            }

            void incGetCounter()
            {
            }

            public void rebuildClassFile()
            {
            }

            public byte[] toBytecode()
                throws IOException, CannotCompileException
            {
                Object obj;
                DataOutputStream dataoutputstream;
/*1388*/        obj = new ByteArrayOutputStream();
/*1389*/        dataoutputstream = new DataOutputStream(((OutputStream) (obj)));
/*1391*/        toBytecode(dataoutputstream);
/*1394*/        dataoutputstream.close();
/*1395*/        break MISSING_BLOCK_LABEL_36;
/*1394*/        obj;
/*1394*/        dataoutputstream.close();
/*1394*/        throw obj;
/*1397*/        return ((ByteArrayOutputStream) (obj)).toByteArray();
            }

            public void writeFile()
                throws NotFoundException, IOException, CannotCompileException
            {
/*1411*/        writeFile(".");
            }

            public void writeFile(String s)
                throws CannotCompileException, IOException
            {
/*1426*/        s = makeFileOutput(s);
/*1428*/        toBytecode(s);
/*1431*/        s.close();
/*1432*/        return;
                Exception exception;
/*1431*/        exception;
/*1431*/        s.close();
/*1431*/        throw exception;
            }

            protected DataOutputStream makeFileOutput(String s)
            {
/*1436*/        String s1 = getName();
                int i;
/*1437*/        if((i = (s = (new StringBuilder()).append(s).append(File.separatorChar).append(s1.replace('.', File.separatorChar)).append(".class").toString()).lastIndexOf(File.separatorChar)) > 0 && !(i = s.substring(0, i)).equals("."))
/*1443*/            (new File(i)).mkdirs();
/*1446*/        return new DataOutputStream(new BufferedOutputStream(new DelayedFileOutputStream(s)));
            }

            public void debugWriteFile()
            {
/*1458*/        debugWriteFile(".");
            }

            public void debugWriteFile(String s)
            {
/*1472*/        try
                {
/*1472*/            boolean flag = stopPruning(true);
/*1473*/            writeFile(s);
/*1474*/            defrost();
/*1475*/            stopPruning(flag);
/*1479*/            return;
                }
/*1477*/        catch(Exception exception)
                {
/*1478*/            throw new RuntimeException(exception);
                }
            }

            public void toBytecode(DataOutputStream dataoutputstream)
                throws CannotCompileException, IOException
            {
/*1535*/        throw new CannotCompileException("not a class");
            }

            public String makeUniqueName(String s)
            {
/*1548*/        throw new RuntimeException((new StringBuilder("not available in ")).append(getName()).toString());
            }

            void compress()
            {
            }

            protected String qualifiedName;
            public static String debugDump = null;
            public static final String version = "3.18.0-GA";
            static final String javaLangObject = "java.lang.Object";
            public static CtClass booleanType;
            public static CtClass charType;
            public static CtClass byteType;
            public static CtClass shortType;
            public static CtClass intType;
            public static CtClass longType;
            public static CtClass floatType;
            public static CtClass doubleType;
            public static CtClass voidType;
            static CtClass primitiveTypes[];

            static 
            {
/* 146*/        primitiveTypes = new CtClass[9];
/* 148*/        booleanType = new CtPrimitiveType("boolean", 'Z', "java.lang.Boolean", "booleanValue", "()Z", 172, 4, 1);
/* 152*/        primitiveTypes[0] = booleanType;
/* 154*/        charType = new CtPrimitiveType("char", 'C', "java.lang.Character", "charValue", "()C", 172, 5, 1);
/* 157*/        primitiveTypes[1] = charType;
/* 159*/        byteType = new CtPrimitiveType("byte", 'B', "java.lang.Byte", "byteValue", "()B", 172, 8, 1);
/* 162*/        primitiveTypes[2] = byteType;
/* 164*/        shortType = new CtPrimitiveType("short", 'S', "java.lang.Short", "shortValue", "()S", 172, 9, 1);
/* 167*/        primitiveTypes[3] = shortType;
/* 169*/        intType = new CtPrimitiveType("int", 'I', "java.lang.Integer", "intValue", "()I", 172, 10, 1);
/* 172*/        primitiveTypes[4] = intType;
/* 174*/        longType = new CtPrimitiveType("long", 'J', "java.lang.Long", "longValue", "()J", 173, 11, 2);
/* 177*/        primitiveTypes[5] = longType;
/* 179*/        floatType = new CtPrimitiveType("float", 'F', "java.lang.Float", "floatValue", "()F", 174, 6, 1);
/* 182*/        primitiveTypes[6] = floatType;
/* 184*/        doubleType = new CtPrimitiveType("double", 'D', "java.lang.Double", "doubleValue", "()D", 175, 7, 2);
/* 187*/        primitiveTypes[7] = doubleType;
/* 189*/        voidType = new CtPrimitiveType("void", 'V', "java.lang.Void", null, null, 177, 0, 0);
/* 191*/        primitiveTypes[8] = voidType;
            }
}
