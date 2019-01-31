// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;
import java.util.List;
import org.glassfish.jersey.message.AbstractEntityProviderModel;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes, MessageBodyFactory

static class 
    implements Comparator
{

            public final int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
            {
/* 156*/        Class class1 = abstractentityprovidermodel.providedType();
/* 157*/        Class class2 = abstractentityprovidermodel1.providedType();
/* 159*/        if(class1 == class2)
/* 161*/            return compare(abstractentityprovidermodel1.declaredTypes(), abstractentityprovidermodel.declaredTypes());
/* 162*/        if(class1.isAssignableFrom(class2))
/* 163*/            return 1;
/* 164*/        return !class2.isAssignableFrom(class1) ? 0 : -1;
            }

            private int compare(List list, List list1)
            {
/* 171*/        list = list.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : list;
/* 172*/        list1 = list1.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : list1;
/* 174*/        return MediaTypes.MEDIA_TYPE_LIST_COMPARATOR.compare(list1, list);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 152*/        return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
            }

            ()
            {
            }
}
