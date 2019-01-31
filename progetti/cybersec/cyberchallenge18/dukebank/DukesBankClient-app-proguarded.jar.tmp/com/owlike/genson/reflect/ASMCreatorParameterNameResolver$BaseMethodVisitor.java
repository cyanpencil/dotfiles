// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASMCreatorParameterNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.internal.asm.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.owlike.genson.reflect:
//            ASMCreatorParameterNameResolver

abstract class paramTypes extends MethodVisitor
{

            public void visitLocalVariable(String s, String s1, String s2, Label label, Label label1, int i)
            {
/* 174*/        if(!ztatic)
/* 175*/            i--;
/* 178*/        if((i >= 0 || forClass.isMemberClass() && (forClass.getModifiers() & 8) == 0) && paramNames.size() < paramTypes.length)
/* 180*/            paramNames.add(s);
            }

            protected Class resolveClass(Type type)
            {
/* 185*/        type.getSort();
/* 185*/        JVM INSTR tableswitch 0 10: default 180
            //                           0 176
            //                           1 82
            //                           2 90
            //                           3 86
            //                           4 172
            //                           5 102
            //                           6 98
            //                           7 106
            //                           8 94
            //                           9 64
            //                           10 110;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L11:
/* 187*/        return Array.newInstance(type = resolveClass(type.getElementType()), 0).getClass();
_L3:
/* 190*/        return Boolean.TYPE;
_L5:
/* 192*/        return Byte.TYPE;
_L4:
/* 194*/        return Character.TYPE;
_L10:
/* 196*/        return Double.TYPE;
_L8:
/* 198*/        return Float.TYPE;
_L7:
/* 200*/        return Integer.TYPE;
_L9:
/* 202*/        return Long.TYPE;
_L12:
/* 205*/        return Class.forName(type.getClassName(), true, forClass.getClassLoader());
                ClassNotFoundException classnotfoundexception;
/* 206*/        classnotfoundexception;
/* 207*/        throw new JsonBindingException((new StringBuilder("Could not find class ")).append(type.getClassName()).append(" while searching for constructor ").append(signature()).append(" parameter names.").toString(), classnotfoundexception);
_L6:
/* 213*/        return Short.TYPE;
_L2:
/* 215*/        return Void.TYPE;
_L1:
/* 218*/        throw new JsonBindingException((new StringBuilder("Could not find corresponding java type to asm type ")).append(type).toString());
            }

            public abstract String signature();

            protected Type paramTypes[];
            protected ArrayList paramNames;
            protected final Class forClass;
            protected boolean ztatic;
            final ASMCreatorParameterNameResolver this$0;

            public Q(Class class1, boolean flag, String s, Map map)
            {
/* 163*/        this$0 = ASMCreatorParameterNameResolver.this;
/* 164*/        super(0x50000);
/* 165*/        forClass = class1;
/* 166*/        ztatic = flag;
/* 167*/        paramTypes = Type.getArgumentTypes(s);
/* 168*/        paramNames = new ArrayList(paramTypes.length);
            }
}
