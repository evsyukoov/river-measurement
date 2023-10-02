package ru.bot.evsukov.service.impl;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bot.evsukov.errors.BrouterException;
import ru.bot.evsukov.service.BrouterService;

import java.awt.geom.Point2D;

@Service
@Slf4j
public class BrouterServiceImpl implements BrouterService {

    private final static String URL = "http://brouter.de/brouter?lonlats=%s,%s|%s,%s&profile=river&alternativeidx=0&format=geojson";

    private final RestTemplate restTemplate;

    @Autowired
    public BrouterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public int getDistance(Point2D start, Point2D end) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(String.format(URL, start.getX(), start.getY(), end.getX(), end.getY()), Object.class);
            String distance = JsonPath.read(response.getBody(), "$.features[0].properties.track-length");
            return Integer.parseInt(distance);
        } catch (Exception e) {
            log.error("Error getting response from BrouterService", e);
            throw new BrouterException();
        }
    }
}
