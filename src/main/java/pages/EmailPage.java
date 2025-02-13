// This will send the Zip files to mail.

//package pages;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.*;
//import java.nio.file.*;
//import java.util.List;
//import java.util.Properties;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//public class EmailPage {
//
//    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com"; // ✅ Your email
//    private static final String PASSWORD = "dkvl xaas gfhe fiks"; // ✅ Use environment variable for security
//    private static final String TO_EMAIL = "nikhil.pachipala@optimworks.com"; // ✅ Send to mail
//
//    public void sendTestResultsWithAttachments(List<String> screenshotPaths) {
//        try {
//            // ✅ Ensure Allure results exist before proceeding
//            File allureResultsDir = new File("target/allure-results");
//            waitForAllureResults(allureResultsDir);
//
//            // ✅ Zip the Allure results
//            File allureZipFile = zipAllureResults(allureResultsDir);
//
//            // ✅ Email properties (Use TLS with Port 587)
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
//            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//            properties.put("mail.smtp.host", "smtp.gmail.com");
//            properties.put("mail.smtp.port", "587");
//            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//            // ✅ Authenticate email session
//            Session session = Session.getInstance(properties, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
//                }
//            });
//
//            session.setDebug(true); // ✅ Enable debug logging for troubleshooting
//
//            // ✅ Create email message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
//            message.setSubject("📝 Test Execution Report with Screenshots");
//
//            Multipart multipart = new MimeMultipart();
//
//            // ✅ Add email body
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("📊 Find attached the test execution screenshots and Allure results.");
//            multipart.addBodyPart(messageBodyPart);
//
//            // ✅ Attach screenshots
//            for (String screenshotPath : screenshotPaths) {
//                File file = new File(screenshotPath);
//                if (file.exists()) {
//                    MimeBodyPart attachmentPart = new MimeBodyPart();
//                    DataSource source = new FileDataSource(screenshotPath);
//                    attachmentPart.setDataHandler(new DataHandler(source));
//                    attachmentPart.setFileName(file.getName());
//                    multipart.addBodyPart(attachmentPart);
//                } else {
//                    System.err.println("⚠️ Screenshot not found: " + screenshotPath);
//                }
//            }
//
//            // ✅ Attach Allure Results (ZIP)
//            if (allureZipFile.exists()) {
//                MimeBodyPart allurePart = new MimeBodyPart();
//                DataSource allureSource = new FileDataSource(allureZipFile);
//                allurePart.setDataHandler(new DataHandler(allureSource));
//                allurePart.setFileName(allureZipFile.getName());
//                multipart.addBodyPart(allurePart);
//            } else {
//                System.err.println("⚠️ Allure results ZIP not found: " + allureZipFile.getAbsolutePath());
//            }
//
//            message.setContent(multipart);
//
//            // ✅ Send email
//            Transport.send(message);
//            System.out.println("📧 Email sent successfully to " + TO_EMAIL);
//
//        } catch (Exception e) {
//            System.err.println("❌ Failed to send email: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Email sending failed due to missing attachments.");
//        }
//    }
//
//    /**
//     * ✅ Ensures Allure results exist before sending the email.
//     * ✅ Waits up to 30 seconds for files to be generated.
//     */
//    private void waitForAllureResults(File allureResultsDir) {
//        int retries = 6;
//        while (retries > 0 && (!allureResultsDir.exists() || allureResultsDir.list().length == 0)) {
//            System.out.println("⌛ Waiting for Allure results...");
//            try {
//                Thread.sleep(5000); // Wait 5 seconds before retrying
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            retries--;
//        }
//
//        if (!allureResultsDir.exists() || allureResultsDir.list().length == 0) {
//            throw new RuntimeException("❌ Allure results folder missing after waiting. Cannot send email.");
//        }
//    }
//
//    /**
//     * ✅ Zips the Allure results folder to ensure it's attachable.
//     * ✅ Deletes old ZIP file before creating a new one.
//     */
//    private File zipAllureResults(File sourceDir) throws IOException {
//        String zipFileName = "target/allure-results.zip";
//        File zipFile = new File(zipFileName);
//
//        if (zipFile.exists()) {
//            zipFile.delete(); // Delete existing ZIP file
//        }
//
//        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
//            Path sourcePath = sourceDir.toPath();
//            Files.walk(sourcePath).forEach(path -> {
//                try {
//                    ZipEntry zipEntry = new ZipEntry(sourcePath.relativize(path).toString());
//                    zos.putNextEntry(zipEntry);
//                    if (!Files.isDirectory(path)) {
//                        Files.copy(path, zos);
//                    }
//                    zos.closeEntry();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        return zipFile;
//    }
//}

// This code will send the UnZip files to mail.

//package pages;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.File;
//import java.util.List;
//import java.util.Properties;
//
//public class EmailPage {
//
//    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com";
//    private static final String PASSWORD = "dkvl xaas gfhe fiks";
//    private static final String TO_EMAIL = "charanabhinav.pydimarri@optimworks.com";
//
//    public void sendTestResultsWithAttachments(List<String> screenshotPaths, List<String> allureFiles) {
//        try {
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
//            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//            properties.put("mail.smtp.host", "smtp.gmail.com");
//            properties.put("mail.smtp.port", "587");
//            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//            Session session = Session.getInstance(properties, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
//                }
//            });
//
//            session.setDebug(true);
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
//            message.setSubject("📝 Test Execution Report with Screenshots and Allure Results");
//
//            Multipart multipart = new MimeMultipart();
//
//            // ✅ Email Body
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("📊 Find attached the test execution screenshots and Allure results.");
//            multipart.addBodyPart(messageBodyPart);
//
//            // ✅ Attach screenshots
//            attachFilesToEmail(multipart, screenshotPaths);
//
//            // ✅ Attach Allure result files (instead of ZIP)
//            attachFilesToEmail(multipart, allureFiles);
//
//            message.setContent(multipart);
//
//            // ✅ Send email
//            Transport.send(message);
//            System.out.println("📧 Email sent successfully to " + TO_EMAIL);
//
//        } catch (MessagingException e) {
//            System.err.println("❌ Failed to send email: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void attachFilesToEmail(Multipart multipart, List<String> filePaths) {
//        for (String filePath : filePaths) {
//            File file = new File(filePath);
//            if (file.exists()) {
//                try {
//                    MimeBodyPart attachmentPart = new MimeBodyPart();
//                    DataSource source = new FileDataSource(filePath);
//                    attachmentPart.setDataHandler(new DataHandler(source));
//                    attachmentPart.setFileName(file.getName());
//                    multipart.addBodyPart(attachmentPart);
//                } catch (MessagingException e) {
//                    System.err.println("❌ Error attaching file: " + filePath);
//                }
//            } else {
//                System.err.println("⚠️ File not found: " + filePath);
//            }
//        }
//    }
//}

// The below code will send mail along with subject.

//package pages;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.File;
//import java.util.List;
//import java.util.Properties;
//
//public class EmailPage {
//
//    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com";
//    private static final String PASSWORD = "dkvl xaas gfhe fiks";
//    private static final String TO_EMAIL = "charanabhinav.pydimarri@optimworks.com";
//
//    public void sendTestResultsWithAttachments(List<String> screenshotPaths, List<String> allureFiles, String subject, String emailBody) {
//        try {
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
//            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//            properties.put("mail.smtp.host", "smtp.gmail.com");
//            properties.put("mail.smtp.port", "587");
//            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//            Session session = Session.getInstance(properties, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
//                }
//            });
//
//            session.setDebug(true);
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
//            message.setSubject(subject);
//
//            Multipart multipart = new MimeMultipart();
//
//            // ✅ Email Body
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(emailBody);
//            multipart.addBodyPart(messageBodyPart);
//
//            // ✅ Attach screenshots
//            attachFilesToEmail(multipart, screenshotPaths);
//
//            // ✅ Attach Allure result files
//            attachFilesToEmail(multipart, allureFiles);
//
//            message.setContent(multipart);
//
//            // ✅ Send email
//            Transport.send(message);
//            System.out.println("📧 Email sent successfully to " + TO_EMAIL);
//
//        } catch (MessagingException e) {
//            System.err.println("❌ Failed to send email: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void attachFilesToEmail(Multipart multipart, List<String> filePaths) {
//        for (String filePath : filePaths) {
//            File file = new File(filePath);
//            if (file.exists()) {
//                try {
//                    MimeBodyPart attachmentPart = new MimeBodyPart();
//                    DataSource source = new FileDataSource(filePath);
//                    attachmentPart.setDataHandler(new DataHandler(source));
//                    attachmentPart.setFileName(file.getName());
//                    multipart.addBodyPart(attachmentPart);
//                } catch (MessagingException e) {
//                    System.err.println("❌ Error attaching file: " + filePath);
//                }
//            } else {
//                System.err.println("⚠️ File not found: " + filePath);
//            }
//        }
//    }
//}

// The below code will send the results to multiple mails at a time.

package pages;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EmailPage {

    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com"; // ✅ Sender email
    private static final String PASSWORD = "dkvl xaas gfhe fiks"; // ✅ Use environment variable for security
    private static final String[] TO_EMAILS = {
            "charanabhinav.pydimarri@optimworks.com"
    }; // ✅ Multiple recipients

    public void sendTestResultsWithAttachments(List<String> screenshotPaths) {
        try {
            // ✅ Ensure Allure results exist before proceeding
            File allureResultsDir = new File("target/allure-results");
            waitForAllureResults(allureResultsDir);

            // ✅ Zip the Allure results
            File allureZipFile = zipAllureResults(allureResultsDir);

            // ✅ Email properties (Use TLS with Port 587)
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            // ✅ Authenticate email session
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            session.setDebug(true); // ✅ Enable debug logging for troubleshooting

            // ✅ Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));

            // ✅ Add multiple recipients
            InternetAddress[] recipientAddresses = new InternetAddress[TO_EMAILS.length];
            for (int i = 0; i < TO_EMAILS.length; i++) {
                recipientAddresses[i] = new InternetAddress(TO_EMAILS[i]);
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);

            message.setSubject("📝 Test Execution Report with Screenshots");

            Multipart multipart = new MimeMultipart();

            // ✅ Add email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("📊 Find attached the test execution screenshots and Allure results.");
            multipart.addBodyPart(messageBodyPart);

            // ✅ Attach screenshots
            for (String screenshotPath : screenshotPaths) {
                File file = new File(screenshotPath);
                if (file.exists()) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(screenshotPath);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getName());
                    multipart.addBodyPart(attachmentPart);
                } else {
                    System.err.println("⚠️ Screenshot not found: " + screenshotPath);
                }
            }

            // ✅ Attach Allure Results (ZIP)
            if (allureZipFile.exists()) {
                MimeBodyPart allurePart = new MimeBodyPart();
                DataSource allureSource = new FileDataSource(allureZipFile);
                allurePart.setDataHandler(new DataHandler(allureSource));
                allurePart.setFileName(allureZipFile.getName());
                multipart.addBodyPart(allurePart);
            } else {
                System.err.println("⚠️ Allure results ZIP not found: " + allureZipFile.getAbsolutePath());
            }

            message.setContent(multipart);

            // ✅ Send email
            Transport.send(message);
            System.out.println("📧 Email sent successfully to multiple recipients!");

        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Email sending failed due to missing attachments.");
        }
    }

    /**
     * ✅ Ensures Allure results exist before sending the email.
     * ✅ Waits up to 30 seconds for files to be generated.
     */
    private void waitForAllureResults(File allureResultsDir) {
        int retries = 6;
        while (retries > 0 && (!allureResultsDir.exists() || allureResultsDir.list().length == 0)) {
            System.out.println("⌛ Waiting for Allure results...");
            try {
                Thread.sleep(5000); // Wait 5 seconds before retrying
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retries--;
        }

        if (!allureResultsDir.exists() || allureResultsDir.list().length == 0) {
            throw new RuntimeException("❌ Allure results folder missing after waiting. Cannot send email.");
        }
    }

    /**
     * ✅ Zips the Allure results folder to ensure it's attachable.
     * ✅ Deletes old ZIP file before creating a new one.
     */
    private File zipAllureResults(File sourceDir) throws IOException {
        String zipFileName = "target/allure-results.zip";
        File zipFile = new File(zipFileName);

        if (zipFile.exists()) {
            zipFile.delete(); // Delete existing ZIP file
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            Path sourcePath = sourceDir.toPath();
            Files.walk(sourcePath).forEach(path -> {
                try {
                    ZipEntry zipEntry = new ZipEntry(sourcePath.relativize(path).toString());
                    zos.putNextEntry(zipEntry);
                    if (!Files.isDirectory(path)) {
                        Files.copy(path, zos);
                    }
                    zos.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return zipFile;
    }
}
