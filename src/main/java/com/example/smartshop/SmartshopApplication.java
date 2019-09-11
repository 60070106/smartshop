package com.example.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@RestController
public class SmartshopApplication {
	private static List<Pet> allPet = new ArrayList<Pet>();
	{
		File file = new File("data.txt");
		if(file.exists()) {
			readFile();
		}

		else{
			allPet.add(PetFactory.createPet(1, "แมวลายสลิด","mammal","น้องเชื่องมาก ขี้อ้อน อายุแค่ 3 เดือนเองค่ะ", 3000));
			allPet.add(PetFactory.createPet(2, "แมวดำ","mammal","ซนมาก ดือ แต่ขี้อ้อน อายุ 6 เดือนเองค่ะ", 2500));
			allPet.add(PetFactory.createPet(3, "ตุ๊กแกญี่ปุ่น","reptile","she is so cute!!!", 7000));
			allPet.add(PetFactory.createPet(4, "นกแก้ว","flying","น้องบินหายไปแล้วค่ะ แต่จะพยายามจับมาขาย", 300));
			writeFile();
		}
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
		return this.readFile();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Pet> create(@RequestBody Pet pet) {
		this.allPet.add(pet);
		this.writeFile();
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}

	@RequestMapping(value = "/findid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pet> getbyid(@PathVariable("id") int id){
		return new ResponseEntity<Pet>(allPet.get(id), HttpStatus.OK);
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

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") int id){
		allPet.remove(id);
		return "Delete succussful";
	}

	public List<Pet> readFile(){
		try {
			FileInputStream fis = new FileInputStream("data.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Pet> pets = (List<Pet>) ois.readObject();
			ois.close();
			return pets;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void writeFile(){
		try {
			FileOutputStream fos = new FileOutputStream("data.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.allPet);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	Owner user = Owner.getInstance();
	@RequestMapping(value = "/setOwnerName/{realname}/{surname}/{address}", method = RequestMethod.GET)
	public Owner setUser(@PathVariable String realname, @PathVariable String surname,@PathVariable String address){
		user.setRealname(realname);
		user.setSurname(surname);
		user.setAddress(address);
		return user;
	}
}
