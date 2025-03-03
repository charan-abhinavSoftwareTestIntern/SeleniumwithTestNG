//// The below code will send the results to multiple mails at a time with the subject, screenshots and Zip files.
//
//package pages;
//
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
//
//public class EmailPage {
//
//    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com"; // ✅ Sender email
//    private static final String PASSWORD = "dkvl xaas gfhe fiks"; // ✅ Use environment variable for security
//    private static final String[] TO_EMAILS = {
//            "charanabhinav.pydimarri@optimworks.com"
//    }; // ✅ Multiple recipients
//
//    public void sendTestResultsWithAttachments(List<String> screenshotPaths, List<String> allureReportFiles) {
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
//
//            // ✅ Add multiple recipients
//            InternetAddress[] recipientAddresses = new InternetAddress[TO_EMAILS.length];
//            for (int i = 0; i < TO_EMAILS.length; i++) {
//                recipientAddresses[i] = new InternetAddress(TO_EMAILS[i]);
//            }
//            message.setRecipients(Message.RecipientType.TO, recipientAddresses);
//
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
//            System.out.println("📧 Email sent successfully to multiple recipients!");
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

package pages;

import utils.PropertyFileReaderUtil;

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

    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com";
    private static final String PASSWORD = PropertyFileReaderUtil.getProperty("mailPassword");
    private static final String[] TO_EMAILS = {"charanabhinav.pydimarri@optimworks.com"};
    private static final String SMTP_HOST = "smtp.gmail.com";  // Change if using another provider
    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
    private static final String ZIP_FILE_PATH = "target/allure-results.zip";

    public void sendTestResultsWithAttachments(List<String> screenshotPaths, List<String> allureZipPaths) {
        try {
            System.out.println("🔍 Checking EMAIL_PASSWORD: " + (PASSWORD != null ? "SET" : "NOT SET"));

            if (PASSWORD == null || PASSWORD.isEmpty()) {
                throw new RuntimeException("❌ EMAIL_PASSWORD is not set. Check your properties file.");
            }

            File allureResultsDir = new File(ALLURE_RESULTS_DIR);
            if (!allureResultsDir.exists() || allureResultsDir.listFiles().length == 0) {
                throw new RuntimeException("❌ Allure results folder is empty or missing!");
            }

            File allureZipFile = zipAllureResults(allureResultsDir);

            if (!allureZipFile.exists()) {
                throw new RuntimeException("❌ Failed to create Allure ZIP file.");
            }

            if (screenshotPaths.isEmpty()) {
                System.out.println("⚠️ No screenshots available to attach.");
            }

            Session session = createEmailSession();
            Message message = createEmailMessage(session, screenshotPaths, allureZipFile);
            Transport.send(message);
            System.out.println("📧 Email sent successfully!");

        } catch (MessagingException e) {
            System.err.println("❌ Email sending failed due to messaging error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Email sending failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Session createEmailSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");       // Gmail SMTP server
        properties.put("mail.smtp.port", "587");                  // Use 587 for TLS
        properties.put("mail.smtp.auth", "true");                 // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true");      // Enable STARTTLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Ensure TLS 1.2 and 1.3 are used
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Trust Gmail's SSL certificate

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
    }

    private Message createEmailMessage(Session session, List<String> screenshotPaths, File allureZipFile) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        InternetAddress[] recipientAddresses = new InternetAddress[TO_EMAILS.length];
        for (int i = 0; i < TO_EMAILS.length; i++) {
            recipientAddresses[i] = new InternetAddress(TO_EMAILS[i]);
        }
        message.setRecipients(Message.RecipientType.TO, recipientAddresses);
        message.setSubject("📝 Test Execution Report with Screenshots");

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("📊 Find attached the test execution screenshots and Allure results.");
        multipart.addBodyPart(textPart);

        addAttachments(multipart, screenshotPaths);
        addAttachment(multipart, allureZipFile);

        message.setContent(multipart);
        return message;
    }

    private void addAttachments(Multipart multipart, List<String> filePaths) throws MessagingException {
        for (String filePath : filePaths) {
            addAttachment(multipart, new File(filePath));
        }
    }

    private void addAttachment(Multipart multipart, File file) throws MessagingException {
        if (file.exists()) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(file.getName());
            multipart.addBodyPart(attachmentPart);
        } else {
            System.err.println("⚠️ File not found: " + file.getAbsolutePath());
        }
    }

    private File zipAllureResults(File sourceDir) throws IOException {
        File zipFile = new File(ZIP_FILE_PATH);
        if (zipFile.exists() && !zipFile.delete()) {
            throw new IOException("Failed to delete existing ZIP file: " + ZIP_FILE_PATH);
        }

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            Path sourcePath = sourceDir.toPath();
            Files.walk(sourcePath).forEach(path -> {
                try {
                    String zipEntryName = sourcePath.relativize(path).toString().replace("\\", "/");
                    ZipEntry zipEntry = new ZipEntry(zipEntryName);
                    zos.putNextEntry(zipEntry);
                    if (!Files.isDirectory(path)) {
                        Files.copy(path, zos);
                    }
                    zos.closeEntry();
                } catch (IOException e) {
                    throw new UncheckedIOException("Error while zipping file: " + path, e);
                }
            });
        }
        return zipFile;
    }
}
