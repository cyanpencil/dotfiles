// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Annotation.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.util.*;
import javassist.*;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;

// Referenced classes of package javassist.bytecode.annotation:
//            AnnotationImpl, AnnotationMemberValue, AnnotationsWriter, ArrayMemberValue, 
//            BooleanMemberValue, ByteMemberValue, CharMemberValue, ClassMemberValue, 
//            DoubleMemberValue, EnumMemberValue, FloatMemberValue, IntegerMemberValue, 
//            LongMemberValue, MemberValue, NoSuchClassError, ShortMemberValue, 
//            StringMemberValue

public class Annotation
{
    static class Pair
    {

                int name;
                MemberValue value;

                Pair()
                {
                }
    }


            public Annotation(int i, ConstPool constpool)
            {
/*  72*/        pool = constpool;
/*  73*/        typeIndex = i;
/*  74*/        members = null;
            }

            public Annotation(String s, ConstPool constpool)
            {
/*  87*/        this(constpool.addUtf8Info(Descriptor.of(s)), constpool);
            }

            public Annotation(ConstPool constpool, CtClass ctclass)
                throws NotFoundException
            {
/* 103*/        this(constpool.addUtf8Info(Descriptor.of(ctclass.getName())), constpool);
/* 105*/        if(!ctclass.isInterface())
/* 106*/            throw new RuntimeException("Only interfaces are allowed for Annotation creation.");
/* 109*/        if((ctclass = ctclass.getDeclaredMethods()).length > 0)
/* 111*/            members = new LinkedHashMap();
/* 114*/        for(int i = 0; i < ctclass.length; i++)
                {
/* 115*/            CtClass ctclass1 = ctclass[i].getReturnType();
/* 116*/            addMemberValue(ctclass[i].getName(), createMemberValue(constpool, ctclass1));
                }

            }

            public static MemberValue createMemberValue(ConstPool constpool, CtClass ctclass)
                throws NotFoundException
            {
/* 133*/        if(ctclass == CtClass.booleanType)
/* 134*/            return new BooleanMemberValue(constpool);
/* 135*/        if(ctclass == CtClass.byteType)
/* 136*/            return new ByteMemberValue(constpool);
/* 137*/        if(ctclass == CtClass.charType)
/* 138*/            return new CharMemberValue(constpool);
/* 139*/        if(ctclass == CtClass.shortType)
/* 140*/            return new ShortMemberValue(constpool);
/* 141*/        if(ctclass == CtClass.intType)
/* 142*/            return new IntegerMemberValue(constpool);
/* 143*/        if(ctclass == CtClass.longType)
/* 144*/            return new LongMemberValue(constpool);
/* 145*/        if(ctclass == CtClass.floatType)
/* 146*/            return new FloatMemberValue(constpool);
/* 147*/        if(ctclass == CtClass.doubleType)
/* 148*/            return new DoubleMemberValue(constpool);
/* 149*/        if(ctclass.getName().equals("java.lang.Class"))
/* 150*/            return new ClassMemberValue(constpool);
/* 151*/        if(ctclass.getName().equals("java.lang.String"))
/* 152*/            return new StringMemberValue(constpool);
/* 153*/        if(ctclass.isArray())
                {
/* 154*/            CtClass ctclass1 = ctclass.getComponentType();
/* 155*/            ctclass = createMemberValue(constpool, ctclass1);
/* 156*/            return new ArrayMemberValue(ctclass, constpool);
                }
/* 158*/        if(ctclass.isInterface())
                {
/* 159*/            Annotation annotation = new Annotation(constpool, ctclass);
/* 160*/            return new AnnotationMemberValue(annotation, constpool);
                } else
                {
                    EnumMemberValue enummembervalue;
/* 166*/            (enummembervalue = new EnumMemberValue(constpool)).setType(ctclass.getName());
/* 168*/            return enummembervalue;
                }
            }

            public void addMemberValue(int i, MemberValue membervalue)
            {
                Pair pair;
/* 182*/        (pair = new Pair()).name = i;
/* 184*/        pair.value = membervalue;
/* 185*/        addMemberValue(pair);
            }

            public void addMemberValue(String s, MemberValue membervalue)
            {
                Pair pair;
/* 195*/        (pair = new Pair()).name = pool.addUtf8Info(s);
/* 197*/        pair.value = membervalue;
/* 198*/        if(members == null)
/* 199*/            members = new LinkedHashMap();
/* 201*/        members.put(s, pair);
            }

            private void addMemberValue(Pair pair)
            {
/* 205*/        String s = pool.getUtf8Info(pair.name);
/* 206*/        if(members == null)
/* 207*/            members = new LinkedHashMap();
/* 209*/        members.put(s, pair);
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 216*/        (stringbuffer = new StringBuffer("@")).append(getTypeName());
/* 218*/        if(members != null)
                {
/* 219*/            stringbuffer.append("(");
/* 220*/            Iterator iterator = members.keySet().iterator();
/* 221*/            do
                    {
/* 221*/                if(!iterator.hasNext())
/* 222*/                    break;
/* 222*/                String s = (String)iterator.next();
/* 223*/                stringbuffer.append(s).append("=").append(getMemberValue(s));
/* 224*/                if(iterator.hasNext())
/* 225*/                    stringbuffer.append(", ");
                    } while(true);
/* 227*/            stringbuffer.append(")");
                }
/* 230*/        return stringbuffer.toString();
            }

            public String getTypeName()
            {
/* 239*/        return Descriptor.toClassName(pool.getUtf8Info(typeIndex));
            }

            public Set getMemberNames()
            {
/* 248*/        if(members == null)
/* 249*/            return null;
/* 251*/        else
/* 251*/            return members.keySet();
            }

            public MemberValue getMemberValue(String s)
            {
/* 270*/        if(members == null)
/* 271*/            return null;
/* 273*/        if((s = (Pair)members.get(s)) == null)
/* 275*/            return null;
/* 277*/        else
/* 277*/            return ((Pair) (s)).value;
            }

            public Object toAnnotationType(ClassLoader classloader, ClassPool classpool)
                throws ClassNotFoundException, NoSuchClassError
            {
/* 295*/        return AnnotationImpl.make(classloader, MemberValue.loadClass(classloader, getTypeName()), classpool, this);
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/* 307*/        String s = pool.getUtf8Info(typeIndex);
/* 308*/        if(members == null)
                {
/* 309*/            annotationswriter.annotation(s, 0);
/* 310*/            return;
                }
/* 313*/        annotationswriter.annotation(s, members.size());
                Pair pair;
/* 314*/        for(Iterator iterator = members.values().iterator(); iterator.hasNext(); pair.value.write(annotationswriter))
                {
/* 316*/            pair = (Pair)iterator.next();
/* 317*/            annotationswriter.memberValuePair(pair.name);
                }

            }

            public boolean equals(Object obj)
            {
/* 327*/        if(obj == this)
/* 328*/            return true;
/* 329*/        if(obj == null || !(obj instanceof Annotation))
/* 330*/            return false;
/* 332*/        obj = (Annotation)obj;
/* 334*/        if(!getTypeName().equals(((Annotation) (obj)).getTypeName()))
/* 335*/            return false;
/* 337*/        obj = ((Annotation) (obj)).members;
/* 338*/        if(members == obj)
/* 339*/            return true;
/* 340*/        if(members == null)
/* 341*/            return obj == null;
/* 343*/        if(obj == null)
/* 344*/            return false;
/* 346*/        else
/* 346*/            return members.equals(obj);
            }

            ConstPool pool;
            int typeIndex;
            LinkedHashMap members;
}
