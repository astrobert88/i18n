package com.piecon.i18n;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findByLanguageCodeAndCountryCodeAndMessageKey(String languageCode, String countryCode, String messageKey);

    // Custom query to fetch data.
    // Note that the syntax uses class- and property names, not table/column names!
    @Query("SELECT DISTINCT languageCode, countryCode FROM Message")
    String[][] findDistinctLocales();

}
