package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotService implements UpdatesListener {

    private final TelegramBot telegramBot;

    private final NotificationService notificationService;

    public BotService(TelegramBot telegramBot, NotificationService notificationService) {
        this.telegramBot = telegramBot;
        this.notificationService = notificationService;
        this.telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        list.stream()
                .filter(update -> update.message() != null)
                .filter(update -> update.message().text() != null)
                .forEach(this::processUpdate);
        return CONFIRMED_UPDATES_ALL;
    }

    private void processUpdate(Update update) {
        String userMessage = update.message().text();
        Long chatId = update.message().chat().id();
        if (userMessage.equals("/start")) {
            this.telegramBot.execute(
                    new SendMessage(chatId, "Привет!\n" +
                            "Я бот-напоминалка о твоем запланированном событии.\n" +
                            "Введите сообщение в формате:\n" +
                            "'01.01.2023 20:00 Твое событие или задача'"));
        } else {
            if (this.notificationService.processNotification(chatId, userMessage)) {
                this.telegramBot.execute(new SendMessage(chatId, "Напоминание о событии создано"));
            } else {
                this.telegramBot.execute(
                        new SendMessage(chatId, "Неверный формат!\n" +
                                "Проверьте и введите сообщение в формате:\n" +
                                "'01.01.2023 20:00 Твое событие или задача'"));
            }
        }
    }
}
