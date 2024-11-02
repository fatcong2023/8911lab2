package xs8911mid;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.logging.Logger;

public class RestApiFunc {
    @FunctionName("RestApiFunction")
    public HttpResponseMessage getHello(
        @HttpTrigger(name = "req", 
                     methods = {HttpMethod.GET}, 
                     route = "get") HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {

        Logger logger = context.getLogger();
        
        logger.info("HTTP trigger function processed a request.");

        // Return response with "hello word \n"
        return request.createResponseBuilder(HttpStatus.OK)
                      .body("hello word \n")
                      .build();
                      
    }
}
