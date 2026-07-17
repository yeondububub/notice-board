package notice.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import notice.board.articleread.repository.ArticleIdListRepository;
import notice.board.articleread.repository.ArticleQueryModelRepository;
import notice.board.articleread.repository.BoardArticleCountRepository;
import notice.board.common.event.Event;
import notice.board.common.event.EventType;
import notice.board.common.event.payload.ArticleDeletedEventPayload;
import notice.board.common.event.payload.ArticleUpdatedEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleDeletedEventHandler implements EventHandler<ArticleDeletedEventPayload> {

    private final ArticleIdListRepository articleIdListRepository;
    private final BoardArticleCountRepository boardArticleCountRepository;
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleDeletedEventPayload> event) {
        ArticleDeletedEventPayload payload = event.getPayload();
        articleIdListRepository.delete(payload.getBoardId(), payload.getArticleId());
        articleQueryModelRepository.delete(payload.getArticleId());
        boardArticleCountRepository.createOrUpdate( payload.getBoardId(), payload.getBoardArticleCount());
    }

    @Override
    public boolean supports(Event<ArticleDeletedEventPayload> event) {
        return EventType.ARTICLE_DELETED == event.getType();
    }
}
