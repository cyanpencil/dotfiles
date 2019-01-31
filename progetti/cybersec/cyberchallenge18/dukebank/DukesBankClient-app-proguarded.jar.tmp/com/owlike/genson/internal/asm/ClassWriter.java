// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            ClassVisitor, AnnotationWriter, Attribute, ByteVector, 
//            ClassReader, FieldWriter, Handle, Item, 
//            MethodWriter, Type, AnnotationVisitor, TypePath, 
//            FieldVisitor, MethodVisitor

public class ClassWriter extends ClassVisitor
{

    public ClassWriter(int i1)
    {
        super(0x50000);
        c = 1;
        d = new ByteVector();
        e = new Item[256];
        f = (int)(0.75D * (double)e.length);
        g = new Item();
        h = new Item();
        i = new Item();
        j = new Item();
        K = (i1 & 1) != 0;
        J = (i1 & 2) != 0;
    }

    public ClassWriter(ClassReader classreader, int i1)
    {
        this(i1);
        classreader.a(this);
        M = classreader;
    }

    public final void visit(int i1, int j1, String s1, String s2, String s3, String as[])
    {
        b = i1;
        k = j1;
        l = newClass(s1);
        I = s1;
        if(s2 != null)
            m = newUTF8(s2);
        n = s3 != null ? newClass(s3) : 0;
        if(as != null && as.length > 0)
        {
            o = as.length;
            p = new int[o];
            for(i1 = 0; i1 < o; i1++)
                p[i1] = newClass(as[i1]);

        }
    }

    public final void visitSource(String s1, String s2)
    {
        if(s1 != null)
            q = newUTF8(s1);
        if(s2 != null)
            r = (new ByteVector()).encodeUTF8(s2, 0, 0x7fffffff);
    }

    public final void visitOuterClass(String s1, String s2, String s3)
    {
        s = newClass(s1);
        if(s2 != null && s3 != null)
            t = newNameType(s2, s3);
    }

    public final AnnotationVisitor visitAnnotation(String s1, boolean flag)
    {
        ByteVector bytevector;
        (bytevector = new ByteVector()).putShort(newUTF8(s1)).putShort(0);
        s1 = new AnnotationWriter(this, true, bytevector, bytevector, 2);
        if(flag)
        {
            s1.g = u;
            u = s1;
        } else
        {
            s1.g = v;
            v = s1;
        }
        return s1;
    }

    public final AnnotationVisitor visitTypeAnnotation(int i1, TypePath typepath, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        AnnotationWriter.a(i1, typepath, bytevector);
        bytevector.putShort(newUTF8(s1)).putShort(0);
        i1 = new AnnotationWriter(this, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = N;
            N = i1;
        } else
        {
            i1.g = O;
            O = i1;
        }
        return i1;
    }

    public final void visitAttribute(Attribute attribute)
    {
        attribute.a = w;
        w = attribute;
    }

    public final void visitInnerClass(String s1, String s2, String s3, int i1)
    {
        if(y == null)
            y = new ByteVector();
        if(((Item) (s1 = a(s1))).c == 0)
        {
            x++;
            y.putShort(((Item) (s1)).a);
            y.putShort(s2 != null ? newClass(s2) : 0);
            y.putShort(s3 != null ? newUTF8(s3) : 0);
            y.putShort(i1);
            s1.c = x;
        }
    }

    public final FieldVisitor visitField(int i1, String s1, String s2, String s3, Object obj)
    {
        return new FieldWriter(this, i1, s1, s2, s3, obj);
    }

    public final MethodVisitor visitMethod(int i1, String s1, String s2, String s3, String as[])
    {
        return new MethodWriter(this, i1, s1, s2, s3, as, K, J);
    }

    public final void visitEnd()
    {
    }

    public byte[] toByteArray()
    {
        if(c > 65535)
            throw new RuntimeException("Class file too large!");
        int i1 = 24 + 2 * o;
        int j1 = 0;
        for(FieldWriter fieldwriter = B; fieldwriter != null; fieldwriter = (FieldWriter)fieldwriter.fv)
        {
            j1++;
            i1 += fieldwriter.a();
        }

        int j2 = 0;
        for(MethodWriter methodwriter = D; methodwriter != null; methodwriter = (MethodWriter)methodwriter.mv)
        {
            j2++;
            i1 += methodwriter.a();
        }

        int k2 = 0;
        if(A != null)
        {
            k2++;
            i1 += 8 + A.b;
            newUTF8("BootstrapMethods");
        }
        if(m != 0)
        {
            k2++;
            i1 += 8;
            newUTF8("Signature");
        }
        if(q != 0)
        {
            k2++;
            i1 += 8;
            newUTF8("SourceFile");
        }
        if(r != null)
        {
            k2++;
            i1 += r.b + 6;
            newUTF8("SourceDebugExtension");
        }
        if(s != 0)
        {
            k2++;
            i1 += 10;
            newUTF8("EnclosingMethod");
        }
        if((k & 0x20000) != 0)
        {
            k2++;
            i1 += 6;
            newUTF8("Deprecated");
        }
        if((k & 0x1000) != 0 && ((b & 0xffff) < 49 || (k & 0x40000) != 0))
        {
            k2++;
            i1 += 6;
            newUTF8("Synthetic");
        }
        if(y != null)
        {
            k2++;
            i1 += 8 + y.b;
            newUTF8("InnerClasses");
        }
        if(u != null)
        {
            k2++;
            i1 += 8 + u.a();
            newUTF8("RuntimeVisibleAnnotations");
        }
        if(v != null)
        {
            k2++;
            i1 += 8 + v.a();
            newUTF8("RuntimeInvisibleAnnotations");
        }
        if(N != null)
        {
            k2++;
            i1 += 8 + N.a();
            newUTF8("RuntimeVisibleTypeAnnotations");
        }
        if(O != null)
        {
            k2++;
            i1 += 8 + O.a();
            newUTF8("RuntimeInvisibleTypeAnnotations");
        }
        if(w != null)
        {
            k2 += w.a();
            i1 += w.a(this, null, 0, -1, -1);
        }
        i1 += d.b;
        ByteVector bytevector;
        (bytevector = new ByteVector(i1)).putInt(0xcafebabe).putInt(b);
        bytevector.putShort(c).putByteArray(d.a, 0, d.b);
        int k1 = 0x60000 | (k & 0x40000) / 64;
        bytevector.putShort(k & ~k1).putShort(l).putShort(n);
        bytevector.putShort(o);
        for(int l1 = 0; l1 < o; l1++)
            bytevector.putShort(p[l1]);

        bytevector.putShort(j1);
        for(FieldWriter fieldwriter1 = B; fieldwriter1 != null; fieldwriter1 = (FieldWriter)fieldwriter1.fv)
            fieldwriter1.a(bytevector);

        bytevector.putShort(j2);
        for(MethodWriter methodwriter1 = D; methodwriter1 != null; methodwriter1 = (MethodWriter)methodwriter1.mv)
            methodwriter1.a(bytevector);

        bytevector.putShort(k2);
        if(A != null)
        {
            bytevector.putShort(newUTF8("BootstrapMethods"));
            bytevector.putInt(A.b + 2).putShort(z);
            bytevector.putByteArray(A.a, 0, A.b);
        }
        if(m != 0)
            bytevector.putShort(newUTF8("Signature")).putInt(2).putShort(m);
        if(q != 0)
            bytevector.putShort(newUTF8("SourceFile")).putInt(2).putShort(q);
        if(r != null)
        {
            int i2 = r.b;
            bytevector.putShort(newUTF8("SourceDebugExtension")).putInt(i2);
            bytevector.putByteArray(r.a, 0, i2);
        }
        if(s != 0)
        {
            bytevector.putShort(newUTF8("EnclosingMethod")).putInt(4);
            bytevector.putShort(s).putShort(t);
        }
        if((k & 0x20000) != 0)
            bytevector.putShort(newUTF8("Deprecated")).putInt(0);
        if((k & 0x1000) != 0 && ((b & 0xffff) < 49 || (k & 0x40000) != 0))
            bytevector.putShort(newUTF8("Synthetic")).putInt(0);
        if(y != null)
        {
            bytevector.putShort(newUTF8("InnerClasses"));
            bytevector.putInt(y.b + 2).putShort(x);
            bytevector.putByteArray(y.a, 0, y.b);
        }
        if(u != null)
        {
            bytevector.putShort(newUTF8("RuntimeVisibleAnnotations"));
            u.a(bytevector);
        }
        if(v != null)
        {
            bytevector.putShort(newUTF8("RuntimeInvisibleAnnotations"));
            v.a(bytevector);
        }
        if(N != null)
        {
            bytevector.putShort(newUTF8("RuntimeVisibleTypeAnnotations"));
            N.a(bytevector);
        }
        if(O != null)
        {
            bytevector.putShort(newUTF8("RuntimeInvisibleTypeAnnotations"));
            O.a(bytevector);
        }
        if(w != null)
            w.a(this, null, 0, -1, -1, bytevector);
        if(L)
        {
            u = null;
            v = null;
            w = null;
            x = 0;
            y = null;
            z = 0;
            A = null;
            B = null;
            C = null;
            D = null;
            E = null;
            K = false;
            J = true;
            L = false;
            (new ClassReader(bytevector.a)).accept(this, 4);
            return toByteArray();
        } else
        {
            return bytevector.a;
        }
    }

    Item a(Object obj)
    {
        if(obj instanceof Integer)
        {
            obj = ((Integer)obj).intValue();
            return a(((int) (obj)));
        }
        if(obj instanceof Byte)
        {
            obj = ((Byte)obj).intValue();
            return a(((int) (obj)));
        }
        if(obj instanceof Character)
        {
            obj = ((Character)obj).charValue();
            return a(((int) (obj)));
        }
        if(obj instanceof Short)
        {
            obj = ((Short)obj).intValue();
            return a(((int) (obj)));
        }
        if(obj instanceof Boolean)
        {
            obj = ((Boolean)obj).booleanValue() ? 1 : 0;
            return a(((int) (obj)));
        }
        if(obj instanceof Float)
        {
            obj = ((Float)obj).floatValue();
            return a(((float) (obj)));
        }
        if(obj instanceof Long)
        {
            long l1 = ((Long)obj).longValue();
            return a(l1);
        }
        if(obj instanceof Double)
        {
            double d1 = ((Double)obj).doubleValue();
            return a(d1);
        }
        if(obj instanceof String)
            return b((String)obj);
        if(obj instanceof Type)
        {
            int i1;
            if((i1 = ((Type) (obj = (Type)obj)).getSort()) == 10)
                return a(((Type) (obj)).getInternalName());
            if(i1 == 11)
                return c(((Type) (obj)).getDescriptor());
            else
                return a(((Type) (obj)).getDescriptor());
        }
        if(obj instanceof Handle)
        {
            obj = (Handle)obj;
            return a(((Handle) (obj)).a, ((Handle) (obj)).b, ((Handle) (obj)).c, ((Handle) (obj)).d);
        } else
        {
            throw new IllegalArgumentException("value " + obj);
        }
    }

    public int newConst(Object obj)
    {
        return a(obj).a;
    }

    public int newUTF8(String s1)
    {
        g.a(1, s1, null, null);
        Item item;
        if((item = a(g)) == null)
        {
            d.putByte(1).putUTF8(s1);
            item = new Item(c++, g);
            b(item);
        }
        return item.a;
    }

    Item a(String s1)
    {
        h.a(7, s1, null, null);
        Item item;
        if((item = a(h)) == null)
        {
            d.b(7, newUTF8(s1));
            item = new Item(c++, h);
            b(item);
        }
        return item;
    }

    public int newClass(String s1)
    {
        return a(s1).a;
    }

    Item c(String s1)
    {
        h.a(16, s1, null, null);
        Item item;
        if((item = a(h)) == null)
        {
            d.b(16, newUTF8(s1));
            item = new Item(c++, h);
            b(item);
        }
        return item;
    }

    public int newMethodType(String s1)
    {
        return c(s1).a;
    }

    Item a(int i1, String s1, String s2, String s3)
    {
        j.a(i1 + 20, s1, s2, s3);
        Item item;
        if((item = a(j)) == null)
        {
            if(i1 <= 4)
                b(15, i1, newField(s1, s2, s3));
            else
                b(15, i1, newMethod(s1, s2, s3, i1 == 9));
            item = new Item(c++, j);
            b(item);
        }
        return item;
    }

    public int newHandle(int i1, String s1, String s2, String s3)
    {
        return a(i1, s1, s2, s3).a;
    }

    transient Item a(String s1, String s2, Handle handle, Object aobj[])
    {
        ByteVector bytevector;
        if((bytevector = A) == null)
            bytevector = A = new ByteVector();
        int i1 = bytevector.b;
        int j1 = handle.hashCode();
        bytevector.putShort(newHandle(handle.a, handle.b, handle.c, handle.d));
        handle = aobj.length;
        bytevector.putShort(handle);
        for(int k1 = 0; k1 < handle; k1++)
        {
            Object obj = aobj[k1];
            j1 ^= obj.hashCode();
            bytevector.putShort(newConst(obj));
        }

        byte abyte0[] = bytevector.a;
        int l1 = handle + 2 << 1;
        j1 &= 0x7fffffff;
        handle = e[j1 % e.length];
label0:
        do
        {
            if(handle != null)
            {
                if(((Item) (handle)).b != 33 || ((Item) (handle)).j != j1)
                {
                    handle = ((Item) (handle)).k;
                    continue;
                }
                aobj = ((Item) (handle)).c;
                int i2 = 0;
                do
                {
                    if(i2 >= l1)
                        break;
                    if(abyte0[i1 + i2] != abyte0[aobj + i2])
                    {
                        handle = ((Item) (handle)).k;
                        continue label0;
                    }
                    i2++;
                } while(true);
            }
            if(handle != null)
            {
                aobj = ((Item) (handle)).a;
                bytevector.b = i1;
            } else
            {
                aobj = z++;
                (handle = new Item(((int) (aobj)))).a(i1, j1);
                b(handle);
            }
            i.a(s1, s2, ((int) (aobj)));
            if((handle = a(i)) == null)
            {
                a(18, ((int) (aobj)), newNameType(s1, s2));
                handle = new Item(c++, i);
                b(handle);
            }
            return handle;
        } while(true);
    }

    public transient int newInvokeDynamic(String s1, String s2, Handle handle, Object aobj[])
    {
        return a(s1, s2, handle, aobj).a;
    }

    Item a(String s1, String s2, String s3)
    {
        i.a(9, s1, s2, s3);
        Item item;
        if((item = a(i)) == null)
        {
            a(9, newClass(s1), newNameType(s2, s3));
            item = new Item(c++, i);
            b(item);
        }
        return item;
    }

    public int newField(String s1, String s2, String s3)
    {
        return a(s1, s2, s3).a;
    }

    Item a(String s1, String s2, String s3, boolean flag)
    {
        flag = flag ? 11 : 10;
        i.a(flag, s1, s2, s3);
        Item item;
        if((item = a(i)) == null)
        {
            a(flag, newClass(s1), newNameType(s2, s3));
            item = new Item(c++, i);
            b(item);
        }
        return item;
    }

    public int newMethod(String s1, String s2, String s3, boolean flag)
    {
        return a(s1, s2, s3, flag).a;
    }

    Item a(int i1)
    {
        g.a(i1);
        Item item;
        if((item = a(g)) == null)
        {
            d.putByte(3).putInt(i1);
            item = new Item(c++, g);
            b(item);
        }
        return item;
    }

    Item a(float f1)
    {
        g.a(f1);
        if((f1 = a(g)) == null)
        {
            d.putByte(4).putInt(g.c);
            f1 = new Item(c++, g);
            b(f1);
        }
        return f1;
    }

    Item a(long l1)
    {
        g.a(l1);
        Item item;
        if((item = a(g)) == null)
        {
            d.putByte(5).putLong(l1);
            item = new Item(c, g);
            c += 2;
            b(item);
        }
        return item;
    }

    Item a(double d1)
    {
        g.a(d1);
        if((d1 = a(g)) == null)
        {
            d.putByte(6).putLong(g.d);
            d1 = new Item(c, g);
            c += 2;
            b(d1);
        }
        return d1;
    }

    private Item b(String s1)
    {
        h.a(8, s1, null, null);
        Item item;
        if((item = a(h)) == null)
        {
            d.b(8, newUTF8(s1));
            item = new Item(c++, h);
            b(item);
        }
        return item;
    }

    public int newNameType(String s1, String s2)
    {
        return a(s1, s2).a;
    }

    Item a(String s1, String s2)
    {
        h.a(12, s1, s2, null);
        Item item;
        if((item = a(h)) == null)
        {
            a(12, newUTF8(s1), newUTF8(s2));
            item = new Item(c++, h);
            b(item);
        }
        return item;
    }

    int c(String s1)
    {
        g.a(30, s1, null, null);
        if((s1 = a(g)) == null)
            s1 = c(g);
        return ((Item) (s1)).a;
    }

    int a(String s1, int i1)
    {
        g.b = 31;
        g.c = i1;
        g.g = s1;
        g.j = 0x7fffffff & 31 + s1.hashCode() + i1;
        if((s1 = a(g)) == null)
            s1 = c(g);
        return ((Item) (s1)).a;
    }

    private Item c(Item item)
    {
        G++;
        item = new Item(G, g);
        b(item);
        if(H == null)
            H = new Item[16];
        if(G == H.length)
        {
            Item aitem[] = new Item[2 * H.length];
            System.arraycopy(H, 0, aitem, 0, H.length);
            H = aitem;
        }
        H[G] = item;
        return item;
    }

    int a(int i1, int j1)
    {
        h.b = 32;
        h.d = (long)i1 | (long)j1 << 32;
        h.j = 0x7fffffff & i1 + 32 + j1;
        Item item;
        if((item = a(h)) == null)
        {
            i1 = H[i1].g;
            j1 = H[j1].g;
            h.c = c(getCommonSuperClass(i1, j1));
            item = new Item(0, h);
            b(item);
        }
        return item.c;
    }

    protected String getCommonSuperClass(String s1, String s2)
    {
        Object obj = getClass().getClassLoader();
        Class class1;
        try
        {
            class1 = Class.forName(s1.replace('/', '.'), false, ((ClassLoader) (obj)));
            obj = Class.forName(s2.replace('/', '.'), false, ((ClassLoader) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(String s1)
        {
            throw new RuntimeException(s1.toString());
        }
        if(class1.isAssignableFrom(((Class) (obj))))
            return s1;
        if(((Class) (obj)).isAssignableFrom(class1))
            return s2;
        if(class1.isInterface() || ((Class) (obj)).isInterface())
            return "java/lang/Object";
        while(!(class1 = class1.getSuperclass()).isAssignableFrom(((Class) (obj)))) ;
        return class1.getName().replace('.', '/');
    }

    private Item a(Item item)
    {
        Item item1;
        for(item1 = e[item.j % e.length]; item1 != null && (item1.b != item.b || !item.a(item1)); item1 = item1.k);
        return item1;
    }

    private void b(Item item)
    {
        if(c + G > f)
        {
            int i1;
            int k1;
            Item aitem[] = new Item[k1 = ((i1 = e.length) << 1) + 1];
            for(i1--; i1 >= 0; i1--)
            {
                Item item2;
                for(Item item1 = e[i1]; item1 != null; item1 = item2)
                {
                    int l1 = item1.j % k1;
                    item2 = item1.k;
                    item1.k = aitem[l1];
                    aitem[l1] = item1;
                }

            }

            e = aitem;
            f = (int)((double)k1 * 0.75D);
        }
        int j1 = item.j % e.length;
        item.k = e[j1];
        e[j1] = item;
    }

    private void a(int i1, int j1, int k1)
    {
        d.b(i1, j1).putShort(k1);
    }

    private void b(int i1, int j1, int k1)
    {
        d.a(i1, j1).putShort(k1);
    }

    static void _clinit_()
    {
    }

    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    static final byte a[];
    ClassReader M;
    int b;
    int c;
    final ByteVector d;
    Item e[];
    int f;
    final Item g;
    final Item h;
    final Item i;
    final Item j;
    Item H[];
    private short G;
    private int k;
    private int l;
    String I;
    private int m;
    private int n;
    private int o;
    private int p[];
    private int q;
    private ByteVector r;
    private int s;
    private int t;
    private AnnotationWriter u;
    private AnnotationWriter v;
    private AnnotationWriter N;
    private AnnotationWriter O;
    private Attribute w;
    private int x;
    private ByteVector y;
    int z;
    ByteVector A;
    FieldWriter B;
    FieldWriter C;
    MethodWriter D;
    MethodWriter E;
    private boolean K;
    private boolean J;
    boolean L;

    static 
    {
        _clinit_();
        byte abyte0[] = new byte[220];
        String s1 = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ";
        for(int i1 = 0; i1 < 220; i1++)
            abyte0[i1] = (byte)(s1.charAt(i1) - 65);

        a = abyte0;
    }
}
