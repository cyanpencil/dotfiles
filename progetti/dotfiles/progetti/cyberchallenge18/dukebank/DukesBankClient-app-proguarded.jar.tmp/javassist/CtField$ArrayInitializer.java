// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField, CtPrimitiveType

static class size extends size
{

            private void addNewarray(Bytecode bytecode)
            {
/*1359*/        if(type.isPrimitive())
                {
/*1360*/            bytecode.addNewarray(((CtPrimitiveType)type).getArrayType(), size);
/*1360*/            return;
                } else
                {
/*1363*/            bytecode.addAnewarray(type, size);
/*1364*/            return;
                }
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1370*/        bytecode.addAload(0);
/*1371*/        addNewarray(bytecode);
/*1372*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1373*/        return 2;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1379*/        addNewarray(bytecode);
/*1380*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1381*/        return 1;
            }

            CtClass type;
            int size;

            (CtClass ctclass, int i)
            {
/*1356*/        type = ctclass;
/*1356*/        size = i;
            }
}
