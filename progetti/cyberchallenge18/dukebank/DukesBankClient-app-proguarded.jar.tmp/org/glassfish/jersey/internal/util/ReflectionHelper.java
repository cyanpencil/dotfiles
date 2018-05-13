// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.security.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.OsgiRegistry;
import org.glassfish.jersey.internal.util.collection.ClassTypePair;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            TypeVisitor

public final class ReflectionHelper
{
    public static class DeclaringClassInterfacePair
    {

                public final Class concreteClass;
                public final Class declaringClass;
                public final Type genericInterface;

                private DeclaringClassInterfacePair(Class class1, Class class2, Type type)
                {
/*1000*/            concreteClass = class1;
/*1001*/            declaringClass = class2;
/*1002*/            genericInterface = type;
                }

    }


            private ReflectionHelper()
            {
/* 107*/        throw new AssertionError("No instances allowed.");
            }

            public static Class getDeclaringClass(AccessibleObject accessibleobject)
            {
/* 122*/        if((accessibleobject instanceof Member) && ((accessibleobject instanceof Field) || (accessibleobject instanceof Method) || (accessibleobject instanceof Constructor)))
/* 123*/            return ((Member)accessibleobject).getDeclaringClass();
/* 125*/        else
/* 125*/            throw new IllegalArgumentException((new StringBuilder("Unsupported accessible object type: ")).append(accessibleobject.getClass().getName()).toString());
            }

            public static String objectToString(Object obj)
            {
/* 146*/        if(obj == null)
/* 147*/            return "null";
/* 149*/        else
/* 149*/            return (new StringBuilder()).append(obj.getClass().getName()).append('@').append(Integer.toHexString(obj.hashCode())).toString();
            }

            public static String methodInstanceToString(Object obj, Method method)
            {
                StringBuilder stringbuilder;
/* 173*/        (stringbuilder = new StringBuilder()).append(obj.getClass().getName()).append('@').append(Integer.toHexString(obj.hashCode())).append('.').append(method.getName()).append('(');
/* 178*/        obj = method.getParameterTypes();
/* 179*/        for(method = 0; method < obj.length; method++)
                {
/* 180*/            stringbuilder.append(getTypeName(obj[method]));
/* 181*/            if(method < obj.length - 1)
/* 182*/                stringbuilder.append(",");
                }

/* 186*/        stringbuilder.append(')');
/* 188*/        return stringbuilder.toString();
            }

            private static String getTypeName(Class class1)
            {
/* 202*/        if(class1.isArray())
                {
/* 203*/            class1 = class1;
/* 204*/            int i = 0;
/* 205*/            for(; class1.isArray(); class1 = class1.getComponentType())
/* 206*/                i++;

                    StringBuilder stringbuilder;
/* 209*/            (stringbuilder = new StringBuilder()).append(class1.getName());
/* 211*/            for(class1 = 0; class1 < i; class1++)
/* 212*/                stringbuilder.append("[]");

/* 214*/            return stringbuilder.toString();
                } else
                {
/* 216*/            return class1.getName();
                }
            }

            public static PrivilegedAction classForNamePA(String s)
            {
/* 236*/        return classForNamePA(s, getContextClassLoader());
            }

            public static PrivilegedAction classForNamePA(String s, ClassLoader classloader)
            {
/* 253*/        return new PrivilegedAction(classloader, s) {

                    public final Class run()
                    {
/* 256*/                if(cl == null)
/* 258*/                    break MISSING_BLOCK_LABEL_85;
/* 258*/                return Class.forName(name, false, cl);
                        ClassNotFoundException classnotfoundexception;
/* 259*/                classnotfoundexception;
/* 260*/                if(ReflectionHelper.LOGGER.isLoggable(Level.FINER))
/* 261*/                    ReflectionHelper.LOGGER.log(Level.FINER, (new StringBuilder("Unable to load class ")).append(name).append(" using the supplied class loader ").append(cl.getClass().getName()).append(".").toString(), classnotfoundexception);
/* 268*/                return Class.forName(name);
/* 269*/                classnotfoundexception;
/* 270*/                if(ReflectionHelper.LOGGER.isLoggable(Level.FINER))
/* 271*/                    ReflectionHelper.LOGGER.log(Level.FINER, (new StringBuilder("Unable to load class ")).append(name).append(" using the current class loader.").toString(), classnotfoundexception);
/* 275*/                return null;
                    }

                    public final volatile Object run()
                    {
/* 253*/                return run();
                    }

                    final ClassLoader val$cl;
                    final String val$name;

                    
                    {
/* 253*/                cl = classloader;
/* 253*/                name = s;
/* 253*/                super();
                    }
        };
            }

            public static PrivilegedAction getClassLoaderPA(Class class1)
            {
/* 290*/        return new PrivilegedAction(class1) {

                    public final ClassLoader run()
                    {
/* 293*/                return clazz.getClassLoader();
                    }

                    public final volatile Object run()
                    {
/* 290*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 290*/                clazz = class1;
/* 290*/                super();
                    }
        };
            }

            public static PrivilegedAction getDeclaredFieldsPA(Class class1)
            {
/* 308*/        return new PrivilegedAction(class1) {

                    public final Field[] run()
                    {
/* 311*/                return clazz.getDeclaredFields();
                    }

                    public final volatile Object run()
                    {
/* 308*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 308*/                clazz = class1;
/* 308*/                super();
                    }
        };
            }

            public static PrivilegedAction getAllFieldsPA(Class class1)
            {
/* 326*/        return new PrivilegedAction(class1) {

                    public final Field[] run()
                    {
/* 329*/                ArrayList arraylist = new ArrayList();
/* 330*/                recurse(clazz, arraylist);
/* 331*/                return (Field[])arraylist.toArray(new Field[arraylist.size()]);
                    }

                    private void recurse(Class class2, List list)
                    {
/* 335*/                do
                        {
/* 335*/                    list.addAll(Arrays.asList(class2.getDeclaredFields()));
/* 336*/                    if(class2.getSuperclass() != null)
                            {
/* 337*/                        class2 = class2.getSuperclass();
/* 337*/                        this = this;
                            } else
                            {
/* 339*/                        return;
                            }
                        } while(true);
                    }

                    public final volatile Object run()
                    {
/* 326*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 326*/                clazz = class1;
/* 326*/                super();
                    }
        };
            }

            public static PrivilegedAction getDeclaredMethodsPA(Class class1)
            {
/* 353*/        return new PrivilegedAction(class1) {

                    public final Collection run()
                    {
/* 356*/                return Arrays.asList(clazz.getDeclaredMethods());
                    }

                    public final volatile Object run()
                    {
/* 353*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 353*/                clazz = class1;
/* 353*/                super();
                    }
        };
            }

            public static PrivilegedExceptionAction classForNameWithExceptionPEA(String s)
                throws ClassNotFoundException
            {
/* 378*/        return classForNameWithExceptionPEA(s, getContextClassLoader());
            }

            public static PrivilegedExceptionAction classForNameWithExceptionPEA(String s, ClassLoader classloader)
                throws ClassNotFoundException
            {
/* 398*/        return new PrivilegedExceptionAction(classloader, s) {

                    public final Class run()
                        throws ClassNotFoundException
                    {
/* 401*/                if(cl == null)
/* 403*/                    break MISSING_BLOCK_LABEL_21;
/* 403*/                return Class.forName(name, false, cl);
/* 404*/                JVM INSTR pop ;
/* 408*/                return Class.forName(name);
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/* 398*/                return run();
                    }

                    final ClassLoader val$cl;
                    final String val$name;

                    
                    {
/* 398*/                cl = classloader;
/* 398*/                name = s;
/* 398*/                super();
                    }
        };
            }

            public static PrivilegedAction getContextClassLoaderPA()
            {
/* 423*/        return new PrivilegedAction() {

                    public final ClassLoader run()
                    {
/* 426*/                return Thread.currentThread().getContextClassLoader();
                    }

                    public final volatile Object run()
                    {
/* 423*/                return run();
                    }

        };
            }

            private static ClassLoader getContextClassLoader()
            {
/* 437*/        return (ClassLoader)AccessController.doPrivileged(getContextClassLoaderPA());
            }

            public static PrivilegedAction setContextClassLoaderPA(ClassLoader classloader)
            {
/* 450*/        return new PrivilegedAction(classloader) {

                    public final Object run()
                    {
/* 453*/                Thread.currentThread().setContextClassLoader(classLoader);
/* 454*/                return null;
                    }

                    final ClassLoader val$classLoader;

                    
                    {
/* 450*/                classLoader = classloader;
/* 450*/                super();
                    }
        };
            }

            public static PrivilegedAction setAccessibleMethodPA(Method method)
            {
/* 469*/        if(Modifier.isPublic(method.getModifiers()))
/* 470*/            return NoOpPrivilegedACTION;
/* 473*/        else
/* 473*/            return new PrivilegedAction(method) {

                        public final Object run()
                        {
/* 477*/                    if(!m.isAccessible())
/* 478*/                        m.setAccessible(true);
/* 480*/                    return m;
                        }

                        final Method val$m;

                    
                    {
/* 473*/                m = method;
/* 473*/                super();
                    }
            };
            }

            public static List getGenericTypeArgumentClasses(Type type)
                throws IllegalArgumentException
            {
/* 509*/        if((type = getTypeArguments(type)) == null)
/* 511*/            return Collections.emptyList();
/* 514*/        else
/* 514*/            return Lists.newArrayList(Collections2.transform(Arrays.asList(type), new Function() {

                        public final Class apply(Type type1)
                        {
/* 518*/                    return ReflectionHelper.erasure(type1);
                        }

                        public final volatile Object apply(Object obj)
                        {
/* 514*/                    return apply((Type)obj);
                        }

            }));
            }

            public static List getTypeArgumentAndClass(Type type)
                throws IllegalArgumentException
            {
/* 547*/        if((type = getTypeArguments(type)) == null)
/* 549*/            return Collections.emptyList();
/* 552*/        else
/* 552*/            return Lists.newArrayList(Collections2.transform(Arrays.asList(type), new Function() {

                        public final ClassTypePair apply(Type type1)
                        {
/* 556*/                    return ClassTypePair.of(ReflectionHelper.erasure(type1), type1);
                        }

                        public final volatile Object apply(Object obj)
                        {
/* 552*/                    return apply((Type)obj);
                        }

            }));
            }

            public static boolean isPrimitive(Type type)
            {
/* 568*/        if(type instanceof Class)
/* 569*/            return (type = (Class)type).isPrimitive();
/* 572*/        else
/* 572*/            return false;
            }

            public static Type[] getTypeArguments(Type type)
            {
/* 585*/        if(!(type instanceof ParameterizedType))
/* 586*/            return null;
/* 589*/        else
/* 589*/            return ((ParameterizedType)type).getActualTypeArguments();
            }

            public static Type getTypeArgument(Type type, int i)
            {
/* 604*/        if(type instanceof ParameterizedType)
/* 605*/            return fix((type = (ParameterizedType)type).getActualTypeArguments()[i]);
/* 609*/        else
/* 609*/            return null;
            }

            private static Type fix(Type type)
            {
/* 619*/        if(!(type instanceof GenericArrayType))
/* 620*/            return type;
                GenericArrayType genericarraytype;
/* 623*/        if((genericarraytype = (GenericArrayType)type).getGenericComponentType() instanceof Class)
/* 625*/            return Array.newInstance(type = (Class)genericarraytype.getGenericComponentType(), 0).getClass();
/* 629*/        else
/* 629*/            return type;
            }

            public static Class erasure(Type type)
            {
/* 677*/        return (Class)eraser.visit(type);
            }

            public static boolean isSubClassOf(Type type, Type type1)
            {
/* 689*/        return erasure(type1).isAssignableFrom(erasure(type));
            }

            public static boolean isArray(Type type)
            {
/* 699*/        if(type instanceof Class)
/* 700*/            return (type = (Class)type).isArray();
/* 703*/        else
/* 703*/            return type instanceof GenericArrayType;
            }

            public static boolean isArrayOfType(Type type, Class class1)
            {
/* 715*/        if(type instanceof Class)
/* 716*/            return (type = (Class)type).isArray() && type != [B;
/* 719*/        if(type instanceof GenericArrayType)
/* 720*/            return (type = ((GenericArrayType)type).getGenericComponentType()) == class1;
/* 723*/        else
/* 723*/            return false;
            }

            public static Type getArrayComponentType(Type type)
            {
/* 734*/        if(type instanceof Class)
/* 735*/            return (type = (Class)type).getComponentType();
/* 738*/        if(type instanceof GenericArrayType)
/* 739*/            return ((GenericArrayType)type).getGenericComponentType();
/* 742*/        else
/* 742*/            throw new IllegalArgumentException();
            }

            public static Class getArrayForComponentType(Class class1)
            {
/* 753*/        return (class1 = ((Class) (Array.newInstance(class1, 0)))).getClass();
/* 755*/        class1;
/* 756*/        throw new IllegalArgumentException(class1);
            }

            public static PrivilegedAction getValueOfStringMethodPA(Class class1)
            {
/* 772*/        return getStringToObjectMethodPA(class1, "valueOf");
            }

            public static PrivilegedAction getFromStringStringMethodPA(Class class1)
            {
/* 787*/        return getStringToObjectMethodPA(class1, "fromString");
            }

            private static PrivilegedAction getStringToObjectMethodPA(Class class1, String s)
            {
/* 802*/        return new PrivilegedAction(class1, s) {

                    public final Method run()
                    {
                        Method method;
/* 806*/                if(Modifier.isStatic((method = clazz.getDeclaredMethod(methodName, new Class[] {
/* 806*/            java/lang/String
        })).getModifiers()) && method.getReturnType() == clazz)
/* 808*/                    return method;
/* 810*/                else
/* 810*/                    return null;
/* 811*/                JVM INSTR pop ;
/* 812*/                return null;
                    }

                    public final volatile Object run()
                    {
/* 802*/                return run();
                    }

                    final Class val$clazz;
                    final String val$methodName;

                    
                    {
/* 802*/                clazz = class1;
/* 802*/                methodName = s;
/* 802*/                super();
                    }
        };
            }

            public static PrivilegedAction getStringConstructorPA(Class class1)
            {
/* 829*/        return new PrivilegedAction(class1) {

                    public final Constructor run()
                    {
/* 833*/                return clazz.getConstructor(new Class[] {
/* 833*/                    java/lang/String
                        });
/* 834*/                JVM INSTR dup ;
                        SecurityException securityexception;
/* 835*/                securityexception;
/* 835*/                throw ;
/* 836*/                JVM INSTR pop ;
/* 837*/                return null;
                    }

                    public final volatile Object run()
                    {
/* 829*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 829*/                clazz = class1;
/* 829*/                super();
                    }
        };
            }

            public static PrivilegedAction getDeclaredConstructorsPA(Class class1)
            {
/* 853*/        return new PrivilegedAction(class1) {

                    public final Constructor[] run()
                    {
/* 856*/                return clazz.getDeclaredConstructors();
                    }

                    public final volatile Object run()
                    {
/* 853*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 853*/                clazz = class1;
/* 853*/                super();
                    }
        };
            }

            public static Collection getAnnotationTypes(AnnotatedElement annotatedelement, Class class1)
            {
/* 872*/        Set set = Sets.newIdentityHashSet();
/* 873*/        int i = (annotatedelement = annotatedelement.getAnnotations()).length;
/* 873*/        for(int j = 0; j < i; j++)
                {
                    Class class2;
/* 873*/            class2 = (class2 = annotatedelement[j]).annotationType();
/* 875*/            if(class1 == null || class2.getAnnotation(class1) != null)
/* 876*/                set.add(class2);
                }

/* 879*/        return set;
            }

            public static boolean isGetter(Method method)
            {
/* 889*/        if(method.getParameterTypes().length == 0 && Modifier.isPublic(method.getModifiers()))
                {
                    String s;
/* 891*/            if((s = method.getName()).startsWith("get"))
/* 894*/                return !Void.TYPE.equals(method.getReturnType());
/* 895*/            if(s.startsWith("is"))
/* 896*/                return Boolean.TYPE.equals(method.getReturnType()) || java/lang/Boolean.equals(method.getReturnType());
                }
/* 899*/        return false;
            }

            public static GenericType genericTypeFor(Object obj)
            {
/* 916*/        return ((GenericType) (obj = (obj instanceof GenericEntity) ? ((Object) (new GenericType(((GenericEntity)obj).getType()))) : ((Object) (obj != null ? ((Object) (new GenericType(obj.getClass()))) : null))));
            }

            public static boolean isSetter(Method method)
            {
/* 931*/        return Modifier.isPublic(method.getModifiers()) && Void.TYPE.equals(method.getReturnType()) && method.getParameterTypes().length == 1 && method.getName().startsWith("set");
            }

            public static String getPropertyName(Method method)
            {
/* 944*/        if(!isGetter(method) && !isSetter(method))
                {
/* 945*/            throw new IllegalArgumentException(LocalizationMessages.METHOD_NOT_GETTER_NOR_SETTER());
                } else
                {
/* 948*/            byte byte0 = ((byte)((method = method.getName()).startsWith("is") ? 2 : 3));
/* 951*/            (method = method.toCharArray())[byte0] = Character.toLowerCase(method[byte0]);
/* 954*/            return new String(method, byte0, method.length - byte0);
                }
            }

            public static Class theMostSpecificTypeOf(Set set)
            {
/* 964*/        Class class1 = null;
/* 965*/        set = set.iterator();
/* 965*/        do
                {
/* 965*/            if(!set.hasNext())
/* 965*/                break;
                    Object obj;
/* 965*/            obj = (Class)(obj = (Type)set.next());
/* 967*/            if(class1 == null)
/* 968*/                class1 = ((Class) (obj));
/* 970*/            else
/* 970*/            if(class1.isAssignableFrom(((Class) (obj))))
/* 971*/                class1 = ((Class) (obj));
                } while(true);
/* 975*/        return class1;
            }

            public static Class[] getParameterizedClassArguments(DeclaringClassInterfacePair declaringclassinterfacepair)
            {
/*1015*/        if(declaringclassinterfacepair.genericInterface instanceof ParameterizedType)
                {
                    ParameterizedType parameterizedtype;
                    Type atype[];
/*1016*/            Class aclass[] = new Class[(atype = (parameterizedtype = (ParameterizedType)declaringclassinterfacepair.genericInterface).getActualTypeArguments()).length];
/*1020*/            for(int i = 0; i < atype.length; i++)
                    {
                        Object obj;
/*1021*/                if((obj = atype[i]) instanceof Class)
                        {
/*1023*/                    aclass[i] = (Class)obj;
/*1023*/                    continue;
                        }
/*1024*/                if(obj instanceof ParameterizedType)
                        {
/*1025*/                    obj = (ParameterizedType)obj;
/*1026*/                    aclass[i] = (Class)((ParameterizedType) (obj)).getRawType();
/*1026*/                    continue;
                        }
/*1027*/                if(obj instanceof TypeVariable)
                        {
/*1028*/                    obj = (TypeVariable)obj;
/*1029*/                    ClassTypePair classtypepair = resolveTypeVariable(declaringclassinterfacepair.concreteClass, declaringclassinterfacepair.declaringClass, ((TypeVariable) (obj)));
/*1030*/                    aclass[i] = classtypepair == null ? (Class)((TypeVariable) (obj)).getBounds()[0] : classtypepair.rawClass();
/*1031*/                    continue;
                        }
                        Type type;
/*1031*/                if((obj instanceof GenericArrayType) && ((type = ((GenericArrayType) (obj = (GenericArrayType)obj)).getGenericComponentType()) instanceof Class))
/*1035*/                    aclass[i] = getArrayForComponentType((Class)type);
                    }

/*1039*/            return aclass;
                } else
                {
/*1041*/            return null;
                }
            }

            public static Type[] getParameterizedTypeArguments(DeclaringClassInterfacePair declaringclassinterfacepair)
            {
/*1054*/        if(declaringclassinterfacepair.genericInterface instanceof ParameterizedType)
                {
                    ParameterizedType parameterizedtype;
                    Type atype[];
/*1055*/            Type atype1[] = new Type[(atype = (parameterizedtype = (ParameterizedType)declaringclassinterfacepair.genericInterface).getActualTypeArguments()).length];
/*1059*/            for(int i = 0; i < atype.length; i++)
                    {
                        Object obj;
/*1060*/                if((obj = atype[i]) instanceof Class)
                        {
/*1062*/                    atype1[i] = ((Type) (obj));
/*1062*/                    continue;
                        }
/*1063*/                if(obj instanceof ParameterizedType)
                        {
/*1064*/                    atype1[i] = ((Type) (obj));
/*1064*/                    continue;
                        }
/*1065*/                if(!(obj instanceof TypeVariable))
/*1066*/                    continue;
/*1066*/                if((obj = resolveTypeVariable(declaringclassinterfacepair.concreteClass, declaringclassinterfacepair.declaringClass, (TypeVariable)obj)) == null)
/*1068*/                    throw new IllegalArgumentException(LocalizationMessages.ERROR_RESOLVING_GENERIC_TYPE_VALUE(declaringclassinterfacepair.genericInterface, declaringclassinterfacepair.concreteClass));
/*1071*/                atype1[i] = ((ClassTypePair) (obj)).type();
                    }

/*1074*/            return atype1;
                } else
                {
/*1076*/            return null;
                }
            }

            public static DeclaringClassInterfacePair getClass(Class class1, Class class2)
            {
/*1090*/        return getClass(class1, class2, class1);
            }

            private static DeclaringClassInterfacePair getClass(Class class1, Class class2, Class class3)
            {
/*1094*/        do
                {
/*1094*/            Type atype[] = class3.getGenericInterfaces();
                    DeclaringClassInterfacePair declaringclassinterfacepair;
/*1095*/            if((declaringclassinterfacepair = getType(class1, class2, class3, atype)) != null)
/*1097*/                return declaringclassinterfacepair;
/*1100*/            if((class3 = class3.getSuperclass()) == null || class3 == java/lang/Object)
/*1102*/                return null;
/*1105*/            class2 = class2;
/*1105*/            class1 = class1;
                } while(true);
            }

            private static DeclaringClassInterfacePair getType(Class class1, Class class2, Class class3, Type atype[])
            {
/*1112*/        int i = (atype = atype).length;
/*1112*/        for(int j = 0; j < i; j++)
                {
/*1112*/            Object obj = atype[j];
/*1113*/            if((obj = getType(class1, class2, class3, ((Type) (obj)))) != null)
/*1115*/                return ((DeclaringClassInterfacePair) (obj));
                }

/*1118*/        return null;
            }

            private static DeclaringClassInterfacePair getType(Class class1, Class class2, Class class3, Type type)
            {
/*1125*/        if(type instanceof Class)
/*1126*/            if(type == class2)
/*1127*/                return new DeclaringClassInterfacePair(class1, class3, type);
/*1129*/            else
/*1129*/                return getClass(class1, class2, (Class)type);
                ParameterizedType parameterizedtype;
/*1131*/        if(type instanceof ParameterizedType)
                {
/*1132*/            if((parameterizedtype = (ParameterizedType)type).getRawType() == class2)
/*1134*/                return new DeclaringClassInterfacePair(class1, class3, type);
/*1136*/            else
/*1136*/                return getClass(class1, class2, (Class)parameterizedtype.getRawType());
                } else
                {
/*1139*/            return null;
                }
            }

            public static ClassTypePair resolveGenericType(Class class1, Class class2, Class class3, Type type)
            {
                ClassTypePair classtypepair1;
/*1157*/        if(type instanceof TypeVariable)
                {
                    ClassTypePair classtypepair;
/*1158*/            if((classtypepair = resolveTypeVariable(class1, class2, (TypeVariable)type)) != null)
/*1164*/                return classtypepair;
/*1166*/            break MISSING_BLOCK_LABEL_226;
                }
/*1166*/        if(type instanceof ParameterizedType)
                {
                    ParameterizedType parameterizedtype;
/*1167*/            Type atype[] = (parameterizedtype = (ParameterizedType)type).getActualTypeArguments();
/*1169*/            boolean flag = false;
/*1170*/            for(int i = 0; i < atype.length; i++)
                    {
                        ClassTypePair classtypepair2;
/*1171*/                if((classtypepair2 = resolveGenericType(class1, class2, (Class)parameterizedtype.getRawType(), atype[i])).type() != atype[i])
                        {
/*1174*/                    atype[i] = classtypepair2.type();
/*1175*/                    flag = true;
                        }
                    }

/*1178*/            if(flag)
                    {
/*1179*/                ParameterizedType parameterizedtype1 = new ParameterizedType(atype, parameterizedtype) {

                            public final Type[] getActualTypeArguments()
                            {
/*1183*/                        return (Type[])ptts.clone();
                            }

                            public final Type getRawType()
                            {
/*1188*/                        return pt.getRawType();
                            }

                            public final Type getOwnerType()
                            {
/*1193*/                        return pt.getOwnerType();
                            }

                            final Type val$ptts[];
                            final ParameterizedType val$pt;

                    
                    {
/*1179*/                ptts = atype;
/*1179*/                pt = parameterizedtype;
/*1179*/                super();
                    }
                };
/*1196*/                return ClassTypePair.of((Class)parameterizedtype.getRawType(), parameterizedtype1);
                    }
/*1198*/            break MISSING_BLOCK_LABEL_226;
                }
/*1198*/        if(!(type instanceof GenericArrayType))
/*1199*/            break MISSING_BLOCK_LABEL_226;
/*1199*/        GenericArrayType genericarraytype = (GenericArrayType)type;
/*1200*/        classtypepair1 = resolveGenericType(class1, class2, null, genericarraytype.getGenericComponentType());
/*1202*/        if(genericarraytype.getGenericComponentType() == classtypepair1.type())
/*1204*/            break MISSING_BLOCK_LABEL_226;
                Class class4;
/*1204*/        return ClassTypePair.of(class4 = getArrayForComponentType(classtypepair1.rawClass()));
                Exception exception;
/*1206*/        exception;
/*1207*/        LOGGER.log(Level.FINEST, "", exception);
/*1212*/        return ClassTypePair.of(class3, type);
            }

            public static ClassTypePair resolveTypeVariable(Class class1, Class class2, TypeVariable typevariable)
            {
/*1225*/        return resolveTypeVariable(class1, class2, typevariable, ((Map) (new HashMap())));
            }

            private static ClassTypePair resolveTypeVariable(Class class1, Class class2, TypeVariable typevariable, Map map)
            {
/*1230*/        do
                {
                    Type atype[];
/*1230*/            int i = (atype = atype = class1.getGenericInterfaces()).length;
/*1231*/            for(int j = 0; j < i; j++)
                    {
                        Object obj;
/*1231*/                if(((obj = atype[j]) instanceof ParameterizedType) && (obj = resolveTypeVariable(((ParameterizedType) (obj = (ParameterizedType)obj)), (Class)((ParameterizedType) (obj)).getRawType(), class2, typevariable, map)) != null)
/*1237*/                    return ((ClassTypePair) (obj));
                    }

                    Type type;
                    ParameterizedType parameterizedtype;
/*1242*/            if((type = class1.getGenericSuperclass()) instanceof ParameterizedType)
/*1245*/                return resolveTypeVariable(parameterizedtype = (ParameterizedType)type, class1.getSuperclass(), class2, typevariable, map);
/*1247*/            if(type instanceof Class)
                    {
/*1248*/                typevariable = typevariable;
/*1248*/                class2 = class2;
/*1248*/                class1 = class1.getSuperclass();
                    } else
                    {
/*1250*/                return null;
                    }
                } while(true);
            }

            private static ClassTypePair resolveTypeVariable(ParameterizedType parameterizedtype, Class class1, Class class2, TypeVariable typevariable, Map map)
            {
                HashMap hashmap;
                Type type;
                GenericArrayType genericarraytype;
/*1255*/        parameterizedtype = parameterizedtype.getActualTypeArguments();
/*1257*/        TypeVariable atypevariable[] = class1.getTypeParameters();
/*1259*/        hashmap = new HashMap();
/*1260*/        for(int i = 0; i < parameterizedtype.length; i++)
                {
                    Object obj;
/*1262*/            if((obj = parameterizedtype[i]) instanceof TypeVariable)
                    {
/*1264*/                Type type1 = (Type)map.get(obj);
/*1265*/                hashmap.put(atypevariable[i], type1);
                    } else
                    {
/*1267*/                hashmap.put(atypevariable[i], obj);
                    }
                }

/*1271*/        if(class1 != class2)
/*1272*/            break MISSING_BLOCK_LABEL_276;
/*1272*/        if((type = (Type)hashmap.get(typevariable)) instanceof Class)
/*1274*/            return ClassTypePair.of((Class)type);
/*1275*/        if(!(type instanceof GenericArrayType))
/*1276*/            break MISSING_BLOCK_LABEL_232;
/*1276*/        if(!((type = (genericarraytype = (GenericArrayType)type).getGenericComponentType()) instanceof Class))
/*1279*/            break MISSING_BLOCK_LABEL_179;
/*1279*/        class1 = (Class)type;
/*1281*/        return ClassTypePair.of(getArrayForComponentType(class1));
/*1282*/        JVM INSTR pop ;
/*1285*/        return null;
/*1286*/        if(!(type instanceof ParameterizedType))
/*1287*/            break MISSING_BLOCK_LABEL_230;
                Type type2;
/*1287*/        if((type2 = ((ParameterizedType)type).getRawType()) instanceof Class)
/*1289*/            class1 = (Class)type2;
/*1291*/        else
/*1291*/            return null;
/*1294*/        return ClassTypePair.of(getArrayForComponentType(class1), genericarraytype);
/*1295*/        JVM INSTR pop ;
/*1296*/        return null;
/*1299*/        return null;
/*1301*/        if(type instanceof ParameterizedType)
                {
/*1302*/            if((parameterizedtype = (ParameterizedType)type).getRawType() instanceof Class)
/*1304*/                return ClassTypePair.of((Class)parameterizedtype.getRawType(), parameterizedtype);
/*1306*/            else
/*1306*/                return null;
                } else
                {
/*1309*/            return null;
                }
/*1312*/        return resolveTypeVariable(class1, class2, typevariable, ((Map) (hashmap)));
            }

            public static PrivilegedAction findMethodOnClassPA(Class class1, Method method)
            {
/*1338*/        return new PrivilegedAction(class1, method) {

                    public final Method run()
                    {
/*1342*/                return c.getMethod(m.getName(), m.getParameterTypes());
/*1343*/                JVM INSTR pop ;
                        Method amethod[];
/*1344*/                int i = (amethod = c.getMethods()).length;
/*1344*/                for(int j = 0; j < i; j++)
                        {
                            Method method1;
/*1344*/                    if((method1 = amethod[j]).getName().equals(m.getName()) && method1.getParameterTypes().length == m.getParameterTypes().length && ReflectionHelper.compareParameterTypes(m.getGenericParameterTypes(), method1.getGenericParameterTypes()))
/*1349*/                        return method1;
                        }

/*1353*/                return null;
                    }

                    public final volatile Object run()
                    {
/*1338*/                return run();
                    }

                    final Class val$c;
                    final Method val$m;

                    
                    {
/*1338*/                c = class1;
/*1338*/                m = method;
/*1338*/                super();
                    }
        };
            }

            public static PrivilegedAction getMethodsPA(Class class1)
            {
/*1388*/        return new PrivilegedAction(class1) {

                    public final Method[] run()
                    {
/*1391*/                return c.getMethods();
                    }

                    public final volatile Object run()
                    {
/*1388*/                return run();
                    }

                    final Class val$c;

                    
                    {
/*1388*/                c = class1;
/*1388*/                super();
                    }
        };
            }

            private static Method[] _getMethods(Class class1)
            {
/*1397*/        return (Method[])AccessController.doPrivileged(getMethodsPA(class1));
            }

            public static Method findOverridingMethodOnClass(Class class1, Method method)
            {
                Method amethod[];
/*1408*/        int i = (amethod = _getMethods(class1)).length;
/*1408*/        for(int j = 0; j < i; j++)
                {
                    Method method1;
/*1408*/            if(!(method1 = amethod[j]).isBridge() && !Modifier.isAbstract(method1.getModifiers()) && method1.getName().equals(method.getName()) && method1.getParameterTypes().length == method.getParameterTypes().length && compareParameterTypes(method1.getGenericParameterTypes(), method.getGenericParameterTypes()))
/*1415*/                return method1;
                }

/*1420*/        if(method.isBridge() || Modifier.isAbstract(method.getModifiers()))
/*1421*/            LOGGER.log(Level.INFO, LocalizationMessages.OVERRIDING_METHOD_CANNOT_BE_FOUND(method, class1));
/*1424*/        return method;
            }

            private static boolean compareParameterTypes(Type atype[], Type atype1[])
            {
/*1436*/        for(int i = 0; i < atype.length; i++)
/*1437*/            if(!atype[i].equals(atype1[i]) && !compareParameterTypes(atype[i], atype1[i]))
/*1439*/                return false;

/*1443*/        return true;
            }

            private static boolean compareParameterTypes(Type type, Type type1)
            {
/*1455*/        if(type instanceof Class)
                {
/*1456*/            type = (Class)type;
/*1458*/            if(type1 instanceof Class)
/*1459*/                return ((Class)type1).isAssignableFrom(type);
/*1460*/            if(type1 instanceof TypeVariable)
/*1461*/                return checkTypeBounds(type, ((TypeVariable)type1).getBounds());
                }
/*1464*/        return type1 instanceof TypeVariable;
            }

            private static boolean checkTypeBounds(Class class1, Type atype[])
            {
/*1469*/        int i = (atype = atype).length;
/*1469*/        for(int j = 0; j < i; j++)
                {
                    Type type;
/*1469*/            if(((type = atype[j]) instanceof Class) && !((Class)type).isAssignableFrom(class1))
/*1472*/                return false;
                }

/*1476*/        return true;
            }

            public static OsgiRegistry getOsgiRegistryInstance()
            {
/*1489*/        if(bundleReferenceClass != null)
/*1490*/            return OsgiRegistry.getInstance();
/*1494*/        break MISSING_BLOCK_LABEL_14;
/*1492*/        JVM INSTR pop ;
/*1496*/        return null;
            }

            public static InputStream getResourceAsStream(ClassLoader classloader, Class class1, String s)
            {
/*1513*/        if(bundleReferenceClass != null && class1 != null && bundleReferenceClass.isInstance(org/glassfish/jersey/internal/util/ReflectionHelper.getClassLoader()) && (class1 = (class1 = FrameworkUtil.getBundle(class1)) == null ? null : ((Class) (class1.getEntry(s)))) != null)
/*1519*/            return class1.openStream();
/*1524*/        break MISSING_BLOCK_LABEL_58;
/*1522*/        JVM INSTR pop ;
/*1525*/        return classloader.getResourceAsStream(s);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/util/ReflectionHelper.getName());
            private static final PrivilegedAction NoOpPrivilegedACTION = new PrivilegedAction() {

                public final Object run()
                {
/*  99*/            return null;
                }

    };
            private static final TypeVisitor eraser = new TypeVisitor() {

                protected final Class onClass(Class class1)
                {
/* 638*/            return class1;
                }

                protected final Class onParameterizedType(ParameterizedType parameterizedtype)
                {
/* 643*/            return (Class)visit(parameterizedtype.getRawType());
                }

                protected final Class onGenericArray(GenericArrayType genericarraytype)
                {
/* 648*/            return Array.newInstance((Class)visit(genericarraytype.getGenericComponentType()), 0).getClass();
                }

                protected final Class onVariable(TypeVariable typevariable)
                {
/* 653*/            return (Class)visit(typevariable.getBounds()[0]);
                }

                protected final Class onWildcard(WildcardType wildcardtype)
                {
/* 658*/            return (Class)visit(wildcardtype.getUpperBounds()[0]);
                }

                protected final RuntimeException createError(Type type)
                {
/* 663*/            return new IllegalArgumentException(LocalizationMessages.TYPE_TO_CLASS_CONVERSION_NOT_SUPPORTED(type));
                }

                protected final volatile Object onWildcard(WildcardType wildcardtype)
                {
/* 635*/            return onWildcard(wildcardtype);
                }

                protected final volatile Object onVariable(TypeVariable typevariable)
                {
/* 635*/            return onVariable(typevariable);
                }

                protected final volatile Object onGenericArray(GenericArrayType genericarraytype)
                {
/* 635*/            return onGenericArray(genericarraytype);
                }

                protected final volatile Object onParameterizedType(ParameterizedType parameterizedtype)
                {
/* 635*/            return onParameterizedType(parameterizedtype);
                }

                protected final volatile Object onClass(Class class1)
                {
/* 635*/            return onClass(class1);
                }

    };
            private static final Class bundleReferenceClass = (Class)AccessController.doPrivileged(classForNamePA("org.osgi.framework.BundleReference", null));



}
