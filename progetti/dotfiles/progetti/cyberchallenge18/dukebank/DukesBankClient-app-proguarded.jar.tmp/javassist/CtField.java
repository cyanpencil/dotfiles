// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import java.util.List;
import java.util.ListIterator;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.SignatureAttribute;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.SymbolTable;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.StringL;

// Referenced classes of package javassist:
//            CtMember, CannotCompileException, CtClass, CtClassType, 
//            NotFoundException, CtNewWrappedMethod, CtPrimitiveType

public class CtField extends CtMember
{
    static class MultiArrayInitializer extends Initializer
    {

                void check(String s)
                    throws CannotCompileException
                {
/*1392*/            if(s.charAt(0) != '[')
/*1393*/                throw new CannotCompileException("type mismatch");
/*1394*/            else
/*1394*/                return;
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1400*/            bytecode.addAload(0);
/*1401*/            actclass = bytecode.addMultiNewarray(ctclass, dim);
/*1402*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1403*/            return actclass + 1;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1409*/            javac = bytecode.addMultiNewarray(ctclass, dim);
/*1410*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1411*/            return javac;
                }

                CtClass type;
                int dim[];

                MultiArrayInitializer(CtClass ctclass, int ai[])
                {
/*1389*/            type = ctclass;
/*1389*/            dim = ai;
                }
    }

    static class ArrayInitializer extends Initializer
    {

                private void addNewarray(Bytecode bytecode)
                {
/*1359*/            if(type.isPrimitive())
                    {
/*1360*/                bytecode.addNewarray(((CtPrimitiveType)type).getArrayType(), size);
/*1360*/                return;
                    } else
                    {
/*1363*/                bytecode.addAnewarray(type, size);
/*1364*/                return;
                    }
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1370*/            bytecode.addAload(0);
/*1371*/            addNewarray(bytecode);
/*1372*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1373*/            return 2;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1379*/            addNewarray(bytecode);
/*1380*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1381*/            return 1;
                }

                CtClass type;
                int size;

                ArrayInitializer(CtClass ctclass, int i)
                {
/*1356*/            type = ctclass;
/*1356*/            size = i;
                }
    }

    static class StringInitializer extends Initializer
    {

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1330*/            bytecode.addAload(0);
/*1331*/            bytecode.addLdc(value);
/*1332*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1333*/            return 2;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1339*/            bytecode.addLdc(value);
/*1340*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1341*/            return 1;
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/*1345*/            if(ctclass.getName().equals("java.lang.String"))
/*1346*/                return constpool.addStringInfo(value);
/*1348*/            else
/*1348*/                return 0;
                }

                String value;

                StringInitializer(String s)
                {
/*1324*/            value = s;
                }
    }

    static class DoubleInitializer extends Initializer
    {

                void check(String s)
                    throws CannotCompileException
                {
/*1291*/            if(!s.equals("D"))
/*1292*/                throw new CannotCompileException("type mismatch");
/*1293*/            else
/*1293*/                return;
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1299*/            bytecode.addAload(0);
/*1300*/            bytecode.addLdc2w(value);
/*1301*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1302*/            return 3;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1308*/            bytecode.addLdc2w(value);
/*1309*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1310*/            return 2;
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/*1314*/            if(ctclass == CtClass.doubleType)
/*1315*/                return constpool.addDoubleInfo(value);
/*1317*/            else
/*1317*/                return 0;
                }

                double value;

                DoubleInitializer(double d)
                {
/*1288*/            value = d;
                }
    }

    static class FloatInitializer extends Initializer
    {

                void check(String s)
                    throws CannotCompileException
                {
/*1255*/            if(!s.equals("F"))
/*1256*/                throw new CannotCompileException("type mismatch");
/*1257*/            else
/*1257*/                return;
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1263*/            bytecode.addAload(0);
/*1264*/            bytecode.addFconst(value);
/*1265*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1266*/            return 3;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1272*/            bytecode.addFconst(value);
/*1273*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1274*/            return 2;
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/*1278*/            if(ctclass == CtClass.floatType)
/*1279*/                return constpool.addFloatInfo(value);
/*1281*/            else
/*1281*/                return 0;
                }

                float value;

                FloatInitializer(float f)
                {
/*1252*/            value = f;
                }
    }

    static class LongInitializer extends Initializer
    {

                void check(String s)
                    throws CannotCompileException
                {
/*1219*/            if(!s.equals("J"))
/*1220*/                throw new CannotCompileException("type mismatch");
/*1221*/            else
/*1221*/                return;
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1227*/            bytecode.addAload(0);
/*1228*/            bytecode.addLdc2w(value);
/*1229*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1230*/            return 3;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1236*/            bytecode.addLdc2w(value);
/*1237*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1238*/            return 2;
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/*1242*/            if(ctclass == CtClass.longType)
/*1243*/                return constpool.addLongInfo(value);
/*1245*/            else
/*1245*/                return 0;
                }

                long value;

                LongInitializer(long l)
                {
/*1216*/            value = l;
                }
    }

    static class IntInitializer extends Initializer
    {

                void check(String s)
                    throws CannotCompileException
                {
/*1185*/            if((s = s.charAt(0)) != 'I' && s != 83 && s != 66 && s != 67 && s != 90)
/*1187*/                throw new CannotCompileException("type mismatch");
/*1188*/            else
/*1188*/                return;
                }

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1194*/            bytecode.addAload(0);
/*1195*/            bytecode.addIconst(value);
/*1196*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1197*/            return 2;
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1203*/            bytecode.addIconst(value);
/*1204*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1205*/            return 1;
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/*1209*/            return constpool.addIntegerInfo(value);
                }

                int value;

                IntInitializer(int i)
                {
/*1182*/            value = i;
                }
    }

    static class MethodInitializer extends NewInitializer
    {

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1121*/            bytecode.addAload(0);
/*1122*/            bytecode.addAload(0);
/*1124*/            if(stringParams == null)
/*1125*/                javac = 2;
/*1127*/            else
/*1127*/                javac = compileStringParameter(bytecode) + 2;
/*1129*/            if(withConstructorParams)
/*1130*/                javac += CtNewWrappedMethod.compileParameterList(bytecode, actclass, 1);
/*1133*/            ctclass = Descriptor.of(ctclass);
/*1134*/            actclass = (new StringBuilder()).append(getDescriptor()).append(ctclass).toString();
/*1135*/            bytecode.addInvokestatic(objectType, methodName, actclass);
/*1136*/            bytecode.addPutfield(Bytecode.THIS, s, ctclass);
/*1137*/            return javac;
                }

                private String getDescriptor()
                {
/*1144*/            if(stringParams == null)
/*1145*/                if(withConstructorParams)
/*1146*/                    return "(Ljava/lang/Object;[Ljava/lang/Object;)";
/*1148*/                else
/*1148*/                    return "(Ljava/lang/Object;)";
/*1150*/            if(withConstructorParams)
/*1151*/                return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)";
/*1153*/            else
/*1153*/                return "(Ljava/lang/Object;[Ljava/lang/String;)";
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1164*/            int i = 1;
/*1165*/            if(stringParams == null)
                    {
/*1166*/                javac = "()";
                    } else
                    {
/*1168*/                javac = "([Ljava/lang/String;)";
/*1169*/                i = 1 + compileStringParameter(bytecode);
                    }
/*1172*/            ctclass = Descriptor.of(ctclass);
/*1173*/            bytecode.addInvokestatic(objectType, methodName, (new StringBuilder()).append(javac).append(ctclass).toString());
/*1174*/            bytecode.addPutstatic(Bytecode.THIS, s, ctclass);
/*1175*/            return i;
                }

                String methodName;

                MethodInitializer()
                {
                }
    }

    static class NewInitializer extends Initializer
    {

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/*1025*/            bytecode.addAload(0);
/*1026*/            bytecode.addNew(objectType);
/*1027*/            bytecode.add(89);
/*1028*/            bytecode.addAload(0);
/*1030*/            if(stringParams == null)
/*1031*/                javac = 4;
/*1033*/            else
/*1033*/                javac = compileStringParameter(bytecode) + 4;
/*1035*/            if(withConstructorParams)
/*1036*/                javac += CtNewWrappedMethod.compileParameterList(bytecode, actclass, 1);
/*1039*/            bytecode.addInvokespecial(objectType, "<init>", getDescriptor());
/*1040*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1041*/            return javac;
                }

                private String getDescriptor()
                {
/*1048*/            if(stringParams == null)
/*1049*/                if(withConstructorParams)
/*1050*/                    return "(Ljava/lang/Object;[Ljava/lang/Object;)V";
/*1052*/                else
/*1052*/                    return "(Ljava/lang/Object;)V";
/*1054*/            if(withConstructorParams)
/*1055*/                return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)V";
/*1057*/            else
/*1057*/                return "(Ljava/lang/Object;[Ljava/lang/String;)V";
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1068*/            bytecode.addNew(objectType);
/*1069*/            bytecode.add(89);
/*1071*/            int i = 2;
/*1072*/            if(stringParams == null)
                    {
/*1073*/                javac = "()V";
                    } else
                    {
/*1075*/                javac = "([Ljava/lang/String;)V";
/*1076*/                i = 2 + compileStringParameter(bytecode);
                    }
/*1079*/            bytecode.addInvokespecial(objectType, "<init>", javac);
/*1080*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1081*/            return i;
                }

                protected final int compileStringParameter(Bytecode bytecode)
                    throws CannotCompileException
                {
/*1087*/            int i = stringParams.length;
/*1088*/            bytecode.addIconst(i);
/*1089*/            bytecode.addAnewarray("java.lang.String");
/*1090*/            for(int j = 0; j < i; j++)
                    {
/*1091*/                bytecode.add(89);
/*1092*/                bytecode.addIconst(j);
/*1093*/                bytecode.addLdc(stringParams[j]);
/*1094*/                bytecode.add(83);
                    }

/*1097*/            return 4;
                }

                CtClass objectType;
                String stringParams[];
                boolean withConstructorParams;

                NewInitializer()
                {
                }
    }

    static class ParamInitializer extends Initializer
    {

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/* 958*/            if(actclass != null && nthParam < actclass.length)
                    {
/* 959*/                bytecode.addAload(0);
/* 960*/                actclass = nthParamToLocal(nthParam, actclass, false);
/* 961*/                actclass = bytecode.addLoad(actclass, ctclass) + 1;
/* 962*/                bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 963*/                return actclass;
                    } else
                    {
/* 966*/                return 0;
                    }
                }

                static int nthParamToLocal(int i, CtClass actclass[], boolean flag)
                {
/* 979*/            CtClass ctclass = CtClass.longType;
/* 980*/            CtClass ctclass1 = CtClass.doubleType;
/* 982*/            if(flag)
/* 983*/                flag = false;
/* 985*/            else
/* 985*/                flag = true;
/* 987*/            for(int j = 0; j < i; j++)
                    {
                        CtClass ctclass2;
/* 988*/                if((ctclass2 = actclass[j]) == ctclass || ctclass2 == ctclass1)
/* 990*/                    flag += 2;
/* 992*/                else
/* 992*/                    flag++;
                    }

/* 995*/            return ((flag) ? 1 : 0);
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/*1001*/            return 0;
                }

                int nthParam;

                ParamInitializer()
                {
                }
    }

    static class PtreeInitializer extends CodeInitializer0
    {

                void compileExpr(Javac javac)
                    throws CompileError
                {
/* 937*/            javac.compileExpr(expression);
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/* 941*/            return getConstantValue2(constpool, ctclass, expression);
                }

                private ASTree expression;

                PtreeInitializer(ASTree astree)
                {
/* 934*/            expression = astree;
                }
    }

    static class CodeInitializer extends CodeInitializer0
    {

                void compileExpr(Javac javac)
                    throws CompileError
                {
/* 917*/            javac.compileExpr(expression);
                }

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/* 922*/            ASTree astree = Javac.parseExpr(expression, new SymbolTable());
/* 923*/            return getConstantValue2(constpool, ctclass, astree);
/* 925*/            JVM INSTR pop ;
/* 926*/            return 0;
                }

                private String expression;

                CodeInitializer(String s)
                {
/* 914*/            expression = s;
                }
    }

    static abstract class CodeInitializer0 extends Initializer
    {

                abstract void compileExpr(Javac javac)
                    throws CompileError;

                int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException
                {
/* 859*/            bytecode.addAload(0);
/* 860*/            compileExpr(javac);
/* 861*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 862*/            return bytecode.getMaxStack();
/* 864*/            ctclass;
/* 865*/            throw new CannotCompileException(ctclass);
                }

                int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException
                {
/* 873*/            compileExpr(javac);
/* 874*/            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 875*/            return bytecode.getMaxStack();
/* 877*/            ctclass;
/* 878*/            throw new CannotCompileException(ctclass);
                }

                int getConstantValue2(ConstPool constpool, CtClass ctclass, ASTree astree)
                {
/* 883*/            if(ctclass.isPrimitive())
                    {
/* 884*/                if(astree instanceof IntConst)
                        {
/* 885*/                    long l = ((IntConst)astree).get();
/* 886*/                    if(ctclass == CtClass.doubleType)
/* 887*/                        return constpool.addDoubleInfo(l);
/* 888*/                    if(ctclass == CtClass.floatType)
/* 889*/                        return constpool.addFloatInfo(l);
/* 890*/                    if(ctclass == CtClass.longType)
/* 891*/                        return constpool.addLongInfo(l);
/* 892*/                    if(ctclass != CtClass.voidType)
/* 893*/                        return constpool.addIntegerInfo((int)l);
                        } else
/* 895*/                if(astree instanceof DoubleConst)
                        {
/* 896*/                    double d = ((DoubleConst)astree).get();
/* 897*/                    if(ctclass == CtClass.floatType)
/* 898*/                        return constpool.addFloatInfo((float)d);
/* 899*/                    if(ctclass == CtClass.doubleType)
/* 900*/                        return constpool.addDoubleInfo(d);
                        }
                    } else
/* 903*/            if((astree instanceof StringL) && ctclass.getName().equals("java.lang.String"))
/* 905*/                return constpool.addStringInfo(((StringL)astree).get());
/* 907*/            return 0;
                }

                CodeInitializer0()
                {
                }
    }

    public static abstract class Initializer
    {

                public static Initializer constant(int i)
                {
/* 484*/            return new IntInitializer(i);
                }

                public static Initializer constant(boolean flag)
                {
/* 492*/            return new IntInitializer(flag ? 1 : 0);
                }

                public static Initializer constant(long l)
                {
/* 500*/            return new LongInitializer(l);
                }

                public static Initializer constant(float f)
                {
/* 508*/            return new FloatInitializer(f);
                }

                public static Initializer constant(double d)
                {
/* 516*/            return new DoubleInitializer(d);
                }

                public static Initializer constant(String s)
                {
/* 524*/            return new StringInitializer(s);
                }

                public static Initializer byParameter(int i)
                {
                    ParamInitializer paraminitializer;
/* 542*/            (paraminitializer = new ParamInitializer()).nthParam = i;
/* 544*/            return paraminitializer;
                }

                public static Initializer byNew(CtClass ctclass)
                {
                    NewInitializer newinitializer;
/* 563*/            (newinitializer = new NewInitializer()).objectType = ctclass;
/* 565*/            newinitializer.stringParams = null;
/* 566*/            newinitializer.withConstructorParams = false;
/* 567*/            return newinitializer;
                }

                public static Initializer byNew(CtClass ctclass, String as[])
                {
                    NewInitializer newinitializer;
/* 591*/            (newinitializer = new NewInitializer()).objectType = ctclass;
/* 593*/            newinitializer.stringParams = as;
/* 594*/            newinitializer.withConstructorParams = false;
/* 595*/            return newinitializer;
                }

                public static Initializer byNewWithParams(CtClass ctclass)
                {
                    NewInitializer newinitializer;
/* 620*/            (newinitializer = new NewInitializer()).objectType = ctclass;
/* 622*/            newinitializer.stringParams = null;
/* 623*/            newinitializer.withConstructorParams = true;
/* 624*/            return newinitializer;
                }

                public static Initializer byNewWithParams(CtClass ctclass, String as[])
                {
                    NewInitializer newinitializer;
/* 651*/            (newinitializer = new NewInitializer()).objectType = ctclass;
/* 653*/            newinitializer.stringParams = as;
/* 654*/            newinitializer.withConstructorParams = true;
/* 655*/            return newinitializer;
                }

                public static Initializer byCall(CtClass ctclass, String s)
                {
                    MethodInitializer methodinitializer;
/* 680*/            (methodinitializer = new MethodInitializer()).objectType = ctclass;
/* 682*/            methodinitializer.methodName = s;
/* 683*/            methodinitializer.stringParams = null;
/* 684*/            methodinitializer.withConstructorParams = false;
/* 685*/            return methodinitializer;
                }

                public static Initializer byCall(CtClass ctclass, String s, String as[])
                {
                    MethodInitializer methodinitializer;
/* 715*/            (methodinitializer = new MethodInitializer()).objectType = ctclass;
/* 717*/            methodinitializer.methodName = s;
/* 718*/            methodinitializer.stringParams = as;
/* 719*/            methodinitializer.withConstructorParams = false;
/* 720*/            return methodinitializer;
                }

                public static Initializer byCallWithParams(CtClass ctclass, String s)
                {
                    MethodInitializer methodinitializer;
/* 748*/            (methodinitializer = new MethodInitializer()).objectType = ctclass;
/* 750*/            methodinitializer.methodName = s;
/* 751*/            methodinitializer.stringParams = null;
/* 752*/            methodinitializer.withConstructorParams = true;
/* 753*/            return methodinitializer;
                }

                public static Initializer byCallWithParams(CtClass ctclass, String s, String as[])
                {
                    MethodInitializer methodinitializer;
/* 785*/            (methodinitializer = new MethodInitializer()).objectType = ctclass;
/* 787*/            methodinitializer.methodName = s;
/* 788*/            methodinitializer.stringParams = as;
/* 789*/            methodinitializer.withConstructorParams = true;
/* 790*/            return methodinitializer;
                }

                public static Initializer byNewArray(CtClass ctclass, int i)
                    throws NotFoundException
                {
/* 804*/            return new ArrayInitializer(ctclass.getComponentType(), i);
                }

                public static Initializer byNewArray(CtClass ctclass, int ai[])
                {
/* 817*/            return new MultiArrayInitializer(ctclass, ai);
                }

                public static Initializer byExpr(String s)
                {
/* 826*/            return new CodeInitializer(s);
                }

                static Initializer byExpr(ASTree astree)
                {
/* 830*/            return new PtreeInitializer(astree);
                }

                void check(String s)
                    throws CannotCompileException
                {
                }

                abstract int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                    throws CannotCompileException;

                abstract int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                    throws CannotCompileException;

                int getConstantValue(ConstPool constpool, CtClass ctclass)
                {
/* 848*/            return 0;
                }

                public Initializer()
                {
                }
    }


            public CtField(CtClass ctclass, String s, CtClass ctclass1)
                throws CannotCompileException
            {
/*  61*/        this(Descriptor.of(ctclass), s, ctclass1);
            }

            public CtField(CtField ctfield, CtClass ctclass)
                throws CannotCompileException
            {
/*  84*/        this(ctfield.fieldInfo.getDescriptor(), ctfield.fieldInfo.getName(), ctclass);
/*  86*/        ctclass = ctfield.fieldInfo.getAttributes().listIterator();
                FieldInfo fieldinfo;
/*  88*/        (fieldinfo = fieldInfo).setAccessFlags(ctfield.fieldInfo.getAccessFlags());
/*  90*/        ctfield = fieldinfo.getConstPool();
                AttributeInfo attributeinfo;
/*  91*/        for(; ctclass.hasNext(); fieldinfo.addAttribute(attributeinfo.copy(ctfield, null)))
/*  92*/            attributeinfo = (AttributeInfo)ctclass.next();

            }

            private CtField(String s, String s1, CtClass ctclass)
                throws CannotCompileException
            {
/* 100*/        super(ctclass);
                ClassFile classfile;
/* 101*/        if((classfile = ctclass.getClassFile2()) == null)
                {
/* 103*/            throw new CannotCompileException((new StringBuilder("bad declaring class: ")).append(ctclass.getName()).toString());
                } else
                {
/* 106*/            fieldInfo = new FieldInfo(classfile.getConstPool(), s1, s);
/* 107*/            return;
                }
            }

            CtField(FieldInfo fieldinfo, CtClass ctclass)
            {
/* 110*/        super(ctclass);
/* 111*/        fieldInfo = fieldinfo;
            }

            public String toString()
            {
/* 118*/        return (new StringBuilder()).append(getDeclaringClass().getName()).append(".").append(getName()).append(":").append(fieldInfo.getDescriptor()).toString();
            }

            protected void extendToString(StringBuffer stringbuffer)
            {
/* 123*/        stringbuffer.append(' ');
/* 124*/        stringbuffer.append(getName());
/* 125*/        stringbuffer.append(' ');
/* 126*/        stringbuffer.append(fieldInfo.getDescriptor());
            }

            protected ASTree getInitAST()
            {
/* 131*/        return null;
            }

            Initializer getInit()
            {
                ASTree astree;
/* 136*/        if((astree = getInitAST()) == null)
/* 138*/            return null;
/* 140*/        else
/* 140*/            return Initializer.byExpr(astree);
            }

            public static CtField make(String s, CtClass ctclass)
                throws CannotCompileException
            {
/* 160*/        ctclass = new Javac(ctclass);
/* 162*/        if((s = ctclass.compile(s)) instanceof CtField)
/* 164*/            return (CtField)s;
/* 170*/        else
/* 170*/            throw new CannotCompileException("not a field");
/* 166*/        s;
/* 167*/        throw new CannotCompileException(s);
            }

            public FieldInfo getFieldInfo()
            {
/* 177*/        declaringClass.checkModify();
/* 178*/        return fieldInfo;
            }

            public FieldInfo getFieldInfo2()
            {
/* 200*/        return fieldInfo;
            }

            public CtClass getDeclaringClass()
            {
/* 207*/        return super.getDeclaringClass();
            }

            public String getName()
            {
/* 214*/        return fieldInfo.getName();
            }

            public void setName(String s)
            {
/* 221*/        declaringClass.checkModify();
/* 222*/        fieldInfo.setName(s);
            }

            public int getModifiers()
            {
/* 231*/        return AccessFlag.toModifier(fieldInfo.getAccessFlags());
            }

            public void setModifiers(int i)
            {
/* 240*/        declaringClass.checkModify();
/* 241*/        fieldInfo.setAccessFlags(AccessFlag.of(i));
            }

            public boolean hasAnnotation(Class class1)
            {
                Object obj;
/* 252*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((FieldInfo) (obj = getFieldInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 255*/        obj = (AnnotationsAttribute)((FieldInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 257*/        return CtClassType.hasAnnotationType(class1, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public Object getAnnotation(Class class1)
                throws ClassNotFoundException
            {
                Object obj;
/* 273*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((FieldInfo) (obj = getFieldInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 276*/        obj = (AnnotationsAttribute)((FieldInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 278*/        return CtClassType.getAnnotationType(class1, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public Object[] getAnnotations()
                throws ClassNotFoundException
            {
/* 290*/        return getAnnotations(false);
            }

            public Object[] getAvailableAnnotations()
            {
/* 304*/        return getAnnotations(true);
                ClassNotFoundException classnotfoundexception;
/* 306*/        classnotfoundexception;
/* 307*/        throw new RuntimeException("Unexpected exception", classnotfoundexception);
            }

            private Object[] getAnnotations(boolean flag)
                throws ClassNotFoundException
            {
                Object obj;
/* 312*/        AnnotationsAttribute annotationsattribute = (AnnotationsAttribute)((FieldInfo) (obj = getFieldInfo2())).getAttribute("RuntimeInvisibleAnnotations");
/* 315*/        obj = (AnnotationsAttribute)((FieldInfo) (obj)).getAttribute("RuntimeVisibleAnnotations");
/* 317*/        return CtClassType.toAnnotationType(flag, getDeclaringClass().getClassPool(), annotationsattribute, ((AnnotationsAttribute) (obj)));
            }

            public String getSignature()
            {
/* 336*/        return fieldInfo.getDescriptor();
            }

            public String getGenericSignature()
            {
                SignatureAttribute signatureattribute;
/* 347*/        if((signatureattribute = (SignatureAttribute)fieldInfo.getAttribute("Signature")) == null)
/* 349*/            return null;
/* 349*/        else
/* 349*/            return signatureattribute.getSignature();
            }

            public void setGenericSignature(String s)
            {
/* 363*/        declaringClass.checkModify();
/* 364*/        fieldInfo.addAttribute(new SignatureAttribute(fieldInfo.getConstPool(), s));
            }

            public CtClass getType()
                throws NotFoundException
            {
/* 371*/        return Descriptor.toCtClass(fieldInfo.getDescriptor(), declaringClass.getClassPool());
            }

            public void setType(CtClass ctclass)
            {
/* 379*/        declaringClass.checkModify();
/* 380*/        fieldInfo.setDescriptor(Descriptor.of(ctclass));
            }

            public Object getConstantValue()
            {
                int i;
/* 401*/        if((i = fieldInfo.getConstantValue()) == 0)
/* 403*/            return null;
                ConstPool constpool;
/* 405*/        switch((constpool = fieldInfo.getConstPool()).getTag(i))
                {
/* 408*/        case 5: // '\005'
/* 408*/            return new Long(constpool.getLongInfo(i));

/* 410*/        case 4: // '\004'
/* 410*/            return new Float(constpool.getFloatInfo(i));

/* 412*/        case 6: // '\006'
/* 412*/            return new Double(constpool.getDoubleInfo(i));

/* 414*/        case 3: // '\003'
/* 414*/            i = constpool.getIntegerInfo(i);
/* 416*/            if("Z".equals(fieldInfo.getDescriptor()))
/* 417*/                return new Boolean(i != 0);
/* 419*/            else
/* 419*/                return new Integer(i);

/* 421*/        case 8: // '\b'
/* 421*/            return constpool.getStringInfo(i);

/* 423*/        case 7: // '\007'
/* 423*/        default:
/* 423*/            throw new RuntimeException((new StringBuilder("bad tag: ")).append(constpool.getTag(i)).append(" at ").append(i).toString());
                }
            }

            public byte[] getAttribute(String s)
            {
/* 440*/        if((s = fieldInfo.getAttribute(s)) == null)
/* 442*/            return null;
/* 444*/        else
/* 444*/            return s.get();
            }

            public void setAttribute(String s, byte abyte0[])
            {
/* 458*/        declaringClass.checkModify();
/* 459*/        fieldInfo.addAttribute(new AttributeInfo(fieldInfo.getConstPool(), s, abyte0));
            }

            static final String javaLangString = "java.lang.String";
            protected FieldInfo fieldInfo;
}
