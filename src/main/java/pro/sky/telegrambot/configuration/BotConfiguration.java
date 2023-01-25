package pro.sky.telegrambot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    private final String botToken;

    public BotConfiguration(@Value("${telegram.bot.token}") String botToken) {
        this.botToken = botToken;
    }

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(this.botToken);
    }
}
