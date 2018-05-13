// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            FieldVisitor, AnnotationWriter, Attribute, ByteVector, 
//            ClassWriter, Item, AnnotationVisitor, TypePath

final class FieldWriter extends FieldVisitor
{

    FieldWriter(ClassWriter classwriter, int i1, String s, String s1, String s2, Object obj)
    {
        super(0x50000);
        if(classwriter.B == null)
            classwriter.B = this;
        else
            classwriter.C.fv = this;
        classwriter.C = this;
        b = classwriter;
        c = i1;
        d = classwriter.newUTF8(s);
        e = classwriter.newUTF8(s1);
        if(s2 != null)
            f = classwriter.newUTF8(s2);
        if(obj != null)
            g = classwriter.a(obj).a;
    }

    public final AnnotationVisitor visitAnnotation(String s, boolean flag)
    {
        ByteVector bytevector;
        (bytevector = new ByteVector()).putShort(b.newUTF8(s)).putShort(0);
        s = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if(flag)
        {
            s.g = h;
            h = s;
        } else
        {
            s.g = i;
            i = s;
        }
        return s;
    }

    public final AnnotationVisitor visitTypeAnnotation(int i1, TypePath typepath, String s, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        AnnotationWriter.a(i1, typepath, bytevector);
        bytevector.putShort(b.newUTF8(s)).putShort(0);
        i1 = new AnnotationWriter(b, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = k;
            k = i1;
        } else
        {
            i1.g = l;
            l = i1;
        }
        return i1;
    }

    public final void visitAttribute(Attribute attribute)
    {
        attribute.a = j;
        j = attribute;
    }

    public final void visitEnd()
    {
    }

    final int a()
    {
        int i1 = 8;
        if(g != 0)
        {
            b.newUTF8("ConstantValue");
            i1 += 8;
        }
        if((c & 0x1000) != 0 && ((b.b & 0xffff) < 49 || (c & 0x40000) != 0))
        {
            b.newUTF8("Synthetic");
            i1 += 6;
        }
        if((c & 0x20000) != 0)
        {
            b.newUTF8("Deprecated");
            i1 += 6;
        }
        if(f != 0)
        {
            b.newUTF8("Signature");
            i1 += 8;
        }
        if(h != null)
        {
            b.newUTF8("RuntimeVisibleAnnotations");
            i1 += 8 + h.a();
        }
        if(i != null)
        {
            b.newUTF8("RuntimeInvisibleAnnotations");
            i1 += 8 + i.a();
        }
        if(k != null)
        {
            b.newUTF8("RuntimeVisibleTypeAnnotations");
            i1 += 8 + k.a();
        }
        if(l != null)
        {
            b.newUTF8("RuntimeInvisibleTypeAnnotations");
            i1 += 8 + l.a();
        }
        if(j != null)
            i1 += j.a(b, null, 0, -1, -1);
        return i1;
    }

    final void a(ByteVector bytevector)
    {
        int i1 = 0x60000 | (c & 0x40000) / 64;
        bytevector.putShort(c & ~i1).putShort(d).putShort(e);
        i1 = 0;
        if(g != 0)
            i1++;
        if((c & 0x1000) != 0 && ((b.b & 0xffff) < 49 || (c & 0x40000) != 0))
            i1++;
        if((c & 0x20000) != 0)
            i1++;
        if(f != 0)
            i1++;
        if(h != null)
            i1++;
        if(i != null)
            i1++;
        if(k != null)
            i1++;
        if(l != null)
            i1++;
        if(j != null)
            i1 += j.a();
        bytevector.putShort(i1);
        if(g != 0)
        {
            bytevector.putShort(b.newUTF8("ConstantValue"));
            bytevector.putInt(2).putShort(g);
        }
        if((c & 0x1000) != 0 && ((b.b & 0xffff) < 49 || (c & 0x40000) != 0))
            bytevector.putShort(b.newUTF8("Synthetic")).putInt(0);
        if((c & 0x20000) != 0)
            bytevector.putShort(b.newUTF8("Deprecated")).putInt(0);
        if(f != 0)
        {
            bytevector.putShort(b.newUTF8("Signature"));
            bytevector.putInt(2).putShort(f);
        }
        if(h != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleAnnotations"));
            h.a(bytevector);
        }
        if(i != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleAnnotations"));
            i.a(bytevector);
        }
        if(k != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleTypeAnnotations"));
            k.a(bytevector);
        }
        if(l != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            l.a(bytevector);
        }
        if(j != null)
            j.a(b, null, 0, -1, -1, bytevector);
    }

    private final ClassWriter b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private AnnotationWriter h;
    private AnnotationWriter i;
    private AnnotationWriter k;
    private AnnotationWriter l;
    private Attribute j;
}
