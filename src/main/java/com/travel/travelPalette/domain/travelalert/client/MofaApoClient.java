package com.travel.travelPalette.domain.travelalert.client;

import com.travel.travelPalette.domain.travelalert.dto.MofaAlertApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MofaApiClient {

    private final RestClient mofaRestClient;

    @Value("${external.mofa.base-url}")
    private String baseUrl;

    @Value("${external.mofa.service-key}")
    private String serviceKey;

    /**
     * 외교부 여행경보 조정내역 최신 데이터 조회
     */
    public List<MofaAlertApiResponse.Item> fetchLatestAlertAdjustments(int numOfRows) {
        try {
            // 공공데이터포털 serviceKey 인코딩 이슈 방지를 위해 URI 직접 조립
            URI uri = UriComponentsBuilder.fromUriString(baseUrl + "/getTravelAlarmAdjustList2")
                    .queryParam("serviceKey", serviceKey)
                    .queryParam("returnType", "JSON")
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", 1)
                    .build(true) // serviceKey가 이미 인코딩된 키일 경우 true
                    .toUri();

            MofaAlertApiResponse response = mofaRestClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(MofaAlertApiResponse.class);

            if (response != null && response.getData() != null) {
                return response.getData();
            }
        } catch (Exception e) {
            log.error("외교부 여행경보조정 API 호출 중 오류 발생: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}