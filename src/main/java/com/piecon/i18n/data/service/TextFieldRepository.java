package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.TextField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextFieldRepository extends JpaRepository<TextField, Long> {
}
