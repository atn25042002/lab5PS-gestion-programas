package pe.devstream;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LaunchApplication {

  @Value("${info.project.version}")
  private String docsVersionApi;

  @Value("${info.project.title}")
  private String titleApi;

  @Value("${info.project.description}")
  private String descripcionApi;

  @Value("${info.project.controller}")
  private String controllerPackageApi;

  @Value("${info.project.group}")
  private String groupApi;

  public static void main(String[] args) {
    SpringApplication.run(LaunchApplication.class, args);
  }
  @Bean
  public GroupedOpenApi streamOpenApi() {
    String[] packagedToMatch = { controllerPackageApi };
    return GroupedOpenApi.builder().group(groupApi)
            .addOpenApiCustomizer(openApi -> {
              openApi.info(new Info().title(titleApi).version(docsVersionApi).description(descripcionApi));
              if (openApi.getServers() != null && !openApi.getServers().isEmpty()) {
                openApi.getServers().get(0).setDescription("Servidor Local");
              }
            })
            .packagesToScan(packagedToMatch)
            .build();
  }
}
