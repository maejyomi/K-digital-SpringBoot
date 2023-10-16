package abc.def;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ABCController {
	
	public ABCController() {
		System.out.println("ABCCOntroller() 생성자 호출");
	}

}
