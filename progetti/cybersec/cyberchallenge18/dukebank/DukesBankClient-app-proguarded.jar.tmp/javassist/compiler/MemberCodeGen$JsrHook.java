// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberCodeGen.java

package javassist.compiler;

import java.util.ArrayList;
import javassist.bytecode.Bytecode;

// Referenced classes of package javassist.compiler:
//            CodeGen, MemberCodeGen

static class var extends var
{

            private int getVar(int i)
            {
/*  98*/        if(var < 0)
                {
/*  99*/            var = cgen.getMaxLocals();
/* 100*/            cgen.incMaxLocals(i);
                }
/* 103*/        return var;
            }

            private void jsrJmp(Bytecode bytecode)
            {
/* 107*/        bytecode.addOpcode(167);
/* 108*/        jsrList.add(new int[] {
/* 108*/            bytecode.currentPc(), var
                });
/* 109*/        bytecode.addIndex(0);
            }

            protected boolean doit(Bytecode bytecode, int i)
            {
/* 113*/        switch(i)
                {
/* 115*/        case 177: 
/* 115*/            jsrJmp(bytecode);
                    break;

/* 118*/        case 176: 
/* 118*/            bytecode.addAstore(getVar(1));
/* 119*/            jsrJmp(bytecode);
/* 120*/            bytecode.addAload(var);
                    break;

/* 123*/        case 172: 
/* 123*/            bytecode.addIstore(getVar(1));
/* 124*/            jsrJmp(bytecode);
/* 125*/            bytecode.addIload(var);
                    break;

/* 128*/        case 173: 
/* 128*/            bytecode.addLstore(getVar(2));
/* 129*/            jsrJmp(bytecode);
/* 130*/            bytecode.addLload(var);
                    break;

/* 133*/        case 175: 
/* 133*/            bytecode.addDstore(getVar(2));
/* 134*/            jsrJmp(bytecode);
/* 135*/            bytecode.addDload(var);
                    break;

/* 138*/        case 174: 
/* 138*/            bytecode.addFstore(getVar(1));
/* 139*/            jsrJmp(bytecode);
/* 140*/            bytecode.addFload(var);
                    break;

/* 143*/        default:
/* 143*/            throw new RuntimeException("fatal");
                }
/* 146*/        return false;
            }

            ArrayList jsrList;
            CodeGen cgen;
            int var;

            (CodeGen codegen)
            {
/*  91*/        super(codegen);
/*  92*/        jsrList = new ArrayList();
/*  93*/        cgen = codegen;
/*  94*/        var = -1;
            }
}
