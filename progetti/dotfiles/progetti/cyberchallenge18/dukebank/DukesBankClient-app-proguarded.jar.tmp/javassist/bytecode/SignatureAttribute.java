// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;
import javassist.CtClass;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, BadBytecode, ByteArray, ConstPool, 
//            Descriptor

public class SignatureAttribute extends AttributeInfo
{
    public static class TypeVariable extends ObjectType
    {

                public String getName()
                {
/* 871*/            return name;
                }

                public String toString()
                {
/* 878*/            return name;
                }

                void encode(StringBuffer stringbuffer)
                {
/* 882*/            stringbuffer.append('T').append(name).append(';');
                }

                String name;

                TypeVariable(String s, int i, int j)
                {
/* 855*/            name = s.substring(i, j);
                }

                public TypeVariable(String s)
                {
/* 864*/            name = s;
                }
    }

    public static class ArrayType extends ObjectType
    {

                public int getDimension()
                {
/* 820*/            return dim;
                }

                public Type getComponentType()
                {
/* 826*/            return componentType;
                }

                public String toString()
                {
/* 833*/            StringBuffer stringbuffer = new StringBuffer(componentType.toString());
/* 834*/            for(int i = 0; i < dim; i++)
/* 835*/                stringbuffer.append("[]");

/* 837*/            return stringbuffer.toString();
                }

                void encode(StringBuffer stringbuffer)
                {
/* 841*/            for(int i = 0; i < dim; i++)
/* 842*/                stringbuffer.append('[');

/* 844*/            componentType.encode(stringbuffer);
                }

                int dim;
                Type componentType;

                public ArrayType(int i, Type type)
                {
/* 813*/            dim = i;
/* 814*/            componentType = type;
                }
    }

    public static class NestedClassType extends ClassType
    {

                public ClassType getDeclaringClass()
                {
/* 796*/            return parent;
                }

                ClassType parent;

                NestedClassType(String s, int i, int j, TypeArgument atypeargument[], ClassType classtype)
                {
/* 775*/            super(s, i, j, atypeargument);
/* 776*/            parent = classtype;
                }

                public NestedClassType(ClassType classtype, String s, TypeArgument atypeargument[])
                {
/* 788*/            super(s, atypeargument);
/* 789*/            parent = classtype;
                }
    }

    public static class ClassType extends ObjectType
    {

                static ClassType make(String s, int i, int j, TypeArgument atypeargument[], ClassType classtype)
                {
/* 663*/            if(classtype == null)
/* 664*/                return new ClassType(s, i, j, atypeargument);
/* 666*/            else
/* 666*/                return new NestedClassType(s, i, j, atypeargument, classtype);
                }

                public String getName()
                {
/* 705*/            return name;
                }

                public TypeArgument[] getTypeArguments()
                {
/* 713*/            return arguments;
                }

                public ClassType getDeclaringClass()
                {
/* 721*/            return null;
                }

                public String toString()
                {
/* 727*/            StringBuffer stringbuffer = new StringBuffer();
                    ClassType classtype;
/* 728*/            if((classtype = getDeclaringClass()) != null)
/* 730*/                stringbuffer.append(classtype.toString()).append('.');
/* 732*/            stringbuffer.append(name);
/* 733*/            if(arguments != null)
                    {
/* 734*/                stringbuffer.append('<');
/* 735*/                int i = arguments.length;
/* 736*/                for(int j = 0; j < i; j++)
                        {
/* 737*/                    if(j > 0)
/* 738*/                        stringbuffer.append(", ");
/* 740*/                    stringbuffer.append(arguments[j].toString());
                        }

/* 743*/                stringbuffer.append('>');
                    }
/* 746*/            return stringbuffer.toString();
                }

                void encode(StringBuffer stringbuffer)
                {
/* 750*/            stringbuffer.append('L');
/* 751*/            encode2(stringbuffer);
/* 752*/            stringbuffer.append(';');
                }

                void encode2(StringBuffer stringbuffer)
                {
                    ClassType classtype;
/* 756*/            if((classtype = getDeclaringClass()) != null)
                    {
/* 758*/                classtype.encode2(stringbuffer);
/* 759*/                stringbuffer.append('$');
                    }
/* 762*/            stringbuffer.append(name.replace('.', '/'));
/* 763*/            if(arguments != null)
/* 764*/                TypeArgument.encode(stringbuffer, arguments);
                }

                String name;
                TypeArgument arguments[];
                public static ClassType OBJECT = new ClassType("java.lang.Object", null);


                ClassType(String s, int i, int j, TypeArgument atypeargument[])
                {
/* 670*/            name = s.substring(i, j).replace('/', '.');
/* 671*/            arguments = atypeargument;
                }

                public ClassType(String s, TypeArgument atypeargument[])
                {
/* 687*/            name = s;
/* 688*/            arguments = atypeargument;
                }

                public ClassType(String s)
                {
/* 698*/            this(s, null);
                }
    }

    public static abstract class ObjectType extends Type
    {

                public String encode()
                {
/* 648*/            StringBuffer stringbuffer = new StringBuffer();
/* 649*/            encode(stringbuffer);
/* 650*/            return stringbuffer.toString();
                }

                public ObjectType()
                {
                }
    }

    public static class BaseType extends Type
    {

                public char getDescriptor()
                {
/* 617*/            return descriptor;
                }

                public CtClass getCtlass()
                {
/* 624*/            return Descriptor.toPrimitiveClass(descriptor);
                }

                public String toString()
                {
/* 631*/            return Descriptor.toClassName(Character.toString(descriptor));
                }

                void encode(StringBuffer stringbuffer)
                {
/* 635*/            stringbuffer.append(descriptor);
                }

                char descriptor;

                BaseType(char c)
                {
/* 601*/            descriptor = c;
                }

                public BaseType(String s)
                {
/* 609*/            this(Descriptor.of(s).charAt(0));
                }
    }

    public static abstract class Type
    {

                abstract void encode(StringBuffer stringbuffer);

                static void toString(StringBuffer stringbuffer, Type atype[])
                {
/* 587*/            for(int i = 0; i < atype.length; i++)
                    {
/* 588*/                if(i > 0)
/* 589*/                    stringbuffer.append(", ");
/* 591*/                stringbuffer.append(atype[i]);
                    }

                }

                public Type()
                {
                }
    }

    public static class TypeArgument
    {

                public static TypeArgument subclassOf(ObjectType objecttype)
                {
/* 514*/            return new TypeArgument(objecttype, '+');
                }

                public static TypeArgument superOf(ObjectType objecttype)
                {
/* 524*/            return new TypeArgument(objecttype, '-');
                }

                public char getKind()
                {
/* 533*/            return wildcard;
                }

                public boolean isWildcard()
                {
/* 539*/            return wildcard != ' ';
                }

                public ObjectType getType()
                {
/* 548*/            return arg;
                }

                public String toString()
                {
/* 554*/            if(wildcard == '*')
/* 555*/                return "?";
/* 557*/            String s = arg.toString();
/* 558*/            if(wildcard == ' ')
/* 559*/                return s;
/* 560*/            if(wildcard == '+')
/* 561*/                return (new StringBuilder("? extends ")).append(s).toString();
/* 563*/            else
/* 563*/                return (new StringBuilder("? super ")).append(s).toString();
                }

                static void encode(StringBuffer stringbuffer, TypeArgument atypeargument[])
                {
/* 567*/            stringbuffer.append('<');
/* 568*/            for(int i = 0; i < atypeargument.length; i++)
                    {
                        TypeArgument typeargument;
/* 569*/                if((typeargument = atypeargument[i]).isWildcard())
/* 571*/                    stringbuffer.append(typeargument.wildcard);
/* 573*/                if(typeargument.getType() != null)
/* 574*/                    typeargument.getType().encode(stringbuffer);
                    }

/* 577*/            stringbuffer.append('>');
                }

                ObjectType arg;
                char wildcard;

                TypeArgument(ObjectType objecttype, char c)
                {
/* 485*/            arg = objecttype;
/* 486*/            wildcard = c;
                }

                public TypeArgument(ObjectType objecttype)
                {
/* 497*/            this(objecttype, ' ');
                }

                public TypeArgument()
                {
/* 504*/            this(null, '*');
                }
    }

    public static class TypeParameter
    {

                public String getName()
                {
/* 409*/            return name;
                }

                public ObjectType getClassBound()
                {
/* 415*/            return superClass;
                }

                public ObjectType[] getInterfaceBound()
                {
/* 422*/            return superInterfaces;
                }

                public String toString()
                {
/* 428*/            StringBuffer stringbuffer = new StringBuffer(getName());
/* 429*/            if(superClass != null)
/* 430*/                stringbuffer.append(" extends ").append(superClass.toString());
                    int i;
/* 432*/            if((i = superInterfaces.length) > 0)
                    {
/* 434*/                for(int j = 0; j < i; j++)
                        {
/* 435*/                    if(j > 0 || superClass != null)
/* 436*/                        stringbuffer.append(" & ");
/* 438*/                    else
/* 438*/                        stringbuffer.append(" extends ");
/* 440*/                    stringbuffer.append(superInterfaces[j].toString());
                        }

                    }
/* 444*/            return stringbuffer.toString();
                }

                static void toString(StringBuffer stringbuffer, TypeParameter atypeparameter[])
                {
/* 448*/            stringbuffer.append('<');
/* 449*/            for(int i = 0; i < atypeparameter.length; i++)
                    {
/* 450*/                if(i > 0)
/* 451*/                    stringbuffer.append(", ");
/* 453*/                stringbuffer.append(atypeparameter[i]);
                    }

/* 456*/            stringbuffer.append('>');
                }

                void encode(StringBuffer stringbuffer)
                {
/* 460*/            stringbuffer.append(name);
/* 461*/            if(superClass == null)
                    {
/* 462*/                stringbuffer.append(":Ljava/lang/Object;");
                    } else
                    {
/* 464*/                stringbuffer.append(':');
/* 465*/                superClass.encode(stringbuffer);
                    }
/* 468*/            for(int i = 0; i < superInterfaces.length; i++)
                    {
/* 469*/                stringbuffer.append(':');
/* 470*/                superInterfaces[i].encode(stringbuffer);
                    }

                }

                String name;
                ObjectType superClass;
                ObjectType superInterfaces[];

                TypeParameter(String s, int i, int j, ObjectType objecttype, ObjectType aobjecttype[])
                {
/* 373*/            name = s.substring(i, j);
/* 374*/            superClass = objecttype;
/* 375*/            superInterfaces = aobjecttype;
                }

                public TypeParameter(String s, ObjectType objecttype, ObjectType aobjecttype[])
                {
/* 387*/            name = s;
/* 388*/            superClass = objecttype;
/* 389*/            if(aobjecttype == null)
                    {
/* 390*/                superInterfaces = new ObjectType[0];
/* 390*/                return;
                    } else
                    {
/* 392*/                superInterfaces = aobjecttype;
/* 393*/                return;
                    }
                }

                public TypeParameter(String s)
                {
/* 402*/            this(s, null, null);
                }
    }

    public static class MethodSignature
    {

                public TypeParameter[] getTypeParameters()
                {
/* 292*/            return typeParams;
                }

                public Type[] getParameterTypes()
                {
/* 299*/            return params;
                }

                public Type getReturnType()
                {
/* 304*/            return retType;
                }

                public ObjectType[] getExceptionTypes()
                {
/* 312*/            return exceptions;
                }

                public String toString()
                {
                    StringBuffer stringbuffer;
/* 318*/            TypeParameter.toString(stringbuffer = new StringBuffer(), typeParams);
/* 321*/            stringbuffer.append(" (");
/* 322*/            Type.toString(stringbuffer, params);
/* 323*/            stringbuffer.append(") ");
/* 324*/            stringbuffer.append(retType);
/* 325*/            if(exceptions.length > 0)
                    {
/* 326*/                stringbuffer.append(" throws ");
/* 327*/                Type.toString(stringbuffer, exceptions);
                    }
/* 330*/            return stringbuffer.toString();
                }

                public String encode()
                {
/* 337*/            StringBuffer stringbuffer = new StringBuffer();
/* 338*/            if(typeParams.length > 0)
                    {
/* 339*/                stringbuffer.append('<');
/* 340*/                for(int i = 0; i < typeParams.length; i++)
/* 341*/                    typeParams[i].encode(stringbuffer);

/* 343*/                stringbuffer.append('>');
                    }
/* 346*/            stringbuffer.append('(');
/* 347*/            for(int j = 0; j < params.length; j++)
/* 348*/                params[j].encode(stringbuffer);

/* 350*/            stringbuffer.append(')');
/* 351*/            retType.encode(stringbuffer);
/* 352*/            if(exceptions.length > 0)
                    {
/* 353*/                for(int k = 0; k < exceptions.length; k++)
                        {
/* 354*/                    stringbuffer.append('^');
/* 355*/                    exceptions[k].encode(stringbuffer);
                        }

                    }
/* 358*/            return stringbuffer.toString();
                }

                TypeParameter typeParams[];
                Type params[];
                Type retType;
                ObjectType exceptions[];

                public MethodSignature(TypeParameter atypeparameter[], Type atype[], Type type, ObjectType aobjecttype[])
                {
/* 281*/            typeParams = atypeparameter != null ? atypeparameter : new TypeParameter[0];
/* 282*/            params = atype != null ? atype : new Type[0];
/* 283*/            retType = ((Type) (type != null ? type : ((Type) (new BaseType("void")))));
/* 284*/            exceptions = aobjecttype != null ? aobjecttype : new ObjectType[0];
                }
    }

    public static class ClassSignature
    {

                public TypeParameter[] getParameters()
                {
/* 210*/            return params;
                }

                public ClassType getSuperClass()
                {
/* 216*/            return superClass;
                }

                public ClassType[] getInterfaces()
                {
/* 223*/            return interfaces;
                }

                public String toString()
                {
                    StringBuffer stringbuffer;
/* 229*/            TypeParameter.toString(stringbuffer = new StringBuffer(), params);
/* 232*/            stringbuffer.append(" extends ").append(superClass);
/* 233*/            if(interfaces.length > 0)
                    {
/* 234*/                stringbuffer.append(" implements ");
/* 235*/                Type.toString(stringbuffer, interfaces);
                    }
/* 238*/            return stringbuffer.toString();
                }

                public String encode()
                {
/* 245*/            StringBuffer stringbuffer = new StringBuffer();
/* 246*/            if(params.length > 0)
                    {
/* 247*/                stringbuffer.append('<');
/* 248*/                for(int i = 0; i < params.length; i++)
/* 249*/                    params[i].encode(stringbuffer);

/* 251*/                stringbuffer.append('>');
                    }
/* 254*/            superClass.encode(stringbuffer);
/* 255*/            for(int j = 0; j < interfaces.length; j++)
/* 256*/                interfaces[j].encode(stringbuffer);

/* 258*/            return stringbuffer.toString();
                }

                TypeParameter params[];
                ClassType superClass;
                ClassType interfaces[];

                public ClassSignature(TypeParameter atypeparameter[], ClassType classtype, ClassType aclasstype[])
                {
/* 190*/            params = atypeparameter != null ? atypeparameter : new TypeParameter[0];
/* 191*/            superClass = classtype != null ? classtype : ClassType.OBJECT;
/* 192*/            interfaces = aclasstype != null ? aclasstype : new ClassType[0];
                }

                public ClassSignature(TypeParameter atypeparameter[])
                {
/* 201*/            this(atypeparameter, null, null);
                }
    }

    static class Cursor
    {

                int indexOf(String s, int i)
                    throws BadBytecode
                {
/* 164*/            if((i = s.indexOf(i, position)) < 0)
                    {
/* 166*/                throw SignatureAttribute.error(s);
                    } else
                    {
/* 168*/                position = i + 1;
/* 169*/                return i;
                    }
                }

                int position;

                private Cursor()
                {
/* 161*/            position = 0;
                }

    }


            SignatureAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  37*/        super(constpool, i, datainputstream);
            }

            public SignatureAttribute(ConstPool constpool, String s)
            {
/*  47*/        super(constpool, "Signature");
/*  48*/        constpool = constpool.addUtf8Info(s);
/*  49*/        (s = new byte[2])[0] = (byte)(constpool >>> 8);
/*  51*/        s[1] = (byte)constpool;
/*  52*/        set(s);
            }

            public String getSignature()
            {
/*  63*/        return getConstPool().getUtf8Info(ByteArray.readU16bit(get(), 0));
            }

            public void setSignature(String s)
            {
/*  74*/        ByteArray.write16bit(s = getConstPool().addUtf8Info(s), info, 0);
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/*  87*/        return new SignatureAttribute(constpool, getSignature());
            }

            void renameClass(String s, String s1)
            {
/*  91*/        s = renameClass(getSignature(), s, s1);
/*  92*/        setSignature(s);
            }

            void renameClass(Map map)
            {
/*  96*/        map = renameClass(getSignature(), map);
/*  97*/        setSignature(map);
            }

            static String renameClass(String s, String s1, String s2)
            {
                HashMap hashmap;
/* 101*/        (hashmap = new HashMap()).put(s1, s2);
/* 103*/        return renameClass(s, ((Map) (hashmap)));
            }

            static String renameClass(String s, Map map)
            {
/* 107*/        if(map == null)
/* 108*/            return s;
/* 110*/        StringBuilder stringbuilder = new StringBuilder();
/* 111*/        int i = 0;
/* 112*/        int j = 0;
/* 114*/        do
                {
                    int k;
/* 114*/            if((k = s.indexOf('L', j)) < 0)
/* 118*/                break;
/* 118*/            Object obj = new StringBuilder();
/* 119*/            j = k;
                    char c;
/* 122*/            try
                    {
/* 122*/                do
                        {
/* 122*/                    if((c = s.charAt(++j)) == ';')
/* 123*/                        break;
/* 123*/                    ((StringBuilder) (obj)).append(c);
/* 124*/                    if(c == '<')
                            {
/* 125*/                        while((c = s.charAt(++j)) != '>') 
/* 126*/                            ((StringBuilder) (obj)).append(c);
/* 128*/                        ((StringBuilder) (obj)).append(c);
                            }
                        } while(true);
                    }
/* 132*/            catch(IndexOutOfBoundsException _ex)
                    {
/* 132*/                break;
                    }
/* 133*/            j++;
/* 134*/            obj = ((StringBuilder) (obj)).toString();
/* 135*/            if((obj = (String)map.get(obj)) != null)
                    {
/* 137*/                stringbuilder.append(s.substring(i, k));
/* 138*/                stringbuilder.append('L');
/* 139*/                stringbuilder.append(((String) (obj)));
/* 140*/                stringbuilder.append(c);
/* 141*/                i = j;
                    }
                } while(true);
/* 145*/        if(i == 0)
/* 146*/            return s;
/* 148*/        int l = s.length();
/* 149*/        if(i < l)
/* 150*/            stringbuilder.append(s.substring(i, l));
/* 152*/        return stringbuilder.toString();
            }

            private static boolean isNamePart(int i)
            {
/* 157*/        return i != 59 && i != 60;
            }

            public static ClassSignature toClassSignature(String s)
                throws BadBytecode
            {
/* 899*/        return parseSig(s);
/* 901*/        JVM INSTR pop ;
/* 902*/        throw error(s);
            }

            public static MethodSignature toMethodSignature(String s)
                throws BadBytecode
            {
/* 919*/        return parseMethodSig(s);
/* 921*/        JVM INSTR pop ;
/* 922*/        throw error(s);
            }

            public static ObjectType toFieldSignature(String s)
                throws BadBytecode
            {
/* 938*/        return parseObjectType(s, new Cursor(), false);
/* 940*/        JVM INSTR pop ;
/* 941*/        throw error(s);
            }

            public static Type toTypeSignature(String s)
                throws BadBytecode
            {
/* 955*/        return parseType(s, new Cursor());
/* 957*/        JVM INSTR pop ;
/* 958*/        throw error(s);
            }

            private static ClassSignature parseSig(String s)
                throws BadBytecode, IndexOutOfBoundsException
            {
/* 965*/        Cursor cursor = new Cursor();
/* 966*/        TypeParameter atypeparameter[] = parseTypeParams(s, cursor);
/* 967*/        ClassType classtype = parseClassType(s, cursor);
/* 968*/        int i = s.length();
/* 969*/        ArrayList arraylist = new ArrayList();
/* 970*/        for(; cursor.position < i && s.charAt(cursor.position) == 'L'; arraylist.add(parseClassType(s, cursor)));
/* 973*/        s = (ClassType[])arraylist.toArray(new ClassType[arraylist.size()]);
/* 975*/        return new ClassSignature(atypeparameter, classtype, s);
            }

            private static MethodSignature parseMethodSig(String s)
                throws BadBytecode
            {
/* 981*/        Cursor cursor = new Cursor();
/* 982*/        TypeParameter atypeparameter[] = parseTypeParams(s, cursor);
/* 983*/        if(s.charAt(cursor.position++) != '(')
/* 984*/            throw error(s);
/* 986*/        ArrayList arraylist = new ArrayList();
                Type type;
/* 987*/        for(; s.charAt(cursor.position) != ')'; arraylist.add(type))
/* 988*/            type = parseType(s, cursor);

/* 992*/        cursor.position++;
/* 993*/        Type type1 = parseType(s, cursor);
/* 994*/        int i = s.length();
/* 995*/        ArrayList arraylist1 = new ArrayList();
                ObjectType objecttype;
/* 996*/        for(; cursor.position < i && s.charAt(cursor.position) == '^'; arraylist1.add(objecttype))
                {
/* 997*/            cursor.position++;
/* 998*/            if((objecttype = parseObjectType(s, cursor, false)) instanceof ArrayType)
/*1000*/                throw error(s);
                }

/*1005*/        Type atype[] = (Type[])arraylist.toArray(new Type[arraylist.size()]);
/*1006*/        s = (ObjectType[])arraylist1.toArray(new ObjectType[arraylist1.size()]);
/*1007*/        return new MethodSignature(atypeparameter, atype, type1, s);
            }

            private static TypeParameter[] parseTypeParams(String s, Cursor cursor)
                throws BadBytecode
            {
/*1013*/        ArrayList arraylist = new ArrayList();
/*1014*/        if(s.charAt(cursor.position) == '<')
                {
                    TypeParameter typeparameter;
/*1015*/            for(cursor.position++; s.charAt(cursor.position) != '>'; arraylist.add(typeparameter))
                    {
/*1017*/                int i = cursor.position;
/*1018*/                int j = cursor.indexOf(s, 58);
/*1019*/                ObjectType objecttype = parseObjectType(s, cursor, true);
/*1020*/                ArrayList arraylist1 = new ArrayList();
                        ObjectType objecttype1;
/*1021*/                for(; s.charAt(cursor.position) == ':'; arraylist1.add(objecttype1))
                        {
/*1022*/                    cursor.position++;
/*1023*/                    objecttype1 = parseObjectType(s, cursor, false);
                        }

/*1027*/                typeparameter = new TypeParameter(s, i, j, objecttype, (ObjectType[])arraylist1.toArray(new ObjectType[arraylist1.size()]));
                    }

/*1032*/            cursor.position++;
                }
/*1035*/        return (TypeParameter[])arraylist.toArray(new TypeParameter[arraylist.size()]);
            }

            private static ObjectType parseObjectType(String s, Cursor cursor, boolean flag)
                throws BadBytecode
            {
/*1042*/        int i = cursor.position;
/*1043*/        switch(s.charAt(i))
                {
/*1045*/        case 76: // 'L'
/*1045*/            return parseClassType2(s, cursor, null);

/*1047*/        case 84: // 'T'
/*1047*/            cursor = cursor.indexOf(s, 59);
/*1048*/            return new TypeVariable(s, i + 1, cursor);

/*1050*/        case 91: // '['
/*1050*/            return parseArray(s, cursor);
                }
/*1052*/        if(flag)
/*1053*/            return null;
/*1055*/        else
/*1055*/            throw error(s);
            }

            private static ClassType parseClassType(String s, Cursor cursor)
                throws BadBytecode
            {
/*1062*/        if(s.charAt(cursor.position) == 'L')
/*1063*/            return parseClassType2(s, cursor, null);
/*1065*/        else
/*1065*/            throw error(s);
            }

            private static ClassType parseClassType2(String s, Cursor cursor, ClassType classtype)
                throws BadBytecode
            {
/*1071*/        do
                {
/*1071*/            int i = ++cursor.position;
                    char c;
/*1074*/            while((c = s.charAt(cursor.position++)) != '$' && c != '<' && c != ';') ;
/*1076*/            int j = cursor.position - 1;
                    TypeArgument atypeargument[];
/*1078*/            if(c == '<')
                    {
/*1079*/                atypeargument = parseTypeArgs(s, cursor);
/*1080*/                c = s.charAt(cursor.position++);
                    } else
                    {
/*1083*/                atypeargument = null;
                    }
/*1085*/            classtype = ClassType.make(s, i, j, atypeargument, classtype);
/*1086*/            if(c == '$' || c == '.')
                    {
/*1087*/                cursor.position--;
/*1088*/                classtype = classtype;
/*1088*/                cursor = cursor;
/*1088*/                s = s;
                    } else
                    {
/*1091*/                return classtype;
                    }
                } while(true);
            }

            private static TypeArgument[] parseTypeArgs(String s, Cursor cursor)
                throws BadBytecode
            {
/*1095*/        ArrayList arraylist = new ArrayList();
                int i;
/*1097*/        for(; (i = s.charAt(cursor.position++)) != '>'; arraylist.add(i))
                {
/*1099*/            if(i == 42)
                    {
/*1100*/                i = new TypeArgument(null, '*');
/*1100*/                continue;
                    }
/*1102*/            if(i != 43 && i != 45)
                    {
/*1103*/                i = 32;
/*1104*/                cursor.position--;
                    }
/*1107*/            i = new TypeArgument(parseObjectType(s, cursor, false), i);
                }

/*1113*/        return (TypeArgument[])arraylist.toArray(new TypeArgument[arraylist.size()]);
            }

            private static ObjectType parseArray(String s, Cursor cursor)
                throws BadBytecode
            {
                int i;
/*1117*/        for(i = 1; s.charAt(++cursor.position) == '['; i++);
/*1121*/        return new ArrayType(i, parseType(s, cursor));
            }

            private static Type parseType(String s, Cursor cursor)
                throws BadBytecode
            {
                Object obj;
/*1125*/        if((obj = parseObjectType(s, cursor, true)) == null)
/*1127*/            obj = new BaseType(s.charAt(cursor.position++));
/*1129*/        return ((Type) (obj));
            }

            private static BadBytecode error(String s)
            {
/*1133*/        return new BadBytecode((new StringBuilder("bad signature: ")).append(s).toString());
            }

            public static final String tag = "Signature";

}
