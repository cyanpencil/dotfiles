// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HotSwapper.java

package javassist.util;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.event.*;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.request.MethodEntryRequest;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package javassist.util:
//            Trigger

public class HotSwapper
{

            public HotSwapper(int i)
                throws IOException, IllegalConnectorArgumentsException
            {
/*  95*/        this(Integer.toString(i));
            }

            public HotSwapper(String s)
                throws IOException, IllegalConnectorArgumentsException
            {
/* 106*/        jvm = null;
/* 107*/        request = null;
/* 108*/        newClassFiles = null;
/* 109*/        trigger = new Trigger();
                AttachingConnector attachingconnector;
                Map map;
/* 110*/        ((com.sun.jdi.connect.Connector.Argument)(map = (attachingconnector = (AttachingConnector)findConnector("com.sun.jdi.SocketAttach")).defaultArguments()).get("hostname")).setValue("localhost");
/* 115*/        ((com.sun.jdi.connect.Connector.Argument)map.get("port")).setValue(s);
/* 116*/        jvm = attachingconnector.attach(map);
/* 117*/        s = jvm.eventRequestManager();
/* 118*/        request = methodEntryRequests(s, TRIGGER_NAME);
            }

            private Connector findConnector(String s)
                throws IOException
            {
                Object obj;
                Connector connector;
/* 122*/        for(obj = ((List) (obj = Bootstrap.virtualMachineManager().allConnectors())).iterator(); ((Iterator) (obj)).hasNext();)
/* 125*/            if((connector = (Connector)((Iterator) (obj)).next()).name().equals(s))
/* 127*/                return connector;

/* 131*/        throw new IOException((new StringBuilder("Not found: ")).append(s).toString());
            }

            private static MethodEntryRequest methodEntryRequests(EventRequestManager eventrequestmanager, String s)
            {
/* 137*/        (eventrequestmanager = eventrequestmanager.createMethodEntryRequest()).addClassFilter(s);
/* 139*/        eventrequestmanager.setSuspendPolicy(1);
/* 140*/        return eventrequestmanager;
            }

            private void deleteEventRequest(EventRequestManager eventrequestmanager, MethodEntryRequest methodentryrequest)
            {
/* 147*/        eventrequestmanager.deleteEventRequest(methodentryrequest);
            }

            public void reload(String s, byte abyte0[])
            {
/* 157*/        ReferenceType referencetype = toRefType(s);
                HashMap hashmap;
/* 158*/        (hashmap = new HashMap()).put(referencetype, abyte0);
/* 160*/        reload2(hashmap, s);
            }

            public void reload(Map map)
            {
/* 172*/        map = (map = map.entrySet()).iterator();
/* 174*/        HashMap hashmap = new HashMap();
/* 175*/        String s = null;
                java.util.Map.Entry entry;
/* 176*/        for(; map.hasNext(); hashmap.put(toRefType(s), entry.getValue()))
/* 177*/            s = (String)(entry = (java.util.Map.Entry)map.next()).getKey();

/* 182*/        if(s != null)
/* 183*/            reload2(hashmap, (new StringBuilder()).append(s).append(" etc.").toString());
            }

            private ReferenceType toRefType(String s)
            {
                List list;
/* 187*/        if((list = jvm.classesByName(s)) == null || list.isEmpty())
/* 189*/            throw new RuntimeException((new StringBuilder("no such class: ")).append(s).toString());
/* 191*/        else
/* 191*/            return (ReferenceType)list.get(0);
            }

            private void reload2(Map map, String s)
            {
/* 195*/        synchronized(trigger)
                {
/* 196*/            startDaemon();
/* 197*/            newClassFiles = map;
/* 198*/            request.enable();
/* 199*/            trigger.doSwap();
/* 200*/            request.disable();
/* 201*/            if((map = newClassFiles) != null)
                    {
/* 203*/                newClassFiles = null;
/* 204*/                throw new RuntimeException((new StringBuilder("failed to reload: ")).append(s).toString());
                    }
                }
            }

            private void startDaemon()
            {
/* 210*/        (new Thread() {

                    private void errorMsg(Throwable throwable)
                    {
/* 212*/                System.err.print("Exception in thread \"HotSwap\" ");
/* 213*/                throwable.printStackTrace(System.err);
                    }

                    public void run()
                    {
                        EventSet eventset;
/* 217*/label0:
                        {
/* 217*/                    eventset = null;
/* 219*/                    try
                            {
/* 219*/                        EventIterator eventiterator = (eventset = waitEvent()).eventIterator();
                                com.sun.jdi.event.Event event;
/* 221*/                        do
/* 221*/                            if(!eventiterator.hasNext())
/* 222*/                                break label0;
/* 222*/                        while(!((event = eventiterator.nextEvent()) instanceof MethodEntryEvent));
/* 224*/                        hotswap();
                            }
/* 229*/                    catch(Throwable throwable)
                            {
/* 230*/                        errorMsg(throwable);
                            }
                        }
/* 233*/                try
                        {
/* 233*/                    if(eventset != null)
/* 234*/                        eventset.resume();
/* 238*/                    return;
                        }
/* 236*/                catch(Throwable throwable1)
                        {
/* 237*/                    errorMsg(throwable1);
                        }
                    }

                    final HotSwapper this$0;

                    
                    {
/* 210*/                this$0 = HotSwapper.this;
/* 210*/                super();
                    }
        }).start();
            }

            EventSet waitEvent()
                throws InterruptedException
            {
                EventQueue eventqueue;
/* 244*/        return (eventqueue = jvm.eventQueue()).remove();
            }

            void hotswap()
            {
/* 249*/        Map map = newClassFiles;
/* 250*/        jvm.redefineClasses(map);
/* 251*/        newClassFiles = null;
            }

            private VirtualMachine jvm;
            private MethodEntryRequest request;
            private Map newClassFiles;
            private Trigger trigger;
            private static final String HOST_NAME = "localhost";
            private static final String TRIGGER_NAME = javassist/util/Trigger.getName();

}
