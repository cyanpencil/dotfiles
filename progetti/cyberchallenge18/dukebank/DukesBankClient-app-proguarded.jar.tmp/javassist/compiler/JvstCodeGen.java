// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JvstCodeGen.java

package javassist.compiler;

import javassist.*;
import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            MemberCodeGen, CompileError, JvstTypeChecker, MemberResolver, 
//            ProceedHandler, SymbolTable

public class JvstCodeGen extends MemberCodeGen
{

            public JvstCodeGen(Bytecode bytecode, CtClass ctclass, ClassPool classpool)
            {
/*  46*/        super(bytecode, ctclass, classpool);
/*  27*/        paramArrayName = null;
/*  28*/        paramListName = null;
/*  29*/        paramTypeList = null;
/*  30*/        paramVarBase = 0;
/*  31*/        useParam0 = false;
/*  32*/        param0Type = null;
/*  36*/        dollarType = null;
/*  37*/        returnType = null;
/*  38*/        returnCastName = null;
/*  39*/        returnVarName = null;
/*  41*/        proceedName = null;
/*  43*/        procHandler = null;
/*  47*/        setTypeChecker(new JvstTypeChecker(ctclass, classpool, this));
            }

            private int indexOfParam1()
            {
/*  53*/        return paramVarBase + (useParam0 ? 1 : 0);
            }

            public void setProceedHandler(ProceedHandler proceedhandler, String s)
            {
/*  62*/        proceedName = s;
/*  63*/        procHandler = proceedhandler;
            }

            public void addNullIfVoid()
            {
/*  70*/        if(exprType == 344)
                {
/*  71*/            bytecode.addOpcode(1);
/*  72*/            exprType = 307;
/*  73*/            arrayDim = 0;
/*  74*/            className = "java/lang/Object";
                }
            }

            public void atMember(Member member)
                throws CompileError
            {
                String s;
/*  82*/        if((s = member.get()).equals(paramArrayName))
                {
/*  84*/            compileParameterList(bytecode, paramTypeList, indexOfParam1());
/*  85*/            exprType = 307;
/*  86*/            arrayDim = 1;
/*  87*/            className = "java/lang/Object";
/*  87*/            return;
                }
/*  89*/        if(s.equals("$sig"))
                {
/*  90*/            bytecode.addLdc(Descriptor.ofMethod(returnType, paramTypeList));
/*  91*/            bytecode.addInvokestatic("javassist/runtime/Desc", "getParams", "(Ljava/lang/String;)[Ljava/lang/Class;");
/*  93*/            exprType = 307;
/*  94*/            arrayDim = 1;
/*  95*/            className = "java/lang/Class";
/*  95*/            return;
                }
/*  97*/        if(s.equals("$type"))
/*  98*/            if(dollarType == null)
                    {
/*  99*/                throw new CompileError("$type is not available");
                    } else
                    {
/* 101*/                bytecode.addLdc(Descriptor.of(dollarType));
/* 102*/                callGetType("getType");
/* 102*/                return;
                    }
/* 104*/        if(s.equals("$class"))
                {
/* 105*/            if(param0Type == null)
                    {
/* 106*/                throw new CompileError("$class is not available");
                    } else
                    {
/* 108*/                bytecode.addLdc(param0Type);
/* 109*/                callGetType("getClazz");
/* 109*/                return;
                    }
                } else
                {
/* 112*/            super.atMember(member);
/* 113*/            return;
                }
            }

            private void callGetType(String s)
            {
/* 116*/        bytecode.addInvokestatic("javassist/runtime/Desc", s, "(Ljava/lang/String;)Ljava/lang/Class;");
/* 118*/        exprType = 307;
/* 119*/        arrayDim = 0;
/* 120*/        className = "java/lang/Class";
            }

            protected void atFieldAssign(Expr expr, int i, ASTree astree, ASTree astree1, boolean flag)
                throws CompileError
            {
/* 126*/        if((astree instanceof Member) && ((Member)astree).get().equals(paramArrayName))
                {
/* 128*/            if(i != 61)
/* 129*/                throw new CompileError((new StringBuilder("bad operator for ")).append(paramArrayName).toString());
/* 131*/            astree1.accept(this);
/* 132*/            if(arrayDim != 1 || exprType != 307)
/* 133*/                throw new CompileError((new StringBuilder("invalid type for ")).append(paramArrayName).toString());
/* 135*/            atAssignParamList(paramTypeList, bytecode);
/* 136*/            if(!flag)
                    {
/* 137*/                bytecode.addOpcode(87);
/* 137*/                return;
                    }
                } else
                {
/* 140*/            super.atFieldAssign(expr, i, astree, astree1, flag);
                }
            }

            protected void atAssignParamList(CtClass actclass[], Bytecode bytecode)
                throws CompileError
            {
/* 146*/        if(actclass == null)
/* 147*/            return;
/* 149*/        int i = indexOfParam1();
/* 150*/        int j = actclass.length;
/* 151*/        for(int k = 0; k < j; k++)
                {
/* 152*/            bytecode.addOpcode(89);
/* 153*/            bytecode.addIconst(k);
/* 154*/            bytecode.addOpcode(50);
/* 155*/            compileUnwrapValue(actclass[k], bytecode);
/* 156*/            bytecode.addStore(i, actclass[k]);
/* 157*/            i += is2word(exprType, arrayDim) ? 2 : 1;
                }

            }

            public void atCastExpr(CastExpr castexpr)
                throws CompileError
            {
                ASTList astlist;
                ASTree astree;
/* 162*/        if((astlist = castexpr.getClassName()) != null && castexpr.getArrayDim() == 0 && ((astree = astlist.head()) instanceof Symbol) && astlist.tail() == null)
                {
                    String s;
/* 166*/            if((s = ((Symbol)astree).get()).equals(returnCastName))
                    {
/* 168*/                atCastToRtype(castexpr);
/* 169*/                return;
                    }
/* 171*/            if(s.equals("$w"))
                    {
/* 172*/                atCastToWrapper(castexpr);
/* 173*/                return;
                    }
                }
/* 178*/        super.atCastExpr(castexpr);
            }

            protected void atCastToRtype(CastExpr castexpr)
                throws CompileError
            {
/* 186*/        castexpr.getOprand().accept(this);
/* 187*/        if(exprType == 344 || isRefType(exprType) || arrayDim > 0)
                {
/* 188*/            compileUnwrapValue(returnType, bytecode);
/* 188*/            return;
                }
/* 189*/        if(returnType instanceof CtPrimitiveType)
                {
/* 190*/            castexpr = MemberResolver.descToType((castexpr = (CtPrimitiveType)returnType).getDescriptor());
/* 192*/            atNumCastExpr(exprType, castexpr);
/* 193*/            exprType = castexpr;
/* 194*/            arrayDim = 0;
/* 195*/            className = null;
/* 196*/            return;
                } else
                {
/* 198*/            throw new CompileError("invalid cast");
                }
            }

            protected void atCastToWrapper(CastExpr castexpr)
                throws CompileError
            {
/* 202*/        castexpr.getOprand().accept(this);
/* 203*/        if(isRefType(exprType) || arrayDim > 0)
/* 204*/            return;
/* 206*/        if((castexpr = resolver.lookupClass(exprType, arrayDim, className)) instanceof CtPrimitiveType)
                {
/* 208*/            String s = (castexpr = (CtPrimitiveType)castexpr).getWrapperName();
/* 210*/            bytecode.addNew(s);
/* 211*/            bytecode.addOpcode(89);
/* 212*/            if(castexpr.getDataSize() > 1)
/* 213*/                bytecode.addOpcode(94);
/* 215*/            else
/* 215*/                bytecode.addOpcode(93);
/* 217*/            bytecode.addOpcode(88);
/* 218*/            bytecode.addInvokespecial(s, "<init>", (new StringBuilder("(")).append(castexpr.getDescriptor()).append(")V").toString());
/* 221*/            exprType = 307;
/* 222*/            arrayDim = 0;
/* 223*/            className = "java/lang/Object";
                }
            }

            public void atCallExpr(CallExpr callexpr)
                throws CompileError
            {
                Object obj;
/* 231*/        if((obj = callexpr.oprand1()) instanceof Member)
                {
/* 233*/            obj = ((Member)obj).get();
/* 234*/            if(procHandler != null && ((String) (obj)).equals(proceedName))
                    {
/* 235*/                procHandler.doit(this, bytecode, (ASTList)callexpr.oprand2());
/* 236*/                return;
                    }
/* 238*/            if(((String) (obj)).equals("$cflow"))
                    {
/* 239*/                atCflow((ASTList)callexpr.oprand2());
/* 240*/                return;
                    }
                }
/* 244*/        super.atCallExpr(callexpr);
            }

            protected void atCflow(ASTList astlist)
                throws CompileError
            {
/* 250*/        StringBuffer stringbuffer = new StringBuffer();
/* 251*/        if(astlist == null || astlist.tail() != null)
/* 252*/            throw new CompileError("bad $cflow");
/* 254*/        makeCflowName(stringbuffer, astlist.head());
/* 255*/        astlist = stringbuffer.toString();
                Object aobj[];
/* 256*/        if((aobj = resolver.getClassPool().lookupCflow(astlist)) == null)
                {
/* 258*/            throw new CompileError((new StringBuilder("no such $cflow: ")).append(astlist).toString());
                } else
                {
/* 260*/            bytecode.addGetstatic((String)aobj[0], (String)aobj[1], "Ljavassist/runtime/Cflow;");
/* 262*/            bytecode.addInvokevirtual("javassist.runtime.Cflow", "value", "()I");
/* 264*/            exprType = 324;
/* 265*/            arrayDim = 0;
/* 266*/            className = null;
/* 267*/            return;
                }
            }

            private static void makeCflowName(StringBuffer stringbuffer, ASTree astree)
                throws CompileError
            {
/* 277*/        do
                {
/* 277*/            if(astree instanceof Symbol)
                    {
/* 278*/                stringbuffer.append(((Symbol)astree).get());
/* 279*/                return;
                    }
/* 281*/            if((astree instanceof Expr) && (astree = (Expr)astree).getOperator() == 46)
                    {
/* 284*/                makeCflowName(stringbuffer, astree.oprand1());
/* 285*/                stringbuffer.append('.');
/* 286*/                astree = astree.oprand2();
/* 286*/                stringbuffer = stringbuffer;
                    } else
                    {
/* 291*/                throw new CompileError("bad $cflow");
                    }
                } while(true);
            }

            public boolean isParamListName(ASTList astlist)
            {
/* 298*/        if(paramTypeList != null && astlist != null && astlist.tail() == null)
/* 300*/            return ((astlist = astlist.head()) instanceof Member) && ((Member)astlist).get().equals(paramListName);
/* 305*/        else
/* 305*/            return false;
            }

            public int getMethodArgsLength(ASTList astlist)
            {
/* 318*/        String s = paramListName;
/* 319*/        int i = 0;
/* 320*/        for(; astlist != null; astlist = astlist.tail())
                {
                    ASTree astree;
/* 321*/            if(((astree = astlist.head()) instanceof Member) && ((Member)astree).get().equals(s))
                    {
/* 323*/                if(paramTypeList != null)
/* 324*/                    i += paramTypeList.length;
                    } else
                    {
/* 327*/                i++;
                    }
                }

/* 332*/        return i;
            }

            public void atMethodArgs(ASTList astlist, int ai[], int ai1[], String as[])
                throws CompileError
            {
/* 337*/        CtClass actclass[] = paramTypeList;
/* 338*/        String s = paramListName;
/* 339*/        int i = 0;
/* 340*/        for(; astlist != null; astlist = astlist.tail())
                {
                    int j;
/* 341*/            if(((j = astlist.head()) instanceof Member) && ((Member)j).get().equals(s))
                    {
/* 343*/                if(actclass == null)
/* 344*/                    continue;
/* 344*/                j = actclass.length;
/* 345*/                int k = indexOfParam1();
/* 346*/                for(int l = 0; l < j; l++)
                        {
/* 347*/                    CtClass ctclass = actclass[l];
/* 348*/                    k += bytecode.addLoad(k, ctclass);
/* 349*/                    setType(ctclass);
/* 350*/                    ai[i] = exprType;
/* 351*/                    ai1[i] = arrayDim;
/* 352*/                    as[i] = className;
/* 353*/                    i++;
                        }

                    } else
                    {
/* 358*/                j.accept(this);
/* 359*/                ai[i] = exprType;
/* 360*/                ai1[i] = arrayDim;
/* 361*/                as[i] = className;
/* 362*/                i++;
                    }
                }

            }

            void compileInvokeSpecial(ASTree astree, String s, String s1, String s2, ASTList astlist)
                throws CompileError
            {
/* 401*/        astree.accept(this);
/* 402*/        astree = getMethodArgsLength(astlist);
/* 403*/        atMethodArgs(astlist, new int[astree], new int[astree], new String[astree]);
/* 405*/        bytecode.addInvokespecial(s, s1, s2);
/* 406*/        setReturnType(s2, false, false);
/* 407*/        addNullIfVoid();
            }

            protected void atReturnStmnt(Stmnt stmnt)
                throws CompileError
            {
/* 414*/        if((stmnt = stmnt.getLeft()) != null && returnType == CtClass.voidType)
                {
/* 416*/            compileExpr(stmnt);
/* 417*/            if(is2word(exprType, arrayDim))
/* 418*/                bytecode.addOpcode(88);
/* 419*/            else
/* 419*/            if(exprType != 344)
/* 420*/                bytecode.addOpcode(87);
/* 422*/            stmnt = null;
                }
/* 425*/        atReturnStmnt2(stmnt);
            }

            public int recordReturnType(CtClass ctclass, String s, String s1, SymbolTable symboltable)
                throws CompileError
            {
/* 441*/        returnType = ctclass;
/* 442*/        returnCastName = s;
/* 443*/        returnVarName = s1;
/* 444*/        if(s1 == null)
                {
/* 445*/            return -1;
                } else
                {
/* 447*/            ctclass = (s = getMaxLocals()) + recordVar(ctclass, s1, s, symboltable);
/* 449*/            setMaxLocals(ctclass);
/* 450*/            return s;
                }
            }

            public void recordType(CtClass ctclass)
            {
/* 458*/        dollarType = ctclass;
            }

            public int recordParams(CtClass actclass[], boolean flag, String s, String s1, String s2, SymbolTable symboltable)
                throws CompileError
            {
/* 471*/        return recordParams(actclass, flag, s, s1, s2, !flag, 0, getThisName(), symboltable);
            }

            public int recordParams(CtClass actclass[], boolean flag, String s, String s1, String s2, boolean flag1, int i, 
                    String s3, SymbolTable symboltable)
                throws CompileError
            {
/* 502*/        paramTypeList = actclass;
/* 503*/        paramArrayName = s1;
/* 504*/        paramListName = s2;
/* 505*/        paramVarBase = i;
/* 506*/        useParam0 = flag1;
/* 508*/        if(s3 != null)
/* 509*/            param0Type = MemberResolver.jvmToJavaName(s3);
/* 511*/        inStaticMethod = flag;
/* 512*/        flag = i;
/* 513*/        if(flag1)
                {
/* 514*/            s1 = (new StringBuilder()).append(s).append("0").toString();
/* 515*/            flag++;
/* 515*/            s2 = new Declarator(307, MemberResolver.javaToJvmName(s3), 0, i, new Symbol(s1));
/* 518*/            symboltable.append(s1, s2);
                }
/* 521*/        for(s1 = 0; s1 < actclass.length; s1++)
/* 522*/            flag += recordVar(actclass[s1], (new StringBuilder()).append(s).append(s1 + 1).toString(), flag, symboltable);

/* 524*/        if(getMaxLocals() < flag)
/* 525*/            setMaxLocals(flag);
/* 527*/        return ((flag) ? 1 : 0);
            }

            public int recordVariable(CtClass ctclass, String s, SymbolTable symboltable)
                throws CompileError
            {
/* 539*/        if(s == null)
                {
/* 540*/            return -1;
                } else
                {
                    int i;
/* 542*/            ctclass = (i = getMaxLocals()) + recordVar(ctclass, s, i, symboltable);
/* 544*/            setMaxLocals(ctclass);
/* 545*/            return i;
                }
            }

            private int recordVar(CtClass ctclass, String s, int i, SymbolTable symboltable)
                throws CompileError
            {
/* 552*/        if(ctclass == CtClass.voidType)
                {
/* 553*/            exprType = 307;
/* 554*/            arrayDim = 0;
/* 555*/            className = "java/lang/Object";
                } else
                {
/* 558*/            setType(ctclass);
                }
/* 560*/        ctclass = new Declarator(exprType, className, arrayDim, i, new Symbol(s));
/* 563*/        symboltable.append(s, ctclass);
/* 564*/        return !is2word(exprType, arrayDim) ? 1 : 2;
            }

            public void recordVariable(String s, String s1, int i, SymbolTable symboltable)
                throws CompileError
            {
                int j;
                int k;
/* 578*/        for(k = 0; (j = s.charAt(k)) == '['; k++);
/* 582*/        j = MemberResolver.descToType(j);
/* 583*/        String s2 = null;
/* 584*/        if(j == 307)
/* 585*/            if(k == 0)
/* 586*/                s2 = s.substring(1, s.length() - 1);
/* 588*/            else
/* 588*/                s2 = s.substring(k + 1, s.length() - 1);
/* 591*/        s = new Declarator(j, s2, k, i, new Symbol(s1));
/* 593*/        symboltable.append(s1, s);
            }

            public static int compileParameterList(Bytecode bytecode, CtClass actclass[], int i)
            {
/* 607*/        if(actclass == null)
                {
/* 608*/            bytecode.addIconst(0);
/* 609*/            bytecode.addAnewarray("java.lang.Object");
/* 610*/            return 1;
                }
/* 613*/        CtClass actclass1[] = new CtClass[1];
/* 614*/        int j = actclass.length;
/* 615*/        bytecode.addIconst(j);
/* 616*/        bytecode.addAnewarray("java.lang.Object");
/* 617*/        for(int k = 0; k < j; k++)
                {
/* 618*/            bytecode.addOpcode(89);
/* 619*/            bytecode.addIconst(k);
/* 620*/            if(actclass[k].isPrimitive())
                    {
                        CtPrimitiveType ctprimitivetype;
/* 621*/                String s = (ctprimitivetype = (CtPrimitiveType)actclass[k]).getWrapperName();
/* 623*/                bytecode.addNew(s);
/* 624*/                bytecode.addOpcode(89);
/* 625*/                int l = bytecode.addLoad(i, ctprimitivetype);
/* 626*/                i += l;
/* 627*/                actclass1[0] = ctprimitivetype;
/* 628*/                bytecode.addInvokespecial(s, "<init>", Descriptor.ofMethod(CtClass.voidType, actclass1));
                    } else
                    {
/* 633*/                bytecode.addAload(i);
/* 634*/                i++;
                    }
/* 637*/            bytecode.addOpcode(83);
                }

/* 640*/        return 8;
            }

            protected void compileUnwrapValue(CtClass ctclass, Bytecode bytecode)
                throws CompileError
            {
/* 647*/        if(ctclass == CtClass.voidType)
                {
/* 648*/            addNullIfVoid();
/* 649*/            return;
                }
/* 652*/        if(exprType == 344)
/* 653*/            throw new CompileError((new StringBuilder("invalid type for ")).append(returnCastName).toString());
/* 655*/        if(ctclass instanceof CtPrimitiveType)
                {
                    CtPrimitiveType ctprimitivetype;
/* 656*/            String s = (ctprimitivetype = (CtPrimitiveType)ctclass).getWrapperName();
/* 659*/            bytecode.addCheckcast(s);
/* 660*/            bytecode.addInvokevirtual(s, ctprimitivetype.getGetMethodName(), ctprimitivetype.getGetMethodDescriptor());
/* 662*/            setType(ctclass);
/* 663*/            return;
                } else
                {
/* 665*/            bytecode.addCheckcast(ctclass);
/* 666*/            setType(ctclass);
/* 668*/            return;
                }
            }

            public void setType(CtClass ctclass)
                throws CompileError
            {
/* 674*/        setType(ctclass, 0);
            }

            private void setType(CtClass ctclass, int i)
                throws CompileError
            {
/* 678*/        if(ctclass.isPrimitive())
                {
/* 679*/            ctclass = (CtPrimitiveType)ctclass;
/* 680*/            exprType = MemberResolver.descToType(ctclass.getDescriptor());
/* 681*/            arrayDim = i;
/* 682*/            className = null;
/* 683*/            return;
                }
/* 684*/        if(ctclass.isArray())
                {
/* 686*/            try
                    {
/* 686*/                setType(ctclass.getComponentType(), i + 1);
/* 690*/                return;
                    }
/* 688*/            catch(NotFoundException _ex)
                    {
/* 689*/                throw new CompileError((new StringBuilder("undefined type: ")).append(ctclass.getName()).toString());
                    }
                } else
                {
/* 692*/            exprType = 307;
/* 693*/            arrayDim = i;
/* 694*/            className = MemberResolver.javaToJvmName(ctclass.getName());
/* 696*/            return;
                }
            }

            public void doNumCast(CtClass ctclass)
                throws CompileError
            {
/* 701*/        if(arrayDim == 0 && !isRefType(exprType))
                {
/* 702*/            if(ctclass instanceof CtPrimitiveType)
                    {
/* 703*/                ctclass = (CtPrimitiveType)ctclass;
/* 704*/                atNumCastExpr(exprType, MemberResolver.descToType(ctclass.getDescriptor()));
/* 706*/                return;
                    } else
                    {
/* 708*/                throw new CompileError("type mismatch");
                    }
                } else
                {
/* 709*/            return;
                }
            }

            String paramArrayName;
            String paramListName;
            CtClass paramTypeList[];
            private int paramVarBase;
            private boolean useParam0;
            private String param0Type;
            public static final String sigName = "$sig";
            public static final String dollarTypeName = "$type";
            public static final String clazzName = "$class";
            private CtClass dollarType;
            CtClass returnType;
            String returnCastName;
            private String returnVarName;
            public static final String wrapperCastName = "$w";
            String proceedName;
            public static final String cflowName = "$cflow";
            ProceedHandler procHandler;
}
