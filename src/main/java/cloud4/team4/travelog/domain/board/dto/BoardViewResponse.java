package cloud4.team4.travelog.domain.board.dto;

import cloud4.team4.travelog.domain.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardViewResponse {
    private Long id;
    private String description;
    private String regionMajor;
    private String regionMiddle;
    private String imagePath;
    private String boardCategory;

    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.description = board.getDescription();
        this.regionMajor = board.getRegionMajor();
        this.regionMiddle = board.getRegionMiddle();
        this.imagePath = board.getImagePath();
        this.boardCategory = board.getBoardCategory();
    }
}
