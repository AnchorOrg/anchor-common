package org.anchor.affe.util

import org.springframework.context.i18n.LocaleContextHolder
import java.util.*

/**
 * Utility class for language-related operations
 */
object LanguageUtils {

    /**
     * Set the current locale
     * @param locale the locale to set
     */
    fun setCurrentLocale(locale: Locale) {
        LocaleContextHolder.setLocale(locale)
    }

    /**
     * Set the current locale by language code
     * @param languageCode the language code (e.g., "en", "zh_CN", "ja", "zh_TW")
     */
    fun setCurrentLocaleByCode(languageCode: String) {
        val supportedLanguage = SupportedLanguage.fromCode(languageCode)
        if (supportedLanguage != null) {
            setCurrentLocale(supportedLanguage.locale)
        } else {
            throw IllegalArgumentException("Unsupported language code: $languageCode")
        }
    }

    /**
     * Get the current locale
     * @return the current locale
     */
    fun getCurrentLocale(): Locale {
        return LocaleContextHolder.getLocale()
    }

    /**
     * Get the current language code
     * @return the current language code or null if not supported
     */
    fun getCurrentLanguageCode(): String? {
        val currentLocale = getCurrentLocale()
        return SupportedLanguage.fromLocale(currentLocale)?.code
    }

    /**
     * Check if a language code is supported
     * @param languageCode the language code to check
     * @return true if supported, false otherwise
     */
    fun isLanguageSupported(languageCode: String): Boolean {
        return SupportedLanguage.fromCode(languageCode) != null
    }

    /**
     * Parse locale from language code string
     * @param languageCode the language code (e.g., "zh_CN", "en")
     * @return the corresponding locale
     */
    fun parseLocale(languageCode: String): Locale {
        val parts = languageCode.split("_")
        return when (parts.size) {
            1 -> Locale(parts[0])
            2 -> Locale(parts[0], parts[1])
            3 -> Locale(parts[0], parts[1], parts[2])
            else -> throw IllegalArgumentException("Invalid language code format: $languageCode")
        }
    }

    /**
     * Format locale to language code string
     * @param locale the locale to format
     * @return the language code string
     */
    fun formatLanguageCode(locale: Locale): String {
        return if (locale.country.isNotEmpty()) {
            "${locale.language}_${locale.country}"
        } else {
            locale.language
        }
    }

    /**
     * Get default language for the system
     * @return the default language
     */
    fun getDefaultLanguage(): SupportedLanguage {
        return SupportedLanguage.ENGLISH
    }

    /**
     * Clear the current locale (reset to default)
     */
    fun clearCurrentLocale() {
        LocaleContextHolder.resetLocaleContext()
    }
}