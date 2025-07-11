package org.anchor.affe.util

import java.util.*

/**
 * Enum representing supported languages in the af-fe module
 */
enum class SupportedLanguage(
    val code: String,
    val displayName: String,
    val locale: Locale
) {
    ENGLISH("en", "English", Locale.ENGLISH),
    CHINESE_SIMPLIFIED("zh_CN", "简体中文", Locale.SIMPLIFIED_CHINESE),
    CHINESE_TRADITIONAL("zh_TW", "繁體中文", Locale.TRADITIONAL_CHINESE),
    JAPANESE("ja", "日本語", Locale.JAPANESE);

    companion object {
        /**
         * Get SupportedLanguage by code
         * @param code the language code
         * @return the corresponding SupportedLanguage or null if not found
         */
        fun fromCode(code: String): SupportedLanguage? {
            return values().find { it.code.equals(code, ignoreCase = true) }
        }

        /**
         * Get SupportedLanguage by locale
         * @param locale the locale
         * @return the corresponding SupportedLanguage or null if not found
         */
        fun fromLocale(locale: Locale): SupportedLanguage? {
            return values().find { it.locale == locale }
        }

        /**
         * Get all supported language codes
         * @return list of language codes
         */
        fun getAllCodes(): List<String> {
            return values().map { it.code }
        }

        /**
         * Get all supported locales
         * @return list of locales
         */
        fun getAllLocales(): List<Locale> {
            return values().map { it.locale }
        }

        /**
         * Get all supported languages as a map
         * @return map of code to display name
         */
        fun getLanguageMap(): Map<String, String> {
            return values().associate { it.code to it.displayName }
        }
    }
}