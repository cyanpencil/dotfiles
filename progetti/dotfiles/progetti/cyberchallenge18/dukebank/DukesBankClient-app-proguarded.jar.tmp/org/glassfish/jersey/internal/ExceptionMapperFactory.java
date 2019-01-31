// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMapperFactory.java

package org.glassfish.jersey.internal;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.ext.ExceptionMapper;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.ClassTypePair;
import org.glassfish.jersey.spi.ExceptionMappers;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages

public class ExceptionMapperFactory
    implements ExceptionMappers
{
    static class ExceptionMapperType
    {

                ServiceHandle mapper;
                Class exceptionType;

                public ExceptionMapperType(ServiceHandle servicehandle, Class class1)
                {
/* 103*/            mapper = servicehandle;
/* 104*/            exceptionType = class1;
                }
    }

    public static class Binder extends AbstractBinder
    {

                protected void configure()
                {
/*  93*/            bindAsContract(org/glassfish/jersey/internal/ExceptionMapperFactory).to(org/glassfish/jersey/spi/ExceptionMappers).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            public ExceptionMapper findMapping(Throwable throwable)
            {
/* 113*/        return find(throwable.getClass(), throwable);
            }

            public ExceptionMapper find(Class class1)
            {
/* 118*/        return find(class1, null);
            }

            private ExceptionMapper find(Class class1, Throwable throwable)
            {
/* 123*/        ExceptionMapper exceptionmapper = null;
/* 124*/        int i = 0x7fffffff;
/* 126*/        for(Iterator iterator = exceptionMapperTypes.iterator(); iterator.hasNext();)
                {
/* 126*/            Object obj = (ExceptionMapperType)iterator.next();
                    int j;
/* 127*/            if((j = distance(class1, ((ExceptionMapperType) (obj)).exceptionType)) >= 0 && j <= i)
                    {
/* 129*/                obj = (ExceptionMapper)((ExceptionMapperType) (obj)).mapper.getService();
/* 131*/                if(isPreferredCandidate(throwable, ((ExceptionMapper) (obj)), j == i))
                        {
/* 132*/                    exceptionmapper = ((ExceptionMapper) (obj));
/* 133*/                    i = j;
/* 134*/                    if(j == 0)
/* 136*/                        return exceptionmapper;
                        }
                    }
                }

/* 141*/        return exceptionmapper;
            }

            private boolean isPreferredCandidate(Throwable throwable, ExceptionMapper exceptionmapper, boolean flag)
            {
/* 156*/        if(throwable == null)
/* 157*/            return true;
/* 159*/        if(exceptionmapper instanceof ExtendedExceptionMapper)
/* 160*/            return !flag && ((ExtendedExceptionMapper)exceptionmapper).isMappable(throwable);
/* 163*/        return !flag;
            }

            public ExceptionMapperFactory(ServiceLocator servicelocator)
            {
/* 177*/        servicelocator = (servicelocator = Providers.getAllServiceHandles(servicelocator, javax/ws/rs/ext/ExceptionMapper)).iterator();
/* 180*/        do
                {
/* 180*/            if(!servicelocator.hasNext())
/* 180*/                break;
                    ServiceHandle servicehandle;
                    Object obj;
/* 180*/            if(Proxy.isProxyClass((obj = (ExceptionMapper)(servicehandle = (ServiceHandle)servicelocator.next()).getService()).getClass()))
                    {
/* 184*/                obj = new TreeSet(new Comparator() {

                            public int compare(Class class1, Class class2)
                            {
/* 189*/                        return !class1.isAssignableFrom(class2) ? 1 : -1;
                            }

                            public volatile int compare(Object obj2, Object obj3)
                            {
/* 185*/                        return compare((Class)obj2, (Class)obj3);
                            }

                            final ExceptionMapperFactory this$0;

                    
                    {
/* 185*/                this$0 = ExceptionMapperFactory.this;
/* 185*/                super();
                    }
                });
                        Object obj1;
/* 193*/                obj1 = ((Set) (obj1 = servicehandle.getActiveDescriptor().getContractTypes())).iterator();
/* 194*/                do
                        {
/* 194*/                    if(!((Iterator) (obj1)).hasNext())
/* 194*/                        break;
                            Type type;
/* 194*/                    if(((type = (Type)((Iterator) (obj1)).next()) instanceof Class) && javax/ws/rs/ext/ExceptionMapper.isAssignableFrom((Class)type) && type != javax/ws/rs/ext/ExceptionMapper)
/* 198*/                        ((SortedSet) (obj)).add((Class)type);
                        } while(true);
/* 202*/                if(!((SortedSet) (obj)).isEmpty() && (obj1 = getExceptionType((Class)((SortedSet) (obj)).first())) != null)
/* 205*/                    exceptionMapperTypes.add(new ExceptionMapperType(servicehandle, ((Class) (obj1))));
                    } else
/* 209*/            if((obj = getExceptionType(obj.getClass())) != null)
/* 211*/                exceptionMapperTypes.add(new ExceptionMapperType(servicehandle, ((Class) (obj))));
                } while(true);
            }

            private int distance(Class class1, Class class2)
            {
/* 218*/        int i = 0;
/* 219*/        if(!class2.isAssignableFrom(class1))
/* 220*/            return -1;
/* 223*/        while(class1 != class2) 
                {
/* 224*/            class1 = class1.getSuperclass();
/* 225*/            i++;
                }
/* 228*/        return i;
            }

            private Class getExceptionType(Class class1)
            {
/* 233*/        Class class2 = getType(class1);
/* 234*/        if(java/lang/Throwable.isAssignableFrom(class2))
/* 235*/            return class2;
/* 238*/        if(LOGGER.isLoggable(Level.WARNING))
/* 239*/            LOGGER.warning(LocalizationMessages.EXCEPTION_MAPPER_SUPPORTED_TYPE_UNKNOWN(class1.getName()));
/* 242*/        return null;
            }

            private Class getType(Class class1)
            {
/* 252*/        for(Class class2 = class1; class2 != java/lang/Object; class2 = class2.getSuperclass())
                {
                    Class class3;
/* 255*/            if((class3 = getTypeFromInterface(class2, class1)) != null)
/* 257*/                return class3;
                }

/* 263*/        throw new ProcessingException(LocalizationMessages.ERROR_FINDING_EXCEPTION_MAPPER_TYPE(class1));
            }

            private Class getTypeFromInterface(Class class1, Class class2)
            {
/* 273*/label0:
/* 273*/        do
                {
                    Type atype[];
/* 273*/            int i = (atype = atype = class1.getGenericInterfaces()).length;
/* 275*/            for(int j = 0; j < i; j++)
                    {
                        Object obj;
/* 275*/                if((obj = atype[j]) instanceof ParameterizedType)
                        {
/* 277*/                    if(((ParameterizedType) (obj = (ParameterizedType)obj)).getRawType() == javax/ws/rs/ext/ExceptionMapper || ((ParameterizedType) (obj)).getRawType() == org/glassfish/jersey/spi/ExtendedExceptionMapper)
/* 280*/                        return getResolvedType(((ParameterizedType) (obj)).getActualTypeArguments()[0], class2, class1);
/* 282*/                    continue;
                        }
/* 282*/                if(!(obj instanceof Class))
/* 283*/                    continue;
/* 283*/                class1 = (Class)obj;
/* 285*/                if(!javax/ws/rs/ext/ExceptionMapper.isAssignableFrom(class1))
/* 286*/                    continue;
/* 286*/                class1 = class1;
/* 286*/                this = this;
/* 286*/                continue label0;
                    }

/* 291*/            return null;
                } while(true);
            }

            private Class getResolvedType(Type type, Class class1, Class class2)
            {
/* 295*/        if(type instanceof Class)
/* 296*/            return (Class)type;
/* 297*/        if(type instanceof TypeVariable)
/* 298*/            if((type = ReflectionHelper.resolveTypeVariable(class1, class2, (TypeVariable)type)) != null)
/* 300*/                return type.rawClass();
/* 302*/            else
/* 302*/                return null;
/* 304*/        if(type instanceof ParameterizedType)
/* 305*/            return (Class)(type = (ParameterizedType)type).getRawType();
/* 308*/        else
/* 308*/            return null;
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/ExceptionMapperFactory.getName());
            private final Set exceptionMapperTypes = new LinkedHashSet();

}
