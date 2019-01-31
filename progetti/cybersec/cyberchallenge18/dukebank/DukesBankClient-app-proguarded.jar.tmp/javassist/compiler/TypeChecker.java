// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeChecker.java

package javassist.compiler;

import javassist.*;
import javassist.bytecode.*;
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
import javassist.compiler.ast.InstanceOfExpr;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Variable;
import javassist.compiler.ast.Visitor;

// Referenced classes of package javassist.compiler:
//            CodeGen, CompileError, MemberResolver, NoFieldException, 
//            TokenId

public class TypeChecker extends Visitor
    implements Opcode, TokenId
{

            public TypeChecker(CtClass ctclass, ClassPool classpool)
            {
/*  45*/        resolver = new MemberResolver(classpool);
/*  46*/        thisClass = ctclass;
/*  47*/        thisMethod = null;
            }

            protected static String argTypesToString(int ai[], int ai1[], String as[])
            {
                StringBuffer stringbuffer;
/*  56*/        (stringbuffer = new StringBuffer()).append('(');
                int i;
/*  58*/        if((i = ai.length) > 0)
                {
/*  60*/            int j = 0;
/*  62*/            do
                    {
/*  62*/                typeToString(stringbuffer, ai[j], ai1[j], as[j]);
/*  63*/                if(++j >= i)
/*  64*/                    break;
/*  64*/                stringbuffer.append(',');
                    } while(true);
                }
/*  70*/        stringbuffer.append(')');
/*  71*/        return stringbuffer.toString();
            }

            protected static StringBuffer typeToString(StringBuffer stringbuffer, int i, int j, String s)
            {
/*  81*/        if(i == 307)
/*  82*/            i = MemberResolver.jvmToJavaName(s);
/*  83*/        else
/*  83*/        if(i == 412)
/*  84*/            i = "Object";
/*  87*/        else
/*  87*/            try
                    {
/*  87*/                i = MemberResolver.getTypeName(i);
                    }
/*  89*/            catch(CompileError _ex)
                    {
/*  90*/                i = "?";
                    }
/*  93*/        stringbuffer.append(i);
/*  94*/        while(j-- > 0) 
/*  95*/            stringbuffer.append("[]");
/*  97*/        return stringbuffer;
            }

            public void setThisMethod(MethodInfo methodinfo)
            {
/* 104*/        thisMethod = methodinfo;
            }

            protected static void fatal()
                throws CompileError
            {
/* 108*/        throw new CompileError("fatal");
            }

            protected String getThisName()
            {
/* 115*/        return MemberResolver.javaToJvmName(thisClass.getName());
            }

            protected String getSuperName()
                throws CompileError
            {
/* 122*/        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(thisClass).getName());
            }

            protected String resolveClassName(ASTList astlist)
                throws CompileError
            {
/* 132*/        return resolver.resolveClassName(astlist);
            }

            protected String resolveClassName(String s)
                throws CompileError
            {
/* 139*/        return resolver.resolveJvmClassName(s);
            }

            public void atNewExpr(NewExpr newexpr)
                throws CompileError
            {
/* 143*/        if(newexpr.isArray())
                {
/* 144*/            atNewArrayExpr(newexpr);
/* 144*/            return;
                } else
                {
                    CtClass ctclass;
/* 146*/            String s = (ctclass = resolver.lookupClassByName(newexpr.getClassName())).getName();
/* 148*/            newexpr = newexpr.getArguments();
/* 149*/            atMethodCallCore(ctclass, "<init>", newexpr);
/* 150*/            exprType = 307;
/* 151*/            arrayDim = 0;
/* 152*/            className = MemberResolver.javaToJvmName(s);
/* 154*/            return;
                }
            }

            public void atNewArrayExpr(NewExpr newexpr)
                throws CompileError
            {
/* 157*/        int i = newexpr.getArrayType();
/* 158*/        ASTList astlist = newexpr.getArraySize();
/* 159*/        ASTList astlist1 = newexpr.getClassName();
/* 160*/        if((newexpr = newexpr.getInitializer()) != null)
/* 162*/            newexpr.accept(this);
/* 164*/        if(astlist.length() > 1)
                {
/* 165*/            atMultiNewArray(i, astlist1, astlist);
/* 165*/            return;
                }
/* 167*/        if((newexpr = astlist.head()) != null)
/* 169*/            newexpr.accept(this);
/* 171*/        exprType = i;
/* 172*/        arrayDim = 1;
/* 173*/        if(i == 307)
                {
/* 174*/            className = resolveClassName(astlist1);
/* 174*/            return;
                } else
                {
/* 176*/            className = null;
/* 178*/            return;
                }
            }

            public void atArrayInit(ArrayInit arrayinit)
                throws CompileError
            {
/* 181*/        arrayinit = arrayinit;
/* 182*/        do
                {
/* 182*/            if(arrayinit == null)
/* 183*/                break;
/* 183*/            ASTree astree = arrayinit.head();
/* 184*/            arrayinit = arrayinit.tail();
/* 185*/            if(astree != null)
/* 186*/                astree.accept(this);
                } while(true);
            }

            protected void atMultiNewArray(int i, ASTList astlist, ASTList astlist1)
                throws CompileError
            {
/* 194*/        int j = astlist1.length();
                ASTree astree;
/* 195*/        for(; astlist1 != null && (astree = astlist1.head()) != null; astlist1 = astlist1.tail())
/* 201*/            astree.accept(this);

/* 204*/        exprType = i;
/* 205*/        arrayDim = j;
/* 206*/        if(i == 307)
                {
/* 207*/            className = resolveClassName(astlist);
/* 207*/            return;
                } else
                {
/* 209*/            className = null;
/* 210*/            return;
                }
            }

            public void atAssignExpr(AssignExpr assignexpr)
                throws CompileError
            {
/* 214*/        int i = assignexpr.getOperator();
/* 215*/        ASTree astree = assignexpr.oprand1();
/* 216*/        ASTree astree1 = assignexpr.oprand2();
/* 217*/        if(astree instanceof Variable)
                {
/* 218*/            atVariableAssign(assignexpr, i, (Variable)astree, ((Variable)astree).getDeclarator(), astree1);
/* 218*/            return;
                }
                Expr expr;
/* 222*/        if((astree instanceof Expr) && (expr = (Expr)astree).getOperator() == 65)
                {
/* 225*/            atArrayAssign(assignexpr, i, (Expr)astree, astree1);
/* 226*/            return;
                } else
                {
/* 230*/            atFieldAssign(assignexpr, i, astree, astree1);
/* 232*/            return;
                }
            }

            private void atVariableAssign(Expr expr, int i, Variable variable, Declarator declarator, ASTree astree)
                throws CompileError
            {
/* 242*/        expr = declarator.getType();
/* 243*/        int j = declarator.getArrayDim();
/* 244*/        declarator = declarator.getClassName();
/* 246*/        if(i != 61)
/* 247*/            atVariable(variable);
/* 249*/        astree.accept(this);
/* 250*/        exprType = expr;
/* 251*/        arrayDim = j;
/* 252*/        className = declarator;
            }

            private void atArrayAssign(Expr expr, int i, Expr expr1, ASTree astree)
                throws CompileError
            {
/* 258*/        atArrayRead(expr1.oprand1(), expr1.oprand2());
/* 259*/        expr = exprType;
/* 260*/        i = arrayDim;
/* 261*/        expr1 = className;
/* 262*/        astree.accept(this);
/* 263*/        exprType = expr;
/* 264*/        arrayDim = i;
/* 265*/        className = expr1;
            }

            protected void atFieldAssign(Expr expr, int i, ASTree astree, ASTree astree1)
                throws CompileError
            {
/* 271*/        expr = fieldAccess(astree);
/* 272*/        atFieldRead(expr);
/* 273*/        expr = exprType;
/* 274*/        i = arrayDim;
/* 275*/        astree = className;
/* 276*/        astree1.accept(this);
/* 277*/        exprType = expr;
/* 278*/        arrayDim = i;
/* 279*/        className = astree;
            }

            public void atCondExpr(CondExpr condexpr)
                throws CompileError
            {
/* 283*/        booleanExpr(condexpr.condExpr());
/* 284*/        condexpr.thenExpr().accept(this);
/* 285*/        int i = exprType;
/* 286*/        int j = arrayDim;
/* 288*/        condexpr.elseExpr().accept(this);
/* 290*/        if(j == 0 && j == arrayDim)
                {
/* 291*/            if(CodeGen.rightIsStrong(i, exprType))
                    {
/* 292*/                condexpr.setThen(new CastExpr(exprType, 0, condexpr.thenExpr()));
/* 292*/                return;
                    }
/* 293*/            if(CodeGen.rightIsStrong(exprType, i))
                    {
/* 294*/                condexpr.setElse(new CastExpr(i, 0, condexpr.elseExpr()));
/* 295*/                exprType = i;
                    }
                }
            }

            public void atBinExpr(BinExpr binexpr)
                throws CompileError
            {
                int i;
                int j;
/* 306*/        if((j = CodeGen.lookupBinOp(i = binexpr.getOperator())) >= 0)
                {
/* 311*/            if(i == 43)
                    {
                        Object obj;
/* 312*/                if((obj = atPlusExpr(binexpr)) != null)
                        {
/* 317*/                    obj = CallExpr.makeCall(Expr.make(46, ((ASTree) (obj)), new Member("toString")), null);
/* 319*/                    binexpr.setOprand1(((ASTree) (obj)));
/* 320*/                    binexpr.setOprand2(null);
/* 321*/                    className = "java/lang/String";
                        }
/* 323*/                return;
                    }
/* 325*/            ASTree astree = binexpr.oprand1();
/* 326*/            ASTree astree1 = binexpr.oprand2();
/* 327*/            astree.accept(this);
/* 328*/            int k = exprType;
/* 329*/            astree1.accept(this);
/* 330*/            if(!isConstant(binexpr, i, astree, astree1))
/* 331*/                computeBinExprType(binexpr, i, k);
/* 332*/            return;
                } else
                {
/* 337*/            booleanExpr(binexpr);
/* 339*/            return;
                }
            }

            private Expr atPlusExpr(BinExpr binexpr)
                throws CompileError
            {
/* 346*/        ASTree astree = binexpr.oprand1();
                ASTree astree1;
/* 347*/        if((astree1 = binexpr.oprand2()) == null)
                {
/* 351*/            astree.accept(this);
/* 352*/            return null;
                }
                Expr expr;
/* 355*/        if(isPlusExpr(astree))
                {
/* 356*/            if((expr = atPlusExpr((BinExpr)astree)) != null)
                    {
/* 358*/                astree1.accept(this);
/* 359*/                exprType = 307;
/* 360*/                arrayDim = 0;
/* 361*/                className = "java/lang/StringBuffer";
/* 362*/                return makeAppendCall(expr, astree1);
                    }
                } else
                {
/* 366*/            astree.accept(this);
                }
/* 368*/        int i = exprType;
/* 369*/        int j = arrayDim;
/* 370*/        String s = className;
/* 371*/        astree1.accept(this);
/* 373*/        if(isConstant(binexpr, 43, astree, astree1))
/* 374*/            return null;
/* 376*/        if(i == 307 && j == 0 && "java/lang/String".equals(s) || exprType == 307 && arrayDim == 0 && "java/lang/String".equals(className))
                {
/* 379*/            binexpr = ASTList.make(new Symbol("java"), new Symbol("lang"), new Symbol("StringBuffer"));
/* 381*/            binexpr = new NewExpr(binexpr, null);
/* 382*/            exprType = 307;
/* 383*/            arrayDim = 0;
/* 384*/            className = "java/lang/StringBuffer";
/* 385*/            return makeAppendCall(makeAppendCall(binexpr, astree), astree1);
                } else
                {
/* 388*/            computeBinExprType(binexpr, 43, i);
/* 389*/            return null;
                }
            }

            private boolean isConstant(BinExpr binexpr, int i, ASTree astree, ASTree astree1)
                throws CompileError
            {
/* 396*/        astree = stripPlusExpr(astree);
/* 397*/        astree1 = stripPlusExpr(astree1);
/* 398*/        Object obj = null;
/* 399*/        if((astree instanceof StringL) && (astree1 instanceof StringL) && i == 43)
/* 400*/            obj = new StringL((new StringBuilder()).append(((StringL)astree).get()).append(((StringL)astree1).get()).toString());
/* 402*/        else
/* 402*/        if(astree instanceof IntConst)
/* 403*/            obj = ((IntConst)astree).compute(i, astree1);
/* 404*/        else
/* 404*/        if(astree instanceof DoubleConst)
/* 405*/            obj = ((DoubleConst)astree).compute(i, astree1);
/* 407*/        if(obj == null)
                {
/* 408*/            return false;
                } else
                {
/* 410*/            binexpr.setOperator(43);
/* 411*/            binexpr.setOprand1(((ASTree) (obj)));
/* 412*/            binexpr.setOprand2(null);
/* 413*/            ((ASTree) (obj)).accept(this);
/* 414*/            return true;
                }
            }

            static ASTree stripPlusExpr(ASTree astree)
            {
                Object obj;
                int i;
/* 421*/        if(astree instanceof BinExpr)
                {
/* 422*/            if(((BinExpr) (obj = (BinExpr)astree)).getOperator() == 43 && ((BinExpr) (obj)).oprand2() == null)
/* 424*/                return ((BinExpr) (obj)).getLeft();
                } else
/* 426*/        if(astree instanceof Expr)
                {
/* 427*/            if((i = ((Expr) (obj = (Expr)astree)).getOperator()) == 35)
                    {
/* 430*/                if((obj = getConstantFieldValue((Member)((Expr) (obj)).oprand2())) != null)
/* 432*/                    return ((ASTree) (obj));
                    } else
/* 434*/            if(i == 43 && ((Expr) (obj)).getRight() == null)
/* 435*/                return ((Expr) (obj)).getLeft();
                } else
/* 437*/        if((astree instanceof Member) && (obj = getConstantFieldValue((Member)astree)) != null)
/* 440*/            return ((ASTree) (obj));
/* 443*/        return astree;
            }

            private static ASTree getConstantFieldValue(Member member)
            {
/* 451*/        return getConstantFieldValue(member.getField());
            }

            public static ASTree getConstantFieldValue(CtField ctfield)
            {
/* 455*/        if(ctfield == null)
/* 456*/            return null;
/* 458*/        if((ctfield = ((CtField) (ctfield.getConstantValue()))) == null)
/* 460*/            return null;
/* 462*/        if(ctfield instanceof String)
/* 463*/            return new StringL((String)ctfield);
/* 464*/        if((ctfield instanceof Double) || (ctfield instanceof Float))
                {
/* 465*/            char c = (ctfield instanceof Double) ? '\u0195' : '\u0194';
/* 467*/            return new DoubleConst(((Number)ctfield).doubleValue(), c);
                }
/* 469*/        if(ctfield instanceof Number)
                {
/* 470*/            char c1 = (ctfield instanceof Long) ? '\u0193' : '\u0192';
/* 471*/            return new IntConst(((Number)ctfield).longValue(), c1);
                }
/* 473*/        if(ctfield instanceof Boolean)
/* 474*/            return new Keyword(((Boolean)ctfield).booleanValue() ? 410 : 411);
/* 477*/        else
/* 477*/            return null;
            }

            private static boolean isPlusExpr(ASTree astree)
            {
/* 481*/        if(astree instanceof BinExpr)
/* 482*/            return (astree = (astree = (BinExpr)astree).getOperator()) == 43;
/* 487*/        else
/* 487*/            return false;
            }

            private static Expr makeAppendCall(ASTree astree, ASTree astree1)
            {
/* 491*/        return CallExpr.makeCall(Expr.make(46, astree, new Member("append")), new ASTList(astree1));
            }

            private void computeBinExprType(BinExpr binexpr, int i, int j)
                throws CompileError
            {
/* 499*/        int k = exprType;
/* 500*/        if(i == 364 || i == 366 || i == 370)
/* 501*/            exprType = j;
/* 503*/        else
/* 503*/            insertCast(binexpr, j, k);
/* 505*/        if(CodeGen.isP_INT(exprType))
/* 506*/            exprType = 324;
            }

            private void booleanExpr(ASTree astree)
                throws CompileError
            {
                int i;
/* 512*/        if((i = CodeGen.getCompOperator(astree)) == 358)
                {
/* 514*/            (astree = (BinExpr)astree).oprand1().accept(this);
/* 516*/            i = exprType;
/* 517*/            int j = arrayDim;
/* 518*/            astree.oprand2().accept(this);
/* 519*/            if(j == 0 && arrayDim == 0)
/* 520*/                insertCast(astree, i, exprType);
                } else
/* 522*/        if(i == 33)
/* 523*/            ((Expr)astree).oprand1().accept(this);
/* 524*/        else
/* 524*/        if(i == 369 || i == 368)
                {
/* 525*/            (astree = (BinExpr)astree).oprand1().accept(this);
/* 527*/            astree.oprand2().accept(this);
                } else
                {
/* 530*/            astree.accept(this);
                }
/* 532*/        exprType = 301;
/* 533*/        arrayDim = 0;
            }

            private void insertCast(BinExpr binexpr, int i, int j)
                throws CompileError
            {
/* 539*/        if(CodeGen.rightIsStrong(i, j))
                {
/* 540*/            binexpr.setLeft(new CastExpr(j, 0, binexpr.oprand1()));
/* 540*/            return;
                } else
                {
/* 542*/            exprType = i;
/* 543*/            return;
                }
            }

            public void atCastExpr(CastExpr castexpr)
                throws CompileError
            {
/* 546*/        String s = resolveClassName(castexpr.getClassName());
/* 547*/        castexpr.getOprand().accept(this);
/* 548*/        exprType = castexpr.getType();
/* 549*/        arrayDim = castexpr.getArrayDim();
/* 550*/        className = s;
            }

            public void atInstanceOfExpr(InstanceOfExpr instanceofexpr)
                throws CompileError
            {
/* 554*/        instanceofexpr.getOprand().accept(this);
/* 555*/        exprType = 301;
/* 556*/        arrayDim = 0;
            }

            public void atExpr(Expr expr)
                throws CompileError
            {
/* 563*/label0:
                {
/* 563*/            int i = expr.getOperator();
/* 564*/            ASTree astree = expr.oprand1();
/* 565*/            if(i == 46)
                    {
/* 566*/                if((i = ((Symbol)expr.oprand2()).get()).equals("length"))
/* 569*/                    try
                            {
/* 569*/                        atArrayLength(expr);
/* 574*/                        break label0;
                            }
/* 571*/                    catch(NoFieldException _ex) { }
/* 575*/                else
/* 575*/                if(i.equals("class"))
                        {
/* 576*/                    atClassObject(expr);
/* 576*/                    break label0;
                        }
/* 578*/                atFieldRead(expr);
/* 579*/                return;
                    }
/* 580*/            if(i == 35)
                    {
/* 581*/                if((i = ((Symbol)expr.oprand2()).get()).equals("class"))
                        {
/* 583*/                    atClassObject(expr);
                        } else
                        {
/* 585*/                    atFieldRead(expr);
/* 586*/                    return;
                        }
                    } else
                    {
/* 587*/                if(i == 65)
                        {
/* 588*/                    atArrayRead(astree, expr.oprand2());
/* 588*/                    return;
                        }
/* 589*/                if(i == 362 || i == 363)
                        {
/* 590*/                    atPlusPlus(i, astree, expr);
/* 590*/                    return;
                        }
/* 591*/                if(i == 33)
                        {
/* 592*/                    booleanExpr(expr);
/* 592*/                    return;
                        }
/* 593*/                if(i == 67)
                        {
/* 594*/                    fatal();
/* 594*/                    return;
                        }
/* 596*/                astree.accept(this);
/* 597*/                if(!isConstant(expr, i, astree) && (i == 45 || i == 126) && CodeGen.isP_INT(exprType))
/* 600*/                    exprType = 324;
                    }
                }
            }

            private boolean isConstant(Expr expr, int i, ASTree astree)
            {
/* 605*/        if((astree = stripPlusExpr(astree)) instanceof IntConst)
                {
/* 607*/            long l = (astree = (IntConst)astree).get();
/* 609*/            if(i == 45)
/* 610*/                l = -l;
/* 611*/            else
/* 611*/            if(i == 126)
/* 612*/                l = ~l;
/* 614*/            else
/* 614*/                return false;
/* 616*/            astree.set(l);
                } else
/* 618*/        if(astree instanceof DoubleConst)
                {
/* 619*/            astree = (DoubleConst)astree;
/* 620*/            if(i == 45)
/* 621*/                astree.set(-astree.get());
/* 623*/            else
/* 623*/                return false;
                } else
                {
/* 626*/            return false;
                }
/* 628*/        expr.setOperator(43);
/* 629*/        return true;
            }

            public void atCallExpr(CallExpr callexpr)
                throws CompileError
            {
                String s;
                CtClass ctclass;
                Object obj;
                ASTList astlist;
                ASTree astree;
/* 633*/        s = null;
/* 634*/        ctclass = null;
/* 635*/        obj = callexpr.oprand1();
/* 636*/        astlist = (ASTList)callexpr.oprand2();
/* 638*/        if(obj instanceof Member)
                {
/* 639*/            s = ((Member)obj).get();
/* 640*/            ctclass = thisClass;
/* 640*/            break MISSING_BLOCK_LABEL_295;
                }
/* 642*/        if(obj instanceof Keyword)
                {
/* 643*/            s = "<init>";
/* 644*/            if(((Keyword)obj).get() == 336)
/* 645*/                ctclass = MemberResolver.getSuperclass(thisClass);
/* 647*/            else
/* 647*/                ctclass = thisClass;
/* 647*/            break MISSING_BLOCK_LABEL_295;
                }
/* 649*/        if(!(obj instanceof Expr))
/* 650*/            break MISSING_BLOCK_LABEL_292;
/* 650*/        s = ((Symbol)((Expr) (obj = (Expr)obj)).oprand2()).get();
                int i;
/* 652*/        if((i = ((Expr) (obj)).getOperator()) == 35)
                {
/* 654*/            ctclass = resolver.lookupClass(((Symbol)((Expr) (obj)).oprand1()).get(), false);
/* 654*/            break MISSING_BLOCK_LABEL_295;
                }
/* 657*/        if(i != 46)
/* 658*/            break MISSING_BLOCK_LABEL_286;
/* 658*/        astree = ((Expr) (obj)).oprand1();
/* 660*/        astree.accept(this);
/* 673*/        break MISSING_BLOCK_LABEL_234;
/* 662*/        JVM INSTR dup ;
                NoFieldException nofieldexception;
/* 663*/        nofieldexception;
/* 663*/        getExpr();
/* 663*/        astree;
/* 663*/        JVM INSTR if_acmpeq 187;
                   goto _L1 _L2
_L1:
/* 664*/        break MISSING_BLOCK_LABEL_184;
_L2:
/* 664*/        break MISSING_BLOCK_LABEL_187;
/* 664*/        throw nofieldexception;
/* 667*/        exprType = 307;
/* 668*/        arrayDim = 0;
/* 669*/        className = nofieldexception.getField();
/* 670*/        ((Expr) (obj)).setOperator(35);
/* 671*/        ((Expr) (obj)).setOprand1(new Symbol(MemberResolver.jvmToJavaName(className)));
/* 675*/        if(arrayDim > 0)
/* 676*/            ctclass = resolver.lookupClass("java.lang.Object", true);
/* 677*/        else
/* 677*/        if(exprType == 307)
/* 678*/            ctclass = resolver.lookupClassByJvmName(className);
/* 680*/        else
/* 680*/            badMethod();
/* 681*/        break MISSING_BLOCK_LABEL_295;
/* 683*/        badMethod();
/* 684*/        break MISSING_BLOCK_LABEL_295;
/* 686*/        fatal();
/* 688*/        MemberResolver.Method method = atMethodCallCore(ctclass, s, astlist);
/* 690*/        callexpr.setMethod(method);
/* 691*/        return;
            }

            private static void badMethod()
                throws CompileError
            {
/* 694*/        throw new CompileError("bad method");
            }

            public MemberResolver.Method atMethodCallCore(CtClass ctclass, String s, ASTList astlist)
                throws CompileError
            {
                int i;
/* 705*/        int ai[] = new int[i = getMethodArgsLength(astlist)];
/* 707*/        int ai1[] = new int[i];
/* 708*/        String as[] = new String[i];
/* 709*/        atMethodArgs(astlist, ai, ai1, as);
/* 711*/        if((astlist = resolver.lookupMethod(ctclass, thisClass, thisMethod, s, ai, ai1, as)) == null)
                {
/* 715*/            ctclass = ctclass.getName();
/* 716*/            astlist = argTypesToString(ai, ai1, as);
/* 718*/            if(s.equals("<init>"))
/* 719*/                ctclass = (new StringBuilder("cannot find constructor ")).append(ctclass).append(astlist).toString();
/* 721*/            else
/* 721*/                ctclass = (new StringBuilder()).append(s).append(astlist).append(" not found in ").append(ctclass).toString();
/* 723*/            throw new CompileError(ctclass);
                } else
                {
/* 726*/            ctclass = ((MemberResolver.Method) (astlist)).info.getDescriptor();
/* 727*/            setReturnType(ctclass);
/* 728*/            return astlist;
                }
            }

            public int getMethodArgsLength(ASTList astlist)
            {
/* 732*/        return ASTList.length(astlist);
            }

            public void atMethodArgs(ASTList astlist, int ai[], int ai1[], String as[])
                throws CompileError
            {
/* 737*/        int i = 0;
/* 738*/        for(; astlist != null; astlist = astlist.tail())
                {
                    ASTree astree;
/* 739*/            (astree = astlist.head()).accept(this);
/* 741*/            ai[i] = exprType;
/* 742*/            ai1[i] = arrayDim;
/* 743*/            as[i] = className;
/* 744*/            i++;
                }

            }

            void setReturnType(String s)
                throws CompileError
            {
                int i;
/* 750*/        if((i = s.indexOf(')')) < 0)
/* 752*/            badMethod();
/* 754*/        int j = s.charAt(++i);
/* 755*/        int k = 0;
/* 756*/        for(; j == 91; j = s.charAt(++i))
/* 757*/            k++;

/* 761*/        arrayDim = k;
/* 762*/        if(j == 76)
                {
/* 763*/            if((j = s.indexOf(';', i + 1)) < 0)
/* 765*/                badMethod();
/* 767*/            exprType = 307;
/* 768*/            className = s.substring(i + 1, j);
/* 769*/            return;
                } else
                {
/* 771*/            exprType = MemberResolver.descToType(j);
/* 772*/            className = null;
/* 774*/            return;
                }
            }

            private void atFieldRead(ASTree astree)
                throws CompileError
            {
/* 777*/        atFieldRead(fieldAccess(astree));
            }

            private void atFieldRead(CtField ctfield)
                throws CompileError
            {
/* 781*/        ctfield = (ctfield = ctfield.getFieldInfo2()).getDescriptor();
/* 784*/        int i = 0;
/* 785*/        int j = 0;
                char c;
/* 786*/        for(c = ctfield.charAt(0); c == '['; c = ctfield.charAt(++i))
/* 788*/            j++;

/* 792*/        arrayDim = j;
/* 793*/        exprType = MemberResolver.descToType(c);
/* 795*/        if(c == 'L')
                {
/* 796*/            className = ctfield.substring(i + 1, ctfield.indexOf(';', i + 1));
/* 796*/            return;
                } else
                {
/* 798*/            className = null;
/* 799*/            return;
                }
            }

            protected CtField fieldAccess(ASTree astree)
                throws CompileError
            {
                Object obj;
                String s;
/* 807*/        if(!(astree instanceof Member))
/* 808*/            break MISSING_BLOCK_LABEL_57;
/* 808*/        s = ((Member) (obj = (Member)astree)).get();
                Object obj1;
/* 811*/        if(Modifier.isStatic(((CtField) (obj1 = thisClass.getField(s))).getModifiers()))
/* 813*/            ((Member) (obj)).setField(((CtField) (obj1)));
/* 815*/        return ((CtField) (obj1));
/* 817*/        JVM INSTR pop ;
/* 819*/        throw new NoFieldException(s, astree);
/* 822*/        if(!(astree instanceof Expr))
/* 823*/            break MISSING_BLOCK_LABEL_240;
                int i;
/* 823*/        if((i = ((Expr) (obj = (Expr)astree)).getOperator()) == 35)
                {
/* 826*/            obj1 = (Member)((Expr) (obj)).oprand2();
/* 827*/            astree = resolver.lookupField(((Symbol)((Expr) (obj)).oprand1()).get(), ((Symbol) (obj1)));
/* 829*/            ((Member) (obj1)).setField(astree);
/* 830*/            return astree;
                }
/* 832*/        if(i != 46)
/* 834*/            break MISSING_BLOCK_LABEL_240;
/* 834*/        ((Expr) (obj)).oprand1().accept(this);
/* 845*/        break MISSING_BLOCK_LABEL_161;
/* 836*/        JVM INSTR dup ;
/* 837*/        obj1;
/* 837*/        getExpr();
/* 837*/        ((Expr) (obj)).oprand1();
/* 837*/        JVM INSTR if_acmpeq 150;
                   goto _L1 _L2
_L1:
/* 838*/        break MISSING_BLOCK_LABEL_147;
_L2:
/* 838*/        break MISSING_BLOCK_LABEL_150;
/* 838*/        throw obj1;
/* 844*/        return fieldAccess2(((Expr) (obj)), ((NoFieldException) (obj1)).getField());
/* 847*/        obj1 = null;
/* 849*/        if(exprType == 307 && arrayDim == 0)
/* 850*/            return resolver.lookupFieldByJvmName(className, (Symbol)((Expr) (obj)).oprand2());
                  goto _L3
/* 853*/        JVM INSTR dup ;
/* 854*/        astree;
/* 854*/        obj1;
_L3:
/* 873*/        if((astree = ((Expr) (obj)).oprand1()) instanceof Symbol)
/* 875*/            return fieldAccess2(((Expr) (obj)), ((Symbol)astree).get());
/* 877*/        if(obj1 != null)
/* 878*/            throw obj1;
/* 882*/        throw new CompileError("bad filed access");
            }

            private CtField fieldAccess2(Expr expr, String s)
                throws CompileError
            {
/* 886*/        Member member = (Member)expr.oprand2();
/* 887*/        CtField ctfield = resolver.lookupFieldByJvmName2(s, member, expr);
/* 888*/        expr.setOperator(35);
/* 889*/        expr.setOprand1(new Symbol(MemberResolver.jvmToJavaName(s)));
/* 890*/        member.setField(ctfield);
/* 891*/        return ctfield;
            }

            public void atClassObject(Expr expr)
                throws CompileError
            {
/* 895*/        exprType = 307;
/* 896*/        arrayDim = 0;
/* 897*/        className = "java/lang/Class";
            }

            public void atArrayLength(Expr expr)
                throws CompileError
            {
/* 901*/        expr.oprand1().accept(this);
/* 902*/        if(arrayDim == 0)
                {
/* 903*/            throw new NoFieldException("length", expr);
                } else
                {
/* 905*/            exprType = 324;
/* 906*/            arrayDim = 0;
/* 907*/            return;
                }
            }

            public void atArrayRead(ASTree astree, ASTree astree1)
                throws CompileError
            {
/* 912*/        astree.accept(this);
/* 913*/        astree = exprType;
/* 914*/        int i = arrayDim;
/* 915*/        String s = className;
/* 916*/        astree1.accept(this);
/* 917*/        exprType = astree;
/* 918*/        arrayDim = i - 1;
/* 919*/        className = s;
            }

            private void atPlusPlus(int i, ASTree astree, Expr expr)
                throws CompileError
            {
/* 925*/        if((i = astree != null ? 0 : 1) != 0)
/* 927*/            astree = expr.oprand2();
/* 929*/        if(astree instanceof Variable)
                {
/* 930*/            i = ((Variable)astree).getDeclarator();
/* 931*/            exprType = i.getType();
/* 932*/            arrayDim = i.getArrayDim();
/* 933*/            return;
                }
/* 935*/        if((astree instanceof Expr) && (i = (Expr)astree).getOperator() == 65)
                {
/* 938*/            atArrayRead(i.oprand1(), i.oprand2());
/* 940*/            if((i = exprType) == 324 || i == 303 || i == 306 || i == 334)
/* 942*/                exprType = 324;
/* 944*/            return;
                } else
                {
/* 948*/            atFieldPlusPlus(astree);
/* 950*/            return;
                }
            }

            protected void atFieldPlusPlus(ASTree astree)
                throws CompileError
            {
/* 954*/        astree = fieldAccess(astree);
/* 955*/        atFieldRead(astree);
/* 956*/        if((astree = exprType) == 324 || astree == 303 || astree == 306 || astree == 334)
/* 958*/            exprType = 324;
            }

            public void atMember(Member member)
                throws CompileError
            {
/* 962*/        atFieldRead(member);
            }

            public void atVariable(Variable variable)
                throws CompileError
            {
/* 966*/        variable = variable.getDeclarator();
/* 967*/        exprType = variable.getType();
/* 968*/        arrayDim = variable.getArrayDim();
/* 969*/        className = variable.getClassName();
            }

            public void atKeyword(Keyword keyword)
                throws CompileError
            {
/* 973*/        arrayDim = 0;
/* 974*/        switch(keyword = keyword.get())
                {
/* 978*/        case 410: 
/* 978*/        case 411: 
/* 978*/            exprType = 301;
/* 979*/            return;

/* 981*/        case 412: 
/* 981*/            exprType = 412;
/* 982*/            return;

/* 985*/        case 336: 
/* 985*/        case 339: 
/* 985*/            exprType = 307;
/* 986*/            if(keyword == 339)
                    {
/* 987*/                className = getThisName();
/* 987*/                return;
                    } else
                    {
/* 989*/                className = getSuperName();
/* 990*/                return;
                    }
                }
/* 992*/        fatal();
            }

            public void atStringL(StringL stringl)
                throws CompileError
            {
/* 997*/        exprType = 307;
/* 998*/        arrayDim = 0;
/* 999*/        className = "java/lang/String";
            }

            public void atIntConst(IntConst intconst)
                throws CompileError
            {
/*1003*/        arrayDim = 0;
/*1004*/        if((intconst = intconst.getType()) == 402 || intconst == 401)
                {
/*1006*/            exprType = intconst != 402 ? 306 : 324;
/*1006*/            return;
                } else
                {
/*1008*/            exprType = 326;
/*1009*/            return;
                }
            }

            public void atDoubleConst(DoubleConst doubleconst)
                throws CompileError
            {
/*1012*/        arrayDim = 0;
/*1013*/        if(doubleconst.getType() == 405)
                {
/*1014*/            exprType = 312;
/*1014*/            return;
                } else
                {
/*1016*/            exprType = 317;
/*1017*/            return;
                }
            }

            static final String javaLangObject = "java.lang.Object";
            static final String jvmJavaLangObject = "java/lang/Object";
            static final String jvmJavaLangString = "java/lang/String";
            static final String jvmJavaLangClass = "java/lang/Class";
            protected int exprType;
            protected int arrayDim;
            protected String className;
            protected MemberResolver resolver;
            protected CtClass thisClass;
            protected MethodInfo thisMethod;
}
