// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CircularClassReferenceConverterFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson.convert:
//            CircularClassReferenceConverterFactory

static final class  extends Wrapper
    implements Converter
{

            public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  26*/        ((Converter)wrapped).serialize(obj, objectwriter, context);
            }

            public final Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  30*/        return ((Converter)wrapped).deserialize(objectreader, context);
            }

            final void setDelegateConverter(Converter converter)
            {
/*  34*/        decorate(converter);
            }

            protected ()
            {
            }
}
