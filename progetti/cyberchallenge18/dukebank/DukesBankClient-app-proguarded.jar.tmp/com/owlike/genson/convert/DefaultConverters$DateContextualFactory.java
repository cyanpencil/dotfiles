// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.annotation.JsonDateFormat;
import com.owlike.genson.reflect.BeanProperty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.owlike.genson.convert:
//            ContextualFactory, DefaultConverters

public static class 
    implements ContextualFactory
{

            public Converter create(BeanProperty beanproperty, Genson genson)
            {
/* 919*/        if((genson = (JsonDateFormat)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonDateFormat)) != null)
                {
/* 921*/            Object obj = genson.lang().isEmpty() ? ((Object) (Locale.getDefault())) : ((Object) (new Locale(genson.lang())));
/* 923*/            obj = genson.value() == null || genson.value().isEmpty() ? ((Object) (SimpleDateFormat.getInstance())) : ((Object) (new SimpleDateFormat(genson.value(), ((Locale) (obj)))));
/* 926*/            if(java/util/Date.isAssignableFrom(beanproperty.getRawClass()))
/* 927*/                return new (((DateFormat) (obj)), genson.asTimeInMillis());
/* 928*/            if(java/util/Calendar.isAssignableFrom(beanproperty.getRawClass()))
/* 929*/                return new t>(new t>(((DateFormat) (obj)), genson.asTimeInMillis()));
                }
/* 932*/        return null;
            }

            public ()
            {
            }
}
