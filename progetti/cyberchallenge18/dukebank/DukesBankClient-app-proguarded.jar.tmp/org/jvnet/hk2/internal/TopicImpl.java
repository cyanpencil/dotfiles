// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TopicImpl.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import org.glassfish.hk2.api.messaging.Topic;
import org.glassfish.hk2.api.messaging.TopicDistributionService;
import org.glassfish.hk2.utilities.NamedImpl;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

public class TopicImpl
    implements Topic
{

            TopicImpl(ServiceLocatorImpl servicelocatorimpl, Type type, Set set)
            {
/*  64*/        locator = servicelocatorimpl;
/*  65*/        topicType = type;
/*  66*/        requiredQualifiers = Collections.unmodifiableSet(set);
            }

            public void publish(Object obj)
            {
/*  74*/        if(obj == null)
/*  74*/            throw new IllegalArgumentException();
                TopicDistributionService topicdistributionservice;
/*  76*/        if((topicdistributionservice = (TopicDistributionService)locator.getService(org/glassfish/hk2/api/messaging/TopicDistributionService, new Annotation[0])) == null)
                {
/*  79*/            throw new IllegalStateException("There is no implementation of the TopicDistributionService to distribute the message");
                } else
                {
/*  82*/            topicdistributionservice.distributeMessage(this, obj);
/*  83*/            return;
                }
            }

            public Topic named(String s)
            {
/*  90*/        return qualifiedWith(new Annotation[] {
/*  90*/            new NamedImpl(s)
                });
            }

            public Topic ofType(Type type)
            {
/*  98*/        return new TopicImpl(locator, type, requiredQualifiers);
            }

            public transient Topic qualifiedWith(Annotation aannotation[])
            {
/* 106*/        HashSet hashset = new HashSet(requiredQualifiers);
/* 107*/        int i = (aannotation = aannotation).length;
/* 107*/        for(int j = 0; j < i; j++)
                {
/* 107*/            Annotation annotation = aannotation[j];
/* 108*/            hashset.add(annotation);
                }

/* 111*/        return new TopicImpl(locator, topicType, hashset);
            }

            public Type getTopicType()
            {
/* 119*/        return topicType;
            }

            public Set getTopicQualifiers()
            {
/* 127*/        return requiredQualifiers;
            }

            private final ServiceLocatorImpl locator;
            private final Type topicType;
            private final Set requiredQualifiers;
}
