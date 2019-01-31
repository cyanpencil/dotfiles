// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;
import java.text.*;
import java.util.Date;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class asTimeInMillis
    implements Converter
{

            public void serialize(Date date, ObjectWriter objectwriter, Context context)
            {
/* 953*/        if(asTimeInMillis)
                {
/* 954*/            objectwriter.writeValue(date.getTime());
/* 954*/            return;
                } else
                {
/* 956*/            objectwriter.writeUnsafeValue(format(date));
/* 957*/            return;
                }
            }

            protected synchronized String format(Date date)
            {
/* 960*/        return dateFormat.format(date);
            }

            public Date deserialize(ObjectReader objectreader, Context context)
            {
/* 965*/        if((context = objectreader.getValueType()) == ValueType.INTEGER)
/* 967*/            return new Date(objectreader.valueAsLong());
/* 968*/        if(context == ValueType.STRING)
/* 969*/            return read(objectreader.valueAsString());
/* 970*/        try
                {
/* 970*/            throw new JsonBindingException(String.format("Can not deserialize type %s to Date, only numeric and string accepted.", new Object[] {
/* 970*/                context
                    }));
                }
                // Misplaced declaration of an exception variable
/* 972*/        catch(Context context)
                {
/* 973*/            throw new JsonBindingException((new StringBuilder("Could not parse date ")).append(objectreader.valueAsString()).toString(), context);
                }
            }

            protected synchronized Date read(String s)
                throws ParseException
            {
/* 979*/        return dateFormat.parse(s);
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 936*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 936*/        serialize((Date)obj, objectwriter, context);
            }

            private DateFormat dateFormat;
            private final boolean asTimeInMillis;

            public ()
            {
/* 943*/        this(SimpleDateFormat.getDateTimeInstance(), true);
            }

            public <init>(DateFormat dateformat, boolean flag)
            {
/* 947*/        if(dateformat == null)
/* 947*/            dateformat = SimpleDateFormat.getDateTimeInstance();
/* 948*/        dateFormat = dateformat;
/* 949*/        asTimeInMillis = flag;
            }
}
