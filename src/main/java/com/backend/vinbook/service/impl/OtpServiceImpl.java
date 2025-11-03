package com.backend.vinbook.service.impl;


import com.backend.vinbook.entity.Otp;
import com.backend.vinbook.repository.OtpRepository;
import com.backend.vinbook.service.OtpService;
import com.backend.vinbook.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;

    @Override
    public String generateOtp(String email)  {

        String otpCode = OtpUtil.generateNumericOtp(6);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expires = now.plusMinutes(5); // OTP hết hạn sau 5 phút

        Otp otp = Otp.builder()
                .code(otpCode)
                .createdAt(now)
                .expiresAt(expires)
                .email(email).build();
        otpRepository.save(otp);



        return otpCode;
    }

    @Override
    public boolean verifyOtp(String email, String code) {
        Optional<Otp> otpOpt = otpRepository.findByEmailAndCode(email, code);
        log.info("otp trong {}" , otpOpt.isEmpty());
        if (otpOpt.isEmpty()) return false;

        Otp otp = otpOpt.get();

        if (otp.isUsed()) return false;
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) return false;

        otp.setUsed(true);
        otpRepository.save(otp);
        return true;
    }
}
