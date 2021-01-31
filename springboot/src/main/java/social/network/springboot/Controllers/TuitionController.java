//package social.network.springboot.Controllers;
//
//
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import social.network.springboot.Entities.Tuition;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/tuition")
//public class TuitionController {
//
//
//	@GetMapping
//	public List<Tuition> getTuition() {
//		final String uri = "https://core.hou.edu.vn/mdm/rest/api/tuition/tuitionInforByStudent?studentCode=T001402";
//		final String author = "Bearer";
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("Authorization", author);
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
//
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
//
//		System.out.println(responseEntity.toString());
//		List<Tuition> tuitions = new ArrayList<>();
//
//		ResponseEntity<Tuition> responseTuition = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Tuition.class);
//		System.out.println(responseTuition.toString());
//
//		return tuitions;
//	}
//}
