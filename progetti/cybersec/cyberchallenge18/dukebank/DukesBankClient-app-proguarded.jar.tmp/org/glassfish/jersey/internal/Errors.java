// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Errors.java

package org.glassfish.jersey.internal;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages

public class Errors
{
    public static class ErrorMessage
    {

                public Severity getSeverity()
                {
/* 486*/            return severity;
                }

                public String getMessage()
                {
/* 495*/            return message;
                }

                public Object getSource()
                {
/* 506*/            return source;
                }

                public boolean equals(Object obj)
                {
/* 511*/            if(this == obj)
/* 512*/                return true;
/* 514*/            if(obj == null || getClass() != obj.getClass())
/* 515*/                return false;
/* 518*/            obj = (ErrorMessage)obj;
/* 520*/            if(message == null ? ((ErrorMessage) (obj)).message != null : !message.equals(((ErrorMessage) (obj)).message))
/* 521*/                return false;
/* 523*/            if(severity != ((ErrorMessage) (obj)).severity)
/* 524*/                return false;
/* 526*/            return source == null ? ((ErrorMessage) (obj)).source == null : source.equals(((ErrorMessage) (obj)).source);
                }

                public int hashCode()
                {
/* 535*/            int i = source == null ? 0 : source.hashCode();
/* 536*/            i = i * 31 + (message == null ? 0 : message.hashCode());
/* 537*/            return i = i * 31 + (severity == null ? 0 : severity.hashCode());
                }

                private final Object source;
                private final String message;
                private final Severity severity;

                private ErrorMessage(Object obj, String s, Severity severity1)
                {
/* 475*/            source = obj;
/* 476*/            message = s;
/* 477*/            severity = severity1;
                }

    }

    public static class ErrorMessagesException extends RuntimeException
    {

                public List getMessages()
                {
/* 461*/            return messages;
                }

                private final List messages;

                private ErrorMessagesException(List list)
                {
/* 452*/            messages = list;
                }

    }


            public static void error(String s, Severity severity)
            {
/*  79*/        error(null, s, severity);
            }

            public static void error(Object obj, String s, Severity severity)
            {
/*  90*/        getInstance().issues.add(new ErrorMessage(obj, s, severity));
            }

            public static void fatal(Object obj, String s)
            {
/* 100*/        error(obj, s, Severity.FATAL);
            }

            public static void warning(Object obj, String s)
            {
/* 110*/        error(obj, s, Severity.WARNING);
            }

            public static void hint(Object obj, String s)
            {
/* 120*/        getInstance().issues.add(new ErrorMessage(obj, s, Severity.HINT));
            }

            private static void processErrors(boolean flag)
            {
                ArrayList arraylist;
/* 131*/        boolean flag1 = logErrors(arraylist = new ArrayList(((Errors)errors.get()).issues));
/* 133*/        if(flag && flag1)
/* 134*/            throw new ErrorMessagesException(arraylist);
/* 136*/        else
/* 136*/            return;
            }

            public static boolean logErrors(boolean flag)
            {
/* 152*/        return logErrors(((Collection) (getInstance()._getErrorMessages(flag))));
            }

            private static boolean logErrors(Collection collection)
            {
/* 164*/        boolean flag = false;
/* 166*/        if(collection.isEmpty()) goto _L2; else goto _L1
_L1:
                StringBuilder stringbuilder;
                StringBuilder stringbuilder1;
                StringBuilder stringbuilder2;
/* 167*/        stringbuilder = new StringBuilder("\n");
/* 168*/        stringbuilder1 = new StringBuilder();
/* 169*/        stringbuilder2 = new StringBuilder();
/* 171*/        collection = collection.iterator();
_L4:
/* 171*/        if(collection.hasNext())
                {
/* 171*/            ErrorMessage errormessage = (ErrorMessage)collection.next();
            static class _cls3
            {

                        static final int $SwitchMap$org$glassfish$jersey$Severity[];

                        static 
                        {
/* 172*/                    $SwitchMap$org$glassfish$jersey$Severity = new int[Severity.values().length];
/* 172*/                    try
                            {
/* 172*/                        $SwitchMap$org$glassfish$jersey$Severity[Severity.FATAL.ordinal()] = 1;
                            }
/* 172*/                    catch(NoSuchFieldError _ex) { }
/* 172*/                    try
                            {
/* 172*/                        $SwitchMap$org$glassfish$jersey$Severity[Severity.WARNING.ordinal()] = 2;
                            }
/* 172*/                    catch(NoSuchFieldError _ex) { }
/* 172*/                    try
                            {
/* 172*/                        $SwitchMap$org$glassfish$jersey$Severity[Severity.HINT.ordinal()] = 3;
                            }
/* 172*/                    catch(NoSuchFieldError _ex) { }
                        }
            }

/* 172*/            switch(_cls3..SwitchMap.org.glassfish.jersey.Severity[errormessage.getSeverity().ordinal()])
                    {
/* 174*/            case 1: // '\001'
/* 174*/                flag = true;
/* 175*/                stringbuilder.append(LocalizationMessages.ERROR_MSG(errormessage.getMessage())).append('\n');
                        break;

/* 178*/            case 2: // '\002'
/* 178*/                stringbuilder1.append(LocalizationMessages.WARNING_MSG(errormessage.getMessage())).append('\n');
                        break;

/* 181*/            case 3: // '\003'
/* 181*/                stringbuilder1.append(LocalizationMessages.HINT_MSG(errormessage.getMessage())).append('\n');
                        break;
                    }
/* 184*/            continue; /* Loop/switch isn't completed */
                }
/* 186*/        if(flag)
                {
/* 187*/            LOGGER.severe(LocalizationMessages.ERRORS_AND_WARNINGS_DETECTED(stringbuilder.append(stringbuilder1).append(stringbuilder2).toString()));
                } else
                {
/* 190*/            if(stringbuilder1.length() > 0)
/* 191*/                LOGGER.warning(LocalizationMessages.WARNINGS_DETECTED(stringbuilder1.toString()));
/* 194*/            if(stringbuilder2.length() > 0)
/* 195*/                LOGGER.config(LocalizationMessages.HINTS_DETECTED(stringbuilder2.toString()));
                }
_L2:
/* 200*/        return flag;
/* 200*/        if(true) goto _L4; else goto _L3
_L3:
            }

            public static boolean fatalIssuesFound()
            {
                ErrorMessage errormessage;
/* 210*/        for(Iterator iterator = getInstance().issues.iterator(); iterator.hasNext();)
/* 210*/            if((errormessage = (ErrorMessage)iterator.next()).getSeverity() == Severity.FATAL)
/* 212*/                return true;

/* 215*/        return false;
            }

            public static Object process(Producer producer)
            {
/* 228*/        return process(producer, false);
            }

            public static Object process(Callable callable)
                throws Exception
            {
/* 242*/        return process(callable, true);
            }

            public static Object processWithException(Producer producer)
            {
/* 255*/        return process(producer, true);
            }

            public static void process(Runnable runnable)
            {
/* 267*/        process(new Producer(runnable) {

                    public final Void call()
                    {
/* 271*/                task.run();
/* 272*/                return null;
                    }

                    public final volatile Object call()
                    {
/* 267*/                return call();
                    }

                    final Runnable val$task;

                    
                    {
/* 267*/                task = runnable;
/* 267*/                super();
                    }
        }, false);
            }

            public static void processWithException(Runnable runnable)
            {
/* 286*/        process(new Producer(runnable) {

                    public final Void call()
                    {
/* 289*/                task.run();
/* 290*/                return null;
                    }

                    public final volatile Object call()
                    {
/* 286*/                return call();
                    }

                    final Runnable val$task;

                    
                    {
/* 286*/                task = runnable;
/* 286*/                super();
                    }
        }, true);
            }

            private static Object process(Producer producer, boolean flag)
            {
/* 297*/        return process(((Callable) (producer)), flag);
/* 298*/        JVM INSTR dup ;
/* 299*/        producer;
/* 299*/        throw ;
/* 300*/        producer;
/* 301*/        throw new RuntimeException(producer);
            }

            private static Object process(Callable callable, boolean flag)
                throws Exception
            {
                Errors errors1;
/* 306*/        if((errors1 = (Errors)errors.get()) == null)
                {
/* 308*/            errors1 = new Errors();
/* 309*/            errors.set(errors1);
                }
/* 311*/        errors1.preProcess();
/* 315*/        callable = ((Callable) (callable.call()));
/* 320*/        errors1.postProcess(flag);
/* 320*/        return callable;
/* 316*/        JVM INSTR dup ;
/* 318*/        callable;
/* 318*/        callable;
/* 320*/        errors1.postProcess(false);
/* 321*/        break MISSING_BLOCK_LABEL_82;
/* 320*/        callable;
/* 320*/        errors1.postProcess(flag);
/* 320*/        throw callable;
/* 323*/        throw callable;
            }

            private static Errors getInstance()
            {
                Errors errors1;
/* 327*/        if((errors1 = (Errors)errors.get()) == null)
/* 330*/            throw new IllegalStateException(LocalizationMessages.NO_ERROR_PROCESSING_IN_SCOPE());
/* 335*/        if(errors1.stack == 0)
                {
/* 336*/            errors.remove();
/* 337*/            throw new IllegalStateException(LocalizationMessages.NO_ERROR_PROCESSING_IN_SCOPE());
                } else
                {
/* 339*/            return errors1;
                }
            }

            public static List getErrorMessages()
            {
/* 348*/        return getErrorMessages(false);
            }

            public static List getErrorMessages(boolean flag)
            {
/* 363*/        return getInstance()._getErrorMessages(flag);
            }

            public static void mark()
            {
/* 370*/        getInstance()._mark();
            }

            public static void unmark()
            {
/* 377*/        getInstance()._unmark();
            }

            public static void reset()
            {
/* 385*/        getInstance()._reset();
            }

            private Errors()
            {
/* 393*/        mark = new ArrayDeque(4);
/* 394*/        stack = 0;
            }

            private void _mark()
            {
/* 397*/        mark.addLast(Integer.valueOf(issues.size()));
            }

            private void _unmark()
            {
/* 401*/        mark.pollLast();
            }

            private void _reset()
            {
                Integer integer;
                int i;
/* 405*/        if((i = (integer = (Integer)mark.pollLast()) != null ? integer.intValue() : -1) >= 0 && i < issues.size())
/* 409*/            issues.subList(i, issues.size()).clear();
            }

            private void preProcess()
            {
/* 414*/        stack++;
            }

            private void postProcess(boolean flag)
            {
/* 418*/        stack--;
/* 420*/        if(stack != 0)
/* 422*/            break MISSING_BLOCK_LABEL_47;
/* 422*/        if(!issues.isEmpty())
/* 423*/            processErrors(flag);
/* 426*/        errors.remove();
/* 427*/        return;
/* 426*/        flag;
/* 426*/        errors.remove();
/* 426*/        throw flag;
            }

            private List _getErrorMessages(boolean flag)
            {
/* 432*/        if(!flag)
/* 433*/            break MISSING_BLOCK_LABEL_71;
/* 433*/        flag = (flag = (Integer)mark.peekLast()) != null ? ((boolean) (flag.intValue())) : -1;
/* 436*/        JVM INSTR iflt 71;
                   goto _L1 _L2
_L1:
/* 436*/        break MISSING_BLOCK_LABEL_34;
_L2:
/* 436*/        break MISSING_BLOCK_LABEL_71;
/* 436*/        if(flag < issues.size())
/* 437*/            return Collections.unmodifiableList(new ArrayList(issues.subList(flag, issues.size())));
/* 441*/        return Collections.unmodifiableList(new ArrayList(issues));
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/Errors.getName());
            private static final ThreadLocal errors = new ThreadLocal();
            private final ArrayList issues = new ArrayList(0);
            private Deque mark;
            private int stack;

}
