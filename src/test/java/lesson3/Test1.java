package lesson3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test1 extends AbstractTest{
    @Test
    void getRequest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("intolerances", "gluten")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200);

    }
    @Test
    void getResponse(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("limitLicense", "true")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive");
    }

    @Test
    void getResponse2(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minSugar", "10")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive");
    }

    @Test
    void getResponse3(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("fillIngredients", "false")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive");
    }

    @Test
    void Test4(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "cheese")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Connection", "keep-alive");
    }
    @Test
    void postRequest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

    }

    @Test
    void postRequest2() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

    }
    @Test
    void postRequest3() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","meat")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

    }
    @Test
    void postRequest4() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pasta")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .header("CF-Cache-Status", "DYNAMIC");
    }
    @Test
    void postRequest5() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("language","en")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .header("CF-Cache-Status", "DYNAMIC");
    }
    @Test
    void addTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
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
                .statusCode(200);
    }
}
