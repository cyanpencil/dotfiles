// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationDefaultAttribute.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;
import javassist.bytecode.annotation.AnnotationsWriter;
import javassist.bytecode.annotation.MemberValue;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, AnnotationsAttribute, ConstPool

public class AnnotationDefaultAttribute extends AttributeInfo
{

            public AnnotationDefaultAttribute(ConstPool constpool, byte abyte0[])
            {
/*  81*/        super(constpool, "AnnotationDefault", abyte0);
            }

            public AnnotationDefaultAttribute(ConstPool constpool)
            {
/*  92*/        this(constpool, new byte[] {
/*  92*/            0, 0
                });
            }

            AnnotationDefaultAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/* 101*/        super(constpool, i, datainputstream);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 108*/        map = new AnnotationsAttribute.Copier(info, constPool, constpool, map);
/* 111*/        map.memberValue(0);
/* 112*/        return new AnnotationDefaultAttribute(constpool, map.close());
/* 114*/        constpool;
/* 115*/        throw new RuntimeException(constpool.toString());
            }

            public MemberValue getDefaultValue()
            {
/* 125*/        return (new AnnotationsAttribute.Parser(info, constPool)).parseMemberValue();
                Exception exception;
/* 128*/        exception;
/* 129*/        throw new RuntimeException(exception.toString());
            }

            public void setDefaultValue(MemberValue membervalue)
            {
/* 140*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 141*/        AnnotationsWriter annotationswriter = new AnnotationsWriter(bytearrayoutputstream, constPool);
/* 143*/        try
                {
/* 143*/            membervalue.write(annotationswriter);
/* 144*/            annotationswriter.close();
                }
                // Misplaced declaration of an exception variable
/* 146*/        catch(MemberValue membervalue)
                {
/* 147*/            throw new RuntimeException(membervalue);
                }
/* 150*/        set(bytearrayoutputstream.toByteArray());
            }

            public String toString()
            {
/* 158*/        return getDefaultValue().toString();
            }

            public static final String tag = "AnnotationDefault";
}
