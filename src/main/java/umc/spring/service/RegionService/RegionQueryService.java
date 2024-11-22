package umc.spring.service.RegionService;



import umc.spring.domain.Region;

import java.util.Optional;

public interface RegionQueryService {

    Optional<Region> findRegion(Long id);

}