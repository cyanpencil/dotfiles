// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryHelper.java

package javassist.util.proxy;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.bytecode.ClassFile;

// Referenced classes of package javassist.util.proxy:
//            SecurityActions

public class FactoryHelper
{

            public FactoryHelper()
            {
            }

            public static final int typeIndex(Class class1)
            {
                Class aclass[];
/*  66*/        int i = (aclass = primitiveTypes).length;
/*  68*/        for(int j = 0; j < i; j++)
/*  69*/            if(aclass[j] == class1)
/*  70*/                return j;

/*  72*/        throw new RuntimeException((new StringBuilder("bad type:")).append(class1.getName()).toString());
            }

            public static Class toClass(ClassFile classfile, ClassLoader classloader)
                throws CannotCompileException
            {
/* 137*/        return toClass(classfile, classloader, null);
            }

            public static Class toClass(ClassFile classfile, ClassLoader classloader, ProtectionDomain protectiondomain)
                throws CannotCompileException
            {
                Method method;
/* 150*/        byte abyte0[] = toBytecode(classfile);
/* 153*/        if(protectiondomain == null)
                {
/* 154*/            method = defineClass1;
/* 155*/            classfile = ((ClassFile) (new Object[] {
/* 155*/                classfile.getName(), abyte0, new Integer(0), new Integer(abyte0.length)
                    }));
                } else
                {
/* 159*/            method = defineClass2;
/* 160*/            classfile = ((ClassFile) (new Object[] {
/* 160*/                classfile.getName(), abyte0, new Integer(0), new Integer(abyte0.length), protectiondomain
                    }));
                }
/* 164*/        return toClass2(method, classloader, classfile);
/* 166*/        JVM INSTR dup ;
                RuntimeException runtimeexception;
/* 167*/        runtimeexception;
/* 167*/        throw ;
                Object obj;
/* 169*/        obj;
/* 170*/        throw new CannotCompileException(((InvocationTargetException) (obj)).getTargetException());
/* 172*/        obj;
/* 173*/        throw new CannotCompileException(((Throwable) (obj)));
            }

            private static synchronized Class toClass2(Method method, ClassLoader classloader, Object aobj[])
                throws Exception
            {
/* 181*/        SecurityActions.setAccessible(method, true);
/* 182*/        classloader = (Class)method.invoke(classloader, aobj);
/* 183*/        SecurityActions.setAccessible(method, false);
/* 184*/        return classloader;
            }

            private static byte[] toBytecode(ClassFile classfile)
                throws IOException
            {
                ByteArrayOutputStream bytearrayoutputstream;
                DataOutputStream dataoutputstream;
/* 188*/        bytearrayoutputstream = new ByteArrayOutputStream();
/* 189*/        dataoutputstream = new DataOutputStream(bytearrayoutputstream);
/* 191*/        classfile.write(dataoutputstream);
/* 194*/        dataoutputstream.close();
/* 195*/        break MISSING_BLOCK_LABEL_36;
/* 194*/        classfile;
/* 194*/        dataoutputstream.close();
/* 194*/        throw classfile;
/* 197*/        return bytearrayoutputstream.toByteArray();
            }

            public static void writeFile(ClassFile classfile, String s)
                throws CannotCompileException
            {
/* 206*/        try
                {
/* 206*/            writeFile0(classfile, s);
/* 210*/            return;
                }
                // Misplaced declaration of an exception variable
/* 208*/        catch(ClassFile classfile)
                {
/* 209*/            throw new CannotCompileException(classfile);
                }
            }

            private static void writeFile0(ClassFile classfile, String s)
                throws CannotCompileException, IOException
            {
                Object obj;
/* 215*/        obj = classfile.getName();
                int i;
/* 216*/        if((i = (s = (new StringBuilder()).append(s).append(File.separatorChar).append(((String) (obj)).replace('.', File.separatorChar)).append(".class").toString()).lastIndexOf(File.separatorChar)) > 0 && !(i = s.substring(0, i)).equals("."))
/* 222*/            (new File(i)).mkdirs();
/* 225*/        i = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(s)));
/* 228*/        classfile.write(i);
/* 234*/        i.close();
/* 235*/        return;
/* 230*/        JVM INSTR dup ;
/* 231*/        classfile;
/* 231*/        throw ;
/* 234*/        classfile;
/* 234*/        i.close();
/* 234*/        throw classfile;
            }

            private static Method defineClass1;
            private static Method defineClass2;
            public static final Class primitiveTypes[];
            public static final String wrapperTypes[] = {
/*  86*/        "java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Void"
            };
            public static final String wrapperDesc[] = {
/*  95*/        "(Z)V", "(B)V", "(C)V", "(S)V", "(I)V", "(J)V", "(F)V", "(D)V"
            };
            public static final String unwarpMethods[] = {
/* 106*/        "booleanValue", "byteValue", "charValue", "shortValue", "intValue", "longValue", "floatValue", "doubleValue"
            };
            public static final String unwrapDesc[] = {
/* 115*/        "()Z", "()B", "()C", "()S", "()I", "()J", "()F", "()D"
            };
            public static final int dataSize[] = {
/* 123*/        1, 1, 1, 1, 1, 2, 1, 2
            };

            static 
            {
/*  42*/        try
                {
                    Class class1;
/*  42*/            defineClass1 = SecurityActions.getDeclaredMethod(class1 = Class.forName("java.lang.ClassLoader"), "defineClass", new Class[] {
/*  43*/                java/lang/String, [B, Integer.TYPE, Integer.TYPE
                    });
/*  49*/            defineClass2 = SecurityActions.getDeclaredMethod(class1, "defineClass", new Class[] {
/*  49*/                java/lang/String, [B, Integer.TYPE, Integer.TYPE, java/security/ProtectionDomain
                    });
                }
/*  55*/        catch(Exception _ex)
                {
/*  56*/            throw new RuntimeException("cannot initialize");
                }
/*  78*/        primitiveTypes = (new Class[] {
/*  78*/            Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE
                });
            }
}
