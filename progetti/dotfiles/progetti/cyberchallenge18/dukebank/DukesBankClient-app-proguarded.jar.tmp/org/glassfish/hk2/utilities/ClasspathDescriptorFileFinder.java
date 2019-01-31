// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClasspathDescriptorFileFinder.java

package org.glassfish.hk2.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import org.glassfish.hk2.api.DescriptorFileFinder;
import org.glassfish.hk2.api.DescriptorFileFinderInformation;
import org.glassfish.hk2.utilities.reflection.Logger;

public class ClasspathDescriptorFileFinder
    implements DescriptorFileFinder, DescriptorFileFinderInformation
{

            public ClasspathDescriptorFileFinder()
            {
/*  90*/        this(org/glassfish/hk2/utilities/ClasspathDescriptorFileFinder.getClassLoader(), new String[] {
/*  90*/            "default"
                });
            }

            public ClasspathDescriptorFileFinder(ClassLoader classloader)
            {
/* 105*/        this(classloader, new String[] {
/* 105*/            "default"
                });
            }

            public transient ClasspathDescriptorFileFinder(ClassLoader classloader, String as[])
            {
/*  78*/        identifiers = new ArrayList();
/* 119*/        classLoader = classloader;
/* 120*/        names = as;
            }

            public List findDescriptorFiles()
                throws IOException
            {
/* 130*/        identifiers.clear();
/* 132*/        Object obj = new ArrayList();
                String as[];
/* 134*/        int i = (as = names).length;
/* 134*/        for(int j = 0; j < i; j++)
                {
/* 134*/            String s = as[j];
                    InputStream inputstream;
/* 135*/            for(Enumeration enumeration = classLoader.getResources((new StringBuilder("META-INF/hk2-locator/")).append(s).toString()); enumeration.hasMoreElements(); ((ArrayList) (obj)).add(inputstream))
                    {
/* 138*/                URL url = (URL)enumeration.nextElement();
/* 140*/                if(DEBUG_DESCRIPTOR_FINDER)
/* 141*/                    Logger.getLogger().debug((new StringBuilder("Adding in URL to set being parsed: ")).append(url).append(" from META-INF/hk2-locator/").append(s).toString());
/* 144*/                try
                        {
/* 144*/                    identifiers.add(url.toURI().toString());
                        }
/* 146*/                catch(URISyntaxException urisyntaxexception)
                        {
/* 147*/                    throw new IOException(urisyntaxexception);
                        }
/* 152*/                try
                        {
/* 152*/                    inputstream = url.openStream();
                        }
                        // Misplaced declaration of an exception variable
/* 154*/                catch(Object obj)
                        {
/* 155*/                    if(DEBUG_DESCRIPTOR_FINDER)
/* 156*/                        Logger.getLogger().debug((new StringBuilder("IOException for url ")).append(url).toString(), ((Throwable) (obj)));
/* 158*/                    throw obj;
                        }
                        // Misplaced declaration of an exception variable
/* 160*/                catch(Object obj)
                        {
/* 161*/                    if(DEBUG_DESCRIPTOR_FINDER)
/* 162*/                        Logger.getLogger().debug((new StringBuilder("Unexpected exception for url ")).append(url).toString(), ((Throwable) (obj)));
/* 164*/                    throw new IOException(((Throwable) (obj)));
                        }
/* 167*/                if(DEBUG_DESCRIPTOR_FINDER)
/* 168*/                    Logger.getLogger().debug((new StringBuilder("Input stream for: ")).append(url).append(" from META-INF/hk2-locator/").append(s).append(" has succesfully been opened").toString());
                    }

                }

/* 174*/        return ((List) (obj));
            }

            public List getDescriptorFileInformation()
            {
/* 182*/        return identifiers;
            }

            public String toString()
            {
/* 186*/        return (new StringBuilder("ClasspathDescriptorFileFinder(")).append(classLoader).append(",").append(Arrays.toString(names)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private static final String DEBUG_DESCRIPTOR_FINDER_PROPERTY = "org.jvnet.hk2.properties.debug.descriptor.file.finder";
            private static final boolean DEBUG_DESCRIPTOR_FINDER = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  69*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.descriptor.file.finder", "false")));
                }

                public final volatile Object run()
                {
/*  66*/            return run();
                }

    })).booleanValue();
            private static final String DEFAULT_NAME = "default";
            private final ClassLoader classLoader;
            private final String names[];
            private final ArrayList identifiers;

}
