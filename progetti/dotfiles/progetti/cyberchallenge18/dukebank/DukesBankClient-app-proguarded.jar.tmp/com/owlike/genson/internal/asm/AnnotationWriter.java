// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            AnnotationVisitor, ByteVector, ClassWriter, Item, 
//            Type, TypePath

final class AnnotationWriter extends AnnotationVisitor
{

    AnnotationWriter(ClassWriter classwriter, boolean flag, ByteVector bytevector, ByteVector bytevector1, int i)
    {
        super(0x50000);
        a = classwriter;
        c = flag;
        d = bytevector;
        e = bytevector1;
        f = i;
    }

    public final void visit(String s, Object obj)
    {
        b++;
        if(c)
            d.putShort(a.newUTF8(s));
        if(obj instanceof String)
        {
            d.b(115, a.newUTF8((String)obj));
            return;
        }
        if(obj instanceof Byte)
        {
            d.b(66, a.a(((Byte)obj).byteValue()).a);
            return;
        }
        if(obj instanceof Boolean)
        {
            s = ((Boolean)obj).booleanValue() ? 1 : 0;
            d.b(90, a.a(s).a);
            return;
        }
        if(obj instanceof Character)
        {
            d.b(67, a.a(((Character)obj).charValue()).a);
            return;
        }
        if(obj instanceof Short)
        {
            d.b(83, a.a(((Short)obj).shortValue()).a);
            return;
        }
        if(obj instanceof Type)
        {
            d.b(99, a.newUTF8(((Type)obj).getDescriptor()));
            return;
        }
        if(obj instanceof byte[])
        {
            s = (byte[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(66, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof boolean[])
        {
            s = (boolean[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(90, a.a(s[obj] == 0 ? 0 : 1).a);

            return;
        }
        if(obj instanceof short[])
        {
            s = (short[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(83, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof char[])
        {
            s = (char[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(67, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof int[])
        {
            s = (int[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(73, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof long[])
        {
            s = (long[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(74, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof float[])
        {
            s = (float[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(70, a.a(s[obj]).a);

            return;
        }
        if(obj instanceof double[])
        {
            s = (double[])obj;
            d.b(91, s.length);
            for(obj = 0; obj < s.length; obj++)
                d.b(68, a.a(s[obj]).a);

            return;
        } else
        {
            s = a.a(obj);
            d.b(".s.IFJDCS".charAt(((Item) (s)).b), ((Item) (s)).a);
            return;
        }
    }

    public final void visitEnum(String s, String s1, String s2)
    {
        b++;
        if(c)
            d.putShort(a.newUTF8(s));
        d.b(101, a.newUTF8(s1)).putShort(a.newUTF8(s2));
    }

    public final AnnotationVisitor visitAnnotation(String s, String s1)
    {
        b++;
        if(c)
            d.putShort(a.newUTF8(s));
        d.b(64, a.newUTF8(s1)).putShort(0);
        return new AnnotationWriter(a, true, d, d, d.b - 2);
    }

    public final AnnotationVisitor visitArray(String s)
    {
        b++;
        if(c)
            d.putShort(a.newUTF8(s));
        d.b(91, 0);
        return new AnnotationWriter(a, false, d, d, d.b - 2);
    }

    public final void visitEnd()
    {
        if(e != null)
        {
            byte abyte0[];
            (abyte0 = e.a)[f] = (byte)(b >>> 8);
            abyte0[f + 1] = (byte)b;
        }
    }

    final int a()
    {
        int i = 0;
        for(AnnotationWriter annotationwriter = this; annotationwriter != null; annotationwriter = annotationwriter.g)
            i += annotationwriter.d.b;

        return i;
    }

    final void a(ByteVector bytevector)
    {
        int i = 0;
        int j = 2;
        AnnotationWriter annotationwriter = this;
        AnnotationWriter annotationwriter2 = null;
        for(; annotationwriter != null; annotationwriter = annotationwriter.g)
        {
            i++;
            j += annotationwriter.d.b;
            annotationwriter.visitEnd();
            annotationwriter.h = annotationwriter2;
            annotationwriter2 = annotationwriter;
        }

        bytevector.putInt(j);
        bytevector.putShort(i);
        for(AnnotationWriter annotationwriter1 = annotationwriter2; annotationwriter1 != null; annotationwriter1 = annotationwriter1.h)
            bytevector.putByteArray(annotationwriter1.d.a, 0, annotationwriter1.d.b);

    }

    static void a(AnnotationWriter aannotationwriter[], int i, ByteVector bytevector)
    {
        int j = 1 + 2 * (aannotationwriter.length - i);
        for(int k = i; k < aannotationwriter.length; k++)
            j += aannotationwriter[k] != null ? aannotationwriter[k].a() : 0;

        bytevector.putInt(j).putByte(aannotationwriter.length - i);
        for(int l = i; l < aannotationwriter.length; l++)
        {
            i = aannotationwriter[l];
            Object obj = null;
            int i1 = 0;
            for(; i != null; i = ((AnnotationWriter) (i)).g)
            {
                i1++;
                i.visitEnd();
                i.h = ((AnnotationWriter) (obj));
                obj = i;
            }

            bytevector.putShort(i1);
            for(i = ((int) (obj)); i != null; i = ((AnnotationWriter) (i)).h)
                bytevector.putByteArray(((AnnotationWriter) (i)).d.a, 0, ((AnnotationWriter) (i)).d.b);

        }

    }

    static void a(int i, TypePath typepath, ByteVector bytevector)
    {
        switch(i >>> 24)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 22: // '\026'
            bytevector.putShort(i >>> 16);
            break;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
            bytevector.putByte(i >>> 24);
            break;

        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
            bytevector.putInt(i);
            break;

        default:
            bytevector.b(i >>> 24, (i & 0xffff00) >> 8);
            break;
        }
        if(typepath == null)
        {
            bytevector.putByte(0);
            return;
        } else
        {
            i = (typepath.a[typepath.b] << 1) + 1;
            bytevector.putByteArray(typepath.a, typepath.b, i);
            return;
        }
    }

    private final ClassWriter a;
    private int b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;
}
