// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.owlike.genson.internal.asm:
//            AnnotationVisitor, Attribute, ByteVector, ClassVisitor, 
//            ClassWriter, Context, FieldVisitor, Handle, 
//            Item, Label, MethodVisitor, MethodWriter, 
//            Opcodes, Type, TypePath

public class ClassReader
{

    public ClassReader(byte abyte0[])
    {
        this(abyte0, 0, abyte0.length);
    }

    public ClassReader(byte abyte0[], int i, int j)
    {
        b = abyte0;
        if(readShort(i + 6) > 52)
            throw new IllegalArgumentException();
        a = new int[readUnsignedShort(i + 8)];
        j = a.length;
        c = new String[j];
        int k = 0;
        i += 10;
        for(int l = 1; l < j; l++)
        {
            a[l] = i + 1;
            int i1;
            switch(abyte0[i])
            {
            case 3: // '\003'
            case 4: // '\004'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 18: // '\022'
                i1 = 5;
                break;

            case 5: // '\005'
            case 6: // '\006'
                i1 = 9;
                l++;
                break;

            case 1: // '\001'
                if((i1 = 3 + readUnsignedShort(i + 1)) > k)
                    k = i1;
                break;

            case 15: // '\017'
                i1 = 4;
                break;

            case 2: // '\002'
            case 7: // '\007'
            case 8: // '\b'
            case 13: // '\r'
            case 14: // '\016'
            case 16: // '\020'
            case 17: // '\021'
            default:
                i1 = 3;
                break;
            }
            i += i1;
        }

        d = k;
        header = i;
    }

    public int getAccess()
    {
        return readUnsignedShort(header);
    }

    public String getClassName()
    {
        return readClass(header + 2, new char[d]);
    }

    public String getSuperName()
    {
        return readClass(header + 4, new char[d]);
    }

    public String[] getInterfaces()
    {
        int i = header + 6;
        int j;
        String as[] = new String[j = readUnsignedShort(i)];
        if(j > 0)
        {
            char ac[] = new char[d];
            for(int k = 0; k < j; k++)
            {
                i += 2;
                as[k] = readClass(i, ac);
            }

        }
        return as;
    }

    void a(ClassWriter classwriter)
    {
        char ac[] = new char[d];
        int i;
        Item aitem[] = new Item[i = a.length];
        for(int j = 1; j < i; j++)
        {
            int l = a[j];
            byte byte0 = b[l - 1];
            Item item = new Item(j);
            switch(byte0)
            {
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
                int i1 = a[readUnsignedShort(l + 2)];
                item.a(byte0, readClass(l, ac), readUTF8(i1, ac), readUTF8(i1 + 2, ac));
                break;

            case 3: // '\003'
                item.a(readInt(l));
                break;

            case 4: // '\004'
                item.a(Float.intBitsToFloat(readInt(l)));
                break;

            case 12: // '\f'
                item.a(byte0, readUTF8(l, ac), readUTF8(l + 2, ac), null);
                break;

            case 5: // '\005'
                item.a(readLong(l));
                j++;
                break;

            case 6: // '\006'
                item.a(Double.longBitsToDouble(readLong(l)));
                j++;
                break;

            case 1: // '\001'
                String s;
                if((s = c[j]) == null)
                {
                    l = a[j];
                    s = c[j] = a(l + 2, readUnsignedShort(l), ac);
                }
                item.a(byte0, s, null, null);
                break;

            case 15: // '\017'
                int l1 = a[readUnsignedShort(l + 1)];
                int j1 = a[readUnsignedShort(l1 + 2)];
                item.a(20 + readByte(l), readClass(l1, ac), readUTF8(j1, ac), readUTF8(j1 + 2, ac));
                break;

            case 18: // '\022'
                if(classwriter.A == null)
                    a(classwriter, aitem, ac);
                int k1 = a[readUnsignedShort(l + 2)];
                item.a(readUTF8(k1, ac), readUTF8(k1 + 2, ac), readUnsignedShort(l));
                break;

            case 2: // '\002'
            case 7: // '\007'
            case 8: // '\b'
            case 13: // '\r'
            case 14: // '\016'
            case 16: // '\020'
            case 17: // '\021'
            default:
                item.a(byte0, readUTF8(l, ac), null, null);
                break;
            }
            int i2 = item.j % i;
            item.k = aitem[i2];
            aitem[i2] = item;
        }

        int k = a[1] - 1;
        classwriter.d.putByteArray(b, k, header - k);
        classwriter.e = aitem;
        classwriter.f = (int)(0.75D * (double)i);
        classwriter.c = i;
    }

    private void a(ClassWriter classwriter, Item aitem[], char ac[])
    {
        int i = a();
        boolean flag = false;
        int k = readUnsignedShort(i);
        do
        {
            if(k <= 0)
                break;
            String s = readUTF8(i + 2, ac);
            if("BootstrapMethods".equals(s))
            {
                flag = true;
                break;
            }
            i += 6 + readInt(i + 4);
            k--;
        } while(true);
        if(!flag)
            return;
        k = readUnsignedShort(i + 8);
        int l = 0;
        int j = i + 10;
        for(; l < k; l++)
        {
            int i1 = j - i - 10;
            int j1 = readConst(readUnsignedShort(j), ac).hashCode();
            for(int k1 = readUnsignedShort(j + 2); k1 > 0; k1--)
            {
                j1 ^= readConst(readUnsignedShort(j + 4), ac).hashCode();
                j += 2;
            }

            j += 4;
            Item item;
            (item = new Item(l)).a(i1, j1 & 0x7fffffff);
            i1 = item.j % aitem.length;
            item.k = aitem[i1];
            aitem[i1] = item;
        }

        l = readInt(i + 4);
        ByteVector bytevector;
        (bytevector = new ByteVector(l + 62)).putByteArray(b, i + 10, l - 2);
        classwriter.z = k;
        classwriter.A = bytevector;
    }

    public ClassReader(InputStream inputstream)
        throws IOException
    {
        this(a(inputstream, false));
    }

    public ClassReader(String s)
        throws IOException
    {
        this(a(ClassLoader.getSystemResourceAsStream(s.replace('.', '/') + ".class"), true));
    }

    private static byte[] a(InputStream inputstream, boolean flag)
        throws IOException
    {
        if(inputstream == null)
            throw new IOException("Class not found");
        byte abyte0[];
        int i;
        abyte0 = new byte[inputstream.available()];
        i = 0;
_L4:
        int j;
        if((j = inputstream.read(abyte0, i, abyte0.length - i)) != -1) goto _L2; else goto _L1
_L1:
        if(i < abyte0.length)
        {
            j = new byte[i];
            System.arraycopy(abyte0, 0, j, 0, i);
            abyte0 = j;
        }
        j = abyte0;
        if(flag)
            inputstream.close();
        return j;
_L2:
        if((i += j) != abyte0.length) goto _L4; else goto _L3
_L3:
        byte abyte1[];
        if((j = inputstream.read()) >= 0)
            break MISSING_BLOCK_LABEL_112;
        abyte1 = abyte0;
        if(flag)
            inputstream.close();
        return abyte1;
        byte abyte2[] = new byte[abyte0.length + 1000];
        System.arraycopy(abyte0, 0, abyte2, 0, i);
        abyte2[i++] = (byte)j;
        abyte0 = abyte2;
          goto _L4
        Exception exception;
        exception;
        if(flag)
            inputstream.close();
        throw exception;
    }

    public void accept(ClassVisitor classvisitor, int i)
    {
        accept(classvisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classvisitor, Attribute aattribute[], int i)
    {
        int j = header;
        char ac[] = new char[d];
        Context context;
        (context = new Context()).a = aattribute;
        context.b = i;
        context.c = ac;
        int k = readUnsignedShort(j);
        String s = readClass(j + 2, ac);
        String s1 = readClass(j + 4, ac);
        String as[] = new String[readUnsignedShort(j + 6)];
        j += 8;
        for(int l = 0; l < as.length; l++)
        {
            as[l] = readClass(j, ac);
            j += 2;
        }

        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        String s7 = null;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        Attribute attribute = null;
        j = a();
        for(int j2 = readUnsignedShort(j); j2 > 0; j2--)
        {
            Object obj = readUTF8(j + 2, ac);
            if("SourceFile".equals(obj))
                s3 = readUTF8(j + 8, ac);
            else
            if("InnerClasses".equals(obj))
                i2 = j + 8;
            else
            if("EnclosingMethod".equals(obj))
            {
                s5 = readClass(j + 8, ac);
                if((obj = readUnsignedShort(j + 10)) != 0)
                {
                    s6 = readUTF8(a[obj], ac);
                    s7 = readUTF8(a[obj] + 2, ac);
                }
            } else
            if("Signature".equals(obj))
                s2 = readUTF8(j + 8, ac);
            else
            if("RuntimeVisibleAnnotations".equals(obj))
                i1 = j + 8;
            else
            if("RuntimeVisibleTypeAnnotations".equals(obj))
                k1 = j + 8;
            else
            if("Deprecated".equals(obj))
                k |= 0x20000;
            else
            if("Synthetic".equals(obj))
                k |= 0x41000;
            else
            if("SourceDebugExtension".equals(obj))
            {
                obj = readInt(j + 4);
                s4 = a(j + 8, ((int) (obj)), new char[obj]);
            } else
            if("RuntimeInvisibleAnnotations".equals(obj))
                j1 = j + 8;
            else
            if("RuntimeInvisibleTypeAnnotations".equals(obj))
                l1 = j + 8;
            else
            if("BootstrapMethods".equals(obj))
            {
                obj = new int[readUnsignedShort(j + 8)];
                int k5 = 0;
                int l5 = j + 10;
                for(; k5 < obj.length; k5++)
                {
                    obj[k5] = l5;
                    l5 += 2 + readUnsignedShort(l5 + 2) << 1;
                }

                context.d = ((int []) (obj));
            } else
            if((obj = a(aattribute, ((String) (obj)), j + 8, readInt(j + 4), ac, -1, null)) != null)
            {
                obj.a = attribute;
                attribute = ((Attribute) (obj));
            }
            j += 6 + readInt(j + 4);
        }

        classvisitor.visit(readInt(a[1] - 7), k, s, s2, s1, as);
        if((i & 2) == 0 && (s3 != null || s4 != null))
            classvisitor.visitSource(s3, s4);
        if(s5 != null)
            classvisitor.visitOuterClass(s5, s6, s7);
        if(i1 != 0)
        {
            int k2 = readUnsignedShort(i1);
            int j4 = i1 + 2;
            for(; k2 > 0; k2--)
                j4 = a(j4 + 2, ac, true, classvisitor.visitAnnotation(readUTF8(j4, ac), true));

        }
        if(j1 != 0)
        {
            int l2 = readUnsignedShort(j1);
            int k4 = j1 + 2;
            for(; l2 > 0; l2--)
                k4 = a(k4 + 2, ac, true, classvisitor.visitAnnotation(readUTF8(k4, ac), false));

        }
        if(k1 != 0)
        {
            int i3 = readUnsignedShort(k1);
            int l4 = k1 + 2;
            for(; i3 > 0; i3--)
            {
                l4 = a(context, l4);
                l4 = a(l4 + 2, ac, true, classvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(l4, ac), true));
            }

        }
        if(l1 != 0)
        {
            int j3 = readUnsignedShort(l1);
            int i5 = l1 + 2;
            for(; j3 > 0; j3--)
            {
                i5 = a(context, i5);
                i5 = a(i5 + 2, ac, true, classvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(i5, ac), false));
            }

        }
        Attribute attribute1;
        for(; attribute != null; attribute = attribute1)
        {
            attribute1 = attribute.a;
            attribute.a = null;
            classvisitor.visitAttribute(attribute);
        }

        if(i2 != 0)
        {
            int k3 = i2 + 2;
            for(int j5 = readUnsignedShort(i2); j5 > 0; j5--)
            {
                classvisitor.visitInnerClass(readClass(k3, ac), readClass(k3 + 2, ac), readUTF8(k3 + 4, ac), readUnsignedShort(k3 + 6));
                k3 += 8;
            }

        }
        j = header + 10 + 2 * as.length;
        for(int l3 = readUnsignedShort(j - 2); l3 > 0; l3--)
            j = a(classvisitor, context, j);

        j += 2;
        for(int i4 = readUnsignedShort(j - 2); i4 > 0; i4--)
            j = b(classvisitor, context, j);

        classvisitor.visitEnd();
    }

    private int a(ClassVisitor classvisitor, Context context, int i)
    {
        char ac[] = context.c;
        int j = readUnsignedShort(i);
        String s = readUTF8(i + 2, ac);
        String s1 = readUTF8(i + 4, ac);
        i += 6;
        String s2 = null;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        Object obj = null;
        Attribute attribute = null;
        for(int k1 = readUnsignedShort(i); k1 > 0; k1--)
        {
            String s3 = readUTF8(i + 2, ac);
            int l2;
            Attribute attribute2;
            if("ConstantValue".equals(s3))
                obj = (l2 = readUnsignedShort(i + 8)) != 0 ? readConst(l2, ac) : null;
            else
            if("Signature".equals(s3))
                s2 = readUTF8(i + 8, ac);
            else
            if("Deprecated".equals(s3))
                j |= 0x20000;
            else
            if("Synthetic".equals(s3))
                j |= 0x41000;
            else
            if("RuntimeVisibleAnnotations".equals(s3))
                k = i + 8;
            else
            if("RuntimeVisibleTypeAnnotations".equals(s3))
                i1 = i + 8;
            else
            if("RuntimeInvisibleAnnotations".equals(s3))
                l = i + 8;
            else
            if("RuntimeInvisibleTypeAnnotations".equals(s3))
                j1 = i + 8;
            else
            if((attribute2 = a(context.a, s3, i + 8, readInt(i + 4), ac, -1, null)) != null)
            {
                attribute2.a = attribute;
                attribute = attribute2;
            }
            i += 6 + readInt(i + 4);
        }

        i += 2;
        FieldVisitor fieldvisitor;
        if((fieldvisitor = classvisitor.visitField(j, s, s1, s2, obj)) == null)
            return i;
        if(k != 0)
        {
            int l1 = readUnsignedShort(k);
            int i3 = k + 2;
            for(; l1 > 0; l1--)
                i3 = a(i3 + 2, ac, true, fieldvisitor.visitAnnotation(readUTF8(i3, ac), true));

        }
        if(l != 0)
        {
            int i2 = readUnsignedShort(l);
            int j3 = l + 2;
            for(; i2 > 0; i2--)
                j3 = a(j3 + 2, ac, true, fieldvisitor.visitAnnotation(readUTF8(j3, ac), false));

        }
        if(i1 != 0)
        {
            int j2 = readUnsignedShort(i1);
            int k3 = i1 + 2;
            for(; j2 > 0; j2--)
            {
                k3 = a(context, k3);
                k3 = a(k3 + 2, ac, true, fieldvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(k3, ac), true));
            }

        }
        if(j1 != 0)
        {
            int k2 = readUnsignedShort(j1);
            int l3 = j1 + 2;
            for(; k2 > 0; k2--)
            {
                l3 = a(context, l3);
                l3 = a(l3 + 2, ac, true, fieldvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(l3, ac), false));
            }

        }
        Attribute attribute1;
        for(; attribute != null; attribute = attribute1)
        {
            attribute1 = attribute.a;
            attribute.a = null;
            fieldvisitor.visitAttribute(attribute);
        }

        fieldvisitor.visitEnd();
        return i;
    }

    private int b(ClassVisitor classvisitor, Context context, int i)
    {
        char ac[] = context.c;
        context.e = readUnsignedShort(i);
        context.f = readUTF8(i + 2, ac);
        context.g = readUTF8(i + 4, ac);
        i += 6;
        int j = 0;
        int k = 0;
        String as[] = null;
        String s = null;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        int l2 = i;
        Attribute attribute = null;
        for(int i3 = readUnsignedShort(i); i3 > 0; i3--)
        {
            String s1 = readUTF8(i + 2, ac);
            Attribute attribute2;
            if("Code".equals(s1))
            {
                if((context.b & 1) == 0)
                    j = i + 8;
            } else
            if("Exceptions".equals(s1))
            {
                as = new String[readUnsignedShort(i + 8)];
                k = i + 10;
                for(int k4 = 0; k4 < as.length; k4++)
                {
                    as[k4] = readClass(k, ac);
                    k += 2;
                }

            } else
            if("Signature".equals(s1))
                s = readUTF8(i + 8, ac);
            else
            if("Deprecated".equals(s1))
                context.e |= 0x20000;
            else
            if("RuntimeVisibleAnnotations".equals(s1))
                i1 = i + 8;
            else
            if("RuntimeVisibleTypeAnnotations".equals(s1))
                k1 = i + 8;
            else
            if("AnnotationDefault".equals(s1))
                i2 = i + 8;
            else
            if("Synthetic".equals(s1))
                context.e |= 0x41000;
            else
            if("RuntimeInvisibleAnnotations".equals(s1))
                j1 = i + 8;
            else
            if("RuntimeInvisibleTypeAnnotations".equals(s1))
                l1 = i + 8;
            else
            if("RuntimeVisibleParameterAnnotations".equals(s1))
                j2 = i + 8;
            else
            if("RuntimeInvisibleParameterAnnotations".equals(s1))
                k2 = i + 8;
            else
            if("MethodParameters".equals(s1))
                l = i + 8;
            else
            if((attribute2 = a(context.a, s1, i + 8, readInt(i + 4), ac, -1, null)) != null)
            {
                attribute2.a = attribute;
                attribute = attribute2;
            }
            i += 6 + readInt(i + 4);
        }

        i += 2;
        MethodVisitor methodvisitor;
        if((methodvisitor = classvisitor.visitMethod(context.e, context.f, context.g, s, as)) == null)
            return i;
        MethodWriter methodwriter;
        if((methodvisitor instanceof MethodWriter) && (methodwriter = (MethodWriter)methodvisitor).b.M == this && s == methodwriter.g)
        {
            boolean flag = false;
            if(as == null)
                flag = methodwriter.j == 0;
            else
            if(as.length == methodwriter.j)
            {
                flag = true;
                classvisitor = as.length - 1;
                do
                {
                    if(classvisitor < 0)
                        break;
                    k -= 2;
                    if(methodwriter.k[classvisitor] != readUnsignedShort(k))
                    {
                        flag = false;
                        break;
                    }
                    classvisitor--;
                } while(true);
            }
            if(flag)
            {
                methodwriter.h = l2;
                methodwriter.i = i - l2;
                return i;
            }
        }
        if(l != 0)
        {
            int j3 = b[l] & 0xff;
            for(int l4 = l + 1; j3 > 0; l4 += 4)
            {
                methodvisitor.visitParameter(readUTF8(l4, ac), readUnsignedShort(l4 + 2));
                j3--;
            }

        }
        if(i2 != 0)
        {
            AnnotationVisitor annotationvisitor = methodvisitor.visitAnnotationDefault();
            a(i2, ac, ((String) (null)), annotationvisitor);
            if(annotationvisitor != null)
                annotationvisitor.visitEnd();
        }
        if(i1 != 0)
        {
            int k3 = readUnsignedShort(i1);
            int i5 = i1 + 2;
            for(; k3 > 0; k3--)
                i5 = a(i5 + 2, ac, true, methodvisitor.visitAnnotation(readUTF8(i5, ac), true));

        }
        if(j1 != 0)
        {
            int l3 = readUnsignedShort(j1);
            int j5 = j1 + 2;
            for(; l3 > 0; l3--)
                j5 = a(j5 + 2, ac, true, methodvisitor.visitAnnotation(readUTF8(j5, ac), false));

        }
        if(k1 != 0)
        {
            int i4 = readUnsignedShort(k1);
            int k5 = k1 + 2;
            for(; i4 > 0; i4--)
            {
                k5 = a(context, k5);
                k5 = a(k5 + 2, ac, true, methodvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(k5, ac), true));
            }

        }
        if(l1 != 0)
        {
            int j4 = readUnsignedShort(l1);
            int l5 = l1 + 2;
            for(; j4 > 0; j4--)
            {
                l5 = a(context, l5);
                l5 = a(l5 + 2, ac, true, methodvisitor.visitTypeAnnotation(context.i, context.j, readUTF8(l5, ac), false));
            }

        }
        if(j2 != 0)
            b(methodvisitor, context, j2, true);
        if(k2 != 0)
            b(methodvisitor, context, k2, false);
        Attribute attribute1;
        for(; attribute != null; attribute = attribute1)
        {
            attribute1 = attribute.a;
            attribute.a = null;
            methodvisitor.visitAttribute(attribute);
        }

        if(j != 0)
        {
            methodvisitor.visitCode();
            a(methodvisitor, context, j);
        }
        methodvisitor.visitEnd();
        return i;
    }

    private void a(MethodVisitor methodvisitor, Context context, int i)
    {
        byte abyte0[] = b;
        char ac[] = context.c;
        int j = readUnsignedShort(i);
        int k = readUnsignedShort(i + 2);
        int l = readInt(i + 4);
        int i1 = i += 8;
        int j1 = i + l;
        Label alabel[] = context.h = new Label[l + 2];
        readLabel(l + 1, alabel);
        do
        {
            if(i >= j1)
                break;
            int k1 = i - i1;
            int i2 = abyte0[i] & 0xff;
            switch(ClassWriter.a[i2])
            {
            case 0: // '\0'
            case 4: // '\004'
                i++;
                break;

            case 9: // '\t'
                readLabel(k1 + readShort(i + 1), alabel);
                i += 3;
                break;

            case 10: // '\n'
                readLabel(k1 + readInt(i + 1), alabel);
                i += 5;
                break;

            case 17: // '\021'
                if((i2 = abyte0[i + 1] & 0xff) == 132)
                    i += 6;
                else
                    i += 4;
                break;

            case 14: // '\016'
                i = (i + 4) - (k1 & 3);
                readLabel(k1 + readInt(i), alabel);
                for(int j2 = (readInt(i + 8) - readInt(i + 4)) + 1; j2 > 0; j2--)
                {
                    readLabel(k1 + readInt(i + 12), alabel);
                    i += 4;
                }

                i += 12;
                break;

            case 15: // '\017'
                i = (i + 4) - (k1 & 3);
                readLabel(k1 + readInt(i), alabel);
                for(int k2 = readInt(i + 4); k2 > 0; k2--)
                {
                    readLabel(k1 + readInt(i + 12), alabel);
                    i += 8;
                }

                i += 8;
                break;

            case 1: // '\001'
            case 3: // '\003'
            case 11: // '\013'
                i += 2;
                break;

            case 2: // '\002'
            case 5: // '\005'
            case 6: // '\006'
            case 12: // '\f'
            case 13: // '\r'
                i += 3;
                break;

            case 7: // '\007'
            case 8: // '\b'
                i += 5;
                break;

            case 16: // '\020'
            default:
                i += 4;
                break;
            }
        } while(true);
        for(int l1 = readUnsignedShort(i); l1 > 0; l1--)
        {
            Label label = readLabel(readUnsignedShort(i + 2), alabel);
            Label label1 = readLabel(readUnsignedShort(i + 4), alabel);
            Label label2 = readLabel(readUnsignedShort(i + 6), alabel);
            String s = readUTF8(a[readUnsignedShort(i + 8)], ac);
            methodvisitor.visitTryCatchBlock(label, label1, label2, s);
            i += 8;
        }

        i += 2;
        int ai[] = null;
        int ai1[] = null;
        int l2 = 0;
        int i3 = 0;
        int j3 = -1;
        int k3 = -1;
        int l3 = 0;
        int i4 = 0;
        boolean flag = true;
        boolean flag1 = (context.b & 8) != 0;
        int j4 = 0;
        int k4 = 0;
        int i5 = 0;
        Context context1 = null;
        Attribute attribute = null;
        for(int j5 = readUnsignedShort(i); j5 > 0; j5--)
        {
            String s3 = readUTF8(i + 2, ac);
            if("LocalVariableTable".equals(s3))
            {
                if((context.b & 2) == 0)
                {
                    l3 = i + 8;
                    int j8 = readUnsignedShort(i + 8);
                    int k9 = i;
                    for(; j8 > 0; j8--)
                    {
                        int l11 = readUnsignedShort(k9 + 10);
                        if(alabel[l11] == null)
                            readLabel(l11, alabel).a |= 1;
                        l11 += readUnsignedShort(k9 + 12);
                        if(alabel[l11] == null)
                            readLabel(l11, alabel).a |= 1;
                        k9 += 10;
                    }

                }
            } else
            if("LocalVariableTypeTable".equals(s3))
                i4 = i + 8;
            else
            if("LineNumberTable".equals(s3))
            {
                if((context.b & 2) == 0)
                {
                    int k8 = readUnsignedShort(i + 8);
                    int l9 = i;
                    for(; k8 > 0; k8--)
                    {
                        int i12 = readUnsignedShort(l9 + 10);
                        if(alabel[i12] == null)
                            readLabel(i12, alabel).a |= 1;
                        alabel[i12].b = readUnsignedShort(l9 + 12);
                        l9 += 4;
                    }

                }
            } else
            if("RuntimeVisibleTypeAnnotations".equals(s3))
                j3 = (ai = a(methodvisitor, context, i + 8, true)).length != 0 && readByte(ai[0]) >= 67 ? readUnsignedShort(ai[0] + 1) : -1;
            else
            if("RuntimeInvisibleTypeAnnotations".equals(s3))
                k3 = (ai1 = a(methodvisitor, context, i + 8, false)).length != 0 && readByte(ai1[0]) >= 67 ? readUnsignedShort(ai1[0] + 1) : -1;
            else
            if("StackMapTable".equals(s3))
            {
                if((context.b & 4) == 0)
                {
                    j4 = i + 10;
                    k4 = readInt(i + 4);
                    i5 = readUnsignedShort(i + 8);
                }
            } else
            if("StackMap".equals(s3))
            {
                if((context.b & 4) == 0)
                {
                    flag = false;
                    j4 = i + 10;
                    k4 = readInt(i + 4);
                    i5 = readUnsignedShort(i + 8);
                }
            } else
            {
                for(int l8 = 0; l8 < context.a.length; l8++)
                {
                    Attribute attribute2;
                    if(context.a[l8].type.equals(s3) && (attribute2 = context.a[l8].read(this, i + 8, readInt(i + 4), ac, i1 - 8, alabel)) != null)
                    {
                        attribute2.a = attribute;
                        attribute = attribute2;
                    }
                }

            }
            i += 6 + readInt(i + 4);
        }

        if(j4 != 0)
        {
            (context1 = context).o = -1;
            context1.p = 0;
            context1.q = 0;
            context1.r = 0;
            context1.t = 0;
            context1.s = new Object[k];
            context1.u = new Object[j];
            if(flag1)
                a(context);
            for(int k5 = j4; k5 < (j4 + k4) - 2; k5++)
            {
                int k6;
                if(abyte0[k5] == 8 && (k6 = readUnsignedShort(k5 + 1)) >= 0 && k6 < l && (abyte0[i1 + k6] & 0xff) == 187)
                    readLabel(k6, alabel);
            }

        }
        for(i = i1; i < j1;)
        {
            int l5 = i - i1;
            Label label3;
            if((label3 = alabel[l5]) != null)
            {
                methodvisitor.visitLabel(label3);
                if((context.b & 2) == 0 && label3.b > 0)
                    methodvisitor.visitLineNumber(label3.b, label3);
            }
            while(context1 != null && (context1.o == l5 || context1.o == -1)) 
            {
                if(context1.o != -1)
                    if(!flag || flag1)
                        methodvisitor.visitFrame(-1, context1.q, context1.s, context1.t, context1.u);
                    else
                        methodvisitor.visitFrame(context1.p, context1.r, context1.s, context1.t, context1.u);
                if(i5 > 0)
                {
                    j4 = a(j4, flag, flag1, context1);
                    i5--;
                } else
                {
                    context1 = null;
                }
            }
            int i9 = abyte0[i] & 0xff;
            switch(ClassWriter.a[i9])
            {
            case 0: // '\0'
                methodvisitor.visitInsn(i9);
                i++;
                break;

            case 4: // '\004'
                if(i9 > 54)
                {
                    i9 -= 59;
                    methodvisitor.visitVarInsn(54 + (i9 >> 2), i9 & 3);
                } else
                {
                    i9 -= 26;
                    methodvisitor.visitVarInsn(21 + (i9 >> 2), i9 & 3);
                }
                i++;
                break;

            case 9: // '\t'
                methodvisitor.visitJumpInsn(i9, alabel[l5 + readShort(i + 1)]);
                i += 3;
                break;

            case 10: // '\n'
                methodvisitor.visitJumpInsn(i9 - 33, alabel[l5 + readInt(i + 1)]);
                i += 5;
                break;

            case 17: // '\021'
                if((i9 = abyte0[i + 1] & 0xff) == 132)
                {
                    methodvisitor.visitIincInsn(readUnsignedShort(i + 2), readShort(i + 4));
                    i += 6;
                } else
                {
                    methodvisitor.visitVarInsn(i9, readUnsignedShort(i + 2));
                    i += 4;
                }
                break;

            case 14: // '\016'
                i = (i + 4) - (l5 & 3);
                int i10 = l5 + readInt(i);
                int j12 = readInt(i + 4);
                int l4;
                Label alabel1[] = new Label[((l4 = readInt(i + 8)) - j12) + 1];
                i += 12;
                for(int l6 = 0; l6 < alabel1.length; l6++)
                {
                    alabel1[l6] = alabel[l5 + readInt(i)];
                    i += 4;
                }

                methodvisitor.visitTableSwitchInsn(j12, l4, alabel[i10], alabel1);
                break;

            case 15: // '\017'
                i = (i + 4) - (l5 & 3);
                int j10 = l5 + readInt(i);
                int k12;
                int ai2[] = new int[k12 = readInt(i + 4)];
                Label alabel2[] = new Label[k12];
                i += 8;
                for(int i7 = 0; i7 < k12; i7++)
                {
                    ai2[i7] = readInt(i);
                    alabel2[i7] = alabel[l5 + readInt(i + 4)];
                    i += 8;
                }

                methodvisitor.visitLookupSwitchInsn(alabel[j10], ai2, alabel2);
                break;

            case 3: // '\003'
                methodvisitor.visitVarInsn(i9, abyte0[i + 1] & 0xff);
                i += 2;
                break;

            case 1: // '\001'
                methodvisitor.visitIntInsn(i9, abyte0[i + 1]);
                i += 2;
                break;

            case 2: // '\002'
                methodvisitor.visitIntInsn(i9, readShort(i + 1));
                i += 3;
                break;

            case 11: // '\013'
                methodvisitor.visitLdcInsn(readConst(abyte0[i + 1] & 0xff, ac));
                i += 2;
                break;

            case 12: // '\f'
                methodvisitor.visitLdcInsn(readConst(readUnsignedShort(i + 1), ac));
                i += 3;
                break;

            case 6: // '\006'
            case 7: // '\007'
                int k10 = a[readUnsignedShort(i + 1)];
                boolean flag2 = abyte0[k10 - 1] == 11;
                String s1 = readClass(k10, ac);
                k10 = a[readUnsignedShort(k10 + 2)];
                String s6 = readUTF8(k10, ac);
                String s4 = readUTF8(k10 + 2, ac);
                if(i9 < 182)
                    methodvisitor.visitFieldInsn(i9, s1, s6, s4);
                else
                    methodvisitor.visitMethodInsn(i9, s1, s6, s4, flag2);
                if(i9 == 185)
                    i += 5;
                else
                    i += 3;
                break;

            case 8: // '\b'
                int l10 = a[readUnsignedShort(i + 1)];
                int l12 = context.d[readUnsignedShort(l10)];
                Handle handle = (Handle)readConst(readUnsignedShort(l12), ac);
                int j13;
                Object aobj[] = new Object[j13 = readUnsignedShort(l12 + 2)];
                l12 += 4;
                for(i9 = 0; i9 < j13; i9++)
                {
                    aobj[i9] = readConst(readUnsignedShort(l12), ac);
                    l12 += 2;
                }

                l10 = a[readUnsignedShort(l10 + 2)];
                i9 = readUTF8(l10, ac);
                String s5 = readUTF8(l10 + 2, ac);
                methodvisitor.visitInvokeDynamicInsn(i9, s5, handle, aobj);
                i += 5;
                break;

            case 5: // '\005'
                methodvisitor.visitTypeInsn(i9, readClass(i + 1, ac));
                i += 3;
                break;

            case 13: // '\r'
                methodvisitor.visitIincInsn(abyte0[i + 1] & 0xff, abyte0[i + 2]);
                i += 3;
                break;

            case 16: // '\020'
            default:
                methodvisitor.visitMultiANewArrayInsn(readClass(i + 1, ac), abyte0[i + 3] & 0xff);
                i += 4;
                break;
            }
            for(; ai != null && l2 < ai.length && j3 <= l5; j3 = ++l2 < ai.length && readByte(ai[l2]) >= 67 ? readUnsignedShort(ai[l2] + 1) : -1)
                if(j3 == l5)
                {
                    int i11 = a(context, ai[l2]);
                    a(i11 + 2, ac, true, methodvisitor.visitInsnAnnotation(context.i, context.j, readUTF8(i11, ac), true));
                }

            while(ai1 != null && i3 < ai1.length && k3 <= l5) 
            {
                if(k3 == l5)
                {
                    int j11 = a(context, ai1[i3]);
                    a(j11 + 2, ac, true, methodvisitor.visitInsnAnnotation(context.i, context.j, readUTF8(j11, ac), false));
                }
                k3 = ++i3 < ai1.length && readByte(ai1[i3]) >= 67 ? readUnsignedShort(ai1[i3] + 1) : -1;
            }
        }

        if(alabel[l] != null)
            methodvisitor.visitLabel(alabel[l]);
        if((context.b & 2) == 0 && l3 != 0)
        {
            int ai3[] = null;
            if(i4 != 0)
            {
                i = i4 + 2;
                for(int j7 = (ai3 = new int[readUnsignedShort(i4) * 3]).length; j7 > 0;)
                {
                    ai3[--j7] = i + 6;
                    ai3[--j7] = readUnsignedShort(i + 8);
                    ai3[--j7] = readUnsignedShort(i);
                    i += 10;
                }

            }
            i = l3 + 2;
            for(int k7 = readUnsignedShort(l3); k7 > 0; k7--)
            {
                int j9 = readUnsignedShort(i);
                int k11 = readUnsignedShort(i + 2);
                int i13 = readUnsignedShort(i + 8);
                String s2 = null;
                if(ai3 != null)
                {
                    int k13 = 0;
                    do
                    {
                        if(k13 >= ai3.length)
                            break;
                        if(ai3[k13] == j9 && ai3[k13 + 1] == i13)
                        {
                            s2 = readUTF8(ai3[k13 + 2], ac);
                            break;
                        }
                        k13 += 3;
                    } while(true);
                }
                methodvisitor.visitLocalVariable(readUTF8(i + 4, ac), readUTF8(i + 6, ac), s2, alabel[j9], alabel[j9 + k11], i13);
                i += 10;
            }

        }
        if(ai != null)
        {
            for(int i6 = 0; i6 < ai.length; i6++)
                if(readByte(ai[i6]) >> 1 == 32)
                {
                    int l7 = a(context, ai[i6]);
                    a(l7 + 2, ac, true, methodvisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, readUTF8(l7, ac), true));
                }

        }
        if(ai1 != null)
        {
            for(int j6 = 0; j6 < ai1.length; j6++)
                if(readByte(ai1[j6]) >> 1 == 32)
                {
                    int i8 = a(context, ai1[j6]);
                    a(i8 + 2, ac, true, methodvisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, readUTF8(i8, ac), false));
                }

        }
        Attribute attribute1;
        for(; attribute != null; attribute = attribute1)
        {
            attribute1 = attribute.a;
            attribute.a = null;
            methodvisitor.visitAttribute(attribute);
        }

        methodvisitor.visitMaxs(j, k);
    }

    private int[] a(MethodVisitor methodvisitor, Context context, int i, boolean flag)
    {
        char ac[] = context.c;
        int ai[] = new int[readUnsignedShort(i)];
        i += 2;
        for(int j = 0; j < ai.length; j++)
        {
            ai[j] = i;
            int k;
            switch((k = readInt(i)) >>> 24)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 22: // '\026'
                i += 2;
                break;

            case 19: // '\023'
            case 20: // '\024'
            case 21: // '\025'
                i++;
                break;

            case 64: // '@'
            case 65: // 'A'
                for(int l = readUnsignedShort(i + 1); l > 0; l--)
                {
                    int j1 = readUnsignedShort(i + 3);
                    int k1 = readUnsignedShort(i + 5);
                    readLabel(j1, context.h);
                    readLabel(j1 + k1, context.h);
                    i += 6;
                }

                i += 3;
                break;

            case 71: // 'G'
            case 72: // 'H'
            case 73: // 'I'
            case 74: // 'J'
            case 75: // 'K'
                i += 4;
                break;

            default:
                i += 3;
                break;
            }
            int i1 = readByte(i);
            if(k >>> 24 == 66)
            {
                TypePath typepath = i1 != 0 ? new TypePath(b, i) : null;
                i += 1 + 2 * i1;
                i = a(i + 2, ac, true, methodvisitor.visitTryCatchAnnotation(k, typepath, readUTF8(i, ac), flag));
            } else
            {
                i = a(i + 3 + 2 * i1, ac, true, ((AnnotationVisitor) (null)));
            }
        }

        return ai;
    }

    private int a(Context context, int i)
    {
        int j;
        switch((j = readInt(i)) >>> 24)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 22: // '\026'
            j &= 0xffff0000;
            i += 2;
            break;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
            j &= 0xff000000;
            i++;
            break;

        case 64: // '@'
        case 65: // 'A'
            j &= 0xff000000;
            int k = readUnsignedShort(i + 1);
            context.l = new Label[k];
            context.m = new Label[k];
            context.n = new int[k];
            i += 3;
            for(int i1 = 0; i1 < k; i1++)
            {
                int j1 = readUnsignedShort(i);
                int k1 = readUnsignedShort(i + 2);
                context.l[i1] = readLabel(j1, context.h);
                context.m[i1] = readLabel(j1 + k1, context.h);
                context.n[i1] = readUnsignedShort(i + 4);
                i += 6;
            }

            break;

        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
            j &= 0xff0000ff;
            i += 4;
            break;

        default:
            j &= j >>> 24 >= 67 ? 0xff000000 : -256;
            i += 3;
            break;
        }
        int l = readByte(i);
        context.i = j;
        context.j = l != 0 ? new TypePath(b, i) : null;
        return i + 1 + 2 * l;
    }

    private void b(MethodVisitor methodvisitor, Context context, int i, boolean flag)
    {
        int j = b[i++] & 0xff;
        int k = Type.getArgumentTypes(context.g).length - j;
        int l;
        for(l = 0; l < k; l++)
        {
            AnnotationVisitor annotationvisitor;
            if((annotationvisitor = methodvisitor.visitParameterAnnotation(l, "Ljava/lang/Synthetic;", false)) != null)
                annotationvisitor.visitEnd();
        }

        context = context.c;
        for(; l < j + k; l++)
        {
            int i1 = readUnsignedShort(i);
            i += 2;
            for(; i1 > 0; i1--)
            {
                AnnotationVisitor annotationvisitor1 = methodvisitor.visitParameterAnnotation(l, readUTF8(i, context), flag);
                i = a(i + 2, context, true, annotationvisitor1);
            }

        }

    }

    private int a(int i, char ac[], boolean flag, AnnotationVisitor annotationvisitor)
    {
        int j = readUnsignedShort(i);
        i += 2;
        if(flag)
            for(; j > 0; j--)
                i = a(i + 2, ac, readUTF8(i, ac), annotationvisitor);

        else
            for(; j > 0; j--)
                i = a(i, ac, ((String) (null)), annotationvisitor);

        if(annotationvisitor != null)
            annotationvisitor.visitEnd();
        return i;
    }

    private int a(int i, char ac[], String s, AnnotationVisitor annotationvisitor)
    {
        if(annotationvisitor == null)
        {
            switch(b[i] & 0xff)
            {
            case 101: // 'e'
                return i + 5;

            case 64: // '@'
                return a(i + 3, ac, true, ((AnnotationVisitor) (null)));

            case 91: // '['
                return a(i + 1, ac, false, ((AnnotationVisitor) (null)));
            }
            return i + 3;
        }
label0:
        switch(b[i++] & 0xff)
        {
        case 65: // 'A'
        case 69: // 'E'
        case 71: // 'G'
        case 72: // 'H'
        case 75: // 'K'
        case 76: // 'L'
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
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        case 97: // 'a'
        case 98: // 'b'
        case 100: // 'd'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 108: // 'l'
        case 109: // 'm'
        case 110: // 'n'
        case 111: // 'o'
        case 112: // 'p'
        case 113: // 'q'
        case 114: // 'r'
        default:
            break;

        case 68: // 'D'
        case 70: // 'F'
        case 73: // 'I'
        case 74: // 'J'
            annotationvisitor.visit(s, readConst(readUnsignedShort(i), ac));
            i += 2;
            break;

        case 66: // 'B'
            annotationvisitor.visit(s, new Byte((byte)readInt(a[readUnsignedShort(i)])));
            i += 2;
            break;

        case 90: // 'Z'
            annotationvisitor.visit(s, readInt(a[readUnsignedShort(i)]) != 0 ? ((Object) (Boolean.TRUE)) : ((Object) (Boolean.FALSE)));
            i += 2;
            break;

        case 83: // 'S'
            annotationvisitor.visit(s, new Short((short)readInt(a[readUnsignedShort(i)])));
            i += 2;
            break;

        case 67: // 'C'
            annotationvisitor.visit(s, new Character((char)readInt(a[readUnsignedShort(i)])));
            i += 2;
            break;

        case 115: // 's'
            annotationvisitor.visit(s, readUTF8(i, ac));
            i += 2;
            break;

        case 101: // 'e'
            annotationvisitor.visitEnum(s, readUTF8(i, ac), readUTF8(i + 2, ac));
            i += 4;
            break;

        case 99: // 'c'
            annotationvisitor.visit(s, Type.getType(readUTF8(i, ac)));
            i += 2;
            break;

        case 64: // '@'
            i = a(i + 2, ac, true, annotationvisitor.visitAnnotation(s, readUTF8(i, ac)));
            break;

        case 91: // '['
            int j = readUnsignedShort(i);
            i += 2;
            if(j == 0)
                return a(i - 2, ac, false, annotationvisitor.visitArray(s));
            switch(b[i++] & 0xff)
            {
            case 66: // 'B'
                ac = new byte[j];
                for(int k = 0; k < j; k++)
                {
                    ac[k] = (byte)readInt(a[readUnsignedShort(i)]);
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 90: // 'Z'
                ac = new boolean[j];
                for(int l = 0; l < j; l++)
                {
                    ac[l] = readInt(a[readUnsignedShort(i)]) == 0 ? '\0' : '\001';
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 83: // 'S'
                ac = new short[j];
                for(int i1 = 0; i1 < j; i1++)
                {
                    ac[i1] = (short)readInt(a[readUnsignedShort(i)]);
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 67: // 'C'
                ac = new char[j];
                for(int j1 = 0; j1 < j; j1++)
                {
                    ac[j1] = (char)readInt(a[readUnsignedShort(i)]);
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 73: // 'I'
                ac = new int[j];
                for(int k1 = 0; k1 < j; k1++)
                {
                    ac[k1] = readInt(a[readUnsignedShort(i)]);
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 74: // 'J'
                ac = new long[j];
                for(int l1 = 0; l1 < j; l1++)
                {
                    ac[l1] = readLong(a[readUnsignedShort(i)]);
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 70: // 'F'
                ac = new float[j];
                for(int i2 = 0; i2 < j; i2++)
                {
                    ac[i2] = Float.intBitsToFloat(readInt(a[readUnsignedShort(i)]));
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break label0;

            case 68: // 'D'
                ac = new double[j];
                for(int j2 = 0; j2 < j; j2++)
                {
                    ac[j2] = Double.longBitsToDouble(readLong(a[readUnsignedShort(i)]));
                    i += 3;
                }

                annotationvisitor.visit(s, ac);
                i--;
                break;

            case 69: // 'E'
            case 71: // 'G'
            case 72: // 'H'
            case 75: // 'K'
            case 76: // 'L'
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
                i = a(i - 3, ac, false, annotationvisitor.visitArray(s));
                break;
            }
            break;
        }
        return i;
    }

    private void a(Context context)
    {
        String s = context.g;
        Object aobj[] = context.s;
        int i = 0;
        if((context.e & 8) == 0)
            if("<init>".equals(context.f))
            {
                i++;
                aobj[0] = Opcodes.UNINITIALIZED_THIS;
            } else
            {
                i++;
                aobj[0] = readClass(header + 2, context.c);
            }
        int j = 1;
        do
        {
            int k = j;
            switch(s.charAt(j++))
            {
            case 66: // 'B'
            case 67: // 'C'
            case 73: // 'I'
            case 83: // 'S'
            case 90: // 'Z'
                aobj[i++] = Opcodes.INTEGER;
                break;

            case 70: // 'F'
                aobj[i++] = Opcodes.FLOAT;
                break;

            case 74: // 'J'
                aobj[i++] = Opcodes.LONG;
                break;

            case 68: // 'D'
                aobj[i++] = Opcodes.DOUBLE;
                break;

            case 91: // '['
                for(; s.charAt(j) == '['; j++);
                if(s.charAt(j) == 'L')
                    for(j++; s.charAt(j) != ';'; j++);
                aobj[i++] = s.substring(k, ++j);
                break;

            case 76: // 'L'
                for(; s.charAt(j) != ';'; j++);
                aobj[i++] = s.substring(k + 1, j++);
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
                context.q = i;
                return;
            }
        } while(true);
    }

    private int a(int i, boolean flag, boolean flag1, Context context)
    {
        char ac[];
        Label alabel[];
        ac = context.c;
        alabel = context.h;
        if(flag)
        {
            flag = b[i++] & 0xff;
        } else
        {
            flag = 255;
            context.o = -1;
        }
        context.r = 0;
        if(flag >= 64) goto _L2; else goto _L1
_L1:
        int j;
        j = ((flag) ? 1 : 0);
        context.p = 3;
        context.t = 0;
          goto _L3
_L2:
        if(flag >= 128) goto _L5; else goto _L4
_L4:
        j = flag - 64;
        i = a(context.u, 0, i, ac, alabel);
        context.p = 4;
        context.t = 1;
          goto _L3
_L5:
        j = readUnsignedShort(i);
        i += 2;
        if(flag != 247) goto _L7; else goto _L6
_L6:
        i = a(context.u, 0, i, ac, alabel);
        context.p = 4;
        context.t = 1;
          goto _L3
_L7:
        if(flag < 248 || flag >= 251) goto _L9; else goto _L8
_L8:
        context.p = 2;
        context.r = 251 - flag;
        context.q -= context.r;
        context.t = 0;
          goto _L3
_L9:
        if(flag != 251) goto _L11; else goto _L10
_L10:
        context.p = 3;
        context.t = 0;
          goto _L3
_L11:
        if(flag >= 255) goto _L13; else goto _L12
_L12:
        flag1 = flag1 ? ((boolean) (context.q)) : false;
        for(int k = flag - 251; k > 0; k--)
            i = a(context.s, flag1++, i, ac, alabel);

        context.p = 1;
        context.r = flag - 251;
        context.q += context.r;
        context.t = 0;
          goto _L3
_L13:
        int l;
        context.p = 0;
        flag1 = readUnsignedShort(i);
        i += 2;
        context.r = ((flag1) ? 1 : 0);
        context.q = ((flag1) ? 1 : 0);
        l = 0;
_L17:
        flag1;
        JVM INSTR ifle 401;
           goto _L14 _L15
_L14:
        break MISSING_BLOCK_LABEL_375;
_L15:
        break; /* Loop/switch isn't completed */
        i = a(context.s, l++, i, ac, alabel);
        flag1--;
        if(true) goto _L17; else goto _L16
_L16:
        flag1 = readUnsignedShort(i);
        i += 2;
        context.t = ((flag1) ? 1 : 0);
        l = 0;
_L20:
        flag1;
        JVM INSTR ifle 449;
           goto _L18 _L19
_L18:
        break MISSING_BLOCK_LABEL_423;
_L19:
        break; /* Loop/switch isn't completed */
        i = a(context.u, l++, i, ac, alabel);
        flag1--;
        if(true) goto _L20; else goto _L3
_L3:
        context.o += j + 1;
        readLabel(context.o, alabel);
        return i;
    }

    private int a(Object aobj[], int i, int j, char ac[], Label alabel[])
    {
        int k;
        switch(k = b[j++] & 0xff)
        {
        case 0: // '\0'
            aobj[i] = Opcodes.TOP;
            break;

        case 1: // '\001'
            aobj[i] = Opcodes.INTEGER;
            break;

        case 2: // '\002'
            aobj[i] = Opcodes.FLOAT;
            break;

        case 3: // '\003'
            aobj[i] = Opcodes.DOUBLE;
            break;

        case 4: // '\004'
            aobj[i] = Opcodes.LONG;
            break;

        case 5: // '\005'
            aobj[i] = Opcodes.NULL;
            break;

        case 6: // '\006'
            aobj[i] = Opcodes.UNINITIALIZED_THIS;
            break;

        case 7: // '\007'
            aobj[i] = readClass(j, ac);
            j += 2;
            break;

        default:
            aobj[i] = readLabel(readUnsignedShort(j), alabel);
            j += 2;
            break;
        }
        return j;
    }

    protected Label readLabel(int i, Label alabel[])
    {
        if(alabel[i] == null)
            alabel[i] = new Label();
        return alabel[i];
    }

    private int a()
    {
        int i = header + 8 + (readUnsignedShort(header + 6) << 1);
        for(int j = readUnsignedShort(i); j > 0; j--)
        {
            for(int l = readUnsignedShort(i + 8); l > 0; l--)
                i += 6 + readInt(i + 12);

            i += 8;
        }

        i += 2;
        for(int k = readUnsignedShort(i); k > 0; k--)
        {
            for(int i1 = readUnsignedShort(i + 8); i1 > 0; i1--)
                i += 6 + readInt(i + 12);

            i += 8;
        }

        return i + 2;
    }

    private Attribute a(Attribute aattribute[], String s, int i, int j, char ac[], int k, Label alabel[])
    {
        for(int l = 0; l < aattribute.length; l++)
            if(aattribute[l].type.equals(s))
                return aattribute[l].read(this, i, j, ac, k, alabel);

        return (new Attribute(s)).read(this, i, j, null, -1, null);
    }

    public int getItemCount()
    {
        return a.length;
    }

    public int getItem(int i)
    {
        return a[i];
    }

    public int getMaxStringLength()
    {
        return d;
    }

    public int readByte(int i)
    {
        return b[i] & 0xff;
    }

    public int readUnsignedShort(int i)
    {
        byte abyte0[];
        return ((abyte0 = b)[i] & 0xff) << 8 | abyte0[i + 1] & 0xff;
    }

    public short readShort(int i)
    {
        byte abyte0[];
        return (short)(((abyte0 = b)[i] & 0xff) << 8 | abyte0[i + 1] & 0xff);
    }

    public int readInt(int i)
    {
        byte abyte0[];
        return ((abyte0 = b)[i] & 0xff) << 24 | (abyte0[i + 1] & 0xff) << 16 | (abyte0[i + 2] & 0xff) << 8 | abyte0[i + 3] & 0xff;
    }

    public long readLong(int i)
    {
        long l = readInt(i);
        long l1 = (long)readInt(i + 4) & 0xffffffffL;
        return l << 32 | l1;
    }

    public String readUTF8(int i, char ac[])
    {
        int j = readUnsignedShort(i);
        if(i == 0 || j == 0)
            return null;
        if((i = c[j]) != null)
        {
            return i;
        } else
        {
            i = a[j];
            return c[j] = a(i + 2, readUnsignedShort(i), ac);
        }
    }

    private String a(int i, int j, char ac[])
    {
        j = i + j;
        byte abyte0[] = b;
        int k = 0;
        int l = 0;
        char c1 = '\0';
        do
            if(i < j)
            {
                int i1 = abyte0[i++];
                switch(l)
                {
                case 0: // '\0'
                    if((i1 &= 0xff) < 128)
                        ac[k++] = (char)i1;
                    else
                    if(i1 < 224 && i1 > 191)
                    {
                        c1 = (char)(i1 & 0x1f);
                        l = 1;
                    } else
                    {
                        c1 = (char)(i1 & 0xf);
                        l = 2;
                    }
                    break;

                case 1: // '\001'
                    ac[k++] = (char)(c1 << 6 | i1 & 0x3f);
                    l = 0;
                    break;

                case 2: // '\002'
                    c1 = (char)(c1 << 6 | i1 & 0x3f);
                    l = 1;
                    break;
                }
            } else
            {
                return new String(ac, 0, k);
            }
        while(true);
    }

    public String readClass(int i, char ac[])
    {
        return readUTF8(a[readUnsignedShort(i)], ac);
    }

    public Object readConst(int i, char ac[])
    {
        i = a[i];
        int j;
        switch(b[i - 1])
        {
        case 3: // '\003'
            return new Integer(readInt(i));

        case 4: // '\004'
            return new Float(Float.intBitsToFloat(readInt(i)));

        case 5: // '\005'
            return new Long(readLong(i));

        case 6: // '\006'
            return new Double(Double.longBitsToDouble(readLong(i)));

        case 7: // '\007'
            return Type.getObjectType(readUTF8(i, ac));

        case 8: // '\b'
            return readUTF8(i, ac);

        case 16: // '\020'
            return Type.getMethodType(readUTF8(i, ac));

        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        default:
            j = readByte(i);
            break;
        }
        int ai[];
        i = (ai = a)[readUnsignedShort(i + 1)];
        String s1 = readClass(i, ac);
        i = ai[readUnsignedShort(i + 2)];
        String s = readUTF8(i, ac);
        i = readUTF8(i + 2, ac);
        return new Handle(j, s1, s, i);
    }

    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    public final byte b[];
    private final int a[];
    private final String c[];
    private final int d;
    public final int header;
}
