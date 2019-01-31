// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Parser.java

package javassist.compiler;

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

// Referenced classes of package javassist.compiler:
//            CodeGen, CompileError, Lex, SymbolTable, 
//            SyntaxError, TokenId

public final class Parser
    implements TokenId
{

            public Parser(Lex lex1)
            {
/*  25*/        lex = lex1;
            }

            public final boolean hasMore()
            {
/*  28*/        return lex.lookAhead() >= 0;
            }

            public final ASTList parseMember(SymbolTable symboltable)
                throws CompileError
            {
                ASTList astlist;
/*  34*/        if((astlist = parseMember1(symboltable)) instanceof MethodDecl)
/*  36*/            return parseMethod2(symboltable, (MethodDecl)astlist);
/*  38*/        else
/*  38*/            return astlist;
            }

            public final ASTList parseMember1(SymbolTable symboltable)
                throws CompileError
            {
/*  44*/        ASTList astlist = parseMemberMods();
/*  46*/        boolean flag = false;
                Declarator declarator;
/*  47*/        if(lex.lookAhead() == 400 && lex.lookAhead(1) == 40)
                {
/*  48*/            declarator = new Declarator(344, 0);
/*  49*/            flag = true;
                } else
                {
/*  52*/            declarator = parseFormalType(symboltable);
                }
/*  54*/        if(lex.get() != 400)
/*  55*/            throw new SyntaxError(lex);
                String s;
/*  58*/        if(flag)
/*  59*/            s = "<init>";
/*  61*/        else
/*  61*/            s = lex.getString();
/*  63*/        declarator.setVariable(new Symbol(s));
/*  64*/        if(flag || lex.lookAhead() == 40)
/*  65*/            return parseMethod1(symboltable, flag, astlist, declarator);
/*  67*/        else
/*  67*/            return parseField(symboltable, astlist, declarator);
            }

            private FieldDecl parseField(SymbolTable symboltable, ASTList astlist, Declarator declarator)
                throws CompileError
            {
/*  78*/        ASTree astree = null;
/*  79*/        if(lex.lookAhead() == 61)
                {
/*  80*/            lex.get();
/*  81*/            astree = parseExpression(symboltable);
                }
/*  84*/        if((symboltable = lex.get()) == 59)
/*  86*/            return new FieldDecl(astlist, new ASTList(declarator, new ASTList(astree)));
/*  87*/        if(symboltable == 44)
/*  88*/            throw new CompileError("only one field can be declared in one declaration", lex);
/*  91*/        else
/*  91*/            throw new SyntaxError(lex);
            }

            private MethodDecl parseMethod1(SymbolTable symboltable, boolean flag, ASTList astlist, Declarator declarator)
                throws CompileError
            {
/* 108*/        if(lex.get() != 40)
/* 109*/            throw new SyntaxError(lex);
/* 111*/        ASTList astlist1 = null;
                int i;
/* 112*/        if(lex.lookAhead() != 41)
/* 114*/            do
/* 114*/                do
                        {
/* 114*/                    astlist1 = ASTList.append(astlist1, parseFormalParam(symboltable));
/* 115*/                    if((i = lex.lookAhead()) != 44)
/* 117*/                        break;
/* 117*/                    lex.get();
                        } while(true);
/* 118*/            while(i != 41);
/* 122*/        lex.get();
/* 123*/        declarator.addArrayDim(parseArrayDimension());
/* 124*/        if(flag && declarator.getArrayDim() > 0)
/* 125*/            throw new SyntaxError(lex);
/* 127*/        ASTList astlist2 = null;
/* 128*/        if(lex.lookAhead() == 341)
                {
/* 129*/            lex.get();
/* 131*/            do
                    {
/* 131*/                astlist2 = ASTList.append(astlist2, parseClassType(symboltable));
/* 132*/                if(lex.lookAhead() != 44)
/* 133*/                    break;
/* 133*/                lex.get();
                    } while(true);
                }
/* 139*/        return new MethodDecl(astlist, new ASTList(declarator, ASTList.make(astlist1, astlist2, null)));
            }

            public final MethodDecl parseMethod2(SymbolTable symboltable, MethodDecl methoddecl)
                throws CompileError
            {
/* 148*/        Stmnt stmnt = null;
/* 149*/        if(lex.lookAhead() == 59)
/* 150*/            lex.get();
/* 152*/        else
/* 152*/        if((stmnt = parseBlock(symboltable)) == null)
/* 154*/            stmnt = new Stmnt(66);
/* 157*/        methoddecl.sublist(4).setHead(stmnt);
/* 158*/        return methoddecl;
            }

            private ASTList parseMemberMods()
            {
                int i;
                ASTList astlist;
/* 168*/        for(astlist = null; (i = lex.lookAhead()) == 300 || i == 315 || i == 332 || i == 331 || i == 330 || i == 338 || i == 335 || i == 345 || i == 342 || i == 347; astlist = new ASTList(new Keyword(lex.get()), astlist));
/* 179*/        return astlist;
            }

            private Declarator parseFormalType(SymbolTable symboltable)
                throws CompileError
            {
                int i;
/* 185*/        if(isBuiltinType(i = lex.lookAhead()) || i == 344)
                {
/* 187*/            lex.get();
/* 188*/            symboltable = parseArrayDimension();
/* 189*/            return new Declarator(i, symboltable);
                } else
                {
/* 192*/            symboltable = parseClassType(symboltable);
/* 193*/            int j = parseArrayDimension();
/* 194*/            return new Declarator(symboltable, j);
                }
            }

            private static boolean isBuiltinType(int i)
            {
/* 199*/        return i == 301 || i == 303 || i == 306 || i == 334 || i == 324 || i == 326 || i == 317 || i == 312;
            }

            private Declarator parseFormalParam(SymbolTable symboltable)
                throws CompileError
            {
/* 208*/        Declarator declarator = parseFormalType(symboltable);
/* 209*/        if(lex.get() != 400)
                {
/* 210*/            throw new SyntaxError(lex);
                } else
                {
/* 212*/            String s = lex.getString();
/* 213*/            declarator.setVariable(new Symbol(s));
/* 214*/            declarator.addArrayDim(parseArrayDimension());
/* 215*/            symboltable.append(s, declarator);
/* 216*/            return declarator;
                }
            }

            public final Stmnt parseStatement(SymbolTable symboltable)
                throws CompileError
            {
                int i;
/* 241*/        if((i = lex.lookAhead()) == 123)
/* 243*/            return parseBlock(symboltable);
/* 244*/        if(i == 59)
                {
/* 245*/            lex.get();
/* 246*/            return new Stmnt(66);
                }
/* 248*/        if(i == 400 && lex.lookAhead(1) == 58)
                {
/* 249*/            lex.get();
/* 250*/            i = lex.getString();
/* 251*/            lex.get();
/* 252*/            return Stmnt.make(76, new Symbol(i), parseStatement(symboltable));
                }
/* 254*/        if(i == 320)
/* 255*/            return parseIf(symboltable);
/* 256*/        if(i == 346)
/* 257*/            return parseWhile(symboltable);
/* 258*/        if(i == 311)
/* 259*/            return parseDo(symboltable);
/* 260*/        if(i == 318)
/* 261*/            return parseFor(symboltable);
/* 262*/        if(i == 343)
/* 263*/            return parseTry(symboltable);
/* 264*/        if(i == 337)
/* 265*/            return parseSwitch(symboltable);
/* 266*/        if(i == 338)
/* 267*/            return parseSynchronized(symboltable);
/* 268*/        if(i == 333)
/* 269*/            return parseReturn(symboltable);
/* 270*/        if(i == 340)
/* 271*/            return parseThrow(symboltable);
/* 272*/        if(i == 302)
/* 273*/            return parseBreak(symboltable);
/* 274*/        if(i == 309)
/* 275*/            return parseContinue(symboltable);
/* 277*/        else
/* 277*/            return parseDeclarationOrExpression(symboltable, false);
            }

            private Stmnt parseBlock(SymbolTable symboltable)
                throws CompileError
            {
/* 283*/        if(lex.get() != 123)
/* 284*/            throw new SyntaxError(lex);
/* 286*/        Object obj = null;
/* 287*/        symboltable = new SymbolTable(symboltable);
/* 288*/        do
                {
/* 288*/            if(lex.lookAhead() == 125)
/* 289*/                break;
                    Stmnt stmnt;
/* 289*/            if((stmnt = parseStatement(symboltable)) != null)
/* 291*/                obj = (Stmnt)ASTList.concat(((ASTList) (obj)), new Stmnt(66, stmnt));
                } while(true);
/* 294*/        lex.get();
/* 295*/        if(obj == null)
/* 296*/            return new Stmnt(66);
/* 298*/        else
/* 298*/            return ((Stmnt) (obj));
            }

            private Stmnt parseIf(SymbolTable symboltable)
                throws CompileError
            {
/* 305*/        int i = lex.get();
/* 306*/        ASTree astree = parseParExpression(symboltable);
/* 307*/        Stmnt stmnt = parseStatement(symboltable);
/* 309*/        if(lex.lookAhead() == 313)
                {
/* 310*/            lex.get();
/* 311*/            symboltable = parseStatement(symboltable);
                } else
                {
/* 314*/            symboltable = null;
                }
/* 316*/        return new Stmnt(i, astree, new ASTList(stmnt, new ASTList(symboltable)));
            }

            private Stmnt parseWhile(SymbolTable symboltable)
                throws CompileError
            {
/* 324*/        int i = lex.get();
/* 325*/        ASTree astree = parseParExpression(symboltable);
/* 326*/        symboltable = parseStatement(symboltable);
/* 327*/        return new Stmnt(i, astree, symboltable);
            }

            private Stmnt parseDo(SymbolTable symboltable)
                throws CompileError
            {
/* 333*/        int i = lex.get();
/* 334*/        Stmnt stmnt = parseStatement(symboltable);
/* 335*/        if(lex.get() != 346 || lex.get() != 40)
/* 336*/            throw new SyntaxError(lex);
/* 338*/        symboltable = parseExpression(symboltable);
/* 339*/        if(lex.get() != 41 || lex.get() != 59)
/* 340*/            throw new SyntaxError(lex);
/* 342*/        else
/* 342*/            return new Stmnt(i, symboltable, stmnt);
            }

            private Stmnt parseFor(SymbolTable symboltable)
                throws CompileError
            {
/* 351*/        int i = lex.get();
/* 353*/        Object obj = new SymbolTable(symboltable);
/* 355*/        if(lex.get() != 40)
/* 356*/            throw new SyntaxError(lex);
/* 358*/        if(lex.lookAhead() == 59)
                {
/* 359*/            lex.get();
/* 360*/            symboltable = null;
                } else
                {
/* 363*/            symboltable = parseDeclarationOrExpression(((SymbolTable) (obj)), true);
                }
                ASTree astree;
/* 365*/        if(lex.lookAhead() == 59)
/* 366*/            astree = null;
/* 368*/        else
/* 368*/            astree = parseExpression(((SymbolTable) (obj)));
/* 370*/        if(lex.get() != 59)
/* 371*/            throw new CompileError("; is missing", lex);
                Stmnt stmnt;
/* 373*/        if(lex.lookAhead() == 41)
/* 374*/            stmnt = null;
/* 376*/        else
/* 376*/            stmnt = parseExprList(((SymbolTable) (obj)));
/* 378*/        if(lex.get() != 41)
                {
/* 379*/            throw new CompileError(") is missing", lex);
                } else
                {
/* 381*/            obj = parseStatement(((SymbolTable) (obj)));
/* 382*/            return new Stmnt(i, symboltable, new ASTList(astree, new ASTList(stmnt, ((ASTList) (obj)))));
                }
            }

            private Stmnt parseSwitch(SymbolTable symboltable)
                throws CompileError
            {
/* 394*/        int i = lex.get();
/* 395*/        ASTree astree = parseParExpression(symboltable);
/* 396*/        symboltable = parseSwitchBlock(symboltable);
/* 397*/        return new Stmnt(i, astree, symboltable);
            }

            private Stmnt parseSwitchBlock(SymbolTable symboltable)
                throws CompileError
            {
/* 401*/        if(lex.get() != 123)
/* 402*/            throw new SyntaxError(lex);
/* 404*/        symboltable = new SymbolTable(symboltable);
                Stmnt stmnt;
/* 405*/        if((stmnt = parseStmntOrCase(symboltable)) == null)
/* 407*/            throw new CompileError("empty switch block", lex);
                int i;
/* 409*/        if((i = stmnt.getOperator()) != 304 && i != 310)
/* 411*/            throw new CompileError("no case or default in a switch block", lex);
/* 414*/        Stmnt stmnt1 = new Stmnt(66, stmnt);
/* 415*/        do
                {
/* 415*/            if(lex.lookAhead() == 125)
/* 416*/                break;
                    Stmnt stmnt2;
                    int j;
/* 416*/            if((stmnt2 = parseStmntOrCase(symboltable)) != null)
/* 418*/                if((j = stmnt2.getOperator()) == 304 || j == 310)
                        {
/* 420*/                    stmnt1 = (Stmnt)ASTList.concat(stmnt1, new Stmnt(66, stmnt2));
/* 421*/                    stmnt = stmnt2;
                        } else
                        {
/* 424*/                    stmnt = (Stmnt)ASTList.concat(stmnt, new Stmnt(66, stmnt2));
                        }
                } while(true);
/* 428*/        lex.get();
/* 429*/        return stmnt1;
            }

            private Stmnt parseStmntOrCase(SymbolTable symboltable)
                throws CompileError
            {
                int i;
/* 433*/        if((i = lex.lookAhead()) != 304 && i != 310)
/* 435*/            return parseStatement(symboltable);
/* 437*/        lex.get();
/* 439*/        if(i == 304)
/* 440*/            symboltable = new Stmnt(i, parseExpression(symboltable));
/* 442*/        else
/* 442*/            symboltable = new Stmnt(310);
/* 444*/        if(lex.get() != 58)
/* 445*/            throw new CompileError(": is missing", lex);
/* 447*/        else
/* 447*/            return symboltable;
            }

            private Stmnt parseSynchronized(SymbolTable symboltable)
                throws CompileError
            {
/* 454*/        int i = lex.get();
/* 455*/        if(lex.get() != 40)
/* 456*/            throw new SyntaxError(lex);
/* 458*/        ASTree astree = parseExpression(symboltable);
/* 459*/        if(lex.get() != 41)
                {
/* 460*/            throw new SyntaxError(lex);
                } else
                {
/* 462*/            symboltable = parseBlock(symboltable);
/* 463*/            return new Stmnt(i, astree, symboltable);
                }
            }

            private Stmnt parseTry(SymbolTable symboltable)
                throws CompileError
            {
/* 472*/        lex.get();
/* 473*/        Stmnt stmnt = parseBlock(symboltable);
                ASTList astlist;
                Object obj;
                Declarator declarator;
/* 474*/        for(astlist = null; lex.lookAhead() == 305; astlist = ASTList.append(astlist, new Pair(declarator, ((ASTree) (obj)))))
                {
/* 476*/            lex.get();
/* 477*/            if(lex.get() != 40)
/* 478*/                throw new SyntaxError(lex);
/* 480*/            obj = new SymbolTable(symboltable);
/* 481*/            if((declarator = parseFormalParam(((SymbolTable) (obj)))).getArrayDim() > 0 || declarator.getType() != 307)
/* 483*/                throw new SyntaxError(lex);
/* 485*/            if(lex.get() != 41)
/* 486*/                throw new SyntaxError(lex);
/* 488*/            obj = parseBlock(((SymbolTable) (obj)));
                }

/* 492*/        Stmnt stmnt1 = null;
/* 493*/        if(lex.lookAhead() == 316)
                {
/* 494*/            lex.get();
/* 495*/            stmnt1 = parseBlock(symboltable);
                }
/* 498*/        return Stmnt.make(343, stmnt, astlist, stmnt1);
            }

            private Stmnt parseReturn(SymbolTable symboltable)
                throws CompileError
            {
/* 504*/        int i = lex.get();
/* 505*/        Stmnt stmnt = new Stmnt(i);
/* 506*/        if(lex.lookAhead() != 59)
/* 507*/            stmnt.setLeft(parseExpression(symboltable));
/* 509*/        if(lex.get() != 59)
/* 510*/            throw new CompileError("; is missing", lex);
/* 512*/        else
/* 512*/            return stmnt;
            }

            private Stmnt parseThrow(SymbolTable symboltable)
                throws CompileError
            {
/* 518*/        int i = lex.get();
/* 519*/        symboltable = parseExpression(symboltable);
/* 520*/        if(lex.get() != 59)
/* 521*/            throw new CompileError("; is missing", lex);
/* 523*/        else
/* 523*/            return new Stmnt(i, symboltable);
            }

            private Stmnt parseBreak(SymbolTable symboltable)
                throws CompileError
            {
/* 531*/        return parseContinue(symboltable);
            }

            private Stmnt parseContinue(SymbolTable symboltable)
                throws CompileError
            {
/* 539*/        symboltable = lex.get();
/* 540*/        symboltable = new Stmnt(symboltable);
                int i;
/* 541*/        if((i = lex.get()) == 400)
                {
/* 543*/            symboltable.setLeft(new Symbol(lex.getString()));
/* 544*/            i = lex.get();
                }
/* 547*/        if(i != 59)
/* 548*/            throw new CompileError("; is missing", lex);
/* 550*/        else
/* 550*/            return symboltable;
            }

            private Stmnt parseDeclarationOrExpression(SymbolTable symboltable, boolean flag)
                throws CompileError
            {
                int i;
/* 566*/        for(i = lex.lookAhead(); i == 315; i = lex.lookAhead())
/* 568*/            lex.get();

/* 572*/        if(isBuiltinType(i))
                {
/* 573*/            i = lex.get();
/* 574*/            int k = parseArrayDimension();
/* 575*/            return parseDeclarators(symboltable, new Declarator(i, k));
                }
                int l;
/* 577*/        if(i == 400 && (l = nextIsClassType(0)) >= 0 && lex.lookAhead(l) == 400)
                {
/* 581*/            flag = parseClassType(symboltable);
/* 582*/            int j = parseArrayDimension();
/* 583*/            return parseDeclarators(symboltable, new Declarator(flag, j));
                }
                Stmnt stmnt;
/* 588*/        if(flag)
/* 589*/            stmnt = parseExprList(symboltable);
/* 591*/        else
/* 591*/            stmnt = new Stmnt(69, parseExpression(symboltable));
/* 593*/        if(lex.get() != 59)
/* 594*/            throw new CompileError("; is missing", lex);
/* 596*/        else
/* 596*/            return stmnt;
            }

            private Stmnt parseExprList(SymbolTable symboltable)
                throws CompileError
            {
/* 602*/        Object obj = null;
/* 604*/        do
                {
/* 604*/            Stmnt stmnt = new Stmnt(69, parseExpression(symboltable));
/* 605*/            obj = (Stmnt)ASTList.concat(((ASTList) (obj)), new Stmnt(66, stmnt));
/* 606*/            if(lex.lookAhead() == 44)
/* 607*/                lex.get();
/* 609*/            else
/* 609*/                return ((Stmnt) (obj));
                } while(true);
            }

            private Stmnt parseDeclarators(SymbolTable symboltable, Declarator declarator)
                throws CompileError
            {
/* 618*/        Object obj = null;
                int i;
/* 620*/        do
                {
/* 620*/            obj = (Stmnt)ASTList.concat(((ASTList) (obj)), new Stmnt(68, parseDeclarator(symboltable, declarator)));
/* 622*/            if((i = lex.get()) == 59)
/* 624*/                return ((Stmnt) (obj));
                } while(i == 44);
/* 626*/        throw new CompileError("; is missing", lex);
            }

            private Declarator parseDeclarator(SymbolTable symboltable, Declarator declarator)
                throws CompileError
            {
/* 635*/        if(lex.get() != 400 || declarator.getType() == 344)
/* 636*/            throw new SyntaxError(lex);
/* 638*/        String s = lex.getString();
/* 639*/        Symbol symbol = new Symbol(s);
/* 640*/        int i = parseArrayDimension();
/* 641*/        ASTree astree = null;
/* 642*/        if(lex.lookAhead() == 61)
                {
/* 643*/            lex.get();
/* 644*/            astree = parseInitializer(symboltable);
                }
/* 647*/        declarator = declarator.make(symbol, i, astree);
/* 648*/        symboltable.append(s, declarator);
/* 649*/        return declarator;
            }

            private ASTree parseInitializer(SymbolTable symboltable)
                throws CompileError
            {
/* 655*/        if(lex.lookAhead() == 123)
/* 656*/            return parseArrayInitializer(symboltable);
/* 658*/        else
/* 658*/            return parseExpression(symboltable);
            }

            private ArrayInit parseArrayInitializer(SymbolTable symboltable)
                throws CompileError
            {
/* 667*/        lex.get();
/* 668*/        ASTree astree = parseExpression(symboltable);
/* 669*/        ArrayInit arrayinit = new ArrayInit(astree);
                ASTree astree1;
/* 670*/        for(; lex.lookAhead() == 44; ASTList.append(arrayinit, astree1))
                {
/* 671*/            lex.get();
/* 672*/            astree1 = parseExpression(symboltable);
                }

/* 676*/        if(lex.get() != 125)
/* 677*/            throw new SyntaxError(lex);
/* 679*/        else
/* 679*/            return arrayinit;
            }

            private ASTree parseParExpression(SymbolTable symboltable)
                throws CompileError
            {
/* 685*/        if(lex.get() != 40)
/* 686*/            throw new SyntaxError(lex);
/* 688*/        symboltable = parseExpression(symboltable);
/* 689*/        if(lex.get() != 41)
/* 690*/            throw new SyntaxError(lex);
/* 692*/        else
/* 692*/            return symboltable;
            }

            public final ASTree parseExpression(SymbolTable symboltable)
                throws CompileError
            {
/* 699*/        ASTree astree = parseConditionalExpr(symboltable);
/* 700*/        if(!isAssignOp(lex.lookAhead()))
                {
/* 701*/            return astree;
                } else
                {
/* 703*/            int i = lex.get();
/* 704*/            symboltable = parseExpression(symboltable);
/* 705*/            return AssignExpr.makeAssign(i, astree, symboltable);
                }
            }

            private static boolean isAssignOp(int i)
            {
/* 709*/        return i == 61 || i == 351 || i == 352 || i == 353 || i == 354 || i == 355 || i == 356 || i == 360 || i == 361 || i == 365 || i == 367 || i == 371;
            }

            private ASTree parseConditionalExpr(SymbolTable symboltable)
                throws CompileError
            {
/* 719*/        ASTree astree = parseBinaryExpr(symboltable);
/* 720*/        if(lex.lookAhead() == 63)
                {
/* 721*/            lex.get();
/* 722*/            ASTree astree1 = parseExpression(symboltable);
/* 723*/            if(lex.get() != 58)
                    {
/* 724*/                throw new CompileError(": is missing", lex);
                    } else
                    {
/* 726*/                symboltable = parseExpression(symboltable);
/* 727*/                return new CondExpr(astree, astree1, symboltable);
                    }
                } else
                {
/* 730*/            return astree;
                }
            }

            private ASTree parseBinaryExpr(SymbolTable symboltable)
                throws CompileError
            {
/* 775*/        ASTree astree = parseUnaryExpr(symboltable);
/* 777*/        do
                {
/* 777*/            int i = lex.lookAhead();
/* 778*/            if((i = getOpPrecedence(i)) == 0)
/* 780*/                return astree;
/* 782*/            astree = binaryExpr2(symboltable, astree, i);
                } while(true);
            }

            private ASTree parseInstanceOf(SymbolTable symboltable, ASTree astree)
                throws CompileError
            {
                int i;
/* 789*/        if(isBuiltinType(i = lex.lookAhead()))
                {
/* 791*/            lex.get();
/* 792*/            symboltable = parseArrayDimension();
/* 793*/            return new InstanceOfExpr(i, symboltable, astree);
                } else
                {
/* 796*/            symboltable = parseClassType(symboltable);
/* 797*/            int j = parseArrayDimension();
/* 798*/            return new InstanceOfExpr(symboltable, j, astree);
                }
            }

            private ASTree binaryExpr2(SymbolTable symboltable, ASTree astree, int i)
                throws CompileError
            {
                int j;
/* 805*/        if((j = lex.get()) == 323)
/* 807*/            return parseInstanceOf(symboltable, astree);
/* 809*/        ASTree astree1 = parseUnaryExpr(symboltable);
/* 811*/        do
                {
/* 811*/            int k = lex.lookAhead();
/* 812*/            if((k = getOpPrecedence(k)) != 0 && i > k)
/* 814*/                astree1 = binaryExpr2(symboltable, astree1, k);
/* 816*/            else
/* 816*/                return BinExpr.makeBin(j, astree, astree1);
                } while(true);
            }

            private int getOpPrecedence(int i)
            {
/* 828*/        if(33 <= i && i <= 63)
/* 829*/            return binaryOpPrecedence[i - 33];
/* 830*/        if(i == 94)
/* 831*/            return 7;
/* 832*/        if(i == 124)
/* 833*/            return 8;
/* 834*/        if(i == 369)
/* 835*/            return 9;
/* 836*/        if(i == 368)
/* 837*/            return 10;
/* 838*/        if(i == 358 || i == 350)
/* 839*/            return 5;
/* 840*/        if(i == 357 || i == 359 || i == 323)
/* 841*/            return 4;
/* 842*/        return i != 364 && i != 366 && i != 370 ? 0 : 3;
            }

            private ASTree parseUnaryExpr(SymbolTable symboltable)
                throws CompileError
            {
/* 859*/        switch(lex.lookAhead())
                {
/* 866*/        case 33: // '!'
/* 866*/        case 43: // '+'
/* 866*/        case 45: // '-'
/* 866*/        case 126: // '~'
/* 866*/        case 362: 
/* 866*/        case 363: 
                    int i;
                    int j;
/* 866*/            if((i = lex.get()) == 45)
/* 868*/                switch(j = lex.lookAhead())
                        {
/* 873*/                case 401: 
/* 873*/                case 402: 
/* 873*/                case 403: 
/* 873*/                    lex.get();
/* 874*/                    return new IntConst(-lex.getLong(), j);

/* 877*/                case 404: 
/* 877*/                case 405: 
/* 877*/                    lex.get();
/* 878*/                    return new DoubleConst(-lex.getDouble(), j);
                        }
/* 884*/            return Expr.make(i, parseUnaryExpr(symboltable));

/* 886*/        case 40: // '('
/* 886*/            return parseCast(symboltable);
                }
/* 888*/        return parsePostfix(symboltable);
            }

            private ASTree parseCast(SymbolTable symboltable)
                throws CompileError
            {
                int i;
/* 901*/        if(isBuiltinType(i = lex.lookAhead(1)) && nextIsBuiltinCast())
                {
/* 903*/            lex.get();
/* 904*/            lex.get();
/* 905*/            int k = parseArrayDimension();
/* 906*/            if(lex.get() != 41)
/* 907*/                throw new CompileError(") is missing", lex);
/* 909*/            else
/* 909*/                return new CastExpr(i, k, parseUnaryExpr(symboltable));
                }
/* 911*/        if(i == 400 && nextIsClassCast())
                {
/* 912*/            lex.get();
/* 913*/            ASTList astlist = parseClassType(symboltable);
/* 914*/            int j = parseArrayDimension();
/* 915*/            if(lex.get() != 41)
/* 916*/                throw new CompileError(") is missing", lex);
/* 918*/            else
/* 918*/                return new CastExpr(astlist, j, parseUnaryExpr(symboltable));
                } else
                {
/* 921*/            return parsePostfix(symboltable);
                }
            }

            private boolean nextIsBuiltinCast()
            {
                int i;
/* 926*/        for(i = 2; lex.lookAhead(i++) == 91;)
/* 928*/            if(lex.lookAhead(i++) != 93)
/* 929*/                return false;

/* 931*/        return lex.lookAhead(i - 1) == 41;
            }

            private boolean nextIsClassCast()
            {
                int i;
/* 935*/        if((i = nextIsClassType(1)) < 0)
/* 937*/            return false;
                int j;
/* 939*/        if((j = lex.lookAhead(i)) != 41)
/* 941*/            return false;
/* 943*/        return (j = lex.lookAhead(i + 1)) == 40 || j == 412 || j == 406 || j == 400 || j == 339 || j == 336 || j == 328 || j == 410 || j == 411 || j == 403 || j == 402 || j == 401 || j == 405 || j == 404;
            }

            private int nextIsClassType(int i)
            {
/* 953*/        while(lex.lookAhead(++i) == 46) 
/* 954*/            if(lex.lookAhead(++i) != 400)
/* 955*/                return -1;
/* 957*/        while(lex.lookAhead(i++) == 91) 
/* 958*/            if(lex.lookAhead(i++) != 93)
/* 959*/                return -1;
/* 961*/        return i - 1;
            }

            private int parseArrayDimension()
                throws CompileError
            {
/* 967*/        int i = 0;
/* 968*/        while(lex.lookAhead() == 91) 
                {
/* 969*/            i++;
/* 970*/            lex.get();
/* 971*/            if(lex.get() != 93)
/* 972*/                throw new CompileError("] is missing", lex);
                }
/* 975*/        return i;
            }

            private ASTList parseClassType(SymbolTable symboltable)
                throws CompileError
            {
/* 981*/        symboltable = null;
/* 983*/        do
                {
/* 983*/            if(lex.get() != 400)
/* 984*/                throw new SyntaxError(lex);
/* 986*/            symboltable = ASTList.append(symboltable, new Symbol(lex.getString()));
/* 987*/            if(lex.lookAhead() == 46)
/* 988*/                lex.get();
/* 993*/            else
/* 993*/                return symboltable;
                } while(true);
            }

            private ASTree parsePostfix(SymbolTable symboltable)
                throws CompileError
            {
                int i;
                Object obj;
/*1013*/        switch(i = lex.lookAhead())
                {
/*1018*/        case 401: 
/*1018*/        case 402: 
/*1018*/        case 403: 
/*1018*/            lex.get();
/*1019*/            return new IntConst(lex.getLong(), i);

/*1022*/        case 404: 
/*1022*/        case 405: 
/*1022*/            lex.get();
/*1023*/            return new DoubleConst(lex.getDouble(), i);

/*1030*/        default:
/*1030*/            obj = parsePrimaryExpr(symboltable);
                    break;
                }
/*1033*/        do
/*1033*/            switch(lex.lookAhead())
                    {
/*1035*/            case 40: // '('
/*1035*/                obj = parseMethodCall(symboltable, ((ASTree) (obj)));
                        break;

/*1038*/            case 91: // '['
/*1038*/                if(lex.lookAhead(1) == 93)
                        {
/*1039*/                    i = parseArrayDimension();
/*1040*/                    if(lex.get() != 46 || lex.get() != 307)
/*1041*/                        throw new SyntaxError(lex);
/*1043*/                    obj = parseDotClass(((ASTree) (obj)), i);
                        } else
                        {
                            ASTree astree;
/*1046*/                    if((astree = parseArrayIndex(symboltable)) == null)
/*1048*/                        throw new SyntaxError(lex);
/*1050*/                    obj = Expr.make(65, ((ASTree) (obj)), astree);
                        }
                        break;

/*1055*/            case 362: 
/*1055*/            case 363: 
/*1055*/                obj = Expr.make(astree = lex.get(), null, ((ASTree) (obj)));
                        break;

/*1059*/            case 46: // '.'
/*1059*/                lex.get();
                        int j;
/*1060*/                if((j = lex.get()) == 307)
/*1062*/                    obj = parseDotClass(((ASTree) (obj)), 0);
/*1064*/                else
/*1064*/                if(j == 400)
                        {
/*1065*/                    String s = lex.getString();
/*1066*/                    obj = Expr.make(46, ((ASTree) (obj)), new Member(s));
                        } else
                        {
/*1069*/                    throw new CompileError("missing member name", lex);
                        }
                        break;

/*1072*/            case 35: // '#'
/*1072*/                lex.get();
                        int k;
/*1073*/                if((k = lex.get()) != 400)
/*1075*/                    throw new CompileError("missing static member name", lex);
/*1077*/                String s1 = lex.getString();
/*1078*/                obj = Expr.make(35, new Symbol(toClassName(((ASTree) (obj)))), new Member(s1));
                        break;

/*1082*/            default:
/*1082*/                return ((ASTree) (obj));
                    }
/*1082*/        while(true);
            }

            private ASTree parseDotClass(ASTree astree, int i)
                throws CompileError
            {
/*1094*/        astree = toClassName(astree);
/*1095*/        if(i > 0)
                {
/*1096*/            StringBuffer stringbuffer = new StringBuffer();
/*1097*/            while(i-- > 0) 
/*1098*/                stringbuffer.append('[');
/*1100*/            stringbuffer.append('L').append(astree.replace('.', '/')).append(';');
/*1101*/            astree = stringbuffer.toString();
                }
/*1104*/        return Expr.make(46, new Symbol(astree), new Member("class"));
            }

            private ASTree parseDotClass(int i, int j)
                throws CompileError
            {
/*1114*/        if(j > 0)
                {
/*1115*/            i = CodeGen.toJvmTypeName(i, j);
/*1116*/            return Expr.make(46, new Symbol(i), new Member("class"));
                }
/*1120*/        switch(i)
                {
/*1122*/        case 301: 
/*1122*/            i = "java.lang.Boolean";
                    break;

/*1125*/        case 303: 
/*1125*/            i = "java.lang.Byte";
                    break;

/*1128*/        case 306: 
/*1128*/            i = "java.lang.Character";
                    break;

/*1131*/        case 334: 
/*1131*/            i = "java.lang.Short";
                    break;

/*1134*/        case 324: 
/*1134*/            i = "java.lang.Integer";
                    break;

/*1137*/        case 326: 
/*1137*/            i = "java.lang.Long";
                    break;

/*1140*/        case 317: 
/*1140*/            i = "java.lang.Float";
                    break;

/*1143*/        case 312: 
/*1143*/            i = "java.lang.Double";
                    break;

/*1146*/        case 344: 
/*1146*/            i = "java.lang.Void";
                    break;

/*1149*/        default:
/*1149*/            throw new CompileError((new StringBuilder("invalid builtin type: ")).append(i).toString());
                }
/*1153*/        return Expr.make(35, new Symbol(i), new Member("TYPE"));
            }

            private ASTree parseMethodCall(SymbolTable symboltable, ASTree astree)
                throws CompileError
            {
                int i;
/*1165*/        if(astree instanceof Keyword)
                {
/*1166*/            if((i = ((Keyword)astree).get()) != 339 && i != 336)
/*1168*/                throw new SyntaxError(lex);
                } else
/*1170*/        if(!(astree instanceof Symbol) && (astree instanceof Expr) && (i = ((Expr)astree).getOperator()) != 46 && i != 35)
/*1175*/            throw new SyntaxError(lex);
/*1178*/        return CallExpr.makeCall(astree, parseArgumentList(symboltable));
            }

            private String toClassName(ASTree astree)
                throws CompileError
            {
/*1184*/        StringBuffer stringbuffer = new StringBuffer();
/*1185*/        toClassName(astree, stringbuffer);
/*1186*/        return stringbuffer.toString();
            }

            private void toClassName(ASTree astree, StringBuffer stringbuffer)
                throws CompileError
            {
/*1192*/        do
                {
/*1192*/            if(astree instanceof Symbol)
                    {
/*1193*/                stringbuffer.append(((Symbol)astree).get());
/*1194*/                return;
                    }
/*1196*/            if((astree instanceof Expr) && (astree = (Expr)astree).getOperator() == 46)
                    {
/*1199*/                toClassName(astree.oprand1(), stringbuffer);
/*1200*/                stringbuffer.append('.');
/*1201*/                astree = astree.oprand2();
/*1201*/                this = this;
                    } else
                    {
/*1206*/                throw new CompileError("bad static member access", lex);
                    }
                } while(true);
            }

            private ASTree parsePrimaryExpr(SymbolTable symboltable)
                throws CompileError
            {
                int i;
/*1225*/        switch(i = lex.get())
                {
/*1231*/        case 336: 
/*1231*/        case 339: 
/*1231*/        case 410: 
/*1231*/        case 411: 
/*1231*/        case 412: 
/*1231*/            return new Keyword(i);

/*1233*/        case 400: 
/*1233*/            i = lex.getString();
/*1234*/            if((symboltable = symboltable.lookup(i)) == null)
/*1236*/                return new Member(i);
/*1238*/            else
/*1238*/                return new Variable(i, symboltable);

/*1240*/        case 406: 
/*1240*/            return new StringL(lex.getString());

/*1242*/        case 328: 
/*1242*/            return parseNew(symboltable);

/*1244*/        case 40: // '('
/*1244*/            symboltable = parseExpression(symboltable);
/*1245*/            if(lex.get() == 41)
/*1246*/                return symboltable;
/*1248*/            else
/*1248*/                throw new CompileError(") is missing", lex);
                }
/*1250*/        if(isBuiltinType(i) || i == 344)
                {
/*1251*/            symboltable = parseArrayDimension();
/*1252*/            if(lex.get() == 46 && lex.get() == 307)
/*1253*/                return parseDotClass(i, symboltable);
                }
/*1256*/        throw new SyntaxError(lex);
            }

            private NewExpr parseNew(SymbolTable symboltable)
                throws CompileError
            {
/*1265*/        ArrayInit arrayinit = null;
                int i;
/*1266*/        if(isBuiltinType(i = lex.lookAhead()))
                {
/*1268*/            lex.get();
/*1269*/            ASTList astlist1 = parseArraySize(symboltable);
/*1270*/            if(lex.lookAhead() == 123)
/*1271*/                arrayinit = parseArrayInitializer(symboltable);
/*1273*/            return new NewExpr(i, astlist1, arrayinit);
                }
/*1275*/        if(i == 400)
                {
/*1276*/            ASTList astlist2 = parseClassType(symboltable);
                    int j;
/*1277*/            if((j = lex.lookAhead()) == 40)
                    {
/*1279*/                j = parseArgumentList(symboltable);
/*1280*/                return new NewExpr(astlist2, j);
                    }
/*1282*/            if(j == 91)
                    {
/*1283*/                ASTList astlist = parseArraySize(symboltable);
/*1284*/                if(lex.lookAhead() == 123)
/*1285*/                    arrayinit = parseArrayInitializer(symboltable);
/*1287*/                return NewExpr.makeObjectArray(astlist2, astlist, arrayinit);
                    }
                }
/*1291*/        throw new SyntaxError(lex);
            }

            private ASTList parseArraySize(SymbolTable symboltable)
                throws CompileError
            {
                ASTList astlist;
/*1297*/        for(astlist = null; lex.lookAhead() == 91; astlist = ASTList.append(astlist, parseArrayIndex(symboltable)));
/*1301*/        return astlist;
            }

            private ASTree parseArrayIndex(SymbolTable symboltable)
                throws CompileError
            {
/*1307*/        lex.get();
/*1308*/        if(lex.lookAhead() == 93)
                {
/*1309*/            lex.get();
/*1310*/            return null;
                }
/*1313*/        symboltable = parseExpression(symboltable);
/*1314*/        if(lex.get() != 93)
/*1315*/            throw new CompileError("] is missing", lex);
/*1317*/        else
/*1317*/            return symboltable;
            }

            private ASTList parseArgumentList(SymbolTable symboltable)
                throws CompileError
            {
/*1324*/        if(lex.get() != 40)
/*1325*/            throw new CompileError("( is missing", lex);
/*1327*/        ASTList astlist = null;
/*1328*/        if(lex.lookAhead() != 41)
/*1330*/            do
                    {
/*1330*/                astlist = ASTList.append(astlist, parseExpression(symboltable));
/*1331*/                if(lex.lookAhead() != 44)
/*1332*/                    break;
/*1332*/                lex.get();
                    } while(true);
/*1337*/        if(lex.get() != 41)
/*1338*/            throw new CompileError(") is missing", lex);
/*1340*/        else
/*1340*/            return astlist;
            }

            private Lex lex;
            private static final int binaryOpPrecedence[] = {
/* 821*/        0, 0, 0, 0, 1, 6, 0, 0, 0, 1, 
/* 821*/        2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 
/* 821*/        0, 0, 0, 0, 0, 0, 0, 4, 0, 4, 
/* 821*/        0
            };

}
