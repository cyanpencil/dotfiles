// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinder.java

package org.glassfish.jersey.internal;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.ReflectPermission;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, OsgiRegistry, ServiceConfigurationError

public final class ServiceFinder
    implements Iterable
{
    public static final class DefaultServiceIteratorProvider extends ServiceIteratorProvider
    {

                public final Iterator createIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
/* 897*/            return new LazyObjectIterator(class1, s, classloader, flag);
                }

                public final Iterator createClassIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
/* 903*/            return new LazyClassIterator(class1, s, classloader, flag);
                }

                public DefaultServiceIteratorProvider()
                {
                }
    }

    public static abstract class ServiceIteratorProvider
    {

                private static ServiceIteratorProvider getInstance()
                {
/* 827*/            if((obj = sip) == null)
/* 829*/                synchronized(sipLock)
                        {
/* 830*/                    if((obj = sip) == null)
/* 832*/                        sip = ((ServiceIteratorProvider) (obj = new DefaultServiceIteratorProvider()));
                        }
/* 836*/            return ((ServiceIteratorProvider) (obj));
                }

                private static void setInstance(ServiceIteratorProvider serviceiteratorprovider)
                    throws SecurityException
                {
                    SecurityManager securitymanager;
/* 840*/            if((securitymanager = System.getSecurityManager()) != null)
                    {
/* 842*/                ReflectPermission reflectpermission = new ReflectPermission("suppressAccessChecks");
/* 843*/                securitymanager.checkPermission(reflectpermission);
                    }
/* 845*/            synchronized(sipLock)
                    {
/* 846*/                sip = serviceiteratorprovider;
                    }
                }

                public abstract Iterator createIterator(Class class1, String s, ClassLoader classloader, boolean flag);

                public abstract Iterator createClassIterator(Class class1, String s, ClassLoader classloader, boolean flag);

                private static volatile ServiceIteratorProvider sip;
                private static final Object sipLock = new Object();




                public ServiceIteratorProvider()
                {
                }
    }

    static final class LazyObjectIterator extends AbstractLazyIterator
        implements Iterator
    {

                public final boolean hasNext()
                    throws ServiceConfigurationError
                {
/* 699*/            if(nextName != null)
/* 700*/                return true;
/* 702*/            setConfigs();
_L4:
/* 704*/            if(nextName != null)
/* 705*/                break; /* Loop/switch isn't completed */
/* 705*/            for(; pending == null || !pending.hasNext(); pending = ServiceFinder.parse(serviceName, (URL)configs.nextElement(), returned))
/* 706*/                if(!configs.hasMoreElements())
/* 707*/                    return false;

/* 711*/            nextName = (String)pending.next();
/* 713*/            t = service.cast(((Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(nextName, loader))).newInstance());
/* 780*/            continue; /* Loop/switch isn't completed */
                    Object obj;
/* 716*/            obj;
/* 717*/            if(ignoreOnClassNotFound)
                    {
/* 718*/                if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 719*/                    ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((InstantiationException) (obj)).getLocalizedMessage()));
/* 723*/                nextName = null;
                    } else
                    {
/* 725*/                ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((InstantiationException) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
                    }
/* 780*/            continue; /* Loop/switch isn't completed */
/* 729*/            obj;
/* 730*/            ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((IllegalAccessException) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
/* 780*/            continue; /* Loop/switch isn't completed */
/* 734*/            JVM INSTR pop ;
/* 735*/            handleClassNotFoundException();
/* 780*/            continue; /* Loop/switch isn't completed */
/* 736*/            obj;
/* 738*/            if(ignoreOnClassNotFound)
                    {
/* 739*/                if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 742*/                    ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((NoClassDefFoundError) (obj)).getLocalizedMessage(), nextName, service));
/* 746*/                nextName = null;
                    } else
                    {
/* 748*/                ServiceFinder.fail(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((NoClassDefFoundError) (obj)).getLocalizedMessage(), nextName, service), ((Throwable) (obj)));
                    }
/* 780*/            continue; /* Loop/switch isn't completed */
/* 754*/            JVM INSTR dup ;
                    Object obj1;
/* 755*/            obj1;
/* 755*/            getCause();
/* 755*/            JVM INSTR dup ;
/* 756*/            obj1;
/* 756*/            JVM INSTR instanceof #4   <Class ClassNotFoundException>;
/* 756*/            JVM INSTR ifeq 344;
                       goto _L1 _L2
_L1:
/* 757*/            break MISSING_BLOCK_LABEL_337;
_L2:
/* 757*/            break MISSING_BLOCK_LABEL_344;
/* 757*/            handleClassNotFoundException();
/* 757*/            continue; /* Loop/switch isn't completed */
/* 758*/            if(obj1 instanceof ClassFormatError)
                    {
/* 760*/                if(ignoreOnClassNotFound)
                        {
/* 761*/                    if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 762*/                        ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj1)).getLocalizedMessage(), nextName, service));
/* 766*/                    nextName = null;
                        } else
                        {
/* 768*/                    ServiceFinder.fail(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj1)).getLocalizedMessage(), nextName, service), ((Throwable) (obj1)));
                        }
                    } else
                    {
/* 775*/                ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((Throwable) (obj1)).getLocalizedMessage()), ((Throwable) (obj1)));
                    }
/* 780*/            if(true) goto _L4; else goto _L3
_L3:
/* 782*/            return true;
                }

                public final Object next()
                {
/* 787*/            if(!hasNext())
/* 788*/                throw new NoSuchElementException();
/* 790*/            nextName = null;
/* 791*/            if(ServiceFinder.LOGGER.isLoggable(Level.FINEST))
/* 792*/                ServiceFinder.LOGGER.log(Level.FINEST, (new StringBuilder("Loading next object: ")).append(t.getClass().getName()).toString());
/* 794*/            return t;
                }

                private void handleClassNotFoundException()
                    throws ServiceConfigurationError
                {
/* 798*/            if(ignoreOnClassNotFound)
                    {
/* 800*/                if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 801*/                    ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.PROVIDER_NOT_FOUND(nextName, service));
/* 804*/                nextName = null;
/* 804*/                return;
                    } else
                    {
/* 806*/                ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(nextName, service));
/* 809*/                return;
                    }
                }

                private Object t;

                private LazyObjectIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
/* 694*/            super(class1, s, classloader, flag);
                }

    }

    static final class LazyClassIterator extends AbstractLazyIterator
        implements Iterator
    {

                public final Class next()
                {
                    String s;
/* 640*/            if(!hasNext())
/* 641*/                throw new NoSuchElementException();
/* 643*/            s = nextName;
/* 644*/            nextName = null;
                    Object obj;
/* 647*/            obj = (Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(s, loader));
/* 650*/            if(ServiceFinder.LOGGER.isLoggable(Level.FINEST))
/* 651*/                ServiceFinder.LOGGER.log(Level.FINEST, (new StringBuilder("Loading next class: ")).append(((Class) (obj)).getName()).toString());
/* 654*/            return ((Class) (obj));
/* 656*/            JVM INSTR pop ;
/* 657*/            ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(s, service));
/* 679*/            break MISSING_BLOCK_LABEL_210;
/* 659*/            JVM INSTR dup ;
/* 661*/            obj;
/* 661*/            getCause();
/* 661*/            JVM INSTR dup ;
/* 663*/            obj;
/* 663*/            JVM INSTR instanceof #4   <Class ClassNotFoundException>;
/* 663*/            JVM INSTR ifeq 132;
                       goto _L1 _L2
_L1:
/* 664*/            break MISSING_BLOCK_LABEL_114;
_L2:
/* 664*/            break MISSING_BLOCK_LABEL_132;
/* 664*/            ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(s, service));
/* 664*/            break MISSING_BLOCK_LABEL_210;
/* 666*/            if(obj instanceof NoClassDefFoundError)
/* 667*/                ServiceFinder.fail(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((Throwable) (obj)).getLocalizedMessage(), s, service));
/* 670*/            else
/* 670*/            if(obj instanceof ClassFormatError)
/* 671*/                ServiceFinder.fail(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj)).getLocalizedMessage(), s, service));
/* 675*/            else
/* 675*/                ServiceFinder.fail(serviceName, LocalizationMessages.PROVIDER_CLASS_COULD_NOT_BE_LOADED(s, service, ((Throwable) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
/* 681*/            return null;
                }

                public final volatile Object next()
                {
/* 627*/            return next();
                }

                private LazyClassIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
/* 635*/            super(class1, s, classloader, flag);
                }

    }

    static class AbstractLazyIterator
    {

                protected final void setConfigs()
                {
/* 551*/            if(configs == null)
/* 553*/                try
                        {
/* 553*/                    String s = (new StringBuilder("META-INF/services/")).append(serviceName).toString();
/* 554*/                    configs = ServiceFinder.getResources(loader, s);
/* 557*/                    return;
                        }
/* 555*/                catch(IOException ioexception)
                        {
/* 556*/                    ServiceFinder.fail(serviceName, (new StringBuilder(": ")).append(ioexception).toString());
                        }
                }

                public boolean hasNext()
                    throws ServiceConfigurationError
                {
/* 562*/            if(nextName != null)
/* 563*/                return true;
/* 565*/            setConfigs();
_L4:
/* 567*/            if(nextName != null)
/* 568*/                break; /* Loop/switch isn't completed */
/* 568*/            for(; pending == null || !pending.hasNext(); pending = ServiceFinder.parse(serviceName, (URL)configs.nextElement(), returned))
/* 569*/                if(!configs.hasMoreElements())
/* 570*/                    return false;

/* 574*/            nextName = (String)pending.next();
/* 575*/            if(!ignoreOnClassNotFound)
/* 577*/                continue; /* Loop/switch isn't completed */
/* 577*/            AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(nextName, loader));
/* 607*/            continue; /* Loop/switch isn't completed */
/* 578*/            JVM INSTR pop ;
/* 579*/            handleClassNotFoundException();
/* 607*/            continue; /* Loop/switch isn't completed */
/* 580*/            JVM INSTR dup ;
                    Object obj;
/* 581*/            obj;
/* 581*/            getException();
/* 581*/            JVM INSTR dup ;
/* 582*/            obj;
/* 582*/            JVM INSTR instanceof #5   <Class ClassNotFoundException>;
/* 582*/            JVM INSTR ifeq 152;
                       goto _L1 _L2
_L1:
/* 583*/            break MISSING_BLOCK_LABEL_145;
_L2:
/* 583*/            break MISSING_BLOCK_LABEL_152;
/* 583*/            handleClassNotFoundException();
/* 583*/            continue; /* Loop/switch isn't completed */
/* 584*/            if(obj instanceof NoClassDefFoundError)
                    {
/* 586*/                if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 589*/                    ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((Throwable) (obj)).getLocalizedMessage(), nextName, service));
/* 593*/                nextName = null;
                    } else
/* 594*/            if(obj instanceof ClassFormatError)
                    {
/* 596*/                if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 597*/                    ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj)).getLocalizedMessage(), nextName, service));
/* 601*/                nextName = null;
                    } else
/* 602*/            if(obj instanceof RuntimeException)
/* 603*/                throw (RuntimeException)obj;
/* 605*/            else
/* 605*/                throw new IllegalStateException(((Throwable) (obj)));
/* 605*/            if(true) goto _L4; else goto _L3
_L3:
/* 610*/            return true;
                }

                public void remove()
                {
/* 614*/            throw new UnsupportedOperationException();
                }

                private void handleClassNotFoundException()
                {
/* 619*/            if(ServiceFinder.LOGGER.isLoggable(Level.CONFIG))
/* 620*/                ServiceFinder.LOGGER.log(Level.CONFIG, LocalizationMessages.PROVIDER_NOT_FOUND(nextName, service));
/* 623*/            nextName = null;
                }

                final Class service;
                final String serviceName;
                final ClassLoader loader;
                final boolean ignoreOnClassNotFound;
                Enumeration configs;
                Iterator pending;
                Set returned;
                String nextName;

                private AbstractLazyIterator(Class class1, String s, ClassLoader classloader, boolean flag)
                {
/* 534*/            configs = null;
/* 535*/            pending = null;
/* 536*/            returned = new TreeSet();
/* 537*/            nextName = null;
/* 544*/            service = class1;
/* 545*/            serviceName = s;
/* 546*/            loader = classloader;
/* 547*/            ignoreOnClassNotFound = flag;
                }

    }


            private static Enumeration getResources(ClassLoader classloader, String s)
                throws IOException
            {
/* 177*/        if(classloader == null)
/* 178*/            return getResources(s);
/* 180*/        if((classloader = classloader.getResources(s)) != null && classloader.hasMoreElements())
/* 182*/            return classloader;
/* 184*/        else
/* 184*/            return getResources(s);
            }

            private static Enumeration getResources(String s)
                throws IOException
            {
/* 190*/        if(org/glassfish/jersey/internal/ServiceFinder.getClassLoader() != null)
/* 191*/            return org/glassfish/jersey/internal/ServiceFinder.getClassLoader().getResources(s);
/* 193*/        else
/* 193*/            return ClassLoader.getSystemResources(s);
            }

            private static ClassLoader _getContextClassLoader()
            {
/* 198*/        return (ClassLoader)AccessController.doPrivileged(ReflectionHelper.getContextClassLoaderPA());
            }

            public static ServiceFinder find(Class class1, ClassLoader classloader)
                throws ServiceConfigurationError
            {
/* 229*/        return find(class1, classloader, false);
            }

            public static ServiceFinder find(Class class1, ClassLoader classloader, boolean flag)
                throws ServiceConfigurationError
            {
/* 265*/        return new ServiceFinder(class1, classloader, flag);
            }

            public static ServiceFinder find(Class class1)
                throws ServiceConfigurationError
            {
/* 288*/        return find(class1, _getContextClassLoader(), false);
            }

            public static ServiceFinder find(Class class1, boolean flag)
                throws ServiceConfigurationError
            {
/* 314*/        return find(class1, _getContextClassLoader(), flag);
            }

            public static ServiceFinder find(String s)
                throws ServiceConfigurationError
            {
/* 331*/        return new ServiceFinder(java/lang/Object, s, _getContextClassLoader(), false);
            }

            public static void setIteratorProvider(ServiceIteratorProvider serviceiteratorprovider)
                throws SecurityException
            {
/* 348*/        ServiceIteratorProvider.setInstance(serviceiteratorprovider);
            }

            private ServiceFinder(Class class1, ClassLoader classloader, boolean flag)
            {
/* 355*/        this(class1, class1.getName(), classloader, flag);
            }

            private ServiceFinder(Class class1, String s, ClassLoader classloader, boolean flag)
            {
/* 363*/        serviceClass = class1;
/* 364*/        serviceName = s;
/* 365*/        classLoader = classloader;
/* 366*/        ignoreOnClassNotFound = flag;
            }

            public final Iterator iterator()
            {
/* 380*/        return ServiceIteratorProvider.getInstance().createIterator(serviceClass, serviceName, classLoader, ignoreOnClassNotFound);
            }

            public final Object[] toArray()
                throws ServiceConfigurationError
            {
/* 395*/        ArrayList arraylist = new ArrayList();
                Object obj;
/* 396*/        for(Iterator iterator1 = iterator(); iterator1.hasNext(); arraylist.add(obj))
/* 396*/            obj = iterator1.next();

/* 399*/        return arraylist.toArray((Object[])Array.newInstance(serviceClass, arraylist.size()));
            }

            public final Class[] toClassArray()
                throws ServiceConfigurationError
            {
/* 413*/        ArrayList arraylist = new ArrayList();
                Object obj;
/* 415*/        for(obj = ((ServiceIteratorProvider) (obj = ServiceIteratorProvider.getInstance())).createClassIterator(serviceClass, serviceName, classLoader, ignoreOnClassNotFound); ((Iterator) (obj)).hasNext(); arraylist.add(((Iterator) (obj)).next()));
/* 421*/        return (Class[])arraylist.toArray((Class[])Array.newInstance(java/lang/Class, arraylist.size()));
            }

            private static void fail(String s, String s1, Throwable throwable)
                throws ServiceConfigurationError
            {
/* 426*/        (s = new ServiceConfigurationError((new StringBuilder()).append(s).append(": ").append(s1).toString())).initCause(throwable);
/* 428*/        throw s;
            }

            private static void fail(String s, String s1)
                throws ServiceConfigurationError
            {
/* 433*/        throw new ServiceConfigurationError((new StringBuilder()).append(s).append(": ").append(s1).toString());
            }

            private static void fail(String s, URL url, int i, String s1)
                throws ServiceConfigurationError
            {
/* 438*/        fail(s, (new StringBuilder()).append(url).append(":").append(i).append(": ").append(s1).toString());
            }

            private static int parseLine(String s, URL url, BufferedReader bufferedreader, int i, List list, Set set)
                throws IOException, ServiceConfigurationError
            {
/* 449*/        if((bufferedreader = bufferedreader.readLine()) == null)
/* 451*/            return -1;
                int j;
/* 453*/        if((j = bufferedreader.indexOf('#')) >= 0)
/* 455*/            bufferedreader = bufferedreader.substring(0, j);
/* 457*/        if((j = (bufferedreader = bufferedreader.trim()).length()) != 0)
                {
/* 460*/            if(bufferedreader.indexOf(' ') >= 0 || bufferedreader.indexOf('\t') >= 0)
/* 461*/                fail(s, url, i, LocalizationMessages.ILLEGAL_CONFIG_SYNTAX());
                    int k;
/* 463*/            if(!Character.isJavaIdentifierStart(k = bufferedreader.codePointAt(0)))
/* 465*/                fail(s, url, i, LocalizationMessages.ILLEGAL_PROVIDER_CLASS_NAME(bufferedreader));
/* 467*/            for(int l = Character.charCount(k); l < j; l += Character.charCount(k))
/* 468*/                if(!Character.isJavaIdentifierPart(k = bufferedreader.codePointAt(l)) && k != 46)
/* 470*/                    fail(s, url, i, LocalizationMessages.ILLEGAL_PROVIDER_CLASS_NAME(bufferedreader));

/* 473*/            if(!set.contains(bufferedreader))
                    {
/* 474*/                list.add(bufferedreader);
/* 475*/                set.add(bufferedreader);
                    }
                }
/* 478*/        return i + 1;
            }

            private static Iterator parse(String s, URL url, Set set)
                throws ServiceConfigurationError
            {
                InputStream inputstream;
                BufferedReader bufferedreader;
                ArrayList arraylist;
/* 499*/        inputstream = null;
/* 500*/        bufferedreader = null;
/* 501*/        arraylist = new ArrayList();
                URLConnection urlconnection;
/* 503*/        (urlconnection = url.openConnection()).setUseCaches(false);
/* 505*/        inputstream = urlconnection.getInputStream();
/* 506*/        bufferedreader = new BufferedReader(new InputStreamReader(inputstream, "utf-8"));
/* 507*/        for(int i = 1; (i = parseLine(s, url, bufferedreader, i, arraylist, set)) >= 0;);
/* 516*/        try
                {
/* 516*/            bufferedreader.close();
/* 518*/            if(inputstream != null)
/* 519*/                inputstream.close();
                }
/* 521*/        catch(IOException ioexception)
                {
/* 522*/            fail(s, (new StringBuilder(": ")).append(ioexception).toString());
                }
/* 524*/        break MISSING_BLOCK_LABEL_228;
                IOException ioexception1;
/* 511*/        ioexception1;
/* 512*/        fail(s, (new StringBuilder(": ")).append(ioexception1).toString());
/* 515*/        try
                {
/* 515*/            if(bufferedreader != null)
/* 516*/                bufferedreader.close();
/* 518*/            if(inputstream != null)
/* 519*/                inputstream.close();
                }
/* 521*/        catch(IOException ioexception2)
                {
/* 522*/            fail(s, (new StringBuilder(": ")).append(ioexception2).toString());
                }
/* 524*/        break MISSING_BLOCK_LABEL_228;
/* 514*/        url;
/* 515*/        try
                {
/* 515*/            if(bufferedreader != null)
/* 516*/                bufferedreader.close();
/* 518*/            if(inputstream != null)
/* 519*/                inputstream.close();
                }
                // Misplaced declaration of an exception variable
/* 521*/        catch(Set set)
                {
/* 522*/            fail(s, (new StringBuilder(": ")).append(set).toString());
                }
/* 523*/        throw url;
/* 525*/        return arraylist.iterator();
            }

            private static final Logger LOGGER;
            private static final String PREFIX = "META-INF/services/";
            private final Class serviceClass;
            private final String serviceName;
            private final ClassLoader classLoader;
            private final boolean ignoreOnClassNotFound;

            static 
            {
/* 157*/        LOGGER = Logger.getLogger(org/glassfish/jersey/internal/ServiceFinder.getName());
                OsgiRegistry osgiregistry;
/* 165*/        if((osgiregistry = ReflectionHelper.getOsgiRegistryInstance()) != null)
                {
/* 168*/            LOGGER.log(Level.CONFIG, "Running in an OSGi environment");
/* 170*/            osgiregistry.hookUp();
                } else
                {
/* 172*/            LOGGER.log(Level.CONFIG, "Running in a non-OSGi environment");
                }
            }





}
