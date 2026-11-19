package com.travel.travelPalette.domain.travelalert.service;

import com.travel.travelPalette.domain.travelalert.client.MofaApiClient;
import com.travel.travelPalette.domain.travelalert.dto.MofaApiResponse;
import com.travel.travelPalette.domain.travelalert.dto.TravelAlertDetailResponse;
import com.travel.travelPalette.domain.travelalert.dto.TravelAlertListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TravelAlertService {

    private final MofaApiClient mofaApiClient;

    // 1. 최신 3개 리스트 조회 (API 직통)
    public List<TravelAlertListResponse> getLatestAdjustments() {
        List<MofaApiResponse.Item> items = fetchItemsFromApi(1, 3);

        return IntStream.range(0, items.size())
                .mapToObj(i -> {
                    MofaApiResponse.Item item = items.get(i);
                    return TravelAlertListResponse.builder()
                            .id((long) (i + 1))
                            .countryName(item.getCountryNm())
                            .countryEnName(item.getCountryEnNm())
                            .alarmLevel(item.getAlarmLvl())
                            .regionType(item.getRegionTy())
                            .title(item.getTitle())
                            .writtenDate(item.getWrtDt())
                            .build();
                })
                .toList();
    }

    // 2. 상세 조회 (임시 순번 1, 2, 3 기준)
    public TravelAlertDetailResponse getAdjustmentDetail(Long alertId) {
        List<MofaApiResponse.Item> items = fetchItemsFromApi(1, 10);
        int index = alertId.intValue() - 1;

        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("해당 여행경보 조정 내역을 찾을 수 없습니다. id=" + alertId);
        }

        MofaApiResponse.Item item = items.get(index);
        return TravelAlertDetailResponse.builder()
                .id(alertId)
                .countryName(item.getCountryNm())
                .countryEnName(item.getCountryEnNm())
                .continentName(item.getContinentNm())
                .alarmLevel(item.getAlarmLvl())
                .regionType(item.getRegionTy())
                .remark(item.getRemark())
                .title(item.getTitle())
                .content(item.getTxtOriginCn())
                .writtenDate(item.getWrtDt())
                .build();
    }

    private List<MofaApiResponse.Item> fetchItemsFromApi(int pageNo, int numOfRows) {
        MofaApiResponse response = mofaApiClient.fetchTravelAlarmAdjustments(pageNo, numOfRows);
        if (response == null || response.getResponse() == null
                || response.getResponse().getBody() == null
                || response.getResponse().getBody().getItems() == null) {
            return Collections.emptyList();
        }
        return response.getResponse().getBody().getItems().getItem();
    }
}