package com.example.spring1.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad05")
public class Brad05 {
	
	@RequestMapping("/calc") // /brad05/calc?x=10&y=3
	public String calc(
		@RequestParam(required= false, defaultValue = "0") String x, 
		@RequestParam(required= false, defaultValue = "0") String y) {
		// System.out.printf("x = %s : y = %s\n",x ,y);
		// int sum = Integer.parseInt(x) + Integer.parseInt(y);
		// System.out.printf("sum = %d\n",sum);
		System.out.printf("x = %s : y = %s\n",x ,y);
		try {
			int r = Integer.parseInt(x) + Integer.parseInt(y);
			return r +"";
		} catch (NumberFormatException e) {
			return "ERROR";
		}
	}
}
