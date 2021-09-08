package by.prus.citybot.service;

import by.prus.citybot.exception.CityServiceException;
import by.prus.citybot.model.CityDTO;
import lombok.extern.flogger.Flogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Component
public class Bot extends TelegramLongPollingBot {

    private SendMessage.SendMessageBuilder builder = SendMessage.builder();
    private static final Logger logger = LogManager.getLogger(Bot.class);

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;
    @Autowired
    CityService cityService;


    /**
     * Метод для приема сообщений. Точка входа
     * @param update Содержит сообщение от пользователя.
     */

    @Override
    public void onUpdateReceived(Update update) {
        logger.info(update.toString());
        update.getUpdateId();
        String messageText;
        String chatId;
        if (update.getMessage() != null) {
            chatId = update.getMessage().getChatId().toString();
            builder.chatId(chatId);
            messageText = update.getMessage().getText();
        } else {
            chatId = update.getChannelPost().getChatId().toString();
            builder.chatId(chatId);
            messageText = update.getChannelPost().getText();
        }

        if (messageText.isEmpty()){ sendMessage("Введите текст"); }
        else if (messageText.contains("/chartId")) { sendMessage("ID Канала : " + chatId); }
        else if (messageText.contains("/start")){sendMessage("Чтобы получить информацю о городе - введите название города на русском языке");}
        else {sendCityInfo(messageText);}

    }


    private void sendCityInfo(String messageText){
        CityDTO cityDTO = null;
        try{
            cityDTO= cityService.findByName(messageText);
            sendMessage(cityDTO.getInfo());
        }catch (CityServiceException e){
            sendMessage("Информации о таком городе нет. Обратитесь к администратору.\n"+ e);
            logger.error("Message not sent. Update message {}", e);
        }
    }

    public void sendMessage (String message){
        builder.text(message);
        logger.info("Message was sent");
        try {
            execute(builder.build());
        } catch (TelegramApiException e) {
            logger.debug(e.toString());
        }
    }

    @Override
    public String getBotUsername() { return username; }
    @Override
    public String getBotToken() {
        return token;
    }

}

