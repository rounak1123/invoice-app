package com.increff.invoice.spring;

//import com.increff.pos.scheduler.DaySalesScheduler;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.increff.invoice")
@PropertySources({ //
		@PropertySource(value = "file:./pos.properties", ignoreResourceNotFound = true) //
})
@EnableScheduling
public class SpringConfig {
//
//	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaySalesScheduler.class);
//
//	}
}
