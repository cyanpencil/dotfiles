// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            MethodVisitor, AnnotationWriter, Attribute, ByteVector, 
//            ClassReader, ClassWriter, Edge, Frame, 
//            Handler, Item, Label, Type, 
//            TypePath, AnnotationVisitor, Handle

class MethodWriter extends MethodVisitor
{

    MethodWriter(ClassWriter classwriter, int i1, String s1, String s2, String s3, String as[], boolean flag, 
            boolean flag1)
    {
        super(0x50000);
        r = new ByteVector();
        if(classwriter.D == null)
            classwriter.D = this;
        else
            classwriter.E.mv = this;
        classwriter.E = this;
        b = classwriter;
        c = i1;
        if("<init>".equals(s1))
            c |= 0x80000;
        d = classwriter.newUTF8(s1);
        e = classwriter.newUTF8(s2);
        f = s2;
        g = s3;
        if(as != null && as.length > 0)
        {
            j = as.length;
            k = new int[j];
            for(s1 = 0; s1 < j; s1++)
                k[s1] = classwriter.newClass(as[s1]);

        }
        M = flag1 ? 0 : flag ? 1 : 2;
        if(flag || flag1)
        {
            s1 = Type.getArgumentsAndReturnSizes(f) >> 2;
            if((i1 & 8) != 0)
                s1--;
            t = s1;
            T = s1;
            N = new Label();
            N.a |= 8;
            visitLabel(N);
        }
    }

    public void visitParameter(String s1, int i1)
    {
        if($ == null)
            $ = new ByteVector();
        Z++;
        $.putShort(s1 != null ? b.newUTF8(s1) : 0).putShort(i1);
    }

    public AnnotationVisitor visitAnnotationDefault()
    {
        l = new ByteVector();
        return new AnnotationWriter(b, false, l, null, 0);
    }

    public AnnotationVisitor visitAnnotation(String s1, boolean flag)
    {
        ByteVector bytevector;
        (bytevector = new ByteVector()).putShort(b.newUTF8(s1)).putShort(0);
        s1 = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if(flag)
        {
            s1.g = m;
            m = s1;
        } else
        {
            s1.g = n;
            n = s1;
        }
        return s1;
    }

    public AnnotationVisitor visitTypeAnnotation(int i1, TypePath typepath, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        AnnotationWriter.a(i1, typepath, bytevector);
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        i1 = new AnnotationWriter(b, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = U;
            U = i1;
        } else
        {
            i1.g = V;
            V = i1;
        }
        return i1;
    }

    public AnnotationVisitor visitParameterAnnotation(int i1, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        if("Ljava/lang/Synthetic;".equals(s1))
        {
            S = Math.max(S, i1 + 1);
            return new AnnotationWriter(b, false, bytevector, null, 0);
        }
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        s1 = new AnnotationWriter(b, true, bytevector, bytevector, 2);
        if(flag)
        {
            if(o == null)
                o = new AnnotationWriter[Type.getArgumentTypes(f).length];
            s1.g = o[i1];
            o[i1] = s1;
        } else
        {
            if(p == null)
                p = new AnnotationWriter[Type.getArgumentTypes(f).length];
            s1.g = p[i1];
            p[i1] = s1;
        }
        return s1;
    }

    public void visitAttribute(Attribute attribute)
    {
        if(attribute.isCodeAttribute())
        {
            attribute.a = J;
            J = attribute;
            return;
        } else
        {
            attribute.a = q;
            q = attribute;
            return;
        }
    }

    public void visitCode()
    {
    }

    public void visitFrame(int i1, int j1, Object aobj[], int k1, Object aobj1[])
    {
        if(M == 0)
            return;
        if(i1 == -1)
        {
            if(x == null)
                f();
            T = j1;
            int l1 = a(r.b, j1, k1);
            for(i1 = 0; i1 < j1; i1++)
            {
                if(aobj[i1] instanceof String)
                {
                    z[l1++] = 0x1700000 | b.c((String)aobj[i1]);
                    continue;
                }
                if(aobj[i1] instanceof Integer)
                    z[l1++] = ((Integer)aobj[i1]).intValue();
                else
                    z[l1++] = 0x1800000 | b.a("", ((Label)aobj[i1]).c);
            }

            for(i1 = 0; i1 < k1; i1++)
            {
                if(aobj1[i1] instanceof String)
                {
                    z[l1++] = 0x1700000 | b.c((String)aobj1[i1]);
                    continue;
                }
                if(aobj1[i1] instanceof Integer)
                    z[l1++] = ((Integer)aobj1[i1]).intValue();
                else
                    z[l1++] = 0x1800000 | b.a("", ((Label)aobj1[i1]).c);
            }

            b();
        } else
        {
            int i2;
            if(v == null)
            {
                v = new ByteVector();
                i2 = r.b;
            } else
            if((i2 = r.b - w - 1) < 0)
                if(i1 == 3)
                    return;
                else
                    throw new IllegalStateException();
            switch(i1)
            {
            default:
                break;

            case 0: // '\0'
                T = j1;
                v.putByte(255).putShort(i2).putShort(j1);
                for(i1 = 0; i1 < j1; i1++)
                    a(aobj[i1]);

                v.putShort(k1);
                for(i1 = 0; i1 < k1; i1++)
                    a(aobj1[i1]);

                break;

            case 1: // '\001'
                T += j1;
                v.putByte(j1 + 251).putShort(i2);
                for(i1 = 0; i1 < j1; i1++)
                    a(aobj[i1]);

                break;

            case 2: // '\002'
                T -= j1;
                v.putByte(251 - j1).putShort(i2);
                break;

            case 3: // '\003'
                if(i2 < 64)
                    v.putByte(i2);
                else
                    v.putByte(251).putShort(i2);
                break;

            case 4: // '\004'
                if(i2 < 64)
                    v.putByte(i2 + 64);
                else
                    v.putByte(247).putShort(i2);
                a(aobj1[0]);
                break;
            }
            w = r.b;
            u++;
        }
        s = Math.max(s, k1);
        t = Math.max(t, T);
    }

    public void visitInsn(int i1)
    {
        Y = r.b;
        r.putByte(i1);
        if(P != null)
        {
            if(M == 0)
            {
                P.h.a(i1, 0, null, null);
            } else
            {
                int j1;
                if((j1 = Q + Frame.a[i1]) > R)
                    R = j1;
                Q = j1;
            }
            if(i1 >= 172 && i1 <= 177 || i1 == 191)
                e();
        }
    }

    public void visitIntInsn(int i1, int j1)
    {
        Y = r.b;
        if(P != null)
            if(M == 0)
                P.h.a(i1, j1, null, null);
            else
            if(i1 != 188)
            {
                int k1;
                if((k1 = Q + 1) > R)
                    R = k1;
                Q = k1;
            }
        if(i1 == 17)
        {
            r.b(i1, j1);
            return;
        } else
        {
            r.a(i1, j1);
            return;
        }
    }

    public void visitVarInsn(int i1, int j1)
    {
        Y = r.b;
        if(P != null)
            if(M == 0)
                P.h.a(i1, j1, null, null);
            else
            if(i1 == 169)
            {
                P.a |= 0x100;
                P.f = Q;
                e();
            } else
            {
                int k1;
                if((k1 = Q + Frame.a[i1]) > R)
                    R = k1;
                Q = k1;
            }
        if(M != 2)
        {
            int l1;
            if(i1 == 22 || i1 == 24 || i1 == 55 || i1 == 57)
                l1 = j1 + 2;
            else
                l1 = j1 + 1;
            if(l1 > t)
                t = l1;
        }
        if(j1 < 4 && i1 != 169)
        {
            int i2;
            if(i1 < 54)
                i2 = 26 + (i1 - 21 << 2) + j1;
            else
                i2 = 59 + (i1 - 54 << 2) + j1;
            r.putByte(i2);
        } else
        if(j1 >= 256)
            r.putByte(196).b(i1, j1);
        else
            r.a(i1, j1);
        if(i1 >= 54 && M == 0 && A > 0)
            visitLabel(new Label());
    }

    public void visitTypeInsn(int i1, String s1)
    {
        Y = r.b;
        s1 = b.a(s1);
        if(P != null)
            if(M == 0)
                P.h.a(i1, r.b, b, s1);
            else
            if(i1 == 187)
            {
                int j1;
                if((j1 = Q + 1) > R)
                    R = j1;
                Q = j1;
            }
        r.b(i1, ((Item) (s1)).a);
    }

    public void visitFieldInsn(int i1, String s1, String s2, String s3)
    {
        Y = r.b;
        s1 = b.a(s1, s2, s3);
        if(P != null)
            if(M == 0)
            {
                P.h.a(i1, 0, b, s1);
            } else
            {
                s2 = s3.charAt(0);
                switch(i1)
                {
                case 178: 
                    s2 = Q + (s2 != 68 && s2 != 74 ? 1 : 2);
                    break;

                case 179: 
                    s2 = Q + (s2 != 68 && s2 != 74 ? -1 : -2);
                    break;

                case 180: 
                    s2 = Q + (s2 != 68 && s2 != 74 ? 0 : 1);
                    break;

                default:
                    s2 = Q + (s2 != 68 && s2 != 74 ? -2 : -3);
                    break;
                }
                if(s2 > R)
                    R = s2;
                Q = s2;
            }
        r.b(i1, ((Item) (s1)).a);
    }

    public void visitMethodInsn(int i1, String s1, String s2, String s3, boolean flag)
    {
        Y = r.b;
        s2 = ((Item) (s1 = b.a(s1, s2, s3, flag))).c;
        if(P != null)
            if(M == 0)
            {
                P.h.a(i1, 0, b, s1);
            } else
            {
                if(s2 == 0)
                {
                    s2 = Type.getArgumentsAndReturnSizes(s3);
                    s1.c = s2;
                }
                if(i1 == 184)
                    flag = (Q - (s2 >> 2)) + (s2 & 3) + 1;
                else
                    flag = (Q - (s2 >> 2)) + (s2 & 3);
                if(flag > R)
                    R = ((flag) ? 1 : 0);
                Q = ((flag) ? 1 : 0);
            }
        if(i1 == 185)
        {
            if(s2 == 0)
            {
                s2 = Type.getArgumentsAndReturnSizes(s3);
                s1.c = s2;
            }
            r.b(185, ((Item) (s1)).a).a(s2 >> 2, 0);
            return;
        } else
        {
            r.b(i1, ((Item) (s1)).a);
            return;
        }
    }

    public transient void visitInvokeDynamicInsn(String s1, String s2, Handle handle, Object aobj[])
    {
        Y = r.b;
        handle = ((Item) (s1 = b.a(s1, s2, handle, aobj))).c;
        if(P != null)
            if(M == 0)
            {
                P.h.a(186, 0, b, s1);
            } else
            {
                if(handle == 0)
                {
                    handle = Type.getArgumentsAndReturnSizes(s2);
                    s1.c = handle;
                }
                if((s2 = (Q - (handle >> 2)) + (handle & 3) + 1) > R)
                    R = s2;
                Q = s2;
            }
        r.b(186, ((Item) (s1)).a);
        r.putShort(0);
    }

    public void visitJumpInsn(int i1, Label label)
    {
        Y = r.b;
        Label label1 = null;
        if(P != null)
            if(M == 0)
            {
                P.h.a(i1, 0, null, null);
                label.a().a |= 0x10;
                a(0, label);
                if(i1 != 167)
                    label1 = new Label();
            } else
            if(i1 == 168)
            {
                if((label.a & 0x200) == 0)
                {
                    label.a |= 0x200;
                    L++;
                }
                P.a |= 0x80;
                a(Q + 1, label);
                label1 = new Label();
            } else
            {
                Q += Frame.a[i1];
                a(Q, label);
            }
        if((label.a & 2) != 0 && label.c - r.b < -32768)
        {
            if(i1 == 167)
                r.putByte(200);
            else
            if(i1 == 168)
            {
                r.putByte(201);
            } else
            {
                if(label1 != null)
                    label1.a |= 0x10;
                r.putByte(i1 > 166 ? i1 ^ 1 : (i1 + 1 ^ 1) - 1);
                r.putShort(8);
                r.putByte(200);
            }
            label.a(this, r, r.b - 1, true);
        } else
        {
            r.putByte(i1);
            label.a(this, r, r.b - 1, false);
        }
        if(P != null)
        {
            if(label1 != null)
                visitLabel(label1);
            if(i1 == 167)
                e();
        }
    }

    public void visitLabel(Label label)
    {
        K |= label.a(this, r.b, r.a);
        if((label.a & 1) != 0)
            return;
        if(M == 0)
        {
            if(P != null)
            {
                if(label.c == P.c)
                {
                    P.a |= label.a & 0x10;
                    label.h = P.h;
                    return;
                }
                a(0, label);
            }
            P = label;
            if(label.h == null)
            {
                label.h = new Frame();
                label.h.b = label;
            }
            if(O != null)
            {
                if(label.c == O.c)
                {
                    O.a |= label.a & 0x10;
                    label.h = O.h;
                    P = O;
                    return;
                }
                O.i = label;
            }
            O = label;
            return;
        }
        if(M == 1)
        {
            if(P != null)
            {
                P.g = R;
                a(Q, label);
            }
            P = label;
            Q = 0;
            R = 0;
            if(O != null)
                O.i = label;
            O = label;
        }
    }

    public void visitLdcInsn(Object obj)
    {
        Y = r.b;
        obj = b.a(obj);
        if(P != null)
            if(M == 0)
            {
                P.h.a(18, 0, b, ((Item) (obj)));
            } else
            {
                int i1;
                if(((Item) (obj)).b == 5 || ((Item) (obj)).b == 6)
                    i1 = Q + 2;
                else
                    i1 = Q + 1;
                if(i1 > R)
                    R = i1;
                Q = i1;
            }
        int j1 = ((Item) (obj)).a;
        if(((Item) (obj)).b == 5 || ((Item) (obj)).b == 6)
        {
            r.b(20, j1);
            return;
        }
        if(j1 >= 256)
        {
            r.b(19, j1);
            return;
        } else
        {
            r.a(18, j1);
            return;
        }
    }

    public void visitIincInsn(int i1, int j1)
    {
        Y = r.b;
        if(P != null && M == 0)
            P.h.a(132, i1, null, null);
        int k1;
        if(M != 2 && (k1 = i1 + 1) > t)
            t = k1;
        if(i1 > 255 || j1 > 127 || j1 < -128)
        {
            r.putByte(196).b(132, i1).putShort(j1);
            return;
        } else
        {
            r.putByte(132).a(i1, j1);
            return;
        }
    }

    public transient void visitTableSwitchInsn(int i1, int j1, Label label, Label alabel[])
    {
        Y = r.b;
        int k1 = r.b;
        r.putByte(170);
        r.putByteArray(null, 0, (4 - r.b % 4) % 4);
        label.a(this, r, k1, true);
        r.putInt(i1).putInt(j1);
        for(i1 = 0; i1 < alabel.length; i1++)
            alabel[i1].a(this, r, k1, true);

        a(label, alabel);
    }

    public void visitLookupSwitchInsn(Label label, int ai[], Label alabel[])
    {
        Y = r.b;
        int i1 = r.b;
        r.putByte(171);
        r.putByteArray(null, 0, (4 - r.b % 4) % 4);
        label.a(this, r, i1, true);
        r.putInt(alabel.length);
        for(int j1 = 0; j1 < alabel.length; j1++)
        {
            r.putInt(ai[j1]);
            alabel[j1].a(this, r, i1, true);
        }

        a(label, alabel);
    }

    private void a(Label label, Label alabel[])
    {
        if(P != null)
        {
            if(M == 0)
            {
                P.h.a(171, 0, null, null);
                a(0, label);
                label.a().a |= 0x10;
                for(label = 0; label < alabel.length; label++)
                {
                    a(0, alabel[label]);
                    alabel[label].a().a |= 0x10;
                }

            } else
            {
                Q--;
                a(Q, label);
                for(label = 0; label < alabel.length; label++)
                    a(Q, alabel[label]);

            }
            e();
        }
    }

    public void visitMultiANewArrayInsn(String s1, int i1)
    {
        Y = r.b;
        s1 = b.a(s1);
        if(P != null)
            if(M == 0)
                P.h.a(197, i1, b, s1);
            else
                Q += 1 - i1;
        r.b(197, ((Item) (s1)).a).putByte(i1);
    }

    public AnnotationVisitor visitInsnAnnotation(int i1, TypePath typepath, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        AnnotationWriter.a(i1 = i1 & 0xff0000ff | Y << 8, typepath, bytevector);
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        i1 = new AnnotationWriter(b, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = W;
            W = i1;
        } else
        {
            i1.g = X;
            X = i1;
        }
        return i1;
    }

    public void visitTryCatchBlock(Label label, Label label1, Label label2, String s1)
    {
        A++;
        Handler handler;
        (handler = new Handler()).a = label;
        handler.b = label1;
        handler.c = label2;
        handler.d = s1;
        handler.e = s1 == null ? 0 : b.newClass(s1);
        if(C == null)
            B = handler;
        else
            C.f = handler;
        C = handler;
    }

    public AnnotationVisitor visitTryCatchAnnotation(int i1, TypePath typepath, String s1, boolean flag)
    {
        ByteVector bytevector = new ByteVector();
        AnnotationWriter.a(i1, typepath, bytevector);
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        i1 = new AnnotationWriter(b, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = W;
            W = i1;
        } else
        {
            i1.g = X;
            X = i1;
        }
        return i1;
    }

    public void visitLocalVariable(String s1, String s2, String s3, Label label, Label label1, int i1)
    {
        if(s3 != null)
        {
            if(G == null)
                G = new ByteVector();
            F++;
            G.putShort(label.c).putShort(label1.c - label.c).putShort(b.newUTF8(s1)).putShort(b.newUTF8(s3)).putShort(i1);
        }
        if(E == null)
            E = new ByteVector();
        D++;
        E.putShort(label.c).putShort(label1.c - label.c).putShort(b.newUTF8(s1)).putShort(b.newUTF8(s2)).putShort(i1);
        if(M != 2)
        {
            s1 = s2.charAt(0);
            if((s1 = i1 + (s1 != 74 && s1 != 68 ? 1 : 2)) > t)
                t = s1;
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int i1, TypePath typepath, Label alabel[], Label alabel1[], int ai[], String s1, boolean flag)
    {
        ByteVector bytevector;
        (bytevector = new ByteVector()).putByte(i1 >>> 24).putShort(alabel.length);
        for(i1 = 0; i1 < alabel.length; i1++)
            bytevector.putShort(alabel[i1].c).putShort(alabel1[i1].c - alabel[i1].c).putShort(ai[i1]);

        if(typepath == null)
        {
            bytevector.putByte(0);
        } else
        {
            i1 = (typepath.a[typepath.b] << 1) + 1;
            bytevector.putByteArray(typepath.a, typepath.b, i1);
        }
        bytevector.putShort(b.newUTF8(s1)).putShort(0);
        i1 = new AnnotationWriter(b, true, bytevector, bytevector, bytevector.b - 2);
        if(flag)
        {
            i1.g = W;
            W = i1;
        } else
        {
            i1.g = X;
            X = i1;
        }
        return i1;
    }

    public void visitLineNumber(int i1, Label label)
    {
        if(I == null)
            I = new ByteVector();
        H++;
        I.putShort(label.c);
        I.putShort(i1);
    }

    public void visitMaxs(int i1, int j1)
    {
        if(K)
            d();
        if(M == 0)
        {
            for(j1 = B; j1 != null; j1 = ((Handler) (j1)).f)
            {
                Label label = ((Handler) (j1)).a.a();
                Label label2 = ((Handler) (j1)).c.a();
                Label label7 = ((Handler) (j1)).b.a();
                String s1 = ((Handler) (j1)).d != null ? ((Handler) (j1)).d : "java/lang/Throwable";
                int i3 = 0x1700000 | b.c(s1);
                label2.a |= 0x10;
                for(; label != label7; label = label.i)
                {
                    Edge edge1;
                    (edge1 = new Edge()).a = i3;
                    edge1.b = label2;
                    edge1.c = label.j;
                    label.j = edge1;
                }

            }

            Frame frame = N.h;
            Type atype[] = Type.getArgumentTypes(f);
            frame.a(b, c, atype, t);
            b(frame);
            int k2 = 0;
            for(Object obj = N; obj != null;)
            {
                Label label14 = ((Label) (obj));
                obj = ((Label) (obj)).k;
                label14.k = null;
                Frame frame1 = label14.h;
                if((label14.a & 0x10) != 0)
                    label14.a |= 0x20;
                label14.a |= 0x40;
                int k3;
                if((k3 = frame1.d.length + label14.g) > k2)
                    k2 = k3;
                i1 = label14.j;
                while(i1 != null) 
                {
                    j1 = ((Edge) (i1)).b.a();
                    boolean flag;
                    if((flag = frame1.a(b, ((Label) (j1)).h, ((Edge) (i1)).a)) && ((Label) (j1)).k == null)
                    {
                        j1.k = ((Label) (obj));
                        obj = j1;
                    }
                    i1 = ((Edge) (i1)).c;
                }
            }

            for(Label label15 = N; label15 != null; label15 = label15.i)
            {
                Frame frame2 = label15.h;
                if((label15.a & 0x20) != 0)
                    b(frame2);
                if((label15.a & 0x40) != 0)
                    continue;
                Label label16 = label15.i;
                i1 = label15.c;
                if((j1 = (label16 != null ? label16.c : r.b) - 1) < i1)
                    continue;
                k2 = Math.max(k2, 1);
                for(int i2 = i1; i2 < j1; i2++)
                    r.a[i2] = 0;

                r.a[j1] = -65;
                int j2 = a(i1, 0, 1);
                z[j2] = 0x1700000 | b.c("java/lang/Throwable");
                b();
                B = Handler.a(B, label15, label16);
            }

            j1 = B;
            A = 0;
            for(; j1 != null; j1 = ((Handler) (j1)).f)
                A++;

            s = k2;
            return;
        }
        if(M == 1)
        {
            for(j1 = B; j1 != null; j1 = ((Handler) (j1)).f)
            {
                Label label1 = ((Handler) (j1)).a;
                Label label3 = ((Handler) (j1)).c;
                for(Label label8 = ((Handler) (j1)).b; label1 != label8; label1 = label1.i)
                {
                    Edge edge;
                    (edge = new Edge()).a = 0x7fffffff;
                    edge.b = label3;
                    if((label1.a & 0x80) == 0)
                    {
                        edge.c = label1.j;
                        label1.j = edge;
                    } else
                    {
                        edge.c = label1.j.c.c;
                        label1.j.c.c = edge;
                    }
                }

            }

            if(L > 0)
            {
                int k1 = 0;
                N.b(null, 1L, L);
                for(Label label4 = N; label4 != null; label4 = label4.i)
                {
                    Label label9;
                    if((label4.a & 0x80) != 0 && ((label9 = label4.j.c.b).a & 0x400) == 0)
                    {
                        k1++;
                        label9.b(null, (long)k1 / 32L << 32 | 1L << k1 % 32, L);
                    }
                }

                for(Label label5 = N; label5 != null; label5 = label5.i)
                {
                    if((label5.a & 0x80) == 0)
                        continue;
                    for(Label label10 = N; label10 != null; label10 = label10.i)
                        label10.a &= 0xfffff7ff;

                    Label label13;
                    (label13 = label5.j.c.b).b(label5, 0L, L);
                }

            }
            int l1 = 0;
            for(Label label6 = N; label6 != null;)
            {
                Label label11 = label6;
                label6 = label6.k;
                int l2;
                int j3;
                if((j3 = (l2 = label11.f) + label11.g) > l1)
                    l1 = j3;
                Edge edge2 = label11.j;
                if((label11.a & 0x80) != 0)
                    edge2 = edge2.c;
                while(edge2 != null) 
                {
                    Label label12;
                    if(((label12 = edge2.b).a & 8) == 0)
                    {
                        label12.f = edge2.a != 0x7fffffff ? l2 + edge2.a : 1;
                        label12.a |= 8;
                        label12.k = label6;
                        label6 = label12;
                    }
                    edge2 = edge2.c;
                }
            }

            s = Math.max(i1, l1);
            return;
        } else
        {
            s = i1;
            t = j1;
            return;
        }
    }

    public void visitEnd()
    {
    }

    private void a(int i1, Label label)
    {
        Edge edge;
        (edge = new Edge()).a = i1;
        edge.b = label;
        edge.c = P.j;
        P.j = edge;
    }

    private void e()
    {
        if(M == 0)
        {
            Label label;
            (label = new Label()).h = new Frame();
            label.h.b = label;
            label.a(this, r.b, r.a);
            O.i = label;
            O = label;
        } else
        {
            P.g = R;
        }
        P = null;
    }

    private void b(Frame frame)
    {
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int ai[] = frame.c;
        int ai1[] = frame.d;
        for(int l1 = 0; l1 < ai.length; l1++)
        {
            int l2;
            if((l2 = ai[l1]) == 0x1000000)
            {
                i1++;
            } else
            {
                j1 += i1 + 1;
                i1 = 0;
            }
            if(l2 == 0x1000004 || l2 == 0x1000003)
                l1++;
        }

        for(int i2 = 0; i2 < ai1.length; i2++)
        {
            int i3 = ai1[i2];
            k1++;
            if(i3 == 0x1000004 || i3 == 0x1000003)
                i2++;
        }

        frame = a(frame.b.c, j1, k1);
        int j2 = 0;
        for(; j1 > 0; j1--)
        {
            int j3 = ai[j2];
            z[frame++] = j3;
            if(j3 == 0x1000004 || j3 == 0x1000003)
                j2++;
            j2++;
        }

        for(int k2 = 0; k2 < ai1.length; k2++)
        {
            int k3 = ai1[k2];
            z[frame++] = k3;
            if(k3 == 0x1000004 || k3 == 0x1000003)
                k2++;
        }

        b();
    }

    private void f()
    {
        int i1 = a(0, f.length() + 1, 0);
        if((c & 8) == 0)
            if((c & 0x80000) == 0)
                z[i1++] = 0x1700000 | b.c(b.I);
            else
                z[i1++] = 6;
        int j1 = 1;
        do
        {
            int k1 = j1;
            switch(f.charAt(j1++))
            {
            case 66: // 'B'
            case 67: // 'C'
            case 73: // 'I'
            case 83: // 'S'
            case 90: // 'Z'
                z[i1++] = 1;
                break;

            case 70: // 'F'
                z[i1++] = 2;
                break;

            case 74: // 'J'
                z[i1++] = 4;
                break;

            case 68: // 'D'
                z[i1++] = 3;
                break;

            case 91: // '['
                for(; f.charAt(j1) == '['; j1++);
                if(f.charAt(j1) == 'L')
                    for(j1++; f.charAt(j1) != ';'; j1++);
                z[i1++] = 0x1700000 | b.c(f.substring(k1, ++j1));
                break;

            case 76: // 'L'
                for(; f.charAt(j1) != ';'; j1++);
                z[i1++] = 0x1700000 | b.c(f.substring(k1 + 1, j1++));
                break;

            case 69: // 'E'
            case 71: // 'G'
            case 72: // 'H'
            case 75: // 'K'
            case 77: // 'M'
            case 78: // 'N'
            case 79: // 'O'
            case 80: // 'P'
            case 81: // 'Q'
            case 82: // 'R'
            case 84: // 'T'
            case 85: // 'U'
            case 86: // 'V'
            case 87: // 'W'
            case 88: // 'X'
            case 89: // 'Y'
            default:
                z[1] = i1 - 3;
                b();
                return;
            }
        } while(true);
    }

    private int a(int i1, int j1, int k1)
    {
        int l1 = j1 + 3 + k1;
        if(z == null || z.length < l1)
            z = new int[l1];
        z[0] = i1;
        z[1] = j1;
        z[2] = k1;
        return 3;
    }

    private void b()
    {
        if(x != null)
        {
            if(v == null)
                v = new ByteVector();
            c();
            u++;
        }
        x = z;
        z = null;
    }

    private void c()
    {
        int i1 = z[1];
        int j1 = z[2];
        if((b.b & 0xffff) < 50)
        {
            v.putShort(z[0]).putShort(i1);
            a(3, i1 + 3);
            v.putShort(j1);
            a(i1 + 3, i1 + 3 + j1);
            return;
        }
        int k1 = x[1];
        char c1 = '\377';
        int l1 = 0;
        int i2;
        if(u == 0)
            i2 = z[0];
        else
            i2 = z[0] - x[0] - 1;
        if(j1 == 0)
            switch(l1 = i1 - k1)
            {
            case -3: 
            case -2: 
            case -1: 
                c1 = '\370';
                k1 = i1;
                break;

            case 0: // '\0'
                c1 = i2 >= 64 ? '\373' : '\0';
                break;

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                c1 = '\374';
                break;
            }
        else
        if(i1 == k1 && j1 == 1)
            c1 = i2 >= 63 ? '\367' : '@';
        if(c1 != '\377')
        {
            int j2 = 3;
            int k2 = 0;
            do
            {
                if(k2 >= k1)
                    break;
                if(z[j2] != x[j2])
                {
                    c1 = '\377';
                    break;
                }
                j2++;
                k2++;
            } while(true);
        }
        switch(c1)
        {
        case 0: // '\0'
            v.putByte(i2);
            return;

        case 64: // '@'
            v.putByte(i2 + 64);
            a(i1 + 3, i1 + 4);
            return;

        case 247: 
            v.putByte(247).putShort(i2);
            a(i1 + 3, i1 + 4);
            return;

        case 251: 
            v.putByte(251).putShort(i2);
            return;

        case 248: 
            v.putByte(l1 + 251).putShort(i2);
            return;

        case 252: 
            v.putByte(l1 + 251).putShort(i2);
            a(k1 + 3, i1 + 3);
            return;
        }
        v.putByte(255).putShort(i2).putShort(i1);
        a(3, i1 + 3);
        v.putShort(j1);
        a(i1 + 3, i1 + 3 + j1);
    }

    private void a(int i1, int j1)
    {
        for(i1 = i1; i1 < j1; i1++)
        {
            int k1;
            int l1;
            if((l1 = (k1 = z[i1]) & 0xf0000000) == 0)
            {
                int i2 = k1 & 0xfffff;
                switch(k1 & 0xff00000)
                {
                case 24117248: 
                    v.putByte(7).putShort(b.newClass(b.H[i2].g));
                    break;

                case 25165824: 
                    v.putByte(8).putShort(b.H[i2].c);
                    break;

                default:
                    v.putByte(i2);
                    break;
                }
                continue;
            }
            StringBuffer stringbuffer = new StringBuffer();
            for(l1 >>= 28; l1-- > 0;)
                stringbuffer.append('[');

            if((k1 & 0xff00000) == 0x1700000)
            {
                stringbuffer.append('L');
                stringbuffer.append(b.H[k1 & 0xfffff].g);
                stringbuffer.append(';');
            } else
            {
                switch(k1 & 0xf)
                {
                case 1: // '\001'
                    stringbuffer.append('I');
                    break;

                case 2: // '\002'
                    stringbuffer.append('F');
                    break;

                case 3: // '\003'
                    stringbuffer.append('D');
                    break;

                case 9: // '\t'
                    stringbuffer.append('Z');
                    break;

                case 10: // '\n'
                    stringbuffer.append('B');
                    break;

                case 11: // '\013'
                    stringbuffer.append('C');
                    break;

                case 12: // '\f'
                    stringbuffer.append('S');
                    break;

                case 4: // '\004'
                case 5: // '\005'
                case 6: // '\006'
                case 7: // '\007'
                case 8: // '\b'
                default:
                    stringbuffer.append('J');
                    break;
                }
            }
            v.putByte(7).putShort(b.newClass(stringbuffer.toString()));
        }

    }

    private void a(Object obj)
    {
        if(obj instanceof String)
        {
            v.putByte(7).putShort(b.newClass((String)obj));
            return;
        }
        if(obj instanceof Integer)
        {
            v.putByte(((Integer)obj).intValue());
            return;
        } else
        {
            v.putByte(8).putShort(((Label)obj).c);
            return;
        }
    }

    final int a()
    {
        if(h != 0)
            return 6 + i;
        int i1 = 8;
        if(r.b > 0)
        {
            if(r.b > 0x10000)
                throw new RuntimeException("Method code too large!");
            b.newUTF8("Code");
            i1 = 8 + (18 + r.b + 8 * A);
            if(E != null)
            {
                b.newUTF8("LocalVariableTable");
                i1 += 8 + E.b;
            }
            if(G != null)
            {
                b.newUTF8("LocalVariableTypeTable");
                i1 += 8 + G.b;
            }
            if(I != null)
            {
                b.newUTF8("LineNumberTable");
                i1 += 8 + I.b;
            }
            if(v != null)
            {
                boolean flag = (b.b & 0xffff) >= 50;
                b.newUTF8(flag ? "StackMapTable" : "StackMap");
                i1 += 8 + v.b;
            }
            if(W != null)
            {
                b.newUTF8("RuntimeVisibleTypeAnnotations");
                i1 += 8 + W.a();
            }
            if(X != null)
            {
                b.newUTF8("RuntimeInvisibleTypeAnnotations");
                i1 += 8 + X.a();
            }
            if(J != null)
                i1 += J.a(b, r.a, r.b, s, t);
        }
        if(j > 0)
        {
            b.newUTF8("Exceptions");
            i1 += 8 + 2 * j;
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
        if(g != null)
        {
            b.newUTF8("Signature");
            b.newUTF8(g);
            i1 += 8;
        }
        if($ != null)
        {
            b.newUTF8("MethodParameters");
            i1 += 7 + $.b;
        }
        if(l != null)
        {
            b.newUTF8("AnnotationDefault");
            i1 += 6 + l.b;
        }
        if(m != null)
        {
            b.newUTF8("RuntimeVisibleAnnotations");
            i1 += 8 + m.a();
        }
        if(n != null)
        {
            b.newUTF8("RuntimeInvisibleAnnotations");
            i1 += 8 + n.a();
        }
        if(U != null)
        {
            b.newUTF8("RuntimeVisibleTypeAnnotations");
            i1 += 8 + U.a();
        }
        if(V != null)
        {
            b.newUTF8("RuntimeInvisibleTypeAnnotations");
            i1 += 8 + V.a();
        }
        if(o != null)
        {
            b.newUTF8("RuntimeVisibleParameterAnnotations");
            i1 += 7 + 2 * (o.length - S);
            for(int j1 = o.length - 1; j1 >= S; j1--)
                i1 += o[j1] != null ? o[j1].a() : 0;

        }
        if(p != null)
        {
            b.newUTF8("RuntimeInvisibleParameterAnnotations");
            i1 += 7 + 2 * (p.length - S);
            for(int k1 = p.length - 1; k1 >= S; k1--)
                i1 += p[k1] != null ? p[k1].a() : 0;

        }
        if(q != null)
            i1 += q.a(b, null, 0, -1, -1);
        return i1;
    }

    final void a(ByteVector bytevector)
    {
        int i1 = 0xe0000 | (c & 0x40000) / 64;
        bytevector.putShort(c & ~i1).putShort(d).putShort(e);
        if(h != 0)
        {
            bytevector.putByteArray(b.M.b, h, i);
            return;
        }
        i1 = 0;
        if(r.b > 0)
            i1++;
        if(j > 0)
            i1++;
        if((c & 0x1000) != 0 && ((b.b & 0xffff) < 49 || (c & 0x40000) != 0))
            i1++;
        if((c & 0x20000) != 0)
            i1++;
        if(g != null)
            i1++;
        if($ != null)
            i1++;
        if(l != null)
            i1++;
        if(m != null)
            i1++;
        if(n != null)
            i1++;
        if(U != null)
            i1++;
        if(V != null)
            i1++;
        if(o != null)
            i1++;
        if(p != null)
            i1++;
        if(q != null)
            i1 += q.a();
        bytevector.putShort(i1);
        if(r.b > 0)
        {
            int j1 = 12 + r.b + 8 * A;
            if(E != null)
                j1 += 8 + E.b;
            if(G != null)
                j1 += 8 + G.b;
            if(I != null)
                j1 += 8 + I.b;
            if(v != null)
                j1 += 8 + v.b;
            if(W != null)
                j1 += 8 + W.a();
            if(X != null)
                j1 += 8 + X.a();
            if(J != null)
                j1 += J.a(b, r.a, r.b, s, t);
            bytevector.putShort(b.newUTF8("Code")).putInt(j1);
            bytevector.putShort(s).putShort(t);
            bytevector.putInt(r.b).putByteArray(r.a, 0, r.b);
            bytevector.putShort(A);
            if(A > 0)
            {
                for(Handler handler = B; handler != null; handler = handler.f)
                    bytevector.putShort(handler.a.c).putShort(handler.b.c).putShort(handler.c.c).putShort(handler.e);

            }
            handler = 0;
            if(E != null)
                handler++;
            if(G != null)
                handler++;
            if(I != null)
                handler++;
            if(v != null)
                handler++;
            if(W != null)
                handler++;
            if(X != null)
                handler++;
            if(J != null)
                handler += J.a();
            bytevector.putShort(handler);
            if(E != null)
            {
                bytevector.putShort(b.newUTF8("LocalVariableTable"));
                bytevector.putInt(E.b + 2).putShort(D);
                bytevector.putByteArray(E.a, 0, E.b);
            }
            if(G != null)
            {
                bytevector.putShort(b.newUTF8("LocalVariableTypeTable"));
                bytevector.putInt(G.b + 2).putShort(F);
                bytevector.putByteArray(G.a, 0, G.b);
            }
            if(I != null)
            {
                bytevector.putShort(b.newUTF8("LineNumberTable"));
                bytevector.putInt(I.b + 2).putShort(H);
                bytevector.putByteArray(I.a, 0, I.b);
            }
            if(v != null)
            {
                boolean flag = (b.b & 0xffff) >= 50;
                bytevector.putShort(b.newUTF8(flag ? "StackMapTable" : "StackMap"));
                bytevector.putInt(v.b + 2).putShort(u);
                bytevector.putByteArray(v.a, 0, v.b);
            }
            if(W != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeVisibleTypeAnnotations"));
                W.a(bytevector);
            }
            if(X != null)
            {
                bytevector.putShort(b.newUTF8("RuntimeInvisibleTypeAnnotations"));
                X.a(bytevector);
            }
            if(J != null)
                J.a(b, r.a, r.b, t, s, bytevector);
        }
        if(j > 0)
        {
            bytevector.putShort(b.newUTF8("Exceptions")).putInt(2 * j + 2);
            bytevector.putShort(j);
            for(int k1 = 0; k1 < j; k1++)
                bytevector.putShort(k[k1]);

        }
        if((c & 0x1000) != 0 && ((b.b & 0xffff) < 49 || (c & 0x40000) != 0))
            bytevector.putShort(b.newUTF8("Synthetic")).putInt(0);
        if((c & 0x20000) != 0)
            bytevector.putShort(b.newUTF8("Deprecated")).putInt(0);
        if(g != null)
            bytevector.putShort(b.newUTF8("Signature")).putInt(2).putShort(b.newUTF8(g));
        if($ != null)
        {
            bytevector.putShort(b.newUTF8("MethodParameters"));
            bytevector.putInt($.b + 1).putByte(Z);
            bytevector.putByteArray($.a, 0, $.b);
        }
        if(l != null)
        {
            bytevector.putShort(b.newUTF8("AnnotationDefault"));
            bytevector.putInt(l.b);
            bytevector.putByteArray(l.a, 0, l.b);
        }
        if(m != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleAnnotations"));
            m.a(bytevector);
        }
        if(n != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleAnnotations"));
            n.a(bytevector);
        }
        if(U != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleTypeAnnotations"));
            U.a(bytevector);
        }
        if(V != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            V.a(bytevector);
        }
        if(o != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.a(o, S, bytevector);
        }
        if(p != null)
        {
            bytevector.putShort(b.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.a(p, S, bytevector);
        }
        if(q != null)
            q.a(b, null, 0, -1, -1, bytevector);
    }

    private void d()
    {
        byte abyte0[] = r.a;
        int ai[] = new int[0];
        int ai1[] = new int[0];
        boolean aflag[] = new boolean[r.b];
        int l1 = 3;
        do
        {
            if(l1 == 3)
                l1 = 2;
            int k2 = 0;
            do
            {
                if(k2 >= abyte0.length)
                    break;
                int l3 = abyte0[k2] & 0xff;
                int i4 = 0;
                switch(ClassWriter.a[l3])
                {
                case 0: // '\0'
                case 4: // '\004'
                    k2++;
                    break;

                case 9: // '\t'
                    int k4;
                    if(l3 > 201)
                    {
                        l3 = l3 >= 218 ? l3 - 20 : l3 - 49;
                        k4 = k2 + c(abyte0, k2 + 1);
                    } else
                    {
                        k4 = k2 + b(abyte0, k2 + 1);
                    }
                    int k6;
                    if(((k6 = a(ai, ai1, k2, k4)) < -32768 || k6 > 32767) && !aflag[k2])
                    {
                        if(l3 == 167 || l3 == 168)
                            i4 = 2;
                        else
                            i4 = 5;
                        aflag[k2] = true;
                    }
                    k2 += 3;
                    break;

                case 10: // '\n'
                    k2 += 5;
                    break;

                case 14: // '\016'
                    int l6;
                    if(l1 == 1)
                        i4 = -((l6 = a(ai, ai1, 0, k2)) & 3);
                    else
                    if(!aflag[k2])
                    {
                        i4 = k2 & 3;
                        aflag[k2] = true;
                    }
                    k2 = (k2 = (k2 + 4) - (k2 & 3)) + (4 * ((a(abyte0, k2 + 8) - a(abyte0, k2 + 4)) + 1) + 12);
                    break;

                case 15: // '\017'
                    int i7;
                    if(l1 == 1)
                        i4 = -((i7 = a(ai, ai1, 0, k2)) & 3);
                    else
                    if(!aflag[k2])
                    {
                        i4 = k2 & 3;
                        aflag[k2] = true;
                    }
                    k2 = (k2 = (k2 + 4) - (k2 & 3)) + (8 * a(abyte0, k2 + 4) + 8);
                    break;

                case 17: // '\021'
                    if((l3 = abyte0[k2 + 1] & 0xff) == 132)
                        k2 += 6;
                    else
                        k2 += 4;
                    break;

                case 1: // '\001'
                case 3: // '\003'
                case 11: // '\013'
                    k2 += 2;
                    break;

                case 2: // '\002'
                case 5: // '\005'
                case 6: // '\006'
                case 12: // '\f'
                case 13: // '\r'
                    k2 += 3;
                    break;

                case 7: // '\007'
                case 8: // '\b'
                    k2 += 5;
                    break;

                case 16: // '\020'
                default:
                    k2 += 4;
                    break;
                }
                if(i4 != 0)
                {
                    int ai3[] = new int[ai.length + 1];
                    int ai2[] = new int[ai1.length + 1];
                    System.arraycopy(ai, 0, ai3, 0, ai.length);
                    System.arraycopy(ai1, 0, ai2, 0, ai1.length);
                    ai3[ai.length] = k2;
                    ai2[ai1.length] = i4;
                    ai = ai3;
                    ai1 = ai2;
                    if(i4 > 0)
                        l1 = 3;
                }
            } while(true);
            if(l1 < 3)
                l1--;
        } while(l1 != 0);
        ByteVector bytevector = new ByteVector(r.b);
        int l2 = 0;
        do
        {
            if(l2 >= r.b)
                break;
            int j4 = abyte0[l2] & 0xff;
            switch(ClassWriter.a[j4])
            {
            case 0: // '\0'
            case 4: // '\004'
                bytevector.putByte(j4);
                l2++;
                break;

            case 9: // '\t'
                int l4;
                if(j4 > 201)
                {
                    j4 = j4 >= 218 ? j4 - 20 : j4 - 49;
                    l4 = l2 + c(abyte0, l2 + 1);
                } else
                {
                    l4 = l2 + b(abyte0, l2 + 1);
                }
                int j7 = a(ai, ai1, l2, l4);
                if(aflag[l2])
                {
                    if(j4 == 167)
                        bytevector.putByte(200);
                    else
                    if(j4 == 168)
                    {
                        bytevector.putByte(201);
                    } else
                    {
                        bytevector.putByte(j4 > 166 ? j4 ^ 1 : (j4 + 1 ^ 1) - 1);
                        bytevector.putShort(8);
                        bytevector.putByte(200);
                        j7 -= 3;
                    }
                    bytevector.putInt(j7);
                } else
                {
                    bytevector.putByte(j4);
                    bytevector.putShort(j7);
                }
                l2 += 3;
                break;

            case 10: // '\n'
                int i5 = l2 + a(abyte0, l2 + 1);
                int k7 = a(ai, ai1, l2, i5);
                bytevector.putByte(j4);
                bytevector.putInt(k7);
                l2 += 5;
                break;

            case 14: // '\016'
                int i2 = l2;
                l2 = (l2 + 4) - (i2 & 3);
                bytevector.putByte(170);
                bytevector.putByteArray(null, 0, (4 - bytevector.b % 4) % 4);
                int j5 = i2 + a(abyte0, l2);
                l2 += 4;
                int l7 = a(ai, ai1, i2, j5);
                bytevector.putInt(l7);
                j4 = a(abyte0, l2);
                l2 += 4;
                bytevector.putInt(j4);
                j4 = (a(abyte0, l2) - j4) + 1;
                l2 += 4;
                bytevector.putInt(a(abyte0, l2 - 4));
                while(j4 > 0) 
                {
                    int k5 = i2 + a(abyte0, l2);
                    l2 += 4;
                    int i8 = a(ai, ai1, i2, k5);
                    bytevector.putInt(i8);
                    j4--;
                }
                break;

            case 15: // '\017'
                int j2 = l2;
                l2 = (l2 + 4) - (j2 & 3);
                bytevector.putByte(171);
                bytevector.putByteArray(null, 0, (4 - bytevector.b % 4) % 4);
                int l5 = j2 + a(abyte0, l2);
                l2 += 4;
                int j8 = a(ai, ai1, j2, l5);
                bytevector.putInt(j8);
                j4 = a(abyte0, l2);
                l2 += 4;
                bytevector.putInt(j4);
                while(j4 > 0) 
                {
                    bytevector.putInt(a(abyte0, l2));
                    l2 += 4;
                    int i6 = j2 + a(abyte0, l2);
                    l2 += 4;
                    int k8 = a(ai, ai1, j2, i6);
                    bytevector.putInt(k8);
                    j4--;
                }
                break;

            case 17: // '\021'
                if((j4 = abyte0[l2 + 1] & 0xff) == 132)
                {
                    bytevector.putByteArray(abyte0, l2, 6);
                    l2 += 6;
                } else
                {
                    bytevector.putByteArray(abyte0, l2, 4);
                    l2 += 4;
                }
                break;

            case 1: // '\001'
            case 3: // '\003'
            case 11: // '\013'
                bytevector.putByteArray(abyte0, l2, 2);
                l2 += 2;
                break;

            case 2: // '\002'
            case 5: // '\005'
            case 6: // '\006'
            case 12: // '\f'
            case 13: // '\r'
                bytevector.putByteArray(abyte0, l2, 3);
                l2 += 3;
                break;

            case 7: // '\007'
            case 8: // '\b'
                bytevector.putByteArray(abyte0, l2, 5);
                l2 += 5;
                break;

            case 16: // '\020'
            default:
                bytevector.putByteArray(abyte0, l2, 4);
                l2 += 4;
                break;
            }
        } while(true);
        if(M == 0)
        {
            for(Label label = N; label != null; label = label.i)
            {
                int i3;
                if((i3 = label.c - 3) >= 0 && aflag[i3])
                    label.a |= 0x10;
                a(ai, ai1, label);
            }

            for(int i1 = 0; i1 < b.H.length; i1++)
            {
                Item item;
                if((item = b.H[i1]) != null && item.b == 31)
                    item.c = a(ai, ai1, 0, item.c);
            }

        } else
        if(u > 0)
            b.L = true;
        for(Handler handler = B; handler != null; handler = handler.f)
        {
            a(ai, ai1, handler.a);
            a(ai, ai1, handler.b);
            a(ai, ai1, handler.c);
        }

        for(int j1 = 0; j1 < 2; j1++)
        {
            ByteVector bytevector1;
            if((bytevector1 = j1 != 0 ? G : E) == null)
                continue;
            byte abyte1[] = bytevector1.a;
            for(int j3 = 0; j3 < bytevector1.b; j3 += 10)
            {
                int j6 = c(abyte1, j3);
                int l8 = a(ai, ai1, 0, j6);
                a(abyte1, j3, l8);
                j6 += c(abyte1, j3 + 2);
                l8 = a(ai, ai1, 0, j6) - l8;
                a(abyte1, j3 + 2, l8);
            }

        }

        if(I != null)
        {
            byte abyte2[] = I.a;
            for(int k3 = 0; k3 < I.b; k3 += 4)
                a(abyte2, k3, a(ai, ai1, 0, c(abyte2, k3)));

        }
        for(Attribute attribute = J; attribute != null; attribute = attribute.a)
        {
            Label alabel[];
            if((alabel = attribute.getLabels()) == null)
                continue;
            for(int k1 = alabel.length - 1; k1 >= 0; k1--)
                a(ai, ai1, alabel[k1]);

        }

        r = bytevector;
    }

    static int c(byte abyte0[], int i1)
    {
        return (abyte0[i1] & 0xff) << 8 | abyte0[i1 + 1] & 0xff;
    }

    static short b(byte abyte0[], int i1)
    {
        return (short)((abyte0[i1] & 0xff) << 8 | abyte0[i1 + 1] & 0xff);
    }

    static int a(byte abyte0[], int i1)
    {
        return (abyte0[i1] & 0xff) << 24 | (abyte0[i1 + 1] & 0xff) << 16 | (abyte0[i1 + 2] & 0xff) << 8 | abyte0[i1 + 3] & 0xff;
    }

    static void a(byte abyte0[], int i1, int j1)
    {
        abyte0[i1] = (byte)(j1 >>> 8);
        abyte0[i1 + 1] = (byte)j1;
    }

    static int a(int ai[], int ai1[], int i1, int j1)
    {
        int k1 = j1 - i1;
        for(int l1 = 0; l1 < ai.length; l1++)
        {
            if(i1 < ai[l1] && ai[l1] <= j1)
            {
                k1 += ai1[l1];
                continue;
            }
            if(j1 < ai[l1] && ai[l1] <= i1)
                k1 -= ai1[l1];
        }

        return k1;
    }

    static void a(int ai[], int ai1[], Label label)
    {
        if((label.a & 4) == 0)
        {
            label.c = a(ai, ai1, 0, label.c);
            label.a |= 4;
        }
    }

    final ClassWriter b;
    private int c;
    private final int d;
    private final int e;
    private final String f;
    String g;
    int h;
    int i;
    int j;
    int k[];
    private ByteVector l;
    private AnnotationWriter m;
    private AnnotationWriter n;
    private AnnotationWriter U;
    private AnnotationWriter V;
    private AnnotationWriter o[];
    private AnnotationWriter p[];
    private int S;
    private Attribute q;
    private ByteVector r;
    private int s;
    private int t;
    private int T;
    private int u;
    private ByteVector v;
    private int w;
    private int x[];
    private int z[];
    private int A;
    private Handler B;
    private Handler C;
    private int Z;
    private ByteVector $;
    private int D;
    private ByteVector E;
    private int F;
    private ByteVector G;
    private int H;
    private ByteVector I;
    private int Y;
    private AnnotationWriter W;
    private AnnotationWriter X;
    private Attribute J;
    private boolean K;
    private int L;
    private final int M;
    private Label N;
    private Label O;
    private Label P;
    private int Q;
    private int R;
}
