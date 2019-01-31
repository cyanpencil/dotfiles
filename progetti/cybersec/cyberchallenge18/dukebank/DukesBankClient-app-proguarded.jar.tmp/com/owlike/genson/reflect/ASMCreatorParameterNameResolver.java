// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASMCreatorParameterNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.internal.asm.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyNameResolver

public final class ASMCreatorParameterNameResolver
    implements PropertyNameResolver
{
    class ConstructorVisitor extends BaseMethodVisitor
    {

                public void visitEnd()
                {
/* 285*/            if(paramNames.size() == paramTypes.length)
                    {
/* 287*/                Class aclass[] = new Class[paramTypes.length];
/* 289*/                for(int i = 0; i < paramTypes.length; i++)
/* 290*/                    aclass[i] = resolveClass(paramTypes[i]);

/* 293*/                try
                        {
/* 293*/                    Constructor constructor = forClass.getDeclaredConstructor(aclass);
/* 294*/                    parameterNamesMap.put(constructor, ((Object) (paramNames.toArray(new String[paramNames.size()]))));
/* 301*/                    return;
                        }
/* 296*/                catch(SecurityException securityexception)
                        {
/* 297*/                    throw new JsonBindingException((new StringBuilder("Unable to locate constructor with signature ")).append(signature()).toString(), securityexception);
                        }
/* 299*/                catch(NoSuchMethodException _ex) { }
                    }
                }

                public String signature()
                {
/* 306*/            StringBuilder stringbuilder = (new StringBuilder(forClass.getSimpleName())).append("(");
/* 307*/            for(int i = 0; i < paramTypes.length; i++)
                    {
/* 308*/                String s = paramNames.isEmpty() ? "?" : (String)paramNames.get(i);
/* 309*/                stringbuilder.append(paramTypes[i].getClassName()).append((new StringBuilder(" ")).append(s).toString());
/* 310*/                if(i < paramTypes.length - 1)
/* 311*/                    stringbuilder.append(", ");
                    }

/* 314*/            stringbuilder.append(")");
/* 316*/            return stringbuilder.toString();
                }

                private final Map parameterNamesMap;
                final ASMCreatorParameterNameResolver this$0;

                public ConstructorVisitor(Class class1, boolean flag, String s, Map map)
                {
/* 279*/            this$0 = ASMCreatorParameterNameResolver.this;
/* 280*/            super(class1, flag, s, methodParameterNames);
/* 281*/            parameterNamesMap = map;
                }
    }

    class NameMethodVisitor extends BaseMethodVisitor
    {

                public void visitEnd()
                {
/* 239*/            if(paramNames.size() == paramTypes.length)
                    {
/* 241*/                Class aclass[] = new Class[paramTypes.length];
/* 243*/                for(int i = 0; i < paramTypes.length; i++)
/* 244*/                    aclass[i] = resolveClass(paramTypes[i]);

/* 247*/                try
                        {
/* 247*/                    Method method = forClass.getMethod(name, aclass);
/* 248*/                    parameterNamesMap.put(method, ((Object) (paramNames.toArray(new String[paramNames.size()]))));
/* 255*/                    return;
                        }
/* 250*/                catch(SecurityException securityexception)
                        {
/* 251*/                    throw new JsonBindingException((new StringBuilder("Unable to locate method with signature ")).append(signature()).toString(), securityexception);
                        }
/* 253*/                catch(NoSuchMethodException _ex) { }
                    }
                }

                public String signature()
                {
/* 261*/            StringBuilder stringbuilder = (new StringBuilder(name)).append("(");
/* 262*/            for(int i = 0; i < paramTypes.length; i++)
                    {
/* 263*/                String s = paramNames.isEmpty() ? "?" : (String)paramNames.get(i);
/* 264*/                stringbuilder.append(paramTypes[i].getClassName()).append((new StringBuilder(" ")).append(s).toString());
/* 265*/                if(i < paramTypes.length - 1)
/* 266*/                    stringbuilder.append(", ");
                    }

/* 269*/            stringbuilder.append(")");
/* 271*/            return stringbuilder.toString();
                }

                private final Map parameterNamesMap;
                private String name;
                final ASMCreatorParameterNameResolver this$0;

                public NameMethodVisitor(String s, Class class1, boolean flag, String s1, Map map)
                {
/* 232*/            this$0 = ASMCreatorParameterNameResolver.this;
/* 233*/            super(class1, flag, s1, map);
/* 234*/            parameterNamesMap = map;
/* 235*/            name = s;
                }
    }

    abstract class BaseMethodVisitor extends MethodVisitor
    {

                public void visitLocalVariable(String s, String s1, String s2, Label label, Label label1, int i)
                {
/* 174*/            if(!ztatic)
/* 175*/                i--;
/* 178*/            if((i >= 0 || forClass.isMemberClass() && (forClass.getModifiers() & 8) == 0) && paramNames.size() < paramTypes.length)
/* 180*/                paramNames.add(s);
                }

                protected Class resolveClass(Type type)
                {
/* 185*/            type.getSort();
/* 185*/            JVM INSTR tableswitch 0 10: default 180
                //                               0 176
                //                               1 82
                //                               2 90
                //                               3 86
                //                               4 172
                //                               5 102
                //                               6 98
                //                               7 106
                //                               8 94
                //                               9 64
                //                               10 110;
                       goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L11:
/* 187*/            return Array.newInstance(type = resolveClass(type.getElementType()), 0).getClass();
_L3:
/* 190*/            return Boolean.TYPE;
_L5:
/* 192*/            return Byte.TYPE;
_L4:
/* 194*/            return Character.TYPE;
_L10:
/* 196*/            return Double.TYPE;
_L8:
/* 198*/            return Float.TYPE;
_L7:
/* 200*/            return Integer.TYPE;
_L9:
/* 202*/            return Long.TYPE;
_L12:
/* 205*/            return Class.forName(type.getClassName(), true, forClass.getClassLoader());
                    ClassNotFoundException classnotfoundexception;
/* 206*/            classnotfoundexception;
/* 207*/            throw new JsonBindingException((new StringBuilder("Could not find class ")).append(type.getClassName()).append(" while searching for constructor ").append(signature()).append(" parameter names.").toString(), classnotfoundexception);
_L6:
/* 213*/            return Short.TYPE;
_L2:
/* 215*/            return Void.TYPE;
_L1:
/* 218*/            throw new JsonBindingException((new StringBuilder("Could not find corresponding java type to asm type ")).append(type).toString());
                }

                public abstract String signature();

                protected Type paramTypes[];
                protected ArrayList paramNames;
                protected final Class forClass;
                protected boolean ztatic;
                final ASMCreatorParameterNameResolver this$0;

                public BaseMethodVisitor(Class class1, boolean flag, String s, Map map)
                {
/* 163*/            this$0 = ASMCreatorParameterNameResolver.this;
/* 164*/            super(0x50000);
/* 165*/            forClass = class1;
/* 166*/            ztatic = flag;
/* 167*/            paramTypes = Type.getArgumentTypes(s);
/* 168*/            paramNames = new ArrayList(paramTypes.length);
                }
    }

    class ClassConstructorsVisitor extends ClassVisitor
    {

                public MethodVisitor visitMethod(int i, String s, String s1, String s2, String as[])
                {
/* 143*/            s2 = (i & 8) <= 0 ? 0 : 1;
/* 144*/            if((i & 0x400) == 0)
                    {
/* 145*/                if("<init>".equals(s))
/* 146*/                    return new ConstructorVisitor(forClass, s2, s1, ctrParameterNames);
/* 148*/                if(!"<clinit>".equals(s))
/* 149*/                    return new NameMethodVisitor(s, forClass, s2, s1, methodParameterNames);
                    }
/* 151*/            return null;
                }

                private static final String CONSTRUCTOR_METHOD_NAME = "<init>";
                private final Class forClass;
                final Map ctrParameterNames;
                final Map methodParameterNames;
                final ASMCreatorParameterNameResolver this$0;

                public ClassConstructorsVisitor(Class class1, Map map, Map map1)
                {
/* 134*/            this$0 = ASMCreatorParameterNameResolver.this;
/* 135*/            super(0x50000);
/* 136*/            forClass = class1;
/* 137*/            ctrParameterNames = map;
/* 138*/            methodParameterNames = map1;
                }
    }


            public ASMCreatorParameterNameResolver(boolean flag)
            {
/*  49*/        doThrowException = flag;
            }

            protected final void read(Class class1)
            {
                Object obj;
/*  53*/        obj = class1.getName();
/*  54*/        obj = (new StringBuilder()).append(((String) (obj)).replace('.', '/')).append(".class").toString();
/*  57*/        if(class1.getClassLoader() == null)
/*  58*/            obj = ClassLoader.getSystemClassLoader().getResourceAsStream(((String) (obj)));
/*  59*/        else
/*  59*/            obj = class1.getClassLoader().getResourceAsStream(((String) (obj)));
/*  62*/        class1 = new ClassConstructorsVisitor(class1, constructorParameterNames, methodParameterNames);
                ClassReader classreader;
/*  64*/        try
                {
/*  64*/            (classreader = new ClassReader(((InputStream) (obj)))).accept(class1, 0);
                }
/*  66*/        catch(IOException _ex)
                {
/*  70*/            try
                    {
/*  70*/                if(obj != null)
/*  71*/                    ((InputStream) (obj)).close();
/*  74*/                return;
                    }
/*  73*/            catch(IOException _ex2)
                    {
/*  75*/                return;
                    }
                }
/*  69*/        finally { }
/*  70*/        try
                {
/*  70*/            if(obj != null)
/*  71*/                ((InputStream) (obj)).close();
/*  74*/            return;
                }
/*  73*/        catch(IOException _ex)
                {
/*  75*/            return;
                }
/*  70*/        try
                {
/*  70*/            if(obj != null)
/*  71*/                ((InputStream) (obj)).close();
                }
/*  73*/        catch(IOException _ex) { }
/*  74*/        throw class1;
            }

            public final String resolve(int i, Constructor constructor)
            {
                String as[];
/*  79*/        if((as = (String[])constructorParameterNames.get(constructor)) == null)
                {
/*  81*/            read(constructor.getDeclaringClass());
/*  82*/            as = (String[])constructorParameterNames.get(constructor);
                }
/*  85*/        if(as == null || as.length <= i)
                {
/*  86*/            if(doThrowException)
/*  87*/                _throwNoDebugInfo(constructor.getDeclaringClass().getName());
/*  88*/            return null;
                } else
                {
/*  91*/            return as[i];
                }
            }

            public final String resolve(Field field)
            {
/*  95*/        return null;
            }

            public final String resolve(Method method)
            {
/*  99*/        return null;
            }

            public final String resolve(int i, Method method)
            {
                String as[];
/* 103*/        if((as = (String[])methodParameterNames.get(method)) == null)
                {
/* 105*/            read(method.getDeclaringClass());
/* 106*/            as = (String[])methodParameterNames.get(method);
                }
/* 109*/        if(as == null || as.length <= i)
                {
/* 110*/            if(doThrowException)
/* 111*/                _throwNoDebugInfo(method.getDeclaringClass().getName());
/* 112*/            return null;
                } else
                {
/* 115*/            return as[i];
                }
            }

            private void _throwNoDebugInfo(String s)
            {
/* 119*/        throw new JsonBindingException((new StringBuilder("Class ")).append(s).append(" has been compiled with no debug information, so we can not deduce constructor/method parameter names.").toString());
            }

            private final boolean doThrowException;
            private final Map constructorParameterNames = new ConcurrentHashMap();
            private final Map methodParameterNames = new ConcurrentHashMap();

}
