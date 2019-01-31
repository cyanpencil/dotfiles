// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            ByteVector, Edge, Frame, MethodWriter

public class Label
{

    public Label()
    {
    }

    public int getOffset()
    {
        if((a & 2) == 0)
            throw new IllegalStateException("Label offset position has not been resolved yet");
        else
            return c;
    }

    void a(MethodWriter methodwriter, ByteVector bytevector, int l, boolean flag)
    {
        if((a & 2) == 0)
            if(flag)
            {
                a(-1 - l, bytevector.b);
                bytevector.putInt(-1);
                return;
            } else
            {
                a(l, bytevector.b);
                bytevector.putShort(-1);
                return;
            }
        if(flag)
        {
            bytevector.putInt(c - l);
            return;
        } else
        {
            bytevector.putShort(c - l);
            return;
        }
    }

    private void a(int l, int i1)
    {
        if(e == null)
            e = new int[6];
        if(d >= e.length)
        {
            int ai[] = new int[e.length + 6];
            System.arraycopy(e, 0, ai, 0, e.length);
            e = ai;
        }
        e[d++] = l;
        e[d++] = i1;
    }

    boolean a(MethodWriter methodwriter, int l, byte abyte0[])
    {
        methodwriter = 0;
        a |= 2;
        c = l;
        for(int i1 = 0; i1 < d;)
        {
            int j1 = e[i1++];
            int k1 = e[i1++];
            if(j1 >= 0)
            {
                if((j1 = l - j1) < -32768 || j1 > 32767)
                {
                    if((methodwriter = abyte0[k1 - 1] & 0xff) <= 168)
                        abyte0[k1 - 1] = (byte)(methodwriter + 49);
                    else
                        abyte0[k1 - 1] = (byte)(methodwriter + 20);
                    methodwriter = 1;
                }
                abyte0[k1++] = (byte)(j1 >>> 8);
                abyte0[k1] = (byte)j1;
            } else
            {
                j1 = l + j1 + 1;
                abyte0[k1++] = j1 >> 24;
                abyte0[k1++] = (byte)(j1 >>> 16);
                abyte0[k1++] = (byte)(j1 >>> 8);
                abyte0[k1] = (byte)j1;
            }
        }

        return methodwriter;
    }

    Label a()
    {
        if(h == null)
            return this;
        else
            return h.b;
    }

    boolean a(long l)
    {
        if((a & 0x400) != 0)
            return (e[(int)(l >>> 32)] & (int)l) != 0;
        else
            return false;
    }

    boolean a(Label label)
    {
        if((a & 0x400) == 0 || (label.a & 0x400) == 0)
            return false;
        for(int l = 0; l < e.length; l++)
            if((e[l] & label.e[l]) != 0)
                return true;

        return false;
    }

    void a(long l, int i1)
    {
        if((a & 0x400) == 0)
        {
            a |= 0x400;
            e = new int[i1 / 32 + 1];
        }
        e[(int)(l >>> 32)] |= (int)l;
    }

    void b(Label label, long l, int i1)
    {
        Label label1 = this;
        do
        {
            if(label1 == null)
                break;
            Label label2;
            label1 = (label2 = label1).k;
            label2.k = null;
            if(label != null)
            {
                if((label2.a & 0x800) != 0)
                    continue;
                label2.a |= 0x800;
                if((label2.a & 0x100) != 0 && !label2.a(label))
                {
                    Edge edge;
                    (edge = new Edge()).a = label2.f;
                    edge.b = label.j.b;
                    edge.c = label2.j;
                    label2.j = edge;
                }
            } else
            {
                if(label2.a(l))
                    continue;
                label2.a(l, i1);
            }
            Edge edge1 = label2.j;
            while(edge1 != null) 
            {
                if(((label2.a & 0x80) == 0 || edge1 != label2.j.c) && edge1.b.k == null)
                {
                    edge1.b.k = label1;
                    label1 = edge1.b;
                }
                edge1 = edge1.c;
            }
        } while(true);
    }

    public String toString()
    {
        return "L" + System.identityHashCode(this);
    }

    public Object info;
    int a;
    int b;
    int c;
    private int d;
    private int e[];
    int f;
    int g;
    Frame h;
    Label i;
    Edge j;
    Label k;
}
