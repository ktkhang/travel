package com.major.project.travel.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;
import java.util.Locale;

/**
 * Created by HUY on 9/9/2018
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.major.project.travel"})
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false)
//                .favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useJaf(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("html", MediaType.TEXT_HTML);
//    }
//
//    @Bean
//    public InternalResourceViewResolver resolver(){
//        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return  resolver;
//    }
//
//    /**
//     *   To support localization
//     * */
//
//    @Bean
//    public LocaleContextResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//
//    @Override
//    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
//        converters.add(converter());
//    }
//
//    // Config JSON
//    @Bean
//    public MappingJackson2HttpMessageConverter converter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new com.major.project.travel.json.JsonSerializer();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false); // Huy add to avoid InvalidDefinitionException exception to JavassistLazyInitializer object.
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST","PUT", "DELETE")
//                .allowedHeaders("*")
//                .exposedHeaders("Access-Control-Allow-Origin", "Content-Type", "Content-Language"
//                        , "Expires", "Last-Modified", "Pragma"
//                        , "Content-Length")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index");
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/view/");
//        bean.setSuffix(".jsp");
//
//        return bean;
//    }
}
