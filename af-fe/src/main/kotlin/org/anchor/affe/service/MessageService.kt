package org.anchor.affe.service

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*

/**
 * Service for handling internationalized messages
 * Provides convenient methods to retrieve localized messages
 */
@Service
class MessageService(private val messageSource: MessageSource) {

    /**
     * Get a localized message by key using the current locale
     * @param key the message key
     * @param args optional arguments for message formatting
     * @return the localized message
     */
    fun getMessage(key: String, vararg args: Any?): String {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale())
    }

    /**
     * Get a localized message by key for a specific locale
     * @param key the message key
     * @param locale the specific locale
     * @param args optional arguments for message formatting
     * @return the localized message
     */
    fun getMessage(key: String, locale: Locale, vararg args: Any?): String {
        return messageSource.getMessage(key, args, locale)
    }

    /**
     * Get a localized message by key with a default message
     * @param key the message key
     * @param defaultMessage the default message if key is not found
     * @param args optional arguments for message formatting
     * @return the localized message or default message
     */
    fun getMessageOrDefault(key: String, defaultMessage: String, vararg args: Any?): String {
        return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale())
    }

    /**
     * Get the current locale
     * @return the current locale
     */
    fun getCurrentLocale(): Locale {
        return LocaleContextHolder.getLocale()
    }

    /**
     * Check if a message key exists for the current locale
     * @param key the message key
     * @return true if the key exists, false otherwise
     */
    fun hasMessage(key: String): Boolean {
        return try {
            messageSource.getMessage(key, null, LocaleContextHolder.getLocale())
            true
        } catch (e: Exception) {
            false
        }
    }
}