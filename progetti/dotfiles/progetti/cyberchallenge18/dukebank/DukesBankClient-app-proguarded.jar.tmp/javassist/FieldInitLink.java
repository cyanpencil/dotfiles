// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtClassType.java

package javassist;


// Referenced classes of package javassist:
//            CtField

class FieldInitLink
{

            FieldInitLink(CtField ctfield, CtField.Initializer initializer)
            {
/*1718*/        next = null;
/*1719*/        field = ctfield;
/*1720*/        init = initializer;
            }

            FieldInitLink next;
            CtField field;
            CtField.Initializer init;
}
