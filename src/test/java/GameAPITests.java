
import com.vodafone.GameAPIs.CreateGame;
import com.vodafone.GameAPIs.CreateMove;
import com.vodafone.GameAPIs.CreateUser;

import io.restassured.response.Response;


import org.testng.annotations.Test;


import static org.hamcrest.Matchers.equalTo;



public class GameAPITests {


    private int positionX = 4;
    private int positionY= 5;

    private String ValidNickname="Test";

    private String ValidEmail="test@example.com";

    private String ExistingNickname="string";

    private String ExistingEmail="example@vodafone.com";


    @Test (priority = 1)
    public void testsGameAPI(){
        String userToken = new CreateUser().CreateUserAndReturnUserToken(ValidNickname,ValidEmail);

        String gameToken = new CreateGame().JoinGameAndReturnGameToken(userToken);


        Response move =new CreateMove().
                MakeMoveAndReturnGameResponse(userToken,gameToken,positionX,positionY);

        move.
                then().
                assertThat().body("coordinates[0].x",equalTo(positionX)).
                assertThat().body("coordinates[0].y",equalTo(positionY));
    }

    @Test(priority = 2)
    public void testsAlreadyRegisteredEmail(){
        new CreateUser().CreateUserWithExistingCredentials(ExistingNickname,ExistingEmail);
    }
}
