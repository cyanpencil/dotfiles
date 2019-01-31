// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, CodeAttribute, CodeIterator, ExceptionTable

static class 
{

            static byte[] doit(byte abyte0[],  , ExceptionTable exceptiontable, CodeAttribute codeattribute)
                throws BadBytecode
            {
/* 444*/        if( != null)
/* 445*/            abyte0 = CodeIterator.changeLdcToLdcW(abyte0, exceptiontable, codeattribute, );
/* 460*/        return abyte0;
            }

            oLdcW next;
            int where;
            int index;

            ()
            {
            }
}
