<template>
    <section>
        <div class="header">
            {{ post.title }}
        </div>
        <div class="body">
            {{ post.text }}
        </div>
        <div class="footer">
            <a href="#" @click.prevent="onSeePost(post.id)">View all</a>
        </div>
    </section>
</template>

<script>
export default {
    name: "SidebarPost",
    props: ["post"],
    methods: {
        onSeePost: function (postId) {
            this.$root.$emit("onSeePost", postId);
        }
    }
}
</script>

<style scoped>
section {
    margin-bottom: 1rem;
}
</style>