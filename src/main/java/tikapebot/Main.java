package tikapebot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static org.glassfish.grizzly.http.util.DataChunk.Type.String;

public class Main {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TikapeBot bot = new TikapeBot();

        try {
            telegramBotsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        // Telegram >>

    }
}
