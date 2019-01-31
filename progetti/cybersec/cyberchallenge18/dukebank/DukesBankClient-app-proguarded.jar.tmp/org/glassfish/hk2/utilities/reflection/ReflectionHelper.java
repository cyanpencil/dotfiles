// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.inject.*;
import org.glassfish.hk2.utilities.reflection.internal.MethodWrapperImpl;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            Constants, GenericArrayTypeImpl, Logger, ParameterizedTypeImpl, 
//            Pretty, MethodWrapper

public final class ReflectionHelper
{

            public ReflectionHelper()
            {
            }

            public static Class getRawClass(Type type)
            {
/* 108*/        if(type == null)
/* 108*/            return null;
/* 110*/        if(!(type instanceof GenericArrayType))
/* 111*/            break MISSING_BLOCK_LABEL_70;
/* 111*/        if(!((type = ((GenericArrayType)type).getGenericComponentType()) instanceof ParameterizedType))
/* 115*/            return null;
/* 118*/        type = getRawClass(type);
/* 120*/        type = (new StringBuilder("[L")).append(type.getName()).append(";").toString();
/* 122*/        return Class.forName(type);
/* 124*/        JVM INSTR pop ;
/* 126*/        return null;
/* 130*/        if(type instanceof Class)
/* 131*/            return (Class)type;
/* 134*/        if((type instanceof ParameterizedType) && ((type = ((ParameterizedType)type).getRawType()) instanceof Class))
/* 137*/            return (Class)type;
/* 141*/        else
/* 141*/            return null;
            }

            public static Type resolveField(Class class1, Field field)
            {
/* 153*/        return resolveMember(class1, field.getGenericType(), field.getDeclaringClass());
            }

            public static Type resolveMember(Class class1, Type type, Class class2)
            {
/* 166*/        if((class1 = typesFromSubClassToDeclaringClass(class1, class2)) == null)
/* 167*/            return type;
/* 169*/        if(type instanceof ParameterizedType)
/* 170*/            return fixTypeVariables((ParameterizedType)type, class1);
/* 173*/        if(type instanceof GenericArrayType)
/* 174*/            return fixGenericArrayTypeVariables((GenericArrayType)type, class1);
/* 177*/        if(!(type instanceof TypeVariable))
/* 178*/            return type;
/* 181*/        class2 = (class2 = (TypeVariable)type).getName();
/* 184*/        if((class2 = (Type)class1.get(class2)) == null)
/* 185*/            return type;
/* 187*/        if(class2 instanceof Class)
/* 187*/            return class2;
/* 189*/        if(class2 instanceof ParameterizedType)
/* 190*/            return fixTypeVariables((ParameterizedType)class2, class1);
/* 193*/        if(class2 instanceof GenericArrayType)
/* 194*/            return fixGenericArrayTypeVariables((GenericArrayType)class2, class1);
/* 197*/        else
/* 197*/            return class2;
            }

            private static Map typesFromSubClassToDeclaringClass(Class class1, Class class2)
            {
/* 201*/        if(class1.equals(class2))
/* 202*/            return null;
                Object obj;
/* 205*/        Class class3 = getRawClass(((Type) (obj = class1.getGenericSuperclass())));
/* 208*/        do
                {
/* 208*/            if(obj == null || class3 == null)
/* 209*/                break;
/* 209*/            if(!(obj instanceof ParameterizedType))
                    {
/* 211*/                if(class3.equals(class2))
/* 211*/                    return null;
/* 213*/                class3 = getRawClass(((Type) (obj = class3.getGenericSuperclass())));
                    } else
                    {
/* 219*/                obj = (ParameterizedType)obj;
/* 221*/                Map map = getTypeArguments(class3, ((ParameterizedType) (obj)));
/* 223*/                if(class3.equals(class2))
/* 224*/                    return map;
/* 227*/                class3 = getRawClass(((Type) (obj = class3.getGenericSuperclass())));
/* 230*/                if(obj instanceof ParameterizedType)
/* 231*/                    obj = fixTypeVariables((ParameterizedType)obj, map);
                    }
                } while(true);
/* 235*/        throw new AssertionError((new StringBuilder()).append(class1.getName()).append(" is not the same as or a subclass of ").append(class2.getName()).toString());
            }

            public static Type getFirstTypeArgument(Type type)
            {
/* 247*/        if(type instanceof Class)
/* 248*/            return java/lang/Object;
/* 251*/        if(!(type instanceof ParameterizedType))
/* 251*/            return java/lang/Object;
/* 253*/        if((type = (type = (ParameterizedType)type).getActualTypeArguments()).length <= 0)
/* 255*/            return java/lang/Object;
/* 257*/        else
/* 257*/            return type[0];
            }

            private static String getNamedName(Named named, Class class1)
            {
/* 261*/        if((named = named.value()) != null && !named.equals(""))
/* 262*/            return named;
/* 264*/        if((class1 = (named = class1.getName()).lastIndexOf(".")) < 0)
/* 267*/            return named;
/* 269*/        else
/* 269*/            return named.substring(class1 + 1);
            }

            public static String getName(Class class1)
            {
                Named named;
/* 279*/        if((class1 = (named = (Named)class1.getAnnotation(javax/inject/Named)) == null ? null : ((Class) (getNamedName(named, class1)))) != null)
/* 283*/            return class1;
/* 285*/        else
/* 285*/            return null;
            }

            private static void addAllGenericInterfaces(Class class1, Type type, Set set)
            {
/* 296*/        Map map = null;
                Type atype[];
/* 298*/        int i = (atype = class1.getGenericInterfaces()).length;
/* 298*/        for(int j = 0; j < i; j++)
                {
/* 298*/            Type type1 = atype[j];
/* 300*/            if((type instanceof ParameterizedType) && (type1 instanceof ParameterizedType))
                    {
/* 303*/                if(map == null)
/* 304*/                    map = getTypeArguments(class1, (ParameterizedType)type);
/* 307*/                type1 = fixTypeVariables((ParameterizedType)type1, map);
                    }
/* 310*/            set.add(type1);
/* 312*/            if((class1 = getRawClass(type1)) != null)
/* 314*/                addAllGenericInterfaces(class1, type1, set);
                }

            }

            private static Type fixTypeVariables(ParameterizedType parameterizedtype, Map map)
            {
/* 327*/        if((map = getNewTypeArguments(parameterizedtype, map)) != null)
/* 330*/            return new ParameterizedTypeImpl(parameterizedtype.getRawType(), map);
/* 333*/        else
/* 333*/            return parameterizedtype;
            }

            private static Type fixGenericArrayTypeVariables(GenericArrayType genericarraytype, Map map)
            {
/* 345*/        if((map = getNewTypeArrayArguments(genericarraytype, map)) != null)
                {
/* 348*/            if(map instanceof Class)
/* 349*/                return getArrayOfType((Class)map);
/* 352*/            if((map instanceof ParameterizedType) && ((genericarraytype = (ParameterizedType)map).getRawType() instanceof Class))
/* 355*/                return getArrayOfType((Class)genericarraytype.getRawType());
/* 359*/            else
/* 359*/                return new GenericArrayTypeImpl(map);
                } else
                {
/* 362*/            return genericarraytype;
                }
            }

            private static Class getArrayOfType(Class class1)
            {
/* 366*/        return Array.newInstance(class1, 0).getClass();
            }

            private static Type[] getNewTypeArguments(ParameterizedType parameterizedtype, Map map)
            {
/* 377*/        Type atype[] = new Type[(parameterizedtype = parameterizedtype.getActualTypeArguments()).length];
/* 379*/        Object obj = 0;
/* 381*/        int i = 0;
/* 382*/        int j = (parameterizedtype = parameterizedtype).length;
/* 382*/        for(int k = 0; k < j; k++)
                {
                    Type type;
                    Object obj1;
                    Type atype1[];
                    Type type1;
/* 382*/            if((type = parameterizedtype[k]) instanceof TypeVariable)
                    {
/* 384*/                atype[i] = (Type)map.get(((TypeVariable)type).getName());
/* 385*/                obj = 1;
                    } else
/* 387*/            if(type instanceof ParameterizedType)
                    {
/* 388*/                if((atype1 = getNewTypeArguments(((ParameterizedType) (obj1 = (ParameterizedType)type)), map)) != null)
                        {
/* 392*/                    atype[i] = new ParameterizedTypeImpl(((ParameterizedType) (obj1)).getRawType(), atype1);
/* 393*/                    obj = 1;
                        } else
                        {
/* 396*/                    atype[i] = type;
                        }
                    } else
/* 399*/            if(type instanceof GenericArrayType)
                    {
/* 400*/                if((type1 = getNewTypeArrayArguments(((GenericArrayType) (obj1 = (GenericArrayType)type)), map)) != null)
                        {
/* 404*/                    if(type1 instanceof Class)
                            {
/* 405*/                        atype[i] = getArrayOfType((Class)type1);
/* 406*/                        obj = 1;
                            } else
/* 408*/                    if((type1 instanceof ParameterizedType) && (((ParameterizedType)type1).getRawType() instanceof Class))
                            {
/* 410*/                        obj = (ParameterizedType)type1;
/* 412*/                        atype[i] = getArrayOfType((Class)((ParameterizedType) (obj)).getRawType());
/* 413*/                        obj = 1;
                            } else
                            {
/* 416*/                        atype[i] = new GenericArrayTypeImpl(type1);
/* 417*/                        obj = 1;
                            }
                        } else
                        {
/* 421*/                    atype[i] = type;
                        }
                    } else
                    {
/* 425*/                atype[i] = type;
                    }
/* 428*/            i++;
                }

/* 431*/        if(obj != 0)
/* 431*/            return atype;
/* 431*/        else
/* 431*/            return null;
            }

            private static Type getNewTypeArrayArguments(GenericArrayType genericarraytype, Map map)
            {
/* 442*/        if((genericarraytype = genericarraytype.getGenericComponentType()) instanceof TypeVariable)
/* 445*/            return (Type)map.get(((TypeVariable)genericarraytype).getName());
/* 448*/        if(genericarraytype instanceof ParameterizedType)
/* 449*/            if((map = getNewTypeArguments(genericarraytype = (ParameterizedType)genericarraytype, map)) != null)
/* 453*/                return new ParameterizedTypeImpl(genericarraytype.getRawType(), map);
/* 456*/            else
/* 456*/                return genericarraytype;
/* 459*/        if(genericarraytype instanceof GenericArrayType)
                {
/* 460*/            if((map = getNewTypeArrayArguments(genericarraytype = (GenericArrayType)genericarraytype, map)) != null)
                    {
/* 464*/                if(map instanceof Class)
/* 465*/                    return getArrayOfType((Class)map);
/* 468*/                if((map instanceof ParameterizedType) && ((genericarraytype = (ParameterizedType)map).getRawType() instanceof Class))
/* 472*/                    return getArrayOfType((Class)genericarraytype.getRawType());
/* 476*/                else
/* 476*/                    return new GenericArrayTypeImpl(map);
                    } else
                    {
/* 479*/                return null;
                    }
                } else
                {
/* 482*/            return null;
                }
            }

            private static Map getTypeArguments(Class class1, ParameterizedType parameterizedtype)
            {
/* 492*/        HashMap hashmap = new HashMap();
/* 493*/        parameterizedtype = parameterizedtype.getActualTypeArguments();
/* 495*/        int i = 0;
/* 496*/        int j = (class1 = class1.getTypeParameters()).length;
/* 496*/        for(int k = 0; k < j; k++)
                {
/* 496*/            TypeVariable typevariable = class1[k];
/* 497*/            hashmap.put(typevariable.getName(), parameterizedtype[i++]);
                }

/* 499*/        return hashmap;
            }

            private static Set getTypeClosure(Type type)
            {
/* 511*/        HashSet hashset = new HashSet();
                Class class1;
/* 512*/        if((class1 = getRawClass(type)) != null)
                {
/* 515*/            Map map = null;
/* 516*/            type = type;
/* 518*/            do
                    {
/* 518*/                if(type == null || class1 == null)
/* 520*/                    break;
/* 520*/                hashset.add(type);
/* 522*/                addAllGenericInterfaces(class1, type, hashset);
/* 524*/                if(map == null && (type instanceof ParameterizedType))
/* 525*/                    map = getTypeArguments(class1, (ParameterizedType)type);
/* 528*/                if((type = class1.getGenericSuperclass()) != null)
                        {
/* 530*/                    class1 = getRawClass(type);
/* 532*/                    if(map != null && (type instanceof ParameterizedType))
/* 533*/                        type = fixTypeVariables((ParameterizedType)type, map);
                        }
                    } while(true);
                }
/* 538*/        return hashset;
            }

            public static Set getTypeClosure(Type type, Set set)
            {
/* 550*/        type = getTypeClosure(type);
/* 552*/        HashSet hashset = new HashSet();
/* 553*/        type = type.iterator();
/* 553*/        do
                {
/* 553*/            if(!type.hasNext())
/* 553*/                break;
                    Type type1;
                    Class class1;
/* 553*/            if((class1 = getRawClass(type1 = (Type)type.next())) != null && set.contains(class1.getName()))
/* 558*/                hashset.add(type1);
                } while(true);
/* 562*/        return hashset;
            }

            public static Set getAdvertisedTypesFromClass(Type type, Class class1)
            {
/* 572*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/* 573*/        if(type == null)
/* 573*/            return linkedhashset;
/* 575*/        linkedhashset.add(type);
/* 577*/        if((type = getRawClass(type)) == null)
/* 578*/            return linkedhashset;
                Class class2;
/* 580*/        for(Type type1 = type.getGenericSuperclass(); type1 != null && (class2 = getRawClass(type1)) != null; type1 = class2.getGenericSuperclass())
/* 585*/            if(class2.isAnnotationPresent(class1))
/* 586*/                linkedhashset.add(type1);

/* 592*/        HashSet hashset = new HashSet();
/* 593*/        for(; type != null; type = type.getSuperclass())
/* 594*/            getAllContractsFromInterfaces(type, class1, linkedhashset, hashset);

/* 602*/        return linkedhashset;
            }

            private static void getAllContractsFromInterfaces(Class class1, Class class2, Set set, Set set1)
            {
/* 609*/        int i = (class1 = class1 = class1.getGenericInterfaces()).length;
/* 611*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
                    Class class3;
/* 611*/            if((class3 = getRawClass(obj = class1[j])) == null || set1.contains(class3))
/* 615*/                continue;
/* 615*/            set1.add(class3);
/* 617*/            if(class3.isAnnotationPresent(class2))
/* 618*/                set.add(obj);
/* 621*/            getAllContractsFromInterfaces(class3, class2, set, set1);
                }

            }

            public static Set getAdvertisedTypesFromObject(Object obj, Class class1)
            {
/* 632*/        if(obj == null)
/* 632*/            return Collections.emptySet();
/* 634*/        else
/* 634*/            return getAdvertisedTypesFromClass(obj.getClass(), class1);
            }

            public static Set getContractsFromClass(Class class1, Class class2)
            {
/* 644*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/* 645*/        if(class1 == null)
/* 645*/            return linkedhashset;
/* 647*/        linkedhashset.add(class1.getName());
/* 649*/        for(Class class3 = class1.getSuperclass(); class3 != null; class3 = class3.getSuperclass())
/* 651*/            if(class3.isAnnotationPresent(class2))
/* 652*/                linkedhashset.add(class3.getName());

/* 658*/        for(; class1 != null; class1 = class1.getSuperclass())
                {
                    Class aclass[];
/* 659*/            int i = (aclass = aclass = class1.getInterfaces()).length;
/* 660*/            for(int j = 0; j < i; j++)
                    {
                        Class class4;
/* 660*/                if((class4 = aclass[j]).isAnnotationPresent(class2))
/* 662*/                    linkedhashset.add(class4.getName());
                    }

                }

/* 669*/        return linkedhashset;
            }

            public static Annotation getScopeAnnotationFromObject(Object obj)
            {
/* 678*/        if(obj == null)
/* 678*/            throw new IllegalArgumentException();
/* 681*/        else
/* 681*/            return getScopeAnnotationFromClass(obj.getClass());
            }

            public static Annotation getScopeAnnotationFromClass(Class class1)
            {
/* 690*/        if(class1 == null)
/* 690*/            throw new IllegalArgumentException();
/* 692*/        int i = (class1 = class1.getAnnotations()).length;
/* 692*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation;
                    Class class2;
/* 692*/            if((class2 = (annotation = class1[j]).annotationType()).isAnnotationPresent(javax/inject/Scope))
/* 696*/                return annotation;
                }

/* 701*/        return null;
            }

            public static Annotation getScopeFromObject(Object obj, Annotation annotation)
            {
/* 711*/        if(obj == null)
/* 711*/            return annotation;
/* 713*/        else
/* 713*/            return getScopeFromClass(obj.getClass(), annotation);
            }

            public static Annotation getScopeFromClass(Class class1, Annotation annotation)
            {
/* 723*/        if(class1 == null)
/* 723*/            return annotation;
/* 725*/        int i = (class1 = class1.getAnnotations()).length;
/* 725*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation1;
                    Class class2;
/* 725*/            if((class2 = (annotation1 = class1[j]).annotationType()).isAnnotationPresent(javax/inject/Scope))
/* 729*/                return annotation1;
                }

/* 734*/        return annotation;
            }

            public static boolean isAnnotationAQualifier(Annotation annotation)
            {
/* 743*/        return (annotation = annotation.annotationType()).isAnnotationPresent(javax/inject/Qualifier);
            }

            public static Set getQualifiersFromObject(Object obj)
            {
/* 754*/        if(obj == null)
/* 754*/            return Collections.emptySet();
/* 756*/        else
/* 756*/            return getQualifierAnnotations(obj.getClass());
            }

            public static Set getQualifiersFromClass(Class class1)
            {
/* 766*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/* 767*/        if(class1 == null)
/* 767*/            return linkedhashset;
                Annotation aannotation[];
/* 769*/        int i = (aannotation = class1.getAnnotations()).length;
/* 769*/        for(int k = 0; k < i; k++)
                {
                    Annotation annotation;
/* 769*/            if(isAnnotationAQualifier(annotation = aannotation[k]))
/* 771*/                linkedhashset.add(annotation.annotationType().getName());
                }

/* 776*/        for(; class1 != null; class1 = class1.getSuperclass())
                {
                    Class aclass[];
/* 777*/            int j = (aclass = class1.getInterfaces()).length;
/* 777*/            for(int l = 0; l < j; l++)
                    {
                        Class class2;
                        Annotation aannotation1[];
/* 777*/                int i1 = (aannotation1 = (class2 = aclass[l]).getAnnotations()).length;
/* 778*/                for(int j1 = 0; j1 < i1; j1++)
                        {
                            Annotation annotation1;
/* 778*/                    if(isAnnotationAQualifier(annotation1 = aannotation1[j1]))
/* 780*/                        linkedhashset.add(annotation1.annotationType().getName());
                        }

                    }

                }

/* 788*/        return linkedhashset;
            }

            private static Set internalGetQualifierAnnotations(AnnotatedElement annotatedelement)
            {
/* 792*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/* 793*/        if(annotatedelement == null)
/* 793*/            return linkedhashset;
                Annotation aannotation[];
/* 795*/        int i = (aannotation = annotatedelement.getAnnotations()).length;
/* 795*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation;
                    Named named;
/* 795*/            if(isAnnotationAQualifier(annotation = aannotation[j]) && (!(annotatedelement instanceof Field) || !javax/inject/Named.equals(annotation.annotationType()) || (named = (Named)annotation).value() != null && !"".equals(named.value())))
/* 808*/                linkedhashset.add(annotation);
                }

/* 812*/        if(!(annotatedelement instanceof Class))
/* 812*/            return linkedhashset;
/* 814*/        for(Class class1 = (Class)annotatedelement; class1 != null; class1 = class1.getSuperclass())
                {
                    Class aclass[];
/* 816*/            int k = (aclass = class1.getInterfaces()).length;
/* 816*/            for(int l = 0; l < k; l++)
                    {
                        Class class2;
/* 816*/                int i1 = (annotatedelement = (class2 = aclass[l]).getAnnotations()).length;
/* 817*/                for(int j1 = 0; j1 < i1; j1++)
                        {
                            Object obj;
/* 817*/                    if(isAnnotationAQualifier(obj = annotatedelement[j1]))
/* 819*/                        linkedhashset.add(obj);
                        }

                    }

                }

/* 827*/        return linkedhashset;
            }

            public static Set getQualifierAnnotations(AnnotatedElement annotatedelement)
            {
/* 846*/        return annotatedelement = (Set)AccessController.doPrivileged(new PrivilegedAction(annotatedelement) {

                    public final Set run()
                    {
/* 850*/                return ReflectionHelper.internalGetQualifierAnnotations(annotatedGuy);
                    }

                    public final volatile Object run()
                    {
/* 846*/                return run();
                    }

                    final AnnotatedElement val$annotatedGuy;

                    
                    {
/* 846*/                annotatedGuy = annotatedelement;
/* 846*/                super();
                    }
        });
            }

            public static String writeSet(Set set)
            {
/* 865*/        return writeSet(set, null);
            }

            public static String writeSet(Set set, Object obj)
            {
/* 876*/        if(set == null)
/* 876*/            return "{}";
/* 878*/        StringBuffer stringbuffer = new StringBuffer("{");
/* 880*/        boolean flag = true;
/* 881*/        set = set.iterator();
/* 881*/        do
                {
/* 881*/            if(!set.hasNext())
/* 881*/                break;
/* 881*/            Object obj1 = set.next();
/* 882*/            if(obj == null || !obj.equals(obj1))
/* 886*/                if(flag)
                        {
/* 887*/                    flag = false;
/* 888*/                    stringbuffer.append(escapeString(obj1.toString()));
                        } else
                        {
/* 891*/                    stringbuffer.append((new StringBuilder(",")).append(escapeString(obj1.toString())).toString());
                        }
                } while(true);
/* 895*/        stringbuffer.append("}");
/* 897*/        return stringbuffer.toString();
            }

            public static void readSet(String s, Collection collection)
                throws IOException
            {
/* 909*/        char ac[] = new char[s.length()];
/* 910*/        s.getChars(0, s.length(), ac, 0);
/* 912*/        internalReadSet(ac, 0, collection);
            }

            private static int internalReadSet(char ac[], int i, Collection collection)
                throws IOException
            {
/* 925*/        int j = i;
/* 926*/        int k = -1;
/* 927*/        do
                {
/* 927*/            if(j >= ac.length)
/* 928*/                break;
/* 928*/            if(ac[j] == '{')
                    {
/* 929*/                k = j;
/* 930*/                j++;
/* 931*/                break;
                    }
/* 933*/            j++;
                } while(true);
/* 936*/        if(k == -1)
/* 937*/            throw new IOException((new StringBuilder("Unknown set format, no initial { character : ")).append(new String(ac)).toString());
/* 940*/        StringBuffer stringbuffer = new StringBuffer();
/* 941*/        int l = -1;
/* 942*/        for(; j < ac.length; j++)
                {
                    char c;
/* 943*/            if((c = ac[j]) == '}')
                    {
/* 946*/                collection.add(stringbuffer.toString());
/* 948*/                l = j;
/* 949*/                break;
                    }
/* 952*/            if(c == ',')
                    {
/* 954*/                collection.add(stringbuffer.toString());
/* 956*/                stringbuffer = new StringBuffer();
/* 956*/                continue;
                    }
/* 960*/            if(c != '\\')
                    {
/* 961*/                stringbuffer.append(c);
/* 961*/                continue;
                    }
/* 965*/            if(j + 1 >= ac.length)
/* 970*/                break;
/* 970*/            j++;
/* 971*/            if((c = ac[j]) == 'n')
                    {
/* 974*/                stringbuffer.append('\n');
/* 974*/                continue;
                    }
/* 976*/            if(c == 'r')
/* 977*/                stringbuffer.append('\r');
/* 980*/            else
/* 980*/                stringbuffer.append(c);
                }

/* 989*/        if(l == -1)
/* 990*/            throw new IOException((new StringBuilder("Unknown set format, no ending } character : ")).append(new String(ac)).toString());
/* 993*/        else
/* 993*/            return j - i;
            }

            private static int readKeyStringListLine(char ac[], int i, Map map)
                throws IOException
            {
/* 997*/        int j = i;
/* 999*/        int k = -1;
/*1000*/        do
                {
/*1000*/            if(j >= ac.length)
/*1001*/                break;
                    char c;
/*1001*/            if((c = ac[j]) == '=')
                    {
/*1004*/                k = j;
/*1005*/                break;
                    }
/*1008*/            j++;
                } while(true);
/*1011*/        if(k < 0)
/*1012*/            throw new IOException((new StringBuilder("Unknown key-string list format, no equals: ")).append(new String(ac)).toString());
/*1015*/        String s = new String(ac, i, k - i);
/*1016*/        if(++j >= ac.length)
/*1020*/            throw new IOException((new StringBuilder("Found a key with no value, ")).append(s).append(" in line ").append(new String(ac)).toString());
/*1024*/        LinkedList linkedlist = new LinkedList();
/*1026*/        int l = internalReadSet(ac, j, linkedlist);
/*1027*/        if(!linkedlist.isEmpty())
/*1028*/            map.put(s, linkedlist);
/*1031*/        if((j += l + 1) < ac.length && (ac = ac[j]) == ',')
/*1035*/            j++;
/*1039*/        return j - i;
            }

            public static void readMetadataMap(String s, Map map)
                throws IOException
            {
/*1049*/        char ac[] = new char[s.length()];
/*1050*/        s.getChars(0, s.length(), ac, 0);
                int i;
/*1052*/        for(s = 0; s < ac.length; s += i)
/*1054*/            i = readKeyStringListLine(ac, s, map);

            }

            private static String escapeString(String s)
            {
/*1060*/        char ac[] = new char[s.length()];
/*1062*/        s.getChars(0, s.length(), ac, 0);
/*1064*/        s = new StringBuffer();
/*1065*/        for(int i = 0; i < ac.length; i++)
                {
/*1066*/            char c = ac[i];
/*1068*/            if(ESCAPE_CHARACTERS.contains(Character.valueOf(c)))
                    {
/*1069*/                s.append('\\');
/*1070*/                s.append(c);
/*1070*/                continue;
                    }
/*1072*/            if(REPLACE_CHARACTERS.containsKey(Character.valueOf(c)))
                    {
/*1073*/                c = ((Character)REPLACE_CHARACTERS.get(Character.valueOf(c))).charValue();
/*1074*/                s.append('\\');
/*1075*/                s.append(c);
                    } else
                    {
/*1078*/                s.append(c);
                    }
                }

/*1082*/        return s.toString();
            }

            private static String writeList(List list)
            {
/*1086*/        StringBuffer stringbuffer = new StringBuffer("{");
/*1088*/        boolean flag = true;
/*1089*/        for(list = list.iterator(); list.hasNext();)
                {
/*1089*/            String s = (String)list.next();
/*1090*/            if(flag)
                    {
/*1091*/                flag = false;
/*1092*/                stringbuffer.append(escapeString(s));
                    } else
                    {
/*1095*/                stringbuffer.append((new StringBuilder(",")).append(escapeString(s)).toString());
                    }
                }

/*1099*/        stringbuffer.append("}");
/*1101*/        return stringbuffer.toString();
            }

            public static String writeMetadata(Map map)
            {
/*1111*/        StringBuffer stringbuffer = new StringBuffer();
/*1113*/        boolean flag = true;
                java.util.Map.Entry entry;
/*1114*/        for(map = map.entrySet().iterator(); map.hasNext(); stringbuffer.append(writeList((List)entry.getValue())))
                {
/*1114*/            entry = (java.util.Map.Entry)map.next();
/*1115*/            if(flag)
                    {
/*1116*/                flag = false;
/*1117*/                stringbuffer.append((new StringBuilder()).append((String)entry.getKey()).append('=').toString());
                    } else
                    {
/*1120*/                stringbuffer.append((new StringBuilder(",")).append((String)entry.getKey()).append('=').toString());
                    }
                }

/*1126*/        return stringbuffer.toString();
            }

            public static void addMetadata(Map map, String s, String s1)
            {
/*1137*/        if(s == null || s1 == null)
/*1137*/            return;
/*1138*/        if(s.indexOf('=') >= 0)
/*1139*/            throw new IllegalArgumentException((new StringBuilder("The key field may not have an = in it:")).append(s).toString());
                Object obj;
/*1142*/        if((obj = (List)map.get(s)) == null)
                {
/*1144*/            obj = new LinkedList();
/*1145*/            map.put(s, obj);
                }
/*1148*/        ((List) (obj)).add(s1);
            }

            public static boolean removeMetadata(Map map, String s, String s1)
            {
/*1160*/        if(s == null || s1 == null)
/*1160*/            return false;
                List list;
/*1162*/        if((list = (List)map.get(s)) == null)
/*1163*/            return false;
/*1165*/        s1 = list.remove(s1);
/*1166*/        if(list.size() <= 0)
/*1166*/            map.remove(s);
/*1168*/        return s1;
            }

            public static boolean removeAllMetadata(Map map, String s)
            {
/*1179*/        return (map = (List)map.remove(s)) != null && map.size() > 0;
            }

            public static Map deepCopyMetadata(Map map)
            {
/*1191*/        if(map == null)
/*1191*/            return null;
/*1193*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
                String s;
                LinkedList linkedlist;
/*1195*/        for(map = map.entrySet().iterator(); map.hasNext(); linkedhashmap.put(s, linkedlist))
                {
                    Object obj;
/*1195*/            if((s = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)map.next())).getKey()).indexOf('=') >= 0)
/*1198*/                throw new IllegalArgumentException((new StringBuilder("The key field may not have an = in it:")).append(s).toString());
/*1201*/            obj = (List)((java.util.Map.Entry) (obj)).getValue();
/*1202*/            linkedlist = new LinkedList();
                    String s1;
/*1203*/            for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); linkedlist.add(s1))
/*1203*/                s1 = (String)((Iterator) (obj)).next();

                }

/*1210*/        return linkedhashmap;
            }

            public static void setField(Field field, Object obj, Object obj1)
                throws Throwable
            {
/*1222*/        setAccessible(field);
/*1225*/        try
                {
/*1225*/            field.set(obj, obj1);
/*1234*/            return;
                }
                // Misplaced declaration of an exception variable
/*1227*/        catch(Object obj)
                {
/*1228*/            Logger.getLogger().debug(field.getDeclaringClass().getName(), field.getName(), ((Throwable) (obj)));
/*1229*/            throw obj;
                }
                // Misplaced declaration of an exception variable
/*1231*/        catch(Object obj)
                {
/*1232*/            Logger.getLogger().debug(field.getDeclaringClass().getName(), field.getName(), ((Throwable) (obj)));
                }
/*1233*/        throw obj;
            }

            public static Object invoke(Object obj, Method method, Object aobj[], boolean flag)
                throws Throwable
            {
                ClassLoader classloader;
/*1250*/        if(isStatic(method))
/*1251*/            obj = null;
/*1254*/        setAccessible(method);
/*1256*/        classloader = null;
/*1257*/        if(flag)
/*1258*/            classloader = getCurrentContextClassLoader();
/*1262*/        obj = method.invoke(obj, aobj);
/*1274*/        if(flag)
/*1275*/            setContextClassLoader(Thread.currentThread(), classloader);
/*1275*/        return obj;
/*1264*/        JVM INSTR dup ;
/*1265*/        obj;
/*1265*/        getTargetException();
/*1265*/        obj;
/*1266*/        Logger.getLogger().debug(method.getDeclaringClass().getName(), method.getName(), ((Throwable) (obj)));
/*1267*/        throw obj;
/*1269*/        obj;
/*1270*/        Logger.getLogger().debug(method.getDeclaringClass().getName(), method.getName(), ((Throwable) (obj)));
/*1271*/        throw obj;
/*1274*/        obj;
/*1274*/        if(flag)
/*1275*/            setContextClassLoader(Thread.currentThread(), classloader);
/*1275*/        throw obj;
            }

            public static boolean isStatic(Member member)
            {
/*1287*/        return ((member = member.getModifiers()) & 8) != 0;
            }

            private static void setContextClassLoader(Thread thread, ClassLoader classloader)
            {
/*1298*/        AccessController.doPrivileged(new PrivilegedAction(thread, classloader) {

                    public final Object run()
                    {
/*1302*/                t.setContextClassLoader(l);
/*1303*/                return null;
                    }

                    final Thread val$t;
                    final ClassLoader val$l;

                    
                    {
/*1298*/                t = thread;
/*1298*/                l = classloader;
/*1298*/                super();
                    }
        });
            }

            private static void setAccessible(AccessibleObject accessibleobject)
            {
/*1317*/        if(accessibleobject.isAccessible())
                {
/*1317*/            return;
                } else
                {
/*1319*/            AccessController.doPrivileged(new PrivilegedAction(accessibleobject) {

                        public final Object run()
                        {
/*1323*/                    ao.setAccessible(true);
/*1324*/                    return null;
                        }

                        final AccessibleObject val$ao;

                    
                    {
/*1319*/                ao = accessibleobject;
/*1319*/                super();
                    }
            });
/*1328*/            return;
                }
            }

            public static Object makeMe(Constructor constructor, Object aobj[], boolean flag)
                throws Throwable
            {
                ClassLoader classloader;
/*1344*/        classloader = null;
/*1345*/        if(flag)
/*1346*/            classloader = getCurrentContextClassLoader();
/*1350*/        aobj = ((Object []) (constructor.newInstance(aobj)));
/*1359*/        if(flag)
/*1360*/            setContextClassLoader(Thread.currentThread(), classloader);
/*1360*/        return ((Object) (aobj));
/*1351*/        JVM INSTR dup ;
/*1352*/        aobj;
/*1352*/        getTargetException();
/*1352*/        aobj;
/*1353*/        Logger.getLogger().debug(constructor.getDeclaringClass().getName(), constructor.getName(), ((Throwable) (aobj)));
/*1354*/        throw aobj;
/*1355*/        aobj;
/*1356*/        Logger.getLogger().debug(constructor.getDeclaringClass().getName(), constructor.getName(), ((Throwable) (aobj)));
/*1357*/        throw aobj;
/*1359*/        constructor;
/*1359*/        if(flag)
/*1360*/            setContextClassLoader(Thread.currentThread(), classloader);
/*1360*/        throw constructor;
            }

            public static void parseServiceMetadataString(String s, Map map)
            {
/*1374*/        StringBuffer stringbuffer = new StringBuffer(s);
/*1376*/        int i = 0;
/*1377*/        for(int j = stringbuffer.indexOf("=", 0); j > 0;)
                {
/*1379*/            String s1 = stringbuffer.substring(i, j);
/*1381*/            i = j + 1;
                    String s2;
/*1385*/            if(stringbuffer.charAt(i) == '"')
                    {
/*1386*/                i++;
                        int k;
/*1388*/                if((k = stringbuffer.indexOf("\"", i)) < 0)
/*1391*/                    throw new IllegalStateException((new StringBuilder("Badly formed metadata \"")).append(s).append("\" for key ").append(s1).append(" has a leading quote but no trailing quote").toString());
/*1395*/                s2 = stringbuffer.substring(i, k);
/*1396*/                i = k + 1;
/*1398*/                j = stringbuffer.indexOf(",", i);
                    } else
/*1401*/            if((j = stringbuffer.indexOf(",", i)) < 0)
/*1405*/                s2 = stringbuffer.substring(i);
/*1408*/            else
/*1408*/                s2 = stringbuffer.substring(i, j);
                    Object obj;
/*1412*/            if((obj = (List)map.get(s1)) == null)
                    {
/*1414*/                obj = new LinkedList();
/*1415*/                map.put(s1, obj);
                    }
/*1417*/            ((List) (obj)).add(s2);
/*1419*/            if(j >= 0)
                    {
/*1420*/                i = j + 1;
/*1421*/                j = stringbuffer.indexOf("=", i);
                    } else
                    {
/*1424*/                j = -1;
                    }
                }

            }

            public static String getNameFromAllQualifiers(Set set, AnnotatedElement annotatedelement)
                throws IllegalStateException
            {
/*1439*/        for(set = set.iterator(); set.hasNext();)
                {
/*1439*/            Annotation annotation = (Annotation)set.next();
/*1440*/            if(javax/inject/Named.equals(annotation.annotationType()))
/*1442*/                if((set = (Named)annotation).value() == null || set.value().equals(""))
                        {
/*1444*/                    if(annotatedelement != null)
                            {
/*1445*/                        if(annotatedelement instanceof Class)
/*1446*/                            return Pretty.clazz((Class)annotatedelement);
/*1449*/                        if(annotatedelement instanceof Field)
/*1450*/                            return ((Field)annotatedelement).getName();
                            }
/*1454*/                    throw new IllegalStateException((new StringBuilder("@Named must have a value for ")).append(annotatedelement).toString());
                        } else
                        {
/*1457*/                    return set.value();
                        }
                }

/*1460*/        return null;
            }

            private static ClassLoader getCurrentContextClassLoader()
            {
/*1469*/        return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                    public final ClassLoader run()
                    {
/*1472*/                return Thread.currentThread().getContextClassLoader();
                    }

                    public final volatile Object run()
                    {
/*1469*/                return run();
                    }

        });
            }

            public static boolean annotationContainsAll(Set set, Set set1)
            {
/*1486*/        return ((Boolean)AccessController.doPrivileged(new PrivilegedAction(set, set1) {

                    public final Boolean run()
                    {
/*1490*/                return Boolean.valueOf(candidateAnnotations.containsAll(requiredAnnotations));
                    }

                    public final volatile Object run()
                    {
/*1486*/                return run();
                    }

                    final Set val$candidateAnnotations;
                    final Set val$requiredAnnotations;

                    
                    {
/*1486*/                candidateAnnotations = set;
/*1486*/                requiredAnnotations = set1;
/*1486*/                super();
                    }
        })).booleanValue();
            }

            public static Class translatePrimitiveType(Class class1)
            {
                Class class2;
/*1504*/        if((class2 = (Class)Constants.PRIMITIVE_MAP.get(class1)) == null)
/*1505*/            return class1;
/*1506*/        else
/*1506*/            return class2;
            }

            public static boolean isPrivate(Member member)
            {
/*1516*/        return ((member = member.getModifiers()) & 2) != 0;
            }

            public static Set getAllTypes(Type type)
            {
                LinkedHashSet linkedhashset;
/*1522*/        (linkedhashset = new LinkedHashSet()).add(type);
/*1525*/        if((type = getRawClass(type)) == null)
/*1526*/            return linkedhashset;
                Class class2;
/*1528*/        for(Type type1 = type.getGenericSuperclass(); type1 != null && (class2 = getRawClass(type1)) != null; type1 = class2.getGenericSuperclass())
/*1533*/            linkedhashset.add(type1);

/*1538*/        for(; type != null; type = type.getSuperclass())
                {
                    Type atype[];
/*1539*/            int i = (atype = type.getGenericInterfaces()).length;
/*1539*/            for(int j = 0; j < i; j++)
                    {
                        Type type2;
/*1539*/                addAllInterfaceContracts(type2 = atype[j], linkedhashset);
                    }

                }

/*1546*/        LinkedHashSet linkedhashset1 = new LinkedHashSet();
/*1547*/        HashMap hashmap = new HashMap();
                Type type3;
/*1549*/        for(Iterator iterator = linkedhashset.iterator(); iterator.hasNext();)
/*1549*/            if(!((type3 = (Type)iterator.next()) instanceof ParameterizedType))
                    {
/*1551*/                linkedhashset1.add(type3);
                    } else
                    {
/*1555*/                type = (ParameterizedType)type3;
/*1556*/                Class class1 = getRawClass(type3);
/*1558*/                hashmap.put(class1, type);
/*1560*/                if(isFilledIn(type))
                        {
/*1561*/                    linkedhashset1.add(type3);
                        } else
                        {
/*1565*/                    type = fillInPT(type, hashmap);
/*1566*/                    linkedhashset1.add(type);
/*1567*/                    hashmap.put(class1, type);
                        }
                    }

/*1570*/        return linkedhashset1;
            }

            private static ParameterizedType fillInPT(ParameterizedType parameterizedtype, HashMap hashmap)
            {
/*1576*/        if(isFilledIn(parameterizedtype))
/*1576*/            return parameterizedtype;
/*1579*/        Type atype[] = new Type[parameterizedtype.getActualTypeArguments().length];
/*1580*/        for(int i = 0; i < atype.length; i++)
                {
/*1581*/            Object obj = parameterizedtype.getActualTypeArguments()[i];
/*1584*/            atype[i] = ((Type) (obj));
/*1586*/            if(obj instanceof ParameterizedType)
                    {
/*1587*/                atype[i] = fillInPT((ParameterizedType)obj, hashmap);
/*1588*/                continue;
                    }
/*1591*/            if(!(obj instanceof TypeVariable))
/*1595*/                continue;
/*1595*/            Class class1 = (Class)((TypeVariable) (obj = (TypeVariable)obj)).getGenericDeclaration();
/*1598*/            boolean flag = false;
/*1599*/            int j = -1;
                    TypeVariable atypevariable[];
/*1600*/            int k = (atypevariable = class1.getTypeParameters()).length;
/*1600*/            int l = 0;
/*1600*/            do
                    {
/*1600*/                if(l >= k)
/*1600*/                    break;
/*1600*/                TypeVariable typevariable = atypevariable[l];
/*1601*/                j++;
/*1602*/                if(typevariable.equals(obj))
                        {
/*1603*/                    flag = true;
/*1604*/                    break;
                        }
/*1600*/                l++;
                    } while(true);
                    ParameterizedType parameterizedtype1;
/*1607*/            if(flag && (parameterizedtype1 = (ParameterizedType)hashmap.get(class1)) != null)
/*1612*/                atype[i] = parameterizedtype1.getActualTypeArguments()[j];
                }

                ParameterizedTypeImpl parameterizedtypeimpl;
/*1615*/        return parameterizedtypeimpl = new ParameterizedTypeImpl(getRawClass(parameterizedtype), atype);
            }

            private static boolean isFilledIn(ParameterizedType parameterizedtype, HashSet hashset)
            {
/*1620*/label0:
/*1620*/        do
                {
/*1620*/            if(hashset.contains(parameterizedtype))
/*1620*/                return false;
/*1621*/            hashset.add(parameterizedtype);
/*1623*/            int i = (parameterizedtype = parameterizedtype.getActualTypeArguments()).length;
/*1623*/            int j = 0;
/*1623*/            do
                    {
/*1623*/                if(j >= i)
/*1623*/                    break;
                        Object obj;
/*1623*/                if((obj = parameterizedtype[j]) instanceof TypeVariable)
/*1624*/                    return false;
/*1625*/                if(obj instanceof WildcardType)
/*1625*/                    return false;
/*1626*/                if(obj instanceof ParameterizedType)
                        {
/*1627*/                    parameterizedtype = (ParameterizedType)obj;
/*1627*/                    continue label0;
                        }
/*1623*/                j++;
                    } while(true);
/*1631*/            return true;
                } while(true);
            }

            private static boolean isFilledIn(ParameterizedType parameterizedtype)
            {
/*1635*/        return isFilledIn(parameterizedtype, new HashSet());
            }

            private static void addAllInterfaceContracts(Type type, LinkedHashSet linkedhashset)
            {
                Class class1;
/*1639*/        if((class1 = getRawClass(type)) == null)
/*1640*/            return;
/*1641*/        if(linkedhashset.contains(type))
/*1641*/            return;
/*1643*/        linkedhashset.add(type);
/*1645*/        int i = (type = class1.getGenericInterfaces()).length;
/*1645*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*1645*/            addAllInterfaceContracts(obj = type[j], linkedhashset);
                }

            }

            public static MethodWrapper createMethodWrapper(Method method)
            {
/*1657*/        return new MethodWrapperImpl(method);
            }

            public static Object cast(Object obj)
            {
/*1667*/        return obj;
            }

            private static final HashSet ESCAPE_CHARACTERS;
            private static final char ILLEGAL_CHARACTERS[] = {
/*  83*/        '{', '}', '[', ']', ':', ';', '=', ',', '\\'
            };
            private static final HashMap REPLACE_CHARACTERS;
            private static final String EQUALS_STRING = "=";
            private static final String COMMA_STRING = ",";
            private static final String QUOTE_STRING = "\"";

            static 
            {
/*  82*/        ESCAPE_CHARACTERS = new HashSet();
/*  86*/        REPLACE_CHARACTERS = new HashMap();
                char ac[];
/*  89*/        int i = (ac = ILLEGAL_CHARACTERS).length;
/*  89*/        for(int j = 0; j < i; j++)
                {
/*  89*/            char c = ac[j];
/*  90*/            ESCAPE_CHARACTERS.add(Character.valueOf(c));
                }

/*  93*/        REPLACE_CHARACTERS.put(Character.valueOf('\n'), Character.valueOf('n'));
/*  94*/        REPLACE_CHARACTERS.put(Character.valueOf('\r'), Character.valueOf('r'));
            }

}
