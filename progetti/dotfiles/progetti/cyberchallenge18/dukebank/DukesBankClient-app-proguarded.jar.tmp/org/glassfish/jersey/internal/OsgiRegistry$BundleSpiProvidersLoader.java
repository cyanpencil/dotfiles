// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, OsgiRegistry

static class bundle
    implements Callable
{

            public List call()
                throws Exception
            {
                Object obj;
                Exception exception1;
/* 222*/        obj = null;
                ArrayList arraylist;
/* 225*/        try
                {
/* 225*/            if(OsgiRegistry.access$100().isLoggable(Level.FINEST))
/* 226*/                OsgiRegistry.access$100().log(Level.FINEST, "Loading providers for SPI: {0}", spi);
/* 228*/            obj = new BufferedReader(new InputStreamReader(spiRegistryUrl.openStream(), "UTF-8"));
/* 231*/            ArrayList arraylist1 = new ArrayList();
/* 232*/            do
                    {
                        String s;
/* 232*/                if((s = ((BufferedReader) (obj)).readLine()) == null)
/* 233*/                    break;
/* 233*/                if(s.trim().length() != 0)
                        {
/* 236*/                    if(OsgiRegistry.access$100().isLoggable(Level.FINEST))
/* 237*/                        OsgiRegistry.access$100().log(Level.FINEST, "SPI provider: {0}", s);
/* 239*/                    arraylist1.add(OsgiRegistry.access$200(bundle, s));
                        }
                    } while(true);
/* 242*/            arraylist = arraylist1;
                }
/* 243*/        catch(Exception exception)
                {
/* 244*/            OsgiRegistry.access$100().log(Level.WARNING, LocalizationMessages.EXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), exception);
/* 245*/            throw exception;
                }
/* 246*/        catch(Error error)
                {
/* 247*/            OsgiRegistry.access$100().log(Level.WARNING, LocalizationMessages.ERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS(), error);
/* 248*/            throw error;
                }
/* 250*/        finally
                {
/* 250*/            if(obj == null) goto _L0; else goto _L0
                }
/* 252*/        try
                {
/* 252*/            ((BufferedReader) (obj)).close();
                }
                // Misplaced declaration of an exception variable
/* 253*/        catch(Object obj)
                {
/* 254*/            OsgiRegistry.access$100().log(Level.FINE, (new StringBuilder("Error closing SPI registry stream:")).append(spiRegistryUrl).toString(), ((Throwable) (obj)));
                }
/* 255*/        return arraylist;
/* 252*/        try
                {
/* 252*/            ((BufferedReader) (obj)).close();
                }
/* 253*/        catch(IOException ioexception)
                {
/* 254*/            OsgiRegistry.access$100().log(Level.FINE, (new StringBuilder("Error closing SPI registry stream:")).append(spiRegistryUrl).toString(), ioexception);
                }
/* 255*/        throw exception1;
            }

            public String toString()
            {
/* 262*/        return spiRegistryUrlString;
            }

            public int hashCode()
            {
/* 267*/        return spiRegistryUrlString.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 272*/        if(obj instanceof spiRegistryUrlString)
/* 273*/            return spiRegistryUrlString.equals(((spiRegistryUrlString)obj).spiRegistryUrlString);
/* 275*/        else
/* 275*/            return false;
            }

            public volatile Object call()
                throws Exception
            {
/* 206*/        return call();
            }

            private final String spi;
            private final URL spiRegistryUrl;
            private final String spiRegistryUrlString;
            private final Bundle bundle;

            I(String s, URL url, Bundle bundle1)
            {
/* 214*/        spi = s;
/* 215*/        spiRegistryUrl = url;
/* 216*/        spiRegistryUrlString = url.toExternalForm();
/* 217*/        bundle = bundle1;
            }
}
