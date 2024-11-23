package trend.project.service;

import org.springframework.stereotype.Service;
import trend.project.web.dto.HistoryDTO;

import java.util.List;

@Service
public interface SearchHistoryService {
    List<HistoryDTO.HistoryResponseDTO> getHistory(String username);
}
