package trend.project.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "서버 안정성 체크 API")
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "health check";
    }

    @RequestMapping("/test")
    public void test() {
        throw new RuntimeException("Error!!");
    }
}
