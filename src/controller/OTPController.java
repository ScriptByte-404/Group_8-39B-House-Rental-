package controller;

import dao.UserDAO;

public class OTPController {

    UserDAO dao = new UserDAO();

    // VERIFY OTP
    public boolean verifyOTP(String email, String otp) {
        boolean verified = dao.verifyOTP(email, otp);
        if (verified) {
            dao.deleteOTP(email);  // delete after verified
        }
        return verified;
    }

    // RESEND OTP
    public boolean resendOTP(String email) {

        // Generate new OTP
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        // Save to DB (saveOTP already deletes old one first)
        boolean saved = dao.saveOTP(email, otp);

        if (saved) {
            // Send email
            return EmailUtility.sendOTP(email, otp);
        }

        return false;
    }
}