package social.network.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import social.network.springboot.Services.UploadFileService;

@Controller
public class UploadFileController {

	@Autowired
	private UploadFileService uploadFileService;

	@RequestMapping(value = "/upload-avatar", method = RequestMethod.POST)
	public String uploadAvatar(@RequestParam("imageFile") MultipartFile imageFile) {
		try {
			uploadFileService.saveImage(imageFile);
		} catch (Exception e) {
		}
		return "redirect:/home";
	}
}
