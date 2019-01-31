// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeGen.java

package javassist.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import javassist.bytecode.Bytecode;
import javassist.bytecode.Opcode;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.ArrayInit;
import javassist.compiler.ast.AssignExpr;
import javassist.compiler.ast.BinExpr;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.CondExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.FieldDecl;
import javassist.compiler.ast.InstanceOfExpr;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.Pair;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Variable;
import javassist.compiler.ast.Visitor;

// Referenced classes of package javassist.compiler:
//            CompileError, MemberResolver, TokenId, TypeChecker

public abstract class CodeGen extends Visitor
    implements Opcode, TokenId
{
    public static abstract class ReturnHook
    {

                protected abstract boolean doit(Bytecode bytecode1, int i);

                protected void remove(CodeGen codegen)
                {
/*  70*/            codegen.returnHooks = next;
                }

                ReturnHook next;

                protected ReturnHook(CodeGen codegen)
                {
/*  65*/            next = codegen.returnHooks;
/*  66*/            codegen.returnHooks = this;
                }
    }


            public CodeGen(Bytecode bytecode1)
            {
/*  84*/        bytecode = bytecode1;
/*  85*/        tempVar = -1;
/*  86*/        typeChecker = null;
/*  87*/        hasReturned = false;
/*  88*/        inStaticMethod = false;
/*  89*/        breakList = null;
/*  90*/        continueList = null;
/*  91*/        returnHooks = null;
            }

            public void setTypeChecker(TypeChecker typechecker)
            {
/*  95*/        typeChecker = typechecker;
            }

            protected static void fatal()
                throws CompileError
            {
/*  99*/        throw new CompileError("fatal");
            }

            public static boolean is2word(int i, int j)
            {
/* 103*/        return j == 0 && (i == 312 || i == 326);
            }

            public int getMaxLocals()
            {
/* 106*/        return bytecode.getMaxLocals();
            }

            public void setMaxLocals(int i)
            {
/* 109*/        bytecode.setMaxLocals(i);
            }

            protected void incMaxLocals(int i)
            {
/* 113*/        bytecode.incMaxLocals(i);
            }

            protected int getTempVar()
            {
/* 121*/        if(tempVar < 0)
                {
/* 122*/            tempVar = getMaxLocals();
/* 123*/            incMaxLocals(2);
                }
/* 126*/        return tempVar;
            }

            protected int getLocalVar(Declarator declarator)
            {
                int i;
/* 130*/        if((i = declarator.getLocalVar()) < 0)
                {
/* 132*/            i = getMaxLocals();
/* 133*/            declarator.setLocalVar(i);
/* 134*/            incMaxLocals(1);
                }
/* 137*/        return i;
            }

            protected abstract String getThisName();

            protected abstract String getSuperName()
                throws CompileError;

            protected abstract String resolveClassName(ASTList astlist)
                throws CompileError;

            protected abstract String resolveClassName(String s)
                throws CompileError;

            protected static String toJvmArrayName(String s, int i)
            {
/* 169*/        if(s == null)
/* 170*/            return null;
/* 172*/        if(i == 0)
/* 173*/            return s;
/* 175*/        StringBuffer stringbuffer = new StringBuffer();
/* 176*/        for(i = i; i-- > 0;)
/* 178*/            stringbuffer.append('[');

/* 180*/        stringbuffer.append('L');
/* 181*/        stringbuffer.append(s);
/* 182*/        stringbuffer.append(';');
/* 184*/        return stringbuffer.toString();
            }

            protected static String toJvmTypeName(int i, int j)
            {
/* 189*/        char c = 'I';
/* 190*/        switch(i)
                {
/* 192*/        case 301: 
/* 192*/            c = 'Z';
                    break;

/* 195*/        case 303: 
/* 195*/            c = 'B';
                    break;

/* 198*/        case 306: 
/* 198*/            c = 'C';
                    break;

/* 201*/        case 334: 
/* 201*/            c = 'S';
                    break;

/* 204*/        case 324: 
/* 204*/            c = 'I';
                    break;

/* 207*/        case 326: 
/* 207*/            c = 'J';
                    break;

/* 210*/        case 317: 
/* 210*/            c = 'F';
                    break;

/* 213*/        case 312: 
/* 213*/            c = 'D';
                    break;

/* 216*/        case 344: 
/* 216*/            c = 'V';
                    break;
                }
/* 220*/        i = new StringBuffer();
/* 221*/        while(j-- > 0) 
/* 222*/            i.append('[');
/* 224*/        i.append(c);
/* 225*/        return i.toString();
            }

            public void compileExpr(ASTree astree)
                throws CompileError
            {
/* 229*/        doTypeCheck(astree);
/* 230*/        astree.accept(this);
            }

            public boolean compileBooleanExpr(boolean flag, ASTree astree)
                throws CompileError
            {
/* 236*/        doTypeCheck(astree);
/* 237*/        return booleanExpr(flag, astree);
            }

            public void doTypeCheck(ASTree astree)
                throws CompileError
            {
/* 241*/        if(typeChecker != null)
/* 242*/            astree.accept(typeChecker);
            }

            public void atASTList(ASTList astlist)
                throws CompileError
            {
/* 245*/        fatal();
            }

            public void atPair(Pair pair)
                throws CompileError
            {
/* 247*/        fatal();
            }

            public void atSymbol(Symbol symbol)
                throws CompileError
            {
/* 249*/        fatal();
            }

            public void atFieldDecl(FieldDecl fielddecl)
                throws CompileError
            {
/* 252*/        fielddecl.getInit().accept(this);
            }

            public void atMethodDecl(MethodDecl methoddecl)
                throws CompileError
            {
/* 256*/        Object obj = methoddecl.getModifiers();
/* 257*/        setMaxLocals(1);
/* 258*/        do
                {
/* 258*/            if(obj == null)
/* 259*/                break;
/* 259*/            Keyword keyword = (Keyword)((ASTList) (obj)).head();
/* 260*/            obj = ((ASTList) (obj)).tail();
/* 261*/            if(keyword.get() == 335)
                    {
/* 262*/                setMaxLocals(0);
/* 263*/                inStaticMethod = true;
                    }
                } while(true);
/* 267*/        for(ASTList astlist = methoddecl.getParams(); astlist != null; astlist = astlist.tail())
/* 269*/            atDeclarator((Declarator)astlist.head());

/* 273*/        obj = methoddecl.getBody();
/* 274*/        atMethodBody(((Stmnt) (obj)), methoddecl.isConstructor(), methoddecl.getReturn().getType() == 344);
            }

            public void atMethodBody(Stmnt stmnt, boolean flag, boolean flag1)
                throws CompileError
            {
/* 285*/        if(stmnt == null)
/* 286*/            return;
/* 288*/        if(flag && needsSuperCall(stmnt))
/* 289*/            insertDefaultSuperCall();
/* 291*/        hasReturned = false;
/* 292*/        stmnt.accept(this);
/* 293*/        if(!hasReturned)
                {
/* 294*/            if(flag1)
                    {
/* 295*/                bytecode.addOpcode(177);
/* 296*/                hasReturned = true;
/* 296*/                return;
                    } else
                    {
/* 299*/                throw new CompileError("no return statement");
                    }
                } else
                {
/* 300*/            return;
                }
            }

            private boolean needsSuperCall(Stmnt stmnt)
                throws CompileError
            {
/* 303*/        if(stmnt.getOperator() == 66)
/* 304*/            stmnt = (Stmnt)stmnt.head();
/* 306*/        if(stmnt != null && stmnt.getOperator() == 69 && (stmnt = stmnt.head()) != null && (stmnt instanceof Expr) && ((Expr)stmnt).getOperator() == 67 && ((stmnt = ((Expr)stmnt).head()) instanceof Keyword))
/* 312*/            return (stmnt = ((Keyword)stmnt).get()) != 339 && stmnt != 336;
/* 318*/        else
/* 318*/            return true;
            }

            protected abstract void insertDefaultSuperCall()
                throws CompileError;

            public void atStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 324*/        if(stmnt == null)
/* 325*/            return;
                int i;
/* 327*/        if((i = stmnt.getOperator()) == 69)
                {
/* 329*/            stmnt = stmnt.getLeft();
/* 330*/            doTypeCheck(stmnt);
/* 331*/            if(stmnt instanceof AssignExpr)
/* 332*/                atAssignExpr((AssignExpr)stmnt, false);
/* 333*/            else
/* 333*/            if(isPlusPlusExpr(stmnt))
                    {
/* 334*/                i = (Expr)stmnt;
/* 335*/                atPlusPlus(i.getOperator(), i.oprand1(), i, false);
                    } else
                    {
/* 338*/                stmnt.accept(this);
/* 339*/                if(is2word(exprType, arrayDim))
                        {
/* 340*/                    bytecode.addOpcode(88);
                        } else
                        {
/* 341*/                    if(exprType != 344)
/* 342*/                        bytecode.addOpcode(87);
/* 344*/                    return;
                        }
                    }
                } else
                {
/* 345*/            if(i == 68 || i == 66)
                    {
/* 346*/                stmnt = stmnt;
/* 347*/                do
                        {
/* 347*/                    if(stmnt == null)
/* 348*/                        break;
/* 348*/                    i = stmnt.head();
/* 349*/                    stmnt = stmnt.tail();
/* 350*/                    if(i != null)
/* 351*/                        i.accept(this);
                        } while(true);
/* 353*/                return;
                    }
/* 354*/            if(i == 320)
                    {
/* 355*/                atIfStmnt(stmnt);
/* 355*/                return;
                    }
/* 356*/            if(i == 346 || i == 311)
                    {
/* 357*/                atWhileStmnt(stmnt, i == 346);
/* 357*/                return;
                    }
/* 358*/            if(i == 318)
                    {
/* 359*/                atForStmnt(stmnt);
/* 359*/                return;
                    }
/* 360*/            if(i == 302 || i == 309)
                    {
/* 361*/                atBreakStmnt(stmnt, i == 302);
/* 361*/                return;
                    }
/* 362*/            if(i == 333)
                    {
/* 363*/                atReturnStmnt(stmnt);
/* 363*/                return;
                    }
/* 364*/            if(i == 340)
                    {
/* 365*/                atThrowStmnt(stmnt);
/* 365*/                return;
                    }
/* 366*/            if(i == 343)
                    {
/* 367*/                atTryStmnt(stmnt);
/* 367*/                return;
                    }
/* 368*/            if(i == 337)
                    {
/* 369*/                atSwitchStmnt(stmnt);
/* 369*/                return;
                    }
/* 370*/            if(i == 338)
                    {
/* 371*/                atSyncStmnt(stmnt);
/* 371*/                return;
                    } else
                    {
/* 374*/                hasReturned = false;
/* 375*/                throw new CompileError((new StringBuilder("sorry, not supported statement: TokenId ")).append(i).toString());
                    }
                }
            }

            private void atIfStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 381*/        ASTree astree = stmnt.head();
/* 382*/        Stmnt stmnt1 = (Stmnt)stmnt.tail().head();
/* 383*/        stmnt = (Stmnt)stmnt.tail().tail().head();
/* 384*/        compileBooleanExpr(false, astree);
/* 385*/        int i = bytecode.currentPc();
/* 386*/        int j = 0;
/* 387*/        bytecode.addIndex(0);
/* 389*/        hasReturned = false;
/* 390*/        if(stmnt1 != null)
/* 391*/            stmnt1.accept(this);
/* 393*/        boolean flag = hasReturned;
/* 394*/        hasReturned = false;
/* 396*/        if(stmnt != null && !flag)
                {
/* 397*/            bytecode.addOpcode(167);
/* 398*/            j = bytecode.currentPc();
/* 399*/            bytecode.addIndex(0);
                }
/* 402*/        bytecode.write16bit(i, (bytecode.currentPc() - i) + 1);
/* 404*/        if(stmnt != null)
                {
/* 405*/            stmnt.accept(this);
/* 406*/            if(!flag)
/* 407*/                bytecode.write16bit(j, (bytecode.currentPc() - j) + 1);
/* 409*/            hasReturned = flag && hasReturned;
                }
            }

            private void atWhileStmnt(Stmnt stmnt, boolean flag)
                throws CompileError
            {
/* 414*/        ArrayList arraylist = breakList;
/* 415*/        ArrayList arraylist1 = continueList;
/* 416*/        breakList = new ArrayList();
/* 417*/        continueList = new ArrayList();
/* 419*/        ASTree astree = stmnt.head();
/* 420*/        stmnt = (Stmnt)stmnt.tail();
/* 422*/        int i = 0;
/* 423*/        if(flag)
                {
/* 424*/            bytecode.addOpcode(167);
/* 425*/            i = bytecode.currentPc();
/* 426*/            bytecode.addIndex(0);
                }
/* 429*/        int j = bytecode.currentPc();
/* 430*/        if(stmnt != null)
/* 431*/            stmnt.accept(this);
/* 433*/        stmnt = bytecode.currentPc();
/* 434*/        if(flag)
/* 435*/            bytecode.write16bit(i, (stmnt - i) + 1);
/* 437*/        flag = compileBooleanExpr(true, astree);
/* 438*/        bytecode.addIndex((j - bytecode.currentPc()) + 1);
/* 440*/        patchGoto(breakList, bytecode.currentPc());
/* 441*/        patchGoto(continueList, stmnt);
/* 442*/        continueList = arraylist1;
/* 443*/        breakList = arraylist;
/* 444*/        hasReturned = flag;
            }

            protected void patchGoto(ArrayList arraylist, int i)
            {
/* 448*/        int j = arraylist.size();
/* 449*/        for(int k = 0; k < j; k++)
                {
/* 450*/            int l = ((Integer)arraylist.get(k)).intValue();
/* 451*/            bytecode.write16bit(l, (i - l) + 1);
                }

            }

            private void atForStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 456*/        ArrayList arraylist = breakList;
/* 457*/        ArrayList arraylist1 = continueList;
/* 458*/        breakList = new ArrayList();
/* 459*/        continueList = new ArrayList();
/* 461*/        Stmnt stmnt1 = (Stmnt)stmnt.head();
/* 462*/        ASTree astree = (stmnt = stmnt.tail()).head();
/* 464*/        Stmnt stmnt2 = (Stmnt)(stmnt = stmnt.tail()).head();
/* 466*/        stmnt = (Stmnt)stmnt.tail();
/* 468*/        if(stmnt1 != null)
/* 469*/            stmnt1.accept(this);
/* 471*/        int i = bytecode.currentPc();
/* 472*/        int j = 0;
/* 473*/        if(astree != null)
                {
/* 474*/            compileBooleanExpr(false, astree);
/* 475*/            j = bytecode.currentPc();
/* 476*/            bytecode.addIndex(0);
                }
/* 479*/        if(stmnt != null)
/* 480*/            stmnt.accept(this);
/* 482*/        stmnt = bytecode.currentPc();
/* 483*/        if(stmnt2 != null)
/* 484*/            stmnt2.accept(this);
/* 486*/        bytecode.addOpcode(167);
/* 487*/        bytecode.addIndex((i - bytecode.currentPc()) + 1);
/* 489*/        i = bytecode.currentPc();
/* 490*/        if(astree != null)
/* 491*/            bytecode.write16bit(j, (i - j) + 1);
/* 493*/        patchGoto(breakList, i);
/* 494*/        patchGoto(continueList, stmnt);
/* 495*/        continueList = arraylist1;
/* 496*/        breakList = arraylist;
/* 497*/        hasReturned = false;
            }

            private void atSwitchStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 501*/        compileExpr(stmnt.head());
/* 503*/        ArrayList arraylist = breakList;
/* 504*/        breakList = new ArrayList();
/* 505*/        int i = bytecode.currentPc();
/* 506*/        bytecode.addOpcode(171);
/* 507*/        for(int j = 3 - (i & 3); j-- > 0;)
/* 509*/            bytecode.add(0);

/* 511*/        stmnt = (Stmnt)stmnt.tail();
/* 512*/        int k = 0;
/* 513*/        for(Object obj = stmnt; obj != null; obj = ((ASTList) (obj)).tail())
/* 514*/            if(((Stmnt)((ASTList) (obj)).head()).getOperator() == 304)
/* 515*/                k++;

/* 518*/        int l = bytecode.currentPc();
/* 519*/        bytecode.addGap(4);
/* 520*/        bytecode.add32bit(k);
/* 521*/        bytecode.addGap(k << 3);
/* 523*/        long al[] = new long[k];
/* 524*/        int i1 = 0;
/* 525*/        int j1 = -1;
/* 526*/        for(stmnt = stmnt; stmnt != null; stmnt = stmnt.tail())
                {
                    Stmnt stmnt1;
                    int i2;
/* 527*/            if((i2 = (stmnt1 = (Stmnt)stmnt.head()).getOperator()) == 310)
/* 530*/                j1 = bytecode.currentPc();
/* 531*/            else
/* 531*/            if(i2 != 304)
/* 532*/                fatal();
/* 534*/            else
/* 534*/                al[i1++] = ((long)computeLabel(stmnt1.head()) << 32) + (long)(bytecode.currentPc() - i);
/* 539*/            hasReturned = false;
/* 540*/            ((Stmnt)stmnt1.tail()).accept(this);
                }

/* 543*/        Arrays.sort(al);
/* 544*/        stmnt = l + 8;
/* 545*/        for(int k1 = 0; k1 < k; k1++)
                {
/* 546*/            bytecode.write32bit(stmnt, (int)(al[k1] >>> 32));
/* 547*/            bytecode.write32bit(stmnt + 4, (int)al[k1]);
/* 548*/            stmnt += 8;
                }

/* 551*/        if(j1 < 0 || breakList.size() > 0)
/* 552*/            hasReturned = false;
/* 554*/        int l1 = bytecode.currentPc();
/* 555*/        if(j1 < 0)
/* 556*/            j1 = l1;
/* 558*/        bytecode.write32bit(l, j1 - i);
/* 560*/        patchGoto(breakList, l1);
/* 561*/        breakList = arraylist;
            }

            private int computeLabel(ASTree astree)
                throws CompileError
            {
/* 565*/        doTypeCheck(astree);
/* 566*/        if((astree = TypeChecker.stripPlusExpr(astree)) instanceof IntConst)
/* 568*/            return (int)((IntConst)astree).get();
/* 570*/        else
/* 570*/            throw new CompileError("bad case label");
            }

            private void atBreakStmnt(Stmnt stmnt, boolean flag)
                throws CompileError
            {
/* 576*/        if(stmnt.head() != null)
/* 577*/            throw new CompileError("sorry, not support labeled break or continue");
/* 580*/        bytecode.addOpcode(167);
/* 581*/        stmnt = new Integer(bytecode.currentPc());
/* 582*/        bytecode.addIndex(0);
/* 583*/        if(flag)
                {
/* 584*/            breakList.add(stmnt);
/* 584*/            return;
                } else
                {
/* 586*/            continueList.add(stmnt);
/* 587*/            return;
                }
            }

            protected void atReturnStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 590*/        atReturnStmnt2(stmnt.getLeft());
            }

            protected final void atReturnStmnt2(ASTree astree)
                throws CompileError
            {
/* 595*/        if(astree == null)
                {
/* 596*/            astree = 177;
                } else
                {
/* 598*/            compileExpr(astree);
                    int i;
/* 599*/            if(arrayDim > 0)
/* 600*/                astree = 176;
/* 602*/            else
/* 602*/            if((i = exprType) == 312)
/* 604*/                astree = 175;
/* 605*/            else
/* 605*/            if(i == 317)
/* 606*/                astree = 174;
/* 607*/            else
/* 607*/            if(i == 326)
/* 608*/                astree = 173;
/* 609*/            else
/* 609*/            if(isRefType(i))
/* 610*/                astree = 176;
/* 612*/            else
/* 612*/                astree = 172;
                }
/* 616*/        for(ReturnHook returnhook = returnHooks; returnhook != null; returnhook = returnhook.next)
/* 617*/            if(returnhook.doit(bytecode, astree))
                    {
/* 618*/                hasReturned = true;
/* 619*/                return;
                    }

/* 622*/        bytecode.addOpcode(astree);
/* 623*/        hasReturned = true;
            }

            private void atThrowStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 627*/        stmnt = stmnt.getLeft();
/* 628*/        compileExpr(stmnt);
/* 629*/        if(exprType != 307 || arrayDim > 0)
                {
/* 630*/            throw new CompileError("bad throw statement");
                } else
                {
/* 632*/            bytecode.addOpcode(191);
/* 633*/            hasReturned = true;
/* 634*/            return;
                }
            }

            protected void atTryStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 639*/        hasReturned = false;
            }

            private void atSyncStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 643*/        int i = getListSize(breakList);
/* 644*/        int j = getListSize(continueList);
/* 646*/        compileExpr(stmnt.head());
/* 647*/        if(exprType != 307 && arrayDim == 0)
/* 648*/            throw new CompileError("bad type expr for synchronized block");
                Bytecode bytecode1;
/* 650*/        int k = (bytecode1 = bytecode).getMaxLocals();
/* 652*/        bytecode1.incMaxLocals(1);
/* 653*/        bytecode1.addOpcode(89);
/* 654*/        bytecode1.addAstore(k);
/* 655*/        bytecode1.addOpcode(194);
/* 657*/        ReturnHook returnhook = new ReturnHook(k) {

                    protected boolean doit(Bytecode bytecode2, int k1)
                    {
/* 659*/                bytecode2.addAload(var);
/* 660*/                bytecode2.addOpcode(195);
/* 661*/                return false;
                    }

                    final int val$var;
                    final CodeGen this$0;

                    
                    {
/* 657*/                this$0 = CodeGen.this;
/* 657*/                var = i;
/* 657*/                super(final_codegen1);
                    }
        };
/* 665*/        int l = bytecode1.currentPc();
/* 666*/        if((stmnt = (Stmnt)stmnt.tail()) != null)
/* 668*/            stmnt.accept(this);
/* 670*/        stmnt = bytecode1.currentPc();
/* 671*/        int i1 = 0;
/* 672*/        if(!hasReturned)
                {
/* 673*/            returnhook.doit(bytecode1, 0);
/* 674*/            bytecode1.addOpcode(167);
/* 675*/            i1 = bytecode1.currentPc();
/* 676*/            bytecode1.addIndex(0);
                }
/* 679*/        if(l < stmnt)
                {
/* 680*/            int j1 = bytecode1.currentPc();
/* 681*/            returnhook.doit(bytecode1, 0);
/* 682*/            bytecode1.addOpcode(191);
/* 683*/            bytecode1.addExceptionHandler(l, stmnt, j1, 0);
                }
/* 686*/        if(!hasReturned)
/* 687*/            bytecode1.write16bit(i1, (bytecode1.currentPc() - i1) + 1);
/* 689*/        returnhook.remove(this);
/* 691*/        if(getListSize(breakList) != i || getListSize(continueList) != j)
/* 693*/            throw new CompileError("sorry, cannot break/continue in synchronized block");
/* 695*/        else
/* 695*/            return;
            }

            private static int getListSize(ArrayList arraylist)
            {
/* 698*/        if(arraylist == null)
/* 698*/            return 0;
/* 698*/        else
/* 698*/            return arraylist.size();
            }

            private static boolean isPlusPlusExpr(ASTree astree)
            {
/* 702*/        if(astree instanceof Expr)
/* 703*/            return (astree = ((Expr)astree).getOperator()) == 362 || astree == 363;
/* 707*/        else
/* 707*/            return false;
            }

            public void atDeclarator(Declarator declarator)
                throws CompileError
            {
/* 711*/        declarator.setLocalVar(getMaxLocals());
/* 712*/        declarator.setClassName(resolveClassName(declarator.getClassName()));
                byte byte0;
/* 715*/        if(is2word(declarator.getType(), declarator.getArrayDim()))
/* 716*/            byte0 = 2;
/* 718*/        else
/* 718*/            byte0 = 1;
/* 720*/        incMaxLocals(byte0);
                ASTree astree;
/* 724*/        if((astree = declarator.getInitializer()) != null)
                {
/* 726*/            doTypeCheck(astree);
/* 727*/            atVariableAssign(null, 61, null, declarator, astree, false);
                }
            }

            public abstract void atNewExpr(NewExpr newexpr)
                throws CompileError;

            public abstract void atArrayInit(ArrayInit arrayinit)
                throws CompileError;

            public void atAssignExpr(AssignExpr assignexpr)
                throws CompileError
            {
/* 736*/        atAssignExpr(assignexpr, true);
            }

            protected void atAssignExpr(AssignExpr assignexpr, boolean flag)
                throws CompileError
            {
/* 743*/        int i = assignexpr.getOperator();
/* 744*/        ASTree astree = assignexpr.oprand1();
/* 745*/        ASTree astree1 = assignexpr.oprand2();
/* 746*/        if(astree instanceof Variable)
                {
/* 747*/            atVariableAssign(assignexpr, i, (Variable)astree, ((Variable)astree).getDeclarator(), astree1, flag);
/* 747*/            return;
                }
                Expr expr;
/* 751*/        if((astree instanceof Expr) && (expr = (Expr)astree).getOperator() == 65)
                {
/* 754*/            atArrayAssign(assignexpr, i, (Expr)astree, astree1, flag);
/* 755*/            return;
                } else
                {
/* 759*/            atFieldAssign(assignexpr, i, astree, astree1, flag);
/* 761*/            return;
                }
            }

            protected static void badAssign(Expr expr)
                throws CompileError
            {
/* 765*/        if(expr == null)
/* 766*/            expr = "incompatible type for assignment";
/* 768*/        else
/* 768*/            expr = (new StringBuilder("incompatible type for ")).append(expr.getName()).toString();
/* 770*/        throw new CompileError(expr);
            }

            private void atVariableAssign(Expr expr, int i, Variable variable, Declarator declarator, ASTree astree, boolean flag)
                throws CompileError
            {
/* 781*/        int j = declarator.getType();
/* 782*/        int k = declarator.getArrayDim();
/* 783*/        String s = declarator.getClassName();
/* 784*/        declarator = getLocalVar(declarator);
/* 786*/        if(i != 61)
/* 787*/            atVariable(variable);
/* 790*/        if(expr == null && (astree instanceof ArrayInit))
/* 791*/            atArrayVariableAssign((ArrayInit)astree, j, k, s);
/* 793*/        else
/* 793*/            atAssignCore(expr, i, astree, j, k, s);
/* 795*/        if(flag)
/* 796*/            if(is2word(j, k))
/* 797*/                bytecode.addOpcode(92);
/* 799*/            else
/* 799*/                bytecode.addOpcode(89);
/* 801*/        if(k > 0)
/* 802*/            bytecode.addAstore(declarator);
/* 803*/        else
/* 803*/        if(j == 312)
/* 804*/            bytecode.addDstore(declarator);
/* 805*/        else
/* 805*/        if(j == 317)
/* 806*/            bytecode.addFstore(declarator);
/* 807*/        else
/* 807*/        if(j == 326)
/* 808*/            bytecode.addLstore(declarator);
/* 809*/        else
/* 809*/        if(isRefType(j))
/* 810*/            bytecode.addAstore(declarator);
/* 812*/        else
/* 812*/            bytecode.addIstore(declarator);
/* 814*/        exprType = j;
/* 815*/        arrayDim = k;
/* 816*/        className = s;
            }

            protected abstract void atArrayVariableAssign(ArrayInit arrayinit, int i, int j, String s)
                throws CompileError;

            private void atArrayAssign(Expr expr, int i, Expr expr1, ASTree astree, boolean flag)
                throws CompileError
            {
/* 825*/        arrayAccess(expr1.oprand1(), expr1.oprand2());
/* 827*/        if(i != 61)
                {
/* 828*/            bytecode.addOpcode(92);
/* 829*/            bytecode.addOpcode(getArrayReadOp(exprType, arrayDim));
                }
/* 832*/        expr1 = exprType;
/* 833*/        int j = arrayDim;
/* 834*/        String s = className;
/* 836*/        atAssignCore(expr, i, astree, expr1, j, s);
/* 838*/        if(flag)
/* 839*/            if(is2word(expr1, j))
/* 840*/                bytecode.addOpcode(94);
/* 842*/            else
/* 842*/                bytecode.addOpcode(91);
/* 844*/        bytecode.addOpcode(getArrayWriteOp(expr1, j));
/* 845*/        exprType = expr1;
/* 846*/        arrayDim = j;
/* 847*/        className = s;
            }

            protected abstract void atFieldAssign(Expr expr, int i, ASTree astree, ASTree astree1, boolean flag)
                throws CompileError;

            protected void atAssignCore(Expr expr, int i, ASTree astree, int j, int k, String s)
                throws CompileError
            {
/* 857*/        if(i == 354 && k == 0 && j == 307)
                {
/* 858*/            atStringPlusEq(expr, j, k, s, astree);
                } else
                {
/* 860*/            astree.accept(this);
/* 861*/            if(invalidDim(exprType, arrayDim, className, j, k, s, false) || i != 61 && k > 0)
/* 863*/                badAssign(expr);
/* 865*/            if(i != 61)
                    {
/* 866*/                if((s = lookupBinOp(astree = assignOps[i - 351])) < 0)
/* 869*/                    fatal();
/* 871*/                atArithBinExpr(expr, astree, s, j);
                    }
                }
/* 875*/        if(i != 61 || k == 0 && !isRefType(j))
/* 876*/            atNumCastExpr(exprType, j);
            }

            private void atStringPlusEq(Expr expr, int i, int j, String s, ASTree astree)
                throws CompileError
            {
/* 885*/        if(!"java/lang/String".equals(s))
/* 886*/            badAssign(expr);
/* 888*/        convToString(i, j);
/* 889*/        astree.accept(this);
/* 890*/        convToString(exprType, arrayDim);
/* 891*/        bytecode.addInvokevirtual("java.lang.String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");
/* 893*/        exprType = 307;
/* 894*/        arrayDim = 0;
/* 895*/        className = "java/lang/String";
            }

            private boolean invalidDim(int i, int j, String s, int k, int l, String s1, boolean flag)
            {
/* 902*/        if(j != l)
                {
/* 903*/            if(i == 412)
/* 904*/                return false;
/* 905*/            if(l == 0 && k == 307 && "java/lang/Object".equals(s1))
/* 907*/                return false;
/* 908*/            return !flag || j != 0 || i != 307 || !"java/lang/Object".equals(s);
                } else
                {
/* 914*/            return false;
                }
            }

            public void atCondExpr(CondExpr condexpr)
                throws CompileError
            {
/* 918*/        booleanExpr(false, condexpr.condExpr());
/* 919*/        int i = bytecode.currentPc();
/* 920*/        bytecode.addIndex(0);
/* 921*/        condexpr.thenExpr().accept(this);
/* 922*/        int j = arrayDim;
/* 923*/        bytecode.addOpcode(167);
/* 924*/        int k = bytecode.currentPc();
/* 925*/        bytecode.addIndex(0);
/* 926*/        bytecode.write16bit(i, (bytecode.currentPc() - i) + 1);
/* 927*/        condexpr.elseExpr().accept(this);
/* 928*/        if(j != arrayDim)
                {
/* 929*/            throw new CompileError("type mismatch in ?:");
                } else
                {
/* 931*/            bytecode.write16bit(k, (bytecode.currentPc() - k) + 1);
/* 932*/            return;
                }
            }

            static int lookupBinOp(int i)
            {
                int ai[];
/* 948*/        int j = (ai = binOp).length;
/* 950*/        for(int k = 0; k < j; k += 5)
/* 951*/            if(ai[k] == i)
/* 952*/                return k;

/* 954*/        return -1;
            }

            public void atBinExpr(BinExpr binexpr)
                throws CompileError
            {
                int i;
                int j;
/* 958*/        if((j = lookupBinOp(i = binexpr.getOperator())) >= 0)
                {
/* 964*/            binexpr.oprand1().accept(this);
                    ASTree astree;
/* 965*/            if((astree = binexpr.oprand2()) == null)
/* 967*/                return;
/* 969*/            int k = exprType;
/* 970*/            int l = arrayDim;
/* 971*/            String s = className;
/* 972*/            astree.accept(this);
/* 973*/            if(l != arrayDim)
/* 974*/                throw new CompileError("incompatible array types");
/* 976*/            if(i == 43 && l == 0 && (k == 307 || exprType == 307))
                    {
/* 978*/                atStringConcatExpr(binexpr, k, l, s);
                    } else
                    {
/* 980*/                atArithBinExpr(binexpr, i, j, k);
/* 981*/                return;
                    }
                } else
                {
/* 985*/            booleanExpr(true, binexpr);
/* 986*/            bytecode.addIndex(7);
/* 987*/            bytecode.addIconst(0);
/* 988*/            bytecode.addOpcode(167);
/* 989*/            bytecode.addIndex(4);
/* 990*/            bytecode.addIconst(1);
                }
            }

            private void atArithBinExpr(Expr expr, int i, int j, int k)
                throws CompileError
            {
/*1001*/        if(arrayDim != 0)
/*1002*/            badTypes(expr);
/*1004*/        int l = exprType;
/*1005*/        if(i == 364 || i == 366 || i == 370)
                {
/*1006*/            if(l == 324 || l == 334 || l == 306 || l == 303)
/*1008*/                exprType = k;
/*1010*/            else
/*1010*/                badTypes(expr);
                } else
                {
/*1012*/            convertOprandTypes(k, l, expr);
                }
/*1014*/        if((i = typePrecedence(exprType)) >= 0 && (j = binOp[j + i + 1]) != 0)
                {
/*1018*/            if(i == 3 && exprType != 301)
/*1019*/                exprType = 324;
/*1021*/            bytecode.addOpcode(j);
/*1022*/            return;
                } else
                {
/*1026*/            badTypes(expr);
/*1027*/            return;
                }
            }

            private void atStringConcatExpr(Expr expr, int i, int j, String s)
                throws CompileError
            {
/*1032*/        expr = exprType;
/*1033*/        s = arrayDim;
/*1034*/        boolean flag = is2word(expr, s);
/*1035*/        boolean flag1 = expr == 307 && "java/lang/String".equals(className);
/*1038*/        if(flag)
/*1039*/            convToString(expr, s);
/*1041*/        if(is2word(i, j))
                {
/*1042*/            bytecode.addOpcode(91);
/*1043*/            bytecode.addOpcode(87);
                } else
                {
/*1046*/            bytecode.addOpcode(95);
                }
/*1049*/        convToString(i, j);
/*1050*/        bytecode.addOpcode(95);
/*1052*/        if(!flag && !flag1)
/*1053*/            convToString(expr, s);
/*1055*/        bytecode.addInvokevirtual("java.lang.String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");
/*1057*/        exprType = 307;
/*1058*/        arrayDim = 0;
/*1059*/        className = "java/lang/String";
            }

            private void convToString(int i, int j)
                throws CompileError
            {
/*1065*/        if(isRefType(i) || j > 0)
                {
/*1066*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
/*1066*/            return;
                }
/*1068*/        if(i == 312)
                {
/*1069*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(D)Ljava/lang/String;");
/*1069*/            return;
                }
/*1071*/        if(i == 317)
                {
/*1072*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(F)Ljava/lang/String;");
/*1072*/            return;
                }
/*1074*/        if(i == 326)
                {
/*1075*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(J)Ljava/lang/String;");
/*1075*/            return;
                }
/*1077*/        if(i == 301)
                {
/*1078*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(Z)Ljava/lang/String;");
/*1078*/            return;
                }
/*1080*/        if(i == 306)
                {
/*1081*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(C)Ljava/lang/String;");
/*1081*/            return;
                }
/*1083*/        if(i == 344)
                {
/*1084*/            throw new CompileError("void type expression");
                } else
                {
/*1086*/            bytecode.addInvokestatic("java.lang.String", "valueOf", "(I)Ljava/lang/String;");
/*1088*/            return;
                }
            }

            private boolean booleanExpr(boolean flag, ASTree astree)
                throws CompileError
            {
                boolean flag1;
                int i;
/*1099*/        if((i = getCompOperator(astree)) == 358)
                {
/*1101*/            astree = (BinExpr)astree;
/*1102*/            i = compileOprands(astree);
/*1105*/            compareExpr(flag, astree.getOperator(), i, astree);
                } else
/*1107*/        if(i == 33)
/*1108*/            booleanExpr(!flag, ((Expr)astree).oprand1());
/*1109*/        else
/*1109*/        if((flag1 = i == 369) || i == 368)
                {
/*1110*/            astree = (BinExpr)astree;
/*1111*/            booleanExpr(!flag1, astree.oprand1());
/*1112*/            int j = bytecode.currentPc();
/*1113*/            bytecode.addIndex(0);
/*1115*/            booleanExpr(flag1, astree.oprand2());
/*1116*/            bytecode.write16bit(j, (bytecode.currentPc() - j) + 3);
/*1117*/            if(flag != flag1)
                    {
/*1118*/                bytecode.addIndex(6);
/*1119*/                bytecode.addOpcode(167);
                    }
                } else
                {
/*1122*/            if(isAlwaysBranch(astree, flag))
                    {
/*1123*/                bytecode.addOpcode(167);
/*1124*/                return true;
                    }
/*1127*/            astree.accept(this);
/*1128*/            if(exprType != 301 || arrayDim != 0)
/*1129*/                throw new CompileError("boolean expr is required");
/*1131*/            bytecode.addOpcode(flag ? 154 : 153);
                }
/*1134*/        exprType = 301;
/*1135*/        arrayDim = 0;
/*1136*/        return false;
            }

            private static boolean isAlwaysBranch(ASTree astree, boolean flag)
            {
/*1141*/        if(astree instanceof Keyword)
                {
/*1142*/            astree = ((Keyword)astree).get();
/*1143*/            if(flag)
/*1143*/                return astree == 410;
/*1143*/            return astree == 411;
                } else
                {
/*1146*/            return false;
                }
            }

            static int getCompOperator(ASTree astree)
                throws CompileError
            {
/*1150*/        if(astree instanceof Expr)
                {
                    int i;
/*1151*/            if((i = (astree = (Expr)astree).getOperator()) == 33)
/*1154*/                return 33;
/*1155*/            if((astree instanceof BinExpr) && i != 368 && i != 369 && i != 38 && i != 124)
/*1158*/                return 358;
/*1160*/            else
/*1160*/                return i;
                } else
                {
/*1163*/            return 32;
                }
            }

            private int compileOprands(BinExpr binexpr)
                throws CompileError
            {
/*1167*/        binexpr.oprand1().accept(this);
/*1168*/        int i = exprType;
/*1169*/        int j = arrayDim;
/*1170*/        binexpr.oprand2().accept(this);
/*1171*/        if(j != arrayDim)
                {
/*1172*/            if(i != 412 && exprType != 412)
/*1173*/                throw new CompileError("incompatible array types");
/*1174*/            if(exprType == 412)
/*1175*/                arrayDim = j;
                }
/*1177*/        if(i == 412)
/*1178*/            return exprType;
/*1180*/        else
/*1180*/            return i;
            }

            private void compareExpr(boolean flag, int i, int j, BinExpr binexpr)
                throws CompileError
            {
/*1206*/        if(arrayDim == 0)
/*1207*/            convertOprandTypes(j, exprType, binexpr);
/*1209*/        if((j = typePrecedence(exprType)) == -1 || arrayDim > 0)
                {
/*1211*/            if(i == 358)
                    {
/*1212*/                bytecode.addOpcode(flag ? 165 : 166);
/*1212*/                return;
                    }
/*1213*/            if(i == 350)
                    {
/*1214*/                bytecode.addOpcode(flag ? 166 : 165);
/*1214*/                return;
                    } else
                    {
/*1216*/                badTypes(binexpr);
/*1216*/                return;
                    }
                }
/*1218*/        if(j == 3)
                {
/*1219*/            j = ifOp;
/*1220*/            for(int k = 0; k < j.length; k += 3)
/*1221*/                if(j[k] == i)
                        {
/*1222*/                    bytecode.addOpcode(j[k + (flag ? 1 : 2)]);
/*1223*/                    return;
                        }

/*1226*/            badTypes(binexpr);
/*1227*/            return;
                }
/*1229*/        if(j == 0)
                {
/*1230*/            if(i == 60 || i == 357)
/*1231*/                bytecode.addOpcode(152);
/*1233*/            else
/*1233*/                bytecode.addOpcode(151);
                } else
/*1234*/        if(j == 1)
                {
/*1235*/            if(i == 60 || i == 357)
/*1236*/                bytecode.addOpcode(150);
/*1238*/            else
/*1238*/                bytecode.addOpcode(149);
                } else
/*1239*/        if(j == 2)
/*1240*/            bytecode.addOpcode(148);
/*1242*/        else
/*1242*/            fatal();
/*1244*/        j = ifOp2;
/*1245*/        for(int l = 0; l < j.length; l += 3)
/*1246*/            if(j[l] == i)
                    {
/*1247*/                bytecode.addOpcode(j[l + (flag ? 1 : 2)]);
/*1248*/                return;
                    }

/*1251*/        badTypes(binexpr);
            }

            protected static void badTypes(Expr expr)
                throws CompileError
            {
/*1256*/        throw new CompileError((new StringBuilder("invalid types for ")).append(expr.getName()).toString());
            }

            protected static boolean isRefType(int i)
            {
/*1266*/        return i == 307 || i == 412;
            }

            private static int typePrecedence(int i)
            {
/*1270*/        if(i == 312)
/*1271*/            return 0;
/*1272*/        if(i == 317)
/*1273*/            return 1;
/*1274*/        if(i == 326)
/*1275*/            return 2;
/*1276*/        if(isRefType(i))
/*1277*/            return -1;
/*1278*/        return i != 344 ? 3 : -1;
            }

            static boolean isP_INT(int i)
            {
/*1286*/        return typePrecedence(i) == 3;
            }

            static boolean rightIsStrong(int i, int j)
            {
/*1291*/        i = typePrecedence(i);
/*1292*/        j = typePrecedence(j);
/*1293*/        return i >= 0 && j >= 0 && i > j;
            }

            private void convertOprandTypes(int i, int j, Expr expr)
                throws CompileError
            {
/*1310*/        int k = typePrecedence(i);
                int l;
/*1311*/        if((l = typePrecedence(j)) < 0 && k < 0)
/*1314*/            return;
/*1316*/        if(l < 0 || k < 0)
/*1317*/            badTypes(expr);
/*1320*/        if(k <= l)
                {
/*1321*/            j = 0;
/*1322*/            exprType = i;
/*1323*/            i = castOp[(l << 2) + k];
                } else
                {
/*1327*/            j = 1;
/*1328*/            i = castOp[(k << 2) + l];
                }
/*1332*/        if(j != 0)
                {
/*1333*/            if(l == 0 || l == 2)
                    {
/*1334*/                if(k == 0 || k == 2)
/*1335*/                    bytecode.addOpcode(94);
/*1337*/                else
/*1337*/                    bytecode.addOpcode(93);
/*1339*/                bytecode.addOpcode(88);
/*1340*/                bytecode.addOpcode(i);
/*1341*/                bytecode.addOpcode(94);
/*1342*/                bytecode.addOpcode(88);
/*1342*/                return;
                    }
/*1344*/            if(l == 1)
                    {
/*1345*/                if(k == 2)
                        {
/*1346*/                    bytecode.addOpcode(91);
/*1347*/                    bytecode.addOpcode(87);
                        } else
                        {
/*1350*/                    bytecode.addOpcode(95);
                        }
/*1352*/                bytecode.addOpcode(i);
/*1353*/                bytecode.addOpcode(95);
/*1353*/                return;
                    } else
                    {
/*1356*/                fatal();
/*1356*/                return;
                    }
                }
/*1358*/        if(i != 0)
/*1359*/            bytecode.addOpcode(i);
            }

            public void atCastExpr(CastExpr castexpr)
                throws CompileError
            {
/*1363*/        String s = resolveClassName(castexpr.getClassName());
/*1364*/        String s1 = checkCastExpr(castexpr, s);
/*1365*/        int i = exprType;
/*1366*/        exprType = castexpr.getType();
/*1367*/        arrayDim = castexpr.getArrayDim();
/*1368*/        className = s;
/*1369*/        if(s1 == null)
                {
/*1370*/            atNumCastExpr(i, exprType);
/*1370*/            return;
                } else
                {
/*1372*/            bytecode.addCheckcast(s1);
/*1373*/            return;
                }
            }

            public void atInstanceOfExpr(InstanceOfExpr instanceofexpr)
                throws CompileError
            {
/*1376*/        String s = resolveClassName(instanceofexpr.getClassName());
/*1377*/        instanceofexpr = checkCastExpr(instanceofexpr, s);
/*1378*/        bytecode.addInstanceof(instanceofexpr);
/*1379*/        exprType = 301;
/*1380*/        arrayDim = 0;
            }

            private String checkCastExpr(CastExpr castexpr, String s)
                throws CompileError
            {
/*1387*/        ASTree astree = castexpr.getOprand();
/*1388*/        int j = castexpr.getArrayDim();
/*1389*/        castexpr = castexpr.getType();
/*1390*/        astree.accept(this);
/*1391*/        int i = exprType;
/*1392*/        if(invalidDim(i, arrayDim, className, castexpr, j, s, true) || i == 344 || castexpr == 344)
/*1394*/            throw new CompileError("invalid cast");
/*1396*/        if(castexpr == 307)
/*1397*/            if(!isRefType(i))
/*1398*/                throw new CompileError("invalid cast");
/*1400*/            else
/*1400*/                return toJvmArrayName(s, j);
/*1403*/        if(j > 0)
/*1404*/            return toJvmTypeName(castexpr, j);
/*1406*/        else
/*1406*/            return null;
            }

            void atNumCastExpr(int i, int j)
                throws CompileError
            {
/*1412*/        if(i == j)
/*1413*/            return;
/*1416*/        i = typePrecedence(i);
/*1417*/        int k = typePrecedence(j);
/*1418*/        if(i >= 0 && i < 3)
/*1419*/            i = castOp[(i << 2) + k];
/*1421*/        else
/*1421*/            i = 0;
/*1423*/        if(j == 312)
/*1424*/            j = 135;
/*1425*/        else
/*1425*/        if(j == 317)
/*1426*/            j = 134;
/*1427*/        else
/*1427*/        if(j == 326)
/*1428*/            j = 133;
/*1429*/        else
/*1429*/        if(j == 334)
/*1430*/            j = 147;
/*1431*/        else
/*1431*/        if(j == 306)
/*1432*/            j = 146;
/*1433*/        else
/*1433*/        if(j == 303)
/*1434*/            j = 145;
/*1436*/        else
/*1436*/            j = 0;
/*1438*/        if(i != 0)
/*1439*/            bytecode.addOpcode(i);
/*1441*/        if((i == 0 || i == 136 || i == 139 || i == 142) && j != 0)
/*1443*/            bytecode.addOpcode(j);
            }

            public void atExpr(Expr expr)
                throws CompileError
            {
/*1450*/label0:
                {
/*1450*/            int i = expr.getOperator();
/*1451*/            Object obj = expr.oprand1();
/*1452*/            if(i == 46)
                    {
/*1453*/                if(((String) (obj = ((Symbol)expr.oprand2()).get())).equals("class"))
                        {
/*1455*/                    atClassObject(expr);
                        } else
                        {
/*1457*/                    atFieldRead(expr);
/*1458*/                    return;
                        }
/*1459*/                break label0;
                    }
/*1459*/            if(i == 35)
                    {
/*1464*/                atFieldRead(expr);
/*1464*/                return;
                    }
/*1466*/            if(i == 65)
                    {
/*1467*/                atArrayRead(((ASTree) (obj)), expr.oprand2());
/*1467*/                return;
                    }
/*1468*/            if(i == 362 || i == 363)
                    {
/*1469*/                atPlusPlus(i, ((ASTree) (obj)), expr, true);
/*1469*/                return;
                    }
/*1470*/            if(i == 33)
                    {
/*1471*/                booleanExpr(false, expr);
/*1472*/                bytecode.addIndex(7);
/*1473*/                bytecode.addIconst(1);
/*1474*/                bytecode.addOpcode(167);
/*1475*/                bytecode.addIndex(4);
/*1476*/                bytecode.addIconst(0);
/*1476*/                return;
                    }
/*1478*/            if(i != 67)
                    {
/*1481*/                expr.oprand1().accept(this);
/*1482*/                int j = typePrecedence(exprType);
/*1483*/                if(arrayDim > 0)
/*1484*/                    badType(expr);
/*1486*/                if(i == 45)
                        {
/*1487*/                    if(j == 0)
                            {
/*1488*/                        bytecode.addOpcode(119);
/*1488*/                        return;
                            }
/*1489*/                    if(j == 1)
                            {
/*1490*/                        bytecode.addOpcode(118);
/*1490*/                        return;
                            }
/*1491*/                    if(j == 2)
                            {
/*1492*/                        bytecode.addOpcode(117);
/*1492*/                        return;
                            }
/*1493*/                    if(j == 3)
                            {
/*1494*/                        bytecode.addOpcode(116);
/*1495*/                        exprType = 324;
/*1495*/                        return;
                            } else
                            {
/*1498*/                        badType(expr);
/*1498*/                        return;
                            }
                        }
/*1500*/                if(i == 126)
                        {
/*1501*/                    if(j == 3)
                            {
/*1502*/                        bytecode.addIconst(-1);
/*1503*/                        bytecode.addOpcode(130);
/*1504*/                        exprType = 324;
/*1504*/                        return;
                            }
/*1506*/                    if(j == 2)
                            {
/*1507*/                        bytecode.addLconst(-1L);
/*1508*/                        bytecode.addOpcode(131);
/*1508*/                        return;
                            } else
                            {
/*1511*/                        badType(expr);
/*1511*/                        return;
                            }
                        }
/*1514*/                if(i == 43)
                        {
/*1515*/                    if(j == -1)
                            {
/*1516*/                        badType(expr);
/*1516*/                        return;
                            }
/*1521*/                    break label0;
                        }
                    }
/*1521*/            fatal();
                }
            }

            protected static void badType(Expr expr)
                throws CompileError
            {
/*1526*/        throw new CompileError((new StringBuilder("invalid type for ")).append(expr.getName()).toString());
            }

            public abstract void atCallExpr(CallExpr callexpr)
                throws CompileError;

            protected abstract void atFieldRead(ASTree astree)
                throws CompileError;

            public void atClassObject(Expr expr)
                throws CompileError
            {
/*1534*/        if(!((expr = expr.oprand1()) instanceof Symbol))
/*1536*/            throw new CompileError("fatal error: badly parsed .class expr");
                int i;
/*1538*/        if((expr = ((Symbol)expr).get()).startsWith("["))
                {
/*1540*/            if((i = expr.indexOf("[L")) >= 0)
                    {
/*1542*/                String s = expr.substring(i + 2, expr.length() - 1);
/*1543*/                String s1 = resolveClassName(s);
/*1544*/                if(!s.equals(s1))
                        {
/*1549*/                    s1 = MemberResolver.jvmToJavaName(s1);
/*1550*/                    expr = new StringBuffer();
/*1551*/                    while(i-- >= 0) 
/*1552*/                        expr.append('[');
/*1554*/                    expr.append('L').append(s1).append(';');
/*1555*/                    expr = expr.toString();
                        }
                    }
                } else
                {
/*1560*/            expr = MemberResolver.jvmToJavaName(expr = resolveClassName(MemberResolver.javaToJvmName(expr)));
                }
/*1564*/        atClassObject2(expr);
/*1565*/        exprType = 307;
/*1566*/        arrayDim = 0;
/*1567*/        className = "java/lang/Class";
            }

            protected void atClassObject2(String s)
                throws CompileError
            {
/*1573*/        int i = bytecode.currentPc();
/*1574*/        bytecode.addLdc(s);
/*1575*/        bytecode.addInvokestatic("java.lang.Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
/*1577*/        s = bytecode.currentPc();
/*1578*/        bytecode.addOpcode(167);
/*1579*/        int j = bytecode.currentPc();
/*1580*/        bytecode.addIndex(0);
/*1582*/        bytecode.addExceptionHandler(i, s, bytecode.currentPc(), "java.lang.ClassNotFoundException");
/*1601*/        bytecode.growStack(1);
/*1602*/        bytecode.addInvokestatic("javassist.runtime.DotClass", "fail", "(Ljava/lang/ClassNotFoundException;)Ljava/lang/NoClassDefFoundError;");
/*1605*/        bytecode.addOpcode(191);
/*1606*/        bytecode.write16bit(j, (bytecode.currentPc() - j) + 1);
            }

            public void atArrayRead(ASTree astree, ASTree astree1)
                throws CompileError
            {
/*1612*/        arrayAccess(astree, astree1);
/*1613*/        bytecode.addOpcode(getArrayReadOp(exprType, arrayDim));
            }

            protected void arrayAccess(ASTree astree, ASTree astree1)
                throws CompileError
            {
/*1619*/        astree.accept(this);
/*1620*/        astree = exprType;
                int i;
/*1621*/        if((i = arrayDim) == 0)
/*1623*/            throw new CompileError("bad array access");
/*1625*/        String s = className;
/*1627*/        astree1.accept(this);
/*1628*/        if(typePrecedence(exprType) != 3 || arrayDim > 0)
                {
/*1629*/            throw new CompileError("bad array index");
                } else
                {
/*1631*/            exprType = astree;
/*1632*/            arrayDim = i - 1;
/*1633*/            className = s;
/*1634*/            return;
                }
            }

            protected static int getArrayReadOp(int i, int j)
            {
/*1637*/        if(j > 0)
/*1638*/            return 50;
/*1640*/        switch(i)
                {
/*1642*/        case 312: 
/*1642*/            return 49;

/*1644*/        case 317: 
/*1644*/            return 48;

/*1646*/        case 326: 
/*1646*/            return 47;

/*1648*/        case 324: 
/*1648*/            return 46;

/*1650*/        case 334: 
/*1650*/            return 53;

/*1652*/        case 306: 
/*1652*/            return 52;

/*1655*/        case 301: 
/*1655*/        case 303: 
/*1655*/            return 51;
                }
/*1657*/        return 50;
            }

            protected static int getArrayWriteOp(int i, int j)
            {
/*1662*/        if(j > 0)
/*1663*/            return 83;
/*1665*/        switch(i)
                {
/*1667*/        case 312: 
/*1667*/            return 82;

/*1669*/        case 317: 
/*1669*/            return 81;

/*1671*/        case 326: 
/*1671*/            return 80;

/*1673*/        case 324: 
/*1673*/            return 79;

/*1675*/        case 334: 
/*1675*/            return 86;

/*1677*/        case 306: 
/*1677*/            return 85;

/*1680*/        case 301: 
/*1680*/        case 303: 
/*1680*/            return 84;
                }
/*1682*/        return 83;
            }

            private void atPlusPlus(int i, ASTree astree, Expr expr, boolean flag)
                throws CompileError
            {
                boolean flag1;
/*1689*/        if(flag1 = astree == null)
/*1691*/            astree = expr.oprand2();
/*1693*/        if(astree instanceof Variable)
                {
/*1694*/            Declarator declarator = ((Variable)astree).getDeclarator();
/*1695*/            astree = exprType = declarator.getType();
/*1696*/            arrayDim = declarator.getArrayDim();
/*1697*/            int j = getLocalVar(declarator);
/*1698*/            if(arrayDim > 0)
/*1699*/                badType(expr);
/*1701*/            if(astree == 312)
                    {
/*1702*/                bytecode.addDload(j);
/*1703*/                if(flag && flag1)
/*1704*/                    bytecode.addOpcode(92);
/*1706*/                bytecode.addDconst(1.0D);
/*1707*/                bytecode.addOpcode(i != 362 ? 103 : 99);
/*1708*/                if(flag && !flag1)
/*1709*/                    bytecode.addOpcode(92);
/*1711*/                bytecode.addDstore(j);
                    } else
/*1713*/            if(astree == 326)
                    {
/*1714*/                bytecode.addLload(j);
/*1715*/                if(flag && flag1)
/*1716*/                    bytecode.addOpcode(92);
/*1718*/                bytecode.addLconst(1L);
/*1719*/                bytecode.addOpcode(i != 362 ? 101 : 97);
/*1720*/                if(flag && !flag1)
/*1721*/                    bytecode.addOpcode(92);
/*1723*/                bytecode.addLstore(j);
                    } else
/*1725*/            if(astree == 317)
                    {
/*1726*/                bytecode.addFload(j);
/*1727*/                if(flag && flag1)
/*1728*/                    bytecode.addOpcode(89);
/*1730*/                bytecode.addFconst(1.0F);
/*1731*/                bytecode.addOpcode(i != 362 ? 102 : 98);
/*1732*/                if(flag && !flag1)
/*1733*/                    bytecode.addOpcode(89);
/*1735*/                bytecode.addFstore(j);
                    } else
/*1737*/            if(astree == 303 || astree == 306 || astree == 334 || astree == 324)
                    {
/*1738*/                if(flag && flag1)
/*1739*/                    bytecode.addIload(j);
/*1741*/                i = i != 362 ? -1 : 1;
/*1742*/                if(j > 255)
                        {
/*1743*/                    bytecode.addOpcode(196);
/*1744*/                    bytecode.addOpcode(132);
/*1745*/                    bytecode.addIndex(j);
/*1746*/                    bytecode.addIndex(i);
                        } else
                        {
/*1749*/                    bytecode.addOpcode(132);
/*1750*/                    bytecode.add(j);
/*1751*/                    bytecode.add(i);
                        }
/*1754*/                if(flag && !flag1)
/*1755*/                    bytecode.addIload(j);
                    } else
                    {
/*1758*/                badType(expr);
/*1759*/                return;
                    }
                } else
                {
                    Expr expr1;
/*1761*/            if((astree instanceof Expr) && (expr1 = (Expr)astree).getOperator() == 65)
                    {
/*1764*/                atArrayPlusPlus(i, flag1, expr1, flag);
/*1765*/                return;
                    }
/*1769*/            atFieldPlusPlus(i, flag1, astree, expr, flag);
                }
            }

            public void atArrayPlusPlus(int i, boolean flag, Expr expr, boolean flag1)
                throws CompileError
            {
/*1776*/        arrayAccess(expr.oprand1(), expr.oprand2());
/*1777*/        int j = exprType;
                int k;
/*1778*/        if((k = arrayDim) > 0)
/*1780*/            badType(expr);
/*1782*/        bytecode.addOpcode(92);
/*1783*/        bytecode.addOpcode(getArrayReadOp(j, arrayDim));
/*1784*/        byte byte0 = ((byte)(is2word(j, k) ? 94 : 91));
/*1785*/        atPlusPlusCore(byte0, flag1, i, flag, expr);
/*1786*/        bytecode.addOpcode(getArrayWriteOp(j, k));
            }

            protected void atPlusPlusCore(int i, boolean flag, int j, boolean flag1, Expr expr)
                throws CompileError
            {
/*1793*/        int k = exprType;
/*1795*/        if(flag && flag1)
/*1796*/            bytecode.addOpcode(i);
/*1798*/        if(k == 324 || k == 303 || k == 306 || k == 334)
                {
/*1799*/            bytecode.addIconst(1);
/*1800*/            bytecode.addOpcode(j != 362 ? 100 : 96);
/*1801*/            exprType = 324;
                } else
/*1803*/        if(k == 326)
                {
/*1804*/            bytecode.addLconst(1L);
/*1805*/            bytecode.addOpcode(j != 362 ? 101 : 97);
                } else
/*1807*/        if(k == 317)
                {
/*1808*/            bytecode.addFconst(1.0F);
/*1809*/            bytecode.addOpcode(j != 362 ? 102 : 98);
                } else
/*1811*/        if(k == 312)
                {
/*1812*/            bytecode.addDconst(1.0D);
/*1813*/            bytecode.addOpcode(j != 362 ? 103 : 99);
                } else
                {
/*1816*/            badType(expr);
                }
/*1818*/        if(flag && !flag1)
/*1819*/            bytecode.addOpcode(i);
            }

            protected abstract void atFieldPlusPlus(int i, boolean flag, ASTree astree, Expr expr, boolean flag1)
                throws CompileError;

            public abstract void atMember(Member member)
                throws CompileError;

            public void atVariable(Variable variable)
                throws CompileError
            {
/*1828*/        variable = variable.getDeclarator();
/*1829*/        exprType = variable.getType();
/*1830*/        arrayDim = variable.getArrayDim();
/*1831*/        className = variable.getClassName();
/*1832*/        variable = getLocalVar(variable);
/*1834*/        if(arrayDim > 0)
                {
/*1835*/            bytecode.addAload(variable);
/*1835*/            return;
                }
/*1837*/        switch(exprType)
                {
/*1839*/        case 307: 
/*1839*/            bytecode.addAload(variable);
/*1840*/            return;

/*1842*/        case 326: 
/*1842*/            bytecode.addLload(variable);
/*1843*/            return;

/*1845*/        case 317: 
/*1845*/            bytecode.addFload(variable);
/*1846*/            return;

/*1848*/        case 312: 
/*1848*/            bytecode.addDload(variable);
/*1849*/            return;
                }
/*1851*/        bytecode.addIload(variable);
            }

            public void atKeyword(Keyword keyword)
                throws CompileError
            {
/*1857*/        arrayDim = 0;
/*1858*/        switch(keyword = keyword.get())
                {
/*1861*/        case 410: 
/*1861*/            bytecode.addIconst(1);
/*1862*/            exprType = 301;
/*1863*/            return;

/*1865*/        case 411: 
/*1865*/            bytecode.addIconst(0);
/*1866*/            exprType = 301;
/*1867*/            return;

/*1869*/        case 412: 
/*1869*/            bytecode.addOpcode(1);
/*1870*/            exprType = 412;
/*1871*/            return;

/*1874*/        case 336: 
/*1874*/        case 339: 
/*1874*/            if(inStaticMethod)
/*1875*/                throw new CompileError((new StringBuilder("not-available: ")).append(keyword != 339 ? "super" : "this").toString());
/*1878*/            bytecode.addAload(0);
/*1879*/            exprType = 307;
/*1880*/            if(keyword == 339)
                    {
/*1881*/                className = getThisName();
/*1881*/                return;
                    } else
                    {
/*1883*/                className = getSuperName();
/*1884*/                return;
                    }
                }
/*1886*/        fatal();
            }

            public void atStringL(StringL stringl)
                throws CompileError
            {
/*1891*/        exprType = 307;
/*1892*/        arrayDim = 0;
/*1893*/        className = "java/lang/String";
/*1894*/        bytecode.addLdc(stringl.get());
            }

            public void atIntConst(IntConst intconst)
                throws CompileError
            {
/*1898*/        arrayDim = 0;
/*1899*/        long l = intconst.get();
/*1900*/        if((intconst = intconst.getType()) == 402 || intconst == 401)
                {
/*1902*/            exprType = intconst != 402 ? 306 : 324;
/*1903*/            bytecode.addIconst((int)l);
/*1903*/            return;
                } else
                {
/*1906*/            exprType = 326;
/*1907*/            bytecode.addLconst(l);
/*1909*/            return;
                }
            }

            public void atDoubleConst(DoubleConst doubleconst)
                throws CompileError
            {
/*1912*/        arrayDim = 0;
/*1913*/        if(doubleconst.getType() == 405)
                {
/*1914*/            exprType = 312;
/*1915*/            bytecode.addDconst(doubleconst.get());
/*1915*/            return;
                } else
                {
/*1918*/            exprType = 317;
/*1919*/            bytecode.addFconst((float)doubleconst.get());
/*1921*/            return;
                }
            }

            static final String javaLangObject = "java.lang.Object";
            static final String jvmJavaLangObject = "java/lang/Object";
            static final String javaLangString = "java.lang.String";
            static final String jvmJavaLangString = "java/lang/String";
            protected Bytecode bytecode;
            private int tempVar;
            TypeChecker typeChecker;
            protected boolean hasReturned;
            public boolean inStaticMethod;
            protected ArrayList breakList;
            protected ArrayList continueList;
            protected ReturnHook returnHooks;
            protected int exprType;
            protected int arrayDim;
            protected String className;
            static final int binOp[] = {
/* 934*/        43, 99, 98, 97, 96, 45, 103, 102, 101, 100, 
/* 934*/        42, 107, 106, 105, 104, 47, 111, 110, 109, 108, 
/* 934*/        37, 115, 114, 113, 112, 124, 0, 0, 129, 128, 
/* 934*/        94, 0, 0, 131, 130, 38, 0, 0, 127, 126, 
/* 934*/        364, 0, 0, 121, 120, 366, 0, 0, 123, 122, 
/* 934*/        370, 0, 0, 125, 124
            };
            private static final int ifOp[] = {
/*1183*/        358, 159, 160, 350, 160, 159, 357, 164, 163, 359, 
/*1183*/        162, 161, 60, 161, 162, 62, 163, 164
            };
            private static final int ifOp2[] = {
/*1190*/        358, 153, 154, 350, 154, 153, 357, 158, 157, 359, 
/*1190*/        156, 155, 60, 155, 156, 62, 157, 158
            };
            private static final int P_DOUBLE = 0;
            private static final int P_FLOAT = 1;
            private static final int P_LONG = 2;
            private static final int P_INT = 3;
            private static final int P_OTHER = -1;
            private static final int castOp[] = {
/*1296*/        0, 144, 143, 142, 141, 0, 140, 139, 138, 137, 
/*1296*/        0, 136, 135, 134, 133, 0
            };

}
