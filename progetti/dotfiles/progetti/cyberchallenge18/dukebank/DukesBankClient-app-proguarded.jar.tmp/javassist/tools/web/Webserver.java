// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Webserver.java

package javassist.tools.web;

import java.io.*;
import java.net.*;
import java.util.Date;
import javassist.*;

// Referenced classes of package javassist.tools.web:
//            BadHttpRequest, ServiceThread

public class Webserver
{

            public static void main(String args[])
                throws IOException
            {
/*  79*/        if(args.length == 1)
                {
/*  80*/            (args = new Webserver(args[0])).run();
/*  82*/            return;
                } else
                {
/*  84*/            System.err.println("Usage: java javassist.tools.web.Webserver <port number>");
/*  86*/            return;
                }
            }

            public Webserver(String s)
                throws IOException
            {
/*  94*/        this(Integer.parseInt(s));
            }

            public Webserver(int i)
                throws IOException
            {
/*  56*/        debugDir = null;
/*  72*/        htmlfileBase = null;
/* 103*/        socket = new ServerSocket(i);
/* 104*/        classPool = null;
/* 105*/        translator = null;
            }

            public void setClassPool(ClassPool classpool)
            {
/* 113*/        classPool = classpool;
            }

            public void addTranslator(ClassPool classpool, Translator translator1)
                throws NotFoundException, CannotCompileException
            {
/* 127*/        classPool = classpool;
/* 128*/        translator = translator1;
/* 129*/        translator1.start(classPool);
            }

            public void end()
                throws IOException
            {
/* 136*/        socket.close();
            }

            public void logging(String s)
            {
/* 143*/        System.out.println(s);
            }

            public void logging(String s, String s1)
            {
/* 150*/        System.out.print(s);
/* 151*/        System.out.print(" ");
/* 152*/        System.out.println(s1);
            }

            public void logging(String s, String s1, String s2)
            {
/* 159*/        System.out.print(s);
/* 160*/        System.out.print(" ");
/* 161*/        System.out.print(s1);
/* 162*/        System.out.print(" ");
/* 163*/        System.out.println(s2);
            }

            public void logging2(String s)
            {
/* 170*/        System.out.print("    ");
/* 171*/        System.out.println(s);
            }

            public void run()
            {
/* 178*/        System.err.println("ready to service...");
                ServiceThread servicethread;
/* 181*/        do
/* 181*/            try
                    {
/* 181*/                (servicethread = new ServiceThread(this, socket.accept())).start();
                    }
/* 184*/            catch(IOException ioexception)
                    {
/* 185*/                logging(ioexception.toString());
                    }
/* 185*/        while(true);
            }

            final void process(Socket socket1)
                throws IOException
            {
/* 190*/        BufferedInputStream bufferedinputstream = new BufferedInputStream(socket1.getInputStream());
/* 191*/        String s = readLine(bufferedinputstream);
/* 192*/        logging(socket1.getInetAddress().getHostName(), (new Date()).toString(), s);
/* 194*/        while(skipLine(bufferedinputstream) > 0) ;
/* 197*/        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(socket1.getOutputStream());
/* 199*/        try
                {
/* 199*/            doReply(bufferedinputstream, bufferedoutputstream, s);
                }
/* 201*/        catch(BadHttpRequest badhttprequest)
                {
/* 202*/            replyError(bufferedoutputstream, badhttprequest);
                }
/* 205*/        bufferedoutputstream.flush();
/* 206*/        bufferedinputstream.close();
/* 207*/        bufferedoutputstream.close();
/* 208*/        socket1.close();
            }

            private String readLine(InputStream inputstream)
                throws IOException
            {
/* 212*/        StringBuffer stringbuffer = new StringBuffer();
                int i;
/* 214*/        while((i = inputstream.read()) >= 0 && i != 13) 
/* 215*/            stringbuffer.append((char)i);
/* 217*/        inputstream.read();
/* 218*/        return stringbuffer.toString();
            }

            private int skipLine(InputStream inputstream)
                throws IOException
            {
                int i;
                int j;
/* 223*/        for(j = 0; (i = inputstream.read()) >= 0 && i != 13; j++);
/* 227*/        inputstream.read();
/* 228*/        return j;
            }

            public void doReply(InputStream inputstream, OutputStream outputstream, String s)
                throws IOException, BadHttpRequest
            {
                String s1;
                String s2;
/* 244*/        if(s.startsWith("GET /"))
/* 245*/            s1 = s2 = s.substring(5, s.indexOf(' ', 5));
/* 247*/        else
/* 247*/            throw new BadHttpRequest();
/* 249*/        if(s1.endsWith(".class"))
/* 250*/            s = 2;
/* 251*/        else
/* 251*/        if(s1.endsWith(".html") || s1.endsWith(".htm"))
/* 252*/            s = 1;
/* 253*/        else
/* 253*/        if(s1.endsWith(".gif"))
/* 254*/            s = 3;
/* 255*/        else
/* 255*/        if(s1.endsWith(".jpg"))
/* 256*/            s = 4;
/* 258*/        else
/* 258*/            s = 5;
/* 260*/        inputstream = s1.length();
/* 261*/        if(s == 2 && letUsersSendClassfile(outputstream, s1, inputstream))
/* 263*/            return;
/* 265*/        checkFilename(s1, inputstream);
/* 266*/        if(htmlfileBase != null)
/* 267*/            s1 = (new StringBuilder()).append(htmlfileBase).append(s1).toString();
/* 269*/        if(File.separatorChar != '/')
/* 270*/            s1 = s1.replace('/', File.separatorChar);
/* 272*/        if((inputstream = new File(s1)).canRead())
                {
/* 274*/            sendHeader(outputstream, inputstream.length(), s);
/* 275*/            s = new FileInputStream(inputstream);
/* 276*/            byte abyte0[] = new byte[4096];
/* 278*/            while((inputstream = s.read(abyte0)) > 0) 
/* 282*/                outputstream.write(abyte0, 0, inputstream);
/* 285*/            s.close();
/* 286*/            return;
                }
/* 292*/        if(s == 2 && (s = getClass().getResourceAsStream((new StringBuilder("/")).append(s2).toString())) != null)
                {
/* 296*/            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 297*/            byte abyte1[] = new byte[4096];
/* 299*/            while((inputstream = s.read(abyte1)) > 0) 
/* 303*/                bytearrayoutputstream.write(abyte1, 0, inputstream);
/* 306*/            inputstream = bytearrayoutputstream.toByteArray();
/* 307*/            sendHeader(outputstream, inputstream.length, 2);
/* 308*/            outputstream.write(inputstream);
/* 309*/            s.close();
/* 310*/            return;
                } else
                {
/* 314*/            throw new BadHttpRequest();
                }
            }

            private void checkFilename(String s, int i)
                throws BadHttpRequest
            {
/* 320*/        for(int j = 0; j < i; j++)
                {
                    char c;
/* 321*/            if(!Character.isJavaIdentifierPart(c = s.charAt(j)) && c != '.' && c != '/')
/* 323*/                throw new BadHttpRequest();
                }

/* 326*/        if(s.indexOf("..") >= 0)
/* 327*/            throw new BadHttpRequest();
/* 328*/        else
/* 328*/            return;
            }

            private boolean letUsersSendClassfile(OutputStream outputstream, String s, int i)
                throws IOException, BadHttpRequest
            {
/* 334*/        if(classPool == null)
/* 335*/            return false;
/* 338*/        s = s.substring(0, i - 6).replace('/', '.');
/* 341*/        try
                {
/* 341*/            if(translator != null)
/* 342*/                translator.onLoad(classPool, s);
/* 344*/            s = (i = classPool.get(s)).toBytecode();
/* 346*/            if(debugDir != null)
/* 347*/                i.writeFile(debugDir);
                }
                // Misplaced declaration of an exception variable
/* 349*/        catch(int i)
                {
/* 350*/            throw new BadHttpRequest(i);
                }
/* 353*/        sendHeader(outputstream, s.length, 2);
/* 354*/        outputstream.write(s);
/* 355*/        return true;
            }

            private void sendHeader(OutputStream outputstream, long l, int i)
                throws IOException
            {
/* 361*/        outputstream.write("HTTP/1.0 200 OK".getBytes());
/* 362*/        outputstream.write(endofline);
/* 363*/        outputstream.write("Content-Length: ".getBytes());
/* 364*/        outputstream.write(Long.toString(l).getBytes());
/* 365*/        outputstream.write(endofline);
/* 366*/        if(i == 2)
/* 367*/            outputstream.write("Content-Type: application/octet-stream".getBytes());
/* 368*/        else
/* 368*/        if(i == 1)
/* 369*/            outputstream.write("Content-Type: text/html".getBytes());
/* 370*/        else
/* 370*/        if(i == 3)
/* 371*/            outputstream.write("Content-Type: image/gif".getBytes());
/* 372*/        else
/* 372*/        if(i == 4)
/* 373*/            outputstream.write("Content-Type: image/jpg".getBytes());
/* 374*/        else
/* 374*/        if(i == 5)
/* 375*/            outputstream.write("Content-Type: text/plain".getBytes());
/* 377*/        outputstream.write(endofline);
/* 378*/        outputstream.write(endofline);
            }

            private void replyError(OutputStream outputstream, BadHttpRequest badhttprequest)
                throws IOException
            {
/* 384*/        logging2((new StringBuilder("bad request: ")).append(badhttprequest.toString()).toString());
/* 385*/        outputstream.write("HTTP/1.0 400 Bad Request".getBytes());
/* 386*/        outputstream.write(endofline);
/* 387*/        outputstream.write(endofline);
/* 388*/        outputstream.write("<H1>Bad Request</H1>".getBytes());
            }

            private ServerSocket socket;
            private ClassPool classPool;
            protected Translator translator;
            private static final byte endofline[] = {
/*  42*/        13, 10
            };
            private static final int typeHtml = 1;
            private static final int typeClass = 2;
            private static final int typeGif = 3;
            private static final int typeJpeg = 4;
            private static final int typeText = 5;
            public String debugDir;
            public String htmlfileBase;

}
