// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NarrowResults.java

package org.jvnet.hk2.internal;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ErrorResults

public class NarrowResults
{

            public NarrowResults()
            {
            }

            void addGoodResult(ActiveDescriptor activedescriptor)
            {
/*  60*/        goodResults.add(activedescriptor);
            }

            void addError(ActiveDescriptor activedescriptor, Injectee injectee, MultiException multiexception)
            {
/*  64*/        errors.add(new ErrorResults(activedescriptor, injectee, multiexception));
            }

            List getResults()
            {
/*  68*/        return goodResults;
            }

            List getErrors()
            {
/*  72*/        return errors;
            }

            void setUnnarrowedResults(List list)
            {
/*  76*/        unnarrowedResults = list;
            }

            ActiveDescriptor removeUnnarrowedResult()
            {
/*  80*/        if(unnarrowedResults == null || unnarrowedResults.isEmpty())
/*  80*/            return null;
/*  82*/        else
/*  82*/            return (ActiveDescriptor)unnarrowedResults.remove(0);
            }

            public String toString()
            {
/*  86*/        return (new StringBuilder("NarrowResults(goodResultsSize=")).append(goodResults.size()).append(",errorsSize=").append(errors.size()).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private List unnarrowedResults;
            private final List goodResults = new LinkedList();
            private final List errors = new LinkedList();
}
