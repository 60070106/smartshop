package com.example.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@RestController
public class SmartshopApplication {
	private static List<Pet> allPet = new ArrayList<Pet>();
	static {
		allPet.add(PetFactory.createPet(1, "แมวลายสลิด","mammal","น้องเชื่องมาก ขี้อ้อน อายุแค่ 3 เดือนเองค่ะ", 3000));
		allPet.add(PetFactory.createPet(2, "แมวดำ","mammal","ซนมาก ดือ แต่ขี้อ้อน อายุ 6 เดือนเองค่ะ", 2500));
		allPet.add(PetFactory.createPet(3, "ตุ๊กแกญี่ปุ่น","reptile","she is so cute!!!", 7000));
		allPet.add(PetFactory.createPet(4, "นกแก้ว","flying","น้องบินหายไปแล้วค่ะ แต่จะพยายามจับมาขาย", 300));
	}
	@RequestMapping("/")
	String home(){
		return "TEST";
	}
	public static void main(String[] args) {
		SpringApplication.run(SmartshopApplication.class, args);
	}

	@RequestMapping("/peties")
	public List<Pet> getAllPets(){
		return this.allPet;
	}

	@RequestMapping("/findid/{id}")
	public List<Pet> getPetid(@PathVariable int id){
		List<Pet> picked_id = new ArrayList<Pet>();
		for (Pet item: getAllPets()){
			if (item.getId() == id){
				picked_id.add(item);
			}
		}
		return picked_id;
	}

	@RequestMapping("/deletenamebyid/{id}")
	public String deletePetNameByid(@PathVariable int id){
		int picked_id = 0;
		String picked_name = "";
		for (Pet item: getAllPets()){
			if (item.getId() == id){
				picked_name = item.getName();
				item.setName("ถูกลบไปแล้วค่ะ");
				picked_id = item.getId();
			}
		}


		return "Your Name is delected from id:"+ picked_id +"<br>Deleted name :"+ picked_name;
	}
}
