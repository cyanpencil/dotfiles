// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.util.collection.KeyComparator;
import org.glassfish.jersey.message.AbstractEntityProviderModel;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes, MessageBodyFactory

static class wantedMediaType
    implements Comparator
{

            public int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
            {
                int i;
/* 343*/        if((i = compareTypeDistances(abstractentityprovidermodel.providedType(), abstractentityprovidermodel1.providedType())) != 0)
/* 345*/            return i;
/* 348*/        if((i = getMediaTypeDistance(wantedMediaType, abstractentityprovidermodel.declaredTypes()) - getMediaTypeDistance(wantedMediaType, abstractentityprovidermodel1.declaredTypes())) != 0)
/* 351*/            return i;
/* 354*/        if(abstractentityprovidermodel.isCustom() ^ abstractentityprovidermodel1.isCustom())
/* 355*/            return !abstractentityprovidermodel.isCustom() ? 1 : -1;
/* 357*/        else
/* 357*/            return 0;
            }

            private int getMediaTypeDistance(MediaType mediatype, List list)
            {
/* 361*/        if(mediatype == null)
/* 362*/            return 0;
/* 365*/        byte byte0 = 2;
/* 367*/        list = list.iterator();
/* 367*/        do
                {
/* 367*/            if(!list.hasNext())
/* 367*/                break;
/* 367*/            MediaType mediatype1 = (MediaType)list.next();
/* 368*/            if(MediaTypes.typeEqual(mediatype, mediatype1))
/* 369*/                return 0;
/* 372*/            if(byte0 > 1 && MediaTypes.typeEqual(MediaTypes.getTypeWildCart(mediatype), mediatype1))
/* 373*/                byte0 = 1;
                } while(true);
/* 377*/        return byte0;
            }

            private int compareTypeDistances(Class class1, Class class2)
            {
/* 381*/        return getTypeDistance(class1) - getTypeDistance(class2);
            }

            private int getTypeDistance(Class class1)
            {
/* 387*/        Class class2 = wantedType;
/* 388*/        Class class3 = class1;
/* 390*/        Iterator iterator = getClassHierarchyIterator(class2);
/* 391*/        Iterator iterator1 = getClassHierarchyIterator(class3);
/* 393*/        int i = 0;
/* 394*/        while(!wantedType.equals(class3) && !class1.equals(class2)) 
                {
/* 395*/            i++;
/* 397*/            if(!wantedType.equals(class3))
/* 398*/                class3 = iterator1.hasNext() ? (Class)iterator1.next() : null;
/* 401*/            if(!class1.equals(class2))
/* 402*/                class2 = iterator.hasNext() ? (Class)iterator.next() : null;
/* 405*/            if(class3 == null && class2 == null)
/* 406*/                return 0x7fffffff;
                }
/* 410*/        return i;
            }

            private Iterator getClassHierarchyIterator(Class class1)
            {
/* 414*/        if(class1 == null)
/* 415*/            return Collections.emptyList().iterator();
/* 418*/        ArrayList arraylist = new ArrayList();
                LinkedList linkedlist;
/* 419*/        (linkedlist = new LinkedList()).add(class1);
/* 422*/        do
                {
/* 422*/            if(linkedlist.isEmpty())
/* 423*/                break;
/* 423*/            class1 = (Class)linkedlist.removeFirst();
/* 425*/            arraylist.add(class1);
/* 426*/            linkedlist.addAll(Arrays.asList(class1.getInterfaces()));
/* 428*/            if((class1 = class1.getSuperclass()) != null)
/* 430*/                linkedlist.add(class1);
                } while(true);
/* 434*/        return arraylist.iterator();
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 330*/        return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
            }

            final Class wantedType;
            final MediaType wantedMediaType;

            private (Class class1, MediaType mediatype)
            {
/* 336*/        wantedType = class1;
/* 337*/        wantedMediaType = mediatype;
            }


            // Unreferenced inner class org/glassfish/jersey/message/internal/MessageBodyFactory$1

/* anonymous class */
    static class MessageBodyFactory._cls1
        implements KeyComparator
    {

                public final boolean equals(MediaType mediatype, MediaType mediatype1)
                {
/* 137*/            return mediatype.isCompatible(mediatype1);
                }

                public final int hash(MediaType mediatype)
                {
/* 143*/            return mediatype.getType().toLowerCase().hashCode() + mediatype.getSubtype().toLowerCase().hashCode();
                }

                public final volatile int hash(Object obj)
                {
/* 131*/            return hash((MediaType)obj);
                }

                public final volatile boolean equals(Object obj, Object obj1)
                {
/* 131*/            return equals((MediaType)obj, (MediaType)obj1);
                }

                private static final long serialVersionUID = 0x167019115f5932f3L;

    }

}
