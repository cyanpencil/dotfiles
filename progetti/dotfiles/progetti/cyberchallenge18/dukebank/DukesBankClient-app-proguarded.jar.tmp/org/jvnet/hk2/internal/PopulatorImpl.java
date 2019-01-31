// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PopulatorImpl.java

package org.jvnet.hk2.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;
import org.glassfish.hk2.utilities.DescriptorImpl;

// Referenced classes of package org.jvnet.hk2.internal:
//            Collector

public class PopulatorImpl
    implements Populator
{

            PopulatorImpl(ServiceLocator servicelocator, DynamicConfigurationService dynamicconfigurationservice)
            {
/*  73*/        serviceLocator = servicelocator;
/*  74*/        dcs = dynamicconfigurationservice;
            }

            public transient List populate(DescriptorFileFinder descriptorfilefinder, PopulatorPostProcessor apopulatorpostprocessor[])
                throws IOException
            {
                LinkedList linkedlist;
                List list;
/*  80*/        linkedlist = new LinkedList();
/*  82*/        if(descriptorfilefinder == null && (descriptorfilefinder = (DescriptorFileFinder)serviceLocator.getService(org/glassfish/hk2/api/DescriptorFileFinder, new Annotation[0])) == null)
/*  84*/            return linkedlist;
/*  87*/        if(apopulatorpostprocessor == null)
/*  87*/            apopulatorpostprocessor = new PopulatorPostProcessor[0];
/*  90*/        list = null;
                Object obj;
/*  92*/        obj = descriptorfilefinder.findDescriptorFiles();
                DescriptorFileFinderInformation descriptorfilefinderinformation;
/*  93*/        if((descriptorfilefinder instanceof DescriptorFileFinderInformation) && (list = (descriptorfilefinderinformation = (DescriptorFileFinderInformation)descriptorfilefinder).getDescriptorFileInformation()) != null && list.size() != ((List) (obj)).size())
/*  99*/            throw new IOException((new StringBuilder("The DescriptorFileFinder implementation ")).append(descriptorfilefinder.getClass().getName()).append(" also implements DescriptorFileFinderInformation, however the cardinality of the list returned from getDescriptorFileInformation (").append(list.size()).append(") does not equal the cardinality of the list returned from findDescriptorFiles (").append(((List) (obj)).size()).append(")").toString());
/* 112*/        break MISSING_BLOCK_LABEL_183;
/* 107*/        JVM INSTR dup ;
                Object obj1;
/* 108*/        obj1;
/* 108*/        throw ;
                Throwable throwable;
/* 110*/        throwable;
/* 111*/        throw new MultiException(throwable);
                int i;
/* 114*/        throwable = new Collector();
/* 116*/        descriptorfilefinder = dcs.createDynamicConfiguration();
/* 118*/        i = 0;
/* 119*/        obj = ((List) (obj)).iterator();
_L2:
                Object obj2;
                String s;
/* 119*/        if(!((Iterator) (obj)).hasNext())
/* 119*/            break; /* Loop/switch isn't completed */
/* 119*/        obj2 = (InputStream)((Iterator) (obj)).next();
/* 120*/        s = list != null ? (String)list.get(i) : null;
/* 121*/        i++;
/* 123*/        obj2 = new BufferedReader(new InputStreamReader(((InputStream) (obj2))));
/* 126*/        boolean flag = false;
/* 129*/        do
                {
/* 129*/            Object obj3 = new DescriptorImpl();
/* 132*/            try
                    {
/* 132*/                flag = ((DescriptorImpl) (obj3)).readObject(((BufferedReader) (obj2)));
                    }
/* 134*/            catch(IOException ioexception)
                    {
/* 135*/                if(s != null)
/* 136*/                    throwable.addThrowable(new IOException((new StringBuilder("InputStream with identifier \"")).append(s).append("\" failed").toString(), ioexception));
/* 139*/                else
/* 139*/                    throwable.addThrowable(ioexception);
                    }
/* 143*/            if(flag)
                    {
                        PopulatorPostProcessor apopulatorpostprocessor1[];
/* 145*/                int j = (apopulatorpostprocessor1 = apopulatorpostprocessor).length;
/* 145*/                int k = 0;
/* 145*/                do
                        {
/* 145*/                    if(k >= j)
/* 145*/                        break;
/* 145*/                    PopulatorPostProcessor populatorpostprocessor = apopulatorpostprocessor1[k];
/* 147*/                    try
                            {
/* 147*/                        obj3 = populatorpostprocessor.process(serviceLocator, ((DescriptorImpl) (obj3)));
                            }
                            // Misplaced declaration of an exception variable
/* 149*/                    catch(Object obj3)
                            {
/* 150*/                        if(s != null)
/* 151*/                            throwable.addThrowable(new IOException((new StringBuilder("InputStream with identifier \"")).append(s).append("\" failed").toString(), ((Throwable) (obj3))));
/* 154*/                        else
/* 154*/                            throwable.addThrowable(((Throwable) (obj3)));
/* 156*/                        obj3 = null;
                            }
/* 159*/                    if(obj3 == null)
/* 145*/                        break;
/* 145*/                    k++;
                        } while(true);
/* 164*/                if(obj3 != null)
/* 165*/                    linkedlist.add(descriptorfilefinder.bind(((org.glassfish.hk2.api.Descriptor) (obj3)), false));
                    }
                } while(flag);
/* 172*/        ((BufferedReader) (obj2)).close();
/* 173*/        if(true) goto _L2; else goto _L1
/* 172*/        descriptorfilefinder;
/* 172*/        ((BufferedReader) (obj2)).close();
/* 172*/        throw descriptorfilefinder;
_L1:
/* 177*/        throwable.throwIfErrors();
/* 179*/        descriptorfilefinder.commit();
/* 181*/        return linkedlist;
            }

            public List populate()
                throws IOException
            {
/* 189*/        return populate(((DescriptorFileFinder) (new ClasspathDescriptorFileFinder())), new PopulatorPostProcessor[0]);
            }

            private final ServiceLocator serviceLocator;
            private final DynamicConfigurationService dcs;
}
