package otus.moryakovdv.task15;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Otus Task15", version = "0.0.1",
                license = @License(name = "moryakovdv (c)")),
        servers = {@Server(description = "Otus Java advanced") } )
/**Класс для OpenAPI*/
public class OpenApiConfiguration {}
