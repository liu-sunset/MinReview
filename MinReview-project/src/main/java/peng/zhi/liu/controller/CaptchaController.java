package peng.zhi.liu.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.result.Result;

import javax.imageio.ImageIO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Slf4j
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    public static final String CAPTCHA_SESSION_KEY = "captcha_code";

    @GetMapping("/image")
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("请求数字图片验证码");
        // 生成验证码文本
        String capText = defaultKaptcha.createText();

        // 将验证码文本存入session
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_SESSION_KEY, capText);

        // 生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(capText);

        // 设置响应内容类型为图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 将图片写入响应输出流
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
            out.flush();
        }
    }

    @PostMapping("/verify")
    public boolean verifyCaptcha(@RequestParam String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(CAPTCHA_SESSION_KEY);

        if (code == null || !code.equalsIgnoreCase(sessionCode)) {
            return false;
        }

        // 验证成功后移除session中的验证码
        session.removeAttribute(CAPTCHA_SESSION_KEY);
        return true;
    }
}