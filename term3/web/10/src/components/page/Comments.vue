<template>
    <table>
        <tr>
            <td class="text">Author: {{ user.name }} &&</td>
            <td class="creationTime">Text: {{ comment.text }}</td>
        </tr>
    </table>
</template>

<script>
export default {
    name: "Comment",
    props: ["comment", "user"]
}
</script>

<style scoped>

</style>