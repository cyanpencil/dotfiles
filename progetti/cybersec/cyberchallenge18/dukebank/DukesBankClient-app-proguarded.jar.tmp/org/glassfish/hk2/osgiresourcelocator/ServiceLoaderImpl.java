// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.io.*;
import java.net.URL;
import java.security.*;
import java.util.*;
import java.util.concurrent.locks.*;
import org.osgi.framework.*;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoader

public final class ServiceLoaderImpl extends ServiceLoader
{
    static class DefaultFactory
        implements ServiceLoader.ProviderFactory
    {

                public Object make(Class class1, Class class2)
                    throws Exception
                {
/* 370*/            if(class2.isAssignableFrom(class1))
/* 371*/                return class1.newInstance();
/* 373*/            else
/* 373*/                return null;
                }

                private DefaultFactory()
                {
                }

    }

    static class ProvidersList
    {

                void addProviders(ProvidersPerBundle providersperbundle)
                {
/* 336*/            long l = providersperbundle.getBundleId();
                    ProvidersPerBundle providersperbundle1;
/* 338*/            for(Iterator iterator = getAllProviders().iterator(); iterator.hasNext();)
/* 340*/                if((providersperbundle1 = (ProvidersPerBundle)iterator.next()).getBundleId() > l)
                        {
/* 342*/                    getAllProviders().add(0, providersperbundle);
/* 343*/                    return;
                        }

/* 346*/            getAllProviders().add(providersperbundle);
                }

                void removeProviders(long l)
                {
                    ProvidersPerBundle providersperbundle;
/* 350*/            for(Iterator iterator = getAllProviders().iterator(); iterator.hasNext();)
/* 352*/                if((providersperbundle = (ProvidersPerBundle)iterator.next()).getBundleId() == l)
                        {
/* 354*/                    iterator.remove();
/* 355*/                    return;
                        }

                }

                public List getAllProviders()
                {
/* 364*/            return allProviders;
                }

                private List allProviders;

                private ProvidersList()
                {
/* 333*/            allProviders = new LinkedList();
                }

    }

    static class ProvidersPerBundle
    {

                public long getBundleId()
                {
/* 315*/            return bundleId;
                }

                public void put(String s, List list)
                {
/* 319*/            serviceToProvidersMap.put(s, list);
                }

                public Map getServiceToProvidersMap()
                {
/* 323*/            return serviceToProvidersMap;
                }

                private long bundleId;
                Map serviceToProvidersMap;

                private ProvidersPerBundle(long l)
                {
/* 308*/            serviceToProvidersMap = new HashMap();
/* 311*/            bundleId = l;
                }

                ProvidersPerBundle(long l, _cls1 _pcls1)
                {
/* 306*/            this(l);
                }
    }

    class BundleTracker
        implements BundleListener
    {

                public void bundleChanged(BundleEvent bundleevent)
                {
/* 249*/            Bundle bundle = bundleevent.getBundle();
/* 250*/            switch(bundleevent.getType())
                    {
/* 252*/            case 1: // '\001'
/* 252*/                addProviders(bundle);
/* 253*/                return;

/* 255*/            case 16: // '\020'
/* 255*/                removeProviders(bundle);
/* 256*/                return;

/* 258*/            case 8: // '\b'
/* 258*/                removeProviders(bundle);
/* 259*/                addProviders(bundle);
                        break;
                    }
                }

                final ServiceLoaderImpl this$0;

                private BundleTracker()
                {
/* 247*/            this$0 = ServiceLoaderImpl.this;
/* 247*/            super();
                }

    }


            public ServiceLoaderImpl()
            {
/*  58*/        rwLock = new ReentrantReadWriteLock();
/*  61*/        providersList = new ProvidersList();
                ClassLoader classloader;
/*  69*/        if((classloader = getClass().getClassLoader()) instanceof BundleReference)
/*  71*/            bundleContext = getBundleContextSecured(((BundleReference)org/osgi/framework/BundleReference.cast(classloader)).getBundle());
/*  73*/        if(bundleContext == null)
/*  74*/            throw new RuntimeException("There is no bundle context available yet. Instatiate this class in STARTING or ACTIVE state only");
/*  77*/        else
/*  77*/            return;
            }

            private BundleContext getBundleContextSecured(final Bundle bundle)
            {
/*  80*/        if(System.getSecurityManager() != null)
/*  81*/            return (BundleContext)AccessController.doPrivileged(new PrivilegedAction() {

                        public BundleContext run()
                        {
/*  83*/                    return bundle.getBundleContext();
                        }

                        public volatile Object run()
                        {
/*  81*/                    return run();
                        }

                        final Bundle val$bundle;
                        final ServiceLoaderImpl this$0;

                    
                    {
/*  81*/                this$0 = ServiceLoaderImpl.this;
/*  81*/                bundle = bundle1;
/*  81*/                super();
                    }
            });
/*  87*/        else
/*  87*/            return bundle.getBundleContext();
            }

            public final void trackBundles()
            {
/*  92*/        if(!$assertionsDisabled && bundleTracker != null)
/*  92*/            throw new AssertionError();
/* 100*/        bundleTracker = new BundleTracker();
/* 101*/        bundleContext.addBundleListener(bundleTracker);
                Bundle abundle[];
/* 102*/        int i = (abundle = bundleContext.getBundles()).length;
/* 102*/        for(int j = 0; j < i; j++)
                {
/* 102*/            Bundle bundle = abundle[j];
/* 103*/            addProviders(bundle);
                }

            }

            final Iterable lookupProviderInstances1(Class class1, ServiceLoader.ProviderFactory providerfactory)
            {
                ArrayList arraylist;
                Iterator iterator;
/* 108*/        if(providerfactory == null)
/* 109*/            providerfactory = new DefaultFactory();
/* 111*/        arraylist = new ArrayList();
/* 112*/        iterator = lookupProviderClasses1(class1).iterator();
_L3:
/* 112*/        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
/* 112*/        Object obj = (Class)iterator.next();
/* 114*/        if((obj = providerfactory.make(((Class) (obj)), class1)) != null)
/* 116*/            arraylist.add(obj);
/* 118*/        else
/* 118*/            debug((new StringBuilder()).append(providerfactory).append(" returned null provider instance!!!").toString());
                  goto _L3
/* 120*/        JVM INSTR dup ;
/* 121*/        obj;
/* 121*/        printStackTrace();
                  goto _L3
_L2:
/* 124*/        return arraylist;
            }

            final Iterable lookupProviderClasses1(Class class1)
            {
                ArrayList arraylist;
/* 128*/        arraylist = new ArrayList();
/* 129*/        rwLock.readLock().lock();
                String s;
                Object obj;
/* 131*/        s = class1.getName();
/* 132*/        obj = providersList.getAllProviders().iterator();
_L4:
/* 132*/        if(!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
/* 132*/        Object obj1 = (ProvidersPerBundle)((Iterator) (obj)).next();
                Bundle bundle;
/* 133*/        if((bundle = bundleContext.getBundle(((ProvidersPerBundle) (obj1)).getBundleId())) == null || (obj1 = (List)((ProvidersPerBundle) (obj1)).getServiceToProvidersMap().get(s)) == null) goto _L4; else goto _L3
_L3:
/* 142*/        obj1 = ((List) (obj1)).iterator();
_L6:
/* 142*/        if(!((Iterator) (obj1)).hasNext()) goto _L4; else goto _L5
_L5:
/* 142*/        Object obj2 = (String)((Iterator) (obj1)).next();
/* 144*/        obj2 = loadClassSecured(bundle, ((String) (obj2)));
/* 145*/        if(isCompatible(((Class) (obj2)), class1))
/* 146*/            arraylist.add(obj2);
                  goto _L6
/* 148*/        JVM INSTR dup ;
/* 149*/        obj2;
/* 149*/        printStackTrace();
                  goto _L6
_L2:
/* 153*/        obj = arraylist;
/* 155*/        rwLock.readLock().unlock();
/* 155*/        return ((Iterable) (obj));
/* 155*/        class1;
/* 155*/        rwLock.readLock().unlock();
/* 155*/        throw class1;
            }

            private Class loadClassSecured(final Bundle bundle, final String name)
                throws ClassNotFoundException
            {
/* 161*/        if(System.getSecurityManager() == null)
/* 163*/            break MISSING_BLOCK_LABEL_37;
/* 163*/        return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public Class run()
                        throws ClassNotFoundException
                    {
/* 165*/                return bundle.loadClass(name);
                    }

                    public volatile Object run()
                        throws Exception
                    {
/* 163*/                return run();
                    }

                    final Bundle val$bundle;
                    final String val$name;
                    final ServiceLoaderImpl this$0;

                    
                    {
/* 163*/                this$0 = ServiceLoaderImpl.this;
/* 163*/                bundle = bundle1;
/* 163*/                name = s;
/* 163*/                super();
                    }
        });
/* 168*/        bundle;
/* 169*/        throw (ClassNotFoundException)java/lang/ClassNotFoundException.cast(bundle.getException());
/* 172*/        return bundle.loadClass(name);
            }

            private boolean isCompatible(Class class1, Class class2)
            {
                Class class3;
                boolean flag;
/* 194*/        if(!(flag = (class3 = Class.forName(class2.getName(), false, class1.getClassLoader())) == class2))
/* 197*/            debug((new StringBuilder()).append(class1).append(" loaded by ").append(class1.getClassLoader()).append(" sees ").append(class2).append(" from ").append(class3.getClassLoader()).append(", where as caller uses ").append(class2).append(" loaded by ").append(class2.getClassLoader()).toString());
/* 201*/        return flag;
                ClassNotFoundException classnotfoundexception;
/* 202*/        classnotfoundexception;
/* 203*/        debug((new StringBuilder("Unable to reach ")).append(class2).append(" from ").append(class1).append(", which is loaded by ").append(class1.getClassLoader()).toString(), classnotfoundexception);
/* 204*/        return true;
            }

            private List load(InputStream inputstream)
                throws IOException
            {
/* 215*/        Object obj = new ArrayList();
/* 229*/        Scanner scanner = new Scanner(inputstream);
/* 231*/        do
                {
/* 231*/            if(!scanner.hasNextLine())
/* 232*/                break;
                    Object obj1;
/* 232*/            if(!((String) (obj1 = scanner.nextLine())).startsWith("#") && ((StringTokenizer) (obj1 = new StringTokenizer(((String) (obj1))))).hasMoreTokens())
/* 236*/                ((List) (obj)).add(((StringTokenizer) (obj1)).nextToken());
                } while(true);
/* 242*/        inputstream.close();
/* 243*/        break MISSING_BLOCK_LABEL_86;
/* 242*/        obj;
/* 242*/        inputstream.close();
/* 242*/        throw obj;
/* 244*/        return ((List) (obj));
            }

            private void addProviders(Bundle bundle)
            {
/* 266*/        rwLock.writeLock().lock();
/* 269*/        if(bundle.getEntry("META-INF/services") == null)
                {
/* 290*/            rwLock.writeLock().unlock();
/* 290*/            return;
                }
                Enumeration enumeration;
/* 271*/        if((enumeration = bundle.getEntryPaths("META-INF/services")) != null)
                {
/* 273*/            ProvidersPerBundle providersperbundle = new ProvidersPerBundle(bundle.getBundleId());
/* 274*/            while(enumeration.hasMoreElements()) 
                    {
                        Object obj;
/* 275*/                String s = ((String) (obj = (String)enumeration.nextElement())).substring(17 + 1);
/* 278*/                obj = bundle.getEntry(((String) (obj)));
/* 280*/                try
                        {
/* 280*/                    obj = ((URL) (obj)).openStream();
/* 281*/                    obj = load(((InputStream) (obj)));
/* 282*/                    debug((new StringBuilder("Bundle = ")).append(bundle).append(", serviceName = ").append(s).append(", providerNames = ").append(obj).toString());
/* 283*/                    providersperbundle.put(s, ((List) (obj)));
                        }
/* 284*/                catch(IOException _ex) { }
                    }
/* 287*/            providersList.addProviders(providersperbundle);
                }
/* 290*/        rwLock.writeLock().unlock();
/* 291*/        return;
/* 290*/        bundle;
/* 290*/        rwLock.writeLock().unlock();
/* 290*/        throw bundle;
            }

            private synchronized void removeProviders(Bundle bundle)
            {
/* 295*/        rwLock.writeLock().lock();
/* 297*/        providersList.removeProviders(bundle.getBundleId());
/* 299*/        rwLock.writeLock().unlock();
/* 300*/        return;
/* 299*/        bundle;
/* 299*/        rwLock.writeLock().unlock();
/* 299*/        throw bundle;
            }

            private void debug(String s)
            {
/* 378*/        if(Boolean.valueOf(bundleContext.getProperty("org.glassfish.hk2.osgiresourcelocator.debug")).booleanValue())
/* 379*/            System.out.println((new StringBuilder("org.glassfish.hk2.osgiresourcelocator:DEBUG: ")).append(s).toString());
            }

            private void debug(String s, Throwable throwable)
            {
/* 384*/        if(Boolean.valueOf(bundleContext.getProperty("org.glassfish.hk2.osgiresourcelocator.debug")).booleanValue())
                {
/* 385*/            System.out.println((new StringBuilder("org.glassfish.hk2.osgiresourcelocator:DEBUG: ")).append(s).toString());
/* 386*/            throwable.printStackTrace(System.out);
                }
            }

            private ReadWriteLock rwLock;
            private BundleListener bundleTracker;
            private BundleContext bundleContext;
            private ProvidersList providersList;
            static final boolean $assertionsDisabled = !org/glassfish/hk2/osgiresourcelocator/ServiceLoaderImpl.desiredAssertionStatus();



}
