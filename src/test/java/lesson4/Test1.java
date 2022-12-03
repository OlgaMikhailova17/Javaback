package lesson4;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Test1 extends AbstractTest{
    @Test
    void getRequest() {
        given()
                .spec(getRequestSpecification())
                .queryParam("intolerances", "gluten")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getResponse(){
        given()
                .spec(getRequestSpecification())
                .queryParam("limitLicense", "true")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getResponse2(){
        given()
                .spec(getRequestSpecification())
                .queryParam("minSugar", "10")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getResponse3(){
        given()
                .spec(getRequestSpecification())
                .queryParam("fillIngredients", "false")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void Test4(){
        given()
                .spec(getRequestSpecification())
                .queryParam("includeIngredients", "cheese")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postRequest() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

    }

    @Test
    void postRequest2() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

    }
    @Test
    void postRequest3() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","meat")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

    }
    @Test
    void postRequest4() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pasta")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postRequest5() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void addTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .spec(getRequestSpecification())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/olga/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/olga/items/" + id)
                .then()
                .spec(responseSpecification);
    }
}

