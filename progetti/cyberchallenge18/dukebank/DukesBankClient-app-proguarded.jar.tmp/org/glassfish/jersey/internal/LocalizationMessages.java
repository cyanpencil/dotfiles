// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalizationMessages.java

package org.glassfish.jersey.internal;

import org.glassfish.jersey.internal.l10n.Localizable;
import org.glassfish.jersey.internal.l10n.LocalizableMessageFactory;
import org.glassfish.jersey.internal.l10n.Localizer;

public final class LocalizationMessages
{

            public LocalizationMessages()
            {
            }

            public static Localizable localizableERRORS_AND_WARNINGS_DETECTED(Object obj)
            {
/*  19*/        return messageFactory.getMessage("errors.and.warnings.detected", new Object[] {
/*  19*/            obj
                });
            }

            public static String ERRORS_AND_WARNINGS_DETECTED(Object obj)
            {
/*  27*/        return localizer.localize(localizableERRORS_AND_WARNINGS_DETECTED(obj));
            }

            public static Localizable localizableCOMMITTING_STREAM_BUFFERING_ILLEGAL_STATE()
            {
/*  31*/        return messageFactory.getMessage("committing.stream.buffering.illegal.state", new Object[0]);
            }

            public static String COMMITTING_STREAM_BUFFERING_ILLEGAL_STATE()
            {
/*  39*/        return localizer.localize(localizableCOMMITTING_STREAM_BUFFERING_ILLEGAL_STATE());
            }

            public static Localizable localizableLOCALE_IS_NULL()
            {
/*  43*/        return messageFactory.getMessage("locale.is.null", new Object[0]);
            }

            public static String LOCALE_IS_NULL()
            {
/*  51*/        return localizer.localize(localizableLOCALE_IS_NULL());
            }

            public static Localizable localizableSSL_KMF_PROVIDER_NOT_REGISTERED()
            {
/*  55*/        return messageFactory.getMessage("ssl.kmf.provider.not.registered", new Object[0]);
            }

            public static String SSL_KMF_PROVIDER_NOT_REGISTERED()
            {
/*  63*/        return localizer.localize(localizableSSL_KMF_PROVIDER_NOT_REGISTERED());
            }

            public static Localizable localizableURI_COMPONENT_ENCODED_OCTET_INVALID_DIGIT(Object obj, Object obj1)
            {
/*  67*/        return messageFactory.getMessage("uri.component.encoded.octet.invalid.digit", new Object[] {
/*  67*/            obj, obj1
                });
            }

            public static String URI_COMPONENT_ENCODED_OCTET_INVALID_DIGIT(Object obj, Object obj1)
            {
/*  75*/        return localizer.localize(localizableURI_COMPONENT_ENCODED_OCTET_INVALID_DIGIT(obj, obj1));
            }

            public static Localizable localizableURI_PARSER_COMPONENT_DELIMITER(Object obj, Object obj1)
            {
/*  79*/        return messageFactory.getMessage("uri.parser.component.delimiter", new Object[] {
/*  79*/            obj, obj1
                });
            }

            public static String URI_PARSER_COMPONENT_DELIMITER(Object obj, Object obj1)
            {
/*  87*/        return localizer.localize(localizableURI_PARSER_COMPONENT_DELIMITER(obj, obj1));
            }

            public static Localizable localizableHK_2_REIFICATION_ERROR(Object obj, Object obj1)
            {
/*  91*/        return messageFactory.getMessage("hk2.reification.error", new Object[] {
/*  91*/            obj, obj1
                });
            }

            public static String HK_2_REIFICATION_ERROR(Object obj, Object obj1)
            {
/* 100*/        return localizer.localize(localizableHK_2_REIFICATION_ERROR(obj, obj1));
            }

            public static Localizable localizableSSL_KMF_ALGORITHM_NOT_SUPPORTED()
            {
/* 104*/        return messageFactory.getMessage("ssl.kmf.algorithm.not.supported", new Object[0]);
            }

            public static String SSL_KMF_ALGORITHM_NOT_SUPPORTED()
            {
/* 112*/        return localizer.localize(localizableSSL_KMF_ALGORITHM_NOT_SUPPORTED());
            }

            public static Localizable localizableERROR_MBR_ISREADABLE(Object obj)
            {
/* 116*/        return messageFactory.getMessage("error.mbr.isreadable", new Object[] {
/* 116*/            obj
                });
            }

            public static String ERROR_MBR_ISREADABLE(Object obj)
            {
/* 124*/        return localizer.localize(localizableERROR_MBR_ISREADABLE(obj));
            }

            public static Localizable localizableSSL_KMF_INIT_FAILED()
            {
/* 128*/        return messageFactory.getMessage("ssl.kmf.init.failed", new Object[0]);
            }

            public static String SSL_KMF_INIT_FAILED()
            {
/* 136*/        return localizer.localize(localizableSSL_KMF_INIT_FAILED());
            }

            public static Localizable localizableOVERRIDING_METHOD_CANNOT_BE_FOUND(Object obj, Object obj1)
            {
/* 140*/        return messageFactory.getMessage("overriding.method.cannot.be.found", new Object[] {
/* 140*/            obj, obj1
                });
            }

            public static String OVERRIDING_METHOD_CANNOT_BE_FOUND(Object obj, Object obj1)
            {
/* 148*/        return localizer.localize(localizableOVERRIDING_METHOD_CANNOT_BE_FOUND(obj, obj1));
            }

            public static Localizable localizableERROR_INTERCEPTOR_READER_PROCEED()
            {
/* 152*/        return messageFactory.getMessage("error.interceptor.reader.proceed", new Object[0]);
            }

            public static String ERROR_INTERCEPTOR_READER_PROCEED()
            {
/* 160*/        return localizer.localize(localizableERROR_INTERCEPTOR_READER_PROCEED());
            }

            public static Localizable localizableMEDIA_TYPE_IS_NULL()
            {
/* 164*/        return messageFactory.getMessage("media.type.is.null", new Object[0]);
            }

            public static String MEDIA_TYPE_IS_NULL()
            {
/* 172*/        return localizer.localize(localizableMEDIA_TYPE_IS_NULL());
            }

            public static Localizable localizableURI_COMPONENT_ENCODED_OCTET_MALFORMED(Object obj)
            {
/* 176*/        return messageFactory.getMessage("uri.component.encoded.octet.malformed", new Object[] {
/* 176*/            obj
                });
            }

            public static String URI_COMPONENT_ENCODED_OCTET_MALFORMED(Object obj)
            {
/* 184*/        return localizer.localize(localizableURI_COMPONENT_ENCODED_OCTET_MALFORMED(obj));
            }

            public static Localizable localizableSSL_KS_INTEGRITY_ALGORITHM_NOT_FOUND()
            {
/* 188*/        return messageFactory.getMessage("ssl.ks.integrity.algorithm.not.found", new Object[0]);
            }

            public static String SSL_KS_INTEGRITY_ALGORITHM_NOT_FOUND()
            {
/* 196*/        return localizer.localize(localizableSSL_KS_INTEGRITY_ALGORITHM_NOT_FOUND());
            }

            public static Localizable localizableMESSAGE_CONTENT_BUFFER_RESET_FAILED()
            {
/* 200*/        return messageFactory.getMessage("message.content.buffer.reset.failed", new Object[0]);
            }

            public static String MESSAGE_CONTENT_BUFFER_RESET_FAILED()
            {
/* 208*/        return localizer.localize(localizableMESSAGE_CONTENT_BUFFER_RESET_FAILED());
            }

            public static Localizable localizableTEMPLATE_PARAM_NULL()
            {
/* 212*/        return messageFactory.getMessage("template.param.null", new Object[0]);
            }

            public static String TEMPLATE_PARAM_NULL()
            {
/* 220*/        return localizer.localize(localizableTEMPLATE_PARAM_NULL());
            }

            public static Localizable localizableSSL_TMF_INIT_FAILED()
            {
/* 224*/        return messageFactory.getMessage("ssl.tmf.init.failed", new Object[0]);
            }

            public static String SSL_TMF_INIT_FAILED()
            {
/* 232*/        return localizer.localize(localizableSSL_TMF_INIT_FAILED());
            }

            public static Localizable localizableURI_BUILDER_CLASS_PATH_ANNOTATION_MISSING(Object obj)
            {
/* 236*/        return messageFactory.getMessage("uri.builder.class.path.annotation.missing", new Object[] {
/* 236*/            obj
                });
            }

            public static String URI_BUILDER_CLASS_PATH_ANNOTATION_MISSING(Object obj)
            {
/* 244*/        return localizer.localize(localizableURI_BUILDER_CLASS_PATH_ANNOTATION_MISSING(obj));
            }

            public static Localizable localizableUNHANDLED_EXCEPTION_DETECTED(Object obj)
            {
/* 248*/        return messageFactory.getMessage("unhandled.exception.detected", new Object[] {
/* 248*/            obj
                });
            }

            public static String UNHANDLED_EXCEPTION_DETECTED(Object obj)
            {
/* 256*/        return localizer.localize(localizableUNHANDLED_EXCEPTION_DETECTED(obj));
            }

            public static Localizable localizableNOT_SUPPORTED_ON_OUTBOUND_MESSAGE()
            {
/* 260*/        return messageFactory.getMessage("not.supported.on.outbound.message", new Object[0]);
            }

            public static String NOT_SUPPORTED_ON_OUTBOUND_MESSAGE()
            {
/* 268*/        return localizer.localize(localizableNOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
            }

            public static Localizable localizableUNABLE_TO_PARSE_HEADER_VALUE(Object obj, Object obj1)
            {
/* 272*/        return messageFactory.getMessage("unable.to.parse.header.value", new Object[] {
/* 272*/            obj, obj1
                });
            }

            public static String UNABLE_TO_PARSE_HEADER_VALUE(Object obj, Object obj1)
            {
/* 280*/        return localizer.localize(localizableUNABLE_TO_PARSE_HEADER_VALUE(obj, obj1));
            }

            public static Localizable localizableERROR_PROVIDER_CONSTRAINED_TO_WRONG_RUNTIME(Object obj, Object obj1, Object obj2)
            {
/* 284*/        return messageFactory.getMessage("error.provider.constrainedTo.wrong.runtime", new Object[] {
/* 284*/            obj, obj1, obj2
                });
            }

            public static String ERROR_PROVIDER_CONSTRAINED_TO_WRONG_RUNTIME(Object obj, Object obj1, Object obj2)
            {
/* 292*/        return localizer.localize(localizableERROR_PROVIDER_CONSTRAINED_TO_WRONG_RUNTIME(obj, obj1, obj2));
            }

            public static Localizable localizableSSL_KMF_NO_PASSWORD_SET(Object obj)
            {
/* 296*/        return messageFactory.getMessage("ssl.kmf.no.password.set", new Object[] {
/* 296*/            obj
                });
            }

            public static String SSL_KMF_NO_PASSWORD_SET(Object obj)
            {
/* 304*/        return localizer.localize(localizableSSL_KMF_NO_PASSWORD_SET(obj));
            }

            public static Localizable localizablePARAM_NULL(Object obj)
            {
/* 308*/        return messageFactory.getMessage("param.null", new Object[] {
/* 308*/            obj
                });
            }

            public static String PARAM_NULL(Object obj)
            {
/* 316*/        return localizer.localize(localizablePARAM_NULL(obj));
            }

            public static Localizable localizableHTTP_HEADER_UNBALANCED_QUOTED()
            {
/* 320*/        return messageFactory.getMessage("http.header.unbalanced.quoted", new Object[0]);
            }

            public static String HTTP_HEADER_UNBALANCED_QUOTED()
            {
/* 328*/        return localizer.localize(localizableHTTP_HEADER_UNBALANCED_QUOTED());
            }

            public static Localizable localizableLINK_IS_NULL()
            {
/* 332*/        return messageFactory.getMessage("link.is.null", new Object[0]);
            }

            public static String LINK_IS_NULL()
            {
/* 340*/        return localizer.localize(localizableLINK_IS_NULL());
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_PART_OF_NAME(Object obj, Object obj1, Object obj2)
            {
/* 344*/        return messageFactory.getMessage("error.template.parser.illegal.char.partOf.name", new Object[] {
/* 344*/            obj, obj1, obj2
                });
            }

            public static String ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_PART_OF_NAME(Object obj, Object obj1, Object obj2)
            {
/* 352*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_PART_OF_NAME(obj, obj1, obj2));
            }

            public static Localizable localizablePROPERTIES_HELPER_DEPRECATED_PROPERTY_NAME(Object obj, Object obj1)
            {
/* 356*/        return messageFactory.getMessage("properties.helper.deprecated.property.name", new Object[] {
/* 356*/            obj, obj1
                });
            }

            public static String PROPERTIES_HELPER_DEPRECATED_PROPERTY_NAME(Object obj, Object obj1)
            {
/* 364*/        return localizer.localize(localizablePROPERTIES_HELPER_DEPRECATED_PROPERTY_NAME(obj, obj1));
            }

            public static Localizable localizableCOMPONENT_CANNOT_BE_NULL()
            {
/* 368*/        return messageFactory.getMessage("component.cannot.be.null", new Object[0]);
            }

            public static String COMPONENT_CANNOT_BE_NULL()
            {
/* 376*/        return localizer.localize(localizableCOMPONENT_CANNOT_BE_NULL());
            }

            public static Localizable localizableURI_BUILDER_ANNOTATEDELEMENT_PATH_ANNOTATION_MISSING(Object obj)
            {
/* 380*/        return messageFactory.getMessage("uri.builder.annotatedelement.path.annotation.missing", new Object[] {
/* 380*/            obj
                });
            }

            public static String URI_BUILDER_ANNOTATEDELEMENT_PATH_ANNOTATION_MISSING(Object obj)
            {
/* 388*/        return localizer.localize(localizableURI_BUILDER_ANNOTATEDELEMENT_PATH_ANNOTATION_MISSING(obj));
            }

            public static Localizable localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_CONTEXT(Object obj)
            {
/* 392*/        return messageFactory.getMessage("error.service.locator.provider.instance.feature.context", new Object[] {
/* 392*/            obj
                });
            }

            public static String ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_CONTEXT(Object obj)
            {
/* 400*/        return localizer.localize(localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_CONTEXT(obj));
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_START_NAME(Object obj, Object obj1, Object obj2)
            {
/* 404*/        return messageFactory.getMessage("error.template.parser.illegal.char.start.name", new Object[] {
/* 404*/            obj, obj1, obj2
                });
            }

            public static String ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_START_NAME(Object obj, Object obj1, Object obj2)
            {
/* 412*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_START_NAME(obj, obj1, obj2));
            }

            public static Localizable localizableCONFIGURATION_NOT_MODIFIABLE()
            {
/* 416*/        return messageFactory.getMessage("configuration.not.modifiable", new Object[0]);
            }

            public static String CONFIGURATION_NOT_MODIFIABLE()
            {
/* 424*/        return localizer.localize(localizableCONFIGURATION_NOT_MODIFIABLE());
            }

            public static Localizable localizableSSL_TS_CERT_LOAD_ERROR()
            {
/* 428*/        return messageFactory.getMessage("ssl.ts.cert.load.error", new Object[0]);
            }

            public static String SSL_TS_CERT_LOAD_ERROR()
            {
/* 436*/        return localizer.localize(localizableSSL_TS_CERT_LOAD_ERROR());
            }

            public static Localizable localizableERROR_FINDING_EXCEPTION_MAPPER_TYPE(Object obj)
            {
/* 440*/        return messageFactory.getMessage("error.finding.exception.mapper.type", new Object[] {
/* 440*/            obj
                });
            }

            public static String ERROR_FINDING_EXCEPTION_MAPPER_TYPE(Object obj)
            {
/* 448*/        return localizer.localize(localizableERROR_FINDING_EXCEPTION_MAPPER_TYPE(obj));
            }

            public static Localizable localizableERROR_NEWCOOKIE_EXPIRES(Object obj)
            {
/* 452*/        return messageFactory.getMessage("error.newcookie.expires", new Object[] {
/* 452*/            obj
                });
            }

            public static String ERROR_NEWCOOKIE_EXPIRES(Object obj)
            {
/* 460*/        return localizer.localize(localizableERROR_NEWCOOKIE_EXPIRES(obj));
            }

            public static Localizable localizableILLEGAL_INITIAL_CAPACITY(Object obj)
            {
/* 464*/        return messageFactory.getMessage("illegal.initial.capacity", new Object[] {
/* 464*/            obj
                });
            }

            public static String ILLEGAL_INITIAL_CAPACITY(Object obj)
            {
/* 472*/        return localizer.localize(localizableILLEGAL_INITIAL_CAPACITY(obj));
            }

            public static Localizable localizableSSL_KS_CERT_LOAD_ERROR()
            {
/* 476*/        return messageFactory.getMessage("ssl.ks.cert.load.error", new Object[0]);
            }

            public static String SSL_KS_CERT_LOAD_ERROR()
            {
/* 484*/        return localizer.localize(localizableSSL_KS_CERT_LOAD_ERROR());
            }

            public static Localizable localizableERROR_READING_ENTITY_FROM_INPUT_STREAM()
            {
/* 488*/        return messageFactory.getMessage("error.reading.entity.from.input.stream", new Object[0]);
            }

            public static String ERROR_READING_ENTITY_FROM_INPUT_STREAM()
            {
/* 496*/        return localizer.localize(localizableERROR_READING_ENTITY_FROM_INPUT_STREAM());
            }

            public static Localizable localizableERROR_PROVIDER_CONSTRAINED_TO_IGNORED(Object obj)
            {
/* 500*/        return messageFactory.getMessage("error.provider.constrainedTo.ignored", new Object[] {
/* 500*/            obj
                });
            }

            public static String ERROR_PROVIDER_CONSTRAINED_TO_IGNORED(Object obj)
            {
/* 508*/        return localizer.localize(localizableERROR_PROVIDER_CONSTRAINED_TO_IGNORED(obj));
            }

            public static Localizable localizableHTTP_HEADER_WHITESPACE_NOT_ALLOWED()
            {
/* 512*/        return messageFactory.getMessage("http.header.whitespace.not.allowed", new Object[0]);
            }

            public static String HTTP_HEADER_WHITESPACE_NOT_ALLOWED()
            {
/* 520*/        return localizer.localize(localizableHTTP_HEADER_WHITESPACE_NOT_ALLOWED());
            }

            public static Localizable localizableILLEGAL_CONFIG_SYNTAX()
            {
/* 524*/        return messageFactory.getMessage("illegal.config.syntax", new Object[0]);
            }

            public static String ILLEGAL_CONFIG_SYNTAX()
            {
/* 532*/        return localizer.localize(localizableILLEGAL_CONFIG_SYNTAX());
            }

            public static Localizable localizableSSL_TS_FILE_NOT_FOUND(Object obj)
            {
/* 536*/        return messageFactory.getMessage("ssl.ts.file.not.found", new Object[] {
/* 536*/            obj
                });
            }

            public static String SSL_TS_FILE_NOT_FOUND(Object obj)
            {
/* 544*/        return localizer.localize(localizableSSL_TS_FILE_NOT_FOUND(obj));
            }

            public static Localizable localizableERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS()
            {
/* 548*/        return messageFactory.getMessage("error.caught.while.loading.spi.providers", new Object[0]);
            }

            public static String ERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS()
            {
/* 556*/        return localizer.localize(localizableERROR_CAUGHT_WHILE_LOADING_SPI_PROVIDERS());
            }

            public static Localizable localizableMULTIPLE_MATCHING_CONSTRUCTORS_FOUND(Object obj, Object obj1, Object obj2, Object obj3)
            {
/* 560*/        return messageFactory.getMessage("multiple.matching.constructors.found", new Object[] {
/* 560*/            obj, obj1, obj2, obj3
                });
            }

            public static String MULTIPLE_MATCHING_CONSTRUCTORS_FOUND(Object obj, Object obj1, Object obj2, Object obj3)
            {
/* 568*/        return localizer.localize(localizableMULTIPLE_MATCHING_CONSTRUCTORS_FOUND(obj, obj1, obj2, obj3));
            }

            public static Localizable localizableMETHOD_NOT_GETTER_NOR_SETTER()
            {
/* 572*/        return messageFactory.getMessage("method.not.getter.nor.setter", new Object[0]);
            }

            public static String METHOD_NOT_GETTER_NOR_SETTER()
            {
/* 580*/        return localizer.localize(localizableMETHOD_NOT_GETTER_NOR_SETTER());
            }

            public static Localizable localizableERROR_PARSING_ENTITY_TAG(Object obj)
            {
/* 584*/        return messageFactory.getMessage("error.parsing.entity.tag", new Object[] {
/* 584*/            obj
                });
            }

            public static String ERROR_PARSING_ENTITY_TAG(Object obj)
            {
/* 592*/        return localizer.localize(localizableERROR_PARSING_ENTITY_TAG(obj));
            }

            public static Localizable localizableSSL_CTX_ALGORITHM_NOT_SUPPORTED()
            {
/* 596*/        return messageFactory.getMessage("ssl.ctx.algorithm.not.supported", new Object[0]);
            }

            public static String SSL_CTX_ALGORITHM_NOT_SUPPORTED()
            {
/* 604*/        return localizer.localize(localizableSSL_CTX_ALGORITHM_NOT_SUPPORTED());
            }

            public static Localizable localizableHK_2_UNKNOWN_ERROR(Object obj)
            {
/* 608*/        return messageFactory.getMessage("hk2.unknown.error", new Object[] {
/* 608*/            obj
                });
            }

            public static String HK_2_UNKNOWN_ERROR(Object obj)
            {
/* 617*/        return localizer.localize(localizableHK_2_UNKNOWN_ERROR(obj));
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_AFTER_NAME(Object obj, Object obj1, Object obj2)
            {
/* 621*/        return messageFactory.getMessage("error.template.parser.illegal.char.after.name", new Object[] {
/* 621*/            obj, obj1, obj2
                });
            }

            public static String ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_AFTER_NAME(Object obj, Object obj1, Object obj2)
            {
/* 629*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_AFTER_NAME(obj, obj1, obj2));
            }

            public static Localizable localizableOUTPUT_STREAM_CLOSED()
            {
/* 633*/        return messageFactory.getMessage("output.stream.closed", new Object[0]);
            }

            public static String OUTPUT_STREAM_CLOSED()
            {
/* 641*/        return localizer.localize(localizableOUTPUT_STREAM_CLOSED());
            }

            public static Localizable localizableENTITY_TAG_IS_NULL()
            {
/* 645*/        return messageFactory.getMessage("entity.tag.is.null", new Object[0]);
            }

            public static String ENTITY_TAG_IS_NULL()
            {
/* 653*/        return localizer.localize(localizableENTITY_TAG_IS_NULL());
            }

            public static Localizable localizableINPUT_STREAM_CLOSED()
            {
/* 657*/        return messageFactory.getMessage("input.stream.closed", new Object[0]);
            }

            public static String INPUT_STREAM_CLOSED()
            {
/* 665*/        return localizer.localize(localizableINPUT_STREAM_CLOSED());
            }

            public static Localizable localizableCOOKIE_IS_NULL()
            {
/* 669*/        return messageFactory.getMessage("cookie.is.null", new Object[0]);
            }

            public static String COOKIE_IS_NULL()
            {
/* 677*/        return localizer.localize(localizableCOOKIE_IS_NULL());
            }

            public static Localizable localizableNEW_COOKIE_IS_NULL()
            {
/* 681*/        return messageFactory.getMessage("new.cookie.is.null", new Object[0]);
            }

            public static String NEW_COOKIE_IS_NULL()
            {
/* 689*/        return localizer.localize(localizableNEW_COOKIE_IS_NULL());
            }

            public static Localizable localizableINJECTION_ERROR_LOCAL_CLASS_NOT_SUPPORTED(Object obj)
            {
/* 693*/        return messageFactory.getMessage("injection.error.local.class.not.supported", new Object[] {
/* 693*/            obj
                });
            }

            public static String INJECTION_ERROR_LOCAL_CLASS_NOT_SUPPORTED(Object obj)
            {
/* 701*/        return localizer.localize(localizableINJECTION_ERROR_LOCAL_CLASS_NOT_SUPPORTED(obj));
            }

            public static Localizable localizableSSL_TS_PROVIDERS_NOT_REGISTERED()
            {
/* 705*/        return messageFactory.getMessage("ssl.ts.providers.not.registered", new Object[0]);
            }

            public static String SSL_TS_PROVIDERS_NOT_REGISTERED()
            {
/* 713*/        return localizer.localize(localizableSSL_TS_PROVIDERS_NOT_REGISTERED());
            }

            public static Localizable localizableINJECTION_ERROR_NONSTATIC_MEMBER_CLASS_NOT_SUPPORTED(Object obj)
            {
/* 717*/        return messageFactory.getMessage("injection.error.nonstatic.member.class.not.supported", new Object[] {
/* 717*/            obj
                });
            }

            public static String INJECTION_ERROR_NONSTATIC_MEMBER_CLASS_NOT_SUPPORTED(Object obj)
            {
/* 725*/        return localizer.localize(localizableINJECTION_ERROR_NONSTATIC_MEMBER_CLASS_NOT_SUPPORTED(obj));
            }

            public static Localizable localizableURI_BUILDER_SCHEME_PART_NULL()
            {
/* 729*/        return messageFactory.getMessage("uri.builder.scheme.part.null", new Object[0]);
            }

            public static String URI_BUILDER_SCHEME_PART_NULL()
            {
/* 737*/        return localizer.localize(localizableURI_BUILDER_SCHEME_PART_NULL());
            }

            public static Localizable localizableMATRIX_PARAM_NULL()
            {
/* 741*/        return messageFactory.getMessage("matrix.param.null", new Object[0]);
            }

            public static String MATRIX_PARAM_NULL()
            {
/* 749*/        return localizer.localize(localizableMATRIX_PARAM_NULL());
            }

            public static Localizable localizableWARNINGS_DETECTED(Object obj)
            {
/* 753*/        return messageFactory.getMessage("warnings.detected", new Object[] {
/* 753*/            obj
                });
            }

            public static String WARNINGS_DETECTED(Object obj)
            {
/* 761*/        return localizer.localize(localizableWARNINGS_DETECTED(obj));
            }

            public static Localizable localizableHINT_MSG(Object obj)
            {
/* 765*/        return messageFactory.getMessage("hint.msg", new Object[] {
/* 765*/            obj
                });
            }

            public static String HINT_MSG(Object obj)
            {
/* 773*/        return localizer.localize(localizableHINT_MSG(obj));
            }

            public static Localizable localizableSSL_TS_LOAD_ERROR(Object obj)
            {
/* 777*/        return messageFactory.getMessage("ssl.ts.load.error", new Object[] {
/* 777*/            obj
                });
            }

            public static String SSL_TS_LOAD_ERROR(Object obj)
            {
/* 785*/        return localizer.localize(localizableSSL_TS_LOAD_ERROR(obj));
            }

            public static Localizable localizableERROR_PROVIDER_REGISTERED_WRONG_RUNTIME(Object obj, Object obj1)
            {
/* 789*/        return messageFactory.getMessage("error.provider.registered.wrong.runtime", new Object[] {
/* 789*/            obj, obj1
                });
            }

            public static String ERROR_PROVIDER_REGISTERED_WRONG_RUNTIME(Object obj, Object obj1)
            {
/* 797*/        return localizer.localize(localizableERROR_PROVIDER_REGISTERED_WRONG_RUNTIME(obj, obj1));
            }

            public static Localizable localizableSSL_KMF_NO_PASSWORD_FOR_PROVIDER_BASED_KS()
            {
/* 801*/        return messageFactory.getMessage("ssl.kmf.no.password.for.provider.based.ks", new Object[0]);
            }

            public static String SSL_KMF_NO_PASSWORD_FOR_PROVIDER_BASED_KS()
            {
/* 809*/        return localizer.localize(localizableSSL_KMF_NO_PASSWORD_FOR_PROVIDER_BASED_KS());
            }

            public static Localizable localizableURI_PARSER_SCHEME_EXPECTED(Object obj, Object obj1)
            {
/* 813*/        return messageFactory.getMessage("uri.parser.scheme.expected", new Object[] {
/* 813*/            obj, obj1
                });
            }

            public static String URI_PARSER_SCHEME_EXPECTED(Object obj, Object obj1)
            {
/* 821*/        return localizer.localize(localizableURI_PARSER_SCHEME_EXPECTED(obj, obj1));
            }

            public static Localizable localizableTHREAD_POOL_EXECUTOR_PROVIDER_CLOSED()
            {
/* 825*/        return messageFactory.getMessage("thread.pool.executor.provider.closed", new Object[0]);
            }

            public static String THREAD_POOL_EXECUTOR_PROVIDER_CLOSED()
            {
/* 833*/        return localizer.localize(localizableTHREAD_POOL_EXECUTOR_PROVIDER_CLOSED());
            }

            public static Localizable localizableMBW_TRYING_TO_CLOSE_STREAM(Object obj)
            {
/* 837*/        return messageFactory.getMessage("mbw.trying.to.close.stream", new Object[] {
/* 837*/            obj
                });
            }

            public static String MBW_TRYING_TO_CLOSE_STREAM(Object obj)
            {
/* 845*/        return localizer.localize(localizableMBW_TRYING_TO_CLOSE_STREAM(obj));
            }

            public static Localizable localizableCOMPONENT_CONTRACTS_EMPTY_OR_NULL(Object obj)
            {
/* 849*/        return messageFactory.getMessage("component.contracts.empty.or.null", new Object[] {
/* 849*/            obj
                });
            }

            public static String COMPONENT_CONTRACTS_EMPTY_OR_NULL(Object obj)
            {
/* 857*/        return localizer.localize(localizableCOMPONENT_CONTRACTS_EMPTY_OR_NULL(obj));
            }

            public static Localizable localizablePROVIDER_NOT_FOUND(Object obj, Object obj1)
            {
/* 861*/        return messageFactory.getMessage("provider.not.found", new Object[] {
/* 861*/            obj, obj1
                });
            }

            public static String PROVIDER_NOT_FOUND(Object obj, Object obj1)
            {
/* 869*/        return localizer.localize(localizablePROVIDER_NOT_FOUND(obj, obj1));
            }

            public static Localizable localizableTOO_MANY_HEADER_VALUES(Object obj, Object obj1)
            {
/* 873*/        return messageFactory.getMessage("too.many.header.values", new Object[] {
/* 873*/            obj, obj1
                });
            }

            public static String TOO_MANY_HEADER_VALUES(Object obj, Object obj1)
            {
/* 881*/        return localizer.localize(localizableTOO_MANY_HEADER_VALUES(obj, obj1));
            }

            public static Localizable localizableCACHE_CONTROL_IS_NULL()
            {
/* 885*/        return messageFactory.getMessage("cache.control.is.null", new Object[0]);
            }

            public static String CACHE_CONTROL_IS_NULL()
            {
/* 893*/        return localizer.localize(localizableCACHE_CONTROL_IS_NULL());
            }

            public static Localizable localizableHTTP_HEADER_END_OF_HEADER()
            {
/* 897*/        return messageFactory.getMessage("http.header.end.of.header", new Object[0]);
            }

            public static String HTTP_HEADER_END_OF_HEADER()
            {
/* 905*/        return localizer.localize(localizableHTTP_HEADER_END_OF_HEADER());
            }

            public static Localizable localizableUSING_SCHEDULER_PROVIDER(Object obj, Object obj1)
            {
/* 909*/        return messageFactory.getMessage("using.scheduler.provider", new Object[] {
/* 909*/            obj, obj1
                });
            }

            public static String USING_SCHEDULER_PROVIDER(Object obj, Object obj1)
            {
/* 917*/        return localizer.localize(localizableUSING_SCHEDULER_PROVIDER(obj, obj1));
            }

            public static Localizable localizableHTTP_HEADER_COMMENTS_NOT_ALLOWED()
            {
/* 921*/        return messageFactory.getMessage("http.header.comments.not.allowed", new Object[0]);
            }

            public static String HTTP_HEADER_COMMENTS_NOT_ALLOWED()
            {
/* 929*/        return localizer.localize(localizableHTTP_HEADER_COMMENTS_NOT_ALLOWED());
            }

            public static Localizable localizableCOMPONENT_CLASS_CANNOT_BE_NULL()
            {
/* 933*/        return messageFactory.getMessage("component.class.cannot.be.null", new Object[0]);
            }

            public static String COMPONENT_CLASS_CANNOT_BE_NULL()
            {
/* 941*/        return localizer.localize(localizableCOMPONENT_CLASS_CANNOT_BE_NULL());
            }

            public static Localizable localizableURI_BUILDER_SCHEMA_PART_OPAQUE()
            {
/* 945*/        return messageFactory.getMessage("uri.builder.schema.part.opaque", new Object[0]);
            }

            public static String URI_BUILDER_SCHEMA_PART_OPAQUE()
            {
/* 953*/        return localizer.localize(localizableURI_BUILDER_SCHEMA_PART_OPAQUE());
            }

            public static Localizable localizableNO_ERROR_PROCESSING_IN_SCOPE()
            {
/* 957*/        return messageFactory.getMessage("no.error.processing.in.scope", new Object[0]);
            }

            public static String NO_ERROR_PROCESSING_IN_SCOPE()
            {
/* 965*/        return localizer.localize(localizableNO_ERROR_PROCESSING_IN_SCOPE());
            }

            public static Localizable localizableCONTRACT_NOT_SUPPORTED(Object obj, Object obj1)
            {
/* 969*/        return messageFactory.getMessage("contract.not.supported", new Object[] {
/* 969*/            obj, obj1
                });
            }

            public static String CONTRACT_NOT_SUPPORTED(Object obj, Object obj1)
            {
/* 977*/        return localizer.localize(localizableCONTRACT_NOT_SUPPORTED(obj, obj1));
            }

            public static Localizable localizableINVALID_SPI_CLASSES(Object obj, Object obj1)
            {
/* 981*/        return messageFactory.getMessage("invalid.spi.classes", new Object[] {
/* 981*/            obj, obj1
                });
            }

            public static String INVALID_SPI_CLASSES(Object obj, Object obj1)
            {
/* 989*/        return localizer.localize(localizableINVALID_SPI_CLASSES(obj, obj1));
            }

            public static Localizable localizablePROVIDER_COULD_NOT_BE_CREATED(Object obj, Object obj1, Object obj2)
            {
/* 993*/        return messageFactory.getMessage("provider.could.not.be.created", new Object[] {
/* 993*/            obj, obj1, obj2
                });
            }

            public static String PROVIDER_COULD_NOT_BE_CREATED(Object obj, Object obj1, Object obj2)
            {
/*1001*/        return localizer.localize(localizablePROVIDER_COULD_NOT_BE_CREATED(obj, obj1, obj2));
            }

            public static Localizable localizableERROR_NOTFOUND_MESSAGEBODYREADER(Object obj, Object obj1, Object obj2)
            {
/*1005*/        return messageFactory.getMessage("error.notfound.messagebodyreader", new Object[] {
/*1005*/            obj, obj1, obj2
                });
            }

            public static String ERROR_NOTFOUND_MESSAGEBODYREADER(Object obj, Object obj1, Object obj2)
            {
/*1013*/        return localizer.localize(localizableERROR_NOTFOUND_MESSAGEBODYREADER(obj, obj1, obj2));
            }

            public static Localizable localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_READER_INTERCEPTOR_CONTEXT(Object obj)
            {
/*1017*/        return messageFactory.getMessage("error.service.locator.provider.instance.feature.reader.interceptor.context", new Object[] {
/*1017*/            obj
                });
            }

            public static String ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_READER_INTERCEPTOR_CONTEXT(Object obj)
            {
/*1025*/        return localizer.localize(localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_READER_INTERCEPTOR_CONTEXT(obj));
            }

            public static Localizable localizableUSING_EXECUTOR_PROVIDER(Object obj, Object obj1)
            {
/*1029*/        return messageFactory.getMessage("using.executor.provider", new Object[] {
/*1029*/            obj, obj1
                });
            }

            public static String USING_EXECUTOR_PROVIDER(Object obj, Object obj1)
            {
/*1037*/        return localizer.localize(localizableUSING_EXECUTOR_PROVIDER(obj, obj1));
            }

            public static Localizable localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_WRITER_INTERCEPTOR_CONTEXT(Object obj)
            {
/*1041*/        return messageFactory.getMessage("error.service.locator.provider.instance.feature.writer.interceptor.context", new Object[] {
/*1041*/            obj
                });
            }

            public static String ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_WRITER_INTERCEPTOR_CONTEXT(Object obj)
            {
/*1049*/        return localizer.localize(localizableERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_WRITER_INTERCEPTOR_CONTEXT(obj));
            }

            public static Localizable localizableIGNORED_SCHEDULER_PROVIDERS(Object obj, Object obj1)
            {
/*1053*/        return messageFactory.getMessage("ignored.scheduler.providers", new Object[] {
/*1053*/            obj, obj1
                });
            }

            public static String IGNORED_SCHEDULER_PROVIDERS(Object obj, Object obj1)
            {
/*1061*/        return localizer.localize(localizableIGNORED_SCHEDULER_PROVIDERS(obj, obj1));
            }

            public static Localizable localizableDEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(Object obj, Object obj1, Object obj2)
            {
/*1065*/        return messageFactory.getMessage("dependent.class.of.provider.not.found", new Object[] {
/*1065*/            obj, obj1, obj2
                });
            }

            public static String DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(Object obj, Object obj1, Object obj2)
            {
/*1073*/        return localizer.localize(localizableDEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(obj, obj1, obj2));
            }

            public static Localizable localizableHTTP_HEADER_NO_END_SEPARATOR(Object obj)
            {
/*1077*/        return messageFactory.getMessage("http.header.no.end.separator", new Object[] {
/*1077*/            obj
                });
            }

            public static String HTTP_HEADER_NO_END_SEPARATOR(Object obj)
            {
/*1085*/        return localizer.localize(localizableHTTP_HEADER_NO_END_SEPARATOR(obj));
            }

            public static Localizable localizableSSL_KS_IMPL_NOT_FOUND()
            {
/*1089*/        return messageFactory.getMessage("ssl.ks.impl.not.found", new Object[0]);
            }

            public static String SSL_KS_IMPL_NOT_FOUND()
            {
/*1097*/        return localizer.localize(localizableSSL_KS_IMPL_NOT_FOUND());
            }

            public static Localizable localizableERROR_PROVIDER_AND_RESOURCE_CONSTRAINED_TO_IGNORED(Object obj)
            {
/*1101*/        return messageFactory.getMessage("error.provider.and.resource.constrainedTo.ignored", new Object[] {
/*1101*/            obj
                });
            }

            public static String ERROR_PROVIDER_AND_RESOURCE_CONSTRAINED_TO_IGNORED(Object obj)
            {
/*1109*/        return localizer.localize(localizableERROR_PROVIDER_AND_RESOURCE_CONSTRAINED_TO_IGNORED(obj));
            }

            public static Localizable localizableINVALID_PORT()
            {
/*1113*/        return messageFactory.getMessage("invalid.port", new Object[0]);
            }

            public static String INVALID_PORT()
            {
/*1121*/        return localizer.localize(localizableINVALID_PORT());
            }

            public static Localizable localizableERROR_INTERCEPTOR_WRITER_PROCEED()
            {
/*1125*/        return messageFactory.getMessage("error.interceptor.writer.proceed", new Object[0]);
            }

            public static String ERROR_INTERCEPTOR_WRITER_PROCEED()
            {
/*1133*/        return localizer.localize(localizableERROR_INTERCEPTOR_WRITER_PROCEED());
            }

            public static Localizable localizableHTTP_HEADER_NO_CHARS_BETWEEN_SEPARATORS(Object obj, Object obj1)
            {
/*1137*/        return messageFactory.getMessage("http.header.no.chars.between.separators", new Object[] {
/*1137*/            obj, obj1
                });
            }

            public static String HTTP_HEADER_NO_CHARS_BETWEEN_SEPARATORS(Object obj, Object obj1)
            {
/*1145*/        return localizer.localize(localizableHTTP_HEADER_NO_CHARS_BETWEEN_SEPARATORS(obj, obj1));
            }

            public static Localizable localizableILLEGAL_LOAD_FACTOR(Object obj)
            {
/*1149*/        return messageFactory.getMessage("illegal.load.factor", new Object[] {
/*1149*/            obj
                });
            }

            public static String ILLEGAL_LOAD_FACTOR(Object obj)
            {
/*1157*/        return localizer.localize(localizableILLEGAL_LOAD_FACTOR(obj));
            }

            public static Localizable localizableSOME_HEADERS_NOT_SENT(Object obj, Object obj1)
            {
/*1161*/        return messageFactory.getMessage("some.headers.not.sent", new Object[] {
/*1161*/            obj, obj1
                });
            }

            public static String SOME_HEADERS_NOT_SENT(Object obj, Object obj1)
            {
/*1170*/        return localizer.localize(localizableSOME_HEADERS_NOT_SENT(obj, obj1));
            }

            public static Localizable localizableQUERY_PARAM_NULL()
            {
/*1174*/        return messageFactory.getMessage("query.param.null", new Object[0]);
            }

            public static String QUERY_PARAM_NULL()
            {
/*1182*/        return localizer.localize(localizableQUERY_PARAM_NULL());
            }

            public static Localizable localizableILLEGAL_PROVIDER_CLASS_NAME(Object obj)
            {
/*1186*/        return messageFactory.getMessage("illegal.provider.class.name", new Object[] {
/*1186*/            obj
                });
            }

            public static String ILLEGAL_PROVIDER_CLASS_NAME(Object obj)
            {
/*1194*/        return localizer.localize(localizableILLEGAL_PROVIDER_CLASS_NAME(obj));
            }

            public static Localizable localizableSTREAM_PROVIDER_NULL()
            {
/*1198*/        return messageFactory.getMessage("stream.provider.null", new Object[0]);
            }

            public static String STREAM_PROVIDER_NULL()
            {
/*1206*/        return localizer.localize(localizableSTREAM_PROVIDER_NULL());
            }

            public static Localizable localizableSSL_TMF_PROVIDER_NOT_REGISTERED()
            {
/*1210*/        return messageFactory.getMessage("ssl.tmf.provider.not.registered", new Object[0]);
            }

            public static String SSL_TMF_PROVIDER_NOT_REGISTERED()
            {
/*1218*/        return localizer.localize(localizableSSL_TMF_PROVIDER_NOT_REGISTERED());
            }

            public static Localizable localizableNO_CONTAINER_AVAILABLE()
            {
/*1222*/        return messageFactory.getMessage("no.container.available", new Object[0]);
            }

            public static String NO_CONTAINER_AVAILABLE()
            {
/*1230*/        return localizer.localize(localizableNO_CONTAINER_AVAILABLE());
            }

            public static Localizable localizableHK_2_FAILURE_OUTSIDE_ERROR_SCOPE()
            {
/*1234*/        return messageFactory.getMessage("hk2.failure.outside.error.scope", new Object[0]);
            }

            public static String HK_2_FAILURE_OUTSIDE_ERROR_SCOPE()
            {
/*1242*/        return localizer.localize(localizableHK_2_FAILURE_OUTSIDE_ERROR_SCOPE());
            }

            public static Localizable localizableERROR_ENTITY_PROVIDER_BASICTYPES_CONSTRUCTOR(Object obj)
            {
/*1246*/        return messageFactory.getMessage("error.entity.provider.basictypes.constructor", new Object[] {
/*1246*/            obj
                });
            }

            public static String ERROR_ENTITY_PROVIDER_BASICTYPES_CONSTRUCTOR(Object obj)
            {
/*1254*/        return localizer.localize(localizableERROR_ENTITY_PROVIDER_BASICTYPES_CONSTRUCTOR(obj));
            }

            public static Localizable localizableERROR_NOTFOUND_MESSAGEBODYWRITER(Object obj, Object obj1, Object obj2)
            {
/*1258*/        return messageFactory.getMessage("error.notfound.messagebodywriter", new Object[] {
/*1258*/            obj, obj1, obj2
                });
            }

            public static String ERROR_NOTFOUND_MESSAGEBODYWRITER(Object obj, Object obj1, Object obj2)
            {
/*1266*/        return localizer.localize(localizableERROR_NOTFOUND_MESSAGEBODYWRITER(obj, obj1, obj2));
            }

            public static Localizable localizableCONTRACT_NOT_ASSIGNABLE(Object obj, Object obj1)
            {
/*1270*/        return messageFactory.getMessage("contract.not.assignable", new Object[] {
/*1270*/            obj, obj1
                });
            }

            public static String CONTRACT_NOT_ASSIGNABLE(Object obj, Object obj1)
            {
/*1278*/        return localizer.localize(localizableCONTRACT_NOT_ASSIGNABLE(obj, obj1));
            }

            public static Localizable localizableSSL_TMF_ALGORITHM_NOT_SUPPORTED()
            {
/*1282*/        return messageFactory.getMessage("ssl.tmf.algorithm.not.supported", new Object[0]);
            }

            public static String SSL_TMF_ALGORITHM_NOT_SUPPORTED()
            {
/*1290*/        return localizer.localize(localizableSSL_TMF_ALGORITHM_NOT_SUPPORTED());
            }

            public static Localizable localizableOSGI_REGISTRY_ERROR_OPENING_RESOURCE_STREAM(Object obj)
            {
/*1294*/        return messageFactory.getMessage("osgi.registry.error.opening.resource.stream", new Object[] {
/*1294*/            obj
                });
            }

            public static String OSGI_REGISTRY_ERROR_OPENING_RESOURCE_STREAM(Object obj)
            {
/*1302*/        return localizer.localize(localizableOSGI_REGISTRY_ERROR_OPENING_RESOURCE_STREAM(obj));
            }

            public static Localizable localizableMBR_TRYING_TO_CLOSE_STREAM(Object obj)
            {
/*1306*/        return messageFactory.getMessage("mbr.trying.to.close.stream", new Object[] {
/*1306*/            obj
                });
            }

            public static String MBR_TRYING_TO_CLOSE_STREAM(Object obj)
            {
/*1314*/        return localizer.localize(localizableMBR_TRYING_TO_CLOSE_STREAM(obj));
            }

            public static Localizable localizableIGNORED_EXECUTOR_PROVIDERS(Object obj, Object obj1)
            {
/*1318*/        return messageFactory.getMessage("ignored.executor.providers", new Object[] {
/*1318*/            obj, obj1
                });
            }

            public static String IGNORED_EXECUTOR_PROVIDERS(Object obj, Object obj1)
            {
/*1326*/        return localizer.localize(localizableIGNORED_EXECUTOR_PROVIDERS(obj, obj1));
            }

            public static Localizable localizableURI_PARSER_NOT_EXECUTED()
            {
/*1330*/        return messageFactory.getMessage("uri.parser.not.executed", new Object[0]);
            }

            public static String URI_PARSER_NOT_EXECUTED()
            {
/*1338*/        return localizer.localize(localizableURI_PARSER_NOT_EXECUTED());
            }

            public static Localizable localizableMESSAGE_CONTENT_BUFFERING_FAILED()
            {
/*1342*/        return messageFactory.getMessage("message.content.buffering.failed", new Object[0]);
            }

            public static String MESSAGE_CONTENT_BUFFERING_FAILED()
            {
/*1350*/        return localizer.localize(localizableMESSAGE_CONTENT_BUFFERING_FAILED());
            }

            public static Localizable localizableRESPONSE_CLOSED()
            {
/*1354*/        return messageFactory.getMessage("response.closed", new Object[0]);
            }

            public static String RESPONSE_CLOSED()
            {
/*1362*/        return localizer.localize(localizableRESPONSE_CLOSED());
            }

            public static Localizable localizableSSL_KS_LOAD_ERROR(Object obj)
            {
/*1366*/        return messageFactory.getMessage("ssl.ks.load.error", new Object[] {
/*1366*/            obj
                });
            }

            public static String SSL_KS_LOAD_ERROR(Object obj)
            {
/*1374*/        return localizer.localize(localizableSSL_KS_LOAD_ERROR(obj));
            }

            public static Localizable localizableCOMMITTING_STREAM_ALREADY_INITIALIZED()
            {
/*1378*/        return messageFactory.getMessage("committing.stream.already.initialized", new Object[0]);
            }

            public static String COMMITTING_STREAM_ALREADY_INITIALIZED()
            {
/*1386*/        return localizer.localize(localizableCOMMITTING_STREAM_ALREADY_INITIALIZED());
            }

            public static Localizable localizableERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS()
            {
/*1390*/        return messageFactory.getMessage("error.entity.provider.basictypes.character.morechars", new Object[0]);
            }

            public static String ERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS()
            {
/*1398*/        return localizer.localize(localizableERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS());
            }

            public static Localizable localizableERROR_ENTITY_STREAM_CLOSED()
            {
/*1402*/        return messageFactory.getMessage("error.entity.stream.closed", new Object[0]);
            }

            public static String ERROR_ENTITY_STREAM_CLOSED()
            {
/*1410*/        return localizer.localize(localizableERROR_ENTITY_STREAM_CLOSED());
            }

            public static Localizable localizableMESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED()
            {
/*1414*/        return messageFactory.getMessage("message.content.input.stream.close.failed", new Object[0]);
            }

            public static String MESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED()
            {
/*1422*/        return localizer.localize(localizableMESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED());
            }

            public static Localizable localizableERROR_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(Object obj, Object obj1)
            {
/*1426*/        return messageFactory.getMessage("error.provider.constrainedTo.wrong.package", new Object[] {
/*1426*/            obj, obj1
                });
            }

            public static String ERROR_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(Object obj, Object obj1)
            {
/*1434*/        return localizer.localize(localizableERROR_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(obj, obj1));
            }

            public static Localizable localizableSSL_KS_PROVIDERS_NOT_REGISTERED()
            {
/*1438*/        return messageFactory.getMessage("ssl.ks.providers.not.registered", new Object[0]);
            }

            public static String SSL_KS_PROVIDERS_NOT_REGISTERED()
            {
/*1446*/        return localizer.localize(localizableSSL_KS_PROVIDERS_NOT_REGISTERED());
            }

            public static Localizable localizablePROPERTIES_HELPER_GET_VALUE_NO_TRANSFORM(Object obj, Object obj1, Object obj2)
            {
/*1450*/        return messageFactory.getMessage("properties.helper.get.value.no.transform", new Object[] {
/*1450*/            obj, obj1, obj2
                });
            }

            public static String PROPERTIES_HELPER_GET_VALUE_NO_TRANSFORM(Object obj, Object obj1, Object obj2)
            {
/*1458*/        return localizer.localize(localizablePROPERTIES_HELPER_GET_VALUE_NO_TRANSFORM(obj, obj1, obj2));
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_INVALID_SYNTAX_TERMINATED(Object obj)
            {
/*1462*/        return messageFactory.getMessage("error.template.parser.invalid.syntax.terminated", new Object[] {
/*1462*/            obj
                });
            }

            public static String ERROR_TEMPLATE_PARSER_INVALID_SYNTAX_TERMINATED(Object obj)
            {
/*1470*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_INVALID_SYNTAX_TERMINATED(obj));
            }

            public static Localizable localizableURI_BUILDER_URI_PART_FRAGMENT(Object obj, Object obj1)
            {
/*1474*/        return messageFactory.getMessage("uri.builder.uri.part.fragment", new Object[] {
/*1474*/            obj, obj1
                });
            }

            public static String URI_BUILDER_URI_PART_FRAGMENT(Object obj, Object obj1)
            {
/*1482*/        return localizer.localize(localizableURI_BUILDER_URI_PART_FRAGMENT(obj, obj1));
            }

            public static Localizable localizableERROR_MBW_ISWRITABLE(Object obj)
            {
/*1486*/        return messageFactory.getMessage("error.mbw.iswritable", new Object[] {
/*1486*/            obj
                });
            }

            public static String ERROR_MBW_ISWRITABLE(Object obj)
            {
/*1494*/        return localizer.localize(localizableERROR_MBW_ISWRITABLE(obj));
            }

            public static Localizable localizableERROR_READING_ENTITY_MISSING()
            {
/*1498*/        return messageFactory.getMessage("error.reading.entity.missing", new Object[0]);
            }

            public static String ERROR_READING_ENTITY_MISSING()
            {
/*1506*/        return localizer.localize(localizableERROR_READING_ENTITY_MISSING());
            }

            public static Localizable localizableINVALID_HOST()
            {
/*1510*/        return messageFactory.getMessage("invalid.host", new Object[0]);
            }

            public static String INVALID_HOST()
            {
/*1518*/        return localizer.localize(localizableINVALID_HOST());
            }

            public static Localizable localizableDEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(Object obj, Object obj1, Object obj2)
            {
/*1522*/        return messageFactory.getMessage("dependent.class.of.provider.format.error", new Object[] {
/*1522*/            obj, obj1, obj2
                });
            }

            public static String DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(Object obj, Object obj1, Object obj2)
            {
/*1530*/        return localizer.localize(localizableDEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(obj, obj1, obj2));
            }

            public static Localizable localizableEXCEPTION_MAPPER_SUPPORTED_TYPE_UNKNOWN(Object obj)
            {
/*1534*/        return messageFactory.getMessage("exception.mapper.supported.type.unknown", new Object[] {
/*1534*/            obj
                });
            }

            public static String EXCEPTION_MAPPER_SUPPORTED_TYPE_UNKNOWN(Object obj)
            {
/*1542*/        return localizer.localize(localizableEXCEPTION_MAPPER_SUPPORTED_TYPE_UNKNOWN(obj));
            }

            public static Localizable localizableSSL_KMF_NO_PASSWORD_FOR_BYTE_BASED_KS()
            {
/*1546*/        return messageFactory.getMessage("ssl.kmf.no.password.for.byte.based.ks", new Object[0]);
            }

            public static String SSL_KMF_NO_PASSWORD_FOR_BYTE_BASED_KS()
            {
/*1554*/        return localizer.localize(localizableSSL_KMF_NO_PASSWORD_FOR_BYTE_BASED_KS());
            }

            public static Localizable localizableTYPE_TO_CLASS_CONVERSION_NOT_SUPPORTED(Object obj)
            {
/*1558*/        return messageFactory.getMessage("type.to.class.conversion.not.supported", new Object[] {
/*1558*/            obj
                });
            }

            public static String TYPE_TO_CLASS_CONVERSION_NOT_SUPPORTED(Object obj)
            {
/*1566*/        return localizer.localize(localizableTYPE_TO_CLASS_CONVERSION_NOT_SUPPORTED(obj));
            }

            public static Localizable localizableFEATURE_HAS_ALREADY_BEEN_PROCESSED(Object obj)
            {
/*1570*/        return messageFactory.getMessage("feature.has.already.been.processed", new Object[] {
/*1570*/            obj
                });
            }

            public static String FEATURE_HAS_ALREADY_BEEN_PROCESSED(Object obj)
            {
/*1578*/        return localizer.localize(localizableFEATURE_HAS_ALREADY_BEEN_PROCESSED(obj));
            }

            public static Localizable localizableSSL_CTX_INIT_FAILED()
            {
/*1582*/        return messageFactory.getMessage("ssl.ctx.init.failed", new Object[0]);
            }

            public static String SSL_CTX_INIT_FAILED()
            {
/*1590*/        return localizer.localize(localizableSSL_CTX_INIT_FAILED());
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_INVALID_SYNTAX(Object obj, Object obj1, Object obj2)
            {
/*1594*/        return messageFactory.getMessage("error.template.parser.invalid.syntax", new Object[] {
/*1594*/            obj, obj1, obj2
                });
            }

            public static String ERROR_TEMPLATE_PARSER_INVALID_SYNTAX(Object obj, Object obj1, Object obj2)
            {
/*1602*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_INVALID_SYNTAX(obj, obj1, obj2));
            }

            public static Localizable localizableURI_BUILDER_SCHEME_PART_UNEXPECTED_COMPONENT(Object obj, Object obj1)
            {
/*1606*/        return messageFactory.getMessage("uri.builder.scheme.part.unexpected.component", new Object[] {
/*1606*/            obj, obj1
                });
            }

            public static String URI_BUILDER_SCHEME_PART_UNEXPECTED_COMPONENT(Object obj, Object obj1)
            {
/*1614*/        return localizer.localize(localizableURI_BUILDER_SCHEME_PART_UNEXPECTED_COMPONENT(obj, obj1));
            }

            public static Localizable localizableSSL_TS_IMPL_NOT_FOUND()
            {
/*1618*/        return messageFactory.getMessage("ssl.ts.impl.not.found", new Object[0]);
            }

            public static String SSL_TS_IMPL_NOT_FOUND()
            {
/*1626*/        return localizer.localize(localizableSSL_TS_IMPL_NOT_FOUND());
            }

            public static Localizable localizableWARNING_MSG(Object obj)
            {
/*1630*/        return messageFactory.getMessage("warning.msg", new Object[] {
/*1630*/            obj
                });
            }

            public static String WARNING_MSG(Object obj)
            {
/*1638*/        return localizer.localize(localizableWARNING_MSG(obj));
            }

            public static Localizable localizableWARNING_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(Object obj, Object obj1, Object obj2, Object obj3)
            {
/*1642*/        return messageFactory.getMessage("warning.provider.constrainedTo.wrong.package", new Object[] {
/*1642*/            obj, obj1, obj2, obj3
                });
            }

            public static String WARNING_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(Object obj, Object obj1, Object obj2, Object obj3)
            {
/*1650*/        return localizer.localize(localizableWARNING_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(obj, obj1, obj2, obj3));
            }

            public static Localizable localizableHINTS_DETECTED(Object obj)
            {
/*1654*/        return messageFactory.getMessage("hints.detected", new Object[] {
/*1654*/            obj
                });
            }

            public static String HINTS_DETECTED(Object obj)
            {
/*1662*/        return localizer.localize(localizableHINTS_DETECTED(obj));
            }

            public static Localizable localizableHTTP_HEADER_UNBALANCED_COMMENTS()
            {
/*1666*/        return messageFactory.getMessage("http.header.unbalanced.comments", new Object[0]);
            }

            public static String HTTP_HEADER_UNBALANCED_COMMENTS()
            {
/*1674*/        return localizer.localize(localizableHTTP_HEADER_UNBALANCED_COMMENTS());
            }

            public static Localizable localizableURI_BUILDER_METHODNAME_NOT_SPECIFIED(Object obj, Object obj1)
            {
/*1678*/        return messageFactory.getMessage("uri.builder.methodname.not.specified", new Object[] {
/*1678*/            obj, obj1
                });
            }

            public static String URI_BUILDER_METHODNAME_NOT_SPECIFIED(Object obj, Object obj1)
            {
/*1686*/        return localizer.localize(localizableURI_BUILDER_METHODNAME_NOT_SPECIFIED(obj, obj1));
            }

            public static Localizable localizableSSL_KMF_UNRECOVERABLE_KEY()
            {
/*1690*/        return messageFactory.getMessage("ssl.kmf.unrecoverable.key", new Object[0]);
            }

            public static String SSL_KMF_UNRECOVERABLE_KEY()
            {
/*1698*/        return localizer.localize(localizableSSL_KMF_UNRECOVERABLE_KEY());
            }

            public static Localizable localizableINJECTION_ERROR_SUITABLE_CONSTRUCTOR_NOT_FOUND(Object obj)
            {
/*1702*/        return messageFactory.getMessage("injection.error.suitable.constructor.not.found", new Object[] {
/*1702*/            obj
                });
            }

            public static String INJECTION_ERROR_SUITABLE_CONSTRUCTOR_NOT_FOUND(Object obj)
            {
/*1710*/        return localizer.localize(localizableINJECTION_ERROR_SUITABLE_CONSTRUCTOR_NOT_FOUND(obj));
            }

            public static Localizable localizableAUTODISCOVERABLE_CONFIGURATION_FAILED(Object obj)
            {
/*1714*/        return messageFactory.getMessage("autodiscoverable.configuration.failed", new Object[] {
/*1714*/            obj
                });
            }

            public static String AUTODISCOVERABLE_CONFIGURATION_FAILED(Object obj)
            {
/*1722*/        return localizer.localize(localizableAUTODISCOVERABLE_CONFIGURATION_FAILED(obj));
            }

            public static Localizable localizableURI_COMPONENT_INVALID_CHARACTER(Object obj, Object obj1, Object obj2, Object obj3)
            {
/*1726*/        return messageFactory.getMessage("uri.component.invalid.character", new Object[] {
/*1726*/            obj, obj1, obj2, obj3
                });
            }

            public static String URI_COMPONENT_INVALID_CHARACTER(Object obj, Object obj1, Object obj2, Object obj3)
            {
/*1734*/        return localizer.localize(localizableURI_COMPONENT_INVALID_CHARACTER(obj, obj1, obj2, obj3));
            }

            public static Localizable localizableSSL_KS_FILE_NOT_FOUND(Object obj)
            {
/*1738*/        return messageFactory.getMessage("ssl.ks.file.not.found", new Object[] {
/*1738*/            obj
                });
            }

            public static String SSL_KS_FILE_NOT_FOUND(Object obj)
            {
/*1746*/        return localizer.localize(localizableSSL_KS_FILE_NOT_FOUND(obj));
            }

            public static Localizable localizableEXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS()
            {
/*1750*/        return messageFactory.getMessage("exception.caught.while.loading.spi.providers", new Object[0]);
            }

            public static String EXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS()
            {
/*1758*/        return localizer.localize(localizableEXCEPTION_CAUGHT_WHILE_LOADING_SPI_PROVIDERS());
            }

            public static Localizable localizableERROR_MSG(Object obj)
            {
/*1762*/        return messageFactory.getMessage("error.msg", new Object[] {
/*1762*/            obj
                });
            }

            public static String ERROR_MSG(Object obj)
            {
/*1770*/        return localizer.localize(localizableERROR_MSG(obj));
            }

            public static Localizable localizableURI_IS_NULL()
            {
/*1774*/        return messageFactory.getMessage("uri.is.null", new Object[0]);
            }

            public static String URI_IS_NULL()
            {
/*1782*/        return localizer.localize(localizableURI_IS_NULL());
            }

            public static Localizable localizableOSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(Object obj)
            {
/*1786*/        return messageFactory.getMessage("osgi.registry.error.processing.resource.stream", new Object[] {
/*1786*/            obj
                });
            }

            public static String OSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(Object obj)
            {
/*1794*/        return localizer.localize(localizableOSGI_REGISTRY_ERROR_PROCESSING_RESOURCE_STREAM(obj));
            }

            public static Localizable localizablePROVIDER_CLASS_COULD_NOT_BE_LOADED(Object obj, Object obj1, Object obj2)
            {
/*1798*/        return messageFactory.getMessage("provider.class.could.not.be.loaded", new Object[] {
/*1798*/            obj, obj1, obj2
                });
            }

            public static String PROVIDER_CLASS_COULD_NOT_BE_LOADED(Object obj, Object obj1, Object obj2)
            {
/*1806*/        return localizer.localize(localizablePROVIDER_CLASS_COULD_NOT_BE_LOADED(obj, obj1, obj2));
            }

            public static Localizable localizableCOMPONENT_TYPE_ALREADY_REGISTERED(Object obj)
            {
/*1810*/        return messageFactory.getMessage("component.type.already.registered", new Object[] {
/*1810*/            obj
                });
            }

            public static String COMPONENT_TYPE_ALREADY_REGISTERED(Object obj)
            {
/*1818*/        return localizer.localize(localizableCOMPONENT_TYPE_ALREADY_REGISTERED(obj));
            }

            public static Localizable localizableERROR_ENTITY_PROVIDER_BASICTYPES_UNKWNOWN(Object obj)
            {
/*1822*/        return messageFactory.getMessage("error.entity.provider.basictypes.unkwnown", new Object[] {
/*1822*/            obj
                });
            }

            public static String ERROR_ENTITY_PROVIDER_BASICTYPES_UNKWNOWN(Object obj)
            {
/*1830*/        return localizer.localize(localizableERROR_ENTITY_PROVIDER_BASICTYPES_UNKWNOWN(obj));
            }

            public static Localizable localizableSTRING_IS_NULL()
            {
/*1834*/        return messageFactory.getMessage("string.is.null", new Object[0]);
            }

            public static String STRING_IS_NULL()
            {
/*1842*/        return localizer.localize(localizableSTRING_IS_NULL());
            }

            public static Localizable localizableDATE_IS_NULL()
            {
/*1846*/        return messageFactory.getMessage("date.is.null", new Object[0]);
            }

            public static String DATE_IS_NULL()
            {
/*1854*/        return localizer.localize(localizableDATE_IS_NULL());
            }

            public static Localizable localizableERROR_RESOLVING_GENERIC_TYPE_VALUE(Object obj, Object obj1)
            {
/*1858*/        return messageFactory.getMessage("error.resolving.generic.type.value", new Object[] {
/*1858*/            obj, obj1
                });
            }

            public static String ERROR_RESOLVING_GENERIC_TYPE_VALUE(Object obj, Object obj1)
            {
/*1866*/        return localizer.localize(localizableERROR_RESOLVING_GENERIC_TYPE_VALUE(obj, obj1));
            }

            public static Localizable localizableERROR_TEMPLATE_PARSER_NAME_MORE_THAN_ONCE(Object obj, Object obj1)
            {
/*1870*/        return messageFactory.getMessage("error.template.parser.name.more.than.once", new Object[] {
/*1870*/            obj, obj1
                });
            }

            public static String ERROR_TEMPLATE_PARSER_NAME_MORE_THAN_ONCE(Object obj, Object obj1)
            {
/*1878*/        return localizer.localize(localizableERROR_TEMPLATE_PARSER_NAME_MORE_THAN_ONCE(obj, obj1));
            }

            public static Localizable localizableSSL_TS_INTEGRITY_ALGORITHM_NOT_FOUND()
            {
/*1882*/        return messageFactory.getMessage("ssl.ts.integrity.algorithm.not.found", new Object[0]);
            }

            public static String SSL_TS_INTEGRITY_ALGORITHM_NOT_FOUND()
            {
/*1890*/        return localizer.localize(localizableSSL_TS_INTEGRITY_ALGORITHM_NOT_FOUND());
            }

            private static final LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("org.glassfish.jersey.internal.localization");
            private static final Localizer localizer = new Localizer();

}
