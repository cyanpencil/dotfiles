// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Javac.java

package javassist.compiler;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.FieldDecl;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            CompileError, JvstCodeGen, Lex, MemberResolver, 
//            Parser, SymbolTable, ProceedHandler, JvstTypeChecker

public class Javac
{
    public static class CtFieldWithInit extends CtField
    {

                protected void setInit(ASTree astree)
                {
/* 121*/            init = astree;
                }

                protected ASTree getInitAST()
                {
/* 124*/            return init;
                }

                private ASTree init;

                CtFieldWithInit(CtClass ctclass, String s, CtClass ctclass1)
                    throws CannotCompileException
                {
/* 117*/            super(ctclass, s, ctclass1);
/* 118*/            init = null;
                }
    }


            public Javac(CtClass ctclass)
            {
/*  53*/        this(new Bytecode(ctclass.getClassFile2().getConstPool(), 0, 0), ctclass);
            }

            public Javac(Bytecode bytecode1, CtClass ctclass)
            {
/*  66*/        gen = new JvstCodeGen(bytecode1, ctclass, ctclass.getClassPool());
/*  67*/        stable = new SymbolTable();
/*  68*/        bytecode = bytecode1;
            }

            public Bytecode getBytecode()
            {
/*  74*/        return bytecode;
            }

            public CtMember compile(String s)
                throws CompileError
            {
/*  89*/        Object obj = (s = new Parser(new Lex(s))).parseMember1(stable);
/*  92*/        if(obj instanceof FieldDecl)
/*  93*/            return compileField((FieldDecl)obj);
/*  95*/        obj = (s = compileMethod(s, (MethodDecl)obj)).getDeclaringClass();
/*  97*/        s.getMethodInfo2().rebuildStackMapIf6(((CtClass) (obj)).getClassPool(), ((CtClass) (obj)).getClassFile2());
/* 100*/        return s;
/* 103*/        s;
/* 104*/        throw new CompileError(s.getMessage());
/* 106*/        s;
/* 107*/        throw new CompileError(s.getMessage());
            }

            private CtField compileField(FieldDecl fielddecl)
                throws CompileError, CannotCompileException
            {
/* 132*/        Object obj = fielddecl.getDeclarator();
/* 133*/        ((CtFieldWithInit) (obj = new CtFieldWithInit(gen.resolver.lookupClass(((Declarator) (obj))), ((Declarator) (obj)).getVariable().get(), gen.getThisClass()))).setModifiers(MemberResolver.getModifiers(fielddecl.getModifiers()));
/* 136*/        if(fielddecl.getInit() != null)
/* 137*/            ((CtFieldWithInit) (obj)).setInit(fielddecl.getInit());
/* 139*/        return ((CtField) (obj));
            }

            private CtBehavior compileMethod(Parser parser, MethodDecl methoddecl)
                throws CompileError
            {
                int i;
                CtClass actclass[];
                CtClass actclass1[];
/* 145*/        i = MemberResolver.getModifiers(methoddecl.getModifiers());
/* 146*/        actclass = gen.makeParamList(methoddecl);
/* 147*/        actclass1 = gen.makeThrowsList(methoddecl);
/* 148*/        recordParams(actclass, Modifier.isStatic(i));
/* 149*/        methoddecl = parser.parseMethod2(stable, methoddecl);
/* 151*/        if(!methoddecl.isConstructor())
/* 152*/            break MISSING_BLOCK_LABEL_108;
/* 152*/        (parser = new CtConstructor(actclass, gen.getThisClass())).setModifiers(i);
/* 155*/        methoddecl.accept(gen);
/* 156*/        parser.getMethodInfo().setCodeAttribute(bytecode.toCodeAttribute());
/* 158*/        parser.setExceptionTypes(actclass1);
/* 159*/        return parser;
/* 162*/        parser = methoddecl.getReturn();
/* 163*/        CtClass ctclass = gen.resolver.lookupClass(parser);
/* 164*/        recordReturnType(ctclass, false);
/* 165*/        (parser = new CtMethod(ctclass, parser.getVariable().get(), actclass, gen.getThisClass())).setModifiers(i);
/* 168*/        gen.setThisMethod(parser);
/* 169*/        methoddecl.accept(gen);
/* 170*/        if(methoddecl.getBody() != null)
/* 171*/            parser.getMethodInfo().setCodeAttribute(bytecode.toCodeAttribute());
/* 174*/        else
/* 174*/            parser.setModifiers(i | 0x400);
/* 176*/        parser.setExceptionTypes(actclass1);
/* 177*/        return parser;
/* 180*/        parser;
/* 181*/        throw new CompileError(parser.toString());
            }

            public Bytecode compileBody(CtBehavior ctbehavior, String s)
                throws CompileError
            {
/* 195*/        int i = ctbehavior.getModifiers();
/* 196*/        recordParams(ctbehavior.getParameterTypes(), Modifier.isStatic(i));
                CtClass ctclass;
/* 199*/        if(ctbehavior instanceof CtMethod)
                {
/* 200*/            gen.setThisMethod((CtMethod)ctbehavior);
/* 201*/            ctclass = ((CtMethod)ctbehavior).getReturnType();
                } else
                {
/* 204*/            ctclass = CtClass.voidType;
                }
/* 206*/        recordReturnType(ctclass, false);
/* 207*/        boolean flag = ctclass == CtClass.voidType;
/* 209*/        if(s == null)
                {
/* 210*/            makeDefaultBody(bytecode, ctclass);
                } else
                {
/* 212*/            s = new Parser(new Lex(s));
/* 213*/            Object obj = new SymbolTable(stable);
/* 214*/            obj = s.parseStatement(((SymbolTable) (obj)));
/* 215*/            if(s.hasMore())
/* 216*/                throw new CompileError("the method/constructor body must be surrounded by {}");
/* 219*/            s = 0;
/* 220*/            if(ctbehavior instanceof CtConstructor)
/* 221*/                s = ((CtConstructor)ctbehavior).isClassInitializer() ? 0 : 1;
/* 223*/            gen.atMethodBody(((Stmnt) (obj)), s, flag);
                }
/* 226*/        return bytecode;
                NotFoundException notfoundexception;
/* 228*/        notfoundexception;
/* 229*/        throw new CompileError(notfoundexception.toString());
            }

            private static void makeDefaultBody(Bytecode bytecode1, CtClass ctclass)
            {
                byte byte0;
/* 236*/        if(ctclass instanceof CtPrimitiveType)
                {
/* 237*/            if((ctclass = (ctclass = (CtPrimitiveType)ctclass).getReturnOp()) == 175)
/* 240*/                byte0 = 14;
/* 241*/            else
/* 241*/            if(ctclass == 174)
/* 242*/                byte0 = 11;
/* 243*/            else
/* 243*/            if(ctclass == 173)
/* 244*/                byte0 = 9;
/* 245*/            else
/* 245*/            if(ctclass == 177)
/* 246*/                byte0 = 0;
/* 248*/            else
/* 248*/                byte0 = 3;
                } else
                {
/* 251*/            ctclass = 176;
/* 252*/            byte0 = 1;
                }
/* 255*/        if(byte0 != 0)
/* 256*/            bytecode1.addOpcode(byte0);
/* 258*/        bytecode1.addOpcode(ctclass);
            }

            public boolean recordLocalVariables(CodeAttribute codeattribute, int i)
                throws CompileError
            {
/* 273*/        if((codeattribute = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable")) == null)
/* 277*/            return false;
/* 279*/        int j = codeattribute.tableLength();
/* 280*/        for(int k = 0; k < j; k++)
                {
/* 281*/            int l = codeattribute.startPc(k);
/* 282*/            int i1 = codeattribute.codeLength(k);
/* 283*/            if(l <= i && i < l + i1)
/* 284*/                gen.recordVariable(codeattribute.descriptor(k), codeattribute.variableName(k), codeattribute.index(k), stable);
                }

/* 288*/        return true;
            }

            public boolean recordParamNames(CodeAttribute codeattribute, int i)
                throws CompileError
            {
/* 303*/        if((codeattribute = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable")) == null)
/* 307*/            return false;
/* 309*/        int j = codeattribute.tableLength();
/* 310*/        for(int k = 0; k < j; k++)
                {
                    int l;
/* 311*/            if((l = codeattribute.index(k)) < i)
/* 313*/                gen.recordVariable(codeattribute.descriptor(k), codeattribute.variableName(k), l, stable);
                }

/* 317*/        return true;
            }

            public int recordParams(CtClass actclass[], boolean flag)
                throws CompileError
            {
/* 334*/        return gen.recordParams(actclass, flag, "$", "$args", "$$", stable);
            }

            public int recordParams(String s, CtClass actclass[], boolean flag, int i, boolean flag1)
                throws CompileError
            {
/* 362*/        return gen.recordParams(actclass, flag1, "$", "$args", "$$", flag, i, s, stable);
            }

            public void setMaxLocals(int i)
            {
/* 376*/        gen.setMaxLocals(i);
            }

            public int recordReturnType(CtClass ctclass, boolean flag)
                throws CompileError
            {
/* 396*/        gen.recordType(ctclass);
/* 397*/        return gen.recordReturnType(ctclass, "$r", flag ? "$_" : null, stable);
            }

            public void recordType(CtClass ctclass)
            {
/* 408*/        gen.recordType(ctclass);
            }

            public int recordVariable(CtClass ctclass, String s)
                throws CompileError
            {
/* 420*/        return gen.recordVariable(ctclass, s, stable);
            }

            public void recordProceed(final String texpr, final String m)
                throws CompileError
            {
/* 435*/        texpr = (texpr = new Parser(new Lex(texpr))).parseExpression(stable);
/* 437*/        m = m;
/* 439*/        texpr = new ProceedHandler() {

                    public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode1, ASTList astlist)
                        throws CompileError
                    {
/* 443*/                bytecode1 = new Member(m);
/* 444*/                if(texpr != null)
/* 445*/                    bytecode1 = Expr.make(46, texpr, bytecode1);
/* 447*/                bytecode1 = CallExpr.makeCall(bytecode1, astlist);
/* 448*/                jvstcodegen.compileExpr(bytecode1);
/* 449*/                jvstcodegen.addNullIfVoid();
                    }

                    public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                        throws CompileError
                    {
/* 455*/                Object obj = new Member(m);
/* 456*/                if(texpr != null)
/* 457*/                    obj = Expr.make(46, texpr, ((ASTree) (obj)));
/* 459*/                ((ASTree) (obj = CallExpr.makeCall(((ASTree) (obj)), astlist))).accept(jvsttypechecker);
/* 461*/                jvsttypechecker.addNullIfVoid();
                    }

                    final String val$m;
                    final ASTree val$texpr;
                    final Javac this$0;

                    
                    {
/* 439*/                this$0 = Javac.this;
/* 439*/                m = s;
/* 439*/                texpr = astree;
/* 439*/                super();
                    }
        };
/* 465*/        gen.setProceedHandler(texpr, "$proceed");
            }

            public void recordStaticProceed(final String c, final String m)
                throws CompileError
            {
/* 480*/        c = c;
/* 481*/        m = m;
/* 483*/        c = new ProceedHandler() {

                    public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode1, ASTList astlist)
                        throws CompileError
                    {
/* 487*/                bytecode1 = CallExpr.makeCall(bytecode1 = Expr.make(35, new Symbol(c), new Member(m)), astlist);
/* 490*/                jvstcodegen.compileExpr(bytecode1);
/* 491*/                jvstcodegen.addNullIfVoid();
                    }

                    public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                        throws CompileError
                    {
                        Object obj;
/* 497*/                ((Expr) (obj = CallExpr.makeCall(((ASTree) (obj = Expr.make(35, new Symbol(c), new Member(m)))), astlist))).accept(jvsttypechecker);
/* 501*/                jvsttypechecker.addNullIfVoid();
                    }

                    final String val$c;
                    final String val$m;
                    final Javac this$0;

                    
                    {
/* 483*/                this$0 = Javac.this;
/* 483*/                c = s;
/* 483*/                m = s1;
/* 483*/                super();
                    }
        };
/* 505*/        gen.setProceedHandler(c, "$proceed");
            }

            public void recordSpecialProceed(final String texpr, final String cname, final String method, final String desc)
                throws CompileError
            {
/* 523*/        texpr = (texpr = new Parser(new Lex(texpr))).parseExpression(stable);
/* 525*/        cname = cname;
/* 526*/        method = method;
/* 527*/        desc = desc;
/* 529*/        texpr = new ProceedHandler() {

                    public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode1, ASTList astlist)
                        throws CompileError
                    {
/* 533*/                jvstcodegen.compileInvokeSpecial(texpr, cname, method, desc, astlist);
                    }

                    public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                        throws CompileError
                    {
/* 539*/                jvsttypechecker.compileInvokeSpecial(texpr, cname, method, desc, astlist);
                    }

                    final ASTree val$texpr;
                    final String val$cname;
                    final String val$method;
                    final String val$desc;
                    final Javac this$0;

                    
                    {
/* 529*/                this$0 = Javac.this;
/* 529*/                texpr = astree;
/* 529*/                cname = s;
/* 529*/                method = s1;
/* 529*/                desc = s2;
/* 529*/                super();
                    }
        };
/* 544*/        gen.setProceedHandler(texpr, "$proceed");
            }

            public void recordProceed(ProceedHandler proceedhandler)
            {
/* 551*/        gen.setProceedHandler(proceedhandler, "$proceed");
            }

            public void compileStmnt(String s)
                throws CompileError
            {
/* 564*/        s = new Parser(new Lex(s));
/* 565*/        SymbolTable symboltable = new SymbolTable(stable);
/* 566*/        do
                {
/* 566*/            if(!s.hasMore())
/* 567*/                break;
                    Stmnt stmnt;
/* 567*/            if((stmnt = s.parseStatement(symboltable)) != null)
/* 569*/                stmnt.accept(gen);
                } while(true);
            }

            public void compileExpr(String s)
                throws CompileError
            {
/* 583*/        s = parseExpr(s, stable);
/* 584*/        compileExpr(((ASTree) (s)));
            }

            public static ASTree parseExpr(String s, SymbolTable symboltable)
                throws CompileError
            {
/* 593*/        return (s = new Parser(new Lex(s))).parseExpression(symboltable);
            }

            public void compileExpr(ASTree astree)
                throws CompileError
            {
/* 607*/        if(astree != null)
/* 608*/            gen.compileExpr(astree);
            }

            JvstCodeGen gen;
            SymbolTable stable;
            private Bytecode bytecode;
            public static final String param0Name = "$0";
            public static final String resultVarName = "$_";
            public static final String proceedName = "$proceed";
}
