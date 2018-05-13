// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExtendedReqRespBodyMethodProcessor.java

package com.owlike.genson.ext.spring;

import com.owlike.genson.ThreadLocalHolder;
import java.io.IOException;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class ExtendedReqRespBodyMethodProcessor extends RequestResponseBodyMethodProcessor
{

            public ExtendedReqRespBodyMethodProcessor(List list)
            {
/*  19*/        super(list);
            }

            public Object resolveArgument(MethodParameter methodparameter, ModelAndViewContainer modelandviewcontainer, NativeWebRequest nativewebrequest, WebDataBinderFactory webdatabinderfactory)
                throws Exception
            {
/*  27*/        ThreadLocalHolder.store("__GENSON$method_param", methodparameter);
/*  28*/        methodparameter = ((MethodParameter) (super.resolveArgument(methodparameter, modelandviewcontainer, nativewebrequest, webdatabinderfactory)));
/*  30*/        ThreadLocalHolder.remove("__GENSON$method_param", org/springframework/core/MethodParameter);
/*  31*/        break MISSING_BLOCK_LABEL_39;
/*  30*/        methodparameter;
/*  30*/        ThreadLocalHolder.remove("__GENSON$method_param", org/springframework/core/MethodParameter);
/*  30*/        throw methodparameter;
/*  33*/        return methodparameter;
            }

            public void handleReturnValue(Object obj, MethodParameter methodparameter, ModelAndViewContainer modelandviewcontainer, NativeWebRequest nativewebrequest)
                throws IOException, HttpMediaTypeNotAcceptableException
            {
/*  42*/        ThreadLocalHolder.store("__GENSON$return_param", methodparameter);
/*  43*/        super.handleReturnValue(obj, methodparameter, modelandviewcontainer, nativewebrequest);
/*  45*/        ThreadLocalHolder.remove("__GENSON$return_param", org/springframework/core/MethodParameter);
/*  46*/        return;
/*  45*/        obj;
/*  45*/        ThreadLocalHolder.remove("__GENSON$return_param", org/springframework/core/MethodParameter);
/*  45*/        throw obj;
            }
}
