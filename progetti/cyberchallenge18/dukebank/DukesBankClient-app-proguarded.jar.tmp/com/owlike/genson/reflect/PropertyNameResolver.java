// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.annotation.JsonProperty;
import java.lang.reflect.*;
import java.util.*;

public interface PropertyNameResolver
{
    public static class AnnotationPropertyNameResolver
        implements PropertyNameResolver
    {

                public String resolve(int i, Constructor constructor)
                {
/* 158*/            i = constructor.getParameterAnnotations()[i];
/* 159*/            constructor = null;
/* 160*/            int j = 0;
/* 160*/            do
                    {
/* 160*/                if(j >= i.length)
/* 161*/                    break;
/* 161*/                if(i[j] instanceof JsonProperty)
                        {
/* 162*/                    constructor = ((JsonProperty)i[j]).value();
/* 163*/                    break;
                        }
/* 160*/                j++;
                    } while(true);
/* 166*/            if("".equals(constructor))
/* 166*/                return null;
/* 166*/            else
/* 166*/                return constructor;
                }

                public String resolve(int i, Method method)
                {
/* 170*/            i = method.getParameterAnnotations()[i];
/* 171*/            method = null;
/* 172*/            int j = (i = i).length;
/* 172*/            int k = 0;
/* 172*/            do
                    {
/* 172*/                if(k >= j)
/* 172*/                    break;
                        Object obj;
/* 172*/                if((obj = i[k]) instanceof JsonProperty)
                        {
/* 174*/                    method = ((JsonProperty)obj).value();
/* 175*/                    break;
                        }
/* 172*/                k++;
                    } while(true);
/* 178*/            if("".equals(method))
/* 178*/                return null;
/* 178*/            else
/* 178*/                return method;
                }

                public String resolve(Field field)
                {
/* 182*/            return getName(field);
                }

                public String resolve(Method method)
                {
/* 186*/            return getName(method);
                }

                protected String getName(AnnotatedElement annotatedelement)
                {
/* 190*/            if((annotatedelement = (JsonProperty)annotatedelement.getAnnotation(com/owlike/genson/annotation/JsonProperty)) != null && annotatedelement.value() != null && !annotatedelement.value().isEmpty())
/* 191*/                return annotatedelement.value();
/* 191*/            else
/* 191*/                return null;
                }

                public AnnotationPropertyNameResolver()
                {
                }
    }

    public static class ConventionalBeanPropertyNameResolver
        implements PropertyNameResolver
    {

                public String resolve(int i, Constructor constructor)
                {
/* 119*/            return null;
                }

                public String resolve(Field field)
                {
/* 123*/            return field.getName();
                }

                public String resolve(Method method)
                {
/* 127*/            method = method.getName();
/* 128*/            byte byte0 = -1;
/* 130*/            if(method.startsWith("get"))
/* 131*/                byte0 = 3;
/* 132*/            else
/* 132*/            if(method.startsWith("is"))
/* 133*/                byte0 = 2;
/* 134*/            else
/* 134*/            if(method.startsWith("set"))
/* 135*/                byte0 = 3;
/* 137*/            if(byte0 >= 0 && byte0 < method.length())
/* 138*/                return (new StringBuilder()).append(Character.toLowerCase(method.charAt(byte0))).append(method.substring(byte0 + 1)).toString();
/* 140*/            else
/* 140*/                return null;
                }

                public String resolve(int i, Method method)
                {
/* 144*/            return null;
                }

                public ConventionalBeanPropertyNameResolver()
                {
                }
    }

    public static class CompositePropertyNameResolver
        implements PropertyNameResolver
    {

                public transient CompositePropertyNameResolver add(PropertyNameResolver apropertynameresolver[])
                {
/*  74*/            components.addAll(0, Arrays.asList(apropertynameresolver));
/*  75*/            return this;
                }

                public String resolve(int i, Constructor constructor)
                {
/*  79*/            String s = null;
/*  80*/            for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(i, constructor));
/*  84*/            return s;
                }

                public String resolve(int i, Method method)
                {
/*  88*/            String s = null;
/*  89*/            for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(i, method));
/*  93*/            return s;
                }

                public String resolve(Field field)
                {
/*  97*/            String s = null;
/*  98*/            for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(field));
/* 102*/            return s;
                }

                public String resolve(Method method)
                {
/* 106*/            String s = null;
/* 107*/            for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(method));
/* 111*/            return s;
                }

                private List components;

                public CompositePropertyNameResolver(List list)
                {
/*  65*/            if(list == null || list.isEmpty())
                    {
/*  66*/                throw new IllegalArgumentException("The composite resolver must have at least one resolver as component!");
                    } else
                    {
/*  69*/                components = new LinkedList(list);
/*  70*/                return;
                    }
                }
    }


    public abstract String resolve(int i, Constructor constructor);

    public abstract String resolve(int i, Method method);

    public abstract String resolve(Field field);

    public abstract String resolve(Method method);
}
