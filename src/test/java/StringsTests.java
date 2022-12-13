import Data.StaticDataProvider;
import com.vodafone.WorkingWithStrings;
import org.testng.annotations.Test;

public class StringsTests {



    @Test (dataProvider = "StringsWithDiacritics", dataProviderClass = StaticDataProvider.class)
    public void testsStringsWithDiacritics (String string){
        WorkingWithStrings workingWithStrings= new WorkingWithStrings();
        System.out.println(workingWithStrings.RemoveDiacritics(string));
    }
}
