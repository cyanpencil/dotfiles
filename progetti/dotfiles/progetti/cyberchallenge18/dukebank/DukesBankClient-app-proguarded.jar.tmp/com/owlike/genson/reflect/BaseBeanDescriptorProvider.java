// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseBeanDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.Genson;
import com.owlike.genson.Trilean;
import com.owlike.genson.annotation.JsonCreator;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            AbstractBeanDescriptorProvider, BeanCreator, BeanMutatorAccessorResolver, BeanProperty, 
//            BeanPropertyFactory, PropertyAccessor, PropertyMutator, PropertyNameResolver, 
//            TypeUtil, VisibilityFilter

public class BaseBeanDescriptorProvider extends AbstractBeanDescriptorProvider
{

            public BaseBeanDescriptorProvider(AbstractBeanDescriptorProvider.ContextualConverterFactory contextualconverterfactory, BeanPropertyFactory beanpropertyfactory, BeanMutatorAccessorResolver beanmutatoraccessorresolver, PropertyNameResolver propertynameresolver, boolean flag, boolean flag1, boolean flag2)
            {
/*  51*/        super(contextualconverterfactory);
/*  53*/        if(beanmutatoraccessorresolver == null)
/*  54*/            throw new IllegalArgumentException("mutatorAccessorResolver must be not null!");
/*  55*/        if(propertynameresolver == null)
/*  56*/            throw new IllegalArgumentException("nameResolver must be not null!");
/*  57*/        if(beanpropertyfactory == null)
/*  58*/            throw new IllegalArgumentException("propertyFactory must be not null!");
/*  60*/        propertyFactory = beanpropertyfactory;
/*  61*/        mutatorAccessorResolver = beanmutatoraccessorresolver;
/*  62*/        nameResolver = propertynameresolver;
/*  63*/        useFields = flag1;
/*  64*/        useGettersAndSetters = flag;
/*  65*/        if(!flag1 && !flag)
                {
/*  66*/            throw new IllegalArgumentException("You must allow at least one mode: with fields or methods.");
                } else
                {
/*  67*/            favorEmptyCreators = flag2;
/*  68*/            return;
                }
            }

            public List provideBeanCreators(Type type, Genson genson)
            {
/*  72*/        ArrayList arraylist = new ArrayList();
                Class class1;
/*  73*/        if((class1 = TypeUtil.getRawClass(type)).isMemberClass() && (class1.getModifiers() & 8) == 0)
/*  75*/            return arraylist;
/*  77*/        provideConstructorCreators(type, arraylist, genson);
/*  78*/        for(class1 = class1; class1 != null && !java/lang/Object.equals(class1); class1 = class1.getSuperclass())
/*  80*/            provideMethodCreators(class1, arraylist, type, genson);

/*  82*/        return arraylist;
            }

            public void provideBeanPropertyAccessors(Type type, Map map, Genson genson)
            {
                ArrayDeque arraydeque;
/*  88*/        (arraydeque = new ArrayDeque()).push(TypeUtil.getRawClass(type));
/*  90*/        do
                {
/*  90*/            if(arraydeque.isEmpty())
/*  91*/                break;
                    Class class1;
/*  91*/            if((class1 = (Class)arraydeque.pop()).getSuperclass() != null && class1.getSuperclass() != java/lang/Object)
/*  93*/                arraydeque.push(class1.getSuperclass());
                    Class aclass[];
/*  95*/            int i = (aclass = class1.getInterfaces()).length;
/*  95*/            for(int j = 0; j < i; j++)
                    {
/*  95*/                Class class2 = aclass[j];
/*  95*/                arraydeque.push(class2);
                    }

/*  98*/            if(useFields)
/*  98*/                provideFieldAccessors(class1, map, type, genson);
/* 100*/            if(useGettersAndSetters)
/* 100*/                provideMethodAccessors(class1, map, type, genson);
                } while(true);
            }

            public void provideBeanPropertyMutators(Type type, Map map, Genson genson)
            {
                ArrayDeque arraydeque;
/* 107*/        (arraydeque = new ArrayDeque()).push(TypeUtil.getRawClass(type));
/* 109*/        do
                {
/* 109*/            if(arraydeque.isEmpty())
/* 110*/                break;
                    Class class1;
/* 110*/            if((class1 = (Class)arraydeque.pop()).getSuperclass() != null && class1.getSuperclass() != java/lang/Object)
/* 112*/                arraydeque.push(class1.getSuperclass());
                    Class aclass[];
/* 114*/            int i = (aclass = class1.getInterfaces()).length;
/* 114*/            for(int j = 0; j < i; j++)
                    {
/* 114*/                Class class2 = aclass[j];
/* 114*/                arraydeque.push(class2);
                    }

/* 117*/            if(useFields)
/* 117*/                provideFieldMutators(class1, map, type, genson);
/* 119*/            if(useGettersAndSetters)
/* 119*/                provideMethodMutators(class1, map, type, genson);
                } while(true);
            }

            protected void provideConstructorCreators(Type type, List list, Genson genson)
            {
                Class class1;
                Constructor aconstructor[];
/* 124*/        int i = (aconstructor = aconstructor = (class1 = TypeUtil.getRawClass(type)).getDeclaredConstructors()).length;
/* 126*/        for(int j = 0; j < i; j++)
                {
/* 126*/            Constructor constructor = aconstructor[j];
/* 127*/            if(Trilean.TRUE != mutatorAccessorResolver.isCreator(constructor, class1))
/* 128*/                continue;
                    Type atype[];
                    int k;
/* 128*/            String as[] = new String[k = (atype = constructor.getGenericParameterTypes()).length];
                    int l;
                    String s;
/* 131*/            for(l = 0; l < k && (s = nameResolver.resolve(l, constructor)) != null; l++)
/* 135*/                as[l] = s;

/* 138*/            if(l == k)
                    {
/* 139*/                BeanCreator beancreator = propertyFactory.createCreator(type, constructor, as, genson);
/* 140*/                list.add(beancreator);
                    }
                }

            }

            protected void provideMethodCreators(Class class1, List list, Type type, Genson genson)
            {
/* 148*/        int i = (class1 = class1 = class1.getDeclaredMethods()).length;
/* 149*/        for(int j = 0; j < i; j++)
                {
/* 149*/            Method method = class1[j];
/* 150*/            if(Trilean.TRUE != mutatorAccessorResolver.isCreator(method, TypeUtil.getRawClass(type)))
/* 151*/                continue;
                    Type atype[];
                    int k;
/* 151*/            String as[] = new String[k = (atype = method.getGenericParameterTypes()).length];
                    int l;
                    String s;
/* 154*/            for(l = 0; l < k && (s = nameResolver.resolve(l, method)) != null; l++)
/* 158*/                as[l] = s;

/* 161*/            if(l == k)
                    {
/* 162*/                BeanCreator beancreator = propertyFactory.createCreator(type, method, as, genson);
/* 163*/                list.add(beancreator);
                    }
                }

            }

            protected void provideFieldAccessors(Class class1, Map map, Type type, Genson genson)
            {
                Field afield[];
/* 171*/        int i = (afield = afield = class1.getDeclaredFields()).length;
/* 172*/        for(int j = 0; j < i; j++)
                {
/* 172*/            Object obj = afield[j];
/* 173*/            if(Trilean.TRUE != mutatorAccessorResolver.isAccessor(((Field) (obj)), TypeUtil.getRawClass(type)))
/* 174*/                continue;
                    String s;
/* 174*/            if((s = nameResolver.resolve(((Field) (obj)))) == null)
/* 176*/                throw new IllegalStateException((new StringBuilder("Field '")).append(((Field) (obj)).getName()).append("' from class ").append(class1.getName()).append(" has been discovered as accessor but its name couldn't be resolved!").toString());
/* 180*/            obj = propertyFactory.createAccessor(s, ((Field) (obj)), type, genson);
/* 181*/            update(((BeanProperty) (obj)), map);
                }

            }

            protected void provideMethodAccessors(Class class1, Map map, Type type, Genson genson)
            {
                Method amethod[];
/* 188*/        int i = (amethod = amethod = class1.getDeclaredMethods()).length;
/* 189*/        for(int j = 0; j < i; j++)
                {
/* 189*/            Object obj = amethod[j];
/* 190*/            if(Trilean.TRUE != mutatorAccessorResolver.isAccessor(((Method) (obj)), TypeUtil.getRawClass(type)))
/* 191*/                continue;
                    String s;
/* 191*/            if((s = nameResolver.resolve(((Method) (obj)))) == null)
/* 193*/                throw new IllegalStateException((new StringBuilder("Method '")).append(((Method) (obj)).getName()).append("' from class ").append(class1.getName()).append(" has been discovered as accessor but its name couldn't be resolved!").toString());
/* 197*/            obj = propertyFactory.createAccessor(s, ((Method) (obj)), type, genson);
/* 198*/            update(((BeanProperty) (obj)), map);
                }

            }

            protected void provideFieldMutators(Class class1, Map map, Type type, Genson genson)
            {
                Field afield[];
/* 205*/        int i = (afield = afield = class1.getDeclaredFields()).length;
/* 206*/        for(int j = 0; j < i; j++)
                {
/* 206*/            Object obj = afield[j];
/* 207*/            if(Trilean.TRUE != mutatorAccessorResolver.isMutator(((Field) (obj)), TypeUtil.getRawClass(type)))
/* 208*/                continue;
                    String s;
/* 208*/            if((s = nameResolver.resolve(((Field) (obj)))) == null)
/* 210*/                throw new IllegalStateException((new StringBuilder("Field '")).append(((Field) (obj)).getName()).append("' from class ").append(class1.getName()).append(" has been discovered as mutator but its name couldn't be resolved!").toString());
/* 215*/            obj = propertyFactory.createMutator(s, ((Field) (obj)), type, genson);
/* 216*/            update(((BeanProperty) (obj)), map);
                }

            }

            protected void provideMethodMutators(Class class1, Map map, Type type, Genson genson)
            {
                Method amethod[];
/* 223*/        int i = (amethod = amethod = class1.getDeclaredMethods()).length;
/* 224*/        for(int j = 0; j < i; j++)
                {
/* 224*/            Object obj = amethod[j];
/* 225*/            if(Trilean.TRUE != mutatorAccessorResolver.isMutator(((Method) (obj)), TypeUtil.getRawClass(type)))
/* 226*/                continue;
                    String s;
/* 226*/            if((s = nameResolver.resolve(((Method) (obj)))) == null)
/* 228*/                throw new IllegalStateException((new StringBuilder("Method '")).append(((Method) (obj)).getName()).append("' from class ").append(class1.getName()).append(" has been discovered as mutator but its name couldn't be resolved!").toString());
/* 232*/            obj = propertyFactory.createMutator(s, ((Method) (obj)), type, genson);
/* 233*/            update(((BeanProperty) (obj)), map);
                }

            }

            protected void update(BeanProperty beanproperty, Map map)
            {
                LinkedList linkedlist;
/* 239*/        if((linkedlist = (LinkedList)map.get(beanproperty.name)) == null)
                {
/* 241*/            linkedlist = new LinkedList();
/* 242*/            map.put(beanproperty.name, linkedlist);
                }
/* 244*/        linkedlist.add(beanproperty);
            }

            protected BeanCreator checkAndMerge(Type type, List list)
            {
                BeanCreator beancreator;
/* 249*/label0:
                {
/* 249*/            type = TypeUtil.getRawClass(type);
/* 255*/            if(list == null || list.isEmpty())
/* 255*/                return null;
/* 258*/            if(favorEmptyCreators)
/* 259*/                Collections.sort(list, _beanCreatorsComparator);
/* 262*/            boolean flag = false;
/* 263*/            beancreator = null;
/* 266*/            for(int i = 0; i < list.size(); i++)
                    {
                        BeanCreator beancreator1;
/* 267*/                if(!(beancreator1 = (BeanCreator)list.get(i)).isAnnotationPresent(com/owlike/genson/annotation/JsonCreator))
/* 269*/                    continue;
/* 269*/                if(!flag)
/* 270*/                    flag = true;
/* 272*/                else
/* 272*/                    _throwCouldCreateBeanDescriptor(type, " only one @JsonCreator annotation per class is allowed.");
                    }

/* 277*/            if(flag)
                    {
/* 278*/                Iterator iterator = list.iterator();
                        BeanCreator beancreator2;
/* 278*/                do
/* 278*/                    if(!iterator.hasNext())
/* 278*/                        break label0;
/* 278*/                while(!(beancreator2 = (BeanCreator)iterator.next()).isAnnotationPresent(com/owlike/genson/annotation/JsonCreator));
/* 279*/                return beancreator2;
                    }
/* 281*/            beancreator = (BeanCreator)list.get(0);
                }
/* 284*/        return beancreator;
            }

            protected void _throwCouldCreateBeanDescriptor(Class class1, String s)
            {
/* 288*/        throw new IllegalStateException((new StringBuilder("Could not create BeanDescriptor for type ")).append(class1.getName()).append(",").append(s).toString());
            }

            protected PropertyAccessor checkAndMergeAccessors(String s, LinkedList linkedlist)
            {
/* 295*/        s = (PropertyAccessor)_mostSpecificPropertyDeclaringClass(s, linkedlist);
/* 296*/        if(VisibilityFilter.ABSTRACT.isVisible(s.getModifiers()))
/* 296*/            return s;
/* 296*/        else
/* 296*/            return null;
            }

            protected PropertyMutator checkAndMergeMutators(String s, LinkedList linkedlist)
            {
/* 302*/        s = (PropertyMutator)_mostSpecificPropertyDeclaringClass(s, linkedlist);
/* 303*/        if(VisibilityFilter.ABSTRACT.isVisible(s.getModifiers()))
/* 303*/            return s;
/* 303*/        else
/* 303*/            return null;
            }

            protected BeanProperty _mostSpecificPropertyDeclaringClass(String s, LinkedList linkedlist)
            {
/* 308*/        linkedlist = (BeanProperty)(s = linkedlist.iterator()).next();
/* 310*/        do
                {
/* 310*/            if(!s.hasNext())
/* 311*/                break;
                    BeanProperty beanproperty;
/* 311*/            (beanproperty = (BeanProperty)s.next()).updateBoth(linkedlist);
/* 316*/            if(((BeanProperty) (linkedlist)).declaringClass.equals(beanproperty.declaringClass) && linkedlist.priority() < beanproperty.priority() || ((BeanProperty) (linkedlist)).declaringClass.isAssignableFrom(beanproperty.declaringClass))
/* 318*/                linkedlist = beanproperty;
                } while(true);
/* 322*/        return linkedlist;
            }

            protected void mergeMutatorsWithCreatorProperties(Type type, Map map, BeanCreator beancreator)
            {
/* 328*/        for(type = beancreator.parameters.entrySet().iterator(); type.hasNext();)
                {
/* 328*/            beancreator = (java.util.Map.Entry)type.next();
                    Object obj;
/* 329*/            if((obj = (PropertyMutator)map.get(beancreator.getKey())) == null)
                    {
/* 333*/                obj = (BeanCreator.BeanCreatorProperty)beancreator.getValue();
/* 334*/                map.put(beancreator.getKey(), obj);
                    } else
                    {
/* 337*/                ((BeanCreator.BeanCreatorProperty)beancreator.getValue()).updateBoth(((BeanProperty) (obj)));
                    }
                }

            }

            protected void mergeAccessorsWithCreatorProperties(Type type, List list, BeanCreator beancreator)
            {
            }

            private static final Comparator _beanCreatorsComparator = new Comparator() {

                public final int compare(BeanCreator beancreator, BeanCreator beancreator1)
                {
/*  37*/            return beancreator.parameters.size() - beancreator1.parameters.size();
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  35*/            return compare((BeanCreator)obj, (BeanCreator)obj1);
                }

    };
            private final BeanPropertyFactory propertyFactory;
            protected final BeanMutatorAccessorResolver mutatorAccessorResolver;
            protected final PropertyNameResolver nameResolver;
            protected final boolean useGettersAndSetters;
            protected final boolean useFields;
            protected final boolean favorEmptyCreators;

}
