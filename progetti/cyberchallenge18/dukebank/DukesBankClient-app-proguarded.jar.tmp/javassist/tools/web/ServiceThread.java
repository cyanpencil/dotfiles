// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Webserver.java

package javassist.tools.web;

import java.io.IOException;
import java.net.Socket;

// Referenced classes of package javassist.tools.web:
//            Webserver

class ServiceThread extends Thread
{

            public ServiceThread(Webserver webserver, Socket socket)
            {
/* 397*/        web = webserver;
/* 398*/        sock = socket;
            }

            public void run()
            {
/* 403*/        try
                {
/* 403*/            web.process(sock);
/* 406*/            return;
                }
/* 405*/        catch(IOException _ex)
                {
/* 407*/            return;
                }
            }

            Webserver web;
            Socket sock;
}
