// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.io.*;
import java.net.URL;
import java.security.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.osgi.framework.*;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, ServiceFinder, ServiceConfigurationError

public final class OsgiRegistry
    implements SynchronousBundleListener
{
    static class BundleSpiProvidersLoader
        implements Callable
    {

                public List call()
                    throws Exception
                {
                    Object obj;
                    Exception exception1;
/* 222*/            obj = null;
                    ArrayList arraylist;
/* 225*/            try
                    {
/* 225*/                if(OsgiRegistry.LOGGER.isLoggable(Level.FINEST))
/* 226*/                    OsgiRegistry.LOGGER.log(Level.FINEST, "Loading providers for SPI: {0}", spi);
/* 228*/                obj = new BufferedReader(new InputStreamReader(spiRegistryUrl.openStream(), "UTF-8"));
/* 231*/                ArrayList arraylist1 = new ArrayList();
/* 232*/                do
                        {
                            String s;
/* 232*/                    if((s = ((BufferedReader) (obj)).readLine()) == null)
/* 233*/                        break;
/* 233*/                    if(s.trim().length() != 0)
                            {
/* 236*/                        if(OsgiRegistry.LOGGER.isLoggable(Level.FINEST))
/* 237*/                            OsgiRegistry.LOGGER.log(Level.FINEST, "SPI provider: {0}", s);
/* 239*/                        arraylist1.add(OsgiRegistry.loadClass(bundle, s));
                            }
                        } while(true);
/* 242*/                arraylist = arraylist1;
                    }
/* 243*/            catch(Exception exception)
                    {
/* 244*/                OsgiRegistry.LOGGER.log(Level.WARNING, LocalizationMessages.EXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), exception);
/* 245*/                throw exception;
                    }
/* 246*/            catch(Error error)
                    {
/* 247*/                OsgiRegistry.LOGGER.log(Level.WARNING, LocalizationMessages.ERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), error);
/* 248*/                throw error;
                    }
/* 250*/            finally
                    {
/* 250*/                if(obj == null) goto _L0; else goto _L0
                    }
/* 252*/            try
                    {
/* 252*/                ((BufferedReader) (obj)).close();
                    }
                    // Misplaced declaration of an exception variable
/* 253*/            catch(Object obj)
                    {
/* 254*/                OsgiRegistry.LOGGER.log(Level.FINE, (new StringBuilder("Error closing SPI registry stream:")).append(spiRegistryUrl).toString(), ((Throwable) (obj)));
                    }
/* 255*/            return arraylist;
/* 252*/            try
                    {
/* 252*/                ((BufferedReader) (obj)).close();
                    }
/* 253*/            catch(IOException ioexception)
                    {
/* 254*/                OsgiRegistry.LOGGER.log(Level.FINE, (new StringBuilder("Error closing SPI registry stream:")).append(spiRegistryUrl).toString(), ioexception);
                    }
/* 255*/            throw exception1;
                }

                public String toString()
                {
/* 262*/            return spiRegistryUrlString;
                }

                public int hashCode()
                {
/* 267*/            return spiRegistryUrlString.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 272*/            if(obj instanceof BundleSpiProvidersLoader)
/* 273*/                return spiRegistryUrlString.equals(((BundleSpiProvidersLoader)obj).spiRegistryUrlString);
/* 275*/            else
/* 275*/                return false;
                }

                public volatile Object call()
                    throws Exception
                {
/* 206*/            return call();
                }

                private final String spi;
                private final URL spiRegistryUrl;
                private final String spiRegistryUrlString;
                private final Bundle bundle;

                BundleSpiProvidersLoader(String s, URL url, Bundle bundle1)
                {
/* 214*/            spi = s;
/* 215*/            spiRegistryUrl = url;
/* 216*/            spiRegistryUrlString = url.toExternalForm();
/* 217*/            bundle = bundle1;
                }
    }

    final class OsgiServiceFinder extends ServiceFinder.ServiceIteratorProvider
    {

                public final Iterator createIterator(final Class serviceClass, final String serviceName, ClassLoader classloader, boolean flag)
                {
                    final List providerClasses;
/* 141*/            if(!(providerClasses = locateAllProviders(serviceName)).isEmpty())
/* 143*/                return new Iterator() {

                            public boolean hasNext()
                            {
/* 149*/                        return it.hasNext();
                            }

                            public Object next()
                            {
/* 155*/                        Object obj = (Class)it.next();
/* 157*/                        return ((Class) (obj)).newInstance();
                                Exception exception;
/* 158*/                        exception;
/* 159*/                        ((ServiceConfigurationError) (obj = new ServiceConfigurationError((new StringBuilder()).append(serviceName).append(": ").append(LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(((Class) (obj)).getName(), serviceClass, exception.getLocalizedMessage())).toString()))).initCause(exception);
/* 163*/                        throw obj;
                            }

                            public void remove()
                            {
/* 169*/                        throw new UnsupportedOperationException();
                            }

                            Iterator it;
                            final List val$providerClasses;
                            final String val$serviceName;
                            final Class val$serviceClass;
                            final OsgiServiceFinder this$1;

                        
                        {
/* 143*/                    this$1 = OsgiServiceFinder.this;
/* 143*/                    providerClasses = list;
/* 143*/                    serviceName = s;
/* 143*/                    serviceClass = class1;
/* 143*/                    super();
/* 145*/                    it = providerClasses.iterator();
                        }
                };
/* 173*/            else
/* 173*/                return defaultIterator.createIterator(serviceClass, serviceName, classloader, flag);
                }

                public final Iterator createClassIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
                    final List providerClasses;
/* 179*/            if(!(providerClasses = locateAllProviders(s)).isEmpty())
/* 181*/                return new Iterator() {

                            public boolean hasNext()
                            {
/* 187*/                        return it.hasNext();
                            }

                            public Class next()
                            {
/* 193*/                        return (Class)it.next();
                            }

                            public void remove()
                            {
/* 198*/                        throw new UnsupportedOperationException();
                            }

                            public volatile Object next()
                            {
/* 181*/                        return next();
                            }

                            Iterator it;
                            final List val$providerClasses;
                            final OsgiServiceFinder this$1;

                        
                        {
/* 181*/                    this$1 = OsgiServiceFinder.this;
/* 181*/                    providerClasses = list;
/* 181*/                    super();
/* 183*/                    it = providerClasses.iterator();
                        }
                };
/* 202*/            else
/* 202*/                return defaultIterator.createClassIterator(class1, s, classloader, flag);
                }

                final ServiceFinder.ServiceIteratorProvider defaultIterator;
                final OsgiRegistry this$0;

                private OsgiServiceFinder()
                {
/* 130*/            this$0 = OsgiRegistry.this;
/* 130*/            super();
/* 132*/            defaultIterator = new ServiceFinder.DefaultServiceIteratorProvider();
                }

    }


            public static synchronized OsgiRegistry getInstance()
            {
                Object obj;
/* 117*/        if(instance == null && ((obj = (ClassLoader)AccessController.doPrivileged(ReflectionHelper.getClassLoaderPA(org/glassfish/jersey/internal/util/ReflectionHelper))) instanceof BundleReference) && (obj = FrameworkUtil.getBundle(org/glassfish/jersey/internal/OsgiRegistry).getBundleContext()) != null)
/* 123*/            instance = new OsgiRegistry(((BundleContext) (obj)));
/* 127*/        return instance;
            }

            public final void bundleChanged(BundleEvent bundleevent)
            {
/* 283*/        if(bundleevent.getType() == 32)
                {
/* 284*/            register(bundleevent.getBundle());
/* 284*/            return;
                }
/* 285*/        if(bundleevent.getType() != 64 && bundleevent.getType() != 16)
/* 287*/            break MISSING_BLOCK_LABEL_139;
/* 287*/        bundleevent = bundleevent.getBundle();
/* 289*/        lock.writeLock().lock();
/* 291*/        factories.remove(Long.valueOf(bundleevent.getBundleId()));
/* 293*/        if(bundleevent.getSymbolicName().equals("org.glassfish.jersey.core.jersey-common"))
                {
/* 294*/            bundleContext.removeBundleListener(this);
/* 295*/            factories.clear();
                }
/* 298*/        lock.writeLock().unlock();
/* 299*/        return;
/* 298*/        bundleevent;
/* 298*/        lock.writeLock().unlock();
/* 298*/        throw bundleevent;
            }

            public static String bundleEntryPathToClassName(String s, String s1)
            {
/* 313*/        s = normalizedPackagePath(s);
/* 316*/        if(s1.contains("WEB-INF/classes/"))
/* 317*/            s1 = s1.substring(s1.indexOf("WEB-INF/classes/") + 16);
                int i;
/* 320*/        return ((s = (i = s1.indexOf(s)) < 0 ? (new StringBuilder()).append(s).append(s1.substring(s1.lastIndexOf('/') + 1)).toString() : s1.substring(i)).startsWith("/") ? s.substring(1) : s).replace('/', '.').replace(".class", "");
            }

            public static boolean isPackageLevelEntry(String s, String s1)
            {
/* 343*/        s = normalizedPackagePath(s);
/* 346*/        return !((s = s1.contains(s) ? s1.substring(s1.indexOf(s) + s.length()) : s1).startsWith("/") ? s.substring(1) : s).contains("/");
            }

            public static String normalizedPackagePath(String s)
            {
/* 363*/        s = (s = s.startsWith("/") ? s.substring(1) : s).endsWith("/") ? s : (new StringBuilder()).append(s).append("/").toString();
/* 365*/        return s = "/".equals(s) ? "" : s;
            }

            public final Enumeration getPackageResources(String s, ClassLoader classloader, boolean flag)
            {
                LinkedList linkedlist;
                Bundle abundle[];
                int i;
                int j;
/* 381*/        linkedlist = new LinkedList();
/* 383*/        i = (abundle = bundleContext.getBundles()).length;
/* 383*/        j = 0;
_L4:
/* 383*/        if(j >= i) goto _L2; else goto _L1
_L1:
                Bundle bundle;
                Enumeration enumeration;
/* 383*/        bundle = abundle[j];
/* 385*/        String as[] = {
/* 385*/            s, (new StringBuilder("WEB-INF/classes/")).append(s).toString()
                };
/* 385*/        for(int k = 0; k < 2; k++)
                {
/* 385*/            String s1 = as[k];
                    Enumeration enumeration1;
/* 386*/            if((enumeration1 = findEntries(bundle, s1, "*.class", flag)) == null)
/* 389*/                continue;
                    URL url1;
/* 389*/            for(; enumeration1.hasMoreElements(); linkedlist.add(url1))
                    {
/* 390*/                String s3 = (url1 = (URL)enumeration1.nextElement()).getPath();
/* 393*/                classToBundleMapping.put(bundleEntryPathToClassName(s, s3), bundle);
                    }

                }

/* 400*/        if((enumeration = findEntries(bundle, "/", "*.jar", true)) == null)
/* 402*/            continue; /* Loop/switch isn't completed */
_L3:
                URL url;
                JarInputStream jarinputstream;
/* 402*/        do
                {
/* 402*/label0:
                    {
/* 402*/                if(!enumeration.hasMoreElements())
/* 403*/                    continue; /* Loop/switch isn't completed */
/* 403*/                url = (URL)enumeration.nextElement();
                        InputStream inputstream;
/* 404*/                if((inputstream = classloader.getResourceAsStream(url.getPath())) == null)
/* 406*/                    LOGGER.config(LocalizationMessages.OSGI_REGISTRY_ERROR_OPENING_RESOURCE_STREAM(url));
/* 411*/                else
/* 411*/                    try
                            {
/* 411*/                        jarinputstream = new JarInputStream(inputstream);
/* 420*/                        break label0;
                            }
/* 412*/                    catch(IOException ioexception)
                            {
/* 413*/                        LOGGER.log(Level.CONFIG, LocalizationMessages.OSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(url), ioexception);
/* 415*/                        try
                                {
/* 415*/                            inputstream.close();
                                }
/* 416*/                        catch(IOException _ex) { }
                            }
                    }
                } while(true);
/* 424*/        do
                {
                    JarEntry jarentry;
/* 424*/            if((jarentry = jarinputstream.getNextJarEntry()) == null)
/* 425*/                break;
                    String s2;
/* 425*/            String s4 = (s2 = jarentry.getName()).startsWith("/") ? s2 : (new StringBuilder("/")).append(s2).toString();
/* 429*/            if(s2.endsWith(".class") && s4.contains((new StringBuilder("/")).append(normalizedPackagePath(s)).toString()) && (flag || isPackageLevelEntry(s, s2)))
                    {
/* 441*/                classToBundleMapping.put(s2.replace(".class", "").replace('/', '.'), bundle);
/* 442*/                linkedlist.add(bundle.getResource(s2));
                    }
                } while(true);
/* 449*/        try
                {
/* 449*/            jarinputstream.close();
                }
/* 450*/        catch(IOException _ex) { }
                  goto _L3
                Exception exception;
/* 445*/        exception;
/* 446*/        LOGGER.log(Level.CONFIG, LocalizationMessages.OSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(url), exception);
/* 449*/        try
                {
/* 449*/            jarinputstream.close();
                }
/* 450*/        catch(IOException _ex) { }
                  goto _L3
/* 448*/        s;
/* 449*/        try
                {
/* 449*/            jarinputstream.close();
                }
/* 450*/        catch(IOException _ex) { }
/* 452*/        throw s;
/* 383*/        j++;
                  goto _L4
_L2:
/* 458*/        return Collections.enumeration(linkedlist);
            }

            public final Class classForNameWithException(String s)
                throws ClassNotFoundException
            {
                Bundle bundle;
/* 473*/        if((bundle = (Bundle)classToBundleMapping.get(s)) == null)
/* 476*/            throw new ClassNotFoundException(s);
/* 478*/        else
/* 478*/            return loadClass(bundle, s);
            }

            public final ResourceBundle getResourceBundle(String s)
            {
                Bundle abundle[];
                String s1;
                int j;
                int k;
/* 489*/        int i = s.lastIndexOf('.');
/* 490*/        s1 = s.substring(0, i).replace('.', '/');
/* 491*/        s = (new StringBuilder()).append(s.substring(i + 1, s.length())).append(".properties").toString();
/* 492*/        j = (abundle = bundleContext.getBundles()).length;
/* 492*/        k = 0;
_L2:
/* 492*/        if(k >= j)
/* 492*/            break; /* Loop/switch isn't completed */
                Object obj;
/* 492*/        if((obj = findEntries(((Bundle) (obj = abundle[k])), s1, s, false)) == null || !((Enumeration) (obj)).hasMoreElements())
/* 495*/            break MISSING_BLOCK_LABEL_150;
/* 495*/        s = (URL)((Enumeration) (obj)).nextElement();
/* 497*/        return new PropertyResourceBundle(s.openStream());
/* 498*/        JVM INSTR pop ;
/* 499*/        if(LOGGER.isLoggable(Level.FINE))
/* 501*/            LOGGER.fine("Exception caught when tried to load resource bundle in OSGi");
/* 503*/        return null;
/* 492*/        k++;
/* 492*/        if(true) goto _L2; else goto _L1
_L1:
/* 507*/        return null;
            }

            private OsgiRegistry(BundleContext bundlecontext)
            {
/* 517*/        bundleContext = bundlecontext;
            }

            final void hookUp()
            {
/* 526*/        setOSGiServiceFinderIteratorProvider();
/* 527*/        bundleContext.addBundleListener(this);
/* 528*/        registerExistingBundles();
            }

            private void registerExistingBundles()
            {
                Bundle abundle[];
/* 532*/        int i = (abundle = bundleContext.getBundles()).length;
/* 532*/        for(int j = 0; j < i; j++)
                {
                    Bundle bundle;
/* 532*/            if((bundle = abundle[j]).getState() == 4 || bundle.getState() == 8 || bundle.getState() == 32 || bundle.getState() == 16)
/* 535*/                register(bundle);
                }

            }

            private void setOSGiServiceFinderIteratorProvider()
            {
/* 541*/        ServiceFinder.setIteratorProvider(new OsgiServiceFinder());
            }

            private void register(Bundle bundle)
            {
/* 545*/        if(LOGGER.isLoggable(Level.FINEST))
/* 546*/            LOGGER.log(Level.FINEST, "checking bundle {0}", Long.valueOf(bundle.getBundleId()));
/* 550*/        lock.writeLock().lock();
                Object obj;
/* 552*/        if((obj = (Map)factories.get(Long.valueOf(bundle.getBundleId()))) == null)
                {
/* 554*/            obj = new ConcurrentHashMap();
/* 555*/            factories.put(Long.valueOf(bundle.getBundleId()), obj);
                }
/* 558*/        lock.writeLock().unlock();
/* 559*/        break MISSING_BLOCK_LABEL_134;
                Exception exception;
/* 558*/        exception;
/* 558*/        lock.writeLock().unlock();
/* 558*/        throw exception;
                Enumeration enumeration;
/* 561*/        if((enumeration = findEntries(bundle, "META-INF/services/", "*", false)) != null)
/* 563*/            do
                    {
/* 563*/                if(!enumeration.hasMoreElements())
/* 564*/                    break;
                        URL url;
                        String s;
/* 564*/                if(!(s = (url = (URL)enumeration.nextElement()).toString()).endsWith("/"))
                        {
/* 569*/                    s = s.substring(s.lastIndexOf("/") + 1);
/* 570*/                    ((Map) (obj)).put(s, new BundleSpiProvidersLoader(s, url, bundle));
                        }
                    } while(true);
/* 573*/        return;
            }

            private List locateAllProviders(String s)
            {
/* 576*/        lock.readLock().lock();
                Object obj;
/* 578*/        LinkedList linkedlist = new LinkedList();
/* 579*/        obj = factories.values().iterator();
/* 579*/        do
                {
/* 579*/            if(!((Iterator) (obj)).hasNext())
/* 579*/                break;
                    Map map;
/* 579*/            if((map = (Map)((Iterator) (obj)).next()).containsKey(s))
/* 582*/                try
                        {
/* 582*/                    linkedlist.addAll((Collection)((Callable)map.get(s)).call());
                        }
/* 583*/                catch(Exception _ex) { }
                } while(true);
/* 588*/        obj = linkedlist;
/* 590*/        lock.readLock().unlock();
/* 590*/        return ((List) (obj));
/* 590*/        s;
/* 590*/        lock.readLock().unlock();
/* 590*/        throw s;
            }

            private static Class loadClass(Bundle bundle, String s)
                throws ClassNotFoundException
            {
/* 596*/        return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction(bundle, s) {

                    public final Class run()
                        throws ClassNotFoundException
                    {
/* 599*/                return bundle.loadClass(className);
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/* 596*/                return run();
                    }

                    final Bundle val$bundle;
                    final String val$className;

                    
                    {
/* 596*/                bundle = bundle1;
/* 596*/                className = s;
/* 596*/                super();
                    }
        });
/* 602*/        JVM INSTR dup ;
/* 603*/        bundle;
/* 603*/        getException();
/* 603*/        JVM INSTR dup ;
/* 604*/        bundle;
/* 604*/        JVM INSTR instanceof #19  <Class ClassNotFoundException>;
/* 604*/        JVM INSTR ifeq 34;
                   goto _L1 _L2
_L1:
/* 605*/        break MISSING_BLOCK_LABEL_29;
_L2:
/* 605*/        break MISSING_BLOCK_LABEL_34;
/* 605*/        throw (ClassNotFoundException)bundle;
/* 606*/        if(bundle instanceof RuntimeException)
/* 607*/            throw (RuntimeException)bundle;
/* 609*/        else
/* 609*/            throw new ProcessingException(bundle);
            }

            private static Enumeration findEntries(Bundle bundle, String s, String s1, boolean flag)
            {
/* 618*/        return (Enumeration)AccessController.doPrivileged(new PrivilegedAction(bundle, s, s1, flag) {

                    public final Enumeration run()
                    {
/* 622*/                return bundle.findEntries(path, fileNamePattern, recursive);
                    }

                    public final volatile Object run()
                    {
/* 618*/                return run();
                    }

                    final Bundle val$bundle;
                    final String val$path;
                    final String val$fileNamePattern;
                    final boolean val$recursive;

                    
                    {
/* 618*/                bundle = bundle1;
/* 618*/                path = s;
/* 618*/                fileNamePattern = s1;
/* 618*/                recursive = flag;
/* 618*/                super();
                    }
        });
            }

            private static final String WEB_INF_CLASSES = "WEB-INF/classes/";
            private static final String CoreBundleSymbolicNAME = "org.glassfish.jersey.core.jersey-common";
            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/OsgiRegistry.getName());
            private final BundleContext bundleContext;
            private final Map factories = new HashMap();
            private final ReadWriteLock lock = new ReentrantReadWriteLock();
            private static OsgiRegistry instance;
            private final Map classToBundleMapping = new HashMap();




}
