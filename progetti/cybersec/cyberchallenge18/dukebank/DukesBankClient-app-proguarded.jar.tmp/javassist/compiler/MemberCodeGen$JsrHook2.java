// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberCodeGen.java

package javassist.compiler;

import javassist.bytecode.Bytecode;

// Referenced classes of package javassist.compiler:
//            CodeGen, MemberCodeGen

static class var extends var
{

            protected boolean doit(Bytecode bytecode, int i)
            {
/* 161*/        switch(i)
                {
/* 165*/        case 176: 
/* 165*/            bytecode.addAstore(var);
                    break;

/* 168*/        case 172: 
/* 168*/            bytecode.addIstore(var);
                    break;

/* 171*/        case 173: 
/* 171*/            bytecode.addLstore(var);
                    break;

/* 174*/        case 175: 
/* 174*/            bytecode.addDstore(var);
                    break;

/* 177*/        case 174: 
/* 177*/            bytecode.addFstore(var);
                    break;

/* 180*/        default:
/* 180*/            throw new RuntimeException("fatal");

/* 161*/        case 177: 
                    break;
                }
/* 183*/        bytecode.addOpcode(167);
/* 184*/        bytecode.addIndex((target - bytecode.currentPc()) + 3);
/* 185*/        return true;
            }

            int var;
            int target;

            (CodeGen codegen, int ai[])
            {
/* 155*/        super(codegen);
/* 156*/        target = ai[0];
/* 157*/        var = ai[1];
            }
}
