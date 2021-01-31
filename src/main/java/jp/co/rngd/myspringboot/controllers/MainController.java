package jp.co.rngd.myspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rngd.myspringboot.models.User;
import jp.co.rngd.myspringboot.models.UserRepository;

@Controller
@RequestMapping("/demo")
public class MainController {
	
	private UserRepository userRepository;
	
	@Autowired
	public MainController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping(path="/add")
	public String addNewUser(@ModelAttribute User user) {
		User newUser = new User();
		newUser.setUserName(user.getUserName());
		newUser.setEmail(user.getEmail());
		userRepository.save(user);
		return "redirect:/demo";
	}
	
	// �f�t�H���g�n���h��
	@GetMapping
	public String getAllUsers(Model model) {
		var users = userRepository.findAll();
		model.addAttribute("users", users);
		return "demo/list";
	}
	
	// Path�̒l���擾����ۂ�@PathVariable�A�m�e�[�V�����𗘗p����
	@GetMapping(path="{id}/edit")
	public String editUser(@PathVariable Integer id, Model model) {
		User editUser = userRepository.getOne(id);
		model.addAttribute("user", editUser);
		return "demo/edit";
	}
	
	// CORS�Ή�
	@CrossOrigin
	// PutMapping��DeleteMapping�𗘗p����ہAspring.mvc.hiddenmethod.filter.enabled=true��application.properties�Ɏw�肷��
	@PutMapping(path="{id}/update")
	public String update(@PathVariable Integer id, @ModelAttribute User user) {
		user.setId(id);
		userRepository.save(user);		
		return "redirect:/demo";
	}
	@CrossOrigin
	@GetMapping(path="{id}/delete")
	public String delete(@PathVariable Integer id) {
		userRepository.deleteById(id);
		return "redirect:/demo";
	}
	
}
