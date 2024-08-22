package cloud4.team4.travelog.domain.post.dto;

import cloud4.team4.travelog.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);;

  Post postPostDtoToPost(PostPostDto postPostDto);

  @Mapping(target = "photos", expression = "java(mapPhotos(post.getPostPhoto()))")
  PostResponseDto postToPostResponseDto(Post post);


//  @Mapping(target = "postId", ignore = true)
//  PostPhoto postPhotoDtoToPostPhoto(PostPhotoDto postPhotoDto);
//
//  @Mapping(source = "post", target = "postId")
//  PostPhotoResponseDto postPhotoToPostPhotoResponseDto(PostPhoto postPhoto);

}
