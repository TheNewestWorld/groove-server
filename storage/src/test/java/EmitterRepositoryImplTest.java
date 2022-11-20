import org.bogus.groove.storage.repository.EmitterRepository;
import org.bogus.groove.storage.repository.EmitterRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class EmitterRepositoryImplTest {
    private EmitterRepository emitterRepository = new EmitterRepositoryImpl();
    private Long DEFAULT_TIME_OUT = 60L * 1000L * 60L;

    @Test
    @DisplayName("새로운 Emitter 추가")
    public void save() throws Exception {
        Long memberId = 1L;
        String emitterId = memberId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIME_OUT);

        Assertions.assertDoesNotThrow(() -> emitterRepository.save(emitterId, sseEmitter));
    }
}
