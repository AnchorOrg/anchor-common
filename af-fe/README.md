# AF-FE Module - Multi-Language Frontend Support

This module provides internationalization (i18n) support for frontend applications, supporting multiple languages including Chinese Simplified (CN), English (EN), Japanese (JA), and Chinese Traditional (TW).

## Supported Languages

- **English (EN)** - Default language
- **Chinese Simplified (CN)** - 简体中文
- **Japanese (JA)** - 日本語  
- **Chinese Traditional (TW)** - 繁體中文

## Features

- **MessageService**: Service for retrieving localized messages
- **InternationalizationConfig**: Spring configuration for i18n setup
- **SupportedLanguage**: Enum for managing supported languages
- **LanguageUtils**: Utility methods for language operations
- **I18nDemoController**: Example REST controller demonstrating usage

## Usage

### Basic Setup

1. Include the af-fe module in your project dependencies
2. Configure Spring to scan the `org.anchor.affe` package
3. Use the provided services and utilities in your application

### Example Usage

```kotlin
// Inject the MessageService
@Autowired
private lateinit var messageService: MessageService

// Get localized message
val welcomeMessage = messageService.getMessage("welcome.message")

// Change language
LanguageUtils.setCurrentLocaleByCode("zh_CN")

// Get message for specific locale
val message = messageService.getMessage("common.save", Locale.JAPANESE)
```

### REST API Examples

Change language:
```
POST /api/i18n/language/zh_CN
```

Get welcome message:
```
GET /api/i18n/welcome
```

Get supported languages:
```
GET /api/i18n/languages
```

## Message Keys

The following message keys are available in all supported languages:

- `welcome.message` - Welcome message
- `error.validation` - Validation error
- `error.not.found` - Resource not found
- `error.server` - Internal server error
- `common.save` - Save button
- `common.cancel` - Cancel button
- `common.delete` - Delete button
- `common.edit` - Edit button
- `common.back` - Back button
- `common.next` - Next button
- `common.previous` - Previous button
- `common.loading` - Loading message
- `common.success` - Success message
- `common.error` - Error message
- `common.warning` - Warning message
- `common.info` - Information message
- `user.name` - Name field
- `user.email` - Email field
- `user.password` - Password field
- `user.login` - Login button
- `user.logout` - Logout button
- `user.register` - Register button

## Configuration

The module uses Spring's built-in internationalization support with:

- **Resource Bundle**: Messages stored in `i18n/messages*.properties` files
- **Locale Resolver**: Session-based locale storage
- **Locale Interceptor**: URL parameter (`?lang=zh_CN`) for language switching

## Adding New Languages

To add support for new languages:

1. Create a new properties file: `src/main/resources/i18n/messages_[locale].properties`
2. Add the language to the `SupportedLanguage` enum
3. Update the documentation

## Testing

The module includes comprehensive tests for:
- Message retrieval in different languages
- Language switching functionality
- Utility methods
- Enum operations

Run tests with:
```bash
./gradlew :af-fe:test
```

## Dependencies

- Spring Boot Starter
- Spring Boot Starter Web
- Kotlin Standard Library
- Kotlin Serialization