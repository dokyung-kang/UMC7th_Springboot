package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);

    // 특정 지역에 가게 추가
    Store createStore(StoreRequestDTO.CreateStoreDTO request);

    // 가게 리뷰 목록 조회
    Page<Review> getReviewList(Long StoreId, Integer page);
}