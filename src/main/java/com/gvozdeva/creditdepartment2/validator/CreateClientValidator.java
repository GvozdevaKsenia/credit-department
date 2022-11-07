package com.gvozdeva.creditdepartment2.validator;

import com.gvozdeva.creditdepartment2.controller.dto.CreateClientDto;
import com.gvozdeva.creditdepartment2.util.LocalDateFormatter;

import java.util.regex.Pattern;

public class CreateClientValidator implements Validator<CreateClientDto> {

    private static final CreateClientValidator INSTANCE = new CreateClientValidator();

    private static final String TELEPHONE_REGEX = "\\+375\\s?\\(\\d{2}\\) \\d{3}-\\d{2}-\\d{2}";
    private static final String EMAIL_REGEX = "[a-zA-Z]\\w*@\\w{3,}\\.(com|ru|org|outlook|by)";
    private static final String NAME_REGEX = "[А-Я][а-я]+";
    private static final String PASSWORD_REGEX = "\\w{5,}";

    @Override
    public ValidationResult isValid(CreateClientDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthDate())) {
            validationResult.add(Error.of("invalid.birthday", "Дата рождения указана неверно"));
        }
        if (!Pattern.matches(TELEPHONE_REGEX, object.getTelephone())) {
            validationResult.add(Error.of("invalid.telephone", "Телефон указан неверно"));
        }
        if (!Pattern.matches(EMAIL_REGEX, object.getEmail())) {
            validationResult.add(Error.of("invalid.email", "Почта указана неверно"));
        }
        if (!Pattern.matches(NAME_REGEX, object.getFirstName())) {
            validationResult.add(Error.of("invalid.name", "Имя на кириллице и с большой буквы"));
        }
        if (!Pattern.matches(NAME_REGEX, object.getSurname())) {
            validationResult.add(Error.of("invalid.surname", "Фамилия на кириллице и с большой буквы"));
        }
        if (!Pattern.matches(PASSWORD_REGEX, object.getPassword())) {
            validationResult.add(Error.of("invalid.email", "Пароль меньше 5 символов"));
        }
        return validationResult;
    }

    public static CreateClientValidator getInstance() {
        return INSTANCE;
    }
}
