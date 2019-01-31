// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ObjectImporter.java

package javassist.tools.rmi;

import java.applet.Applet;
import java.io.*;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.net.URL;

// Referenced classes of package javassist.tools.rmi:
//            ObjectNotFoundException, Proxy, RemoteException, RemoteRef

public class ObjectImporter
    implements Serializable
{

            public ObjectImporter(Applet applet)
            {
/*  80*/        lookupCommand = "POST /lookup HTTP/1.0".getBytes();
/*  81*/        rmiCommand = "POST /rmi HTTP/1.0".getBytes();
/*  92*/        applet = applet.getCodeBase();
/*  93*/        orgServername = servername = applet.getHost();
/*  94*/        orgPort = port = applet.getPort();
            }

            public ObjectImporter(String s, int i)
            {
/*  80*/        lookupCommand = "POST /lookup HTTP/1.0".getBytes();
/*  81*/        rmiCommand = "POST /rmi HTTP/1.0".getBytes();
/* 111*/        orgServername = servername = s;
/* 112*/        orgPort = port = i;
            }

            public Object getObject(String s)
            {
/* 124*/        return lookupObject(s);
/* 126*/        JVM INSTR pop ;
/* 127*/        return null;
            }

            public void setHttpProxy(String s, int i)
            {
/* 136*/        String s1 = (new StringBuilder("POST http://")).append(orgServername).append(":").append(orgPort).toString();
/* 137*/        String s2 = (new StringBuilder()).append(s1).append("/lookup HTTP/1.0").toString();
/* 138*/        lookupCommand = s2.getBytes();
/* 139*/        s2 = (new StringBuilder()).append(s1).append("/rmi HTTP/1.0").toString();
/* 140*/        rmiCommand = s2.getBytes();
/* 141*/        servername = s;
/* 142*/        port = i;
            }

            public Object lookupObject(String s)
                throws ObjectNotFoundException
            {
                int i;
                String s1;
                Socket socket;
                Object obj;
/* 156*/        ((OutputStream) (obj = (socket = new Socket(servername, port)).getOutputStream())).write(lookupCommand);
/* 159*/        ((OutputStream) (obj)).write(endofline);
/* 160*/        ((OutputStream) (obj)).write(endofline);
/* 162*/        ((ObjectOutputStream) (obj = new ObjectOutputStream(((OutputStream) (obj))))).writeUTF(s);
/* 164*/        ((ObjectOutputStream) (obj)).flush();
/* 166*/        Object obj1 = new BufferedInputStream(socket.getInputStream());
/* 167*/        skipHeader(((InputStream) (obj1)));
/* 168*/        i = ((ObjectInputStream) (obj1 = new ObjectInputStream(((InputStream) (obj1))))).readInt();
/* 170*/        s1 = ((ObjectInputStream) (obj1)).readUTF();
/* 171*/        ((ObjectInputStream) (obj1)).close();
/* 172*/        ((ObjectOutputStream) (obj)).close();
/* 173*/        socket.close();
                Exception exception;
/* 175*/        if(i >= 0)
/* 176*/            return createProxy(i, s1);
/* 183*/        else
/* 183*/            throw new ObjectNotFoundException(s);
/* 178*/        JVM INSTR dup ;
/* 179*/        exception;
/* 179*/        printStackTrace();
/* 180*/        throw new ObjectNotFoundException(s, exception);
            }

            private Object createProxy(int i, String s)
                throws Exception
            {
/* 190*/        return (s = (s = Class.forName(s)).getConstructor(proxyConstructorParamTypes)).newInstance(new Object[] {
/* 192*/            this, new Integer(i)
                });
            }

            public Object call(int i, int j, Object aobj[])
                throws RemoteException
            {
/* 225*/        try
                {
/* 225*/            Socket socket = new Socket(servername, port);
                    Object obj;
/* 226*/            ((OutputStream) (obj = new BufferedOutputStream(socket.getOutputStream()))).write(rmiCommand);
/* 229*/            ((OutputStream) (obj)).write(endofline);
/* 230*/            ((OutputStream) (obj)).write(endofline);
/* 232*/            ((ObjectOutputStream) (obj = new ObjectOutputStream(((OutputStream) (obj))))).writeInt(i);
/* 234*/            ((ObjectOutputStream) (obj)).writeInt(j);
/* 235*/            writeParameters(((ObjectOutputStream) (obj)), aobj);
/* 236*/            ((ObjectOutputStream) (obj)).flush();
/* 238*/            i = new BufferedInputStream(socket.getInputStream());
/* 239*/            skipHeader(i);
                    ObjectInputStream objectinputstream;
/* 240*/            i = (objectinputstream = new ObjectInputStream(i)).readBoolean();
/* 242*/            j = null;
/* 243*/            aobj = null;
/* 244*/            if(i != 0)
/* 245*/                j = ((int) (objectinputstream.readObject()));
/* 247*/            else
/* 247*/                aobj = objectinputstream.readUTF();
/* 249*/            objectinputstream.close();
/* 250*/            ((ObjectOutputStream) (obj)).close();
/* 251*/            socket.close();
/* 253*/            if(j instanceof RemoteRef)
                    {
/* 254*/                j = (RemoteRef)j;
/* 255*/                j = ((int) (createProxy(((RemoteRef) (j)).oid, ((RemoteRef) (j)).classname)));
                    }
                }
/* 258*/        catch(ClassNotFoundException classnotfoundexception)
                {
/* 259*/            throw new RemoteException(classnotfoundexception);
                }
/* 261*/        catch(IOException ioexception)
                {
/* 262*/            throw new RemoteException(ioexception);
                }
/* 264*/        catch(Exception exception)
                {
/* 265*/            throw new RemoteException(exception);
                }
/* 268*/        if(i != 0)
/* 269*/            return j;
/* 271*/        else
/* 271*/            throw new RemoteException(((String) (aobj)));
            }

            private void skipHeader(InputStream inputstream)
                throws IOException
            {
                int i;
/* 278*/        do
                {
                    int j;
/* 278*/            for(i = 0; (j = inputstream.read()) >= 0 && j != 13; i++);
/* 282*/            inputstream.read();
                } while(i > 0);
            }

            private void writeParameters(ObjectOutputStream objectoutputstream, Object aobj[])
                throws IOException
            {
/* 289*/        int i = aobj.length;
/* 290*/        objectoutputstream.writeInt(i);
/* 291*/        for(int j = 0; j < i; j++)
/* 292*/            if(aobj[j] instanceof Proxy)
                    {
/* 293*/                Proxy proxy = (Proxy)aobj[j];
/* 294*/                objectoutputstream.writeObject(new RemoteRef(proxy._getObjectId()));
                    } else
                    {
/* 297*/                objectoutputstream.writeObject(aobj[j]);
                    }

            }

            private final byte endofline[] = {
/*  76*/        13, 10
            };
            private String servername;
            private String orgServername;
            private int port;
            private int orgPort;
            protected byte lookupCommand[];
            protected byte rmiCommand[];
            private static final Class proxyConstructorParamTypes[];

            static 
            {
/* 186*/        proxyConstructorParamTypes = (new Class[] {
/* 186*/            javassist/tools/rmi/ObjectImporter, Integer.TYPE
                });
            }
}
