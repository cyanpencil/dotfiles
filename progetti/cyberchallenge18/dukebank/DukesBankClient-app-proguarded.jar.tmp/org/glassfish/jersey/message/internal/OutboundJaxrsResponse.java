// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundJaxrsResponse.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.MoreObjects;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext, Statuses

public class OutboundJaxrsResponse extends Response
{
    public static class Builder extends javax.ws.rs.core.Response.ResponseBuilder
    {

                public static void setBaseUri(URI uri)
                {
/* 349*/            baseUriThreadLocal.set(uri);
                }

                private static URI getBaseUri()
                {
/* 363*/            return (URI)baseUriThreadLocal.get();
                }

                public static void clearBaseUri()
                {
/* 375*/            baseUriThreadLocal.remove();
                }

                public Response build()
                {
                    Object obj;
/* 389*/            if((obj = status) == null)
/* 391*/                obj = context.hasEntity() ? ((Object) (javax.ws.rs.core.Response.Status.OK)) : ((Object) (javax.ws.rs.core.Response.Status.NO_CONTENT));
/* 393*/            return new OutboundJaxrsResponse(((javax.ws.rs.core.Response.StatusType) (obj)), new OutboundMessageContext(context));
                }

                public javax.ws.rs.core.Response.ResponseBuilder clone()
                {
/* 399*/            return (new Builder(new OutboundMessageContext(context))).status(status);
                }

                public javax.ws.rs.core.Response.ResponseBuilder status(javax.ws.rs.core.Response.StatusType statustype)
                {
/* 404*/            if(statustype == null)
                    {
/* 405*/                throw new IllegalArgumentException("Response status must not be 'null'");
                    } else
                    {
/* 408*/                status = statustype;
/* 409*/                return this;
                    }
                }

                public javax.ws.rs.core.Response.ResponseBuilder status(int i)
                {
/* 414*/            status = Statuses.from(i);
/* 415*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder entity(Object obj)
                {
/* 420*/            context.setEntity(obj);
/* 421*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder entity(Object obj, Annotation aannotation[])
                {
/* 426*/            context.setEntity(obj, aannotation);
/* 427*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder type(MediaType mediatype)
                {
/* 432*/            context.setMediaType(mediatype);
/* 433*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder type(String s)
                {
/* 438*/            return type(s != null ? MediaType.valueOf(s) : null);
                }

                public javax.ws.rs.core.Response.ResponseBuilder variant(Variant variant1)
                {
/* 443*/            if(variant1 == null)
                    {
/* 444*/                type(((MediaType) (null)));
/* 445*/                language(((String) (null)));
/* 446*/                encoding(null);
/* 447*/                return this;
                    } else
                    {
/* 450*/                type(variant1.getMediaType());
/* 451*/                language(variant1.getLanguage());
/* 452*/                encoding(variant1.getEncoding());
/* 454*/                return this;
                    }
                }

                public javax.ws.rs.core.Response.ResponseBuilder variants(List list)
                {
/* 459*/            if(list == null)
                    {
/* 460*/                header("Vary", null);
/* 461*/                return this;
                    }
/* 464*/            if(list.isEmpty())
/* 465*/                return this;
/* 468*/            MediaType mediatype = ((Variant)list.get(0)).getMediaType();
/* 469*/            boolean flag = false;
/* 471*/            Locale locale = ((Variant)list.get(0)).getLanguage();
/* 472*/            boolean flag1 = false;
/* 474*/            String s = ((Variant)list.get(0)).getEncoding();
/* 475*/            boolean flag2 = false;
/* 477*/            for(list = list.iterator(); list.hasNext();)
                    {
/* 477*/                Variant variant1 = (Variant)list.next();
/* 478*/                flag |= !flag && vary(variant1.getMediaType(), mediatype);
/* 479*/                flag1 |= !flag1 && vary(variant1.getLanguage(), locale);
/* 480*/                flag2 |= !flag2 && vary(variant1.getEncoding(), s);
                    }

/* 483*/            list = new StringBuilder();
/* 484*/            append(list, flag, "Accept");
/* 485*/            append(list, flag1, "Accept-Language");
/* 486*/            append(list, flag2, "Accept-Encoding");
/* 488*/            if(list.length() > 0)
/* 489*/                header("Vary", list.toString());
/* 491*/            return this;
                }

                private boolean vary(MediaType mediatype, MediaType mediatype1)
                {
/* 495*/            return mediatype != null && !mediatype.equals(mediatype1);
                }

                private boolean vary(Locale locale, Locale locale1)
                {
/* 499*/            return locale != null && !locale.equals(locale1);
                }

                private boolean vary(String s, String s1)
                {
/* 503*/            return s != null && !s.equalsIgnoreCase(s1);
                }

                private void append(StringBuilder stringbuilder, boolean flag, String s)
                {
/* 507*/            if(flag)
                    {
/* 508*/                if(stringbuilder.length() > 0)
/* 509*/                    stringbuilder.append(',');
/* 511*/                stringbuilder.append(s);
                    }
                }

                public javax.ws.rs.core.Response.ResponseBuilder language(String s)
                {
/* 517*/            headerSingle("Content-Language", s);
/* 518*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder language(Locale locale)
                {
/* 523*/            headerSingle("Content-Language", locale);
/* 524*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder location(URI uri)
                {
/* 529*/            URI uri1 = uri;
                    URI uri2;
/* 530*/            if(uri != null && !uri.isAbsolute() && (uri2 = getBaseUri()) != null)
/* 533*/                uri1 = uri2.resolve(uri);
/* 536*/            headerSingle("Location", uri1);
/* 537*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder contentLocation(URI uri)
                {
/* 542*/            headerSingle("Content-Location", uri);
/* 543*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder encoding(String s)
                {
/* 548*/            headerSingle("Content-Encoding", s);
/* 549*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder tag(EntityTag entitytag)
                {
/* 554*/            headerSingle("ETag", entitytag);
/* 555*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder tag(String s)
                {
/* 560*/            return tag(s != null ? new EntityTag(s) : null);
                }

                public javax.ws.rs.core.Response.ResponseBuilder lastModified(Date date)
                {
/* 565*/            headerSingle("Last-Modified", date);
/* 566*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder cacheControl(CacheControl cachecontrol)
                {
/* 571*/            headerSingle("Cache-Control", cachecontrol);
/* 572*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder expires(Date date)
                {
/* 577*/            headerSingle("Expires", date);
/* 578*/            return this;
                }

                public transient javax.ws.rs.core.Response.ResponseBuilder cookie(NewCookie anewcookie[])
                {
/* 583*/            if(anewcookie != null)
                    {
/* 584*/                int i = (anewcookie = anewcookie).length;
/* 584*/                for(int j = 0; j < i; j++)
                        {
/* 584*/                    NewCookie newcookie = anewcookie[j];
/* 585*/                    header("Set-Cookie", newcookie);
                        }

                    } else
                    {
/* 588*/                header("Set-Cookie", null);
                    }
/* 590*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder header(String s, Object obj)
                {
/* 595*/            return header(s, obj, false);
                }

                private javax.ws.rs.core.Response.ResponseBuilder headerSingle(String s, Object obj)
                {
/* 599*/            return header(s, obj, true);
                }

                private javax.ws.rs.core.Response.ResponseBuilder header(String s, Object obj, boolean flag)
                {
/* 603*/            if(obj != null)
                    {
/* 604*/                if(flag)
/* 605*/                    context.getHeaders().putSingle(s, obj);
/* 607*/                else
/* 607*/                    context.getHeaders().add(s, obj);
                    } else
                    {
/* 610*/                context.getHeaders().remove(s);
                    }
/* 612*/            return this;
                }

                public transient javax.ws.rs.core.Response.ResponseBuilder variants(Variant avariant[])
                {
/* 617*/            return variants(Arrays.asList(avariant));
                }

                public transient javax.ws.rs.core.Response.ResponseBuilder links(Link alink[])
                {
/* 622*/            if(alink != null)
                    {
/* 623*/                int i = (alink = alink).length;
/* 623*/                for(int j = 0; j < i; j++)
                        {
/* 623*/                    Link link1 = alink[j];
/* 624*/                    header("Link", link1);
                        }

                    } else
                    {
/* 627*/                header("Link", null);
                    }
/* 629*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder link(URI uri, String s)
                {
/* 634*/            header("Link", Link.fromUri(uri).rel(s).build(new Object[0]));
/* 635*/            return this;
                }

                public javax.ws.rs.core.Response.ResponseBuilder link(String s, String s1)
                {
/* 640*/            header("Link", Link.fromUri(s).rel(s1).build(new Object[0]));
/* 641*/            return this;
                }

                public transient javax.ws.rs.core.Response.ResponseBuilder allow(String as[])
                {
/* 646*/            if(as == null || as.length == 1 && as[0] == null)
/* 647*/                return allow(((Set) (null)));
/* 649*/            else
/* 649*/                return allow(((Set) (new HashSet(Arrays.asList(as)))));
                }

                public javax.ws.rs.core.Response.ResponseBuilder allow(Set set)
                {
/* 655*/            if(set == null)
/* 656*/                return header("Allow", null, true);
/* 659*/            StringBuilder stringbuilder = new StringBuilder();
                    String s;
/* 660*/            for(set = set.iterator(); set.hasNext(); append(stringbuilder, true, s))
/* 660*/                s = (String)set.next();

/* 663*/            return header("Allow", stringbuilder, true);
                }

                public javax.ws.rs.core.Response.ResponseBuilder replaceAll(MultivaluedMap multivaluedmap)
                {
/* 668*/            context.replaceHeaders(multivaluedmap);
/* 669*/            return this;
                }

                public volatile Object clone()
                    throws CloneNotSupportedException
                {
/* 324*/            return clone();
                }

                private javax.ws.rs.core.Response.StatusType status;
                private final OutboundMessageContext context;
                private static final InheritableThreadLocal baseUriThreadLocal = new InheritableThreadLocal();


                public Builder(OutboundMessageContext outboundmessagecontext)
                {
/* 384*/            context = outboundmessagecontext;
                }
    }


            public static OutboundJaxrsResponse from(Response response)
            {
/*  94*/        if(response instanceof OutboundJaxrsResponse)
                {
/*  95*/            return (OutboundJaxrsResponse)response;
                } else
                {
/*  97*/            javax.ws.rs.core.Response.StatusType statustype = response.getStatusInfo();
                    OutboundMessageContext outboundmessagecontext;
/*  98*/            (outboundmessagecontext = new OutboundMessageContext()).getHeaders().putAll(response.getMetadata());
/* 100*/            outboundmessagecontext.setEntity(response.getEntity());
/* 101*/            return new OutboundJaxrsResponse(statustype, outboundmessagecontext);
                }
            }

            public OutboundJaxrsResponse(javax.ws.rs.core.Response.StatusType statustype, OutboundMessageContext outboundmessagecontext)
            {
/*  84*/        closed = false;
/*  85*/        buffered = false;
/* 112*/        status = statustype;
/* 113*/        context = outboundmessagecontext;
            }

            public OutboundMessageContext getContext()
            {
/* 122*/        return context;
            }

            public int getStatus()
            {
/* 127*/        return status.getStatusCode();
            }

            public javax.ws.rs.core.Response.StatusType getStatusInfo()
            {
/* 132*/        return status;
            }

            public Object getEntity()
            {
/* 137*/        if(closed)
/* 138*/            throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
/* 140*/        else
/* 140*/            return context.getEntity();
            }

            public Object readEntity(Class class1)
                throws ProcessingException
            {
/* 145*/        throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
            }

            public Object readEntity(GenericType generictype)
                throws ProcessingException
            {
/* 150*/        throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
            }

            public Object readEntity(Class class1, Annotation aannotation[])
                throws ProcessingException
            {
/* 155*/        throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
            }

            public Object readEntity(GenericType generictype, Annotation aannotation[])
                throws ProcessingException
            {
/* 160*/        throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
            }

            public boolean hasEntity()
            {
/* 165*/        if(closed)
/* 166*/            throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
/* 168*/        else
/* 168*/            return context.hasEntity();
            }

            public boolean bufferEntity()
                throws ProcessingException
            {
                InputStream inputstream;
                Object obj;
/* 173*/        if(closed)
/* 174*/            throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
/* 177*/        if(!context.hasEntity() || !java/io/InputStream.isAssignableFrom(context.getEntityClass()))
/* 178*/            return false;
/* 181*/        if(buffered)
/* 183*/            return true;
/* 185*/        inputstream = (InputStream)java/io/InputStream.cast(context.getEntity());
/* 186*/        obj = new ByteArrayOutputStream();
/* 187*/        byte abyte0[] = new byte[1024];
                int i;
/* 190*/        try
                {
/* 190*/            while((i = inputstream.read(abyte0)) != -1) 
/* 191*/                ((ByteArrayOutputStream) (obj)).write(abyte0, 0, i);
                }
                // Misplaced declaration of an exception variable
/* 193*/        catch(Object obj)
                {
/* 194*/            throw new ProcessingException(((Throwable) (obj)));
                }
/* 196*/        finally { }
/* 197*/        try
                {
/* 197*/            inputstream.close();
                }
                // Misplaced declaration of an exception variable
/* 198*/        catch(Object obj)
                {
/* 199*/            throw new ProcessingException(((Throwable) (obj)));
                }
/* 197*/        break MISSING_BLOCK_LABEL_154;
/* 197*/        try
                {
/* 197*/            inputstream.close();
                }
/* 198*/        catch(IOException ioexception)
                {
/* 199*/            throw new ProcessingException(ioexception);
                }
/* 199*/        throw obj;
/* 203*/        context.setEntity(new ByteArrayInputStream(((ByteArrayOutputStream) (obj)).toByteArray()));
/* 204*/        buffered = true;
/* 205*/        return true;
            }

            public void close()
                throws ProcessingException
            {
/* 210*/        closed = true;
/* 211*/        context.close();
/* 212*/        if(buffered)
                {
/* 214*/            context.setEntity(null);
/* 214*/            return;
                }
/* 215*/        if(context.hasEntity() && java/io/InputStream.isAssignableFrom(context.getEntityClass()))
/* 217*/            try
                    {
/* 217*/                ((InputStream)java/io/InputStream.cast(context.getEntity())).close();
/* 220*/                return;
                    }
/* 218*/            catch(IOException ioexception)
                    {
/* 219*/                throw new ProcessingException(ioexception);
                    }
/* 222*/        else
/* 222*/            return;
            }

            public MultivaluedMap getStringHeaders()
            {
/* 226*/        return context.getStringHeaders();
            }

            public String getHeaderString(String s)
            {
/* 231*/        return context.getHeaderString(s);
            }

            public MediaType getMediaType()
            {
/* 236*/        return context.getMediaType();
            }

            public Locale getLanguage()
            {
/* 241*/        return context.getLanguage();
            }

            public int getLength()
            {
/* 246*/        return context.getLength();
            }

            public Map getCookies()
            {
/* 251*/        return context.getResponseCookies();
            }

            public EntityTag getEntityTag()
            {
/* 256*/        return context.getEntityTag();
            }

            public Date getDate()
            {
/* 261*/        return context.getDate();
            }

            public Date getLastModified()
            {
/* 266*/        return context.getLastModified();
            }

            public Set getAllowedMethods()
            {
/* 271*/        return context.getAllowedMethods();
            }

            public URI getLocation()
            {
/* 276*/        return context.getLocation();
            }

            public Set getLinks()
            {
/* 281*/        return context.getLinks();
            }

            public boolean hasLink(String s)
            {
/* 286*/        return context.hasLink(s);
            }

            public Link getLink(String s)
            {
/* 291*/        return context.getLink(s);
            }

            public javax.ws.rs.core.Link.Builder getLinkBuilder(String s)
            {
/* 296*/        return context.getLinkBuilder(s);
            }

            public MultivaluedMap getMetadata()
            {
/* 302*/        return context.getHeaders();
            }

            public String toString()
            {
/* 307*/        return MoreObjects.toStringHelper(this).add("status", status.getStatusCode()).add("reason", status.getReasonPhrase()).add("hasEntity", Boolean.valueOf(context.hasEntity())).add("closed", Boolean.valueOf(closed)).add("buffered", Boolean.valueOf(buffered)).toString();
            }

            private final OutboundMessageContext context;
            private final javax.ws.rs.core.Response.StatusType status;
            private boolean closed;
            private boolean buffered;
}
