// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Providers.java

package org.glassfish.jersey.internal.inject;

import javax.ws.rs.RuntimeType;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            Providers

static final class runtime extends Enum
{

            public static runtime[] values()
            {
/* 137*/        return (runtime[])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/* 137*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/jersey/internal/inject/Providers$ProviderRuntime, s);
            }

            public final RuntimeType getRuntime()
            {
/* 148*/        return runtime;
            }

            public static final CLIENT BOTH;
            public static final CLIENT SERVER;
            public static final CLIENT CLIENT;
            private final RuntimeType runtime;
            private static final CLIENT $VALUES[];

            static 
            {
/* 139*/        BOTH = new <init>("BOTH", 0, null);
/* 139*/        SERVER = new <init>("SERVER", 1, RuntimeType.SERVER);
/* 139*/        CLIENT = new <init>("CLIENT", 2, RuntimeType.CLIENT);
/* 137*/        $VALUES = (new .VALUES[] {
/* 137*/            BOTH, SERVER, CLIENT
                });
            }

            private (String s, int i, RuntimeType runtimetype)
            {
/* 143*/        super(s, i);
/* 144*/        runtime = runtimetype;
            }
}
