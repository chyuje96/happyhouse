<template>
  <qna-input-item type="modify" @doModify="modifyArticle" />
</template>

<script>
import QnaInputItem from "@/components/qna/item/QnaInputItem.vue";
import http from "@/api/http";

export default {
  name: "QnaModify",
  components: {
    QnaInputItem,
  },
  methods: {
    modifyArticle(article) {
      http
        .put(`/qna/${article.no}`, {
          no: article.no,
          userId: article.userId,
          title: article.title,
          content: article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnaList" });
        });
    },
  },
};
</script>

<style></style>
