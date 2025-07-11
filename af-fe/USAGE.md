# AF-FE Module Usage Examples

This document provides examples of how to use the af-fe module for multi-language support.

## Quick Start

### 1. Include the module in your Spring Boot application

Add the af-fe module as a dependency and ensure your application scans the `org.anchor.affe` package:

```kotlin
@SpringBootApplication
@ComponentScan(basePackages = ["com.yourapp", "org.anchor.affe"])
class YourApplication

fun main(args: Array<String>) {
    runApplication<YourApplication>(*args)
}
```

### 2. Use MessageService for localized messages

```kotlin
@RestController
class YourController(private val messageService: MessageService) {

    @GetMapping("/hello")
    fun hello(): String {
        return messageService.getMessage("welcome.message")
    }
}
```

### 3. Change language programmatically

```kotlin
@Service
class YourService {
    
    fun switchToChineseSimplified() {
        LanguageUtils.setCurrentLocaleByCode("zh_CN")
    }
    
    fun switchToJapanese() {
        LanguageUtils.setCurrentLocaleByCode("ja")
    }
}
```

### 4. Change language via HTTP request

Send a request with the `lang` parameter:
```
GET /your-endpoint?lang=zh_CN
GET /your-endpoint?lang=ja
GET /your-endpoint?lang=zh_TW
GET /your-endpoint?lang=en
```

## Available Message Keys

The following keys are available in all supported languages:

| Key | English | 简体中文 | 日本語 | 繁體中文 |
|-----|---------|----------|---------|----------|
| welcome.message | Welcome | 欢迎 | いらっしゃいませ | 歡迎 |
| common.save | Save | 保存 | 保存 | 儲存 |
| common.cancel | Cancel | 取消 | キャンセル | 取消 |
| user.login | Login | 登录 | ログイン | 登入 |
| user.logout | Logout | 登出 | ログアウト | 登出 |

## Supported Languages

- **English (en)** - Default language
- **Chinese Simplified (zh_CN)** - 简体中文
- **Japanese (ja)** - 日本語
- **Chinese Traditional (zh_TW)** - 繁體中文

## REST API Endpoints

The module provides demo endpoints that you can use or reference:

- `GET /api/i18n/welcome` - Get localized welcome message
- `GET /api/i18n/languages` - Get all supported languages
- `POST /api/i18n/language/{code}` - Change current language
- `GET /api/i18n/current` - Get current locale information

## Adding Custom Messages

To add your own messages:

1. Add the key-value pairs to all language files:
   - `src/main/resources/i18n/messages.properties` (English)
   - `src/main/resources/i18n/messages_zh_CN.properties` (Chinese Simplified)
   - `src/main/resources/i18n/messages_ja.properties` (Japanese)
   - `src/main/resources/i18n/messages_zh_TW.properties` (Chinese Traditional)

2. Use the MessageService to retrieve your messages:
   ```kotlin
   val customMessage = messageService.getMessage("your.custom.key")
   ```