package com.piecon.i18n.service;

import com.piecon.i18n.core.I18nConstants;
import com.piecon.i18n.data.entity.Page;
import com.piecon.i18n.data.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase
public class PageServiceTest implements I18nConstants {

    @Autowired
    private PageService pageService;

    @Test
    public void getAllPages() {
        log.info("getAllPages()");

        List<Page> allPages = pageService.getAllPages();
        Assert.assertEquals(3, allPages.size());
    }

    @Test
    public void getPageWhenI18nKeyNotFound() {
        log.info("getPageWhenI18nKeyNotFound()");

        Locale locale = new Locale.Builder().build();
        Page page = pageService.getPage("no.such.page", locale);

        Assert.assertNotNull(page);
        Assert.assertEquals(PAGE_404, page.getTitle());
        Assert.assertEquals(PAGE_NOT_FOUND, page.getText1());
    }
}
