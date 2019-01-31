// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SubroutineScanner.java

package javassist.bytecode.analysis;

import java.util.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.analysis:
//            Subroutine, Util

public class SubroutineScanner
    implements Opcode
{

            public SubroutineScanner()
            {
/*  38*/        subTable = new HashMap();
/*  39*/        done = new HashSet();
            }

            public Subroutine[] scan(MethodInfo methodinfo)
                throws BadBytecode
            {
/*  43*/        CodeIterator codeiterator = (methodinfo = methodinfo.getCodeAttribute()).iterator();
/*  46*/        subroutines = new Subroutine[methodinfo.getCodeLength()];
/*  47*/        subTable.clear();
/*  48*/        done.clear();
/*  50*/        scan(0, codeiterator, null);
/*  52*/        methodinfo = methodinfo.getExceptionTable();
/*  53*/        for(int i = 0; i < methodinfo.size(); i++)
                {
/*  54*/            int j = methodinfo.handlerPc(i);
/*  57*/            scan(j, codeiterator, subroutines[methodinfo.startPc(i)]);
                }

/*  60*/        return subroutines;
            }

            private void scan(int i, CodeIterator codeiterator, Subroutine subroutine)
                throws BadBytecode
            {
/*  65*/        if(done.contains(new Integer(i)))
/*  66*/            return;
/*  68*/        done.add(new Integer(i));
/*  70*/        int j = codeiterator.lookAhead();
/*  71*/        codeiterator.move(i);
/*  75*/        do
/*  75*/            i = codeiterator.next();
/*  77*/        while((i = !scanOp(i, codeiterator, subroutine) || !codeiterator.hasNext() ? 0 : 1) != 0);
/*  79*/        codeiterator.move(j);
            }

            private boolean scanOp(int i, CodeIterator codeiterator, Subroutine subroutine)
                throws BadBytecode
            {
/*  83*/        subroutines[i] = subroutine;
                int j;
/*  85*/        if((j = codeiterator.byteAt(i)) == 170)
                {
/*  88*/            scanTableSwitch(i, codeiterator, subroutine);
/*  90*/            return false;
                }
/*  93*/        if(j == 171)
                {
/*  94*/            scanLookupSwitch(i, codeiterator, subroutine);
/*  96*/            return false;
                }
/* 100*/        if(Util.isReturn(j) || j == 169 || j == 191)
/* 101*/            return false;
/* 103*/        if(Util.isJumpInstruction(j))
                {
/* 104*/            int k = Util.getJumpTarget(i, codeiterator);
/* 105*/            if(j == 168 || j == 201)
                    {
/* 106*/                if((subroutine = (Subroutine)subTable.get(new Integer(k))) == null)
                        {
/* 108*/                    subroutine = new Subroutine(k, i);
/* 109*/                    subTable.put(new Integer(k), subroutine);
/* 110*/                    scan(k, codeiterator, subroutine);
                        } else
                        {
/* 112*/                    subroutine.addCaller(i);
                        }
                    } else
                    {
/* 115*/                scan(k, codeiterator, subroutine);
/* 118*/                if(Util.isGoto(j))
/* 119*/                    return false;
                    }
                }
/* 123*/        return true;
            }

            private void scanLookupSwitch(int i, CodeIterator codeiterator, Subroutine subroutine)
                throws BadBytecode
            {
/* 127*/        int j = (i & -4) + 4;
/* 129*/        scan(i + codeiterator.s32bitAt(j), codeiterator, subroutine);
                int k;
/* 130*/        k = ((k = codeiterator.s32bitAt(j += 4)) << 3) + (j += 4);
/* 134*/        for(j += 4; j < k; j += 8)
                {
/* 135*/            int l = codeiterator.s32bitAt(j) + i;
/* 136*/            scan(l, codeiterator, subroutine);
                }

            }

            private void scanTableSwitch(int i, CodeIterator codeiterator, Subroutine subroutine)
                throws BadBytecode
            {
/* 142*/        int j = (i & -4) + 4;
/* 144*/        scan(i + codeiterator.s32bitAt(j), codeiterator, subroutine);
/* 145*/        int k = codeiterator.s32bitAt(j += 4);
                int l;
/* 146*/        for(k = (((l = codeiterator.s32bitAt(j += 4)) - k) + 1 << 2) + (j += 4); j < k; j += 4)
                {
/* 151*/            int i1 = codeiterator.s32bitAt(j) + i;
/* 152*/            scan(i1, codeiterator, subroutine);
                }

            }

            private Subroutine subroutines[];
            Map subTable;
            Set done;
}
