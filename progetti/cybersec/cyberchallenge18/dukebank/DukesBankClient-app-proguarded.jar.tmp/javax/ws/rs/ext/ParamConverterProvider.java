// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParamConverterProvider.java

package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

// Referenced classes of package javax.ws.rs.ext:
//            ParamConverter

public interface ParamConverterProvider
{

    public abstract ParamConverter getConverter(Class class1, Type type, Annotation aannotation[]);
}
