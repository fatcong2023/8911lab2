package xs8911mid;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;

public class RestApiFunc {
    @FunctionName("RestApiFunction")
    public HttpResponseMessage getHello(
        @HttpTrigger(name = "req", 
                     methods = {HttpMethod.GET},
                     authLevel = AuthorizationLevel.ANONYMOUS,
                     route = "get") HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {


        Logger logger = context.getLogger();
        logger.info("HTTP trigger function processed a request.");

        List<String> students;
        try {
            students = DatabaseService.getStudents();
        } catch (Exception e) {
            logger.severe("Database connection error: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Database connection error: " + e.getMessage())
                          .build();
        }

        String jsonResponse = "[" + String.join(",", students) + "]";
        return request.createResponseBuilder(HttpStatus.OK)
                      .header("Content-Type", "application/json")
                      .body(jsonResponse)
                      .build();

    }
}
