package com.kidzoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// Swagger URL: http://localhost:8080/kidzoo/swagger-ui/index.html

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.kidzoo"))
				.paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Swagger Kidzoo Toy Store Service")
				.description("This is Kidzoo Toy Store Service")
				.version("1.0.0")
				.license("Contact storeteam@kidzoo.com")
				.licenseUrl("http://kidzoo.store.com/licenses/LICENSE-2.0.html")
				.contact(new Contact("Kidzoo Toy Store Service", "kidzoo.store.com", "storeteam@kidzoo.com"))
				.build();
	}

}