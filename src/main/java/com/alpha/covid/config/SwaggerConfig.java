//package com.alpha.covid.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig
//{
//    @Bean
//    public Docket createRestApi()
//    {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.alpha.covid.controllers"))
//                .paths(PathSelectors.any())
//                .build();
//
//    }
//
//    private ApiInfo apiInfo()
//    {
//        return new ApiInfoBuilder()
//                .title("Covid-19 Api Doc")
//                .description("Swagger UI")
//                .termsOfServiceUrl("https://github.com/alphaoumardev")
//                .contact(new Contact("Diallo", "https://github.com/alphaoumardev", "oumardialo98@qq.com"))
//                .version("1.0")
//                .build();
//    }
//
////    private List<Parameter> buildAuthPar()
////    {
////        ParameterBuilder auth = new ParameterBuilder();
////        List<Parameter> pars = new ArrayList<>();
////        auth.name("Authorization").description("Connecting to the data").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
////        pars.add(auth.build());
////        return pars;
////    }
//}
