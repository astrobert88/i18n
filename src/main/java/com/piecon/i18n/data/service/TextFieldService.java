package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TextFieldService extends I18nEntityService {

    private TextFieldRepository textFieldRepository;

    public TextFieldService(TextFieldRepository textFieldRepository) {
        super(textFieldRepository);
        this.textFieldRepository = textFieldRepository;
    }

    @SuppressWarnings("unchecked")
    public List<TextField> getAllTextFields() {
        return (List<TextField>) (List<?>) getAllI18nEntities();
    }
}
