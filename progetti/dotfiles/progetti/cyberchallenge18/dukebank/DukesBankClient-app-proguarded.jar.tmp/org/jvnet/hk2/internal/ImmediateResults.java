// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateResults.java

package org.jvnet.hk2.internal;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.api.ActiveDescriptor;

// Referenced classes of package org.jvnet.hk2.internal:
//            NarrowResults

public class ImmediateResults
{

            ImmediateResults(NarrowResults narrowresults)
            {
/*  59*/        if(narrowresults == null)
                {
/*  60*/            timelessResults = new NarrowResults();
/*  60*/            return;
                } else
                {
/*  63*/            timelessResults = narrowresults;
/*  65*/            return;
                }
            }

            NarrowResults getTimelessResults()
            {
/*  68*/        return timelessResults;
            }

            List getImmediateResults()
            {
/*  72*/        return validatedImmediateResults;
            }

            void addValidatedResult(ActiveDescriptor activedescriptor)
            {
/*  76*/        validatedImmediateResults.add(activedescriptor);
            }

            public String toString()
            {
/*  81*/        return (new StringBuilder("ImmediateResults(")).append(timelessResults).append(",").append(validatedImmediateResults).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final NarrowResults timelessResults;
            private final List validatedImmediateResults = new LinkedList();
}
