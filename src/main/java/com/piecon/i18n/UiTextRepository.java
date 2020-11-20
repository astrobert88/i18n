package com.piecon.i18n;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UiTextRepository extends JpaRepository<UiText, Integer> {

    UiText findByLanguageCodeAndCountryCodeAndTextKey(String languageCode, String countryCode, String textKey);

    List<UiText> findAll();

    // Custom query to fetch data.
    // Note that the syntax uses class- and property names, not table/column names!
    @Query("SELECT DISTINCT languageCode, countryCode FROM UiText")
    String[][] findDistinctLocales();

}
