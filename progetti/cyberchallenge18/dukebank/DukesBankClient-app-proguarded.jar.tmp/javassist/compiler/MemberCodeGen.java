// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberCodeGen.java

package javassist.compiler;

import java.util.ArrayList;
import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.ArrayInit;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.Pair;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            CodeGen, AccessorMaker, CompileError, MemberResolver, 
//            NoFieldException, TypeChecker

public class MemberCodeGen extends CodeGen
{
    static class JsrHook2 extends CodeGen.ReturnHook
    {

                protected boolean doit(Bytecode bytecode, int i)
                {
/* 161*/            switch(i)
                    {
/* 165*/            case 176: 
/* 165*/                bytecode.addAstore(var);
                        break;

/* 168*/            case 172: 
/* 168*/                bytecode.addIstore(var);
                        break;

/* 171*/            case 173: 
/* 171*/                bytecode.addLstore(var);
                        break;

/* 174*/            case 175: 
/* 174*/                bytecode.addDstore(var);
                        break;

/* 177*/            case 174: 
/* 177*/                bytecode.addFstore(var);
                        break;

/* 180*/            default:
/* 180*/                throw new RuntimeException("fatal");

/* 161*/            case 177: 
                        break;
                    }
/* 183*/            bytecode.addOpcode(167);
/* 184*/            bytecode.addIndex((target - bytecode.currentPc()) + 3);
/* 185*/            return true;
                }

                int var;
                int target;

                JsrHook2(CodeGen codegen, int ai[])
                {
/* 155*/            super(codegen);
/* 156*/            target = ai[0];
/* 157*/            var = ai[1];
                }
    }

    static class JsrHook extends CodeGen.ReturnHook
    {

                private int getVar(int i)
                {
/*  98*/            if(var < 0)
                    {
/*  99*/                var = cgen.getMaxLocals();
/* 100*/                cgen.incMaxLocals(i);
                    }
/* 103*/            return var;
                }

                private void jsrJmp(Bytecode bytecode)
                {
/* 107*/            bytecode.addOpcode(167);
/* 108*/            jsrList.add(new int[] {
/* 108*/                bytecode.currentPc(), var
                    });
/* 109*/            bytecode.addIndex(0);
                }

                protected boolean doit(Bytecode bytecode, int i)
                {
/* 113*/            switch(i)
                    {
/* 115*/            case 177: 
/* 115*/                jsrJmp(bytecode);
                        break;

/* 118*/            case 176: 
/* 118*/                bytecode.addAstore(getVar(1));
/* 119*/                jsrJmp(bytecode);
/* 120*/                bytecode.addAload(var);
                        break;

/* 123*/            case 172: 
/* 123*/                bytecode.addIstore(getVar(1));
/* 124*/                jsrJmp(bytecode);
/* 125*/                bytecode.addIload(var);
                        break;

/* 128*/            case 173: 
/* 128*/                bytecode.addLstore(getVar(2));
/* 129*/                jsrJmp(bytecode);
/* 130*/                bytecode.addLload(var);
                        break;

/* 133*/            case 175: 
/* 133*/                bytecode.addDstore(getVar(2));
/* 134*/                jsrJmp(bytecode);
/* 135*/                bytecode.addDload(var);
                        break;

/* 138*/            case 174: 
/* 138*/                bytecode.addFstore(getVar(1));
/* 139*/                jsrJmp(bytecode);
/* 140*/                bytecode.addFload(var);
                        break;

/* 143*/            default:
/* 143*/                throw new RuntimeException("fatal");
                    }
/* 146*/            return false;
                }

                ArrayList jsrList;
                CodeGen cgen;
                int var;

                JsrHook(CodeGen codegen)
                {
/*  91*/            super(codegen);
/*  92*/            jsrList = new ArrayList();
/*  93*/            cgen = codegen;
/*  94*/            var = -1;
                }
    }


            public MemberCodeGen(Bytecode bytecode, CtClass ctclass, ClassPool classpool)
            {
/*  35*/        super(bytecode);
/*  36*/        resolver = new MemberResolver(classpool);
/*  37*/        thisClass = ctclass;
/*  38*/        thisMethod = null;
            }

            public int getMajorVersion()
            {
                ClassFile classfile;
/*  46*/        if((classfile = thisClass.getClassFile2()) == null)
/*  48*/            return ClassFile.MAJOR_VERSION;
/*  50*/        else
/*  50*/            return classfile.getMajorVersion();
            }

            public void setThisMethod(CtMethod ctmethod)
            {
/*  57*/        thisMethod = ctmethod.getMethodInfo2();
/*  58*/        if(typeChecker != null)
/*  59*/            typeChecker.setThisMethod(thisMethod);
            }

            public CtClass getThisClass()
            {
/*  62*/        return thisClass;
            }

            protected String getThisName()
            {
/*  68*/        return MemberResolver.javaToJvmName(thisClass.getName());
            }

            protected String getSuperName()
                throws CompileError
            {
/*  75*/        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(thisClass).getName());
            }

            protected void insertDefaultSuperCall()
                throws CompileError
            {
/*  80*/        bytecode.addAload(0);
/*  81*/        bytecode.addInvokespecial(MemberResolver.getSuperclass(thisClass), "<init>", "()V");
            }

            protected void atTryStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 190*/        Bytecode bytecode = this.bytecode;
                Stmnt stmnt1;
/* 191*/        if((stmnt1 = (Stmnt)stmnt.getLeft()) == null)
/* 193*/            return;
/* 195*/        ASTList astlist = (ASTList)stmnt.getRight().getLeft();
/* 196*/        stmnt = (Stmnt)stmnt.getRight().getRight().getLeft();
/* 197*/        ArrayList arraylist = new ArrayList();
/* 199*/        JsrHook jsrhook = null;
/* 200*/        if(stmnt != null)
/* 201*/            jsrhook = new JsrHook(this);
/* 203*/        int j = bytecode.currentPc();
/* 204*/        stmnt1.accept(this);
/* 205*/        int i = bytecode.currentPc();
/* 206*/        if(j == i)
/* 207*/            throw new CompileError("empty try block");
                boolean flag;
/* 209*/        if(flag = !hasReturned)
                {
/* 211*/            bytecode.addOpcode(167);
/* 212*/            arraylist.add(new Integer(bytecode.currentPc()));
/* 213*/            bytecode.addIndex(0);
                }
/* 216*/        int k = getMaxLocals();
/* 217*/        incMaxLocals(1);
/* 218*/        do
                {
/* 218*/            if(astlist == null)
/* 220*/                break;
/* 220*/            Object obj = (Pair)astlist.head();
/* 221*/            astlist = astlist.tail();
/* 222*/            Declarator declarator = (Declarator)((Pair) (obj)).getLeft();
/* 223*/            obj = (Stmnt)((Pair) (obj)).getRight();
/* 225*/            declarator.setLocalVar(k);
/* 227*/            CtClass ctclass = resolver.lookupClassByJvmName(declarator.getClassName());
/* 228*/            declarator.setClassName(MemberResolver.javaToJvmName(ctclass.getName()));
/* 229*/            bytecode.addExceptionHandler(j, i, bytecode.currentPc(), ctclass);
/* 230*/            bytecode.growStack(1);
/* 231*/            bytecode.addAstore(k);
/* 232*/            hasReturned = false;
/* 233*/            if(obj != null)
/* 234*/                ((Stmnt) (obj)).accept(this);
/* 236*/            if(!hasReturned)
                    {
/* 237*/                bytecode.addOpcode(167);
/* 238*/                arraylist.add(new Integer(bytecode.currentPc()));
/* 239*/                bytecode.addIndex(0);
/* 240*/                flag = true;
                    }
                } while(true);
/* 244*/        if(stmnt != null)
                {
/* 245*/            jsrhook.remove(this);
/* 247*/            int l = bytecode.currentPc();
/* 248*/            bytecode.addExceptionHandler(j, l, l, 0);
/* 249*/            bytecode.growStack(1);
/* 250*/            bytecode.addAstore(k);
/* 251*/            hasReturned = false;
/* 252*/            stmnt.accept(this);
/* 253*/            if(!hasReturned)
                    {
/* 254*/                bytecode.addAload(k);
/* 255*/                bytecode.addOpcode(191);
                    }
/* 258*/            addFinally(jsrhook.jsrList, stmnt);
                }
/* 261*/        int i1 = bytecode.currentPc();
/* 262*/        patchGoto(arraylist, i1);
/* 263*/        hasReturned = !flag;
/* 264*/        if(stmnt != null && flag)
/* 266*/            stmnt.accept(this);
            }

            private void addFinally(ArrayList arraylist, Stmnt stmnt)
                throws CompileError
            {
/* 276*/        Bytecode bytecode = this.bytecode;
/* 277*/        int i = arraylist.size();
/* 278*/        for(int j = 0; j < i; j++)
                {
                    int ai[];
/* 279*/            int k = (ai = (int[])arraylist.get(j))[0];
/* 281*/            bytecode.write16bit(k, (bytecode.currentPc() - k) + 1);
/* 282*/            JsrHook2 jsrhook2 = new JsrHook2(this, ai);
/* 283*/            stmnt.accept(this);
/* 284*/            jsrhook2.remove(this);
/* 285*/            if(!hasReturned)
                    {
/* 286*/                bytecode.addOpcode(167);
/* 287*/                bytecode.addIndex((k + 3) - bytecode.currentPc());
                    }
                }

            }

            public void atNewExpr(NewExpr newexpr)
                throws CompileError
            {
/* 293*/        if(newexpr.isArray())
                {
/* 294*/            atNewArrayExpr(newexpr);
/* 294*/            return;
                } else
                {
                    CtClass ctclass;
/* 296*/            String s = (ctclass = resolver.lookupClassByName(newexpr.getClassName())).getName();
/* 298*/            newexpr = newexpr.getArguments();
/* 299*/            bytecode.addNew(s);
/* 300*/            bytecode.addOpcode(89);
/* 302*/            atMethodCallCore(ctclass, "<init>", newexpr, false, true, -1, null);
/* 305*/            exprType = 307;
/* 306*/            arrayDim = 0;
/* 307*/            className = MemberResolver.javaToJvmName(s);
/* 309*/            return;
                }
            }

            public void atNewArrayExpr(NewExpr newexpr)
                throws CompileError
            {
/* 312*/        int i = newexpr.getArrayType();
/* 313*/        Object obj = newexpr.getArraySize();
/* 314*/        ASTList astlist = newexpr.getClassName();
/* 315*/        newexpr = newexpr.getInitializer();
/* 316*/        if(((ASTList) (obj)).length() > 1)
                {
/* 317*/            if(newexpr != null)
                    {
/* 318*/                throw new CompileError("sorry, multi-dimensional array initializer for new is not supported");
                    } else
                    {
/* 322*/                atMultiNewArray(i, astlist, ((ASTList) (obj)));
/* 323*/                return;
                    }
                } else
                {
/* 326*/            obj = ((ASTList) (obj)).head();
/* 327*/            atNewArrayExpr2(i, ((ASTree) (obj)), Declarator.astToClassName(astlist, '/'), newexpr);
/* 328*/            return;
                }
            }

            private void atNewArrayExpr2(int i, ASTree astree, String s, ArrayInit arrayinit)
                throws CompileError
            {
/* 332*/        if(arrayinit == null)
                {
/* 333*/            if(astree == null)
/* 334*/                throw new CompileError("no array size");
/* 336*/            astree.accept(this);
                } else
/* 338*/        if(astree == null)
                {
/* 339*/            astree = arrayinit.length();
/* 340*/            bytecode.addIconst(astree);
                } else
                {
/* 343*/            throw new CompileError("unnecessary array size specified for new");
                }
/* 346*/        if(i == 307)
                {
/* 347*/            astree = resolveClassName(s);
/* 348*/            bytecode.addAnewarray(MemberResolver.jvmToJavaName(astree));
                } else
                {
/* 351*/            astree = null;
/* 352*/            s = 0;
/* 353*/            switch(i)
                    {
/* 355*/            case 301: 
/* 355*/                s = 4;
                        break;

/* 358*/            case 306: 
/* 358*/                s = 5;
                        break;

/* 361*/            case 317: 
/* 361*/                s = 6;
                        break;

/* 364*/            case 312: 
/* 364*/                s = 7;
                        break;

/* 367*/            case 303: 
/* 367*/                s = 8;
                        break;

/* 370*/            case 334: 
/* 370*/                s = 9;
                        break;

/* 373*/            case 324: 
/* 373*/                s = 10;
                        break;

/* 376*/            case 326: 
/* 376*/                s = 11;
                        break;

/* 379*/            default:
/* 379*/                badNewExpr();
                        break;
                    }
/* 383*/            bytecode.addOpcode(188);
/* 384*/            bytecode.add(s);
                }
/* 387*/        if(arrayinit != null)
                {
/* 388*/            s = arrayinit.length();
/* 389*/            arrayinit = arrayinit;
/* 390*/            for(int j = 0; j < s; j++)
                    {
/* 391*/                bytecode.addOpcode(89);
/* 392*/                bytecode.addIconst(j);
/* 393*/                arrayinit.head().accept(this);
/* 394*/                if(!isRefType(i))
/* 395*/                    atNumCastExpr(exprType, i);
/* 397*/                bytecode.addOpcode(getArrayWriteOp(i, 0));
/* 398*/                arrayinit = arrayinit.tail();
                    }

                }
/* 402*/        exprType = i;
/* 403*/        arrayDim = 1;
/* 404*/        className = astree;
            }

            private static void badNewExpr()
                throws CompileError
            {
/* 408*/        throw new CompileError("bad new expression");
            }

            protected void atArrayVariableAssign(ArrayInit arrayinit, int i, int j, String s)
                throws CompileError
            {
/* 413*/        atNewArrayExpr2(i, null, s, arrayinit);
            }

            public void atArrayInit(ArrayInit arrayinit)
                throws CompileError
            {
/* 417*/        throw new CompileError("array initializer is not supported");
            }

            protected void atMultiNewArray(int i, ASTList astlist, ASTList astlist1)
                throws CompileError
            {
/* 424*/        int k = astlist1.length();
/* 425*/        int j = 0;
                ASTree astree;
/* 425*/        for(; astlist1 != null && (astree = astlist1.head()) != null; astlist1 = astlist1.tail())
                {
/* 430*/            j++;
/* 431*/            astree.accept(this);
/* 432*/            if(exprType != 324)
/* 433*/                throw new CompileError("bad type for array size");
                }

/* 437*/        exprType = i;
/* 438*/        arrayDim = k;
                String s;
/* 439*/        if(i == 307)
                {
/* 440*/            className = resolveClassName(astlist);
/* 441*/            s = toJvmArrayName(className, k);
                } else
                {
/* 444*/            s = toJvmTypeName(i, k);
                }
/* 446*/        bytecode.addMultiNewarray(s, j);
            }

            public void atCallExpr(CallExpr callexpr)
                throws CompileError
            {
                String s;
                CtClass ctclass;
                Object obj;
                ASTList astlist;
                boolean flag;
                boolean flag1;
                int i;
/* 450*/        s = null;
/* 451*/        ctclass = null;
/* 452*/        obj = callexpr.oprand1();
/* 453*/        astlist = (ASTList)callexpr.oprand2();
/* 454*/        flag = false;
/* 455*/        flag1 = false;
/* 456*/        i = -1;
/* 458*/        callexpr = callexpr.getMethod();
/* 459*/        if(obj instanceof Member)
                {
/* 460*/            s = ((Member)obj).get();
/* 461*/            ctclass = thisClass;
/* 462*/            if(inStaticMethod || callexpr != null && callexpr.isStatic())
                    {
/* 463*/                flag = true;
                    } else
                    {
/* 465*/                i = bytecode.currentPc();
/* 466*/                bytecode.addAload(0);
                    }
/* 466*/            break MISSING_BLOCK_LABEL_376;
                }
/* 469*/        if(obj instanceof Keyword)
                {
/* 470*/            flag1 = true;
/* 471*/            s = "<init>";
/* 472*/            ctclass = thisClass;
/* 473*/            if(inStaticMethod)
/* 474*/                throw new CompileError("a constructor cannot be static");
/* 476*/            bytecode.addAload(0);
/* 478*/            if(((Keyword)obj).get() == 336)
/* 479*/                ctclass = MemberResolver.getSuperclass(ctclass);
/* 479*/            break MISSING_BLOCK_LABEL_376;
                }
/* 481*/        if(!(obj instanceof Expr))
/* 482*/            break MISSING_BLOCK_LABEL_373;
/* 482*/        s = ((Symbol)((Expr) (obj = (Expr)obj)).oprand2()).get();
                int j;
/* 484*/        if((j = ((Expr) (obj)).getOperator()) == 35)
                {
/* 486*/            ctclass = resolver.lookupClass(((Symbol)((Expr) (obj)).oprand1()).get(), false);
/* 488*/            flag = true;
/* 488*/            break MISSING_BLOCK_LABEL_376;
                }
/* 490*/        if(j != 46)
/* 491*/            break MISSING_BLOCK_LABEL_367;
/* 491*/        if(((obj = ((Expr) (obj)).oprand1()) instanceof Keyword) && ((Keyword)obj).get() == 336)
/* 494*/            flag1 = true;
/* 497*/        ((ASTree) (obj)).accept(this);
/* 508*/        break MISSING_BLOCK_LABEL_315;
/* 499*/        JVM INSTR dup ;
/* 500*/        flag;
/* 500*/        getExpr();
/* 500*/        obj;
/* 500*/        JVM INSTR if_acmpeq 291;
                   goto _L1 _L2
_L1:
/* 501*/        break MISSING_BLOCK_LABEL_288;
_L2:
/* 501*/        break MISSING_BLOCK_LABEL_291;
/* 501*/        throw flag;
/* 504*/        exprType = 307;
/* 505*/        arrayDim = 0;
/* 506*/        className = flag.getField();
/* 507*/        flag = true;
/* 510*/        if(arrayDim > 0)
/* 511*/            ctclass = resolver.lookupClass("java.lang.Object", true);
/* 512*/        else
/* 512*/        if(exprType == 307)
/* 513*/            ctclass = resolver.lookupClassByJvmName(className);
/* 515*/        else
/* 515*/            badMethod();
/* 516*/        break MISSING_BLOCK_LABEL_376;
/* 518*/        badMethod();
/* 519*/        break MISSING_BLOCK_LABEL_376;
/* 521*/        fatal();
/* 523*/        atMethodCallCore(ctclass, s, astlist, flag, flag1, i, callexpr);
/* 525*/        return;
            }

            private static void badMethod()
                throws CompileError
            {
/* 528*/        throw new CompileError("bad method");
            }

            public void atMethodCallCore(CtClass ctclass, String s, ASTList astlist, boolean flag, boolean flag1, int i, MemberResolver.Method method)
                throws CompileError
            {
                int j;
/* 542*/        int ai[] = new int[j = getMethodArgsLength(astlist)];
/* 544*/        int ai1[] = new int[j];
/* 545*/        String as[] = new String[j];
/* 547*/        if(!flag && method != null && method.isStatic())
                {
/* 548*/            bytecode.addOpcode(87);
/* 549*/            flag = true;
                }
/* 552*/        int k = bytecode.getStackDepth();
/* 555*/        atMethodArgs(astlist, ai, ai1, as);
/* 558*/        astlist = (bytecode.getStackDepth() - k) + 1;
/* 560*/        if(method == null)
/* 561*/            method = resolver.lookupMethod(ctclass, thisClass, thisMethod, s, ai, ai1, as);
/* 564*/        if(method == null)
                {
/* 566*/            if(s.equals("<init>"))
/* 567*/                ctclass = "constructor not found";
/* 569*/            else
/* 569*/                ctclass = (new StringBuilder("Method ")).append(s).append(" not found in ").append(ctclass.getName()).toString();
/* 572*/            throw new CompileError(ctclass);
                } else
                {
/* 575*/            atMethodCallCore2(ctclass, s, flag, flag1, i, astlist, method);
/* 577*/            return;
                }
            }

            private void atMethodCallCore2(CtClass ctclass, String s, boolean flag, boolean flag1, int i, int j, MemberResolver.Method method)
                throws CompileError
            {
/* 585*/        CtClass ctclass1 = method.declaring;
/* 586*/        String s1 = (method = method.info).getDescriptor();
/* 588*/        int k = method.getAccessFlags();
/* 590*/        if(s.equals("<init>"))
                {
/* 591*/            flag1 = true;
/* 592*/            if(ctclass1 != ctclass)
/* 593*/                throw new CompileError((new StringBuilder("no such constructor: ")).append(ctclass.getName()).toString());
/* 595*/            if(ctclass1 != thisClass && AccessFlag.isPrivate(k))
                    {
/* 596*/                s1 = getAccessibleConstructor(s1, ctclass1, method);
/* 597*/                bytecode.addOpcode(1);
                    }
                } else
/* 600*/        if(AccessFlag.isPrivate(k))
/* 601*/            if(ctclass1 == thisClass)
                    {
/* 602*/                flag1 = true;
                    } else
                    {
/* 604*/                flag1 = false;
/* 605*/                flag = true;
/* 606*/                String s2 = s1;
/* 607*/                if((k & 8) == 0)
/* 608*/                    s1 = Descriptor.insertParameter(ctclass1.getName(), s2);
/* 611*/                k = AccessFlag.setPackage(k) | 8;
/* 612*/                s = getAccessiblePrivate(s, s2, s1, method, ctclass1);
                    }
/* 616*/        boolean flag2 = false;
/* 617*/        if((k & 8) != 0)
                {
/* 618*/            if(!flag)
                    {
/* 624*/                flag = true;
/* 625*/                if(i >= 0)
/* 626*/                    bytecode.write(i, 0);
/* 628*/                else
/* 628*/                    flag2 = true;
                    }
/* 631*/            bytecode.addInvokestatic(ctclass1, s, s1);
                } else
/* 633*/        if(flag1)
                {
/* 634*/            bytecode.addInvokespecial(ctclass1, s, s1);
                } else
                {
/* 636*/            if(!Modifier.isPublic(ctclass1.getModifiers()) || ctclass1.isInterface() != ctclass.isInterface())
/* 638*/                ctclass1 = ctclass;
/* 640*/            if(ctclass1.isInterface())
                    {
/* 641*/                bytecode.addInvokeinterface(ctclass1, s, s1, j);
                    } else
                    {
/* 643*/                if(flag)
/* 644*/                    throw new CompileError((new StringBuilder()).append(s).append(" is not static").toString());
/* 646*/                bytecode.addInvokevirtual(ctclass1, s, s1);
                    }
                }
/* 649*/        setReturnType(s1, flag, flag2);
            }

            protected String getAccessiblePrivate(String s, String s1, String s2, MethodInfo methodinfo, CtClass ctclass)
                throws CompileError
            {
/* 664*/        if(isEnclosing(ctclass, thisClass) && (ctclass = ctclass.getAccessorMaker()) != null)
/* 667*/            return ctclass.getMethodAccessor(s, s1, s2, methodinfo);
/* 671*/        else
/* 671*/            throw new CompileError((new StringBuilder("Method ")).append(s).append(" is private").toString());
            }

            protected String getAccessibleConstructor(String s, CtClass ctclass, MethodInfo methodinfo)
                throws CompileError
            {
                AccessorMaker accessormaker;
/* 688*/        if(isEnclosing(ctclass, thisClass) && (accessormaker = ctclass.getAccessorMaker()) != null)
/* 691*/            return accessormaker.getConstructor(ctclass, s, methodinfo);
/* 694*/        else
/* 694*/            throw new CompileError((new StringBuilder("the called constructor is private in ")).append(ctclass.getName()).toString());
            }

            private boolean isEnclosing(CtClass ctclass, CtClass ctclass1)
            {
/* 700*/        while(ctclass1 != null) 
/* 701*/            if((ctclass1 = ctclass1.getDeclaringClass()) == ctclass)
/* 703*/                return true;
/* 706*/        break MISSING_BLOCK_LABEL_20;
/* 706*/        JVM INSTR pop ;
/* 707*/        return false;
            }

            public int getMethodArgsLength(ASTList astlist)
            {
/* 711*/        return ASTList.length(astlist);
            }

            public void atMethodArgs(ASTList astlist, int ai[], int ai1[], String as[])
                throws CompileError
            {
/* 716*/        int i = 0;
/* 717*/        for(; astlist != null; astlist = astlist.tail())
                {
                    ASTree astree;
/* 718*/            (astree = astlist.head()).accept(this);
/* 720*/            ai[i] = exprType;
/* 721*/            ai1[i] = arrayDim;
/* 722*/            as[i] = className;
/* 723*/            i++;
                }

            }

            void setReturnType(String s, boolean flag, boolean flag1)
                throws CompileError
            {
                int i;
/* 731*/        if((i = s.indexOf(')')) < 0)
/* 733*/            badMethod();
/* 735*/        int j = s.charAt(++i);
/* 736*/        int k = 0;
/* 737*/        for(; j == 91; j = s.charAt(++i))
/* 738*/            k++;

/* 742*/        arrayDim = k;
/* 743*/        if(j == 76)
                {
/* 744*/            if((j = s.indexOf(';', i + 1)) < 0)
/* 746*/                badMethod();
/* 748*/            exprType = 307;
/* 749*/            className = s.substring(i + 1, j);
                } else
                {
/* 752*/            exprType = MemberResolver.descToType(j);
/* 753*/            className = null;
                }
/* 756*/        j = exprType;
/* 757*/        if(flag && flag1)
                {
/* 759*/            if(is2word(j, k))
                    {
/* 760*/                bytecode.addOpcode(93);
/* 761*/                bytecode.addOpcode(88);
/* 762*/                bytecode.addOpcode(87);
/* 762*/                return;
                    }
/* 764*/            if(j == 344)
                    {
/* 765*/                bytecode.addOpcode(87);
/* 765*/                return;
                    }
/* 767*/            bytecode.addOpcode(95);
/* 768*/            bytecode.addOpcode(87);
                }
            }

            protected void atFieldAssign(Expr expr, int i, ASTree astree, ASTree astree1, boolean flag)
                throws CompileError
            {
/* 777*/        astree = fieldAccess(astree, false);
/* 778*/        boolean flag1 = resultStatic;
/* 779*/        if(i != 61 && !flag1)
/* 780*/            bytecode.addOpcode(89);
                int j;
/* 783*/        if(i == 61)
                {
/* 784*/            FieldInfo fieldinfo = astree.getFieldInfo2();
/* 785*/            setFieldType(fieldinfo);
                    AccessorMaker accessormaker;
/* 786*/            if((accessormaker = isAccessibleField(astree, fieldinfo)) == null)
/* 788*/                j = addFieldrefInfo(astree, fieldinfo);
/* 790*/            else
/* 790*/                j = 0;
                } else
                {
/* 793*/            j = atFieldRead(astree, flag1);
                }
/* 795*/        int k = exprType;
/* 796*/        int l = arrayDim;
/* 797*/        String s = className;
/* 799*/        atAssignCore(expr, i, astree1, k, l, s);
/* 801*/        expr = is2word(k, l);
/* 802*/        if(flag)
                {
/* 804*/            if(flag1)
/* 805*/                i = expr == 0 ? 89 : 92;
/* 807*/            else
/* 807*/                i = expr == 0 ? 90 : 93;
/* 809*/            bytecode.addOpcode(i);
                }
/* 812*/        atFieldAssignCore(astree, flag1, j, expr);
/* 814*/        exprType = k;
/* 815*/        arrayDim = l;
/* 816*/        className = s;
            }

            private void atFieldAssignCore(CtField ctfield, boolean flag, int i, boolean flag1)
                throws CompileError
            {
/* 823*/        if(i != 0)
                {
/* 824*/            if(flag)
                    {
/* 825*/                bytecode.add(179);
/* 826*/                bytecode.growStack(flag1 ? -2 : -1);
                    } else
                    {
/* 829*/                bytecode.add(181);
/* 830*/                bytecode.growStack(flag1 ? -3 : -2);
                    }
/* 833*/            bytecode.addIndex(i);
/* 833*/            return;
                } else
                {
/* 836*/            flag1 = (i = ctfield.getDeclaringClass()).getAccessorMaker();
/* 839*/            ctfield = ctfield.getFieldInfo2();
/* 840*/            ctfield = flag1.getFieldSetter(ctfield, flag);
/* 841*/            bytecode.addInvokestatic(i, ctfield.getName(), ctfield.getDescriptor());
/* 844*/            return;
                }
            }

            public void atMember(Member member)
                throws CompileError
            {
/* 849*/        atFieldRead(member);
            }

            protected void atFieldRead(ASTree astree)
                throws CompileError
            {
                CtField ctfield;
/* 854*/        if((ctfield = fieldAccess(astree, true)) == null)
                {
/* 856*/            atArrayLength(astree);
/* 857*/            return;
                }
/* 860*/        astree = resultStatic;
                ASTree astree1;
/* 861*/        if((astree1 = TypeChecker.getConstantFieldValue(ctfield)) == null)
                {
/* 863*/            atFieldRead(ctfield, astree);
/* 863*/            return;
                } else
                {
/* 865*/            astree1.accept(this);
/* 866*/            setFieldType(ctfield.getFieldInfo2());
/* 868*/            return;
                }
            }

            private void atArrayLength(ASTree astree)
                throws CompileError
            {
/* 871*/        if(arrayDim == 0)
                {
/* 872*/            throw new CompileError(".length applied to a non array");
                } else
                {
/* 874*/            bytecode.addOpcode(190);
/* 875*/            exprType = 324;
/* 876*/            arrayDim = 0;
/* 877*/            return;
                }
            }

            private int atFieldRead(CtField ctfield, boolean flag)
                throws CompileError
            {
/* 885*/        Object obj = ctfield.getFieldInfo2();
/* 886*/        boolean flag1 = setFieldType(((FieldInfo) (obj)));
                AccessorMaker accessormaker;
/* 887*/        if((accessormaker = isAccessibleField(ctfield, ((FieldInfo) (obj)))) != null)
                {
/* 889*/            obj = accessormaker.getFieldGetter(((FieldInfo) (obj)), flag);
/* 890*/            bytecode.addInvokestatic(ctfield.getDeclaringClass(), ((MethodInfo) (obj)).getName(), ((MethodInfo) (obj)).getDescriptor());
/* 892*/            return 0;
                }
/* 895*/        int i = addFieldrefInfo(ctfield, ((FieldInfo) (obj)));
/* 896*/        if(flag)
                {
/* 897*/            bytecode.add(178);
/* 898*/            bytecode.growStack(flag1 ? 2 : 1);
                } else
                {
/* 901*/            bytecode.add(180);
/* 902*/            bytecode.growStack(flag1 ? 1 : 0);
                }
/* 905*/        bytecode.addIndex(i);
/* 906*/        return i;
            }

            private AccessorMaker isAccessibleField(CtField ctfield, FieldInfo fieldinfo)
                throws CompileError
            {
/* 918*/        if(AccessFlag.isPrivate(fieldinfo.getAccessFlags()) && ctfield.getDeclaringClass() != thisClass)
                {
/* 920*/            fieldinfo = ctfield.getDeclaringClass();
/* 921*/            if(isEnclosing(fieldinfo, thisClass))
                    {
/* 922*/                if((ctfield = fieldinfo.getAccessorMaker()) != null)
/* 924*/                    return ctfield;
/* 926*/                else
/* 926*/                    throw new CompileError("fatal error.  bug?");
                    } else
                    {
/* 929*/                throw new CompileError((new StringBuilder("Field ")).append(ctfield.getName()).append(" in ").append(fieldinfo.getName()).append(" is private.").toString());
                    }
                } else
                {
/* 933*/            return null;
                }
            }

            private boolean setFieldType(FieldInfo fieldinfo)
                throws CompileError
            {
/* 942*/        fieldinfo = fieldinfo.getDescriptor();
/* 944*/        int i = 0;
/* 945*/        int j = 0;
                char c;
/* 946*/        for(c = fieldinfo.charAt(0); c == '['; c = fieldinfo.charAt(++i))
/* 948*/            j++;

/* 952*/        arrayDim = j;
/* 953*/        exprType = MemberResolver.descToType(c);
/* 955*/        if(c == 'L')
/* 956*/            className = fieldinfo.substring(i + 1, fieldinfo.indexOf(';', i + 1));
/* 958*/        else
/* 958*/            className = null;
/* 960*/        return fieldinfo = j != 0 || c != 'J' && c != 'D' ? 0 : 1;
            }

            private int addFieldrefInfo(CtField ctfield, FieldInfo fieldinfo)
            {
/* 965*/        ConstPool constpool = bytecode.getConstPool();
/* 966*/        ctfield = ctfield.getDeclaringClass().getName();
/* 967*/        ctfield = constpool.addClassInfo(ctfield);
/* 968*/        String s = fieldinfo.getName();
/* 969*/        fieldinfo = fieldinfo.getDescriptor();
/* 970*/        return constpool.addFieldrefInfo(ctfield, s, fieldinfo);
            }

            protected void atClassObject2(String s)
                throws CompileError
            {
/* 974*/        if(getMajorVersion() < 49)
                {
/* 975*/            super.atClassObject2(s);
/* 975*/            return;
                } else
                {
/* 977*/            bytecode.addLdc(bytecode.getConstPool().addClassInfo(s));
/* 978*/            return;
                }
            }

            protected void atFieldPlusPlus(int i, boolean flag, ASTree astree, Expr expr, boolean flag1)
                throws CompileError
            {
/* 984*/        astree = fieldAccess(astree, false);
                boolean flag2;
/* 985*/        if(!(flag2 = resultStatic))
/* 987*/            bytecode.addOpcode(89);
/* 989*/        int j = atFieldRead(astree, flag2);
                int k;
/* 990*/        boolean flag3 = is2word(k = exprType, arrayDim);
                byte byte0;
/* 994*/        if(flag2)
/* 995*/            byte0 = ((byte)(flag3 ? 92 : 89));
/* 997*/        else
/* 997*/            byte0 = ((byte)(flag3 ? 93 : 90));
/* 999*/        atPlusPlusCore(byte0, flag1, i, flag, expr);
/*1000*/        atFieldAssignCore(astree, flag2, j, flag3);
            }

            protected CtField fieldAccess(ASTree astree, boolean flag)
                throws CompileError
            {
                Object obj;
                CtField ctfield2;
/*1010*/        if(astree instanceof Member)
                {
/*1011*/            String s = ((Member)astree).get();
                    CtField ctfield;
/*1014*/            try
                    {
/*1014*/                ctfield = thisClass.getField(s);
                    }
/*1016*/            catch(NotFoundException _ex)
                    {
/*1018*/                throw new NoFieldException(s, astree);
                    }
                    boolean flag1;
/*1021*/            if(!(flag1 = Modifier.isStatic(ctfield.getModifiers())))
                    {
/*1023*/                if(inStaticMethod)
/*1024*/                    throw new CompileError((new StringBuilder("not available in a static method: ")).append(s).toString());
/*1027*/                bytecode.addAload(0);
                    }
/*1029*/            resultStatic = flag1;
/*1030*/            return ctfield;
                }
/*1032*/        if(!(astree instanceof Expr))
/*1033*/            break MISSING_BLOCK_LABEL_334;
                int i;
/*1033*/        if((i = ((Expr) (obj = (Expr)astree)).getOperator()) == 35)
                {
/*1040*/            CtField ctfield1 = resolver.lookupField(((Symbol)((Expr) (obj)).oprand1()).get(), (Symbol)((Expr) (obj)).oprand2());
/*1042*/            resultStatic = true;
/*1043*/            return ctfield1;
                }
/*1045*/        if(i != 46)
/*1046*/            break MISSING_BLOCK_LABEL_328;
/*1046*/        ctfield2 = null;
/*1048*/        ((Expr) (obj)).oprand1().accept(this);
/*1053*/        if(exprType == 307 && arrayDim == 0)
                {
/*1054*/            ctfield2 = resolver.lookupFieldByJvmName(className, (Symbol)((Expr) (obj)).oprand2());
/*1054*/            break MISSING_BLOCK_LABEL_251;
                }
/*1056*/        if(flag && arrayDim > 0 && ((Symbol)((Expr) (obj)).oprand2()).get().equals("length"))
/*1058*/            return null;
/*1060*/        badLvalue();
/*1062*/        if(flag = Modifier.isStatic(ctfield2.getModifiers()))
/*1064*/            bytecode.addOpcode(87);
/*1066*/        resultStatic = flag;
/*1067*/        return ctfield2;
/*1069*/        JVM INSTR dup ;
/*1070*/        flag;
/*1070*/        getExpr();
/*1070*/        ((Expr) (obj)).oprand1();
/*1070*/        JVM INSTR if_acmpeq 295;
                   goto _L1 _L2
_L1:
/*1071*/        break MISSING_BLOCK_LABEL_293;
_L2:
/*1071*/        break MISSING_BLOCK_LABEL_295;
/*1071*/        throw flag;
/*1077*/        obj = (Symbol)((Expr) (obj)).oprand2();
/*1078*/        flag = flag.getField();
/*1079*/        CtField ctfield3 = resolver.lookupFieldByJvmName2(flag, ((Symbol) (obj)), astree);
/*1080*/        resultStatic = true;
/*1081*/        return ctfield3;
/*1085*/        badLvalue();
/*1086*/        break MISSING_BLOCK_LABEL_337;
/*1088*/        badLvalue();
/*1090*/        resultStatic = false;
/*1091*/        return null;
            }

            private static void badLvalue()
                throws CompileError
            {
/*1095*/        throw new CompileError("bad l-value");
            }

            public CtClass[] makeParamList(MethodDecl methoddecl)
                throws CompileError
            {
                ASTList astlist;
/*1100*/        if((astlist = methoddecl.getParams()) == null)
                {
/*1102*/            methoddecl = new CtClass[0];
                } else
                {
/*1104*/            int i = 0;
/*1105*/            methoddecl = new CtClass[astlist.length()];
/*1106*/            for(; astlist != null; astlist = astlist.tail())
/*1107*/                methoddecl[i++] = resolver.lookupClass((Declarator)astlist.head());

                }
/*1112*/        return methoddecl;
            }

            public CtClass[] makeThrowsList(MethodDecl methoddecl)
                throws CompileError
            {
                ASTList astlist;
/*1117*/        if((astlist = methoddecl.getThrows()) == null)
/*1119*/            return null;
/*1121*/        int i = 0;
/*1122*/        methoddecl = new CtClass[astlist.length()];
/*1123*/        for(; astlist != null; astlist = astlist.tail())
/*1124*/            methoddecl[i++] = resolver.lookupClassByName((ASTList)astlist.head());

/*1128*/        return methoddecl;
            }

            protected String resolveClassName(ASTList astlist)
                throws CompileError
            {
/*1138*/        return resolver.resolveClassName(astlist);
            }

            protected String resolveClassName(String s)
                throws CompileError
            {
/*1145*/        return resolver.resolveJvmClassName(s);
            }

            protected MemberResolver resolver;
            protected CtClass thisClass;
            protected MethodInfo thisMethod;
            protected boolean resultStatic;
}
