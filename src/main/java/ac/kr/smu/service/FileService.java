package ac.kr.smu.service;

import ac.kr.smu.vo.FileVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public void saveAll(int postId, List<MultipartFile> files);
    public FileSystemResource getFileSystemResource(int id);
    public void deleteByPostId(int postId);
}


