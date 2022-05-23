package com.baltacristiandorin.homeassignment.service.implementations;

import com.baltacristiandorin.homeassignment.dto.PlaceDetailsDto;
import com.baltacristiandorin.homeassignment.entity.DayDetailsEntity;
import com.baltacristiandorin.homeassignment.entity.OpeningHoursEntity;
import com.baltacristiandorin.homeassignment.entity.PlaceDetailsEntity;
import com.baltacristiandorin.homeassignment.entity.embedded.DayDetailsEntityEmbeddedKey;
import com.baltacristiandorin.homeassignment.exception.ApplicationException;
import com.baltacristiandorin.homeassignment.service.PlaceDetailsService;
import com.baltacristiandorin.homeassignment.service.PlacesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier(value = "byRequest")
public class PlacesServiceByRequest implements PlacesService {
    private final ObjectMapper objectMapper;
    private final String placesUrl;
    private final String connectionTimeout;
    private final String readTimeout;
    private final PlaceDetailsService placeDetailsService;

    public PlacesServiceByRequest(
            @Qualifier("placesServiceObjectMapper") ObjectMapper objectMapper,
            @Value("${service.url.places}") String placesUrl,
            @Value("${service.timeout.connection}") String connectionTimeout,
            @Value("${service.timeout.read}") String readTimeout,
            PlaceDetailsService placeDetailsService) {
        this.objectMapper = objectMapper;
        this.placesUrl = placesUrl;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.placeDetailsService = placeDetailsService;
    }

    public PlaceDetailsDto getPlaceDetailsById(String placeId) {
        PlaceDetailsDto placeDetailsDto;
        try {
            URL url = new URL("%s%s".formatted(placesUrl, placeId));
            String requestMethod = "GET";
            Map<String, String> connectionProperties = new HashMap<>();
            connectionProperties.put("Content-Type", "application/json");

            HttpURLConnection httpURLConnection = getHttpURLConnection(url, requestMethod, connectionProperties);
            String response = getRequestContent(httpURLConnection).toString();
            placeDetailsDto = objectMapper.readValue(response, PlaceDetailsDto.class);
            saveOrUpdatePlaceDetails(placeId, placeDetailsDto);

        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        return placeDetailsDto;
    }

    private HttpURLConnection getHttpURLConnection(
            URL url, String requestMethod, Map<String, String> connectionProperties
    ) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        connectionProperties.forEach(httpURLConnection::setRequestProperty);
        httpURLConnection.setConnectTimeout(Integer.parseInt(connectionTimeout));
        httpURLConnection.setReadTimeout(Integer.parseInt(readTimeout));
        httpURLConnection.disconnect();

        return httpURLConnection;
    }

    private StringBuilder getRequestContent(HttpURLConnection httpURLConnection) throws IOException {

        @Cleanup BufferedReader in = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content;
    }

    private void saveOrUpdatePlaceDetails(String placeId, PlaceDetailsDto placeDetailsDto) {
        PlaceDetailsEntity placeDetailsEntity = PlaceDetailsEntity.builder()
                .id(placeId)
                .name(placeDetailsDto.getName())
                .location(placeDetailsDto.getLocation())
                .build();

        OpeningHoursEntity openingHoursEntity = OpeningHoursEntity.builder()
                .id(placeId)
                .openByArrangement(placeDetailsDto.getOpeningHoursDto().getOpenByArrangement())
                .closedOnHolidays(placeDetailsDto.getOpeningHoursDto().getClosedOnHolidays())
                .build();

        List<DayDetailsEntity> dayDetailsEntityList = new ArrayList<>();

        placeDetailsDto.getOpeningHoursDto().getOpeningHours().forEach((key, value) -> {
                    value.forEach(dayDetailsDto -> {
                        DayDetailsEntityEmbeddedKey dayDetailsEntityEmbeddedKey = DayDetailsEntityEmbeddedKey.builder()
                                .day(key)
                                .start(dayDetailsDto.getStart())
                                .end(dayDetailsDto.getEnd())
                                .type(dayDetailsDto.getType())
                                .build();

                        DayDetailsEntity dayDetailsEntity = DayDetailsEntity.builder()
                                .dayDetailsEntityEmbeddedKey(dayDetailsEntityEmbeddedKey)
                                .openingHoursEntity(openingHoursEntity)
                                .build();
                        dayDetailsEntityList.add(dayDetailsEntity);
                    });
                }
        );

        openingHoursEntity.setDayDetailsEntities(dayDetailsEntityList);
        openingHoursEntity.setPlaceDetailsEntity(placeDetailsEntity);
        placeDetailsEntity.setOpeningHoursEntity(openingHoursEntity);
        placeDetailsService.save(placeDetailsEntity);
    }
}
