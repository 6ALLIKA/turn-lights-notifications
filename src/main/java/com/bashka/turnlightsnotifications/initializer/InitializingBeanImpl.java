package com.bashka.turnlightsnotifications;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InitializingBeanImpl implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
//    DictionaryAdditionService dictionaryAdditionService;
//    ResourceLoader resourceLoader;
//
//    @Override
//    public void afterPropertiesSet() {
//        Map<String, XSSFWorkbook> defaultDictionaryMap = resourceLoader.getDefaultDictionaries();
//        for (Map.Entry<String, XSSFWorkbook> pair : defaultDictionaryMap.entrySet()) {
//            dictionaryAdditionService.addDefaultDictionary(pair.getKey(), pair.getValue());
//        }
//    }
}