// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntervalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.*;
import java.lang.reflect.Type;
import org.joda.time.DateTime;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            IntervalConverter

public static class 
    implements Factory
{

            public Converter create(Type type, Genson genson)
            {
/*  23*/        return new IntervalConverter(genson.provideConverter(org/joda/time/DateTime));
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  19*/        return create(type, genson);
            }

            public ()
            {
            }
}
