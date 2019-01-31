// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DuplicatePostProcessor.java

package org.glassfish.hk2.utilities;

import java.util.*;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl

public class DuplicatePostProcessor
    implements PopulatorPostProcessor
{

            public DuplicatePostProcessor()
            {
            }

            public DescriptorImpl process(ServiceLocator servicelocator, DescriptorImpl descriptorimpl)
            {
/*  76*/        if(dupSet.contains(descriptorimpl))
/*  77*/            return null;
/*  79*/        dupSet.add(descriptorimpl);
/*  81*/        final String fContract = descriptorimpl.getAdvertisedContracts();
/*  82*/        final String fName = null;
/*  83*/        fContract = fContract.iterator();
/*  83*/        do
                {
/*  83*/            if(!fContract.hasNext())
/*  83*/                break;
/*  83*/            if((fName = (String)fContract.next()).equals(descriptorimpl.getImplementation()))
                    {
/*  86*/                fName = fName;
/*  87*/                break;
                    }
/*  90*/            fName = fName;
                } while(true);
/*  93*/        fContract = fName;
/*  94*/        fName = descriptorimpl.getName();
/*  95*/        final DescriptorImpl fDescriptorImpl = descriptorimpl;
/*  97*/        if(servicelocator.getBestDescriptor(new IndexedFilter() {

                public boolean matches(Descriptor descriptor)
                {
/* 101*/            return fDescriptorImpl.equals(descriptor);
                }

                public String getAdvertisedContract()
                {
/* 106*/            return fContract;
                }

                public String getName()
                {
/* 111*/            return fName;
                }

                final DescriptorImpl val$fDescriptorImpl;
                final String val$fContract;
                final String val$fName;
                final DuplicatePostProcessor this$0;

                    
                    {
/*  97*/                this$0 = DuplicatePostProcessor.this;
/*  97*/                fDescriptorImpl = descriptorimpl;
/*  97*/                fContract = s;
/*  97*/                fName = s1;
/*  97*/                super();
                    }
    }) != null)
/* 116*/            return null;
/* 119*/        else
/* 119*/            return descriptorimpl;
            }

            private final HashSet dupSet = new HashSet();
}
