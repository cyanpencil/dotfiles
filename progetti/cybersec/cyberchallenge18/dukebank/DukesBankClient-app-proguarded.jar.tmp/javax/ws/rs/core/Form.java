// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Form.java

package javax.ws.rs.core;

import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package javax.ws.rs.core:
//            MultivaluedMap, AbstractMultivaluedMap

public class Form
{

            public Form()
            {
/*  64*/        this(((MultivaluedMap) (new AbstractMultivaluedMap(new LinkedHashMap()) {

        })));
            }

            public Form(String s, String s1)
            {
/*  81*/        this();
/*  83*/        parameters.add(s, s1);
            }

            public Form(MultivaluedMap multivaluedmap)
            {
/*  97*/        parameters = multivaluedmap;
            }

            public Form param(String s, String s1)
            {
/* 108*/        parameters.add(s, s1);
/* 110*/        return this;
            }

            public MultivaluedMap asMap()
            {
/* 120*/        return parameters;
            }

            private final MultivaluedMap parameters;
}
