// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFilePrinter.java

package javassist.bytecode;

import java.io.PrintWriter;
import java.util.List;
import javassist.Modifier;

// Referenced classes of package javassist.bytecode:
//            AccessFlag, AnnotationsAttribute, AttributeInfo, BadBytecode, 
//            ClassFile, CodeAttribute, ExceptionTable, FieldInfo, 
//            MethodInfo, ParameterAnnotationsAttribute, SignatureAttribute, StackMap, 
//            StackMapTable

public class ClassFilePrinter
{

            public ClassFilePrinter()
            {
            }

            public static void print(ClassFile classfile)
            {
/*  33*/        print(classfile, new PrintWriter(System.out, true));
            }

            public static void print(ClassFile classfile, PrintWriter printwriter)
            {
/*  46*/        int i = AccessFlag.toModifier(classfile.getAccessFlags() & 0xffffffdf);
/*  49*/        printwriter.println((new StringBuilder("major: ")).append(classfile.major).append(", minor: ").append(classfile.minor).append(" modifiers: ").append(Integer.toHexString(classfile.getAccessFlags())).toString());
/*  51*/        printwriter.println((new StringBuilder()).append(Modifier.toString(i)).append(" class ").append(classfile.getName()).append(" extends ").append(classfile.getSuperclass()).toString());
                String as[];
/*  54*/        if((as = classfile.getInterfaces()) != null && as.length > 0)
                {
/*  56*/            printwriter.print("    implements ");
/*  57*/            printwriter.print(as[0]);
/*  58*/            for(int k = 1; k < as.length; k++)
/*  59*/                printwriter.print((new StringBuilder(", ")).append(as[k]).toString());

/*  61*/            printwriter.println();
                }
/*  64*/        printwriter.println();
                List list;
/*  65*/        int j = (list = classfile.getFields()).size();
/*  67*/        for(int l = 0; l < j; l++)
                {
                    FieldInfo fieldinfo;
/*  68*/            int j1 = (fieldinfo = (FieldInfo)list.get(l)).getAccessFlags();
/*  70*/            printwriter.println((new StringBuilder()).append(Modifier.toString(AccessFlag.toModifier(j1))).append(" ").append(fieldinfo.getName()).append("\t").append(fieldinfo.getDescriptor()).toString());
/*  73*/            printAttributes(fieldinfo.getAttributes(), printwriter, 'f');
                }

/*  76*/        printwriter.println();
/*  77*/        j = (list = classfile.getMethods()).size();
/*  79*/        for(int i1 = 0; i1 < j; i1++)
                {
                    MethodInfo methodinfo;
/*  80*/            int k1 = (methodinfo = (MethodInfo)list.get(i1)).getAccessFlags();
/*  82*/            printwriter.println((new StringBuilder()).append(Modifier.toString(AccessFlag.toModifier(k1))).append(" ").append(methodinfo.getName()).append("\t").append(methodinfo.getDescriptor()).toString());
/*  85*/            printAttributes(methodinfo.getAttributes(), printwriter, 'm');
/*  86*/            printwriter.println();
                }

/*  89*/        printwriter.println();
/*  90*/        printAttributes(classfile.getAttributes(), printwriter, 'c');
            }

            static void printAttributes(List list, PrintWriter printwriter, char c)
            {
/*  94*/        if(list == null)
/*  95*/            return;
/*  97*/        int i = list.size();
/*  98*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  99*/            if((obj = (AttributeInfo)list.get(j)) instanceof CodeAttribute)
                    {
/* 101*/                CodeAttribute codeattribute = (CodeAttribute)obj;
/* 102*/                printwriter.println((new StringBuilder("attribute: ")).append(((AttributeInfo) (obj)).getName()).append(": ").append(obj.getClass().getName()).toString());
/* 104*/                printwriter.println((new StringBuilder("max stack ")).append(codeattribute.getMaxStack()).append(", max locals ").append(codeattribute.getMaxLocals()).append(", ").append(codeattribute.getExceptionTable().size()).append(" catch blocks").toString());
/* 108*/                printwriter.println("<code attribute begin>");
/* 109*/                printAttributes(codeattribute.getAttributes(), printwriter, c);
/* 110*/                printwriter.println("<code attribute end>");
/* 111*/                continue;
                    }
/* 112*/            if(obj instanceof AnnotationsAttribute)
                    {
/* 113*/                printwriter.println((new StringBuilder("annnotation: ")).append(obj.toString()).toString());
/* 113*/                continue;
                    }
/* 115*/            if(obj instanceof ParameterAnnotationsAttribute)
                    {
/* 116*/                printwriter.println((new StringBuilder("parameter annnotations: ")).append(obj.toString()).toString());
/* 116*/                continue;
                    }
/* 118*/            if(obj instanceof StackMapTable)
                    {
/* 119*/                printwriter.println("<stack map table begin>");
/* 120*/                StackMapTable.Printer.print((StackMapTable)obj, printwriter);
/* 121*/                printwriter.println("<stack map table end>");
/* 121*/                continue;
                    }
/* 123*/            if(obj instanceof StackMap)
                    {
/* 124*/                printwriter.println("<stack map begin>");
/* 125*/                ((StackMap)obj).print(printwriter);
/* 126*/                printwriter.println("<stack map end>");
/* 126*/                continue;
                    }
/* 128*/            if(obj instanceof SignatureAttribute)
                    {
                        SignatureAttribute signatureattribute;
/* 129*/                obj = (signatureattribute = (SignatureAttribute)obj).getSignature();
/* 131*/                printwriter.println((new StringBuilder("signature: ")).append(((String) (obj))).toString());
/* 134*/                try
                        {
/* 134*/                    if(c == 'c')
/* 135*/                        obj = SignatureAttribute.toClassSignature(((String) (obj))).toString();
/* 136*/                    else
/* 136*/                    if(c == 'm')
/* 137*/                        obj = SignatureAttribute.toMethodSignature(((String) (obj))).toString();
/* 139*/                    else
/* 139*/                        obj = SignatureAttribute.toFieldSignature(((String) (obj))).toString();
/* 141*/                    printwriter.println((new StringBuilder("           ")).append(((String) (obj))).toString());
                        }
/* 143*/                catch(BadBytecode _ex)
                        {
/* 144*/                    printwriter.println("           syntax error");
                        }
                    } else
                    {
/* 148*/                printwriter.println((new StringBuilder("attribute: ")).append(((AttributeInfo) (obj)).getName()).append(" (").append(((AttributeInfo) (obj)).get().length).append(" byte): ").append(obj.getClass().getName()).toString());
                    }
                }

            }
}
