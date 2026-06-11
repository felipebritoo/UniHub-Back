package com.unihub.backend.service;

import com.unihub.backend.dto.ChaveVirtualRequestDTO;
import com.unihub.backend.dto.ChaveVirtualResponseDTO;

public interface ChaveVirtualService {
    ChaveVirtualResponseDTO destravarSala(ChaveVirtualRequestDTO request);
}
