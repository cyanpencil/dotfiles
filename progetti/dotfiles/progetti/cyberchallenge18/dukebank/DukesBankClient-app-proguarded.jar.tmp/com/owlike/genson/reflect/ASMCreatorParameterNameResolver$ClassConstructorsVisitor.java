// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASMCreatorParameterNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.internal.asm.ClassVisitor;
import com.owlike.genson.internal.asm.MethodVisitor;
import java.util.Map;

// Referenced classes of package com.owlike.genson.reflect:
//            ASMCreatorParameterNameResolver

class methodParameterNames extends ClassVisitor
{

            public MethodVisitor visitMethod(int i, String s, String s1, String s2, String as[])
            {
/* 143*/        s2 = (i & 8) <= 0 ? 0 : 1;
/* 144*/        if((i & 0x400) == 0)
                {
/* 145*/            if("<init>".equals(s))
/* 146*/                return new (ASMCreatorParameterNameResolver.this, forClass, s2, s1, ctrParameterNames);
/* 148*/            if(!"<clinit>".equals(s))
/* 149*/                return new ctrParameterNames(ASMCreatorParameterNameResolver.this, s, forClass, s2, s1, methodParameterNames);
                }
/* 151*/        return null;
            }

            private static final String CONSTRUCTOR_METHOD_NAME = "<init>";
            private final Class forClass;
            final Map ctrParameterNames;
            final Map methodParameterNames;
            final ASMCreatorParameterNameResolver this$0;

            public (Class class1, Map map, Map map1)
            {
/* 134*/        this$0 = ASMCreatorParameterNameResolver.this;
/* 135*/        super(0x50000);
/* 136*/        forClass = class1;
/* 137*/        ctrParameterNames = map;
/* 138*/        methodParameterNames = map1;
            }
}
