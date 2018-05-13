// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.transform.Source;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Sets;
import jersey.repackaged.com.google.common.primitives.Primitives;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.message.*;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes, MsgTraceEvent, ReaderInterceptorExecutor, ReaderWriter, 
//            TracingLogger, WriterInterceptorExecutor

public class MessageBodyFactory
    implements MessageBodyWorkers
{
    static class ModelLookupKey
    {

                public boolean equals(Object obj)
                {
/* 485*/            if(this == obj)
/* 486*/                return true;
/* 488*/            if(obj == null || getClass() != obj.getClass())
/* 489*/                return false;
/* 492*/            obj = (ModelLookupKey)obj;
/* 494*/            return (clazz == null ? ((ModelLookupKey) (obj)).clazz == null : clazz.equals(((ModelLookupKey) (obj)).clazz)) && (mediaType == null ? ((ModelLookupKey) (obj)).mediaType == null : mediaType.equals(((ModelLookupKey) (obj)).mediaType));
                }

                public int hashCode()
                {
/* 500*/            int i = clazz == null ? 0 : clazz.hashCode();
/* 501*/            return i = i * 31 + (mediaType == null ? 0 : mediaType.hashCode());
                }

                final Class clazz;
                final MediaType mediaType;

                private ModelLookupKey(Class class1, MediaType mediatype)
                {
/* 479*/            clazz = class1;
/* 480*/            mediaType = mediatype;
                }

    }

    static class LegacyWorkerComparator
        implements Comparator
    {

                public int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
                {
/* 459*/            if(abstractentityprovidermodel.isCustom() ^ abstractentityprovidermodel1.isCustom())
/* 460*/                return !abstractentityprovidermodel.isCustom() ? 1 : -1;
/* 462*/            MediaType mediatype = (MediaType)abstractentityprovidermodel.declaredTypes().get(0);
/* 463*/            MediaType mediatype1 = (MediaType)abstractentityprovidermodel1.declaredTypes().get(0);
                    int i;
/* 465*/            if((i = MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(mediatype, mediatype1)) != 0 && !mediatype.isCompatible(mediatype1))
/* 467*/                return i;
/* 469*/            else
/* 469*/                return distanceComparator.compare(abstractentityprovidermodel.provider(), abstractentityprovidermodel1.provider());
                }

                public volatile int compare(Object obj, Object obj1)
                {
/* 448*/            return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
                }

                final DeclarationDistanceComparator distanceComparator;

                private LegacyWorkerComparator(Class class1)
                {
/* 453*/            distanceComparator = new DeclarationDistanceComparator(class1);
                }

    }

    static class WorkerComparator
        implements Comparator
    {

                public int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
                {
                    int i;
/* 343*/            if((i = compareTypeDistances(abstractentityprovidermodel.providedType(), abstractentityprovidermodel1.providedType())) != 0)
/* 345*/                return i;
/* 348*/            if((i = getMediaTypeDistance(wantedMediaType, abstractentityprovidermodel.declaredTypes()) - getMediaTypeDistance(wantedMediaType, abstractentityprovidermodel1.declaredTypes())) != 0)
/* 351*/                return i;
/* 354*/            if(abstractentityprovidermodel.isCustom() ^ abstractentityprovidermodel1.isCustom())
/* 355*/                return !abstractentityprovidermodel.isCustom() ? 1 : -1;
/* 357*/            else
/* 357*/                return 0;
                }

                private int getMediaTypeDistance(MediaType mediatype, List list)
                {
/* 361*/            if(mediatype == null)
/* 362*/                return 0;
/* 365*/            byte byte0 = 2;
/* 367*/            list = list.iterator();
/* 367*/            do
                    {
/* 367*/                if(!list.hasNext())
/* 367*/                    break;
/* 367*/                MediaType mediatype1 = (MediaType)list.next();
/* 368*/                if(MediaTypes.typeEqual(mediatype, mediatype1))
/* 369*/                    return 0;
/* 372*/                if(byte0 > 1 && MediaTypes.typeEqual(MediaTypes.getTypeWildCart(mediatype), mediatype1))
/* 373*/                    byte0 = 1;
                    } while(true);
/* 377*/            return byte0;
                }

                private int compareTypeDistances(Class class1, Class class2)
                {
/* 381*/            return getTypeDistance(class1) - getTypeDistance(class2);
                }

                private int getTypeDistance(Class class1)
                {
/* 387*/            Class class2 = wantedType;
/* 388*/            Class class3 = class1;
/* 390*/            Iterator iterator = getClassHierarchyIterator(class2);
/* 391*/            Iterator iterator1 = getClassHierarchyIterator(class3);
/* 393*/            int i = 0;
/* 394*/            while(!wantedType.equals(class3) && !class1.equals(class2)) 
                    {
/* 395*/                i++;
/* 397*/                if(!wantedType.equals(class3))
/* 398*/                    class3 = iterator1.hasNext() ? (Class)iterator1.next() : null;
/* 401*/                if(!class1.equals(class2))
/* 402*/                    class2 = iterator.hasNext() ? (Class)iterator.next() : null;
/* 405*/                if(class3 == null && class2 == null)
/* 406*/                    return 0x7fffffff;
                    }
/* 410*/            return i;
                }

                private Iterator getClassHierarchyIterator(Class class1)
                {
/* 414*/            if(class1 == null)
/* 415*/                return Collections.emptyList().iterator();
/* 418*/            ArrayList arraylist = new ArrayList();
                    LinkedList linkedlist;
/* 419*/            (linkedlist = new LinkedList()).add(class1);
/* 422*/            do
                    {
/* 422*/                if(linkedlist.isEmpty())
/* 423*/                    break;
/* 423*/                class1 = (Class)linkedlist.removeFirst();
/* 425*/                arraylist.add(class1);
/* 426*/                linkedlist.addAll(Arrays.asList(class1.getInterfaces()));
/* 428*/                if((class1 = class1.getSuperclass()) != null)
/* 430*/                    linkedlist.add(class1);
                    } while(true);
/* 434*/            return arraylist.iterator();
                }

                public volatile int compare(Object obj, Object obj1)
                {
/* 330*/            return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
                }

                final Class wantedType;
                final MediaType wantedMediaType;

                private WorkerComparator(Class class1, MediaType mediatype)
                {
/* 336*/            wantedType = class1;
/* 337*/            wantedMediaType = mediatype;
                }

    }

    static class DeclarationDistanceComparator
        implements Comparator
    {

                public int compare(Object obj, Object obj1)
                {
/* 293*/            obj = getDistance(obj);
/* 294*/            return (obj1 = getDistance(obj1)) - obj;
                }

                private int getDistance(Object obj)
                {
                    Object obj1;
/* 299*/            if((obj1 = (Integer)distanceMap.get(obj.getClass())) != null)
/* 301*/                return ((Integer) (obj1)).intValue();
                    Class aclass[];
/* 304*/            Class class1 = (aclass = ReflectionHelper.getParameterizedClassArguments(((org.glassfish.jersey.internal.util.ReflectionHelper.DeclaringClassInterfacePair) (obj1 = ReflectionHelper.getClass(obj.getClass(), declared))))) == null ? null : aclass[0];
/* 309*/            aclass = Integer.valueOf(0);
/* 310*/            for(; class1 != null && class1 != java/lang/Object; class1 = class1.getSuperclass())
/* 311*/                aclass = Integer.valueOf(aclass.intValue() + 1);

/* 315*/            distanceMap.put(obj.getClass(), aclass);
/* 316*/            return aclass.intValue();
                }

                private final Class declared;
                private final Map distanceMap = new HashMap();

                DeclarationDistanceComparator(Class class1)
                {
/* 288*/            declared = class1;
                }
    }

    public static class Binder extends AbstractBinder
    {

                protected void configure()
                {
/* 123*/            bindAsContract(org/glassfish/jersey/message/internal/MessageBodyFactory).to(org/glassfish/jersey/message/MessageBodyWorkers).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            public MessageBodyFactory(ServiceLocator servicelocator, Configuration configuration)
            {
/* 185*/        readersCache = new KeyComparatorHashMap(MEDIA_TYPE_KEY_COMPARATOR);
/* 187*/        writersCache = new KeyComparatorHashMap(MEDIA_TYPE_KEY_COMPARATOR);
/* 192*/        mbrTypeLookupCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 194*/        mbwTypeLookupCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 197*/        typeToMediaTypeReadersCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 199*/        typeToMediaTypeWritersCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 202*/        mbrLookupCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 204*/        mbwLookupCache = DataStructures.createConcurrentMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
/* 215*/        serviceLocator = servicelocator;
/* 216*/        legacyProviderOrdering = Boolean.valueOf(configuration != null && PropertiesHelper.isProperty(configuration.getProperty("jersey.config.workers.legacyOrdering")));
/* 221*/        configuration = Providers.getCustomProviders(servicelocator, javax/ws/rs/ext/MessageBodyReader);
/* 222*/        Object obj = Providers.getProviders(servicelocator, javax/ws/rs/ext/MessageBodyReader);
/* 224*/        addReaders(readers, configuration, true);
/* 225*/        ((Set) (obj)).removeAll(configuration);
/* 226*/        addReaders(readers, ((Set) (obj)), false);
/* 228*/        if(legacyProviderOrdering.booleanValue())
                {
/* 229*/            Collections.sort(readers, new LegacyWorkerComparator(javax/ws/rs/ext/MessageBodyReader));
/* 231*/            for(configuration = readers.iterator(); configuration.hasNext();)
                    {
/* 231*/                Iterator iterator = ((ReaderModel) (obj = (ReaderModel)configuration.next())).declaredTypes().iterator();
/* 232*/                while(iterator.hasNext()) 
                        {
/* 232*/                    MediaType mediatype = (MediaType)iterator.next();
                            Object obj1;
/* 233*/                    if((obj1 = (List)readersCache.get(mediatype)) == null)
                            {
/* 236*/                        obj1 = new ArrayList();
/* 237*/                        readersCache.put(mediatype, obj1);
                            }
/* 239*/                    ((List) (obj1)).add(((ReaderModel) (obj)).provider());
                        }
                    }

                }
/* 247*/        configuration = Providers.getCustomProviders(servicelocator, javax/ws/rs/ext/MessageBodyWriter);
/* 248*/        obj = Providers.getProviders(servicelocator, javax/ws/rs/ext/MessageBodyWriter);
/* 250*/        addWriters(writers, configuration, true);
/* 251*/        ((Set) (obj)).removeAll(configuration);
/* 252*/        addWriters(writers, ((Set) (obj)), false);
/* 254*/        if(legacyProviderOrdering.booleanValue())
                {
/* 255*/            Collections.sort(writers, new LegacyWorkerComparator(javax/ws/rs/ext/MessageBodyWriter));
/* 257*/            for(Iterator iterator1 = writers.iterator(); iterator1.hasNext();)
                    {
                        AbstractEntityProviderModel abstractentityprovidermodel;
/* 257*/                Iterator iterator2 = (abstractentityprovidermodel = (AbstractEntityProviderModel)iterator1.next()).declaredTypes().iterator();
/* 258*/                while(iterator2.hasNext()) 
                        {
/* 258*/                    servicelocator = (MediaType)iterator2.next();
/* 259*/                    if((configuration = (List)writersCache.get(servicelocator)) == null)
                            {
/* 262*/                        configuration = new ArrayList();
/* 263*/                        writersCache.put(servicelocator, configuration);
                            }
/* 265*/                    configuration.add(abstractentityprovidermodel.provider());
                        }
                    }

                }
            }

            private static void addReaders(List list, Set set, boolean flag)
            {
                MessageBodyReader messagebodyreader;
                List list1;
/* 507*/        for(set = set.iterator(); set.hasNext(); list.add(new ReaderModel(messagebodyreader, list1, Boolean.valueOf(flag))))
/* 507*/            list1 = MediaTypes.createFrom((Consumes)(messagebodyreader = (MessageBodyReader)set.next()).getClass().getAnnotation(javax/ws/rs/Consumes));

            }

            private static void addWriters(List list, Set set, boolean flag)
            {
                MessageBodyWriter messagebodywriter;
                List list1;
/* 514*/        for(set = set.iterator(); set.hasNext(); list.add(new WriterModel(messagebodywriter, list1, Boolean.valueOf(flag))))
/* 514*/            list1 = MediaTypes.createFrom((Produces)(messagebodywriter = (MessageBodyWriter)set.next()).getClass().getAnnotation(javax/ws/rs/Produces));

            }

            public Map getReaders(MediaType mediatype)
            {
/* 523*/        KeyComparatorLinkedHashMap keycomparatorlinkedhashmap = new KeyComparatorLinkedHashMap(MEDIA_TYPE_KEY_COMPARATOR);
/* 526*/        getCompatibleProvidersMap(mediatype, readers, keycomparatorlinkedhashmap);
/* 527*/        return keycomparatorlinkedhashmap;
            }

            public Map getWriters(MediaType mediatype)
            {
/* 532*/        KeyComparatorLinkedHashMap keycomparatorlinkedhashmap = new KeyComparatorLinkedHashMap(MEDIA_TYPE_KEY_COMPARATOR);
/* 535*/        getCompatibleProvidersMap(mediatype, writers, keycomparatorlinkedhashmap);
/* 536*/        return keycomparatorlinkedhashmap;
            }

            public String readersToString(Map map)
            {
/* 541*/        return toString(map);
            }

            public String writersToString(Map map)
            {
/* 546*/        return toString(map);
            }

            private String toString(Map map)
            {
/* 550*/        StringWriter stringwriter = new StringWriter();
/* 551*/        PrintWriter printwriter = new PrintWriter(stringwriter);
/* 552*/        for(map = map.entrySet().iterator(); map.hasNext();)
                {
/* 552*/            Object obj = (java.util.Map.Entry)map.next();
/* 553*/            printwriter.append(((MediaType)((java.util.Map.Entry) (obj)).getKey()).toString()).println(" ->");
/* 554*/            obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 554*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 554*/                Object obj1 = ((Iterator) (obj)).next();
/* 555*/                printwriter.append("  ").println(obj1.getClass().getName());
                    }
                }

/* 558*/        printwriter.flush();
/* 559*/        return stringwriter.toString();
            }

            public MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 566*/        return getMessageBodyReader(class1, type, aannotation, mediatype, null);
            }

            public MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype, PropertiesDelegate propertiesdelegate)
            {
/* 575*/        MessageBodyReader messagebodyreader = null;
/* 576*/        if(legacyProviderOrdering.booleanValue())
                {
/* 577*/            if(mediatype != null && (messagebodyreader = _getMessageBodyReader(class1, type, aannotation, mediatype, mediatype, propertiesdelegate)) == null)
/* 580*/                messagebodyreader = _getMessageBodyReader(class1, type, aannotation, mediatype, MediaTypes.getTypeWildCart(mediatype), propertiesdelegate);
/* 583*/            if(messagebodyreader == null)
/* 584*/                messagebodyreader = _getMessageBodyReader(class1, type, aannotation, mediatype, MediaType.WILDCARD_TYPE, propertiesdelegate);
                } else
                {
/* 587*/            messagebodyreader = _getMessageBodyReader(class1, type, aannotation, mediatype, readers, propertiesdelegate);
                }
/* 590*/        return messagebodyreader;
            }

            public List getMessageBodyReaderMediaTypes(Class class1, Type type, Annotation aannotation[])
            {
/* 598*/        LinkedHashSet linkedhashset = Sets.newLinkedHashSet();
/* 600*/        for(Iterator iterator = readers.iterator(); iterator.hasNext();)
                {
/* 600*/            ReaderModel readermodel = (ReaderModel)iterator.next();
/* 601*/            boolean flag = false;
/* 603*/            Iterator iterator1 = readermodel.declaredTypes().iterator();
/* 603*/            while(iterator1.hasNext()) 
                    {
/* 603*/                MediaType mediatype = (MediaType)iterator1.next();
/* 604*/                if(readermodel.isReadable(class1, type, aannotation, mediatype))
                        {
/* 605*/                    linkedhashset.add(mediatype);
/* 606*/                    flag = true;
                        }
/* 609*/                if(!linkedhashset.contains(MediaType.WILDCARD_TYPE) && flag && readermodel.declaredTypes().contains(MediaType.WILDCARD_TYPE))
/* 612*/                    linkedhashset.add(MediaType.WILDCARD_TYPE);
                    }
                }

                ArrayList arraylist;
/* 617*/        Collections.sort(arraylist = Lists.newArrayList(linkedhashset), MediaTypes.PARTIAL_ORDER_COMPARATOR);
/* 619*/        return arraylist;
            }

            private boolean isCompatible(AbstractEntityProviderModel abstractentityprovidermodel, Class class1, MediaType mediatype)
            {
/* 624*/label0:
                {
/* 624*/            if(!abstractentityprovidermodel.providedType().equals(java/lang/Object) && !abstractentityprovidermodel.providedType().isAssignableFrom(class1) && !class1.isAssignableFrom(abstractentityprovidermodel.providedType()))
/* 629*/                break label0;
/* 629*/            abstractentityprovidermodel = abstractentityprovidermodel.declaredTypes().iterator();
/* 629*/            do
                    {
/* 629*/                if(!abstractentityprovidermodel.hasNext())
/* 629*/                    break label0;
/* 629*/                class1 = (MediaType)abstractentityprovidermodel.next();
/* 630*/                if(mediatype == null)
/* 631*/                    return true;
                    } while(!mediatype.isCompatible(class1));
/* 634*/            return true;
                }
/* 639*/        return false;
            }

            private MessageBodyReader _getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype, List list, PropertiesDelegate propertiesdelegate)
            {
/* 651*/        Object obj = mediatype != null && !mediatype.getParameters().isEmpty() ? ((Object) (new MediaType(mediatype.getType(), mediatype.getSubtype()))) : ((Object) (mediatype));
/* 655*/        obj = new ModelLookupKey(class1, ((MediaType) (obj)));
                Object obj1;
/* 656*/        if((obj1 = (List)mbrLookupCache.get(obj)) == null)
                {
/* 658*/            obj1 = new ArrayList();
/* 660*/            list = list.iterator();
/* 660*/            do
                    {
/* 660*/                if(!list.hasNext())
/* 660*/                    break;
/* 660*/                ReaderModel readermodel2 = (ReaderModel)list.next();
/* 661*/                if(isCompatible(readermodel2, class1, mediatype))
/* 662*/                    ((List) (obj1)).add(readermodel2);
                    } while(true);
/* 665*/            Collections.sort(((List) (obj1)), new WorkerComparator(class1, mediatype));
/* 666*/            mbrLookupCache.put(obj, obj1);
                }
/* 669*/        if(((List) (obj1)).isEmpty())
/* 670*/            return null;
/* 673*/        list = TracingLogger.getInstance(propertiesdelegate);
/* 674*/        MessageBodyReader messagebodyreader = null;
/* 675*/        propertiesdelegate = ((List) (obj1)).iterator();
/* 676*/        do
                {
/* 676*/            if(!propertiesdelegate.hasNext())
/* 677*/                break;
                    ReaderModel readermodel;
/* 677*/            if((readermodel = (ReaderModel)propertiesdelegate.next()).isReadable(class1, type, aannotation, mediatype))
                    {
/* 679*/                messagebodyreader = (MessageBodyReader)readermodel.provider();
/* 680*/                list.log(MsgTraceEvent.MBR_SELECTED, new Object[] {
/* 680*/                    messagebodyreader
                        });
/* 681*/                break;
                    }
/* 683*/            list.log(MsgTraceEvent.MBR_NOT_READABLE, new Object[] {
/* 683*/                readermodel.provider()
                    });
                } while(true);
/* 686*/        if(list.isLogEnabled(MsgTraceEvent.MBR_SKIPPED))
                {
                    ReaderModel readermodel1;
/* 687*/            for(; propertiesdelegate.hasNext(); list.log(MsgTraceEvent.MBR_SKIPPED, new Object[] {
/* 689*/    readermodel1.provider()
}))
/* 688*/                readermodel1 = (ReaderModel)propertiesdelegate.next();

                }
/* 693*/        return messagebodyreader;
            }

            private MessageBodyReader _getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MediaType mediatype1, PropertiesDelegate propertiesdelegate)
            {
/* 702*/        if((mediatype1 = (List)readersCache.get(mediatype1)) == null)
/* 705*/            return null;
/* 708*/        propertiesdelegate = TracingLogger.getInstance(propertiesdelegate);
/* 709*/        MessageBodyReader messagebodyreader = null;
/* 710*/        mediatype1 = mediatype1.iterator();
/* 711*/        do
                {
/* 711*/            if(!mediatype1.hasNext())
/* 712*/                break;
                    MessageBodyReader messagebodyreader1;
/* 712*/            if(isReadable(messagebodyreader1 = (MessageBodyReader)mediatype1.next(), class1, type, aannotation, mediatype))
                    {
/* 714*/                messagebodyreader = messagebodyreader1;
/* 715*/                propertiesdelegate.log(MsgTraceEvent.MBR_SELECTED, new Object[] {
/* 715*/                    messagebodyreader
                        });
/* 716*/                break;
                    }
/* 718*/            propertiesdelegate.log(MsgTraceEvent.MBR_NOT_READABLE, new Object[] {
/* 718*/                messagebodyreader1
                    });
                } while(true);
/* 721*/        if(propertiesdelegate.isLogEnabled(MsgTraceEvent.MBR_SKIPPED))
                {
                    MessageBodyReader messagebodyreader2;
/* 722*/            for(; mediatype1.hasNext(); propertiesdelegate.log(MsgTraceEvent.MBR_SKIPPED, new Object[] {
/* 724*/    messagebodyreader2
}))
/* 723*/                messagebodyreader2 = (MessageBodyReader)mediatype1.next();

                }
/* 728*/        return messagebodyreader;
            }

            public MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 735*/        return getMessageBodyWriter(class1, type, aannotation, mediatype, null);
            }

            public MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype, PropertiesDelegate propertiesdelegate)
            {
/* 743*/        MessageBodyWriter messagebodywriter = null;
/* 745*/        if(legacyProviderOrdering.booleanValue())
                {
/* 746*/            if(mediatype != null && (messagebodywriter = _getMessageBodyWriter(class1, type, aannotation, mediatype, mediatype, propertiesdelegate)) == null)
/* 749*/                messagebodywriter = _getMessageBodyWriter(class1, type, aannotation, mediatype, MediaTypes.getTypeWildCart(mediatype), propertiesdelegate);
/* 752*/            if(messagebodywriter == null)
/* 753*/                messagebodywriter = _getMessageBodyWriter(class1, type, aannotation, mediatype, MediaType.WILDCARD_TYPE, propertiesdelegate);
                } else
                {
/* 756*/            messagebodywriter = _getMessageBodyWriter(class1, type, aannotation, mediatype, writers, propertiesdelegate);
                }
/* 759*/        return messagebodywriter;
            }

            private MessageBodyWriter _getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype, List list, PropertiesDelegate propertiesdelegate)
            {
/* 770*/        Object obj = mediatype != null && !mediatype.getParameters().isEmpty() ? ((Object) (new MediaType(mediatype.getType(), mediatype.getSubtype()))) : ((Object) (mediatype));
/* 774*/        obj = new ModelLookupKey(class1, ((MediaType) (obj)));
                Object obj1;
/* 775*/        if((obj1 = (List)mbwLookupCache.get(obj)) == null)
                {
/* 778*/            obj1 = new ArrayList();
/* 780*/            list = list.iterator();
/* 780*/            do
                    {
/* 780*/                if(!list.hasNext())
/* 780*/                    break;
/* 780*/                WriterModel writermodel2 = (WriterModel)list.next();
/* 781*/                if(isCompatible(writermodel2, class1, mediatype))
/* 782*/                    ((List) (obj1)).add(writermodel2);
                    } while(true);
/* 785*/            Collections.sort(((List) (obj1)), new WorkerComparator(class1, mediatype));
/* 786*/            mbwLookupCache.put(obj, obj1);
                }
/* 789*/        if(((List) (obj1)).isEmpty())
/* 790*/            return null;
/* 793*/        list = TracingLogger.getInstance(propertiesdelegate);
/* 794*/        MessageBodyWriter messagebodywriter = null;
/* 795*/        propertiesdelegate = ((List) (obj1)).iterator();
/* 796*/        do
                {
/* 796*/            if(!propertiesdelegate.hasNext())
/* 797*/                break;
                    WriterModel writermodel;
/* 797*/            if((writermodel = (WriterModel)propertiesdelegate.next()).isWriteable(class1, type, aannotation, mediatype))
                    {
/* 799*/                messagebodywriter = (MessageBodyWriter)writermodel.provider();
/* 800*/                list.log(MsgTraceEvent.MBW_SELECTED, new Object[] {
/* 800*/                    messagebodywriter
                        });
/* 801*/                break;
                    }
/* 803*/            list.log(MsgTraceEvent.MBW_NOT_WRITEABLE, new Object[] {
/* 803*/                writermodel.provider()
                    });
                } while(true);
/* 806*/        if(list.isLogEnabled(MsgTraceEvent.MBW_SKIPPED))
                {
                    WriterModel writermodel1;
/* 807*/            for(; propertiesdelegate.hasNext(); list.log(MsgTraceEvent.MBW_SKIPPED, new Object[] {
/* 809*/    writermodel1.provider()
}))
/* 808*/                writermodel1 = (WriterModel)propertiesdelegate.next();

                }
/* 813*/        return messagebodywriter;
            }

            private MessageBodyWriter _getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MediaType mediatype1, PropertiesDelegate propertiesdelegate)
            {
/* 821*/        if((mediatype1 = (List)writersCache.get(mediatype1)) == null)
/* 824*/            return null;
/* 827*/        propertiesdelegate = TracingLogger.getInstance(propertiesdelegate);
/* 828*/        MessageBodyWriter messagebodywriter = null;
/* 829*/        mediatype1 = mediatype1.iterator();
/* 830*/        do
                {
/* 830*/            if(!mediatype1.hasNext())
/* 831*/                break;
                    MessageBodyWriter messagebodywriter1;
/* 831*/            if(isWriteable(messagebodywriter1 = (MessageBodyWriter)mediatype1.next(), class1, type, aannotation, mediatype))
                    {
/* 833*/                messagebodywriter = messagebodywriter1;
/* 834*/                propertiesdelegate.log(MsgTraceEvent.MBW_SELECTED, new Object[] {
/* 834*/                    messagebodywriter
                        });
/* 835*/                break;
                    }
/* 837*/            propertiesdelegate.log(MsgTraceEvent.MBW_NOT_WRITEABLE, new Object[] {
/* 837*/                messagebodywriter1
                    });
                } while(true);
/* 840*/        if(propertiesdelegate.isLogEnabled(MsgTraceEvent.MBW_SKIPPED))
                {
                    MessageBodyWriter messagebodywriter2;
/* 841*/            for(; mediatype1.hasNext(); propertiesdelegate.log(MsgTraceEvent.MBW_SKIPPED, new Object[] {
/* 843*/    messagebodywriter2
}))
/* 842*/                messagebodywriter2 = (MessageBodyWriter)mediatype1.next();

                }
/* 846*/        return messagebodywriter;
            }

            private static void getCompatibleProvidersMap(MediaType mediatype, List list, Map map)
            {
/* 854*/        if(mediatype.isWildcardType())
                {
/* 855*/            getCompatibleProvidersList(mediatype, list, map);
/* 855*/            return;
                }
/* 856*/        if(mediatype.isWildcardSubtype())
                {
/* 857*/            getCompatibleProvidersList(mediatype, list, map);
/* 858*/            getCompatibleProvidersList(MediaType.WILDCARD_TYPE, list, map);
/* 858*/            return;
                } else
                {
/* 860*/            getCompatibleProvidersList(mediatype, list, map);
/* 861*/            getCompatibleProvidersList(MediaTypes.getTypeWildCart(mediatype), list, map);
/* 864*/            getCompatibleProvidersList(MediaType.WILDCARD_TYPE, list, map);
/* 867*/            return;
                }
            }

            private static void getCompatibleProvidersList(MediaType mediatype, List list, Map map)
            {
/* 874*/        ArrayList arraylist = new ArrayList();
/* 876*/        list = list.iterator();
/* 876*/        do
                {
/* 876*/            if(!list.hasNext())
/* 876*/                break;
                    AbstractEntityProviderModel abstractentityprovidermodel;
/* 876*/            if((abstractentityprovidermodel = (AbstractEntityProviderModel)list.next()).declaredTypes().contains(mediatype))
/* 878*/                arraylist.add(abstractentityprovidermodel.provider());
                } while(true);
/* 882*/        if(!arraylist.isEmpty())
/* 883*/            map.put(mediatype, Collections.unmodifiableList(arraylist));
            }

            public List getMessageBodyWriterMediaTypes(Class class1, Type type, Annotation aannotation[])
            {
/* 890*/        LinkedHashSet linkedhashset = Sets.newLinkedHashSet();
/* 892*/        for(Iterator iterator = writers.iterator(); iterator.hasNext();)
                {
/* 892*/            WriterModel writermodel = (WriterModel)iterator.next();
/* 893*/            boolean flag = false;
/* 895*/            Iterator iterator1 = writermodel.declaredTypes().iterator();
/* 895*/            while(iterator1.hasNext()) 
                    {
/* 895*/                MediaType mediatype = (MediaType)iterator1.next();
/* 896*/                if(writermodel.isWriteable(class1, type, aannotation, mediatype))
                        {
/* 897*/                    linkedhashset.add(mediatype);
/* 898*/                    flag = true;
                        }
/* 901*/                if(!linkedhashset.contains(MediaType.WILDCARD_TYPE) && flag && writermodel.declaredTypes().contains(MediaType.WILDCARD_TYPE))
/* 904*/                    linkedhashset.add(MediaType.WILDCARD_TYPE);
                    }
                }

                ArrayList arraylist;
/* 909*/        Collections.sort(arraylist = Lists.newArrayList(linkedhashset), MediaTypes.PARTIAL_ORDER_COMPARATOR);
/* 911*/        return arraylist;
            }

            public List getMessageBodyWritersForType(Class class1)
            {
/* 925*/        return Lists.transform(getWritersModelsForType(class1), MODEL_TO_WRITER);
            }

            public List getWritersModelsForType(Class class1)
            {
                List list;
/* 930*/        if((list = (List)mbwTypeLookupCache.get(class1)) != null)
/* 932*/            return list;
/* 934*/        else
/* 934*/            return processMessageBodyWritersForType(class1);
            }

            private List processMessageBodyWritersForType(Class class1)
            {
/* 938*/        ArrayList arraylist = Lists.newArrayList();
/* 940*/        if(javax/ws/rs/core/Response.isAssignableFrom(class1))
                {
/* 941*/            arraylist.addAll(writers);
                } else
                {
/* 943*/            Class class2 = Primitives.wrap(class1);
/* 944*/            Iterator iterator = writers.iterator();
/* 944*/            do
                    {
/* 944*/                if(!iterator.hasNext())
/* 944*/                    break;
                        WriterModel writermodel;
/* 944*/                if((writermodel = (WriterModel)iterator.next()).providedType() == null || writermodel.providedType() == class1 || writermodel.providedType().isAssignableFrom(class2))
/* 950*/                    arraylist.add(writermodel);
                    } while(true);
                }
/* 955*/        Collections.sort(arraylist, WORKER_BY_TYPE_COMPARATOR);
/* 956*/        mbwTypeLookupCache.put(class1, arraylist);
/* 959*/        typeToMediaTypeWritersCache.put(class1, getMessageBodyWorkersMediaTypesByType(arraylist));
/* 961*/        return arraylist;
            }

            public List getMessageBodyWriterMediaTypesByType(Class class1)
            {
/* 966*/        if(!typeToMediaTypeWritersCache.containsKey(class1))
/* 967*/            processMessageBodyWritersForType(class1);
/* 969*/        return (List)typeToMediaTypeWritersCache.get(class1);
            }

            public List getMessageBodyReaderMediaTypesByType(Class class1)
            {
/* 974*/        if(!typeToMediaTypeReadersCache.containsKey(class1))
/* 975*/            processMessageBodyReadersForType(class1);
/* 977*/        return (List)typeToMediaTypeReadersCache.get(class1);
            }

            private static List getMessageBodyWorkersMediaTypesByType(List list)
            {
/* 984*/        HashSet hashset = Sets.newHashSet();
                AbstractEntityProviderModel abstractentityprovidermodel;
/* 985*/        for(list = list.iterator(); list.hasNext(); hashset.addAll(abstractentityprovidermodel.declaredTypes()))
/* 985*/            abstractentityprovidermodel = (AbstractEntityProviderModel)list.next();

/* 989*/        Collections.sort(list = Lists.newArrayList(hashset), MediaTypes.PARTIAL_ORDER_COMPARATOR);
/* 991*/        return list;
            }

            public List getMessageBodyReadersForType(Class class1)
            {
/*1005*/        return Lists.transform(getReaderModelsForType(class1), MODEL_TO_READER);
            }

            public List getReaderModelsForType(Class class1)
            {
/*1010*/        if(!mbrTypeLookupCache.containsKey(class1))
/*1011*/            processMessageBodyReadersForType(class1);
/*1014*/        return (List)mbrTypeLookupCache.get(class1);
            }

            private List processMessageBodyReadersForType(Class class1)
            {
/*1018*/        ArrayList arraylist = Lists.newArrayList();
/*1020*/        Class class2 = Primitives.wrap(class1);
/*1021*/        Iterator iterator = readers.iterator();
/*1021*/        do
                {
/*1021*/            if(!iterator.hasNext())
/*1021*/                break;
                    ReaderModel readermodel;
/*1021*/            if((readermodel = (ReaderModel)iterator.next()).providedType() == null || readermodel.providedType() == class1 || readermodel.providedType().isAssignableFrom(class2))
/*1025*/                arraylist.add(readermodel);
                } while(true);
/*1030*/        Collections.sort(arraylist, WORKER_BY_TYPE_COMPARATOR);
/*1031*/        mbrTypeLookupCache.put(class1, arraylist);
/*1034*/        typeToMediaTypeReadersCache.put(class1, getMessageBodyWorkersMediaTypesByType(arraylist));
/*1036*/        return arraylist;
            }

            public MediaType getMessageBodyWriterMediaType(Class class1, Type type, Annotation aannotation[], List list)
            {
/*1044*/        list = list.iterator();
_L2:
                MediaType mediatype;
                Iterator iterator;
/*1044*/        if(!list.hasNext())
/*1044*/            break MISSING_BLOCK_LABEL_136;
/*1044*/        mediatype = (MediaType)list.next();
/*1045*/        iterator = writers.iterator();
_L4:
/*1045*/        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
                WriterModel writermodel;
/*1045*/        Iterator iterator1 = (writermodel = (WriterModel)iterator.next()).declaredTypes().iterator();
_L6:
/*1046*/        if(!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
                MediaType mediatype1;
/*1046*/        if(!(mediatype1 = (MediaType)iterator1.next()).isCompatible(mediatype) || !writermodel.isWriteable(class1, type, aannotation, mediatype)) goto _L6; else goto _L5
_L5:
/*1048*/        return MediaTypes.mostSpecific(mediatype1, mediatype);
/*1054*/        return null;
            }

            public Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, InputStream inputstream, 
                    Iterable iterable, boolean flag)
                throws WebApplicationException, IOException
            {
                long l;
/*1068*/        class1 = new ReaderInterceptorExecutor(class1, type, aannotation, mediatype, multivaluedmap, propertiesdelegate, inputstream, this, iterable, flag, serviceLocator);
/*1081*/        l = (type = TracingLogger.getInstance(propertiesdelegate)).timestamp(MsgTraceEvent.RI_SUMMARY);
/*1085*/        if(!((aannotation = ((Annotation []) (class1.proceed()))) instanceof Closeable) && !(aannotation instanceof Source) && (mediatype = class1.getInputStream()) != inputstream && mediatype != null)
/*1091*/            ReaderWriter.safelyClose(mediatype);
/*1095*/        mediatype = aannotation;
/*1097*/        type.logDuration(MsgTraceEvent.RI_SUMMARY, l, new Object[] {
/*1097*/            Integer.valueOf(class1.getProcessedCount())
                });
/*1097*/        return mediatype;
/*1097*/        aannotation;
/*1097*/        type.logDuration(MsgTraceEvent.RI_SUMMARY, l, new Object[] {
/*1097*/            Integer.valueOf(class1.getProcessedCount())
                });
/*1097*/        throw aannotation;
            }

            public OutputStream writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, 
                    OutputStream outputstream, Iterable iterable)
                throws IOException, WebApplicationException
            {
                long l;
/*1113*/        obj = new WriterInterceptorExecutor(obj, class1, type, aannotation, mediatype, multivaluedmap, propertiesdelegate, outputstream, this, iterable, serviceLocator);
/*1126*/        l = (class1 = TracingLogger.getInstance(propertiesdelegate)).timestamp(MsgTraceEvent.WI_SUMMARY);
/*1130*/        ((WriterInterceptorExecutor) (obj)).proceed();
/*1132*/        class1.logDuration(MsgTraceEvent.WI_SUMMARY, l, new Object[] {
/*1132*/            Integer.valueOf(((WriterInterceptorExecutor) (obj)).getProcessedCount())
                });
/*1133*/        break MISSING_BLOCK_LABEL_99;
/*1132*/        type;
/*1132*/        class1.logDuration(MsgTraceEvent.WI_SUMMARY, l, new Object[] {
/*1132*/            Integer.valueOf(((WriterInterceptorExecutor) (obj)).getProcessedCount())
                });
/*1132*/        throw type;
/*1135*/        return ((WriterInterceptorExecutor) (obj)).getOutputStream();
            }

            public static boolean isWriteable(MessageBodyWriter messagebodywriter, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*1160*/        return messagebodywriter.isWriteable(class1, type, aannotation, mediatype);
/*1161*/        class1;
/*1162*/        if(LOGGER.isLoggable(Level.FINE))
/*1163*/            LOGGER.log(Level.FINE, LocalizationMessages.ERROR_MBW_ISWRITABLE(messagebodywriter.getClass().getName()), class1);
/*1166*/        return false;
            }

            public static boolean isReadable(MessageBodyReader messagebodyreader, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*1198*/        return messagebodyreader.isReadable(class1, type, aannotation, mediatype);
/*1199*/        class1;
/*1200*/        if(LOGGER.isLoggable(Level.FINE))
/*1201*/            LOGGER.log(Level.FINE, LocalizationMessages.ERROR_MBR_ISREADABLE(messagebodyreader.getClass().getName()), class1);
/*1204*/        return false;
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/MessageBodyFactory.getName());
            public static final KeyComparator MEDIA_TYPE_KEY_COMPARATOR = new KeyComparator() {

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

    };
            private static final Comparator WORKER_BY_TYPE_COMPARATOR = new Comparator() {

                public final int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
                {
/* 156*/            Class class1 = abstractentityprovidermodel.providedType();
/* 157*/            Class class2 = abstractentityprovidermodel1.providedType();
/* 159*/            if(class1 == class2)
/* 161*/                return compare(abstractentityprovidermodel1.declaredTypes(), abstractentityprovidermodel.declaredTypes());
/* 162*/            if(class1.isAssignableFrom(class2))
/* 163*/                return 1;
/* 164*/            return !class2.isAssignableFrom(class1) ? 0 : -1;
                }

                private int compare(List list, List list1)
                {
/* 171*/            list = list.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : list;
/* 172*/            list1 = list1.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : list1;
/* 174*/            return MediaTypes.MEDIA_TYPE_LIST_COMPARATOR.compare(list1, list);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 152*/            return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
                }

    };
            private final ServiceLocator serviceLocator;
            private final Boolean legacyProviderOrdering;
            private final List readers = new ArrayList();
            private final List writers = new ArrayList();
            private final Map readersCache;
            private final Map writersCache;
            private static final int LOOKUP_CACHE_INITIAL_CAPACITY = 32;
            private static final float LOOKUP_CACHE_LOAD_FACTOR = 0.75F;
            private final Map mbrTypeLookupCache;
            private final Map mbwTypeLookupCache;
            private final Map typeToMediaTypeReadersCache;
            private final Map typeToMediaTypeWritersCache;
            private final Map mbrLookupCache;
            private final Map mbwLookupCache;
            private static final Function MODEL_TO_WRITER = new Function() {

                public final MessageBodyWriter apply(WriterModel writermodel)
                {
/* 918*/            return (MessageBodyWriter)writermodel.provider();
                }

                public final volatile Object apply(Object obj)
                {
/* 915*/            return apply((WriterModel)obj);
                }

    };
            private static final Function MODEL_TO_READER = new Function() {

                public final MessageBodyReader apply(ReaderModel readermodel)
                {
/* 998*/            return (MessageBodyReader)readermodel.provider();
                }

                public final volatile Object apply(Object obj)
                {
/* 995*/            return apply((ReaderModel)obj);
                }

    };

}
