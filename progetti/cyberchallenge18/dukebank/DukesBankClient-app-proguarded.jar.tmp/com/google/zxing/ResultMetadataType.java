// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResultMetadataType.java

package com.google.zxing;


public final class ResultMetadataType extends Enum
{

            public static ResultMetadataType[] values()
            {
/*  25*/        return (ResultMetadataType[])$VALUES.clone();
            }

            public static ResultMetadataType valueOf(String s)
            {
/*  25*/        return (ResultMetadataType)Enum.valueOf(com/google/zxing/ResultMetadataType, s);
            }

            private ResultMetadataType(String s, int i)
            {
/*  25*/        super(s, i);
            }

            public static final ResultMetadataType OTHER;
            public static final ResultMetadataType ORIENTATION;
            public static final ResultMetadataType BYTE_SEGMENTS;
            public static final ResultMetadataType ERROR_CORRECTION_LEVEL;
            public static final ResultMetadataType ISSUE_NUMBER;
            public static final ResultMetadataType SUGGESTED_PRICE;
            public static final ResultMetadataType POSSIBLE_COUNTRY;
            public static final ResultMetadataType UPC_EAN_EXTENSION;
            public static final ResultMetadataType PDF417_EXTRA_METADATA;
            public static final ResultMetadataType STRUCTURED_APPEND_SEQUENCE;
            public static final ResultMetadataType STRUCTURED_APPEND_PARITY;
            private static final ResultMetadataType $VALUES[];

            static 
            {
/*  30*/        OTHER = new ResultMetadataType("OTHER", 0);
/*  39*/        ORIENTATION = new ResultMetadataType("ORIENTATION", 1);
/*  50*/        BYTE_SEGMENTS = new ResultMetadataType("BYTE_SEGMENTS", 2);
/*  56*/        ERROR_CORRECTION_LEVEL = new ResultMetadataType("ERROR_CORRECTION_LEVEL", 3);
/*  61*/        ISSUE_NUMBER = new ResultMetadataType("ISSUE_NUMBER", 4);
/*  67*/        SUGGESTED_PRICE = new ResultMetadataType("SUGGESTED_PRICE", 5);
/*  73*/        POSSIBLE_COUNTRY = new ResultMetadataType("POSSIBLE_COUNTRY", 6);
/*  78*/        UPC_EAN_EXTENSION = new ResultMetadataType("UPC_EAN_EXTENSION", 7);
/*  83*/        PDF417_EXTRA_METADATA = new ResultMetadataType("PDF417_EXTRA_METADATA", 8);
/*  89*/        STRUCTURED_APPEND_SEQUENCE = new ResultMetadataType("STRUCTURED_APPEND_SEQUENCE", 9);
/*  95*/        STRUCTURED_APPEND_PARITY = new ResultMetadataType("STRUCTURED_APPEND_PARITY", 10);
/*  25*/        $VALUES = (new ResultMetadataType[] {
/*  25*/            OTHER, ORIENTATION, BYTE_SEGMENTS, ERROR_CORRECTION_LEVEL, ISSUE_NUMBER, SUGGESTED_PRICE, POSSIBLE_COUNTRY, UPC_EAN_EXTENSION, PDF417_EXTRA_METADATA, STRUCTURED_APPEND_SEQUENCE, 
/*  25*/            STRUCTURED_APPEND_PARITY
                });
            }
}
