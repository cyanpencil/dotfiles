// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeType.java

package javax.ws.rs;


public final class RuntimeType extends Enum
{

            public static RuntimeType[] values()
            {
/*  48*/        return (RuntimeType[])$VALUES.clone();
            }

            public static RuntimeType valueOf(String s)
            {
/*  48*/        return (RuntimeType)Enum.valueOf(javax/ws/rs/RuntimeType, s);
            }

            private RuntimeType(String s, int i)
            {
/*  48*/        super(s, i);
            }

            public static final RuntimeType CLIENT;
            public static final RuntimeType SERVER;
            private static final RuntimeType $VALUES[];

            static 
            {
/*  52*/        CLIENT = new RuntimeType("CLIENT", 0);
/*  56*/        SERVER = new RuntimeType("SERVER", 1);
/*  48*/        $VALUES = (new RuntimeType[] {
/*  48*/            CLIENT, SERVER
                });
            }
}
