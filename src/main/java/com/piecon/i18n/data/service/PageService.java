package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.I18nEntity;
import com.piecon.i18n.data.entity.Page;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.piecon.i18n.core.I18nConstants.PAGE_404;
import static com.piecon.i18n.core.I18nConstants.PAGE_NOT_FOUND;

@Service
@Slf4j
public class PageService extends I18nEntityService {

    public PageService(PageRepository pageRepository) {
        super(pageRepository);
    }

    @SuppressWarnings("unchecked")
    public List<Page> getAllPages() {
        return (List<Page>) (List<?>) getAllI18nEntities();
    }

    /**
     * Returns the Page object with the given i18nKey for the given Locale.
     * <p>
     * This method never returns null; if no Page is found with the given key and locale, an error is logged and
     * the method returns a Page with the title set to '404' and text1 set to 'Page not found'.
     *
     * @param i18nKey
     * @param locale
     * @return
     */
    public Page getPage(@NonNull String i18nKey, @NonNull Locale locale) {
        log.info("getPage(i18nKey=" + i18nKey + ", locale=" + locale + ")");

        I18nEntity i18nEntity = getI18nEntity(i18nKey, locale);

        if (i18nEntity == null) {
            log.error("No Page found with i18nKey=" + i18nKey + " and locale=" + locale);
            log.info("Returning Page 404 instead");
            Page page404 = new Page(i18nKey, locale.getLanguage(), locale.getCountry());
            page404.setTitle(PAGE_404);
            page404.setText1(PAGE_NOT_FOUND);

            return page404;
        }
        else {
            return (Page) i18nEntity;
        }
    }
}
