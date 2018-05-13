// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DuplicatePostProcessor.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, DuplicatePostProcessor

class val.fName
    implements IndexedFilter
{

            public boolean matches(Descriptor descriptor)
            {
/* 101*/        return val$fDescriptorImpl.equals(descriptor);
            }

            public String getAdvertisedContract()
            {
/* 106*/        return val$fContract;
            }

            public String getName()
            {
/* 111*/        return val$fName;
            }

            final DescriptorImpl val$fDescriptorImpl;
            final String val$fContract;
            final String val$fName;
            final DuplicatePostProcessor this$0;

            ()
            {
/*  97*/        this$0 = final_duplicatepostprocessor;
/*  97*/        val$fDescriptorImpl = descriptorimpl;
/*  97*/        val$fContract = s;
/*  97*/        val$fName = String.this;
/*  97*/        super();
            }
}
