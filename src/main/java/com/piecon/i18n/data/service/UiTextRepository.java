package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.UiText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UiTextRepository extends JpaRepository<UiText, Long> {

    // Custom query to fetch data.
    // Note that the syntax uses class- and property names, not table/column names!
    @Query("SELECT DISTINCT languageCode, countryCode FROM UiText")
    String[][] findDistinctLocales();

    @Query("SELECT DISTINCT languageCode FROM UiText")
    String[] findDistinctLanguages();

}
