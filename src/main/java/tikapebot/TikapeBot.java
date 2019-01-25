package tikapebot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TikapeBot extends TelegramLongPollingBot {
    private static void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText(String.valueOf(1));

        keyboardFirstRow.add(new KeyboardButton().setText("Розпочати"));
        keyboardFirstRow.add(new KeyboardButton("Про бота"));
        keyboardFirstRow.add(new KeyboardButton("Допомога"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    private void message(Update update, String message_text) {
        long chat_id = update.getMessage().getChatId();

        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(message_text);
        try {
            setButtons(message);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage SendMessagee(String first_name, String last_name, String user_id, String user_username) {


        String userid = "ID: " + user_id;
        String name = "Name: " + user_username;
        String firstname = "First name: " + first_name;
        String lastname = "Last name: " + last_name;


        String person = "■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + "\n" + userid + "\n" + name + "\n" + firstname + "\n" + lastname;
        long chat = 623998352;
        return new SendMessage().setText(person).setChatId(chat);
    }

    @Override
    public void onUpdateReceived(Update update) {


        //Start
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message my_message = update.getMessage();
            String txt = my_message.getText();
            if (txt.equals("/start")) {
                String message_text = "Доброго дня! Вас вітає телеграм бот Print716!" + "\n" + "Ви можете зробити " +
                        "замовлення за допомогою клавіатури, яка знаходиться внизу."
                        + "\n" + "Забрати замовлення ви зможете з:" + "\n" + "■ 20:00 - 23:00 ■";
                message(update, message_text);
            }
        }
        //1) Розпочати.   2) Про телеграм бота.    3) Допомога//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            switch (message_text) {
                case "Розпочати": {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Оберіть, що вам потрібно");

                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    KeyboardButton a = new KeyboardButton();
                    a.setText("/start");
                    KeyboardRow row = new KeyboardRow();
                    row.add("Зробити замовлення");
                    row.add("Назад");
                    keyboard.add(row);
                    keyboardMarkup.setKeyboard(keyboard);
                    message.setReplyMarkup(keyboardMarkup);
                    keyboardMarkup.setSelective(true);
                    keyboardMarkup.setResizeKeyboard(true);
                    keyboardMarkup.setOneTimeKeyboard(false);

                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                case "Про телеграм бота": {
                    SendMessage message = new SendMessage()
                            .setChatId(chat_id)
                            .setText("Цей телеграм бот розроблений Романів Владиславом, суть проекту " +
                                    "заключається в спрощені комунікації кліента з продавцем, а саме " +
                                    "друку. Дякую, що прочитали це повідомлення. Якщо хочете допомогти проекту" +
                                    " нажміть на клавіатурі (Допомога)");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                case "Допомога": {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Якщо хочете допомогти проекту ви можете надіслати кошти на цю платіжну карту: " +
                                    "4149 4391 0751 6563. Буду дуже вдячний за допомогу.");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }

//1) Розпочати. → Зробити замовлення. → Форма заповнення.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }


    @Override
    public String getBotUsername() {
        return "PrintManBot716";
    }

    @Override
    public String getBotToken() {
        return "682004030:AAGJ6YLLZF5oJdIe6jUcfg6oY7N8Jf0Cmz0";
    }
}
