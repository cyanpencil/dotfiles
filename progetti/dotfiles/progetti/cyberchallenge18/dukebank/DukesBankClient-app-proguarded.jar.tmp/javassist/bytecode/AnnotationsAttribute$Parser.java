// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsAttribute.java

package javassist.bytecode;

import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
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
//            AnnotationsAttribute, ConstPool

static class pool extends pool
{

            Annotation[][] parseParameters()
                throws Exception
            {
/* 583*/        parameters();
/* 584*/        return allParams;
            }

            Annotation[] parseAnnotations()
                throws Exception
            {
/* 588*/        annotationArray();
/* 589*/        return allAnno;
            }

            MemberValue parseMemberValue()
                throws Exception
            {
/* 593*/        memberValue(0);
/* 594*/        return currentMember;
            }

            void parameters(int i, int j)
                throws Exception
            {
/* 598*/        Annotation aannotation[][] = new Annotation[i][];
/* 599*/        for(int k = 0; k < i; k++)
                {
/* 600*/            j = annotationArray(j);
/* 601*/            aannotation[k] = allAnno;
                }

/* 604*/        allParams = aannotation;
            }

            int annotationArray(int i, int j)
                throws Exception
            {
/* 608*/        Annotation aannotation[] = new Annotation[j];
/* 609*/        for(int k = 0; k < j; k++)
                {
/* 610*/            i = annotation(i);
/* 611*/            aannotation[k] = currentAnno;
                }

/* 614*/        allAnno = aannotation;
/* 615*/        return i;
            }

            int annotation(int i, int j, int k)
                throws Exception
            {
/* 619*/        currentAnno = new Annotation(j, pool);
/* 620*/        return super.annotation(i, j, k);
            }

            int memberValuePair(int i, int j)
                throws Exception
            {
/* 624*/        i = super.memberValuePair(i, j);
/* 625*/        currentAnno.addMemberValue(j, currentMember);
/* 626*/        return i;
            }

            void constValueMember(int i, int j)
                throws Exception
            {
/* 631*/        Object obj = pool;
/* 632*/        switch(i)
                {
/* 634*/        case 66: // 'B'
/* 634*/            obj = new ByteMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 637*/        case 67: // 'C'
/* 637*/            obj = new CharMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 640*/        case 68: // 'D'
/* 640*/            obj = new DoubleMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 643*/        case 70: // 'F'
/* 643*/            obj = new FloatMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 646*/        case 73: // 'I'
/* 646*/            obj = new IntegerMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 649*/        case 74: // 'J'
/* 649*/            obj = new LongMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 652*/        case 83: // 'S'
/* 652*/            obj = new ShortMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 655*/        case 90: // 'Z'
/* 655*/            obj = new BooleanMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 658*/        case 115: // 's'
/* 658*/            obj = new StringMemberValue(j, ((ConstPool) (obj)));
                    break;

/* 661*/        default:
/* 661*/            throw new RuntimeException((new StringBuilder("unknown tag:")).append(i).toString());
                }
/* 664*/        currentMember = ((MemberValue) (obj));
/* 665*/        super.constValueMember(i, j);
            }

            void enumMemberValue(int i, int j, int k)
                throws Exception
            {
/* 671*/        currentMember = new EnumMemberValue(j, k, pool);
/* 673*/        super.enumMemberValue(i, j, k);
            }

            void classMemberValue(int i, int j)
                throws Exception
            {
/* 677*/        currentMember = new ClassMemberValue(j, pool);
/* 678*/        super.classMemberValue(i, j);
            }

            int annotationMemberValue(int i)
                throws Exception
            {
/* 682*/        Annotation annotation1 = currentAnno;
/* 683*/        i = super.annotationMemberValue(i);
/* 684*/        currentMember = new AnnotationMemberValue(currentAnno, pool);
/* 685*/        currentAnno = annotation1;
/* 686*/        return i;
            }

            int arrayMemberValue(int i, int j)
                throws Exception
            {
/* 690*/        ArrayMemberValue arraymembervalue = new ArrayMemberValue(pool);
/* 691*/        MemberValue amembervalue[] = new MemberValue[j];
/* 692*/        for(int k = 0; k < j; k++)
                {
/* 693*/            i = memberValue(i);
/* 694*/            amembervalue[k] = currentMember;
                }

/* 697*/        arraymembervalue.setValue(amembervalue);
/* 698*/        currentMember = arraymembervalue;
/* 699*/        return i;
            }

            ConstPool pool;
            Annotation allParams[][];
            Annotation allAnno[];
            Annotation currentAnno;
            MemberValue currentMember;

            (byte abyte0[], ConstPool constpool)
            {
/* 578*/        super(abyte0);
/* 579*/        pool = constpool;
            }
}
