// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class exceptions
{

            public exceptions[] getTypeParameters()
            {
/* 292*/        return typeParams;
            }

            public typeParams[] getParameterTypes()
            {
/* 299*/        return params;
            }

            public params getReturnType()
            {
/* 304*/        return retType;
            }

            public retType[] getExceptionTypes()
            {
/* 312*/        return exceptions;
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 318*/        String(stringbuffer = new StringBuffer(), typeParams);
/* 321*/        stringbuffer.append(" (");
/* 322*/        typeParams(stringbuffer, params);
/* 323*/        stringbuffer.append(") ");
/* 324*/        stringbuffer.append(retType);
/* 325*/        if(exceptions.length > 0)
                {
/* 326*/            stringbuffer.append(" throws ");
/* 327*/            exceptions(stringbuffer, exceptions);
                }
/* 330*/        return stringbuffer.toString();
            }

            public String encode()
            {
/* 337*/        StringBuffer stringbuffer = new StringBuffer();
/* 338*/        if(typeParams.length > 0)
                {
/* 339*/            stringbuffer.append('<');
/* 340*/            for(int i = 0; i < typeParams.length; i++)
/* 341*/                typeParams[i].code(stringbuffer);

/* 343*/            stringbuffer.append('>');
                }
/* 346*/        stringbuffer.append('(');
/* 347*/        for(int j = 0; j < params.length; j++)
/* 348*/            params[j].params(stringbuffer);

/* 350*/        stringbuffer.append(')');
/* 351*/        retType.retType(stringbuffer);
/* 352*/        if(exceptions.length > 0)
                {
/* 353*/            for(int k = 0; k < exceptions.length; k++)
                    {
/* 354*/                stringbuffer.append('^');
/* 355*/                exceptions[k].e(stringbuffer);
                    }

                }
/* 358*/        return stringbuffer.toString();
            }

            e typeParams[];
            e params[];
            e retType;
            e exceptions[];

            public ( a[],  a1[],  ,  a2[])
            {
/* 281*/        typeParams = a != null ? a : new typeParams[0];
/* 282*/        params = a1 != null ? a1 : new params[0];
/* 283*/        retType = ((retType) ( != null ?  : ((retType) (new retType("void")))));
/* 284*/        exceptions = a2 != null ? a2 : new exceptions[0];
            }
}
