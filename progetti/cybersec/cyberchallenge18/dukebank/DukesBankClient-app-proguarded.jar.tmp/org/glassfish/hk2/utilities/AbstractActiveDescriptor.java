// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractActiveDescriptor.java

package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.inject.Named;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, NamedImpl

public abstract class AbstractActiveDescriptor extends DescriptorImpl
    implements ActiveDescriptor
{

            public AbstractActiveDescriptor()
            {
/*  79*/        advertisedContracts = new LinkedHashSet();
/*  85*/        isReified = true;
/*  87*/        cacheSet = false;
/*  90*/        rwLock = new ReentrantReadWriteLock();
/*  91*/        rLock = rwLock.readLock();
/*  92*/        wLock = rwLock.writeLock();
/*  99*/        scope = null;
            }

            protected AbstractActiveDescriptor(Descriptor descriptor)
            {
/* 109*/        super(descriptor);
/*  79*/        advertisedContracts = new LinkedHashSet();
/*  85*/        isReified = true;
/*  87*/        cacheSet = false;
/*  90*/        rwLock = new ReentrantReadWriteLock();
/*  91*/        rLock = rwLock.readLock();
/*  92*/        wLock = rwLock.writeLock();
/* 110*/        isReified = false;
/* 111*/        scope = null;
            }

            protected AbstractActiveDescriptor(Set set, Class class1, String s, Set set1, DescriptorType descriptortype, DescriptorVisibility descriptorvisibility, int i, 
                    Boolean boolean1, Boolean boolean2, String s1, Map map)
            {
/*  79*/        advertisedContracts = new LinkedHashSet();
/*  85*/        isReified = true;
/*  87*/        cacheSet = false;
/*  90*/        rwLock = new ReentrantReadWriteLock();
/*  91*/        rLock = rwLock.readLock();
/*  92*/        wLock = rwLock.writeLock();
/* 143*/        scope = class1;
/* 144*/        advertisedContracts.addAll(set);
/* 145*/        if(set1 != null && !set1.isEmpty())
                {
/* 146*/            qualifiers = new LinkedHashSet();
/* 147*/            qualifiers.addAll(set1);
                }
/* 150*/        setRanking(i);
/* 151*/        setDescriptorType(descriptortype);
/* 152*/        setDescriptorVisibility(descriptorvisibility);
/* 153*/        setName(s);
/* 154*/        setProxiable(boolean1);
/* 155*/        setProxyForSameScope(boolean2);
/* 157*/        if(class1 != null)
/* 158*/            setScope(class1.getName());
/* 161*/        set = set.iterator();
/* 161*/        do
                {
/* 161*/            if(!set.hasNext())
/* 161*/                break;
/* 161*/            if((s = ReflectionHelper.getRawClass(class1 = (Type)set.next())) != null)
/* 165*/                addAdvertisedContract(s.getName());
                } while(true);
/* 168*/        if(set1 != null)
/* 169*/            for(set = set1.iterator(); set.hasNext(); addQualifier(class1.annotationType().getName()))
/* 169*/                class1 = (Annotation)set.next();

/* 174*/        setClassAnalysisName(s1);
/* 176*/        if(map == null)
/* 176*/            return;
/* 178*/        for(set = map.entrySet().iterator(); set.hasNext();)
                {
/* 178*/            s = (String)(class1 = (java.util.Map.Entry)set.next()).getKey();
/* 180*/            class1 = (class1 = (List)class1.getValue()).iterator();
/* 182*/            while(class1.hasNext()) 
                    {
/* 182*/                set1 = (String)class1.next();
/* 183*/                addMetadata(s, set1);
                    }
                }

            }

            private void removeNamedQualifier()
            {
/* 190*/        wLock.lock();
/* 192*/        if(qualifiers == null)
                {
/* 201*/            wLock.unlock();
/* 201*/            return;
                }
/* 194*/        Iterator iterator = qualifiers.iterator();
                Annotation annotation;
/* 194*/        do
/* 194*/            if(!iterator.hasNext())
/* 194*/                break MISSING_BLOCK_LABEL_88;
/* 194*/        while(!(annotation = (Annotation)iterator.next()).annotationType().equals(javax/inject/Named));
/* 196*/        removeQualifierAnnotation(annotation);
/* 201*/        wLock.unlock();
/* 201*/        return;
/* 201*/        wLock.unlock();
/* 202*/        return;
                Exception exception;
/* 201*/        exception;
/* 201*/        wLock.unlock();
/* 201*/        throw exception;
            }

            public void setName(String s)
            {
/* 212*/        wLock.lock();
/* 213*/        super.setName(s);
/* 215*/        removeNamedQualifier();
/* 217*/        if(s == null)
                {
/* 221*/            wLock.unlock();
/* 221*/            return;
                }
/* 219*/        addQualifierAnnotation(new NamedImpl(s));
/* 221*/        wLock.unlock();
/* 222*/        return;
/* 221*/        s;
/* 221*/        wLock.unlock();
/* 221*/        throw s;
            }

            public Object getCache()
            {
                Object obj;
/* 231*/        rLock.lock();
/* 232*/        obj = cachedValue;
/* 234*/        rLock.unlock();
/* 234*/        return obj;
                Exception exception;
/* 234*/        exception;
/* 234*/        rLock.unlock();
/* 234*/        throw exception;
            }

            public boolean isCacheSet()
            {
                boolean flag;
/* 244*/        rLock.lock();
/* 245*/        flag = cacheSet;
/* 247*/        rLock.unlock();
/* 247*/        return flag;
                Exception exception;
/* 247*/        exception;
/* 247*/        rLock.unlock();
/* 247*/        throw exception;
            }

            public void setCache(Object obj)
            {
/* 257*/        wLock.lock();
/* 258*/        cachedValue = obj;
/* 259*/        cacheSet = true;
/* 261*/        wLock.unlock();
/* 262*/        return;
/* 261*/        obj;
/* 261*/        wLock.unlock();
/* 261*/        throw obj;
            }

            public void releaseCache()
            {
/* 271*/        wLock.lock();
/* 272*/        cacheSet = false;
/* 273*/        cachedValue = null;
/* 275*/        wLock.unlock();
/* 276*/        return;
                Exception exception;
/* 275*/        exception;
/* 275*/        wLock.unlock();
/* 275*/        throw exception;
            }

            public boolean isReified()
            {
                boolean flag;
/* 285*/        rLock.lock();
/* 286*/        flag = isReified;
/* 288*/        rLock.unlock();
/* 288*/        return flag;
                Exception exception;
/* 288*/        exception;
/* 288*/        rLock.unlock();
/* 288*/        throw exception;
            }

            public void setReified(boolean flag)
            {
/* 301*/        wLock.lock();
/* 302*/        isReified = flag;
/* 304*/        wLock.unlock();
/* 305*/        return;
/* 304*/        flag;
/* 304*/        wLock.unlock();
/* 304*/        throw flag;
            }

            public Set getContractTypes()
            {
                Set set;
/* 314*/        rLock.lock();
/* 315*/        set = Collections.unmodifiableSet(advertisedContracts);
/* 317*/        rLock.unlock();
/* 317*/        return set;
                Exception exception;
/* 317*/        exception;
/* 317*/        rLock.unlock();
/* 317*/        throw exception;
            }

            public void addContractType(Type type)
            {
/* 327*/        wLock.lock();
/* 328*/        if(type == null)
                {
/* 338*/            wLock.unlock();
/* 338*/            return;
                }
/* 331*/        advertisedContracts.add(type);
/* 332*/        if((type = ReflectionHelper.getRawClass(type)) == null)
                {
/* 338*/            wLock.unlock();
/* 338*/            return;
                }
/* 336*/        addAdvertisedContract(type.getName());
/* 338*/        wLock.unlock();
/* 339*/        return;
/* 338*/        type;
/* 338*/        wLock.unlock();
/* 338*/        throw type;
            }

            public boolean removeContractType(Type type)
            {
/* 349*/        wLock.lock();
/* 350*/        if(type == null)
                {
/* 359*/            wLock.unlock();
/* 359*/            return false;
                }
/* 352*/        boolean flag = advertisedContracts.remove(type);
/* 354*/        if((type = ReflectionHelper.getRawClass(type)) == null)
                {
/* 359*/            wLock.unlock();
/* 359*/            return flag;
                }
/* 357*/        type = removeAdvertisedContract(type.getName());
/* 359*/        wLock.unlock();
/* 359*/        return type;
/* 359*/        type;
/* 359*/        wLock.unlock();
/* 359*/        throw type;
            }

            public Annotation getScopeAsAnnotation()
            {
/* 365*/        return scopeAnnotation;
            }

            public void setScopeAsAnnotation(Annotation annotation)
            {
/* 377*/        scopeAnnotation = annotation;
/* 378*/        if(annotation != null)
/* 379*/            setScopeAnnotation(annotation.annotationType());
            }

            public Class getScopeAnnotation()
            {
/* 388*/        return scope;
            }

            public void setScopeAnnotation(Class class1)
            {
/* 397*/        scope = class1;
/* 398*/        setScope(scope.getName());
            }

            public Set getQualifierAnnotations()
            {
                Set set;
/* 407*/        rLock.lock();
/* 408*/        if(qualifiers != null)
/* 408*/            break MISSING_BLOCK_LABEL_31;
/* 408*/        set = EMPTY_QUALIFIER_SET;
/* 412*/        rLock.unlock();
/* 412*/        return set;
/* 410*/        set = Collections.unmodifiableSet(qualifiers);
/* 412*/        rLock.unlock();
/* 412*/        return set;
                Exception exception;
/* 412*/        exception;
/* 412*/        rLock.unlock();
/* 412*/        throw exception;
            }

            public void addQualifierAnnotation(Annotation annotation)
            {
/* 423*/        wLock.lock();
/* 424*/        if(annotation == null)
                {
/* 429*/            wLock.unlock();
/* 429*/            return;
                }
/* 425*/        if(qualifiers == null)
/* 425*/            qualifiers = new LinkedHashSet();
/* 426*/        qualifiers.add(annotation);
/* 427*/        addQualifier(annotation.annotationType().getName());
/* 429*/        wLock.unlock();
/* 430*/        return;
/* 429*/        annotation;
/* 429*/        wLock.unlock();
/* 429*/        throw annotation;
            }

            public boolean removeQualifierAnnotation(Annotation annotation)
            {
/* 441*/        wLock.lock();
/* 442*/        if(annotation == null)
                {
/* 450*/            wLock.unlock();
/* 450*/            return false;
                }
/* 443*/        if(qualifiers == null)
                {
/* 450*/            wLock.unlock();
/* 450*/            return false;
                }
                boolean flag;
/* 445*/        flag = qualifiers.remove(annotation);
/* 446*/        removeQualifier(annotation.annotationType().getName());
/* 450*/        wLock.unlock();
/* 450*/        return flag;
/* 450*/        annotation;
/* 450*/        wLock.unlock();
/* 450*/        throw annotation;
            }

            public Long getFactoryServiceId()
            {
/* 456*/        return factoryServiceId;
            }

            public Long getFactoryLocatorId()
            {
/* 461*/        return factoryLocatorId;
            }

            public void setFactoryId(Long long1, Long long2)
            {
/* 474*/        if(!getDescriptorType().equals(DescriptorType.PROVIDE_METHOD))
                {
/* 475*/            throw new IllegalStateException("The descriptor type must be PROVIDE_METHOD");
                } else
                {
/* 478*/            factoryServiceId = long2;
/* 479*/            factoryLocatorId = long1;
/* 480*/            return;
                }
            }

            public List getInjectees()
            {
/* 487*/        return Collections.emptyList();
            }

            public void dispose(Object obj)
            {
            }

            public int hashCode()
            {
/* 500*/        return super.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 505*/        return super.equals(obj);
            }

            private static final long serialVersionUID = 0x62425286fc67ea4bL;
            private static final Set EMPTY_QUALIFIER_SET = Collections.emptySet();
            private Set advertisedContracts;
            private Annotation scopeAnnotation;
            private Class scope;
            private Set qualifiers;
            private Long factoryServiceId;
            private Long factoryLocatorId;
            private boolean isReified;
            private transient boolean cacheSet;
            private transient Object cachedValue;
            private final ReentrantReadWriteLock rwLock;
            private final Lock rLock;
            private final Lock wLock;

}
