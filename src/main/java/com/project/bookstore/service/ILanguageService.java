package com.project.bookstore.service;

import com.project.bookstore.models.Language;
import java.util.List;

public interface ILanguageService {

    void saveLanguage(Language language);

    boolean isLanguageExist(Language language);
    
    List<Language> findAllLanguages();
}
