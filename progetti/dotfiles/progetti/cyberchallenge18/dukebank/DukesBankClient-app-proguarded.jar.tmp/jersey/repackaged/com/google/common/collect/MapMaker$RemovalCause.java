// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMaker.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMaker

static abstract class <init> extends Enum
{

            public static final SIZE EXPLICIT;
            public static final SIZE REPLACED;
            public static final SIZE COLLECTED;
            public static final SIZE EXPIRED;
            public static final SIZE SIZE;
            private static final SIZE $VALUES[];

            static 
            {
/* 690*/        EXPLICIT = new MapMaker.RemovalCause("EXPLICIT", 0) {

        };
/* 703*/        REPLACED = new MapMaker.RemovalCause("REPLACED", 1) {

        };
/* 714*/        COLLECTED = new MapMaker.RemovalCause("COLLECTED", 2) {

        };
/* 725*/        EXPIRED = new MapMaker.RemovalCause("EXPIRED", 3) {

        };
/* 736*/        SIZE = new MapMaker.RemovalCause("SIZE", 4) {

        };
/* 685*/        $VALUES = (new .VALUES[] {
/* 685*/            EXPLICIT, REPLACED, COLLECTED, EXPIRED, SIZE
                });
            }

            private _cls1(String s, int i)
            {
/* 685*/        super(s, i);
            }

            _cls1(String s, int i, _cls1 _pcls1)
            {
/* 685*/        this(s, i);
            }
}
