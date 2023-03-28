<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
      Count of users is : {{ usersSize }}, Count of posts is : {{ postsSize }}
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersSize", "postsSize"]
}
</script>

<style scoped>

</style>