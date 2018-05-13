// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Predicate;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag

class val.modelEnhancer
    implements Producer
{

            public Boolean call()
            {
/* 401*/        if(ComponentBag.access$000(ComponentBag.this).containsKey(val$componentClass))
                {
/* 402*/            Errors.error(LocalizationMessages.COMPONENT_TYPE_ALREADY_REGISTERED(val$componentClass), Severity.WARNING);
/* 404*/            return Boolean.valueOf(false);
                }
/* 408*/        ContractProvider contractprovider = ComponentBag.access$100(val$componentClass, val$defaultPriority, val$contractMap, val$modelEnhancer);
/* 411*/        if(!ComponentBag.access$200(ComponentBag.this).apply(contractprovider))
                {
/* 412*/            return Boolean.valueOf(false);
                } else
                {
/* 415*/            ComponentBag.access$000(ComponentBag.this).put(val$componentClass, contractprovider);
/* 416*/            return Boolean.valueOf(true);
                }
            }

            public volatile Object call()
            {
/* 398*/        return call();
            }

            final Class val$componentClass;
            final int val$defaultPriority;
            final Map val$contractMap;
            final Inflector val$modelEnhancer;
            final ComponentBag this$0;

            ()
            {
/* 398*/        this$0 = final_componentbag;
/* 398*/        val$componentClass = class1;
/* 398*/        val$defaultPriority = i;
/* 398*/        val$contractMap = map;
/* 398*/        val$modelEnhancer = Inflector.this;
/* 398*/        super();
            }
}
