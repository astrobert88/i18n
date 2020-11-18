package com.piecon.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) { this.messageRepository = messageRepository; }

    public Message getMessage(String messageKey, Locale locale) {
        Message msg = messageRepository.findByLanguageCodeAndCountryCodeAndMessageKey(
                locale.getLanguage(), locale.getCountry(), messageKey);


        if (msg == null) {
            msg = new Message();
            msg.setMessageContent("No message in table: languageCode="+ locale.getLanguage() + "," +
                                    "countryCode="+locale.getCountry() + " " + messageKey
            );
        }

        return msg;
    }

    public String[][] findDistinctLocales() {
        return messageRepository.findDistinctLocales();
    }
}
