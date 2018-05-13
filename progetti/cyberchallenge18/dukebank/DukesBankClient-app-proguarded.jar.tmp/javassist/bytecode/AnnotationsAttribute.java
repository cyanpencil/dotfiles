// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsAttribute.java

package javassist.bytecode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.AnnotationsWriter;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool, Descriptor

public class AnnotationsAttribute extends AttributeInfo
{
    static class Parser extends Walker
    {

                Annotation[][] parseParameters()
                    throws Exception
                {
/* 583*/            parameters();
/* 584*/            return allParams;
                }

                Annotation[] parseAnnotations()
                    throws Exception
                {
/* 588*/            annotationArray();
/* 589*/            return allAnno;
                }

                MemberValue parseMemberValue()
                    throws Exception
                {
/* 593*/            memberValue(0);
/* 594*/            return currentMember;
                }

                void parameters(int i, int j)
                    throws Exception
                {
/* 598*/            Annotation aannotation[][] = new Annotation[i][];
/* 599*/            for(int k = 0; k < i; k++)
                    {
/* 600*/                j = annotationArray(j);
/* 601*/                aannotation[k] = allAnno;
                    }

/* 604*/            allParams = aannotation;
                }

                int annotationArray(int i, int j)
                    throws Exception
                {
/* 608*/            Annotation aannotation[] = new Annotation[j];
/* 609*/            for(int k = 0; k < j; k++)
                    {
/* 610*/                i = annotation(i);
/* 611*/                aannotation[k] = currentAnno;
                    }

/* 614*/            allAnno = aannotation;
/* 615*/            return i;
                }

                int annotation(int i, int j, int k)
                    throws Exception
                {
/* 619*/            currentAnno = new Annotation(j, pool);
/* 620*/            return super.annotation(i, j, k);
                }

                int memberValuePair(int i, int j)
                    throws Exception
                {
/* 624*/            i = super.memberValuePair(i, j);
/* 625*/            currentAnno.addMemberValue(j, currentMember);
/* 626*/            return i;
                }

                void constValueMember(int i, int j)
                    throws Exception
                {
/* 631*/            Object obj = pool;
/* 632*/            switch(i)
                    {
/* 634*/            case 66: // 'B'
/* 634*/                obj = new ByteMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 637*/            case 67: // 'C'
/* 637*/                obj = new CharMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 640*/            case 68: // 'D'
/* 640*/                obj = new DoubleMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 643*/            case 70: // 'F'
/* 643*/                obj = new FloatMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 646*/            case 73: // 'I'
/* 646*/                obj = new IntegerMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 649*/            case 74: // 'J'
/* 649*/                obj = new LongMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 652*/            case 83: // 'S'
/* 652*/                obj = new ShortMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 655*/            case 90: // 'Z'
/* 655*/                obj = new BooleanMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 658*/            case 115: // 's'
/* 658*/                obj = new StringMemberValue(j, ((ConstPool) (obj)));
                        break;

/* 661*/            default:
/* 661*/                throw new RuntimeException((new StringBuilder("unknown tag:")).append(i).toString());
                    }
/* 664*/            currentMember = ((MemberValue) (obj));
/* 665*/            super.constValueMember(i, j);
                }

                void enumMemberValue(int i, int j, int k)
                    throws Exception
                {
/* 671*/            currentMember = new EnumMemberValue(j, k, pool);
/* 673*/            super.enumMemberValue(i, j, k);
                }

                void classMemberValue(int i, int j)
                    throws Exception
                {
/* 677*/            currentMember = new ClassMemberValue(j, pool);
/* 678*/            super.classMemberValue(i, j);
                }

                int annotationMemberValue(int i)
                    throws Exception
                {
/* 682*/            Annotation annotation1 = currentAnno;
/* 683*/            i = super.annotationMemberValue(i);
/* 684*/            currentMember = new AnnotationMemberValue(currentAnno, pool);
/* 685*/            currentAnno = annotation1;
/* 686*/            return i;
                }

                int arrayMemberValue(int i, int j)
                    throws Exception
                {
/* 690*/            ArrayMemberValue arraymembervalue = new ArrayMemberValue(pool);
/* 691*/            MemberValue amembervalue[] = new MemberValue[j];
/* 692*/            for(int k = 0; k < j; k++)
                    {
/* 693*/                i = memberValue(i);
/* 694*/                amembervalue[k] = currentMember;
                    }

/* 697*/            arraymembervalue.setValue(amembervalue);
/* 698*/            currentMember = arraymembervalue;
/* 699*/            return i;
                }

                ConstPool pool;
                Annotation allParams[][];
                Annotation allAnno[];
                Annotation currentAnno;
                MemberValue currentMember;

                Parser(byte abyte0[], ConstPool constpool)
                {
/* 578*/            super(abyte0);
/* 579*/            pool = constpool;
                }
    }

    static class Copier extends Walker
    {

                byte[] close()
                    throws IOException
                {
/* 482*/            writer.close();
/* 483*/            return output.toByteArray();
                }

                void parameters(int i, int j)
                    throws Exception
                {
/* 487*/            writer.numParameters(i);
/* 488*/            super.parameters(i, j);
                }

                int annotationArray(int i, int j)
                    throws Exception
                {
/* 492*/            writer.numAnnotations(j);
/* 493*/            return super.annotationArray(i, j);
                }

                int annotation(int i, int j, int k)
                    throws Exception
                {
/* 497*/            writer.annotation(copyType(j), k);
/* 498*/            return super.annotation(i, j, k);
                }

                int memberValuePair(int i, int j)
                    throws Exception
                {
/* 502*/            writer.memberValuePair(copy(j));
/* 503*/            return super.memberValuePair(i, j);
                }

                void constValueMember(int i, int j)
                    throws Exception
                {
/* 507*/            writer.constValueIndex(i, copy(j));
/* 508*/            super.constValueMember(i, j);
                }

                void enumMemberValue(int i, int j, int k)
                    throws Exception
                {
/* 514*/            writer.enumConstValue(copyType(j), copy(k));
/* 515*/            super.enumMemberValue(i, j, k);
                }

                void classMemberValue(int i, int j)
                    throws Exception
                {
/* 519*/            writer.classInfoIndex(copyType(j));
/* 520*/            super.classMemberValue(i, j);
                }

                int annotationMemberValue(int i)
                    throws Exception
                {
/* 524*/            writer.annotationValue();
/* 525*/            return super.annotationMemberValue(i);
                }

                int arrayMemberValue(int i, int j)
                    throws Exception
                {
/* 529*/            writer.arrayValue(j);
/* 530*/            return super.arrayMemberValue(i, j);
                }

                int copy(int i)
                {
/* 543*/            return srcPool.copy(i, destPool, classnames);
                }

                int copyType(int i)
                {
/* 557*/            i = Descriptor.rename(i = srcPool.getUtf8Info(i), classnames);
/* 559*/            return destPool.addUtf8Info(i);
                }

                ByteArrayOutputStream output;
                AnnotationsWriter writer;
                ConstPool srcPool;
                ConstPool destPool;
                Map classnames;

                Copier(byte abyte0[], ConstPool constpool, ConstPool constpool1, Map map)
                {
/* 473*/            super(abyte0);
/* 474*/            output = new ByteArrayOutputStream();
/* 475*/            writer = new AnnotationsWriter(output, constpool1);
/* 476*/            srcPool = constpool;
/* 477*/            destPool = constpool1;
/* 478*/            classnames = map;
                }
    }

    static class Renamer extends Walker
    {

                int annotation(int i, int j, int k)
                    throws Exception
                {
/* 429*/            renameType(i - 4, j);
/* 430*/            return super.annotation(i, j, k);
                }

                void enumMemberValue(int i, int j, int k)
                    throws Exception
                {
/* 436*/            renameType(i + 1, j);
/* 437*/            super.enumMemberValue(i, j, k);
                }

                void classMemberValue(int i, int j)
                    throws Exception
                {
/* 441*/            renameType(i + 1, j);
/* 442*/            super.classMemberValue(i, j);
                }

                private void renameType(int i, int j)
                {
/* 446*/            String s = Descriptor.rename(j = cpool.getUtf8Info(j), classnames);
/* 448*/            if(!j.equals(s))
/* 449*/                ByteArray.write16bit(j = cpool.addUtf8Info(s), info, i);
                }

                ConstPool cpool;
                Map classnames;

                Renamer(byte abyte0[], ConstPool constpool, Map map)
                {
/* 423*/            super(abyte0);
/* 424*/            cpool = constpool;
/* 425*/            classnames = map;
                }
    }

    static class Walker
    {

                final void parameters()
                    throws Exception
                {
/* 315*/            int i = info[0] & 0xff;
/* 316*/            parameters(i, 1);
                }

                void parameters(int i, int j)
                    throws Exception
                {
/* 320*/            for(int k = 0; k < i; k++)
/* 321*/                j = annotationArray(j);

                }

                final void annotationArray()
                    throws Exception
                {
/* 325*/            annotationArray(0);
                }

                final int annotationArray(int i)
                    throws Exception
                {
/* 329*/            int j = ByteArray.readU16bit(info, i);
/* 330*/            return annotationArray(i + 2, j);
                }

                int annotationArray(int i, int j)
                    throws Exception
                {
/* 334*/            for(int k = 0; k < j; k++)
/* 335*/                i = annotation(i);

/* 337*/            return i;
                }

                final int annotation(int i)
                    throws Exception
                {
/* 341*/            int j = ByteArray.readU16bit(info, i);
/* 342*/            int k = ByteArray.readU16bit(info, i + 2);
/* 343*/            return annotation(i + 4, j, k);
                }

                int annotation(int i, int j, int k)
                    throws Exception
                {
/* 347*/            for(j = 0; j < k; j++)
/* 348*/                i = memberValuePair(i);

/* 350*/            return i;
                }

                final int memberValuePair(int i)
                    throws Exception
                {
/* 354*/            int j = ByteArray.readU16bit(info, i);
/* 355*/            return memberValuePair(i + 2, j);
                }

                int memberValuePair(int i, int j)
                    throws Exception
                {
/* 359*/            return memberValue(i);
                }

                final int memberValue(int i)
                    throws Exception
                {
                    int j;
/* 363*/            if((j = info[i] & 0xff) == 101)
                    {
/* 365*/                int k = ByteArray.readU16bit(info, i + 1);
/* 366*/                j = ByteArray.readU16bit(info, i + 3);
/* 367*/                enumMemberValue(i, k, j);
/* 368*/                return i + 5;
                    }
/* 370*/            if(j == 99)
                    {
/* 371*/                int l = ByteArray.readU16bit(info, i + 1);
/* 372*/                classMemberValue(i, l);
/* 373*/                return i + 3;
                    }
/* 375*/            if(j == 64)
/* 376*/                return annotationMemberValue(i + 1);
/* 377*/            if(j == 91)
                    {
/* 378*/                int i1 = ByteArray.readU16bit(info, i + 1);
/* 379*/                return arrayMemberValue(i + 3, i1);
                    } else
                    {
/* 382*/                int j1 = ByteArray.readU16bit(info, i + 1);
/* 383*/                constValueMember(j, j1);
/* 384*/                return i + 3;
                    }
                }

                void constValueMember(int i, int j)
                    throws Exception
                {
                }

                void enumMemberValue(int i, int j, int k)
                    throws Exception
                {
                }

                void classMemberValue(int i, int j)
                    throws Exception
                {
                }

                int annotationMemberValue(int i)
                    throws Exception
                {
/* 397*/            return annotation(i);
                }

                int arrayMemberValue(int i, int j)
                    throws Exception
                {
/* 401*/            for(int k = 0; k < j; k++)
/* 402*/                i = memberValue(i);

/* 405*/            return i;
                }

                byte info[];

                Walker(byte abyte0[])
                {
/* 311*/            info = abyte0;
                }
    }


            public AnnotationsAttribute(ConstPool constpool, String s, byte abyte0[])
            {
/* 126*/        super(constpool, s, abyte0);
            }

            public AnnotationsAttribute(ConstPool constpool, String s)
            {
/* 141*/        this(constpool, s, new byte[] {
/* 141*/            0, 0
                });
            }

            AnnotationsAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/* 150*/        super(constpool, i, datainputstream);
            }

            public int numAnnotations()
            {
/* 157*/        return ByteArray.readU16bit(info, 0);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 164*/        map = new Copier(info, constPool, constpool, map);
/* 166*/        map.annotationArray();
/* 167*/        return new AnnotationsAttribute(constpool, getName(), map.close());
/* 169*/        constpool;
/* 170*/        throw new RuntimeException(constpool);
            }

            public Annotation getAnnotation(String s)
            {
/* 184*/        Annotation aannotation[] = getAnnotations();
/* 185*/        for(int i = 0; i < aannotation.length; i++)
/* 186*/            if(aannotation[i].getTypeName().equals(s))
/* 187*/                return aannotation[i];

/* 190*/        return null;
            }

            public void addAnnotation(Annotation annotation)
            {
/* 200*/        String s = annotation.getTypeName();
/* 201*/        Annotation aannotation[] = getAnnotations();
/* 202*/        for(int i = 0; i < aannotation.length; i++)
/* 203*/            if(aannotation[i].getTypeName().equals(s))
                    {
/* 204*/                aannotation[i] = annotation;
/* 205*/                setAnnotations(aannotation);
/* 206*/                return;
                    }

/* 210*/        Annotation aannotation1[] = new Annotation[aannotation.length + 1];
/* 211*/        System.arraycopy(aannotation, 0, aannotation1, 0, aannotation.length);
/* 212*/        aannotation1[aannotation.length] = annotation;
/* 213*/        setAnnotations(aannotation1);
            }

            public Annotation[] getAnnotations()
            {
/* 227*/        return (new Parser(info, constPool)).parseAnnotations();
                Exception exception;
/* 229*/        exception;
/* 230*/        throw new RuntimeException(exception);
            }

            public void setAnnotations(Annotation aannotation[])
            {
/* 242*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 243*/        AnnotationsWriter annotationswriter = new AnnotationsWriter(bytearrayoutputstream, constPool);
/* 245*/        try
                {
/* 245*/            int i = aannotation.length;
/* 246*/            annotationswriter.numAnnotations(i);
/* 247*/            for(int j = 0; j < i; j++)
/* 248*/                aannotation[j].write(annotationswriter);

/* 250*/            annotationswriter.close();
                }
/* 252*/        catch(IOException ioexception)
                {
/* 253*/            throw new RuntimeException(ioexception);
                }
/* 256*/        set(bytearrayoutputstream.toByteArray());
            }

            public void setAnnotation(Annotation annotation)
            {
/* 267*/        setAnnotations(new Annotation[] {
/* 267*/            annotation
                });
            }

            void renameClass(String s, String s1)
            {
                HashMap hashmap;
/* 275*/        (hashmap = new HashMap()).put(s, s1);
/* 277*/        renameClass(((Map) (hashmap)));
            }

            void renameClass(Map map)
            {
/* 281*/        map = new Renamer(info, getConstPool(), map);
/* 283*/        try
                {
/* 283*/            map.annotationArray();
/* 286*/            return;
                }
                // Misplaced declaration of an exception variable
/* 284*/        catch(Map map)
                {
/* 285*/            throw new RuntimeException(map);
                }
            }

            void getRefClasses(Map map)
            {
/* 289*/        renameClass(map);
            }

            public String toString()
            {
/* 295*/        Annotation aannotation[] = getAnnotations();
/* 296*/        StringBuilder stringbuilder = new StringBuilder();
/* 297*/        int i = 0;
/* 298*/        do
                {
/* 298*/            if(i >= aannotation.length)
/* 299*/                break;
/* 299*/            stringbuilder.append(aannotation[i++].toString());
/* 300*/            if(i != aannotation.length)
/* 301*/                stringbuilder.append(", ");
                } while(true);
/* 304*/        return stringbuilder.toString();
            }

            public static final String visibleTag = "RuntimeVisibleAnnotations";
            public static final String invisibleTag = "RuntimeInvisibleAnnotations";
}
