package pages;

import listeners.TestListener;
import utils.PropertyFileReaderUtil;
import utils.ZipUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.List;
import java.util.Properties;

public class EmailPage {

    private static final String FROM_EMAIL = "charanabhinav.pydimarri@optimworks.com";
    private static final String PASSWORD = PropertyFileReaderUtil.getProperty("mailPassword");
    private static final String[] TO_EMAILS = {"charanabhinav.pydimarri@optimworks.com"};
    private static final String SMTP_HOST = "smtp.gmail.com";
    public static final String ALLURE_RESULTS_DIR = "target/allure-results";
    public static final String ZIP_FILE_PATH = "target/allure-results.zip";

    public void sendSuiteResultsEmail(List<String> screenshotPaths) {
        try {
            if (PASSWORD == null || PASSWORD.isEmpty()) {
                throw new RuntimeException("❌ EMAIL_PASSWORD is not set. Check your properties file.");
            }

            File allureZipFile = ZipUtils.zipDirectory(ALLURE_RESULTS_DIR, ZIP_FILE_PATH);
            if (!allureZipFile.exists()) {
                throw new RuntimeException("❌ Failed to create Allure ZIP file.");
            }

            Session session = createEmailSession();
            Message message = createEmailMessage(session, allureZipFile, screenshotPaths);
            Transport.send(message);
            System.out.println("📧 Email sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Email sending failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Session createEmailSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", SMTP_HOST);

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
    }

    private Message createEmailMessage(Session session, File allureZipFile, List<String> screenshotPaths) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", TO_EMAILS)));
        message.setSubject("📝 TestNG Suite Execution Summary");

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(generateSuiteSummary());
        multipart.addBodyPart(textPart);

        // Attach Allure ZIP report
        addAttachment(multipart, allureZipFile);

        // Attach screenshots if available
        if (screenshotPaths != null && !screenshotPaths.isEmpty()) {
            for (String screenshotPath : screenshotPaths) {
                addAttachment(multipart, new File(screenshotPath));
            }
        }

        message.setContent(multipart);
        return message;
    }

    private String generateSuiteSummary() {
        return "📊 Test Suite Execution Summary:\n" +
                "✅ Passed: " + TestListener.getPassedTests() + "\n" +
                "❌ Failed: " + TestListener.getFailedTests() + "\n" +
                "⚠️ Skipped: " + TestListener.getSkippedTests() + "\n" +
                "📌 Total: " + TestListener.getTotalTests() + "\n" +
                "🔗 Allure Report: Attachments Included";
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
}
