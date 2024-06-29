package com.hackathonpraktika.DevManagementSystem.utilities;

import org.springframework.web.multipart.MultipartFile;
import com.hackathonpraktika.DevManagementSystem.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.UUID;

@Slf4j
public class Utilities {
    private static final String USER_PROFILE_IMAGE_DIR = "./src/main/resources/static/assets/images/";

    public static String uploadProfileImage(MultipartFile file, Person person) {
        return uploadImage(file, person, USER_PROFILE_IMAGE_DIR);
    }

    private static String uploadImage(MultipartFile file, Object entity, String uploadDir) {
        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
        Path filePath = createDirectoryAndResolveFilePath(uploadDir, uniqueFileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            setFilePermissions(filePath); // Set file permissions
            deleteExistingImage(entity);
            return getRelativeImagePath(uploadDir, uniqueFileName);
        } catch (IOException e) {
            log.error("Failed to upload {} for {}: {}", "profile image", entity, e.getMessage());
            return "";
        }
    }

    // Method to set file permissions
    private static void setFilePermissions(Path filePath) throws IOException {
        Files.setPosixFilePermissions(filePath, PosixFilePermissions.fromString("rw-r--r--"));
    }

    private static Path createDirectoryAndResolveFilePath(String uploadDir, String uniqueFileName) {
        Path dirPath = Paths.get(uploadDir);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                log.error("Failed to create directory for {}: {}", uploadDir, e.getMessage());
                return null;
            }
        }
        return dirPath.resolve(uniqueFileName);
    }

    private static void deleteExistingImage(Object entity) throws IOException {
        if (entity instanceof Person person) {
            if (!StringUtils.isEmpty(person.getProfilePicture())) {
                Path previousImagePath = Paths.get("./src/main/resources/static/" + person.getProfilePicture());
                Files.deleteIfExists(previousImagePath);
            }
        }
    }

    private static String getRelativeImagePath(String uploadDir, String uniqueFileName) {
        return uploadDir.replace("./src/main/resources/static", "") + uniqueFileName;
    }

    private static String generateUniqueFileName(String originalFileName) {
        String fileNameWithoutExtension = FilenameUtils.removeExtension(originalFileName);
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        return fileNameWithoutExtension + "_" + UUID.randomUUID() + "." + fileExtension;
    }
}
