package jp.co.rngd.myspringboot;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rngd.myspringboot.controllers.MainController;
import jp.co.rngd.myspringboot.models.User;
import jp.co.rngd.myspringboot.models.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MySpringBootApplicationTestConfiguration.class)
@Transactional
class MySpringBootApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MainController target;

	@Before("execute")
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}
	
	@Test
	public void addNewUserで新規ユーザが作成される() throws Exception {

		this.mockMvc.perform(post("/demo/add")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("userName", "rinne-grid")
			.param("email", "foobar@any.com")
		);

		
	}
	
	@Test
	public void トップページにアクセスして作成したユーザが表示される() throws Exception {
		
		this.mockMvc.perform(get("/demo"))
		.andExpect(status().isOk())
		.andExpect(view().name("demo/list"))
		.andExpect(
				model().attribute("users", 
					hasItem
					(
					    HasPropertyWithValue.hasProperty(
					    		"userName", is("rinne-grid")
					    )
					)
				)
			).andExpect(
				model().attribute("users", 
					hasItem(
						HasPropertyWithValue.hasProperty(
								"email", is("foobar@any.com")
						)
					)
				)
			);
	}
	
	@Test
	void contextLoads() {
	}

}
