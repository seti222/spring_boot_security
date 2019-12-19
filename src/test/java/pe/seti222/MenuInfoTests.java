package pe.seti222;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pe.seti222.domain.menu.MenuInfo;
import pe.seti222.service.menu.MenuInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MenuInfoTests {
	
	@Autowired
	private MenuInfoService menuInfoService;
	
	
	@Test
	public void getloadAllTest() {
		List<MenuInfo> all = menuInfoService.findAll();
		System.out.println("=====================getloadAllTest===========================");
		System.out.println(all);
		System.out.println("===========================end================================");

	}
}
