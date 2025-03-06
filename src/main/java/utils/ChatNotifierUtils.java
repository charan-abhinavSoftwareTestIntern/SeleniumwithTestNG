package utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ChatNotifierUtils {

    private static final String CHAT_WEBHOOK_URL = PropertyFileReaderUtil.getProperty("chatWebhookUrl");

    public static void sendSuiteResultsToChat(String suiteName, String triggeredBy, int totalTests, int passedTests, int failedTests, int skippedTests, String reportLink) {
        try {
            String message = buildSuiteSummary(suiteName, triggeredBy, totalTests, passedTests, failedTests, skippedTests, reportLink);
            sendToChat(message);
        } catch (Exception e) {
            System.err.println("❌ Chat notification failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String buildSuiteSummary(String suiteName, String triggeredBy, int totalTests, int passedTests, int failedTests, int skippedTests, String reportLink) {
        return "📢 *Test Suite Execution Summary* 📢\n" +
                "🔹 *Suite Name*: " + suiteName + "\n" +
                "👤 *Triggered By*: " + triggeredBy + "\n" +
                "✅ *Passed*: " + passedTests + "\n" +
                "❌ *Failed*: " + failedTests + "\n" +
                "⚠️ *Skipped*: " + skippedTests + "\n" +
                "📌 *Total*: " + totalTests + "\n" +
                "🔗 *Report*: " + reportLink;
    }

    private static void sendToChat(String message) throws Exception {
        if (CHAT_WEBHOOK_URL == null || CHAT_WEBHOOK_URL.isEmpty()) {
            System.err.println("❌ Google Chat webhook URL is missing. Check your configuration.");
            return;
        }

        URL url = new URL(CHAT_WEBHOOK_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        String jsonPayload = "{ \"text\": \"" + message + "\" }";
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            System.out.println("📩 Chat notification sent successfully!");
        } else {
            System.err.println("❌ Failed to send chat notification. Response Code: " + responseCode);
        }
    }
}
