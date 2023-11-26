package com.example.spring_jobs.post.repository;

import com.example.spring_jobs.comment.entity.QComment;
import com.example.spring_jobs.like.entity.QLike;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostCustomRepository {

    private final JPAQueryFactory factory;
    QComment qComment = QComment.comment;
    QPost qPost = QPost.post;
    QLike qLike = QLike.like;

    public Optional<Post> findPostAndCommentAndLike(Long id){
        return Optional.ofNullable(factory.select(qPost)
                .from(qPost)
                .leftJoin(qPost.commentList, qComment)
                .leftJoin(qPost.likeList, qLike)
                .distinct()
                .fetchJoin()
                .where(qPost.id.eq(id))
                .fetchOne());
    }
}
