// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodeHintType.java

package com.google.zxing;

import java.util.List;

// Referenced classes of package com.google.zxing:
//            ResultPointCallback

public final class DecodeHintType extends Enum
{

            public static DecodeHintType[] values()
            {
/*  30*/        return (DecodeHintType[])$VALUES.clone();
            }

            public static DecodeHintType valueOf(String s)
            {
/*  30*/        return (DecodeHintType)Enum.valueOf(com/google/zxing/DecodeHintType, s);
            }

            private DecodeHintType(String s, int i, Class class1)
            {
/* 114*/        super(s, i);
/* 115*/        valueType = class1;
            }

            public final Class getValueType()
            {
/* 119*/        return valueType;
            }

            public static final DecodeHintType OTHER;
            public static final DecodeHintType PURE_BARCODE;
            public static final DecodeHintType POSSIBLE_FORMATS;
            public static final DecodeHintType TRY_HARDER;
            public static final DecodeHintType CHARACTER_SET;
            public static final DecodeHintType ALLOWED_LENGTHS;
            public static final DecodeHintType ASSUME_CODE_39_CHECK_DIGIT;
            public static final DecodeHintType ASSUME_GS1;
            public static final DecodeHintType RETURN_CODABAR_START_END;
            public static final DecodeHintType NEED_RESULT_POINT_CALLBACK;
            public static final DecodeHintType ALLOWED_EAN_EXTENSIONS;
            private final Class valueType;
            private static final DecodeHintType $VALUES[];

            static 
            {
/*  35*/        OTHER = new DecodeHintType("OTHER", 0, java/lang/Object);
/*  41*/        PURE_BARCODE = new DecodeHintType("PURE_BARCODE", 1, java/lang/Void);
/*  47*/        POSSIBLE_FORMATS = new DecodeHintType("POSSIBLE_FORMATS", 2, java/util/List);
/*  53*/        TRY_HARDER = new DecodeHintType("TRY_HARDER", 3, java/lang/Void);
/*  58*/        CHARACTER_SET = new DecodeHintType("CHARACTER_SET", 4, java/lang/String);
/*  63*/        ALLOWED_LENGTHS = new DecodeHintType("ALLOWED_LENGTHS", 5, [I);
/*  69*/        ASSUME_CODE_39_CHECK_DIGIT = new DecodeHintType("ASSUME_CODE_39_CHECK_DIGIT", 6, java/lang/Void);
/*  76*/        ASSUME_GS1 = new DecodeHintType("ASSUME_GS1", 7, java/lang/Void);
/*  83*/        RETURN_CODABAR_START_END = new DecodeHintType("RETURN_CODABAR_START_END", 8, java/lang/Void);
/*  89*/        NEED_RESULT_POINT_CALLBACK = new DecodeHintType("NEED_RESULT_POINT_CALLBACK", 9, com/google/zxing/ResultPointCallback);
/*  99*/        ALLOWED_EAN_EXTENSIONS = new DecodeHintType("ALLOWED_EAN_EXTENSIONS", 10, [I);
/*  30*/        $VALUES = (new DecodeHintType[] {
/*  30*/            OTHER, PURE_BARCODE, POSSIBLE_FORMATS, TRY_HARDER, CHARACTER_SET, ALLOWED_LENGTHS, ASSUME_CODE_39_CHECK_DIGIT, ASSUME_GS1, RETURN_CODABAR_START_END, NEED_RESULT_POINT_CALLBACK, 
/*  30*/            ALLOWED_EAN_EXTENSIONS
                });
            }
}
