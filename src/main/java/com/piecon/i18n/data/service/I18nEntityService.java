package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.I18nEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This service returns an entity which has an i18nKey, languageCode and countryCode.
 */
@Slf4j
public class I18nEntityService {

    private JpaRepository jpaRepository;
    @Getter
    private List<I18nEntity> i18nEntities;

    public I18nEntityService(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;

        // Store all the i18nEntities in memory to avoid hitting the database every time a translation is needed.
        i18nEntities = jpaRepository.findAll();
        log.info("Loaded " + i18nEntities.size() + " i18nEntities");
    }

    /**
     * Returns all I18nEntities in the repository irrespective of language code and country code.
     */
    public List<I18nEntity> getAllI18nEntities() {
        return jpaRepository.findAll();
    }

    /**
     * Returns the I18nEntity with the given i18nKey for the given Locale.
     *
     * @param i18nKey
     * @param locale
     * @return
     */
    public I18nEntity getI18nEntity(@NonNull String i18nKey, @NonNull Locale locale) {
        log.info("getI18nEntity(i18nKey=" + i18nKey + ", locale=" + locale + ")");

        // First see if there are any entities with the given i18nKey and language code. If there aren't, give up
        List<I18nEntity> entitiesWithMatchingI18nKeyAndLanguageCode =
                i18nEntities.stream().filter(uit ->
                        uit.getI18nKey().equals(i18nKey) &&
                        uit.getLanguageCode().equals(locale.getLanguage()))
                        .collect(Collectors.toList());

        // If there are no entities with a matching key and language at all, log the error and return null.
        if (entitiesWithMatchingI18nKeyAndLanguageCode.isEmpty()) {
            log.error("No I18nEntity found with i18nKey=" + i18nKey +" and languageCode=" + locale.getLanguage());

            return null;
        } else {
            // We have a match. Now check for 1) matching countryCode 2) countryCode='' 3) any countryCode at all
            Optional<I18nEntity> countryCodeMatch = entitiesWithMatchingI18nKeyAndLanguageCode
                    .stream()
                    .filter(uit -> uit.getCountryCode().equals(locale.getCountry()))
                    .findAny();

            // If we have an exact match for i18nKey, languageCode and countryCode, return it
            if (countryCodeMatch.isPresent()) {
                I18nEntity i18nEntity = countryCodeMatch.get();
                log.info("Returning " + i18nEntity);

                return i18nEntity;
            } else {
                // The i18nKey and languageCode match but there is no matching countryCode.
                // First see if there is an entity where countryCode is ''
                Optional<I18nEntity> countryCodeEmptyMatch = entitiesWithMatchingI18nKeyAndLanguageCode
                        .stream()
                        .filter(uit -> uit.getCountryCode().equals(""))
                        .findAny();

                // If there is a language entry with an empty country code, return that
                if (countryCodeEmptyMatch.isPresent()) {
                    I18nEntity i18nEntity = countryCodeEmptyMatch.get();
                    log.info("Returning " + i18nEntity);

                    return i18nEntity;
                }
                // Just return the first country match in the list
                else {
                    return entitiesWithMatchingI18nKeyAndLanguageCode.get(0);
                }
            }
        }

    }
}