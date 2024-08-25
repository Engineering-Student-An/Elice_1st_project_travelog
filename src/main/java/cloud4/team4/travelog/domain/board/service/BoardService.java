package cloud4.team4.travelog.domain.board.service;

import cloud4.team4.travelog.domain.board.dto.BoardRequestDto;
import cloud4.team4.travelog.domain.board.dto.BoardUpdateRequestDto;
import cloud4.team4.travelog.domain.board.dto.BoardUpdateResponseDto;
import cloud4.team4.travelog.domain.board.dto.BoardViewResponse;
import cloud4.team4.travelog.domain.board.entity.Board;
import cloud4.team4.travelog.domain.board.entity.BoardMapper;
import cloud4.team4.travelog.domain.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
//@NoArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    private final BoardPhotoService boardPhotoService;

    /*----------Create----------*/
    public void save(BoardRequestDto requestDto) {
        Board board = boardMapper.toEntity(requestDto);
        boardRepository.save(board);
    }

    @Transactional
    public void saveBoard(Long id, BoardRequestDto requestDto) {

        Board board = BoardMapper.INSTANCE.toEntity(requestDto);
        Board savedBoard = boardRepository.save(board);

        try {
            boardPhotoService.savePhoto(requestDto.getPhoto(), savedBoard);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /*----------Read----------*/
    // Todo: 사진을 포함한 모든 게시판 조회 - 완료
    public List<BoardViewResponse> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        List<BoardViewResponse> result = boards.stream()
                .map(BoardViewResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    // Todo: 사진을 포함, 대분류에 따른(regionMajor) 게시판 조회 - 완료
    public List<BoardViewResponse> getMiddleBoards(String regionMajor) {
        List<Board> boards = boardRepository.findByRegionMajor(regionMajor);

        List<BoardViewResponse> result = boards.stream()
                .map(BoardViewResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    /*----------Update----------*/
    // 업데이트
    @Transactional
    public BoardUpdateResponseDto update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(RuntimeException::new);

        board.update(requestDto.getDescription());
        return new BoardUpdateResponseDto(board);
    }

    /*public BoardUpdateResponseDto update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found with id: " + id));

        // DTO의 필드를 엔티티에 매핑하여 업데이트
        boardMapper.updateBoardFromDto(requestDto, board);
        boardRepository.save(board); // 엔티티 저장하여 변경 사항 반영

        return new BoardUpdateResponseDto(board);
    }*/

    /*----------Delete----------*/
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        boardRepository.delete(board);
    }



}