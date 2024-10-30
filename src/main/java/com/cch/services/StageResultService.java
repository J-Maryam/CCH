package com.cch.services;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.request.StageResultRequestDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.dtos.response.StageResponseDTO;
import com.cch.dtos.response.StageResultResponseDTO;
import com.cch.entities.StageResult;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.entities.embeddebals.StageResultId;

import java.util.List;

public interface StageResultService extends GenericService<StageResult, StageResultId, StageResultRequestDTO, StageResultResponseDTO> {

}
