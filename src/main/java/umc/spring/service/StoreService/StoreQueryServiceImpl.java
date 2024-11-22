package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.apiPaylaod.exception.handler.StoreHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    // 특정 지역에 가게 추가
    @Override
    public Store createStore(StoreRequestDTO.CreateStoreDTO request){

        Store newStore = StoreConverter.toStore(request);

        // 지역 찾기
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.REGION_NOT_FOUND));
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }
}