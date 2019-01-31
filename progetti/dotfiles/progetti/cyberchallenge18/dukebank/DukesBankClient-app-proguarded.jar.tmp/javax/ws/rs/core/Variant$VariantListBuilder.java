// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Variant.java

package javax.ws.rs.core;

import java.util.List;
import java.util.Locale;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            Variant, MediaType

public static abstract class 
{

            public static  newInstance()
            {
/* 309*/        return RuntimeDelegate.getInstance().createVariantListBuilder();
            }

            public abstract List build();

            public abstract tListBuilder add();

            public transient abstract tListBuilder languages(Locale alocale[]);

            public transient abstract tListBuilder encodings(String as[]);

            public transient abstract tListBuilder mediaTypes(MediaType amediatype[]);

            protected ()
            {
            }
}
