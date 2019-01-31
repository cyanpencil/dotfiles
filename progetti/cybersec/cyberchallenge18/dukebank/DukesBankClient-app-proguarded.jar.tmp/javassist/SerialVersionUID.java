// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerialVersionUID.java

package javassist;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import javassist.bytecode.ClassFile;
import javassist.bytecode.Descriptor;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist:
//            CannotCompileException, ClassPool, CtClass, CtConstructor, 
//            CtField, CtMethod, NotFoundException

public class SerialVersionUID
{

            public SerialVersionUID()
            {
            }

            public static void setSerialVersionUID(CtClass ctclass)
                throws CannotCompileException, NotFoundException
            {
/*  43*/        try
                {
/*  43*/            ctclass.getDeclaredField("serialVersionUID");
/*  44*/            return;
                }
/*  46*/        catch(NotFoundException _ex) { }
/*  49*/        if(!isSerializable(ctclass))
                {
/*  50*/            return;
                } else
                {
                    CtField ctfield;
/*  53*/            (ctfield = new CtField(CtClass.longType, "serialVersionUID", ctclass)).setModifiers(26);
/*  57*/            ctclass.addField(ctfield, (new StringBuilder()).append(calculateDefault(ctclass)).append("L").toString());
/*  58*/            return;
                }
            }

            private static boolean isSerializable(CtClass ctclass)
                throws NotFoundException
            {
/*  66*/        ClassPool classpool = ctclass.getClassPool();
/*  67*/        return ctclass.subtypeOf(classpool.get("java.io.Serializable"));
            }

            static long calculateDefault(CtClass ctclass)
                throws CannotCompileException
            {
                long l2;
/*  78*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/*  79*/        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
/*  80*/        ClassFile classfile = ctclass.getClassFile();
/*  83*/        String s = javaName(ctclass);
/*  84*/        dataoutputstream.writeUTF(s);
/*  86*/        CtMethod actmethod[] = ctclass.getDeclaredMethods();
                int i;
/*  89*/        if(((i = ctclass.getModifiers()) & 0x200) != 0)
/*  91*/            if(actmethod.length > 0)
/*  92*/                i |= 0x400;
/*  94*/            else
/*  94*/                i &= 0xfffffbff;
/*  96*/        dataoutputstream.writeInt(i);
/*  99*/        Object aobj[] = classfile.getInterfaces();
/* 100*/        for(int k = 0; k < aobj.length; k++)
/* 101*/            aobj[k] = javaName(aobj[k]);

/* 103*/        Arrays.sort(aobj);
/* 104*/        for(int l = 0; l < aobj.length; l++)
/* 105*/            dataoutputstream.writeUTF(aobj[l]);

                CtField actfield[];
/* 108*/        Arrays.sort(actfield = ctclass.getDeclaredFields(), new Comparator() {

                    public final int compare(Object obj1, Object obj2)
                    {
/* 111*/                obj1 = (CtField)obj1;
/* 112*/                obj2 = (CtField)obj2;
/* 113*/                return ((CtField) (obj1)).getName().compareTo(((CtField) (obj2)).getName());
                    }

        });
/* 117*/        for(int j = 0; j < actfield.length; j++)
                {
                    CtField ctfield;
                    int k1;
/* 118*/            if(((k1 = (ctfield = actfield[j]).getModifiers()) & 2) == 0 || (k1 & 0x88) == 0)
                    {
/* 122*/                dataoutputstream.writeUTF(ctfield.getName());
/* 123*/                dataoutputstream.writeInt(k1);
/* 124*/                dataoutputstream.writeUTF(ctfield.getFieldInfo2().getDescriptor());
                    }
                }

/* 129*/        if(classfile.getStaticInitializer() != null)
                {
/* 130*/            dataoutputstream.writeUTF("<clinit>");
/* 131*/            dataoutputstream.writeInt(8);
/* 132*/            dataoutputstream.writeUTF("()V");
                }
/* 136*/        Arrays.sort(j = ctclass.getDeclaredConstructors(), new Comparator() {

                    public final int compare(Object obj1, Object obj2)
                    {
/* 139*/                obj1 = (CtConstructor)obj1;
/* 140*/                obj2 = (CtConstructor)obj2;
/* 141*/                return ((CtConstructor) (obj1)).getMethodInfo2().getDescriptor().compareTo(((CtConstructor) (obj2)).getMethodInfo2().getDescriptor());
                    }

        });
/* 146*/        for(int i1 = 0; i1 < j.length; i1++)
                {
                    CtConstructor ctconstructor;
                    int l1;
/* 147*/            if(((l1 = (ctconstructor = j[i1]).getModifiers()) & 2) == 0)
                    {
/* 150*/                dataoutputstream.writeUTF("<init>");
/* 151*/                dataoutputstream.writeInt(l1);
/* 152*/                dataoutputstream.writeUTF(ctconstructor.getMethodInfo2().getDescriptor().replace('/', '.'));
                    }
                }

/* 158*/        Arrays.sort(actmethod, new Comparator() {

                    public final int compare(Object obj1, Object obj2)
                    {
/* 160*/                obj1 = (CtMethod)obj1;
/* 161*/                obj2 = (CtMethod)obj2;
                        int j2;
/* 162*/                if((j2 = ((CtMethod) (obj1)).getName().compareTo(((CtMethod) (obj2)).getName())) == 0)
/* 164*/                    j2 = ((CtMethod) (obj1)).getMethodInfo2().getDescriptor().compareTo(((CtMethod) (obj2)).getMethodInfo2().getDescriptor());
/* 167*/                return j2;
                    }

        });
/* 171*/        for(int j1 = 0; j1 < actmethod.length; j1++)
                {
                    CtMethod ctmethod;
                    int i2;
/* 172*/            if(((i2 = (ctmethod = actmethod[j1]).getModifiers() & 0xd3f) & 2) == 0)
                    {
/* 179*/                dataoutputstream.writeUTF(ctmethod.getName());
/* 180*/                dataoutputstream.writeInt(i2);
/* 181*/                dataoutputstream.writeUTF(ctmethod.getMethodInfo2().getDescriptor().replace('/', '.'));
                    }
                }

/* 187*/        dataoutputstream.flush();
                MessageDigest messagedigest;
/* 188*/        byte abyte0[] = (messagedigest = MessageDigest.getInstance("SHA")).digest(bytearrayoutputstream.toByteArray());
/* 190*/        l2 = 0L;
/* 191*/        for(ctclass = Math.min(abyte0.length, 8) - 1; ctclass >= 0; ctclass--)
/* 192*/            l2 = l2 << 8 | (long)(abyte0[ctclass] & 0xff);

/* 194*/        return l2;
                Object obj;
/* 196*/        obj;
/* 197*/        throw new CannotCompileException(((Throwable) (obj)));
/* 199*/        obj;
/* 200*/        throw new CannotCompileException(((Throwable) (obj)));
            }

            private static String javaName(CtClass ctclass)
            {
/* 205*/        return Descriptor.toJavaName(Descriptor.toJvmName(ctclass));
            }

            private static String javaName(String s)
            {
/* 209*/        return Descriptor.toJavaName(Descriptor.toJvmName(s));
            }
}
