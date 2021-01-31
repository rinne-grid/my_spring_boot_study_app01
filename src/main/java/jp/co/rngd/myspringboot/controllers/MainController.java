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
	
	// デフォルトハンドラ
	@GetMapping
	public String getAllUsers(Model model) {
		var users = userRepository.findAll();
		model.addAttribute("users", users);
		return "demo/list";
	}
	
	// Pathの値を取得する際は@PathVariableアノテーションを利用する
	@GetMapping(path="{id}/edit")
	public String editUser(@PathVariable Integer id, Model model) {
		User editUser = userRepository.getOne(id);
		model.addAttribute("user", editUser);
		return "demo/edit";
	}
	
	// CORS対応
	@CrossOrigin
	// PutMappingやDeleteMappingを利用する際、spring.mvc.hiddenmethod.filter.enabled=trueをapplication.propertiesに指定する
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
