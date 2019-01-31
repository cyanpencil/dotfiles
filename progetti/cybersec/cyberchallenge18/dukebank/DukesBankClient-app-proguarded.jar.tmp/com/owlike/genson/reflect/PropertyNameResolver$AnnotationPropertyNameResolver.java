// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.annotation.JsonProperty;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyNameResolver

public static class 
    implements PropertyNameResolver
{

            public String resolve(int i, Constructor constructor)
            {
/* 158*/        i = constructor.getParameterAnnotations()[i];
/* 159*/        constructor = null;
/* 160*/        int j = 0;
/* 160*/        do
                {
/* 160*/            if(j >= i.length)
/* 161*/                break;
/* 161*/            if(i[j] instanceof JsonProperty)
                    {
/* 162*/                constructor = ((JsonProperty)i[j]).value();
/* 163*/                break;
                    }
/* 160*/            j++;
                } while(true);
/* 166*/        if("".equals(constructor))
/* 166*/            return null;
/* 166*/        else
/* 166*/            return constructor;
            }

            public String resolve(int i, Method method)
            {
/* 170*/        i = method.getParameterAnnotations()[i];
/* 171*/        method = null;
/* 172*/        int j = (i = i).length;
/* 172*/        int k = 0;
/* 172*/        do
                {
/* 172*/            if(k >= j)
/* 172*/                break;
                    Object obj;
/* 172*/            if((obj = i[k]) instanceof JsonProperty)
                    {
/* 174*/                method = ((JsonProperty)obj).value();
/* 175*/                break;
                    }
/* 172*/            k++;
                } while(true);
/* 178*/        if("".equals(method))
/* 178*/            return null;
/* 178*/        else
/* 178*/            return method;
            }

            public String resolve(Field field)
            {
/* 182*/        return getName(field);
            }

            public String resolve(Method method)
            {
/* 186*/        return getName(method);
            }

            protected String getName(AnnotatedElement annotatedelement)
            {
/* 190*/        if((annotatedelement = (JsonProperty)annotatedelement.getAnnotation(com/owlike/genson/annotation/JsonProperty)) != null && annotatedelement.value() != null && !annotatedelement.value().isEmpty())
/* 191*/            return annotatedelement.value();
/* 191*/        else
/* 191*/            return null;
            }

            public ()
            {
            }
}
