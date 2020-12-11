package social.network.springboot.ServiceImps;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import social.network.springboot.Services.UploadFileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImp implements UploadFileService {
	@Override
	public void saveImage(MultipartFile file) throws Exception {
		String folder = "/upload-dir";
		byte[] bytes = file.getBytes();
		Path path = Paths.get(folder + file.getOriginalFilename());
		Files.write(path, bytes);
	}
}
