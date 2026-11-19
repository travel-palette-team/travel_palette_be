package com.travel.travelPalette.domain.travelalert.controller;

import com.travel.travelPalette.domain.travelalert.dto.AlertAdjustmentResponse;
import com.travel.travelPalette.domain.travelalert.service.TravelAlertService;
import com.travel.travelPalette.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-alerts")
@RequiredArgsConstructor
public class TravelAlertController {

    private final TravelAlertService travelAlertService;

    /**
     * 1. 여행경보조정 최신 3개 리스트 조회 API
     * - 응답에 최신 3개 항목(items)과 외교부 이동 링크(moreUrl)가 함께 내려갑니다.
     */
    @GetMapping("/adjustments")
    public ResponseEntity<ApiResponse<AlertAdjustmentResponse.ListWrapper>> getLatestAlertAdjustments() {
        AlertAdjustmentResponse.ListWrapper response = travelAlertService.getLatestAlertAdjustments();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    /**
     * 2. 여행경보조정 게시글 상세보기 팝업 조회 API
     * - 리스트에서 특정 항목 클릭 시 호출합니다.
     */
    @GetMapping("/adjustments/{alertId}")
    public ResponseEntity<ApiResponse<AlertAdjustmentResponse.DetailPopup>> getAlertAdjustmentDetail(
            @PathVariable Long alertId
    ) {
        AlertAdjustmentResponse.DetailPopup response = travelAlertService.getAlertAdjustmentDetail(alertId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    /**
     * 3. (관리자/수동 테스트용) 외교부 API 수동 동기화 트리거
     */
    @PostMapping("/adjustments/sync")
    public ResponseEntity<ApiResponse<Void>> syncAlertAdjustments() {
        travelAlertService.syncAlertAdjustmentsFromMofa();
        return ResponseEntity.ok(ApiResponse.ok("여행경보조정 데이터 동기화가 완료되었습니다.", null));
    }
}