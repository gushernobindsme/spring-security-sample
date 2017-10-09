package com.inmotion.security.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResourceHelper {

    private static String MESSAGE_KEY = "message";

    private static String ERROR_MESSAGE_KEY = "errorMessage";

    public static void addMessage(final MessageSource messageSource, Model model, String messageCode) {
        model.addAttribute(MESSAGE_KEY, getMessage(messageSource, messageCode));
    }

    public static void addErrorMessage(final MessageSource messageSource, Model model, String messageCode) {
        model.addAttribute(ERROR_MESSAGE_KEY, getMessage(messageSource, messageCode));
    }

    private static String getMessage(final MessageSource messageSource, final String code, final Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
