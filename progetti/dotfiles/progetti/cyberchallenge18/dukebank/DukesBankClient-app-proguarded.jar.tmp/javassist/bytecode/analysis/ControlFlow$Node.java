// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;


// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

public static class parent
{

            public String toString()
            {
                StringBuffer stringbuffer;
/* 342*/        (stringbuffer = new StringBuffer()).append("Node[pos=").append(block().position());
/* 344*/        stringbuffer.append(", parent=");
/* 345*/        stringbuffer.append(parent != null ? Integer.toString(parent.block().position()) : "*");
/* 346*/        stringbuffer.append(", children{");
/* 347*/        for(int i = 0; i < children.length; i++)
/* 348*/            stringbuffer.append(children[i].block().position()).append(", ");

/* 350*/        stringbuffer.append("}]");
/* 351*/        return stringbuffer.toString();
            }

            public  block()
            {
/* 357*/        return block;
            }

            public block parent()
            {
/* 362*/        return parent;
            }

            public int children()
            {
/* 367*/        return children.length;
            }

            public children child(int i)
            {
/* 374*/        return children[i];
            }

            int makeDepth1stTree(s s, boolean aflag[], int i, int ai[], s s1)
            {
/* 382*/        int j = block.index;
/* 383*/        if(aflag[j])
/* 384*/            return i;
/* 386*/        aflag[j] = true;
/* 387*/        parent = s;
/* 388*/        if((s = s1.exits(this)) != null)
                {
/* 390*/            for(int k = 0; k < s.length; k++)
                    {
                        s s2;
/* 391*/                i = (s2 = s1.node(s[k])).makeDepth1stTree(this, aflag, i, ai, s1);
                    }

                }
/* 395*/        ai[j] = i++;
/* 396*/        return i;
            }

            boolean makeDominatorTree(boolean aflag[], int ai[], s s)
            {
/* 400*/        int i = block.index;
/* 401*/        if(aflag[i])
/* 402*/            return false;
/* 404*/        aflag[i] = true;
/* 405*/        i = 0;
                javassist.bytecode.stackmap.BasicBlock abasicblock[];
/* 406*/        if((abasicblock = s.exits(this)) != null)
                {
/* 408*/            for(int j = 0; j < abasicblock.length; j++)
                    {
                        s s1;
/* 409*/                if((s1 = s.node(abasicblock[j])).makeDominatorTree(aflag, ai, s))
/* 411*/                    i = 1;
                    }

                }
                javassist.bytecode.stackmap.BasicBlock abasicblock1[];
/* 414*/        if((abasicblock1 = s.entrances(this)) != null)
                {
/* 416*/            for(int k = 0; k < abasicblock1.length; k++)
/* 417*/                if(parent != null && (aflag = getAncestor(parent, s.node(abasicblock1[k]), ai)) != parent)
                        {
/* 420*/                    parent = aflag;
/* 421*/                    i = 1;
                        }

                }
/* 426*/        return i;
            }

            private static parent getAncestor(parent parent1, parent parent2, int ai[])
            {
/* 430*/        while(parent1 != parent2) 
                {
/* 431*/            if(ai[parent1.block.index] < ai[parent2.block.index])
/* 432*/                parent1 = parent1.parent;
/* 434*/            else
/* 434*/                parent2 = parent2.parent;
/* 436*/            if(parent1 == null || parent2 == null)
/* 437*/                return null;
                }
/* 440*/        return parent1;
            }

            private static void setChildren(parent aparent[])
            {
                int i;
/* 444*/        int ai[] = new int[i = aparent.length];
/* 446*/        for(int j = 0; j < i; j++)
/* 447*/            ai[j] = 0;

/* 449*/        for(int k = 0; k < i; k++)
                {
                    parent parent1;
/* 450*/            if((parent1 = aparent[k].parent) != null)
/* 452*/                ai[parent1.block.index]++;
                }

/* 455*/        for(int l = 0; l < i; l++)
/* 456*/            aparent[l].children = new children[ai[l]];

/* 458*/        for(int i1 = 0; i1 < i; i1++)
/* 459*/            ai[i1] = 0;

/* 461*/        for(int j1 = 0; j1 < i; j1++)
                {
                    parent parent2;
                    parent parent3;
/* 462*/            if((parent3 = (parent2 = aparent[j1]).parent) != null)
/* 465*/                parent3.children[ai[parent3.block.index]++] = parent2;
                }

            }

            private  block;
            private  parent;
            private  children[];



            ( )
            {
/* 334*/        block = ;
/* 335*/        parent = null;
            }
}
