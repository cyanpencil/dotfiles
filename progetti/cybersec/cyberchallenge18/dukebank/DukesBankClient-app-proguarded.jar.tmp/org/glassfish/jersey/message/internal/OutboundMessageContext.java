// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.RuntimeDelegate;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.Collections2;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableLanguageTag, AcceptableMediaType, CommittingOutputStream, HeaderUtils, 
//            HeaderValueException, HttpHeaderReader, LinkProvider, MediaTypes, 
//            LanguageTag

public class OutboundMessageContext
{
    public static interface StreamProvider
    {

        public abstract OutputStream getOutputStream(int i)
            throws IOException;
    }


            public OutboundMessageContext()
            {
/*  96*/        entityAnnotations = EMPTY_ANNOTATIONS;
/* 126*/        headers = HeaderUtils.createOutbound();
/* 127*/        committingOutputStream = new CommittingOutputStream();
/* 128*/        entityStream = committingOutputStream;
            }

            public OutboundMessageContext(OutboundMessageContext outboundmessagecontext)
            {
/*  96*/        entityAnnotations = EMPTY_ANNOTATIONS;
/* 138*/        headers = HeaderUtils.createOutbound();
/* 139*/        headers.putAll(outboundmessagecontext.headers);
/* 140*/        committingOutputStream = new CommittingOutputStream();
/* 141*/        entityStream = committingOutputStream;
/* 143*/        entity = outboundmessagecontext.entity;
/* 144*/        entityType = outboundmessagecontext.entityType;
/* 145*/        entityAnnotations = outboundmessagecontext.entityAnnotations;
            }

            public void replaceHeaders(MultivaluedMap multivaluedmap)
            {
/* 154*/        getHeaders().clear();
/* 155*/        if(multivaluedmap != null)
/* 156*/            getHeaders().putAll(multivaluedmap);
            }

            public MultivaluedMap getStringHeaders()
            {
/* 167*/        return HeaderUtils.asStringHeaders(headers);
            }

            public String getHeaderString(String s)
            {
/* 187*/        return HeaderUtils.asHeaderString((List)headers.get(s), RuntimeDelegate.getInstance());
            }

            private Object singleHeader(String s, Class class1, Function function, boolean flag)
            {
                Object obj;
/* 203*/        if((obj = (List)headers.get(s)) == null || ((List) (obj)).isEmpty())
/* 206*/            if(flag)
/* 206*/                return function.apply(null);
/* 206*/            else
/* 206*/                return null;
/* 208*/        if(((List) (obj)).size() > 1)
/* 209*/            throw new HeaderValueException(LocalizationMessages.TOO_MANY_HEADER_VALUES(s, obj.toString()), HeaderValueException.Context.OUTBOUND);
/* 213*/        if((obj = ((List) (obj)).get(0)) == null)
/* 215*/            if(flag)
/* 215*/                return function.apply(null);
/* 215*/            else
/* 215*/                return null;
/* 218*/        if(class1.isInstance(obj))
/* 219*/            return class1.cast(obj);
/* 222*/        return function.apply(HeaderUtils.asString(obj, null));
/* 223*/        class1;
/* 224*/        throw exception(s, obj, class1);
            }

            private static HeaderValueException exception(String s, Object obj, Exception exception1)
            {
/* 230*/        return new HeaderValueException(LocalizationMessages.UNABLE_TO_PARSE_HEADER_VALUE(s, obj), exception1, HeaderValueException.Context.OUTBOUND);
            }

            public MultivaluedMap getHeaders()
            {
/* 240*/        return headers;
            }

            public Date getDate()
            {
/* 249*/        return (Date)singleHeader("Date", java/util/Date, new Function() {

                    public Date apply(String s)
                    {
/* 253*/                return HttpHeaderReader.readDate(s);
/* 254*/                s;
/* 255*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 249*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 249*/                this$0 = OutboundMessageContext.this;
/* 249*/                super();
                    }
        }, false);
            }

            public Locale getLanguage()
            {
/* 267*/        return (Locale)singleHeader("Content-Language", java/util/Locale, new Function() {

                    public Locale apply(String s)
                    {
/* 271*/                return (new LanguageTag(s)).getAsLocale();
/* 272*/                s;
/* 273*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 267*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 267*/                this$0 = OutboundMessageContext.this;
/* 267*/                super();
                    }
        }, false);
            }

            public MediaType getMediaType()
            {
/* 286*/        return (MediaType)singleHeader("Content-Type", javax/ws/rs/core/MediaType, new Function() {

                    public MediaType apply(String s)
                    {
/* 289*/                return MediaType.valueOf(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 286*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 286*/                this$0 = OutboundMessageContext.this;
/* 286*/                super();
                    }
        }, false);
            }

            public List getAcceptableMediaTypes()
            {
                Object obj;
/* 302*/        if((obj = (List)headers.get("Accept")) == null || ((List) (obj)).isEmpty())
/* 305*/            return WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
/* 307*/        ArrayList arraylist = new ArrayList(((List) (obj)).size());
/* 308*/        RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 309*/        boolean flag = false;
/* 310*/        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                {
/* 310*/            Object obj1 = ((Iterator) (obj)).next();
/* 312*/            try
                    {
/* 312*/                if(obj1 instanceof MediaType)
                        {
                            AcceptableMediaType acceptablemediatype;
/* 313*/                    flag = (acceptablemediatype = AcceptableMediaType.valueOf((MediaType)obj1)) != obj1;
/* 315*/                    arraylist.add(acceptablemediatype);
                        } else
                        {
/* 317*/                    flag = true;
/* 318*/                    arraylist.addAll(HttpHeaderReader.readAcceptMediaType(HeaderUtils.asString(obj1, runtimedelegate)));
                        }
                    }
/* 320*/            catch(ParseException parseexception)
                    {
/* 321*/                throw exception("Accept", obj1, parseexception);
                    }
                }

/* 325*/        if(flag)
/* 327*/            headers.put("Accept", Lists.transform(arraylist, new Function() {

                        public Object apply(MediaType mediatype)
                        {
/* 330*/                    return mediatype;
                        }

                        public volatile Object apply(Object obj2)
                        {
/* 327*/                    return apply((MediaType)obj2);
                        }

                        final OutboundMessageContext this$0;

                    
                    {
/* 327*/                this$0 = OutboundMessageContext.this;
/* 327*/                super();
                    }
            }));
/* 335*/        return Collections.unmodifiableList(arraylist);
            }

            public List getAcceptableLanguages()
            {
                Object obj;
/* 345*/        if((obj = (List)headers.get("Accept-Language")) == null || ((List) (obj)).isEmpty())
/* 348*/            return Collections.singletonList((new AcceptableLanguageTag("*", null)).getAsLocale());
/* 351*/        ArrayList arraylist = new ArrayList(((List) (obj)).size());
/* 352*/        RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 353*/        boolean flag = false;
                Object obj1;
/* 354*/        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
/* 354*/            if((obj1 = ((Iterator) (obj)).next()) instanceof Locale)
                    {
/* 356*/                arraylist.add((Locale)obj1);
                    } else
                    {
/* 358*/                flag = true;
/* 360*/                try
                        {
/* 360*/                    arraylist.addAll(Lists.transform(HttpHeaderReader.readAcceptLanguage(HeaderUtils.asString(obj1, runtimedelegate)), new Function() {

                                public Locale apply(AcceptableLanguageTag acceptablelanguagetag)
                                {
/* 365*/                            return acceptablelanguagetag.getAsLocale();
                                }

                                public volatile Object apply(Object obj2)
                                {
/* 361*/                            return apply((AcceptableLanguageTag)obj2);
                                }

                                final OutboundMessageContext this$0;

                    
                    {
/* 361*/                this$0 = OutboundMessageContext.this;
/* 361*/                super();
                    }
                    }));
                        }
/* 368*/                catch(ParseException parseexception)
                        {
/* 369*/                    throw exception("Accept-Language", obj1, parseexception);
                        }
                    }

/* 374*/        if(flag)
/* 376*/            headers.put("Accept-Language", Lists.transform(arraylist, new Function() {

                        public Object apply(Locale locale)
                        {
/* 379*/                    return locale;
                        }

                        public volatile Object apply(Object obj2)
                        {
/* 376*/                    return apply((Locale)obj2);
                        }

                        final OutboundMessageContext this$0;

                    
                    {
/* 376*/                this$0 = OutboundMessageContext.this;
/* 376*/                super();
                    }
            }));
/* 384*/        return Collections.unmodifiableList(arraylist);
            }

            public Map getRequestCookies()
            {
                Object obj;
/* 393*/        if((obj = (List)headers.get("Cookie")) == null || ((List) (obj)).isEmpty())
/* 395*/            return Collections.emptyMap();
/* 398*/        HashMap hashmap = new HashMap();
/* 399*/        obj = HeaderUtils.asStringList(((List) (obj)), RuntimeDelegate.getInstance()).iterator();
/* 399*/        do
                {
/* 399*/            if(!((Iterator) (obj)).hasNext())
/* 399*/                break;
                    String s;
/* 399*/            if((s = (String)((Iterator) (obj)).next()) != null)
/* 401*/                hashmap.putAll(HttpHeaderReader.readCookies(s));
                } while(true);
/* 404*/        return hashmap;
            }

            public Set getAllowedMethods()
            {
                String s;
/* 414*/        if((s = getHeaderString("Allow")) == null || s.isEmpty())
/* 416*/            return Collections.emptySet();
/* 419*/        return new HashSet(HttpHeaderReader.readStringList(s));
                ParseException parseexception;
/* 420*/        parseexception;
/* 421*/        throw exception("Allow", s, parseexception);
            }

            public int getLength()
            {
/* 432*/        return ((Integer)singleHeader("Content-Length", java/lang/Integer, new Function() {

                    public Integer apply(String s)
                    {
/* 436*/                return Integer.valueOf(s == null || s.isEmpty() ? -1 : Integer.parseInt(s));
/* 437*/                s;
/* 438*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 432*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 432*/                this$0 = OutboundMessageContext.this;
/* 432*/                super();
                    }
        }, true)).intValue();
            }

            public Map getResponseCookies()
            {
                Object obj;
/* 450*/        if((obj = (List)headers.get("Set-Cookie")) == null || ((List) (obj)).isEmpty())
/* 452*/            return Collections.emptyMap();
/* 455*/        HashMap hashmap = new HashMap();
/* 456*/        obj = HeaderUtils.asStringList(((List) (obj)), RuntimeDelegate.getInstance()).iterator();
/* 456*/        do
                {
/* 456*/            if(!((Iterator) (obj)).hasNext())
/* 456*/                break;
                    Object obj1;
/* 456*/            if((obj1 = (String)((Iterator) (obj)).next()) != null)
                    {
/* 458*/                obj1 = HttpHeaderReader.readNewCookie(((String) (obj1)));
/* 459*/                hashmap.put(((NewCookie) (obj1)).getName(), obj1);
                    }
                } while(true);
/* 462*/        return hashmap;
            }

            public EntityTag getEntityTag()
            {
/* 471*/        return (EntityTag)singleHeader("ETag", javax/ws/rs/core/EntityTag, new Function() {

                    public EntityTag apply(String s)
                    {
/* 475*/                if(s == null)
/* 475*/                    return null;
/* 475*/                return EntityTag.valueOf(s);
/* 476*/                s;
/* 477*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 471*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 471*/                this$0 = OutboundMessageContext.this;
/* 471*/                super();
                    }
        }, false);
            }

            public Date getLastModified()
            {
/* 489*/        return (Date)singleHeader("Last-Modified", java/util/Date, new Function() {

                    public Date apply(String s)
                    {
/* 493*/                return HttpHeaderReader.readDate(s);
/* 494*/                s;
/* 495*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 489*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 489*/                this$0 = OutboundMessageContext.this;
/* 489*/                super();
                    }
        }, false);
            }

            public URI getLocation()
            {
/* 507*/        return (URI)singleHeader("Location", java/net/URI, new Function() {

                    public URI apply(String s)
                    {
/* 511*/                if(s == null)
/* 511*/                    return null;
/* 511*/                return URI.create(s);
/* 512*/                s;
/* 513*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 507*/                return apply((String)obj);
                    }

                    final OutboundMessageContext this$0;

                    
                    {
/* 507*/                this$0 = OutboundMessageContext.this;
/* 507*/                super();
                    }
        }, false);
            }

            public Set getLinks()
            {
                Object obj;
/* 526*/        if((obj = (List)headers.get("Link")) == null || ((List) (obj)).isEmpty())
/* 528*/            return Collections.emptySet();
/* 531*/        HashSet hashset = new HashSet(((List) (obj)).size());
/* 532*/        RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 533*/        boolean flag = false;
                Object obj1;
/* 534*/        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
/* 534*/            if((obj1 = ((Iterator) (obj)).next()) instanceof Link)
                    {
/* 536*/                hashset.add((Link)obj1);
                    } else
                    {
/* 538*/                flag = true;
/* 540*/                try
                        {
/* 540*/                    hashset.add(Link.valueOf(HeaderUtils.asString(obj1, runtimedelegate)));
                        }
/* 541*/                catch(IllegalArgumentException illegalargumentexception)
                        {
/* 542*/                    throw exception("Link", obj1, illegalargumentexception);
                        }
                    }

/* 547*/        if(flag)
/* 549*/            headers.put("Link", new ArrayList(Collections2.transform(hashset, new Function() {

                        public Object apply(Link link)
                        {
/* 552*/                    return link;
                        }

                        public volatile Object apply(Object obj2)
                        {
/* 549*/                    return apply((Link)obj2);
                        }

                        final OutboundMessageContext this$0;

                    
                    {
/* 549*/                this$0 = OutboundMessageContext.this;
/* 549*/                super();
                    }
            })));
/* 557*/        return Collections.unmodifiableSet(hashset);
            }

            public boolean hasLink(String s)
            {
                Object obj;
/* 568*/        for(Iterator iterator = getLinks().iterator(); iterator.hasNext();)
/* 568*/            if((obj = LinkProvider.getLinkRelations(((Link) (obj = (Link)iterator.next())).getRel())) != null && ((List) (obj)).contains(s))
/* 571*/                return true;

/* 574*/        return false;
            }

            public Link getLink(String s)
            {
                Link link;
                List list;
/* 584*/        for(Iterator iterator = getLinks().iterator(); iterator.hasNext();)
/* 584*/            if((list = LinkProvider.getLinkRelations((link = (Link)iterator.next()).getRel())) != null && list.contains(s))
/* 587*/                return link;

/* 590*/        return null;
            }

            public javax.ws.rs.core.Link.Builder getLinkBuilder(String s)
            {
/* 602*/        if((s = getLink(s)) == null)
/* 604*/            return null;
/* 607*/        else
/* 607*/            return Link.fromLink(s);
            }

            public boolean hasEntity()
            {
/* 622*/        return entity != null;
            }

            public Object getEntity()
            {
/* 634*/        return entity;
            }

            public void setEntity(Object obj)
            {
/* 644*/        setEntity(obj, ReflectionHelper.genericTypeFor(obj));
            }

            public void setEntity(Object obj, Annotation aannotation[])
            {
/* 655*/        setEntity(obj, ReflectionHelper.genericTypeFor(obj));
/* 656*/        setEntityAnnotations(aannotation);
            }

            private void setEntity(Object obj, GenericType generictype)
            {
/* 667*/        if(obj instanceof GenericEntity)
/* 668*/            entity = ((GenericEntity)obj).getEntity();
/* 670*/        else
/* 670*/            entity = obj;
/* 673*/        entityType = generictype;
            }

            public void setEntity(Object obj, Type type, Annotation aannotation[])
            {
/* 685*/        setEntity(obj, new GenericType(type));
/* 686*/        setEntityAnnotations(aannotation);
            }

            public void setEntity(Object obj, Annotation aannotation[], MediaType mediatype)
            {
/* 698*/        setEntity(obj, aannotation);
/* 699*/        setMediaType(mediatype);
            }

            public void setMediaType(MediaType mediatype)
            {
/* 708*/        headers.putSingle("Content-Type", mediatype);
            }

            public Class getEntityClass()
            {
/* 717*/        if(entityType == null)
/* 717*/            return null;
/* 717*/        else
/* 717*/            return entityType.getRawType();
            }

            public Type getEntityType()
            {
/* 726*/        if(entityType == null)
/* 726*/            return null;
/* 726*/        else
/* 726*/            return entityType.getType();
            }

            public void setEntityType(Type type)
            {
/* 737*/        entityType = new GenericType(type);
            }

            public Annotation[] getEntityAnnotations()
            {
/* 746*/        return (Annotation[])entityAnnotations.clone();
            }

            public void setEntityAnnotations(Annotation aannotation[])
            {
/* 755*/        entityAnnotations = aannotation != null ? aannotation : EMPTY_ANNOTATIONS;
            }

            public OutputStream getEntityStream()
            {
/* 764*/        return entityStream;
            }

            public void setEntityStream(OutputStream outputstream)
            {
/* 773*/        entityStream = outputstream;
            }

            public void enableBuffering(Configuration configuration)
            {
/* 786*/        if((configuration = (Integer)CommonProperties.getValue(configuration.getProperties(), configuration.getRuntimeType(), "jersey.config.contentLength.buffer", java/lang/Integer)) != null)
                {
/* 789*/            committingOutputStream.enableBuffering(configuration.intValue());
/* 789*/            return;
                } else
                {
/* 791*/            committingOutputStream.enableBuffering();
/* 793*/            return;
                }
            }

            public void setStreamProvider(StreamProvider streamprovider)
            {
/* 803*/        committingOutputStream.setStreamProvider(streamprovider);
            }

            public void commitStream()
                throws IOException
            {
/* 813*/        if(!committingOutputStream.isCommitted())
                {
/* 814*/            entityStream.flush();
/* 815*/            if(!committingOutputStream.isCommitted())
                    {
/* 816*/                committingOutputStream.commit();
/* 817*/                committingOutputStream.flush();
                    }
                }
            }

            public boolean isCommitted()
            {
/* 828*/        return committingOutputStream.isCommitted();
            }

            public void close()
            {
/* 835*/        if(!hasEntity())
/* 837*/            break MISSING_BLOCK_LABEL_161;
                OutputStream outputstream;
/* 837*/        (outputstream = getEntityStream()).flush();
/* 839*/        outputstream.close();
/* 849*/        if(!committingOutputStream.isClosed())
                {
/* 851*/            try
                    {
/* 851*/                committingOutputStream.close();
/* 855*/                return;
                    }
/* 852*/            catch(IOException ioexception)
                    {
/* 854*/                Logger.getLogger(org/glassfish/jersey/message/internal/OutboundMessageContext.getName()).log(Level.FINE, ioexception.getMessage(), ioexception);
                    }
/* 855*/            return;
                }
/* 840*/        break MISSING_BLOCK_LABEL_161;
                Object obj;
/* 840*/        obj;
/* 845*/        Logger.getLogger(org/glassfish/jersey/message/internal/OutboundMessageContext.getName()).log(Level.FINE, ((IOException) (obj)).getMessage(), ((Throwable) (obj)));
/* 849*/        if(!committingOutputStream.isClosed())
                {
/* 851*/            try
                    {
/* 851*/                committingOutputStream.close();
/* 855*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 852*/            catch(Object obj)
                    {
/* 854*/                Logger.getLogger(org/glassfish/jersey/message/internal/OutboundMessageContext.getName()).log(Level.FINE, ((IOException) (obj)).getMessage(), ((Throwable) (obj)));
                    }
/* 855*/            return;
                }
/* 849*/        break MISSING_BLOCK_LABEL_161;
/* 849*/        obj;
/* 849*/        if(!committingOutputStream.isClosed())
/* 851*/            try
                    {
/* 851*/                committingOutputStream.close();
                    }
/* 852*/            catch(IOException ioexception1)
                    {
/* 854*/                Logger.getLogger(org/glassfish/jersey/message/internal/OutboundMessageContext.getName()).log(Level.FINE, ioexception1.getMessage(), ioexception1);
                    }
/* 855*/        throw obj;
            }

            private static final Annotation EMPTY_ANNOTATIONS[] = new Annotation[0];
            private static final List WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
            private final MultivaluedMap headers;
            private final CommittingOutputStream committingOutputStream;
            private Object entity;
            private GenericType entityType;
            private Annotation entityAnnotations[];
            private OutputStream entityStream;

            static 
            {
/*  88*/        WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST = Collections.singletonList(MediaTypes.WILDCARD_ACCEPTABLE_TYPE);
            }
}
