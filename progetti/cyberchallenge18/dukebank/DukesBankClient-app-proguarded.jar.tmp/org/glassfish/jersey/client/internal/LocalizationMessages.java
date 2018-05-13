// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalizationMessages.java

package org.glassfish.jersey.client.internal;

import org.glassfish.jersey.internal.l10n.*;

public final class LocalizationMessages
{

            public LocalizationMessages()
            {
            }

            public static Localizable localizableCLIENT_INSTANCE_CLOSED()
            {
/*  19*/        return messageFactory.getMessage("client.instance.closed", new Object[0]);
            }

            public static String CLIENT_INSTANCE_CLOSED()
            {
/*  27*/        return localizer.localize(localizableCLIENT_INSTANCE_CLOSED());
            }

            public static Localizable localizableCLIENT_TARGET_LINK_NULL()
            {
/*  31*/        return messageFactory.getMessage("client.target.link.null", new Object[0]);
            }

            public static String CLIENT_TARGET_LINK_NULL()
            {
/*  39*/        return localizer.localize(localizableCLIENT_TARGET_LINK_NULL());
            }

            public static Localizable localizableERROR_HTTP_METHOD_ENTITY_NULL(Object obj)
            {
/*  43*/        return messageFactory.getMessage("error.http.method.entity.null", new Object[] {
/*  43*/            obj
                });
            }

            public static String ERROR_HTTP_METHOD_ENTITY_NULL(Object obj)
            {
/*  51*/        return localizer.localize(localizableERROR_HTTP_METHOD_ENTITY_NULL(obj));
            }

            public static Localizable localizableCLIENT_RESPONSE_STATUS_NULL()
            {
/*  55*/        return messageFactory.getMessage("client.response.status.null", new Object[0]);
            }

            public static String CLIENT_RESPONSE_STATUS_NULL()
            {
/*  63*/        return localizer.localize(localizableCLIENT_RESPONSE_STATUS_NULL());
            }

            public static Localizable localizableCHUNKED_INPUT_STREAM_CLOSING_ERROR()
            {
/*  67*/        return messageFactory.getMessage("chunked.input.stream.closing.error", new Object[0]);
            }

            public static String CHUNKED_INPUT_STREAM_CLOSING_ERROR()
            {
/*  75*/        return localizer.localize(localizableCHUNKED_INPUT_STREAM_CLOSING_ERROR());
            }

            public static Localizable localizableIGNORED_ASYNC_THREADPOOL_SIZE(Object obj)
            {
/*  79*/        return messageFactory.getMessage("ignored.async.threadpool.size", new Object[] {
/*  79*/            obj
                });
            }

            public static String IGNORED_ASYNC_THREADPOOL_SIZE(Object obj)
            {
/*  87*/        return localizer.localize(localizableIGNORED_ASYNC_THREADPOOL_SIZE(obj));
            }

            public static Localizable localizableCLIENT_URI_BUILDER_NULL()
            {
/*  91*/        return messageFactory.getMessage("client.uri.builder.null", new Object[0]);
            }

            public static String CLIENT_URI_BUILDER_NULL()
            {
/*  99*/        return localizer.localize(localizableCLIENT_URI_BUILDER_NULL());
            }

            public static Localizable localizableAUTHENTICATION_CREDENTIALS_REQUEST_PASSWORD_UNSUPPORTED()
            {
/* 103*/        return messageFactory.getMessage("authentication.credentials.request.password.unsupported", new Object[0]);
            }

            public static String AUTHENTICATION_CREDENTIALS_REQUEST_PASSWORD_UNSUPPORTED()
            {
/* 111*/        return localizer.localize(localizableAUTHENTICATION_CREDENTIALS_REQUEST_PASSWORD_UNSUPPORTED());
            }

            public static Localizable localizableERROR_REQUEST_CANCELLED()
            {
/* 115*/        return messageFactory.getMessage("error.request.cancelled", new Object[0]);
            }

            public static String ERROR_REQUEST_CANCELLED()
            {
/* 123*/        return localizer.localize(localizableERROR_REQUEST_CANCELLED());
            }

            public static Localizable localizableERROR_HTTP_METHOD_ENTITY_NOT_NULL(Object obj)
            {
/* 127*/        return messageFactory.getMessage("error.http.method.entity.not.null", new Object[] {
/* 127*/            obj
                });
            }

            public static String ERROR_HTTP_METHOD_ENTITY_NOT_NULL(Object obj)
            {
/* 135*/        return localizer.localize(localizableERROR_HTTP_METHOD_ENTITY_NOT_NULL(obj));
            }

            public static Localizable localizableNULL_SSL_CONTEXT()
            {
/* 139*/        return messageFactory.getMessage("null.ssl.context", new Object[0]);
            }

            public static String NULL_SSL_CONTEXT()
            {
/* 147*/        return localizer.localize(localizableNULL_SSL_CONTEXT());
            }

            public static Localizable localizableERROR_LISTENER_CLOSE(Object obj)
            {
/* 151*/        return messageFactory.getMessage("error.listener.close", new Object[] {
/* 151*/            obj
                });
            }

            public static String ERROR_LISTENER_CLOSE(Object obj)
            {
/* 159*/        return localizer.localize(localizableERROR_LISTENER_CLOSE(obj));
            }

            public static Localizable localizableHTTPURLCONNECTION_REPLACES_GET_WITH_ENTITY()
            {
/* 163*/        return messageFactory.getMessage("httpurlconnection.replaces.get.with.entity", new Object[0]);
            }

            public static String HTTPURLCONNECTION_REPLACES_GET_WITH_ENTITY()
            {
/* 171*/        return localizer.localize(localizableHTTPURLCONNECTION_REPLACES_GET_WITH_ENTITY());
            }

            public static Localizable localizableERROR_COMMITTING_OUTPUT_STREAM()
            {
/* 175*/        return messageFactory.getMessage("error.committing.output.stream", new Object[0]);
            }

            public static String ERROR_COMMITTING_OUTPUT_STREAM()
            {
/* 183*/        return localizer.localize(localizableERROR_COMMITTING_OUTPUT_STREAM());
            }

            public static Localizable localizableREQUEST_ENTITY_WRITER_NULL()
            {
/* 187*/        return messageFactory.getMessage("request.entity.writer.null", new Object[0]);
            }

            public static String REQUEST_ENTITY_WRITER_NULL()
            {
/* 195*/        return localizer.localize(localizableREQUEST_ENTITY_WRITER_NULL());
            }

            public static Localizable localizableUSING_FIXED_ASYNC_THREADPOOL(Object obj)
            {
/* 199*/        return messageFactory.getMessage("using.fixed.async.threadpool", new Object[] {
/* 199*/            obj
                });
            }

            public static String USING_FIXED_ASYNC_THREADPOOL(Object obj)
            {
/* 207*/        return localizer.localize(localizableUSING_FIXED_ASYNC_THREADPOOL(obj));
            }

            public static Localizable localizableCLIENT_RESPONSE_RESOLVED_URI_NOT_ABSOLUTE()
            {
/* 211*/        return messageFactory.getMessage("client.response.resolved.uri.not.absolute", new Object[0]);
            }

            public static String CLIENT_RESPONSE_RESOLVED_URI_NOT_ABSOLUTE()
            {
/* 219*/        return localizer.localize(localizableCLIENT_RESPONSE_RESOLVED_URI_NOT_ABSOLUTE());
            }

            public static Localizable localizableREQUEST_ENTITY_ALREADY_WRITTEN()
            {
/* 223*/        return messageFactory.getMessage("request.entity.already.written", new Object[0]);
            }

            public static String REQUEST_ENTITY_ALREADY_WRITTEN()
            {
/* 231*/        return localizer.localize(localizableREQUEST_ENTITY_ALREADY_WRITTEN());
            }

            public static Localizable localizableCLIENT_URI_NULL()
            {
/* 235*/        return messageFactory.getMessage("client.uri.null", new Object[0]);
            }

            public static String CLIENT_URI_NULL()
            {
/* 243*/        return localizer.localize(localizableCLIENT_URI_NULL());
            }

            public static Localizable localizableAUTHENTICATION_CREDENTIALS_MISSING_DIGEST()
            {
/* 247*/        return messageFactory.getMessage("authentication.credentials.missing.digest", new Object[0]);
            }

            public static String AUTHENTICATION_CREDENTIALS_MISSING_DIGEST()
            {
/* 255*/        return localizer.localize(localizableAUTHENTICATION_CREDENTIALS_MISSING_DIGEST());
            }

            public static Localizable localizableCHUNKED_INPUT_CLOSED()
            {
/* 259*/        return messageFactory.getMessage("chunked.input.closed", new Object[0]);
            }

            public static String CHUNKED_INPUT_CLOSED()
            {
/* 267*/        return localizer.localize(localizableCHUNKED_INPUT_CLOSED());
            }

            public static Localizable localizableNULL_KEYSTORE()
            {
/* 271*/        return messageFactory.getMessage("null.keystore", new Object[0]);
            }

            public static String NULL_KEYSTORE()
            {
/* 279*/        return localizer.localize(localizableNULL_KEYSTORE());
            }

            public static Localizable localizableNEGATIVE_INPUT_PARAMETER(Object obj)
            {
/* 283*/        return messageFactory.getMessage("negative.input.parameter", new Object[] {
/* 283*/            obj
                });
            }

            public static String NEGATIVE_INPUT_PARAMETER(Object obj)
            {
/* 291*/        return localizer.localize(localizableNEGATIVE_INPUT_PARAMETER(obj));
            }

            public static Localizable localizableDIGEST_FILTER_QOP_UNSUPPORTED(Object obj)
            {
/* 295*/        return messageFactory.getMessage("digest.filter.qop.unsupported", new Object[] {
/* 295*/            obj
                });
            }

            public static String DIGEST_FILTER_QOP_UNSUPPORTED(Object obj)
            {
/* 303*/        return localizer.localize(localizableDIGEST_FILTER_QOP_UNSUPPORTED(obj));
            }

            public static Localizable localizableCHUNKED_INPUT_MEDIA_TYPE_NULL()
            {
/* 307*/        return messageFactory.getMessage("chunked.input.media.type.null", new Object[0]);
            }

            public static String CHUNKED_INPUT_MEDIA_TYPE_NULL()
            {
/* 315*/        return localizer.localize(localizableCHUNKED_INPUT_MEDIA_TYPE_NULL());
            }

            public static Localizable localizableAUTHENTICATION_CREDENTIALS_MISSING_BASIC()
            {
/* 319*/        return messageFactory.getMessage("authentication.credentials.missing.basic", new Object[0]);
            }

            public static String AUTHENTICATION_CREDENTIALS_MISSING_BASIC()
            {
/* 327*/        return localizer.localize(localizableAUTHENTICATION_CREDENTIALS_MISSING_BASIC());
            }

            public static Localizable localizableRESTRICTED_HEADER_POSSIBLY_IGNORED(Object obj)
            {
/* 331*/        return messageFactory.getMessage("restricted.header.possibly.ignored", new Object[] {
/* 331*/            obj
                });
            }

            public static String RESTRICTED_HEADER_POSSIBLY_IGNORED(Object obj)
            {
/* 339*/        return localizer.localize(localizableRESTRICTED_HEADER_POSSIBLY_IGNORED(obj));
            }

            public static Localizable localizableERROR_SHUTDOWNHOOK_CLOSE(Object obj)
            {
/* 343*/        return messageFactory.getMessage("error.shutdownhook.close", new Object[] {
/* 343*/            obj
                });
            }

            public static String ERROR_SHUTDOWNHOOK_CLOSE(Object obj)
            {
/* 351*/        return localizer.localize(localizableERROR_SHUTDOWNHOOK_CLOSE(obj));
            }

            public static Localizable localizableRESTRICTED_HEADER_PROPERTY_SETTING_TRUE(Object obj)
            {
/* 355*/        return messageFactory.getMessage("restricted.header.property.setting.true", new Object[] {
/* 355*/            obj
                });
            }

            public static String RESTRICTED_HEADER_PROPERTY_SETTING_TRUE(Object obj)
            {
/* 363*/        return localizer.localize(localizableRESTRICTED_HEADER_PROPERTY_SETTING_TRUE(obj));
            }

            public static Localizable localizableNULL_TRUSTSTORE()
            {
/* 367*/        return messageFactory.getMessage("null.truststore", new Object[0]);
            }

            public static String NULL_TRUSTSTORE()
            {
/* 375*/        return localizer.localize(localizableNULL_TRUSTSTORE());
            }

            public static Localizable localizableERROR_CLOSING_OUTPUT_STREAM()
            {
/* 379*/        return messageFactory.getMessage("error.closing.output.stream", new Object[0]);
            }

            public static String ERROR_CLOSING_OUTPUT_STREAM()
            {
/* 387*/        return localizer.localize(localizableERROR_CLOSING_OUTPUT_STREAM());
            }

            public static Localizable localizableERROR_LISTENER_INIT(Object obj)
            {
/* 391*/        return messageFactory.getMessage("error.listener.init", new Object[] {
/* 391*/            obj
                });
            }

            public static String ERROR_LISTENER_INIT(Object obj)
            {
/* 399*/        return localizer.localize(localizableERROR_LISTENER_INIT(obj));
            }

            public static Localizable localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_RESPONSE(Object obj)
            {
/* 403*/        return messageFactory.getMessage("error.service.locator.provider.instance.response", new Object[] {
/* 403*/            obj
                });
            }

            public static String ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_RESPONSE(Object obj)
            {
/* 411*/        return localizer.localize(localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_RESPONSE(obj));
            }

            public static Localizable localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_REQUEST(Object obj)
            {
/* 415*/        return messageFactory.getMessage("error.service.locator.provider.instance.request", new Object[] {
/* 415*/            obj
                });
            }

            public static String ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_REQUEST(Object obj)
            {
/* 423*/        return localizer.localize(localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_REQUEST(obj));
            }

            public static Localizable localizableUNEXPECTED_ERROR_RESPONSE_PROCESSING()
            {
/* 427*/        return messageFactory.getMessage("unexpected.error.response.processing", new Object[0]);
            }

            public static String UNEXPECTED_ERROR_RESPONSE_PROCESSING()
            {
/* 435*/        return localizer.localize(localizableUNEXPECTED_ERROR_RESPONSE_PROCESSING());
            }

            public static Localizable localizableRESPONSE_TO_EXCEPTION_CONVERSION_FAILED()
            {
/* 439*/        return messageFactory.getMessage("response.to.exception.conversion.failed", new Object[0]);
            }

            public static String RESPONSE_TO_EXCEPTION_CONVERSION_FAILED()
            {
/* 447*/        return localizer.localize(localizableRESPONSE_TO_EXCEPTION_CONVERSION_FAILED());
            }

            public static Localizable localizableNULL_KEYSTORE_PASWORD()
            {
/* 451*/        return messageFactory.getMessage("null.keystore.pasword", new Object[0]);
            }

            public static String NULL_KEYSTORE_PASWORD()
            {
/* 459*/        return localizer.localize(localizableNULL_KEYSTORE_PASWORD());
            }

            public static Localizable localizableCLIENT_URI_TEMPLATE_NULL()
            {
/* 463*/        return messageFactory.getMessage("client.uri.template.null", new Object[0]);
            }

            public static String CLIENT_URI_TEMPLATE_NULL()
            {
/* 471*/        return localizer.localize(localizableCLIENT_URI_TEMPLATE_NULL());
            }

            public static Localizable localizableCLIENT_RESPONSE_RESOLVED_URI_NULL()
            {
/* 475*/        return messageFactory.getMessage("client.response.resolved.uri.null", new Object[0]);
            }

            public static String CLIENT_RESPONSE_RESOLVED_URI_NULL()
            {
/* 483*/        return localizer.localize(localizableCLIENT_RESPONSE_RESOLVED_URI_NULL());
            }

            public static Localizable localizableCLIENT_INVOCATION_LINK_NULL()
            {
/* 487*/        return messageFactory.getMessage("client.invocation.link.null", new Object[0]);
            }

            public static String CLIENT_INVOCATION_LINK_NULL()
            {
/* 495*/        return localizer.localize(localizableCLIENT_INVOCATION_LINK_NULL());
            }

            public static Localizable localizableNULL_INPUT_PARAMETER(Object obj)
            {
/* 499*/        return messageFactory.getMessage("null.input.parameter", new Object[] {
/* 499*/            obj
                });
            }

            public static String NULL_INPUT_PARAMETER(Object obj)
            {
/* 507*/        return localizer.localize(localizableNULL_INPUT_PARAMETER(obj));
            }

            public static Localizable localizableRESPONSE_TYPE_IS_NULL()
            {
/* 511*/        return messageFactory.getMessage("response.type.is.null", new Object[0]);
            }

            public static String RESPONSE_TYPE_IS_NULL()
            {
/* 519*/        return localizer.localize(localizableRESPONSE_TYPE_IS_NULL());
            }

            public static Localizable localizableUSE_ENCODING_IGNORED(Object obj, Object obj1, Object obj2)
            {
/* 523*/        return messageFactory.getMessage("use.encoding.ignored", new Object[] {
/* 523*/            obj, obj1, obj2
                });
            }

            public static String USE_ENCODING_IGNORED(Object obj, Object obj1, Object obj2)
            {
/* 531*/        return localizer.localize(localizableUSE_ENCODING_IGNORED(obj, obj1, obj2));
            }

            public static Localizable localizableRESTRICTED_HEADER_PROPERTY_SETTING_FALSE(Object obj)
            {
/* 535*/        return messageFactory.getMessage("restricted.header.property.setting.false", new Object[] {
/* 535*/            obj
                });
            }

            public static String RESTRICTED_HEADER_PROPERTY_SETTING_FALSE(Object obj)
            {
/* 543*/        return localizer.localize(localizableRESTRICTED_HEADER_PROPERTY_SETTING_FALSE(obj));
            }

            public static Localizable localizableNULL_CONNECTOR_PROVIDER()
            {
/* 547*/        return messageFactory.getMessage("null.connector.provider", new Object[0]);
            }

            public static String NULL_CONNECTOR_PROVIDER()
            {
/* 555*/        return localizer.localize(localizableNULL_CONNECTOR_PROVIDER());
            }

            public static Localizable localizableERROR_DIGEST_FILTER_GENERATOR()
            {
/* 559*/        return messageFactory.getMessage("error.digest.filter.generator", new Object[0]);
            }

            public static String ERROR_DIGEST_FILTER_GENERATOR()
            {
/* 567*/        return localizer.localize(localizableERROR_DIGEST_FILTER_GENERATOR());
            }

            public static Localizable localizableNEGATIVE_CHUNK_SIZE(Object obj, Object obj1)
            {
/* 571*/        return messageFactory.getMessage("negative.chunk.size", new Object[] {
/* 571*/            obj, obj1
                });
            }

            public static String NEGATIVE_CHUNK_SIZE(Object obj, Object obj1)
            {
/* 579*/        return localizer.localize(localizableNEGATIVE_CHUNK_SIZE(obj, obj1));
            }

            private static final LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("org.glassfish.jersey.client.internal.localization");
            private static final Localizer localizer = new Localizer();

}
