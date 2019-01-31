// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AppletServer.java

package javassist.tools.rmi;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import javassist.*;
import javassist.tools.web.BadHttpRequest;
import javassist.tools.web.Webserver;

// Referenced classes of package javassist.tools.rmi:
//            ExportedObject, RemoteRef, StubGenerator

public class AppletServer extends Webserver
{

            public AppletServer(String s)
                throws IOException, NotFoundException, CannotCompileException
            {
/*  54*/        this(Integer.parseInt(s));
            }

            public AppletServer(int i)
                throws IOException, NotFoundException, CannotCompileException
            {
/*  65*/        this(ClassPool.getDefault(), new StubGenerator(), i);
            }

            public AppletServer(int i, ClassPool classpool)
                throws IOException, NotFoundException, CannotCompileException
            {
/*  77*/        this(new ClassPool(classpool), new StubGenerator(), i);
            }

            private AppletServer(ClassPool classpool, StubGenerator stubgenerator, int i)
                throws IOException, NotFoundException, CannotCompileException
            {
/*  83*/        super(i);
/*  84*/        exportedNames = new Hashtable();
/*  85*/        exportedObjects = new Vector();
/*  86*/        stubGen = stubgenerator;
/*  87*/        addTranslator(classpool, stubgenerator);
            }

            public void run()
            {
/*  94*/        super.run();
            }

            public synchronized int exportObject(String s, Object obj)
                throws CannotCompileException
            {
/* 112*/        Class class1 = obj.getClass();
                ExportedObject exportedobject;
/* 113*/        (exportedobject = new ExportedObject()).object = obj;
/* 115*/        exportedobject.methods = class1.getMethods();
/* 116*/        exportedObjects.addElement(exportedobject);
/* 117*/        exportedobject.identifier = exportedObjects.size() - 1;
/* 118*/        if(s != null)
/* 119*/            exportedNames.put(s, exportedobject);
/* 122*/        try
                {
/* 122*/            stubGen.makeProxyClass(class1);
                }
                // Misplaced declaration of an exception variable
/* 124*/        catch(String s)
                {
/* 125*/            throw new CannotCompileException(s);
                }
/* 128*/        return exportedobject.identifier;
            }

            public void doReply(InputStream inputstream, OutputStream outputstream, String s)
                throws IOException, BadHttpRequest
            {
/* 137*/        if(s.startsWith("POST /rmi "))
                {
/* 138*/            processRMI(inputstream, outputstream);
/* 138*/            return;
                }
/* 139*/        if(s.startsWith("POST /lookup "))
                {
/* 140*/            lookupName(s, inputstream, outputstream);
/* 140*/            return;
                } else
                {
/* 142*/            super.doReply(inputstream, outputstream, s);
/* 143*/            return;
                }
            }

            private void processRMI(InputStream inputstream, OutputStream outputstream)
                throws IOException
            {
                int i;
                int j;
                Exception exception1;
                Object obj;
/* 148*/        i = (inputstream = new ObjectInputStream(inputstream)).readInt();
/* 151*/        j = inputstream.readInt();
/* 152*/        exception1 = null;
/* 153*/        obj = null;
/* 155*/        ExportedObject exportedobject = (ExportedObject)exportedObjects.elementAt(i);
/* 157*/        Object aobj[] = readParameters(inputstream);
/* 158*/        obj = convertRvalue(exportedobject.methods[j].invoke(exportedobject.object, aobj));
/* 164*/        break MISSING_BLOCK_LABEL_82;
/* 161*/        JVM INSTR dup ;
                Exception exception;
/* 162*/        exception;
/* 162*/        exception1;
/* 163*/        logging2(exception.toString());
/* 166*/        outputstream.write(okHeader);
/* 167*/        ObjectOutputStream objectoutputstream = new ObjectOutputStream(outputstream);
/* 168*/        if(exception1 != null)
                {
/* 169*/            objectoutputstream.writeBoolean(false);
/* 170*/            objectoutputstream.writeUTF(exception1.toString());
                } else
                {
/* 174*/            try
                    {
/* 174*/                objectoutputstream.writeBoolean(true);
/* 175*/                objectoutputstream.writeObject(obj);
                    }
/* 177*/            catch(NotSerializableException notserializableexception)
                    {
/* 178*/                logging2(notserializableexception.toString());
                    }
/* 180*/            catch(InvalidClassException invalidclassexception)
                    {
/* 181*/                logging2(invalidclassexception.toString());
                    }
                }
/* 184*/        objectoutputstream.flush();
/* 185*/        objectoutputstream.close();
/* 186*/        inputstream.close();
/* 187*/        return;
            }

            private Object[] readParameters(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
                int i;
/* 192*/        Object aobj[] = new Object[i = objectinputstream.readInt()];
/* 194*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 195*/            if((obj = objectinputstream.readObject()) instanceof RemoteRef)
                    {
/* 197*/                obj = (RemoteRef)obj;
/* 198*/                obj = ((ExportedObject) (obj = (ExportedObject)exportedObjects.elementAt(((RemoteRef) (obj)).oid))).object;
                    }
/* 203*/            aobj[j] = obj;
                }

/* 206*/        return aobj;
            }

            private Object convertRvalue(Object obj)
                throws CannotCompileException
            {
/* 212*/        if(obj == null)
/* 213*/            return null;
/* 215*/        String s = obj.getClass().getName();
/* 216*/        if(stubGen.isProxyClass(s))
/* 217*/            return new RemoteRef(exportObject(null, obj), s);
/* 219*/        else
/* 219*/            return obj;
            }

            private void lookupName(String s, InputStream inputstream, OutputStream outputstream)
                throws IOException
            {
/* 225*/        inputstream = DataInputStream.readUTF(s = new ObjectInputStream(inputstream));
/* 227*/        ExportedObject exportedobject = (ExportedObject)exportedNames.get(inputstream);
/* 228*/        outputstream.write(okHeader);
/* 229*/        outputstream = new ObjectOutputStream(outputstream);
/* 230*/        if(exportedobject == null)
                {
/* 231*/            logging2((new StringBuilder()).append(inputstream).append("not found.").toString());
/* 232*/            outputstream.writeInt(-1);
/* 233*/            outputstream.writeUTF("error");
                } else
                {
/* 236*/            logging2(inputstream);
/* 237*/            outputstream.writeInt(exportedobject.identifier);
/* 238*/            outputstream.writeUTF(exportedobject.object.getClass().getName());
                }
/* 241*/        outputstream.flush();
/* 242*/        outputstream.close();
/* 243*/        s.close();
            }

            private StubGenerator stubGen;
            private Hashtable exportedNames;
            private Vector exportedObjects;
            private static final byte okHeader[] = "HTTP/1.0 200 OK\r\n\r\n".getBytes();

}
