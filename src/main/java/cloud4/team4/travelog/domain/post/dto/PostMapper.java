package cloud4.team4.travelog.domain.post.dto;

import cloud4.team4.travelog.domain.post.entity.Post;
import cloud4.team4.travelog.domain.post.entity.PostPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import cloud4.team4.travelog.domain.member.entity.Member;

@Mapper(componentModel = "spring")
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);;

  Post postPostDtoToPost(PostPostDto postPostDto);

  @Mapping(source = "member", target="memberId")
  @Mapping(source = "postPhoto", target = "photos")
  PostResponseDto postToPostResponseDto(Post post);

  default Long mapMemberToLong(Member member) {
    return member != null ? member.getId() : null;
  }

//  @Mapping(target = "postId", ignore = true)
//  PostPhoto postPhotoDtoToPostPhoto(PostPhotoDto postPhotoDto);
//
//  @Mapping(source = "post", target = "postId")
//  PostPhotoResponseDto postPhotoToPostPhotoResponseDto(PostPhoto postPhoto);

  /*---------추가(디버깅)---------*/
  default String mapPostPhotoToString(PostPhoto postPhoto) {
    return postPhoto != null ? postPhoto.getImagePath() : null;
  }

}
