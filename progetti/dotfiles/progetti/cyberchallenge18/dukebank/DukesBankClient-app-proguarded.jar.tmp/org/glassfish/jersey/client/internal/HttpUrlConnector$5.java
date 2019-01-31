// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.security.PrivilegedExceptionAction;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            HttpUrlConnector

static class val.method
    implements PrivilegedExceptionAction
{

            public final Object run()
                throws NoSuchFieldException, IllegalAccessException
            {
/* 474*/label0:
                {
                    Class class1;
/* 474*/            try
                    {
/* 474*/                val$httpURLConnection.setRequestMethod(val$method);
/* 512*/                break label0;
                    }
/* 477*/            catch(ProtocolException _ex)
                    {
/* 478*/                class1 = val$httpURLConnection.getClass();
/* 481*/                try
                        {
                            Object obj;
/* 481*/                    ((Field) (obj = class1.getDeclaredField("delegate"))).setAccessible(true);
/* 484*/                    HttpUrlConnector.access$200(((HttpURLConnection) (obj = (HttpURLConnection)((Field) (obj)).get(val$httpURLConnection))), val$method);
                        }
/* 487*/                catch(NoSuchFieldException _ex2) { }
/* 489*/                catch(IllegalArgumentException illegalargumentexception)
                        {
/* 490*/                    throw new RuntimeException(illegalargumentexception);
                        }
/* 491*/                catch(IllegalAccessException illegalaccessexception)
                        {
/* 492*/                    throw new RuntimeException(illegalaccessexception);
                        }
                    }
/* 496*/            try
                    {
                        Field field;
/* 496*/                do
                        {
/* 496*/                    if(class1 == null)
/* 498*/                        break label0;
/* 498*/                    try
                            {
/* 498*/                        field = class1.getDeclaredField("method");
/* 504*/                        break;
                            }
/* 500*/                    catch(NoSuchFieldException _ex)
                            {
/* 501*/                        class1 = class1.getSuperclass();
                            }
                        } while(true);
/* 505*/                field.setAccessible(true);
/* 506*/                field.set(val$httpURLConnection, val$method);
                    }
/* 509*/            catch(Exception exception)
                    {
/* 510*/                throw new RuntimeException(exception);
                    }
                }
/* 513*/        return null;
            }

            final HttpURLConnection val$httpURLConnection;
            final String val$method;

            (HttpURLConnection httpurlconnection, String s)
            {
/* 469*/        val$httpURLConnection = httpurlconnection;
/* 469*/        val$method = s;
/* 469*/        super();
            }
}
