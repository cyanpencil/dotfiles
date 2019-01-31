// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Types.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;

// Referenced classes of package org.jvnet.tiger_types:
//            Types

static class etTypeParameters
{

            Type replace(TypeVariable typevariable)
            {
/* 142*/        for(int i = 0; i < params.length; i++)
/* 143*/            if(params[i].equals(typevariable))
/* 144*/                return args[i];

/* 145*/        return typevariable;
            }

            final TypeVariable params[];
            final Type args[];
            static final boolean $assertionsDisabled = !org/jvnet/tiger_types/Types.desiredAssertionStatus();


            (TypeVariable atypevariable[], Type atype[])
            {
/* 132*/        params = atypevariable;
/* 133*/        args = atype;
/* 134*/        if(!$assertionsDisabled && atypevariable.length != atype.length)
/* 134*/            throw new AssertionError();
/* 135*/        else
/* 135*/            return;
            }

            public .assertionsDisabled(GenericDeclaration genericdeclaration, Type atype[])
            {
/* 138*/        this(genericdeclaration.getTypeParameters(), atype);
            }
}
