package org.anchor.affe.controller

import org.anchor.affe.service.MessageService
import org.anchor.affe.util.LanguageUtils
import org.anchor.affe.util.SupportedLanguage
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Demo controller for internationalization functionality
 * This demonstrates how to use the af-fe multi-language support
 */
@RestController
@RequestMapping("/api/i18n")
class I18nDemoController(private val messageService: MessageService) {

    /**
     * Get a localized welcome message
     * @return localized welcome message
     */
    @GetMapping("/welcome")
    fun getWelcomeMessage(): Map<String, Any> {
        return mapOf(
            "message" to messageService.getMessage("welcome.message"),
            "locale" to LanguageUtils.getCurrentLocale().toString(),
            "languageCode" to (LanguageUtils.getCurrentLanguageCode() ?: "unknown")
        )
    }

    /**
     * Get all supported languages
     * @return map of supported languages
     */
    @GetMapping("/languages")
    fun getSupportedLanguages(): Map<String, String> {
        return SupportedLanguage.getLanguageMap()
    }

    /**
     * Get a specific message by key
     * @param key the message key
     * @return localized message
     */
    @GetMapping("/message/{key}")
    fun getMessage(@PathVariable key: String): Map<String, Any> {
        return mapOf(
            "key" to key,
            "message" to messageService.getMessage(key),
            "locale" to LanguageUtils.getCurrentLocale().toString(),
            "exists" to messageService.hasMessage(key)
        )
    }

    /**
     * Change the current locale
     * @param languageCode the language code to set
     * @return confirmation message
     */
    @PostMapping("/language/{languageCode}")
    fun changeLanguage(@PathVariable languageCode: String): Map<String, Any> {
        return try {
            LanguageUtils.setCurrentLocaleByCode(languageCode)
            mapOf(
                "success" to true,
                "message" to "Language changed successfully",
                "newLocale" to LanguageUtils.getCurrentLocale().toString(),
                "newLanguageCode" to languageCode
            )
        } catch (e: IllegalArgumentException) {
            mapOf(
                "success" to false,
                "message" to e.message.orEmpty(),
                "supportedLanguages" to SupportedLanguage.getAllCodes()
            )
        }
    }

    /**
     * Get current locale information
     * @return current locale details
     */
    @GetMapping("/current")
    fun getCurrentLocaleInfo(): Map<String, Any> {
        val currentLocale = LanguageUtils.getCurrentLocale()
        val currentLanguage = LanguageUtils.getCurrentLanguageCode()
        
        return mapOf(
            "locale" to currentLocale.toString(),
            "languageCode" to (currentLanguage ?: "unknown"),
            "language" to currentLocale.language,
            "country" to currentLocale.country,
            "displayName" to currentLocale.displayName
        )
    }
}