package modulefactory.postcode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(basePackages = {"modulefactory.postcode"}, excludeFilters = {@ComponentScan.Filter(value = {Controller.class, Configuration.class})})
public class AppContextConfig {

}
