<template>
    <div>
        <article>
            <div class="title"><a href="#" @click.prevent="onSeePost(post.id)">{{ post.title }} {{ post.id }}</a></div>
            <div class="body">{{ post.text }}</div>

            <div class="footer">
                <div class="left">
                    <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a>{{ commentsSize }}</a>
                </div>
            </div>
        </article>
        <Comments v-for="comment in comments"
                  :comment="comment" :user="users[comment.userId]" :key="comment.id"/>
    </div>
</template>

<script>
import Comments from "./Comments";
export default {
    name: "Post",
    components: {
        Comments
    },
    methods: {
        onSeePost: function (postId) {
            this.$root.$emit("onSeePost", postId);
        }
    },
    props: ["post", "users", "commentsSize", "comments"]
}
</script>

<style scoped>

</style>