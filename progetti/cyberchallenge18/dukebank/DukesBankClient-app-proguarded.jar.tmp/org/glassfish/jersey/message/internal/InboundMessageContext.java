// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.ParseException;
import java.util.*;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.transform.Source;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableLanguageTag, AcceptableToken, HeaderUtils, HeaderValueException, 
//            HttpHeaderReader, LinkProvider, MediaTypes, ReaderWriter, 
//            EntityInputStream, LanguageTag

public abstract class InboundMessageContext
{
    static class EntityContent extends EntityInputStream
    {

                void setContent(InputStream inputstream, boolean flag)
                {
/* 134*/            buffered = flag;
/* 135*/            setWrappedStream(inputstream);
                }

                boolean hasContent()
                {
/* 139*/            return getWrappedStream() != InboundMessageContext.EMPTY;
                }

                boolean isBuffered()
                {
/* 143*/            return buffered;
                }

                public void close()
                {
/* 148*/            close(false);
                }

                void close(boolean flag)
                {
/* 152*/            if(buffered && !flag)
/* 153*/                return;
/* 156*/            super.close();
/* 158*/            buffered = false;
/* 159*/            setWrappedStream(null);
/* 160*/            return;
/* 158*/            flag;
/* 158*/            buffered = false;
/* 159*/            setWrappedStream(null);
/* 159*/            throw flag;
                }

                private boolean buffered;

                EntityContent()
                {
/* 130*/            super(InboundMessageContext.EMPTY);
                }
    }


            public InboundMessageContext()
            {
/* 168*/        this(false);
            }

            public InboundMessageContext(boolean flag)
            {
/* 179*/        headers = HeaderUtils.createInbound();
/* 180*/        entityContent = new EntityContent();
/* 181*/        translateNce = flag;
            }

            public InboundMessageContext header(String s, Object obj)
            {
/* 194*/        getHeaders().add(s, HeaderUtils.asString(obj, RuntimeDelegate.getInstance()));
/* 195*/        return this;
            }

            public transient InboundMessageContext headers(String s, Object aobj[])
            {
/* 206*/        getHeaders().addAll(s, HeaderUtils.asStringList(Arrays.asList(aobj), RuntimeDelegate.getInstance()));
/* 207*/        return this;
            }

            public InboundMessageContext headers(String s, Iterable iterable)
            {
/* 218*/        getHeaders().addAll(s, iterableToList(iterable));
/* 219*/        return this;
            }

            public InboundMessageContext headers(MultivaluedMap multivaluedmap)
            {
                java.util.Map.Entry entry;
/* 229*/        for(multivaluedmap = multivaluedmap.entrySet().iterator(); multivaluedmap.hasNext(); headers.addAll(entry.getKey(), (List)entry.getValue()))
/* 229*/            entry = (java.util.Map.Entry)multivaluedmap.next();

/* 232*/        return this;
            }

            public InboundMessageContext headers(Map map)
            {
                java.util.Map.Entry entry;
/* 242*/        for(map = map.entrySet().iterator(); map.hasNext(); headers.addAll(entry.getKey(), (List)entry.getValue()))
/* 242*/            entry = (java.util.Map.Entry)map.next();

/* 245*/        return this;
            }

            public InboundMessageContext remove(String s)
            {
/* 255*/        getHeaders().remove(s);
/* 256*/        return this;
            }

            private static List iterableToList(Iterable iterable)
            {
/* 260*/        LinkedList linkedlist = new LinkedList();
/* 262*/        RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
                Object obj;
/* 263*/        for(iterable = iterable.iterator(); iterable.hasNext(); linkedlist.add(HeaderUtils.asString(obj, runtimedelegate)))
/* 263*/            obj = iterable.next();

/* 267*/        return linkedlist;
            }

            public String getHeaderString(String s)
            {
/* 287*/        if((s = (List)headers.get(s)) == null)
/* 289*/            return null;
/* 291*/        if(s.isEmpty())
/* 292*/            return "";
/* 295*/        s = s.iterator();
/* 296*/        StringBuilder stringbuilder = new StringBuilder((String)s.next());
/* 297*/        for(; s.hasNext(); stringbuilder.append(',').append((String)s.next()));
/* 301*/        return stringbuilder.toString();
            }

            private Object singleHeader(String s, Function function, boolean flag)
            {
                Object obj;
/* 315*/        if((obj = (List)headers.get(s)) == null || ((List) (obj)).isEmpty())
/* 318*/            if(flag)
/* 318*/                return function.apply(null);
/* 318*/            else
/* 318*/                return null;
/* 320*/        if(((List) (obj)).size() > 1)
/* 321*/            throw new HeaderValueException(LocalizationMessages.TOO_MANY_HEADER_VALUES(s, obj.toString()), HeaderValueException.Context.INBOUND);
/* 325*/        if((obj = ((List) (obj)).get(0)) == null)
/* 327*/            if(flag)
/* 327*/                return function.apply(null);
/* 327*/            else
/* 327*/                return null;
/* 331*/        return function.apply(HeaderUtils.asString(obj, null));
/* 332*/        function;
/* 333*/        throw exception(s, obj, function);
            }

            private static HeaderValueException exception(String s, Object obj, Exception exception1)
            {
/* 338*/        return new HeaderValueException(LocalizationMessages.UNABLE_TO_PARSE_HEADER_VALUE(s, obj), exception1, HeaderValueException.Context.INBOUND);
            }

            public MultivaluedMap getHeaders()
            {
/* 348*/        return headers;
            }

            public Date getDate()
            {
/* 357*/        return (Date)singleHeader("Date", new Function() {

                    public Date apply(String s)
                    {
/* 361*/                return HttpHeaderReader.readDate(s);
/* 362*/                s;
/* 363*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 357*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 357*/                this$0 = InboundMessageContext.this;
/* 357*/                super();
                    }
        }, false);
            }

            public Set getIfMatch()
            {
                String s;
/* 375*/        if((s = getHeaderString("If-Match")) == null || s.isEmpty())
/* 377*/            return null;
/* 380*/        return HttpHeaderReader.readMatchingEntityTag(s);
                ParseException parseexception;
/* 381*/        parseexception;
/* 382*/        throw exception("If-Match", s, parseexception);
            }

            public Set getIfNoneMatch()
            {
                String s;
/* 392*/        if((s = getHeaderString("If-None-Match")) == null || s.isEmpty())
/* 394*/            return null;
/* 397*/        return HttpHeaderReader.readMatchingEntityTag(s);
                ParseException parseexception;
/* 398*/        parseexception;
/* 399*/        throw exception("If-None-Match", s, parseexception);
            }

            public Locale getLanguage()
            {
/* 409*/        return (Locale)singleHeader("Content-Language", new Function() {

                    public Locale apply(String s)
                    {
/* 413*/                return (new LanguageTag(s)).getAsLocale();
/* 414*/                s;
/* 415*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 409*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 409*/                this$0 = InboundMessageContext.this;
/* 409*/                super();
                    }
        }, false);
            }

            public int getLength()
            {
/* 427*/        return ((Integer)singleHeader("Content-Length", new Function() {

                    public Integer apply(String s)
                    {
/* 431*/                return Integer.valueOf(s == null || s.isEmpty() ? -1 : Integer.parseInt(s));
/* 432*/                s;
/* 433*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 427*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 427*/                this$0 = InboundMessageContext.this;
/* 427*/                super();
                    }
        }, true)).intValue();
            }

            public MediaType getMediaType()
            {
/* 446*/        return (MediaType)singleHeader("Content-Type", new Function() {

                    public MediaType apply(String s)
                    {
/* 450*/                return MediaType.valueOf(s);
/* 451*/                s;
/* 452*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 446*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 446*/                this$0 = InboundMessageContext.this;
/* 446*/                super();
                    }
        }, false);
            }

            public List getQualifiedAcceptableMediaTypes()
            {
                String s;
/* 465*/        if((s = getHeaderString("Accept")) == null || s.isEmpty())
/* 468*/            return WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
/* 472*/        return Collections.unmodifiableList(HttpHeaderReader.readAcceptMediaType(s));
                ParseException parseexception;
/* 473*/        parseexception;
/* 474*/        throw exception("Accept", s, parseexception);
            }

            public List getQualifiedAcceptableLanguages()
            {
                String s;
/* 485*/        if((s = getHeaderString("Accept-Language")) == null || s.isEmpty())
/* 488*/            return Collections.singletonList(new AcceptableLanguageTag("*", null));
/* 492*/        return Collections.unmodifiableList(HttpHeaderReader.readAcceptLanguage(s));
                ParseException parseexception;
/* 493*/        parseexception;
/* 494*/        throw exception("Accept-Language", s, parseexception);
            }

            public List getQualifiedAcceptCharset()
            {
/* 505*/        String s = getHeaderString("Accept-Charset");
/* 507*/        if(s == null || s.isEmpty())
/* 508*/            return Collections.singletonList(new AcceptableToken("*"));
/* 510*/        return HttpHeaderReader.readAcceptToken(s);
                ParseException parseexception;
/* 511*/        parseexception;
/* 512*/        throw exception("Accept-Charset", s, parseexception);
            }

            public List getQualifiedAcceptEncoding()
            {
/* 523*/        String s = getHeaderString("Accept-Encoding");
/* 525*/        if(s == null || s.isEmpty())
/* 526*/            return Collections.singletonList(new AcceptableToken("*"));
/* 528*/        return HttpHeaderReader.readAcceptToken(s);
                ParseException parseexception;
/* 529*/        parseexception;
/* 530*/        throw exception("Accept-Encoding", s, parseexception);
            }

            public Map getRequestCookies()
            {
                Object obj;
/* 540*/        if((obj = (List)headers.get("Cookie")) == null || ((List) (obj)).isEmpty())
/* 542*/            return Collections.emptyMap();
/* 545*/        HashMap hashmap = new HashMap();
/* 546*/        obj = ((List) (obj)).iterator();
/* 546*/        do
                {
/* 546*/            if(!((Iterator) (obj)).hasNext())
/* 546*/                break;
                    String s;
/* 546*/            if((s = (String)((Iterator) (obj)).next()) != null)
/* 548*/                hashmap.putAll(HttpHeaderReader.readCookies(s));
                } while(true);
/* 551*/        return hashmap;
            }

            public Set getAllowedMethods()
            {
                String s;
/* 561*/        if((s = getHeaderString("Allow")) == null || s.isEmpty())
/* 563*/            return Collections.emptySet();
/* 566*/        return new HashSet(HttpHeaderReader.readStringList(s.toUpperCase()));
                ParseException parseexception;
/* 567*/        parseexception;
/* 568*/        throw exception("Allow", s, parseexception);
            }

            public Map getResponseCookies()
            {
                Object obj;
/* 578*/        if((obj = (List)headers.get("Set-Cookie")) == null || ((List) (obj)).isEmpty())
/* 580*/            return Collections.emptyMap();
/* 583*/        HashMap hashmap = new HashMap();
/* 584*/        obj = ((List) (obj)).iterator();
/* 584*/        do
                {
/* 584*/            if(!((Iterator) (obj)).hasNext())
/* 584*/                break;
                    Object obj1;
/* 584*/            if((obj1 = (String)((Iterator) (obj)).next()) != null)
                    {
/* 586*/                obj1 = HttpHeaderReader.readNewCookie(((String) (obj1)));
/* 587*/                hashmap.put(((NewCookie) (obj1)).getName(), obj1);
                    }
                } while(true);
/* 590*/        return hashmap;
            }

            public EntityTag getEntityTag()
            {
/* 599*/        return (EntityTag)singleHeader("ETag", new Function() {

                    public EntityTag apply(String s)
                    {
/* 602*/                return EntityTag.valueOf(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 599*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 599*/                this$0 = InboundMessageContext.this;
/* 599*/                super();
                    }
        }, false);
            }

            public Date getLastModified()
            {
/* 613*/        return (Date)singleHeader("Last-Modified", new Function() {

                    public Date apply(String s)
                    {
/* 617*/                return HttpHeaderReader.readDate(s);
/* 618*/                s;
/* 619*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 613*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 613*/                this$0 = InboundMessageContext.this;
/* 613*/                super();
                    }
        }, false);
            }

            public URI getLocation()
            {
/* 631*/        return (URI)singleHeader("Location", new Function() {

                    public URI apply(String s)
                    {
/* 635*/                return URI.create(s);
/* 636*/                s;
/* 637*/                throw new ProcessingException(s);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 631*/                return apply((String)obj);
                    }

                    final InboundMessageContext this$0;

                    
                    {
/* 631*/                this$0 = InboundMessageContext.this;
/* 631*/                super();
                    }
        }, false);
            }

            public Set getLinks()
            {
                List list;
/* 650*/        if((list = (List)headers.get("Link")) == null || list.isEmpty())
/* 652*/            return Collections.emptySet();
                HashSet hashset;
/* 656*/        hashset = new HashSet(list.size());
/* 658*/        Iterator iterator = list.iterator();
/* 658*/        do
                {
/* 658*/            if(!iterator.hasNext())
/* 658*/                break;
/* 658*/            Object obj = (String)iterator.next();
/* 659*/            StringBuilder stringbuilder = new StringBuilder();
/* 660*/            obj = new StringTokenizer(((String) (obj)), "<>,", true);
/* 661*/            boolean flag = false;
/* 662*/            do
                    {
/* 662*/                if(!((StringTokenizer) (obj)).hasMoreTokens())
/* 663*/                    break;
                        String s;
/* 663*/                if((s = ((StringTokenizer) (obj)).nextToken()).equals("<"))
/* 665*/                    flag = true;
/* 666*/                else
/* 666*/                if(s.equals(">"))
/* 667*/                    flag = false;
/* 668*/                else
/* 668*/                if(!flag && s.equals(","))
                        {
/* 669*/                    hashset.add(Link.valueOf(stringbuilder.toString().trim()));
/* 670*/                    stringbuilder = new StringBuilder();
/* 671*/                    continue;
                        }
/* 674*/                stringbuilder.append(s);
                    } while(true);
/* 677*/            if(stringbuilder.length() > 0)
/* 678*/                hashset.add(Link.valueOf(stringbuilder.toString().trim()));
                } while(true);
/* 681*/        return hashset;
                IllegalArgumentException illegalargumentexception;
/* 682*/        illegalargumentexception;
/* 683*/        throw exception("Link", list, illegalargumentexception);
            }

            public boolean hasLink(String s)
            {
                Object obj;
/* 695*/        for(Iterator iterator = getLinks().iterator(); iterator.hasNext();)
/* 695*/            if((obj = LinkProvider.getLinkRelations(((Link) (obj = (Link)iterator.next())).getRel())) != null && ((List) (obj)).contains(s))
/* 699*/                return true;

/* 702*/        return false;
            }

            public Link getLink(String s)
            {
                Link link;
                List list;
/* 712*/        for(Iterator iterator = getLinks().iterator(); iterator.hasNext();)
/* 712*/            if((list = LinkProvider.getLinkRelations((link = (Link)iterator.next()).getRel())) != null && list.contains(s))
/* 715*/                return link;

/* 718*/        return null;
            }

            public javax.ws.rs.core.Link.Builder getLinkBuilder(String s)
            {
/* 730*/        if((s = getLink(s)) == null)
/* 732*/            return null;
/* 735*/        else
/* 735*/            return Link.fromLink(s);
            }

            public MessageBodyWorkers getWorkers()
            {
/* 746*/        return workers;
            }

            public void setWorkers(MessageBodyWorkers messagebodyworkers)
            {
/* 755*/        workers = messagebodyworkers;
            }

            public boolean hasEntity()
            {
/* 769*/        entityContent.ensureNotClosed();
/* 772*/        if(!entityContent.isEmpty())
/* 772*/            return true;
/* 772*/        return false;
/* 773*/        JVM INSTR pop ;
/* 775*/        return false;
            }

            public InputStream getEntityStream()
            {
/* 785*/        entityContent.ensureNotClosed();
/* 787*/        return entityContent.getWrappedStream();
            }

            public void setEntityStream(InputStream inputstream)
            {
/* 796*/        entityContent.setContent(inputstream, false);
            }

            public Object readEntity(Class class1, PropertiesDelegate propertiesdelegate)
            {
/* 808*/        return readEntity(class1, ((Type) (class1)), EMPTY_ANNOTATIONS, propertiesdelegate);
            }

            public Object readEntity(Class class1, Annotation aannotation[], PropertiesDelegate propertiesdelegate)
            {
/* 821*/        return readEntity(class1, ((Type) (class1)), aannotation, propertiesdelegate);
            }

            public Object readEntity(Class class1, Type type, PropertiesDelegate propertiesdelegate)
            {
/* 834*/        return readEntity(class1, type, EMPTY_ANNOTATIONS, propertiesdelegate);
            }

            public Object readEntity(Class class1, Type type, Annotation aannotation[], PropertiesDelegate propertiesdelegate)
            {
                boolean flag;
/* 849*/        if(flag = entityContent.isBuffered())
/* 851*/            entityContent.reset();
/* 854*/        entityContent.ensureNotClosed();
/* 865*/        if(workers == null)
/* 866*/            return null;
                MediaType mediatype;
/* 869*/        mediatype = (mediatype = getMediaType()) != null ? mediatype : MediaType.APPLICATION_OCTET_STREAM_TYPE;
/* 872*/        flag = !flag;
/* 874*/        try
                {
/* 874*/            class1 = ((Class) (workers.readFrom(class1, type, aannotation, mediatype, headers, propertiesdelegate, entityContent.getWrappedStream(), ((Iterable) (entityContent.hasContent() ? getReaderInterceptors() : ((Iterable) (Collections.emptyList())))), translateNce)));
/* 885*/            flag = flag && !(class1 instanceof Closeable) && !(class1 instanceof Source);
/* 887*/            class1 = class1;
                }
                // Misplaced declaration of an exception variable
/* 888*/        catch(Class class1)
                {
/* 889*/            throw new ProcessingException(LocalizationMessages.ERROR_READING_ENTITY_FROM_INPUT_STREAM(), class1);
                }
/* 891*/        finally
                {
/* 891*/            if(!flag) goto _L0; else goto _L0
                }
/* 891*/        if(flag)
/* 896*/            ReaderWriter.safelyClose(entityContent);
/* 896*/        return class1;
/* 896*/        ReaderWriter.safelyClose(entityContent);
/* 896*/        throw class1;
            }

            public boolean bufferEntity()
                throws ProcessingException
            {
/* 908*/        entityContent.ensureNotClosed();
/* 911*/        if(entityContent.isBuffered() || !entityContent.hasContent())
/* 912*/            return true;
                InputStream inputstream;
                Object obj;
/* 915*/        inputstream = entityContent.getWrappedStream();
/* 916*/        obj = new ByteArrayOutputStream();
/* 918*/        ReaderWriter.writeTo(inputstream, ((java.io.OutputStream) (obj)));
/* 924*/        ReaderWriter.safelyClose(inputstream);
/* 925*/        break MISSING_BLOCK_LABEL_64;
/* 924*/        obj;
/* 924*/        ReaderWriter.safelyClose(inputstream);
/* 924*/        throw obj;
/* 927*/        entityContent.setContent(new ByteArrayInputStream(((ByteArrayOutputStream) (obj)).toByteArray()), true);
/* 929*/        return true;
                IOException ioexception;
/* 930*/        ioexception;
/* 931*/        throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_BUFFERING_FAILED(), ioexception);
            }

            public void close()
            {
/* 939*/        entityContent.close(true);
            }

            protected abstract Iterable getReaderInterceptors();

            private static final InputStream EMPTY = new InputStream() {

                public final int read()
                    throws IOException
                {
/*  94*/            return -1;
                }

                public final void mark(int i)
                {
                }

                public final void reset()
                    throws IOException
                {
                }

                public final boolean markSupported()
                {
/* 109*/            return true;
                }

    };
            private static final Annotation EMPTY_ANNOTATIONS[] = new Annotation[0];
            private static final List WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
            private final MultivaluedMap headers;
            private final EntityContent entityContent;
            private final boolean translateNce;
            private MessageBodyWorkers workers;

            static 
            {
/* 113*/        WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST = Collections.singletonList(MediaTypes.WILDCARD_ACCEPTABLE_TYPE);
            }

}
