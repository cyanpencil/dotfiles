// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class 
    implements Converter
{

            public final void serialize(Character character, ObjectWriter objectwriter, Context context)
            {
/* 568*/        objectwriter.writeValue(character.toString());
            }

            public final Character deserialize(ObjectReader objectreader, Context context)
            {
/* 572*/        if((objectreader = objectreader.valueAsString()).length() > 1)
/* 573*/            throw new JsonBindingException("Could not convert a string with length greater than 1 to a single char.");
/* 577*/        else
/* 577*/            return Character.valueOf(objectreader.charAt(0));
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 559*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 559*/        serialize((Character)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
