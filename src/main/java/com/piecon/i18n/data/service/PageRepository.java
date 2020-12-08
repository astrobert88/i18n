package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
}
