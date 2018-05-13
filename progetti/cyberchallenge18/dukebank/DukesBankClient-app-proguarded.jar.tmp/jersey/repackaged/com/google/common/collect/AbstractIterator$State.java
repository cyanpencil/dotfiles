// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractIterator.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIterator

static final class  extends Enum
{

            public static [] values()
            {
/*  70*/        return ([])$VALUES.clone();
            }

            public static final FAILED READY;
            public static final FAILED NOT_READY;
            public static final FAILED DONE;
            public static final FAILED FAILED;
            private static final FAILED $VALUES[];

            static 
            {
/*  72*/        READY = new <init>("READY", 0);
/*  75*/        NOT_READY = new <init>("NOT_READY", 1);
/*  78*/        DONE = new <init>("DONE", 2);
/*  81*/        FAILED = new <init>("FAILED", 3);
/*  70*/        $VALUES = (new .VALUES[] {
/*  70*/            READY, NOT_READY, DONE, FAILED
                });
            }

            private (String s, int i)
            {
/*  70*/        super(s, i);
            }
}
