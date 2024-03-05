package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import com.razorpay.*;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	//method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model ,Principal principal) {
		String username = principal.getName();
		System.out.println("USERNAME "+ username);
		
		User user = userRepository.getUserByUserName(username);
		System.out.println("USER:"+user);
		
		model.addAttribute("user",user);
		
	}
	
	//dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		model.addAttribute("title","User Dashboard");
		return "normal/user_dashboard";
	}
	//open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact",new Contact());
		return "normal/add_contact_form";
	}
	
	//processsing add contact form
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact,
			@RequestParam("profileImage")MultipartFile file ,
			Principal principal,HttpSession session) {
		try {
		String name = principal.getName();
	    User user = this.userRepository.getUserByUserName(name);
	    
	    
	    
	    //processing and uploading File
	    if(file.isEmpty()) {
	    	//if the file is empty then try our message
	    	System.out.println("File is Empty");
	    	contact.setImage("contact2.jpg");
	    	
	    }
	    else {
	    	//file the file to folder and update the name to contact
	    	contact.setImage(file.getOriginalFilename());
	    	
	    	File saveFile=new ClassPathResource("static/img").getFile();
	    	java.nio.file.Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+ file.getOriginalFilename());
	    	Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
	    	
	    	System.out.println("Image is Uploaded");
	    }
	    
	    
	    contact.setUser(user);
	    user.getContacts().add(contact);
	    this.userRepository.save(user);
		System.out.println("DATA"+contact);
		System.out.println("Added to DataBase");
		
		//message success
		session.setAttribute("message", new Message("Your Contact is added !! Add more..", "success"));
		
		
		}
		catch(Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong", "danger"));
			
		}
		return "normal/add_contact_form";
	}
	
	//show contacts handler
	//per page 5 contacts 5[n]
	//current page =0 [page]
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page,Model m,Principal principal) {
		m.addAttribute("title","Show User Contacts");
		//need to send the list of the contact
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		Pageable pageable = PageRequest.of(page, 3);
		
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
		m.addAttribute("contacts",contacts);
		m.addAttribute("currentPage",page);
		m.addAttribute("totalPages",contacts.getTotalPages());
		
		return "normal/show_contacts";
	}
	
	//showing particular contact details
	@RequestMapping("/{cId}/contact")
	public String showContactDetails(@PathVariable("cId") Integer cId,Model model,Principal principal) {
		System.out.println("CID "+cId);
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact = contactOptional.get();
		
		//
		String userName = principal.getName();
	    User user = this.userRepository.getUserByUserName(userName);
		
	    if(user.getId()==contact.getUser().getId())
	    {
	    	model.addAttribute("contact",contact);
	    	model.addAttribute("title",contact.getName());
	    	
	    }
	    return "normal/contact_detail";
		
      }
	
	//delete contact handler
	
	@GetMapping("/delete/{cid}")
	@Transactional
    public String deleteContact(@PathVariable("cid") Integer cId,Model model,HttpSession session,Principal principal) {
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact = contactOptional.get();
		
		//contact.setUser(null);
		//check....Assignment
		//remove img 
		//contact.getImage();
		User user = this.userRepository.getUserByUserName(principal.getName());
		user.getContacts().remove(contact);
		this.userRepository.save(user);
		
		
//		User user = this.userRepository.getUserByUserName(principal.getName());
//		user.getContacts().remove(contact);
		
		
		
		
		
		System.out.println("DELETED");
		session.setAttribute("message", new Message("Contact deleted successfully","success"));
    	return "redirect:/user/show-contacts/0";
    }
	
	//open update form handler
	@PostMapping("/update-contact/{cid}")
	public String updateForm(@PathVariable("cid") Integer cid,Model m) {
		m.addAttribute("title","Update Contact");
		Contact contact = this.contactRepository.findById(cid).get();
		m.addAttribute("contact",contact);
		return "normal/update_form";
	}
	
	
	//update contact handler
	@RequestMapping(value="/process-update",method=RequestMethod.POST)
	public String updateHandler(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file,Model m,HttpSession session
			,Principal principal) {
		try {
			Contact oldcontactDetail= this.contactRepository.findById(contact.getcId()).get();
			if(!file.isEmpty())
			{
				
				
				//file work
				//rewrit
				
				
				//delete old photo
				File deleteFile=new ClassPathResource("static/img").getFile();
				File file1 = new File(deleteFile,oldcontactDetail.getImage());
				file1.delete();
				
				
				///update new photo
				File saveFile = new ClassPathResource("static/img").getFile();
				java.nio.file.Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			    Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
			    contact.setImage(file.getOriginalFilename());
			}
			else {
				contact.setImage(oldcontactDetail.getImage());
			}
			User user = this.userRepository.getUserByUserName(principal.getName());
			contact.setUser(user);
					
			this.contactRepository.save(contact);
			session.setAttribute("message", new Message("Your contact is updated","success"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(contact.getcId());
		System.out.println(contact.getName());
		return "redirect:/user/"+contact.getcId()+"/contact";
	}
	
	//your profile handler
	@GetMapping("/profile")
	public String yourProfile(Model model) {
		model.addAttribute("title","profile page");
		return "normal/profile";
	}
	
	//open settings handler
	@GetMapping("/settings")
	public String openSettings() {
		return "normal/settings";
	}
	
	//change password handler
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,Principal principal,HttpSession session ) {
		System.out.println("OLD PASSWORD "+oldPassword);
		System.out.println("NEW PASSWORD "+newPassword);
		
		String userName = principal.getName();
		User currentUser = this.userRepository.getUserByUserName(userName);
		System.out.println(currentUser.getPassword());
		if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			//change the password
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepository.save(currentUser);
			session.setAttribute("message", new Message("Your Passoword is Changed","success"));
		}
		else {
			//error..
			session.setAttribute("message", new Message("Please enter correct old Password","danger "));
			return "redirect:/user/settings";
		}
		
		return "redirect:/user/index";
	}
	
	//creating order for payment
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String,Object> data) throws RazorpayException {
		int amt = Integer.parseInt(data.get("amount").toString());
		var client=new RazorpayClient("rzp_test_haDRsJIQo9vFP", "owKJJes2fwE6YD6DToishFuH");
		JSONObject ob = new JSONObject();
		ob.put("amount","amt*100");
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		
		//creating new order
		Order order = client.Orders.create(ob);
		System.out.println(order);
		
		System.out.println("Hey order function executed");
		System.out.println(data);
		return "done";
		
	}
	
	
	
	
	
}

