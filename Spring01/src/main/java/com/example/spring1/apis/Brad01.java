package com.example.spring1.apis;

import org.springframework.stereotype.Component;

/*
 * IoC => Bean => @Component(類別) or @Bean(方法 => return 物件)
 */
@Component
public class Brad01 {
	public Brad01() {
		System.out.println("Brad01()");
	}
	
	public Brad01(int a) {
		System.out.println("Brad01(int)");
	}
}
