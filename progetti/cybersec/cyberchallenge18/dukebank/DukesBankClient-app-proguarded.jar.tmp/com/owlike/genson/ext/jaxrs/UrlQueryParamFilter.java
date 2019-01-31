// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UrlQueryParamFilter.java

package com.owlike.genson.ext.jaxrs;

import com.owlike.genson.Context;
import com.owlike.genson.ThreadLocalHolder;
import com.owlike.genson.reflect.BeanProperty;
import com.owlike.genson.reflect.RuntimePropertyFilter;
import java.io.IOException;
import java.util.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class UrlQueryParamFilter
    implements RuntimePropertyFilter, ContainerRequestFilter
{

            public UrlQueryParamFilter()
            {
/*  21*/        paramName = "filter";
/*  23*/        inclusionFilter = true;
            }

            public boolean shouldInclude(BeanProperty beanproperty, Context context)
            {
/*  28*/        context = (Set)ThreadLocalHolder.get("_jaxrs_params_to_filter", java/util/Set);
/*  30*/        if(inclusionFilter)
/*  30*/            return context.contains(beanproperty.getName());
/*  31*/        return !context.contains(beanproperty.getName());
            }

            public void filter(ContainerRequestContext containerrequestcontext)
                throws IOException
            {
/*  36*/        containerrequestcontext = (List)containerrequestcontext.getUriInfo().getQueryParameters().get(paramName);
/*  37*/        HashSet hashset = new HashSet();
/*  38*/        if(containerrequestcontext != null)
/*  39*/            for(containerrequestcontext = containerrequestcontext.iterator(); containerrequestcontext.hasNext();)
                    {
/*  39*/                String s = (String)containerrequestcontext.next();
/*  40*/                if(splitBy != null)
/*  40*/                    Collections.addAll(hashset, s.split(splitBy));
/*  40*/                else
/*  40*/                    hashset.add(s);
                    }

/*  44*/        ThreadLocalHolder.store("_jaxrs_params_to_filter", hashset);
            }

            public UrlQueryParamFilter inclusionFilter(boolean flag)
            {
/*  53*/        inclusionFilter = flag;
/*  54*/        return this;
            }

            public UrlQueryParamFilter paramName(String s)
            {
/*  61*/        paramName = s;
/*  62*/        return this;
            }

            public UrlQueryParamFilter splitBy(String s)
            {
/*  70*/        splitBy = s;
/*  71*/        return this;
            }

            private String paramName;
            private boolean inclusionFilter;
            private String splitBy;
}
