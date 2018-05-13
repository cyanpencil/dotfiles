// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtMember.java

package javassist;


// Referenced classes of package javassist:
//            CtMember, CtClassType

static class fieldTail extends CtMember
{

            protected void extendToString(StringBuffer stringbuffer)
            {
            }

            public boolean hasAnnotation(Class class1)
            {
/*  33*/        return false;
            }

            public Object getAnnotation(Class class1)
                throws ClassNotFoundException
            {
/*  35*/        return null;
            }

            public Object[] getAnnotations()
                throws ClassNotFoundException
            {
/*  37*/        return null;
            }

            public byte[] getAttribute(String s)
            {
/*  38*/        return null;
            }

            public Object[] getAvailableAnnotations()
            {
/*  39*/        return null;
            }

            public int getModifiers()
            {
/*  40*/        return 0;
            }

            public String getName()
            {
/*  41*/        return null;
            }

            public String getSignature()
            {
/*  42*/        return null;
            }

            public void setAttribute(String s, byte abyte0[])
            {
            }

            public void setModifiers(int i)
            {
            }

            public String getGenericSignature()
            {
/*  45*/        return null;
            }

            public void setGenericSignature(String s)
            {
            }

            CtMember methodHead()
            {
/*  60*/        return this;
            }

            CtMember lastMethod()
            {
/*  61*/        return methodTail;
            }

            CtMember consHead()
            {
/*  62*/        return methodTail;
            }

            CtMember lastCons()
            {
/*  63*/        return consTail;
            }

            CtMember fieldHead()
            {
/*  64*/        return consTail;
            }

            CtMember lastField()
            {
/*  65*/        return fieldTail;
            }

            void addMethod(CtMember ctmember)
            {
/*  68*/        ctmember.next = methodTail.next;
/*  69*/        methodTail.next = ctmember;
/*  70*/        if(methodTail == consTail)
                {
/*  71*/            consTail = ctmember;
/*  72*/            if(methodTail == fieldTail)
/*  73*/                fieldTail = ctmember;
                }
/*  76*/        methodTail = ctmember;
            }

            void addConstructor(CtMember ctmember)
            {
/*  82*/        ctmember.next = consTail.next;
/*  83*/        consTail.next = ctmember;
/*  84*/        if(consTail == fieldTail)
/*  85*/            fieldTail = ctmember;
/*  87*/        consTail = ctmember;
            }

            void addField(CtMember ctmember)
            {
/*  91*/        ctmember.next = this;
/*  92*/        fieldTail.next = ctmember;
/*  93*/        fieldTail = ctmember;
            }

            static int count(CtMember ctmember, CtMember ctmember1)
            {
/*  97*/        int i = 0;
/*  98*/        for(; ctmember != ctmember1; ctmember = ctmember.next)
/*  99*/            i++;

/* 103*/        return i;
            }

            void remove(CtMember ctmember)
            {
/* 107*/        Object obj = this;
/* 109*/        do
                {
                    CtMember ctmember1;
/* 109*/            if((ctmember1 = ((CtMember) (obj)).next) == this)
/* 110*/                break;
/* 110*/            if(ctmember1 == ctmember)
                    {
/* 111*/                obj.next = ctmember1.next;
/* 112*/                if(ctmember1 == methodTail)
/* 113*/                    methodTail = ((CtMember) (obj));
/* 115*/                if(ctmember1 == consTail)
/* 116*/                    consTail = ((CtMember) (obj));
/* 118*/                if(ctmember1 == fieldTail)
                        {
/* 119*/                    fieldTail = ((CtMember) (obj));
/* 119*/                    return;
                        }
/* 124*/                break;
                    }
/* 124*/            obj = ((CtMember) (obj)).next;
                } while(true);
            }

            private CtMember methodTail;
            private CtMember consTail;
            private CtMember fieldTail;

            ception(CtClassType ctclasstype)
            {
/*  53*/        super(ctclasstype);
/*  54*/        methodTail = this;
/*  55*/        consTail = this;
/*  56*/        fieldTail = this;
/*  57*/        fieldTail.next = this;
            }
}
