package tasks.apitest;

import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import tasks.data.Task;

import java.time.LocalDate;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class ApiTest {

    private final String URL = "http://localhost:8001/tasks-backend";

    @Test
    public void deveRetornarTarefas() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get(URL + "/todo")
                .then()
                    .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefas() {
        Task todo = new Task("todo", LocalDate.now().plusDays(1).toString());

        RestAssured
                .given()
                    .contentType(JSON)
                    .body(todo)
                .when()
                    .post(URL + "/todo")
                .then()
                    .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida() {
        Task todo = new Task("todo", LocalDate.now().minusDays(1).toString());

        RestAssured
                .given()
                    .contentType(JSON)
                    .body(todo)
                .when()
                    .post(URL + "/todo")
                .then()
                    .statusCode(400)
                    .body("message", is("Due date must not be in past"));
    }

}
