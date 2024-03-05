package com.jpa.test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootjpaexampleApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(BootjpaexampleApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
//		User user = new User();
//		user.setName("Durgesh Kumar Tiwari");
//		user.setCity("Delhi");
//		user.setStatus("I am java programmer");
//		
//		User user1 = userRepository.save(user);
//		System.out.println(user1);
//		System.out.println(1);
//		User user1 = new User();
//		user1.setName("Uttam");
//		user1.setCity("City1");
//		user1.setStatus("Java Programmer");
//		
//		
//		User user2 = new User();
//		user2.setName("Ankit");
//		user2.setCity("City2");
//		user2.setStatus("C Programmer");
//		List<User> users = List.of(user1,user2);
//		//save multiple objects
//		Iterable<User> result = userRepository.saveAll(users);
//		result.forEach(user->{
//			System.out.println(user);
//		});
		
		//update
//		Optional<User> optional = userRepository.findById(353);
//		User user = optional.get();
//		System.out.println(user);
//		user.setName("Atunabh");
//		userRepository.save(user);
//		System.out.println(user);
		
		//how to get the data
		// findById()-return Optional containing the data
		Iterable<User> itr = userRepository.findAll();
		//using iterator
//		Iterator<User> iterator = itr.iterator();
//		while(iterator.hasNext()) {
//			User user = iterator.next();
//			System.out.println(user);
//		}
		
		//using consumer
//		itr.forEach(new Consumer<User>() {
//
//			@Override
//			public void accept(User t) {
//				// TODO Auto-generated method stub
//				System.out.println(t);
//				
//			}
//			
//		});
		
//		itr.forEach(user->System.out.println(user));
		
		//deleting the user element
		
//		userRepository.deleteById(353);
//		System.out.println("deleted");
		
//		List<User> users = userRepository.findByName("Uttam");
//		users.forEach(e->System.out.println(e));
		
		
//		List<User> result = userRepository.findByNameAndCity("Uttam", "City1");
//		result.forEach(e->System.out.println(e));
		
		//List<User> allUser = userRepository.getAllUser();
		
		//allUser.forEach(e->System.out.println(e));
		
//		List<User> userByName = userRepository.getUserByName("Durgesh Kumar Tiwari","Delhi");
//		userByName.forEach(e->System.out.println(e));
		
		
		List<User> users = userRepository.getUsers();
		users.forEach(e->{System.out.println(e);});
		
		
		
	}

}
