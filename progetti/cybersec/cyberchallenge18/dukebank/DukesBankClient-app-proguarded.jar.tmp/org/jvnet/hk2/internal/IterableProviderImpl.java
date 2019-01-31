// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IterableProviderImpl.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.InjecteeImpl;
import org.glassfish.hk2.utilities.NamedImpl;
import org.glassfish.hk2.utilities.reflection.Pretty;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

public class IterableProviderImpl
    implements IterableProvider
{
    static class MyHandleIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 262*/            return !handles.isEmpty();
                }

                public ServiceHandle next()
                {
/* 270*/            return (ServiceHandle)handles.removeFirst();
                }

                public void remove()
                {
/* 278*/            throw new UnsupportedOperationException();
                }

                public volatile Object next()
                {
/* 250*/            return next();
                }

                private final LinkedList handles;

                private MyHandleIterator(List list)
                {
/* 254*/            handles = new LinkedList(list);
                }

    }

    static class HandleIterable
        implements Iterable
    {

                public Iterator iterator()
                {
/* 245*/            return new MyHandleIterator(handles);
                }

                private final List handles;

                private HandleIterable(List list)
                {
/* 237*/            handles = new LinkedList(list);
                }

    }

    static class MyIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 207*/            return !handles.isEmpty();
                }

                public Object next()
                {
                    ServiceHandle servicehandle;
/* 215*/            if(handles.isEmpty())
/* 215*/                throw new NoSuchElementException();
/* 217*/            else
/* 217*/                return (servicehandle = (ServiceHandle)handles.removeFirst()).getService();
                }

                public void remove()
                {
/* 227*/            throw new UnsupportedOperationException();
                }

                private final LinkedList handles;

                private MyIterator(List list)
                {
/* 199*/            handles = new LinkedList(list);
                }

    }


            IterableProviderImpl(ServiceLocatorImpl servicelocatorimpl, Type type, Set set, Unqualified unqualified1, Injectee injectee, boolean flag)
            {
/*  82*/        locator = servicelocatorimpl;
/*  83*/        requiredType = type;
/*  84*/        requiredQualifiers = Collections.unmodifiableSet(set);
/*  85*/        unqualified = unqualified1;
/*  86*/        originalInjectee = injectee;
/*  87*/        isIterable = flag;
            }

            private void justInTime()
            {
                InjecteeImpl injecteeimpl;
/*  91*/        (injecteeimpl = new InjecteeImpl(originalInjectee)).setRequiredType(requiredType);
/*  93*/        injecteeimpl.setRequiredQualifiers(requiredQualifiers);
/*  94*/        if(unqualified != null)
/*  95*/            injecteeimpl.setUnqualified(unqualified);
/*  99*/        locator.getInjecteeDescriptor(injecteeimpl);
            }

            public Object get()
            {
/* 108*/        justInTime();
/* 111*/        return locator.getUnqualifiedService(requiredType, unqualified, isIterable, (Annotation[])requiredQualifiers.toArray(new Annotation[requiredQualifiers.size()]));
            }

            public ServiceHandle getHandle()
            {
/* 121*/        justInTime();
/* 123*/        return locator.getUnqualifiedServiceHandle(requiredType, unqualified, isIterable, (Annotation[])requiredQualifiers.toArray(new Annotation[requiredQualifiers.size()]));
            }

            public Iterator iterator()
            {
/* 133*/        justInTime();
/* 136*/        List list = (List)ReflectionHelper.cast(locator.getAllUnqualifiedServiceHandles(requiredType, unqualified, isIterable, (Annotation[])requiredQualifiers.toArray(new Annotation[requiredQualifiers.size()])));
/* 139*/        return new MyIterator(list);
            }

            public int getSize()
            {
/* 147*/        justInTime();
/* 149*/        return locator.getAllUnqualifiedServiceHandles(requiredType, unqualified, isIterable, (Annotation[])requiredQualifiers.toArray(new Annotation[requiredQualifiers.size()])).size();
            }

            public IterableProvider named(String s)
            {
/* 158*/        return qualifiedWith(new Annotation[] {
/* 158*/            new NamedImpl(s)
                });
            }

            public IterableProvider ofType(Type type)
            {
/* 166*/        return new IterableProviderImpl(locator, type, requiredQualifiers, unqualified, originalInjectee, isIterable);
            }

            public transient IterableProvider qualifiedWith(Annotation aannotation[])
            {
/* 174*/        HashSet hashset = new HashSet(requiredQualifiers);
/* 175*/        int i = (aannotation = aannotation).length;
/* 175*/        for(int j = 0; j < i; j++)
                {
/* 175*/            Annotation annotation = aannotation[j];
/* 176*/            hashset.add(annotation);
                }

/* 179*/        return new IterableProviderImpl(locator, requiredType, hashset, unqualified, originalInjectee, isIterable);
            }

            public Iterable handleIterator()
            {
/* 187*/        justInTime();
/* 189*/        List list = (List)ReflectionHelper.cast(locator.getAllServiceHandles(requiredType, (Annotation[])requiredQualifiers.toArray(new Annotation[requiredQualifiers.size()])));
/* 192*/        return new HandleIterable(list);
            }

            public String toString()
            {
/* 285*/        return (new StringBuilder("IterableProviderImpl(")).append(Pretty.type(requiredType)).append(",").append(Pretty.collection(requiredQualifiers)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ServiceLocatorImpl locator;
            private final Type requiredType;
            private final Set requiredQualifiers;
            private final Unqualified unqualified;
            private final Injectee originalInjectee;
            private final boolean isIterable;
}
