package social.network.springboot.Services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

	public void saveImage(MultipartFile file) throws Exception;
}
