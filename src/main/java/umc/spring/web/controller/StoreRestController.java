package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPaylaod.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    // 특정 지역에 가게 추가
    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(@RequestBody @Valid StoreRequestDTO.CreateStoreDTO request){
        Store store = storeQueryService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDTO(store));
    }
}
