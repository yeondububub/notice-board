package notice.board.articleread.controller;

import lombok.RequiredArgsConstructor;
import notice.board.articleread.service.ArticleReadService;
import notice.board.articleread.service.response.ArticleReadResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleReadController {

    private final ArticleReadService articleReadService;

    @GetMapping("/v1/articles/{articleId}")
    public ArticleReadResponse read(@PathVariable Long articleId){
        return articleReadService.read(articleId);
    }
}
