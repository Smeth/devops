package smeth.dev.devops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SystemInfoController {

    @GetMapping("/")
    public Map<String, String> getDefaultMessage() {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Bienvenue sur l'API DevOps !");
        message.put("endpoints", "/api/info - Affiche l'heure actuelle et le nom du PC");
        return message;
    }

    @GetMapping("/info")
    public Map<String, String> getSystemInfo() {
        Map<String, String> info = new HashMap<>();
        
        // Heure actuelle
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        info.put("heureActuelle", now.format(formatter));
        
        // Nom du PC
        String hostName = System.getenv("COMPUTERNAME");
        if (hostName == null || hostName.isEmpty()) {
            hostName = System.getenv("HOSTNAME");
        }
        if (hostName == null || hostName.isEmpty()) {
            hostName = "Inconnu";
        }
        info.put("nomPC", hostName);
        
        return info;
    }
}

