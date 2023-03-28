<template>
    <div>
        <Post v-for="post in Object.values(posts).sort((a, b) => b.id - a.id)"
              :post="post"
              :commentsSize="Object.values(comments).filter(c => c.postId === post.id).length"
              :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/page/Post.vue";

export default {
    name: "Index",
    components: {
        Post
    },
    props: ["posts", "comments"]
}
</script>

<style scoped>

</style>
