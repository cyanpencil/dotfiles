// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonString.java

package com.owlike.genson.ext.jsr353;

import javax.json.JsonString;
import javax.json.JsonValue;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            JSR353Bundle

class GensonJsonString
    implements JsonString
{

            public GensonJsonString(String s)
            {
/*   9*/        value = s;
            }

            public String getString()
            {
/*  14*/        return value;
            }

            public CharSequence getChars()
            {
/*  19*/        return value;
            }

            public javax.json.JsonValue.ValueType getValueType()
            {
/*  24*/        return javax.json.JsonValue.ValueType.STRING;
            }

            public boolean equals(Object obj)
            {
/*  29*/        if(this == obj)
/*  29*/            return true;
/*  30*/        if(obj == null || getClass() != obj.getClass())
/*  30*/            return false;
/*  32*/        obj = (GensonJsonString)obj;
/*  34*/        return value == null ? ((GensonJsonString) (obj)).value == null : value.equals(((GensonJsonString) (obj)).value);
            }

            public int hashCode()
            {
/*  41*/        if(value != null)
/*  41*/            return value.hashCode();
/*  41*/        else
/*  41*/            return 0;
            }

            public String toString()
            {
/*  46*/        return JSR353Bundle.toString(this);
            }

            private final String value;
}
