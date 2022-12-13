package Data;

import org.testng.annotations.DataProvider;

public  class StaticDataProvider {

    @DataProvider(name = "StringsWithDiacritics")
    public static Object[][] createStringsData() {
        return new Object[][]{
                {"Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ"},
                {"àèé"}
        };

    }


}
