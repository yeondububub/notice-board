package notice.board.articleread.cache;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

class OptimizedCacheTTLTest {

    @Test
    void ofTest() {
        long ttlSeconds = 10;

        OptimizedCacheTTL optimizedCacheTTL = OptimizedCacheTTL.of(ttlSeconds);

        assertThat(optimizedCacheTTL.getLogicalTTL()).isEqualTo(Duration.ofSeconds(ttlSeconds));
        assertThat(optimizedCacheTTL.getPhysicalTTL()).isEqualTo(
                Duration.ofSeconds(ttlSeconds)
                        .plusSeconds(OptimizedCacheTTL.PHYSICAL_TTL_DELAY_SECONDS)
        );
    }
}