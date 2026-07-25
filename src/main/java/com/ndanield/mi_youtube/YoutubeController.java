package com.ndanield.mi_youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class YoutubeController {

    private final RestClient restClient;

    @Value("${youtube.api.key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(YoutubeController.class);

    public YoutubeController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://www.googleapis.com/youtube/v3").build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("query") String query) {
        try {
            // Usamos el cliente fluido para la petición
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                    .path("/search")
                    .queryParam("part", "snippet")
                    .queryParam("type", "video")
                    .queryParam("maxResults", 15)
                    .queryParam("q", query)
                    .queryParam("key", apiKey)
                    .build())
                    .retrieve()
                    .body(Object.class);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // IMPORTANTE: Esto imprimirá el error real en tu terminal de VS Code
        
            logger.error("Error al buscar videos en YouTube: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }

}
