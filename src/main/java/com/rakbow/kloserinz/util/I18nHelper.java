package com.rakbow.kloserinz.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author Rakbow
 * @since 2024/2/21 11:48
 */
public class I18nHelper {

    private static MessageSource messageSource;

    public static void init() {
        if(messageSource == null){
            messageSource = SpringUtil.getMessageSource();
        }
    }

    public static String getMessage(String key) {
        init();
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    public static String getMessage(String key, Locale locale) {
        init();
        return messageSource.getMessage(key, null, locale);
    }

    public static String getMessage(String key, String... args) {
        init();
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

}
