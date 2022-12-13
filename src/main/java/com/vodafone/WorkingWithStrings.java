package com.vodafone;

import org.apache.commons.lang3.StringUtils;

public class WorkingWithStrings {


    public String RemoveDiacritics (String string){
        return StringUtils.stripAccents(string);
    }
}
