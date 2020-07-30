package social.network.springboot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class TemplateConfig {

//	@Bean
//	public ITemplateResolver templateResolver()
//	{
//		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("templates/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(TemplateMode.HTML);
//
//		return templateResolver;
//	}
//
//	@Bean
//	public TemplateEngine templateEngine()
//	{
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.setTemplateResolver(this.templateResolver());
//
//		return templateEngine;
//	}

	@Primary
	@Bean
	public FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean bean=new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("classpath:/templates");
		return bean;
	}
}
