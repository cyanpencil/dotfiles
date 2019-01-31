// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheControl.java

package javax.ws.rs.core;

import java.util.*;
import javax.ws.rs.ext.RuntimeDelegate;

public class CacheControl
{

            public CacheControl()
            {
/*  73*/        maxAge = -1;
/*  74*/        sMaxAge = -1;
/*  93*/        privateFlag = false;
/*  94*/        noCache = false;
/*  95*/        noStore = false;
/*  96*/        noTransform = true;
/*  97*/        mustRevalidate = false;
/*  98*/        proxyRevalidate = false;
            }

            public static CacheControl valueOf(String s)
            {
/* 111*/        return (CacheControl)HEADER_DELEGATE.fromString(s);
            }

            public boolean isMustRevalidate()
            {
/* 123*/        return mustRevalidate;
            }

            public void setMustRevalidate(boolean flag)
            {
/* 134*/        mustRevalidate = flag;
            }

            public boolean isProxyRevalidate()
            {
/* 146*/        return proxyRevalidate;
            }

            public void setProxyRevalidate(boolean flag)
            {
/* 157*/        proxyRevalidate = flag;
            }

            public int getMaxAge()
            {
/* 168*/        return maxAge;
            }

            public void setMaxAge(int i)
            {
/* 178*/        maxAge = i;
            }

            public int getSMaxAge()
            {
/* 189*/        return sMaxAge;
            }

            public void setSMaxAge(int i)
            {
/* 199*/        sMaxAge = i;
            }

            public List getNoCacheFields()
            {
/* 213*/        if(noCacheFields == null)
/* 214*/            noCacheFields = new ArrayList();
/* 216*/        return noCacheFields;
            }

            public void setNoCache(boolean flag)
            {
/* 228*/        noCache = flag;
            }

            public boolean isNoCache()
            {
/* 241*/        return noCache;
            }

            public boolean isPrivate()
            {
/* 254*/        return privateFlag;
            }

            public List getPrivateFields()
            {
/* 268*/        if(privateFields == null)
/* 269*/            privateFields = new ArrayList();
/* 271*/        return privateFields;
            }

            public void setPrivate(boolean flag)
            {
/* 283*/        privateFlag = flag;
            }

            public boolean isNoTransform()
            {
/* 295*/        return noTransform;
            }

            public void setNoTransform(boolean flag)
            {
/* 306*/        noTransform = flag;
            }

            public boolean isNoStore()
            {
/* 318*/        return noStore;
            }

            public void setNoStore(boolean flag)
            {
/* 329*/        noStore = flag;
            }

            public Map getCacheExtension()
            {
/* 344*/        if(cacheExtension == null)
/* 345*/            cacheExtension = new HashMap();
/* 347*/        return cacheExtension;
            }

            public String toString()
            {
/* 358*/        return HEADER_DELEGATE.toString(this);
            }

            public int hashCode()
            {
/* 369*/        int i = 287 + (privateFlag ? 1 : 0);
/* 370*/        i = i * 41 + (noCache ? 1 : 0);
/* 371*/        i = i * 41 + (noStore ? 1 : 0);
/* 372*/        i = i * 41 + (noTransform ? 1 : 0);
/* 373*/        i = i * 41 + (mustRevalidate ? 1 : 0);
/* 374*/        i = i * 41 + (proxyRevalidate ? 1 : 0);
/* 375*/        i = i * 41 + maxAge;
/* 376*/        i = i * 41 + sMaxAge;
/* 377*/        i = i * 41 + hashCodeOf(privateFields);
/* 378*/        i = i * 41 + hashCodeOf(noCacheFields);
/* 379*/        return i = i * 41 + hashCodeOf(cacheExtension);
            }

            public boolean equals(Object obj)
            {
/* 392*/        if(obj == null)
/* 393*/            return false;
/* 395*/        if(getClass() != obj.getClass())
/* 396*/            return false;
/* 398*/        obj = (CacheControl)obj;
/* 399*/        if(privateFlag != ((CacheControl) (obj)).privateFlag)
/* 400*/            return false;
/* 402*/        if(noCache != ((CacheControl) (obj)).noCache)
/* 403*/            return false;
/* 405*/        if(noStore != ((CacheControl) (obj)).noStore)
/* 406*/            return false;
/* 408*/        if(noTransform != ((CacheControl) (obj)).noTransform)
/* 409*/            return false;
/* 411*/        if(mustRevalidate != ((CacheControl) (obj)).mustRevalidate)
/* 412*/            return false;
/* 414*/        if(proxyRevalidate != ((CacheControl) (obj)).proxyRevalidate)
/* 415*/            return false;
/* 417*/        if(maxAge != ((CacheControl) (obj)).maxAge)
/* 418*/            return false;
/* 420*/        if(sMaxAge != ((CacheControl) (obj)).sMaxAge)
/* 421*/            return false;
/* 423*/        if(notEqual(privateFields, ((CacheControl) (obj)).privateFields))
/* 424*/            return false;
/* 426*/        if(notEqual(noCacheFields, ((CacheControl) (obj)).noCacheFields))
/* 427*/            return false;
/* 429*/        return !notEqual(cacheExtension, ((CacheControl) (obj)).cacheExtension);
            }

            private static boolean notEqual(Collection collection, Collection collection1)
            {
/* 446*/        if(collection == collection1)
/* 447*/            return false;
/* 449*/        if(collection == null)
/* 451*/            return !collection1.isEmpty();
/* 453*/        if(collection1 == null)
/* 455*/            return !collection.isEmpty();
/* 458*/        return !collection.equals(collection1);
            }

            private static boolean notEqual(Map map, Map map1)
            {
/* 472*/        if(map == map1)
/* 473*/            return false;
/* 475*/        if(map == null)
/* 477*/            return !map1.isEmpty();
/* 479*/        if(map1 == null)
/* 481*/            return !map.isEmpty();
/* 484*/        return !map.equals(map1);
            }

            private static int hashCodeOf(Collection collection)
            {
/* 497*/        if(collection == null || collection.isEmpty())
/* 497*/            return 0;
/* 497*/        else
/* 497*/            return collection.hashCode();
            }

            private static int hashCodeOf(Map map)
            {
/* 510*/        if(map == null || map.isEmpty())
/* 510*/            return 0;
/* 510*/        else
/* 510*/            return map.hashCode();
            }

            private static final javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/CacheControl);
            private List privateFields;
            private List noCacheFields;
            private Map cacheExtension;
            private boolean privateFlag;
            private boolean noCache;
            private boolean noStore;
            private boolean noTransform;
            private boolean mustRevalidate;
            private boolean proxyRevalidate;
            private int maxAge;
            private int sMaxAge;

}
