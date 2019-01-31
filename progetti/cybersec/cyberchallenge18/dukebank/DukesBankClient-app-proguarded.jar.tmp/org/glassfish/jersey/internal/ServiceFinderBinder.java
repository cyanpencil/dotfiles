// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinderBinder.java

package org.glassfish.jersey.internal;

import java.util.Map;
import javax.ws.rs.RuntimeType;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.CommonProperties;

// Referenced classes of package org.glassfish.jersey.internal:
//            ServiceFinder

public class ServiceFinderBinder extends AbstractBinder
{

            public ServiceFinderBinder(Class class1, Map map, RuntimeType runtimetype)
            {
/*  76*/        contract = class1;
/*  77*/        applicationProperties = map;
/*  78*/        runtimeType = runtimetype;
            }

            protected void configure()
            {
/*  84*/        boolean flag = false;
/*  85*/        if(applicationProperties != null)
/*  86*/            flag = ((Boolean)CommonProperties.getValue(applicationProperties, runtimeType, "jersey.config.disableMetainfServicesLookup", Boolean.valueOf(false), java/lang/Boolean)).booleanValue();
/*  89*/        if(!flag)
                {
                    Class aclass[];
/*  90*/            int i = (aclass = ServiceFinder.find(contract, true).toClassArray()).length;
/*  90*/            for(int j = 0; j < i; j++)
                    {
/*  90*/                Class class1 = aclass[j];
/*  91*/                bind(class1).to(contract);
                    }

                }
            }

            private final Class contract;
            private final Map applicationProperties;
            private final RuntimeType runtimeType;
}
