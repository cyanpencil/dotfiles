// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryFinder.java

package javax.ws.rs.ext;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

final class FactoryFinder
{

            private FactoryFinder()
            {
            }

            static ClassLoader getContextClassLoader()
            {
/*  71*/        return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                    public final ClassLoader run()
                    {
/*  76*/                ClassLoader classloader = null;
/*  78*/                try
                        {
/*  78*/                    classloader = Thread.currentThread().getContextClassLoader();
                        }
/*  79*/                catch(SecurityException securityexception)
                        {
/*  80*/                    FactoryFinder.LOGGER.log(Level.WARNING, "Unable to get context classloader instance.", securityexception);
                        }
/*  85*/                return classloader;
                    }

                    public final volatile Object run()
                    {
/*  72*/                return run();
                    }

        });
            }

            private static Object newInstance(String s, ClassLoader classloader)
                throws ClassNotFoundException
            {
                Object obj;
/* 103*/        if(classloader == null)
/* 104*/            obj = Class.forName(s);
/* 107*/        else
/* 107*/            try
                    {
/* 107*/                obj = Class.forName(s, false, classloader);
                    }
                    // Misplaced declaration of an exception variable
/* 108*/            catch(Object obj)
                    {
/* 109*/                LOGGER.log(Level.FINE, (new StringBuilder("Unable to load provider class ")).append(s).append(" using custom classloader ").append(classloader.getClass().getName()).append(" trying again with current classloader.").toString(), ((Throwable) (obj)));
/* 115*/                obj = Class.forName(s);
                    }
/* 118*/        return ((Class) (obj)).newInstance();
/* 119*/        JVM INSTR dup ;
/* 120*/        obj;
/* 120*/        throw ;
                Exception exception;
/* 121*/        exception;
/* 122*/        throw new ClassNotFoundException((new StringBuilder("Provider ")).append(s).append(" could not be instantiated: ").append(exception).toString(), exception);
            }

            static Object find(String s, String s1)
                throws ClassNotFoundException
            {
                ClassLoader classloader;
                Object obj;
                Object obj1;
/* 148*/        classloader = getContextClassLoader();
/* 150*/        obj = (new StringBuilder("META-INF/services/")).append(s).toString();
/* 152*/        obj1 = null;
                Object obj3;
                InputStream inputstream;
/* 155*/        if(classloader == null)
/* 156*/            inputstream = ClassLoader.getSystemResourceAsStream(((String) (obj)));
/* 158*/        else
/* 158*/            inputstream = classloader.getResourceAsStream(((String) (obj)));
                String s2;
/* 161*/        if(inputstream == null || (s2 = ((BufferedReader) (obj1 = new BufferedReader(new InputStreamReader(inputstream, "UTF-8")))).readLine()) == null || "".equals(s2))
/* 166*/            break MISSING_BLOCK_LABEL_134;
/* 166*/        obj3 = newInstance(s2, classloader);
/* 174*/        try
                {
/* 174*/            ((BufferedReader) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
/* 175*/        catch(Object obj1)
                {
/* 176*/            LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 176*/                obj
                    }), ((Throwable) (obj1)));
                }
/* 177*/        return obj3;
/* 172*/        if(obj1 != null)
/* 174*/            try
                    {
/* 174*/                ((BufferedReader) (obj1)).close();
                    }
/* 175*/            catch(IOException ioexception1)
                    {
/* 176*/                LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 176*/                    obj
                        }), ioexception1);
                    }
/* 177*/        break MISSING_BLOCK_LABEL_300;
                Exception exception;
/* 169*/        exception;
/* 170*/        LOGGER.log(Level.FINER, (new StringBuilder("Failed to load service ")).append(s).append(" from ").append(((String) (obj))).toString(), exception);
/* 172*/        if(obj1 != null)
/* 174*/            try
                    {
/* 174*/                ((BufferedReader) (obj1)).close();
                    }
/* 175*/            catch(IOException ioexception2)
                    {
/* 176*/                LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 176*/                    obj
                        }), ioexception2);
                    }
/* 177*/        break MISSING_BLOCK_LABEL_300;
                Object obj4;
/* 172*/        obj4;
/* 172*/        if(obj1 != null)
/* 174*/            try
                    {
/* 174*/                ((BufferedReader) (obj1)).close();
                    }
/* 175*/            catch(IOException ioexception)
                    {
/* 176*/                LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 176*/                    obj
                        }), ioexception);
                    }
/* 177*/        throw obj4;
                FileInputStream fileinputstream;
                String s3;
/* 183*/        fileinputstream = null;
/* 184*/        s3 = null;
/* 186*/        Object obj5 = System.getProperty("java.home");
/* 187*/        s3 = (new StringBuilder()).append(((String) (obj5))).append(File.separator).append("lib").append(File.separator).append("jaxrs.properties").toString();
                Object obj2;
/* 188*/        if(!((File) (obj2 = new File(s3))).exists())
/* 190*/            break MISSING_BLOCK_LABEL_567;
/* 190*/        obj5 = new Properties();
/* 191*/        fileinputstream = new FileInputStream(((File) (obj2)));
/* 192*/        ((Properties) (obj5)).load(fileinputstream);
/* 193*/        obj = newInstance(((String) (obj2 = ((Properties) (obj5)).getProperty(s))), classloader);
/* 202*/        try
                {
/* 202*/            fileinputstream.close();
                }
                // Misplaced declaration of an exception variable
/* 203*/        catch(String s)
                {
/* 204*/            LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 204*/                s3
                    }), s);
                }
/* 205*/        return obj;
/* 196*/        obj5;
/* 197*/        LOGGER.log(Level.FINER, (new StringBuilder("Failed to load service ")).append(s).append(" from $java.home/lib/jaxrs.properties").toString(), ((Throwable) (obj5)));
/* 200*/        if(fileinputstream != null)
/* 202*/            try
                    {
/* 202*/                fileinputstream.close();
                    }
                    // Misplaced declaration of an exception variable
/* 203*/            catch(Object obj5)
                    {
/* 204*/                LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 204*/                    s3
                        }), ((Throwable) (obj5)));
                    }
/* 205*/        break MISSING_BLOCK_LABEL_567;
/* 200*/        s;
/* 200*/        if(fileinputstream != null)
/* 202*/            try
                    {
/* 202*/                fileinputstream.close();
                    }
                    // Misplaced declaration of an exception variable
/* 203*/            catch(String s1)
                    {
/* 204*/                LOGGER.log(Level.FINER, String.format("Error closing %s file.", new Object[] {
/* 204*/                    s3
                        }), s1);
                    }
/* 205*/        throw s;
                String s4;
/* 211*/        if((s4 = System.getProperty(s)) != null)
/* 213*/            return newInstance(s4, classloader);
/* 218*/        break MISSING_BLOCK_LABEL_621;
/* 215*/        s4;
/* 216*/        LOGGER.log(Level.FINER, (new StringBuilder("Failed to load service ")).append(s).append(" from a system property").toString(), s4);
/* 220*/        if(s1 == null)
/* 221*/            throw new ClassNotFoundException((new StringBuilder("Provider for ")).append(s).append(" cannot be found").toString(), null);
/* 225*/        else
/* 225*/            return newInstance(s1, classloader);
            }

            private static final Logger LOGGER = Logger.getLogger(javax/ws/rs/ext/FactoryFinder.getName());


}
