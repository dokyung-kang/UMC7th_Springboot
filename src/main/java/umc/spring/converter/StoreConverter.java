package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    // 특정 지역에 가게 추가
    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.CreateStoreDTO request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
