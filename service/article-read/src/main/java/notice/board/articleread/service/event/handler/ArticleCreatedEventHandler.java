package notice.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import notice.board.articleread.repository.ArticleIdListRepository;
import notice.board.articleread.repository.ArticleQueryModel;
import notice.board.articleread.repository.ArticleQueryModelRepository;
import notice.board.articleread.repository.BoardArticleCountRepository;
import notice.board.common.event.Event;
import notice.board.common.event.EventType;
import notice.board.common.event.payload.ArticleCreatedEventPayload;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ArticleCreatedEventHandler implements EventHandler<ArticleCreatedEventPayload> {

    private final ArticleIdListRepository articleIdListRepository;
    private final BoardArticleCountRepository boardArticleCountRepository;
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleCreatedEventPayload> event) {
        ArticleCreatedEventPayload payload = event.getPayload();
        articleQueryModelRepository.create(
                ArticleQueryModel.create(payload),
                Duration.ofDays(1)
        );
        articleIdListRepository.add(payload.getBoardId(), payload.getArticleId(), 1000L);
        boardArticleCountRepository.createOrUpdate(payload.getBoardId(), payload.getBoardArticleCount());
    }

    @Override
    public boolean supports(Event<ArticleCreatedEventPayload> event) {
        return EventType.ARTICLE_CREATED == event.getType();
    }
}
