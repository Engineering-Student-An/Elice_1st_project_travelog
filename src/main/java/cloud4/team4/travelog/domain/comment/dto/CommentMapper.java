package cloud4.team4.travelog.domain.comment.dto;

import cloud4.team4.travelog.domain.comment.entity.Comment;
import cloud4.team4.travelog.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "member", target="memberId")
    CommentResponseDto toResponseDto(Comment comment);

    default Long mapMemberToLong(Member member) {
        return member != null ? member.getId() : null;
    }

    @Mapping(target = "member", ignore = true) // member 필드 매핑 제외
    @Mapping(target = "created_at", expression = "java(java.time.LocalDateTime.now())") // 현재 시각으로 createdAt 매핑
    Comment toEntity(CommentRequestDto commentRequestDto);
}
