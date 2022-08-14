package com.charm1nk.store.service.impl;

import com.charm1nk.store.dto.GetMakersResponse;
import com.charm1nk.store.repository.MakerRepository;
import com.charm1nk.store.service.MakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MakerServiceImpl implements MakerService {

    private final MakerRepository makerRepository;

    public GetMakersResponse getMakers() {
        final var makers = makerRepository.findAll();
        return GetMakersResponse.from(makers, makers.size());
    }
}
