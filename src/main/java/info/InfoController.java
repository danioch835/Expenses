package info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/info")
public class InfoController {

	@RequestMapping(value="/exceptions")
	public void handleServletException(HttpServletRequest request,
									   HttpServletResponse response) throws Exception {
		
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		
		if(response.isCommitted()) {
			return;
		} else {
			throw new Exception(throwable);
		}
		
		
	}
	
}
