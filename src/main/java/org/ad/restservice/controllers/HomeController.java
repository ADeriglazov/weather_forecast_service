package org.ad.restservice.controllers;


import org.ad.request.handler.OWMWeatherForecastRequestHandlerImpl;
import org.ad.request.handler.PageNotFoundException;
import org.ad.request.handler.WeatherForecastRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> sessions =
            new ConcurrentHashMap<>();

    @PostMapping(value = "/")
    public String greetingPost(@RequestParam(value = "city", defaultValue = "Москва") String city)
            throws IOException {
        WeatherForecastRequestHandler requestHandler = new OWMWeatherForecastRequestHandlerImpl();

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        String response = null;
        try {
            response = requestHandler.getResponse(city);
        } catch (PageNotFoundException e) {
            logger.warn(String.format("Handled PageNotFoundException. See the code below:%n%s", e.getMessage()));
            response = String.format("Запрос погоды в городе %s не может быть выполнен, приносим свои извенения. " +
                    "Возможно, ошибка в написании. Проверьте и попробуйте еще раз!", city);
        } catch (IOException e) {
            logger.warn(String.format("Handled IOException. While parsing:%n%s", e.getMessage()));
            response = String.format("Запрос погоды в городе %s не может быть выполнен, приносим свои извенения. " +
                    "Попробуйте мгновение спустя!", city);
        }
        ConcurrentLinkedDeque<String> queryStack2Session = sessions.getOrDefault(sessionId, new ConcurrentLinkedDeque<>());
        queryStack2Session.addFirst(response);
        sessions.put(sessionId, queryStack2Session);

        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String greetingGet(Model model) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        List<String> historyStack = new LinkedList<>(sessions.getOrDefault(sessionId, new ConcurrentLinkedDeque<>()));
        model.addAttribute("messages",historyStack);
        return "weather-view";
    }

}
