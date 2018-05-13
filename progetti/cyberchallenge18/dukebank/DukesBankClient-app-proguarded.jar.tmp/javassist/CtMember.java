// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtMember.java

package javassist;


// Referenced classes of package javassist:
//            CtClass, Modifier, CtClassType

public abstract class CtMember
{
    static class Cache extends CtMember
    {

                protected void extendToString(StringBuffer stringbuffer)
                {
                }

                public boolean hasAnnotation(Class class1)
                {
/*  33*/            return false;
                }

                public Object getAnnotation(Class class1)
                    throws ClassNotFoundException
                {
/*  35*/            return null;
                }

                public Object[] getAnnotations()
                    throws ClassNotFoundException
                {
/*  37*/            return null;
                }

                public byte[] getAttribute(String s)
                {
/*  38*/            return null;
                }

                public Object[] getAvailableAnnotations()
                {
/*  39*/            return null;
                }

                public int getModifiers()
                {
/*  40*/            return 0;
                }

                public String getName()
                {
/*  41*/            return null;
                }

                public String getSignature()
                {
/*  42*/            return null;
                }

                public void setAttribute(String s, byte abyte0[])
                {
                }

                public void setModifiers(int i)
                {
                }

                public String getGenericSignature()
                {
/*  45*/            return null;
                }

                public void setGenericSignature(String s)
                {
                }

                CtMember methodHead()
                {
/*  60*/            return this;
                }

                CtMember lastMethod()
                {
/*  61*/            return methodTail;
                }

                CtMember consHead()
                {
/*  62*/            return methodTail;
                }

                CtMember lastCons()
                {
/*  63*/            return consTail;
                }

                CtMember fieldHead()
                {
/*  64*/            return consTail;
                }

                CtMember lastField()
                {
/*  65*/            return fieldTail;
                }

                void addMethod(CtMember ctmember)
                {
/*  68*/            ctmember.next = methodTail.next;
/*  69*/            methodTail.next = ctmember;
/*  70*/            if(methodTail == consTail)
                    {
/*  71*/                consTail = ctmember;
/*  72*/                if(methodTail == fieldTail)
/*  73*/                    fieldTail = ctmember;
                    }
/*  76*/            methodTail = ctmember;
                }

                void addConstructor(CtMember ctmember)
                {
/*  82*/            ctmember.next = consTail.next;
/*  83*/            consTail.next = ctmember;
/*  84*/            if(consTail == fieldTail)
/*  85*/                fieldTail = ctmember;
/*  87*/            consTail = ctmember;
                }

                void addField(CtMember ctmember)
                {
/*  91*/            ctmember.next = this;
/*  92*/            fieldTail.next = ctmember;
/*  93*/            fieldTail = ctmember;
                }

                static int count(CtMember ctmember, CtMember ctmember1)
                {
/*  97*/            int i = 0;
/*  98*/            for(; ctmember != ctmember1; ctmember = ctmember.next)
/*  99*/                i++;

/* 103*/            return i;
                }

                void remove(CtMember ctmember)
                {
/* 107*/            Object obj = this;
/* 109*/            do
                    {
                        CtMember ctmember1;
/* 109*/                if((ctmember1 = ((CtMember) (obj)).next) == this)
/* 110*/                    break;
/* 110*/                if(ctmember1 == ctmember)
                        {
/* 111*/                    obj.next = ctmember1.next;
/* 112*/                    if(ctmember1 == methodTail)
/* 113*/                        methodTail = ((CtMember) (obj));
/* 115*/                    if(ctmember1 == consTail)
/* 116*/                        consTail = ((CtMember) (obj));
/* 118*/                    if(ctmember1 == fieldTail)
                            {
/* 119*/                        fieldTail = ((CtMember) (obj));
/* 119*/                        return;
                            }
/* 124*/                    break;
                        }
/* 124*/                obj = ((CtMember) (obj)).next;
                    } while(true);
                }

                private CtMember methodTail;
                private CtMember consTail;
                private CtMember fieldTail;

                Cache(CtClassType ctclasstype)
                {
/*  53*/            super(ctclasstype);
/*  54*/            methodTail = this;
/*  55*/            consTail = this;
/*  56*/            fieldTail = this;
/*  57*/            fieldTail.next = this;
                }
    }


            protected CtMember(CtClass ctclass)
            {
/* 130*/        declaringClass = ctclass;
/* 131*/        next = null;
            }

            final CtMember next()
            {
/* 134*/        return next;
            }

            void nameReplaced()
            {
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 145*/        (stringbuffer = new StringBuffer(getClass().getName())).append("@");
/* 147*/        stringbuffer.append(Integer.toHexString(hashCode()));
/* 148*/        stringbuffer.append("[");
/* 149*/        stringbuffer.append(Modifier.toString(getModifiers()));
/* 150*/        extendToString(stringbuffer);
/* 151*/        stringbuffer.append("]");
/* 152*/        return stringbuffer.toString();
            }

            protected abstract void extendToString(StringBuffer stringbuffer);

            public CtClass getDeclaringClass()
            {
/* 167*/        return declaringClass;
            }

            public boolean visibleFrom(CtClass ctclass)
            {
                int i;
/* 173*/        if(Modifier.isPublic(i = getModifiers()))
/* 175*/            return true;
/* 176*/        if(Modifier.isPrivate(i))
/* 177*/            return ctclass == declaringClass;
/* 179*/        boolean flag = declaringClass.getPackageName();
/* 180*/        String s = ctclass.getPackageName();
/* 182*/        if(flag == null)
/* 183*/            flag = s == null;
/* 185*/        else
/* 185*/            flag = flag.equals(s);
/* 187*/        if(!flag && Modifier.isProtected(i))
/* 188*/            return ctclass.subclassOf(declaringClass);
/* 190*/        else
/* 190*/            return flag;
            }

            public abstract int getModifiers();

            public abstract void setModifiers(int i);

            public abstract boolean hasAnnotation(Class class1);

            public abstract Object getAnnotation(Class class1)
                throws ClassNotFoundException;

            public abstract Object[] getAnnotations()
                throws ClassNotFoundException;

            public abstract Object[] getAvailableAnnotations();

            public abstract String getName();

            public abstract String getSignature();

            public abstract String getGenericSignature();

            public abstract void setGenericSignature(String s);

            public abstract byte[] getAttribute(String s);

            public abstract void setAttribute(String s, byte abyte0[]);

            CtMember next;
            protected CtClass declaringClass;
}
