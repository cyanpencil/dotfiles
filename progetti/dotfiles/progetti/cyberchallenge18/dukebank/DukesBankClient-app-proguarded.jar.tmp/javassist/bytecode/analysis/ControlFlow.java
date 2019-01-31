// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import java.util.ArrayList;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.*;
import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            Analyzer, Frame

public class ControlFlow
{
    public static class Catcher
    {

                public Block block()
                {
/* 485*/            return node;
                }

                public String type()
                {
/* 492*/            if(typeIndex == 0)
/* 493*/                return "java.lang.Throwable";
/* 495*/            else
/* 495*/                return node.method.getConstPool().getClassInfo(typeIndex);
                }

                private Block node;
                private int typeIndex;

                Catcher(javassist.bytecode.stackmap.BasicBlock.Catch catch1)
                {
/* 478*/            node = (Block)catch1.body;
/* 479*/            typeIndex = catch1.typeIndex;
                }
    }

    public static class Node
    {

                public String toString()
                {
                    StringBuffer stringbuffer;
/* 342*/            (stringbuffer = new StringBuffer()).append("Node[pos=").append(block().position());
/* 344*/            stringbuffer.append(", parent=");
/* 345*/            stringbuffer.append(parent != null ? Integer.toString(parent.block().position()) : "*");
/* 346*/            stringbuffer.append(", children{");
/* 347*/            for(int i = 0; i < children.length; i++)
/* 348*/                stringbuffer.append(children[i].block().position()).append(", ");

/* 350*/            stringbuffer.append("}]");
/* 351*/            return stringbuffer.toString();
                }

                public Block block()
                {
/* 357*/            return block;
                }

                public Node parent()
                {
/* 362*/            return parent;
                }

                public int children()
                {
/* 367*/            return children.length;
                }

                public Node child(int i)
                {
/* 374*/            return children[i];
                }

                int makeDepth1stTree(Node node, boolean aflag[], int i, int ai[], Access access)
                {
/* 382*/            int j = block.index;
/* 383*/            if(aflag[j])
/* 384*/                return i;
/* 386*/            aflag[j] = true;
/* 387*/            parent = node;
/* 388*/            if((node = access.exits(this)) != null)
                    {
/* 390*/                for(int k = 0; k < node.length; k++)
                        {
                            Node node1;
/* 391*/                    i = (node1 = access.node(node[k])).makeDepth1stTree(this, aflag, i, ai, access);
                        }

                    }
/* 395*/            ai[j] = i++;
/* 396*/            return i;
                }

                boolean makeDominatorTree(boolean aflag[], int ai[], Access access)
                {
/* 400*/            int i = block.index;
/* 401*/            if(aflag[i])
/* 402*/                return false;
/* 404*/            aflag[i] = true;
/* 405*/            i = 0;
                    BasicBlock abasicblock[];
/* 406*/            if((abasicblock = access.exits(this)) != null)
                    {
/* 408*/                for(int j = 0; j < abasicblock.length; j++)
                        {
                            Node node;
/* 409*/                    if((node = access.node(abasicblock[j])).makeDominatorTree(aflag, ai, access))
/* 411*/                        i = 1;
                        }

                    }
                    BasicBlock abasicblock1[];
/* 414*/            if((abasicblock1 = access.entrances(this)) != null)
                    {
/* 416*/                for(int k = 0; k < abasicblock1.length; k++)
/* 417*/                    if(parent != null && (aflag = getAncestor(parent, access.node(abasicblock1[k]), ai)) != parent)
                            {
/* 420*/                        parent = aflag;
/* 421*/                        i = 1;
                            }

                    }
/* 426*/            return i;
                }

                private static Node getAncestor(Node node, Node node1, int ai[])
                {
/* 430*/            while(node != node1) 
                    {
/* 431*/                if(ai[node.block.index] < ai[node1.block.index])
/* 432*/                    node = node.parent;
/* 434*/                else
/* 434*/                    node1 = node1.parent;
/* 436*/                if(node == null || node1 == null)
/* 437*/                    return null;
                    }
/* 440*/            return node;
                }

                private static void setChildren(Node anode[])
                {
                    int i;
/* 444*/            int ai[] = new int[i = anode.length];
/* 446*/            for(int j = 0; j < i; j++)
/* 447*/                ai[j] = 0;

/* 449*/            for(int k = 0; k < i; k++)
                    {
                        Node node;
/* 450*/                if((node = anode[k].parent) != null)
/* 452*/                    ai[node.block.index]++;
                    }

/* 455*/            for(int l = 0; l < i; l++)
/* 456*/                anode[l].children = new Node[ai[l]];

/* 458*/            for(int i1 = 0; i1 < i; i1++)
/* 459*/                ai[i1] = 0;

/* 461*/            for(int j1 = 0; j1 < i; j1++)
                    {
                        Node node1;
                        Node node2;
/* 462*/                if((node2 = (node1 = anode[j1]).parent) != null)
/* 465*/                    node2.children[ai[node2.block.index]++] = node1;
                    }

                }

                private Block block;
                private Node parent;
                private Node children[];



                Node(Block block1)
                {
/* 334*/            block = block1;
/* 335*/            parent = null;
                }
    }

    static abstract class Access
    {

                Node node(BasicBlock basicblock)
                {
/* 320*/            return all[((Block)basicblock).index];
                }

                abstract BasicBlock[] exits(Node node1);

                abstract BasicBlock[] entrances(Node node1);

                Node all[];

                Access(Node anode[])
                {
/* 319*/            all = anode;
                }
    }

    public static class Block extends BasicBlock
    {

                protected void toString2(StringBuffer stringbuffer)
                {
/* 245*/            super.toString2(stringbuffer);
/* 246*/            stringbuffer.append(", incoming{");
/* 247*/            for(int i = 0; i < entrances.length; i++)
/* 248*/                stringbuffer.append(entrances[i].position).append(", ");

/* 250*/            stringbuffer.append("}");
                }

                BasicBlock[] getExit()
                {
/* 253*/            return exit;
                }

                public int index()
                {
/* 262*/            return index;
                }

                public int position()
                {
/* 268*/            return position;
                }

                public int length()
                {
/* 273*/            return length;
                }

                public int incomings()
                {
/* 278*/            return incoming;
                }

                public Block incoming(int i)
                {
/* 284*/            return entrances[i];
                }

                public int exits()
                {
/* 291*/            if(exit == null)
/* 291*/                return 0;
/* 291*/            else
/* 291*/                return exit.length;
                }

                public Block exit(int i)
                {
/* 299*/            return (Block)exit[i];
                }

                public Catcher[] catchers()
                {
/* 306*/            ArrayList arraylist = new ArrayList();
/* 307*/            for(javassist.bytecode.stackmap.BasicBlock.Catch catch1 = toCatch; catch1 != null; catch1 = catch1.next)
/* 309*/                arraylist.add(new Catcher(catch1));

/* 313*/            return (Catcher[])arraylist.toArray(new Catcher[arraylist.size()]);
                }

                public Object clientData;
                int index;
                MethodInfo method;
                Block entrances[];

                Block(int i, MethodInfo methodinfo)
                {
/* 240*/            super(i);
/* 233*/            clientData = null;
/* 241*/            method = methodinfo;
                }
    }


            public ControlFlow(CtMethod ctmethod)
                throws BadBytecode
            {
/*  56*/        this(ctmethod.getDeclaringClass(), ctmethod.getMethodInfo2());
            }

            public ControlFlow(CtClass ctclass, MethodInfo methodinfo)
                throws BadBytecode
            {
/*  63*/        clazz = ctclass;
/*  64*/        methodInfo = methodinfo;
/*  65*/        frames = null;
/*  66*/        basicBlocks = (Block[])(new javassist.bytecode.stackmap.BasicBlock.Maker() {

                    protected BasicBlock makeBlock(int l)
                    {
/*  68*/                return new Block(l, methodInfo);
                    }

                    protected BasicBlock[] makeArray(int l)
                    {
/*  71*/                return new Block[l];
                    }

                    final ControlFlow this$0;

                    
                    {
/*  66*/                this$0 = ControlFlow.this;
/*  66*/                super();
                    }
        }).make(methodinfo);
/*  74*/        methodinfo = new int[ctclass = basicBlocks.length];
/*  76*/        for(int i = 0; i < ctclass; i++)
                {
                    Block block;
/*  77*/            (block = basicBlocks[i]).index = i;
/*  79*/            block.entrances = new Block[block.incomings()];
/*  80*/            methodinfo[i] = 0;
                }

/*  83*/        for(int j = 0; j < ctclass; j++)
                {
/*  84*/            Block block1 = basicBlocks[j];
/*  85*/            for(int k = 0; k < block1.exits(); k++)
                    {
                        Block block2;
/*  86*/                (block2 = block1.exit(k)).entrances[methodinfo[block2.index]++] = block1;
                    }

                }

            }

            public Block[] basicBlocks()
            {
/*  96*/        return basicBlocks;
            }

            public Frame frameAt(int i)
                throws BadBytecode
            {
/* 108*/        if(frames == null)
/* 109*/            frames = (new Analyzer()).analyze(clazz, methodInfo);
/* 111*/        return frames[i];
            }

            public Node[] dominatorTree()
            {
                int i;
/* 135*/        if((i = basicBlocks.length) == 0)
/* 137*/            return null;
/* 139*/        Node anode[] = new Node[i];
/* 140*/        boolean aflag[] = new boolean[i];
/* 141*/        int ai[] = new int[i];
/* 142*/        for(int j = 0; j < i; j++)
                {
/* 143*/            anode[j] = new Node(basicBlocks[j]);
/* 144*/            aflag[j] = false;
                }

/* 147*/        Access access = new Access(anode) {

                    BasicBlock[] exits(Node node)
                    {
/* 148*/                return node.block.getExit();
                    }

                    BasicBlock[] entrances(Node node)
                    {
/* 149*/                return node.block.entrances;
                    }

                    final ControlFlow this$0;

                    
                    {
/* 147*/                this$0 = ControlFlow.this;
/* 147*/                super(anode);
                    }
        };
/* 151*/        anode[0].makeDepth1stTree(null, aflag, 0, ai, access);
/* 153*/        do
                {
/* 153*/            for(int k = 0; k < i; k++)
/* 154*/                aflag[k] = false;

                } while(anode[0].makeDominatorTree(aflag, ai, access));
/* 156*/        Node.setChildren(anode);
/* 157*/        return anode;
            }

            public Node[] postDominatorTree()
            {
                int i;
/* 181*/        if((i = basicBlocks.length) == 0)
/* 183*/            return null;
/* 185*/        Node anode[] = new Node[i];
/* 186*/        boolean aflag[] = new boolean[i];
/* 187*/        int ai[] = new int[i];
/* 188*/        for(int j = 0; j < i; j++)
                {
/* 189*/            anode[j] = new Node(basicBlocks[j]);
/* 190*/            aflag[j] = false;
                }

/* 193*/        Access access = new Access(anode) {

                    BasicBlock[] exits(Node node)
                    {
/* 194*/                return node.block.entrances;
                    }

                    BasicBlock[] entrances(Node node)
                    {
/* 195*/                return node.block.getExit();
                    }

                    final ControlFlow this$0;

                    
                    {
/* 193*/                this$0 = ControlFlow.this;
/* 193*/                super(anode);
                    }
        };
/* 198*/        int k = 0;
/* 199*/        for(int j1 = 0; j1 < i; j1++)
/* 200*/            if(anode[j1].block.exits() == 0)
/* 201*/                k = anode[j1].makeDepth1stTree(null, aflag, k, ai, access);

                boolean flag;
/* 205*/        do
                {
/* 205*/            for(int l = 0; l < i; l++)
/* 206*/                aflag[l] = false;

/* 208*/            flag = false;
/* 209*/            for(int i1 = 0; i1 < i; i1++)
/* 210*/                if(anode[i1].block.exits() == 0 && anode[i1].makeDominatorTree(aflag, ai, access))
/* 212*/                    flag = true;

                } while(flag);
/* 215*/        Node.setChildren(anode);
/* 216*/        return anode;
            }

            private CtClass clazz;
            private MethodInfo methodInfo;
            private Block basicBlocks[];
            private Frame frames[];

}
