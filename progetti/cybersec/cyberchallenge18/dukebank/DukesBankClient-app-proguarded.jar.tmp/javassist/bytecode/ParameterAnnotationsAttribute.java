// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParameterAnnotationsAttribute.java

package javassist.bytecode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationsWriter;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, AnnotationsAttribute, ConstPool

public class ParameterAnnotationsAttribute extends AttributeInfo
{

            public ParameterAnnotationsAttribute(ConstPool constpool, String s, byte abyte0[])
            {
/*  70*/        super(constpool, s, abyte0);
            }

            public ParameterAnnotationsAttribute(ConstPool constpool, String s)
            {
/*  85*/        this(constpool, s, new byte[] {
/*  85*/            0
                });
            }

            ParameterAnnotationsAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  94*/        super(constpool, i, datainputstream);
            }

            public int numParameters()
            {
/* 101*/        return info[0] & 0xff;
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 108*/        map = new AnnotationsAttribute.Copier(info, constPool, constpool, map);
/* 110*/        map.parameters();
/* 111*/        return new ParameterAnnotationsAttribute(constpool, getName(), map.close());
/* 114*/        constpool;
/* 115*/        throw new RuntimeException(constpool.toString());
            }

            public Annotation[][] getAnnotations()
            {
/* 133*/        return (new AnnotationsAttribute.Parser(info, constPool)).parseParameters();
                Exception exception;
/* 135*/        exception;
/* 136*/        throw new RuntimeException(exception.toString());
            }

            public void setAnnotations(Annotation aannotation[][])
            {
/* 150*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 151*/        AnnotationsWriter annotationswriter = new AnnotationsWriter(bytearrayoutputstream, constPool);
/* 153*/        try
                {
/* 153*/            int i = aannotation.length;
/* 154*/            annotationswriter.numParameters(i);
/* 155*/            for(int j = 0; j < i; j++)
                    {
/* 156*/                Annotation aannotation1[] = aannotation[j];
/* 157*/                annotationswriter.numAnnotations(aannotation1.length);
/* 158*/                for(int k = 0; k < aannotation1.length; k++)
/* 159*/                    aannotation1[k].write(annotationswriter);

                    }

/* 162*/            annotationswriter.close();
                }
/* 164*/        catch(IOException ioexception)
                {
/* 165*/            throw new RuntimeException(ioexception);
                }
/* 168*/        set(bytearrayoutputstream.toByteArray());
            }

            void renameClass(String s, String s1)
            {
                HashMap hashmap;
/* 176*/        (hashmap = new HashMap()).put(s, s1);
/* 178*/        renameClass(((Map) (hashmap)));
            }

            void renameClass(Map map)
            {
/* 182*/        map = new AnnotationsAttribute.Renamer(info, getConstPool(), map);
/* 184*/        try
                {
/* 184*/            map.parameters();
/* 187*/            return;
                }
                // Misplaced declaration of an exception variable
/* 185*/        catch(Map map)
                {
/* 186*/            throw new RuntimeException(map);
                }
            }

            void getRefClasses(Map map)
            {
/* 190*/        renameClass(map);
            }

            public String toString()
            {
/* 196*/        Annotation aannotation[][] = getAnnotations();
/* 197*/        StringBuilder stringbuilder = new StringBuilder();
/* 198*/        int i = 0;
/* 199*/        do
                {
/* 199*/            if(i >= aannotation.length)
/* 200*/                break;
/* 200*/            Annotation aannotation1[] = aannotation[i++];
/* 201*/            int j = 0;
/* 202*/            do
                    {
/* 202*/                if(j >= aannotation1.length)
/* 203*/                    break;
/* 203*/                stringbuilder.append(aannotation1[j++].toString());
/* 204*/                if(j != aannotation1.length)
/* 205*/                    stringbuilder.append(" ");
                    } while(true);
/* 208*/            if(i != aannotation.length)
/* 209*/                stringbuilder.append(", ");
                } while(true);
/* 212*/        return stringbuilder.toString();
            }

            public static final String visibleTag = "RuntimeVisibleParameterAnnotations";
            public static final String invisibleTag = "RuntimeInvisibleParameterAnnotations";
}
