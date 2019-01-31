// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorFactory.java

package org.glassfish.hk2.api;

import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.internal.ServiceLocatorFactoryImpl;

// Referenced classes of package org.glassfish.hk2.api:
//            ServiceLocator, ServiceLocatorListener

public abstract class ServiceLocatorFactory
{
    public static final class CreatePolicy extends Enum
    {

                public static CreatePolicy[] values()
                {
/* 204*/            return (CreatePolicy[])$VALUES.clone();
                }

                public static CreatePolicy valueOf(String s)
                {
/* 204*/            return (CreatePolicy)Enum.valueOf(org/glassfish/hk2/api/ServiceLocatorFactory$CreatePolicy, s);
                }

                public static final CreatePolicy RETURN;
                public static final CreatePolicy DESTROY;
                public static final CreatePolicy ERROR;
                private static final CreatePolicy $VALUES[];

                static 
                {
/* 206*/            RETURN = new CreatePolicy("RETURN", 0);
/* 209*/            DESTROY = new CreatePolicy("DESTROY", 1);
/* 212*/            ERROR = new CreatePolicy("ERROR", 2);
/* 204*/            $VALUES = (new CreatePolicy[] {
/* 204*/                RETURN, DESTROY, ERROR
                    });
                }

                private CreatePolicy(String s, int i)
                {
/* 204*/            super(s, i);
                }
    }


            public ServiceLocatorFactory()
            {
            }

            public static ServiceLocatorFactory getInstance()
            {
/*  60*/        return INSTANCE;
            }

            public abstract ServiceLocator create(String s);

            public abstract ServiceLocator create(String s, ServiceLocator servicelocator);

            public abstract ServiceLocator create(String s, ServiceLocator servicelocator, ServiceLocatorGenerator servicelocatorgenerator);

            public abstract ServiceLocator create(String s, ServiceLocator servicelocator, ServiceLocatorGenerator servicelocatorgenerator, CreatePolicy createpolicy);

            public abstract ServiceLocator find(String s);

            public abstract void destroy(String s);

            public abstract void destroy(ServiceLocator servicelocator);

            public abstract void addListener(ServiceLocatorListener servicelocatorlistener);

            public abstract void removeListener(ServiceLocatorListener servicelocatorlistener);

            private static ServiceLocatorFactory INSTANCE = new ServiceLocatorFactoryImpl();

}
