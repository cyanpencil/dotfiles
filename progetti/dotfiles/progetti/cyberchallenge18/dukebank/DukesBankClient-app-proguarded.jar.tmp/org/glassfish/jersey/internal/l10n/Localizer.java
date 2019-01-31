// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Localizer.java

package org.glassfish.jersey.internal.l10n;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import org.glassfish.hk2.osgiresourcelocator.ResourceFinder;
import org.glassfish.jersey.internal.OsgiRegistry;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.internal.l10n:
//            Localizable

public class Localizer
{

            public Localizer()
            {
/*  68*/        this(Locale.getDefault());
            }

            public Localizer(Locale locale)
            {
/*  72*/        _locale = locale;
/*  73*/        _resourceBundles = new HashMap();
            }

            public Locale getLocale()
            {
/*  77*/        return _locale;
            }

            public String localize(Localizable localizable)
            {
                String s;
                String s1;
/*  81*/        s = localizable.getKey();
/*  82*/        if("\0".equals(s))
/*  84*/            return (String)localizable.getArguments()[0];
/*  86*/        s1 = localizable.getResourceBundleName();
                Object obj;
/*  89*/        if((obj = (ResourceBundle)_resourceBundles.get(s1)) != null)
/*  93*/            break MISSING_BLOCK_LABEL_202;
                int i;
/*  93*/        try
                {
/*  93*/            obj = ResourceBundle.getBundle(s1, _locale);
                }
/*  94*/        catch(MissingResourceException _ex)
                {
/* 102*/            if((i = s1.lastIndexOf('.')) != -1)
                    {
/* 104*/                String s2 = s1.substring(i + 1);
                        Object obj1;
/* 107*/                try
                        {
/* 107*/                    obj = ResourceBundle.getBundle(s2, _locale);
                        }
/* 111*/                catch(MissingResourceException _ex2)
                        {
/* 113*/                    if((obj1 = ReflectionHelper.getOsgiRegistryInstance()) != null)
/* 115*/                        obj = ((OsgiRegistry) (obj1)).getResourceBundle(s1);
/* 117*/                    else
/* 117*/                    if((obj1 = ResourceFinder.findEntry(((String) (obj1 = (new StringBuilder()).append(s1.replace('.', '/')).append(".properties").toString())))) != null)
/* 121*/                        try
                                {
/* 121*/                            obj = new PropertyResourceBundle(((URL) (obj1)).openStream());
                                }
/* 122*/                        catch(IOException _ex3) { }
                        }
                    }
                }
/* 131*/        if(obj == null)
/* 132*/            return getDefaultMessage(localizable);
/* 134*/        _resourceBundles.put(s1, obj);
                Object aobj[];
/* 138*/        if(s == null)
/* 139*/            s = "undefined";
/* 144*/        try
                {
/* 144*/            s = ((ResourceBundle) (obj)).getString(s);
                }
/* 145*/        catch(MissingResourceException _ex)
                {
/* 147*/            s = ((ResourceBundle) (obj)).getString("undefined");
                }
/* 151*/        aobj = localizable.getArguments();
/* 152*/        for(int j = 0; j < aobj.length; j++)
/* 153*/            if(aobj[j] instanceof Localizable)
/* 154*/                aobj[j] = localize((Localizable)aobj[j]);

                String s3;
/* 158*/        return s3 = MessageFormat.format(s, aobj);
/* 161*/        JVM INSTR pop ;
/* 162*/        return getDefaultMessage(localizable);
            }

            private String getDefaultMessage(Localizable localizable)
            {
/* 168*/        String s = localizable.getKey();
/* 169*/        localizable = ((Localizable) (localizable.getArguments()));
                StringBuilder stringbuilder;
/* 170*/        (stringbuilder = new StringBuilder()).append("[failed to localize] ");
/* 172*/        stringbuilder.append(s);
/* 173*/        if(localizable != null)
                {
/* 174*/            stringbuilder.append('(');
/* 175*/            for(int i = 0; i < localizable.length; i++)
                    {
/* 176*/                if(i != 0)
/* 177*/                    stringbuilder.append(", ");
/* 179*/                stringbuilder.append(String.valueOf(localizable[i]));
                    }

/* 181*/            stringbuilder.append(')');
                }
/* 183*/        return stringbuilder.toString();
            }

            private final Locale _locale;
            private final HashMap _resourceBundles;
}
