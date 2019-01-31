// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GuavaBundle.java

package com.owlike.genson.ext.guava;

import com.google.common.base.Optional;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.GensonBundle;

// Referenced classes of package com.owlike.genson.ext.guava:
//            OptionalConverter

public class GuavaBundle extends GensonBundle
{

            public GuavaBundle()
            {
            }

            public void configure(GensonBuilder gensonbuilder)
            {
/*  10*/        gensonbuilder.useDefaultValue(Optional.absent(), com/google/common/base/Optional).withConverterFactory(new OptionalConverter.OptionalConverterFactory());
            }
}
