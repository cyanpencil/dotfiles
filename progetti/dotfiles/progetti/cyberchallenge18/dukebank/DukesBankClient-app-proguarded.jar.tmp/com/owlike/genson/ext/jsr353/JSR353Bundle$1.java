// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JSR353Bundle.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.*;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            JSR353Bundle

class this._cls0
    implements Factory
{

            public Converter create(Type type, Genson genson)
            {
/*  35*/        return new onValueConverter(JSR353Bundle.this);
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  32*/        return create(type, genson);
            }

            final JSR353Bundle this$0;

            onValueConverter()
            {
/*  32*/        this$0 = JSR353Bundle.this;
/*  32*/        super();
            }
}
