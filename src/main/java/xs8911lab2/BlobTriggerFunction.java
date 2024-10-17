package xs8911lab2;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.logging.Logger;

public class BlobTriggerFunction {
    @FunctionName("BlobTriggerFunction")
    public void run(
        @BlobTrigger(name = "content", 
                     path = "blob1/{name}", 
                     dataType = "binary",
                     connection = "AzureWebJobsStorage") byte[] content,
        @BindingName("name") String fileName,
        final ExecutionContext context) {

        Logger logger = context.getLogger();
        logger.info("Blob trigger function processed a blob.");
        logger.info("Blob name: " + fileName);
        logger.info("Blob size: " + content.length + " Bytes");
    }
}