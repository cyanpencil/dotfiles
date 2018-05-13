// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Member.java

package javassist.compiler.ast;

import javassist.CtField;
import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            Symbol, Visitor

public class Member extends Symbol
{

            public Member(String s)
            {
/*  31*/        super(s);
/*  32*/        field = null;
            }

            public void setField(CtField ctfield)
            {
/*  35*/        field = ctfield;
            }

            public CtField getField()
            {
/*  37*/        return field;
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  39*/        visitor.atMember(this);
            }

            private CtField field;
}
