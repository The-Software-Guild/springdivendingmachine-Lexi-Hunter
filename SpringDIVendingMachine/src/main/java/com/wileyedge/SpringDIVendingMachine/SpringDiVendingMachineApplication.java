package com.wileyedge.SpringDIVendingMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wileyedge.SpringDIVendingMachine.controller.VendingMachineController;

@SpringBootApplication
public class SpringDiVendingMachineApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringDiVendingMachineApplication.class, args);
		
		VendingMachineController controller = ctx.getBean("vendingMachineController", VendingMachineController.class);
		controller.run();
	}
}
