package com.hackathonpraktika.DevManagementSystem.services;
import com.hackathonpraktika.DevManagementSystem.model.DevSkill;
import com.hackathonpraktika.DevManagementSystem.model.Person;
import com.hackathonpraktika.DevManagementSystem.model.PersonProject;
import com.hackathonpraktika.DevManagementSystem.repositories.DevSkillRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonProjectRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfGenerationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonProjectRepository personProjectRepository;

    public byte[] generatePdf() throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Developer Details:");
                contentStream.newLineAtOffset(0, -20);

                // Fetch data from database
                List<Person> developers = personRepository.findAll();

                for (Person developer : developers) {
                    String name = developer.getName();;
                    contentStream.showText("Name: " + name);
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Surname: " + developer.getSurname());
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Email: " + developer.getEmail());
                    contentStream.newLineAtOffset(0, -15);

                    // Fetch projects associated with the developer
//                    List<PersonProject> projects = personProjectRepository.findByPersonId(developer.getPersonId());
//                    contentStream.showText("Projects:");
//                    contentStream.newLineAtOffset(0, -15);
//                    for (PersonProject project : projects) {
//                        contentStream.showText("- " + project.getProjects().getProjectName());
//                        contentStream.newLineAtOffset(0, -15);
//                    }

                    contentStream.newLineAtOffset(0, -30); // increased spacing between developers
                }

                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        }
    }


    public byte[] generatePdfForDeveloper(Long developerId) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                // Fetch data for the specific developer
                Person developer = personRepository.findById(developerId)
                        .orElseThrow(() -> new RuntimeException("Developer not found with id: " + developerId));

                contentStream.showText("Developer Details:");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Name: " + developer.getName());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Surname: " + developer.getSurname());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Email: " + developer.getEmail());
                contentStream.newLineAtOffset(0, -15);
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        }
    }

}
